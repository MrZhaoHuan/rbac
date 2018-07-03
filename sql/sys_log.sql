/*
Navicat MySQL Data Transfer

Source Server         : javaweb
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : web

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-07-03 09:21:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type` char(1) NOT NULL DEFAULT '0',
  `targetId` int(10) unsigned NOT NULL,
  `oldValue` text,
  `newValue` text,
  `operator` varchar(20) NOT NULL DEFAULT '',
  `operate_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `operate_ip` varchar(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('21', '1', '29', '', '{\"id\":29,\"level\":\"0\",\"name\":\"a1\",\"operateIp\":\"127.0.0.1\",\"operateTime\":1529136390768,\"operator\":\"admin\",\"parentId\":0,\"remark\":\"\",\"seq\":11}', 'admin', '2018-06-16 16:06:31', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('24', '1', '29', '{\"id\":29,\"level\":\"0\",\"name\":\"a1\",\"operateIp\":\"127.0.0.1\",\"operateTime\":1529137097000,\"operator\":\"admin\",\"parentId\":0,\"remark\":\"a1\",\"seq\":11}', '{\"id\":29,\"level\":\"0\",\"name\":\"a1-update\",\"operateIp\":\"127.0.0.1\",\"operateTime\":1529137135271,\"operator\":\"admin\",\"parentId\":0,\"remark\":\"a1-update\",\"seq\":11}', 'admin', '2018-06-16 16:18:55', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('25', '1', '14', '{\"id\":14,\"level\":\"0.12\",\"name\":\"前端\",\"operateIp\":\"127.0.0.1\",\"operateTime\":1528876825000,\"operator\":\"admin\",\"parentId\":12,\"remark\":\"\",\"seq\":2}', '{\"id\":14,\"level\":\"0.12\",\"name\":\"前端-update\",\"operateIp\":\"127.0.0.1\",\"operateTime\":1529137233113,\"operator\":\"admin\",\"parentId\":12,\"remark\":\"前端-update\",\"seq\":2}', 'admin', '2018-06-16 16:20:33', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('28', '1', '14', '{\"id\":14,\"level\":\"0.12\",\"name\":\"前端-update\",\"operateIp\":\"127.0.0.1\",\"operateTime\":1529137233000,\"operator\":\"admin\",\"parentId\":12,\"remark\":\"前端-update\",\"seq\":2}', '{\"id\":14,\"level\":\"0.20\",\"name\":\"前端-update\",\"operateIp\":\"127.0.0.1\",\"operateTime\":1529137275012,\"operator\":\"admin\",\"parentId\":20,\"remark\":\"前端-update\",\"seq\":2}', 'admin', '2018-06-16 16:21:15', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('29', '1', '29', '{\"id\":29,\"level\":\"0\",\"name\":\"a1-update\",\"operateIp\":\"127.0.0.1\",\"operateTime\":1529137135000,\"operator\":\"admin\",\"parentId\":0,\"remark\":\"a1-update\",\"seq\":11}', '{\"id\":29,\"level\":\"0\",\"name\":\"a1\",\"operateIp\":\"127.0.0.1\",\"operateTime\":1529137097000,\"operator\":\"admin\",\"parentId\":0,\"remark\":\"a1\",\"seq\":11}', 'admin', '2018-06-16 17:00:58', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('30', '1', '14', '{\"id\":14,\"level\":\"0.20\",\"name\":\"前端-update\",\"operateIp\":\"127.0.0.1\",\"operateTime\":1529137275000,\"operator\":\"admin\",\"parentId\":20,\"remark\":\"前端-update\",\"seq\":2}', '{\"id\":14,\"level\":\"0.12\",\"name\":\"前端-update\",\"operateIp\":\"127.0.0.1\",\"operateTime\":1529137233000,\"operator\":\"admin\",\"parentId\":12,\"remark\":\"前端-update\",\"seq\":2}', 'admin', '2018-06-16 17:01:59', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('31', '1', '15', '{\"id\":15,\"level\":\"0.11\",\"name\":\"后端\",\"operateIp\":\"127.0.0.1\",\"operateTime\":1529136306000,\"operator\":\"admin\",\"parentId\":11,\"remark\":\"\",\"seq\":1}', '{\"id\":15,\"level\":\"0.20\",\"name\":\"后端\",\"operateIp\":\"127.0.0.1\",\"operateTime\":1529140242679,\"operator\":\"admin\",\"parentId\":20,\"remark\":\"\",\"seq\":1}', 'admin', '2018-06-16 17:10:43', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('32', '1', '15', '{\"id\":15,\"level\":\"0.20\",\"name\":\"后端\",\"operateIp\":\"127.0.0.1\",\"operateTime\":1529140243000,\"operator\":\"admin\",\"parentId\":20,\"remark\":\"\",\"seq\":1}', '{\"id\":15,\"level\":\"0.11\",\"name\":\"后端\",\"operateIp\":\"127.0.0.1\",\"operateTime\":1529136306000,\"operator\":\"admin\",\"parentId\":11,\"remark\":\"\",\"seq\":1}', 'admin', '2018-06-16 17:10:53', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('33', '1', '19', '{\"id\":19,\"level\":\"0.11.15\",\"name\":\"C++\",\"operateIp\":\"127.0.0.1\",\"operateTime\":1529140252000,\"operator\":\"admin\",\"parentId\":15,\"remark\":\"\",\"seq\":1}', '{\"id\":19,\"level\":\"0.20\",\"name\":\"C++\",\"operateIp\":\"127.0.0.1\",\"operateTime\":1529140265533,\"operator\":\"admin\",\"parentId\":20,\"remark\":\"\",\"seq\":1}', 'admin', '2018-06-16 17:11:06', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('34', '1', '19', '{\"id\":19,\"level\":\"0.20\",\"name\":\"C++\",\"operateIp\":\"127.0.0.1\",\"operateTime\":1529140266000,\"operator\":\"admin\",\"parentId\":20,\"remark\":\"\",\"seq\":1}', '{\"id\":19,\"level\":\"0.11.15\",\"name\":\"C++\",\"operateIp\":\"127.0.0.1\",\"operateTime\":1529140252000,\"operator\":\"admin\",\"parentId\":15,\"remark\":\"\",\"seq\":1}', 'admin', '2018-06-16 17:11:10', '127.0.0.1');
INSERT INTO `sys_log` VALUES ('35', '1', '15', '{\"id\":15,\"level\":\"0.11\",\"name\":\"后端\",\"operateIp\":\"127.0.0.1\",\"operateTime\":1529136306000,\"operator\":\"admin\",\"parentId\":11,\"remark\":\"\",\"seq\":1}', '{\"id\":15,\"level\":\"0.12\",\"name\":\"后端\",\"operateIp\":\"127.0.0.1\",\"operateTime\":1529140279109,\"operator\":\"admin\",\"parentId\":12,\"remark\":\"\",\"seq\":1}', 'admin', '2018-06-16 17:11:19', '127.0.0.1');
