
-- 学生成绩表
DROP TABLE
IF EXISTS `student_score`;

CREATE TABLE `student_score` (
  `id` bigint(20) unsigned AUTO_INCREMENT COMMENT 'id',
  `student_id` bigint(20) unsigned not NULL COMMENT '学生id',
  `student_name` varchar(255) DEFAULT '' COMMENT '学生姓名',
  `exam_id` bigint(20) unsigned not NULL COMMENT '考试名称',
  `exam_name` varchar(255) DEFAULT '' COMMENT '考试名称',
  `chinese_score` float(6,2) DEFAULT NULL COMMENT '语文成绩',
  `math_score` float(6,2) DEFAULT NULL COMMENT '数学成绩',
  `english_score` float(6,2) DEFAULT NULL COMMENT '英语成绩',
  `politics_score` float(6,2) DEFAULT NULL COMMENT '政治成绩',
  `physical_score` float(6,2) DEFAULT NULL COMMENT '物理成绩',
  `chemistry_score` float(6,2) DEFAULT NULL COMMENT '化学成绩',
  `geography_score` float(6,2) DEFAULT NULL COMMENT '地理成绩',
  `biology_score` float(6,2) DEFAULT NULL COMMENT '生物成绩',
  `general_score` float(6,2) DEFAULT NULL COMMENT '综合成绩',  
  `total` float(6,2) DEFAULT NULL COMMENT '总分',   
  `gmt_created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY `pk_id`(`id`)
)
  ENGINE = INNODB
  DEFAULT CHARACTER
  SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  AUTO_INCREMENT = 1
  ROW_FORMAT = DYNAMIC
  COMMENT = '学生成绩表';
