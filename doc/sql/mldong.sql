/*
 Navicat Premium Data Transfer

 Source Server         : hyper-db-root
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : 192.168.0.160:3306
 Source Schema         : mldong

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 08/05/2022 22:01:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cms_article
-- ----------------------------
DROP TABLE IF EXISTS `cms_article`;
CREATE TABLE `cms_article`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '栏目id',
  `title` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '大图',
  `author` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '作者',
  `source` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文章来源',
  `sort` double(10, 2) UNSIGNED NULL DEFAULT 10.00 COMMENT '排序',
  `status` int(6) UNSIGNED NULL DEFAULT 1 COMMENT '状态(1->草稿|DRAFT,2->审核中|AUDITING,3->审批通过|PASS,4->审批不通过|NO_PASS)',
  `business_no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '工作流业务编号',
  `publish_time` datetime(3) NULL DEFAULT NULL COMMENT '发布时间',
  `is_publish` tinyint(1) NULL DEFAULT NULL COMMENT '是否发布(1->否|NO,2->是|YES)',
  `content` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '文本内容',
  `ext_form_value` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '扩展信息',
  `dept_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '所属部门',
  `user_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '所属用户',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) UNSIGNED NULL DEFAULT 1 COMMENT '是否删除(1->未删除|NO,2->已删除|YES)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_article
-- ----------------------------
INSERT INTO `cms_article` VALUES (1, 1, '测试文章', NULL, NULL, '立东', NULL, 10.00, 1, 'e8492b4e38e348a1aae9053eb74ca29c', NULL, 1, NULL, '{}', NULL, NULL, '2022-04-05 20:30:38.955', '2022-04-06 22:39:29.135', 1);

-- ----------------------------
-- Table structure for cms_category
-- ----------------------------
DROP TABLE IF EXISTS `cms_category`;
CREATE TABLE `cms_category`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` bigint(20) UNSIGNED NULL DEFAULT 0 COMMENT '父栏目id',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '栏目名称',
  `sort` double(10, 2) UNSIGNED NULL DEFAULT 10.00 COMMENT '排序',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `is_nav` tinyint(1) UNSIGNED NULL DEFAULT 2 COMMENT '是否导航(1->否|NO,2->是|YES)',
  `is_show` tinyint(1) UNSIGNED NULL DEFAULT 2 COMMENT '是否显示(1->否|NO,2->是|YES)',
  `is_page` tinyint(1) UNSIGNED NULL DEFAULT 1 COMMENT '是否单页面(1->否|NO,2->是|YES)',
  `dept_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '所属部门',
  `user_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '所属用户',
  `model_id` bigint(255) UNSIGNED NULL DEFAULT NULL COMMENT '所属模型',
  `seo_keyworks` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'seo关键字',
  `seo_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'seo描述',
  `ext_form_config` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '扩展的表单配置',
  `ext_form_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展表单值',
  `ext_article_form_config` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '文章扩展字段配置',
  `content` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '富文本',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) UNSIGNED NULL DEFAULT 1 COMMENT '是否删除(1->未删除|NO,2->已删除|YES)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_category
-- ----------------------------
INSERT INTO `cms_category` VALUES (1, 0, '新闻中心', 1.00, NULL, 2, 2, 2, NULL, NULL, NULL, NULL, NULL, '{\"formItems\":[]}', NULL, '{\"formItems\":[]}', NULL, '2022-04-05 19:42:34.276', '2022-04-05 19:42:34.276', 1);

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号',
  `config_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数名称',
  `config_key` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数键名',
  `config_value` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '参数键值',
  `is_system` tinyint(1) UNSIGNED NULL DEFAULT 2 COMMENT '系统内置(1->否|NO,2->是|YES)',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) UNSIGNED NULL DEFAULT 1 COMMENT '是否删除(1->未删除|NO,2->已删除|YES)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '参数配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, 'test', 'test', '1', 2, NULL, '2022-05-02 14:58:52.625', '2022-05-02 14:58:52.625', 1);

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父级id',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门名称',
  `code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门编码',
  `sort` double(10, 2) NULL DEFAULT 10.00 COMMENT '排序',
  `contacts` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `mobile_phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系人手机号',
  `telephone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `is_enabled` tinyint(1) UNSIGNED NULL DEFAULT 2 COMMENT '是否启用(1->禁用|NO,2->启用|YES)',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) UNSIGNED NULL DEFAULT 1 COMMENT '是否删除(1->未删除|NO,2->已删除|YES)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (6, 0, '广西大学', '1', 10.00, NULL, NULL, NULL, NULL, NULL, 2, '2020-10-21 16:48:38.445', '2020-10-22 08:52:50.531', 1);
INSERT INTO `sys_dept` VALUES (7, 6, '计算机与电子信息学院', '2', 10.00, NULL, NULL, NULL, NULL, NULL, 2, '2020-10-21 16:48:51.857', '2020-10-22 08:53:03.495', 1);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `dict_key` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '唯一编码',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否删除(1->未删除|NO,2->已删除|YES)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (1, '测试', 'sys_test', '测试', '2020-06-11 23:11:49.532', '2020-06-11 23:11:49.532', 2);
INSERT INTO `sys_dict` VALUES (2, '用户类型', 'user_type', '用户类型123', '2020-06-28 21:23:32.961', '2020-07-16 23:51:19.401', 1);
INSERT INTO `sys_dict` VALUES (3, '测试', '3333', NULL, '2020-07-03 15:38:00.319', '2020-07-03 15:38:00.319', 1);
INSERT INTO `sys_dict` VALUES (4, '123', '123', '123', '2020-07-03 16:33:48.592', '2020-07-03 16:33:48.592', 1);

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dict_id` bigint(20) UNSIGNED NOT NULL COMMENT '字典id',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `dict_item_value` int(10) UNSIGNED NOT NULL DEFAULT 1 COMMENT '值',
  `sort` double(10, 2) UNSIGNED NULL DEFAULT 10.00 COMMENT '排序',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否删除(1->未删除|NO,2->已删除|YES)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典项' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
INSERT INTO `sys_dict_item` VALUES (1, 1, '男', 10, 10.00, '', '2020-06-11 23:12:37.042', '2020-06-11 23:12:37.042', 2);
INSERT INTO `sys_dict_item` VALUES (2, 2, 'test1', 10, 10.00, '普通用户', '2020-08-03 19:16:33.780', '2020-08-03 19:18:52.088', 2);
INSERT INTO `sys_dict_item` VALUES (3, 2, 'test1', 10, 10.00, '普通用户', '2020-08-03 19:19:04.534', '2020-08-03 19:50:26.957', 1);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` bigint(20) UNSIGNED NULL DEFAULT 0 COMMENT '父菜单id',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `sort` double(10, 2) NULL DEFAULT 10.00 COMMENT '排序',
  `route_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由名称',
  `path` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由地址',
  `icon` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `is_show` tinyint(1) UNSIGNED NULL DEFAULT 2 COMMENT '是否显示(1->不显示|NO,2->显示|YES)',
  `is_cache` tinyint(1) UNSIGNED NULL DEFAULT 1 COMMENT '是否缓存(1->不缓存|NO,2->缓存|YES)',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否删除(1->未删除|NO,2->已删除|YES)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, '系统设置', 10.00, 'sys', '/sys', 'sys', 2, 1, NULL, '2020-06-25 21:05:01.000', '2020-09-08 15:31:18.640', 1);
INSERT INTO `sys_menu` VALUES (2, 1, '菜单管理', 1.00, 'sys:menu:index', '/sys/menu/index', 'tree-table', 2, 1, NULL, '2020-06-25 21:06:34.000', '2020-10-30 08:49:07.433', 1);
INSERT INTO `sys_menu` VALUES (3, 1, '用户管理', 2.00, 'sys:user:index', '/sys/user/index', 'user', 2, 1, NULL, '2020-06-25 21:07:05.000', '2020-10-30 08:49:40.069', 1);
INSERT INTO `sys_menu` VALUES (4, 1, '角色管理', 3.00, 'sys:role:index', '/sys/role/index', 'peoples', 2, 1, NULL, '2020-06-25 21:07:37.000', '2020-10-30 08:50:08.941', 1);
INSERT INTO `sys_menu` VALUES (5, 1, '字典管理', 4.00, 'sys:dict:index', '/sys/dict/index', 'dict', 2, 1, NULL, '2020-06-25 21:08:08.000', '2020-10-30 08:50:30.754', 1);
INSERT INTO `sys_menu` VALUES (6, 0, '内容管理', 11.00, 'cms', '/cms', 'cms', 2, 1, NULL, '2020-06-25 21:09:05.000', '2020-06-29 09:31:58.956', 1);
INSERT INTO `sys_menu` VALUES (7, 6, '栏目管理', 1.00, 'cms:category:index', '/cms/category/index', 'table', 2, 1, NULL, '2020-06-25 21:09:36.000', '2020-10-30 08:53:54.727', 1);
INSERT INTO `sys_menu` VALUES (8, 6, '模型管理', 2.00, 'cms:model:index', '/cms/model/index', 'build', 2, 1, NULL, '2020-06-25 21:10:23.000', '2020-10-30 08:55:12.253', 1);
INSERT INTO `sys_menu` VALUES (9, 6, '文章管理', 3.00, 'cms:article:index', '/cms/article/index', 'documentation', 2, 1, NULL, '2020-06-25 21:10:50.000', '2020-10-30 08:55:19.841', 1);
INSERT INTO `sys_menu` VALUES (10, 0, '订单管理', 12.00, 'oms', '/oms', 'oms', 2, 1, NULL, '2020-06-25 21:11:29.000', '2020-06-29 09:31:58.956', 1);
INSERT INTO `sys_menu` VALUES (11, 10, '订单列表', 1.00, 'oms:order:index', '/oms/order/index', 'clipboard', 2, 1, NULL, '2020-06-25 21:11:55.000', '2020-10-30 08:55:49.596', 1);
INSERT INTO `sys_menu` VALUES (12, 10, '订单设置', 2.00, 'oms:orderSetting:index', '/oms/orderSetting/index', 'edit', 2, 1, NULL, '2020-06-25 21:12:15.000', '2020-10-30 08:56:25.056', 1);
INSERT INTO `sys_menu` VALUES (13, 0, '商品管理', 13.00, 'pms', 'pms', 'pms', 2, 1, NULL, '2020-06-25 21:14:02.000', '2020-07-10 10:31:46.482', 1);
INSERT INTO `sys_menu` VALUES (14, 13, '商品分类', 1.00, 'pms:productCategory:index', '/pms/productCategory/index', 'cascader', 2, 1, NULL, '2020-06-25 21:16:05.000', '2020-10-30 08:56:43.499', 1);
INSERT INTO `sys_menu` VALUES (15, 13, '商品列表', 2.00, 'pms:product:index', '/pms/product/index', 'excel', 2, 1, NULL, '2020-06-25 21:16:36.000', '2020-10-30 08:56:57.348', 1);
INSERT INTO `sys_menu` VALUES (16, 13, '品牌管理', 3.00, 'pms:brand:index', '/pms/brand/index', 'dashboard', 2, 1, NULL, '2020-06-25 21:16:57.000', '2020-10-30 08:57:06.981', 1);
INSERT INTO `sys_menu` VALUES (21, 1, '日志管理', 5.00, 'sys:requestLog:index', '/sys/requestLog/index', 'log', 2, 1, NULL, '2020-09-06 20:39:14.137', '2020-10-30 08:50:57.871', 1);
INSERT INTO `sys_menu` VALUES (22, 1, '部门管理', 10.00, 'sys:dept:index', '/sys/dept/index', 'tree', 2, 1, NULL, '2020-10-21 16:15:22.926', '2020-10-30 08:51:31.096', 1);
INSERT INTO `sys_menu` VALUES (23, 1, '岗位管理', 10.00, 'sys:post:index', '/sys/post/index', 'post', 2, 1, NULL, '2020-10-21 17:23:12.122', '2020-10-30 08:51:45.195', 1);
INSERT INTO `sys_menu` VALUES (24, 1, '通知公告', 10.10, 'sys:notice:index', '/sys/notice/index', 'message', 2, 1, NULL, '2020-10-27 20:41:46.632', '2020-10-30 08:52:52.775', 1);
INSERT INTO `sys_menu` VALUES (25, 1, '参数配置', 10.20, 'sys:config:index', '/sys/config/index', 'edit', 2, 1, NULL, '2020-11-08 16:39:05.673', '2020-11-08 16:39:05.673', 1);
INSERT INTO `sys_menu` VALUES (26, 1, '接口清单', 1.10, 'sys:menu:mapilist', '/sys/menu/mapilist', 'list', 2, 1, NULL, '2020-11-08 17:17:28.527', '2020-11-08 17:40:07.579', 1);
INSERT INTO `sys_menu` VALUES (27, 0, '工作流', 10.10, 'wf', '/wf', 'guide', 2, 1, NULL, '2022-04-04 20:15:47.642', '2022-04-04 20:16:13.804', 1);
INSERT INTO `sys_menu` VALUES (28, 27, '流程定义', 1.00, 'wf:process:index', '/wf/process/index', 'drag', 2, 1, NULL, '2022-04-04 20:16:48.936', '2022-04-04 20:16:48.936', 1);
INSERT INTO `sys_menu` VALUES (29, 27, '流程实例', 2.00, 'wf:order:index', '/wf/order/index', 'documentation', 2, 1, NULL, '2022-04-04 20:19:27.441', '2022-04-04 20:19:27.441', 1);
INSERT INTO `sys_menu` VALUES (30, 27, '流程任务', 3.00, 'wf:task:index', 'BlankLayout', 'excel', 2, 1, NULL, '2022-04-04 20:20:13.762', '2022-04-05 10:01:30.417', 1);
INSERT INTO `sys_menu` VALUES (31, 30, '我的待办', 1.00, 'wf:task:todolist', '/wf/task/todolist', NULL, 2, 1, NULL, '2022-04-05 09:48:03.076', '2022-04-05 09:48:03.076', 1);
INSERT INTO `sys_menu` VALUES (32, 30, '我的已办', 2.00, 'wf:task:donelist', '/wf/task/donelist', NULL, 2, 1, NULL, '2022-04-05 09:48:27.676', '2022-04-05 09:48:27.676', 1);

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公告标题',
  `type` int(6) UNSIGNED NULL DEFAULT 10 COMMENT '公告类型(10->通知|TZ,20->公告|GG)',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '公告内容',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) UNSIGNED NULL DEFAULT 1 COMMENT '是否删除(1->未删除|NO,2->已删除|YES)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '通知公告' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES (1, '元旦放假通知', 10, '元旦放假通知', '2020-11-03 21:55:00.915', '2020-11-03 21:55:00.915', 1);

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位名称',
  `code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位编号',
  `sort` double(10, 2) UNSIGNED NULL DEFAULT 10.00 COMMENT '排序',
  `is_enabled` tinyint(1) UNSIGNED NULL DEFAULT 2 COMMENT '是否启用(1->禁用|NO,2->启用|YES)',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) UNSIGNED NULL DEFAULT 1 COMMENT '是否删除(1->未删除|NO,2->已删除|YES)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES (1, '董事长', 'ceo', 10.00, 2, '2020-10-21 17:23:40.267', '2020-10-21 17:23:40.267', 1);
INSERT INTO `sys_post` VALUES (2, '项目经理', 'se', 10.00, 2, '2020-10-21 17:23:51.307', '2020-10-21 17:23:51.307', 1);
INSERT INTO `sys_post` VALUES (3, '人力资源', 'hr', 10.00, 2, '2020-10-21 17:24:11.484', '2020-10-21 17:24:11.484', 1);
INSERT INTO `sys_post` VALUES (4, '普通员工', 'user', 10.00, 2, '2020-10-21 17:25:01.004', '2020-10-21 17:25:01.004', 1);

-- ----------------------------
-- Table structure for sys_request_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_request_log`;
CREATE TABLE `sys_request_log`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `track_id` char(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求唯一标识',
  `request_type` int(10) UNSIGNED NULL DEFAULT 99 COMMENT '请求类型(10->添加|SAVE,20->修改|UPDATE,30->删除|REMOVE,40->导入|IMPORT,50->导出|EXPORT,99->其他|OTHER)',
  `uri` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求路径',
  `query_string` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求url参数',
  `method` char(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求方式',
  `description` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作说明',
  `ip` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '客户端ip',
  `body` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '请求体',
  `token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求token',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `return_data` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '返回参数',
  `start_time` datetime(3) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime(3) NULL DEFAULT NULL COMMENT '结束时间',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) UNSIGNED NULL DEFAULT 1 COMMENT '是否删除(1->未删除|NO,2->已删除|YES)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 683 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '请求日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_request_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色标识(唯一)',
  `role_type` int(6) NULL DEFAULT 10 COMMENT '角色类型(10->管理员|ADMIN,20->流程审核员|WORKFLOW)',
  `data_scope` int(6) UNSIGNED NULL DEFAULT 10 COMMENT '数据范围(10->所有数据权限|ALL,20->部门数据权限|DEPT,30->部门及以下数据权限|DEPT_CHILD,40->仅本人数据权限|MYSELF,50->自定义数据权限|CUSTOM)',
  `is_enabled` tinyint(1) NULL DEFAULT 2 COMMENT '是否启用(1->禁用|NO,2->启用|YES)',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否删除(1->未删除|NO,2->已删除|YES)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '普通管理员', 'NORMAL_MANAGE', 10, 10, 1, '普通管理员', '2020-05-31 07:07:26.000', '2020-05-31 07:07:29.000', 2);
INSERT INTO `sys_role` VALUES (2, '超级管理员', 'SUPER_ADMIN', 10, 10, 2, '超级管理员', '2020-06-27 07:20:01.638', '2020-07-14 16:54:49.631', 1);
INSERT INTO `sys_role` VALUES (3, '审批专员', 'approveBoss', 10, 10, 2, '审批专员', '2020-06-27 07:23:38.401', '2020-07-03 16:34:27.677', 1);
INSERT INTO `sys_role` VALUES (4, '工作流审批', 'approveDept', 10, 10, 2, '123', '2020-07-03 16:34:09.588', '2020-07-03 16:34:09.588', 1);

-- ----------------------------
-- Table structure for sys_role_access
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_access`;
CREATE TABLE `sys_role_access`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) UNSIGNED NOT NULL COMMENT '角色id',
  `access` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限标识',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否删除(1->未删除|NO,2->已删除|YES)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_sys_role_access`(`access`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色权限关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_access
-- ----------------------------
INSERT INTO `sys_role_access` VALUES (1, 3, 'sys:uploadRecord:index', '2020-06-30 23:06:48.500', '2020-06-30 23:06:48.500', 2);
INSERT INTO `sys_role_access` VALUES (2, 3, 'sys:upload:createUploadToken', '2020-06-30 23:06:48.500', '2020-06-30 23:06:48.500', 2);
INSERT INTO `sys_role_access` VALUES (3, 3, 'sys:uploadRecord:remove', '2020-06-30 23:06:48.500', '2020-06-30 23:06:48.500', 2);
INSERT INTO `sys_role_access` VALUES (4, 3, 'sys:uploadRecord:get', '2020-06-30 23:06:48.500', '2020-06-30 23:06:48.500', 2);
INSERT INTO `sys_role_access` VALUES (5, 3, 'sys:uploadRecord:list', '2020-06-30 23:06:48.500', '2020-06-30 23:06:48.500', 2);
INSERT INTO `sys_role_access` VALUES (6, 3, 'sys:dictItem:index', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', 1);
INSERT INTO `sys_role_access` VALUES (7, 3, 'sys:dictItem:remove', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', 1);
INSERT INTO `sys_role_access` VALUES (8, 3, 'sys:dictItem:get', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', 1);
INSERT INTO `sys_role_access` VALUES (9, 3, 'sys:dictItem:update', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', 1);
INSERT INTO `sys_role_access` VALUES (10, 3, 'sys:dictItem:list', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', 1);
INSERT INTO `sys_role_access` VALUES (11, 3, 'sys:dictItem:save', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', 1);
INSERT INTO `sys_role_access` VALUES (12, 3, 'sys:dict:index', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', 1);
INSERT INTO `sys_role_access` VALUES (13, 3, 'sys:dict:listAllEnum', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', 1);
INSERT INTO `sys_role_access` VALUES (14, 3, 'sys:dict:getByDictKey', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', 1);
INSERT INTO `sys_role_access` VALUES (15, 3, 'sys:dict:remove', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', 1);
INSERT INTO `sys_role_access` VALUES (16, 3, 'sys:dict:get', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', 1);
INSERT INTO `sys_role_access` VALUES (17, 3, 'sys:dict:update', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', 1);
INSERT INTO `sys_role_access` VALUES (18, 3, 'sys:dict:list', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', 1);
INSERT INTO `sys_role_access` VALUES (19, 3, 'sys:dict:save', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', 1);
INSERT INTO `sys_role_access` VALUES (20, 3, 'sys:user:get', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', 1);
INSERT INTO `sys_role_access` VALUES (21, 3, 'sys:user:update', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', 1);
INSERT INTO `sys_role_access` VALUES (22, 3, 'sys:user:list', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', 1);
INSERT INTO `sys_role_access` VALUES (23, 3, 'sys:user:save', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', 1);
INSERT INTO `sys_role_access` VALUES (24, 3, 'sys:menu:index', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', 1);
INSERT INTO `sys_role_access` VALUES (25, 3, 'sys:menu:remove', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', 1);
INSERT INTO `sys_role_access` VALUES (26, 3, 'sys:menu:get', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', 1);
INSERT INTO `sys_role_access` VALUES (27, 3, 'sys:menu:update', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', 1);
INSERT INTO `sys_role_access` VALUES (28, 3, 'sys:menu:list', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', 1);
INSERT INTO `sys_role_access` VALUES (29, 3, 'sys:menu:save', '2020-07-01 21:50:57.094', '2020-07-01 21:50:57.094', 1);
INSERT INTO `sys_role_access` VALUES (30, 2, 'sys:role:remove', '2020-07-02 17:21:14.484', '2020-07-02 17:21:14.484', 1);
INSERT INTO `sys_role_access` VALUES (31, 2, 'sys:uploadConfig:index', '2020-07-07 09:16:39.508', '2020-07-07 09:16:39.508', 2);
INSERT INTO `sys_role_access` VALUES (32, 2, 'sys:uploadConfig:remove', '2020-07-07 09:16:39.508', '2020-07-07 09:16:39.508', 2);
INSERT INTO `sys_role_access` VALUES (33, 2, 'sys:uploadConfig:get', '2020-07-07 09:16:39.508', '2020-07-07 09:16:39.508', 2);
INSERT INTO `sys_role_access` VALUES (34, 2, 'sys:uploadConfig:update', '2020-07-07 09:16:39.508', '2020-07-07 09:16:39.508', 2);
INSERT INTO `sys_role_access` VALUES (35, 2, 'sys:uploadConfig:list', '2020-07-07 09:16:39.508', '2020-07-07 09:16:39.508', 2);
INSERT INTO `sys_role_access` VALUES (36, 2, 'sys:uploadConfig:save', '2020-07-07 09:16:39.508', '2020-07-07 09:16:39.508', 2);
INSERT INTO `sys_role_access` VALUES (37, 2, 'sys:user:index', '2020-07-19 02:31:43.325', '2020-07-19 02:31:43.325', 1);
INSERT INTO `sys_role_access` VALUES (38, 2, 'sys:user:remove', '2020-07-19 02:31:43.325', '2020-07-19 02:31:43.325', 1);
INSERT INTO `sys_role_access` VALUES (39, 2, 'sys:user:get', '2020-07-19 02:31:43.325', '2020-07-19 02:31:43.325', 1);
INSERT INTO `sys_role_access` VALUES (40, 2, 'sys:user:update', '2020-07-19 02:31:43.325', '2020-07-19 02:31:43.325', 1);
INSERT INTO `sys_role_access` VALUES (41, 2, 'sys:user:list', '2020-07-19 02:31:43.325', '2020-07-19 02:31:43.325', 1);
INSERT INTO `sys_role_access` VALUES (42, 2, 'sys:user:save', '2020-07-19 02:31:43.325', '2020-07-19 02:31:43.325', 1);

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) UNSIGNED NOT NULL COMMENT '角色id',
  `dept_id` bigint(20) UNSIGNED NOT NULL COMMENT '角色id',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否删除(1->未删除|NO,2->已删除|YES)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色部门关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES (1, 3, 6, '2020-12-23 20:02:27.000', '2020-12-23 20:02:29.000', 1);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) UNSIGNED NOT NULL COMMENT '角色id',
  `menu_id` bigint(20) UNSIGNED NOT NULL COMMENT '菜单id',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否删除(1->未删除|NO,2->已删除|YES)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色菜单关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 2, 6, '2020-06-30 23:13:54.765', '2020-06-30 23:13:54.765', 2);
INSERT INTO `sys_role_menu` VALUES (2, 2, 7, '2020-06-30 23:13:54.765', '2020-06-30 23:13:54.765', 2);
INSERT INTO `sys_role_menu` VALUES (3, 2, 8, '2020-06-30 23:13:54.765', '2020-06-30 23:13:54.765', 2);
INSERT INTO `sys_role_menu` VALUES (4, 2, 9, '2020-06-30 23:13:54.765', '2020-06-30 23:13:54.765', 2);
INSERT INTO `sys_role_menu` VALUES (5, 3, 7, '2020-06-30 23:26:49.656', '2020-06-30 23:26:49.656', 2);
INSERT INTO `sys_role_menu` VALUES (6, 3, 8, '2020-06-30 23:26:49.656', '2020-06-30 23:26:49.656', 2);
INSERT INTO `sys_role_menu` VALUES (7, 3, 10, '2020-06-30 23:26:49.656', '2020-06-30 23:26:49.656', 2);
INSERT INTO `sys_role_menu` VALUES (8, 3, 11, '2020-06-30 23:26:49.656', '2020-06-30 23:26:49.656', 2);
INSERT INTO `sys_role_menu` VALUES (9, 3, 12, '2020-06-30 23:26:49.656', '2020-06-30 23:26:49.656', 2);
INSERT INTO `sys_role_menu` VALUES (10, 3, 1, '2020-07-01 21:51:20.294', '2020-07-01 21:51:20.294', 2);
INSERT INTO `sys_role_menu` VALUES (11, 3, 2, '2020-07-01 21:51:20.294', '2020-07-01 21:51:20.294', 1);
INSERT INTO `sys_role_menu` VALUES (12, 3, 3, '2020-07-01 21:51:20.294', '2020-07-01 21:51:20.294', 1);
INSERT INTO `sys_role_menu` VALUES (13, 3, 4, '2020-07-01 21:51:20.294', '2020-07-01 21:51:20.294', 2);
INSERT INTO `sys_role_menu` VALUES (14, 3, 5, '2020-07-01 21:51:20.294', '2020-07-01 21:51:20.294', 1);
INSERT INTO `sys_role_menu` VALUES (15, 3, 7, '2020-07-01 21:51:20.294', '2020-07-01 21:51:20.294', 1);
INSERT INTO `sys_role_menu` VALUES (16, 3, 9, '2020-07-01 21:51:20.294', '2020-07-01 21:51:20.294', 1);
INSERT INTO `sys_role_menu` VALUES (17, 2, 2, '2020-07-02 12:20:51.642', '2020-07-02 12:20:51.642', 2);
INSERT INTO `sys_role_menu` VALUES (18, 2, 3, '2020-07-02 12:20:51.642', '2020-07-02 12:20:51.642', 2);
INSERT INTO `sys_role_menu` VALUES (19, 2, 1, '2020-07-02 12:23:15.379', '2020-07-02 12:23:15.379', 2);
INSERT INTO `sys_role_menu` VALUES (20, 2, 4, '2020-07-02 12:23:15.379', '2020-07-02 12:23:15.379', 2);
INSERT INTO `sys_role_menu` VALUES (21, 2, 5, '2020-07-02 12:23:15.379', '2020-07-02 12:23:15.379', 2);
INSERT INTO `sys_role_menu` VALUES (22, 2, 10, '2020-07-02 12:23:15.379', '2020-07-02 12:23:15.379', 2);
INSERT INTO `sys_role_menu` VALUES (23, 2, 11, '2020-07-02 12:23:15.379', '2020-07-02 12:23:15.379', 2);
INSERT INTO `sys_role_menu` VALUES (24, 2, 12, '2020-07-02 12:23:15.379', '2020-07-02 12:23:15.379', 1);
INSERT INTO `sys_role_menu` VALUES (25, 2, 13, '2020-07-02 12:23:15.379', '2020-07-02 12:23:15.379', 2);
INSERT INTO `sys_role_menu` VALUES (26, 2, 14, '2020-07-02 12:23:15.379', '2020-07-02 12:23:15.379', 2);
INSERT INTO `sys_role_menu` VALUES (27, 2, 15, '2020-07-02 12:23:15.379', '2020-07-02 12:23:15.379', 2);
INSERT INTO `sys_role_menu` VALUES (28, 2, 16, '2020-07-02 12:23:15.379', '2020-07-02 12:23:15.379', 2);
INSERT INTO `sys_role_menu` VALUES (29, 2, 10, '2020-07-02 13:48:35.866', '2020-07-02 13:48:35.866', 1);
INSERT INTO `sys_role_menu` VALUES (30, 2, 11, '2020-07-02 13:48:35.866', '2020-07-02 13:48:35.866', 1);
INSERT INTO `sys_role_menu` VALUES (31, 4, 3, '2020-07-15 09:15:50.925', '2020-07-15 09:15:50.925', 1);
INSERT INTO `sys_role_menu` VALUES (32, 4, 4, '2020-07-15 09:15:50.925', '2020-07-15 09:15:50.925', 1);
INSERT INTO `sys_role_menu` VALUES (33, 2, 1, '2020-08-26 15:41:12.202', '2020-08-26 15:41:12.202', 1);
INSERT INTO `sys_role_menu` VALUES (34, 2, 2, '2020-08-26 15:41:12.202', '2020-08-26 15:41:12.202', 1);
INSERT INTO `sys_role_menu` VALUES (35, 2, 3, '2020-08-26 15:41:12.202', '2020-08-26 15:41:12.202', 1);
INSERT INTO `sys_role_menu` VALUES (36, 2, 4, '2020-08-26 15:41:12.202', '2020-08-26 15:41:12.202', 1);
INSERT INTO `sys_role_menu` VALUES (37, 2, 5, '2020-08-26 15:41:12.202', '2020-08-26 15:41:12.202', 1);
INSERT INTO `sys_role_menu` VALUES (38, 2, 6, '2020-08-26 15:41:12.202', '2020-08-26 15:41:12.202', 1);
INSERT INTO `sys_role_menu` VALUES (39, 2, 7, '2020-08-26 15:41:12.202', '2020-08-26 15:41:12.202', 1);
INSERT INTO `sys_role_menu` VALUES (40, 2, 8, '2020-08-26 15:41:12.202', '2020-08-26 15:41:12.202', 1);
INSERT INTO `sys_role_menu` VALUES (41, 2, 9, '2020-08-26 15:41:12.202', '2020-08-26 15:41:12.202', 1);
INSERT INTO `sys_role_menu` VALUES (42, 2, 13, '2020-08-26 15:41:12.202', '2020-08-26 15:41:12.202', 1);
INSERT INTO `sys_role_menu` VALUES (43, 2, 14, '2020-08-26 15:41:12.202', '2020-08-26 15:41:12.202', 1);
INSERT INTO `sys_role_menu` VALUES (44, 2, 15, '2020-08-26 15:41:12.202', '2020-08-26 15:41:12.202', 1);
INSERT INTO `sys_role_menu` VALUES (45, 2, 16, '2020-08-26 15:41:12.202', '2020-08-26 15:41:12.202', 1);
INSERT INTO `sys_role_menu` VALUES (46, 2, 17, '2020-08-26 15:41:12.202', '2020-08-26 15:41:12.202', 1);

-- ----------------------------
-- Table structure for sys_upload_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_upload_config`;
CREATE TABLE `sys_upload_config`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `biz_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务名称',
  `biz_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '业务类型',
  `file_size_min` bigint(20) UNSIGNED NULL DEFAULT 0 COMMENT '限定上传文件大小最小值，单位`byte`。（0为不限制）',
  `file_size_max` bigint(20) UNSIGNED NULL DEFAULT 0 COMMENT '限定上传文件大小最大值，单位`byte`。（0为不限制）',
  `file_ext` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '限定用户上传后辍(多个逗号分割)',
  `upload_dir` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '上传目录',
  `upload_sub_dir` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '上传子目录',
  `base_url` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '访问地址前辍',
  `callback_url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '回调地址',
  `naming_strategy` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '命名策略',
  `is_record` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否记录(1->不记录|NO,2->记录|YES)',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) UNSIGNED NULL DEFAULT 1 COMMENT '是否删除(1->未删除|NO,2->已删除|YES)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '上传配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_upload_config
-- ----------------------------
INSERT INTO `sys_upload_config` VALUES (1, '头像', 'avatar', 0, 2097152, '.png,.jpg,.jpeg', NULL, '', 'http://qiniu.mldong.com/', 'http://api.mldong.com/sys/uploadRecord/handleCallback', 'default', 1, '头像', '2020-06-14 12:07:03.024', '2020-06-14 12:07:03.024', 2);

-- ----------------------------
-- Table structure for sys_upload_record
-- ----------------------------
DROP TABLE IF EXISTS `sys_upload_record`;
CREATE TABLE `sys_upload_record`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `biz_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务类型',
  `biz_id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务id',
  `url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件保存的资源路径',
  `file_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上传的原始文件名',
  `file_size` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '资源大小，单位为字节',
  `mime_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源类型',
  `file_ext` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上传资源的后缀名',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) UNSIGNED NULL DEFAULT 1 COMMENT '是否删除(1->未删除|NO,2->已删除|YES)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '上传记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_upload_record
-- ----------------------------
INSERT INTO `sys_upload_record` VALUES (4, 'avatar', '7f07a3cc-77ba-47b9-af74-88b2631f2bc4', 'avatar/202006/50d0f9e5-fa60-4551-a0d9-714923575637.png', 'qrcode.png', 497, 'image/png', '.png', '2020-06-14 13:10:30.102', '2020-06-14 13:10:30.102', 2);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `real_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `avatar` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `mobile_phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `telephone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '加盐',
  `sex` int(6) UNSIGNED NULL DEFAULT 1 COMMENT '性别(1->男|MALE,2->女|FEMALE,3->未知|UNKNOWN)',
  `is_locked` tinyint(1) UNSIGNED NULL DEFAULT 1 COMMENT '是否锁定(2->已锁定|YES,1->未锁定|NO)',
  `dept_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '部门id',
  `post_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '岗位id',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否删除(1->未删除|NO,2->已删除|YES)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `real_name`(`real_name`) USING BTREE,
  INDEX `user_name`(`user_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', 'mldong', '', '', '18676163666', '', 'd235fdf30381ac33cf71a19344a97590', 'qpma6gn1', 1, 1, 6, 4, NULL, '2020-06-09 21:47:33.417', '2020-10-22 08:47:53.307', 1);
INSERT INTO `sys_user` VALUES (5, 'demo666', '测试号', NULL, NULL, '18276163688', NULL, '835d2134b35fa1d9387e42f625331acb', 'kkv5dxxw', 1, 1, NULL, NULL, NULL, '2020-06-19 23:00:53.709', '2020-06-20 00:15:45.055', 2);
INSERT INTO `sys_user` VALUES (6, '小李子', '李白', NULL, '851321457@qq.com', '13669584561', NULL, 'ab55f269cbe23cc7f55dfbaf68bc3ac3', 'c2vntsr0', 2, 1, NULL, NULL, NULL, '2020-06-21 16:22:07.275', '2020-06-21 16:26:41.629', 2);
INSERT INTO `sys_user` VALUES (7, '123', '123', NULL, '123456789@qq.com', '13333333333', NULL, 'f74a8d6c25e272cab53c33fa44459e09', 'dm78rsew', 1, 1, NULL, NULL, NULL, '2020-06-22 09:54:38.859', '2020-06-22 10:04:55.848', 2);
INSERT INTO `sys_user` VALUES (8, '对方的发', '大的', NULL, '8551312@163.com', '13023123256', NULL, '810fe86875a5687287e84af1cef2ad54', 'g80fa065', 1, 1, NULL, NULL, NULL, '2020-06-22 10:00:08.934', '2020-06-22 10:04:51.947', 2);
INSERT INTO `sys_user` VALUES (9, 'ni', '大的', NULL, '8551312@163.com', '13023123256', NULL, 'c079872794690156289617a60a11c316', 'ztih3jzc', 2, 1, NULL, NULL, NULL, '2020-06-22 10:01:12.890', '2020-07-09 14:17:28.401', 2);
INSERT INTO `sys_user` VALUES (10, '地方v的', 'hghg', NULL, '13696452586@163.com', '13645607895', NULL, 'd9acbc2eef540ffc8ea6dd8fdacd37cc', 'jpqocubh', 2, 1, NULL, NULL, NULL, '2020-06-22 10:06:41.892', '2020-07-09 14:17:25.562', 2);
INSERT INTO `sys_user` VALUES (11, '孙狗', '孙笑川', NULL, NULL, '17444444444', NULL, '7941882c9fdcd4bb2a17b804d2b5c5ba', 'ewst066f', 1, 1, NULL, NULL, NULL, '2020-06-22 15:03:06.617', '2020-07-09 14:17:20.153', 2);
INSERT INTO `sys_user` VALUES (12, 'mldong', 'mldong', NULL, '', '18276162636', NULL, 'cc11af98f4e595d4ee99154fc212ec49', 's9x7qwnk', 1, 1, NULL, NULL, NULL, '2020-07-01 21:49:03.509', '2020-07-09 14:17:17.237', 1);
INSERT INTO `sys_user` VALUES (13, 'www', 'www', NULL, NULL, '13000000000', NULL, 'b2e5c56f0b93187d054a0be5c03e13c3', 'dwuiljkf', 1, 1, NULL, NULL, NULL, '2020-07-02 12:25:29.586', '2020-07-02 14:25:48.123', 2);
INSERT INTO `sys_user` VALUES (14, 'dsfdf', 'dfgdfg', NULL, '85513141@163.com', '13512345678', NULL, '5850c814ee651629fb437c01227e9abd', 't60qeu2p', 1, 1, NULL, NULL, NULL, '2020-07-09 14:18:02.937', '2020-07-11 11:16:00.501', 2);
INSERT INTO `sys_user` VALUES (15, '111111', '111111111', NULL, NULL, '13333333333', NULL, '6b58a2de83b205feed7d931f8eb639c1', 'vqmpxen7', 1, 1, 7, 4, NULL, '2020-07-15 09:16:17.159', '2020-10-22 08:48:57.973', 1);

-- ----------------------------
-- Table structure for sys_user_login_times
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_login_times`;
CREATE TABLE `sys_user_login_times`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '用户id',
  `login_ip` char(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录ip',
  `times` int(10) UNSIGNED NOT NULL DEFAULT 1 COMMENT '登录次数',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否删除(1->未删除|NO,2->已删除|YES)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户登录次数' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_login_times
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '用户id',
  `role_id` bigint(20) UNSIGNED NOT NULL COMMENT '角色id',
  `create_time` datetime(3) NOT NULL COMMENT '创建时间',
  `update_time` datetime(3) NOT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否删除(1->未删除|NO,2->已删除|YES)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 10, 2, '2020-07-01 21:24:36.859', '2020-07-01 21:24:36.859', 2);
INSERT INTO `sys_user_role` VALUES (2, 11, 2, '2020-07-01 21:24:36.859', '2020-07-01 21:24:36.859', 2);
INSERT INTO `sys_user_role` VALUES (3, 11, 2, '2020-07-01 21:34:35.573', '2020-07-01 21:34:35.573', 1);
INSERT INTO `sys_user_role` VALUES (4, 9, 2, '2020-07-01 21:35:27.229', '2020-07-01 21:35:27.229', 2);
INSERT INTO `sys_user_role` VALUES (5, 9, 2, '2020-07-01 21:36:31.589', '2020-07-01 21:36:31.589', 2);
INSERT INTO `sys_user_role` VALUES (6, 9, 2, '2020-07-01 21:40:58.618', '2020-07-01 21:40:58.618', 2);
INSERT INTO `sys_user_role` VALUES (7, 1, 2, '2020-07-01 21:46:19.510', '2020-07-01 21:46:19.510', 2);
INSERT INTO `sys_user_role` VALUES (8, 9, 2, '2020-07-01 21:47:30.524', '2020-07-01 21:47:30.524', 2);
INSERT INTO `sys_user_role` VALUES (9, 1, 2, '2020-07-01 21:47:49.724', '2020-07-01 21:47:49.724', 2);
INSERT INTO `sys_user_role` VALUES (10, 12, 3, '2020-07-01 21:50:12.448', '2020-07-01 21:50:12.448', 1);
INSERT INTO `sys_user_role` VALUES (11, 1, 2, '2020-07-02 12:21:17.749', '2020-07-02 12:21:17.749', 1);
INSERT INTO `sys_user_role` VALUES (12, 1, 4, '2020-07-15 09:15:32.158', '2020-07-15 09:15:32.158', 1);
INSERT INTO `sys_user_role` VALUES (13, 1, 3, '2022-04-03 13:33:32.000', '2022-04-03 13:33:36.000', 1);

-- ----------------------------
-- Table structure for wf_cc_order
-- ----------------------------
DROP TABLE IF EXISTS `wf_cc_order`;
CREATE TABLE `wf_cc_order`  (
  `order_Id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '流程实例ID',
  `actor_Id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参与者ID',
  `creator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发起人',
  `create_Time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '抄送时间',
  `finish_Time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '完成时间',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '状态',
  INDEX `IDX_CCORDER_ORDER`(`order_Id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '抄送实例表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wf_cc_order
-- ----------------------------

-- ----------------------------
-- Table structure for wf_hist_order
-- ----------------------------
DROP TABLE IF EXISTS `wf_hist_order`;
CREATE TABLE `wf_hist_order`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `process_Id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '流程定义ID',
  `order_State` tinyint(1) NOT NULL COMMENT '状态',
  `creator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发起人',
  `create_Time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '发起时间',
  `end_Time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '完成时间',
  `expire_Time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '期望完成时间',
  `priority` tinyint(1) NULL DEFAULT NULL COMMENT '优先级',
  `parent_Id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父流程ID',
  `order_No` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '流程实例编号',
  `variable` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附属变量json存储',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `IDX_HIST_ORDER_PROCESSID`(`process_Id`) USING BTREE,
  INDEX `IDX_HIST_ORDER_NO`(`order_No`) USING BTREE,
  INDEX `FK_HIST_ORDER_PARENTID`(`parent_Id`) USING BTREE,
  CONSTRAINT `FK_HIST_ORDER_PARENTID` FOREIGN KEY (`parent_Id`) REFERENCES `wf_hist_order` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_HIST_ORDER_PROCESSID` FOREIGN KEY (`process_Id`) REFERENCES `wf_process` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '历史流程实例表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wf_hist_order
-- ----------------------------
INSERT INTO `wf_hist_order` VALUES ('3a73e28221f04c56be3a15926409d3d2', '29cf3267885b4f8a85f0ac0e89b735a9', 1, '1', '2022-05-06 23:05:51', NULL, NULL, NULL, NULL, '1b0221016ce44a6f9d2261a625ac8168', '{\"apply\":1,\"audit\":1,\"instanceUrl\":\"\",\"snaker.orderNo\":\"1b0221016ce44a6f9d2261a625ac8168\",\"operator.userName\":\"admin\",\"operator.realName\":\"mldong\"}');
INSERT INTO `wf_hist_order` VALUES ('70c2a2143487441b9e4a1d37126e95c4', 'b28c099e9a3648b68d4358ae26553519', 0, '1', '2022-04-05 22:45:51', '2022-04-05 22:51:59', NULL, NULL, NULL, 'dc13383002704e2bb9ff7accf3504f3b', '{\"f_day\":1,\"f_reason\":\"8888\",\"f_startTime\":\"2022-04-05 00:00:00\",\"f_endTime\":\"2022-04-06 00:00:00\",\"instanceUrl\":\"leaveForm\",\"snaker.orderNo\":\"dc13383002704e2bb9ff7accf3504f3b\",\"operator.userName\":\"admin\",\"operator.realName\":\"mldong\",\"orderStatus\":3,\"approvalType\":0,\"approvalOpinion\":\"不同意\"}');
INSERT INTO `wf_hist_order` VALUES ('e875cd755f05439a87143c38f9c26a4e', '29cf3267885b4f8a85f0ac0e89b735a9', 0, '1', '2022-05-06 22:20:54', '2022-05-06 22:58:30', NULL, NULL, NULL, 'c21034b29b33488581b8971bf594125a', '{\"apply\":1,\"audit\":1,\"instanceUrl\":\"\",\"snaker.orderNo\":\"c21034b29b33488581b8971bf594125a\",\"operator.userName\":\"admin\",\"operator.realName\":\"mldong\",\"hasNext\":1,\"orderState\":3}');
INSERT INTO `wf_hist_order` VALUES ('f1bd25797aae4732838676f539c622ef', 'b28c099e9a3648b68d4358ae26553519', 1, '1', '2022-04-19 21:35:57', NULL, NULL, NULL, NULL, '295279926757418fa88ad9e8a368cab6', '{\"f_day\":3,\"instanceUrl\":\"leaveForm\",\"snaker.orderNo\":\"295279926757418fa88ad9e8a368cab6\",\"operator.userName\":\"admin\",\"operator.realName\":\"mldong\"}');

-- ----------------------------
-- Table structure for wf_hist_task
-- ----------------------------
DROP TABLE IF EXISTS `wf_hist_task`;
CREATE TABLE `wf_hist_task`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `order_Id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '流程实例ID',
  `task_Name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务名称',
  `display_Name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务显示名称',
  `task_Type` tinyint(1) NOT NULL COMMENT '任务类型',
  `perform_Type` tinyint(1) NULL DEFAULT NULL COMMENT '参与类型',
  `task_State` tinyint(1) NOT NULL COMMENT '任务状态',
  `operator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务处理人',
  `create_Time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务创建时间',
  `finish_Time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务完成时间',
  `expire_Time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务期望完成时间',
  `action_Url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务处理url',
  `parent_Task_Id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父任务ID',
  `variable` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附属变量json存储',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `IDX_HIST_TASK_ORDER`(`order_Id`) USING BTREE,
  INDEX `IDX_HIST_TASK_TASKNAME`(`task_Name`) USING BTREE,
  INDEX `IDX_HIST_TASK_PARENTTASK`(`parent_Task_Id`) USING BTREE,
  CONSTRAINT `FK_HIST_TASK_ORDERID` FOREIGN KEY (`order_Id`) REFERENCES `wf_hist_order` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '历史任务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wf_hist_task
-- ----------------------------
INSERT INTO `wf_hist_task` VALUES ('09071361d62f4817aa13bf77b3bebaeb', '3a73e28221f04c56be3a15926409d3d2', 'audit', '审核', 0, 0, 0, '1', '2022-05-06 23:07:34', '2022-05-06 23:09:40', NULL, '', '24d936fc114a4e6995976581e06438fd', '{\"audit\":12,\"hasNext\":1,\"approvalType\":1,\"operator.userName\":\"admin\",\"operator.realName\":\"mldong\"}');
INSERT INTO `wf_hist_task` VALUES ('24d936fc114a4e6995976581e06438fd', '3a73e28221f04c56be3a15926409d3d2', 'audit', '审核', 0, 0, 0, '1', '2022-05-06 23:06:12', '2022-05-06 23:07:34', NULL, '', '7f3018bc7da94194830c7b93ae6a6118', '{\"audit\":1,\"hasNext\":1,\"approvalType\":1,\"operator.userName\":\"admin\",\"operator.realName\":\"mldong\"}');
INSERT INTO `wf_hist_task` VALUES ('4e139687bc02435bb878b247b45c2c7b', '70c2a2143487441b9e4a1d37126e95c4', 'apply', '请假申请', 0, 0, 0, 'snaker.auto', '2022-04-05 22:45:51', '2022-04-05 22:45:51', NULL, '', 'start', '{\"f_day\":1,\"f_reason\":\"8888\",\"f_startTime\":\"2022-04-05 00:00:00\",\"f_endTime\":\"2022-04-06 00:00:00\",\"instanceUrl\":\"leaveForm\",\"snaker.orderNo\":\"dc13383002704e2bb9ff7accf3504f3b\",\"operator.userName\":\"admin\",\"operator.realName\":\"mldong\",\"S-ACTOR\":\"approve.operator\"}');
INSERT INTO `wf_hist_task` VALUES ('50e8c560ef7c4e91acfc8ddb641ef811', 'e875cd755f05439a87143c38f9c26a4e', 'audit', '审核', 0, 0, 0, '1', '2022-05-06 22:20:54', '2022-05-06 22:58:30', NULL, '', 'b2cec0e7a1864e0f99bfbb67d5531f65', '{\"audit\":1,\"hasNext\":1,\"operator.userName\":\"admin\",\"operator.realName\":\"mldong\"}');
INSERT INTO `wf_hist_task` VALUES ('7f3018bc7da94194830c7b93ae6a6118', '3a73e28221f04c56be3a15926409d3d2', 'audit', '审核', 0, 0, 0, '1', '2022-05-06 23:05:51', '2022-05-06 23:06:12', NULL, '', 'ffeaaa58854545e49cce92160cffa051', '{\"audit\":1,\"hasNext\":1,\"approvalType\":1,\"operator.userName\":\"admin\",\"operator.realName\":\"mldong\"}');
INSERT INTO `wf_hist_task` VALUES ('8a89fc85f934431399a8fa7f45d5d6fe', '70c2a2143487441b9e4a1d37126e95c4', 'approveDept', '部门领导审批', 0, 0, 0, '1', '2022-04-05 22:45:51', '2022-04-05 22:51:59', NULL, '', '4e139687bc02435bb878b247b45c2c7b', '{\"approvalType\":0,\"approvalOpinion\":\"不同意\",\"operator.userName\":\"admin\",\"operator.realName\":\"mldong\"}');
INSERT INTO `wf_hist_task` VALUES ('b2cec0e7a1864e0f99bfbb67d5531f65', 'e875cd755f05439a87143c38f9c26a4e', 'apply', '申请', 0, 0, 0, 'snaker.auto', '2022-05-06 22:20:54', '2022-05-06 22:20:54', NULL, '', 'start', '{\"apply\":1,\"audit\":1,\"instanceUrl\":\"\",\"snaker.orderNo\":\"c21034b29b33488581b8971bf594125a\",\"operator.userName\":\"admin\",\"operator.realName\":\"mldong\",\"S-ACTOR\":\"1\"}');
INSERT INTO `wf_hist_task` VALUES ('b492070e6b0748aea23f9e15ef4755ca', 'f1bd25797aae4732838676f539c622ef', 'apply', '请假申请', 0, 0, 0, 'snaker.auto', '2022-04-19 21:35:57', '2022-04-19 21:35:58', NULL, '', 'start', '{\"f_day\":3,\"instanceUrl\":\"leaveForm\",\"snaker.orderNo\":\"295279926757418fa88ad9e8a368cab6\",\"operator.userName\":\"admin\",\"operator.realName\":\"mldong\",\"S-ACTOR\":\"approve.operator\"}');
INSERT INTO `wf_hist_task` VALUES ('ffeaaa58854545e49cce92160cffa051', '3a73e28221f04c56be3a15926409d3d2', 'apply', '申请', 0, 0, 0, 'snaker.auto', '2022-05-06 23:05:51', '2022-05-06 23:05:51', NULL, '', 'start', '{\"apply\":1,\"audit\":1,\"instanceUrl\":\"\",\"snaker.orderNo\":\"1b0221016ce44a6f9d2261a625ac8168\",\"operator.userName\":\"admin\",\"operator.realName\":\"mldong\",\"S-ACTOR\":\"1\"}');

-- ----------------------------
-- Table structure for wf_hist_task_actor
-- ----------------------------
DROP TABLE IF EXISTS `wf_hist_task_actor`;
CREATE TABLE `wf_hist_task_actor`  (
  `task_Id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务ID',
  `actor_Id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '参与者ID',
  INDEX `IDX_HIST_TASKACTOR_TASK`(`task_Id`) USING BTREE,
  CONSTRAINT `FK_HIST_TASKACTOR` FOREIGN KEY (`task_Id`) REFERENCES `wf_hist_task` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '历史任务参与者表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wf_hist_task_actor
-- ----------------------------
INSERT INTO `wf_hist_task_actor` VALUES ('4e139687bc02435bb878b247b45c2c7b', 'approve.operator');
INSERT INTO `wf_hist_task_actor` VALUES ('8a89fc85f934431399a8fa7f45d5d6fe', '1');
INSERT INTO `wf_hist_task_actor` VALUES ('b492070e6b0748aea23f9e15ef4755ca', 'approve.operator');
INSERT INTO `wf_hist_task_actor` VALUES ('b2cec0e7a1864e0f99bfbb67d5531f65', '1');
INSERT INTO `wf_hist_task_actor` VALUES ('50e8c560ef7c4e91acfc8ddb641ef811', '1');
INSERT INTO `wf_hist_task_actor` VALUES ('ffeaaa58854545e49cce92160cffa051', '1');
INSERT INTO `wf_hist_task_actor` VALUES ('7f3018bc7da94194830c7b93ae6a6118', '1');
INSERT INTO `wf_hist_task_actor` VALUES ('24d936fc114a4e6995976581e06438fd', '1');
INSERT INTO `wf_hist_task_actor` VALUES ('09071361d62f4817aa13bf77b3bebaeb', '1');

-- ----------------------------
-- Table structure for wf_model_designer
-- ----------------------------
DROP TABLE IF EXISTS `wf_model_designer`;
CREATE TABLE `wf_model_designer`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `model_group_id` bigint(20) UNSIGNED NOT NULL DEFAULT 0 COMMENT '分组ID',
  `model_type` int(6) UNSIGNED NOT NULL DEFAULT 10 COMMENT '模型类型(10->流程|PROCESS,20->表单|FORM)',
  `model_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '模型名称',
  `model_key` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '唯一标识',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
  `is_deleted` tinyint(1) UNSIGNED NULL DEFAULT 1 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '模型设计' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wf_model_designer
-- ----------------------------

-- ----------------------------
-- Table structure for wf_model_designer_his
-- ----------------------------
DROP TABLE IF EXISTS `wf_model_designer_his`;
CREATE TABLE `wf_model_designer_his`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `model_designer_id` bigint(20) NOT NULL COMMENT '模型设计ID',
  `content` longblob NULL COMMENT '模型定义',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_wf_model_d_his_id`(`model_designer_id`) USING BTREE,
  INDEX `idx_wf_mdh_create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '模型设计历史' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wf_model_designer_his
-- ----------------------------

-- ----------------------------
-- Table structure for wf_model_group
-- ----------------------------
DROP TABLE IF EXISTS `wf_model_group`;
CREATE TABLE `wf_model_group`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分组名称',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '模型分组' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wf_model_group
-- ----------------------------

-- ----------------------------
-- Table structure for wf_order
-- ----------------------------
DROP TABLE IF EXISTS `wf_order`;
CREATE TABLE `wf_order`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `parent_Id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父流程ID',
  `process_Id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '流程定义ID',
  `creator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发起人',
  `create_Time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '发起时间',
  `expire_Time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '期望完成时间',
  `last_Update_Time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上次更新时间',
  `last_Updator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上次更新人',
  `priority` tinyint(1) NULL DEFAULT NULL COMMENT '优先级',
  `parent_Node_Name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父流程依赖的节点名称',
  `order_No` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '流程实例编号',
  `variable` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附属变量json存储',
  `version` int(3) NULL DEFAULT NULL COMMENT '版本',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `IDX_ORDER_PROCESSID`(`process_Id`) USING BTREE,
  INDEX `IDX_ORDER_NO`(`order_No`) USING BTREE,
  INDEX `FK_ORDER_PARENTID`(`parent_Id`) USING BTREE,
  CONSTRAINT `FK_ORDER_PARENTID` FOREIGN KEY (`parent_Id`) REFERENCES `wf_order` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_ORDER_PROCESSID` FOREIGN KEY (`process_Id`) REFERENCES `wf_process` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '流程实例表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wf_order
-- ----------------------------
INSERT INTO `wf_order` VALUES ('3a73e28221f04c56be3a15926409d3d2', NULL, '29cf3267885b4f8a85f0ac0e89b735a9', '1', '2022-05-06 23:05:51', NULL, '2022-05-06 23:09:40', '1', NULL, NULL, '1b0221016ce44a6f9d2261a625ac8168', '{\"apply\":1,\"audit\":1,\"instanceUrl\":\"\",\"snaker.orderNo\":\"1b0221016ce44a6f9d2261a625ac8168\",\"operator.userName\":\"admin\",\"operator.realName\":\"mldong\"}', 4);
INSERT INTO `wf_order` VALUES ('f1bd25797aae4732838676f539c622ef', NULL, 'b28c099e9a3648b68d4358ae26553519', '1', '2022-04-19 21:35:57', NULL, '2022-04-19 21:35:58', 'snaker.auto', NULL, NULL, '295279926757418fa88ad9e8a368cab6', '{\"f_day\":3,\"instanceUrl\":\"leaveForm\",\"snaker.orderNo\":\"295279926757418fa88ad9e8a368cab6\",\"operator.userName\":\"admin\",\"operator.realName\":\"mldong\"}', 1);

-- ----------------------------
-- Table structure for wf_process
-- ----------------------------
DROP TABLE IF EXISTS `wf_process`;
CREATE TABLE `wf_process`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '流程名称',
  `display_Name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '流程显示名称',
  `type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '流程类型',
  `instance_Url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '实例url',
  `state` tinyint(1) NULL DEFAULT NULL COMMENT '流程是否可用',
  `content` longblob NULL COMMENT '流程模型定义',
  `version` int(2) NULL DEFAULT NULL COMMENT '版本',
  `create_Time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `IDX_PROCESS_NAME`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '流程定义表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wf_process
-- ----------------------------
INSERT INTO `wf_process` VALUES ('29cf3267885b4f8a85f0ac0e89b735a9', 'repeat', '循环流程', NULL, '', 1, 0x3C3F786D6C2076657273696F6E3D22312E302220656E636F64696E673D225554462D3822207374616E64616C6F6E653D226E6F223F3E0D0A3C70726F6365737320206E616D653D227265706561742220646973706C61794E616D653D22E5BEAAE78EAFE6B581E7A88B223E0D0A093C7374617274206E616D653D2273746172742220646973706C61794E616D653D22E5BC80E5A78B22206C61796F75743D223432302C3234302C3132302C3830223E0D0A09093C7472616E736974696F6E206E616D653D2237363566613232652D613335372D346662632D386262372D3534633537656435383132642220746F3D226170706C792220673D223433382C3234303B3536302C323430223E3C2F7472616E736974696F6E3E0D0A093C2F73746172743E0D0A093C7461736B206E616D653D226170706C792220646973706C61794E616D653D22E794B3E8AFB722206C61796F75743D223632302C3234302C3132302C3830222061737369676E65653D226170706C7922207461736B547970653D224D616A6F722220706572666F726D547970653D22414E59223E0D0A09093C7472616E736974696F6E206E616D653D2265363537663031612D653738362D343730382D626230352D6339643537656564333538322220746F3D2261756469742220673D223638302C3234303B3738302C323430223E3C2F7472616E736974696F6E3E0D0A093C2F7461736B3E0D0A093C7461736B206E616D653D2261756469742220646973706C61794E616D653D22E5AEA1E6A0B822206C61796F75743D223834302C3234302C3132302C3830222061737369676E65653D22617564697422207461736B547970653D224D616A6F722220706572666F726D547970653D22414E59223E0D0A09093C7472616E736974696F6E206E616D653D2264363362346362322D626234312D346163312D626561652D6264356435343866366639622220746F3D2235613133373332302D623566312D343939332D386566622D3764373364643537396466322220673D223930302C3234303B3939352C323430223E3C2F7472616E736974696F6E3E0D0A093C2F7461736B3E0D0A093C6465636973696F6E206E616D653D2235613133373332302D623566312D343939332D386566622D37643733646435373964663222206C61796F75743D22313032302C3234302C3132302C3830223E0D0A09093C7472616E736974696F6E206E616D653D2237636434323132622D646535642D343539322D393163302D6432363434396537623739332220746F3D22656E642220657870723D22236861734E6578743D3D302220673D22313034352C3234303B313136322C323430223E3C2F7472616E736974696F6E3E0D0A093C7472616E736974696F6E206E616D653D2232393639663462352D663935362D346666382D383030362D6536316537303137633235642220646973706C61794E616D653D22E8BDACE4BAA4E7BB99E696B0E79A84E5AEA1E6A0B8E4BABA2220746F3D2261756469742220657870723D22236861734E6578743D3D312220673D22313032302C3236353B313032302C3331303B3834302C3331303B3834302C323830223E3C2F7472616E736974696F6E3E0D0A093C2F6465636973696F6E3E0D0A093C656E64206E616D653D22656E642220646973706C61794E616D653D22E7BB93E69D9F22206C61796F75743D22313138302C3234302C3132302C3830223E0D0A09093C2F656E643E0D0A3C2F70726F636573733E, 0, '2022-05-06 22:18:35', '1');
INSERT INTO `wf_process` VALUES ('a5a211f4fda142319ca6479d1f98e387', 'articleAudit', '文章审核', NULL, 'articleAuditForm', 1, 0x3C3F786D6C2076657273696F6E3D22312E302220656E636F64696E673D225554462D3822207374616E64616C6F6E653D226E6F223F3E0A3C70726F6365737320206E616D653D2261727469636C6541756469742220646973706C61794E616D653D22E69687E7ABA0E5AEA1E6A0B82220696E7374616E636555726C3D2261727469636C654175646974466F726D223E0A093C7374617274206E616D653D2273746172742220646973706C61794E616D653D22E5BC80E5A78B22206C61796F75743D223336302C3138302C3132302C38302220707265496E746572636570746F72733D22636F6D2E6D6C646F6E672E6D6F64756C65732E636D732E77662E696E746572636570746F722E41727469636C655374617274507265496E746572636570746F72223E0A09093C7472616E736974696F6E206E616D653D2274312220746F3D226170706C792220673D223337382C3138303B3434302C313830223E3C2F7472616E736974696F6E3E0A093C2F73746172743E0A093C7461736B206E616D653D226170706C792220646973706C61794E616D653D22E69687E7ABA0E5AEA1E6A0B8E794B3E8AFB722206C61796F75743D223530302C3138302C3132302C383022207461736B547970653D224D616A6F722220706572666F726D547970653D22414E59222061737369676E65653D22617070726F76652E6F70657261746F72223E0A09093C7472616E736974696F6E206E616D653D2274322220746F3D22617070726F7665446570742220673D223536302C3138303B3632302C313830223E3C2F7472616E736974696F6E3E0A093C2F7461736B3E0A093C7461736B206E616D653D22617070726F7665446570742220646973706C61794E616D653D22E983A8E997A8E9A286E5AFBCE5AEA1E689B922206C61796F75743D223638302C3138302C3132302C383022207461736B547970653D224D616A6F722220706572666F726D547970653D22414E59222061737369676E6D656E7448616E646C65723D22636F6D2E6D6C646F6E672E636F6E6669672E466C6F7741737369676E6D656E7448616E646C6572223E0A09093C7472616E736974696F6E206E616D653D2274332220746F3D22617070726F7665426F73732220673D223734302C3138303B3830302C313830223E3C2F7472616E736974696F6E3E0A093C2F7461736B3E0A093C7461736B206E616D653D22617070726F7665426F73732220646973706C61794E616D653D22E585ACE58FB8E9A286E5AFBCE5AEA1E689B922206C61796F75743D223836302C3138302C3132302C383022207461736B547970653D224D616A6F722220706572666F726D547970653D22414E59222061737369676E6D656E7448616E646C65723D22636F6D2E6D6C646F6E672E636F6E6669672E466C6F7741737369676E6D656E7448616E646C6572223E0A09093C7472616E736974696F6E206E616D653D2274342220746F3D22656E642220673D223932302C3138303B3938322C313830223E3C2F7472616E736974696F6E3E0A093C2F7461736B3E0A093C656E64206E616D653D22656E642220646973706C61794E616D653D22E7BB93E69D9F22206C61796F75743D22313030302C3138302C3132302C38302220706F7374496E746572636570746F72733D22636F6D2E6D6C646F6E672E6D6F64756C65732E636D732E77662E696E746572636570746F722E41727469636C65456E64506F7374496E7465726570746F72223E0A09093C2F656E643E0A3C2F70726F636573733E, 0, '2022-04-05 21:53:29', '1');
INSERT INTO `wf_process` VALUES ('b28c099e9a3648b68d4358ae26553519', 'leave', '请假', NULL, 'leaveForm', 1, 0x3C3F786D6C2076657273696F6E3D22312E302220656E636F64696E673D225554462D3822207374616E64616C6F6E653D226E6F223F3E0A3C70726F6365737320206E616D653D226C656176652220646973706C61794E616D653D22E8AFB7E581872220696E7374616E636555726C3D226C65617665466F726D223E0A093C7374617274206E616D653D2273746172742220646973706C61794E616D653D22E5BC80E5A78B22206C61796F75743D223334302C3136302C3132302C3830223E0A09093C7472616E736974696F6E206E616D653D2233303337626534312D353638322D343334342D623934612D3966616635633365363262612220746F3D226170706C792220673D223335382C3136303B3436302C313630223E3C2F7472616E736974696F6E3E0A093C2F73746172743E0A093C7461736B206E616D653D226170706C792220646973706C61794E616D653D22E8AFB7E58187E794B3E8AFB722206C61796F75743D223532302C3136302C3132302C383022207461736B547970653D224D616A6F722220706572666F726D547970653D22414E5922206175746F457865637574653D224E222061737369676E65653D22617070726F76652E6F70657261746F72223E0A09093C7472616E736974696F6E206E616D653D2263373936343261652D396632382D343231332D386364662D3065306436343637623162392220746F3D22617070726F7665446570742220673D223538302C3136303B3638302C313630223E3C2F7472616E736974696F6E3E0A093C2F7461736B3E0A093C7461736B206E616D653D22617070726F7665446570742220646973706C61794E616D653D22E983A8E997A8E9A286E5AFBCE5AEA1E689B922206C61796F75743D223734302C3136302C3132302C383022207461736B547970653D224D616A6F722220706572666F726D547970653D22414E5922206175746F457865637574653D224E222061737369676E6D656E7448616E646C65723D22636F6D2E6D6C646F6E672E636F6E6669672E466C6F7741737369676E6D656E7448616E646C6572223E0A09093C7472616E736974696F6E206E616D653D2230396439623134332D393437332D346130662D383238372D3961626636663635626166352220746F3D2232633735656562662D356261662D346364302D613762332D3035343636626531333633342220673D223734302C3230303B3734302C333135223E3C2F7472616E736974696F6E3E0A093C2F7461736B3E0A093C6465636973696F6E206E616D653D2232633735656562662D356261662D346364302D613762332D30353436366265313336333422206C61796F75743D223734302C3334302C3132302C3830223E0A09093C7472616E736974696F6E206E616D653D2235313765663263372D333438362D343939322D623535342D3066353338616239313735312220646973706C61794E616D653D22E8AFB7E58187E5A4A9E695B0E5B08FE4BA8E332220746F3D22656E642220657870723D2223665F646179266C743B332220673D223736342C3333393B313038302C3333393B313038302C313738223E3C2F7472616E736974696F6E3E0A093C7472616E736974696F6E206E616D653D2264376563343136362D663366632D346664362D613261632D6136633464353039633464642220646973706C61794E616D653D22E8AFB7E58187E5A4A9E695B0E5A4A7E4BA8EE7AD89E4BA8E332220746F3D22617070726F7665426F73732220657870723D2223665F6461792667743B3D332220673D223734302C3336353B3734302C3438303B3834302C343830223E3C2F7472616E736974696F6E3E0A093C2F6465636973696F6E3E0A093C656E64206E616D653D22656E642220646973706C61794E616D653D22E7BB93E69D9F22206C61796F75743D22313038302C3136302C3132302C3830223E0A09093C2F656E643E0A093C7461736B206E616D653D22617070726F7665426F73732220646973706C61794E616D653D22E585ACE58FB8E9A286E5AFBCE5AEA1E689B922206C61796F75743D223930302C3438302C3132302C383022207461736B547970653D224D616A6F722220706572666F726D547970653D22414E5922206175746F457865637574653D224E222061737369676E6D656E7448616E646C65723D22636F6D2E6D6C646F6E672E636F6E6669672E466C6F7741737369676E6D656E7448616E646C6572223E0A09093C7472616E736974696F6E206E616D653D2261363433343865632D343136382D346633362D386136312D3135636631326337313062392220746F3D22656E642220673D223936302C3438303B313134302C3438303B313134302C3131323B313038302C3131323B313038302C313432223E3C2F7472616E736974696F6E3E0A093C2F7461736B3E0A3C2F70726F636573733E, 0, '2022-04-05 16:35:21', '1');

-- ----------------------------
-- Table structure for wf_surrogate
-- ----------------------------
DROP TABLE IF EXISTS `wf_surrogate`;
CREATE TABLE `wf_surrogate`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `process_Name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '流程名称',
  `operator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '授权人',
  `surrogate` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '代理人',
  `odate` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作时间',
  `sdate` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '开始时间',
  `edate` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '结束时间',
  `state` tinyint(1) NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `IDX_SURROGATE_OPERATOR`(`operator`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '委托代理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wf_surrogate
-- ----------------------------

-- ----------------------------
-- Table structure for wf_task
-- ----------------------------
DROP TABLE IF EXISTS `wf_task`;
CREATE TABLE `wf_task`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `order_Id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '流程实例ID',
  `task_Name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务名称',
  `display_Name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务显示名称',
  `task_Type` tinyint(1) NOT NULL COMMENT '任务类型',
  `perform_Type` tinyint(1) NULL DEFAULT NULL COMMENT '参与类型',
  `operator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务处理人',
  `create_Time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务创建时间',
  `finish_Time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务完成时间',
  `expire_Time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务期望完成时间',
  `action_Url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务处理的url',
  `parent_Task_Id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父任务ID',
  `variable` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附属变量json存储',
  `version` tinyint(1) NULL DEFAULT NULL COMMENT '版本',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `IDX_TASK_ORDER`(`order_Id`) USING BTREE,
  INDEX `IDX_TASK_TASKNAME`(`task_Name`) USING BTREE,
  INDEX `IDX_TASK_PARENTTASK`(`parent_Task_Id`) USING BTREE,
  CONSTRAINT `FK_TASK_ORDERID` FOREIGN KEY (`order_Id`) REFERENCES `wf_order` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '任务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wf_task
-- ----------------------------
INSERT INTO `wf_task` VALUES ('91f21e40ecc84833948a7ccd3a138b7c', 'f1bd25797aae4732838676f539c622ef', 'approveDept', '部门领导审批', 0, 0, NULL, '2022-04-19 21:35:58', NULL, NULL, '', 'b492070e6b0748aea23f9e15ef4755ca', '{\"f_day\":3,\"instanceUrl\":\"leaveForm\",\"snaker.orderNo\":\"295279926757418fa88ad9e8a368cab6\",\"operator.userName\":\"admin\",\"operator.realName\":\"mldong\",\"S-ACTOR\":\"1\"}', 0);
INSERT INTO `wf_task` VALUES ('e3402b1d2572407eaa2e1afa9ecc731e', '3a73e28221f04c56be3a15926409d3d2', 'audit', '审核', 0, 0, NULL, '2022-05-06 23:09:40', NULL, NULL, '', '09071361d62f4817aa13bf77b3bebaeb', '{\"audit\":12,\"hasNext\":1,\"approvalType\":1,\"operator.userName\":\"admin\",\"operator.realName\":\"mldong\",\"apply\":1,\"instanceUrl\":\"\",\"snaker.orderNo\":\"1b0221016ce44a6f9d2261a625ac8168\",\"S-ACTOR\":\"12\"}', 0);

-- ----------------------------
-- Table structure for wf_task_actor
-- ----------------------------
DROP TABLE IF EXISTS `wf_task_actor`;
CREATE TABLE `wf_task_actor`  (
  `task_Id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务ID',
  `actor_Id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '参与者ID',
  INDEX `IDX_TASKACTOR_TASK`(`task_Id`) USING BTREE,
  CONSTRAINT `FK_TASK_ACTOR_TASKID` FOREIGN KEY (`task_Id`) REFERENCES `wf_task` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '任务参与者表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wf_task_actor
-- ----------------------------
INSERT INTO `wf_task_actor` VALUES ('91f21e40ecc84833948a7ccd3a138b7c', '1');
INSERT INTO `wf_task_actor` VALUES ('e3402b1d2572407eaa2e1afa9ecc731e', '12');

SET FOREIGN_KEY_CHECKS = 1;
