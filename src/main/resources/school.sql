-- ----------------------------
-- Table structure for crud_student
-- ----------------------------
DROP TABLE IF EXISTS `crud_student`;
CREATE TABLE `crud_student`
(
  `id`    int           NOT NULL ,
  `name`  varchar(255)  NOT NULL ,
  `age`   int           NULL ,
  `score`  int          NULL ,
PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for crud_teacher
-- ----------------------------
DROP TABLE IF EXISTS `crud_teacher`;
CREATE TABLE `crud_teacher`
(
  `id`    int          NOT NULL ,
  `name`  varchar(255) NOT NULL ,
  `age`   int          NULL ,
PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for crud_student_teacher
-- ----------------------------
DROP TABLE IF EXISTS `crud_student_teacher`;
CREATE TABLE `crud_student_teacher`
(
  `id`          int NOT NULL ,
  `student_id`  int NOT NULL ,
  `teacher_id`  int NOT NULL ,
  PRIMARY KEY (`id`)
);