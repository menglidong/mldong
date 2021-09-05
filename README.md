# mldong快速开发平台

# 加群可加作者微信：mldong_

`微信号有下划线`

快速开发平台

## 目录结构
```lua
├── mldong-admin[18080]  后台管理，只有启动类，通过maven模块依赖的方式聚合业务模块
├── mldong-app[18081]  用户端接口，有控制层代码，通过maven模块依赖业务接口实现模块供控制层调用
├── mldong-cms-parent  内容管理模块
	├── mldong-cms-domain  entity/vo/dto/enum等实体类模块
	├── mldong-cms-portal[18002]  控制层模块
	├── mldong-cms-repository  持久层模块
		└── modules
			└──cms
                ├── mapper 持久层-由代码生成器生成，不可手动变更
                └── dao 持久层-自定义层，需手动添加修改
	└── mldong-cms-service  业务模块
├── mldong-framework  框架/基类等
├── mldong-generator  代码生成器
└── mldong-sys-parent 系统管理模块
	├── mldong-sys-domain  entity/vo/dto/enum等实体类模块
	├── mldong-sys-portal[18001]  控制层模块
	├── mldong-sys-repository  持久层模块
		└── modules
			└──sys
                ├── mapper 持久层-由代码生成器生成，不可手动变更
                └── dao 持久层-自定义层，需手动添加修改
	└── mldong-cms-service  业务模块
```

## 开发规范
### 关于表名
表名使用下划线。例：模块名_表名。主表中必要字段（`id/create_time/update_time/is_deleted`,如为父子关系表,新增字段(`parent_id/sort/name`)

### 关于表字段

**如要生成是否枚举**

以`is_`为字段前辍

**如要生成枚举类型，注释可这样写如：**
申请状态(10->初建|CREATE,15->摇号中|LOTING,20->已命中|HITED,25->已发送|SENDED,30->已核销|FINISHED,35->已废弃|OBSOLETE)

**如要生成关联枚举类，注释可以这样写：**

角色类型`<sys_role.role_type>`

==> `com.mldong.modules.sys.entity.SysRole.RoleType`

**如要生成自定义枚举类，注释可以这样写**

订单状态`<oms_order_status>`

==> `com.mldong.modules.oms.enums.OmsOrderStatusEnum`

其他参考阿里巴巴java规范手册

### 关于权限标识

为了更方便前端构造树形列表展示，这里做了三级菜单分类，基于特定规范的注解。

``` java
@RestController
@RequestMapping("/cms/category")
@Api(tags="cms-栏目管理",authorizations={
    @Authorization(value="cms|内容管理",scopes={
    	@AuthorizationScope(description="栏目管理",scope="cms:category:index")
    })
})
public class CmsCategoryController {
    @PostMapping("save")
	@ApiOperation(value="添加栏目", notes="cms:category:save")
	public CommonResult<?> save(@RequestBody @Validated({Groups.Save.class}) CmsCategoryParam param) {
		int count = cmsCategoryService.save(param);
		if(count>0) {
			return CommonResult.success("添加栏目成功", null);
		} else {
			return CommonResult.fail("添加栏目失败", null);
		}
	}
}
```

* `value="cms|内容管理"`  这里是一级菜单，定义在value上，由模块权限标识|模块说明组成

* `@AuthorizationScope(description="栏目管理",scope="cms:category:index")`

  * `description` 为控制层权限标识说明
  * `scope` 为控制层权限标识

* `@ApiOperation(value="添加栏目", notes="cms:category:save")`

  * `value` 为方法层权限标识说明
  * `notes` 为方法层权限标识

最终生成的json为：

``` json
{
	"id": "cms",
	"access": "cms",
	"uri": "/cms",
	"name": "内容管理",
	"remark": "内容管理",
	"sort": 0,
	"children": [{
		"id": "cms:article:index",
		"access": "cms:article:index",
		"uri": "/cms/article/index",
		"name": "文章管理",
		"remark": "文章管理",
		"sort": 0,
		"children": [
			{
				"id": "cms:article:save",
				"access": "cms:article:save",
				"uri": "/cms/article/save",
				"name": "添加文章",
				"remark": "添加文章",
				"sort": 0
			}
		]
	}]
}
```

  

### 关于通用查询

通用查询共两个模板接口
`/xx/xxxx/list`和`/xx/xxxx/listWithExt`，前者为单表查询，后者为自定义查询。

