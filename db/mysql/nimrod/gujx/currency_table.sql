SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `currency`
-- ----------------------------
DROP TABLE IF EXISTS `currency`;
CREATE TABLE `currency` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `currname` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '币种名称',
  `currcode` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '币种代码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='币种表';

-- ----------------------------
-- Records of currency
-- ----------------------------
INSERT INTO `currency` VALUES ('001'	, '人民币', '001');
INSERT INTO `currency` VALUES ('002'	, '美元', '014');
