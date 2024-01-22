/*
 Navicat Premium Data Transfer

 Source Server         : dev
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : 172.16.31.160:3306
 Source Schema         : mldong-plus

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 22/01/2024 16:35:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dev_schema
-- ----------------------------
DROP TABLE IF EXISTS `dev_schema`;
CREATE TABLE `dev_schema`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `schema_group_id` bigint(20) NULL DEFAULT NULL COMMENT '所属分组',
  `table_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '表名称',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '表注释',
  `other_remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '额外说明',
  `table_type` int(11) NULL DEFAULT NULL COMMENT '表类型',
  `form_type` int(11) NULL DEFAULT NULL COMMENT '表单类型',
  `is_tree` tinyint(1) NULL DEFAULT 0 COMMENT '是否树',
  `sort` bigint(20) NULL DEFAULT 99 COMMENT '排序',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `search_form_keys` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '搜索表单key，可用于排序',
  `list_keys` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '列表key，可用于排序',
  `variable` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '扩展属性JSON',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建用户',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新用户',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_schema_gid`(`schema_group_id`) USING BTREE,
  INDEX `idx_schema_table_name`(`table_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '数据模型' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for dev_schema_field
-- ----------------------------
DROP TABLE IF EXISTS `dev_schema_field`;
CREATE TABLE `dev_schema_field`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `schema_id` bigint(20) NOT NULL COMMENT '所属模型',
  `field_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段名称',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段注释',
  `field_size` int(11) NULL DEFAULT NULL COMMENT '字段长度',
  `data_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据类型',
  `is_primary` tinyint(1) NULL DEFAULT 0 COMMENT '是否主键',
  `nullable` tinyint(1) NULL DEFAULT 0 COMMENT '允许为空',
  `default_value` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '默认值',
  `component` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表单组件',
  `sort` bigint(20) NULL DEFAULT 99 COMMENT '排序',
  `variable` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '扩展属性JSON',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建用户',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新用户',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_schema_field_sid`(`schema_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '模型字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for dev_schema_group
-- ----------------------------
DROP TABLE IF EXISTS `dev_schema_group`;
CREATE TABLE `dev_schema_group`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分组名称',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '唯一编码',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `sort` bigint(20) NULL DEFAULT NULL COMMENT '排序',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '备注',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建用户',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新用户',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_schema_group_name`(`name`) USING BTREE,
  INDEX `idx_schema_group_code`(`code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '模型分组' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
