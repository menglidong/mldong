package com.mldong.config;

import com.mldong.common.tool.CxtTool;
import com.mldong.modules.sys.dao.SysUserDao;
import org.snaker.engine.Assignment;
import org.snaker.engine.core.Execution;
import org.snaker.engine.model.FieldModel;
import org.snaker.engine.model.TaskModel;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义参与者类-基于角色标识获取
 * @author mldong
 */
public class FlowAssignmentHandler extends Assignment {
    @Override
    public Object assign(TaskModel model, Execution execution) {
        String roleKey = model.getName();
        // 如果扩展参数中存在roleKey,则使用扩展参数的值
        FieldModel fieldModel = model.getFields().stream().filter(fm -> {
            return "ext".equals(fm.getName());
        }).findAny().orElse(null);
        if(fieldModel!=null) {
            Object roleKeyObj = fieldModel.getAttrMap().get("roleKey");
            if(roleKeyObj!=null) {
                roleKey = roleKeyObj.toString();
            }
        }
        SysUserDao sysUserDao = CxtTool.getBean(SysUserDao.class);
        List<String> ids = sysUserDao.selectUserIdsByRoleKey(roleKey).stream().map(id->{return id.toString();}).collect(Collectors.toList());
        if(ids.isEmpty()) {
            ids.add(roleKey);// 为空，则使用roleKey
        }
        return ids;
    }
}
