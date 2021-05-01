/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : student

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 01/05/2021 18:53:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `class_id` varchar(18) NOT NULL COMMENT '班级主键',
  `class_name` varchar(64) DEFAULT NULL COMMENT '班级名称',
  `class_info` varchar(255) DEFAULT NULL COMMENT '班级信息',
  `subject_id` varchar(18) DEFAULT NULL COMMENT '班级所属专业',
  PRIMARY KEY (`class_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `stu_id` varchar(18) NOT NULL COMMENT '学生主键-学号',
  `id_num` varchar(18) NOT NULL COMMENT '身份证号',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(6) DEFAULT NULL COMMENT '性别',
  `sid` varchar(64) DEFAULT NULL COMMENT '所属专业',
  `cid` varchar(64) DEFAULT NULL COMMENT '所属班级',
  `grade` varchar(64) DEFAULT NULL COMMENT '年级',
  `phone` varchar(11) DEFAULT NULL COMMENT '电话',
  `qq` varchar(20) DEFAULT NULL COMMENT 'QQ',
  `address` varchar(128) DEFAULT NULL COMMENT '家庭住址',
  `teacher_name` varchar(20) DEFAULT NULL COMMENT '班主任姓名',
  `parent_name` varchar(20) DEFAULT NULL COMMENT '家长姓名',
  `parent_phone` varchar(20) DEFAULT NULL COMMENT '家长电话',
  `email` varchar(64) DEFAULT NULL COMMENT '电子邮件地址',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`stu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for subject
-- ----------------------------
DROP TABLE IF EXISTS `subject`;
CREATE TABLE `subject` (
  `subject_id` varchar(18) NOT NULL COMMENT '专业主键',
  `subject_name` varchar(64) DEFAULT NULL COMMENT '专业名称',
  `subject_info` varchar(255) DEFAULT NULL COMMENT '专业信息',
  PRIMARY KEY (`subject_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
