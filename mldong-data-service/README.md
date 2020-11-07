## 通用查询服务

仅仅只是人手不足，想偷懒，简单地设计一下通用查询。

### 简单查询分析

#### 主键查询

``` sql
select * from sys_menu where id = ?
```

#### 等于

``` sql
select * from sys_menu where parent_id = ?
```

#### 不等于

```sql
select * from sys_menu where parent_id <> ?
```

#### 大于

``` sql
select * from sys_menu where sort > ?
```

#### 大于等于

``` sql
select * from sys_menu where sort >= ?
```

#### 小于

``` sql
select * from sys_menu where sort < ?
```

#### 小于等于

``` sql
select * from sys_menu where sort <= ?
```

#### 区间范围

``` sql
select * from sys_menu where id between ? and ?
```

#### 非区间范围

``` sql
select * from sys_menu where id not between ? and ?
```

#### 模糊(全/左/右)

``` sql
select * from sys_menu where name like ?
```

#### 值集合

``` sql
select * from sys_menu where id in (?,?,?)
```

#### 非值集合

``` sql
select * from sys_menu where id not in (?,?,?)
```

#### 组合查询(and)

``` sql
select * from sys_menu where parent_id = ? and id between ? and ?
```

### 入参设计

#### 主键查询

**请求地址1：**`/ds/{tableName}`

**请求地址2：**`/ds/{dbName}/{tableName}`

**入参：**

``` json
{
    "pk_id":1
}
```

**最终生成sql==>**

``` sql
select * from {tableName} where id = ?
```


**说明：**

`{tableName}`为要查询的表

`pk_`为参数前辍，之后的为数据库表主键列名

**curl样例：**

```shell
curl -X POST -H  "Accept:*/*" -H  "Request-Origion:Knife4j" -H  "Content-Type:application/json" -d "{\"pk_id\":1}" "http://localhost:8888/ds/sys_menu"
```

**返回结果：**

``` sql
{
  "code": 0,
  "msg": "操作成功",
  "data": {
    "id": 1,
    "parent_id": 0,
    "name": "系统设置",
    "sort": 10,
    "route_name": "sys",
    "icon": "sys",
    "is_show": 2,
    "create_time": "2020-06-25 21:05:01",
    "update_time": "2020-09-08 15:31:18",
    "is_deleted": 1
  }
}
```

#### 常规查询

**请求地址1：**`/ds/{tableName}?pageNum=1&pageSize=10`

**请求地址2：**`/ds/{dbName}/{tableName}?pageNum=1&pageSize=10`



**入参：**

``` sql
{
	"m_EQ_parent_id": 0
}
```

**最终生成sql==>**

``` sql
select * from {tableName} where parent_id = ? limit ?,?
```

**说明：**

`{tableName}`为要查询的表

`m_`为参数前辍

`EQ/BT`为操作符，对应关系见下表，之后的为数据库表列名

`pageNum`为当前页，默认1

`pageSize`为每页大小，默认10

**curl样例：**

```shell
curl -X POST -H  "Accept:*/*" -H  "Request-Origion:Knife4j" -H  "Content-Type:application/json" -d "{\"m_EQ_parent_id\":0}" "http://localhost:8888/ds/sys_menu?pageNum=1&pageSize=10"
```

**返回结果：**

