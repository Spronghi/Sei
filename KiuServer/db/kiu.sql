-- MySQL dump 10.13  Distrib 5.7.15, for Linux (x86_64)
--
-- Host: localhost    Database: kiu
-- ------------------------------------------------------
-- Server version	5.7.15-0ubuntu0.16.04.1

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
-- Table structure for table `helper`
--
CREATE DATABASE IF NOT EXISTS kiu;

CREATE USER 'kiu_user'@'localhost' IDENTIFIED BY 'popo';
GRANT DELETE, INSERT, SELECT, UPDATE ON kiu.* TO `kiu_user`@`localhost`;

USE kiu;

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `helper`
--

LOCK TABLES `helper` WRITE;
/*!40000 ALTER TABLE `helper` DISABLE KEYS */;
INSERT INTO `helper` VALUES (1,'madassa@gmail.com','Baldasse','92eb5ffee6ae2fec3ad71c777531578f','Lecce',5.00),(2,'s.h@vs.com','so','b8723f87e63b8ada92f79f546ff9cc','lecce',2.00),(3,'nobody@nobody.com','nobody','a538abf561e4e3157fae9132ff79f0d4','nobody',0.00),(4,'fabri@gmail.com','Fabri','9f3ce0a3cd30f4a852d8e8817318a2d','Milano',10.00),(5,'piano@gmail.com','Piano','d113f1c3f9ed8019288f4e8ddecfb8ec','Milano',6.00),(6,'lambda@gmail.com','Lambda','945f3fc449518a73b9f5f32868db466c','Milano',10.00);
/*!40000 ALTER TABLE `helper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `helper_request`
--

DROP TABLE IF EXISTS `helper_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `helper_request` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `seen` tinyint(1) NOT NULL DEFAULT '1',
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
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `helper_request`
--

LOCK TABLES `helper_request` WRITE;
/*!40000 ALTER TABLE `helper_request` DISABLE KEYS */;
INSERT INTO `helper_request` VALUES (1,1,1,1,1,2),(2,1,1,1,1,2),(3,1,1,1,1,2),(4,1,1,1,1,2),(5,1,1,1,1,2),(6,1,1,1,1,3),(7,1,1,1,1,2),(8,1,1,1,1,2),(9,1,1,1,1,2),(10,1,1,1,1,2),(11,1,1,1,1,2),(12,1,1,1,1,2),(13,1,1,1,1,2),(14,1,1,1,1,2),(15,1,1,1,1,2),(16,1,1,1,1,2),(17,1,1,1,1,2),(18,1,1,1,1,2),(19,1,1,1,1,2),(20,1,1,1,1,2),(21,1,1,1,1,2),(22,1,1,1,1,2),(23,1,1,1,1,3),(24,1,1,1,1,3),(25,1,1,1,1,3),(26,1,1,1,1,2),(27,1,1,1,3,2),(28,1,1,1,3,3),(29,0,1,1,6,2);
/*!40000 ALTER TABLE `helper_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kiuer`
--

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kiuer`
--

LOCK TABLES `kiuer` WRITE;
/*!40000 ALTER TABLE `kiuer` DISABLE KEYS */;
INSERT INTO `kiuer` VALUES (1,'spronghi@gmail.com','Spronghi','cc175b9c0f1b6a831c399e269772661','Lecce'),(4,'a@jd.kc','so','163dac4b988686f2cb560b4b8406d9a','Lecce'),(5,'giulio@gmail.com','giulio','bd58d9594cbd9a64fbe8a12b4a8e17','Lecce'),(6,'fiore@gmail.com','fiore','de4366386db4fa5dce2030568416b0','Bologna'),(7,'poro@gmail.com','Poro','c47264d2d77fa9b5632c381a13393b1','Milano');
/*!40000 ALTER TABLE `kiuer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kiuer_request`
--

DROP TABLE IF EXISTS `kiuer_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kiuer_request` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `seen` tinyint(1) NOT NULL DEFAULT '1',
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
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kiuer_request`
--

LOCK TABLES `kiuer_request` WRITE;
/*!40000 ALTER TABLE `kiuer_request` DISABLE KEYS */;
INSERT INTO `kiuer_request` VALUES (1,1,1,1,1,1),(2,1,1,1,1,1),(3,1,1,1,1,1),(4,1,1,1,1,1),(5,1,1,1,1,1),(6,1,1,1,1,1),(7,1,1,1,1,1),(8,1,1,1,1,1),(9,1,1,1,1,1),(10,1,1,1,1,1),(11,1,1,1,1,1),(12,1,1,1,1,1),(13,1,1,1,1,1),(14,1,1,1,1,1),(15,1,1,1,1,1),(16,1,1,1,1,1),(17,1,1,1,1,1),(18,1,1,1,1,1),(19,1,1,1,1,1),(20,1,1,1,1,1),(21,1,1,1,1,1),(22,1,1,1,1,1),(23,1,1,1,1,1),(24,1,1,1,1,1),(25,1,1,1,1,1),(26,1,1,1,1,1),(27,1,1,1,1,1),(28,1,1,1,1,1),(29,1,1,1,1,1),(30,1,1,1,1,3),(31,1,1,1,1,3),(32,1,1,1,1,3),(33,1,1,1,1,3),(34,1,1,1,1,3),(35,1,1,1,1,1),(36,1,1,1,1,1),(37,1,1,1,1,1),(38,1,1,1,1,1),(39,1,1,1,1,1),(40,1,1,1,1,1),(41,1,1,1,1,1),(42,1,1,1,1,1),(43,1,1,1,1,3),(44,1,1,1,1,1),(45,1,1,1,3,1),(46,1,1,1,3,1),(47,1,1,1,3,3),(48,1,1,1,6,1);
/*!40000 ALTER TABLE `kiuer_request` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kiuing`
--

LOCK TABLES `kiuing` WRITE;
/*!40000 ALTER TABLE `kiuing` DISABLE KEYS */;
INSERT INTO `kiuing` VALUES (2,1),(3,1),(4,1),(5,1),(6,1),(7,1),(8,1),(9,1),(10,1),(11,1),(12,1),(13,1),(14,1),(15,1),(16,1),(17,1),(18,1),(19,3),(20,6);
/*!40000 ALTER TABLE `kiuing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kiuing_operation`
--

DROP TABLE IF EXISTS `kiuing_operation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kiuing_operation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `done` tinyint(1) NOT NULL DEFAULT '1',
  `kiuing_id` int(11) NOT NULL,
  `operation_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `kiuing_id` (`kiuing_id`),
  KEY `operation_id` (`operation_id`),
  CONSTRAINT `kiuing_operation_ibfk_1` FOREIGN KEY (`kiuing_id`) REFERENCES `kiuing` (`id`),
  CONSTRAINT `kiuing_operation_ibfk_2` FOREIGN KEY (`operation_id`) REFERENCES `operation` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kiuing_operation`
--

LOCK TABLES `kiuing_operation` WRITE;
/*!40000 ALTER TABLE `kiuing_operation` DISABLE KEYS */;
INSERT INTO `kiuing_operation` VALUES (1,0,3,4),(2,0,3,5),(3,0,3,6),(4,0,3,7),(5,0,4,4),(6,0,4,5),(7,0,4,6),(8,0,4,7),(9,0,5,4),(10,0,5,5),(11,0,5,6),(12,0,5,7),(13,1,6,4),(14,1,6,5),(15,1,6,6),(16,1,6,7),(17,0,7,4),(18,0,7,5),(19,0,7,6),(20,0,7,7),(21,0,8,4),(22,0,8,5),(23,0,8,6),(24,0,8,7),(25,0,9,4),(26,0,9,5),(27,0,9,6),(28,0,9,7),(29,0,10,4),(30,0,10,5),(31,0,10,6),(32,0,10,7),(33,0,11,4),(34,0,11,5),(35,0,11,6),(36,0,11,7),(37,0,12,4),(38,0,12,5),(39,0,12,6),(40,0,12,7),(41,0,13,4),(42,0,13,5),(43,0,13,6),(44,0,13,7),(45,0,14,4),(46,0,14,5),(47,0,14,6),(48,0,14,7),(49,0,15,4),(50,0,15,5),(51,0,15,6),(52,0,15,7),(53,0,16,4),(54,0,16,5),(55,0,16,6),(56,0,16,7),(57,0,17,4),(58,0,17,5),(59,0,17,6),(60,0,17,7),(61,0,18,4),(62,0,18,5),(63,0,18,6),(64,0,18,7),(65,0,19,4),(66,0,19,5),(67,0,19,6),(68,0,19,7),(69,1,20,4),(70,1,20,5),(71,1,20,6),(72,1,20,7);
/*!40000 ALTER TABLE `kiuing_operation` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operation`
--

LOCK TABLES `operation` WRITE;
/*!40000 ALTER TABLE `operation` DISABLE KEYS */;
INSERT INTO `operation` VALUES (4,'going'),(5,'arrived'),(6,'kiuing'),(7,'finished');
/*!40000 ALTER TABLE `operation` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `place`
--

LOCK TABLES `place` WRITE;
/*!40000 ALTER TABLE `place` DISABLE KEYS */;
INSERT INTO `place` VALUES (1,'Lecce','ViaRonzoli','PizzeriaRonzoli'),(2,'lec','bd','hd'),(3,'Lecce','via Doroti','Pizza Orologi'),(4,'Milano','Via Luigi IX','Pizza Pazza'),(5,'Lecce','Via Leoncavallo','Poste Centrali'),(6,'Lecce','Via Giuseppe Verdi','Pizza Pazza'),(7,'Lecce','Viale Marche','Ivision');
/*!40000 ALTER TABLE `place` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_kiuer`
--

DROP TABLE IF EXISTS `post_kiuer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post_kiuer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `open` tinyint(1) NOT NULL DEFAULT '0',
  `duration` int(11) NOT NULL,
  `cost` decimal(15,2) NOT NULL,
  `start` datetime NOT NULL,
  `to_helper_feedback` decimal(15,2) NOT NULL,
  `to_kiuer_feedback` decimal(15,2) NOT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_kiuer`
--

LOCK TABLES `post_kiuer` WRITE;
/*!40000 ALTER TABLE `post_kiuer` DISABLE KEYS */;
INSERT INTO `post_kiuer` VALUES (1,0,10,10.00,'2016-09-28 15:29:00',4.50,4.00,1,1,1),(2,1,10,5.00,'2016-10-05 10:37:00',0.00,0.00,1,3,2),(3,0,10,5.00,'2016-10-05 10:48:00',0.00,3.00,1,1,3),(4,1,5,10.00,'2016-10-07 15:56:00',0.00,0.00,7,3,4),(5,1,10,5.00,'2016-10-06 17:42:00',0.00,0.00,1,3,5),(6,0,100,5.00,'2016-10-06 17:57:00',3.00,0.00,1,1,6),(7,1,10,5.00,'2016-10-06 18:07:00',0.00,0.00,1,3,7);
/*!40000 ALTER TABLE `post_kiuer` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request_type`
--

LOCK TABLES `request_type` WRITE;
/*!40000 ALTER TABLE `request_type` DISABLE KEYS */;
INSERT INTO `request_type` VALUES (1,'request'),(2,'accept'),(3,'refuse');
/*!40000 ALTER TABLE `request_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-08  9:20:20
