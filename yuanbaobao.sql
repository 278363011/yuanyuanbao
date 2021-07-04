/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 8.0.13 : Database - yuanyuanbao
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`yuanyuanbao` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `yuanyuanbao`;

/*Table structure for table `custom_design` */

DROP TABLE IF EXISTS `custom_design`;

CREATE TABLE `custom_design` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键ID',
  `user_id` bigint(20) NOT NULL COMMENT '提交者',
  `concact_method` varchar(512) NOT NULL COMMENT '联系方式',
  `design_desc` varchar(1024) NOT NULL COMMENT '项目描述',
  `design_language` varchar(50) NOT NULL COMMENT '开发语言',
  `design_count` varchar(128) NOT NULL COMMENT '开发预算',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_del` int(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `custom_design` */

insert  into `custom_design`(`id`,`user_id`,`concact_method`,`design_desc`,`design_language`,`design_count`,`create_time`,`update_time`,`is_del`) values (1,9527,'qq278363011','基于springboot的物联网系统','java','300','2021-07-03 13:47:30','2021-07-03 13:47:30',0);

/*Table structure for table `design_tags_middle` */

DROP TABLE IF EXISTS `design_tags_middle`;

CREATE TABLE `design_tags_middle` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键ID',
  `design_id` bigint(20) NOT NULL COMMENT '项目ID',
  `tag_id` bigint(20) NOT NULL COMMENT '标签ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `design_tags_middle` */

/*Table structure for table `exchange_design` */

DROP TABLE IF EXISTS `exchange_design`;

CREATE TABLE `exchange_design` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `project_id` bigint(20) NOT NULL COMMENT '设计ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `exchange_design` */

/*Table structure for table `project_category_dic` */

DROP TABLE IF EXISTS `project_category_dic`;

CREATE TABLE `project_category_dic` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键ID',
  `cate_name` varchar(100) NOT NULL COMMENT '分类名称',
  `cate_desc` varchar(100) DEFAULT NULL COMMENT '分类描述',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `project_category_dic` */

insert  into `project_category_dic`(`id`,`cate_name`,`cate_desc`,`create_time`,`update_time`) values (1,'Java','Java分类','2021-07-03 12:09:20','2021-07-03 12:11:39'),(2,'Python','Python分类','2021-07-03 12:10:00','2021-07-03 12:10:00'),(3,'小程序/H5','小程序H5分类','2021-07-03 12:10:40','2021-07-03 12:10:40'),(4,'C/C++','C/C++分类','2021-07-03 12:11:02','2021-07-03 12:11:02'),(5,'Nodejs','Nodejs分类','2021-07-03 12:11:17','2021-07-03 12:11:17'),(6,'Golang','Golang分类','2021-07-03 12:11:56','2021-07-03 12:11:56');

/*Table structure for table `project_design` */

DROP TABLE IF EXISTS `project_design`;

CREATE TABLE `project_design` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键ID',
  `project_id` varchar(64) NOT NULL COMMENT '项目id',
  `title_name` varchar(256) NOT NULL COMMENT '项目标题',
  `title_desc` text NOT NULL COMMENT '项目描述',
  `title_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '源码详情html',
  `integration` int(20) NOT NULL COMMENT '所需积分',
  `user_view_num` int(20) DEFAULT NULL COMMENT '用户浏览量',
  `share_num` int(20) DEFAULT NULL COMMENT '分享量',
  `exchange_num` int(20) DEFAULT NULL COMMENT '兑换数',
  `download_url` text NOT NULL COMMENT '设计资源下载地址',
  `current_status` int(1) DEFAULT NULL COMMENT '0 待发布,1 上线,2 下线',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_del` int(1) unsigned DEFAULT '0' COMMENT '逻辑删除',
  `lock_version` bigint(20) unsigned DEFAULT '0' COMMENT '乐观锁',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `project_design` */

/*Table structure for table `project_tags` */

DROP TABLE IF EXISTS `project_tags`;

CREATE TABLE `project_tags` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键ID',
  `tag_name` varchar(30) NOT NULL COMMENT '标签名称',
  `tag_desc` varchar(30) DEFAULT NULL COMMENT '标签描述',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `project_tags` */

insert  into `project_tags`(`id`,`tag_name`,`tag_desc`,`create_time`,`update_time`) values (1,'java',NULL,'2021-07-03 13:47:50','2021-07-03 13:47:50'),(2,'c++',NULL,'2021-07-03 13:47:58','2021-07-03 13:47:58'),(3,'nodejs',NULL,'2021-07-03 13:48:01','2021-07-03 13:48:08'),(4,'vue',NULL,'2021-07-03 13:48:04','2021-07-03 13:48:04'),(5,'springboot',NULL,'2021-07-03 13:48:13','2021-07-03 13:48:13');

/*Table structure for table `sys_api` */

DROP TABLE IF EXISTS `sys_api`;

CREATE TABLE `sys_api` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `code` varchar(64) NOT NULL COMMENT '权限码',
  `url` varchar(255) NOT NULL COMMENT 'api url',
  `request_type` varchar(20) DEFAULT NULL COMMENT '请求方式-get post',
  `perm_flag` varchar(32) DEFAULT NULL COMMENT '菜单权限标识',
  `desc` varchar(255) DEFAULT NULL COMMENT 'api描述',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_del` int(1) NOT NULL DEFAULT '0' COMMENT '0--正常 1--删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `sys_api` */

