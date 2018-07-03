/*
Navicat MySQL Data Transfer

Source Server         : javaweb
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : web

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-07-03 09:22:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_perm_module
-- ----------------------------
DROP TABLE IF EXISTS `sys_perm_module`;
CREATE TABLE `sys_perm_module` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `parent_id` int(10) unsigned NOT NULL DEFAULT '0',
  `level` varchar(200) NOT NULL DEFAULT '',
  `seq` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '0-有效，1-冻结',
  `remark` varchar(200) NOT NULL DEFAULT '',
  `operator` varchar(20) NOT NULL DEFAULT '',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `operate_ip` varchar(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_perm_module
-- ----------------------------
INSERT INTO `sys_perm_module` VALUES ('7', '产品管理', '0', '0', '1', '0', '', 'admin', '2018-06-14 12:49:33', '127.0.0.1');
INSERT INTO `sys_perm_module` VALUES ('8', '订单管理', '0', '0', '2', '0', '', 'admin', '2018-06-14 12:49:44', '127.0.0.1');
INSERT INTO `sys_perm_module` VALUES ('9', '支付管理', '0', '0', '3', '0', '', 'admin', '2018-06-14 12:49:56', '127.0.0.1');
INSERT INTO `sys_perm_module` VALUES ('10', '下架产品管理', '7', '0.7', '1', '0', '', 'admin', '2018-06-14 12:50:48', '127.0.0.1');
INSERT INTO `sys_perm_module` VALUES ('11', '运维管理', '0', '0', '4', '0', '', 'admin', '2018-06-14 12:52:06', '127.0.0.1');
INSERT INTO `sys_perm_module` VALUES ('12', '菜单管理', '0', '0', '1', '0', '', 'admin', '2018-06-15 20:47:21', '127.0.0.1');
