/*
 Navicat Premium Data Transfer

 Source Server         : 39.96.14.169
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 39.96.14.169:3306
 Source Schema         : common

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 11/11/2019 18:25:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ev_event
-- ----------------------------
DROP TABLE IF EXISTS `ev_event`;
CREATE TABLE `ev_event`  (
  `id` bigint(20) NOT NULL COMMENT '自增主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ev_step
-- ----------------------------
DROP TABLE IF EXISTS `ev_step`;
CREATE TABLE `ev_step`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '步骤名',
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '步骤类型',
  `step_desc` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '步骤描述',
  `creator` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `editor` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for st_authority
-- ----------------------------
DROP TABLE IF EXISTS `st_authority`;
CREATE TABLE `st_authority`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '权限名称',
  `code` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '权限编码',
  `full_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '编号路径',
  `desc` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '权限描述',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '父Id',
  `flag` tinyint(1) NULL DEFAULT NULL COMMENT '有效标志',
  `creator` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `editor` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of st_authority
-- ----------------------------
INSERT INTO `st_authority` VALUES (1, '公共', 'public', '0', NULL, 1, 0, 1, NULL, NULL, NULL, '2019-07-26 16:44:51');
INSERT INTO `st_authority` VALUES (2, '系统管理', 'sysmgr', '0', NULL, 1, 0, 1, NULL, NULL, NULL, '2019-07-26 11:56:42');
INSERT INTO `st_authority` VALUES (3, '用户管理', 'sysmgr.user', '0-2', NULL, 1, 2, 1, NULL, NULL, NULL, '2019-07-26 18:44:24');
INSERT INTO `st_authority` VALUES (4, '角色管理', 'sysmgr.rule', '0-2', NULL, 2, 2, 1, NULL, NULL, NULL, '2019-07-26 11:53:51');
INSERT INTO `st_authority` VALUES (5, '菜单管理', 'sysmgr.resource', '0-2', NULL, 3, 2, 1, NULL, NULL, NULL, '2019-07-26 18:44:50');
INSERT INTO `st_authority` VALUES (6, '权限管理', 'sysmgr.authority', '0-2', NULL, 4, 2, 1, NULL, NULL, NULL, '2019-07-26 18:44:53');
INSERT INTO `st_authority` VALUES (7, '查询用户', 'sysmgr.user.query', '0-2-3', NULL, 1, 3, 1, NULL, NULL, NULL, '2019-07-26 18:44:57');
INSERT INTO `st_authority` VALUES (8, '编辑用户', 'sysmgr.user.save', '0-2-3', NULL, 2, 3, 1, NULL, NULL, NULL, '2019-07-26 18:45:00');
INSERT INTO `st_authority` VALUES (9, '删除用户', 'sysmgr.user.delete', '0-2-3', NULL, 3, 3, 1, NULL, NULL, NULL, '2019-07-26 18:45:04');
INSERT INTO `st_authority` VALUES (10, '查询角色', 'sysmgr.role.query', '0-2-4', NULL, 1, 4, 1, NULL, NULL, NULL, '2019-07-26 18:45:06');
INSERT INTO `st_authority` VALUES (11, '编辑角色', 'sysmgr.role.save', '0-2-4', NULL, 2, 4, 1, NULL, NULL, NULL, '2019-07-26 18:45:09');
INSERT INTO `st_authority` VALUES (12, '删除角色', 'sysmgr.role.delete', '0-2-4', NULL, 3, 4, 1, NULL, NULL, NULL, '2019-07-26 18:45:13');
INSERT INTO `st_authority` VALUES (13, '查询菜单', 'sysmgr.resource.query', '0-2-5', NULL, 1, 5, 1, NULL, NULL, NULL, '2019-07-26 11:50:34');
INSERT INTO `st_authority` VALUES (14, '编辑菜单', 'sysmgr.resource.save', '0-2-5', NULL, 2, 5, 1, NULL, NULL, NULL, '2019-07-26 18:45:23');
INSERT INTO `st_authority` VALUES (15, '删除菜单', 'sysmgr.resource.delete', '0-2-5', NULL, 3, 5, 1, NULL, NULL, NULL, '2019-07-26 18:45:26');
INSERT INTO `st_authority` VALUES (16, '查询权限', 'sysmgr.authority.query', '0-2-6', NULL, 1, 6, 1, NULL, NULL, NULL, '2019-07-26 18:45:28');
INSERT INTO `st_authority` VALUES (17, '编辑权限', 'sysmgr.authority.save', '0-2-6', NULL, 2, 6, 1, NULL, NULL, NULL, '2019-07-26 18:45:31');
INSERT INTO `st_authority` VALUES (18, '删除权限', 'sysmgr.authority.delete', '0-2-6', NULL, 3, 6, 1, NULL, NULL, NULL, '2019-07-26 18:45:35');
INSERT INTO `st_authority` VALUES (30, '登录日志', 'sysmgr.loginlog', '0-2', NULL, 5, 2, 1, 1, 1, '2019-07-26 03:11:50', '2019-11-09 09:11:07');
INSERT INTO `st_authority` VALUES (31, '查询日志', 'sysmgr.loginlog.query', '0-2-30', NULL, 1, 30, 1, 1, 1, '2019-07-26 03:12:14', '2019-11-09 09:11:07');
INSERT INTO `st_authority` VALUES (32, '删除日志', 'sysmgr.loginlog.delete', '0-2-30', NULL, 2, 30, 1, 1, 1, '2019-07-26 03:12:44', '2019-11-09 09:11:07');
INSERT INTO `st_authority` VALUES (33, '基础信息', 'baseinfo', '0', NULL, 3, 0, 1, 1, 1, '2019-07-26 03:13:11', '2019-11-09 09:11:07');
INSERT INTO `st_authority` VALUES (34, '字典管理', 'baseinfo.dict', '0-33', NULL, 1, 33, 1, 1, 1, '2019-07-26 03:13:31', '2019-11-09 09:11:07');
INSERT INTO `st_authority` VALUES (35, '查询字典', 'baseinfo.dict.query', '0-33-34', NULL, 1, 34, 1, 1, 1, '2019-07-26 03:14:01', '2019-11-09 09:11:07');
INSERT INTO `st_authority` VALUES (36, '编辑字典', 'baseinfo.dict.save', '0-33-34', NULL, 2, 34, 1, 1, 1, '2019-07-26 03:14:43', '2019-11-09 09:11:07');
INSERT INTO `st_authority` VALUES (37, '删除字典', 'baseinfo.dict.delete', '0-33-34', NULL, 3, 34, 1, 1, 1, '2019-07-26 03:15:03', '2019-11-09 09:11:07');
INSERT INTO `st_authority` VALUES (38, '附件管理', 'sysmgr.att', '0-2', NULL, 6, 2, 1, 1, 1, '2019-09-24 13:03:19', '2019-11-09 09:11:08');
INSERT INTO `st_authority` VALUES (39, '查询附件', 'sysmgr.att.query', '0-2-38', NULL, 1, 38, 1, 1, 1, '2019-09-24 13:05:23', '2019-11-09 09:11:08');
INSERT INTO `st_authority` VALUES (40, '删除附件', 'sysmgr.att.delete', '0-2-38', NULL, 2, 38, 1, 1, 1, '2019-09-24 13:06:55', '2019-11-09 09:11:08');
INSERT INTO `st_authority` VALUES (41, '系统日志', 'sysmgr.syslog', '0-2', NULL, 7, 2, 1, 1, 1, '2019-09-24 13:09:29', '2019-11-09 09:11:08');
INSERT INTO `st_authority` VALUES (42, '查询系统日志', 'sysmgr.syslog.query', '0-2-41', NULL, 1, 41, 1, 1, 1, '2019-09-24 13:13:39', '2019-11-09 09:11:08');
INSERT INTO `st_authority` VALUES (43, '系统备份', 'sysmgr.backup', '0-2', NULL, 8, 2, 1, 1, 1, '2019-09-25 06:10:01', '2019-11-09 09:11:08');
INSERT INTO `st_authority` VALUES (44, '查询备份', 'sysmgr.backup.query', '0-2-43', NULL, 1, 43, 1, 1, 1, '2019-09-25 06:10:15', '2019-11-09 09:11:08');
INSERT INTO `st_authority` VALUES (45, '删除备份', 'sysmgr.backup.delete', '0-2-43', NULL, 2, 43, 1, 1, 1, '2019-09-25 06:10:35', '2019-11-09 09:11:08');
INSERT INTO `st_authority` VALUES (46, '定时任务', 'sysmgr.schedulejob', '0-2', NULL, 9, 2, 1, 1, 1, '2019-09-25 06:11:03', '2019-11-09 09:11:08');
INSERT INTO `st_authority` VALUES (47, '查询任务', 'sysmgr.schedulejob.query', '0-2-46', NULL, 1, 46, 1, 1, 1, '2019-09-25 06:11:35', '2019-11-09 09:11:08');
INSERT INTO `st_authority` VALUES (48, '编辑任务', 'sysmgr.schedulejob.save', '0-2-46', NULL, 2, 46, 1, 1, 1, '2019-09-25 06:12:04', '2019-11-09 09:11:08');
INSERT INTO `st_authority` VALUES (49, '删除任务', 'sysmgr.schedulejob.delete', '0-2-46', NULL, 3, 46, 1, 1, 1, '2019-09-25 06:12:25', '2019-11-09 09:11:08');
INSERT INTO `st_authority` VALUES (50, '上传附件', 'sysmgr.att.upload', '0-2-38', NULL, 3, 38, 1, 1, 1, '2019-09-25 06:15:38', '2019-11-09 09:11:08');
INSERT INTO `st_authority` VALUES (51, '个人空间', 'tool', '0', NULL, 10, 0, 1, 1, 1, '2019-09-25 07:11:47', '2019-11-09 09:11:08');
INSERT INTO `st_authority` VALUES (52, '待办事项', 'tool.todo', '0-51', NULL, 1, 51, 1, 1, 1, '2019-09-25 07:13:25', '2019-11-09 09:11:08');
INSERT INTO `st_authority` VALUES (53, '查询待办事项', 'tool.todo.query', '0-51-52', NULL, 1, 52, 1, 1, 1, '2019-09-25 07:13:41', '2019-11-09 09:11:08');
INSERT INTO `st_authority` VALUES (54, '编辑待办事项', 'tool.todo.save', '0-51-52', NULL, 2, 52, 1, 1, 1, '2019-09-25 07:14:22', '2019-11-09 09:11:08');
INSERT INTO `st_authority` VALUES (55, '删除待办事项', 'tool.todo.delete', '0-51-52', NULL, 3, 52, 1, 1, 1, '2019-09-25 07:14:45', '2019-11-09 09:11:08');

-- ----------------------------
-- Table structure for st_login_log
-- ----------------------------
DROP TABLE IF EXISTS `st_login_log`;
CREATE TABLE `st_login_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '访问时间',
  `content` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '内容',
  `flag` tinyint(1) NULL DEFAULT NULL COMMENT '有效标志',
  `creator` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `editor` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 229 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '登录日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of st_login_log
-- ----------------------------
INSERT INTO `st_login_log` VALUES (1, 'admin', '2019-07-26 05:50:21', '登录成功', 1, 1, 1, '2019-07-26 05:50:21', '2019-11-09 09:24:19');
INSERT INTO `st_login_log` VALUES (2, 'admin', '2019-07-26 08:31:30', '登录成功', 1, 1, 1, '2019-07-26 08:31:30', '2019-11-09 09:24:19');
INSERT INTO `st_login_log` VALUES (3, 'admin', '2019-07-26 08:58:40', '登录成功', 1, 1, 1, '2019-07-26 08:58:40', '2019-11-09 09:24:19');
INSERT INTO `st_login_log` VALUES (4, 'admin', '2019-07-26 09:04:12', '登录成功', 1, 1, 1, '2019-07-26 09:04:12', '2019-11-09 09:24:19');
INSERT INTO `st_login_log` VALUES (5, 'admin', '2019-07-26 11:35:11', '登录成功', 1, 1, 1, '2019-07-26 11:35:11', '2019-11-09 09:24:19');
INSERT INTO `st_login_log` VALUES (6, 'admin', '2019-07-29 06:06:49', '登录成功', 1, 1, 1, '2019-07-29 06:06:49', '2019-11-09 09:24:19');
INSERT INTO `st_login_log` VALUES (7, 'admin', '2019-07-29 06:20:18', '登录成功', 1, 1, 1, '2019-07-29 06:20:18', '2019-11-09 09:24:19');
INSERT INTO `st_login_log` VALUES (8, 'admin', '2019-07-29 09:12:12', '登录成功', 1, 1, 1, '2019-07-29 09:12:12', '2019-11-09 09:24:19');
INSERT INTO `st_login_log` VALUES (9, 'admin', '2019-07-30 11:36:51', '登录成功', 1, 1, 1, '2019-07-30 11:36:51', '2019-11-09 09:24:19');
INSERT INTO `st_login_log` VALUES (10, 'admin', '2019-07-30 11:53:40', '登录成功', 1, 1, 1, '2019-07-30 11:53:40', '2019-11-09 09:24:19');
INSERT INTO `st_login_log` VALUES (11, 'admin', '2019-08-01 06:03:23', '登录成功', 1, 1, 1, '2019-08-01 06:03:23', '2019-11-09 09:24:19');
INSERT INTO `st_login_log` VALUES (12, 'admin', '2019-08-01 06:34:26', '登录成功', 1, 1, 1, '2019-08-01 06:34:26', '2019-11-09 09:24:19');
INSERT INTO `st_login_log` VALUES (13, 'admin', '2019-08-03 12:43:20', '登录成功', 1, 1, 1, '2019-08-03 12:43:20', '2019-11-09 09:24:19');
INSERT INTO `st_login_log` VALUES (14, 'admin', '2019-08-05 02:41:57', '登录成功', 1, 1, 1, '2019-08-05 02:41:57', '2019-11-09 09:24:19');
INSERT INTO `st_login_log` VALUES (15, 'admin', '2019-08-05 11:45:57', '登录成功', 1, 1, 1, '2019-08-05 11:45:57', '2019-11-09 09:24:19');
INSERT INTO `st_login_log` VALUES (16, 'admin', '2019-08-07 02:31:36', '登录成功', 1, 1, 1, '2019-08-07 02:31:36', '2019-11-09 09:24:19');
INSERT INTO `st_login_log` VALUES (17, 'admin', '2019-08-12 06:03:37', '登录成功', 1, 1, 1, '2019-08-12 06:03:37', '2019-11-09 09:24:19');
INSERT INTO `st_login_log` VALUES (18, 'admin', '2019-08-12 06:24:23', '登录成功', 1, 1, 1, '2019-08-12 06:24:23', '2019-11-09 09:24:20');
INSERT INTO `st_login_log` VALUES (19, 'admin', '2019-08-12 06:45:17', '登录成功', 1, 1, 1, '2019-08-12 06:45:17', '2019-11-09 09:24:20');
INSERT INTO `st_login_log` VALUES (20, 'admin', '2019-08-12 06:45:58', '登录成功', 1, 1, 1, '2019-08-12 06:45:58', '2019-11-09 09:24:20');
INSERT INTO `st_login_log` VALUES (21, 'admin', '2019-08-12 06:46:36', '登录成功', 1, 1, 1, '2019-08-12 06:46:36', '2019-11-09 09:24:20');
INSERT INTO `st_login_log` VALUES (22, 'admin', '2019-08-12 07:38:59', '登录成功', 1, 1, 1, '2019-08-12 07:38:59', '2019-11-09 09:24:20');
INSERT INTO `st_login_log` VALUES (23, 'admin', '2019-08-12 08:22:30', '登录成功', 1, 1, 1, '2019-08-12 08:22:30', '2019-11-09 09:24:20');
INSERT INTO `st_login_log` VALUES (24, 'admin', '2019-08-12 09:40:29', '登录成功', 1, 1, 1, '2019-08-12 09:40:29', '2019-11-09 09:24:20');
INSERT INTO `st_login_log` VALUES (25, 'admin', '2019-08-12 10:47:12', '登录成功', 1, 1, 1, '2019-08-12 10:47:12', '2019-11-09 09:24:20');
INSERT INTO `st_login_log` VALUES (26, 'admin', '2019-08-13 01:37:26', '登录成功', 1, 1, 1, '2019-08-13 01:37:26', '2019-11-09 09:24:20');
INSERT INTO `st_login_log` VALUES (27, 'admin', '2019-08-13 05:48:12', '登录成功', 1, 1, 1, '2019-08-13 05:48:12', '2019-11-09 09:24:20');
INSERT INTO `st_login_log` VALUES (28, 'admin', '2019-08-13 07:03:36', '登录成功', 1, 1, 1, '2019-08-13 07:03:36', '2019-11-09 09:24:20');
INSERT INTO `st_login_log` VALUES (29, 'admin', '2019-08-13 07:52:05', '登录成功', 1, 1, 1, '2019-08-13 07:52:05', '2019-11-09 09:24:20');
INSERT INTO `st_login_log` VALUES (30, 'admin', '2019-08-14 06:04:20', '登录成功', 1, 1, 1, '2019-08-14 06:04:20', '2019-11-09 09:24:20');
INSERT INTO `st_login_log` VALUES (31, 'admin', '2019-08-14 09:57:30', '登录成功', 1, 1, 1, '2019-08-14 09:57:30', '2019-11-09 09:24:20');
INSERT INTO `st_login_log` VALUES (32, 'admin', '2019-08-15 02:05:58', '登录成功', 1, 1, 1, '2019-08-15 02:05:58', '2019-11-09 09:24:20');
INSERT INTO `st_login_log` VALUES (33, 'admin', '2019-08-15 05:54:23', '登录成功', 1, 1, 1, '2019-08-15 05:54:23', '2019-11-09 09:24:20');
INSERT INTO `st_login_log` VALUES (34, 'admin', '2019-08-16 04:52:55', '登录成功', 1, 1, 1, '2019-08-16 04:52:55', '2019-11-09 09:24:20');
INSERT INTO `st_login_log` VALUES (35, 'admin', '2019-08-16 05:03:52', '登录成功', 1, 1, 1, '2019-08-16 05:03:52', '2019-11-09 09:24:20');
INSERT INTO `st_login_log` VALUES (36, 'admin', '2019-08-16 05:06:07', '登录成功', 1, 1, 1, '2019-08-16 05:06:07', '2019-11-09 09:24:20');
INSERT INTO `st_login_log` VALUES (37, 'admin', '2019-08-16 06:07:13', '登录成功', 1, 1, 1, '2019-08-16 06:07:13', '2019-11-09 09:24:20');
INSERT INTO `st_login_log` VALUES (38, 'admin', '2019-08-16 06:07:46', '登录成功', 1, 1, 1, '2019-08-16 06:07:46', '2019-11-09 09:24:20');
INSERT INTO `st_login_log` VALUES (39, 'admin', '2019-08-16 06:08:04', '登录成功', 1, 1, 1, '2019-08-16 06:08:04', '2019-11-09 09:24:20');
INSERT INTO `st_login_log` VALUES (40, 'admin', '2019-08-18 12:37:26', '登录成功', 1, 1, 1, '2019-08-18 12:37:26', '2019-11-09 09:24:20');
INSERT INTO `st_login_log` VALUES (41, 'admin', '2019-08-19 01:35:08', '登录成功', 1, 1, 1, '2019-08-19 01:35:08', '2019-11-09 09:24:20');
INSERT INTO `st_login_log` VALUES (42, 'admin', '2019-08-19 03:02:58', '登录成功', 1, 1, 1, '2019-08-19 03:02:58', '2019-11-09 09:24:20');
INSERT INTO `st_login_log` VALUES (43, 'admin', '2019-08-19 03:03:04', '登录成功', 1, 1, 1, '2019-08-19 03:03:04', '2019-11-09 09:24:20');
INSERT INTO `st_login_log` VALUES (44, 'admin', '2019-08-19 03:03:07', '登录成功', 1, 1, 1, '2019-08-19 03:03:07', '2019-11-09 09:24:20');
INSERT INTO `st_login_log` VALUES (45, 'admin', '2019-08-19 03:04:53', '登录成功', 1, 1, 1, '2019-08-19 03:04:53', '2019-11-09 09:24:20');
INSERT INTO `st_login_log` VALUES (46, 'admin', '2019-08-19 03:18:46', '登录成功', 1, 1, 1, '2019-08-19 03:18:46', '2019-11-09 09:24:20');
INSERT INTO `st_login_log` VALUES (47, 'admin', '2019-08-19 10:22:09', '登录成功', 1, 1, 1, '2019-08-19 10:22:09', '2019-11-09 09:24:20');
INSERT INTO `st_login_log` VALUES (48, 'admin', '2019-08-23 08:42:16', '登录成功', 1, 1, 1, '2019-08-23 08:42:16', '2019-11-09 09:24:20');
INSERT INTO `st_login_log` VALUES (49, 'admin', '2019-08-26 11:59:42', '登录成功', 1, 1, 1, '2019-08-26 11:59:42', '2019-11-09 09:24:20');
INSERT INTO `st_login_log` VALUES (50, 'admin', '2019-08-28 03:27:06', '登录成功', 1, 1, 1, '2019-08-28 03:27:06', '2019-11-09 09:24:20');
INSERT INTO `st_login_log` VALUES (51, 'admin', '2019-08-30 06:05:28', '登录成功', 1, 1, 1, '2019-08-30 06:05:28', '2019-11-09 09:24:20');
INSERT INTO `st_login_log` VALUES (52, 'admin', '2019-09-06 02:17:43', '登录成功', 1, 1, 1, '2019-09-06 02:17:43', '2019-11-09 09:24:20');
INSERT INTO `st_login_log` VALUES (53, 'admin', '2019-09-06 12:01:25', '登录成功', 1, 1, 1, '2019-09-06 12:01:25', '2019-11-09 09:24:20');
INSERT INTO `st_login_log` VALUES (54, 'admin', '2019-09-06 12:02:53', '登录成功', 1, 1, 1, '2019-09-06 12:02:53', '2019-11-09 09:24:20');
INSERT INTO `st_login_log` VALUES (55, 'admin', '2019-09-07 12:49:07', '登录成功', 1, 1, 1, '2019-09-07 12:49:07', '2019-11-09 09:24:20');
INSERT INTO `st_login_log` VALUES (56, 'admin', '2019-09-07 12:54:26', '登录成功', 1, 1, 1, '2019-09-07 12:54:26', '2019-11-09 09:24:20');
INSERT INTO `st_login_log` VALUES (57, 'admin', '2019-09-07 12:55:38', '登录成功', 1, 1, 1, '2019-09-07 12:55:38', '2019-11-09 09:24:21');
INSERT INTO `st_login_log` VALUES (58, 'admin', '2019-09-07 12:56:42', '登录成功', 1, 1, 1, '2019-09-07 12:56:42', '2019-11-09 09:24:21');
INSERT INTO `st_login_log` VALUES (59, 'admin', '2019-09-07 12:59:28', '登录成功', 1, 1, 1, '2019-09-07 12:59:28', '2019-11-09 09:24:21');
INSERT INTO `st_login_log` VALUES (60, 'admin', '2019-09-07 13:05:07', '登录成功', 1, 1, 1, '2019-09-07 13:05:07', '2019-11-09 09:24:21');
INSERT INTO `st_login_log` VALUES (61, 'admin', '2019-09-07 13:06:19', '登录成功', 1, 1, 1, '2019-09-07 13:06:19', '2019-11-09 09:24:21');
INSERT INTO `st_login_log` VALUES (62, 'admin', '2019-09-07 13:08:12', '登录成功', 1, 1, 1, '2019-09-07 13:08:12', '2019-11-09 09:24:21');
INSERT INTO `st_login_log` VALUES (63, 'admin', '2019-09-07 13:10:11', '登录成功', 1, 1, 1, '2019-09-07 13:10:11', '2019-11-09 09:24:21');
INSERT INTO `st_login_log` VALUES (64, 'admin', '2019-09-07 13:12:38', '登录成功', 1, 1, 1, '2019-09-07 13:12:38', '2019-11-09 09:24:21');
INSERT INTO `st_login_log` VALUES (65, 'admin', '2019-09-07 13:26:07', '登录成功', 1, 1, 1, '2019-09-07 13:26:07', '2019-11-09 09:24:21');
INSERT INTO `st_login_log` VALUES (66, 'admin', '2019-09-09 02:32:46', '登录成功', 1, 1, 1, '2019-09-09 02:32:46', '2019-11-09 09:24:21');
INSERT INTO `st_login_log` VALUES (67, 'admin', '2019-09-09 06:42:40', '登录成功', 1, 1, 1, '2019-09-09 06:42:40', '2019-11-09 09:24:21');
INSERT INTO `st_login_log` VALUES (68, 'admin', '2019-09-10 03:27:06', '登录成功', 1, 1, 1, '2019-09-10 03:27:06', '2019-11-09 09:24:21');
INSERT INTO `st_login_log` VALUES (69, 'admin', '2019-09-10 12:09:47', '登录成功', 1, 1, 1, '2019-09-10 12:09:47', '2019-11-09 09:24:21');
INSERT INTO `st_login_log` VALUES (70, 'admin', '2019-09-11 02:10:38', '登录成功', 1, 1, 1, '2019-09-11 02:10:38', '2019-11-09 09:24:21');
INSERT INTO `st_login_log` VALUES (71, 'admin', '2019-09-12 01:39:31', '登录成功', 1, 1, 1, '2019-09-12 01:39:31', '2019-11-09 09:24:21');
INSERT INTO `st_login_log` VALUES (72, 'admin', '2019-09-12 02:02:23', '登录成功', 1, 1, 1, '2019-09-12 02:02:23', '2019-11-09 09:24:21');
INSERT INTO `st_login_log` VALUES (73, 'admin', '2019-09-12 07:58:35', '登录成功', 1, 1, 1, '2019-09-12 07:58:35', '2019-11-09 09:24:21');
INSERT INTO `st_login_log` VALUES (74, 'admin', '2019-09-12 11:25:01', '登录成功', 1, 1, 1, '2019-09-12 11:25:01', '2019-11-09 09:24:21');
INSERT INTO `st_login_log` VALUES (75, 'admin', '2019-09-16 06:15:41', '登录成功', 1, 1, 1, '2019-09-16 06:15:41', '2019-11-09 09:24:21');
INSERT INTO `st_login_log` VALUES (76, 'admin', '2019-09-16 07:03:26', '登录成功', 1, 1, 1, '2019-09-16 07:03:26', '2019-11-09 09:24:21');
INSERT INTO `st_login_log` VALUES (77, 'admin', '2019-09-17 02:09:06', '登录成功', 1, 1, 1, '2019-09-17 02:09:06', '2019-11-09 09:24:21');
INSERT INTO `st_login_log` VALUES (78, 'admin', '2019-09-17 10:09:14', '登录成功', 1, 1, 1, '2019-09-17 10:09:14', '2019-11-09 09:24:21');
INSERT INTO `st_login_log` VALUES (79, 'admin', '2019-09-18 06:26:24', '登录成功', 1, 1, 1, '2019-09-18 06:26:24', '2019-11-09 09:24:21');
INSERT INTO `st_login_log` VALUES (80, 'admin', '2019-09-19 05:57:54', '登录成功', 1, 1, 1, '2019-09-19 05:57:54', '2019-11-09 09:24:21');
INSERT INTO `st_login_log` VALUES (81, 'admin', '2019-09-20 02:49:42', '登录成功', 1, 1, 1, '2019-09-20 02:49:42', '2019-11-09 09:24:21');
INSERT INTO `st_login_log` VALUES (82, 'admin', '2019-09-24 02:06:37', '登录成功', 1, 1, 1, '2019-09-24 02:06:37', '2019-11-09 09:24:21');
INSERT INTO `st_login_log` VALUES (83, 'admin', '2019-09-25 06:08:16', '登录成功', 1, 1, 1, '2019-09-25 06:08:16', '2019-11-09 09:24:21');
INSERT INTO `st_login_log` VALUES (84, 'admin', '2019-09-25 06:22:56', '登录成功', 1, 1, 1, '2019-09-25 06:22:56', '2019-11-09 09:24:21');
INSERT INTO `st_login_log` VALUES (85, 'admin', '2019-09-25 07:18:07', '登录成功', 1, 1, 1, '2019-09-25 07:18:07', '2019-11-09 09:24:21');
INSERT INTO `st_login_log` VALUES (86, 'admin', '2019-09-25 07:18:25', '登录成功', 1, 1, 1, '2019-09-25 07:18:25', '2019-11-09 09:24:21');
INSERT INTO `st_login_log` VALUES (87, 'admin', '2019-10-10 03:47:37', '登录成功', 1, 1, 1, '2019-10-10 03:47:37', '2019-11-09 09:24:21');
INSERT INTO `st_login_log` VALUES (88, 'admin', '2019-10-10 03:50:35', '登录成功', 1, 1, 1, '2019-10-10 03:50:35', '2019-11-09 09:24:21');
INSERT INTO `st_login_log` VALUES (89, 'admin', '2019-10-10 03:50:45', '登录成功', 1, 1, 1, '2019-10-10 03:50:45', '2019-11-09 09:24:21');
INSERT INTO `st_login_log` VALUES (90, 'admin', '2019-10-11 15:21:04', '登录成功', 1, 1, 1, '2019-10-11 15:21:04', '2019-11-09 09:24:21');
INSERT INTO `st_login_log` VALUES (91, 'admin', '2019-10-11 15:21:18', '登录成功', 1, 1, 1, '2019-10-11 15:21:18', '2019-11-09 09:24:22');
INSERT INTO `st_login_log` VALUES (92, 'admin', '2019-10-11 15:21:35', '登录成功', 1, 1, 1, '2019-10-11 15:21:35', '2019-11-09 09:24:22');
INSERT INTO `st_login_log` VALUES (93, 'admin', '2019-10-11 15:27:23', '登录成功', 1, 1, 1, '2019-10-11 15:27:23', '2019-11-09 09:24:22');
INSERT INTO `st_login_log` VALUES (94, 'admin', '2019-10-11 15:33:18', '登录成功', 1, 1, 1, '2019-10-11 15:33:18', '2019-11-09 09:24:22');
INSERT INTO `st_login_log` VALUES (95, 'admin', '2019-10-11 15:33:40', '登录成功', 1, 1, 1, '2019-10-11 15:33:40', '2019-11-09 09:24:22');
INSERT INTO `st_login_log` VALUES (96, 'admin', '2019-10-11 15:55:54', '登录成功', 1, 1, 1, '2019-10-11 15:55:54', '2019-11-09 09:24:22');
INSERT INTO `st_login_log` VALUES (97, 'admin', '2019-10-11 16:01:04', '登录成功', 1, 1, 1, '2019-10-11 16:01:04', '2019-11-09 09:24:22');
INSERT INTO `st_login_log` VALUES (98, 'admin', '2019-10-11 16:04:28', '登录成功', 1, 1, 1, '2019-10-11 16:04:28', '2019-11-09 09:24:22');
INSERT INTO `st_login_log` VALUES (99, 'admin', '2019-10-11 16:07:13', '登录成功', 1, 1, 1, '2019-10-11 16:07:13', '2019-11-09 09:24:22');
INSERT INTO `st_login_log` VALUES (100, 'admin', '2019-10-11 16:17:56', '登录成功', 1, 1, 1, '2019-10-11 16:17:56', '2019-11-09 09:24:22');
INSERT INTO `st_login_log` VALUES (101, 'admin', '2019-10-11 16:20:34', '登录成功', 1, 1, 1, '2019-10-11 16:20:34', '2019-11-09 09:24:22');
INSERT INTO `st_login_log` VALUES (102, 'admin', '2019-10-16 21:40:06', '登录成功', 1, 1, 1, '2019-10-16 21:40:06', '2019-11-09 09:24:22');
INSERT INTO `st_login_log` VALUES (103, 'admin', '2019-10-16 21:53:28', '登录成功', 1, 1, 1, '2019-10-16 21:53:28', '2019-11-09 09:24:22');
INSERT INTO `st_login_log` VALUES (104, 'admin', '2019-10-16 21:54:02', '登录成功', 1, 1, 1, '2019-10-16 21:54:02', '2019-11-09 09:24:22');
INSERT INTO `st_login_log` VALUES (105, 'admin', '2019-10-16 21:54:34', '登录成功', 1, 1, 1, '2019-10-16 21:54:34', '2019-11-09 09:24:22');
INSERT INTO `st_login_log` VALUES (106, 'admin', '2019-10-16 21:55:16', '登录成功', 1, 1, 1, '2019-10-16 21:55:16', '2019-11-09 09:24:22');
INSERT INTO `st_login_log` VALUES (107, 'admin', '2019-10-16 21:55:28', '登录成功', 1, 1, 1, '2019-10-16 21:55:28', '2019-11-09 09:24:22');
INSERT INTO `st_login_log` VALUES (108, 'admin', '2019-10-16 21:56:06', '登录成功', 1, 1, 1, '2019-10-16 21:56:06', '2019-11-09 09:24:22');
INSERT INTO `st_login_log` VALUES (109, 'admin', '2019-10-16 21:56:19', '登录成功', 1, 1, 1, '2019-10-16 21:56:19', '2019-11-09 09:24:22');
INSERT INTO `st_login_log` VALUES (110, 'admin', '2019-10-16 21:56:28', '登录成功', 1, 1, 1, '2019-10-16 21:56:28', '2019-11-09 09:24:22');
INSERT INTO `st_login_log` VALUES (111, 'admin', '2019-10-16 21:56:44', '登录成功', 1, 1, 1, '2019-10-16 21:56:44', '2019-11-09 09:24:22');
INSERT INTO `st_login_log` VALUES (112, 'admin', '2019-10-16 21:57:19', '登录成功', 1, 1, 1, '2019-10-16 21:57:19', '2019-11-09 09:24:22');
INSERT INTO `st_login_log` VALUES (113, 'admin', '2019-10-16 21:57:31', '登录成功', 1, 1, 1, '2019-10-16 21:57:31', '2019-11-09 09:24:22');
INSERT INTO `st_login_log` VALUES (114, 'admin', '2019-10-16 22:00:20', '登录成功', 1, 1, 1, '2019-10-16 22:00:20', '2019-11-09 09:24:22');
INSERT INTO `st_login_log` VALUES (115, 'admin', '2019-10-16 22:01:14', '登录成功', 1, 1, 1, '2019-10-16 22:01:14', '2019-11-09 09:24:22');
INSERT INTO `st_login_log` VALUES (116, 'admin', '2019-10-16 22:02:51', '登录成功', 1, 1, 1, '2019-10-16 22:02:51', '2019-11-09 09:24:22');
INSERT INTO `st_login_log` VALUES (117, 'admin', '2019-10-16 22:03:21', '登录成功', 1, 1, 1, '2019-10-16 22:03:21', '2019-11-09 09:24:22');
INSERT INTO `st_login_log` VALUES (118, 'admin', '2019-10-16 22:03:29', '登录成功', 1, 1, 1, '2019-10-16 22:03:29', '2019-11-09 09:24:22');
INSERT INTO `st_login_log` VALUES (119, 'admin', '2019-10-16 22:03:49', '登录成功', 1, 1, 1, '2019-10-16 22:03:49', '2019-11-09 09:24:22');
INSERT INTO `st_login_log` VALUES (120, 'admin', '2019-10-16 22:04:09', '登录成功', 1, 1, 1, '2019-10-16 22:04:09', '2019-11-09 09:24:22');
INSERT INTO `st_login_log` VALUES (121, 'admin', '2019-10-16 22:04:15', '登录成功', 1, 1, 1, '2019-10-16 22:04:15', '2019-11-09 09:24:22');
INSERT INTO `st_login_log` VALUES (122, 'admin', '2019-10-16 22:05:02', '登录成功', 1, 1, 1, '2019-10-16 22:05:02', '2019-11-09 09:24:22');
INSERT INTO `st_login_log` VALUES (123, 'admin', '2019-10-16 22:11:29', '登录成功', 1, 1, 1, '2019-10-16 22:11:29', '2019-11-09 09:24:22');
INSERT INTO `st_login_log` VALUES (124, 'admin', '2019-10-16 22:13:13', '登录成功', 1, 1, 1, '2019-10-16 22:13:13', '2019-11-09 09:24:22');
INSERT INTO `st_login_log` VALUES (125, 'admin', '2019-10-16 22:15:17', '登录成功', 1, 1, 1, '2019-10-16 22:15:17', '2019-11-09 09:24:22');
INSERT INTO `st_login_log` VALUES (126, 'admin', '2019-10-16 23:31:45', '登录成功', 1, 1, 1, '2019-10-16 23:31:45', '2019-11-09 09:24:22');
INSERT INTO `st_login_log` VALUES (127, 'admin', '2019-10-16 23:35:40', '登录成功', 1, 1, 1, '2019-10-16 23:35:40', '2019-11-09 09:24:22');
INSERT INTO `st_login_log` VALUES (128, 'admin', '2019-10-17 18:26:16', '登录成功', 1, 1, 1, '2019-10-17 18:26:16', '2019-11-09 09:24:22');
INSERT INTO `st_login_log` VALUES (129, 'admin', '2019-10-17 19:00:49', '登录成功', 1, 1, 1, '2019-10-17 19:00:49', '2019-11-09 09:24:22');
INSERT INTO `st_login_log` VALUES (130, 'admin', '2019-10-17 19:06:27', '登录成功', 1, 1, 1, '2019-10-17 19:06:27', '2019-11-09 09:24:22');
INSERT INTO `st_login_log` VALUES (131, 'admin', '2019-10-17 19:07:44', '登录成功', 1, 1, 1, '2019-10-17 19:07:44', '2019-11-09 09:24:23');
INSERT INTO `st_login_log` VALUES (132, 'admin', '2019-10-17 19:08:32', '登录成功', 1, 1, 1, '2019-10-17 19:08:32', '2019-11-09 09:24:23');
INSERT INTO `st_login_log` VALUES (133, 'admin', '2019-10-17 19:09:57', '登录成功', 1, 1, 1, '2019-10-17 19:09:57', '2019-11-09 09:24:23');
INSERT INTO `st_login_log` VALUES (134, 'admin', '2019-10-19 21:31:26', '登录成功', 1, 1, 1, '2019-10-19 21:31:26', '2019-11-09 09:24:23');
INSERT INTO `st_login_log` VALUES (135, 'admin', '2019-10-19 21:52:54', '登录成功', 1, 1, 1, '2019-10-19 21:52:54', '2019-11-09 09:24:23');
INSERT INTO `st_login_log` VALUES (136, 'admin', '2019-10-19 21:52:59', '登录成功', 1, 1, 1, '2019-10-19 21:52:59', '2019-11-09 09:24:23');
INSERT INTO `st_login_log` VALUES (137, 'admin', '2019-10-19 21:57:15', '登录成功', 1, 1, 1, '2019-10-19 21:57:15', '2019-11-09 09:24:23');
INSERT INTO `st_login_log` VALUES (138, 'admin', '2019-10-19 22:02:52', '登录成功', 1, 1, 1, '2019-10-19 22:02:52', '2019-11-09 09:24:23');
INSERT INTO `st_login_log` VALUES (139, 'admin', '2019-10-19 23:14:03', '登录成功', 1, 1, 1, '2019-10-19 23:14:03', '2019-11-09 09:24:23');
INSERT INTO `st_login_log` VALUES (140, 'admin', '2019-10-19 23:14:04', '登录成功', 1, 1, 1, '2019-10-19 23:14:04', '2019-11-09 09:24:23');
INSERT INTO `st_login_log` VALUES (141, 'admin', '2019-10-19 23:14:05', '登录成功', 1, 1, 1, '2019-10-19 23:14:05', '2019-11-09 09:24:23');
INSERT INTO `st_login_log` VALUES (142, 'admin', '2019-10-19 23:14:06', '登录成功', 1, 1, 1, '2019-10-19 23:14:06', '2019-11-09 09:24:23');
INSERT INTO `st_login_log` VALUES (143, 'admin', '2019-10-19 23:14:06', '登录成功', 1, 1, 1, '2019-10-19 23:14:06', '2019-11-09 09:24:23');
INSERT INTO `st_login_log` VALUES (144, 'admin', '2019-10-19 23:14:06', '登录成功', 1, 1, 1, '2019-10-19 23:14:06', '2019-11-09 09:24:23');
INSERT INTO `st_login_log` VALUES (145, 'admin', '2019-10-19 23:14:07', '登录成功', 1, 1, 1, '2019-10-19 23:14:07', '2019-11-09 09:24:23');
INSERT INTO `st_login_log` VALUES (146, 'admin', '2019-10-19 23:14:07', '登录成功', 1, 1, 1, '2019-10-19 23:14:07', '2019-11-09 09:24:23');
INSERT INTO `st_login_log` VALUES (147, 'admin', '2019-10-19 23:14:07', '登录成功', 1, 1, 1, '2019-10-19 23:14:07', '2019-11-09 09:24:23');
INSERT INTO `st_login_log` VALUES (148, 'admin', '2019-10-19 23:14:07', '登录成功', 1, 1, 1, '2019-10-19 23:14:07', '2019-11-09 09:24:23');
INSERT INTO `st_login_log` VALUES (149, 'admin', '2019-10-19 23:14:26', '登录成功', 1, 1, 1, '2019-10-19 23:14:26', '2019-11-09 09:24:23');
INSERT INTO `st_login_log` VALUES (150, 'admin', '2019-10-19 23:17:24', '登录成功', 1, 1, 1, '2019-10-19 23:17:24', '2019-11-09 09:24:23');
INSERT INTO `st_login_log` VALUES (151, 'admin', '2019-10-19 23:31:29', '登录成功', 1, 1, 1, '2019-10-19 23:31:29', '2019-11-09 09:24:23');
INSERT INTO `st_login_log` VALUES (152, 'admin', '2019-10-19 23:32:39', '登录成功', 1, 1, 1, '2019-10-19 23:32:39', '2019-11-09 09:24:23');
INSERT INTO `st_login_log` VALUES (153, 'admin', '2019-10-19 23:33:45', '登录成功', 1, 1, 1, '2019-10-19 23:33:45', '2019-11-09 09:24:23');
INSERT INTO `st_login_log` VALUES (154, 'admin', '2019-10-19 23:34:59', '登录成功', 1, 1, 1, '2019-10-19 23:34:59', '2019-11-09 09:24:23');
INSERT INTO `st_login_log` VALUES (155, 'admin', '2019-10-19 23:35:09', '登录成功', 1, 1, 1, '2019-10-19 23:35:09', '2019-11-09 09:24:23');
INSERT INTO `st_login_log` VALUES (156, 'admin', '2019-10-19 23:35:57', '登录成功', 1, 1, 1, '2019-10-19 23:35:57', '2019-11-09 09:24:23');
INSERT INTO `st_login_log` VALUES (157, 'admin', '2019-10-19 23:45:22', '登录成功', 1, 1, 1, '2019-10-19 23:45:22', '2019-11-09 09:24:23');
INSERT INTO `st_login_log` VALUES (158, 'admin', '2019-10-19 23:47:25', '登录成功', 1, 1, 1, '2019-10-19 23:47:25', '2019-11-09 09:24:23');
INSERT INTO `st_login_log` VALUES (159, 'admin', '2019-10-19 23:47:42', '登录成功', 1, 1, 1, '2019-10-19 23:47:42', '2019-11-09 09:24:23');
INSERT INTO `st_login_log` VALUES (160, 'admin', '2019-10-19 23:48:30', '登录成功', 1, 1, 1, '2019-10-19 23:48:30', '2019-11-09 09:24:23');
INSERT INTO `st_login_log` VALUES (161, 'admin', '2019-10-19 23:55:42', '登录成功', 1, 1, 1, '2019-10-19 23:55:42', '2019-11-09 09:24:23');
INSERT INTO `st_login_log` VALUES (162, 'admin', '2019-10-20 21:47:39', '登录成功', 1, 1, 1, '2019-10-20 21:47:39', '2019-11-09 09:24:23');
INSERT INTO `st_login_log` VALUES (163, 'admin', '2019-10-20 21:50:13', '登录成功', 1, 1, 1, '2019-10-20 21:50:13', '2019-11-09 09:24:23');
INSERT INTO `st_login_log` VALUES (164, 'admin', '2019-10-20 21:54:44', '登录成功', 1, 1, 1, '2019-10-20 21:54:44', '2019-11-09 09:24:23');
INSERT INTO `st_login_log` VALUES (165, 'admin', '2019-10-20 21:55:20', '登录成功', 1, 1, 1, '2019-10-20 21:55:20', '2019-11-09 09:24:23');
INSERT INTO `st_login_log` VALUES (166, 'admin', '2019-10-20 21:55:54', '登录成功', 1, 1, 1, '2019-10-20 21:55:54', '2019-11-09 09:24:23');
INSERT INTO `st_login_log` VALUES (167, 'admin', '2019-10-20 21:56:59', '登录成功', 1, 1, 1, '2019-10-20 21:56:59', '2019-11-09 09:24:23');
INSERT INTO `st_login_log` VALUES (168, 'admin', '2019-10-20 21:59:35', '登录成功', 1, 1, 1, '2019-10-20 21:59:35', '2019-11-09 09:24:23');
INSERT INTO `st_login_log` VALUES (169, 'admin', '2019-10-20 22:01:48', '登录成功', 1, 1, 1, '2019-10-20 22:01:48', '2019-11-09 09:24:23');
INSERT INTO `st_login_log` VALUES (170, 'admin', '2019-10-20 22:11:04', '登录成功', 1, 1, 1, '2019-10-20 22:11:04', '2019-11-09 09:24:24');
INSERT INTO `st_login_log` VALUES (171, 'admin', '2019-10-20 22:12:53', '登录成功', 1, 1, 1, '2019-10-20 22:12:53', '2019-11-09 09:24:24');
INSERT INTO `st_login_log` VALUES (172, 'admin', '2019-10-20 22:13:50', '登录成功', 1, 1, 1, '2019-10-20 22:13:50', '2019-11-09 09:24:24');
INSERT INTO `st_login_log` VALUES (173, 'admin', '2019-10-20 22:14:14', '登录成功', 1, 1, 1, '2019-10-20 22:14:14', '2019-11-09 09:24:24');
INSERT INTO `st_login_log` VALUES (174, 'admin', '2019-10-20 22:19:43', '登录成功', 1, 1, 1, '2019-10-20 22:19:43', '2019-11-09 09:24:24');
INSERT INTO `st_login_log` VALUES (175, 'admin', '2019-10-20 22:19:58', '登录成功', 1, 1, 1, '2019-10-20 22:19:58', '2019-11-09 09:24:24');
INSERT INTO `st_login_log` VALUES (176, 'admin', '2019-10-20 22:20:54', '登录成功', 1, 1, 1, '2019-10-20 22:20:54', '2019-11-09 09:24:24');
INSERT INTO `st_login_log` VALUES (177, 'admin', '2019-10-20 22:21:12', '登录成功', 1, 1, 1, '2019-10-20 22:21:12', '2019-11-09 09:24:24');
INSERT INTO `st_login_log` VALUES (178, 'admin', '2019-10-20 22:27:38', '登录成功', 1, 1, 1, '2019-10-20 22:27:38', '2019-11-09 09:24:24');
INSERT INTO `st_login_log` VALUES (179, 'admin', '2019-10-20 22:27:41', '登录成功', 1, 1, 1, '2019-10-20 22:27:41', '2019-11-09 09:24:24');
INSERT INTO `st_login_log` VALUES (180, 'admin', '2019-10-20 22:28:48', '登录成功', 1, 1, 1, '2019-10-20 22:28:48', '2019-11-09 09:24:24');
INSERT INTO `st_login_log` VALUES (181, 'admin', '2019-10-20 22:28:59', '登录成功', 1, 1, 1, '2019-10-20 22:28:59', '2019-11-09 09:24:24');
INSERT INTO `st_login_log` VALUES (182, 'admin', '2019-10-20 22:31:54', '登录成功', 1, 1, 1, '2019-10-20 22:31:54', '2019-11-09 09:24:24');
INSERT INTO `st_login_log` VALUES (183, 'admin', '2019-10-20 22:32:25', '登录成功', 1, 1, 1, '2019-10-20 22:32:25', '2019-11-09 09:24:24');
INSERT INTO `st_login_log` VALUES (184, 'admin', '2019-10-20 22:32:38', '登录成功', 1, 1, 1, '2019-10-20 22:32:38', '2019-11-09 09:24:24');
INSERT INTO `st_login_log` VALUES (185, 'admin', '2019-10-20 22:33:08', '登录成功', 1, 1, 1, '2019-10-20 22:33:08', '2019-11-09 09:24:24');
INSERT INTO `st_login_log` VALUES (186, 'admin', '2019-10-20 22:33:23', '登录成功', 1, 1, 1, '2019-10-20 22:33:23', '2019-11-09 09:24:24');
INSERT INTO `st_login_log` VALUES (187, 'admin', '2019-10-20 22:38:01', '登录成功', 1, 1, 1, '2019-10-20 22:38:01', '2019-11-09 09:24:24');
INSERT INTO `st_login_log` VALUES (188, 'admin', '2019-10-20 22:47:19', '登录成功', 1, 1, 1, '2019-10-20 22:47:19', '2019-11-09 09:24:24');
INSERT INTO `st_login_log` VALUES (189, 'admin', '2019-10-20 22:48:56', '登录成功', 1, 1, 1, '2019-10-20 22:48:56', '2019-11-09 09:24:24');
INSERT INTO `st_login_log` VALUES (190, 'admin', '2019-10-20 22:49:16', '登录成功', 1, 1, 1, '2019-10-20 22:49:16', '2019-11-09 09:24:24');
INSERT INTO `st_login_log` VALUES (191, 'admin', '2019-10-20 22:50:40', '登录成功', 1, 1, 1, '2019-10-20 22:50:40', '2019-11-09 09:24:24');
INSERT INTO `st_login_log` VALUES (192, 'admin', '2019-10-20 22:50:50', '登录成功', 1, 1, 1, '2019-10-20 22:50:50', '2019-11-09 09:24:24');
INSERT INTO `st_login_log` VALUES (193, 'admin', '2019-10-20 22:54:08', '登录成功', 1, 1, 1, '2019-10-20 22:54:08', '2019-11-09 09:24:24');
INSERT INTO `st_login_log` VALUES (194, 'admin', '2019-10-20 22:55:01', '登录成功', 1, 1, 1, '2019-10-20 22:55:01', '2019-11-09 09:24:24');
INSERT INTO `st_login_log` VALUES (195, 'admin', '2019-10-20 22:55:40', '登录成功', 1, 1, 1, '2019-10-20 22:55:40', '2019-11-09 09:24:24');
INSERT INTO `st_login_log` VALUES (196, 'admin', '2019-10-20 22:56:05', '登录成功', 1, 1, 1, '2019-10-20 22:56:05', '2019-11-09 09:24:24');
INSERT INTO `st_login_log` VALUES (197, 'admin', '2019-10-20 22:56:59', '登录成功', 1, 1, 1, '2019-10-20 22:56:59', '2019-11-09 09:24:24');
INSERT INTO `st_login_log` VALUES (198, 'admin', '2019-10-20 22:57:09', '登录成功', 1, 1, 1, '2019-10-20 22:57:09', '2019-11-09 09:24:24');
INSERT INTO `st_login_log` VALUES (199, 'admin', '2019-10-20 22:57:45', '登录成功', 1, 1, 1, '2019-10-20 22:57:45', '2019-11-09 09:24:24');
INSERT INTO `st_login_log` VALUES (200, 'admin', '2019-10-20 22:58:54', '登录成功', 1, 1, 1, '2019-10-20 22:58:54', '2019-11-09 09:24:24');
INSERT INTO `st_login_log` VALUES (201, 'admin', '2019-10-20 22:59:26', '登录成功', 1, 1, 1, '2019-10-20 22:59:26', '2019-11-09 09:24:24');
INSERT INTO `st_login_log` VALUES (202, 'admin', '2019-10-20 23:00:48', '登录成功', 1, 1, 1, '2019-10-20 23:00:48', '2019-11-09 09:24:24');
INSERT INTO `st_login_log` VALUES (203, 'admin', '2019-10-20 23:01:25', '登录成功', 1, 1, 1, '2019-10-20 23:01:25', '2019-11-09 09:24:24');
INSERT INTO `st_login_log` VALUES (204, 'admin', '2019-10-20 23:02:51', '登录成功', 1, 1, 1, '2019-10-20 23:02:51', '2019-11-09 09:24:24');
INSERT INTO `st_login_log` VALUES (205, 'admin', '2019-10-20 23:04:03', '登录成功', 1, 1, 1, '2019-10-20 23:04:03', '2019-11-09 09:24:24');
INSERT INTO `st_login_log` VALUES (206, 'admin', '2019-10-20 23:04:46', '登录成功', 1, 1, 1, '2019-10-20 23:04:46', '2019-11-09 09:24:24');
INSERT INTO `st_login_log` VALUES (207, 'admin', '2019-10-20 23:05:37', '登录成功', 1, 1, 1, '2019-10-20 23:05:37', '2019-11-09 09:24:24');
INSERT INTO `st_login_log` VALUES (208, 'admin', '2019-10-20 23:08:43', '登录成功', 1, 1, 1, '2019-10-20 23:08:43', '2019-11-09 09:24:24');
INSERT INTO `st_login_log` VALUES (209, 'admin', '2019-11-09 10:30:29', '登录成功', 1, 1, 1, '2019-11-09 10:30:29', '2019-11-09 10:30:26');
INSERT INTO `st_login_log` VALUES (210, 'admin', '2019-11-09 11:14:37', '登录成功', 1, 1, 1, '2019-11-09 11:14:37', '2019-11-09 11:14:35');
INSERT INTO `st_login_log` VALUES (211, 'admin', '2019-11-09 11:33:23', '登录成功', 1, 1, 1, '2019-11-09 11:33:23', '2019-11-09 11:33:20');
INSERT INTO `st_login_log` VALUES (212, 'admin', '2019-11-09 11:34:31', '登录成功', 1, 1, 1, '2019-11-09 11:34:31', '2019-11-09 11:34:28');
INSERT INTO `st_login_log` VALUES (213, 'admin', '2019-11-09 11:34:39', '登录成功', 1, 1, 1, '2019-11-09 11:34:39', '2019-11-09 11:34:37');
INSERT INTO `st_login_log` VALUES (214, 'admin', '2019-11-09 12:00:49', '登录成功', 1, 1, 1, '2019-11-09 12:00:49', '2019-11-09 12:00:46');
INSERT INTO `st_login_log` VALUES (215, 'admin', '2019-11-09 12:01:51', '登录成功', 1, 1, 1, '2019-11-09 12:01:51', '2019-11-09 12:01:49');
INSERT INTO `st_login_log` VALUES (216, 'admin', '2019-11-09 12:02:08', '登录成功', 1, 1, 1, '2019-11-09 12:02:08', '2019-11-09 12:02:06');
INSERT INTO `st_login_log` VALUES (217, 'admin', '2019-11-09 12:02:45', '登录成功', 1, 1, 1, '2019-11-09 12:02:45', '2019-11-09 12:02:42');
INSERT INTO `st_login_log` VALUES (218, 'admin', '2019-11-09 12:03:10', '登录成功', 1, 1, 1, '2019-11-09 12:03:10', '2019-11-09 12:03:08');
INSERT INTO `st_login_log` VALUES (219, 'admin', '2019-11-09 12:05:39', '登录成功', 1, 1, 1, '2019-11-09 12:05:39', '2019-11-09 12:05:36');
INSERT INTO `st_login_log` VALUES (220, 'admin', '2019-11-09 15:20:25', '登录成功', 1, 1, 1, '2019-11-09 15:20:25', '2019-11-09 15:20:22');
INSERT INTO `st_login_log` VALUES (221, 'admin', '2019-11-09 17:18:46', '登陆成功!', 1, 1, 1, '2019-11-09 17:18:46', '2019-11-09 17:18:43');
INSERT INTO `st_login_log` VALUES (222, 'admin', '2019-11-09 17:19:54', '登陆成功!', 1, 1, 1, '2019-11-09 17:19:54', '2019-11-09 17:19:51');
INSERT INTO `st_login_log` VALUES (223, 'admin', '2019-11-09 17:19:57', '登陆成功!', 1, 1, 1, '2019-11-09 17:19:57', '2019-11-09 17:19:54');
INSERT INTO `st_login_log` VALUES (224, 'admin', '2019-11-09 17:19:59', '登陆成功!', 1, 1, 1, '2019-11-09 17:19:59', '2019-11-09 17:19:56');
INSERT INTO `st_login_log` VALUES (225, 'admin', '2019-11-09 17:21:52', '登陆成功!', 1, 1, 1, '2019-11-09 17:21:52', '2019-11-09 17:21:49');
INSERT INTO `st_login_log` VALUES (226, 'admin', '2019-11-09 17:23:10', '登陆成功!', 1, 1, 1, '2019-11-09 17:23:10', '2019-11-09 17:23:07');
INSERT INTO `st_login_log` VALUES (227, 'admin', '2019-11-09 17:23:12', '登陆成功!', 1, 1, 1, '2019-11-09 17:23:12', '2019-11-09 17:23:09');
INSERT INTO `st_login_log` VALUES (228, 'admin', '2019-11-09 17:42:40', '登陆成功!', 1, 1, 1, '2019-11-09 17:42:40', '2019-11-09 17:42:37');

-- ----------------------------
-- Table structure for st_resource
-- ----------------------------
DROP TABLE IF EXISTS `st_resource`;
CREATE TABLE `st_resource`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '菜单名称',
  `full_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '菜单编号路径',
  `icon_class` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '图标样式类',
  `show_order` int(11) NULL DEFAULT NULL COMMENT '排序',
  `url` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '链接',
  `component` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '页面路径',
  `authority_id` bigint(20) NULL DEFAULT NULL COMMENT '权限ID',
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '父id',
  `resource_desc` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '菜单描述',
  `yn_flag` char(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '有效标志',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '创建人',
  `editor` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '修改人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of st_resource
-- ----------------------------
INSERT INTO `st_resource` VALUES (1, '系统管理', '0', 'nested', 30, '/sysmgr', '/layout/Layout', 1, 0, NULL, '1', 'admin', 'admin', '2019-07-12 09:20:40', '2019-09-25 07:25:47');
INSERT INTO `st_resource` VALUES (2, '用户管理', '0-1', 'user', 1, 'user', '/sysmgr/user/index', 7, 1, NULL, '1', 'admin', 'admin', '2019-07-12 09:20:40', '2019-08-19 11:04:38');
INSERT INTO `st_resource` VALUES (3, '角色管理', '0-1', 'peoples', 3, 'role', '/sysmgr/role/index', 10, 1, NULL, '1', 'admin', 'admin', '2019-07-12 09:20:40', '2019-08-19 11:04:40');
INSERT INTO `st_resource` VALUES (4, '菜单管理', '0-1', 'list', 4, 'menu', '/sysmgr/menu/index', 13, 1, NULL, '1', 'admin', 'admin', '2019-07-12 09:20:40', '2019-08-19 11:04:42');
INSERT INTO `st_resource` VALUES (5, '权限管理', '0-1', 'password', 5, 'authority', '/sysmgr/authority/index', 16, 1, NULL, '1', 'admin', 'admin', '2019-07-12 09:20:40', '2019-08-19 11:04:43');
INSERT INTO `st_resource` VALUES (6, '基础信息', '0', 'nested', 20, '/baseinfo', '/layout/Layout', 1, 0, NULL, '1', 'admin', 'admin', '2019-07-12 09:20:40', '2019-09-25 07:25:42');
INSERT INTO `st_resource` VALUES (7, '字典管理', '0-6', 'component', 1, 'dict', '/baseinfo/dict', 1, 6, NULL, '1', 'admin', 'admin', '2019-07-12 09:54:30', '2019-07-16 09:17:55');
INSERT INTO `st_resource` VALUES (8, '附件管理', '0-1', 'zip', 7, 'att', '/sysmgr/att/index', 39, 1, NULL, '1', 'admin', 'admin', '2019-07-12 10:25:37', '2019-09-25 06:18:31');
INSERT INTO `st_resource` VALUES (9, '登陆日志', '0-1', 'people', 6, 'loginlog', '/sysmgr/loginlog/index', 31, 1, NULL, '1', 'admin', 'admin', '2019-07-12 10:35:56', '2019-07-26 11:36:45');
INSERT INTO `st_resource` VALUES (10, '系统备份', '0-1', 'clipboard', 9, 'backup', '/sysmgr/backup/index', 44, 1, NULL, '1', 'admin', 'admin', '2019-07-12 10:49:11', '2019-09-25 06:18:49');
INSERT INTO `st_resource` VALUES (11, '系统日志', '0-1', 'documentation', 8, 'syslog', '/sysmgr/syslog/index', 42, 1, NULL, '1', 'admin', 'admin', '2019-07-12 11:58:59', '2019-09-25 06:18:41');
INSERT INTO `st_resource` VALUES (12, '定时任务', '0-1', 'guide', 20, 'schedulejob', '/sysmgr/schedulejob/index', 47, 1, NULL, '1', 'admin', 'admin', '2019-08-19 03:02:50', '2019-09-25 06:18:55');
INSERT INTO `st_resource` VALUES (13, '个人空间', '0', 'nested', 10, '/tools', '/layout/Layout', 1, 0, '', '1', 'admin', 'admin', '2019-09-25 06:28:53', '2019-09-25 14:30:48');
INSERT INTO `st_resource` VALUES (14, '待办事项', '0-13', 'table', NULL, 'todolist', '/tools/todolist', 53, 13, NULL, '1', 'admin', 'admin', '2019-09-25 07:09:20', '2019-09-25 07:17:58');

-- ----------------------------
-- Table structure for st_role
-- ----------------------------
DROP TABLE IF EXISTS `st_role`;
CREATE TABLE `st_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `role_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '角色名称',
  `desc` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '角色描述',
  `flag` tinyint(1) NULL DEFAULT NULL COMMENT '有效标志',
  `creator` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `editor` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of st_role
-- ----------------------------
INSERT INTO `st_role` VALUES (1, '管理员', NULL, 1, NULL, 1, '2018-12-29 11:23:15', '2019-11-09 09:08:31');
INSERT INTO `st_role` VALUES (3, '供应商', NULL, 1, NULL, NULL, NULL, '2019-01-04 11:00:37');
INSERT INTO `st_role` VALUES (4, '游客', NULL, 1, NULL, NULL, NULL, '2019-01-04 11:00:37');

-- ----------------------------
-- Table structure for st_role_authority
-- ----------------------------
DROP TABLE IF EXISTS `st_role_authority`;
CREATE TABLE `st_role_authority`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `authority_id` bigint(20) NOT NULL COMMENT '权限ID',
  `flag` tinyint(1) NULL DEFAULT NULL COMMENT '有效标志',
  `creator` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `editor` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 333 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '角色权限关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of st_role_authority
-- ----------------------------
INSERT INTO `st_role_authority` VALUES (219, 1, 0, 0, 1, NULL, '2019-07-26 03:15:48', '2019-11-09 09:09:34');
INSERT INTO `st_role_authority` VALUES (220, 1, 1, 0, 1, NULL, '2019-07-26 03:15:48', '2019-11-09 09:09:34');
INSERT INTO `st_role_authority` VALUES (221, 1, 2, 0, 1, NULL, '2019-07-26 03:15:48', '2019-11-09 09:09:34');
INSERT INTO `st_role_authority` VALUES (222, 1, 3, 0, 1, NULL, '2019-07-26 03:15:48', '2019-11-09 09:09:34');
INSERT INTO `st_role_authority` VALUES (223, 1, 4, 0, 1, NULL, '2019-07-26 03:15:48', '2019-11-09 09:09:34');
INSERT INTO `st_role_authority` VALUES (224, 1, 5, 0, 1, NULL, '2019-07-26 03:15:48', '2019-11-09 09:09:34');
INSERT INTO `st_role_authority` VALUES (225, 1, 6, 0, 1, NULL, '2019-07-26 03:15:48', '2019-11-09 09:09:34');
INSERT INTO `st_role_authority` VALUES (226, 1, 7, 0, 1, NULL, '2019-07-26 03:15:48', '2019-11-09 09:09:34');
INSERT INTO `st_role_authority` VALUES (227, 1, 8, 0, 1, NULL, '2019-07-26 03:15:48', '2019-11-09 09:09:34');
INSERT INTO `st_role_authority` VALUES (228, 1, 9, 0, 1, NULL, '2019-07-26 03:15:48', '2019-11-09 09:09:34');
INSERT INTO `st_role_authority` VALUES (229, 1, 10, 0, 1, NULL, '2019-07-26 03:15:48', '2019-11-09 09:09:34');
INSERT INTO `st_role_authority` VALUES (230, 1, 11, 0, 1, NULL, '2019-07-26 03:15:48', '2019-11-09 09:09:34');
INSERT INTO `st_role_authority` VALUES (231, 1, 12, 0, 1, NULL, '2019-07-26 03:15:48', '2019-11-09 09:09:34');
INSERT INTO `st_role_authority` VALUES (232, 1, 13, 0, 1, NULL, '2019-07-26 03:15:48', '2019-11-09 09:09:34');
INSERT INTO `st_role_authority` VALUES (233, 1, 14, 0, 1, NULL, '2019-07-26 03:15:48', '2019-11-09 09:09:34');
INSERT INTO `st_role_authority` VALUES (234, 1, 15, 0, 1, NULL, '2019-07-26 03:15:48', '2019-11-09 09:09:34');
INSERT INTO `st_role_authority` VALUES (235, 1, 16, 0, 1, NULL, '2019-07-26 03:15:48', '2019-11-09 09:09:34');
INSERT INTO `st_role_authority` VALUES (236, 1, 17, 0, 1, NULL, '2019-07-26 03:15:48', '2019-11-09 09:09:35');
INSERT INTO `st_role_authority` VALUES (237, 1, 18, 0, 1, NULL, '2019-07-26 03:15:48', '2019-11-09 09:09:35');
INSERT INTO `st_role_authority` VALUES (238, 1, 30, 0, 1, NULL, '2019-07-26 03:15:48', '2019-11-09 09:09:35');
INSERT INTO `st_role_authority` VALUES (239, 1, 31, 0, 1, NULL, '2019-07-26 03:15:48', '2019-11-09 09:09:35');
INSERT INTO `st_role_authority` VALUES (240, 1, 32, 0, 1, NULL, '2019-07-26 03:15:48', '2019-11-09 09:09:35');
INSERT INTO `st_role_authority` VALUES (241, 1, 33, 0, 1, NULL, '2019-07-26 03:15:48', '2019-11-09 09:09:35');
INSERT INTO `st_role_authority` VALUES (242, 1, 34, 0, 1, NULL, '2019-07-26 03:15:48', '2019-11-09 09:09:35');
INSERT INTO `st_role_authority` VALUES (243, 1, 35, 0, 1, NULL, '2019-07-26 03:15:48', '2019-11-09 09:09:35');
INSERT INTO `st_role_authority` VALUES (244, 1, 36, 0, 1, NULL, '2019-07-26 03:15:48', '2019-11-09 09:09:35');
INSERT INTO `st_role_authority` VALUES (245, 1, 37, 0, 1, NULL, '2019-07-26 03:15:48', '2019-11-09 09:09:35');
INSERT INTO `st_role_authority` VALUES (246, 3, 0, 1, 1, 1, '2019-07-26 05:54:06', '2019-11-09 09:09:50');
INSERT INTO `st_role_authority` VALUES (247, 3, 1, 1, 1, 1, '2019-07-26 05:54:06', '2019-11-09 09:09:50');
INSERT INTO `st_role_authority` VALUES (248, 1, 0, 0, 1, NULL, '2019-09-25 06:22:42', '2019-11-09 09:09:35');
INSERT INTO `st_role_authority` VALUES (249, 1, 1, 0, 1, NULL, '2019-09-25 06:22:42', '2019-11-09 09:09:35');
INSERT INTO `st_role_authority` VALUES (250, 1, 2, 0, 1, NULL, '2019-09-25 06:22:42', '2019-11-09 09:09:35');
INSERT INTO `st_role_authority` VALUES (251, 1, 3, 0, 1, NULL, '2019-09-25 06:22:42', '2019-11-09 09:09:35');
INSERT INTO `st_role_authority` VALUES (252, 1, 4, 0, 1, NULL, '2019-09-25 06:22:42', '2019-11-09 09:09:35');
INSERT INTO `st_role_authority` VALUES (253, 1, 5, 0, 1, NULL, '2019-09-25 06:22:42', '2019-11-09 09:09:35');
INSERT INTO `st_role_authority` VALUES (254, 1, 6, 0, 1, NULL, '2019-09-25 06:22:42', '2019-11-09 09:09:35');
INSERT INTO `st_role_authority` VALUES (255, 1, 7, 0, 1, NULL, '2019-09-25 06:22:42', '2019-11-09 09:09:35');
INSERT INTO `st_role_authority` VALUES (256, 1, 8, 0, 1, NULL, '2019-09-25 06:22:42', '2019-11-09 09:09:35');
INSERT INTO `st_role_authority` VALUES (257, 1, 9, 0, 1, NULL, '2019-09-25 06:22:42', '2019-11-09 09:09:35');
INSERT INTO `st_role_authority` VALUES (258, 1, 10, 0, 1, NULL, '2019-09-25 06:22:42', '2019-11-09 09:09:35');
INSERT INTO `st_role_authority` VALUES (259, 1, 11, 0, 1, NULL, '2019-09-25 06:22:42', '2019-11-09 09:09:35');
INSERT INTO `st_role_authority` VALUES (260, 1, 12, 0, 1, NULL, '2019-09-25 06:22:42', '2019-11-09 09:09:35');
INSERT INTO `st_role_authority` VALUES (261, 1, 13, 0, 1, NULL, '2019-09-25 06:22:42', '2019-11-09 09:09:35');
INSERT INTO `st_role_authority` VALUES (262, 1, 14, 0, 1, NULL, '2019-09-25 06:22:42', '2019-11-09 09:09:35');
INSERT INTO `st_role_authority` VALUES (263, 1, 15, 0, 1, NULL, '2019-09-25 06:22:42', '2019-11-09 09:09:35');
INSERT INTO `st_role_authority` VALUES (264, 1, 16, 0, 1, NULL, '2019-09-25 06:22:42', '2019-11-09 09:09:35');
INSERT INTO `st_role_authority` VALUES (265, 1, 17, 0, 1, NULL, '2019-09-25 06:22:42', '2019-11-09 09:09:35');
INSERT INTO `st_role_authority` VALUES (266, 1, 18, 0, 1, NULL, '2019-09-25 06:22:42', '2019-11-09 09:09:35');
INSERT INTO `st_role_authority` VALUES (267, 1, 30, 0, 1, NULL, '2019-09-25 06:22:42', '2019-11-09 09:09:35');
INSERT INTO `st_role_authority` VALUES (268, 1, 31, 0, 1, NULL, '2019-09-25 06:22:42', '2019-11-09 09:09:35');
INSERT INTO `st_role_authority` VALUES (269, 1, 32, 0, 1, NULL, '2019-09-25 06:22:42', '2019-11-09 09:09:35');
INSERT INTO `st_role_authority` VALUES (270, 1, 33, 0, 1, NULL, '2019-09-25 06:22:42', '2019-11-09 09:09:36');
INSERT INTO `st_role_authority` VALUES (271, 1, 34, 0, 1, NULL, '2019-09-25 06:22:42', '2019-11-09 09:09:36');
INSERT INTO `st_role_authority` VALUES (272, 1, 35, 0, 1, NULL, '2019-09-25 06:22:42', '2019-11-09 09:09:36');
INSERT INTO `st_role_authority` VALUES (273, 1, 36, 0, 1, NULL, '2019-09-25 06:22:42', '2019-11-09 09:09:36');
INSERT INTO `st_role_authority` VALUES (274, 1, 37, 0, 1, NULL, '2019-09-25 06:22:42', '2019-11-09 09:09:36');
INSERT INTO `st_role_authority` VALUES (275, 1, 38, 0, 1, NULL, '2019-09-25 06:22:42', '2019-11-09 09:09:36');
INSERT INTO `st_role_authority` VALUES (276, 1, 39, 0, 1, NULL, '2019-09-25 06:22:42', '2019-11-09 09:09:36');
INSERT INTO `st_role_authority` VALUES (277, 1, 40, 0, 1, NULL, '2019-09-25 06:22:42', '2019-11-09 09:09:36');
INSERT INTO `st_role_authority` VALUES (278, 1, 41, 0, 1, NULL, '2019-09-25 06:22:42', '2019-11-09 09:09:36');
INSERT INTO `st_role_authority` VALUES (279, 1, 42, 0, 1, NULL, '2019-09-25 06:22:42', '2019-11-09 09:09:36');
INSERT INTO `st_role_authority` VALUES (280, 1, 43, 0, 1, NULL, '2019-09-25 06:22:42', '2019-11-09 09:09:36');
INSERT INTO `st_role_authority` VALUES (281, 1, 44, 0, 1, NULL, '2019-09-25 06:22:42', '2019-11-09 09:09:36');
INSERT INTO `st_role_authority` VALUES (282, 1, 45, 0, 1, NULL, '2019-09-25 06:22:42', '2019-11-09 09:09:36');
INSERT INTO `st_role_authority` VALUES (283, 1, 46, 0, 1, NULL, '2019-09-25 06:22:42', '2019-11-09 09:09:36');
INSERT INTO `st_role_authority` VALUES (284, 1, 47, 0, 1, NULL, '2019-09-25 06:22:42', '2019-11-09 09:09:36');
INSERT INTO `st_role_authority` VALUES (285, 1, 48, 0, 1, NULL, '2019-09-25 06:22:42', '2019-11-09 09:09:36');
INSERT INTO `st_role_authority` VALUES (286, 1, 49, 0, 1, NULL, '2019-09-25 06:22:42', '2019-11-09 09:09:36');
INSERT INTO `st_role_authority` VALUES (287, 1, 50, 0, 1, NULL, '2019-09-25 06:22:42', '2019-11-09 09:09:36');
INSERT INTO `st_role_authority` VALUES (288, 1, 0, 1, 1, 1, '2019-09-25 07:18:14', '2019-11-09 09:09:50');
INSERT INTO `st_role_authority` VALUES (289, 1, 1, 1, 1, 1, '2019-09-25 07:18:14', '2019-11-09 09:09:50');
INSERT INTO `st_role_authority` VALUES (290, 1, 2, 1, 1, 1, '2019-09-25 07:18:14', '2019-11-09 09:09:50');
INSERT INTO `st_role_authority` VALUES (291, 1, 3, 1, 1, 1, '2019-09-25 07:18:14', '2019-11-09 09:09:50');
INSERT INTO `st_role_authority` VALUES (292, 1, 4, 1, 1, 1, '2019-09-25 07:18:14', '2019-11-09 09:09:50');
INSERT INTO `st_role_authority` VALUES (293, 1, 5, 1, 1, 1, '2019-09-25 07:18:14', '2019-11-09 09:09:50');
INSERT INTO `st_role_authority` VALUES (294, 1, 6, 1, 1, 1, '2019-09-25 07:18:14', '2019-11-09 09:09:50');
INSERT INTO `st_role_authority` VALUES (295, 1, 7, 1, 1, 1, '2019-09-25 07:18:14', '2019-11-09 09:09:50');
INSERT INTO `st_role_authority` VALUES (296, 1, 8, 1, 1, 1, '2019-09-25 07:18:14', '2019-11-09 09:09:50');
INSERT INTO `st_role_authority` VALUES (297, 1, 9, 1, 1, 1, '2019-09-25 07:18:14', '2019-11-09 09:09:50');
INSERT INTO `st_role_authority` VALUES (298, 1, 10, 1, 1, 1, '2019-09-25 07:18:14', '2019-11-09 09:09:50');
INSERT INTO `st_role_authority` VALUES (299, 1, 11, 1, 1, 1, '2019-09-25 07:18:14', '2019-11-09 09:09:50');
INSERT INTO `st_role_authority` VALUES (300, 1, 12, 1, 1, 1, '2019-09-25 07:18:14', '2019-11-09 09:09:51');
INSERT INTO `st_role_authority` VALUES (301, 1, 13, 1, 1, 1, '2019-09-25 07:18:14', '2019-11-09 09:09:51');
INSERT INTO `st_role_authority` VALUES (302, 1, 14, 1, 1, 1, '2019-09-25 07:18:14', '2019-11-09 09:09:51');
INSERT INTO `st_role_authority` VALUES (303, 1, 15, 1, 1, 1, '2019-09-25 07:18:14', '2019-11-09 09:09:51');
INSERT INTO `st_role_authority` VALUES (304, 1, 16, 1, 1, 1, '2019-09-25 07:18:14', '2019-11-09 09:09:51');
INSERT INTO `st_role_authority` VALUES (305, 1, 17, 1, 1, 1, '2019-09-25 07:18:14', '2019-11-09 09:09:51');
INSERT INTO `st_role_authority` VALUES (306, 1, 18, 1, 1, 1, '2019-09-25 07:18:14', '2019-11-09 09:09:51');
INSERT INTO `st_role_authority` VALUES (307, 1, 30, 1, 1, 1, '2019-09-25 07:18:14', '2019-11-09 09:09:51');
INSERT INTO `st_role_authority` VALUES (308, 1, 31, 1, 1, 1, '2019-09-25 07:18:14', '2019-11-09 09:09:51');
INSERT INTO `st_role_authority` VALUES (309, 1, 32, 1, 1, 1, '2019-09-25 07:18:14', '2019-11-09 09:09:51');
INSERT INTO `st_role_authority` VALUES (310, 1, 33, 1, 1, 1, '2019-09-25 07:18:14', '2019-11-09 09:09:51');
INSERT INTO `st_role_authority` VALUES (311, 1, 34, 1, 1, 1, '2019-09-25 07:18:14', '2019-11-09 09:09:51');
INSERT INTO `st_role_authority` VALUES (312, 1, 35, 1, 1, 1, '2019-09-25 07:18:14', '2019-11-09 09:09:51');
INSERT INTO `st_role_authority` VALUES (313, 1, 36, 1, 1, 1, '2019-09-25 07:18:14', '2019-11-09 09:09:51');
INSERT INTO `st_role_authority` VALUES (314, 1, 37, 1, 1, 1, '2019-09-25 07:18:14', '2019-11-09 09:09:51');
INSERT INTO `st_role_authority` VALUES (315, 1, 38, 1, 1, 1, '2019-09-25 07:18:14', '2019-11-09 09:09:51');
INSERT INTO `st_role_authority` VALUES (316, 1, 39, 1, 1, 1, '2019-09-25 07:18:14', '2019-11-09 09:09:51');
INSERT INTO `st_role_authority` VALUES (317, 1, 40, 1, 1, 1, '2019-09-25 07:18:14', '2019-11-09 09:09:51');
INSERT INTO `st_role_authority` VALUES (318, 1, 41, 1, 1, 1, '2019-09-25 07:18:14', '2019-11-09 09:09:51');
INSERT INTO `st_role_authority` VALUES (319, 1, 42, 1, 1, 1, '2019-09-25 07:18:14', '2019-11-09 09:09:51');
INSERT INTO `st_role_authority` VALUES (320, 1, 43, 1, 1, 1, '2019-09-25 07:18:14', '2019-11-09 09:09:51');
INSERT INTO `st_role_authority` VALUES (321, 1, 44, 1, 1, 1, '2019-09-25 07:18:14', '2019-11-09 09:09:51');
INSERT INTO `st_role_authority` VALUES (322, 1, 45, 1, 1, 1, '2019-09-25 07:18:14', '2019-11-09 09:09:51');
INSERT INTO `st_role_authority` VALUES (323, 1, 46, 1, 1, 1, '2019-09-25 07:18:14', '2019-11-09 09:09:51');
INSERT INTO `st_role_authority` VALUES (324, 1, 47, 1, 1, 1, '2019-09-25 07:18:14', '2019-11-09 09:09:51');
INSERT INTO `st_role_authority` VALUES (325, 1, 48, 1, 1, 1, '2019-09-25 07:18:14', '2019-11-09 09:09:51');
INSERT INTO `st_role_authority` VALUES (326, 1, 49, 1, 1, 1, '2019-09-25 07:18:14', '2019-11-09 09:09:51');
INSERT INTO `st_role_authority` VALUES (327, 1, 50, 1, 1, 1, '2019-09-25 07:18:14', '2019-11-09 09:09:51');
INSERT INTO `st_role_authority` VALUES (328, 1, 51, 1, 1, 1, '2019-09-25 07:18:14', '2019-11-09 09:09:51');
INSERT INTO `st_role_authority` VALUES (329, 1, 52, 1, 1, 1, '2019-09-25 07:18:14', '2019-11-09 09:09:51');
INSERT INTO `st_role_authority` VALUES (330, 1, 53, 1, 1, 1, '2019-09-25 07:18:14', '2019-11-09 09:09:51');
INSERT INTO `st_role_authority` VALUES (331, 1, 54, 1, 1, 1, '2019-09-25 07:18:14', '2019-11-09 09:09:51');
INSERT INTO `st_role_authority` VALUES (332, 1, 55, 1, 1, 1, '2019-09-25 07:18:14', '2019-11-09 09:09:51');

-- ----------------------------
-- Table structure for st_user
-- ----------------------------
DROP TABLE IF EXISTS `st_user`;
CREATE TABLE `st_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `account` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '账号',
  `username` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '姓名',
  `pwd` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '密码',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户头像,存储路径',
  `email` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '手机号码',
  `last_pwd_modified_time` datetime(0) NULL DEFAULT NULL COMMENT '上次修改密码时间',
  `state` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态',
  `flag` tinyint(1) NULL DEFAULT NULL COMMENT '有效标志',
  `creator` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `editor` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `flag_erp` tinyint(1) NULL DEFAULT NULL COMMENT 'ERP账号标识',
  `token_key` int(10) NOT NULL DEFAULT 0 COMMENT 'token key',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of st_user
-- ----------------------------
INSERT INTO `st_user` VALUES (1, 'admin', '管理员', 'f0c5872ba98bf6cf25317b84f6815313', NULL, 'admin@admin.admin', NULL, '2019-01-17 15:05:04', 1, 1, NULL, 1, '2018-12-29 10:40:07', '2019-11-08 17:46:53', 0, 1571309001);
INSERT INTO `st_user` VALUES (2, 'sunnj01', 'sunnj01', '0ace0a5fc5802a43305c8ae547a81afe', NULL, 'aa@aa.aa2', NULL, '2019-01-17 15:25:42', 0, 1, NULL, NULL, NULL, '2019-10-17 18:39:24', 0, 0);
INSERT INTO `st_user` VALUES (4, 'test', '测试', '0ace0a5fc5802a43305c8ae547a81afe', NULL, 'test@aa.aa', NULL, '2019-01-17 15:23:28', 1, 1, NULL, NULL, NULL, '2019-10-17 18:39:24', 0, 0);

-- ----------------------------
-- Table structure for st_user_role
-- ----------------------------
DROP TABLE IF EXISTS `st_user_role`;
CREATE TABLE `st_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `flag` tinyint(1) NULL DEFAULT NULL COMMENT '有效标志',
  `creator` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `editor` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  `created_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户角色关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of st_user_role
-- ----------------------------
INSERT INTO `st_user_role` VALUES (3, 1, 1, 1, 1, 1, '2019-01-04 10:38:31', '2019-11-09 09:08:50');

SET FOREIGN_KEY_CHECKS = 1;
