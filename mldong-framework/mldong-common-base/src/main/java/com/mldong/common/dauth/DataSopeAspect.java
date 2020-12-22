package com.mldong.common.dauth;

import com.mldong.common.tool.StringTool;
import com.mldong.common.web.RequestHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Aspect
@Component
public class DataSopeAspect {
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
    @Before("@annotation(dataScope)")
    public void dataScope(JoinPoint point, DataScope dataScope) throws Throwable {
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
                            sqlString.append(String.format(" OR dept_id = {0} ", deptId));
                        } else {
                            sqlString.append(String.format(" OR {0}.dept_id = {1} ", deptAlias, deptId));
                        }
                    } else if(DATA_SCOPE_DEPT_AND_CHILD.toString().equals(item)) {
                        // 部门及以下数据权限
                        sqlString.append(String.format(" OR {0}.dept_id IN ({1})", deptAlias, StringTool.isEmpty(childDeptIds) ? deptId : deptId+","+childDeptIds));
                    } else if(DATA_SCOPE_SELF.toString().equals(item)) {
                        // 仅本人数据权限
                        if (StringTool.isNotEmpty(userAlias)) {
                            sqlString.append(String.format(" OR {0}.user_id = {1} ", userAlias, userId));
                        } else {
                            // 数据权限为仅本人且没有userAlias别名不查询任何数据
                            sqlString.append(" OR 1=0 ");
                        }
                    }
                }
        }
        if (StringTool.isNotEmpty(sqlString.toString())) {
            DataScopeHelper.setLocalDataAuthSql(" AND (" + sqlString.substring(4) + ")");
        }
    }

}
