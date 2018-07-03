/*
Navicat MySQL Data Transfer

Source Server         : javaweb
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : web

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-07-03 09:21:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '部门名称',
  `parent_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '上级部门id',
  `level` varchar(200) NOT NULL DEFAULT '' COMMENT '部门层级',
  `seq` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '部门在当前层级下的顺序，从小到大',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `operator` varchar(20) NOT NULL COMMENT '操作人',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `operate_ip` varchar(20) NOT NULL DEFAULT '' COMMENT '操作人ip',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('11', '产品部', '0', '0', '1', '', 'admin', '2018-06-13 15:59:36', '127.0.0.1');
INSERT INTO `sys_dept` VALUES ('12', '开发部', '0', '0', '3', '', 'admin', '2018-06-16 15:29:14', '127.0.0.1');
INSERT INTO `sys_dept` VALUES ('13', '销售部', '0', '0', '4', '', 'admin', '2018-06-16 15:29:12', '127.0.0.1');
INSERT INTO `sys_dept` VALUES ('14', '前端-update', '12', '0.12', '2', '前端-update', 'admin', '2018-06-16 16:20:33', '127.0.0.1');
INSERT INTO `sys_dept` VALUES ('15', '后端', '12', '0.12', '1', '', 'admin', '2018-06-16 17:11:19', '127.0.0.1');
INSERT INTO `sys_dept` VALUES ('17', 'FLASH', '14', '0.12.14', '2', '', 'admin', '2018-06-16 17:01:58', '127.0.0.1');
INSERT INTO `sys_dept` VALUES ('18', 'JAVA', '15', '0.12.15', '1', '', 'admin', '2018-06-16 17:11:19', '127.0.0.1');
INSERT INTO `sys_dept` VALUES ('19', 'C++', '15', '0.12.15', '1', '', 'admin', '2018-06-16 17:11:19', '127.0.0.1');
INSERT INTO `sys_dept` VALUES ('20', '财务部', '0', '0', '2', '', 'admin', '2018-06-16 15:29:08', '127.0.0.1');
INSERT INTO `sys_dept` VALUES ('21', 'UI', '14', '0.12.14', '1', '', 'admin', '2018-06-16 17:01:58', '127.0.0.1');
INSERT INTO `sys_dept` VALUES ('29', 'a1', '0', '0', '11', 'a1', 'admin', '2018-06-16 16:18:17', '127.0.0.1');
