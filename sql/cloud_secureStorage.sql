/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50722
Source Host           : 120.24.237.131:3306
Source Database       : cloud_secureStorage

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-05-24 19:51:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for conf_aliyun
-- ----------------------------
DROP TABLE IF EXISTS `conf_aliyun`;
CREATE TABLE `conf_aliyun` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `endPoint` varchar(255) DEFAULT NULL,
  `accessKey` varchar(255) DEFAULT NULL,
  `accessKeySecret` varchar(255) DEFAULT NULL,
  `BucketName` varchar(255) DEFAULT NULL,
  `accessUrl` varchar(255) DEFAULT NULL,
  `user_Id` int(11) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for conf_netease
-- ----------------------------
DROP TABLE IF EXISTS `conf_netease`;
CREATE TABLE `conf_netease` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `endPoint` varchar(255) DEFAULT NULL,
  `secretId` varchar(255) DEFAULT NULL,
  `secretKey` varchar(255) DEFAULT NULL,
  `bucketName` varchar(255) DEFAULT NULL,
  `user_Id` int(11) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for conf_qcloud
-- ----------------------------
DROP TABLE IF EXISTS `conf_qcloud`;
CREATE TABLE `conf_qcloud` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `appId` varchar(255) NOT NULL,
  `secretId` varchar(255) DEFAULT NULL,
  `secretKey` varchar(255) DEFAULT NULL,
  `bucketName` varchar(255) DEFAULT NULL,
  `user_Id` int(11) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for conf_qiniu
-- ----------------------------
DROP TABLE IF EXISTS `conf_qiniu`;
CREATE TABLE `conf_qiniu` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `domainOfBucket` varchar(255) DEFAULT NULL,
  `accessKey` varchar(255) DEFAULT NULL,
  `secretKey` varchar(255) DEFAULT NULL,
  `bucket` varchar(255) DEFAULT NULL,
  `user_Id` int(11) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for conf_upyun
-- ----------------------------
DROP TABLE IF EXISTS `conf_upyun`;
CREATE TABLE `conf_upyun` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `bucketName` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_Id` int(11) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for files
-- ----------------------------
DROP TABLE IF EXISTS `files`;
CREATE TABLE `files` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `size` varchar(30) DEFAULT NULL,
  `user_id` int(16) unsigned DEFAULT NULL,
  `status` int(16) unsigned zerofill DEFAULT NULL COMMENT '-1上传文件失败，0初始值，1 上传成功，2正在上传中',
  `version` int(16) unsigned zerofill DEFAULT NULL,
  `createtime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `updatetime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `FKdgr5hx49828s5vhjo1s8q3wdp` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=200 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for files_hash
-- ----------------------------
DROP TABLE IF EXISTS `files_hash`;
CREATE TABLE `files_hash` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `file_id` int(16) DEFAULT NULL,
  `file_hash` varchar(255) DEFAULT NULL,
  `aliyun_hash` varchar(255) DEFAULT NULL,
  `netease_hash` varchar(255) DEFAULT NULL,
  `qcloud_hash` varchar(255) DEFAULT NULL,
  `qiniu_hash` varchar(255) DEFAULT NULL,
  `upyun_hash` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `file_id` (`file_id`),
  CONSTRAINT `FK8qo69ay8hhnw08aiwp8y6tbif` FOREIGN KEY (`file_id`) REFERENCES `files` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=147 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `roleId` int(16) NOT NULL,
  `roleName` varchar(20) DEFAULT NULL,
  `permissions` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_advice
-- ----------------------------
DROP TABLE IF EXISTS `user_advice`;
CREATE TABLE `user_advice` (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  `main_idea` varchar(128) DEFAULT NULL,
  `message_detail` text,
  `submit_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_auths
-- ----------------------------
DROP TABLE IF EXISTS `user_auths`;
CREATE TABLE `user_auths` (
  `id` int(16) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(16) unsigned DEFAULT NULL,
  `identifier` varchar(30) DEFAULT NULL,
  `identity_type` varchar(30) DEFAULT NULL,
  `credential` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl35o65ovs04mariyxjj10ack1` (`user_id`),
  CONSTRAINT `FKl35o65ovs04mariyxjj10ack1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(16) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(32) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  `phone` varchar(32) DEFAULT NULL,
  `roleId` int(16) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  KEY `roleId` (`roleId`),
  CONSTRAINT `FKh1qetlrsro6pu1acb9ici59p2` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
