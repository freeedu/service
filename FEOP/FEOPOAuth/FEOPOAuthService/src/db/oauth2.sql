/*
 Navicat Premium Data Transfer

 Source Server         : Localhost
 Source Server Type    : MySQL
 Source Server Version : 50521
 Source Host           : localhost
 Source Database       : oauth2

 Target Server Type    : MySQL
 Target Server Version : 50521
 File Encoding         : utf-8

 Date: 06/17/2013 23:20:04 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `oauth_access_token`
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(256) DEFAULT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `oauth_client_details`
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `client_id` varchar(256) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT '0',
  `refresh_token_validity` int(11) DEFAULT '0',
  `additional_information` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `oauth_client_details`
-- ----------------------------
BEGIN;
INSERT INTO `oauth_client_details` VALUES ('1', 'my-trusted-client', null, null, 'read,write,trust', 'password,authorization_code,refresh_token,implicit', null, 'ROLE_CLIENT,ROLE_TRUSTED_CLIENT', '60', '0', null), ('2', 'client', 'sparklr', 'secret', 'read,write', 'authorization_code,implicit', null, 'ROLE_CLIENT', '0', '0', null);
COMMIT;

-- ----------------------------
--  Table structure for `oauth_client_token`
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(256) DEFAULT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `oauth_code`
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(256) DEFAULT NULL,
  `authentication` blob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `oauth_code`
-- ----------------------------
BEGIN;
INSERT INTO `oauth_code` VALUES ('1', 'TcRzTj', 0xaced00057372004c6f72672e737072696e676672616d65776f726b2e73656375726974792e6f61757468322e70726f76696465722e636f64652e417574686f72697a6174696f6e52657175657374486f6c6465720cb29e361b24face0200024c0014617574686f72697a6174696f6e526571756573747400434c6f72672f737072696e676672616d65776f726b2f73656375726974792f6f61757468322f70726f76696465722f417574686f72697a6174696f6e526571756573743b4c00127573657241757468656e7469636174696f6e7400324c6f72672f737072696e676672616d65776f726b2f73656375726974792f636f72652f41757468656e7469636174696f6e3b7870737200486f72672e737072696e676672616d65776f726b2e73656375726974792e6f61757468322e70726f76696465722e44656661756c74417574686f72697a6174696f6e52657175657374dd2f6c1608186d180200075a0008617070726f7665644c0012617070726f76616c506172616d657465727374000f4c6a6176612f7574696c2f4d61703b4c000b617574686f7269746965737400164c6a6176612f7574696c2f436f6c6c656374696f6e3b4c0017617574686f72697a6174696f6e506172616d657465727371007e00054c00137265736f6c76656452656469726563745572697400124c6a6176612f6c616e672f537472696e673b4c000b7265736f7572636549647374000f4c6a6176612f7574696c2f5365743b4c000573636f706571007e0008787001737200116a6176612e7574696c2e486173684d61700507dac1c31660d103000246000a6c6f6164466163746f724900097468726573686f6c6478703f4000000000000c77080000001000000002740009617574686f72697a65740006e68e88e69d83740013757365725f6f617574685f617070726f76616c7400047472756578737200116a6176612e7574696c2e48617368536574ba44859596b8b7340300007870770c000000103f40000000000001737200426f72672e737072696e676672616d65776f726b2e73656375726974792e636f72652e617574686f726974792e53696d706c654772616e746564417574686f7269747900000000000001360200014c0004726f6c6571007e0007787074000b524f4c455f434c49454e5478737200266a6176612e7574696c2e636f6e63757272656e742e436f6e63757272656e74486173684d61706499de129d87293d03000349000b7365676d656e744d61736b49000c7365676d656e7453686966745b00087365676d656e74737400315b4c6a6176612f7574696c2f636f6e63757272656e742f436f6e63757272656e74486173684d6170245365676d656e743b78700000000f0000001c757200315b4c6a6176612e7574696c2e636f6e63757272656e742e436f6e63757272656e74486173684d6170245365676d656e743b52773f41329b39740200007870000000107372002e6a6176612e7574696c2e636f6e63757272656e742e436f6e63757272656e74486173684d6170245365676d656e741f364c905893293d02000146000a6c6f6164466163746f72787200286a6176612e7574696c2e636f6e63757272656e742e6c6f636b732e5265656e7472616e744c6f636b6655a82c2cc86aeb0200014c000473796e6374002f4c6a6176612f7574696c2f636f6e63757272656e742f6c6f636b732f5265656e7472616e744c6f636b2453796e633b7870737200346a6176612e7574696c2e636f6e63757272656e742e6c6f636b732e5265656e7472616e744c6f636b244e6f6e6661697253796e63658832e7537bbf0b0200007872002d6a6176612e7574696c2e636f6e63757272656e742e6c6f636b732e5265656e7472616e744c6f636b2453796e63b81ea294aa445a7c020000787200356a6176612e7574696c2e636f6e63757272656e742e6c6f636b732e416273747261637451756575656453796e6368726f6e697a65726655a843753f52e30200014900057374617465787200366a6176612e7574696c2e636f6e63757272656e742e6c6f636b732e41627374726163744f776e61626c6553796e6368726f6e697a657233dfafb9ad6d6fa90200007870000000003f4000007371007e001a7371007e001e000000003f4000007371007e001a7371007e001e000000003f4000007371007e001a7371007e001e000000003f4000007371007e001a7371007e001e000000003f4000007371007e001a7371007e001e000000003f4000007371007e001a7371007e001e000000003f4000007371007e001a7371007e001e000000003f4000007371007e001a7371007e001e000000003f4000007371007e001a7371007e001e000000003f4000007371007e001a7371007e001e000000003f4000007371007e001a7371007e001e000000003f4000007371007e001a7371007e001e000000003f4000007371007e001a7371007e001e000000003f4000007371007e001a7371007e001e000000003f4000007371007e001a7371007e001e000000003f40000074000c72656469726563745f757269740015687474703a2f2f7777772e62616964752e636f6d2f74000d726573706f6e73655f74797065740004636f6465740009636c69656e745f6964740006636c69656e7474000573636f706574000a7772697465207265616470707871007e00427371007e0010770c000000103f40000000000001740007737061726b6c7278737200176a6176612e7574696c2e4c696e6b656448617368536574d86cd75a95dd2a1e0200007871007e0010770c000000103f40000000000002740005777269746574000472656164787372004f6f72672e737072696e676672616d65776f726b2e73656375726974792e61757468656e7469636174696f6e2e557365726e616d6550617373776f726441757468656e7469636174696f6e546f6b656e00000000000001360200024c000b63726564656e7469616c737400124c6a6176612f6c616e672f4f626a6563743b4c00097072696e636970616c71007e0050787200476f72672e737072696e676672616d65776f726b2e73656375726974792e61757468656e7469636174696f6e2e416273747261637441757468656e7469636174696f6e546f6b656ed3aa287e6e47640e0200035a000d61757468656e746963617465644c000b617574686f72697469657371007e00064c000764657461696c7371007e0050787001737200266a6176612e7574696c2e436f6c6c656374696f6e7324556e6d6f6469666961626c654c697374fc0f2531b5ec8e100200014c00046c6973747400104c6a6176612f7574696c2f4c6973743b7872002c6a6176612e7574696c2e436f6c6c656374696f6e7324556e6d6f6469666961626c65436f6c6c656374696f6e19420080cb5ef71e0200014c00016371007e00067870737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a657870000000027704000000027371007e001274000541444d494e7371007e0012740009524f4c455f555345527871007e0058737200486f72672e737072696e676672616d65776f726b2e73656375726974792e7765622e61757468656e7469636174696f6e2e57656241757468656e7469636174696f6e44657461696c7300000000000001360200024c000d72656d6f74654164647265737371007e00074c000973657373696f6e496471007e000778707400093132372e302e302e31740020394139323932373544303641464334303435373130363131393846444338343570737200326f72672e737072696e676672616d65776f726b2e73656375726974792e636f72652e7573657264657461696c732e5573657200000000000001360200075a00116163636f756e744e6f6e457870697265645a00106163636f756e744e6f6e4c6f636b65645a001563726564656e7469616c734e6f6e457870697265645a0007656e61626c65644c000b617574686f72697469657371007e00084c000870617373776f726471007e00074c0008757365726e616d6571007e0007787001010101737200256a6176612e7574696c2e436f6c6c656374696f6e7324556e6d6f6469666961626c65536574801d92d18f9b80550200007871007e0055737200116a6176612e7574696c2e54726565536574dd98509395ed875b0300007870737200466f72672e737072696e676672616d65776f726b2e73656375726974792e636f72652e7573657264657461696c732e5573657224417574686f72697479436f6d70617261746f720000000000000136020000787077040000000271007e005971007e005b78707400136d61736f6e2e6d656940676d61696c2e636f6d), ('2', 'cEOs8G', 0xaced00057372004c6f72672e737072696e676672616d65776f726b2e73656375726974792e6f61757468322e70726f76696465722e636f64652e417574686f72697a6174696f6e52657175657374486f6c6465720cb29e361b24face0200024c0014617574686f72697a6174696f6e526571756573747400434c6f72672f737072696e676672616d65776f726b2f73656375726974792f6f61757468322f70726f76696465722f417574686f72697a6174696f6e526571756573743b4c00127573657241757468656e7469636174696f6e7400324c6f72672f737072696e676672616d65776f726b2f73656375726974792f636f72652f41757468656e7469636174696f6e3b7870737200486f72672e737072696e676672616d65776f726b2e73656375726974792e6f61757468322e70726f76696465722e44656661756c74417574686f72697a6174696f6e52657175657374dd2f6c1608186d180200075a0008617070726f7665644c0012617070726f76616c506172616d657465727374000f4c6a6176612f7574696c2f4d61703b4c000b617574686f7269746965737400164c6a6176612f7574696c2f436f6c6c656374696f6e3b4c0017617574686f72697a6174696f6e506172616d657465727371007e00054c00137265736f6c76656452656469726563745572697400124c6a6176612f6c616e672f537472696e673b4c000b7265736f7572636549647374000f4c6a6176612f7574696c2f5365743b4c000573636f706571007e0008787001737200116a6176612e7574696c2e486173684d61700507dac1c31660d103000246000a6c6f6164466163746f724900097468726573686f6c6478703f4000000000000c77080000001000000002740009617574686f72697a65740009417574686f72697a65740013757365725f6f617574685f617070726f76616c7400047472756578737200116a6176612e7574696c2e48617368536574ba44859596b8b7340300007870770c000000103f40000000000001737200426f72672e737072696e676672616d65776f726b2e73656375726974792e636f72652e617574686f726974792e53696d706c654772616e746564417574686f7269747900000000000001360200014c0004726f6c6571007e0007787074000b524f4c455f434c49454e5478737200266a6176612e7574696c2e636f6e63757272656e742e436f6e63757272656e74486173684d61706499de129d87293d03000349000b7365676d656e744d61736b49000c7365676d656e7453686966745b00087365676d656e74737400315b4c6a6176612f7574696c2f636f6e63757272656e742f436f6e63757272656e74486173684d6170245365676d656e743b78700000000f0000001c757200315b4c6a6176612e7574696c2e636f6e63757272656e742e436f6e63757272656e74486173684d6170245365676d656e743b52773f41329b39740200007870000000107372002e6a6176612e7574696c2e636f6e63757272656e742e436f6e63757272656e74486173684d6170245365676d656e741f364c905893293d02000146000a6c6f6164466163746f72787200286a6176612e7574696c2e636f6e63757272656e742e6c6f636b732e5265656e7472616e744c6f636b6655a82c2cc86aeb0200014c000473796e6374002f4c6a6176612f7574696c2f636f6e63757272656e742f6c6f636b732f5265656e7472616e744c6f636b2453796e633b7870737200346a6176612e7574696c2e636f6e63757272656e742e6c6f636b732e5265656e7472616e744c6f636b244e6f6e6661697253796e63658832e7537bbf0b0200007872002d6a6176612e7574696c2e636f6e63757272656e742e6c6f636b732e5265656e7472616e744c6f636b2453796e63b81ea294aa445a7c020000787200356a6176612e7574696c2e636f6e63757272656e742e6c6f636b732e416273747261637451756575656453796e6368726f6e697a65726655a843753f52e30200014900057374617465787200366a6176612e7574696c2e636f6e63757272656e742e6c6f636b732e41627374726163744f776e61626c6553796e6368726f6e697a657233dfafb9ad6d6fa90200007870000000003f4000007371007e001a7371007e001e000000003f4000007371007e001a7371007e001e000000003f4000007371007e001a7371007e001e000000003f4000007371007e001a7371007e001e000000003f4000007371007e001a7371007e001e000000003f4000007371007e001a7371007e001e000000003f4000007371007e001a7371007e001e000000003f4000007371007e001a7371007e001e000000003f4000007371007e001a7371007e001e000000003f4000007371007e001a7371007e001e000000003f4000007371007e001a7371007e001e000000003f4000007371007e001a7371007e001e000000003f4000007371007e001a7371007e001e000000003f4000007371007e001a7371007e001e000000003f4000007371007e001a7371007e001e000000003f40000074000c72656469726563745f757269740015687474703a2f2f7777772e62616964752e636f6d2f74000d726573706f6e73655f74797065740004636f6465740009636c69656e745f6964740006636c69656e7474000573636f706574000a7772697465207265616470707871007e00427371007e0010770c000000103f40000000000001740007737061726b6c7278737200176a6176612e7574696c2e4c696e6b656448617368536574d86cd75a95dd2a1e0200007871007e0010770c000000103f40000000000002740005777269746574000472656164787372004f6f72672e737072696e676672616d65776f726b2e73656375726974792e61757468656e7469636174696f6e2e557365726e616d6550617373776f726441757468656e7469636174696f6e546f6b656e00000000000001360200024c000b63726564656e7469616c737400124c6a6176612f6c616e672f4f626a6563743b4c00097072696e636970616c71007e0050787200476f72672e737072696e676672616d65776f726b2e73656375726974792e61757468656e7469636174696f6e2e416273747261637441757468656e7469636174696f6e546f6b656ed3aa287e6e47640e0200035a000d61757468656e746963617465644c000b617574686f72697469657371007e00064c000764657461696c7371007e0050787001737200266a6176612e7574696c2e436f6c6c656374696f6e7324556e6d6f6469666961626c654c697374fc0f2531b5ec8e100200014c00046c6973747400104c6a6176612f7574696c2f4c6973743b7872002c6a6176612e7574696c2e436f6c6c656374696f6e7324556e6d6f6469666961626c65436f6c6c656374696f6e19420080cb5ef71e0200014c00016371007e00067870737200136a6176612e7574696c2e41727261794c6973747881d21d99c7619d03000149000473697a657870000000027704000000027371007e001274000541444d494e7371007e0012740009524f4c455f555345527871007e0058737200486f72672e737072696e676672616d65776f726b2e73656375726974792e7765622e61757468656e7469636174696f6e2e57656241757468656e7469636174696f6e44657461696c7300000000000001360200024c000d72656d6f74654164647265737371007e00074c000973657373696f6e496471007e000778707400093132372e302e302e31740020334633344430453646344345383946303842453636414242303841454636444170737200326f72672e737072696e676672616d65776f726b2e73656375726974792e636f72652e7573657264657461696c732e5573657200000000000001360200075a00116163636f756e744e6f6e457870697265645a00106163636f756e744e6f6e4c6f636b65645a001563726564656e7469616c734e6f6e457870697265645a0007656e61626c65644c000b617574686f72697469657371007e00084c000870617373776f726471007e00074c0008757365726e616d6571007e0007787001010101737200256a6176612e7574696c2e436f6c6c656374696f6e7324556e6d6f6469666961626c65536574801d92d18f9b80550200007871007e0055737200116a6176612e7574696c2e54726565536574dd98509395ed875b0300007870737200466f72672e737072696e676672616d65776f726b2e73656375726974792e636f72652e7573657264657461696c732e5573657224417574686f72697479436f6d70617261746f720000000000000136020000787077040000000271007e005971007e005b78707400136d61736f6e2e6d656940676d61696c2e636f6d);
COMMIT;

-- ----------------------------
--  Table structure for `oauth_refresh_token`
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication` blob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `roles`
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `enabled` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `roles`
-- ----------------------------
BEGIN;
INSERT INTO `roles` VALUES ('1', 'ADMIN', b'1'), ('2', 'ROLE_USER', b'1');
COMMIT;

-- ----------------------------
--  Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) NOT NULL,
  `user_name` varchar(30) DEFAULT NULL,
  `email` varchar(80) NOT NULL,
  `activated` bit(1) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`) USING BTREE,
  UNIQUE KEY `user_name` (`user_name`) USING BTREE,
  KEY `phone` (`phone`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `users`
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES ('1', '85adecfd-b0fb-4e7e-b8b8-f76c445f9855', 'mason_mei', 'mason.mei@gmail.com', b'1', '2a7b9cce1c47fdb745b1f6cd331301d77c3088e4c382a50e7379725d86e9e5c4b1834e309b37f51e', null), ('2', '384ead31-8e2e-4e82-a63d-20b8ddbe1c13', 'dongxu_mei', 'dongxu.m@gmail.com', b'1', 'a82e7026c41ac7ab44697dbf6a80be548ebc02673890a5512bd2ac2f5b00c495657a6a21d7b053f1', null), ('3', '89786734-b3e9-4cf6-beeb-7eaf99d4dab1', 'a mei', 'a.mei@gmail.com', b'1', '324194bbedd170aa0862609f19a2e3a0ac9577ad2b7211f4d8ff418bf88a10d50befc31c736556da', null);
COMMIT;

-- ----------------------------
--  Table structure for `users_roles`
-- ----------------------------
DROP TABLE IF EXISTS `users_roles`;
CREATE TABLE `users_roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `role_id` (`role_id`) USING BTREE,
  KEY `FK_k2mq1ee4ob6uw649wgaus1ate` (`role_id`),
  KEY `FK_1hjw31qvltj7v3wb5o31jsrd8` (`user_id`),
  CONSTRAINT `FK_1hjw31qvltj7v3wb5o31jsrd8` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK_k2mq1ee4ob6uw649wgaus1ate` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `users_roles`
-- ----------------------------
BEGIN;
INSERT INTO `users_roles` VALUES ('1', '1', '1'), ('2', '1', '2'), ('3', '3', '2');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
