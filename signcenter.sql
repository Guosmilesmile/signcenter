/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50151
Source Host           : localhost:3306
Source Database       : signcenter

Target Server Type    : MYSQL
Target Server Version : 50151
File Encoding         : 65001

Date: 2016-10-27 15:01:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cc_courseclass
-- ----------------------------
DROP TABLE IF EXISTS `cc_courseclass`;
CREATE TABLE `cc_courseclass` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `courseid` int(11) DEFAULT NULL,
  `classname` varchar(50) DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `CC_COURSECLASS` (`courseid`),
  CONSTRAINT `CC_COURSECLASS` FOREIGN KEY (`courseid`) REFERENCES `c_course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cc_courseclass
-- ----------------------------
INSERT INTO `cc_courseclass` VALUES ('6', '4', '不猝死一班？');
INSERT INTO `cc_courseclass` VALUES ('7', '4', '不猝死二班');

-- ----------------------------
-- Table structure for cs_classstudent
-- ----------------------------
DROP TABLE IF EXISTS `cs_classstudent`;
CREATE TABLE `cs_classstudent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `classid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `CU_UNIQUE` (`classid`,`userid`),
  KEY `CU_CLASSSTUENDT` (`userid`),
  CONSTRAINT `CC_CLASS` FOREIGN KEY (`classid`) REFERENCES `cc_courseclass` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `CU_CLASSSTUENDT` FOREIGN KEY (`userid`) REFERENCES `u_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cs_classstudent
-- ----------------------------
INSERT INTO `cs_classstudent` VALUES ('14', '6', '7');
INSERT INTO `cs_classstudent` VALUES ('16', '6', '9');
INSERT INTO `cs_classstudent` VALUES ('15', '7', '8');

-- ----------------------------
-- Table structure for ct_classtime
-- ----------------------------
DROP TABLE IF EXISTS `ct_classtime`;
CREATE TABLE `ct_classtime` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `classid` int(11) DEFAULT NULL,
  `classtime` varchar(50) DEFAULT NULL,
  `indexs` int(2) DEFAULT NULL,
  `count` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `CC_CLASSCLASS` (`classid`),
  CONSTRAINT `CC_CLASSCLASS` FOREIGN KEY (`classid`) REFERENCES `cc_courseclass` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ct_classtime
-- ----------------------------
INSERT INTO `ct_classtime` VALUES ('5', '6', '1_1_3', '1', '16');
INSERT INTO `ct_classtime` VALUES ('6', '6', '2_1_5', '2', '80');
INSERT INTO `ct_classtime` VALUES ('8', '7', '1_2_1', '1', '8');

-- ----------------------------
-- Table structure for c_course
-- ----------------------------
DROP TABLE IF EXISTS `c_course`;
CREATE TABLE `c_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT '',
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UC_USERCOURSE` (`userid`),
  CONSTRAINT `UC_USERCOURSE` FOREIGN KEY (`userid`) REFERENCES `u_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of c_course
-- ----------------------------
INSERT INTO `c_course` VALUES ('4', '如何通宵不猝死', '6');

-- ----------------------------
-- Table structure for s_sign
-- ----------------------------
DROP TABLE IF EXISTS `s_sign`;
CREATE TABLE `s_sign` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `classid` int(11) DEFAULT NULL,
  `timestamp` int(20) DEFAULT NULL,
  `situation` int(2) DEFAULT NULL COMMENT '0-旷课，1-迟到，2-正常签到',
  `indexs` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `US_USER` (`userid`),
  KEY `US_CLASS` (`classid`),
  CONSTRAINT `US_CLASS` FOREIGN KEY (`classid`) REFERENCES `cc_courseclass` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `US_USER` FOREIGN KEY (`userid`) REFERENCES `u_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_sign
-- ----------------------------
INSERT INTO `s_sign` VALUES ('10', '7', '6', '1477482792', '2', '1');
INSERT INTO `s_sign` VALUES ('19', '8', '7', '1477650450', '3', '1');
INSERT INTO `s_sign` VALUES ('20', '9', '6', '1477650450', '1', '1');

-- ----------------------------
-- Table structure for u_user
-- ----------------------------
DROP TABLE IF EXISTS `u_user`;
CREATE TABLE `u_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(25) DEFAULT '',
  `password` varchar(200) DEFAULT '',
  `nickname` varchar(50) DEFAULT '',
  `role` int(2) DEFAULT '1' COMMENT '0-管理员，1-学生，2-老师',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of u_user
-- ----------------------------
INSERT INTO `u_user` VALUES ('1', 'admin', '$2a$10$RtCDFfOKE82JwsO4XOkSk.dyG9098Q.91PLsO8OiqlVtEGeUoo0mC', 'admin', '0');
INSERT INTO `u_user` VALUES ('6', '123456', '$2a$10$VigM.mEg4ggNAYWfwEdD9OHQ5S/k9z0IhF7nvYnnYoqrRebaCc4oK', '王鹭雄', '2');
INSERT INTO `u_user` VALUES ('7', '123456789', '$2a$10$8Zz.oaYXNOASLk8f63WSQuh4WN5Ce2oEtjkKWdUVcQn596iqHHmmq', '韦笑颖', '1');
INSERT INTO `u_user` VALUES ('8', '12345678', '$2a$10$8MklRo3yE56JyrvWi0joCe9hS46eMQUQm4WpFvwjRsFb6qpVhUUFm', '涂蕾', '1');
INSERT INTO `u_user` VALUES ('9', '12345', '$2a$10$OX0ZnwIM20Wb4ttFVmSlG.27qYKNABdZjEKmrLkroOSi48eZpd7xW', '郭煜', '1');
INSERT INTO `u_user` VALUES ('10', '123', '$2a$10$xcyXg.LtBQM.bETmDpy4SuzWDI56xq0oWhn4UiTGnk7U.wdJxZYR2', '123', '0');
