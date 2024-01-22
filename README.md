## 相关项目

- [前端工程](https://gitee.com/mldong/mldong-vue)
- [演示地址](http://flow.mldong.com/) 
  
演示环境账号密码：admin/123456

本地环境账号密码：superAdmin/123456

## 框架介绍
| 框架 | 版本 | 备注 | 
| --- | --- | --- | 
| SpringBoot | 2.3.7.RELEASE | web框架 | 
| Mybatis-Plus | 3.5.2 | ORM框架 |
| sa-token | 1.35.0.RC | 权限认证 |
| Hutool | 5.8.4 | 工具包 | 
| knife4j | 2.0.7 | 接口文档 | 
| spring-file-storage | 0.7.0 | 文件上传 | 
| tlog | 1.5.1 | 日志记录 |
| easypoi | 4.2.0 | excel导入导出 |
| minio | 8.4.3 | 文件存储 |
| xx-job | 2.4.0 | 分布式任务调度 |
## 功能清单

- [ ] 系统设置
	- [x] 用户管理
        - [x] 扮演用户
        - [x] 重置密码
        - [x] 授权角色
	- [x] 角色管理
        - [x] 授权菜单
        - [x] 成员管理
	- [x] 菜单管理
	- [x] 前端路由
        - [x] 同步路由清单
	- [x] 部门管理
	- [x] 岗位管理
    - [x] 数据字典
    - [x] 参数配置
    - [ ] 系统日志
	- [ ] 系统通知
- [ ] 工作流程
	- [x] 流程设计
        - [x] 流程设计
        - [x] 表单设计
        - [x] 部署流程
        - [ ] 导出流程
        - [ ] 导入流程
    - [x] 流程定义
	- [x] 发起申请
	- [x] 我发起的
	- [x] 我的待办
      - [x] 同意
      - [x] 拒绝
      - [x] 退回上一步
      - [x] 退回发起人
      - [x] 跳转
      - [x] 转办
      - [x] 委托
      - [x] 抄送
      - [ ] 转发
      - [ ] 加签
      - [ ] 减签
	- [x] 我的已办
	- [x] 我的抄送
- [ ] 在线开发
    - [x] 模型分组
    - [x] 数据模型
        - [x] 导入数据库表
        - [x] 查看元数据
        - [x] 模型字段管理
        - [ ] 在线预览
## 目录结构
```lua
├── mldong-[18080] 后台管理模块主启动模块
	├── com.mldong.config  启动配置
	└── com.mldong.modules  控制层模块
├── mldong-api  API包
    ├── mldong-biz-api  业务模块API包
    ├── mldong-sys-api  系统管理API包
    └── mldong-wf-api 工作流API包
├── mldong-app[18081]  app端主启动模块
	├── com.mldong.config  启动配置
	└── com.mldong.modules  控制层模块
├── mldong-core  业务相关
	├── mldong-biz-core  业务模块核心代码
    ├── mldong-sys-core  系统管理核心代码
		├── com.mldong.config  配置
		└── com.mldong.modules.sys
			├── dto dto层，入参
			├── entity 实体层-该模块不可手动修改，由代码生成工具生成
			├── enums 枚举
			├── excel 导入/导出excel实体
			├── mapper 执久层
			├── provider mldong-api包的实现
			├── service 业务逻辑层
			├── tasks 定时任务
			├── util 工具 
			└── vo VO层，返回实体
	└── mldong-wf-core  工作流核心代码
├── mldong-fremawork  框架
	└── mldong-base  基础模块
	
├── mldong-generator  代码生成器
	├── src/main/java/com/mldong/genertor/CodeGenerator.java 代码生成器主类
	└── src/main/resources
		├── templates 代码生成模板
		└── gencode.setting 代码生成配置文件
	
```