insert  into `sys_api`(`id`,`code`,`url`,`request_type`,`perm_flag`,`desc`,`create_time`,`update_time`,`is_del`) values (1,'test','/test','post','test','测试标识','2021-07-04 13:23:54','2021-07-04 13:23:54',0),(2,'test2','/test2','post','test2','测试标识2','2021-07-04 13:24:07','2021-07-04 13:24:07',0),(3,'test3','/test3','get','test3','测试标识3','2021-07-04 13:24:29','2021-07-04 13:24:29',0);

/*Table structure for table `sys_login_log` */

DROP TABLE IF EXISTS `sys_login_log`;

CREATE TABLE `sys_login_log` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userid` bigint(20) NOT NULL COMMENT '用户ID',
  `message` text COMMENT '错误信息',
  `ip` varchar(50) DEFAULT NULL COMMENT '登录IP',
  `location` varchar(255) DEFAULT NULL COMMENT '登录地点',
  `is_succeed` int(1) DEFAULT '1' COMMENT '1成功 2失败',
  `os` varchar(50) DEFAULT NULL COMMENT '登录平台',
  `createtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_login_log` */

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `name` varchar(32) NOT NULL COMMENT '菜单名称',
  `perm_flag` varchar(32) DEFAULT NULL COMMENT '菜单权限标识',
  `url` varchar(255) DEFAULT NULL COMMENT '前端跳转URL',
  `is_frame` int(1) NOT NULL DEFAULT '1' COMMENT '1 非外链 2 外链',
  `parent_id` int(11) DEFAULT NULL COMMENT '父菜单',
  `level` int(11) NOT NULL DEFAULT '1' COMMENT '所在层级',
  `icon` varchar(32) DEFAULT NULL COMMENT '图标',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_del` int(1) DEFAULT '0' COMMENT '0--正常 1--删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`name`,`perm_flag`,`url`,`is_frame`,`parent_id`,`level`,`icon`,`sort`,`create_time`,`update_time`,`is_del`) values (1,'菜单管理','sys:menu',NULL,1,NULL,1,NULL,4,'2020-10-28 08:33:20','2020-10-28 09:15:32',0),(2,'用户管理','sys:user',NULL,1,NULL,1,NULL,2,'2020-10-28 08:33:28','2020-10-28 09:15:30',0),(3,'角色管理','sys:role',NULL,1,NULL,1,NULL,3,'2020-10-28 08:33:33','2020-10-28 09:15:31',0),(4,'API管理','sys:api',NULL,1,NULL,1,NULL,5,'2020-10-28 08:47:44','2020-10-28 09:29:05',0),(5,'首页','sys:home',NULL,1,NULL,1,NULL,1,'2020-10-28 09:15:29','2020-10-28 09:15:29',0);

/*Table structure for table `sys_operation_log` */

DROP TABLE IF EXISTS `sys_operation_log`;

