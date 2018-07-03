/*
Navicat MySQL Data Transfer

Source Server         : javaweb
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : web

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-07-03 09:22:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名称',
  `telephone` varchar(11) NOT NULL DEFAULT '',
  `email` varchar(50) NOT NULL DEFAULT '',
  `password` varchar(50) NOT NULL,
  `remark` varchar(200) NOT NULL DEFAULT '',
  `deptId` int(10) unsigned NOT NULL,
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '0-有效，1-冻结,2-删除',
  `operator` varchar(20) CHARACTER SET utf8mb4 NOT NULL DEFAULT '',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `operate_ip` varchar(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '15018775835', 'admin@qq.com', 'E10ADC3949BA59ABBE56E057F20F883E', '', '12', '0', 'zhaohuan', '2018-06-15 23:58:41', '127.0.0.1');
INSERT INTO `sys_user` VALUES ('7', 'zhaohuan', '11111111111', 'zhaohuan@qq.com', 'E10ADC3949BA59ABBE56E057F20F883E', '1101006260@qq.com', '18', '0', 'zhaohuan', '2018-06-16 00:37:32', '127.0.0.1');
INSERT INTO `sys_user` VALUES ('8', 'xiaoli', '22222222222', 'xiaoli@qq.com', 'E10ADC3949BA59ABBE56E057F20F883E', '', '18', '0', 'admin', '2018-06-16 17:13:07', '127.0.0.1');
