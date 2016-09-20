-- MySQL dump 10.13  Distrib 5.7.12, for Linux (x86_64)
--
-- Host: localhost    Database: kiu
-- ------------------------------------------------------
-- Server version	5.7.12-0ubuntu1.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `announcement`
--
CREATE DATABASE IF NOT EXISTS kiu;

CREATE USER 'kiu_user'@'localhost' IDENTIFIED BY 'popo';
GRANT DELETE, INSERT, SELECT, UPDATE ON kiu.* TO `kiu_user`@`localhost`;


USE kiu;

DROP TABLE IF EXISTS `post_kiuer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post_kiuer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `open` boolean NOT NULL DEFAULT 0,
  `title` varchar(10) NOT NULL,
  `status` varchar(10) NOT NULL,
  `duration` int(11) NOT NULL,
  `cost` decimal(15,2) NOT NULL,
  `start` datetime NOT NULL,
  `kiuer_id` int(11) NOT NULL,
  `helper_id` int(11) NOT NULL,
  `place_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `kiuer_id` (`kiuer_id`),
  KEY `helper_id` (`helper_id`),
  KEY `place_id` (`place_id`),
  CONSTRAINT `post_kiuer_ibfk_1` FOREIGN KEY (`kiuer_id`) REFERENCES `kiuer` (`id`),
  CONSTRAINT `post_kiuer_ibfk_2` FOREIGN KEY (`helper_id`) REFERENCES `helper` (`id`),
  CONSTRAINT `post_kiuer_ibfk_3` FOREIGN KEY (`place_id`) REFERENCES `place` (`id`)
);
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS `post_helper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post_helper` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `open` boolean NOT NULL DEFAULT 0,
  `title` varchar(10) NOT NULL,
  `city` varchar(10) NOT NULL,
  `cost` decimal(15,2) NOT NULL,
  `start` datetime NOT NULL,
  `end` datetime NOT NULL,
  `helper_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `helper_id` (`helper_id`),
  CONSTRAINT `post_helper_ibfk_1` FOREIGN KEY (`helper_id`) REFERENCES `helper` (`id`)
);
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS `kiuer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kiuer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(200) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `favorite_city` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `helper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `helper` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(200) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `favorite_city` varchar(10) NOT NULL,
  `profile_cost` decimal(15,2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
);
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `place`
--

DROP TABLE IF EXISTS `place`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `place` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(50) NOT NULL,
  `address` varchar(250) NOT NULL,
  `location` varchar(250) NOT NULL,
  PRIMARY KEY (`id`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `operation`
--
DROP TABLE IF EXISTS `operation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operation` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `kiuing`
--

DROP TABLE IF EXISTS `kiuing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kiuing` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `post_kiuer_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `post_kiuer_id` (`post_kiuer_id`),
  CONSTRAINT `kiuing_ibfk_1` FOREIGN KEY (`post_kiuer_id`) REFERENCES `post_kiuer` (`id`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `request_type`
--
DROP TABLE IF EXISTS `kiuing_operation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kiuing_operation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operation_id` varchar(50) NOT NULL,
  `done` boolean NOT NULL DEFAULT 1,
  `kiuing_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `kiuing_id` (`kiuing_id`),
  KEY `operation_id` (`operation_id`),
  CONSTRAINT `kiuing_operation_ibfk_1` FOREIGN KEY (`kiuing_id`) REFERENCES `kiuing` (`id`),
  CONSTRAINT `kiuing_operation_ibfk_2` FOREIGN KEY (`operation_id`) REFERENCES `operation` (`id`)
);
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `request_type`
--
DROP TABLE IF EXISTS `request_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `request_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `helper_request`
--
DROP TABLE IF EXISTS `helper_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `helper_request` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `seen` boolean NOT NULL DEFAULT 1,
  `kiuer_id` int(11) NOT NULL,
  `helper_id` int(11) NOT NULL,
  `post_kiuer_id` int(11) NOT NULL,
  `type_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `kiuer_id` (`kiuer_id`),
  KEY `helper_id` (`helper_id`),
  KEY `post_kiuer_id` (`post_kiuer_id`),
  KEY `type_id` (`type_id`),
  CONSTRAINT `helper_request_ibfk_1` FOREIGN KEY (`kiuer_id`) REFERENCES `kiuer` (`id`),
  CONSTRAINT `helper_request_ibfk_2` FOREIGN KEY (`helper_id`) REFERENCES `helper` (`id`),
  CONSTRAINT `helper_request_ibfk_3` FOREIGN KEY (`post_kiuer_id`) REFERENCES `post_kiuer` (`id`),
  CONSTRAINT `helper_request_ibfk_4` FOREIGN KEY (`type_id`) REFERENCES `request_type` (`id`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `kiuer_request`
--
DROP TABLE IF EXISTS `kiuer_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kiuer_request` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `seen` boolean NOT NULL DEFAULT 1,
  `kiuer_id` int(11) NOT NULL,
  `helper_id` int(11) NOT NULL,
  `post_kiuer_id` int(11) NOT NULL,
  `type_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `kiuer_id` (`kiuer_id`),
  KEY `helper_id` (`helper_id`),
  KEY `post_kiuer_id` (`post_kiuer_id`),
  KEY `type_id` (`type_id`),
  CONSTRAINT `kiuer_request_ibfk_1` FOREIGN KEY (`kiuer_id`) REFERENCES `kiuer` (`id`),
  CONSTRAINT `kiuer_request_ibfk_2` FOREIGN KEY (`helper_id`) REFERENCES `helper` (`id`),
  CONSTRAINT `kiuer_request_ibfk_3` FOREIGN KEY (`post_kiuer_id`) REFERENCES `post_kiuer` (`id`),
  CONSTRAINT `kiuer_request_ibfk_4` FOREIGN KEY (`type_id`) REFERENCES `request_type` (`id`)
);
/*!40101 SET character_set_client = @saved_cs_client */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-07-03 21:46:35
