# mldong敏捷开发平台
敏捷开发平台

## 目录结构
```
├── mldong-common  工具类及通用代码
├── mldong-generator  代码生成器
├── mldong-admin  管理端接口
    ├── component  组件包
    ├── config  配置加载
    └── modules
      └──sys
        ├── dao 持久层-该层代码可自定义
        ├── dto dto层，可为查询结果实体、请求参数实体、业务实体
        ├── enums 错误码定义
		├── vo vo层，前端需要什么，定义什么
        └── service 服务层
└── mldong-mapper 持久层，该层代码由代码生成器生成，不可手动变更
    └── modules
      └──sys
        ├── entity 实体类
        └── mapper 持久层
```

## 开发规范
### 关于表名
表名使用下划线。例：模块名_表名。主表中必要字段（id/create_time/update_time/is_deleted,如为父子关系表,新增字段(parent_id/display_order)

如要生成枚举类型，注释可这样写如：
申请状态(10->初建|CREATE,15->摇号中|LOTING,20->已命中|HITED,25->已发送|SENDED,30->已核销|FINISHED,35->已废弃|OBSOLETE)


其他参考阿里巴巴java规范手册

## 关于代码生成器

### 命令说明
