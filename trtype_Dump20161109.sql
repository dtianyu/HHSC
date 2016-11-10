-- MySQL dump 10.13  Distrib 5.6.24, for Win32 (x86)
--
-- Host: localhost    Database: hhsc
-- ------------------------------------------------------
-- Server version	5.5.24

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
-- Table structure for table `transactiontype`
--

DROP TABLE IF EXISTS `transactiontype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transactiontype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `trtype` varchar(10) NOT NULL,
  `trname` varchar(20) NOT NULL,
  `sysid` varchar(10) DEFAULT NULL,
  `iocode` int(11) NOT NULL COMMENT '-1出库\n0不变\n1入库',
  `hascost` tinyint(1) NOT NULL,
  `objtype` varchar(100) DEFAULT NULL,
  `objselect` varchar(45) DEFAULT NULL,
  `srctype` varchar(100) DEFAULT NULL,
  `srcselect` varchar(45) DEFAULT NULL,
  `reasontype` varchar(10) DEFAULT NULL,
  `updateindate` tinyint(1) NOT NULL,
  `updateoutdate` tinyint(1) NOT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `status` varchar(2) NOT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `credate` datetime DEFAULT NULL,
  `optuser` varchar(20) DEFAULT NULL,
  `optdate` datetime DEFAULT NULL,
  `cfmuser` varchar(20) DEFAULT NULL,
  `cfmdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `transactiontype_UK1` (`trtype`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactiontype`
--

LOCK TABLES `transactiontype` WRITE;
/*!40000 ALTER TABLE `transactiontype` DISABLE KEYS */;
INSERT INTO `transactiontype` VALUES (1,'PAA','验收入库','PUR',1,1,'','','','',NULL,0,0,'','V','Admin','2016-05-05 20:23:27',NULL,NULL,'Admin','2016-05-05 21:23:24'),(2,'PAB','验收退货','PUR',-1,1,'','','','',NULL,0,0,'','V','Admin','2016-05-05 20:33:45',NULL,NULL,'Admin','2016-05-05 21:25:25'),(3,'SDA','销售出货','SD',-1,1,'','','','',NULL,0,0,'','V','Admin','2016-05-05 20:34:13',NULL,NULL,'Admin','2016-05-05 21:27:01'),(4,'SDB','销售退货','SD',1,1,'','','','',NULL,0,0,'','V','Admin','2016-05-05 20:42:58','Admin','2016-05-18 07:38:54','Admin','2016-05-18 07:41:29'),(5,'IAA','异动入库','MM',1,1,'','','','',NULL,0,0,'','V','系统管理员','2016-06-29 22:46:02',NULL,NULL,'系统管理员','2016-11-01 23:27:57'),(6,'IAB','异动出库','MM',-1,1,'','','','',NULL,0,0,'','V','系统管理员','2016-06-29 22:47:37',NULL,NULL,'系统管理员','2016-11-01 23:28:00'),(7,'IKA','客供入库','MM',1,0,'Customer','customerSelect','SalesOrderDetailForQuery','salesordermaterialSelect',NULL,0,0,'','V','系统管理员','2016-06-29 22:48:03',NULL,NULL,'系统管理员','2016-11-01 23:28:02'),(8,'IKB','客供出库','MM',-1,0,'Customer','customerSelect','','',NULL,0,0,'','V','系统管理员','2016-06-29 22:48:30',NULL,NULL,'系统管理员','2016-11-01 23:28:04'),(9,'MPA','内部领料','PP',-1,1,'','','','',NULL,0,0,'','V','系统管理员','2016-07-13 22:28:05',NULL,NULL,'系统管理员','2016-11-01 23:28:06'),(10,'MPB','内部退料','MM',1,1,'','','','',NULL,0,0,'','V','系统管理员','2016-07-13 22:33:23',NULL,NULL,'系统管理员','2016-11-01 23:28:09'),(11,'MKA','客供领料','PP',-1,0,'','','','',NULL,0,0,'','V','系统管理员','2016-07-13 22:31:51',NULL,NULL,'系统管理员','2016-11-01 23:28:11'),(12,'MKB','客供退料','MM',1,0,'','','','',NULL,0,0,'','V','系统管理员','2016-07-13 22:35:45',NULL,NULL,'系统管理员','2016-11-01 23:27:40'),(13,'MFA','良品入库','PF',1,1,'','','','',NULL,0,0,'','V','系统管理员','2016-07-23 12:11:21',NULL,NULL,'系统管理员','2016-11-01 23:28:17'),(14,'MFB','不良入库','PF',1,1,'','','','',NULL,0,0,'','V','系统管理员','2016-07-23 19:26:59',NULL,NULL,'系统管理员','2016-11-01 23:28:22'),(15,'IAE','库存转换','MM',0,1,'','','','',NULL,0,0,'','V','系统管理员','2016-11-01 21:25:14',NULL,NULL,'系统管理员','2016-11-01 23:28:26'),(16,'IRA','染色出库','M',-1,1,'','','','',NULL,0,0,'','V','系统管理员','2016-11-08 07:53:04',NULL,NULL,'系统管理员','2016-11-08 07:55:52'),(17,'IRB','染色入库','M',1,1,'','','','',NULL,0,0,'','V','系统管理员','2016-11-08 07:55:10',NULL,NULL,'系统管理员','2016-11-08 07:55:56');
/*!40000 ALTER TABLE `transactiontype` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-09 19:33:13
