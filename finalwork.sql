/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : finalwork

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-12-27 20:12:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `course_homework`
-- ----------------------------
DROP TABLE IF EXISTS `course_homework`;
CREATE TABLE `course_homework` (
  `homework_code` int(16) NOT NULL AUTO_INCREMENT,
  `homework_title` varchar(16) NOT NULL,
  `homework_course_code` varchar(16) NOT NULL,
  `homework_date` varchar(16) NOT NULL,
  PRIMARY KEY (`homework_code`)
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_homework
-- ----------------------------
INSERT INTO `course_homework` VALUES ('111', '跳远', '1001', '2019/1/1');
INSERT INTO `course_homework` VALUES ('112', '踢球', '1001', '2018/1/1');
INSERT INTO `course_homework` VALUES ('113', '篮球', '1001', '2019/1/1');
INSERT INTO `course_homework` VALUES ('114', '网球', '1001', '2019/1/1');
INSERT INTO `course_homework` VALUES ('116', '铅球', '1002', '2018/1/1');

-- ----------------------------
-- Table structure for `course_student_info`
-- ----------------------------
DROP TABLE IF EXISTS `course_student_info`;
CREATE TABLE `course_student_info` (
  `stu_code` varchar(16) NOT NULL,
  `course_code` varchar(16) NOT NULL,
  `qm_grade` varchar(16) NOT NULL DEFAULT '',
  `stu_course_code` int(16) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`stu_course_code`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_student_info
-- ----------------------------
INSERT INTO `course_student_info` VALUES ('17905834', '1001', '65', '9');
INSERT INTO `course_student_info` VALUES ('17905831', '1001', '15', '10');
INSERT INTO `course_student_info` VALUES ('17905837', '1001', '45', '11');
INSERT INTO `course_student_info` VALUES ('17905803', '1001', '100', '12');
INSERT INTO `course_student_info` VALUES ('17905802', '1001', '30', '13');
INSERT INTO `course_student_info` VALUES ('17905838', '1001', '100', '14');
INSERT INTO `course_student_info` VALUES ('17905836', '1001', '15', '15');
INSERT INTO `course_student_info` VALUES ('17905804', '1001', '15', '16');
INSERT INTO `course_student_info` VALUES ('17905802', '1002', '81', '17');
INSERT INTO `course_student_info` VALUES ('17905803', '1002', '100', '18');
INSERT INTO `course_student_info` VALUES ('17905804', '1002', '0', '19');
INSERT INTO `course_student_info` VALUES ('17905802', '1003', '', '22');
INSERT INTO `course_student_info` VALUES ('17905803', '1003', '', '23');
INSERT INTO `course_student_info` VALUES ('17905804', '1003', '', '24');
INSERT INTO `course_student_info` VALUES ('17905831', '1003', '', '25');
INSERT INTO `course_student_info` VALUES ('17905834', '1002', '', '26');

-- ----------------------------
-- Table structure for `homework_info`
-- ----------------------------
DROP TABLE IF EXISTS `homework_info`;
CREATE TABLE `homework_info` (
  `homework_code` varchar(16) NOT NULL,
  `homework_date` varchar(16) NOT NULL,
  `homework_title` varchar(16) NOT NULL,
  PRIMARY KEY (`homework_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of homework_info
-- ----------------------------
INSERT INTO `homework_info` VALUES ('111', '2018/1/1', '跳远');
INSERT INTO `homework_info` VALUES ('112', '2018/1/1', '踢球');
INSERT INTO `homework_info` VALUES ('113', '2018/1/1', '篮球');
INSERT INTO `homework_info` VALUES ('114', '2018/1/1', '网球');
INSERT INTO `homework_info` VALUES ('116', '2018/1/1', '铅球');

-- ----------------------------
-- Table structure for `homework_student`
-- ----------------------------
DROP TABLE IF EXISTS `homework_student`;
CREATE TABLE `homework_student` (
  `homework_stu_code` int(16) NOT NULL AUTO_INCREMENT,
  `homework_code` varchar(16) NOT NULL,
  `homework_name` varchar(16) NOT NULL,
  `homework_date` varchar(16) NOT NULL,
  `stu_code` varchar(16) NOT NULL,
  `stu_name` varchar(16) NOT NULL,
  `homework_txt` varchar(255) NOT NULL,
  `homework_filename` varchar(16) NOT NULL,
  `homework_grade` varchar(16) NOT NULL,
  `stu_pro` varchar(16) NOT NULL,
  `stu_class` varchar(16) NOT NULL,
  PRIMARY KEY (`homework_stu_code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of homework_student
-- ----------------------------
INSERT INTO `homework_student` VALUES ('1', '112', '跳远', '2018/1/1', '17905834', '叶嘉赞', '112233', '作业1', '112', '计算机科学与技术', '计科6班');
INSERT INTO `homework_student` VALUES ('2', '116', '铅球', '2018/1/1', '17905834', '叶嘉赞', '112233', '作业2', '112', '计算机科学与技术', '计科6班');
INSERT INTO `homework_student` VALUES ('3', '117', '铅球2', '2018/1/1', '17905834', '叶嘉赞', '112233', '作业3', '100', '计算机科学与技术', '计科6班');

-- ----------------------------
-- Table structure for `student_info`
-- ----------------------------
DROP TABLE IF EXISTS `student_info`;
CREATE TABLE `student_info` (
  `stu_code` varchar(16) NOT NULL,
  `stu_name` varchar(16) NOT NULL,
  `stu_sex` varchar(16) NOT NULL,
  `stu_pwd` varchar(16) NOT NULL,
  `stu_pro` varchar(16) NOT NULL,
  `stu_class` varchar(16) NOT NULL,
  PRIMARY KEY (`stu_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student_info
-- ----------------------------
INSERT INTO `student_info` VALUES ('17905802', '陈天怡', '女', '123', '计算机科学与技术', '计科6班');
INSERT INTO `student_info` VALUES ('17905803', '顾炜', '女', '123', '计算机科学与技术', '计科6班');
INSERT INTO `student_info` VALUES ('17905804', '李芳芳', '女', '123', '计算机科学与技术', '计科6班');
INSERT INTO `student_info` VALUES ('17905831', '谢家炎', '男', '123', '计算机科学与技术', '计科6班');
INSERT INTO `student_info` VALUES ('17905834', '叶嘉赞', '男', '123', '计算机科学与技术', '计科6班');
INSERT INTO `student_info` VALUES ('17905835', '岳鹏', '男', '123', '网络工程', '网工1班');
INSERT INTO `student_info` VALUES ('17905836', '张磊', '男', '123', '计算机科学与技术', '计科6班');
INSERT INTO `student_info` VALUES ('17905837', '张淑芬', '女', '123', '计算机科学与技术', '计科6班');
INSERT INTO `student_info` VALUES ('17905838', '张泽渊', '男', '123', '计算机科学与技术', '计科6班');

-- ----------------------------
-- Table structure for `teacher_course_info`
-- ----------------------------
DROP TABLE IF EXISTS `teacher_course_info`;
CREATE TABLE `teacher_course_info` (
  `course_code` varchar(16) NOT NULL,
  `course_class` varchar(16) NOT NULL,
  `course_name` varchar(16) NOT NULL,
  `course_time` varchar(16) NOT NULL,
  `teacher_code` varchar(16) NOT NULL,
  `course_year` varchar(16) NOT NULL,
  PRIMARY KEY (`course_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher_course_info
-- ----------------------------
INSERT INTO `teacher_course_info` VALUES ('1001', 'c409', '体育', '周一', '40275', '2018');
INSERT INTO `teacher_course_info` VALUES ('1002', 'd509', '英语', '周末', '40275', '2018');
INSERT INTO `teacher_course_info` VALUES ('1003', 'd509', '物联网导论', '周二', '40275', '2018');
INSERT INTO `teacher_course_info` VALUES ('1004', 'c501', '计算机接口', '周二', '40275', '2018');
INSERT INTO `teacher_course_info` VALUES ('1005', 'c210', 'c++', '周五', '40275', '2018');
INSERT INTO `teacher_course_info` VALUES ('1006', 'b504', '毛概', '周三', '40275', '2018');
INSERT INTO `teacher_course_info` VALUES ('1007', 'c510', 'javaee设计', '周五', '40275', '2018');
INSERT INTO `teacher_course_info` VALUES ('1008', 'd405', 'javaee设计', '周四', '40276', '2018');
INSERT INTO `teacher_course_info` VALUES ('1009', 'c202', '英语', '周六', '40275', '2018');
INSERT INTO `teacher_course_info` VALUES ('1010', 'c604', '翻译', '周四', '40275', '2018');

-- ----------------------------
-- Table structure for `teacher_info`
-- ----------------------------
DROP TABLE IF EXISTS `teacher_info`;
CREATE TABLE `teacher_info` (
  `teacher_code` varchar(16) NOT NULL,
  `teacher_name` varchar(16) NOT NULL,
  `teacher_pwd` varchar(16) NOT NULL,
  `teacher_qqnumber` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`teacher_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher_info
-- ----------------------------
INSERT INTO `teacher_info` VALUES ('40275', '唐老师', '123', '1842267272');
INSERT INTO `teacher_info` VALUES ('40276', '周老师', '123', '906431289');
