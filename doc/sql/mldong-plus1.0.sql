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

 Date: 28/09/2023 16:59:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `id` bigint(20) NOT NULL COMMENT '配置ID',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '配置名称',
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '唯一编码',
  `group_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分组编码',
  `content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '配置内容',
  `is_sys` tinyint(1) NULL DEFAULT 0 COMMENT '是否系统',
  `enabled` tinyint(1) NULL DEFAULT 1 COMMENT '是否启用',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建用户',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新用户',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_config_code`(`code`) USING BTREE,
  INDEX `idx_config_group_code`(`group_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_config
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` bigint(20) NOT NULL COMMENT '部门ID',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父ID',
  `pids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父ID集合',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门名称',
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '唯一编码',
  `sort` bigint(20) NULL DEFAULT 999 COMMENT '排序',
  `enabled` tinyint(1) NULL DEFAULT 1 COMMENT '是否启用',
  `leader_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门负责人ID集合',
  `main_leader_id` bigint(20) NULL DEFAULT NULL COMMENT '分管领导ID',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建用户',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新用户',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_sys_dept_code`(`code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (1704769507461627905, 0, NULL, '一级部门', 'dept_10', 10, 1, '1686404946814533633', 1686404946814533633, NULL, '2023-09-21 16:08:18.321', 1567738052492341249, '2023-09-21 16:08:18.321', 1567738052492341249, 0);
INSERT INTO `sys_dept` VALUES (1704792169135079426, 1704769507461627905, NULL, '一级子部门', 'dept_110', 10, 1, '1686404946814533633', 1686404946814533633, NULL, '2023-09-21 17:38:21.286', 1567738052492341249, '2023-09-28 15:06:32.374', 1567738052492341249, 0);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` bigint(20) NOT NULL COMMENT '字典ID',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典名称',
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '唯一编码',
  `data_type` int(11) NULL DEFAULT 1 COMMENT '数据类型(1:字符串；2：整型)',
  `group_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分组编码',
  `sort` bigint(20) NULL DEFAULT 999 COMMENT '排序',
  `enabled` tinyint(1) NULL DEFAULT 1 COMMENT '是否启用',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建用户',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新用户',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_dict_code`(`code`) USING BTREE,
  INDEX `idx_dict_group_code`(`group_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES (1707304964112236546, '内置工作流参与者处理类', 'wf_assignment_handler', 1, 'wf', 1, 1, NULL, '2023-09-28 16:03:18.293', 1567738052492341249, '2023-09-28 16:03:18.294', 1567738052492341249, 0);

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item`  (
  `id` bigint(20) NOT NULL COMMENT '字典项ID',
  `dict_id` bigint(20) NOT NULL COMMENT '字典ID',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典项名称',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '唯一编码',
  `sort` bigint(20) NULL DEFAULT 999 COMMENT '排序',
  `enabled` tinyint(1) NULL DEFAULT NULL COMMENT '是否启用',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建用户',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新用户',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_dict_item_dict_id`(`dict_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典项' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
INSERT INTO `sys_dict_item` VALUES (1707305170941755394, 1707304964112236546, '当前用户所属部门经理', 'com.mldong.modules.wf.flow.handlers.DeptLeaderAssignmentHandler', 1, 1, NULL, '2023-09-28 16:04:07.606', 1567738052492341249, '2023-09-28 16:04:07.606', 1567738052492341249, 0);
INSERT INTO `sys_dict_item` VALUES (1707305702993412097, 1707304964112236546, '当前用户所属部门分管领导', 'com.mldong.modules.wf.flow.handlers.DeptMainLeaderAssignmentHandler', 2, 1, NULL, '2023-09-28 16:06:14.457', 1567738052492341249, '2023-09-28 16:06:14.457', 1567738052492341249, 0);

-- ----------------------------
-- Table structure for sys_file_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_file_info`;
CREATE TABLE `sys_file_info`  (
  `id` bigint(20) NOT NULL COMMENT '文件信息ID',
  `url` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件访问地址',
  `size` bigint(20) NULL DEFAULT NULL COMMENT '文件大小，单位字节',
  `size_info` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件大小，有单位',
  `filename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件名称',
  `original_filename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '原始文件名',
  `base_path` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '基础存储路径',
  `path` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '存储路径',
  `ext` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件扩展名',
  `content_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'MIME类型',
  `platform` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '存储平台',
  `th_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '缩略图访问路径',
  `th_filename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '缩略图大小，单位字节',
  `th_size` bigint(20) NULL DEFAULT NULL COMMENT '缩略图大小，单位字节',
  `th_size_info` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '缩略图大小，有单位',
  `th_content_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '缩略图MIME类型',
  `object_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件所属对象id',
  `object_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件所属对象类型，例如用户头像，评价图片',
  `attr` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '附加属性',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建用户',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_file_info
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint(20) NOT NULL COMMENT '菜单ID',
  `app_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '应用编码',
  `parent_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '父ID',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '唯一编码',
  `pids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父ID集合',
  `type` int(11) NULL DEFAULT NULL COMMENT '菜单类型<sys_menu_type>',
  `sort` bigint(20) NULL DEFAULT 999 COMMENT '排序',
  `path` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由地址',
  `component` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件地址',
  `icon` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `is_show` tinyint(1) NULL DEFAULT 1 COMMENT '是否显示',
  `is_link` tinyint(1) NULL DEFAULT NULL COMMENT '是否链接',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '外部链接地址',
  `enabled` tinyint(1) NULL DEFAULT 1 COMMENT '是否启用',
  `open_type` int(11) NULL DEFAULT NULL COMMENT '打开方式<sys_menu_open_type>',
  `is_cache` tinyint(1) NULL DEFAULT NULL COMMENT '是否缓存',
  `is_sync` tinyint(1) NULL DEFAULT 1 COMMENT '是否同步',
  `variable` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '额外参数JSON',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建用户',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新用户',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_sys_menu_code`(`code`) USING BTREE,
  INDEX `idx_sys_menu_app_code`(`app_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1704792844212551681, 'platform', 0, '首页', '首页', '[0],', 2, 10, '/dashboard', NULL, 'icon-qietu-home', 0, 0, NULL, 1, 1, 0, 1, '{}', '2023-09-21 17:41:02.237', 1567738052492341249, '2023-09-22 16:37:05.325', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844254494721, 'platform', 1704792844212551681, '首页', 'Analysis', '[0],[1704792844212551681],', 2, 999, 'analysis', NULL, '', 0, 0, NULL, 1, 1, 0, 1, '{}', '2023-09-21 17:41:02.246', 1567738052492341249, '2023-09-22 16:37:05.334', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844271271937, 'platform', 0, '系统设置', 'sys:manager', '[0],', 1, 100, '/sys/manager', 'LAYOUT', 'icon-qietu-shezhi', 0, 0, NULL, 1, 1, 0, 1, '{}', '2023-09-21 17:41:02.250', 1567738052492341249, '2023-09-22 16:37:05.339', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844296437762, 'platform', 1704792844271271937, '用户管理', 'sys:user', '[0],[1704792844271271937],', 2, 210, '/sys/user/index', '/sys/user/index', 'ion:grid-outline', 0, 0, NULL, 1, 1, 0, 1, '{}', '2023-09-21 17:41:02.256', 1567738052492341249, '2023-09-22 16:37:05.345', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844317409281, 'platform', 1704792844296437762, '分页查询用户', 'sys:user:page', '[0],[1704792844271271937],[1704792844296437762],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.261', 1567738052492341249, '2023-09-22 16:37:05.351', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844346769410, 'platform', 1704792844296437762, '查看用户详情', 'sys:user:detail', '[0],[1704792844271271937],[1704792844296437762],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.269', 1567738052492341249, '2023-09-22 16:37:05.358', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844371935233, 'platform', 1704792844296437762, '添加用户', 'sys:user:save', '[0],[1704792844271271937],[1704792844296437762],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.275', 1567738052492341249, '2023-09-22 16:37:05.363', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844392906754, 'platform', 1704792844296437762, '修改用户', 'sys:user:update', '[0],[1704792844271271937],[1704792844296437762],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.279', 1567738052492341249, '2023-09-22 16:37:05.369', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844426461186, 'platform', 1704792844296437762, '删除用户', 'sys:user:remove', '[0],[1704792844271271937],[1704792844296437762],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.287', 1567738052492341249, '2023-09-22 16:37:05.376', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844447432706, 'platform', 1704792844271271937, '角色管理', 'sys:role', '[0],[1704792844271271937],', 2, 220, '/sys/role/index', '/sys/role/index', 'ion:grid-outline', 0, 0, NULL, 1, 1, 0, 1, '{}', '2023-09-21 17:41:02.292', 1567738052492341249, '2023-09-22 16:37:05.385', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844464209922, 'platform', 1704792844447432706, '分页查询角色', 'sys:role:page', '[0],[1704792844271271937],[1704792844447432706],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.296', 1567738052492341249, '2023-09-22 16:37:05.390', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844480987137, 'platform', 1704792844447432706, '查看角色详情', 'sys:role:detail', '[0],[1704792844271271937],[1704792844447432706],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.300', 1567738052492341249, '2023-09-22 16:37:05.394', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844497764353, 'platform', 1704792844447432706, '添加角色', 'sys:role:save', '[0],[1704792844271271937],[1704792844447432706],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.305', 1567738052492341249, '2023-09-22 16:37:05.398', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844518735873, 'platform', 1704792844447432706, '修改角色', 'sys:role:update', '[0],[1704792844271271937],[1704792844447432706],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.309', 1567738052492341249, '2023-09-22 16:37:05.404', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844535513089, 'platform', 1704792844447432706, '删除角色', 'sys:role:remove', '[0],[1704792844271271937],[1704792844447432706],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.313', 1567738052492341249, '2023-09-22 16:37:05.409', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844556484610, 'platform', 1704792844447432706, '设置权限（保存角色菜单关系）', 'sys:rbac:saveRoleMenu', '[0],[1704792844271271937],[1704792844447432706],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.318', 1567738052492341249, '2023-09-22 16:37:05.414', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844573261826, 'platform', 1704792844447432706, '成员管理（通过角色ID获取用户列表）', 'sys:rbac:userListByRoleId', '[0],[1704792844271271937],[1704792844447432706],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.322', 1567738052492341249, '2023-09-22 16:37:05.418', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844590039042, 'platform', 1704792844447432706, '成员管理（添加用户角色关系）', 'sys:rbac:saveUserRole', '[0],[1704792844271271937],[1704792844447432706],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.326', 1567738052492341249, '2023-09-22 16:37:05.422', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844602621953, 'platform', 1704792844447432706, '成员管理（获取用户列表-排除指定角色）', 'sys:rbac:userListExcludeRoleId', '[0],[1704792844271271937],[1704792844447432706],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.329', 1567738052492341249, '2023-09-22 16:37:05.425', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844611010562, 'platform', 1704792844447432706, '成员管理（删除用户角色关系）', 'sys:rbac:removeUserRole', '[0],[1704792844271271937],[1704792844447432706],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.333', 1567738052492341249, '2023-09-22 16:37:05.428', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844636176385, 'platform', 1704792844271271937, '菜单管理', 'sys:menu', '[0],[1704792844271271937],', 2, 222, '/sys/menu/index', '/sys/menu/index', 'ion:grid-outline', 0, 0, NULL, 1, 1, 0, 1, '{}', '2023-09-21 17:41:02.337', 1567738052492341249, '2023-09-22 16:37:05.432', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844648759298, 'platform', 1704792844636176385, '菜单列表', 'sys:menu:list', '[0],[1704792844271271937],[1704792844636176385],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.340', 1567738052492341249, '2023-09-22 16:37:05.436', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844665536514, 'platform', 1704792844636176385, '查看菜单详情', 'sys:menu:detail', '[0],[1704792844271271937],[1704792844636176385],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.344', 1567738052492341249, '2023-09-22 16:37:05.442', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844678119425, 'platform', 1704792844636176385, '添加菜单', 'sys:menu:save', '[0],[1704792844271271937],[1704792844636176385],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.347', 1567738052492341249, '2023-09-22 16:37:05.445', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844690702337, 'platform', 1704792844636176385, '修改菜单', 'sys:menu:update', '[0],[1704792844271271937],[1704792844636176385],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.350', 1567738052492341249, '2023-09-22 16:37:05.449', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844699090945, 'platform', 1704792844636176385, '删除菜单', 'sys:menu:remove', '[0],[1704792844271271937],[1704792844636176385],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.353', 1567738052492341249, '2023-09-22 16:37:05.452', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844715868161, 'platform', 1704792844271271937, '前端路由', 'sys:routelist', '[0],[1704792844271271937],', 2, 224, '/sys/menu/routelist', '/sys/menu/routelist', 'ion:grid-outline', 0, 0, NULL, 1, 1, 0, 1, '{}', '2023-09-21 17:41:02.356', 1567738052492341249, '2023-09-22 16:37:05.455', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844732645377, 'platform', 1704792844715868161, '同步前端路由', 'sys:menu:syncRoute', '[0],[1704792844271271937],[1704792844715868161],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.360', 1567738052492341249, '2023-09-22 16:37:05.458', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844741033985, 'platform', 1704792844715868161, '查看路由配置', 'btn:route:config', '[0],[1704792844271271937],[1704792844715868161],', 3, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.363', 1567738052492341249, '2023-09-22 16:37:05.461', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844757811202, 'platform', 1704792844715868161, '复制id/pid数据', 'btn:copy:idAndPidData', '[0],[1704792844271271937],[1704792844715868161],', 3, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.366', 1567738052492341249, '2023-09-22 16:37:05.464', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844774588417, 'platform', 1704792844715868161, '复制AntTreeData', 'btn:copy:antTreData', '[0],[1704792844271271937],[1704792844715868161],', 3, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.370', 1567738052492341249, '2023-09-22 16:37:05.468', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844787171329, 'platform', 1704792844271271937, '部门管理', 'sys:dept', '[0],[1704792844271271937],', 2, 230, '/sys/dept/index', '/sys/dept/index', 'ion:grid-outline', 0, 0, NULL, 1, 1, 0, 1, '{}', '2023-09-21 17:41:02.373', 1567738052492341249, '2023-09-22 16:37:05.471', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844795559938, 'platform', 1704792844787171329, '部门列表', 'sys:dept:list', '[0],[1704792844271271937],[1704792844787171329],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.376', 1567738052492341249, '2023-09-22 16:37:05.475', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844812337154, 'platform', 1704792844787171329, '查看部门详情', 'sys:dept:detail', '[0],[1704792844271271937],[1704792844787171329],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.379', 1567738052492341249, '2023-09-22 16:37:05.481', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844820725761, 'platform', 1704792844787171329, '添加部门', 'sys:dept:save', '[0],[1704792844271271937],[1704792844787171329],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.383', 1567738052492341249, '2023-09-22 16:37:05.484', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844837502977, 'platform', 1704792844787171329, '修改部门', 'sys:dept:update', '[0],[1704792844271271937],[1704792844787171329],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.386', 1567738052492341249, '2023-09-22 16:37:05.487', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844854280193, 'platform', 1704792844787171329, '删除部门', 'sys:dept:remove', '[0],[1704792844271271937],[1704792844787171329],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.390', 1567738052492341249, '2023-09-22 16:37:05.490', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844862668802, 'platform', 1704792844271271937, '岗位管理', 'sys:post', '[0],[1704792844271271937],', 2, 240, '/sys/post/index', '/sys/post/index', 'ion:grid-outline', 0, 0, NULL, 1, 1, 0, 1, '{}', '2023-09-21 17:41:02.392', 1567738052492341249, '2023-09-22 16:37:05.493', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844879446017, 'platform', 1704792844862668802, '分页查询岗位', 'sys:post:page', '[0],[1704792844271271937],[1704792844862668802],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.395', 1567738052492341249, '2023-09-22 16:37:05.496', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844887834626, 'platform', 1704792844862668802, '查看岗位详情', 'sys:post:detail', '[0],[1704792844271271937],[1704792844862668802],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.398', 1567738052492341249, '2023-09-22 16:37:05.500', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844904611842, 'platform', 1704792844862668802, '添加岗位', 'sys:post:save', '[0],[1704792844271271937],[1704792844862668802],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.401', 1567738052492341249, '2023-09-22 16:37:05.504', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844917194753, 'platform', 1704792844862668802, '修改岗位', 'sys:post:update', '[0],[1704792844271271937],[1704792844862668802],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.404', 1567738052492341249, '2023-09-22 16:37:05.507', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844929777665, 'platform', 1704792844862668802, '删除岗位', 'sys:post:remove', '[0],[1704792844271271937],[1704792844862668802],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.408', 1567738052492341249, '2023-09-22 16:37:05.509', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844946554882, 'platform', 1704792844271271937, '数据字典', 'sys:dict', '[0],[1704792844271271937],', 2, 250, '/sys/dict/index', '/sys/dict/index', 'ion:grid-outline', 0, 0, NULL, 1, 1, 0, 1, '{}', '2023-09-21 17:41:02.411', 1567738052492341249, '2023-09-22 16:37:05.512', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844954943489, 'platform', 1704792844946554882, '分页查询字典', 'sys:dict:page', '[0],[1704792844271271937],[1704792844946554882],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.414', 1567738052492341249, '2023-09-22 16:37:05.515', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844967526402, 'platform', 1704792844946554882, '查看参数字典', 'sys:dict:detail', '[0],[1704792844271271937],[1704792844946554882],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.417', 1567738052492341249, '2023-09-22 16:37:05.518', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844984303618, 'platform', 1704792844946554882, '添加字典', 'sys:dict:save', '[0],[1704792844271271937],[1704792844946554882],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.421', 1567738052492341249, '2023-09-22 16:37:05.521', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792844996886529, 'platform', 1704792844946554882, '修改字典', 'sys:dict:update', '[0],[1704792844271271937],[1704792844946554882],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.423', 1567738052492341249, '2023-09-22 16:37:05.524', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792845009469441, 'platform', 1704792844946554882, '删除字典', 'sys:dict:remove', '[0],[1704792844271271937],[1704792844946554882],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.426', 1567738052492341249, '2023-09-22 16:37:05.527', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792845022052354, 'platform', 1704792844946554882, '分页查询字典项', 'sys:dictItem:page', '[0],[1704792844271271937],[1704792844946554882],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.429', 1567738052492341249, '2023-09-22 16:37:05.530', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792845030440961, 'platform', 1704792844946554882, '查看参数字典项', 'sys:dictItem:detail', '[0],[1704792844271271937],[1704792844946554882],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.432', 1567738052492341249, '2023-09-22 16:37:05.534', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792845047218177, 'platform', 1704792844946554882, '添加字典项', 'sys:dictItem:save', '[0],[1704792844271271937],[1704792844946554882],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.435', 1567738052492341249, '2023-09-22 16:37:05.537', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792845055606785, 'platform', 1704792844946554882, '修改字典项', 'sys:dictItem:update', '[0],[1704792844271271937],[1704792844946554882],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.438', 1567738052492341249, '2023-09-22 16:37:05.540', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792845072384001, 'platform', 1704792844946554882, '删除字典项', 'sys:dictItem:remove', '[0],[1704792844271271937],[1704792844946554882],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.441', 1567738052492341249, '2023-09-22 16:37:05.542', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792845084966913, 'platform', 1704792844946554882, '枚举字典列表', 'sys:dict:enumDictList', '[0],[1704792844271271937],[1704792844946554882],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.444', 1567738052492341249, '2023-09-22 16:37:05.545', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792845097549826, 'platform', 1704792844271271937, '参数配置', 'sys:config', '[0],[1704792844271271937],', 2, 260, '/sys/config/index', '/sys/config/index', 'ion:grid-outline', 0, 0, NULL, 1, 1, 0, 1, '{}', '2023-09-21 17:41:02.447', 1567738052492341249, '2023-09-22 16:37:05.549', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792845105938433, 'platform', 1704792845097549826, '分页查询参数配置', 'sys:config:page', '[0],[1704792844271271937],[1704792845097549826],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.450', 1567738052492341249, '2023-09-22 16:37:05.552', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792845118521345, 'platform', 1704792845097549826, '查看参数配置详情', 'sys:config:detail', '[0],[1704792844271271937],[1704792845097549826],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.453', 1567738052492341249, '2023-09-22 16:37:05.555', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792845135298561, 'platform', 1704792845097549826, '添加参数配置', 'sys:config:save', '[0],[1704792844271271937],[1704792845097549826],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.456', 1567738052492341249, '2023-09-22 16:37:05.558', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792845143687169, 'platform', 1704792845097549826, '修改参数配置', 'sys:config:update', '[0],[1704792844271271937],[1704792845097549826],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.458', 1567738052492341249, '2023-09-22 16:37:05.561', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1704792845152075778, 'platform', 1704792845097549826, '删除参数配置', 'sys:config:remove', '[0],[1704792844271271937],[1704792845097549826],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-21 17:41:02.461', 1567738052492341249, '2023-09-22 16:37:05.566', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1705121465989025793, 'platform', 1704792844296437762, '扮演用户', 'sys:playUser', '[0],[1704792844271271937],[1704792844296437762],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-22 15:26:51.773', 1567738052492341249, '2023-09-22 16:37:05.381', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1705121466236489730, 'platform', 1704792844636176385, '菜单树', 'sys:menu:tree', '[0],[1704792844271271937],[1704792844636176385],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-22 15:26:51.837', 1567738052492341249, '2023-09-22 16:37:05.439', 1567738052492341249, 0);
INSERT INTO `sys_menu` VALUES (1705121466366513154, 'platform', 1704792844787171329, '部门树', 'sys:dept:tree', '[0],[1704792844271271937],[1704792844787171329],', 4, 999, NULL, NULL, NULL, 1, NULL, NULL, 1, 0, NULL, 1, '{}', '2023-09-22 15:26:51.873', 1567738052492341249, '2023-09-22 16:37:05.478', 1567738052492341249, 0);

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `id` bigint(20) NOT NULL COMMENT '通知公告ID',
  `title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '内容',
  `type` int(11) NOT NULL COMMENT '类型<sys_notice_type>',
  `publish_time` datetime(3) NULL DEFAULT NULL COMMENT '发布时间',
  `state` int(11) NULL DEFAULT NULL COMMENT '发布状态<sys_notice_state>',
  `variable` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展参数JSON',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建用户',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新用户',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '通知公告' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------

-- ----------------------------
-- Table structure for sys_notice_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice_user`;
CREATE TABLE `sys_notice_user`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `notice_id` bigint(20) NOT NULL COMMENT '通知公告ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `is_read` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否已读',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_notice_user_user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'r_通知公告用户关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_notice_user
-- ----------------------------

-- ----------------------------
-- Table structure for sys_op_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_op_log`;
CREATE TABLE `sys_op_log`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `op_type` tinyint(4) NULL DEFAULT NULL COMMENT '操作类型',
  `success` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否执行成功（Y-是，N-否）',
  `message` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '具体消息',
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'ip',
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `browser` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '浏览器',
  `os` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作系统',
  `url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求地址',
  `class_name` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类名称',
  `method_name` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '方法名称',
  `req_method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求方式（GET POST PUT DELETE)',
  `param` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '请求参数',
  `result` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '返回结果',
  `op_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  `account` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作账号',
  `play_user_account` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扮演者账号',
  `request_no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求编号',
  `sign_value` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '签名数据（除ID外）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_sys_op_log_request_no`(`request_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统操作日志表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_op_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`  (
  `id` bigint(20) NOT NULL COMMENT '岗位ID',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位名称',
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '唯一编码',
  `sort` bigint(20) NULL DEFAULT 999 COMMENT '排序',
  `enabled` tinyint(1) NULL DEFAULT 1 COMMENT '是否启用',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建用户',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新用户',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_sys_post_code`(`code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '岗位' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES (1705025204485042177, '架构师', 'architect', 1, 1, NULL, '2023-09-22 09:04:21.243', 1567738052492341249, '2023-09-22 09:04:21.243', 1567738052492341249, 0);
INSERT INTO `sys_post` VALUES (1705025270398529538, 'java开发工程师', 'java', 2, 1, NULL, '2023-09-22 09:04:36.957', 1567738052492341249, '2023-09-22 09:04:36.957', 1567738052492341249, 0);
INSERT INTO `sys_post` VALUES (1705025362337673217, '前端开发', 'front_dev', 3, 1, NULL, '2023-09-22 09:04:58.878', 1567738052492341249, '2023-09-22 15:30:32.663', 1686404946814533633, 0);
INSERT INTO `sys_post` VALUES (1705025419610894338, '业务员', 'biz', 4, 1, NULL, '2023-09-22 09:05:12.532', 1567738052492341249, '2023-09-22 09:05:12.532', 1567738052492341249, 0);

-- ----------------------------
-- Table structure for sys_rel_third_account
-- ----------------------------
DROP TABLE IF EXISTS `sys_rel_third_account`;
CREATE TABLE `sys_rel_third_account`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `third_account` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '第三方账号',
  `third_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '第三方账号类型',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建用户',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新用户',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_rel_third_account_uid`(`user_id`) USING BTREE,
  INDEX `idx_rel_third_account_aid`(`third_account`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '关联的第三方账号' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_rel_third_account
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(20) NOT NULL COMMENT '角色ID',
  `app_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '应用编码',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '唯一编码',
  `role_type` int(11) NOT NULL COMMENT '角色类型<sys_role_type>',
  `enabled` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否启用',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建用户',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新用户',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_sys_role_name`(`name`) USING BTREE,
  INDEX `idx_sys_role_code`(`code`) USING BTREE,
  INDEX `idx_sys_role_app_code`(`app_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1705019929250172930, 'platform', '管理员', 'manage', 1, 1, NULL, '2023-09-22 08:43:23.528', 1567738052492341249, '2023-09-22 08:43:23.528', 1567738052492341249, 0);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_role_menu_rid`(`role_id`) USING BTREE,
  INDEX `idx_role_menu_mid`(`menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'r_角色菜单关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1705121576425050114, 1705019929250172930, 1704792844732645377);
INSERT INTO `sys_role_menu` VALUES (1705121576425050115, 1705019929250172930, 1704792844715868161);
INSERT INTO `sys_role_menu` VALUES (1705121576433438722, 1705019929250172930, 1704792844741033985);
INSERT INTO `sys_role_menu` VALUES (1705121576437633026, 1705019929250172930, 1704792844757811202);
INSERT INTO `sys_role_menu` VALUES (1705121576437633027, 1705019929250172930, 1704792844774588417);
INSERT INTO `sys_role_menu` VALUES (1705121576446021633, 1705019929250172930, 1704792844879446017);
INSERT INTO `sys_role_menu` VALUES (1705121576446021634, 1705019929250172930, 1704792844887834626);
INSERT INTO `sys_role_menu` VALUES (1705121576446021635, 1705019929250172930, 1704792844904611842);
INSERT INTO `sys_role_menu` VALUES (1705121576454410242, 1705019929250172930, 1704792844917194753);
INSERT INTO `sys_role_menu` VALUES (1705121576454410243, 1705019929250172930, 1704792844795559938);
INSERT INTO `sys_role_menu` VALUES (1705121576454410244, 1705019929250172930, 1704792844812337154);
INSERT INTO `sys_role_menu` VALUES (1705121576454410245, 1705019929250172930, 1705121466366513154);
INSERT INTO `sys_role_menu` VALUES (1705121576462798850, 1705019929250172930, 1704792844271271937);
INSERT INTO `sys_role_menu` VALUES (1705121576462798851, 1705019929250172930, 1704792844862668802);
INSERT INTO `sys_role_menu` VALUES (1705121576466993154, 1705019929250172930, 1704792844787171329);

-- ----------------------------
-- Table structure for sys_task_run_history_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_task_run_history_data`;
CREATE TABLE `sys_task_run_history_data`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `task_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务名称',
  `spring_bean_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'springbean名称',
  `biz_id` bigint(20) NOT NULL COMMENT '业务ID',
  `task_state` int(11) NULL DEFAULT 1 COMMENT '任务状态(0：结束；1：活动)',
  `variable` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '扩展属性JSON',
  `result` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '执行结果',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建用户',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '任务运行的历史数据' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_task_run_history_data
-- ----------------------------

-- ----------------------------
-- Table structure for sys_task_running_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_task_running_data`;
CREATE TABLE `sys_task_running_data`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `task_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务名称',
  `spring_bean_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'springbean名称',
  `biz_id` bigint(20) NOT NULL COMMENT '业务ID',
  `variable` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '扩展属性JSON',
  `allow_max_error_count` int(11) NULL DEFAULT 1000 COMMENT '允许最大失败次数',
  `error_count` int(11) NULL DEFAULT 0 COMMENT '运行失败次数',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建用户',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '任务运行中的数据' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_task_running_data
-- ----------------------------

-- ----------------------------
-- Table structure for sys_third_party_callback
-- ----------------------------
DROP TABLE IF EXISTS `sys_third_party_callback`;
CREATE TABLE `sys_third_party_callback`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `channel_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '回调渠道(wx：微信；ali：支付宝；union银联)',
  `service_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '回调业务ID',
  `service_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '回调服务类型(wx_pay：微信支付回调，wx_refund微信退款回调)',
  `body` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '回调请求正文',
  `handle_status` int(11) NULL DEFAULT 0 COMMENT '处理状态（0：未处理；1：处理成功：2：处理失败）',
  `handle_result` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '处理结果数据',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新用户',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_third_party_callback_sid`(`service_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '第三方回调处理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_third_party_callback
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) NOT NULL COMMENT '用户ID',
  `user_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `real_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
  `nick_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `salt` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码加盐',
  `mobile_phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
  `tel` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `admin_type` int(11) NULL DEFAULT NULL COMMENT '管理员类型<sys_admin_type>',
  `sex` int(11) NULL DEFAULT 3 COMMENT '性别<sys_sex>',
  `is_locked` tinyint(1) NULL DEFAULT 0 COMMENT '是否锁定',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '所属部门',
  `post_id` bigint(20) NULL DEFAULT NULL COMMENT '所属岗位',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建用户',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新用户',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_sys_user_user_name`(`user_name`) USING BTREE,
  INDEX `idx_sys_user_real_name`(`real_name`) USING BTREE,
  INDEX `idx_sys_user_mobile_phone`(`mobile_phone`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1567738052492341249, 'superAdmin', '超级管理员', NULL, NULL, '211a86bdebe70443aa2e567bfc1e4d6e', 'tyszoxth', '18600000000', '18276163680', 't.bqoqwhoo@kcshc.nc', 1, 1, 0, 1704792169135079426, 1705025204485042177, NULL, '2022-09-08 12:54:13.535', 0, '2023-09-22 09:08:49.157', 1567738052492341249, 0);
INSERT INTO `sys_user` VALUES (1686404946814533633, 'admin', '蒙立东', NULL, NULL, '211a86bdebe70443aa2e567bfc1e4d6e', 'tyszoxth', '18276163688', NULL, NULL, 2, 1, 0, 1704792169135079426, 1705025419610894338, NULL, '2023-08-01 23:54:05.866', 1567738052492341249, '2023-09-25 17:01:02.723', 1567738052492341249, 0);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_sys_user_role_uid`(`user_id`) USING BTREE,
  INDEX `idx_sys_user_role_rid`(`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'r_用户角色关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1705027977171644418, 1686404946814533633, 1705019929250172930);

-- ----------------------------
-- Table structure for sys_vis_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_vis_log`;
CREATE TABLE `sys_vis_log`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `success` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否执行成功（Y-是，N-否）',
  `message` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '具体消息',
  `ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'ip',
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `browser` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '浏览器',
  `os` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作系统',
  `vis_type` tinyint(4) NOT NULL COMMENT '操作类型（字典 1登入 2登出）',
  `vis_time` datetime(0) NULL DEFAULT NULL COMMENT '访问时间',
  `account` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '访问账号',
  `sign_value` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '签名数据（除ID外）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统访问日志表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_vis_log
-- ----------------------------

-- ----------------------------
-- Table structure for wf_process_define
-- ----------------------------
DROP TABLE IF EXISTS `wf_process_define`;
CREATE TABLE `wf_process_define`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '唯一编码',
  `display_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '显示名称',
  `type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '流程类型',
  `state` int(11) NULL DEFAULT NULL COMMENT '流程是否可用(1可用；0不可用)',
  `content` blob NULL COMMENT '流程模型定义',
  `version` int(11) NULL DEFAULT NULL COMMENT '版本',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建用户',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新用户',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_process_define_name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '流程定义' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wf_process_define
-- ----------------------------
INSERT INTO `wf_process_define` VALUES (1706603590206496770, 'leave', '请假', NULL, 0, 0x7B22646973706C61794E616D65223A22E8AFB7E58187222C226E616D65223A226C65617665222C22696E7374616E636555726C223A226C65617665466F726D222C22707265496E746572636570746F7273223A22222C22706F7374496E746572636570746F7273223A22222C226E6F646573223A5B7B226964223A227374617274222C2274797065223A22736E616B65723A7374617274222C2278223A3334302C2279223A3136302C2270726F70657274696573223A7B227769647468223A3132302C22686569676874223A38302C22707265496E746572636570746F7273223A22222C22706F7374496E746572636570746F7273223A22227D2C2274657874223A7B2278223A3334302C2279223A3230302C2276616C7565223A22E5BC80E5A78B227D7D2C7B226964223A226170706C79222C2274797065223A22736E616B65723A7461736B222C2278223A3532302C2279223A3136302C2270726F70657274696573223A7B2261737369676E6565223A22617070726F76652E6F70657261746F72222C227461736B54797065223A224D616A6F72222C22706572666F726D54797065223A22414E59222C226175746F45786563757465223A224E222C227769647468223A3132302C22686569676874223A38302C226669656C64223A7B7D2C22707265496E746572636570746F7273223A22222C22706F7374496E746572636570746F7273223A22227D2C2274657874223A7B2278223A3532302C2279223A3136302C2276616C7565223A22E8AFB7E58187E794B3E8AFB7227D7D2C7B226964223A22617070726F766544657074222C2274797065223A22736E616B65723A7461736B222C2278223A3734302C2279223A3136302C2270726F70657274696573223A7B2261737369676E6D656E7448616E646C6572223A22222C227461736B54797065223A224D616A6F72222C22706572666F726D54797065223A22414C4C222C226175746F45786563757465223A224E222C227769647468223A3132302C22686569676874223A38302C226669656C64223A7B22636F756E7465727369676E54797065223A2253455155454E5449414C222C22636F756E7465727369676E436F6D706C6574696F6E436F6E646974696F6E223A22236E724F66436F6D706C65746564496E7374616E6365733D3D32227D2C22707265496E746572636570746F7273223A22222C22706F7374496E746572636570746F7273223A22222C2261737369676E6565223A22617070726F7665446570742E6F70657261746F72227D2C2274657874223A7B2278223A3734302C2279223A3136302C2276616C7565223A22E983A8E997A8E9A286E5AFBCE5AEA1E689B9227D7D2C7B226964223A22656E64222C2274797065223A22736E616B65723A656E64222C2278223A3938302C2279223A3136302C2270726F70657274696573223A7B227769647468223A3132302C22686569676874223A38302C22707265496E746572636570746F7273223A22222C22706F7374496E746572636570746F7273223A22227D2C2274657874223A7B2278223A3938302C2279223A3230302C2276616C7565223A22E7BB93E69D9F227D7D5D2C226564676573223A5B7B226964223A227431222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A227374617274222C227461726765744E6F64654964223A226170706C79222C227374617274506F696E74223A7B2278223A3335382C2279223A3136307D2C22656E64506F696E74223A7B2278223A3436302C2279223A3136307D2C2270726F70657274696573223A7B22686569676874223A38302C227769647468223A3132307D2C22706F696E74734C697374223A5B7B2278223A3335382C2279223A3136307D2C7B2278223A3436302C2279223A3136307D5D7D2C7B226964223A227432222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A226170706C79222C227461726765744E6F64654964223A22617070726F766544657074222C227374617274506F696E74223A7B2278223A3538302C2279223A3136307D2C22656E64506F696E74223A7B2278223A3638302C2279223A3136307D2C2270726F70657274696573223A7B22686569676874223A38302C227769647468223A3132307D2C22706F696E74734C697374223A5B7B2278223A3538302C2279223A3136307D2C7B2278223A3638302C2279223A3136307D5D7D2C7B226964223A227433222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A22617070726F766544657074222C227461726765744E6F64654964223A22656E64222C227374617274506F696E74223A7B2278223A3830302C2279223A3136307D2C22656E64506F696E74223A7B2278223A3936322C2279223A3136307D2C2270726F70657274696573223A7B22686569676874223A38302C227769647468223A3132307D2C22706F696E74734C697374223A5B7B2278223A3830302C2279223A3136307D2C7B2278223A3833302C2279223A3136307D2C7B2278223A3833302C2279223A3136307D2C7B2278223A3933322C2279223A3136307D2C7B2278223A3933322C2279223A3136307D2C7B2278223A3936322C2279223A3136307D5D7D5D7D, 0, '2023-09-26 17:36:17.706', NULL, NULL, NULL);
INSERT INTO `wf_process_define` VALUES (1706867096311025666, 'leave', '请假', NULL, 0, 0x7B22646973706C61794E616D65223A22E8AFB7E58187222C226E616D65223A226C65617665222C22696E7374616E636555726C223A226C65617665466F726D222C22707265496E746572636570746F7273223A22222C22706F7374496E746572636570746F7273223A22222C226E6F646573223A5B7B226964223A227374617274222C2274797065223A22736E616B65723A7374617274222C2278223A3334302C2279223A3136302C2270726F70657274696573223A7B227769647468223A3132302C22686569676874223A38302C22707265496E746572636570746F7273223A22222C22706F7374496E746572636570746F7273223A22227D2C2274657874223A7B2278223A3334302C2279223A3230302C2276616C7565223A22E5BC80E5A78B227D7D2C7B226964223A226170706C79222C2274797065223A22736E616B65723A7461736B222C2278223A3532302C2279223A3136302C2270726F70657274696573223A7B2261737369676E6565223A22617070726F76652E6F70657261746F72222C227461736B54797065223A224D616A6F72222C22706572666F726D54797065223A22414E59222C226175746F45786563757465223A224E222C227769647468223A3132302C22686569676874223A38302C226669656C64223A7B7D2C22707265496E746572636570746F7273223A22222C22706F7374496E746572636570746F7273223A22227D2C2274657874223A7B2278223A3532302C2279223A3136302C2276616C7565223A22E8AFB7E58187E794B3E8AFB7227D7D2C7B226964223A22617070726F766544657074222C2274797065223A22736E616B65723A7461736B222C2278223A3734302C2279223A3136302C2270726F70657274696573223A7B2261737369676E6D656E7448616E646C6572223A22222C227461736B54797065223A224D616A6F72222C22706572666F726D54797065223A22414C4C222C226175746F45786563757465223A224E222C227769647468223A3132302C22686569676874223A38302C226669656C64223A7B22636F756E7465727369676E54797065223A2253455155454E5449414C222C22636F756E7465727369676E436F6D706C6574696F6E436F6E646974696F6E223A22236E724F66436F6D706C65746564496E7374616E6365733D3D32227D2C22707265496E746572636570746F7273223A22222C22706F7374496E746572636570746F7273223A22222C2261737369676E6565223A22617070726F7665446570742E6F70657261746F72227D2C2274657874223A7B2278223A3734302C2279223A3136302C2276616C7565223A22E983A8E997A8E9A286E5AFBCE5AEA1E689B9227D7D2C7B226964223A22656E64222C2274797065223A22736E616B65723A656E64222C2278223A3938302C2279223A3136302C2270726F70657274696573223A7B227769647468223A3132302C22686569676874223A38302C22707265496E746572636570746F7273223A22222C22706F7374496E746572636570746F7273223A22227D2C2274657874223A7B2278223A3938302C2279223A3230302C2276616C7565223A22E7BB93E69D9F227D7D5D2C226564676573223A5B7B226964223A227431222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A227374617274222C227461726765744E6F64654964223A226170706C79222C227374617274506F696E74223A7B2278223A3335382C2279223A3136307D2C22656E64506F696E74223A7B2278223A3436302C2279223A3136307D2C2270726F70657274696573223A7B22686569676874223A38302C227769647468223A3132307D2C22706F696E74734C697374223A5B7B2278223A3335382C2279223A3136307D2C7B2278223A3436302C2279223A3136307D5D7D2C7B226964223A227432222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A226170706C79222C227461726765744E6F64654964223A22617070726F766544657074222C227374617274506F696E74223A7B2278223A3538302C2279223A3136307D2C22656E64506F696E74223A7B2278223A3638302C2279223A3136307D2C2270726F70657274696573223A7B22686569676874223A38302C227769647468223A3132307D2C22706F696E74734C697374223A5B7B2278223A3538302C2279223A3136307D2C7B2278223A3638302C2279223A3136307D5D7D2C7B226964223A227433222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A22617070726F766544657074222C227461726765744E6F64654964223A22656E64222C227374617274506F696E74223A7B2278223A3830302C2279223A3136307D2C22656E64506F696E74223A7B2278223A3936322C2279223A3136307D2C2270726F70657274696573223A7B22686569676874223A38302C227769647468223A3132307D2C22706F696E74734C697374223A5B7B2278223A3830302C2279223A3136307D2C7B2278223A3833302C2279223A3136307D2C7B2278223A3833302C2279223A3136307D2C7B2278223A3933322C2279223A3136307D2C7B2278223A3933322C2279223A3136307D2C7B2278223A3936322C2279223A3136307D5D7D5D7D, 1, '2023-09-27 11:03:22.460', NULL, NULL, NULL);
INSERT INTO `wf_process_define` VALUES (1706867563694895105, 'leave', '请假', NULL, 0, 0x7B22646973706C61794E616D65223A22E8AFB7E58187222C226E616D65223A226C65617665222C22696E7374616E636555726C223A226C65617665466F726D222C22707265496E746572636570746F7273223A22222C22706F7374496E746572636570746F7273223A22222C226E6F646573223A5B7B226964223A227374617274222C2274797065223A22736E616B65723A7374617274222C2278223A3334302C2279223A3136302C2270726F70657274696573223A7B227769647468223A3132302C22686569676874223A38302C22707265496E746572636570746F7273223A22222C22706F7374496E746572636570746F7273223A22227D2C2274657874223A7B2278223A3334302C2279223A3230302C2276616C7565223A22E5BC80E5A78B227D7D2C7B226964223A226170706C79222C2274797065223A22736E616B65723A7461736B222C2278223A3532302C2279223A3136302C2270726F70657274696573223A7B2261737369676E6565223A22617070726F76652E6F70657261746F72222C227461736B54797065223A224D616A6F72222C22706572666F726D54797065223A22414E59222C226175746F45786563757465223A224E222C227769647468223A3132302C22686569676874223A38302C226669656C64223A7B7D2C22707265496E746572636570746F7273223A22222C22706F7374496E746572636570746F7273223A22227D2C2274657874223A7B2278223A3532302C2279223A3136302C2276616C7565223A22E8AFB7E58187E794B3E8AFB7227D7D2C7B226964223A22617070726F766544657074222C2274797065223A22736E616B65723A7461736B222C2278223A3734302C2279223A3136302C2270726F70657274696573223A7B2261737369676E6D656E7448616E646C6572223A22222C227461736B54797065223A224D616A6F72222C22706572666F726D54797065223A22414E59222C226175746F45786563757465223A224E222C227769647468223A3132302C22686569676874223A38302C226669656C64223A7B7D2C22707265496E746572636570746F7273223A22222C22706F7374496E746572636570746F7273223A22222C2261737369676E6565223A22617070726F7665446570742E6F70657261746F72227D2C2274657874223A7B2278223A3734302C2279223A3136302C2276616C7565223A22E983A8E997A8E9A286E5AFBCE5AEA1E689B9227D7D2C7B226964223A22656E64222C2274797065223A22736E616B65723A656E64222C2278223A3938302C2279223A3136302C2270726F70657274696573223A7B227769647468223A3132302C22686569676874223A38302C22707265496E746572636570746F7273223A22222C22706F7374496E746572636570746F7273223A22227D2C2274657874223A7B2278223A3938302C2279223A3230302C2276616C7565223A22E7BB93E69D9F227D7D5D2C226564676573223A5B7B226964223A227431222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A227374617274222C227461726765744E6F64654964223A226170706C79222C227374617274506F696E74223A7B2278223A3335382C2279223A3136307D2C22656E64506F696E74223A7B2278223A3436302C2279223A3136307D2C2270726F70657274696573223A7B22686569676874223A38302C227769647468223A3132307D2C22706F696E74734C697374223A5B7B2278223A3335382C2279223A3136307D2C7B2278223A3436302C2279223A3136307D5D7D2C7B226964223A227432222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A226170706C79222C227461726765744E6F64654964223A22617070726F766544657074222C227374617274506F696E74223A7B2278223A3538302C2279223A3136307D2C22656E64506F696E74223A7B2278223A3638302C2279223A3136307D2C2270726F70657274696573223A7B22686569676874223A38302C227769647468223A3132307D2C22706F696E74734C697374223A5B7B2278223A3538302C2279223A3136307D2C7B2278223A3638302C2279223A3136307D5D7D2C7B226964223A227433222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A22617070726F766544657074222C227461726765744E6F64654964223A22656E64222C227374617274506F696E74223A7B2278223A3830302C2279223A3136307D2C22656E64506F696E74223A7B2278223A3936322C2279223A3136307D2C2270726F70657274696573223A7B22686569676874223A38302C227769647468223A3132307D2C22706F696E74734C697374223A5B7B2278223A3830302C2279223A3136307D2C7B2278223A3833302C2279223A3136307D2C7B2278223A3833302C2279223A3136307D2C7B2278223A3933322C2279223A3136307D2C7B2278223A3933322C2279223A3136307D2C7B2278223A3936322C2279223A3136307D5D7D5D7D, 2, '2023-09-27 11:05:13.906', NULL, NULL, NULL);
INSERT INTO `wf_process_define` VALUES (1707230160256475137, 'leave', '请假', NULL, 0, 0x7B22646973706C61794E616D65223A22E8AFB7E58187222C226E616D65223A226C65617665222C22696E7374616E636555726C223A226C65617665466F726D222C226E6F646573223A5B7B226964223A227374617274222C2274797065223A22736E616B65723A7374617274222C2278223A3334302C2279223A3136302C2270726F70657274696573223A7B227769647468223A22313230222C22686569676874223A223830227D2C2274657874223A7B2278223A3334302C2279223A3230302C2276616C7565223A22E5BC80E5A78B227D7D2C7B226964223A226170706C79222C2274797065223A22736E616B65723A7461736B222C2278223A3532302C2279223A3136302C2270726F70657274696573223A7B2261737369676E6565223A22617070726F76652E6F70657261746F72222C227461736B54797065223A224D616A6F72222C22706572666F726D54797065223A22414E59222C226175746F45786563757465223A224E222C227769647468223A3132302C22686569676874223A38302C226669656C64223A7B7D7D2C2274657874223A7B2278223A3532302C2279223A3136302C2276616C7565223A22E8AFB7E58187E794B3E8AFB7227D7D2C7B226964223A22617070726F766544657074222C2274797065223A22736E616B65723A7461736B222C2278223A3734302C2279223A3136302C2270726F70657274696573223A7B2261737369676E6D656E7448616E646C6572223A22222C227461736B54797065223A224D616A6F72222C22706572666F726D54797065223A22414E59222C226175746F45786563757465223A224E222C227769647468223A3132302C22686569676874223A38302C226669656C64223A7B7D2C2261737369676E6565223A22617070726F7665446570742E6F70657261746F72227D2C2274657874223A7B2278223A3734302C2279223A3136302C2276616C7565223A22E983A8E997A8E9A286E5AFBCE5AEA1E689B9227D7D2C7B226964223A22617070726F7665426F7373222C2274797065223A22736E616B65723A7461736B222C2278223A3930302C2279223A3438302C2270726F70657274696573223A7B2261737369676E6D656E7448616E646C6572223A22222C227461736B54797065223A224D616A6F72222C22706572666F726D54797065223A22414E59222C226175746F45786563757465223A224E222C227769647468223A3132302C22686569676874223A38302C226669656C64223A7B7D2C2261737369676E6565223A22617070726F7665426F73732E6F70657261746F72227D2C2274657874223A7B2278223A3930302C2279223A3438302C2276616C7565223A22E585ACE58FB8E9A286E5AFBCE5AEA1E689B9227D7D2C7B226964223A2232633735656562662D356261662D346364302D613762332D303534363662653133363334222C2274797065223A22736E616B65723A6465636973696F6E222C2278223A3734302C2279223A3334302C2270726F70657274696573223A7B227769647468223A22313230222C22686569676874223A223830227D7D2C7B226964223A22656E64222C2274797065223A22736E616B65723A656E64222C2278223A313038302C2279223A3136302C2270726F70657274696573223A7B227769647468223A22313230222C22686569676874223A223830227D2C2274657874223A7B2278223A313038302C2279223A3230302C2276616C7565223A22E7BB93E69D9F227D7D5D2C226564676573223A5B7B226964223A2233303337626534312D353638322D343334342D623934612D396661663563336536326261222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A227374617274222C227461726765744E6F64654964223A226170706C79222C227374617274506F696E74223A7B2278223A3335382C2279223A3136307D2C22656E64506F696E74223A7B2278223A3436302C2279223A3136307D2C2270726F70657274696573223A7B7D2C22706F696E74734C697374223A5B7B2278223A3335382C2279223A3136307D2C7B2278223A3436302C2279223A3136307D5D7D2C7B226964223A2263373936343261652D396632382D343231332D386364662D306530643634363762316239222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A226170706C79222C227461726765744E6F64654964223A22617070726F766544657074222C227374617274506F696E74223A7B2278223A3538302C2279223A3136307D2C22656E64506F696E74223A7B2278223A3638302C2279223A3136307D2C2270726F70657274696573223A7B7D2C22706F696E74734C697374223A5B7B2278223A3538302C2279223A3136307D2C7B2278223A3638302C2279223A3136307D5D7D2C7B226964223A2230396439623134332D393437332D346130662D383238372D396162663666363562616635222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A22617070726F766544657074222C227461726765744E6F64654964223A2232633735656562662D356261662D346364302D613762332D303534363662653133363334222C227374617274506F696E74223A7B2278223A3734302C2279223A3230307D2C22656E64506F696E74223A7B2278223A3734302C2279223A3331357D2C2270726F70657274696573223A7B7D2C22706F696E74734C697374223A5B7B2278223A3734302C2279223A3230307D2C7B2278223A3734302C2279223A3331357D5D7D2C7B226964223A2261363433343865632D343136382D346633362D386136312D313563663132633731306239222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A22617070726F7665426F7373222C227461726765744E6F64654964223A22656E64222C227374617274506F696E74223A7B2278223A3936302C2279223A3438307D2C22656E64506F696E74223A7B2278223A313038302C2279223A3134327D2C2270726F70657274696573223A7B7D2C22706F696E74734C697374223A5B7B2278223A3936302C2279223A3438307D2C7B2278223A313134302C2279223A3438307D2C7B2278223A313134302C2279223A3131327D2C7B2278223A313038302C2279223A3131327D2C7B2278223A313038302C2279223A3134327D5D7D2C7B226964223A2235313765663263372D333438362D343939322D623535342D306635333861623931373531222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A2232633735656562662D356261662D346364302D613762332D303534363662653133363334222C227461726765744E6F64654964223A22656E64222C227374617274506F696E74223A7B2278223A3736342C2279223A3333397D2C22656E64506F696E74223A7B2278223A313038302C2279223A3137387D2C2270726F70657274696573223A7B2265787072223A2223665F6461793C33222C227769647468223A3132302C22686569676874223A38307D2C2274657874223A7B2278223A3932322C2279223A3333392C2276616C7565223A22E8AFB7E58187E5A4A9E695B0E5B08FE4BA8E33227D2C22706F696E74734C697374223A5B7B2278223A3736342C2279223A3333397D2C7B2278223A313038302C2279223A3333397D2C7B2278223A313038302C2279223A3137387D5D7D2C7B226964223A2264376563343136362D663366632D346664362D613261632D613663346435303963346464222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A2232633735656562662D356261662D346364302D613762332D303534363662653133363334222C227461726765744E6F64654964223A22617070726F7665426F7373222C227374617274506F696E74223A7B2278223A3734302C2279223A3336357D2C22656E64506F696E74223A7B2278223A3834302C2279223A3438307D2C2270726F70657274696573223A7B2265787072223A2223665F6461793E3D33227D2C2274657874223A7B2278223A3734302C2279223A3432322E352C2276616C7565223A22E8AFB7E58187E5A4A9E695B0E5A4A7E4BA8EE7AD89E4BA8E33227D2C22706F696E74734C697374223A5B7B2278223A3734302C2279223A3336357D2C7B2278223A3734302C2279223A3438307D2C7B2278223A3834302C2279223A3438307D5D7D5D7D, 3, '2023-09-28 11:06:03.651', NULL, NULL, NULL);
INSERT INTO `wf_process_define` VALUES (1707234699034468354, 'leave', '请假', NULL, 0, 0x7B22646973706C61794E616D65223A22E8AFB7E58187222C226E616D65223A226C65617665222C22696E7374616E636555726C223A226C656176655766466F726D222C226E6F646573223A5B7B226964223A227374617274222C2274797065223A22736E616B65723A7374617274222C2278223A3334302C2279223A3136302C2270726F70657274696573223A7B227769647468223A22313230222C22686569676874223A223830227D2C2274657874223A7B2278223A3334302C2279223A3230302C2276616C7565223A22E5BC80E5A78B227D7D2C7B226964223A226170706C79222C2274797065223A22736E616B65723A7461736B222C2278223A3532302C2279223A3136302C2270726F70657274696573223A7B2261737369676E6565223A22617070726F76652E6F70657261746F72222C227461736B54797065223A224D616A6F72222C22706572666F726D54797065223A22414E59222C226175746F45786563757465223A224E222C227769647468223A3132302C22686569676874223A38302C226669656C64223A7B7D7D2C2274657874223A7B2278223A3532302C2279223A3136302C2276616C7565223A22E8AFB7E58187E794B3E8AFB7227D7D2C7B226964223A22617070726F766544657074222C2274797065223A22736E616B65723A7461736B222C2278223A3734302C2279223A3136302C2270726F70657274696573223A7B2261737369676E6D656E7448616E646C6572223A22222C227461736B54797065223A224D616A6F72222C22706572666F726D54797065223A22414E59222C226175746F45786563757465223A224E222C227769647468223A3132302C22686569676874223A38302C226669656C64223A7B7D2C2261737369676E6565223A22617070726F7665446570742E6F70657261746F72227D2C2274657874223A7B2278223A3734302C2279223A3136302C2276616C7565223A22E983A8E997A8E9A286E5AFBCE5AEA1E689B9227D7D2C7B226964223A22617070726F7665426F7373222C2274797065223A22736E616B65723A7461736B222C2278223A3930302C2279223A3438302C2270726F70657274696573223A7B2261737369676E6D656E7448616E646C6572223A22222C227461736B54797065223A224D616A6F72222C22706572666F726D54797065223A22414E59222C226175746F45786563757465223A224E222C227769647468223A3132302C22686569676874223A38302C226669656C64223A7B7D2C2261737369676E6565223A22617070726F7665426F73732E6F70657261746F72227D2C2274657874223A7B2278223A3930302C2279223A3438302C2276616C7565223A22E585ACE58FB8E9A286E5AFBCE5AEA1E689B9227D7D2C7B226964223A2232633735656562662D356261662D346364302D613762332D303534363662653133363334222C2274797065223A22736E616B65723A6465636973696F6E222C2278223A3734302C2279223A3334302C2270726F70657274696573223A7B227769647468223A22313230222C22686569676874223A223830227D7D2C7B226964223A22656E64222C2274797065223A22736E616B65723A656E64222C2278223A313038302C2279223A3136302C2270726F70657274696573223A7B227769647468223A22313230222C22686569676874223A223830227D2C2274657874223A7B2278223A313038302C2279223A3230302C2276616C7565223A22E7BB93E69D9F227D7D5D2C226564676573223A5B7B226964223A2233303337626534312D353638322D343334342D623934612D396661663563336536326261222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A227374617274222C227461726765744E6F64654964223A226170706C79222C227374617274506F696E74223A7B2278223A3335382C2279223A3136307D2C22656E64506F696E74223A7B2278223A3436302C2279223A3136307D2C2270726F70657274696573223A7B7D2C22706F696E74734C697374223A5B7B2278223A3335382C2279223A3136307D2C7B2278223A3436302C2279223A3136307D5D7D2C7B226964223A2263373936343261652D396632382D343231332D386364662D306530643634363762316239222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A226170706C79222C227461726765744E6F64654964223A22617070726F766544657074222C227374617274506F696E74223A7B2278223A3538302C2279223A3136307D2C22656E64506F696E74223A7B2278223A3638302C2279223A3136307D2C2270726F70657274696573223A7B7D2C22706F696E74734C697374223A5B7B2278223A3538302C2279223A3136307D2C7B2278223A3638302C2279223A3136307D5D7D2C7B226964223A2230396439623134332D393437332D346130662D383238372D396162663666363562616635222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A22617070726F766544657074222C227461726765744E6F64654964223A2232633735656562662D356261662D346364302D613762332D303534363662653133363334222C227374617274506F696E74223A7B2278223A3734302C2279223A3230307D2C22656E64506F696E74223A7B2278223A3734302C2279223A3331357D2C2270726F70657274696573223A7B7D2C22706F696E74734C697374223A5B7B2278223A3734302C2279223A3230307D2C7B2278223A3734302C2279223A3331357D5D7D2C7B226964223A2261363433343865632D343136382D346633362D386136312D313563663132633731306239222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A22617070726F7665426F7373222C227461726765744E6F64654964223A22656E64222C227374617274506F696E74223A7B2278223A3936302C2279223A3438307D2C22656E64506F696E74223A7B2278223A313038302C2279223A3134327D2C2270726F70657274696573223A7B7D2C22706F696E74734C697374223A5B7B2278223A3936302C2279223A3438307D2C7B2278223A313134302C2279223A3438307D2C7B2278223A313134302C2279223A3131327D2C7B2278223A313038302C2279223A3131327D2C7B2278223A313038302C2279223A3134327D5D7D2C7B226964223A2235313765663263372D333438362D343939322D623535342D306635333861623931373531222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A2232633735656562662D356261662D346364302D613762332D303534363662653133363334222C227461726765744E6F64654964223A22656E64222C227374617274506F696E74223A7B2278223A3736342C2279223A3333397D2C22656E64506F696E74223A7B2278223A313038302C2279223A3137387D2C2270726F70657274696573223A7B2265787072223A2223665F6461793C33222C227769647468223A3132302C22686569676874223A38307D2C2274657874223A7B2278223A3932322C2279223A3333392C2276616C7565223A22E8AFB7E58187E5A4A9E695B0E5B08FE4BA8E33227D2C22706F696E74734C697374223A5B7B2278223A3736342C2279223A3333397D2C7B2278223A313038302C2279223A3333397D2C7B2278223A313038302C2279223A3137387D5D7D2C7B226964223A2264376563343136362D663366632D346664362D613261632D613663346435303963346464222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A2232633735656562662D356261662D346364302D613762332D303534363662653133363334222C227461726765744E6F64654964223A22617070726F7665426F7373222C227374617274506F696E74223A7B2278223A3734302C2279223A3336357D2C22656E64506F696E74223A7B2278223A3834302C2279223A3438307D2C2270726F70657274696573223A7B2265787072223A2223665F6461793E3D33227D2C2274657874223A7B2278223A3734302C2279223A3432322E352C2276616C7565223A22E8AFB7E58187E5A4A9E695B0E5A4A7E4BA8EE7AD89E4BA8E33227D2C22706F696E74734C697374223A5B7B2278223A3734302C2279223A3336357D2C7B2278223A3734302C2279223A3438307D2C7B2278223A3834302C2279223A3438307D5D7D5D7D, 4, '2023-09-28 11:24:05.790', NULL, NULL, NULL);
INSERT INTO `wf_process_define` VALUES (1707288521396101122, 'leave', '请假', NULL, 1, 0x7B22646973706C61794E616D65223A22E8AFB7E58187222C226E616D65223A226C65617665222C22696E7374616E636555726C223A226C656176655766466F726D222C226E6F646573223A5B7B226964223A227374617274222C2274797065223A22736E616B65723A7374617274222C2278223A3334302C2279223A3136302C2270726F70657274696573223A7B227769647468223A22313230222C22686569676874223A223830227D2C2274657874223A7B2278223A3334302C2279223A3230302C2276616C7565223A22E5BC80E5A78B227D7D2C7B226964223A226170706C79222C2274797065223A22736E616B65723A7461736B222C2278223A3532302C2279223A3136302C2270726F70657274696573223A7B2261737369676E6565223A22617070726F76652E6F70657261746F72222C227461736B54797065223A224D616A6F72222C22706572666F726D54797065223A22414E59222C226175746F45786563757465223A224E222C227769647468223A3132302C22686569676874223A38302C226669656C64223A7B7D7D2C2274657874223A7B2278223A3532302C2279223A3136302C2276616C7565223A22E8AFB7E58187E794B3E8AFB7227D7D2C7B226964223A22617070726F766544657074222C2274797065223A22736E616B65723A7461736B222C2278223A3734302C2279223A3136302C2270726F70657274696573223A7B2261737369676E6D656E7448616E646C6572223A22636F6D2E6D6C646F6E672E6D6F64756C65732E77662E666C6F772E68616E646C6572732E446570744C656164657241737369676E6D656E7448616E646C6572222C227461736B54797065223A224D616A6F72222C22706572666F726D54797065223A22414E59222C226175746F45786563757465223A224E222C227769647468223A3132302C22686569676874223A38302C226669656C64223A7B7D2C2261737369676E6565223A22227D2C2274657874223A7B2278223A3734302C2279223A3136302C2276616C7565223A22E983A8E997A8E9A286E5AFBCE5AEA1E689B9227D7D2C7B226964223A22617070726F7665426F7373222C2274797065223A22736E616B65723A7461736B222C2278223A3930302C2279223A3438302C2270726F70657274696573223A7B2261737369676E6D656E7448616E646C6572223A22636F6D2E6D6C646F6E672E6D6F64756C65732E77662E666C6F772E68616E646C6572732E446570744D61696E4C656164657241737369676E6D656E7448616E646C6572222C227461736B54797065223A224D616A6F72222C22706572666F726D54797065223A22414E59222C226175746F45786563757465223A224E222C227769647468223A3132302C22686569676874223A38302C226669656C64223A7B7D2C2261737369676E6565223A22227D2C2274657874223A7B2278223A3930302C2279223A3438302C2276616C7565223A22E585ACE58FB8E9A286E5AFBCE5AEA1E689B9227D7D2C7B226964223A2232633735656562662D356261662D346364302D613762332D303534363662653133363334222C2274797065223A22736E616B65723A6465636973696F6E222C2278223A3734302C2279223A3334302C2270726F70657274696573223A7B227769647468223A22313230222C22686569676874223A223830227D7D2C7B226964223A22656E64222C2274797065223A22736E616B65723A656E64222C2278223A313038302C2279223A3136302C2270726F70657274696573223A7B227769647468223A22313230222C22686569676874223A223830227D2C2274657874223A7B2278223A313038302C2279223A3230302C2276616C7565223A22E7BB93E69D9F227D7D5D2C226564676573223A5B7B226964223A2233303337626534312D353638322D343334342D623934612D396661663563336536326261222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A227374617274222C227461726765744E6F64654964223A226170706C79222C227374617274506F696E74223A7B2278223A3335382C2279223A3136307D2C22656E64506F696E74223A7B2278223A3436302C2279223A3136307D2C2270726F70657274696573223A7B7D2C22706F696E74734C697374223A5B7B2278223A3335382C2279223A3136307D2C7B2278223A3436302C2279223A3136307D5D7D2C7B226964223A2263373936343261652D396632382D343231332D386364662D306530643634363762316239222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A226170706C79222C227461726765744E6F64654964223A22617070726F766544657074222C227374617274506F696E74223A7B2278223A3538302C2279223A3136307D2C22656E64506F696E74223A7B2278223A3638302C2279223A3136307D2C2270726F70657274696573223A7B7D2C22706F696E74734C697374223A5B7B2278223A3538302C2279223A3136307D2C7B2278223A3638302C2279223A3136307D5D7D2C7B226964223A2230396439623134332D393437332D346130662D383238372D396162663666363562616635222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A22617070726F766544657074222C227461726765744E6F64654964223A2232633735656562662D356261662D346364302D613762332D303534363662653133363334222C227374617274506F696E74223A7B2278223A3734302C2279223A3230307D2C22656E64506F696E74223A7B2278223A3734302C2279223A3331357D2C2270726F70657274696573223A7B7D2C22706F696E74734C697374223A5B7B2278223A3734302C2279223A3230307D2C7B2278223A3734302C2279223A3331357D5D7D2C7B226964223A2261363433343865632D343136382D346633362D386136312D313563663132633731306239222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A22617070726F7665426F7373222C227461726765744E6F64654964223A22656E64222C227374617274506F696E74223A7B2278223A3936302C2279223A3438307D2C22656E64506F696E74223A7B2278223A313038302C2279223A3134327D2C2270726F70657274696573223A7B7D2C22706F696E74734C697374223A5B7B2278223A3936302C2279223A3438307D2C7B2278223A313134302C2279223A3438307D2C7B2278223A313134302C2279223A3131327D2C7B2278223A313038302C2279223A3131327D2C7B2278223A313038302C2279223A3134327D5D7D2C7B226964223A2235313765663263372D333438362D343939322D623535342D306635333861623931373531222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A2232633735656562662D356261662D346364302D613762332D303534363662653133363334222C227461726765744E6F64654964223A22656E64222C227374617274506F696E74223A7B2278223A3736342C2279223A3333397D2C22656E64506F696E74223A7B2278223A313038302C2279223A3137387D2C2270726F70657274696573223A7B2265787072223A2223665F6461793C33222C227769647468223A3132302C22686569676874223A38307D2C2274657874223A7B2278223A3932322C2279223A3333392C2276616C7565223A22E8AFB7E58187E5A4A9E695B0E5B08FE4BA8E33227D2C22706F696E74734C697374223A5B7B2278223A3736342C2279223A3333397D2C7B2278223A313038302C2279223A3333397D2C7B2278223A313038302C2279223A3137387D5D7D2C7B226964223A2264376563343136362D663366632D346664362D613261632D613663346435303963346464222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A2232633735656562662D356261662D346364302D613762332D303534363662653133363334222C227461726765744E6F64654964223A22617070726F7665426F7373222C227374617274506F696E74223A7B2278223A3734302C2279223A3336357D2C22656E64506F696E74223A7B2278223A3834302C2279223A3438307D2C2270726F70657274696573223A7B2265787072223A2223665F6461793E3D33227D2C2274657874223A7B2278223A3734302C2279223A3432322E352C2276616C7565223A22E8AFB7E58187E5A4A9E695B0E5A4A7E4BA8EE7AD89E4BA8E33227D2C22706F696E74734C697374223A5B7B2278223A3734302C2279223A3336357D2C7B2278223A3734302C2279223A3438307D2C7B2278223A3834302C2279223A3438307D5D7D5D7D, 5, '2023-09-28 14:57:58.026', '1567738052492341249', '2023-09-28 14:57:58.026', '1567738052492341249');

-- ----------------------------
-- Table structure for wf_process_design
-- ----------------------------
DROP TABLE IF EXISTS `wf_process_design`;
CREATE TABLE `wf_process_design`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '唯一编码',
  `display_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '显示名称',
  `is_deployed` tinyint(1) NULL DEFAULT 0 COMMENT '是否已部署',
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '备注',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建用户',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新用户',
  `is_deleted` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_process_designer_name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '流程设计' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wf_process_design
-- ----------------------------
INSERT INTO `wf_process_design` VALUES (1706475684696399874, 'leave', '请假', 1, '请假啊啊啊', '2023-09-26 09:08:02.674', 1567738052492341249, '2023-09-28 14:57:58.021', 1567738052492341249, 0);

-- ----------------------------
-- Table structure for wf_process_design_his
-- ----------------------------
DROP TABLE IF EXISTS `wf_process_design_his`;
CREATE TABLE `wf_process_design_his`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `process_design_id` bigint(20) NOT NULL COMMENT '流程设计ID',
  `content` blob NULL COMMENT '流程模型定义',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建用户',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_process_design_his_pdid`(`process_design_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '流程设计历史' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wf_process_design_his
-- ----------------------------
INSERT INTO `wf_process_design_his` VALUES (1706554907133927426, 1706475684696399874, 0x7B22646973706C61794E616D65223A22E8AFB7E58187222C226E616D65223A226C65617665222C22696E7374616E636555726C223A226C65617665466F726D222C22707265496E746572636570746F7273223A22222C22706F7374496E746572636570746F7273223A22222C226E6F646573223A5B7B226964223A227374617274222C2274797065223A22736E616B65723A7374617274222C2278223A3334302C2279223A3136302C2270726F70657274696573223A7B227769647468223A3132302C22686569676874223A38302C22707265496E746572636570746F7273223A22222C22706F7374496E746572636570746F7273223A22227D2C2274657874223A7B2278223A3334302C2279223A3230302C2276616C7565223A22E5BC80E5A78B227D7D2C7B226964223A226170706C79222C2274797065223A22736E616B65723A7461736B222C2278223A3532302C2279223A3136302C2270726F70657274696573223A7B2261737369676E6565223A22617070726F76652E6F70657261746F72222C227461736B54797065223A224D616A6F72222C22706572666F726D54797065223A22414E59222C226175746F45786563757465223A224E222C227769647468223A3132302C22686569676874223A38302C226669656C64223A7B7D2C22707265496E746572636570746F7273223A22222C22706F7374496E746572636570746F7273223A22227D2C2274657874223A7B2278223A3532302C2279223A3136302C2276616C7565223A22E8AFB7E58187E794B3E8AFB7227D7D2C7B226964223A22617070726F766544657074222C2274797065223A22736E616B65723A7461736B222C2278223A3734302C2279223A3136302C2270726F70657274696573223A7B2261737369676E6D656E7448616E646C6572223A22222C227461736B54797065223A224D616A6F72222C22706572666F726D54797065223A22414C4C222C226175746F45786563757465223A224E222C227769647468223A3132302C22686569676874223A38302C226669656C64223A7B22636F756E7465727369676E54797065223A2253455155454E5449414C222C22636F756E7465727369676E436F6D706C6574696F6E436F6E646974696F6E223A22236E724F66436F6D706C65746564496E7374616E6365733D3D32227D2C22707265496E746572636570746F7273223A22222C22706F7374496E746572636570746F7273223A22222C2261737369676E6565223A22617070726F7665446570742E6F70657261746F72227D2C2274657874223A7B2278223A3734302C2279223A3136302C2276616C7565223A22E983A8E997A8E9A286E5AFBCE5AEA1E689B9227D7D2C7B226964223A22656E64222C2274797065223A22736E616B65723A656E64222C2278223A3938302C2279223A3136302C2270726F70657274696573223A7B227769647468223A3132302C22686569676874223A38302C22707265496E746572636570746F7273223A22222C22706F7374496E746572636570746F7273223A22227D2C2274657874223A7B2278223A3938302C2279223A3230302C2276616C7565223A22E7BB93E69D9F227D7D5D2C226564676573223A5B7B226964223A227431222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A227374617274222C227461726765744E6F64654964223A226170706C79222C227374617274506F696E74223A7B2278223A3335382C2279223A3136307D2C22656E64506F696E74223A7B2278223A3436302C2279223A3136307D2C2270726F70657274696573223A7B22686569676874223A38302C227769647468223A3132307D2C22706F696E74734C697374223A5B7B2278223A3335382C2279223A3136307D2C7B2278223A3436302C2279223A3136307D5D7D2C7B226964223A227432222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A226170706C79222C227461726765744E6F64654964223A22617070726F766544657074222C227374617274506F696E74223A7B2278223A3538302C2279223A3136307D2C22656E64506F696E74223A7B2278223A3638302C2279223A3136307D2C2270726F70657274696573223A7B22686569676874223A38302C227769647468223A3132307D2C22706F696E74734C697374223A5B7B2278223A3538302C2279223A3136307D2C7B2278223A3638302C2279223A3136307D5D7D2C7B226964223A227433222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A22617070726F766544657074222C227461726765744E6F64654964223A22656E64222C227374617274506F696E74223A7B2278223A3830302C2279223A3136307D2C22656E64506F696E74223A7B2278223A3936322C2279223A3136307D2C2270726F70657274696573223A7B22686569676874223A38302C227769647468223A3132307D2C22706F696E74734C697374223A5B7B2278223A3830302C2279223A3136307D2C7B2278223A3833302C2279223A3136307D2C7B2278223A3833302C2279223A3136307D2C7B2278223A3933322C2279223A3136307D2C7B2278223A3933322C2279223A3136307D2C7B2278223A3936322C2279223A3136307D5D7D5D7D, '2023-09-26 14:22:50.775', 1567738052492341249);
INSERT INTO `wf_process_design_his` VALUES (1706556852833853441, 1706475684696399874, 0x7B22646973706C61794E616D65223A22E8AFB7E5818731222C226E616D65223A226C65617665222C22696E7374616E636555726C223A226C65617665466F726D222C22707265496E746572636570746F7273223A22222C22706F7374496E746572636570746F7273223A22222C226E6F646573223A5B7B226964223A227374617274222C2274797065223A22736E616B65723A7374617274222C2278223A3334302C2279223A3136302C2270726F70657274696573223A7B227769647468223A3132302C22686569676874223A38302C22707265496E746572636570746F7273223A22222C22706F7374496E746572636570746F7273223A22227D2C2274657874223A7B2278223A3334302C2279223A3230302C2276616C7565223A22E5BC80E5A78B227D7D2C7B226964223A226170706C79222C2274797065223A22736E616B65723A7461736B222C2278223A3532302C2279223A3136302C2270726F70657274696573223A7B2261737369676E6565223A22617070726F76652E6F70657261746F72222C227461736B54797065223A224D616A6F72222C22706572666F726D54797065223A22414E59222C226175746F45786563757465223A224E222C227769647468223A3132302C22686569676874223A38302C226669656C64223A7B7D2C22707265496E746572636570746F7273223A22222C22706F7374496E746572636570746F7273223A22227D2C2274657874223A7B2278223A3532302C2279223A3136302C2276616C7565223A22E8AFB7E58187E794B3E8AFB7227D7D2C7B226964223A22617070726F766544657074222C2274797065223A22736E616B65723A7461736B222C2278223A3734302C2279223A3136302C2270726F70657274696573223A7B2261737369676E6D656E7448616E646C6572223A22222C227461736B54797065223A224D616A6F72222C22706572666F726D54797065223A22414C4C222C226175746F45786563757465223A224E222C227769647468223A3132302C22686569676874223A38302C226669656C64223A7B22636F756E7465727369676E54797065223A2253455155454E5449414C222C22636F756E7465727369676E436F6D706C6574696F6E436F6E646974696F6E223A22236E724F66436F6D706C65746564496E7374616E6365733D3D32227D2C22707265496E746572636570746F7273223A22222C22706F7374496E746572636570746F7273223A22222C2261737369676E6565223A22617070726F7665446570742E6F70657261746F72227D2C2274657874223A7B2278223A3734302C2279223A3136302C2276616C7565223A22E983A8E997A8E9A286E5AFBCE5AEA1E689B9227D7D2C7B226964223A22656E64222C2274797065223A22736E616B65723A656E64222C2278223A3938302C2279223A3136302C2270726F70657274696573223A7B227769647468223A3132302C22686569676874223A38302C22707265496E746572636570746F7273223A22222C22706F7374496E746572636570746F7273223A22227D2C2274657874223A7B2278223A3938302C2279223A3230302C2276616C7565223A22E7BB93E69D9F227D7D5D2C226564676573223A5B7B226964223A227431222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A227374617274222C227461726765744E6F64654964223A226170706C79222C227374617274506F696E74223A7B2278223A3335382C2279223A3136307D2C22656E64506F696E74223A7B2278223A3436302C2279223A3136307D2C2270726F70657274696573223A7B22686569676874223A38302C227769647468223A3132307D2C22706F696E74734C697374223A5B7B2278223A3335382C2279223A3136307D2C7B2278223A3436302C2279223A3136307D5D7D2C7B226964223A227432222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A226170706C79222C227461726765744E6F64654964223A22617070726F766544657074222C227374617274506F696E74223A7B2278223A3538302C2279223A3136307D2C22656E64506F696E74223A7B2278223A3638302C2279223A3136307D2C2270726F70657274696573223A7B22686569676874223A38302C227769647468223A3132307D2C22706F696E74734C697374223A5B7B2278223A3538302C2279223A3136307D2C7B2278223A3638302C2279223A3136307D5D7D2C7B226964223A227433222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A22617070726F766544657074222C227461726765744E6F64654964223A22656E64222C227374617274506F696E74223A7B2278223A3830302C2279223A3136307D2C22656E64506F696E74223A7B2278223A3936322C2279223A3136307D2C2270726F70657274696573223A7B22686569676874223A38302C227769647468223A3132307D2C22706F696E74734C697374223A5B7B2278223A3830302C2279223A3136307D2C7B2278223A3833302C2279223A3136307D2C7B2278223A3833302C2279223A3136307D2C7B2278223A3933322C2279223A3136307D2C7B2278223A3933322C2279223A3136307D2C7B2278223A3936322C2279223A3136307D5D7D5D7D, '2023-09-26 14:30:34.666', 1567738052492341249);
INSERT INTO `wf_process_design_his` VALUES (1706557624581595138, 1706475684696399874, 0x7B22646973706C61794E616D65223A22E8AFB7E58187222C226E616D65223A226C65617665222C22696E7374616E636555726C223A226C65617665466F726D222C22707265496E746572636570746F7273223A22222C22706F7374496E746572636570746F7273223A22222C226E6F646573223A5B7B226964223A227374617274222C2274797065223A22736E616B65723A7374617274222C2278223A3334302C2279223A3136302C2270726F70657274696573223A7B227769647468223A3132302C22686569676874223A38302C22707265496E746572636570746F7273223A22222C22706F7374496E746572636570746F7273223A22227D2C2274657874223A7B2278223A3334302C2279223A3230302C2276616C7565223A22E5BC80E5A78B227D7D2C7B226964223A226170706C79222C2274797065223A22736E616B65723A7461736B222C2278223A3532302C2279223A3136302C2270726F70657274696573223A7B2261737369676E6565223A22617070726F76652E6F70657261746F72222C227461736B54797065223A224D616A6F72222C22706572666F726D54797065223A22414E59222C226175746F45786563757465223A224E222C227769647468223A3132302C22686569676874223A38302C226669656C64223A7B7D2C22707265496E746572636570746F7273223A22222C22706F7374496E746572636570746F7273223A22227D2C2274657874223A7B2278223A3532302C2279223A3136302C2276616C7565223A22E8AFB7E58187E794B3E8AFB7227D7D2C7B226964223A22617070726F766544657074222C2274797065223A22736E616B65723A7461736B222C2278223A3734302C2279223A3136302C2270726F70657274696573223A7B2261737369676E6D656E7448616E646C6572223A22222C227461736B54797065223A224D616A6F72222C22706572666F726D54797065223A22414C4C222C226175746F45786563757465223A224E222C227769647468223A3132302C22686569676874223A38302C226669656C64223A7B22636F756E7465727369676E54797065223A2253455155454E5449414C222C22636F756E7465727369676E436F6D706C6574696F6E436F6E646974696F6E223A22236E724F66436F6D706C65746564496E7374616E6365733D3D32227D2C22707265496E746572636570746F7273223A22222C22706F7374496E746572636570746F7273223A22222C2261737369676E6565223A22617070726F7665446570742E6F70657261746F72227D2C2274657874223A7B2278223A3734302C2279223A3136302C2276616C7565223A22E983A8E997A8E9A286E5AFBCE5AEA1E689B9227D7D2C7B226964223A22656E64222C2274797065223A22736E616B65723A656E64222C2278223A3938302C2279223A3136302C2270726F70657274696573223A7B227769647468223A3132302C22686569676874223A38302C22707265496E746572636570746F7273223A22222C22706F7374496E746572636570746F7273223A22227D2C2274657874223A7B2278223A3938302C2279223A3230302C2276616C7565223A22E7BB93E69D9F227D7D5D2C226564676573223A5B7B226964223A227431222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A227374617274222C227461726765744E6F64654964223A226170706C79222C227374617274506F696E74223A7B2278223A3335382C2279223A3136307D2C22656E64506F696E74223A7B2278223A3436302C2279223A3136307D2C2270726F70657274696573223A7B22686569676874223A38302C227769647468223A3132307D2C22706F696E74734C697374223A5B7B2278223A3335382C2279223A3136307D2C7B2278223A3436302C2279223A3136307D5D7D2C7B226964223A227432222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A226170706C79222C227461726765744E6F64654964223A22617070726F766544657074222C227374617274506F696E74223A7B2278223A3538302C2279223A3136307D2C22656E64506F696E74223A7B2278223A3638302C2279223A3136307D2C2270726F70657274696573223A7B22686569676874223A38302C227769647468223A3132307D2C22706F696E74734C697374223A5B7B2278223A3538302C2279223A3136307D2C7B2278223A3638302C2279223A3136307D5D7D2C7B226964223A227433222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A22617070726F766544657074222C227461726765744E6F64654964223A22656E64222C227374617274506F696E74223A7B2278223A3830302C2279223A3136307D2C22656E64506F696E74223A7B2278223A3936322C2279223A3136307D2C2270726F70657274696573223A7B22686569676874223A38302C227769647468223A3132307D2C22706F696E74734C697374223A5B7B2278223A3830302C2279223A3136307D2C7B2278223A3833302C2279223A3136307D2C7B2278223A3833302C2279223A3136307D2C7B2278223A3933322C2279223A3136307D2C7B2278223A3933322C2279223A3136307D2C7B2278223A3936322C2279223A3136307D5D7D5D7D, '2023-09-26 14:33:38.675', 1567738052492341249);
INSERT INTO `wf_process_design_his` VALUES (1706867534229909505, 1706475684696399874, 0x7B22646973706C61794E616D65223A22E8AFB7E58187222C226E616D65223A226C65617665222C22696E7374616E636555726C223A226C65617665466F726D222C22707265496E746572636570746F7273223A22222C22706F7374496E746572636570746F7273223A22222C226E6F646573223A5B7B226964223A227374617274222C2274797065223A22736E616B65723A7374617274222C2278223A3334302C2279223A3136302C2270726F70657274696573223A7B227769647468223A3132302C22686569676874223A38302C22707265496E746572636570746F7273223A22222C22706F7374496E746572636570746F7273223A22227D2C2274657874223A7B2278223A3334302C2279223A3230302C2276616C7565223A22E5BC80E5A78B227D7D2C7B226964223A226170706C79222C2274797065223A22736E616B65723A7461736B222C2278223A3532302C2279223A3136302C2270726F70657274696573223A7B2261737369676E6565223A22617070726F76652E6F70657261746F72222C227461736B54797065223A224D616A6F72222C22706572666F726D54797065223A22414E59222C226175746F45786563757465223A224E222C227769647468223A3132302C22686569676874223A38302C226669656C64223A7B7D2C22707265496E746572636570746F7273223A22222C22706F7374496E746572636570746F7273223A22227D2C2274657874223A7B2278223A3532302C2279223A3136302C2276616C7565223A22E8AFB7E58187E794B3E8AFB7227D7D2C7B226964223A22617070726F766544657074222C2274797065223A22736E616B65723A7461736B222C2278223A3734302C2279223A3136302C2270726F70657274696573223A7B2261737369676E6D656E7448616E646C6572223A22222C227461736B54797065223A224D616A6F72222C22706572666F726D54797065223A22414E59222C226175746F45786563757465223A224E222C227769647468223A3132302C22686569676874223A38302C226669656C64223A7B7D2C22707265496E746572636570746F7273223A22222C22706F7374496E746572636570746F7273223A22222C2261737369676E6565223A22617070726F7665446570742E6F70657261746F72227D2C2274657874223A7B2278223A3734302C2279223A3136302C2276616C7565223A22E983A8E997A8E9A286E5AFBCE5AEA1E689B9227D7D2C7B226964223A22656E64222C2274797065223A22736E616B65723A656E64222C2278223A3938302C2279223A3136302C2270726F70657274696573223A7B227769647468223A3132302C22686569676874223A38302C22707265496E746572636570746F7273223A22222C22706F7374496E746572636570746F7273223A22227D2C2274657874223A7B2278223A3938302C2279223A3230302C2276616C7565223A22E7BB93E69D9F227D7D5D2C226564676573223A5B7B226964223A227431222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A227374617274222C227461726765744E6F64654964223A226170706C79222C227374617274506F696E74223A7B2278223A3335382C2279223A3136307D2C22656E64506F696E74223A7B2278223A3436302C2279223A3136307D2C2270726F70657274696573223A7B22686569676874223A38302C227769647468223A3132307D2C22706F696E74734C697374223A5B7B2278223A3335382C2279223A3136307D2C7B2278223A3436302C2279223A3136307D5D7D2C7B226964223A227432222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A226170706C79222C227461726765744E6F64654964223A22617070726F766544657074222C227374617274506F696E74223A7B2278223A3538302C2279223A3136307D2C22656E64506F696E74223A7B2278223A3638302C2279223A3136307D2C2270726F70657274696573223A7B22686569676874223A38302C227769647468223A3132307D2C22706F696E74734C697374223A5B7B2278223A3538302C2279223A3136307D2C7B2278223A3638302C2279223A3136307D5D7D2C7B226964223A227433222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A22617070726F766544657074222C227461726765744E6F64654964223A22656E64222C227374617274506F696E74223A7B2278223A3830302C2279223A3136307D2C22656E64506F696E74223A7B2278223A3936322C2279223A3136307D2C2270726F70657274696573223A7B22686569676874223A38302C227769647468223A3132307D2C22706F696E74734C697374223A5B7B2278223A3830302C2279223A3136307D2C7B2278223A3833302C2279223A3136307D2C7B2278223A3833302C2279223A3136307D2C7B2278223A3933322C2279223A3136307D2C7B2278223A3933322C2279223A3136307D2C7B2278223A3936322C2279223A3136307D5D7D5D7D, '2023-09-27 11:05:06.883', 1567738052492341249);
INSERT INTO `wf_process_design_his` VALUES (1707230139599527937, 1706475684696399874, 0x7B22646973706C61794E616D65223A22E8AFB7E58187222C226E616D65223A226C65617665222C22696E7374616E636555726C223A226C65617665466F726D222C226E6F646573223A5B7B226964223A227374617274222C2274797065223A22736E616B65723A7374617274222C2278223A3334302C2279223A3136302C2270726F70657274696573223A7B227769647468223A22313230222C22686569676874223A223830227D2C2274657874223A7B2278223A3334302C2279223A3230302C2276616C7565223A22E5BC80E5A78B227D7D2C7B226964223A226170706C79222C2274797065223A22736E616B65723A7461736B222C2278223A3532302C2279223A3136302C2270726F70657274696573223A7B2261737369676E6565223A22617070726F76652E6F70657261746F72222C227461736B54797065223A224D616A6F72222C22706572666F726D54797065223A22414E59222C226175746F45786563757465223A224E222C227769647468223A3132302C22686569676874223A38302C226669656C64223A7B7D7D2C2274657874223A7B2278223A3532302C2279223A3136302C2276616C7565223A22E8AFB7E58187E794B3E8AFB7227D7D2C7B226964223A22617070726F766544657074222C2274797065223A22736E616B65723A7461736B222C2278223A3734302C2279223A3136302C2270726F70657274696573223A7B2261737369676E6D656E7448616E646C6572223A22222C227461736B54797065223A224D616A6F72222C22706572666F726D54797065223A22414E59222C226175746F45786563757465223A224E222C227769647468223A3132302C22686569676874223A38302C226669656C64223A7B7D2C2261737369676E6565223A22617070726F7665446570742E6F70657261746F72227D2C2274657874223A7B2278223A3734302C2279223A3136302C2276616C7565223A22E983A8E997A8E9A286E5AFBCE5AEA1E689B9227D7D2C7B226964223A22617070726F7665426F7373222C2274797065223A22736E616B65723A7461736B222C2278223A3930302C2279223A3438302C2270726F70657274696573223A7B2261737369676E6D656E7448616E646C6572223A22222C227461736B54797065223A224D616A6F72222C22706572666F726D54797065223A22414E59222C226175746F45786563757465223A224E222C227769647468223A3132302C22686569676874223A38302C226669656C64223A7B7D2C2261737369676E6565223A22617070726F7665426F73732E6F70657261746F72227D2C2274657874223A7B2278223A3930302C2279223A3438302C2276616C7565223A22E585ACE58FB8E9A286E5AFBCE5AEA1E689B9227D7D2C7B226964223A2232633735656562662D356261662D346364302D613762332D303534363662653133363334222C2274797065223A22736E616B65723A6465636973696F6E222C2278223A3734302C2279223A3334302C2270726F70657274696573223A7B227769647468223A22313230222C22686569676874223A223830227D7D2C7B226964223A22656E64222C2274797065223A22736E616B65723A656E64222C2278223A313038302C2279223A3136302C2270726F70657274696573223A7B227769647468223A22313230222C22686569676874223A223830227D2C2274657874223A7B2278223A313038302C2279223A3230302C2276616C7565223A22E7BB93E69D9F227D7D5D2C226564676573223A5B7B226964223A2233303337626534312D353638322D343334342D623934612D396661663563336536326261222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A227374617274222C227461726765744E6F64654964223A226170706C79222C227374617274506F696E74223A7B2278223A3335382C2279223A3136307D2C22656E64506F696E74223A7B2278223A3436302C2279223A3136307D2C2270726F70657274696573223A7B7D2C22706F696E74734C697374223A5B7B2278223A3335382C2279223A3136307D2C7B2278223A3436302C2279223A3136307D5D7D2C7B226964223A2263373936343261652D396632382D343231332D386364662D306530643634363762316239222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A226170706C79222C227461726765744E6F64654964223A22617070726F766544657074222C227374617274506F696E74223A7B2278223A3538302C2279223A3136307D2C22656E64506F696E74223A7B2278223A3638302C2279223A3136307D2C2270726F70657274696573223A7B7D2C22706F696E74734C697374223A5B7B2278223A3538302C2279223A3136307D2C7B2278223A3638302C2279223A3136307D5D7D2C7B226964223A2230396439623134332D393437332D346130662D383238372D396162663666363562616635222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A22617070726F766544657074222C227461726765744E6F64654964223A2232633735656562662D356261662D346364302D613762332D303534363662653133363334222C227374617274506F696E74223A7B2278223A3734302C2279223A3230307D2C22656E64506F696E74223A7B2278223A3734302C2279223A3331357D2C2270726F70657274696573223A7B7D2C22706F696E74734C697374223A5B7B2278223A3734302C2279223A3230307D2C7B2278223A3734302C2279223A3331357D5D7D2C7B226964223A2261363433343865632D343136382D346633362D386136312D313563663132633731306239222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A22617070726F7665426F7373222C227461726765744E6F64654964223A22656E64222C227374617274506F696E74223A7B2278223A3936302C2279223A3438307D2C22656E64506F696E74223A7B2278223A313038302C2279223A3134327D2C2270726F70657274696573223A7B7D2C22706F696E74734C697374223A5B7B2278223A3936302C2279223A3438307D2C7B2278223A313134302C2279223A3438307D2C7B2278223A313134302C2279223A3131327D2C7B2278223A313038302C2279223A3131327D2C7B2278223A313038302C2279223A3134327D5D7D2C7B226964223A2235313765663263372D333438362D343939322D623535342D306635333861623931373531222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A2232633735656562662D356261662D346364302D613762332D303534363662653133363334222C227461726765744E6F64654964223A22656E64222C227374617274506F696E74223A7B2278223A3736342C2279223A3333397D2C22656E64506F696E74223A7B2278223A313038302C2279223A3137387D2C2270726F70657274696573223A7B2265787072223A2223665F6461793C33222C227769647468223A3132302C22686569676874223A38307D2C2274657874223A7B2278223A3932322C2279223A3333392C2276616C7565223A22E8AFB7E58187E5A4A9E695B0E5B08FE4BA8E33227D2C22706F696E74734C697374223A5B7B2278223A3736342C2279223A3333397D2C7B2278223A313038302C2279223A3333397D2C7B2278223A313038302C2279223A3137387D5D7D2C7B226964223A2264376563343136362D663366632D346664362D613261632D613663346435303963346464222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A2232633735656562662D356261662D346364302D613762332D303534363662653133363334222C227461726765744E6F64654964223A22617070726F7665426F7373222C227374617274506F696E74223A7B2278223A3734302C2279223A3336357D2C22656E64506F696E74223A7B2278223A3834302C2279223A3438307D2C2270726F70657274696573223A7B2265787072223A2223665F6461793E3D33227D2C2274657874223A7B2278223A3734302C2279223A3432322E352C2276616C7565223A22E8AFB7E58187E5A4A9E695B0E5A4A7E4BA8EE7AD89E4BA8E33227D2C22706F696E74734C697374223A5B7B2278223A3734302C2279223A3336357D2C7B2278223A3734302C2279223A3438307D2C7B2278223A3834302C2279223A3438307D5D7D5D7D, '2023-09-28 11:05:58.740', 1567738052492341249);
INSERT INTO `wf_process_design_his` VALUES (1707234685671415809, 1706475684696399874, 0x7B22646973706C61794E616D65223A22E8AFB7E58187222C226E616D65223A226C65617665222C22696E7374616E636555726C223A226C656176655766466F726D222C226E6F646573223A5B7B226964223A227374617274222C2274797065223A22736E616B65723A7374617274222C2278223A3334302C2279223A3136302C2270726F70657274696573223A7B227769647468223A22313230222C22686569676874223A223830227D2C2274657874223A7B2278223A3334302C2279223A3230302C2276616C7565223A22E5BC80E5A78B227D7D2C7B226964223A226170706C79222C2274797065223A22736E616B65723A7461736B222C2278223A3532302C2279223A3136302C2270726F70657274696573223A7B2261737369676E6565223A22617070726F76652E6F70657261746F72222C227461736B54797065223A224D616A6F72222C22706572666F726D54797065223A22414E59222C226175746F45786563757465223A224E222C227769647468223A3132302C22686569676874223A38302C226669656C64223A7B7D7D2C2274657874223A7B2278223A3532302C2279223A3136302C2276616C7565223A22E8AFB7E58187E794B3E8AFB7227D7D2C7B226964223A22617070726F766544657074222C2274797065223A22736E616B65723A7461736B222C2278223A3734302C2279223A3136302C2270726F70657274696573223A7B2261737369676E6D656E7448616E646C6572223A22222C227461736B54797065223A224D616A6F72222C22706572666F726D54797065223A22414E59222C226175746F45786563757465223A224E222C227769647468223A3132302C22686569676874223A38302C226669656C64223A7B7D2C2261737369676E6565223A22617070726F7665446570742E6F70657261746F72227D2C2274657874223A7B2278223A3734302C2279223A3136302C2276616C7565223A22E983A8E997A8E9A286E5AFBCE5AEA1E689B9227D7D2C7B226964223A22617070726F7665426F7373222C2274797065223A22736E616B65723A7461736B222C2278223A3930302C2279223A3438302C2270726F70657274696573223A7B2261737369676E6D656E7448616E646C6572223A22222C227461736B54797065223A224D616A6F72222C22706572666F726D54797065223A22414E59222C226175746F45786563757465223A224E222C227769647468223A3132302C22686569676874223A38302C226669656C64223A7B7D2C2261737369676E6565223A22617070726F7665426F73732E6F70657261746F72227D2C2274657874223A7B2278223A3930302C2279223A3438302C2276616C7565223A22E585ACE58FB8E9A286E5AFBCE5AEA1E689B9227D7D2C7B226964223A2232633735656562662D356261662D346364302D613762332D303534363662653133363334222C2274797065223A22736E616B65723A6465636973696F6E222C2278223A3734302C2279223A3334302C2270726F70657274696573223A7B227769647468223A22313230222C22686569676874223A223830227D7D2C7B226964223A22656E64222C2274797065223A22736E616B65723A656E64222C2278223A313038302C2279223A3136302C2270726F70657274696573223A7B227769647468223A22313230222C22686569676874223A223830227D2C2274657874223A7B2278223A313038302C2279223A3230302C2276616C7565223A22E7BB93E69D9F227D7D5D2C226564676573223A5B7B226964223A2233303337626534312D353638322D343334342D623934612D396661663563336536326261222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A227374617274222C227461726765744E6F64654964223A226170706C79222C227374617274506F696E74223A7B2278223A3335382C2279223A3136307D2C22656E64506F696E74223A7B2278223A3436302C2279223A3136307D2C2270726F70657274696573223A7B7D2C22706F696E74734C697374223A5B7B2278223A3335382C2279223A3136307D2C7B2278223A3436302C2279223A3136307D5D7D2C7B226964223A2263373936343261652D396632382D343231332D386364662D306530643634363762316239222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A226170706C79222C227461726765744E6F64654964223A22617070726F766544657074222C227374617274506F696E74223A7B2278223A3538302C2279223A3136307D2C22656E64506F696E74223A7B2278223A3638302C2279223A3136307D2C2270726F70657274696573223A7B7D2C22706F696E74734C697374223A5B7B2278223A3538302C2279223A3136307D2C7B2278223A3638302C2279223A3136307D5D7D2C7B226964223A2230396439623134332D393437332D346130662D383238372D396162663666363562616635222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A22617070726F766544657074222C227461726765744E6F64654964223A2232633735656562662D356261662D346364302D613762332D303534363662653133363334222C227374617274506F696E74223A7B2278223A3734302C2279223A3230307D2C22656E64506F696E74223A7B2278223A3734302C2279223A3331357D2C2270726F70657274696573223A7B7D2C22706F696E74734C697374223A5B7B2278223A3734302C2279223A3230307D2C7B2278223A3734302C2279223A3331357D5D7D2C7B226964223A2261363433343865632D343136382D346633362D386136312D313563663132633731306239222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A22617070726F7665426F7373222C227461726765744E6F64654964223A22656E64222C227374617274506F696E74223A7B2278223A3936302C2279223A3438307D2C22656E64506F696E74223A7B2278223A313038302C2279223A3134327D2C2270726F70657274696573223A7B7D2C22706F696E74734C697374223A5B7B2278223A3936302C2279223A3438307D2C7B2278223A313134302C2279223A3438307D2C7B2278223A313134302C2279223A3131327D2C7B2278223A313038302C2279223A3131327D2C7B2278223A313038302C2279223A3134327D5D7D2C7B226964223A2235313765663263372D333438362D343939322D623535342D306635333861623931373531222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A2232633735656562662D356261662D346364302D613762332D303534363662653133363334222C227461726765744E6F64654964223A22656E64222C227374617274506F696E74223A7B2278223A3736342C2279223A3333397D2C22656E64506F696E74223A7B2278223A313038302C2279223A3137387D2C2270726F70657274696573223A7B2265787072223A2223665F6461793C33222C227769647468223A3132302C22686569676874223A38307D2C2274657874223A7B2278223A3932322C2279223A3333392C2276616C7565223A22E8AFB7E58187E5A4A9E695B0E5B08FE4BA8E33227D2C22706F696E74734C697374223A5B7B2278223A3736342C2279223A3333397D2C7B2278223A313038302C2279223A3333397D2C7B2278223A313038302C2279223A3137387D5D7D2C7B226964223A2264376563343136362D663366632D346664362D613261632D613663346435303963346464222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A2232633735656562662D356261662D346364302D613762332D303534363662653133363334222C227461726765744E6F64654964223A22617070726F7665426F7373222C227374617274506F696E74223A7B2278223A3734302C2279223A3336357D2C22656E64506F696E74223A7B2278223A3834302C2279223A3438307D2C2270726F70657274696573223A7B2265787072223A2223665F6461793E3D33227D2C2274657874223A7B2278223A3734302C2279223A3432322E352C2276616C7565223A22E8AFB7E58187E5A4A9E695B0E5A4A7E4BA8EE7AD89E4BA8E33227D2C22706F696E74734C697374223A5B7B2278223A3734302C2279223A3336357D2C7B2278223A3734302C2279223A3438307D2C7B2278223A3834302C2279223A3438307D5D7D5D7D, '2023-09-28 11:24:02.619', 1567738052492341249);
INSERT INTO `wf_process_design_his` VALUES (1707288394098974722, 1706475684696399874, 0x7B22646973706C61794E616D65223A22E8AFB7E58187222C226E616D65223A226C65617665222C22696E7374616E636555726C223A226C656176655766466F726D222C226E6F646573223A5B7B226964223A227374617274222C2274797065223A22736E616B65723A7374617274222C2278223A3334302C2279223A3136302C2270726F70657274696573223A7B227769647468223A22313230222C22686569676874223A223830227D2C2274657874223A7B2278223A3334302C2279223A3230302C2276616C7565223A22E5BC80E5A78B227D7D2C7B226964223A226170706C79222C2274797065223A22736E616B65723A7461736B222C2278223A3532302C2279223A3136302C2270726F70657274696573223A7B2261737369676E6565223A22617070726F76652E6F70657261746F72222C227461736B54797065223A224D616A6F72222C22706572666F726D54797065223A22414E59222C226175746F45786563757465223A224E222C227769647468223A3132302C22686569676874223A38302C226669656C64223A7B7D7D2C2274657874223A7B2278223A3532302C2279223A3136302C2276616C7565223A22E8AFB7E58187E794B3E8AFB7227D7D2C7B226964223A22617070726F766544657074222C2274797065223A22736E616B65723A7461736B222C2278223A3734302C2279223A3136302C2270726F70657274696573223A7B2261737369676E6D656E7448616E646C6572223A22636F6D2E6D6C646F6E672E6D6F64756C65732E77662E666C6F772E68616E646C6572732E446570744C656164657241737369676E6D656E7448616E646C6572222C227461736B54797065223A224D616A6F72222C22706572666F726D54797065223A22414E59222C226175746F45786563757465223A224E222C227769647468223A3132302C22686569676874223A38302C226669656C64223A7B7D2C2261737369676E6565223A22227D2C2274657874223A7B2278223A3734302C2279223A3136302C2276616C7565223A22E983A8E997A8E9A286E5AFBCE5AEA1E689B9227D7D2C7B226964223A22617070726F7665426F7373222C2274797065223A22736E616B65723A7461736B222C2278223A3930302C2279223A3438302C2270726F70657274696573223A7B2261737369676E6D656E7448616E646C6572223A22222C227461736B54797065223A224D616A6F72222C22706572666F726D54797065223A22414E59222C226175746F45786563757465223A224E222C227769647468223A3132302C22686569676874223A38302C226669656C64223A7B7D2C2261737369676E6565223A22617070726F7665426F73732E6F70657261746F72227D2C2274657874223A7B2278223A3930302C2279223A3438302C2276616C7565223A22E585ACE58FB8E9A286E5AFBCE5AEA1E689B9227D7D2C7B226964223A2232633735656562662D356261662D346364302D613762332D303534363662653133363334222C2274797065223A22736E616B65723A6465636973696F6E222C2278223A3734302C2279223A3334302C2270726F70657274696573223A7B227769647468223A22313230222C22686569676874223A223830227D7D2C7B226964223A22656E64222C2274797065223A22736E616B65723A656E64222C2278223A313038302C2279223A3136302C2270726F70657274696573223A7B227769647468223A22313230222C22686569676874223A223830227D2C2274657874223A7B2278223A313038302C2279223A3230302C2276616C7565223A22E7BB93E69D9F227D7D5D2C226564676573223A5B7B226964223A2233303337626534312D353638322D343334342D623934612D396661663563336536326261222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A227374617274222C227461726765744E6F64654964223A226170706C79222C227374617274506F696E74223A7B2278223A3335382C2279223A3136307D2C22656E64506F696E74223A7B2278223A3436302C2279223A3136307D2C2270726F70657274696573223A7B7D2C22706F696E74734C697374223A5B7B2278223A3335382C2279223A3136307D2C7B2278223A3436302C2279223A3136307D5D7D2C7B226964223A2263373936343261652D396632382D343231332D386364662D306530643634363762316239222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A226170706C79222C227461726765744E6F64654964223A22617070726F766544657074222C227374617274506F696E74223A7B2278223A3538302C2279223A3136307D2C22656E64506F696E74223A7B2278223A3638302C2279223A3136307D2C2270726F70657274696573223A7B7D2C22706F696E74734C697374223A5B7B2278223A3538302C2279223A3136307D2C7B2278223A3638302C2279223A3136307D5D7D2C7B226964223A2230396439623134332D393437332D346130662D383238372D396162663666363562616635222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A22617070726F766544657074222C227461726765744E6F64654964223A2232633735656562662D356261662D346364302D613762332D303534363662653133363334222C227374617274506F696E74223A7B2278223A3734302C2279223A3230307D2C22656E64506F696E74223A7B2278223A3734302C2279223A3331357D2C2270726F70657274696573223A7B7D2C22706F696E74734C697374223A5B7B2278223A3734302C2279223A3230307D2C7B2278223A3734302C2279223A3331357D5D7D2C7B226964223A2261363433343865632D343136382D346633362D386136312D313563663132633731306239222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A22617070726F7665426F7373222C227461726765744E6F64654964223A22656E64222C227374617274506F696E74223A7B2278223A3936302C2279223A3438307D2C22656E64506F696E74223A7B2278223A313038302C2279223A3134327D2C2270726F70657274696573223A7B7D2C22706F696E74734C697374223A5B7B2278223A3936302C2279223A3438307D2C7B2278223A313134302C2279223A3438307D2C7B2278223A313134302C2279223A3131327D2C7B2278223A313038302C2279223A3131327D2C7B2278223A313038302C2279223A3134327D5D7D2C7B226964223A2235313765663263372D333438362D343939322D623535342D306635333861623931373531222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A2232633735656562662D356261662D346364302D613762332D303534363662653133363334222C227461726765744E6F64654964223A22656E64222C227374617274506F696E74223A7B2278223A3736342C2279223A3333397D2C22656E64506F696E74223A7B2278223A313038302C2279223A3137387D2C2270726F70657274696573223A7B2265787072223A2223665F6461793C33222C227769647468223A3132302C22686569676874223A38307D2C2274657874223A7B2278223A3932322C2279223A3333392C2276616C7565223A22E8AFB7E58187E5A4A9E695B0E5B08FE4BA8E33227D2C22706F696E74734C697374223A5B7B2278223A3736342C2279223A3333397D2C7B2278223A313038302C2279223A3333397D2C7B2278223A313038302C2279223A3137387D5D7D2C7B226964223A2264376563343136362D663366632D346664362D613261632D613663346435303963346464222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A2232633735656562662D356261662D346364302D613762332D303534363662653133363334222C227461726765744E6F64654964223A22617070726F7665426F7373222C227374617274506F696E74223A7B2278223A3734302C2279223A3336357D2C22656E64506F696E74223A7B2278223A3834302C2279223A3438307D2C2270726F70657274696573223A7B2265787072223A2223665F6461793E3D33227D2C2274657874223A7B2278223A3734302C2279223A3432322E352C2276616C7565223A22E8AFB7E58187E5A4A9E695B0E5A4A7E4BA8EE7AD89E4BA8E33227D2C22706F696E74734C697374223A5B7B2278223A3734302C2279223A3336357D2C7B2278223A3734302C2279223A3438307D2C7B2278223A3834302C2279223A3438307D5D7D5D7D, '2023-09-28 14:57:27.695', 1567738052492341249);
INSERT INTO `wf_process_design_his` VALUES (1707288497736032258, 1706475684696399874, 0x7B22646973706C61794E616D65223A22E8AFB7E58187222C226E616D65223A226C65617665222C22696E7374616E636555726C223A226C656176655766466F726D222C226E6F646573223A5B7B226964223A227374617274222C2274797065223A22736E616B65723A7374617274222C2278223A3334302C2279223A3136302C2270726F70657274696573223A7B227769647468223A22313230222C22686569676874223A223830227D2C2274657874223A7B2278223A3334302C2279223A3230302C2276616C7565223A22E5BC80E5A78B227D7D2C7B226964223A226170706C79222C2274797065223A22736E616B65723A7461736B222C2278223A3532302C2279223A3136302C2270726F70657274696573223A7B2261737369676E6565223A22617070726F76652E6F70657261746F72222C227461736B54797065223A224D616A6F72222C22706572666F726D54797065223A22414E59222C226175746F45786563757465223A224E222C227769647468223A3132302C22686569676874223A38302C226669656C64223A7B7D7D2C2274657874223A7B2278223A3532302C2279223A3136302C2276616C7565223A22E8AFB7E58187E794B3E8AFB7227D7D2C7B226964223A22617070726F766544657074222C2274797065223A22736E616B65723A7461736B222C2278223A3734302C2279223A3136302C2270726F70657274696573223A7B2261737369676E6D656E7448616E646C6572223A22636F6D2E6D6C646F6E672E6D6F64756C65732E77662E666C6F772E68616E646C6572732E446570744C656164657241737369676E6D656E7448616E646C6572222C227461736B54797065223A224D616A6F72222C22706572666F726D54797065223A22414E59222C226175746F45786563757465223A224E222C227769647468223A3132302C22686569676874223A38302C226669656C64223A7B7D2C2261737369676E6565223A22227D2C2274657874223A7B2278223A3734302C2279223A3136302C2276616C7565223A22E983A8E997A8E9A286E5AFBCE5AEA1E689B9227D7D2C7B226964223A22617070726F7665426F7373222C2274797065223A22736E616B65723A7461736B222C2278223A3930302C2279223A3438302C2270726F70657274696573223A7B2261737369676E6D656E7448616E646C6572223A22636F6D2E6D6C646F6E672E6D6F64756C65732E77662E666C6F772E68616E646C6572732E446570744D61696E4C656164657241737369676E6D656E7448616E646C6572222C227461736B54797065223A224D616A6F72222C22706572666F726D54797065223A22414E59222C226175746F45786563757465223A224E222C227769647468223A3132302C22686569676874223A38302C226669656C64223A7B7D2C2261737369676E6565223A22227D2C2274657874223A7B2278223A3930302C2279223A3438302C2276616C7565223A22E585ACE58FB8E9A286E5AFBCE5AEA1E689B9227D7D2C7B226964223A2232633735656562662D356261662D346364302D613762332D303534363662653133363334222C2274797065223A22736E616B65723A6465636973696F6E222C2278223A3734302C2279223A3334302C2270726F70657274696573223A7B227769647468223A22313230222C22686569676874223A223830227D7D2C7B226964223A22656E64222C2274797065223A22736E616B65723A656E64222C2278223A313038302C2279223A3136302C2270726F70657274696573223A7B227769647468223A22313230222C22686569676874223A223830227D2C2274657874223A7B2278223A313038302C2279223A3230302C2276616C7565223A22E7BB93E69D9F227D7D5D2C226564676573223A5B7B226964223A2233303337626534312D353638322D343334342D623934612D396661663563336536326261222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A227374617274222C227461726765744E6F64654964223A226170706C79222C227374617274506F696E74223A7B2278223A3335382C2279223A3136307D2C22656E64506F696E74223A7B2278223A3436302C2279223A3136307D2C2270726F70657274696573223A7B7D2C22706F696E74734C697374223A5B7B2278223A3335382C2279223A3136307D2C7B2278223A3436302C2279223A3136307D5D7D2C7B226964223A2263373936343261652D396632382D343231332D386364662D306530643634363762316239222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A226170706C79222C227461726765744E6F64654964223A22617070726F766544657074222C227374617274506F696E74223A7B2278223A3538302C2279223A3136307D2C22656E64506F696E74223A7B2278223A3638302C2279223A3136307D2C2270726F70657274696573223A7B7D2C22706F696E74734C697374223A5B7B2278223A3538302C2279223A3136307D2C7B2278223A3638302C2279223A3136307D5D7D2C7B226964223A2230396439623134332D393437332D346130662D383238372D396162663666363562616635222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A22617070726F766544657074222C227461726765744E6F64654964223A2232633735656562662D356261662D346364302D613762332D303534363662653133363334222C227374617274506F696E74223A7B2278223A3734302C2279223A3230307D2C22656E64506F696E74223A7B2278223A3734302C2279223A3331357D2C2270726F70657274696573223A7B7D2C22706F696E74734C697374223A5B7B2278223A3734302C2279223A3230307D2C7B2278223A3734302C2279223A3331357D5D7D2C7B226964223A2261363433343865632D343136382D346633362D386136312D313563663132633731306239222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A22617070726F7665426F7373222C227461726765744E6F64654964223A22656E64222C227374617274506F696E74223A7B2278223A3936302C2279223A3438307D2C22656E64506F696E74223A7B2278223A313038302C2279223A3134327D2C2270726F70657274696573223A7B7D2C22706F696E74734C697374223A5B7B2278223A3936302C2279223A3438307D2C7B2278223A313134302C2279223A3438307D2C7B2278223A313134302C2279223A3131327D2C7B2278223A313038302C2279223A3131327D2C7B2278223A313038302C2279223A3134327D5D7D2C7B226964223A2235313765663263372D333438362D343939322D623535342D306635333861623931373531222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A2232633735656562662D356261662D346364302D613762332D303534363662653133363334222C227461726765744E6F64654964223A22656E64222C227374617274506F696E74223A7B2278223A3736342C2279223A3333397D2C22656E64506F696E74223A7B2278223A313038302C2279223A3137387D2C2270726F70657274696573223A7B2265787072223A2223665F6461793C33222C227769647468223A3132302C22686569676874223A38307D2C2274657874223A7B2278223A3932322C2279223A3333392C2276616C7565223A22E8AFB7E58187E5A4A9E695B0E5B08FE4BA8E33227D2C22706F696E74734C697374223A5B7B2278223A3736342C2279223A3333397D2C7B2278223A313038302C2279223A3333397D2C7B2278223A313038302C2279223A3137387D5D7D2C7B226964223A2264376563343136362D663366632D346664362D613261632D613663346435303963346464222C2274797065223A22736E616B65723A7472616E736974696F6E222C22736F757263654E6F64654964223A2232633735656562662D356261662D346364302D613762332D303534363662653133363334222C227461726765744E6F64654964223A22617070726F7665426F7373222C227374617274506F696E74223A7B2278223A3734302C2279223A3336357D2C22656E64506F696E74223A7B2278223A3834302C2279223A3438307D2C2270726F70657274696573223A7B2265787072223A2223665F6461793E3D33227D2C2274657874223A7B2278223A3734302C2279223A3432322E352C2276616C7565223A22E8AFB7E58187E5A4A9E695B0E5A4A7E4BA8EE7AD89E4BA8E33227D2C22706F696E74734C697374223A5B7B2278223A3734302C2279223A3336357D2C7B2278223A3734302C2279223A3438307D2C7B2278223A3834302C2279223A3438307D5D7D5D7D, '2023-09-28 14:57:52.403', 1567738052492341249);

-- ----------------------------
-- Table structure for wf_process_instance
-- ----------------------------
DROP TABLE IF EXISTS `wf_process_instance`;
CREATE TABLE `wf_process_instance`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父流程ID，子流程实例才有值',
  `process_define_id` bigint(20) NULL DEFAULT NULL COMMENT '流程定义ID',
  `state` int(11) NULL DEFAULT NULL COMMENT '流程实例状态(10：进行中；20：已完成；30：已撤回；40：强行中止；50：挂起；99：已废弃)',
  `parent_node_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父流程依赖的节点名称',
  `business_no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务编号',
  `operator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '流程发起人',
  `expire_time` datetime(3) NULL DEFAULT NULL COMMENT '期望完成时间',
  `variable` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '附属变量json存储',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建用户',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新用户',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_process_instance_pfid`(`process_define_id`) USING BTREE,
  INDEX `idx_process_instance_operator`(`operator`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '流程实例' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wf_process_instance
-- ----------------------------
INSERT INTO `wf_process_instance` VALUES (1707278921594822658, NULL, 1707234699034468354, 10, NULL, NULL, '1567738052492341249', NULL, '{\"f_title\":\"我要请假啊啊\",\"f_reasonType\":3,\"f_day\":1,\"f_startTime\":\"2023-09-19 14:19:32\",\"f_endTime\":\"2023-09-20 14:19:32\"}', '2023-09-28 14:19:49.271', NULL, '2023-09-28 14:19:49.271', NULL);
INSERT INTO `wf_process_instance` VALUES (1707280707089059842, NULL, 1707234699034468354, 10, NULL, NULL, '1567738052492341249', NULL, '{\"f_title\":\"请假啊啊啊\",\"f_reasonType\":3,\"f_day\":1,\"f_startTime\":\"2023-09-12 14:26:36\",\"f_endTime\":\"2023-09-13 14:26:36\"}', '2023-09-28 14:26:54.956', '1567738052492341249', '2023-09-28 14:26:54.956', '1567738052492341249');
INSERT INTO `wf_process_instance` VALUES (1707288645736243201, NULL, 1707288521396101122, 10, NULL, NULL, '1567738052492341249', NULL, '{\"f_title\":\"我要请假啊啊\",\"f_reasonType\":3,\"f_day\":1,\"f_startTime\":\"2023-09-29 14:58:04\",\"f_endTime\":\"2023-09-30 14:58:04\"}', '2023-09-28 14:58:27.688', '1567738052492341249', '2023-09-28 14:58:27.688', '1567738052492341249');
INSERT INTO `wf_process_instance` VALUES (1707288981699993602, NULL, 1707288521396101122, 10, NULL, NULL, '1567738052492341249', NULL, '{\"f_title\":\"我要请假啊啊\",\"f_reasonType\":2,\"f_day\":1,\"f_startTime\":\"2023-09-28 14:59:32\",\"f_endTime\":\"2023-09-29 14:59:32\"}', '2023-09-28 14:59:47.790', '1567738052492341249', '2023-09-28 14:59:47.790', '1567738052492341249');
INSERT INTO `wf_process_instance` VALUES (1707289309778452481, NULL, 1707288521396101122, 10, NULL, NULL, '1567738052492341249', NULL, '{\"f_title\":\"请假啊啊\",\"f_reasonType\":3,\"f_day\":1,\"f_startTime\":\"2023-09-13 15:00:55\",\"f_endTime\":\"2023-09-14 15:00:55\"}', '2023-09-28 15:01:06.018', '1567738052492341249', '2023-09-28 15:01:06.018', '1567738052492341249');
INSERT INTO `wf_process_instance` VALUES (1707289881957015554, NULL, 1707288521396101122, 10, NULL, NULL, '1567738052492341249', NULL, '{\"f_title\":\"我要请假啊啊\",\"f_reasonType\":3,\"f_day\":1,\"f_startTime\":\"2023-09-20 15:03:10\",\"f_endTime\":\"2023-09-21 15:03:10\"}', '2023-09-28 15:03:22.425', '1567738052492341249', '2023-09-28 15:03:22.425', '1567738052492341249');
INSERT INTO `wf_process_instance` VALUES (1707290209880326146, NULL, 1707288521396101122, 10, NULL, NULL, '1567738052492341249', NULL, '{\"f_title\":\"请假啊啊\",\"f_reasonType\":3,\"f_day\":1,\"f_startTime\":\"2023-09-27 15:04:28\",\"f_endTime\":\"2023-09-28 15:04:28\"}', '2023-09-28 15:04:40.606', '1567738052492341249', '2023-09-28 15:04:40.606', '1567738052492341249');
INSERT INTO `wf_process_instance` VALUES (1707290878372708354, NULL, 1707288521396101122, 10, NULL, NULL, '1567738052492341249', NULL, '{\"f_title\":\"请假啊啊\",\"f_reasonType\":3,\"f_day\":2,\"f_startTime\":\"2023-09-20 15:07:00\",\"f_endTime\":\"2023-09-22 15:07:00\"}', '2023-09-28 15:07:19.989', '1567738052492341249', '2023-09-28 15:07:19.989', '1567738052492341249');

-- ----------------------------
-- Table structure for wf_process_task
-- ----------------------------
DROP TABLE IF EXISTS `wf_process_task`;
CREATE TABLE `wf_process_task`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `process_instance_id` bigint(20) NOT NULL COMMENT '流程实例ID',
  `task_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务名称编码',
  `display_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务显示名称',
  `task_type` int(11) NULL DEFAULT NULL COMMENT '任务类型(0：主办任务；1：协办任务)',
  `perform_type` int(11) NULL DEFAULT NULL COMMENT '参与类型(0：普通参与；1：会签参与)',
  `task_state` int(11) NULL DEFAULT NULL COMMENT '任务状态(10：进行中；20：已完成；30：已撤回；40：强行中止；50：挂起；99：已废弃)',
  `operator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务处理人',
  `finish_time` datetime(3) NULL DEFAULT NULL COMMENT '任务完成时间',
  `expire_time` datetime(3) NULL DEFAULT NULL COMMENT '任务期待完成时间',
  `form_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务处理表单KEY',
  `task_parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父任务ID',
  `variable` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '附属变量json存储',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建用户',
  `update_time` datetime(3) NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新用户',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_process_task_piid`(`process_instance_id`) USING BTREE,
  INDEX `idx_process_task_name`(`task_name`) USING BTREE,
  INDEX `idx_process_task_operator`(`operator`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '流程任务' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wf_process_task
-- ----------------------------
INSERT INTO `wf_process_task` VALUES (1707278921607405569, 1707278921594822658, 'apply', '请假申请', NULL, NULL, 20, 'flow.auto', NULL, NULL, NULL, 0, '{\"f_title\":\"我要请假啊啊\",\"f_reasonType\":3,\"f_day\":1,\"f_startTime\":\"2023-09-19 14:19:32\",\"f_endTime\":\"2023-09-20 14:19:32\"}', '2023-09-28 14:19:49.283', NULL, '2023-09-28 14:19:49.300', 'flow.auto');
INSERT INTO `wf_process_task` VALUES (1707278921674514434, 1707278921594822658, 'approveDept', '部门领导审批', NULL, NULL, 10, NULL, NULL, NULL, NULL, 1707278921607405569, '{\"f_title\":\"我要请假啊啊\",\"f_reasonType\":3,\"f_day\":1,\"f_startTime\":\"2023-09-19 14:19:32\",\"f_endTime\":\"2023-09-20 14:19:32\"}', '2023-09-28 14:19:49.304', NULL, '2023-09-28 14:19:49.304', NULL);
INSERT INTO `wf_process_task` VALUES (1707280707126808577, 1707280707089059842, 'apply', '请假申请', NULL, NULL, 20, 'flow.auto', NULL, NULL, NULL, 0, '{\"f_title\":\"请假啊啊啊\",\"f_reasonType\":3,\"f_day\":1,\"f_startTime\":\"2023-09-12 14:26:36\",\"f_endTime\":\"2023-09-13 14:26:36\"}', '2023-09-28 14:26:54.976', '1567738052492341249', '2023-09-28 14:26:55.213', 'flow.auto');
INSERT INTO `wf_process_task` VALUES (1707280708141830145, 1707280707089059842, 'approveDept', '部门领导审批', NULL, NULL, 10, NULL, NULL, NULL, NULL, 1707280707126808577, '{\"f_title\":\"请假啊啊啊\",\"f_reasonType\":3,\"f_day\":1,\"f_startTime\":\"2023-09-12 14:26:36\",\"f_endTime\":\"2023-09-13 14:26:36\"}', '2023-09-28 14:26:55.218', '1567738052492341249', '2023-09-28 14:26:55.218', '1567738052492341249');
INSERT INTO `wf_process_task` VALUES (1707288645765603329, 1707288645736243201, 'apply', '请假申请', NULL, NULL, 20, 'flow.auto', NULL, NULL, NULL, 0, '{\"f_title\":\"我要请假啊啊\",\"f_reasonType\":3,\"f_day\":1,\"f_startTime\":\"2023-09-29 14:58:04\",\"f_endTime\":\"2023-09-30 14:58:04\"}', '2023-09-28 14:58:27.695', '1567738052492341249', '2023-09-28 14:58:27.710', 'flow.auto');
INSERT INTO `wf_process_task` VALUES (1707288645841100801, 1707288645736243201, 'approveDept', '部门领导审批', NULL, NULL, 10, NULL, NULL, NULL, NULL, 1707288645765603329, '{\"f_title\":\"我要请假啊啊\",\"f_reasonType\":3,\"f_day\":1,\"f_startTime\":\"2023-09-29 14:58:04\",\"f_endTime\":\"2023-09-30 14:58:04\"}', '2023-09-28 14:58:27.714', '1567738052492341249', '2023-09-28 14:58:27.714', '1567738052492341249');
INSERT INTO `wf_process_task` VALUES (1707288981708382209, 1707288981699993602, 'apply', '请假申请', NULL, NULL, 20, 'flow.auto', NULL, NULL, NULL, 0, '{\"f_title\":\"我要请假啊啊\",\"f_reasonType\":2,\"f_day\":1,\"f_startTime\":\"2023-09-28 14:59:32\",\"f_endTime\":\"2023-09-29 14:59:32\"}', '2023-09-28 14:59:47.792', '1567738052492341249', '2023-09-28 14:59:47.799', 'flow.auto');
INSERT INTO `wf_process_task` VALUES (1707288981750325249, 1707288981699993602, 'approveDept', '部门领导审批', NULL, NULL, 10, NULL, NULL, NULL, NULL, 1707288981708382209, '{\"f_title\":\"我要请假啊啊\",\"f_reasonType\":2,\"f_day\":1,\"f_startTime\":\"2023-09-28 14:59:32\",\"f_endTime\":\"2023-09-29 14:59:32\"}', '2023-09-28 14:59:47.800', '1567738052492341249', '2023-09-28 14:59:47.800', '1567738052492341249');
INSERT INTO `wf_process_task` VALUES (1707289309778452482, 1707289309778452481, 'apply', '请假申请', NULL, NULL, 20, 'flow.auto', NULL, NULL, NULL, 0, '{\"f_title\":\"请假啊啊\",\"f_reasonType\":3,\"f_day\":1,\"f_startTime\":\"2023-09-13 15:00:55\",\"f_endTime\":\"2023-09-14 15:00:55\"}', '2023-09-28 15:01:06.020', '1567738052492341249', '2023-09-28 15:01:06.029', 'flow.auto');
INSERT INTO `wf_process_task` VALUES (1707289309845561346, 1707289309778452481, 'approveDept', '部门领导审批', NULL, NULL, 10, NULL, NULL, NULL, NULL, 1707289309778452482, '{\"f_title\":\"请假啊啊\",\"f_reasonType\":3,\"f_day\":1,\"f_startTime\":\"2023-09-13 15:00:55\",\"f_endTime\":\"2023-09-14 15:00:55\"}', '2023-09-28 15:01:06.031', '1567738052492341249', '2023-09-28 15:01:06.031', '1567738052492341249');
INSERT INTO `wf_process_task` VALUES (1707289881957015555, 1707289881957015554, 'apply', '请假申请', NULL, NULL, 20, 'flow.auto', NULL, NULL, NULL, 0, '{\"f_title\":\"我要请假啊啊\",\"f_reasonType\":3,\"f_day\":1,\"f_startTime\":\"2023-09-20 15:03:10\",\"f_endTime\":\"2023-09-21 15:03:10\"}', '2023-09-28 15:03:22.436', '1567738052492341249', '2023-09-28 15:03:22.476', 'flow.auto');
INSERT INTO `wf_process_task` VALUES (1707289882158342146, 1707289881957015554, 'approveDept', '部门领导审批', NULL, NULL, 10, NULL, NULL, NULL, NULL, 1707289881957015555, '{\"f_title\":\"我要请假啊啊\",\"f_reasonType\":3,\"f_day\":1,\"f_startTime\":\"2023-09-20 15:03:10\",\"f_endTime\":\"2023-09-21 15:03:10\"}', '2023-09-28 15:03:22.480', '1567738052492341249', '2023-09-28 15:03:22.480', '1567738052492341249');
INSERT INTO `wf_process_task` VALUES (1707290209926463490, 1707290209880326146, 'apply', '请假申请', NULL, NULL, 20, 'flow.auto', NULL, NULL, NULL, 0, '{\"f_title\":\"请假啊啊\",\"f_reasonType\":3,\"f_day\":1,\"f_startTime\":\"2023-09-27 15:04:28\",\"f_endTime\":\"2023-09-28 15:04:28\"}', '2023-09-28 15:04:40.619', '1567738052492341249', '2023-09-28 15:05:07.122', 'flow.auto');
INSERT INTO `wf_process_task` VALUES (1707290321109073922, 1707290209880326146, 'approveDept', '部门领导审批', NULL, NULL, 10, NULL, NULL, NULL, NULL, 1707290209926463490, '{\"f_title\":\"请假啊啊\",\"f_reasonType\":3,\"f_day\":1,\"f_startTime\":\"2023-09-27 15:04:28\",\"f_endTime\":\"2023-09-28 15:04:28\"}', '2023-09-28 15:05:07.129', '1567738052492341249', '2023-09-28 15:05:07.129', '1567738052492341249');
INSERT INTO `wf_process_task` VALUES (1707290878381096961, 1707290878372708354, 'apply', '请假申请', NULL, NULL, 20, 'flow.auto', NULL, NULL, NULL, 0, '{\"f_title\":\"请假啊啊\",\"f_reasonType\":3,\"f_day\":2,\"f_startTime\":\"2023-09-20 15:07:00\",\"f_endTime\":\"2023-09-22 15:07:00\"}', '2023-09-28 15:07:19.999', '1567738052492341249', '2023-09-28 15:07:20.108', 'flow.auto');
INSERT INTO `wf_process_task` VALUES (1707290878884413442, 1707290878372708354, 'approveDept', '部门领导审批', NULL, NULL, 10, NULL, NULL, NULL, NULL, 1707290878381096961, '{\"f_title\":\"请假啊啊\",\"f_reasonType\":3,\"f_day\":2,\"f_startTime\":\"2023-09-20 15:07:00\",\"f_endTime\":\"2023-09-22 15:07:00\"}', '2023-09-28 15:07:20.114', '1567738052492341249', '2023-09-28 15:07:20.114', '1567738052492341249');

-- ----------------------------
-- Table structure for wf_process_task_actor
-- ----------------------------
DROP TABLE IF EXISTS `wf_process_task_actor`;
CREATE TABLE `wf_process_task_actor`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `process_task_id` bigint(20) NOT NULL COMMENT '流程任务ID',
  `actor_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '参与者ID',
  `create_time` datetime(3) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建用户',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_process_task_actor_ptid`(`process_task_id`) USING BTREE,
  INDEX `idx_process_task_actor_aid`(`actor_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '流程任务和参与人关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wf_process_task_actor
-- ----------------------------
INSERT INTO `wf_process_task_actor` VALUES (1707278921607405570, 1707278921607405569, 'approve.operator', '2023-09-28 14:19:49.287', NULL);
INSERT INTO `wf_process_task_actor` VALUES (1707278921737428994, 1707278921674514434, 'approveDept.operator', '2023-09-28 14:19:49.307', NULL);
INSERT INTO `wf_process_task_actor` VALUES (1707280707500101633, 1707280707126808577, 'approve.operator', '2023-09-28 14:26:55.067', '1567738052492341249');
INSERT INTO `wf_process_task_actor` VALUES (1707280708141830146, 1707280708141830145, 'approveDept.operator', '2023-09-28 14:26:55.220', '1567738052492341249');
INSERT INTO `wf_process_task_actor` VALUES (1707288645778186242, 1707288645765603329, 'approve.operator', '2023-09-28 14:58:27.698', '1567738052492341249');
INSERT INTO `wf_process_task_actor` VALUES (1707288981716770818, 1707288981708382209, 'approve.operator', '2023-09-28 14:59:47.793', '1567738052492341249');
INSERT INTO `wf_process_task_actor` VALUES (1707289309778452483, 1707289309778452482, 'approve.operator', '2023-09-28 15:01:06.022', '1567738052492341249');
INSERT INTO `wf_process_task_actor` VALUES (1707289882099621890, 1707289881957015555, 'approve.operator', '2023-09-28 15:03:22.462', '1567738052492341249');
INSERT INTO `wf_process_task_actor` VALUES (1707290308329029634, 1707290209926463490, 'approve.operator', '2023-09-28 15:05:04.079', '1567738052492341249');
INSERT INTO `wf_process_task_actor` VALUES (1707290878817304578, 1707290878381096961, 'approve.operator', '2023-09-28 15:07:20.097', '1567738052492341249');
INSERT INTO `wf_process_task_actor` VALUES (1707290878934745089, 1707290878884413442, '1686404946814533633', '2023-09-28 15:07:20.125', '1567738052492341249');

SET FOREIGN_KEY_CHECKS = 1;
