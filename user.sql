CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `age` smallint(6) DEFAULT NULL COMMENT '年龄',
  `sex` varchar(255) DEFAULT NULL COMMENT '性别',
  `telNo` varchar(255) NOT NULL COMMENT '电话号/登录名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `gmtCreate` datetime NOT NULL COMMENT '创建日期',
  `gmtModify` datetime NOT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_intex` (`telNo`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;