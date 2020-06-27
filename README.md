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



## 相关文章

 [打造一款适合自己的快速开发框架-先导篇](https://juejin.im/post/5eca0304518825432978055c) 

 [打造一款适合自己的快速开发框架-后端脚手架搭建](https://juejin.im/post/5eca05206fb9a047e16c7b3c) 

 [打造一款适合自己的快速开发框架-集成mapper](https://juejin.im/post/5eca484551882543345e81f4) 

 [打造一款适合自己的快速开发框架-集成swaggerui和knife4j](https://juejin.im/post/5eca68d56fb9a04802146091) 

 [打造一款适合自己的快速开发框架-通用类封装之统一结果返回、统一异常处理](https://juejin.im/post/5ed10fb16fb9a047aa65f33b) 

 [打造一款适合自己的快速开发框架-业务错误码规范及实践](https://juejin.im/post/5ed1f623e51d457890602b62) 

 [打造一款适合自己的快速开发框架-框架分层及CURD样例](https://juejin.im/post/5ed30ae0e51d45788c739711) 

 [打造一款适合自己的快速开发框架-mapper逻辑删除及枚举类型规范](https://juejin.im/post/5ed363dc6fb9a047d112719c) 

 [打造一款适合自己的快速开发框架-数据校验之Hibernate Validator](https://juejin.im/post/5ed3a24c6fb9a047ba31fce7) 

 [打造一款适合自己的快速开发框架-代码生成器原理及实现](https://juejin.im/post/5eda67c651882543306822df) 

 [打造一款适合自己的快速开发框架-通用查询设计及实现](https://juejin.im/post/5edb82736fb9a047fe5c0aad) 

 [打造一款适合自己的快速开发框架-基于rbac的权限管理](https://juejin.im/post/5edcf981518825432a35a066) 

 [打造一款适合自己的快速开发框架-登录与权限拦截](https://juejin.im/post/5edf8d17518825433a57c56d) 

 [打造一款适合自己的快速开发框架-http请求日志全局处理](https://juejin.im/post/5ee0f916e51d457b3f4a1ad3) 

 [打造一款适合自己的快速开发框架-字典模块设计与实现](https://juejin.im/post/5ee383f26fb9a047f9374768) 

 [打造一款适合自己的快速开发框架-上传模块设计与实现](https://juejin.im/post/5ee60041f265da76d85d249b) 

 [打造一款适合自己的快速开发框架-持续部署之一键发布脚本设计与实现](https://juejin.im/post/5ee8caad6fb9a0479f00de2c) 