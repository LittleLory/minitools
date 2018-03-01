SET NAMES utf8;

-- ----------------------------
--  Table structure for `t_tool_entry`
-- ----------------------------
DROP TABLE IF EXISTS `t_tool_entry`;
CREATE TABLE `t_tool_entry` (
  `entry_id` int(11) NOT NULL AUTO_INCREMENT,
  `entry_name` varchar(45) DEFAULT NULL,
  `dependency_json` varchar(512) DEFAULT NULL,
  `parameter_json` varchar(512) DEFAULT NULL,
  `code` mediumtext,
  `belong_id` int(11) DEFAULT NULL,
  `belong_name` varchar(45) DEFAULT NULL,
  `is_public` tinyint(1) DEFAULT '1',
  `create_time` timestamp NULL DEFAULT NULL,
  `is_open` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`entry_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
