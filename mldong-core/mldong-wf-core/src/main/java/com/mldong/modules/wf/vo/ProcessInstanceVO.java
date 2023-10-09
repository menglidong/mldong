package com.mldong.modules.wf.vo;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.mldong.modules.wf.entity.ProcessInstance;
import com.mldong.modules.wf.enums.FlowConst;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 流程实例
 * </p>
 *
 * @author mldong
 * @since 2023-09-26
 */
@Data
@ApiModel(value = "ProcessInstanceVO对象", description = "流程实例VO")
public class ProcessInstanceVO extends ProcessInstance {
    @ApiModelProperty(value = "定义名称")
    private String displayName;
    @ApiModelProperty(value = "唯一编码")
    private String name;
    @ApiModelProperty(value = "流程设计json对象")
    private JSONObject jsonObject;
    @ApiModelProperty(value = "定义版本")
    private Integer version;
    @ApiModelProperty(value = "JSON对象")
    private JSONObject ext;
    @ApiModelProperty(value = "表单数据")
    private JSONObject formData;
    public JSONObject getFormData() {
        // f_前辍才是表单数据
        this.formData = new JSONObject();
        JSONObject ext = this.getExt();
        List<String> formDataKeys = ext.keySet().stream().filter(key->key.startsWith(FlowConst.FORM_DATA_PREFIX)).collect(Collectors.toList());
        formDataKeys.forEach(key->{
            this.formData.set(key,ext.get(key));
        });
        return formData;
    }
    public JSONObject getExt() {
        if(this.ext!=null) return this.ext;
        String variable = getVariable();
        if(ObjectUtil.isEmpty(ext) && JSONUtil.isTypeJSON(variable)){
            this.ext = JSONUtil.parseObj(variable);
        }
        return ext;
    }
}
