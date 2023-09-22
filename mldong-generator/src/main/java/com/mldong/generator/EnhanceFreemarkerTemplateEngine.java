package com.mldong.generator;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.template.TemplateUtil;
import cn.hutool.setting.Setting;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 代码生成器支持自定义[DTO\VO等]模版
 */
public final class EnhanceFreemarkerTemplateEngine extends FreemarkerTemplateEngine {

    @Override
    protected void outputCustomFile(@NotNull Map<String, String> customFile, @NotNull TableInfo tableInfo, @NotNull Map<String, Object> objectMap) {
        String entityName = tableInfo.getEntityName();
        String otherPath = this.getPathInfo(OutputFile.other);
        Setting setting = (Setting) objectMap.get("g");
        objectMap.put("hasOtherDate", false);
        tableInfo.getFields().forEach(tableField -> {
            if(!"createTime,updateTime".contains(tableField.getPropertyName()) && tableField.getColumnType().getType().equalsIgnoreCase("Date")) {
                objectMap.put("hasOtherDate",true);
            }
        });
        objectMap.put("hasLogicDeleted",tableInfo.getFieldNames().contains("is_deleted"));
        // 表表中id/parent_id/name字段时，说明为树
        List<String> fieldNameList = tableInfo.getFields().stream().map(item->{
            return item.getName();
        }).collect(Collectors.toList());
        objectMap.put("isTree", CollectionUtil.containsAll(fieldNameList,CollectionUtil.newArrayList("id","name","parent_id")));
        // 是否关系表
        final boolean r = StrUtil.isNotEmpty(tableInfo.getComment()) && tableInfo.getComment().startsWith("r_");
        objectMap.put("r",r);
        customFile.forEach((key, value) -> {
            String fileName = String.format(otherPath + File.separator + entityName + "%s", key);
            if("VO.java".equalsIgnoreCase(key)) {
                fileName = otherPath + TemplateUtil.createEngine().getTemplate(setting.get("voOutPath")).render(setting) + File.separator + entityName + "VO.java";
            }
            if("Param.java".equalsIgnoreCase(key)) {
                fileName = otherPath + TemplateUtil.createEngine().getTemplate(setting.get("paramOutPath")).render(setting) + File.separator + entityName + "Param.java";
            }
            if("PageParam.java".equalsIgnoreCase(key)) {
                fileName = otherPath + TemplateUtil.createEngine().getTemplate(setting.get("pageParamOutPath")).render(setting) + File.separator + entityName + "PageParam.java";
            }
            boolean isFileOverride = Convert.toBool(objectMap.get(key+"FileOverride"),false);
            if(r) {
                if(!"PageParam.java".equalsIgnoreCase(key)) {
                    this.outputFile(new File(fileName), objectMap, value, isFileOverride);
                }
            } else {
                this.outputFile(new File(fileName), objectMap, value, isFileOverride);
            }
        });
    }

    @Override
    protected void outputController(@org.jetbrains.annotations.NotNull TableInfo tableInfo, @org.jetbrains.annotations.NotNull Map<String, Object> objectMap) {
        // 表注释以r_开头的表，不生成controller
        if(StrUtil.isNotEmpty(tableInfo.getComment()) && tableInfo.getComment().startsWith("r_")) {
            return;
        }
        super.outputController(tableInfo, objectMap);
    }
}
