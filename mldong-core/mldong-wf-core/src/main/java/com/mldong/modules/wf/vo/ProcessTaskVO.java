package com.mldong.modules.wf.vo;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.mldong.modules.wf.entity.ProcessTask;
import com.mldong.modules.wf.enums.FlowConst;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 流程任务
 * </p>
 *
 * @author mldong
 * @since 2023-09-26
 */
@Data
@ApiModel(value = "ProcessTaskVO对象", description = "流程任务VO")
public class ProcessTaskVO extends ProcessTask {
    @ApiModelProperty(value = "流程定义名称")
    private String processDefineName;
    @ApiModelProperty(value = "流程定义显示名称")
    private String processDefineDisplayName;
    @ApiModelProperty(value = "JSON对象")
    private JSONObject ext;
    @ApiModelProperty(value = "流程实例参数")
    private String instanceVariable;
    @ApiModelProperty(value = "流程实例参数JSON对象")
    private JSONObject instanceExt;
    @ApiModelProperty(value = "流程发起时间")
    private String instanceCreateTime;
    @ApiModelProperty(value = "任务表单数据")
    private JSONObject taskFormData;
    public JSONObject getExt() {
        if(this.ext!=null) return this.ext;
        String variable = getVariable();
        if(ObjectUtil.isEmpty(ext) && JSONUtil.isTypeJSON(variable)){
            this.ext = JSONUtil.parseObj(variable);
        }
        return ext;
    }

    public JSONObject getInstanceExt() {
        if(this.instanceExt!=null) return this.instanceExt;
        String variable = getVariable();
        if(ObjectUtil.isEmpty(instanceExt) && JSONUtil.isTypeJSON(instanceVariable)){
            this.instanceExt = JSONUtil.parseObj(instanceVariable);
        }
        return instanceExt;
    }

    public JSONObject getTaskFormData() {
        // f_前辍才是表单数据
        this.taskFormData = new JSONObject();
        JSONObject ext = this.getExt();
        List<String> formDataKeys = ext.keySet().stream().filter(key->key.startsWith(FlowConst.TASK_FORM_DATA_PREFIX)).collect(Collectors.toList());
        formDataKeys.forEach(key->{
            this.taskFormData.set(key,ext.get(key));
        });
        return taskFormData;
    }
}
