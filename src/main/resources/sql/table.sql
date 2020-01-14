CREATE TABLE `au_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_name` varchar(63) NOT NULL COMMENT '用户名称',
  `user_password` varchar(255) NOT NULL COMMENT '用户密码',
  `real_name` varchar(63) NOT NULL COMMENT '用户姓名',
  `user_age` int(3) unsigned NOT NULL COMMENT '用户年龄',
  `user_gender` char(1) NOT NULL COMMENT '用户性别',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_login_time` timestamp NULL DEFAULT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_user_name` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';

CREATE TABLE `au_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_name` varchar(63) NOT NULL COMMENT '角色名称',
  `role_note` varchar(255) DEFAULT NULL COMMENT '角色备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_role_name` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色信息表';

CREATE TABLE `au_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户主键ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色主键ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_user_role` (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

CREATE TABLE `au_authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `authority_name` varchar(63) NOT NULL COMMENT '权限名称',
  `authority_note` varchar(255) NOT NULL COMMENT '权限备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_authority_name` (`authority_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限信息表';

CREATE TABLE `au_role_authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色主键ID',
  `authority_id` bigint(20) NOT NULL COMMENT '权限主键ID',
  `is_valid` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否有效',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `udx_role_authority` (`role_id`,`authority_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限表';