为了查询的便捷性，这里将查询进行了初步的封装，这里先简单的说一下样例：

```json
{
	"pageNum": 1,
	"pageSize": 15,
	"whereParams": [
		{
            "tableAlias": "t",
			"operateType": "LIKE",
			"propertyName": "userName",
			"propertyValue": "admin"
		},
        {
			"operateType": "BT",
			"propertyName": "createTime",
			"propertyValue": ["2020-01-01","2020-06-06"]
		},
        {
			"operateType": "EQ",
			"propertyName": "isLocked",
			"propertyValue": 2
		}
	]
}
```
**参数说明**

| 属性 | 类型 | 说明 |
| :-----| :---- | :---- |
| tableAlias | string | 表别名，只有在多表关联查询时才可能用到 |
| operateType | enum | 操作枚举,见操作说明表 |
| propertyName | string | 属性 |
| propertyValue | object | 属性值，可以是int/string/double/array |

**操作说明-operateType**

| 操作名 | 说明                    |传参类型|
| :-----| :---- | :---- |
| EQ     | 等于=                   | string   |
| NE     | 不等于<>                | string   |
| GT     | 大于>                   | string   |
| GE     | 大于等于>=              | string   |
| LT     | 小于<                   | string   |
| LE     | 小于等于<=              |string|
| BT     | between 值1 and 值2     |array|
| NBT    | not between 值1 and 值2 |array|
| LIKE   | like '%值%'             |string|
| NLIKE  | not like '%值%'         |string|
| LLIKE  | like '%abc'             |string|
| RLIKE  | like 'abc%'             |string|
| IN     | in(值1,值2)             |array|
| NIN    | not in(值1,值2)         |array|

**另一种参数传递方式：**

```json
{
	"pageNum": 1,
	"pageSize": 15,
    "m_LIKE_userName": "admin",
    "m_BT_createTime": ["2020-01-01","2020-06-06"],
    "m_EQ_isLocked": 2
}
```

该种方式需要在XxpageParam实体类上加参数，主要目的其实是为了接口文档描述。

## 关于代码生成器

代码生成器主函数为`mldong-generator`工程下的`com.mldong.Generator.java`。

### 配置说明

``` yaml
database:
  # jdbc驱动
  driverClass: "com.mysql.cj.jdbc.Driver"
  # 数据库地址
  url: "jdbc:mysql://localhost:3306/mldong?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai"
  # 数据库名称
  dbName: "mldong"
  # 用户名
  username: "root"
  # 密码
  password: ""
# 包名
basePackage: "com.mldong"
# 作者
author: "mldong"
# 生成代码目标目录
targetProject: "D:/mldong/couse-workspace/mldong/"
# 模块名
moduleName: "sys"
# 模块描述 
moduleDesc: "系统管理"
# 是否生成逻辑删除
logicDelete: true
tables:
  - tableName: "sys_role" # 直接模糊
templates:
    # 模板名称
  - name: "实体类"
    # 是否选中，选中则会生成对应代码
    selected: true
    # 文件存在是否覆盖
    covered: true
    # 模板文件名称
    templateFile: "entity.ftl"
    # 代码生成目录
    targetPath: "mldong-${moduleName}-parent/mldong-${moduleName}-domain/src/main/java/${basePackage}/modules/${moduleName}/entity/"
    # 生成文件名(同上需要占位符，代码中要转换)
    targetFileName: "${table.className}.java"
    # 生成文件编码
    encoding: "utf-8"
```

### 模板说明

模板文件存放在`mldong-generator/src/main/resource/templates`目录下

``` lua
template
	├── controller.ftl # 控制层
	├── dto.ftl # 入参实体
	├── entity.ftl # 实体类
	├── mapper.ftl # 持久层接口
	├── mapperXml.ftl # 持久层xml
	├── pageParam.ftl # 分页入参实体
	├── service.ftl # 业务层接口
	└── serviceImpl.ftl # 业务层接口实现

```

### 其他

更详细的说明可看文章：

 [打造一款适合自己的快速开发框架-代码生成器原理及实现](https://juejin.im/post/5eda67c651882543306822df) 

## 功能截图

## 相关项目

- [mldong前端工程](https://gitee.com/mldong/mldong-vue)
- [演示地址](http://vueadmin.mldong.com/)

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