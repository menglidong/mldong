/*
Navicat MySQL Data Transfer

Source Server         : dev
Source Server Version : 50728
Source Host           : 192.168.1.160:3306
Source Database       : mldong

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2020-10-26 10:36:08
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='部门';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='字典';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', '测试', 'sys_test', '测试', '2020-06-11 23:11:49.532', '2020-06-11 23:11:49.532', '2');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='字典项';

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
INSERT INTO `sys_dict_item` VALUES ('1', '1', '男', '10', '10.00', '', '2020-06-11 23:12:37.042', '2020-06-11 23:12:37.042', '2');

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
  `icon` varchar(32) DEFAULT NULL COMMENT '图标',
  `is_show` tinyint(1) unsigned DEFAULT '2' COMMENT '是否显示(1->不显示|NO,2->显示|YES)',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否删除(1->未删除|NO,2->已删除|YES)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='岗位';

-- ----------------------------
-- Records of sys_post
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COMMENT='请求日志';

-- ----------------------------
-- Records of sys_request_log
-- ----------------------------
INSERT INTO `sys_request_log` VALUES ('2', '369fc910-bc9b-4514-9152-1d1a645050f9', '30', '/sys/user/remove', '', 'POST', '删除用户222', '0:0:0:0:0:0:0:1', '{\n	\"ids\": [22]\n}', 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHQiOiJ7fSIsImlzcyI6Im1sZG9uZyIsImV4cCI6MTU5OTQwMTIyMSwidXNlck5hbWUiOiJhZG1pbiIsImlhdCI6MTU5OTM5NDAyMSwidXNlcklkIjoxfQ.obM5vUe_4UtK4Grz7ud8ZOIiiQGZ1a-s_PwA6K8gY94', '1', 'admin', '{\"code\":99999999,\"msg\":\"删除用户失败\",\"data\":null}', '2020-09-06 20:08:28.135', '2020-09-06 20:08:28.301', '2020-09-06 20:08:28.301', '2020-09-06 20:08:28.301', '1');
INSERT INTO `sys_request_log` VALUES ('3', '05169859-7acf-4396-8673-cf8106b43d0d', '99', '/error', '', 'GET', null, '0:0:0:0:0:0:0:1', null, null, '0', '', '{\"code\":99990401,\"msg\":\"未授权\",\"data\":null}', '2020-10-21 09:06:15.160', '2020-10-21 09:08:45.434', '2020-10-21 09:08:45.434', '2020-10-21 09:08:45.434', '1');
INSERT INTO `sys_request_log` VALUES ('4', '2f3669e2-3a53-4aed-a772-51471a992c08', '99', '/sys/login', '', 'POST', '登录系统', '0:0:0:0:0:0:0:1', '{\n	\"password\": \"mldong@321\",\n	\"userName\": \"admin\"\n}', null, null, null, '{\"code\":0,\"msg\":\"登录成功\",\"data\":{\"token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHQiOiJ7fSIsImlzcyI6Im1sZG9uZyIsImV4cCI6MTYwMzI1MzcwOCwidXNlck5hbWUiOiJhZG1pbiIsImlhdCI6MTYwMzI0NjUwOCwidXNlcklkIjoxfQ.NTvo-FUGWdvK2M_GiphPAWdtDginnQ72337nxpw6pXQ\",\"userId\":1,\"userName\":\"admin\",\"realName\":\"蒙立东\",\"avatar\":\"\",\"accessList\":[\"admin\"],\"menuList\":[]}}', '2020-10-21 10:15:08.257', '2020-10-21 10:15:09.114', '2020-10-21 10:15:09.115', '2020-10-21 10:15:09.115', '1');
INSERT INTO `sys_request_log` VALUES ('5', '91180755-98bd-469c-9ea1-f0d21ea872df', '99', '/sys/login', '', 'POST', '登录系统', '0:0:0:0:0:0:0:1', '{\n	\"password\": \"mldong@321\",\n	\"userName\": \"admin\"\n}', null, null, null, '{\"code\":0,\"msg\":\"登录成功\",\"data\":{\"token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHQiOiJ7fSIsImlzcyI6Im1sZG9uZyIsImV4cCI6MTYwMzMzNzAwNiwidXNlck5hbWUiOiJhZG1pbiIsImlhdCI6MTYwMzMyOTgwNiwidXNlcklkIjoxfQ.RuRZPD_KqHV60o9xtPD7H5k5_VBU9RsVZGHJu1za_us\",\"userId\":1,\"userName\":\"admin\",\"realName\":\"蒙立东\",\"avatar\":\"\",\"accessList\":[\"admin\"],\"menuList\":[]}}', '2020-10-22 09:23:26.095', '2020-10-22 09:23:26.854', '2020-10-22 09:23:26.855', '2020-10-22 09:23:26.855', '1');
INSERT INTO `sys_request_log` VALUES ('6', '7d6d1ec5-ffac-4f88-89fe-f97bc8eb0c13', '99', '/sys/login', '', 'POST', '登录系统', '0:0:0:0:0:0:0:1', '{\n	\"password\": \"mldong@321\",\n	\"userName\": \"admin\"\n}', null, null, null, '{\"code\":0,\"msg\":\"登录成功\",\"data\":{\"token\":\"21f78109accb498088d54df9cd8cc910\",\"userId\":1,\"userName\":\"admin\",\"realName\":\"蒙立东\",\"avatar\":\"\",\"accessList\":[\"admin\"],\"menuList\":[]}}', '2020-10-23 11:50:11.362', '2020-10-23 11:50:12.800', '2020-10-23 11:50:12.801', '2020-10-23 11:50:12.801', '1');
INSERT INTO `sys_request_log` VALUES ('7', '4d145037-eda3-4606-aa8a-e7f361cccaf5', '99', '/sys/login', '', 'POST', '登录系统', '0:0:0:0:0:0:0:1', '{\n	\"password\": \"mldong@321\",\n	\"userName\": \"admin\"\n}', null, null, null, '{\"code\":0,\"msg\":\"登录成功\",\"data\":{\"token\":\"7e9cd2f4b0a4448c8cca50ceda936996\",\"userId\":1,\"userName\":\"admin\",\"realName\":\"蒙立东\",\"avatar\":\"\",\"accessList\":[\"admin\"],\"menuList\":[]}}', '2020-10-23 11:52:29.380', '2020-10-23 11:52:29.398', '2020-10-23 11:52:29.398', '2020-10-23 11:52:29.398', '1');
INSERT INTO `sys_request_log` VALUES ('8', '0498ee32-f1ae-4d3a-918f-b0e157ef64c9', '99', '/sys/login', '', 'POST', '登录系统', '0:0:0:0:0:0:0:1', '{\n	\"password\": \"mldong@321\",\n	\"userName\": \"admin\"\n}', null, null, null, '{\"code\":0,\"msg\":\"登录成功\",\"data\":{\"token\":\"682211a8a0fb4eae95fe6eede9cf6778\",\"userId\":1,\"userName\":\"admin\",\"realName\":\"蒙立东\",\"avatar\":\"\",\"accessList\":[\"admin\"],\"menuList\":[]}}', '2020-10-23 11:58:51.930', '2020-10-23 11:58:53.372', '2020-10-23 11:58:53.372', '2020-10-23 11:58:53.372', '1');
INSERT INTO `sys_request_log` VALUES ('9', '3341b8e6-2fb0-4b41-ad95-7d0c5b04a31b', '99', '/sys/login', '', 'POST', '登录系统', '0:0:0:0:0:0:0:1', '{\n	\"password\": \"mldong@321\",\n	\"userName\": \"admin\"\n}', null, null, null, '{\"code\":0,\"msg\":\"登录成功\",\"data\":{\"token\":\"cb99d1169e3b4e7e8e278c77eba04503\",\"userId\":1,\"userName\":\"admin\",\"realName\":\"蒙立东\",\"avatar\":\"\",\"accessList\":[\"admin\"],\"menuList\":[]}}', '2020-10-23 11:59:21.224', '2020-10-23 11:59:22.654', '2020-10-23 11:59:22.655', '2020-10-23 11:59:22.655', '1');
INSERT INTO `sys_request_log` VALUES ('10', '36ebddf9-86d3-4ac8-9cf8-d27bb075d1b4', '99', '/sys/login', '', 'POST', '登录系统', '0:0:0:0:0:0:0:1', '{\n	\"password\": \"mldong@321\",\n	\"userName\": \"admin\"\n}', null, null, null, '{\"code\":0,\"msg\":\"登录成功\",\"data\":{\"token\":\"fc84aac2d3ed4d56868104a81aad86bf\",\"userId\":1,\"userName\":\"admin\",\"realName\":\"蒙立东\",\"avatar\":\"\",\"accessList\":[\"admin\"],\"menuList\":[]}}', '2020-10-23 12:01:22.509', '2020-10-23 12:01:23.946', '2020-10-23 12:01:23.946', '2020-10-23 12:01:23.946', '1');
INSERT INTO `sys_request_log` VALUES ('11', '7a2334e6-e270-4684-bbbd-2634de702c35', '99', '/sys/login', '', 'POST', '登录系统', '0:0:0:0:0:0:0:1', '{\n	\"password\": \"mldong@321\",\n	\"userName\": \"admin\"\n}', null, null, null, '{\"code\":0,\"msg\":\"登录成功\",\"data\":{\"token\":\"c9a5b84ed83d416c844523948043b3b0\",\"userId\":1,\"userName\":\"admin\",\"realName\":\"蒙立东\",\"avatar\":\"\",\"accessList\":[\"admin\"],\"menuList\":[]}}', '2020-10-23 15:02:05.823', '2020-10-23 15:02:07.252', '2020-10-23 15:02:07.252', '2020-10-23 15:02:07.252', '1');
INSERT INTO `sys_request_log` VALUES ('12', '68ced801-ed0b-41ee-8384-efc5c944c6c4', '99', '/sys/login', '', 'POST', '登录系统', '0:0:0:0:0:0:0:1', '{\n	\"password\": \"mldong@321\",\n	\"userName\": \"admin\"\n}', null, null, null, '{\"code\":0,\"msg\":\"登录成功\",\"data\":{\"token\":\"980c5dad24864e078e5265f6c61b85a2\",\"userId\":1,\"userName\":\"admin\",\"realName\":\"蒙立东\",\"avatar\":\"\",\"accessList\":[\"admin\"],\"menuList\":[]}}', '2020-10-23 15:05:06.216', '2020-10-23 15:05:07.681', '2020-10-23 15:05:07.682', '2020-10-23 15:05:07.682', '1');
INSERT INTO `sys_request_log` VALUES ('13', '3f1dc587-5ec5-40bc-baf8-581bdd8c86cd', '99', '/sys/login', '', 'POST', '登录系统', '0:0:0:0:0:0:0:1', '{\n	\"password\": \"mldong@321\",\n	\"userName\": \"admin\"\n}', null, null, null, '{\"code\":0,\"msg\":\"登录成功\",\"data\":{\"token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHQiOiJ7fSIsImlzcyI6Im1sZG9uZyIsImV4cCI6MTYwMzQ0NDIyNiwidXNlck5hbWUiOiJhZG1pbiIsImlhdCI6MTYwMzQzNzAyNiwidXNlcklkIjoxfQ.dlZQJ8AAKrViLxvrBIZLK05LEhazKdcEjr4iogdmCBs\",\"userId\":1,\"userName\":\"admin\",\"realName\":\"蒙立东\",\"avatar\":\"\",\"accessList\":[\"admin\"],\"menuList\":[]}}', '2020-10-23 15:10:26.405', '2020-10-23 15:10:27.234', '2020-10-23 15:10:27.235', '2020-10-23 15:10:27.235', '1');
INSERT INTO `sys_request_log` VALUES ('14', '12264fac-9ce3-4fac-910c-3e023370bc10', '99', '/sys/login', '', 'POST', '登录系统', '0:0:0:0:0:0:0:1', '{\n	\"password\": \"mldong@321\",\n	\"userName\": \"admin\"\n}', null, null, null, '{\"code\":99999999,\"msg\":\"Unable to connect to Redis; nested exception is io.lettuce.core.RedisConnectionException: Unable to connect to localhost:6379\",\"data\":null}', '2020-10-23 15:14:20.970', '2020-10-23 15:14:24.434', '2020-10-23 15:14:24.434', '2020-10-23 15:14:24.434', '1');
INSERT INTO `sys_request_log` VALUES ('15', 'd1c2a333-fdc7-427c-9da3-0f8c8412ce6f', '99', '/sys/login', '', 'POST', '登录系统', '0:0:0:0:0:0:0:1', '{\n	\"password\": \"mldong@321\",\n	\"userName\": \"admin\"\n}', null, null, null, '{\"code\":0,\"msg\":\"登录成功\",\"data\":{\"token\":\"f45485d2316b467a9d3009a7ba0a4570\",\"userId\":1,\"userName\":\"admin\",\"realName\":\"蒙立东\",\"avatar\":\"\",\"accessList\":[\"admin\"],\"menuList\":[]}}', '2020-10-23 15:15:55.608', '2020-10-23 15:15:57.121', '2020-10-23 15:15:57.121', '2020-10-23 15:15:57.121', '1');
INSERT INTO `sys_request_log` VALUES ('16', 'f2d959dc-30d4-43ee-b120-78fcd4c3168d', '99', '/sys/login', '', 'POST', '登录系统', '0:0:0:0:0:0:0:1', '{\n	\"password\": \"mldong@321\",\n	\"userName\": \"admin\"\n}', null, null, null, '{\"code\":0,\"msg\":\"登录成功\",\"data\":{\"token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHQiOiJ7fSIsImlzcyI6Im1sZG9uZyIsImV4cCI6MTYwMzQ0NjI5NiwidXNlck5hbWUiOiJhZG1pbiIsImlhdCI6MTYwMzQzOTA5NiwidXNlcklkIjoxfQ.bO6a7zDtVUdtsudZFKmP9Nk0QJxYqZCsB-nqXxZjoX4\",\"userId\":1,\"userName\":\"admin\",\"realName\":\"蒙立东\",\"avatar\":\"\",\"accessList\":[\"admin\"],\"menuList\":[]}}', '2020-10-23 15:44:56.534', '2020-10-23 15:44:57.344', '2020-10-23 15:44:57.345', '2020-10-23 15:44:57.345', '1');
INSERT INTO `sys_request_log` VALUES ('17', 'c9b74293-7fcc-45ae-aa2f-a28f2d20dcfc', '99', '/sys/login', '', 'POST', '登录系统', '0:0:0:0:0:0:0:1', '{\n	\"password\": \"mldong@321\",\n	\"userName\": \"admin\"\n}', null, null, null, '{\"code\":0,\"msg\":\"登录成功\",\"data\":{\"token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHQiOiJ7fSIsImlzcyI6Im1sZG9uZyIsImV4cCI6MTYwMzY4NjIwNSwidXNlck5hbWUiOiJhZG1pbiIsImlhdCI6MTYwMzY3OTAwNSwidXNlcklkIjoxfQ.KCiWg-39XA7PsQ52dFwwDKk51gjgGceCNYzYjly5sxo\",\"userId\":1,\"userName\":\"admin\",\"realName\":\"蒙立东\",\"avatar\":\"\",\"accessList\":[\"admin\"],\"menuList\":[]}}', '2020-10-26 10:23:25.357', '2020-10-26 10:23:25.569', '2020-10-26 10:23:25.569', '2020-10-26 10:23:25.569', '1');
INSERT INTO `sys_request_log` VALUES ('18', 'e74b3ad6-5913-4ac4-8d4e-df5a49579d1d', '99', '/sys/uploadRecord/file', '', 'POST', '上传文件', '0:0:0:0:0:0:0:1', null, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHQiOiJ7fSIsImlzcyI6Im1sZG9uZyIsImV4cCI6MTYwMzY4NjIwNSwidXNlck5hbWUiOiJhZG1pbiIsImlhdCI6MTYwMzY3OTAwNSwidXNlcklkIjoxfQ.KCiWg-39XA7PsQ52dFwwDKk51gjgGceCNYzYjly5sxo', '1', 'admin', '{\"code\":99990008,\"msg\":\"文件上传配置不存在\",\"data\":null}', '2020-10-26 10:23:50.732', '2020-10-26 10:23:50.744', '2020-10-26 10:23:50.744', '2020-10-26 10:23:50.744', '1');
INSERT INTO `sys_request_log` VALUES ('19', 'f9560f4e-feca-4983-be05-02615cdc4f8b', '99', '/sys/uploadRecord/file', '', 'POST', '上传文件', '0:0:0:0:0:0:0:1', null, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHQiOiJ7fSIsImlzcyI6Im1sZG9uZyIsImV4cCI6MTYwMzY4NjIwNSwidXNlck5hbWUiOiJhZG1pbiIsImlhdCI6MTYwMzY3OTAwNSwidXNlcklkIjoxfQ.KCiWg-39XA7PsQ52dFwwDKk51gjgGceCNYzYjly5sxo', '1', 'admin', '{\"code\":99990006,\"msg\":\"文件后辍不允许\",\"data\":null}', '2020-10-26 10:24:36.277', '2020-10-26 10:24:36.287', '2020-10-26 10:24:36.287', '2020-10-26 10:24:36.287', '1');
INSERT INTO `sys_request_log` VALUES ('20', '6c9e8935-2788-4bd2-ac37-b039d9e0a945', '99', '/sys/uploadRecord/file', '', 'POST', '上传文件', '0:0:0:0:0:0:0:1', null, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHQiOiJ7fSIsImlzcyI6Im1sZG9uZyIsImV4cCI6MTYwMzY4NjIwNSwidXNlck5hbWUiOiJhZG1pbiIsImlhdCI6MTYwMzY3OTAwNSwidXNlcklkIjoxfQ.KCiWg-39XA7PsQ52dFwwDKk51gjgGceCNYzYjly5sxo', '1', 'admin', '{\"code\":99990006,\"msg\":\"文件后辍不允许\",\"data\":null}', '2020-10-26 10:24:43.743', '2020-10-26 10:24:43.752', '2020-10-26 10:24:43.753', '2020-10-26 10:24:43.753', '1');
INSERT INTO `sys_request_log` VALUES ('21', '3bd5ab99-ec23-4131-bcba-702174177cc9', '99', '/sys/uploadRecord/file', '', 'POST', '上传文件', '0:0:0:0:0:0:0:1', null, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHQiOiJ7fSIsImlzcyI6Im1sZG9uZyIsImV4cCI6MTYwMzY4NjIwNSwidXNlck5hbWUiOiJhZG1pbiIsImlhdCI6MTYwMzY3OTAwNSwidXNlcklkIjoxfQ.KCiWg-39XA7PsQ52dFwwDKk51gjgGceCNYzYjly5sxo', '1', 'admin', '{\"code\":99990006,\"msg\":\"文件后辍不允许\",\"data\":null}', '2020-10-26 10:26:15.894', '2020-10-26 10:28:55.310', '2020-10-26 10:28:55.310', '2020-10-26 10:28:55.310', '1');
INSERT INTO `sys_request_log` VALUES ('22', '39d815a3-4023-45ca-8213-82f5f0b9e9f7', '99', '/sys/uploadRecord/file', '', 'POST', '上传文件', '0:0:0:0:0:0:0:1', null, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHQiOiJ7fSIsImlzcyI6Im1sZG9uZyIsImV4cCI6MTYwMzY4NjIwNSwidXNlck5hbWUiOiJhZG1pbiIsImlhdCI6MTYwMzY3OTAwNSwidXNlcklkIjoxfQ.KCiWg-39XA7PsQ52dFwwDKk51gjgGceCNYzYjly5sxo', '1', 'admin', '{\"code\":0,\"msg\":\"操作成功\",\"data\":{\"uploadRecordId\":1,\"bizId\":\"\",\"bizType\":\"\",\"baseUrl\":\"http://qiniu.mldong.com\",\"url\":\"common/7c003d02-9147-45bf-9563-b36a43435c3a.png\",\"fileSize\":806888,\"mimeType\":\"image/png\",\"fileName\":null,\"fileExt\":\".png\"}}', '2020-10-26 10:29:09.182', '2020-10-26 10:29:10.172', '2020-10-26 10:29:10.172', '2020-10-26 10:29:10.172', '1');
INSERT INTO `sys_request_log` VALUES ('23', 'f71f2584-a015-406d-bb61-63d96114b34d', '99', '/sys/uploadRecord/file', '', 'POST', '上传文件', '0:0:0:0:0:0:0:1', null, 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHQiOiJ7fSIsImlzcyI6Im1sZG9uZyIsImV4cCI6MTYwMzY4NjIwNSwidXNlck5hbWUiOiJhZG1pbiIsImlhdCI6MTYwMzY3OTAwNSwidXNlcklkIjoxfQ.KCiWg-39XA7PsQ52dFwwDKk51gjgGceCNYzYjly5sxo', '1', 'admin', '{\"code\":0,\"msg\":\"操作成功\",\"data\":{\"uploadRecordId\":2,\"bizId\":\"\",\"bizType\":\"\",\"baseUrl\":\"http://qiniu.mldong.com\",\"url\":\"2362a448-48bd-47fc-b09d-3ba7212a25a9.png\",\"fileSize\":806888,\"mimeType\":\"image/png\",\"fileName\":null,\"fileExt\":\".png\"}}', '2020-10-26 10:31:00.269', '2020-10-26 10:31:01.243', '2020-10-26 10:31:01.243', '2020-10-26 10:31:01.243', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '普通管理员', 'NORMAL_MANAGE', '10', '1', '普通管理员', '2020-05-31 07:07:26.000', '2020-05-31 07:07:29.000', '2');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限关系';

-- ----------------------------
-- Records of sys_role_access
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色菜单关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='上传配置';

-- ----------------------------
-- Records of sys_upload_config
-- ----------------------------
INSERT INTO `sys_upload_config` VALUES ('1', '通用', 'common', '0', '10485760', '.png,.gif,.jpng', 'D:/mldong/upload/common', '', 'http://qiniu.mldong.com', '', 'default', '1', '通用-10M', '2020-10-26 09:47:35.000', '2020-10-26 09:47:38.000', '1');
INSERT INTO `sys_upload_config` VALUES ('2', '食谱大图', 'stms_recipe_cover', '0', '10485760', '.png,.gif,.jpeg', 'D:/mldong/upload/image', '', 'http://qiniu.mldong.com', '', 'default', '1', '食谱大图-10M', '2020-10-26 09:49:06.000', '2020-10-26 09:49:10.000', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='上传记录';

-- ----------------------------
-- Records of sys_upload_record
-- ----------------------------
INSERT INTO `sys_upload_record` VALUES ('1', '', '', 'common/7c003d02-9147-45bf-9563-b36a43435c3a.png', '工作汇报20201015.png', '806888', 'image/png', '.png', '2020-10-26 10:29:09.962', '2020-10-26 10:29:09.962', '1');
INSERT INTO `sys_upload_record` VALUES ('2', '', '', '2362a448-48bd-47fc-b09d-3ba7212a25a9.png', '工作汇报20201015.png', '806888', 'image/png', '.png', '2020-10-26 10:31:01.061', '2020-10-26 10:31:01.061', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '蒙立东', '', '', '18276163680', '', '52618c88aa68c63d37e50d6acd8b8456', 'v7hc7v69', '1', '1', null, null, null, '2020-06-09 21:47:33.417', '2020-06-09 21:47:33.417', '1');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关系表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
