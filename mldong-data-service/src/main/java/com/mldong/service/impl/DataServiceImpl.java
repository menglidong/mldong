package com.mldong.service.impl;

import com.mldong.common.base.CommonPage;
import com.mldong.common.base.OperateTypeEnum;
import com.mldong.common.base.constant.GlobalErrEnum;
import com.mldong.common.ds.TargetDataSource;
import com.mldong.common.exception.BizException;
import com.mldong.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Service
public class DataServiceImpl implements DataService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean isExsit(String tableName) {
        boolean flag = false;
        Connection connection = null;
        try {
            connection = jdbcTemplate.getDataSource().getConnection();
            DatabaseMetaData meta = connection.getMetaData();
            String type[] = {"TABLE", "VIEW"};
            ResultSet rs = meta.getTables(null, null, tableName, type);
            flag = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    @Override
    public Map<String, Object> get(String tableName, String pkPropertyName, Object pkPropertyValue) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from ");
        sql.append(tableName);
        sql.append(" where ").append(pkPropertyName).append("=?").append(" limit 1");
        try {
            return jdbcTemplate.queryForMap(sql.toString(), pkPropertyValue);
        } catch (EmptyResultDataAccessException e) {
            return new HashMap<>();
        }
    }
    @TargetDataSource(value = "#dbName")
    @Override
    public Map<String, Object> get(String dbName, String tableName, String pkPropertyName, Object pkPropertyValue) {
        return get(tableName, pkPropertyName, pkPropertyValue);
    }

    @Override
    public CommonPage<Map<String, Object>> list(String tableName, Map<String, Object> map, Integer pageNum, Integer pageSize) {
        if (!isExsit(tableName)) {
            throw new BizException(GlobalErrEnum.GL99990100);
        }
        StringBuilder sql = new StringBuilder();
        sql.append("select * from ");
        sql.append(tableName);
        StringBuilder countSql = new StringBuilder();
        countSql.append("select count(0) from ");
        countSql.append(tableName);
        StringBuilder whereSb = new StringBuilder();
        List<Object> params = buildCondition(whereSb, map);
        sql.append(whereSb);
        countSql.append(whereSb);
        CommonPage<Map<String, Object>> page = new CommonPage<>();
        long recordCount = jdbcTemplate.queryForObject(countSql.toString(),Long.class,params.toArray());
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page.setRecordCount(recordCount);
        long totalPage = (recordCount+ pageSize-1) / pageSize;
        page.setTotalPage(Long.valueOf(totalPage).intValue());
        if(recordCount > 0 ) {
            sql.append(" limit ?, ?");
            params.add(pageNum-1);
            params.add(pageSize);
            page.setRows(jdbcTemplate.queryForList(sql.toString(), params.toArray()));
        } else {
            page.setRows(new ArrayList<>());
        }
        return page;
    }
    @TargetDataSource(value = "#dbName")
    @Override
    public CommonPage<Map<String, Object>> list(String dbName, String tableName, Map<String, Object> map, Integer pageNum, Integer pageSize) {
        return list(tableName, map, pageNum, pageSize);
    }

    /**
     * 构建动态条件
     *
     * @param sb
     * @param map
     * @return
     */
    private List<Object> buildCondition(StringBuilder sb, Map<String, Object> map) {
        int i = 0;
        List<Object> params = new ArrayList<>();
        Set<Map.Entry<String, Object>> set = map.entrySet();
        Iterator<Map.Entry<String, Object>> iterator = set.iterator();
        while (iterator.hasNext()) {
            // 只处理key值满足m_EQ_xxx 类的参数
            Map.Entry<String, Object> entity = iterator.next();
            String key = entity.getKey();
            Object value = entity.getValue();
            String[] arr = key.split("_");
            if (!key.startsWith("m_") || arr.length < 3 || value == null) {
                // 只处理m_前辍的参数
                continue;
            }
            OperateTypeEnum ops[] = OperateTypeEnum.values();
            // 默认等值
            OperateTypeEnum operateType = OperateTypeEnum.EQ;
            for (OperateTypeEnum item : ops) {
                // 匹配查询操作
                if (item.name().equals(arr[1].toUpperCase())) {
                    operateType = item;
                    break;
                }
            }
            String column = String.join("_", Arrays.asList(arr).subList(2, arr.length));
            switch (operateType) {
                case EQ: case NE: case GT: case GE: case LT: case LE:
                    if (i == 0) {
                        sb.append(" where ").append(column).append(" ").append(operateType.getValue()).append(" ");
                    } else {
                        sb.append(" and ").append(column).append(" ").append(operateType.getValue()).append(" ");
                    }
                    params.add(value);
                    i++;
                    break;
                case BT: case NBT:
                    if (value instanceof Collection<?>) {
                        Collection<?> collection = (Collection<?>) value;
                        if (collection.size() == 2) {
                            if (i == 0) {
                                sb.append(" where ").append(column).append(" ").append(operateType.getValue()).append(" ");
                            } else {
                                sb.append(" and ").append(column).append(" ").append(operateType.getValue()).append(" ");
                            }
                            params.addAll(collection);
                            i++;
                        }
                    }
                    break;
                case LIKE:
                    if (i == 0) {
                        sb.append(" where ").append(column).append(" like ?");
                    } else {
                        sb.append(" and ").append(column).append(" like ?");
                    }
                    params.add("%"+value+"%");
                    i++;
                    break;
                case LLIKE:
                    if (i == 0) {
                        sb.append(" where ").append(column).append(" like ?");
                    } else {
                        sb.append(" and ").append(column).append(" like ?");
                    }
                    params.add("%"+value);;
                    i++;
                    break;
                case RLIKE:
                    if (i == 0) {
                        sb.append(" where ").append(column).append(" like ?");
                    } else {
                        sb.append(" and ").append(column).append(" like ?");
                    }
                    params.add(value+"%");
                    i++;
                    break;
                case IN: case NIN:
                    if (value instanceof Collection<?>) {
                        Collection<?> collection = (Collection<?>) value;
                        if (!collection.isEmpty()) {
                            if (i == 0) {
                                sb.append(" where ").append(column).append(" in(");
                            } else {
                                sb.append(" and ").append(column).append(" in(");
                            }
                            for (int j = 0, len = collection.size(); j < len; j++) {
                                if (j < len - 1) {
                                    sb.append("?,");
                                } else {
                                    sb.append("?)");
                                }
                            }
                            params.addAll(collection);
                        }
                    }
                    break;
            }
        }
        return params;
    }
}
