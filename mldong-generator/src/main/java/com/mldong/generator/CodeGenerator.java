package com.mldong.generator;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.extra.template.TemplateUtil;
import cn.hutool.setting.Setting;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.builder.Controller;
import com.baomidou.mybatisplus.generator.config.builder.Entity;
import com.baomidou.mybatisplus.generator.config.builder.Mapper;
import com.baomidou.mybatisplus.generator.config.builder.Service;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.fill.Property;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * @author mldong
 * @date 2023/9/20
 */
public class CodeGenerator {
    public static void main(String[] args) {
        Setting setting = new Setting("gencode.setting");
        String projectRootDir = setting.getStr("targetProject",System.getProperty("user.dir"));
        String dbUrl = setting.get("db.url");
        String dbUsername = setting.get("db.username");
        String dbPassword = setting.get("db.password");
        FastAutoGenerator.create(
                new DataSourceConfig.Builder(dbUrl,dbUsername,dbPassword)
        ).globalConfig(builder -> {
                    builder.author(setting.get("author")) // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .disableOpenDir() // 禁止打开目录
                            .dateType(DateType.ONLY_DATE)
                            .outputDir(projectRootDir+"/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    Map<OutputFile,String> pathInfo = new HashMap<>();
                    // 实体类输出
                    pathInfo.put(OutputFile.entity, projectRootDir+"/"+ TemplateUtil.createEngine().getTemplate(setting.get("entityOutPath")).render(setting));
                    pathInfo.put(OutputFile.mapper, projectRootDir+"/"+ TemplateUtil.createEngine().getTemplate(setting.get("mapperOutPath")).render(setting));
                    pathInfo.put(OutputFile.xml, projectRootDir+"/"+ TemplateUtil.createEngine().getTemplate(setting.get("mapperXmlOutPath")).render(setting));
                    pathInfo.put(OutputFile.service, projectRootDir+"/"+ TemplateUtil.createEngine().getTemplate(setting.get("serviceOutPath")).render(setting));
                    pathInfo.put(OutputFile.serviceImpl, projectRootDir+"/"+ TemplateUtil.createEngine().getTemplate(setting.get("serviceImplOutPath")).render(setting));
                    pathInfo.put(OutputFile.controller, projectRootDir+"/"+ TemplateUtil.createEngine().getTemplate(setting.get("controllerOutPath")).render(setting));
                    pathInfo.put(OutputFile.other,projectRootDir+"/");
                    builder.parent(setting.get("packageParent")) // 设置父包名
                            .xml("mapper.mapping")
                            .other("dto") // 设置dto包名
                            .moduleName(setting.get("moduleName"))
                    .pathInfo(pathInfo);
                    ; // 设置父包模块名
                    //.pathInfo(Collections.singletonMap(OutputFile.xml, "F://code")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    String tableName = setting.get("tableName");
                    if(tableName.endsWith("%")) {
                        builder.likeTable(new LikeTable(tableName)); // 模糊查询
                    } else {
                        Arrays.stream(tableName.split(",")).forEach(item->{
                            builder.addInclude(item); // 设置需要生成的表名
                        });
                    }
                    builder.addTablePrefix(setting.get("tablePrefix")); // 设置过滤表前缀
                    // 实体类生成策略配置
                    Entity.Builder entityBuilder = builder.entityBuilder()
                            .enableLombok() // 启用lombok
                            .logicDeleteColumnName("is_deleted")
                            .addTableFills(
                                    new Column("create_time", FieldFill.INSERT),
                                    new Property("createUser", FieldFill.INSERT),
                                    new Property("updateTime", FieldFill.INSERT_UPDATE),
                                    new Property("updateUser", FieldFill.INSERT_UPDATE),
                                    new Property("isDeleted", FieldFill.INSERT)
                            )
                            .idType(IdType.ASSIGN_ID); // 配置主键策略
                    //.fileOverride();// 实体类模板可覆盖
                    if(Convert.toBool(setting.get("entityFileOverride"), true)) {
                        entityBuilder.fileOverride();
                    }
                    // mapper生成策略配置
                    Mapper.Builder mapperBuilder = builder.mapperBuilder()
                            .enableMapperAnnotation(); // 启用@Mapper注解
                    //.enableBaseResultMap()
                    //.enableBaseColumnList();
                    //.fileOverride();// 可覆盖
                    if(Convert.toBool(setting.get("mapperFileOverride"), false)) {
                        mapperBuilder.fileOverride();
                    }
                    // service生成策略配置
                    Service.Builder serviceBuilder = builder.serviceBuilder()
                            .formatServiceFileName("%sService");// 文件命名规则
                    //.fileOverride(); // 可覆盖
                    if(Convert.toBool(setting.get("serviceFileOverride"), false)) {
                        serviceBuilder.fileOverride();
                    }
                    // controller生成策略配置
                    Controller.Builder controllerBuilder = builder.controllerBuilder();
                    //.fileOverride(); // 可覆盖
                    if(Convert.toBool(setting.get("controllerFileOverride"), false)) {
                        controllerBuilder.fileOverride();
                    }
                })
                .templateConfig(builder -> {
                    // builder.disable(); // 禁止所有模板
                    // builder.disable(TemplateType.ENTITY); // 禁止生成entity
                    // builder.disable(TemplateType.MAPPER);
                    // builder.disable(TemplateType.XML);
                    // builder.disable(TemplateType.SERVICE);
                    // builder.disable(TemplateType.SERVICEIMPL);
                    // builder.disable(TemplateType.CONTROLLER);
                })
                .templateEngine(new EnhanceFreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .injectionConfig(consumer -> {
                    Map<String, String> customFile = new HashMap<>();
                    // DTO
                    customFile.put("Param.java", "/templates/param.java.ftl");
                    customFile.put("PageParam.java","/templates/pageParam.java.ftl");
                    customFile.put("VO.java","/templates/vo.java.ftl");
                    // consumer.fileOverride();
                    Map<String,Object> customMap = new HashMap<>();
                    customMap.put("g",setting);
                    customMap.put("Param.javaFileOverride",setting.get("paramFileOverride"));
                    customMap.put("PageParam.javaFileOverride",setting.get("pageParamFileOverride"));
                    customMap.put("VO.javaFileOverride",setting.get("voFileOverride"));
                    consumer.customMap(customMap);
                    consumer.customFile(customFile);
                    consumer.beforeOutputFile(new BiConsumer<TableInfo, Map<String, Object>>() {
                        @Override
                        public void accept(TableInfo tableInfo, Map<String, Object> map) {
                            tableInfo.getFields().forEach(tableField->{
                                if(CollectionUtil.newArrayList("Year","Boolean").contains(tableField.getPropertyType())) {
                                    tableField.setPropertyName(tableField.getPropertyName(), DbColumnType.INTEGER);
                                }
                                if(CollectionUtil.newArrayList("Blob").contains(tableField.getPropertyType())) {
                                    tableField.setPropertyName(tableField.getPropertyName(), DbColumnType.BYTE_ARRAY);
                                }
                            });
                        }
                    });
                })
                .execute();
    }
}
