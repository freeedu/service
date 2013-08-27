/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50521
 Source Host           : localhost
 Source Database       : blog

 Target Server Type    : MySQL
 Target Server Version : 50521
 File Encoding         : utf-8

 Date: 08/27/2013 00:47:32 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `blog`
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `author_name` varchar(255) DEFAULT NULL,
  `author_uid` varchar(255) DEFAULT NULL,
  `blog_desc` varchar(255) DEFAULT NULL,
  `blog_subtitle` varchar(255) DEFAULT NULL,
  `blog_title` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `last_update` datetime DEFAULT NULL,
  `tag_ids` varchar(255) DEFAULT NULL,
  `blogSetting_id` bigint(20) NOT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `series_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`blogSetting_id`),
  KEY `FK_bk9fqmdtfa13hj9lb6n5pq96q` (`blogSetting_id`),
  KEY `FK_c6bjx18svyosjbdnjoqawq2p2` (`category_id`),
  KEY `FK_nuq14ahfvfgfikuvpcptuc1p0` (`series_id`),
  CONSTRAINT `FK_bk9fqmdtfa13hj9lb6n5pq96q` FOREIGN KEY (`blogSetting_id`) REFERENCES `blog_settings` (`id`),
  CONSTRAINT `FK_c6bjx18svyosjbdnjoqawq2p2` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FK_nuq14ahfvfgfikuvpcptuc1p0` FOREIGN KEY (`series_id`) REFERENCES `series` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `blog`
-- ----------------------------
BEGIN;
INSERT INTO `blog` VALUES (null, null, 'fasfasdfafaf', 'fasfasdf', 'fasdfaf', '2013-08-06 00:34:10', null, null, '1', null, null), (null, null, 'klajdsflkjaskdfjkdlsaj jfdasjfdsljdfa flsdjaf', 'kljkjasf', 'fsadf', '2013-08-06 00:35:58', null, null, '2', null, null);
COMMIT;

-- ----------------------------
--  Table structure for `blog_section`
-- ----------------------------
DROP TABLE IF EXISTS `blog_section`;
CREATE TABLE `blog_section` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `section_content` longtext,
  `section_title` varchar(255) DEFAULT NULL,
  `seq` int(11) DEFAULT NULL,
  `blog_blogSetting_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_o1wdqufa0gn7y5q4qvh7rikdd` (`blog_blogSetting_id`),
  CONSTRAINT `FK_o1wdqufa0gn7y5q4qvh7rikdd` FOREIGN KEY (`blog_blogSetting_id`) REFERENCES `blog` (`blogSetting_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `blog_settings`
-- ----------------------------
DROP TABLE IF EXISTS `blog_settings`;
CREATE TABLE `blog_settings` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `price` int(11) DEFAULT NULL,
  `pub_privilege` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `blog_settings`
-- ----------------------------
BEGIN;
INSERT INTO `blog_settings` VALUES ('1', '0', '0'), ('2', '0', '0');
COMMIT;

-- ----------------------------
--  Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) DEFAULT NULL,
  `description` longtext,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_81thrbnb8c08gua7tvqj7xdqk` (`parent_id`),
  CONSTRAINT `FK_81thrbnb8c08gua7tvqj7xdqk` FOREIGN KEY (`parent_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author` varchar(255) DEFAULT NULL,
  `author_uid` varchar(255) DEFAULT NULL,
  `comment_content` longtext,
  `create_time` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `blog_blogSetting_id` bigint(20) DEFAULT NULL,
  `blog_section_id` bigint(20) DEFAULT NULL,
  `at_comment_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ecmisb9d4a8s2urm87ggdkm8j` (`blog_blogSetting_id`),
  KEY `FK_c95n0gw4hyk8or9bs6nptvdtj` (`blog_section_id`),
  KEY `FK_ckuuiksskfqy0nquyp2d03n2y` (`at_comment_id`),
  CONSTRAINT `FK_c95n0gw4hyk8or9bs6nptvdtj` FOREIGN KEY (`blog_section_id`) REFERENCES `blog_section` (`id`),
  CONSTRAINT `FK_ckuuiksskfqy0nquyp2d03n2y` FOREIGN KEY (`at_comment_id`) REFERENCES `comment` (`id`),
  CONSTRAINT `FK_ecmisb9d4a8s2urm87ggdkm8j` FOREIGN KEY (`blog_blogSetting_id`) REFERENCES `blog` (`blogSetting_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `media_info`
-- ----------------------------
DROP TABLE IF EXISTS `media_info`;
CREATE TABLE `media_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `media_addtime` datetime DEFAULT NULL,
  `media_code` varchar(255) DEFAULT NULL,
  `media_name` varchar(255) DEFAULT NULL,
  `media_owner_uid` varchar(255) DEFAULT NULL,
  `media_redundant_url` varchar(255) DEFAULT NULL,
  `media_type` varchar(255) DEFAULT NULL,
  `media_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_8hr17l16jjbw8ytbop2eii3gg` (`media_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `series`
-- ----------------------------
DROP TABLE IF EXISTS `series`;
CREATE TABLE `series` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` longtext,
  `series_name` varchar(255) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_mxi571rtaicay74am72r8c8ax` (`category_id`),
  CONSTRAINT `FK_mxi571rtaicay74am72r8c8ax` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `subscribe`
-- ----------------------------
DROP TABLE IF EXISTS `subscribe`;
CREATE TABLE `subscribe` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `blog_ids` varchar(255) DEFAULT NULL,
  `category_ids` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `enable` tinyint(1) DEFAULT NULL,
  `sery_ids` varchar(255) DEFAULT NULL,
  `subscriber_uid` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_s9f7kc4n2p6gindadmk56i68b` (`subscriber_uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `system_settings`
-- ----------------------------
DROP TABLE IF EXISTS `system_settings`;
CREATE TABLE `system_settings` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `setting_key` varchar(255) DEFAULT NULL,
  `setting_profile` varchar(255) DEFAULT NULL,
  `setting_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `system_settings`
-- ----------------------------
BEGIN;
INSERT INTO `system_settings` VALUES ('1', 'response.type', 'FEOP_OAUTH', 'authorization_code'), ('2', 'client.id', 'FEOP_OAUTH', 'blog.aws.af.cm'), ('3', 'client.secret', 'FEOP_OAUTH', '50183923ab5fa342'), ('4', 'redirect.uri', 'FEOP_OAUTH', null), ('5', 'user.authorization.uri', 'FEOP_OAUTH', 'http://oauth.aws.af.cm/oauth/authorize'), ('6', 'access.token.uri', 'FEOP_OAUTH', 'http://oauth.aws.af.cm/oauth/token'), ('7', 'scope', 'FEOP_OAUTH', null), ('8', 'csrf.enable', 'FEOP_OAUTH', null), ('9', 'user.info.uri', 'FEOP_OAUTH', 'http://oauth.aws.af.cm/userinfo'), ('10', 'oauth.logout.uri', 'FEOP_OAUTH', 'http://oauth.aws.af.cm/oauth/logout.do'), ('11', 'oauth.register.uri', 'FEOP_OAUTH', 'http://oauth.aws.af.cm/signup/'), ('12', 'login.success.uri', 'FEOP_OAUTH', '/');
COMMIT;

-- ----------------------------
--  Table structure for `tags`
-- ----------------------------
DROP TABLE IF EXISTS `tags`;
CREATE TABLE `tags` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
