/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50562
Source Host           : localhost:3306
Source Database       : home-video

Target Server Type    : MYSQL
Target Server Version : 50562
File Encoding         : 65001

Date: 2019-05-26 11:39:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_film
-- ----------------------------
DROP TABLE IF EXISTS `tb_film`;
CREATE TABLE `tb_film` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `img` text COLLATE utf8mb4_unicode_ci,
  `title` text COLLATE utf8mb4_unicode_ci,
  `actors` text COLLATE utf8mb4_unicode_ci,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `language` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  `source` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `anotherName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `region` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `briefIntroduction` text COLLATE utf8mb4_unicode_ci,
  `director` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `videoItemUrl` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `type1` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `parser` bit(1) DEFAULT b'1',
  `score` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29281 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for tb_film_copy
-- ----------------------------
DROP TABLE IF EXISTS `tb_film_copy`;
CREATE TABLE `tb_film_copy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `img` text COLLATE utf8mb4_unicode_ci,
  `title` text COLLATE utf8mb4_unicode_ci,
  `actors` text COLLATE utf8mb4_unicode_ci,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `language` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  `source` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `anotherName` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `region` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `briefIntroduction` text COLLATE utf8mb4_unicode_ci,
  `director` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `videoItemUrl` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `type1` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `parser` bit(1) DEFAULT b'1',
  `score` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7651 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for tb_tvsrc
-- ----------------------------
DROP TABLE IF EXISTS `tb_tvsrc`;
CREATE TABLE `tb_tvsrc` (
  `name` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `type` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `link` varchar(255) COLLATE utf8mb4_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for tb_videoitem
-- ----------------------------
DROP TABLE IF EXISTS `tb_videoitem`;
CREATE TABLE `tb_videoitem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `url` text COLLATE utf8mb4_unicode_ci,
  `filmId` int(11) DEFAULT NULL,
  `playbackLine` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `serialNumber` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=943239 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for tb_videoitem_copy
-- ----------------------------
DROP TABLE IF EXISTS `tb_videoitem_copy`;
CREATE TABLE `tb_videoitem_copy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `url` text COLLATE utf8mb4_unicode_ci,
  `filmId` int(11) DEFAULT NULL,
  `playbackLine` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `serialNumber` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=108478 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for temp
-- ----------------------------
DROP TABLE IF EXISTS `temp`;
CREATE TABLE `temp` (
  `filmId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Procedure structure for searchSaveVideoItem
-- ----------------------------
DROP PROCEDURE IF EXISTS `searchSaveVideoItem`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `searchSaveVideoItem`(filmId INT, serialNumber INT, url varchar(256), title VARCHAR(256))
BEGIN
	DECLARE count int;  
	SELECT count(*) INTO count
	from tb_film, tb_videoitem 
	where tb_film.id = tb_videoitem.filmId AND
	tb_film.id = filmId AND
	tb_videoitem.title = title;

	if count =0 THEN
		INSERT into tb_videoitem (filmId, url, title, serialNumber)
		VALUES(filmId, url, title, serialNumber);
	END IF;
	SELECT * from tb_videoitem
	WHERE tb_videoitem.filmId = filmId and
	tb_videoitem.serialNumber = serialNumber AND
	tb_videoitem.title=title;
end
;;
DELIMITER ;
