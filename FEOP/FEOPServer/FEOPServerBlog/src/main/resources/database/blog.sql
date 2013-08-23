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

 Date: 08/03/2013 19:06:55 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `blog`
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `blog_title` varchar(255) NOT NULL,
  `blog_subtitle` varchar(255) DEFAULT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_update` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `author_name` varchar(255) NOT NULL,
  `author_uid` varchar(255) NOT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `blog_desc` text NOT NULL,
  `blog_setting` bigint(20) NOT NULL,
  `series_id` bigint(20) DEFAULT NULL,
  `tag_ids` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `series_id` (`series_id`),
  KEY `category_id` (`category_id`),
  KEY `blog_setting` (`blog_setting`),
  CONSTRAINT `blog_ibfk_1` FOREIGN KEY (`series_id`) REFERENCES `series` (`id`),
  CONSTRAINT `blog_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `blog_ibfk_3` FOREIGN KEY (`blog_setting`) REFERENCES `blog_settings` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `blog_section`
-- ----------------------------
DROP TABLE IF EXISTS `blog_section`;
CREATE TABLE `blog_section` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `section_title` varchar(255) NOT NULL,
  `section_content` text NOT NULL,
  `sequence` int(11) NOT NULL,
  `blog_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `blog_id` (`blog_id`),
  CONSTRAINT `blog_section_ibfk_1` FOREIGN KEY (`blog_id`) REFERENCES `blog` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `blog_settings`
-- ----------------------------
DROP TABLE IF EXISTS `blog_settings`;
CREATE TABLE `blog_settings` (
  `id` bigint(20) NOT NULL,
  `pub_privilege` bit(1) NOT NULL,
  `price` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) NOT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`),
  CONSTRAINT `category_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `commons`
-- ----------------------------
DROP TABLE IF EXISTS `commons`;
CREATE TABLE `commons` (
  `id` bigint(20) NOT NULL,
  `common_content` text NOT NULL,
  `status` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `author` varchar(255) DEFAULT NULL,
  `author_uid` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `blog_id` bigint(20) NOT NULL,
  `blog_section_id` bigint(20) DEFAULT NULL,
  `at_common_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `blog_id` (`blog_id`),
  KEY `at_common_id` (`at_common_id`),
  KEY `blog_section_id` (`blog_section_id`),
  CONSTRAINT `commons_ibfk_1` FOREIGN KEY (`blog_id`) REFERENCES `blog` (`id`),
  CONSTRAINT `commons_ibfk_2` FOREIGN KEY (`at_common_id`) REFERENCES `commons` (`id`),
  CONSTRAINT `commons_ibfk_3` FOREIGN KEY (`blog_section_id`) REFERENCES `blog_section` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `media_info`
-- ----------------------------
DROP TABLE IF EXISTS `media_info`;
CREATE TABLE `media_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `media_name` varchar(255) NOT NULL,
  `media_url` varchar(255) NOT NULL,
  `media_code` varchar(255) NOT NULL,
  `media_type` varchar(255) NOT NULL,
  `media_ redundant_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `media_code` (`media_code`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `series`
-- ----------------------------
DROP TABLE IF EXISTS `series`;
CREATE TABLE `series` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `series_name` varchar(255) NOT NULL,
  `description` text,
  `category_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `series_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `subscribe`
-- ----------------------------
DROP TABLE IF EXISTS `subscribe`;
CREATE TABLE `subscribe` (
  `id` bigint(20) NOT NULL,
  `subscriber_uid` varchar(255) NOT NULL,
  `topic_id` bigint(20) NOT NULL,
  `enable` bit(1) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  KEY `topic_id` (`topic_id`),
  CONSTRAINT `subscribe_ibfk_1` FOREIGN KEY (`topic_id`) REFERENCES `subscribe_topic` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `subscribe_topic`
-- ----------------------------
DROP TABLE IF EXISTS `subscribe_topic`;
CREATE TABLE `subscribe_topic` (
  `id` bigint(20) NOT NULL,
  `topic_name` varchar(255) NOT NULL,
  `public_usage` bit(1) NOT NULL,
  `blog_id` bigint(20) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `series_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `blog_id` (`blog_id`),
  KEY `category_id` (`category_id`),
  KEY `series_id` (`series_id`),
  CONSTRAINT `subscribe_topic_ibfk_1` FOREIGN KEY (`blog_id`) REFERENCES `blog` (`id`),
  CONSTRAINT `subscribe_topic_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `subscribe_topic_ibfk_3` FOREIGN KEY (`series_id`) REFERENCES `series` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tags`
-- ----------------------------
DROP TABLE IF EXISTS `tags`;
CREATE TABLE `tags` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