``` sql
{
  "code": 0,
  "msg": "操作成功",
  "data": {
    "pageNum": 1,
    "pageSize": 10,
    "recordCount": 5,
    "totalPage": 1,
    "rows": [
      {
        "id": 1,
        "parent_id": 0,
        "name": "系统设置",
        "sort": 10,
        "route_name": "sys",
        "icon": "sys",
        "is_show": 2,
        "create_time": "2020-06-25 21:05:01",
        "update_time": "2020-09-08 15:31:18",
        "is_deleted": 1
      },
      {
        "id": 6,
        "parent_id": 0,
        "name": "内容管理",
        "sort": 11,
        "route_name": "cms",
        "icon": "cms",
        "is_show": 2,
        "create_time": "2020-06-25 21:09:05",
        "update_time": "2020-06-29 09:31:58",
        "is_deleted": 1
      },
      {
        "id": 10,
        "parent_id": 0,
        "name": "订单管理",
        "sort": 12,
        "route_name": "oms",
        "icon": "oms",
        "is_show": 2,
        "create_time": "2020-06-25 21:11:29",
        "update_time": "2020-06-29 09:31:58",
        "is_deleted": 1
      },
      {
        "id": 13,
        "parent_id": 0,
        "name": "商品管理",
        "sort": 13,
        "route_name": "pms",
        "icon": "pms",
        "is_show": 2,
        "create_time": "2020-06-25 21:14:02",
        "update_time": "2020-07-10 10:31:46",
        "is_deleted": 1
      },
      {
        "id": 17,
        "parent_id": 0,
        "name": "vhhg",
        "sort": 10,
        "route_name": "fgh",
        "is_show": 2,
        "create_time": "2020-07-04 09:18:45",
        "update_time": "2020-09-04 22:25:26",
        "is_deleted": 2
      }
    ]
  }
}
```

#### 组合查询

**请求地址1：**`/ds/{tableName}?pageNum=1&pageSize=10`

**请求地址2：**`/ds/{dbName}/{tableName}?pageNum=1&pageSize=10`

**入参：**

``` sql
{
	"m_EQ_parent_id": 0,
	"m_BT_create_time": ["2020-06-25","2020-06-25 21:11:29"]
}
```

**最终生成sql==>**

``` sql
select * from {tableName} where parent_id = ? and create_time between ? and ? limit ?,?
```

**说明：**

`{tableName}`为要查询的表

`m_`为参数前辍

`EQ/BT`为操作符，对应关系见下表，之后的为数据库表主键列名

`pageNum`为当前页，默认1

`pageSize`为每页大小，默认10

**curl样例：**

```shell
curl -X POST -H  "Accept:*/*" -H  "Request-Origion:Knife4j" -H  "Content-Type:application/json" -d "{\"m_EQ_parent_id\":0,\"m_BT_create_time\":[\"2020-06-25\",\"2020-06-25 21:11:29\"]}" "http://localhost:8888/ds/sys_menu?pageNum=1&pageSize=10"
```



**返回结果：**

``` sql
{
  "code": 0,
  "msg": "操作成功",
  "data": {
    "pageNum": 1,
    "pageSize": 10,
    "recordCount": 3,
    "totalPage": 1,
    "rows": [
      {
        "id": 1,
        "parent_id": 0,
        "name": "系统设置",
        "sort": 10,
        "route_name": "sys",
        "icon": "sys",
        "is_show": 2,
        "create_time": "2020-06-25 21:05:01",
        "update_time": "2020-09-08 15:31:18",
        "is_deleted": 1
      },
      {
        "id": 6,
        "parent_id": 0,
        "name": "内容管理",
        "sort": 11,
        "route_name": "cms",
        "icon": "cms",
        "is_show": 2,
        "create_time": "2020-06-25 21:09:05",
        "update_time": "2020-06-29 09:31:58",
        "is_deleted": 1
      },
      {
        "id": 10,
        "parent_id": 0,
        "name": "订单管理",
        "sort": 12,
        "route_name": "oms",
        "icon": "oms",
        "is_show": 2,
        "create_time": "2020-06-25 21:11:29",
        "update_time": "2020-06-29 09:31:58",
        "is_deleted": 1
      }
    ]
  }
}
```

#### 操作符说明

| 操作名 | 说明                | 传参类型 |
| :----- | :------------------ | :------- |
| EQ     | 等于=               | string   |
| NE     | 不等于<>            | string   |
| GT     | 大于>               | string   |
| GE     | 大于等于>=          | string   |
| LT     | 小于<               | string   |
| LE     | 小于等于<=          | string   |
| BT     | between ? and ?     | array    |
| NBT    | not between ? and ? | array    |
| LIKE   | like '%值%'         | string   |
| NLIKE  | not like '%值%'     | string   |
| LLIKE  | like '%abc'         | string   |
| RLIKE  | like 'abc%'         | string   |
| IN     | in(?,?)             | array    |
| NIN    | not in(?,?)         | array    |

### 关于多数据源

`src/main/java/com/mldong/config/DsConfig.java` 有配置样例