CREATE TABLE `sys_operation_log` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `user_id` bigint(30) NOT NULL COMMENT '用户名',
  `description` varchar(255) DEFAULT NULL COMMENT '操作描述',
  `action_url` varchar(255) DEFAULT NULL COMMENT '请求地址',
  `action_method` text COMMENT '请求方法',
  `start_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '请求时间',
  `finish_time` datetime NOT NULL COMMENT '结束时间',
  `ip` varchar(255) DEFAULT NULL COMMENT '操作IP',
  `location` varchar(255) DEFAULT NULL COMMENT '操作地点',
  `os` varchar(255) DEFAULT NULL COMMENT '操作平台',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_operation_log` */

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_name` varchar(64) NOT NULL COMMENT '角色名称',
  `role_code` varchar(40) NOT NULL COMMENT '角色标识',
  `remark` varchar(60) DEFAULT NULL COMMENT '角色描述',
  `is_del` int(1) NOT NULL DEFAULT '0' COMMENT '0-正常,1-删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`role_name`,`role_code`,`remark`,`is_del`,`create_time`,`update_time`) values (1,'小程序普通用户','normal','微信普通用户',0,'2021-07-04 13:19:13','2021-07-04 13:19:35'),(2,'小程序VIP用户','vip','微信vip用户',0,'2021-07-04 13:19:29','2021-07-04 13:19:42'),(3,'小程序超级vip用户','svip','微信超级vip用户',0,'2021-07-04 13:20:05','2021-07-04 13:20:05'),(4,'后台共建者','co_creator','后台共建者',0,'2021-07-04 13:20:30','2021-07-04 13:20:30'),(5,'后台管理员','admin','后台管理员',0,'2021-07-04 13:20:46','2021-07-04 13:21:06'),(6,'超级管理员','root','超级管理员',0,'2021-07-04 13:21:01','2021-07-04 13:21:01');

/*Table structure for table `sys_role_api` */

DROP TABLE IF EXISTS `sys_role_api`;

CREATE TABLE `sys_role_api` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `api_id` bigint(20) NOT NULL COMMENT 'APIID',
  PRIMARY KEY (`id`),
  KEY `role_key_index` (`role_id`),
  KEY `api_key_index` (`api_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role_api` */

insert  into `sys_role_api`(`id`,`role_id`,`api_id`) values (1,1,1),(2,1,2);

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`),
  KEY `role_key_index` (`role_id`) COMMENT 'roleid普通索引',
  KEY `menu_key_index` (`menu_id`) COMMENT 'menuid普通索引'
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`id`,`role_id`,`menu_id`) values (1,1,1),(2,1,2);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增ID',
  `account` varchar(64) NOT NULL COMMENT '账号',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `salt` varchar(50) NOT NULL COMMENT '密码盐',
  `name` varchar(50) NOT NULL COMMENT '用户名字',
  `email` varchar(30) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像地址',
  `sex` int(1) NOT NULL DEFAULT '1' COMMENT '性别 1男 2女',
  `createUserId` int(11) unsigned DEFAULT NULL COMMENT '创建者',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `lock_flag` int(1) NOT NULL DEFAULT '0' COMMENT '0-待审核 ，1-正常，2-锁定',
  `is_del` int(1) NOT NULL DEFAULT '0' COMMENT '0-正常，1-逻辑删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

/*Table structure for table `tutorial` */

DROP TABLE IF EXISTS `tutorial`;

CREATE TABLE `tutorial` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键ID',
  `tutorial_name` varchar(256) NOT NULL COMMENT '教程名称',
  `tutorial_desc` varchar(256) DEFAULT NULL COMMENT '教程描述',
  `tutorial_content` text COMMENT '教程内容',
  `user_view_num` int(11) DEFAULT NULL COMMENT '用户浏览量',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_del` int(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `lock_version` bigint(20) NOT NULL DEFAULT '0' COMMENT '乐观锁',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tutorial` */

insert  into `tutorial`(`id`,`tutorial_name`,`tutorial_desc`,`tutorial_content`,`user_view_num`,`create_time`,`update_time`,`is_del`,`lock_version`) values (1,'windows下的IDEA安装和部署','如何部署IDEA',NULL,1,'2021-07-03 13:40:44','2021-07-03 13:40:44',0,0),(2,'windows下的REDIS安装教程','如何安装REDIS',NULL,10,'2021-07-03 13:41:09','2021-07-03 13:41:09',0,0);

