/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50151
Source Host           : localhost:3306
Source Database       : signcenter

Target Server Type    : MYSQL
Target Server Version : 50151
File Encoding         : 65001

Date: 2016-10-24 11:00:10
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cc_courseclass
-- ----------------------------
INSERT INTO `cc_courseclass` VALUES ('1', '1', '卓越班');
INSERT INTO `cc_courseclass` VALUES ('2', '1', '普通班');

-- ----------------------------
-- Table structure for cs_classstudent
-- ----------------------------
DROP TABLE IF EXISTS `cs_classstudent`;
CREATE TABLE `cs_classstudent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `classid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `CC_CLASS` (`classid`),
  KEY `CU_CLASSSTUENDT` (`userid`),
  CONSTRAINT `CC_CLASS` FOREIGN KEY (`classid`) REFERENCES `cc_courseclass` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `CU_CLASSSTUENDT` FOREIGN KEY (`userid`) REFERENCES `u_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cs_classstudent
-- ----------------------------
INSERT INTO `cs_classstudent` VALUES ('1', '1', '1');
INSERT INTO `cs_classstudent` VALUES ('2', '1', '2');
INSERT INTO `cs_classstudent` VALUES ('3', '2', '3');
INSERT INTO `cs_classstudent` VALUES ('4', '1', '3');

-- ----------------------------
-- Table structure for ct_classtime
-- ----------------------------
DROP TABLE IF EXISTS `ct_classtime`;
CREATE TABLE `ct_classtime` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `classid` int(11) DEFAULT NULL,
  `classtime` varchar(50) DEFAULT NULL,
  `index` int(2) DEFAULT NULL,
  `count` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `CC_CLASSCLASS` (`classid`),
  CONSTRAINT `CC_CLASSCLASS` FOREIGN KEY (`classid`) REFERENCES `cc_courseclass` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ct_classtime
-- ----------------------------
INSERT INTO `ct_classtime` VALUES ('1', '1', '1_1_3', '1', '16');
INSERT INTO `ct_classtime` VALUES ('2', '1', '1_1_5', '2', '16');
INSERT INTO `ct_classtime` VALUES ('3', '2', '1_1_7', '1', '16');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of c_course
-- ----------------------------
INSERT INTO `c_course` VALUES ('1', '操作系统', '1');
INSERT INTO `c_course` VALUES ('2', '数据库', '3');

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
  `index` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `US_USER` (`userid`),
  KEY `US_CLASS` (`classid`),
  CONSTRAINT `US_CLASS` FOREIGN KEY (`classid`) REFERENCES `cc_courseclass` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `US_USER` FOREIGN KEY (`userid`) REFERENCES `u_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_sign
-- ----------------------------
INSERT INTO `s_sign` VALUES ('1', '1', '1', '1477275166', '1', '1');
INSERT INTO `s_sign` VALUES ('2', '2', '1', '1477275366', '2', '1');
INSERT INTO `s_sign` VALUES ('3', '3', '2', '1477275166', '2', '1');

-- ----------------------------
-- Table structure for u_user
-- ----------------------------
DROP TABLE IF EXISTS `u_user`;
CREATE TABLE `u_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(25) DEFAULT '',
  `password` varchar(50) DEFAULT '',
  `nickname` varchar(50) DEFAULT '',
  `role` int(2) DEFAULT '1' COMMENT '0-管理员，1-学生，2-老师',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of u_user
-- ----------------------------
INSERT INTO `u_user` VALUES ('1', '123', '1234', 'gy', '2');
INSERT INTO `u_user` VALUES ('2', 'admin', 'admin', 'admin', '0');
INSERT INTO `u_user` VALUES ('3', '321', '321', 'ss', '2');
INSERT INTO `u_user` VALUES ('4', 'ss', 'ss', 'ss', '2');
