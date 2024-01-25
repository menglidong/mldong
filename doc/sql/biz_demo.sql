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

 Date: 25/01/2024 12:37:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for biz_demo
-- ----------------------------
DROP TABLE IF EXISTS `biz_demo`;
CREATE TABLE `biz_demo`  (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `input_text_area` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '多行文本',
  `auto_complete` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '自动完成',
  `m_switch` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '开关',
  `divider` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分割线',
  `api_select` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '远程下拉',
  `dict` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典',
  `api_tree_select` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '远程树',
  `api_radio_group` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '远程单选组',
  `api_checkbox_group` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '远程多选组',
  `date_picker` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日期',
  `range_picker` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日期区间选择',
  `api_cascader` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '远程级联选择',
  `upload` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件上传',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  `is_deleted` tinyint(1) NULL DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '演示' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of biz_demo
-- ----------------------------
INSERT INTO `biz_demo` VALUES (1740545776803344386, '名称11', '多行文本11', NULL, NULL, NULL, '1686404946814533633', '2', NULL, '1711659551216500738', '789', '2023-12-29 09:30:22', NULL, NULL, NULL, '2023-12-29 09:30:26', 1567738052492341249, '2024-01-25 10:37:36', 1567738052492341249, 0);

SET FOREIGN_KEY_CHECKS = 1;
