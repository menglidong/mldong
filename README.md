
## 框架介绍

## 目录结构
```lua
├── mldong-main[18080] 后台管理模块主启动模块
	├── cn.mldong.config  启动配置
	└── cn.mldong.modules  控制层模块
├── mldong-api  API包
    ├── mldong-biz-api  业务模块API包
    ├── mldong-sys-api  系统管理API包
    └── mldong-wf-api 工作流API包
├── mldong-app[18081]  app端主启动模块
	├── cn.mldong.config  启动配置
	└── cn.mldong.modules  控制层模块
├── mldong-core  业务相关
	├── mldong-biz-core  业务模块核心代码
    ├── mldong-sys-core  系统管理核心代码
		├── cn.mldong.config  配置
		└── cn.mldong.modules.sys
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