/*Table structure for table `tutorial_software` */

DROP TABLE IF EXISTS `tutorial_software`;

CREATE TABLE `tutorial_software` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键ID',
  `tutorial_id` bigint(20) NOT NULL COMMENT '教程ID',
  `sofeware_name` varchar(128) NOT NULL COMMENT '软件名称',
  `sofeware_download_url` text COMMENT '软件下载地址',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `tutorial_software` */

insert  into `tutorial_software`(`id`,`tutorial_id`,`sofeware_name`,`sofeware_download_url`,`create_time`,`update_time`) values (1,2,'redis6.3安装包','www.baidu.com','2021-07-03 13:41:39','2021-07-03 13:43:25'),(2,2,'redis6.4安装包','www.qq.com','2021-07-03 13:43:43','2021-07-03 13:43:43'),(3,1,'idea2019.3安装包','www.csdn.com','2021-07-03 13:44:04','2021-07-03 13:44:04');

/*Table structure for table `upload_design` */

DROP TABLE IF EXISTS `upload_design`;

CREATE TABLE `upload_design` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键ID',
  `user_id` bigint(20) NOT NULL COMMENT '上传者',
  `design_name` varchar(50) NOT NULL COMMENT '项目名称',
  `design_language` varchar(50) NOT NULL COMMENT '开发语言',
  `upload_url` varchar(0) NOT NULL COMMENT '下载地址',
  `concact_method` varchar(256) NOT NULL COMMENT '联系方式',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_del` int(1) NOT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `upload_design` */

/*Table structure for table `wx_user` */

DROP TABLE IF EXISTS `wx_user`;

CREATE TABLE `wx_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键ID',
  `open_id` varchar(30) NOT NULL COMMENT '小程序用户的openId',
  `role_id` int(11) NOT NULL DEFAULT '0' COMMENT '0.普通 1.vip 2.svip',
  `user_id` varchar(64) NOT NULL COMMENT '用户ID,方便查找',
  `nick_name` varchar(100) DEFAULT NULL COMMENT '微信昵称',
  `avatar_url` varchar(256) DEFAULT NULL COMMENT '用户头像',
  `session_key` varchar(256) NOT NULL COMMENT '微信session key',
  `gender` int(1) NOT NULL DEFAULT '0' COMMENT '性别  0-男、1-女',
  `country` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'china' COMMENT '所在国家',
  `province` varchar(100) DEFAULT NULL COMMENT '省份',
  `city` varchar(100) DEFAULT NULL COMMENT '城市',
  `wx_language` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'china' COMMENT '语种',
  `mobile` varchar(50) DEFAULT NULL COMMENT '手机号码',
  `integral` bigint(20) NOT NULL DEFAULT '0' COMMENT '我的积分',
  `share_user_id` bigint(20) DEFAULT NULL COMMENT '分享的用户ID',
  `current_status` int(1) NOT NULL DEFAULT '0' COMMENT '0可用, 1 禁用, 2 注销',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_del` int(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `lock_version` bigint(20) NOT NULL DEFAULT '0' COMMENT '乐观锁',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `wx_user` */

insert  into `wx_user`(`id`,`open_id`,`role_id`,`user_id`,`nick_name`,`avatar_url`,`session_key`,`gender`,`country`,`province`,`city`,`wx_language`,`mobile`,`integral`,`share_user_id`,`current_status`,`create_time`,`update_time`,`is_del`,`lock_version`) values (1,'12345',1,'9527','小强','www.baidu.com','',0,'中国','湖南','长沙','中文','13054181563',10000,1,0,'2021-07-03 13:44:57','2021-07-03 13:45:48',0,0),(2,'otz6W4kpYWq7rXJ7sCPPyqU0eU7A',0,'5f2146b2f48e42429c3cae37990f76cd','佚名',NULL,'eRqJYBr1d3L5S321pbgqXg==',0,'china',NULL,NULL,'china',NULL,0,NULL,0,'2021-07-04 00:36:29','2021-07-04 00:36:29',0,0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
