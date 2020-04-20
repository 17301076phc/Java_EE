/*
Navicat MySQL Data Transfer

*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `teacher` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `description` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO course VALUES ('1', "Computer organization","Isabella Zhu","2019-11-11","Computer organization is very important");
INSERT INTO course VALUES ('2', "C++","Lucas. Wang", "2017-12-01","C++ is very important");
INSERT INTO course VALUES ('3', "Database","Ethan.Du", "2018-01-20","Database is very important");
INSERT INTO course VALUES ('4', "Android Dev","Dr.liu", "2018-11-20","Android Developing is very important");
INSERT INTO course VALUES ('5', "Python","Zhang San", "2019-01-20","Python is very important");
INSERT INTO course VALUES ('6', "Java","Li Si", "2019-02-21","Java is very important");
INSERT INTO course VALUES ('7', "Computer NetWork","Old Wang", "2020-07-15","Computer NetWork is very important");
INSERT INTO course VALUES ('8', "Math","Zeng L", "2018-01-30","Math is very important");
INSERT INTO course VALUES ('9', "English","Du Tian", "2020-01-20","English is very important");
INSERT INTO course VALUES ('10', "Sports","Ethan.Li", "2020-02-20","Sports is very important");
INSERT INTO course VALUES ('11', "Politics","Wang wu", "2017-01-20","Politics is very important");
INSERT INTO course VALUES ('12', 'Web', 'Yang','2020-03-03','Web is very important');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO role VALUES ('1', 'ROLE_USER');
INSERT INTO role VALUES ('2', 'ROLE_Teacher');
INSERT INTO role VALUES ('3', 'ROLE_Student');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO user VALUES ('1', 'yoyo@123.com', 'yoyo', '$2a$06$Her60GVyob.Ndhay4KbH3OShEeuyy0SNQStZ.Pl90HWiN7UEETv0i');
INSERT INTO user VALUES ('2', 'zozo@123.com', 'zozo', '$2a$06$Her60GVyob.Ndhay4KbH3OShEeuyy0SNQStZ.Pl90HWiN7UEETv0i');
INSERT INTO user VALUES ('3', 'popo@123.com', 'popo', '$2a$06$Her60GVyob.Ndhay4KbH3OShEeuyy0SNQStZ.Pl90HWiN7UEETv0i');

-- ----------------------------
-- Table structure for `usercourse`
-- ----------------------------
DROP TABLE IF EXISTS `usercourse`;
CREATE TABLE `usercourse` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `teachername` varchar(255) DEFAULT NULL,
  `coursetime` varchar(255) DEFAULT NULL,
  `description` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `usercourse_ibfk_1` FOREIGN KEY (`email`) REFERENCES `user` (`email`),
  CONSTRAINT `usercourse_ibfk_2` FOREIGN KEY (`name`) REFERENCES `course` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of usercourse
-- ----------------------------
INSERT INTO usercourse VALUES ('1', 'yoyo@123.com', "Computer organization","Isabella Zhu","2019-11-11","Computer organization is very important");
INSERT INTO usercourse VALUES ('2', 'yoyo@123.com', "Python","Zhang San", "2019-01-20","Python is very important");
INSERT INTO usercourse VALUES ('3', 'yoyo@123.com', 'Web', 'Yang','2020-03-03','Web is very important');

-- ----------------------------
-- Table structure for `users_roles`
-- ----------------------------
DROP TABLE IF EXISTS `users_roles`;
CREATE TABLE `users_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `role_id` (`role_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `users_roles_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `users_roles_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users_roles
-- ----------------------------
INSERT INTO users_roles VALUES ('1', '1');
INSERT INTO users_roles VALUES ('2', '2');
INSERT INTO users_roles VALUES ('3', '3');
