package com.mldong.common.dauth;

import com.mldong.common.tool.StringTool;
import com.mldong.common.web.RequestHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Aspect
@Component
public class DataSopeAspect {
    // private static final Logger logger= LoggerFactory.getLogger(DataSopeAspect.class);
    /**
     * 全部数据权限
     */
    public static final Integer DATA_SCOPE_ALL = 10;

    /**
     * 部门数据权限
     */
    public static final Integer DATA_SCOPE_DEPT = 20;

    /**
     * 部门及以下数据权限
     */
    public static final Integer DATA_SCOPE_DEPT_AND_CHILD = 30;
    /**
     * 仅本人数据权限
     */
    public static final Integer DATA_SCOPE_SELF = 40;
    /**
     * 自定义数据权限
     */
    public static final Integer DATA_SCOPE_CUSTOM = 50;
    @Before("@annotation(dataScope)")
    public void dataScopeBefore(JoinPoint point, DataScope dataScope) throws Throwable {
        // 超级管理员，不处理
        if(RequestHolder.isSuperAdmin()) {
            return;
        }
        StringBuilder sqlString = new StringBuilder();
        Long userId = RequestHolder.getUserId();
        String deptAlias = dataScope.deptAlias();
        String userAlias = dataScope.userAlias();
        Map<String,Object> ext = RequestHolder.getUserExt();
        if(ext == null) {
            return;
        }
        Object deptId = ext.get("deptId");
        String childDeptIds = (String)ext.get("childDeptIds");
        List<String> dataScopeList = (List<String>) ext.get("dataScope");
        if(dataScopeList != null && !dataScopeList.isEmpty()) {
                for(String item : dataScopeList) {
                    if(DATA_SCOPE_ALL.toString().equals(item)) {
                        // 全部数据权限
                        sqlString = new StringBuilder();
                        break;
                    } else if(DATA_SCOPE_DEPT.toString().equals(item)) {
                        // 部门数据权限
                        if(StringTool.isEmpty(deptAlias)) {
                            sqlString.append(String.format(" OR dept_id = %s ", deptId == null ? "" : deptId.toString()));
                        } else {
                            sqlString.append(String.format(" OR %s.dept_id = %s ", deptAlias, deptId == null ? "" : deptId.toString()));
                        }
                    } else if(DATA_SCOPE_DEPT_AND_CHILD.toString().equals(item)) {
                        // 部门及以下数据权限
                        if(StringTool.isEmpty(deptAlias)) {
                            sqlString.append(String.format(" OR dept_id IN (%s)", StringTool.isEmpty(childDeptIds) ? deptId : deptId+","+childDeptIds));
                        } else {
                            sqlString.append(String.format(" OR %s.dept_id IN (%s)", deptAlias, StringTool.isEmpty(childDeptIds) ? deptId : deptId+","+childDeptIds));
                        }
                    } else if(DATA_SCOPE_SELF.toString().equals(item)) {
                        // 仅本人数据权限
                        if (StringTool.isNotEmpty(userAlias)) {
                            sqlString.append(String.format(" OR %s.user_id = %s ", userAlias, userId.toString()));
                        } else {
                            // 数据权限为仅本人且没有userAlias别名不查询任何数据
                            sqlString.append(" OR 1=0 ");
                        }
                    } else if(DATA_SCOPE_CUSTOM.toString().equals(item)) {
                        // 自定义数据权限
                        if(StringTool.isEmpty(deptAlias)) {
                            sqlString.append(String.format(" OR dept_id in(select dept_id from sys_role_dept rd left join sys_user_role ur on rd.role_id=ur.role_id where ur.user_id=%s group by dept_id)", userId.toString()));
                        } else {
                            sqlString.append(String.format(" OR %s.dept_id in(select dept_id from sys_role_dept rd left join sys_user_role ur on rd.role_id=ur.role_id where ur.user_id=%s group by dept_id)", deptAlias,userId.toString()));
                        }
                    }
                }
        }
        if (StringTool.isNotEmpty(sqlString.toString())) {
            DataScopeHelper.setLocalDataAuthSql(" AND (" + sqlString.substring(4) + ")");
        }
    }
    @After("@annotation(dataScope)")
    @AfterThrowing("@annotation(dataScope)")
    public void dataScopeAfter(JoinPoint point, DataScope dataScope) throws Throwable {
        if(StringTool.isNotEmpty(DataScopeHelper.getLocalDataAuthSql())) {
            // 执行完成，要清除当前权限Sql
            DataScopeHelper.clearDataAuthSql();
        }
    }

}
