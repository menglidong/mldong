/*
Navicat MySQL Data Transfer

Source Server         : me
Source Server Version : 50619
Source Host           : localhost:3306
Source Database       : mldong

Target Server Type    : MYSQL
Target Server Version : 50619
File Encoding         : 65001

Date: 2020-06-14 11:42:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_dict`
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
-- Table structure for `sys_dict_item`
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
-- Table structure for `sys_menu`
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
-- Table structure for `sys_role`
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
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否删除(1->未删除|YES,2->已删除|NO)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '普通管理员', 'NORMAL_MANAGE', '10', '1', '普通管理员', '2020-05-31 07:07:26.000', '2020-05-31 07:07:29.000', '2');

-- ----------------------------
-- Table structure for `sys_role_access`
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
-- Table structure for `sys_role_menu`
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
-- Table structure for `sys_upload_config`
-- ----------------------------
DROP TABLE IF EXISTS `sys_upload_config`;
CREATE TABLE `sys_upload_config` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `biz_type` varchar(32) NOT NULL COMMENT '业务类型',
  `file_size_min` bigint(20) unsigned DEFAULT '0' COMMENT '限定上传文件大小最小值，单位`byte`。（0为不限制）',
  `file_size_max` bigint(20) unsigned DEFAULT '0' COMMENT '限定上传文件大小最大值，单位`byte`。（0为不限制）',
  `file_ext` varchar(64) NOT NULL COMMENT '限定用户上传后辍(多个逗号分割)',
  `base_url` varchar(32) DEFAULT '' COMMENT '访问地址前辍',
  `callback_url` varchar(100) DEFAULT '' COMMENT '回调地址',
  `naming_strategy` varchar(32) NOT NULL COMMENT '命名策略',
  `is_record` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否记录(1->不记录|NO,2->记录|YES)',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) unsigned DEFAULT '1' COMMENT '是否删除(1->未删除|NO,2->已删除|YES)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='上传配置';

-- ----------------------------
-- Records of sys_upload_config
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_upload_record`
-- ----------------------------
DROP TABLE IF EXISTS `sys_upload_record`;
CREATE TABLE `sys_upload_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `biz_type` varchar(32) DEFAULT NULL COMMENT '业务类型',
  `biz_id` bigint(20) unsigned DEFAULT NULL COMMENT '业务id',
  `url` varchar(100) DEFAULT NULL COMMENT '文件保存的资源路径',
  `file_name` varchar(100) DEFAULT NULL COMMENT '上传的原始文件名',
  `file_size` bigint(20) unsigned DEFAULT NULL COMMENT '资源大小，单位为字节',
  `mime_type` varchar(32) DEFAULT NULL COMMENT '资源类型',
  `file_ext` varchar(10) DEFAULT NULL COMMENT '上传资源的后缀名',
  `create_time` datetime(3) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(3) DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) unsigned DEFAULT '1' COMMENT '是否删除(1->未删除|NO,2->已删除|YES)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='上传记录';

-- ----------------------------
-- Records of sys_upload_record
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_user`
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
  `is_locked` tinyint(1) unsigned DEFAULT '2' COMMENT '是否锁定(1->已锁定|YES,2->未锁定|NO)',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否删除(1->未删除|NO,2->已删除|YES)',
  PRIMARY KEY (`id`),
  KEY `real_name` (`real_name`),
  KEY `user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '蒙立东', '', '', '18276163680', '', '7b93ca5f9d7d0608299cf87c5ba3c164', 'v7hc7v69', '1', '2', null, '2020-06-09 21:47:33.417', '2020-06-09 21:47:33.417', '2');

-- ----------------------------
-- Table structure for `sys_user_login_times`
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
-- Table structure for `sys_user_role`
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
