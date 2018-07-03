/*
Navicat MySQL Data Transfer

Source Server         : javaweb
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : web

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-07-03 09:21:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_perm
-- ----------------------------
DROP TABLE IF EXISTS `sys_perm`;
CREATE TABLE `sys_perm` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `code` varchar(50) NOT NULL DEFAULT '',
  `permModuleId` int(10) unsigned NOT NULL,
  `url` varchar(255) NOT NULL DEFAULT '',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '0-有效，1-冻结',
  `seq` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `remark` varchar(200) NOT NULL DEFAULT '',
  `operator` varchar(20) NOT NULL DEFAULT '',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `operate_ip` varchar(20) NOT NULL DEFAULT '',
  `type` char(1) NOT NULL DEFAULT '0' COMMENT '0-菜单，1-按钮，2-其他',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_perm
-- ----------------------------
INSERT INTO `sys_perm` VALUES ('15', '用户管理', 'ec43f9bb-79fc-46fb-9a04-b61d7416245f', '12', '/rbac/dept/listPage.page', '0', '1', '', 'admin', '2018-06-15 21:29:07', '127.0.0.1', '0');
INSERT INTO `sys_perm` VALUES ('16', '角色管理', '1b5d9bb0-c8e9-4431-8b1c-4e38d31839c6', '12', '/rbac/role/role.page', '0', '2', '', 'admin', '2018-06-15 21:39:27', '127.0.0.1', '0');
INSERT INTO `sys_perm` VALUES ('17', '权限管理', 'c677de3e-7881-474e-8ad8-8f2ea82e88d6', '12', '/rbac/permModule/listPage.page', '0', '3', '', 'admin', '2018-06-15 21:41:40', '127.0.0.1', '0');
INSERT INTO `sys_perm` VALUES ('18', '权限更新记录', 'dc38e7f4-259d-4d38-a8d8-2e570293f716', '12', '/rbac/log/log.page', '0', '4', '', 'admin', '2018-06-15 21:42:26', '127.0.0.1', '0');
