/*
Navicat MySQL Data Transfer

Source Server         : hyper-db-root
Source Server Version : 50731
Source Host           : 192.168.1.160:3306
Source Database       : mldong

Target Server Type    : MYSQL
Target Server Version : 50731
File Encoding         : 65001

Date: 2020-11-09 21:33:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cms_article
-- ----------------------------
DROP TABLE IF EXISTS `cms_article`;
CREATE TABLE `cms_article` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category_id` bigint(20) unsigned DEFAULT NULL COMMENT '栏目id',
  `title` varchar(120) NOT NULL COMMENT '标题',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `cover` varchar(255) DEFAULT NULL COMMENT '大图',
  `author` varchar(30) DEFAULT NULL COMMENT '作者',
  `source` varchar(64) DEFAULT NULL COMMENT '文章来源',
  `sort` double(10,2) unsigned DEFAULT '10.00' COMMENT '排序',
  `publish_time` datetime(3) DEFAULT NULL COMMENT '发布时间',
  `is_publish` tinyint(1) DEFAULT NULL COMMENT '是否发布(1->否|NO,2->是|YES)',
  `content` mediumtext COMMENT '文本内容',
  `create_time` datetime(3) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(3) DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) unsigned DEFAULT '1' COMMENT '是否删除(1->未删除|NO,2->已删除|YES)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章';

-- ----------------------------
-- Records of cms_article
-- ----------------------------

-- ----------------------------
-- Table structure for cms_category
-- ----------------------------
DROP TABLE IF EXISTS `cms_category`;
CREATE TABLE `cms_category` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` bigint(20) unsigned DEFAULT NULL COMMENT '父栏目id',
  `name` varchar(100) NOT NULL COMMENT '栏目名称',
  `sort` double(10,2) unsigned DEFAULT '10.00' COMMENT '排序',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `is_nav` tinyint(1) unsigned DEFAULT '2' COMMENT '是否导航(1->否|NO,2->是|YES)',
  `is_show` tinyint(1) unsigned DEFAULT '2' COMMENT '是否显示(1->否|NO,2->是|YES)',
  `seo_keyworks` varchar(100) DEFAULT NULL COMMENT 'seo关键字',
  `seo_description` varchar(255) DEFAULT NULL COMMENT 'seo描述',
  `create_time` datetime(3) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(3) DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) unsigned DEFAULT '1' COMMENT '是否删除(1->未删除|NO,2->已删除|YES)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='栏目';

-- ----------------------------
-- Records of cms_category
-- ----------------------------

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `config_name` varchar(64) DEFAULT NULL COMMENT '参数名称',
  `config_key` varchar(64) DEFAULT NULL COMMENT '参数键名',
  `config_value` text COMMENT '参数键值',
  `is_system` tinyint(1) unsigned DEFAULT '2' COMMENT '系统内置(1->否|NO,2->是|YES)',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime(3) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(3) DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) unsigned DEFAULT '1' COMMENT '是否删除(1->未删除|NO,2->已删除|YES)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='参数配置';

-- ----------------------------
-- Records of sys_config
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父级id',
  `name` varchar(100) NOT NULL COMMENT '部门名称',
  `code` varchar(30) DEFAULT NULL COMMENT '部门编码',
  `sort` double(10,2) DEFAULT '10.00' COMMENT '排序',
  `contacts` varchar(20) DEFAULT NULL COMMENT '联系人',
  `mobile_phone` varchar(15) DEFAULT NULL COMMENT '联系人手机号',
  `telephone` varchar(20) DEFAULT NULL COMMENT '电话',
  `email` varchar(32) DEFAULT NULL COMMENT '邮箱',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `is_enabled` tinyint(1) unsigned DEFAULT '2' COMMENT '是否启用(1->禁用|NO,2->启用|YES)',
  `create_time` datetime(3) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(3) DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) unsigned DEFAULT '1' COMMENT '是否删除(1->未删除|NO,2->已删除|YES)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='部门';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('6', '0', '广西大学', '1', '10.00', null, null, null, null, null, '2', '2020-10-21 16:48:38.445', '2020-10-22 08:52:50.531', '1');
INSERT INTO `sys_dept` VALUES ('7', '6', '计算机与电子信息学院', '2', '10.00', null, null, null, null, null, '2', '2020-10-21 16:48:51.857', '2020-10-22 08:53:03.495', '1');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) NOT NULL COMMENT '名称',
  `dict_key` varchar(64) NOT NULL COMMENT '唯一编码',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否删除(1->未删除|NO,2->已删除|YES)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='字典';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', '测试', 'sys_test', '测试', '2020-06-11 23:11:49.532', '2020-06-11 23:11:49.532', '2');
INSERT INTO `sys_dict` VALUES ('2', '用户类型', 'user_type', '用户类型123', '2020-06-28 21:23:32.961', '2020-07-16 23:51:19.401', '1');
INSERT INTO `sys_dict` VALUES ('3', '测试', '3333', null, '2020-07-03 15:38:00.319', '2020-07-03 15:38:00.319', '1');
INSERT INTO `sys_dict` VALUES ('4', '123', '123', '123', '2020-07-03 16:33:48.592', '2020-07-03 16:33:48.592', '1');

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dict_id` bigint(20) unsigned NOT NULL COMMENT '字典id',
  `name` varchar(32) NOT NULL COMMENT '名称',
  `dict_item_value` int(10) unsigned NOT NULL DEFAULT '1' COMMENT '值',
  `sort` double(10,2) unsigned DEFAULT '10.00' COMMENT '排序',
  `remark` varchar(255) DEFAULT NULL,
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否删除(1->未删除|NO,2->已删除|YES)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='字典项';

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
INSERT INTO `sys_dict_item` VALUES ('1', '1', '男', '10', '10.00', '', '2020-06-11 23:12:37.042', '2020-06-11 23:12:37.042', '2');
INSERT INTO `sys_dict_item` VALUES ('2', '2', 'test1', '10', '10.00', '普通用户', '2020-08-03 19:16:33.780', '2020-08-03 19:18:52.088', '2');
INSERT INTO `sys_dict_item` VALUES ('3', '2', 'test1', '10', '10.00', '普通用户', '2020-08-03 19:19:04.534', '2020-08-03 19:50:26.957', '1');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` bigint(20) unsigned DEFAULT '0' COMMENT '父菜单id',
  `name` varchar(64) NOT NULL COMMENT '菜单名称',
  `sort` double(10,2) DEFAULT '10.00' COMMENT '排序',
  `route_name` varchar(64) DEFAULT NULL COMMENT '路由名称',
  `path` varchar(64) DEFAULT NULL COMMENT '路由地址',
  `icon` varchar(32) DEFAULT NULL COMMENT '图标',
  `is_show` tinyint(1) unsigned DEFAULT '2' COMMENT '是否显示(1->不显示|NO,2->显示|YES)',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否删除(1->未删除|NO,2->已删除|YES)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COMMENT='菜单';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '系统设置', '10.00', 'sys', '/sys', 'sys', '2', null, '2020-06-25 21:05:01.000', '2020-09-08 15:31:18.640', '1');
INSERT INTO `sys_menu` VALUES ('2', '1', '菜单管理', '1.00', 'sys:menu:index', '/sys/menu/index', 'tree-table', '2', null, '2020-06-25 21:06:34.000', '2020-10-30 08:49:07.433', '1');
INSERT INTO `sys_menu` VALUES ('3', '1', '用户管理', '2.00', 'sys:user:index', '/sys/user/index', 'user', '2', null, '2020-06-25 21:07:05.000', '2020-10-30 08:49:40.069', '1');
INSERT INTO `sys_menu` VALUES ('4', '1', '角色管理', '3.00', 'sys:role:index', '/sys/role/index', 'peoples', '2', null, '2020-06-25 21:07:37.000', '2020-10-30 08:50:08.941', '1');
INSERT INTO `sys_menu` VALUES ('5', '1', '字典管理', '4.00', 'sys:dict:index', '/sys/dict/index', 'dict', '2', null, '2020-06-25 21:08:08.000', '2020-10-30 08:50:30.754', '1');
INSERT INTO `sys_menu` VALUES ('6', '0', '内容管理', '11.00', 'cms', '/cms', 'cms', '2', null, '2020-06-25 21:09:05.000', '2020-06-29 09:31:58.956', '1');
INSERT INTO `sys_menu` VALUES ('7', '6', '栏目管理', '1.00', 'cms:category:index', '/cms/category/index', 'table', '2', null, '2020-06-25 21:09:36.000', '2020-10-30 08:53:54.727', '1');
INSERT INTO `sys_menu` VALUES ('8', '6', '模型管理', '2.00', 'cms:model:index', '/cms/model/index', 'build', '2', null, '2020-06-25 21:10:23.000', '2020-10-30 08:55:12.253', '1');
INSERT INTO `sys_menu` VALUES ('9', '6', '文章管理', '3.00', 'cms:article:index', '/cms/article/index', 'documentation', '2', null, '2020-06-25 21:10:50.000', '2020-10-30 08:55:19.841', '1');
INSERT INTO `sys_menu` VALUES ('10', '0', '订单管理', '12.00', 'oms', '/oms', 'oms', '2', null, '2020-06-25 21:11:29.000', '2020-06-29 09:31:58.956', '1');
INSERT INTO `sys_menu` VALUES ('11', '10', '订单列表', '1.00', 'oms:order:index', '/oms/order/index', 'clipboard', '2', null, '2020-06-25 21:11:55.000', '2020-10-30 08:55:49.596', '1');
INSERT INTO `sys_menu` VALUES ('12', '10', '订单设置', '2.00', 'oms:orderSetting:index', '/oms/order/index', 'edit', '2', null, '2020-06-25 21:12:15.000', '2020-10-30 08:56:25.056', '1');
INSERT INTO `sys_menu` VALUES ('13', '0', '商品管理', '13.00', 'pms', 'pms', 'pms', '2', null, '2020-06-25 21:14:02.000', '2020-07-10 10:31:46.482', '1');
INSERT INTO `sys_menu` VALUES ('14', '13', '商品分类', '1.00', 'pms:productCategory:index', '/pms/productCategory/index', 'cascader', '2', null, '2020-06-25 21:16:05.000', '2020-10-30 08:56:43.499', '1');
INSERT INTO `sys_menu` VALUES ('15', '13', '商品列表', '2.00', 'pms:product:index', '/pms/product/index', 'excel', '2', null, '2020-06-25 21:16:36.000', '2020-10-30 08:56:57.348', '1');
INSERT INTO `sys_menu` VALUES ('16', '13', '品牌管理', '3.00', 'pms:brand:index', '/pms/brand/index', 'dashboard', '2', null, '2020-06-25 21:16:57.000', '2020-10-30 08:57:06.981', '1');
INSERT INTO `sys_menu` VALUES ('21', '1', '日志管理', '5.00', 'sys:requestLog:index', '/sys/requestLog/index', 'log', '2', null, '2020-09-06 20:39:14.137', '2020-10-30 08:50:57.871', '1');
INSERT INTO `sys_menu` VALUES ('22', '1', '部门管理', '10.00', 'sys:dept:index', '/sys/dept/index', 'tree', '2', null, '2020-10-21 16:15:22.926', '2020-10-30 08:51:31.096', '1');
INSERT INTO `sys_menu` VALUES ('23', '1', '岗位管理', '10.00', 'sys:post:index', '/sys/post/index', 'post', '2', null, '2020-10-21 17:23:12.122', '2020-10-30 08:51:45.195', '1');
INSERT INTO `sys_menu` VALUES ('24', '1', '通知公告', '10.10', 'sys:notice:index', '/sys/notice/index', 'message', '2', null, '2020-10-27 20:41:46.632', '2020-10-30 08:52:52.775', '1');
INSERT INTO `sys_menu` VALUES ('25', '1', '参数配置', '10.20', 'sys:config:index', '/sys/config/index', 'edit', '2', null, '2020-11-08 16:39:05.673', '2020-11-08 16:39:05.673', '1');
INSERT INTO `sys_menu` VALUES ('26', '1', '接口清单', '1.10', 'sys:menu:mapilist', '/sys/menu/maplist', 'list', '2', null, '2020-11-08 17:17:28.527', '2020-11-08 17:40:07.579', '1');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '编号',
  `title` varchar(100) DEFAULT NULL COMMENT '公告标题',
  `type` int(6) unsigned DEFAULT '10' COMMENT '公告类型(10->通知|TZ,20->公告|GG)',
  `content` longtext COMMENT '公告内容',
  `create_time` datetime(3) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(3) DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) unsigned DEFAULT '1' COMMENT '是否删除(1->未删除|NO,2->已删除|YES)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES ('1', '元旦放假通知', '10', '元旦放假通知', '2020-11-03 21:55:00.915', '2020-11-03 21:55:00.915', '1');

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '岗位名称',
  `code` varchar(30) NOT NULL COMMENT '岗位编号',
  `sort` double(10,2) unsigned DEFAULT '10.00' COMMENT '排序',
  `is_enabled` tinyint(1) unsigned DEFAULT '2' COMMENT '是否启用(1->禁用|NO,2->启用|YES)',
  `create_time` datetime(3) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(3) DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) unsigned DEFAULT '1' COMMENT '是否删除(1->未删除|NO,2->已删除|YES)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='岗位';

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES ('1', '董事长', 'ceo', '10.00', '2', '2020-10-21 17:23:40.267', '2020-10-21 17:23:40.267', '1');
INSERT INTO `sys_post` VALUES ('2', '项目经理', 'se', '10.00', '2', '2020-10-21 17:23:51.307', '2020-10-21 17:23:51.307', '1');
INSERT INTO `sys_post` VALUES ('3', '人力资源', 'hr', '10.00', '2', '2020-10-21 17:24:11.484', '2020-10-21 17:24:11.484', '1');
INSERT INTO `sys_post` VALUES ('4', '普通员工', 'user', '10.00', '2', '2020-10-21 17:25:01.004', '2020-10-21 17:25:01.004', '1');

-- ----------------------------
-- Table structure for sys_request_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_request_log`;
CREATE TABLE `sys_request_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `track_id` char(40) DEFAULT NULL COMMENT '请求唯一标识',
  `request_type` int(10) unsigned DEFAULT '99' COMMENT '请求类型(10->添加|SAVE,20->修改|UPDATE,30->删除|REMOVE,40->导入|IMPORT,50->导出|EXPORT,99->其他|OTHER)',
  `uri` varchar(64) DEFAULT NULL COMMENT '请求路径',
  `query_string` varchar(255) DEFAULT NULL COMMENT '请求url参数',
  `method` char(8) DEFAULT NULL COMMENT '请求方式',
  `description` varchar(64) DEFAULT NULL COMMENT '操作说明',
  `ip` varchar(16) DEFAULT NULL COMMENT '客户端ip',
  `body` text COMMENT '请求体',
  `token` varchar(255) DEFAULT NULL COMMENT '请求token',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(64) DEFAULT NULL COMMENT '用户名',
  `return_data` longtext COMMENT '返回参数',
  `start_time` datetime(3) DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime(3) DEFAULT NULL COMMENT '结束时间',
  `create_time` datetime(3) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(3) DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) unsigned DEFAULT '1' COMMENT '是否删除(1->未删除|NO,2->已删除|YES)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COMMENT='请求日志';

-- ----------------------------
-- Records of sys_request_log
-- ----------------------------
INSERT INTO `sys_request_log` VALUES ('2', '369fc910-bc9b-4514-9152-1d1a645050f9', '30', '/sys/user/remove', '', 'POST', '删除用户222', '0:0:0:0:0:0:0:1', '{\n	\"ids\": [22]\n}', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHQiOiJ7fSIsImlzcyI6Im1sZG9uZyIsImV4cCI6MTU5OTQwMTIyMSwidXNlck5hbWUiOiJhZG1pbiIsImlhdCI6MTU5OTM5NDAyMSwidXNlcklkIjoxfQ.obM5vUe_4UtK4Grz7ud8ZOIiiQGZ1a-s_PwA6K8gY94', '1', 'admin', '{\"code\":99999999,\"msg\":\"删除用户失败\",\"data\":null}', '2020-09-06 20:08:28.135', '2020-09-06 20:08:28.301', '2020-09-06 20:08:28.301', '2020-09-06 20:08:28.301', '1');
INSERT INTO `sys_request_log` VALUES ('3', '05169859-7acf-4396-8673-cf8106b43d0d', '99', '/error', '', 'GET', null, '0:0:0:0:0:0:0:1', null, null, '0', '', '{\"code\":99990401,\"msg\":\"未授权\",\"data\":null}', '2020-10-21 09:06:15.160', '2020-10-21 09:08:45.434', '2020-10-21 09:08:45.434', '2020-10-21 09:08:45.434', '1');
INSERT INTO `sys_request_log` VALUES ('4', '2f3669e2-3a53-4aed-a772-51471a992c08', '99', '/sys/login', '', 'POST', '登录系统', '0:0:0:0:0:0:0:1', '{\n	\"password\": \"mldong@321\",\n	\"userName\": \"admin\"\n}', null, null, null, '{\"code\":0,\"msg\":\"登录成功\",\"data\":{\"token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHQiOiJ7fSIsImlzcyI6Im1sZG9uZyIsImV4cCI6MTYwMzI1MzcwOCwidXNlck5hbWUiOiJhZG1pbiIsImlhdCI6MTYwMzI0NjUwOCwidXNlcklkIjoxfQ.NTvo-FUGWdvK2M_GiphPAWdtDginnQ72337nxpw6pXQ\",\"userId\":1,\"userName\":\"admin\",\"realName\":\"蒙立东\",\"avatar\":\"\",\"accessList\":[\"admin\"],\"menuList\":[]}}', '2020-10-21 10:15:08.257', '2020-10-21 10:15:09.114', '2020-10-21 10:15:09.115', '2020-10-21 10:15:09.115', '1');
INSERT INTO `sys_request_log` VALUES ('5', '91180755-98bd-469c-9ea1-f0d21ea872df', '99', '/sys/login', '', 'POST', '登录系统', '0:0:0:0:0:0:0:1', '{\n	\"password\": \"mldong@321\",\n	\"userName\": \"admin\"\n}', null, null, null, '{\"code\":0,\"msg\":\"登录成功\",\"data\":{\"token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHQiOiJ7fSIsImlzcyI6Im1sZG9uZyIsImV4cCI6MTYwMzMzNzAwNiwidXNlck5hbWUiOiJhZG1pbiIsImlhdCI6MTYwMzMyOTgwNiwidXNlcklkIjoxfQ.RuRZPD_KqHV60o9xtPD7H5k5_VBU9RsVZGHJu1za_us\",\"userId\":1,\"userName\":\"admin\",\"realName\":\"蒙立东\",\"avatar\":\"\",\"accessList\":[\"admin\"],\"menuList\":[]}}', '2020-10-22 09:23:26.095', '2020-10-22 09:23:26.854', '2020-10-22 09:23:26.855', '2020-10-22 09:23:26.855', '1');
INSERT INTO `sys_request_log` VALUES ('6', '6080c5a4-4f23-4331-abeb-0e1a82bf91d8', '99', '/sys/login', '', 'POST', '登录系统', '0:0:0:0:0:0:0:1', '{\n	\"password\": \"mldong@321\",\n	\"userName\": \"admin\"\n}', null, null, null, '{\"code\":0,\"msg\":\"登录成功\",\"data\":{\"token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHQiOiJ7fSIsImlzcyI6Im1sZG9uZyIsImV4cCI6MTYwMzM3NTA3NSwidXNlck5hbWUiOiJhZG1pbiIsImlhdCI6MTYwMzM2Nzg3NSwidXNlcklkIjoxfQ.qqJQjANA6kfBYd5YvC_M7LqWmeaNXGrlaGp2XIB-8Qw\",\"userId\":1,\"userName\":\"admin\",\"realName\":\"蒙立东\",\"avatar\":\"\",\"accessList\":[\"admin\"],\"menuList\":[]}}', '2020-10-22 19:57:55.626', '2020-10-22 19:57:55.684', '2020-10-22 19:57:55.684', '2020-10-22 19:57:55.684', '1');
INSERT INTO `sys_request_log` VALUES ('7', '7e924c48-4a90-4a74-9520-6addf570903e', '99', '/sys/login', '', 'POST', '登录系统', '0:0:0:0:0:0:0:1', '{\n	\"password\": \"mldong@321\",\n	\"userName\": \"admin\"\n}', null, null, null, '{\"code\":0,\"msg\":\"登录成功\",\"data\":{\"token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHQiOiJ7fSIsImlzcyI6Im1sZG9uZyIsImV4cCI6MTYwMzQ2OTQzMywidXNlck5hbWUiOiJhZG1pbiIsImlhdCI6MTYwMzQ2MjIzMywidXNlcklkIjoxfQ.y-7H1jXOJuDEH02ezhozs35Yiyo61tImY9oxcxhPjS8\",\"userId\":1,\"userName\":\"admin\",\"realName\":\"蒙立东\",\"avatar\":\"\",\"accessList\":[\"admin\"],\"menuList\":[]}}', '2020-10-23 22:10:33.650', '2020-10-23 22:10:33.866', '2020-10-23 22:10:33.866', '2020-10-23 22:10:33.866', '1');
INSERT INTO `sys_request_log` VALUES ('8', '10f09c9a-4490-4630-95d8-5d3bf3485b22', '99', '/error', '', 'GET', null, '0:0:0:0:0:0:0:1', null, '', null, null, '{\"code\":99990401,\"msg\":\"未授权\",\"data\":null}', '2020-10-24 20:54:43.529', '2020-10-24 20:55:30.032', '2020-10-24 20:55:30.033', '2020-10-24 20:55:30.033', '1');
INSERT INTO `sys_request_log` VALUES ('9', '8fb296b7-408d-49ca-b919-c58291ebca64', '99', '/sys/login', '', 'POST', '登录系统', '0:0:0:0:0:0:0:1', '{\n	\"password\": \"mldong@321\",\n	\"userName\": \"admin\"\n}', null, null, null, '{\"code\":0,\"msg\":\"登录成功\",\"data\":{\"token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHQiOiJ7fSIsImlzcyI6Im1sZG9uZyIsImV4cCI6MTYwMzk4NDg0OCwidXNlck5hbWUiOiJhZG1pbiIsImlhdCI6MTYwMzk3NzY0OCwidXNlcklkIjoxfQ.wArKQwG7HPAzhoGvk7-21TJ_Cs2oeJKhhIWS9Q9zBb0\",\"userId\":1,\"userName\":\"admin\",\"realName\":\"蒙立东\",\"avatar\":\"\",\"accessList\":[\"admin\"],\"menuList\":[]}}', '2020-10-29 21:20:48.590', '2020-10-29 21:20:48.807', '2020-10-29 21:20:48.808', '2020-10-29 21:20:48.808', '1');
INSERT INTO `sys_request_log` VALUES ('10', '6daec947-1d4f-4e49-b18d-89babea934c8', '99', '/sys/login', '', 'POST', '登录系统', '0:0:0:0:0:0:0:1', '{\n	\"password\": \"mldong@321\",\n	\"userName\": \"123456\"\n}', null, null, null, '{\"code\":80009001,\"msg\":\"用户不存在\",\"data\":null}', '2020-11-01 23:07:10.942', '2020-11-01 23:07:11.101', '2020-11-01 23:07:11.101', '2020-11-01 23:07:11.101', '1');
INSERT INTO `sys_request_log` VALUES ('11', '9470388d-5b1c-41e3-86c5-3c3952f91c39', '99', '/sys/login', '', 'POST', '登录系统', '0:0:0:0:0:0:0:1', '{\n	\"password\": \"mldong@321\",\n	\"userName\": \"mldong\"\n}', null, null, null, '{\"code\":80009001,\"msg\":\"用户不存在\",\"data\":null}', '2020-11-01 23:07:16.233', '2020-11-01 23:07:16.237', '2020-11-01 23:07:16.237', '2020-11-01 23:07:16.237', '1');
INSERT INTO `sys_request_log` VALUES ('12', '6558496b-61d2-4c5e-8434-d036fc518f37', '99', '/sys/login', '', 'POST', '登录系统', '0:0:0:0:0:0:0:1', '{\n	\"password\": \"mldong@321\",\n	\"userName\": \"admin\"\n}', null, null, null, '{\"code\":0,\"msg\":\"登录成功\",\"data\":{\"token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHQiOiJ7fSIsImlzcyI6Im1sZG9uZyIsImV4cCI6MTYwNDI1MDQ0MSwidXNlck5hbWUiOiJhZG1pbiIsImlhdCI6MTYwNDI0MzI0MSwidXNlcklkIjoxfQ.9Q-ZI4YnETcZ5LGNCD49Pxwlr1UQtSqYxLnH6Pg_SvM\",\"userId\":1,\"userName\":\"admin\",\"realName\":\"mldong\",\"avatar\":\"\",\"accessList\":[\"admin\"],\"menuList\":[{\"id\":2,\"parentId\":1,\"name\":\"菜单管理\",\"sort\":1.0,\"routeName\":\"sys:menu:index\",\"icon\":\"tree-table\",\"isShow\":2,\"createTime\":1593090394000,\"updateTime\":1604018947433,\"isDeleted\":1},{\"id\":14,\"parentId\":13,\"name\":\"商品分类\",\"sort\":1.0,\"routeName\":\"pms:productCategory:index\",\"icon\":\"cascader\",\"isShow\":2,\"createTime\":1593090965000,\"updateTime\":1604019403499,\"isDeleted\":1},{\"id\":11,\"parentId\":10,\"name\":\"订单列表\",\"sort\":1.0,\"routeName\":\"oms:order:index\",\"icon\":\"clipboard\",\"isShow\":2,\"createTime\":1593090715000,\"updateTime\":1604019349596,\"isDeleted\":1},{\"id\":7,\"parentId\":6,\"name\":\"栏目管理\",\"sort\":1.0,\"routeName\":\"cms:category:index\",\"icon\":\"table\",\"isShow\":2,\"createTime\":1593090576000,\"updateTime\":1604019234727,\"isDeleted\":1},{\"id\":8,\"parentId\":6,\"name\":\"模型管理\",\"sort\":2.0,\"routeName\":\"cms:model:index\",\"icon\":\"build\",\"isShow\":2,\"createTime\":1593090623000,\"updateTime\":1604019312253,\"isDeleted\":1},{\"id\":3,\"parentId\":1,\"name\":\"用户管理\",\"sort\":2.0,\"routeName\":\"sys:user:index\",\"icon\":\"user\",\"isShow\":2,\"createTime\":1593090425000,\"updateTime\":1604018980069,\"isDeleted\":1},{\"id\":12,\"parentId\":10,\"name\":\"订单设置\",\"sort\":2.0,\"routeName\":\"oms:orderSetting:index\",\"icon\":\"edit\",\"isShow\":2,\"createTime\":1593090735000,\"updateTime\":1604019385056,\"isDeleted\":1},{\"id\":15,\"parentId\":13,\"name\":\"商品列表\",\"sort\":2.0,\"routeName\":\"pms:product:index\",\"icon\":\"excel\",\"isShow\":2,\"createTime\":1593090996000,\"updateTime\":1604019417348,\"isDeleted\":1},{\"id\":4,\"parentId\":1,\"name\":\"角色管理\",\"sort\":3.0,\"routeName\":\"sys:role:index\",\"icon\":\"peoples\",\"isShow\":2,\"createTime\":1593090457000,\"updateTime\":1604019008941,\"isDeleted\":1},{\"id\":9,\"parentId\":6,\"name\":\"文章管理\",\"sort\":3.0,\"routeName\":\"cms:article:index\",\"icon\":\"documentation\",\"isShow\":2,\"createTime\":1593090650000,\"updateTime\":1604019319841,\"isDeleted\":1},{\"id\":16,\"parentId\":13,\"name\":\"品牌管理\",\"sort\":3.0,\"routeName\":\"pms:brand:index\",\"icon\":\"dashboard\",\"isShow\":2,\"createTime\":1593091017000,\"updateTime\":1604019426981,\"isDeleted\":1},{\"id\":5,\"parentId\":1,\"name\":\"字典管理\",\"sort\":4.0,\"routeName\":\"sys:dict:index\",\"icon\":\"dict\",\"isShow\":2,\"createTime\":1593090488000,\"updateTime\":1604019030754,\"isDeleted\":1},{\"id\":21,\"parentId\":1,\"name\":\"日志管理\",\"sort\":5.0,\"routeName\":\"sys:requestLog:index\",\"icon\":\"log\",\"isShow\":2,\"createTime\":1599395954137,\"updateTime\":1604019057871,\"isDeleted\":1},{\"id\":1,\"parentId\":0,\"name\":\"系统设置\",\"sort\":10.0,\"routeName\":\"sys\",\"icon\":\"sys\",\"isShow\":2,\"createTime\":1593090301000,\"updateTime\":1599550278640,\"isDeleted\":1},{\"id\":22,\"parentId\":1,\"name\":\"部门管理\",\"sort\":10.0,\"routeName\":\"sys:dept:index\",\"icon\":\"tree\",\"isShow\":2,\"createTime\":1603268122926,\"updateTime\":1604019091096,\"isDeleted\":1},{\"id\":23,\"parentId\":1,\"name\":\"岗位管理\",\"sort\":10.0,\"routeName\":\"sys:post:index\",\"icon\":\"post\",\"isShow\":2,\"createTime\":1603272192122,\"updateTime\":1604019105195,\"isDeleted\":1},{\"id\":24,\"parentId\":1,\"name\":\"通知公告\",\"sort\":10.1,\"routeName\":\"sys:notice:index\",\"icon\":\"message\",\"isShow\":2,\"createTime\":1603802506632,\"updateTime\":1604019172775,\"isDeleted\":1},{\"id\":6,\"parentId\":0,\"name\":\"内容管理\",\"sort\":11.0,\"routeName\":\"cms\",\"icon\":\"cms\",\"isShow\":2,\"createTime\":1593090545000,\"updateTime\":1593394318956,\"isDeleted\":1},{\"id\":10,\"parentId\":0,\"name\":\"订单管理\",\"sort\":12.0,\"routeName\":\"oms\",\"icon\":\"oms\",\"isShow\":2,\"createTime\":1593090689000,\"updateTime\":1593394318956,\"isDeleted\":1},{\"id\":13,\"parentId\":0,\"name\":\"商品管理\",\"sort\":13.0,\"routeName\":\"pms\",\"icon\":\"pms\",\"isShow\":2,\"createTime\":1593090842000,\"updateTime\":1594348306482,\"isDeleted\":1}]}}', '2020-11-01 23:07:21.503', '2020-11-01 23:07:21.577', '2020-11-01 23:07:21.578', '2020-11-01 23:07:21.578', '1');
INSERT INTO `sys_request_log` VALUES ('13', '9307cda0-f0e3-4d0e-80d3-33dc6e08adfb', '99', '/sys/notice/query', '', 'POST', 'query', '0:0:0:0:0:0:0:1', '{\n	\"keyworks\": \"\",\n	\"pageNum\": 0,\n	\"pageSize\": 0,\n	\"whereParams\": [\n	\n	]\n}', null, null, null, '{\"code\":0,\"msg\":\"查询通知公告成功\",\"data\":{\"pageNum\":1,\"pageSize\":15,\"recordCount\":0,\"totalPage\":0,\"rows\":[]}}', '2020-11-03 20:17:17.780', '2020-11-03 20:17:17.937', '2020-11-03 20:17:17.937', '2020-11-03 20:17:17.937', '1');
INSERT INTO `sys_request_log` VALUES ('14', 'da7c6d78-14aa-4d27-89e9-c90f22026901', '99', '/sys/notice/query', '', 'POST', 'query', '0:0:0:0:0:0:0:1', '{\n	\"keyworks\": \"\",\n	\"pageNum\": 0,\n	\"pageSize\": 0,\n	\"whereParams\": [\n	\n	]\n}', null, null, null, '{\"code\":0,\"msg\":\"查询通知公告成功\",\"data\":{\"pageNum\":1,\"pageSize\":15,\"recordCount\":0,\"totalPage\":0,\"rows\":[]}}', '2020-11-03 20:18:34.606', '2020-11-03 20:18:34.796', '2020-11-03 20:18:34.796', '2020-11-03 20:18:34.796', '1');
INSERT INTO `sys_request_log` VALUES ('15', 'f0d6549b-d700-4d3b-9e1a-c7650832e134', '99', '/sys/login', '', 'POST', '登录系统', '0:0:0:0:0:0:0:1', '{\n	\"password\": \"mldong@321\",\n	\"userName\": \"admin\"\n}', null, null, null, '{\"code\":0,\"msg\":\"登录成功\",\"data\":{\"token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHQiOiJ7fSIsImlzcyI6Im1sZG9uZyIsImV4cCI6MTYwNDQxNTMwMSwidXNlck5hbWUiOiJhZG1pbiIsImlhdCI6MTYwNDQwODEwMSwidXNlcklkIjoxfQ.mlGTbkEwW_OhWXlYKmXfwwU6HzaDV5p4egFoLYRXffo\",\"userId\":1,\"userName\":\"admin\",\"realName\":\"mldong\",\"avatar\":\"\",\"accessList\":[\"admin\"],\"menuList\":[{\"id\":2,\"parentId\":1,\"name\":\"菜单管理\",\"sort\":1.0,\"routeName\":\"sys:menu:index\",\"icon\":\"tree-table\",\"isShow\":2,\"createTime\":1593090394000,\"updateTime\":1604018947433,\"isDeleted\":1},{\"id\":14,\"parentId\":13,\"name\":\"商品分类\",\"sort\":1.0,\"routeName\":\"pms:productCategory:index\",\"icon\":\"cascader\",\"isShow\":2,\"createTime\":1593090965000,\"updateTime\":1604019403499,\"isDeleted\":1},{\"id\":11,\"parentId\":10,\"name\":\"订单列表\",\"sort\":1.0,\"routeName\":\"oms:order:index\",\"icon\":\"clipboard\",\"isShow\":2,\"createTime\":1593090715000,\"updateTime\":1604019349596,\"isDeleted\":1},{\"id\":7,\"parentId\":6,\"name\":\"栏目管理\",\"sort\":1.0,\"routeName\":\"cms:category:index\",\"icon\":\"table\",\"isShow\":2,\"createTime\":1593090576000,\"updateTime\":1604019234727,\"isDeleted\":1},{\"id\":8,\"parentId\":6,\"name\":\"模型管理\",\"sort\":2.0,\"routeName\":\"cms:model:index\",\"icon\":\"build\",\"isShow\":2,\"createTime\":1593090623000,\"updateTime\":1604019312253,\"isDeleted\":1},{\"id\":3,\"parentId\":1,\"name\":\"用户管理\",\"sort\":2.0,\"routeName\":\"sys:user:index\",\"icon\":\"user\",\"isShow\":2,\"createTime\":1593090425000,\"updateTime\":1604018980069,\"isDeleted\":1},{\"id\":12,\"parentId\":10,\"name\":\"订单设置\",\"sort\":2.0,\"routeName\":\"oms:orderSetting:index\",\"icon\":\"edit\",\"isShow\":2,\"createTime\":1593090735000,\"updateTime\":1604019385056,\"isDeleted\":1},{\"id\":15,\"parentId\":13,\"name\":\"商品列表\",\"sort\":2.0,\"routeName\":\"pms:product:index\",\"icon\":\"excel\",\"isShow\":2,\"createTime\":1593090996000,\"updateTime\":1604019417348,\"isDeleted\":1},{\"id\":4,\"parentId\":1,\"name\":\"角色管理\",\"sort\":3.0,\"routeName\":\"sys:role:index\",\"icon\":\"peoples\",\"isShow\":2,\"createTime\":1593090457000,\"updateTime\":1604019008941,\"isDeleted\":1},{\"id\":9,\"parentId\":6,\"name\":\"文章管理\",\"sort\":3.0,\"routeName\":\"cms:article:index\",\"icon\":\"documentation\",\"isShow\":2,\"createTime\":1593090650000,\"updateTime\":1604019319841,\"isDeleted\":1},{\"id\":16,\"parentId\":13,\"name\":\"品牌管理\",\"sort\":3.0,\"routeName\":\"pms:brand:index\",\"icon\":\"dashboard\",\"isShow\":2,\"createTime\":1593091017000,\"updateTime\":1604019426981,\"isDeleted\":1},{\"id\":5,\"parentId\":1,\"name\":\"字典管理\",\"sort\":4.0,\"routeName\":\"sys:dict:index\",\"icon\":\"dict\",\"isShow\":2,\"createTime\":1593090488000,\"updateTime\":1604019030754,\"isDeleted\":1},{\"id\":21,\"parentId\":1,\"name\":\"日志管理\",\"sort\":5.0,\"routeName\":\"sys:requestLog:index\",\"icon\":\"log\",\"isShow\":2,\"createTime\":1599395954137,\"updateTime\":1604019057871,\"isDeleted\":1},{\"id\":1,\"parentId\":0,\"name\":\"系统设置\",\"sort\":10.0,\"routeName\":\"sys\",\"icon\":\"sys\",\"isShow\":2,\"createTime\":1593090301000,\"updateTime\":1599550278640,\"isDeleted\":1},{\"id\":22,\"parentId\":1,\"name\":\"部门管理\",\"sort\":10.0,\"routeName\":\"sys:dept:index\",\"icon\":\"tree\",\"isShow\":2,\"createTime\":1603268122926,\"updateTime\":1604019091096,\"isDeleted\":1},{\"id\":23,\"parentId\":1,\"name\":\"岗位管理\",\"sort\":10.0,\"routeName\":\"sys:post:index\",\"icon\":\"post\",\"isShow\":2,\"createTime\":1603272192122,\"updateTime\":1604019105195,\"isDeleted\":1},{\"id\":24,\"parentId\":1,\"name\":\"通知公告\",\"sort\":10.1,\"routeName\":\"sys:notice:index\",\"icon\":\"message\",\"isShow\":2,\"createTime\":1603802506632,\"updateTime\":1604019172775,\"isDeleted\":1},{\"id\":6,\"parentId\":0,\"name\":\"内容管理\",\"sort\":11.0,\"routeName\":\"cms\",\"icon\":\"cms\",\"isShow\":2,\"createTime\":1593090545000,\"updateTime\":1593394318956,\"isDeleted\":1},{\"id\":10,\"parentId\":0,\"name\":\"订单管理\",\"sort\":12.0,\"routeName\":\"oms\",\"icon\":\"oms\",\"isShow\":2,\"createTime\":1593090689000,\"updateTime\":1593394318956,\"isDeleted\":1},{\"id\":13,\"parentId\":0,\"name\":\"商品管理\",\"sort\":13.0,\"routeName\":\"pms\",\"icon\":\"pms\",\"isShow\":2,\"createTime\":1593090842000,\"updateTime\":1594348306482,\"isDeleted\":1}]}}', '2020-11-03 20:55:01.771', '2020-11-03 20:55:02.095', '2020-11-03 20:55:02.096', '2020-11-03 20:55:02.096', '1');
INSERT INTO `sys_request_log` VALUES ('16', 'e3406f40-3929-4f0f-9082-b58da2d38a82', '10', '/sys/notice/save', '', 'POST', '添加通知公告', '0:0:0:0:0:0:0:1', '{\n	\"content\": \"元旦放假通知\",\n	\"id\": 0,\n	\"title\": \"元旦放假通知\",\n	\"type\": \"10\"\n}', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHQiOiJ7fSIsImlzcyI6Im1sZG9uZyIsImV4cCI6MTYwNDQxNTMwMSwidXNlck5hbWUiOiJhZG1pbiIsImlhdCI6MTYwNDQwODEwMSwidXNlcklkIjoxfQ.mlGTbkEwW_OhWXlYKmXfwwU6HzaDV5p4egFoLYRXffo', '1', 'admin', '{\"code\":0,\"msg\":\"添加通知公告成功\",\"data\":null}', '2020-11-03 21:55:00.873', '2020-11-03 21:55:00.940', '2020-11-03 21:55:00.940', '2020-11-03 21:55:00.940', '1');
INSERT INTO `sys_request_log` VALUES ('17', 'e17b4801-9a2d-484b-a282-270a0dab3fa9', '99', '/sys/login', '', 'POST', '登录系统', '0:0:0:0:0:0:0:1', '{\n	\"password\": \"mldong@321\",\n	\"userName\": \"admin\"\n}', null, null, null, '{\"code\":0,\"msg\":\"登录成功\",\"data\":{\"token\":\"9b358b35092f499caf308327163d5f95\",\"userId\":1,\"userName\":\"admin\",\"realName\":\"mldong\",\"avatar\":\"\",\"accessList\":[\"admin\"],\"menuList\":[{\"id\":2,\"parentId\":1,\"name\":\"菜单管理\",\"sort\":1.0,\"routeName\":\"sys:menu:index\",\"icon\":\"tree-table\",\"isShow\":2,\"createTime\":1593090394000,\"updateTime\":1604018947433,\"isDeleted\":1},{\"id\":14,\"parentId\":13,\"name\":\"商品分类\",\"sort\":1.0,\"routeName\":\"pms:productCategory:index\",\"icon\":\"cascader\",\"isShow\":2,\"createTime\":1593090965000,\"updateTime\":1604019403499,\"isDeleted\":1},{\"id\":11,\"parentId\":10,\"name\":\"订单列表\",\"sort\":1.0,\"routeName\":\"oms:order:index\",\"icon\":\"clipboard\",\"isShow\":2,\"createTime\":1593090715000,\"updateTime\":1604019349596,\"isDeleted\":1},{\"id\":7,\"parentId\":6,\"name\":\"栏目管理\",\"sort\":1.0,\"routeName\":\"cms:category:index\",\"icon\":\"table\",\"isShow\":2,\"createTime\":1593090576000,\"updateTime\":1604019234727,\"isDeleted\":1},{\"id\":8,\"parentId\":6,\"name\":\"模型管理\",\"sort\":2.0,\"routeName\":\"cms:model:index\",\"icon\":\"build\",\"isShow\":2,\"createTime\":1593090623000,\"updateTime\":1604019312253,\"isDeleted\":1},{\"id\":3,\"parentId\":1,\"name\":\"用户管理\",\"sort\":2.0,\"routeName\":\"sys:user:index\",\"icon\":\"user\",\"isShow\":2,\"createTime\":1593090425000,\"updateTime\":1604018980069,\"isDeleted\":1},{\"id\":12,\"parentId\":10,\"name\":\"订单设置\",\"sort\":2.0,\"routeName\":\"oms:orderSetting:index\",\"icon\":\"edit\",\"isShow\":2,\"createTime\":1593090735000,\"updateTime\":1604019385056,\"isDeleted\":1},{\"id\":15,\"parentId\":13,\"name\":\"商品列表\",\"sort\":2.0,\"routeName\":\"pms:product:index\",\"icon\":\"excel\",\"isShow\":2,\"createTime\":1593090996000,\"updateTime\":1604019417348,\"isDeleted\":1},{\"id\":4,\"parentId\":1,\"name\":\"角色管理\",\"sort\":3.0,\"routeName\":\"sys:role:index\",\"icon\":\"peoples\",\"isShow\":2,\"createTime\":1593090457000,\"updateTime\":1604019008941,\"isDeleted\":1},{\"id\":9,\"parentId\":6,\"name\":\"文章管理\",\"sort\":3.0,\"routeName\":\"cms:article:index\",\"icon\":\"documentation\",\"isShow\":2,\"createTime\":1593090650000,\"updateTime\":1604019319841,\"isDeleted\":1},{\"id\":16,\"parentId\":13,\"name\":\"品牌管理\",\"sort\":3.0,\"routeName\":\"pms:brand:index\",\"icon\":\"dashboard\",\"isShow\":2,\"createTime\":1593091017000,\"updateTime\":1604019426981,\"isDeleted\":1},{\"id\":5,\"parentId\":1,\"name\":\"字典管理\",\"sort\":4.0,\"routeName\":\"sys:dict:index\",\"icon\":\"dict\",\"isShow\":2,\"createTime\":1593090488000,\"updateTime\":1604019030754,\"isDeleted\":1},{\"id\":21,\"parentId\":1,\"name\":\"日志管理\",\"sort\":5.0,\"routeName\":\"sys:requestLog:index\",\"icon\":\"log\",\"isShow\":2,\"createTime\":1599395954137,\"updateTime\":1604019057871,\"isDeleted\":1},{\"id\":1,\"parentId\":0,\"name\":\"系统设置\",\"sort\":10.0,\"routeName\":\"sys\",\"icon\":\"sys\",\"isShow\":2,\"createTime\":1593090301000,\"updateTime\":1599550278640,\"isDeleted\":1},{\"id\":22,\"parentId\":1,\"name\":\"部门管理\",\"sort\":10.0,\"routeName\":\"sys:dept:index\",\"icon\":\"tree\",\"isShow\":2,\"createTime\":1603268122926,\"updateTime\":1604019091096,\"isDeleted\":1},{\"id\":23,\"parentId\":1,\"name\":\"岗位管理\",\"sort\":10.0,\"routeName\":\"sys:post:index\",\"icon\":\"post\",\"isShow\":2,\"createTime\":1603272192122,\"updateTime\":1604019105195,\"isDeleted\":1},{\"id\":24,\"parentId\":1,\"name\":\"通知公告\",\"sort\":10.1,\"routeName\":\"sys:notice:index\",\"icon\":\"message\",\"isShow\":2,\"createTime\":1603802506632,\"updateTime\":1604019172775,\"isDeleted\":1},{\"id\":6,\"parentId\":0,\"name\":\"内容管理\",\"sort\":11.0,\"routeName\":\"cms\",\"icon\":\"cms\",\"isShow\":2,\"createTime\":1593090545000,\"updateTime\":1593394318956,\"isDeleted\":1},{\"id\":10,\"parentId\":0,\"name\":\"订单管理\",\"sort\":12.0,\"routeName\":\"oms\",\"icon\":\"oms\",\"isShow\":2,\"createTime\":1593090689000,\"updateTime\":1593394318956,\"isDeleted\":1},{\"id\":13,\"parentId\":0,\"name\":\"商品管理\",\"sort\":13.0,\"routeName\":\"pms\",\"icon\":\"pms\",\"isShow\":2,\"createTime\":1593090842000,\"updateTime\":1594348306482,\"isDeleted\":1}]}}', '2020-11-07 20:55:51.529', '2020-11-07 20:55:53.140', '2020-11-07 20:55:53.143', '2020-11-07 20:55:53.143', '1');
INSERT INTO `sys_request_log` VALUES ('18', '4bc64b3b-3a35-4c88-a598-bfa1475d87bf', '99', '/sys/login', '', 'POST', '登录系统', '0:0:0:0:0:0:0:1', '{\n	\"password\": \"mldong@321\",\n	\"userName\": \"admin\"\n}', null, null, null, '{\"code\":0,\"msg\":\"登录成功\",\"data\":{\"token\":\"56d2efef570347e8a1bc149c69ef1951\",\"userId\":1,\"userName\":\"admin\",\"realName\":\"mldong\",\"avatar\":\"\",\"accessList\":[\"admin\"],\"menuList\":[{\"id\":2,\"parentId\":1,\"name\":\"菜单管理\",\"sort\":1.0,\"routeName\":\"sys:menu:index\",\"icon\":\"tree-table\",\"isShow\":2,\"createTime\":1593090394000,\"updateTime\":1604018947433,\"isDeleted\":1},{\"id\":14,\"parentId\":13,\"name\":\"商品分类\",\"sort\":1.0,\"routeName\":\"pms:productCategory:index\",\"icon\":\"cascader\",\"isShow\":2,\"createTime\":1593090965000,\"updateTime\":1604019403499,\"isDeleted\":1},{\"id\":11,\"parentId\":10,\"name\":\"订单列表\",\"sort\":1.0,\"routeName\":\"oms:order:index\",\"icon\":\"clipboard\",\"isShow\":2,\"createTime\":1593090715000,\"updateTime\":1604019349596,\"isDeleted\":1},{\"id\":7,\"parentId\":6,\"name\":\"栏目管理\",\"sort\":1.0,\"routeName\":\"cms:category:index\",\"icon\":\"table\",\"isShow\":2,\"createTime\":1593090576000,\"updateTime\":1604019234727,\"isDeleted\":1},{\"id\":8,\"parentId\":6,\"name\":\"模型管理\",\"sort\":2.0,\"routeName\":\"cms:model:index\",\"icon\":\"build\",\"isShow\":2,\"createTime\":1593090623000,\"updateTime\":1604019312253,\"isDeleted\":1},{\"id\":3,\"parentId\":1,\"name\":\"用户管理\",\"sort\":2.0,\"routeName\":\"sys:user:index\",\"icon\":\"user\",\"isShow\":2,\"createTime\":1593090425000,\"updateTime\":1604018980069,\"isDeleted\":1},{\"id\":12,\"parentId\":10,\"name\":\"订单设置\",\"sort\":2.0,\"routeName\":\"oms:orderSetting:index\",\"icon\":\"edit\",\"isShow\":2,\"createTime\":1593090735000,\"updateTime\":1604019385056,\"isDeleted\":1},{\"id\":15,\"parentId\":13,\"name\":\"商品列表\",\"sort\":2.0,\"routeName\":\"pms:product:index\",\"icon\":\"excel\",\"isShow\":2,\"createTime\":1593090996000,\"updateTime\":1604019417348,\"isDeleted\":1},{\"id\":4,\"parentId\":1,\"name\":\"角色管理\",\"sort\":3.0,\"routeName\":\"sys:role:index\",\"icon\":\"peoples\",\"isShow\":2,\"createTime\":1593090457000,\"updateTime\":1604019008941,\"isDeleted\":1},{\"id\":9,\"parentId\":6,\"name\":\"文章管理\",\"sort\":3.0,\"routeName\":\"cms:article:index\",\"icon\":\"documentation\",\"isShow\":2,\"createTime\":1593090650000,\"updateTime\":1604019319841,\"isDeleted\":1},{\"id\":16,\"parentId\":13,\"name\":\"品牌管理\",\"sort\":3.0,\"routeName\":\"pms:brand:index\",\"icon\":\"dashboard\",\"isShow\":2,\"createTime\":1593091017000,\"updateTime\":1604019426981,\"isDeleted\":1},{\"id\":5,\"parentId\":1,\"name\":\"字典管理\",\"sort\":4.0,\"routeName\":\"sys:dict:index\",\"icon\":\"dict\",\"isShow\":2,\"createTime\":1593090488000,\"updateTime\":1604019030754,\"isDeleted\":1},{\"id\":21,\"parentId\":1,\"name\":\"日志管理\",\"sort\":5.0,\"routeName\":\"sys:requestLog:index\",\"icon\":\"log\",\"isShow\":2,\"createTime\":1599395954137,\"updateTime\":1604019057871,\"isDeleted\":1},{\"id\":1,\"parentId\":0,\"name\":\"系统设置\",\"sort\":10.0,\"routeName\":\"sys\",\"icon\":\"sys\",\"isShow\":2,\"createTime\":1593090301000,\"updateTime\":1599550278640,\"isDeleted\":1},{\"id\":22,\"parentId\":1,\"name\":\"部门管理\",\"sort\":10.0,\"routeName\":\"sys:dept:index\",\"icon\":\"tree\",\"isShow\":2,\"createTime\":1603268122926,\"updateTime\":1604019091096,\"isDeleted\":1},{\"id\":23,\"parentId\":1,\"name\":\"岗位管理\",\"sort\":10.0,\"routeName\":\"sys:post:index\",\"icon\":\"post\",\"isShow\":2,\"createTime\":1603272192122,\"updateTime\":1604019105195,\"isDeleted\":1},{\"id\":24,\"parentId\":1,\"name\":\"通知公告\",\"sort\":10.1,\"routeName\":\"sys:notice:index\",\"icon\":\"message\",\"isShow\":2,\"createTime\":1603802506632,\"updateTime\":1604019172775,\"isDeleted\":1},{\"id\":6,\"parentId\":0,\"name\":\"内容管理\",\"sort\":11.0,\"routeName\":\"cms\",\"icon\":\"cms\",\"isShow\":2,\"createTime\":1593090545000,\"updateTime\":1593394318956,\"isDeleted\":1},{\"id\":10,\"parentId\":0,\"name\":\"订单管理\",\"sort\":12.0,\"routeName\":\"oms\",\"icon\":\"oms\",\"isShow\":2,\"createTime\":1593090689000,\"updateTime\":1593394318956,\"isDeleted\":1},{\"id\":13,\"parentId\":0,\"name\":\"商品管理\",\"sort\":13.0,\"routeName\":\"pms\",\"icon\":\"pms\",\"isShow\":2,\"createTime\":1593090842000,\"updateTime\":1594348306482,\"isDeleted\":1}]}}', '2020-11-08 17:06:06.100', '2020-11-08 17:06:07.652', '2020-11-08 17:06:07.655', '2020-11-08 17:06:07.655', '1');
INSERT INTO `sys_request_log` VALUES ('19', 'f2c245f1-e884-46a5-873b-ef26b0caa192', '99', '/sys/login', '', 'POST', '登录系统', '0:0:0:0:0:0:0:1', '{\n	\"password\": \"*****\",\n	\"userName\": \"admin\"\n}', null, null, null, '{\"code\":0,\"msg\":\"登录成功\",\"data\":{\"token\":\"3f64721f993a40dbae90956078085c2f\",\"userId\":1,\"userName\":\"admin\",\"realName\":\"mldong\",\"avatar\":\"\",\"accessList\":[\"admin\"],\"menuList\":[{\"id\":2,\"parentId\":1,\"name\":\"菜单管理\",\"sort\":1.0,\"routeName\":\"sys:menu:index\",\"icon\":\"tree-table\",\"isShow\":2,\"createTime\":1593090394000,\"updateTime\":1604018947433,\"isDeleted\":1},{\"id\":14,\"parentId\":13,\"name\":\"商品分类\",\"sort\":1.0,\"routeName\":\"pms:productCategory:index\",\"icon\":\"cascader\",\"isShow\":2,\"createTime\":1593090965000,\"updateTime\":1604019403499,\"isDeleted\":1},{\"id\":11,\"parentId\":10,\"name\":\"订单列表\",\"sort\":1.0,\"routeName\":\"oms:order:index\",\"icon\":\"clipboard\",\"isShow\":2,\"createTime\":1593090715000,\"updateTime\":1604019349596,\"isDeleted\":1},{\"id\":7,\"parentId\":6,\"name\":\"栏目管理\",\"sort\":1.0,\"routeName\":\"cms:category:index\",\"icon\":\"table\",\"isShow\":2,\"createTime\":1593090576000,\"updateTime\":1604019234727,\"isDeleted\":1},{\"id\":26,\"parentId\":1,\"name\":\"接口清单\",\"sort\":1.1,\"routeName\":\"sys:menu:mapilist\",\"icon\":\"list\",\"isShow\":2,\"createTime\":1604827048527,\"updateTime\":1604828407579,\"isDeleted\":1},{\"id\":8,\"parentId\":6,\"name\":\"模型管理\",\"sort\":2.0,\"routeName\":\"cms:model:index\",\"icon\":\"build\",\"isShow\":2,\"createTime\":1593090623000,\"updateTime\":1604019312253,\"isDeleted\":1},{\"id\":15,\"parentId\":13,\"name\":\"商品列表\",\"sort\":2.0,\"routeName\":\"pms:product:index\",\"icon\":\"excel\",\"isShow\":2,\"createTime\":1593090996000,\"updateTime\":1604019417348,\"isDeleted\":1},{\"id\":12,\"parentId\":10,\"name\":\"订单设置\",\"sort\":2.0,\"routeName\":\"oms:orderSetting:index\",\"icon\":\"edit\",\"isShow\":2,\"createTime\":1593090735000,\"updateTime\":1604019385056,\"isDeleted\":1},{\"id\":3,\"parentId\":1,\"name\":\"用户管理\",\"sort\":2.0,\"routeName\":\"sys:user:index\",\"icon\":\"user\",\"isShow\":2,\"createTime\":1593090425000,\"updateTime\":1604018980069,\"isDeleted\":1},{\"id\":16,\"parentId\":13,\"name\":\"品牌管理\",\"sort\":3.0,\"routeName\":\"pms:brand:index\",\"icon\":\"dashboard\",\"isShow\":2,\"createTime\":1593091017000,\"updateTime\":1604019426981,\"isDeleted\":1},{\"id\":9,\"parentId\":6,\"name\":\"文章管理\",\"sort\":3.0,\"routeName\":\"cms:article:index\",\"icon\":\"documentation\",\"isShow\":2,\"createTime\":1593090650000,\"updateTime\":1604019319841,\"isDeleted\":1},{\"id\":4,\"parentId\":1,\"name\":\"角色管理\",\"sort\":3.0,\"routeName\":\"sys:role:index\",\"icon\":\"peoples\",\"isShow\":2,\"createTime\":1593090457000,\"updateTime\":1604019008941,\"isDeleted\":1},{\"id\":5,\"parentId\":1,\"name\":\"字典管理\",\"sort\":4.0,\"routeName\":\"sys:dict:index\",\"icon\":\"dict\",\"isShow\":2,\"createTime\":1593090488000,\"updateTime\":1604019030754,\"isDeleted\":1},{\"id\":21,\"parentId\":1,\"name\":\"日志管理\",\"sort\":5.0,\"routeName\":\"sys:requestLog:index\",\"icon\":\"log\",\"isShow\":2,\"createTime\":1599395954137,\"updateTime\":1604019057871,\"isDeleted\":1},{\"id\":22,\"parentId\":1,\"name\":\"部门管理\",\"sort\":10.0,\"routeName\":\"sys:dept:index\",\"icon\":\"tree\",\"isShow\":2,\"createTime\":1603268122926,\"updateTime\":1604019091096,\"isDeleted\":1},{\"id\":23,\"parentId\":1,\"name\":\"岗位管理\",\"sort\":10.0,\"routeName\":\"sys:post:index\",\"icon\":\"post\",\"isShow\":2,\"createTime\":1603272192122,\"updateTime\":1604019105195,\"isDeleted\":1},{\"id\":1,\"parentId\":0,\"name\":\"系统设置\",\"sort\":10.0,\"routeName\":\"sys\",\"icon\":\"sys\",\"isShow\":2,\"createTime\":1593090301000,\"updateTime\":1599550278640,\"isDeleted\":1},{\"id\":24,\"parentId\":1,\"name\":\"通知公告\",\"sort\":10.1,\"routeName\":\"sys:notice:index\",\"icon\":\"message\",\"isShow\":2,\"createTime\":1603802506632,\"updateTime\":1604019172775,\"isDeleted\":1},{\"id\":25,\"parentId\":1,\"name\":\"参数配置\",\"sort\":10.2,\"routeName\":\"sys:config:index\",\"icon\":\"edit\",\"isShow\":2,\"createTime\":1604824745673,\"updateTime\":1604824745673,\"isDeleted\":1},{\"id\":6,\"parentId\":0,\"name\":\"内容管理\",\"sort\":11.0,\"routeName\":\"cms\",\"icon\":\"cms\",\"isShow\":2,\"createTime\":1593090545000,\"updateTime\":1593394318956,\"isDeleted\":1},{\"id\":10,\"parentId\":0,\"name\":\"订单管理\",\"sort\":12.0,\"routeName\":\"oms\",\"icon\":\"oms\",\"isShow\":2,\"createTime\":1593090689000,\"updateTime\":1593394318956,\"isDeleted\":1},{\"id\":13,\"parentId\":0,\"name\":\"商品管理\",\"sort\":13.0,\"routeName\":\"pms\",\"icon\":\"pms\",\"isShow\":2,\"createTime\":1593090842000,\"updateTime\":1594348306482,\"isDeleted\":1}]}}', '2020-11-09 20:54:41.266', '2020-11-09 20:54:42.857', '2020-11-09 20:54:42.860', '2020-11-09 20:54:42.860', '1');
INSERT INTO `sys_request_log` VALUES ('20', '7ba403ce-3174-4d0e-8298-c34c19104f65', '10', '/sys/menu/save', '', 'POST', '添加菜单', '0:0:0:0:0:0:0:1', '{\n	\"icon\": \"sys\",\n	\"isShow\": \"2\",\n	\"name\": \"测试\",\n	\"parentId\": 0,\n	\"routeName\": \"sys\",\n	\"sort\": 10\n}', '3f64721f993a40dbae90956078085c2f', '1', 'admin', '{\"code\":99990100,\"msg\":\"routeName字段唯一，不可重复添加\",\"data\":null}', '2020-11-09 20:56:03.747', '2020-11-09 20:56:03.806', '2020-11-09 20:56:03.806', '2020-11-09 20:56:03.806', '1');
INSERT INTO `sys_request_log` VALUES ('21', 'ef57dec7-e011-41d2-9e16-7c4b3190a967', '10', '/sys/menu/save', '', 'POST', '添加菜单', '0:0:0:0:0:0:0:1', '{\n	\"icon\": \"sys\",\n	\"isShow\": \"2\",\n	\"name\": \"测试\",\n	\"parentId\": 0,\n	\"routeName\": \"sys1\",\n	\"sort\": 10\n}', '3f64721f993a40dbae90956078085c2f', '1', 'admin', '{\"code\":0,\"msg\":\"添加菜单成功\",\"data\":null}', '2020-11-09 20:56:16.872', '2020-11-09 20:56:16.897', '2020-11-09 20:56:16.898', '2020-11-09 20:56:16.898', '1');
INSERT INTO `sys_request_log` VALUES ('22', '0e848f86-4919-49f6-a7d4-9c71751ac9d0', '20', '/sys/menu/update', '', 'POST', '修改菜单', '0:0:0:0:0:0:0:1', '{\n        \"id\": 27,\n        \"parentId\": 0,\n        \"name\": \"测试\",\n        \"sort\": 10,\n        \"routeName\": \"sys1\",\n        \"icon\": \"sys\",\n        \"isShow\": 2,\n        \"createTime\": \"2020-11-09 20:56:16\",\n        \"updateTime\": \"2020-11-09 20:56:16\",\n        \"isDeleted\": 1\n      },', '3f64721f993a40dbae90956078085c2f', '1', 'admin', '{\"code\":0,\"msg\":\"修改菜单成功\",\"data\":null}', '2020-11-09 20:57:07.831', '2020-11-09 20:57:07.851', '2020-11-09 20:57:07.852', '2020-11-09 20:57:07.852', '1');
INSERT INTO `sys_request_log` VALUES ('23', '0aeee8a1-7e2a-40f2-be7d-e0db1577359c', '20', '/sys/menu/update', '', 'POST', '修改菜单', '0:0:0:0:0:0:0:1', '{\n        \"id\": 27,\n        \"parentId\": 0,\n        \"name\": \"测试\",\n        \"sort\": 10,\n        \"routeName\": \"sys2\",\n        \"icon\": \"sys\",\n        \"isShow\": 2,\n        \"createTime\": \"2020-11-09 20:56:16\",\n        \"updateTime\": \"2020-11-09 20:56:16\",\n        \"isDeleted\": 1\n      },', '3f64721f993a40dbae90956078085c2f', '1', 'admin', '{\"code\":0,\"msg\":\"修改菜单成功\",\"data\":null}', '2020-11-09 20:57:21.893', '2020-11-09 20:57:21.916', '2020-11-09 20:57:21.916', '2020-11-09 20:57:21.916', '1');
INSERT INTO `sys_request_log` VALUES ('24', '415cb3df-8356-4253-ae8e-38788fd7ed73', '20', '/sys/menu/update', '', 'POST', '修改菜单', '0:0:0:0:0:0:0:1', '{\n        \"id\": 27,\n        \"parentId\": 0,\n        \"name\": \"测试\",\n        \"sort\": 10,\n        \"routeName\": \"sys\",\n        \"icon\": \"sys\",\n        \"isShow\": 2,\n        \"createTime\": \"2020-11-09 20:56:16\",\n        \"updateTime\": \"2020-11-09 20:56:16\",\n        \"isDeleted\": 1\n      },', '3f64721f993a40dbae90956078085c2f', '1', 'admin', '{\"code\":99990100,\"msg\":\"routeName字段唯一，请更换别的值\",\"data\":null}', '2020-11-09 20:57:30.160', '2020-11-09 20:57:30.172', '2020-11-09 20:57:30.172', '2020-11-09 20:57:30.172', '1');
INSERT INTO `sys_request_log` VALUES ('25', 'af92c9a8-7a8c-4322-ba7b-1736780e8977', '20', '/sys/menu/update', '', 'POST', '修改菜单', '0:0:0:0:0:0:0:1', '{\n        \"id\": 27,\n        \"parentId\": 0,\n        \"name\": \"测试\",\n        \"sort\": 10,\n        \"routeName\": \"sys2\",\n        \"icon\": \"sys\",\n        \"isShow\": 2,\n        \"createTime\": \"2020-11-09 20:56:16\",\n        \"updateTime\": \"2020-11-09 20:56:16\",\n        \"isDeleted\": 1\n      },', '3f64721f993a40dbae90956078085c2f', '1', 'admin', '{\"code\":0,\"msg\":\"修改菜单成功\",\"data\":null}', '2020-11-09 20:57:39.570', '2020-11-09 20:57:39.590', '2020-11-09 20:57:39.591', '2020-11-09 20:57:39.591', '1');
INSERT INTO `sys_request_log` VALUES ('26', '5f169da0-8523-458a-bb48-c5d51501d84a', '20', '/sys/menu/update', '', 'POST', '修改菜单', '0:0:0:0:0:0:0:1', '{\n        \"id\": 27,\n        \"parentId\": 0,\n        \"name\": \"测试\",\n        \"sort\": 10,\n        \"routeName\": \"sys1\",\n        \"icon\": \"sys\",\n        \"isShow\": 2,\n        \"createTime\": \"2020-11-09 20:56:16\",\n        \"updateTime\": \"2020-11-09 20:56:16\",\n        \"isDeleted\": 1\n      },', '3f64721f993a40dbae90956078085c2f', '1', 'admin', '{\"code\":0,\"msg\":\"修改菜单成功\",\"data\":null}', '2020-11-09 20:57:45.665', '2020-11-09 20:57:45.683', '2020-11-09 20:57:45.683', '2020-11-09 20:57:45.683', '1');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8mb4 NOT NULL COMMENT '角色名称',
  `role_key` varchar(32) DEFAULT NULL COMMENT '角色标识(唯一)',
  `role_type` int(6) DEFAULT '10' COMMENT '角色类型(10->管理员|ADMIN,20->流程审核员|WORKFLOW)',
  `is_enabled` tinyint(1) DEFAULT '2' COMMENT '是否启用(1->禁用|NO,2->启用|YES)',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否删除(1->未删除|NO,2->已删除|YES)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '普通管理员', 'NORMAL_MANAGE', '10', '1', '普通管理员', '2020-05-31 07:07:26.000', '2020-05-31 07:07:29.000', '2');
INSERT INTO `sys_role` VALUES ('2', '超级管理员', 'SUPER_ADMIN', '10', '2', '超级管理员', '2020-06-27 07:20:01.638', '2020-07-14 16:54:49.631', '1');
INSERT INTO `sys_role` VALUES ('3', '审批专员', 'approving_commissioner', '10', '2', '审批专员', '2020-06-27 07:23:38.401', '2020-07-03 16:34:27.677', '1');
INSERT INTO `sys_role` VALUES ('4', '234', '412', '10', '2', '123', '2020-07-03 16:34:09.588', '2020-07-03 16:34:09.588', '1');

-- ----------------------------
-- Table structure for sys_role_access
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_access`;
CREATE TABLE `sys_role_access` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) unsigned NOT NULL COMMENT '角色id',
  `access` varchar(64) NOT NULL COMMENT '权限标识',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否删除(1->未删除|NO,2->已删除|YES)',
  PRIMARY KEY (`id`),
  KEY `idx_sys_role_access` (`access`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COMMENT='角色权限关系';

-- ----------------------------
-- Records of sys_role_access
-- ----------------------------
INSERT INTO `sys_role_access` VALUES ('1', '3', 'sys:uploadRecord:index', '2020-06-30 23:06:48.500', '2020-06-30 23:06:48.500', '2');
INSERT INTO `sys_role_access` VALUES ('2', '3', 'sys:upload:createUploadToken', '2020-06-30 23:06:48.500', '2020-06-30 23:06:48.500', '2');
INSERT INTO `sys_role_access` VALUES ('3', '3', 'sys:uploadRecord:remove', '2020-06-30 23:06:48.500', '2020-06-30 23:06:48.500', '2');
INSERT INTO `sys_role_access` VALUES ('4', '3', 'sys:uploadRecord:get', '2020-06-30 23:06:48.500', '2020-06-30 23:06:48.500', '2');
INSERT INTO `sys_role_access` VALUES ('5', '3', 'sys:uploadRecord:list', '2020-06-30 23:06:48.500', '2020-06-30 23:06:48.500', '2');
INSERT INTO `sys_role_access` VALUES ('6', '3', 'sys:dictItem:index', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', '1');
INSERT INTO `sys_role_access` VALUES ('7', '3', 'sys:dictItem:remove', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', '1');
INSERT INTO `sys_role_access` VALUES ('8', '3', 'sys:dictItem:get', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', '1');
INSERT INTO `sys_role_access` VALUES ('9', '3', 'sys:dictItem:update', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', '1');
INSERT INTO `sys_role_access` VALUES ('10', '3', 'sys:dictItem:list', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', '1');
INSERT INTO `sys_role_access` VALUES ('11', '3', 'sys:dictItem:save', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', '1');
INSERT INTO `sys_role_access` VALUES ('12', '3', 'sys:dict:index', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', '1');
INSERT INTO `sys_role_access` VALUES ('13', '3', 'sys:dict:listAllEnum', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', '1');
INSERT INTO `sys_role_access` VALUES ('14', '3', 'sys:dict:getByDictKey', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', '1');
INSERT INTO `sys_role_access` VALUES ('15', '3', 'sys:dict:remove', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', '1');
INSERT INTO `sys_role_access` VALUES ('16', '3', 'sys:dict:get', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', '1');
INSERT INTO `sys_role_access` VALUES ('17', '3', 'sys:dict:update', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', '1');
INSERT INTO `sys_role_access` VALUES ('18', '3', 'sys:dict:list', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', '1');
INSERT INTO `sys_role_access` VALUES ('19', '3', 'sys:dict:save', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', '1');
INSERT INTO `sys_role_access` VALUES ('20', '3', 'sys:user:get', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', '1');
INSERT INTO `sys_role_access` VALUES ('21', '3', 'sys:user:update', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', '1');
INSERT INTO `sys_role_access` VALUES ('22', '3', 'sys:user:list', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', '1');
INSERT INTO `sys_role_access` VALUES ('23', '3', 'sys:user:save', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', '1');
INSERT INTO `sys_role_access` VALUES ('24', '3', 'sys:menu:index', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', '1');
INSERT INTO `sys_role_access` VALUES ('25', '3', 'sys:menu:remove', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', '1');
INSERT INTO `sys_role_access` VALUES ('26', '3', 'sys:menu:get', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', '1');
INSERT INTO `sys_role_access` VALUES ('27', '3', 'sys:menu:update', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', '1');
INSERT INTO `sys_role_access` VALUES ('28', '3', 'sys:menu:list', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', '1');
INSERT INTO `sys_role_access` VALUES ('29', '3', 'sys:menu:save', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', '1');
INSERT INTO `sys_role_access` VALUES ('30', '2', 'sys:role:remove', '2020-07-02 17:21:14.484', '2020-07-02 17:21:14.484', '1');
INSERT INTO `sys_role_access` VALUES ('31', '2', 'sys:uploadConfig:index', '2020-07-07 09:16:39.508', '2020-07-07 09:16:39.508', '2');
INSERT INTO `sys_role_access` VALUES ('32', '2', 'sys:uploadConfig:remove', '2020-07-07 09:16:39.508', '2020-07-07 09:16:39.508', '2');
INSERT INTO `sys_role_access` VALUES ('33', '2', 'sys:uploadConfig:get', '2020-07-07 09:16:39.508', '2020-07-07 09:16:39.508', '2');
INSERT INTO `sys_role_access` VALUES ('34', '2', 'sys:uploadConfig:update', '2020-07-07 09:16:39.508', '2020-07-07 09:16:39.508', '2');
INSERT INTO `sys_role_access` VALUES ('35', '2', 'sys:uploadConfig:list', '2020-07-07 09:16:39.508', '2020-07-07 09:16:39.508', '2');
INSERT INTO `sys_role_access` VALUES ('36', '2', 'sys:uploadConfig:save', '2020-07-07 09:16:39.508', '2020-07-07 09:16:39.508', '2');
INSERT INTO `sys_role_access` VALUES ('37', '2', 'sys:user:index', '2020-07-19 02:31:43.325', '2020-07-19 02:31:43.325', '1');
INSERT INTO `sys_role_access` VALUES ('38', '2', 'sys:user:remove', '2020-07-19 02:31:43.325', '2020-07-19 02:31:43.325', '1');
INSERT INTO `sys_role_access` VALUES ('39', '2', 'sys:user:get', '2020-07-19 02:31:43.325', '2020-07-19 02:31:43.325', '1');
INSERT INTO `sys_role_access` VALUES ('40', '2', 'sys:user:update', '2020-07-19 02:31:43.325', '2020-07-19 02:31:43.325', '1');
INSERT INTO `sys_role_access` VALUES ('41', '2', 'sys:user:list', '2020-07-19 02:31:43.325', '2020-07-19 02:31:43.325', '1');
INSERT INTO `sys_role_access` VALUES ('42', '2', 'sys:user:save', '2020-07-19 02:31:43.325', '2020-07-19 02:31:43.325', '1');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) unsigned NOT NULL COMMENT '角色id',
  `menu_id` bigint(20) unsigned NOT NULL COMMENT '菜单id',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否删除(1->未删除|NO,2->已删除|YES)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COMMENT='角色菜单关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '2', '6', '2020-06-30 23:13:54.765', '2020-06-30 23:13:54.765', '2');
INSERT INTO `sys_role_menu` VALUES ('2', '2', '7', '2020-06-30 23:13:54.765', '2020-06-30 23:13:54.765', '2');
INSERT INTO `sys_role_menu` VALUES ('3', '2', '8', '2020-06-30 23:13:54.765', '2020-06-30 23:13:54.765', '2');
INSERT INTO `sys_role_menu` VALUES ('4', '2', '9', '2020-06-30 23:13:54.765', '2020-06-30 23:13:54.765', '2');
INSERT INTO `sys_role_menu` VALUES ('5', '3', '7', '2020-06-30 23:26:49.656', '2020-06-30 23:26:49.656', '2');
INSERT INTO `sys_role_menu` VALUES ('6', '3', '8', '2020-06-30 23:26:49.656', '2020-06-30 23:26:49.656', '2');
INSERT INTO `sys_role_menu` VALUES ('7', '3', '10', '2020-06-30 23:26:49.656', '2020-06-30 23:26:49.656', '2');
INSERT INTO `sys_role_menu` VALUES ('8', '3', '11', '2020-06-30 23:26:49.656', '2020-06-30 23:26:49.656', '2');
INSERT INTO `sys_role_menu` VALUES ('9', '3', '12', '2020-06-30 23:26:49.656', '2020-06-30 23:26:49.656', '2');
INSERT INTO `sys_role_menu` VALUES ('10', '3', '1', '2020-07-01 21:51:20.294', '2020-07-01 21:51:20.294', '2');
INSERT INTO `sys_role_menu` VALUES ('11', '3', '2', '2020-07-01 21:51:20.294', '2020-07-01 21:51:20.294', '1');
INSERT INTO `sys_role_menu` VALUES ('12', '3', '3', '2020-07-01 21:51:20.294', '2020-07-01 21:51:20.294', '1');
INSERT INTO `sys_role_menu` VALUES ('13', '3', '4', '2020-07-01 21:51:20.294', '2020-07-01 21:51:20.294', '2');
INSERT INTO `sys_role_menu` VALUES ('14', '3', '5', '2020-07-01 21:51:20.294', '2020-07-01 21:51:20.294', '1');
INSERT INTO `sys_role_menu` VALUES ('15', '3', '7', '2020-07-01 21:51:20.294', '2020-07-01 21:51:20.294', '1');
INSERT INTO `sys_role_menu` VALUES ('16', '3', '9', '2020-07-01 21:51:20.294', '2020-07-01 21:51:20.294', '1');
INSERT INTO `sys_role_menu` VALUES ('17', '2', '2', '2020-07-02 12:20:51.642', '2020-07-02 12:20:51.642', '2');
INSERT INTO `sys_role_menu` VALUES ('18', '2', '3', '2020-07-02 12:20:51.642', '2020-07-02 12:20:51.642', '2');
INSERT INTO `sys_role_menu` VALUES ('19', '2', '1', '2020-07-02 12:23:15.379', '2020-07-02 12:23:15.379', '2');
INSERT INTO `sys_role_menu` VALUES ('20', '2', '4', '2020-07-02 12:23:15.379', '2020-07-02 12:23:15.379', '2');
INSERT INTO `sys_role_menu` VALUES ('21', '2', '5', '2020-07-02 12:23:15.379', '2020-07-02 12:23:15.379', '2');
INSERT INTO `sys_role_menu` VALUES ('22', '2', '10', '2020-07-02 12:23:15.379', '2020-07-02 12:23:15.379', '2');
INSERT INTO `sys_role_menu` VALUES ('23', '2', '11', '2020-07-02 12:23:15.379', '2020-07-02 12:23:15.379', '2');
INSERT INTO `sys_role_menu` VALUES ('24', '2', '12', '2020-07-02 12:23:15.379', '2020-07-02 12:23:15.379', '1');
INSERT INTO `sys_role_menu` VALUES ('25', '2', '13', '2020-07-02 12:23:15.379', '2020-07-02 12:23:15.379', '2');
INSERT INTO `sys_role_menu` VALUES ('26', '2', '14', '2020-07-02 12:23:15.379', '2020-07-02 12:23:15.379', '2');
INSERT INTO `sys_role_menu` VALUES ('27', '2', '15', '2020-07-02 12:23:15.379', '2020-07-02 12:23:15.379', '2');
INSERT INTO `sys_role_menu` VALUES ('28', '2', '16', '2020-07-02 12:23:15.379', '2020-07-02 12:23:15.379', '2');
INSERT INTO `sys_role_menu` VALUES ('29', '2', '10', '2020-07-02 13:48:35.866', '2020-07-02 13:48:35.866', '1');
INSERT INTO `sys_role_menu` VALUES ('30', '2', '11', '2020-07-02 13:48:35.866', '2020-07-02 13:48:35.866', '1');
INSERT INTO `sys_role_menu` VALUES ('31', '4', '3', '2020-07-15 09:15:50.925', '2020-07-15 09:15:50.925', '1');
INSERT INTO `sys_role_menu` VALUES ('32', '4', '4', '2020-07-15 09:15:50.925', '2020-07-15 09:15:50.925', '1');
INSERT INTO `sys_role_menu` VALUES ('33', '2', '1', '2020-08-26 15:41:12.202', '2020-08-26 15:41:12.202', '1');
INSERT INTO `sys_role_menu` VALUES ('34', '2', '2', '2020-08-26 15:41:12.202', '2020-08-26 15:41:12.202', '1');
INSERT INTO `sys_role_menu` VALUES ('35', '2', '3', '2020-08-26 15:41:12.202', '2020-08-26 15:41:12.202', '1');
INSERT INTO `sys_role_menu` VALUES ('36', '2', '4', '2020-08-26 15:41:12.202', '2020-08-26 15:41:12.202', '1');
INSERT INTO `sys_role_menu` VALUES ('37', '2', '5', '2020-08-26 15:41:12.202', '2020-08-26 15:41:12.202', '1');
INSERT INTO `sys_role_menu` VALUES ('38', '2', '6', '2020-08-26 15:41:12.202', '2020-08-26 15:41:12.202', '1');
INSERT INTO `sys_role_menu` VALUES ('39', '2', '7', '2020-08-26 15:41:12.202', '2020-08-26 15:41:12.202', '1');
INSERT INTO `sys_role_menu` VALUES ('40', '2', '8', '2020-08-26 15:41:12.202', '2020-08-26 15:41:12.202', '1');
INSERT INTO `sys_role_menu` VALUES ('41', '2', '9', '2020-08-26 15:41:12.202', '2020-08-26 15:41:12.202', '1');
INSERT INTO `sys_role_menu` VALUES ('42', '2', '13', '2020-08-26 15:41:12.202', '2020-08-26 15:41:12.202', '1');
INSERT INTO `sys_role_menu` VALUES ('43', '2', '14', '2020-08-26 15:41:12.202', '2020-08-26 15:41:12.202', '1');
INSERT INTO `sys_role_menu` VALUES ('44', '2', '15', '2020-08-26 15:41:12.202', '2020-08-26 15:41:12.202', '1');
INSERT INTO `sys_role_menu` VALUES ('45', '2', '16', '2020-08-26 15:41:12.202', '2020-08-26 15:41:12.202', '1');
INSERT INTO `sys_role_menu` VALUES ('46', '2', '17', '2020-08-26 15:41:12.202', '2020-08-26 15:41:12.202', '1');

-- ----------------------------
-- Table structure for sys_upload_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_upload_config`;
CREATE TABLE `sys_upload_config` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `biz_name` varchar(100) DEFAULT NULL COMMENT '业务名称',
  `biz_type` varchar(32) NOT NULL COMMENT '业务类型',
  `file_size_min` bigint(20) unsigned DEFAULT '0' COMMENT '限定上传文件大小最小值，单位`byte`。（0为不限制）',
  `file_size_max` bigint(20) unsigned DEFAULT '0' COMMENT '限定上传文件大小最大值，单位`byte`。（0为不限制）',
  `file_ext` varchar(64) NOT NULL COMMENT '限定用户上传后辍(多个逗号分割)',
  `upload_dir` varchar(100) DEFAULT '' COMMENT '上传目录',
  `upload_sub_dir` varchar(255) DEFAULT '' COMMENT '上传子目录',
  `base_url` varchar(32) DEFAULT '' COMMENT '访问地址前辍',
  `callback_url` varchar(100) DEFAULT '' COMMENT '回调地址',
  `naming_strategy` varchar(32) NOT NULL COMMENT '命名策略',
  `is_record` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否记录(1->不记录|NO,2->记录|YES)',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) unsigned DEFAULT '1' COMMENT '是否删除(1->未删除|NO,2->已删除|YES)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='上传配置';

-- ----------------------------
-- Records of sys_upload_config
-- ----------------------------
INSERT INTO `sys_upload_config` VALUES ('1', '头像', 'avatar', '0', '2097152', '.png,.jpg,.jpeg', null, '', 'http://qiniu.mldong.com/', 'http://api.mldong.com/sys/uploadRecord/handleCallback', 'default', '1', '头像', '2020-06-14 12:07:03.024', '2020-06-14 12:07:03.024', '2');

-- ----------------------------
-- Table structure for sys_upload_record
-- ----------------------------
DROP TABLE IF EXISTS `sys_upload_record`;
CREATE TABLE `sys_upload_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `biz_type` varchar(32) DEFAULT NULL COMMENT '业务类型',
  `biz_id` varchar(40) DEFAULT NULL COMMENT '业务id',
  `url` varchar(100) DEFAULT NULL COMMENT '文件保存的资源路径',
  `file_name` varchar(100) DEFAULT NULL COMMENT '上传的原始文件名',
  `file_size` bigint(20) unsigned DEFAULT NULL COMMENT '资源大小，单位为字节',
  `mime_type` varchar(32) DEFAULT NULL COMMENT '资源类型',
  `file_ext` varchar(10) DEFAULT NULL COMMENT '上传资源的后缀名',
  `create_time` datetime(3) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(3) DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) unsigned DEFAULT '1' COMMENT '是否删除(1->未删除|NO,2->已删除|YES)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='上传记录';

-- ----------------------------
-- Records of sys_upload_record
-- ----------------------------
INSERT INTO `sys_upload_record` VALUES ('4', 'avatar', '7f07a3cc-77ba-47b9-af74-88b2631f2bc4', 'avatar/202006/50d0f9e5-fa60-4551-a0d9-714923575637.png', 'qrcode.png', '497', 'image/png', '.png', '2020-06-14 13:10:30.102', '2020-06-14 13:10:30.102', '2');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(32) NOT NULL COMMENT '用户名',
  `real_name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `avatar` varchar(200) DEFAULT NULL COMMENT '头像',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `mobile_phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `telephone` varchar(20) DEFAULT NULL COMMENT '电话',
  `password` varchar(40) DEFAULT NULL COMMENT '密码',
  `salt` varchar(10) DEFAULT NULL COMMENT '加盐',
  `sex` int(6) unsigned DEFAULT '1' COMMENT '性别(1->男|MALE,2->女|FEMALE,3->未知|UNKNOWN)',
  `is_locked` tinyint(1) unsigned DEFAULT '1' COMMENT '是否锁定(2->已锁定|YES,1->未锁定|NO)',
  `dept_id` bigint(20) unsigned DEFAULT NULL COMMENT '部门id',
  `post_id` bigint(20) unsigned DEFAULT NULL COMMENT '岗位id',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否删除(1->未删除|NO,2->已删除|YES)',
  PRIMARY KEY (`id`),
  KEY `real_name` (`real_name`),
  KEY `user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'mldong', '', '', '18676163666', '', '52618c88aa68c63d37e50d6acd8b8456', 'v7hc7v69', '1', '1', '6', '4', null, '2020-06-09 21:47:33.417', '2020-10-22 08:47:53.307', '1');
INSERT INTO `sys_user` VALUES ('5', 'demo666', '测试号', null, null, '18276163688', null, '835d2134b35fa1d9387e42f625331acb', 'kkv5dxxw', '1', '1', null, null, null, '2020-06-19 23:00:53.709', '2020-06-20 00:15:45.055', '2');
INSERT INTO `sys_user` VALUES ('6', '小李子', '李白', null, '851321457@qq.com', '13669584561', null, 'ab55f269cbe23cc7f55dfbaf68bc3ac3', 'c2vntsr0', '2', '1', null, null, null, '2020-06-21 16:22:07.275', '2020-06-21 16:26:41.629', '2');
INSERT INTO `sys_user` VALUES ('7', '123', '123', null, '123456789@qq.com', '13333333333', null, 'f74a8d6c25e272cab53c33fa44459e09', 'dm78rsew', '1', '1', null, null, null, '2020-06-22 09:54:38.859', '2020-06-22 10:04:55.848', '2');
INSERT INTO `sys_user` VALUES ('8', '对方的发', '大的', null, '8551312@163.com', '13023123256', null, '810fe86875a5687287e84af1cef2ad54', 'g80fa065', '1', '1', null, null, null, '2020-06-22 10:00:08.934', '2020-06-22 10:04:51.947', '2');
INSERT INTO `sys_user` VALUES ('9', 'ni', '大的', null, '8551312@163.com', '13023123256', null, 'c079872794690156289617a60a11c316', 'ztih3jzc', '2', '1', null, null, null, '2020-06-22 10:01:12.890', '2020-07-09 14:17:28.401', '2');
INSERT INTO `sys_user` VALUES ('10', '地方v的', 'hghg', null, '13696452586@163.com', '13645607895', null, 'd9acbc2eef540ffc8ea6dd8fdacd37cc', 'jpqocubh', '2', '1', null, null, null, '2020-06-22 10:06:41.892', '2020-07-09 14:17:25.562', '2');
INSERT INTO `sys_user` VALUES ('11', '孙狗', '孙笑川', null, null, '17444444444', null, '7941882c9fdcd4bb2a17b804d2b5c5ba', 'ewst066f', '1', '1', null, null, null, '2020-06-22 15:03:06.617', '2020-07-09 14:17:20.153', '2');
INSERT INTO `sys_user` VALUES ('12', 'mldong', 'mldong', null, '', '18276162636', null, 'cc11af98f4e595d4ee99154fc212ec49', 's9x7qwnk', '1', '1', null, null, null, '2020-07-01 21:49:03.509', '2020-07-09 14:17:17.237', '2');
INSERT INTO `sys_user` VALUES ('13', 'www', 'www', null, null, '13000000000', null, 'b2e5c56f0b93187d054a0be5c03e13c3', 'dwuiljkf', '1', '1', null, null, null, '2020-07-02 12:25:29.586', '2020-07-02 14:25:48.123', '2');
INSERT INTO `sys_user` VALUES ('14', 'dsfdf', 'dfgdfg', null, '85513141@163.com', '13512345678', null, '5850c814ee651629fb437c01227e9abd', 't60qeu2p', '1', '1', null, null, null, '2020-07-09 14:18:02.937', '2020-07-11 11:16:00.501', '2');
INSERT INTO `sys_user` VALUES ('15', '111111', '111111111', null, null, '13333333333', null, '6b58a2de83b205feed7d931f8eb639c1', 'vqmpxen7', '1', '1', '7', '4', null, '2020-07-15 09:16:17.159', '2020-10-22 08:48:57.973', '1');

-- ----------------------------
-- Table structure for sys_user_login_times
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_login_times`;
CREATE TABLE `sys_user_login_times` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户id',
  `login_ip` char(15) DEFAULT NULL COMMENT '登录ip',
  `times` int(10) unsigned NOT NULL DEFAULT '1' COMMENT '登录次数',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否删除(1->未删除|NO,2->已删除|YES)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户登录次数';

-- ----------------------------
-- Records of sys_user_login_times
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户id',
  `role_id` bigint(20) unsigned NOT NULL COMMENT '角色id',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否删除(1->未删除|NO,2->已删除|YES)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关系表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '10', '2', '2020-07-01 21:24:36.859', '2020-07-01 21:24:36.859', '2');
INSERT INTO `sys_user_role` VALUES ('2', '11', '2', '2020-07-01 21:24:36.859', '2020-07-01 21:24:36.859', '2');
INSERT INTO `sys_user_role` VALUES ('3', '11', '2', '2020-07-01 21:34:35.573', '2020-07-01 21:34:35.573', '1');
INSERT INTO `sys_user_role` VALUES ('4', '9', '2', '2020-07-01 21:35:27.229', '2020-07-01 21:35:27.229', '2');
INSERT INTO `sys_user_role` VALUES ('5', '9', '2', '2020-07-01 21:36:31.589', '2020-07-01 21:36:31.589', '2');
INSERT INTO `sys_user_role` VALUES ('6', '9', '2', '2020-07-01 21:40:58.618', '2020-07-01 21:40:58.618', '2');
INSERT INTO `sys_user_role` VALUES ('7', '1', '2', '2020-07-01 21:46:19.510', '2020-07-01 21:46:19.510', '2');
INSERT INTO `sys_user_role` VALUES ('8', '9', '2', '2020-07-01 21:47:30.524', '2020-07-01 21:47:30.524', '2');
INSERT INTO `sys_user_role` VALUES ('9', '1', '2', '2020-07-01 21:47:49.724', '2020-07-01 21:47:49.724', '2');
INSERT INTO `sys_user_role` VALUES ('10', '12', '3', '2020-07-01 21:50:12.448', '2020-07-01 21:50:12.448', '1');
INSERT INTO `sys_user_role` VALUES ('11', '1', '2', '2020-07-02 12:21:17.749', '2020-07-02 12:21:17.749', '1');
INSERT INTO `sys_user_role` VALUES ('12', '1', '4', '2020-07-15 09:15:32.158', '2020-07-15 09:15:32.158', '1');
