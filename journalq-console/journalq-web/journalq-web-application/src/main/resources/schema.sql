

-- 导出  表 laf_config.application 结构
CREATE TABLE IF NOT EXISTS `application` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增id',
  `code` varchar(128) NOT NULL COMMENT '应用 代码',
  `name` varchar(64) NOT NULL COMMENT '应用 名称',
  `system` varchar(64) NOT NULL COMMENT '所属系统',
  `department` varchar(128) NOT NULL COMMENT '所属部门',
  `owner_id` bigint(20) NOT NULL COMMENT '拥有者id',
  `owner_code` varchar(64) NOT NULL COMMENT '拥有者code',
  `source` tinyint(4) NOT NULL DEFAULT '0' COMMENT '来源：0 手动，jone 1， jdos 2 ',
  `sign` int(11) NOT NULL DEFAULT '0' COMMENT '签名',
  `create_by` bigint(20) NOT NULL COMMENT '创建者id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) NOT NULL DEFAULT '0' COMMENT '修改人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态：-1 删除，0 禁用，1 启用',
  `alias_code` varchar(128) NOT NULL COMMENT '别名',
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;


-- 导出  表 laf_config.application_user 结构
CREATE TABLE IF NOT EXISTS `application_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `app_id` bigint(20) NOT NULL COMMENT '应用id',
  `app_code` varchar(128) NOT NULL COMMENT '应用code',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `user_code` varchar(64) NOT NULL COMMENT '用户code',
  `update_by` bigint(20) DEFAULT '0' COMMENT '修改人',
  `update_by_code` varchar(64) DEFAULT 'auto' COMMENT '修改人',
  `update_time` datetime NOT NULL COMMENT '记录更新时间',
  `create_by` bigint(20) DEFAULT '0' COMMENT '创建人',
  `create_by_code` varchar(64) DEFAULT 'auto' COMMENT '创建人code',
  `create_time` datetime NOT NULL COMMENT '记录创建时间',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态：-1 删除，0 禁用，1 启用',
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;

-- 导出  表 laf_config.user 结构
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `code` varchar(64) NOT NULL COMMENT '用户erp',
  `name` varchar(64) NOT NULL COMMENT '用户中文名',
  `org_id` varchar(20) DEFAULT NULL COMMENT '组织id',
  `org_name` varchar(128) DEFAULT NULL COMMENT '组织名',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `role` tinyint(4) NOT NULL DEFAULT '0' COMMENT '权限：0 普通用户，1 管理员',
  `sign` int(11) NOT NULL DEFAULT '0' COMMENT '签名',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态：-1 删除，0 禁用，1 启用',
  PRIMARY KEY (`id`)
)  DEFAULT CHARSET=utf8;

CREATE TABLE  IF NOT EXISTS `oper_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type` int(11) NOT NULL COMMENT '类型',
  `identity` bigint(20) NOT NULL COMMENT '操作资源ID',
  `oper_type` int(11) NOT NULL COMMENT '操作类型',
  `target` varchar(1500) DEFAULT NULL COMMENT '目标',
  `result` varchar(1024) DEFAULT NULL COMMENT '操作结果，成功或异常信息',
  `description` varchar(512) DEFAULT NULL COMMENT '操作描述，url或其他',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` bigint(20) NOT NULL COMMENT '创建人',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `update_by` bigint(20) NOT NULL COMMENT '更新人',
  `status` tinyint(4) NOT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;


CREATE TABLE  IF NOT EXISTS `metric` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(128) NOT NULL COMMENT '代码',
  `alias_code` varchar(256) NOT NULL COMMENT '值,唯一',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `type` tinyint(4) NOT NULL COMMENT '类型：0 atomic, 1 aggregator, 10 others(mdc)',
  `charts` varchar(64) DEFAULT NULL COMMENT '监控图表数组：0 其他，1 生产详情，2 消费详情，3 生产汇总，4 消费汇总，5 主机监控，6 broker监控',
  `source` varchar(128) DEFAULT NULL COMMENT '来源指标code',
  `provider` varchar(128) DEFAULT NULL COMMENT '指标提供方',
  `description` varchar(1000) DEFAULT NULL COMMENT '聚合描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` bigint(20) NOT NULL COMMENT '创建人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `update_by` bigint(20) NOT NULL COMMENT '修改人',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态：-1 删除，0 禁用，1 启用',
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `message_retry` (
	`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
	`message_id` varchar(50) NOT NULL COMMENT '消息编号',
	`business_id` varchar(100) DEFAULT NULL COMMENT '业务编号',
	`topic` varchar(100) NOT NULL COMMENT '主题',
	`app` varchar(100) NOT NULL COMMENT '应用',
	`send_time` datetime NOT NULL COMMENT '发送时间',
	`expire_time` datetime NOT NULL COMMENT '过期时间',
	`retry_time` datetime NOT NULL COMMENT '重试时间',
	`retry_count` int(10) NOT NULL DEFAULT '0' COMMENT '重试次数',
	`data` mediumblob NOT NULL COMMENT '消息体',
	`exception` blob COMMENT '异常信息',
	`create_time` datetime NOT NULL COMMENT '创建时间',
	`create_by` int(10) NOT NULL DEFAULT '0' COMMENT '创建人',
	`update_time` datetime NOT NULL COMMENT '更新时间',
	`update_by` int(10) NOT NULL DEFAULT '0' COMMENT '更新人',
	`status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态,0:成功,1:失败,-2:过期',
	PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARSET = utf8;