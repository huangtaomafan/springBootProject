CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����id',
  `name` varchar(255) DEFAULT NULL COMMENT '����',
  `age` smallint(6) DEFAULT NULL COMMENT '����',
  `sex` varchar(255) DEFAULT NULL COMMENT '�Ա�',
  `telNo` varchar(255) NOT NULL COMMENT '�绰��/��¼��',
  `password` varchar(255) NOT NULL COMMENT '����',
  `gmtCreate` datetime NOT NULL COMMENT '��������',
  `gmtModify` datetime NOT NULL COMMENT '�޸�����',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_intex` (`telNo`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;