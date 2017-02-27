-- MySQL dump 10.13  Distrib 5.7.12, for osx10.9 (x86_64)
--
-- Host: 127.0.0.1    Database: hhsc
-- ------------------------------------------------------
-- Server version	5.7.10

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
-- Table structure for table `accountreceipt`
--

DROP TABLE IF EXISTS `accountreceipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accountreceipt` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `formid` varchar(20) NOT NULL,
  `formdate` date NOT NULL,
  `formtype` varchar(10) DEFAULT NULL,
  `formkind` varchar(10) DEFAULT NULL,
  `customerid` int(11) NOT NULL,
  `deptid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `summary` varchar(100) DEFAULT NULL,
  `recamts` decimal(16,2) NOT NULL DEFAULT '0.00',
  `recamt` decimal(16,2) NOT NULL DEFAULT '0.00',
  `remark` varchar(100) DEFAULT NULL,
  `status` varchar(2) NOT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `credate` datetime DEFAULT NULL,
  `optuser` varchar(20) DEFAULT NULL,
  `optdate` datetime DEFAULT NULL,
  `cfmuser` varchar(20) DEFAULT NULL,
  `cfmdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `accountreceipt_UK1` (`formid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accountreceipt`
--

LOCK TABLES `accountreceipt` WRITE;
/*!40000 ALTER TABLE `accountreceipt` DISABLE KEYS */;
/*!40000 ALTER TABLE `accountreceipt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accountreceiptdetail`
--

DROP TABLE IF EXISTS `accountreceiptdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accountreceiptdetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` varchar(20) NOT NULL,
  `seq` int(11) NOT NULL,
  `summary` varchar(45) DEFAULT NULL,
  `currency` varchar(10) NOT NULL,
  `exchange` decimal(16,4) NOT NULL,
  `recamts` decimal(16,2) DEFAULT '0.00',
  `recamt` decimal(16,2) DEFAULT '0.00',
  `srcapi` varchar(100) DEFAULT NULL,
  `srcformid` varchar(20) DEFAULT NULL,
  `srcseq` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `accountreceiptdetail_UK1` (`pid`,`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accountreceiptdetail`
--

LOCK TABLES `accountreceiptdetail` WRITE;
/*!40000 ALTER TABLE `accountreceiptdetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `accountreceiptdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accountreceivable`
--

DROP TABLE IF EXISTS `accountreceivable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accountreceivable` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `formid` varchar(20) NOT NULL,
  `formdate` date NOT NULL,
  `customerid` int(11) NOT NULL,
  `deptid` int(11) DEFAULT NULL,
  `salerid` int(11) DEFAULT NULL,
  `currency` varchar(10) NOT NULL,
  `exchange` decimal(16,4) NOT NULL,
  `taxtype` varchar(2) NOT NULL,
  `taxkind` varchar(10) NOT NULL,
  `taxrate` decimal(16,4) NOT NULL,
  `paydate` date DEFAULT NULL,
  `amts` decimal(16,2) NOT NULL DEFAULT '0.00',
  `amt` decimal(16,2) NOT NULL DEFAULT '0.00',
  `preamts` decimal(16,2) DEFAULT '0.00',
  `preamt` decimal(16,2) DEFAULT '0.00',
  `addamts` decimal(16,2) DEFAULT '0.00',
  `addamt` decimal(16,2) DEFAULT '0.00',
  `offamts` decimal(16,2) DEFAULT '0.00',
  `offamt` decimal(16,2) DEFAULT '0.00',
  `extaxs` decimal(16,2) DEFAULT '0.00',
  `extax` decimal(16,2) DEFAULT '0.00',
  `taxess` decimal(16,2) DEFAULT '0.00',
  `taxes` decimal(16,2) DEFAULT '0.00',
  `recamts` decimal(16,2) DEFAULT '0.00',
  `recamt` decimal(16,2) DEFAULT '0.00',
  `remark` varchar(100) DEFAULT NULL,
  `status` varchar(2) NOT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `credate` datetime DEFAULT NULL,
  `optuser` varchar(20) DEFAULT NULL,
  `optdate` datetime DEFAULT NULL,
  `cfmuser` varchar(20) DEFAULT NULL,
  `cfmdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `accountreceivable_UK1` (`formid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accountreceivable`
--

LOCK TABLES `accountreceivable` WRITE;
/*!40000 ALTER TABLE `accountreceivable` DISABLE KEYS */;
/*!40000 ALTER TABLE `accountreceivable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `area`
--

DROP TABLE IF EXISTS `area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lvl` int(11) DEFAULT NULL,
  `name` varchar(20) NOT NULL,
  `pid` int(11) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `status` varchar(2) NOT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `credate` datetime DEFAULT NULL,
  `optuser` varchar(20) DEFAULT NULL,
  `optdate` datetime DEFAULT NULL,
  `cfmuser` varchar(20) DEFAULT NULL,
  `cfmdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `area_UK1` (`name`),
  KEY `area_area_FK1_idx` (`pid`),
  CONSTRAINT `area_area_FK1` FOREIGN KEY (`pid`) REFERENCES `area` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `area`
--

LOCK TABLES `area` WRITE;
/*!40000 ALTER TABLE `area` DISABLE KEYS */;
INSERT INTO `area` VALUES (1,NULL,'中国',NULL,'','V','Admin','2016-06-13 22:00:46',NULL,NULL,'Admin','2016-06-18 13:09:46'),(2,NULL,'华东',1,'','N','Admin','2016-06-13 22:01:06',NULL,NULL,NULL,NULL),(3,NULL,'浙江',2,'','N','Admin','2016-06-18 13:03:20',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `area` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `currency`
--

DROP TABLE IF EXISTS `currency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `currency` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `currency` varchar(10) NOT NULL,
  `exchange` decimal(16,4) NOT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `status` varchar(2) NOT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `credate` datetime DEFAULT NULL,
  `optuser` varchar(20) DEFAULT NULL,
  `optdate` datetime DEFAULT NULL,
  `cfmuser` varchar(20) DEFAULT NULL,
  `cfmdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `dept_UK1` (`currency`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currency`
--

LOCK TABLES `currency` WRITE;
/*!40000 ALTER TABLE `currency` DISABLE KEYS */;
INSERT INTO `currency` VALUES (1,'CNY',1.0000,'人民币','N','Admin','2016-05-20 07:42:10',NULL,NULL,NULL,NULL),(2,'USD',6.5320,'美元','N','Admin','2016-06-13 20:19:12',NULL,NULL,NULL,NULL),(3,'JPY',16.0000,'日元','N','Admin','2016-06-18 13:02:35',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `currency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customerno` varchar(20) NOT NULL,
  `customer` varchar(20) NOT NULL,
  `fullname` varchar(100) NOT NULL,
  `simplecode` varchar(10) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `fax` varchar(20) DEFAULT NULL,
  `tel2` varchar(20) DEFAULT NULL,
  `fax2` varchar(20) DEFAULT NULL,
  `boss` varchar(20) DEFAULT NULL,
  `weburl` varchar(45) DEFAULT NULL,
  `src` varchar(20) DEFAULT NULL,
  `grade` varchar(20) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `industry` varchar(200) DEFAULT NULL,
  `contacter` varchar(20) DEFAULT NULL,
  `contactadd` varchar(200) DEFAULT NULL,
  `zipcode` varchar(10) DEFAULT NULL,
  `country` varchar(10) DEFAULT NULL,
  `area` varchar(10) DEFAULT NULL,
  `city` varchar(10) DEFAULT NULL,
  `salerid` int(11) DEFAULT NULL,
  `deptid` int(11) DEFAULT NULL,
  `pricingtype` varchar(10) DEFAULT NULL,
  `currency` varchar(10) NOT NULL,
  `taxtype` varchar(2) NOT NULL,
  `taxkind` varchar(10) NOT NULL,
  `taxrate` decimal(16,4) NOT NULL,
  `tradetype` varchar(10) DEFAULT NULL,
  `tradename` varchar(45) DEFAULT NULL,
  `paymentid` int(11) DEFAULT NULL,
  `payment` varchar(45) DEFAULT NULL,
  `shipadd` varchar(10) DEFAULT NULL,
  `regaddress` varchar(200) DEFAULT NULL,
  `regcapital` decimal(16,4) DEFAULT NULL,
  `taxid` varchar(30) DEFAULT NULL,
  `bankid` varchar(60) DEFAULT NULL,
  `bankaccount` varchar(60) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `status` varchar(2) NOT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `credate` date DEFAULT NULL,
  `optuser` varchar(20) DEFAULT NULL,
  `optdate` date DEFAULT NULL,
  `cfmuser` varchar(20) DEFAULT NULL,
  `cfmdate` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `customer_UK1` (`customerno`),
  UNIQUE KEY `customer_UK2` (`customer`),
  UNIQUE KEY `customer_UK3` (`fullname`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (2,'K000002','JQS-ZACH','查切里电子商务',NULL,'','','','','','','1','C','','','',NULL,'','','','',NULL,NULL,NULL,'CNY','0','VAT17',17.0000,'C&F',NULL,NULL,'',NULL,'上海是金山区枫泾镇亭枫公路8358号',NULL,'',NULL,NULL,'','V','计秋森','2016-08-04',NULL,NULL,'朱怡雯','2016-09-28'),(3,'K000003','ZYH-ZACH','ZYH-ZCH',NULL,'','','','','','','3','C','','','',NULL,'','','','',NULL,NULL,NULL,'CNY','0','VAT17',17.0000,'C&F',NULL,NULL,'',NULL,'上海金山区枫泾镇亭枫公路8358号',NULL,'',NULL,NULL,'','N','朱燕华','2016-08-10',NULL,NULL,NULL,NULL),(4,'K000004','JQS-8(卢荻）','JQS-8(卢荻）',NULL,'','','63872223','','','','3','C','','','',NULL,'','','','',NULL,NULL,NULL,'CNY','0','VAT17',17.0000,'C&F',NULL,NULL,'',NULL,'上海市顺昌路10号20楼A室',NULL,'',NULL,NULL,'','N','计秋森','2016-09-06',NULL,NULL,NULL,NULL),(5,'K000005','WY-2','ECHO DESIGN GROUP',NULL,'','','','','','','1','C','','','',NULL,'','','','',NULL,NULL,NULL,'USD','3','VAT17',17.0000,'C&F',NULL,NULL,'其他约定',NULL,'USA',NULL,'',NULL,NULL,'','N','王月','2016-09-06',NULL,NULL,NULL,NULL),(84,'K000006','ZYW-ZACH','查切里电子商务公司',NULL,'57351616','','','','','','2','C','','','王凯欣',NULL,'','','','',NULL,NULL,NULL,'CNY','0','VAT17',17.0000,'C&F',NULL,NULL,'',NULL,'上海',NULL,'',NULL,NULL,'','N','系统管理员','2016-09-29',NULL,NULL,NULL,NULL),(86,'K000007','Shanghai ECHO','Shanghai ECHo design',NULL,'13636520123','51673516','12122121212','','Tianyu','','3','C','200 people','scarf top','Tianyu',NULL,'201hhh','China','SHanghai','Shanhai',NULL,NULL,NULL,'USD','0','VAT17',17.0000,'C&F',NULL,NULL,'30day',NULL,'Shanghai',NULL,'TEST ',NULL,NULL,'','V','朱怡雯','2016-10-15',NULL,NULL,'朱怡雯','2016-10-15'),(87,'K000008','ZYW - Testing','shanghai Testing',NULL,'57351616','','','','','','1','C','贸易公司','围巾','张三',NULL,'','','','',NULL,NULL,NULL,'CNY','0','VAT17',17.0000,'C&F',NULL,NULL,'30 天',NULL,'shanghai上海',NULL,'回回回 ',NULL,NULL,'','N','系统管理员','2016-10-29',NULL,NULL,NULL,NULL),(88,'K000009','huayue','zhejiang huayue',NULL,'355444','','3122233332','','','','2','V','10 人','白坯面料','吴春光',NULL,'','我的vv','','',NULL,NULL,NULL,'CNY','0','VAT17',17.0000,'C&F',NULL,NULL,'30 days',NULL,'haiyan',NULL,'分公司人工输入个人 ',NULL,NULL,'','V','朱怡雯','2016-10-29',NULL,NULL,'朱怡雯','2016-10-29');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customercontacter`
--

DROP TABLE IF EXISTS `customercontacter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customercontacter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL,
  `seq` int(11) NOT NULL,
  `contacter` varchar(20) NOT NULL,
  `tel` varchar(45) DEFAULT NULL,
  `fax` varchar(45) DEFAULT NULL,
  `mobile` varchar(45) DEFAULT NULL,
  `mail` varchar(45) DEFAULT NULL,
  `major` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customercontacter`
--

LOCK TABLES `customercontacter` WRITE;
/*!40000 ALTER TABLE `customercontacter` DISABLE KEYS */;
INSERT INTO `customercontacter` VALUES (1,0,1,'ZYW','12221212121212','','','',0),(2,0,1,'李四','187688798797','','','',0),(3,0,1,' 王五','214234234232','','','',0);
/*!40000 ALTER TABLE `customercontacter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customeritem`
--

DROP TABLE IF EXISTS `customeritem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customeritem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `customerno` varchar(20) NOT NULL COMMENT '客户编号',
  `seq` int(11) NOT NULL,
  `itemid` int(11) NOT NULL,
  `itemno` varchar(45) NOT NULL,
  `customeritemno` varchar(45) NOT NULL,
  `customeritemdesc` varchar(45) DEFAULT NULL,
  `customeritemspec` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customeritem`
--

LOCK TABLES `customeritem` WRITE;
/*!40000 ALTER TABLE `customeritem` DISABLE KEYS */;
INSERT INTO `customeritem` VALUES (1,1,'K000001',1,3,'001','001','',NULL),(2,1,'K000001',1,9,'A160920','ZCM000014','',NULL),(3,1,'K000001',1,10,'A160921','ZCM000015','',NULL),(4,2,'K000002',1,11,'A160890','ZCS000042','',NULL),(5,2,'K000002',1,12,'A160891','ZCS000041','原花号A160780',NULL),(6,2,'K000002',1,13,'A160892','ZCS000040','原花号A160820',NULL),(7,2,'K000002',1,14,'A160801','ZCD000013-1','跟原花型',NULL),(8,14,'K000002',1,14,'A160801','ZCD000013-2','',NULL),(9,2,'K000002',1,15,'A160893','ZCD000013-2','原花号160801',NULL),(10,2,'K000002',1,16,'A160926','ZCD000013-3','原花号A160801',NULL),(11,2,'K000002',1,17,'A160824','ZCD000014-1','跟原花号A160824',NULL),(12,2,'K000002',1,18,'A160919','ZCD000014-2','原花号A160824',NULL),(13,1,'K000001',1,20,'A160932','ZCM000016','',NULL),(14,1,'K000001',1,21,'A160933','ZCM000017','',NULL),(15,1,'K000001',1,22,'A160934','ZCM000018','',NULL),(16,23,'K000001',1,23,'A160935','ZCM000019','',NULL),(17,1,'K000001',1,24,'A160936','ZCM000020','',NULL),(18,1,'K000001',1,25,'A160970','ZCM000022','',NULL),(19,1,'K000001',1,26,'A160937','ZCM000021','',NULL),(20,1,'K000001',1,28,'A160938','ZCS000025','',NULL),(21,1,'K000001',1,29,'A160939','ZCB000021','',NULL),(22,1,'K000001',1,30,'A160940','ZCB000022','',NULL),(23,1,'K000001',1,34,'A160983','ZCS000026','',NULL),(24,1,'K000001',1,35,'A160984','ZCS000027','',NULL),(25,1,'K000001',1,38,'A160987','ZCS000030','',NULL),(26,1,'K000001',1,39,'A160988','A160988','',NULL),(27,1,'K000001',1,40,'A160989','ZCS000032','',NULL),(28,1,'K000001',1,42,'A160991','ZCS000034','',NULL),(29,1,'K000001',1,43,'A160992','ZCS000036','',NULL),(30,1,'K000001',1,44,'A160994','ZCS000037','',NULL),(31,1,'K000001',1,45,'A160995','A160995','',NULL),(32,1,'K000001',1,46,'A160996','ZCS000039','',NULL),(33,1,'K000001',1,47,'A160918','ZCS000043','',NULL),(34,1,'K000001',1,48,'A160993','ZCS000035','',NULL),(35,4,'K000004',1,52,'A160886','1M4778对称花型','',NULL),(36,4,'K000004',1,53,'A160887','1M4778边框斜花','',NULL),(37,4,'K000004',1,54,'A160889','1M4780几何组合','',NULL),(38,4,'K000004',1,55,'A160888','1M4779四角花朵','',NULL),(39,5,'K000005',1,62,'A161137','UN0004','玫瑰大花朵',NULL),(40,86,'K000007',1,66,'A16000011','COFI002','',NULL),(41,84,'K000006',1,67,'A161138','叶子','叶子',NULL),(42,4,'K000004',1,68,'A161139','789798798','',NULL),(43,2,'K000002',1,70,'A161140','Z123333','',NULL),(44,5,'K000005',2,70,'A161140','5675','',NULL);
/*!40000 ALTER TABLE `customeritem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deptno` varchar(20) NOT NULL,
  `dept` varchar(45) NOT NULL,
  `pid` int(11) DEFAULT NULL,
  `leader` varchar(10) DEFAULT NULL,
  `remark` varchar(300) DEFAULT NULL,
  `status` varchar(2) NOT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `credate` date DEFAULT NULL,
  `optuser` varchar(20) DEFAULT NULL,
  `optdate` date DEFAULT NULL,
  `cfmuser` varchar(20) DEFAULT NULL,
  `cfmdate` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `department_UK1` (`deptno`),
  KEY `department_IX1` (`pid`),
  CONSTRAINT `department_department_FK1` FOREIGN KEY (`pid`) REFERENCES `department` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'200','业务一部',NULL,'','','N','Admin','2016-05-20','朱怡雯','2016-10-15',NULL,NULL),(2,'300','采购部',NULL,'','','N','Admin','2016-06-13',NULL,NULL,NULL,NULL),(3,'500','业务二部',NULL,'朱怡雯','','N','朱怡雯','2016-10-15',NULL,NULL,NULL,NULL),(4,'100','生产管理部',NULL,'顾国兴','','N','朱怡雯','2016-10-15',NULL,NULL,NULL,NULL),(5,'fengzhi','四楼缝制车间',4,'朱金明','','N','系统管理员','2016-10-29',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventorytransaction`
--

DROP TABLE IF EXISTS `inventorytransaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inventorytransaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `trtype` varchar(10) NOT NULL,
  `formid` varchar(20) NOT NULL,
  `formdate` date NOT NULL,
  `seq` int(11) NOT NULL,
  `itemno` varchar(45) NOT NULL,
  `colorno` varchar(20) NOT NULL,
  `brand` varchar(20) NOT NULL,
  `batch` varchar(20) NOT NULL,
  `sn` varchar(45) NOT NULL,
  `qty` decimal(16,2) NOT NULL DEFAULT '0.00',
  `unit` varchar(10) DEFAULT NULL,
  `warehouseno` varchar(20) NOT NULL,
  `iocode` int(11) DEFAULT NULL,
  `objtype` varchar(45) DEFAULT NULL,
  `objno` varchar(20) DEFAULT NULL,
  `reasontype` varchar(10) DEFAULT NULL,
  `reason` varchar(45) DEFAULT NULL,
  `proptype` varchar(2) DEFAULT NULL,
  `maketype` varchar(2) DEFAULT NULL,
  `sysno` varchar(10) DEFAULT NULL,
  `srcapi` varchar(45) DEFAULT NULL,
  `srcformid` varchar(20) DEFAULT NULL,
  `srcseq` int(11) DEFAULT NULL,
  `costma` decimal(16,4) DEFAULT '0.0000',
  `costla` decimal(16,4) DEFAULT '0.0000',
  `costex` decimal(16,4) DEFAULT '0.0000',
  `costsub` decimal(16,4) DEFAULT '0.0000',
  `cost` decimal(16,4) DEFAULT '0.0000',
  `status` varchar(2) NOT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `credate` datetime DEFAULT NULL,
  `optuser` varchar(20) DEFAULT NULL,
  `optdate` datetime DEFAULT NULL,
  `cfmuser` varchar(20) DEFAULT NULL,
  `cfmdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `inventorytransaction_UK1` (`formid`,`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventorytransaction`
--

LOCK TABLES `inventorytransaction` WRITE;
/*!40000 ALTER TABLE `inventorytransaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `inventorytransaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itemcategory`
--

DROP TABLE IF EXISTS `itemcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `itemcategory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(20) NOT NULL,
  `name` varchar(45) NOT NULL,
  `proptype` varchar(2) NOT NULL COMMENT '1成品\n2半成品\n3原料\n4物料\n5包装物\n6低值易耗品\n9费用\nA固定资产\n',
  `maketype` varchar(2) NOT NULL COMMENT 'F自制完成品\nM自制半成品\nP外购件\nS委外托工件\nV虚拟件',
  `pptype` varchar(8) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  `status` varchar(2) NOT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `credate` datetime DEFAULT NULL,
  `optuser` varchar(20) DEFAULT NULL,
  `optdate` datetime DEFAULT NULL,
  `cfmuser` varchar(20) DEFAULT NULL,
  `cfmdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itemcategory`
--

LOCK TABLES `itemcategory` WRITE;
/*!40000 ALTER TABLE `itemcategory` DISABLE KEYS */;
INSERT INTO `itemcategory` VALUES (1,'100','成品','1','F','P','','V','Admin','2016-05-20 08:57:27',NULL,NULL,'Admin','2016-05-20 08:58:55'),(2,'200','印花面料','2','M','P','','V','Admin','2016-05-20 08:57:48',NULL,NULL,'Admin','2016-05-20 08:59:05'),(3,'300','白坯面料','3','P','P','','V','Admin','2016-05-20 08:58:10',NULL,NULL,'Admin','2016-05-20 08:59:11'),(4,'999','客供面料','3','V','M&P','','N','系统管理员','2016-06-27 23:27:59',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `itemcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iteminventory`
--

DROP TABLE IF EXISTS `iteminventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iteminventory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `itemno` varchar(45) NOT NULL,
  `colorno` varchar(20) NOT NULL,
  `brand` varchar(20) NOT NULL,
  `batch` varchar(20) NOT NULL,
  `sn` varchar(45) NOT NULL,
  `warehouseno` varchar(20) NOT NULL,
  `qty` decimal(16,2) NOT NULL DEFAULT '0.00',
  `preqty` decimal(16,2) NOT NULL DEFAULT '0.00',
  `location` varchar(45) DEFAULT NULL,
  `objectkind` varchar(45) DEFAULT NULL,
  `objectid` varchar(45) DEFAULT NULL,
  `indate` datetime DEFAULT NULL,
  `outdate` datetime DEFAULT NULL,
  `status` varchar(2) NOT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `credate` datetime DEFAULT NULL,
  `optuser` varchar(20) DEFAULT NULL,
  `optdate` datetime DEFAULT NULL,
  `cfmuser` varchar(20) DEFAULT NULL,
  `cfmdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `iteminventory_UK1` (`itemno`,`colorno`,`brand`,`batch`,`sn`,`warehouseno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iteminventory`
--

LOCK TABLES `iteminventory` WRITE;
/*!40000 ALTER TABLE `iteminventory` DISABLE KEYS */;
/*!40000 ALTER TABLE `iteminventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itemmake`
--

DROP TABLE IF EXISTS `itemmake`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `itemmake` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `itemno` varchar(45) NOT NULL,
  `seq` int(11) NOT NULL,
  `make` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `itemmake_UK1` (`itemno`,`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itemmake`
--

LOCK TABLES `itemmake` WRITE;
/*!40000 ALTER TABLE `itemmake` DISABLE KEYS */;
INSERT INTO `itemmake` VALUES (1,8,'W160006',1,'100%人造棉'),(2,27,'W160008',1,'100%silk'),(3,33,'W160009',1,'100%silk'),(4,50,'W160011',1,'100%人造棉'),(5,61,'W160013',1,'80%莫代尔20%羊毛'),(6,65,'W160015',1,'10% cotton'),(7,65,'W160015',2,'20% silk'),(8,65,'W160015',3,'30% wool'),(9,65,'W160015',4,'40% poly'),(10,69,'W160016',1,'70% 莫代尔'),(11,69,'W160016',2,'30% 丝'),(12,71,'T160001',1,'20% poly'),(13,71,'T160001',2,'80% silk'),(14,75,'T160002',1,'rrr'),(15,76,'W160020',1,'2ee'),(16,77,'T160003',1,'ERR');
/*!40000 ALTER TABLE `itemmake` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itemmaster`
--

DROP TABLE IF EXISTS `itemmaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `itemmaster` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categoryid` int(11) NOT NULL,
  `itemno` varchar(45) NOT NULL,
  `itemdesc` varchar(45) NOT NULL,
  `itemspec` varchar(100) DEFAULT NULL,
  `simplecode` varchar(10) DEFAULT NULL,
  `proptype` varchar(2) DEFAULT NULL,
  `maketype` varchar(2) DEFAULT NULL,
  `pptype` varchar(8) DEFAULT NULL,
  `purkind` varchar(10) DEFAULT NULL,
  `qcpass` tinyint(1) DEFAULT NULL,
  `itemmake` varchar(100) DEFAULT NULL COMMENT '成分',
  `itemweight` varchar(45) DEFAULT NULL COMMENT '克重',
  `itemyarncount` varchar(45) DEFAULT NULL COMMENT '纱支',
  `itemdensity` varchar(45) DEFAULT NULL COMMENT '密度',
  `itemwidth` varchar(45) DEFAULT NULL COMMENT '门幅',
  `opendate` datetime DEFAULT NULL,
  `opensize` varchar(45) DEFAULT NULL,
  `sampleperiod` int(11) DEFAULT '0',
  `deliveryperiod` int(11) DEFAULT '0',
  `brand` varchar(45) DEFAULT NULL,
  `batch` varchar(45) DEFAULT NULL,
  `sn` varchar(45) DEFAULT NULL,
  `unittype` varchar(2) NOT NULL,
  `unit` varchar(10) NOT NULL,
  `unit2` varchar(10) DEFAULT NULL,
  `unitexch` decimal(16,4) DEFAULT NULL,
  `unitsales` varchar(10) DEFAULT NULL,
  `unitpurchase` varchar(10) DEFAULT NULL,
  `invtype` tinyint(1) DEFAULT NULL,
  `bbstype` varchar(3) DEFAULT NULL,
  `stdcost` decimal(16,2) DEFAULT '0.00',
  `purprice` decimal(16,2) DEFAULT '0.00',
  `purmin` decimal(16,2) DEFAULT '0.00',
  `purmax` decimal(16,2) DEFAULT '0.00',
  `invmin` decimal(16,2) DEFAULT '0.00',
  `invmax` decimal(16,2) DEFAULT '0.00',
  `barcode` varchar(45) DEFAULT NULL,
  `img1` varchar(100) DEFAULT NULL,
  `img2` varchar(100) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  `status` varchar(2) NOT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `credate` datetime DEFAULT NULL,
  `optuser` varchar(20) DEFAULT NULL,
  `optdate` datetime DEFAULT NULL,
  `cfmuser` varchar(20) DEFAULT NULL,
  `cfmdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `itemmaster_UK1` (`itemno`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itemmaster`
--

LOCK TABLES `itemmaster` WRITE;
/*!40000 ALTER TABLE `itemmaster` DISABLE KEYS */;
INSERT INTO `itemmaster` VALUES (1,3,'A000000','打样版费','',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','套',NULL,1.0000,NULL,NULL,0,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,NULL,NULL,'','V','系统管理员','2016-07-31 09:23:23','系统管理员','2016-07-31 09:28:27','系统管理员','2016-07-31 09:28:46'),(2,3,'W160001','12108','114cm',NULL,'3',NULL,NULL,NULL,0,'','14mm','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','姆米',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,NULL,NULL,NULL,'N','王月','2016-08-02 19:32:18',NULL,NULL,NULL,NULL),(4,3,'W160002','12108双绉','114cm',NULL,'3',NULL,NULL,NULL,0,'','10mm','','','114cm',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','米',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,NULL,NULL,NULL,'N','计秋森','2016-08-04 18:07:13',NULL,NULL,NULL,NULL),(5,3,'W160003','雪纺','90cm',NULL,'3',NULL,NULL,NULL,0,'','8mm','','','90cm',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','米',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,NULL,NULL,NULL,'N','计秋森','2016-08-04 18:10:39',NULL,NULL,NULL,NULL),(6,3,'W160004','15682斜纹绸','114cm',NULL,'3',NULL,NULL,NULL,0,'','10mm','','','114cm',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','米',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,NULL,NULL,NULL,'N','计秋森','2016-08-04 18:13:16',NULL,NULL,NULL,NULL),(7,3,'W160005','100*100/90*88棉布','145cm',NULL,'3',NULL,NULL,NULL,0,'','','','','145cm',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','米',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,NULL,NULL,NULL,'N','计秋森','2016-08-04 18:15:01',NULL,NULL,NULL,NULL),(8,3,'W160006','W12531','40*40/48*34',NULL,'3',NULL,NULL,NULL,0,'100%人造棉;','54.0g/m2','','','145cm',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','米',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,NULL,NULL,NULL,'N','王月','2016-08-08 07:48:37',NULL,NULL,NULL,NULL),(9,2,'A160920','豹纹匹料','65*180+2*10捻须',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','条',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'18.jpg',NULL,'','N','王月','2016-08-08 07:53:11',NULL,NULL,NULL,NULL),(10,2,'A160921','豹纹红底','65*180+2*10捻须',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','条',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'17.jpg',NULL,'','N','王月','2016-08-08 08:12:03',NULL,NULL,NULL,NULL),(11,2,'A160890','霸王龙玫瑰花大方巾','110*110cm四边平车卷边',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','条',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'A160819.gif',NULL,'','N','计秋森','2016-08-08 11:42:12',NULL,NULL,NULL,NULL),(12,2,'A160891','扇形边刺绣方巾','85*85cm四边平车卷边',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','条',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'A160780.jpg',NULL,'','N','计秋森','2016-08-08 12:24:44',NULL,NULL,NULL,NULL),(13,2,'A160892','米字旗直条玫瑰花长巾','150*110cm四边平车卷边',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','条',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'A160820.gif',NULL,'','N','计秋森','2016-08-08 12:32:08',NULL,NULL,NULL,NULL),(14,2,'A160801','小花小手帕','55*55cm四边平车卷边',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','条',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'A160801.jpg',NULL,'','N','计秋森','2016-08-08 12:36:00',NULL,NULL,NULL,NULL),(15,2,'A160893','腰果小花手帕','55*55cm四边平车卷边',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','条',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'A160801.jpg',NULL,'','N','计秋森','2016-08-08 12:42:00',NULL,NULL,NULL,NULL),(16,2,'A160926','腰果小手帕','55*55cm四边平车卷边',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','条',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'A160801.jpg',NULL,'','N','计秋森','2016-08-08 12:57:02',NULL,NULL,NULL,NULL),(17,2,'A160824','蓝白花小手帕','55*55cm四边平车卷边',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','条',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'A160824.jpg',NULL,'','N','计秋森','2016-08-08 13:00:22',NULL,NULL,NULL,NULL),(18,2,'A160919','腰果小手帕','55*55cm四边平车卷边',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','条',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'A160824.jpg',NULL,'','N','计秋森','2016-08-08 13:04:20',NULL,NULL,NULL,NULL),(19,3,'W160007','W12132 化纤绒雪纺','15D+15D*15D+15D/119*114化纤绒雪纺145门幅 涤100% 53.0g/m2',NULL,'3',NULL,NULL,NULL,0,'','53g/m2','','','145cm',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','米',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,NULL,NULL,NULL,'N','王月','2016-08-10 09:21:07',NULL,NULL,NULL,NULL),(20,2,'A160932','豹纹条子','65*180+2*10cm捻须',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','条',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'19.jpg',NULL,'','N','王月','2016-08-10 09:23:20',NULL,NULL,NULL,NULL),(21,2,'A160933','丛林鹦鹉鸟','65*180+10*2捻须',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','条',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'24.jpg',NULL,'另一款面料做沙滩装','N','王月','2016-08-10 09:26:46',NULL,NULL,NULL,NULL),(22,2,'A160934','丛林大花朵','65*180+10*2 捻须',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','米',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'23.jpg',NULL,'','N','王月','2016-08-10 09:53:45',NULL,NULL,NULL,NULL),(23,2,'A160935','丛林素色花','65*180+10*2捻须',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','条',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'22.jpg',NULL,'','V','王月','2016-08-10 10:01:06',NULL,NULL,'王月','2016-08-10 10:11:25'),(24,2,'A160936','丛林乱花','65*180+10*2捻须',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','条',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'8.jpg',NULL,'','N','王月','2016-08-10 10:06:58',NULL,NULL,NULL,NULL),(25,2,'A160970','抽象条纹','65*180+10*2',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','条',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'27.jpg',NULL,'','N','王月','2016-08-10 10:15:41',NULL,NULL,NULL,NULL),(26,2,'A160937','芭蕉叶鹦鹉花','65*180+10*2捻须',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','条',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'3.jpg',NULL,'','N','王月','2016-08-10 10:25:34',NULL,NULL,NULL,NULL),(27,3,'W160008','15682 10mm斜纹','',NULL,'3',NULL,NULL,NULL,0,'100%silk;','10mm','','','114cm',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','米',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,NULL,NULL,NULL,'N','王月','2016-08-10 09:23:11',NULL,NULL,NULL,NULL),(28,2,'A160938','花朵之字纹','110*110平车机卷',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','条',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'7.jpg',NULL,'','N','王月','2016-08-10 10:33:25',NULL,NULL,NULL,NULL),(29,2,'A160939','蝴蝶','',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','条',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'29.jpg',NULL,'','N','王月','2016-08-10 10:41:04',NULL,NULL,NULL,NULL),(30,2,'A160940','黑底花朵','',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','条',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'30.jpg',NULL,'','N','王月','2016-08-10 10:52:47',NULL,NULL,NULL,NULL),(31,2,'A160723','黄花梨木','',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','米',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'160723.jpg',NULL,'','N','朱燕华','2016-08-10 10:26:01',NULL,NULL,NULL,NULL),(32,2,'A160724','黑底兰花','',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','米',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'160724.jpg',NULL,'','N','朱燕华','2016-08-10 17:40:04',NULL,NULL,NULL,NULL),(33,3,'W160009','W13176 雪纺',' 8mm',NULL,'3',NULL,NULL,NULL,0,'100%silk;','8mm','','','140cm',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','米',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,NULL,NULL,NULL,'N','王月','2016-08-11 10:20:18',NULL,NULL,NULL,NULL),(34,2,'A160983','蝴蝶细茎花','110*110',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','条',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'001.png',NULL,'','N','王月','2016-08-11 10:49:36',NULL,NULL,NULL,NULL),(35,2,'A160984','抽象细茎','110*110',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','条',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'002.png',NULL,'','N','王月','2016-08-11 11:08:40',NULL,NULL,NULL,NULL),(36,2,'A160985','三角细茎花','88*88',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','条',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'007.png',NULL,'','N','王月','2016-08-11 11:10:18',NULL,NULL,NULL,NULL),(37,2,'A160986','云层花','135*135',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','条',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'008.png',NULL,'','N','王月','2016-08-11 11:12:09',NULL,NULL,NULL,NULL),(38,2,'A160987','风车轮细茎花','110*110',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','条',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'014.png',NULL,'','N','王月','2016-08-11 11:13:39',NULL,NULL,NULL,NULL),(39,2,'A160988','动物总动员','110*110',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','条',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'009.png',NULL,'','N','王月','2016-08-11 11:15:40',NULL,NULL,NULL,NULL),(40,2,'A160989','喇叭花','110*110',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','条',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'160989.png',NULL,'','N','王月','2016-08-11 11:22:02',NULL,NULL,NULL,NULL),(41,2,'A160990','抽象蝴蝶','110*110',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','条',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'160990.png',NULL,'','N','王月','2016-08-11 11:27:02',NULL,NULL,NULL,NULL),(42,2,'A160991','乱花','110*110',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','条',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'011.png',NULL,'','N','王月','2016-08-11 11:29:24',NULL,NULL,NULL,NULL),(43,2,'A160992','菱形框花','110*110',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','条',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'160992.png',NULL,'','N','王月','2016-08-11 11:31:50',NULL,NULL,NULL,NULL),(44,2,'A160994','小清新花朵','90*90',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','条',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'160994.png',NULL,'','N','王月','2016-08-11 11:33:34',NULL,NULL,NULL,NULL),(45,2,'A160995','玫瑰花','110*110',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','条',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'160995.png',NULL,'','N','王月','2016-08-11 11:35:07',NULL,NULL,NULL,NULL),(46,2,'A160996','万物生','110*110',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','条',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'160996.png',NULL,'','N','王月','2016-08-11 11:36:51',NULL,NULL,NULL,NULL),(47,2,'A160918','青花绿叶','',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','条',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'013.png',NULL,'','N','王月','2016-08-11 11:38:47',NULL,NULL,NULL,NULL),(48,2,'A160993','百叶花','110*110',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','条',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'1.jpg',NULL,'','N','王月','2016-08-11 11:40:37',NULL,NULL,NULL,NULL),(49,3,'W160010','W142847228-A莫代尔丝','',NULL,'3',NULL,NULL,NULL,0,'','','','','145',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','米',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,NULL,NULL,NULL,'N','王璐','2016-08-29 19:14:59',NULL,NULL,NULL,NULL),(50,3,'W160011','w14899人棉竹节布','',NULL,'3',NULL,NULL,NULL,0,'100%人造棉;','','','','150',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','米',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,NULL,NULL,NULL,'N','计秋森','2016-09-06 12:21:45',NULL,NULL,NULL,NULL),(51,3,'W160012','W16088斜纹人棉','',NULL,'3',NULL,NULL,NULL,0,'','','','','220',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','米',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,NULL,NULL,NULL,'N','计秋森','2016-09-06 12:25:59',NULL,NULL,NULL,NULL),(52,1,'A160886','长巾','183*102含四边1.3散须',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','条',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'A160886.jpg',NULL,'','N','计秋森','2016-09-06 13:05:55',NULL,NULL,NULL,NULL),(53,1,'A160887','长巾','183*102四边卷边+扫把穗',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','条',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'A160887.jpg',NULL,'','N','计秋森','2016-09-06 13:13:22',NULL,NULL,NULL,NULL),(54,1,'A160889','长巾','183*102四边卷边+扫把穗',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','条',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'A160889.jpg',NULL,'','N','计秋森','2016-09-06 13:17:15',NULL,NULL,NULL,NULL),(55,1,'A160888','大方巾','127*127四边卷边',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','条',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'A160888.jpg',NULL,'','N','计秋森','2016-09-06 13:19:34',NULL,NULL,NULL,NULL),(61,3,'W160013','W15421W2009 水溶莫代尔羊毛','90 menfu',NULL,'3',NULL,NULL,NULL,0,'80%莫代尔20%羊毛;','52g/m2','','','145',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','米',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,NULL,NULL,NULL,'V','王月','2016-09-06 14:25:22',NULL,NULL,'朱怡雯','2016-10-29 15:10:50'),(62,2,'A161137','UN0004','',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','条',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'161137.png',NULL,'','N','王月','2016-09-06 14:29:10',NULL,NULL,NULL,NULL),(63,3,'W160014','W9049全羊毛','',NULL,'3',NULL,NULL,NULL,0,'','66','','','145',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','米',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,NULL,NULL,NULL,'V','朱怡雯','2016-09-28 21:32:21',NULL,NULL,'朱怡雯','2016-09-28 21:39:05'),(64,2,'A160610','藤蔓花大方巾','200*135',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','条',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'8311C9AE654B788BB7191E1C3E6CAFFA.png',NULL,'四周平车卷边','V','朱怡雯','2016-09-28 21:39:17',NULL,NULL,'朱怡雯','2016-09-28 21:45:32'),(65,3,'W160015','w16000009','',NULL,'3',NULL,NULL,NULL,0,'10% cotton;20% silk;30% wool;40% poly;','67','15 S','12*12','145',NULL,NULL,30,35,NULL,NULL,NULL,'1','米',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,NULL,NULL,NULL,'N','朱怡雯','2016-10-15 14:07:00',NULL,NULL,NULL,NULL),(66,2,'A16000011','Testing for Name','',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','张',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'untitled.png',NULL,'old style Number #A12009','N','朱怡雯','2016-10-15 14:11:35',NULL,NULL,NULL,NULL),(67,2,'A161138','测试','110 *110',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','个',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'untitled.png',NULL,'方巾','N','系统管理员','2016-10-29 10:39:38',NULL,NULL,NULL,NULL),(68,2,'A161139','testing','140*140',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','个',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'untitled.png',NULL,'定位花型','N','系统管理员','2016-10-29 11:20:29',NULL,NULL,NULL,NULL),(69,3,'W160016','莫代尔丝','145门幅',NULL,'3',NULL,NULL,NULL,0,'70% 莫代尔;30% 丝;','60','60支','12*14','145',NULL,NULL,25,35,NULL,NULL,NULL,'1','米',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,NULL,NULL,NULL,'V','朱怡雯','2016-10-29 15:05:15','朱怡雯','2016-10-29 15:12:10','朱怡雯','2016-10-29 15:13:09'),(70,2,'A161140','火腿花方巾','140*140',NULL,'2',NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','个',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,'untitled.png',NULL,'手卷','V','朱怡雯','2016-10-29 15:18:37',NULL,NULL,'朱怡雯','2016-10-29 15:25:45'),(71,1,'T160001','hhdsiudhih','123*123',NULL,'3',NULL,NULL,NULL,0,'20% poly;80% silk;','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','条',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,NULL,NULL,NULL,'V','朱怡雯','2016-10-29 15:26:58',NULL,NULL,'朱怡雯','2016-10-29 15:29:40'),(72,3,'W160017','莫代尔','',NULL,'3',NULL,NULL,NULL,0,'','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','条',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,NULL,NULL,NULL,'V','朱怡雯','2016-10-29 16:15:23',NULL,NULL,'朱怡雯','2016-10-29 16:28:04'),(73,3,'W160018','莫代尔','',NULL,'3',NULL,NULL,NULL,0,'','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','米',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,NULL,NULL,NULL,'V','朱怡雯','2016-10-29 16:17:59',NULL,NULL,'朱怡雯','2016-10-29 16:28:27'),(74,3,'W160019','莫代尔','145门幅',NULL,'3',NULL,NULL,NULL,0,'','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','米',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,NULL,NULL,NULL,'V','朱怡雯','2016-10-29 16:23:09',NULL,NULL,'朱怡雯','2016-10-29 16:27:48'),(75,1,'T160002','qggkjj','',NULL,'3',NULL,NULL,NULL,0,'rrr;','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','条',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,NULL,NULL,NULL,'N','朱怡雯','2016-10-29 16:29:40',NULL,NULL,NULL,NULL),(76,3,'W160020','modd','',NULL,'3',NULL,NULL,NULL,0,'2ee;','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','米',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,NULL,NULL,NULL,'V','朱怡雯','2016-10-29 16:34:45',NULL,NULL,'朱怡雯','2016-10-29 16:36:00'),(77,1,'T160003','GGD','',NULL,'3',NULL,NULL,NULL,0,'ERR;','','','','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1','条',NULL,1.0000,NULL,NULL,1,'000',NULL,NULL,0.00,0.00,0.00,0.00,NULL,NULL,NULL,NULL,'V','朱怡雯','2016-10-29 16:36:12',NULL,NULL,'朱怡雯','2016-10-29 16:37:03');
/*!40000 ALTER TABLE `itemmaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itemprocess`
--

DROP TABLE IF EXISTS `itemprocess`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `itemprocess` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `itemid` int(11) NOT NULL,
  `itemno` varchar(45) NOT NULL,
  `itemspec` varchar(100) DEFAULT NULL,
  `colorno` varchar(20) DEFAULT NULL,
  `procid` int(11) DEFAULT NULL,
  `fyreq` varchar(400) DEFAULT NULL,
  `hgreq` varchar(400) DEFAULT NULL,
  `zbreq` varchar(400) DEFAULT NULL,
  `psreq` varchar(400) DEFAULT NULL,
  `yhreq` varchar(400) DEFAULT NULL,
  `zhreq` varchar(400) DEFAULT NULL,
  `sxreq` varchar(400) DEFAULT NULL,
  `dxreq` varchar(400) DEFAULT NULL,
  `ckreq` varchar(400) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `status` varchar(2) NOT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `credate` date DEFAULT NULL,
  `optuser` varchar(20) DEFAULT NULL,
  `optdate` date DEFAULT NULL,
  `cfmuser` varchar(20) DEFAULT NULL,
  `cfmdate` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `itemprocess_UK1` (`colorno`,`itemid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itemprocess`
--

LOCK TABLES `itemprocess` WRITE;
/*!40000 ALTER TABLE `itemprocess` DISABLE KEYS */;
/*!40000 ALTER TABLE `itemprocess` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itemresource`
--

DROP TABLE IF EXISTS `itemresource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `itemresource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL COMMENT 'Process.Id',
  `itemno` varchar(45) NOT NULL,
  `seq` int(11) NOT NULL,
  `procid` int(11) NOT NULL,
  `procseq` int(11) NOT NULL,
  `kind` varchar(10) NOT NULL COMMENT 'H:人员\nE:设备\nM:物料\nP:工艺',
  `content` varchar(45) NOT NULL,
  `valuetype` varchar(45) NOT NULL COMMENT 'bool\nDecimal\nString',
  `boolvalue` tinyint(1) DEFAULT NULL,
  `numvalue` decimal(16,2) DEFAULT NULL,
  `strvalue` varchar(45) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `itemresource_UK1` (`seq`,`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itemresource`
--

LOCK TABLES `itemresource` WRITE;
/*!40000 ALTER TABLE `itemresource` DISABLE KEYS */;
/*!40000 ALTER TABLE `itemresource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itemtransaction`
--

DROP TABLE IF EXISTS `itemtransaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `itemtransaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `formid` varchar(20) NOT NULL,
  `formdate` date NOT NULL,
  `trtype` varchar(10) NOT NULL,
  `deptid` int(11) DEFAULT NULL,
  `objtype` varchar(45) DEFAULT NULL,
  `objno` varchar(20) DEFAULT NULL,
  `reason` varchar(45) DEFAULT NULL,
  `warehouseno` varchar(20) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `status` varchar(2) NOT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `credate` datetime DEFAULT NULL,
  `optuser` varchar(20) DEFAULT NULL,
  `optdate` datetime DEFAULT NULL,
  `cfmuser` varchar(20) DEFAULT NULL,
  `cfmdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `itemtransaction_UK1` (`formid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itemtransaction`
--

LOCK TABLES `itemtransaction` WRITE;
/*!40000 ALTER TABLE `itemtransaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `itemtransaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itemtransactiondetail`
--

DROP TABLE IF EXISTS `itemtransactiondetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `itemtransactiondetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` varchar(20) NOT NULL,
  `seq` int(11) NOT NULL,
  `trtype` varchar(10) NOT NULL,
  `itemid` int(11) NOT NULL,
  `itemno` varchar(45) NOT NULL,
  `colorno` varchar(20) DEFAULT NULL,
  `batch` varchar(20) DEFAULT NULL,
  `brand` varchar(20) DEFAULT NULL,
  `sn` varchar(20) DEFAULT NULL,
  `qty` decimal(16,2) NOT NULL,
  `unit` varchar(10) DEFAULT NULL,
  `warehouseno` varchar(20) NOT NULL,
  `srcapi` varchar(100) DEFAULT NULL,
  `srcformid` varchar(20) DEFAULT NULL,
  `srcseq` int(11) DEFAULT NULL,
  `relapi` varchar(100) DEFAULT NULL,
  `relformid` varchar(20) DEFAULT NULL,
  `relseq` int(11) DEFAULT NULL,
  `remark` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `itemtransactiondetail_UK1` (`pid`,`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itemtransactiondetail`
--

LOCK TABLES `itemtransactiondetail` WRITE;
/*!40000 ALTER TABLE `itemtransactiondetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `itemtransactiondetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `process`
--

DROP TABLE IF EXISTS `process`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `process` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `processno` varchar(10) NOT NULL,
  `process` varchar(45) NOT NULL,
  `sortid` int(11) DEFAULT NULL,
  `deptid` int(11) DEFAULT NULL,
  `leader` varchar(45) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `status` varchar(2) NOT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `credate` date DEFAULT NULL,
  `optuser` varchar(20) DEFAULT NULL,
  `optdate` date DEFAULT NULL,
  `cfmuser` varchar(20) DEFAULT NULL,
  `cfmdate` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `process_UK1` (`processno`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `process`
--

LOCK TABLES `process` WRITE;
/*!40000 ALTER TABLE `process` DISABLE KEYS */;
INSERT INTO `process` VALUES (2,'HG','画稿',1,1,'','','N','系统管理员','2016-07-26',NULL,NULL,NULL,NULL),(3,'ZB','制版',2,1,'','','N','系统管理员','2016-07-10',NULL,NULL,NULL,NULL),(4,'PS','配色',3,1,'','','N','系统管理员','2016-07-07',NULL,NULL,NULL,NULL),(5,'YH','印花',4,1,'','','N','系统管理员','2016-07-09',NULL,NULL,NULL,NULL),(7,'ZH','蒸化',5,1,'','','N','系统管理员','2016-07-07',NULL,NULL,NULL,NULL),(9,'SX','水洗',6,1,'','','N','系统管理员','2016-07-09',NULL,NULL,NULL,NULL),(11,'DX','定型',7,1,'','','N','系统管理员','2016-07-10',NULL,NULL,NULL,NULL),(12,'CK','仓库',8,1,'','','N','系统管理员','2016-07-25',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `process` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `processdetail`
--

DROP TABLE IF EXISTS `processdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `processdetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL COMMENT 'Process.Id',
  `seq` int(11) NOT NULL,
  `procid` int(11) NOT NULL,
  `procseq` int(11) NOT NULL,
  `kind` varchar(10) NOT NULL COMMENT 'H:人员\nE:设备\nM:物料\nP:工艺',
  `content` varchar(45) NOT NULL,
  `valuetype` varchar(45) NOT NULL COMMENT 'bool\nDecimal\nString',
  `boolvalue` tinyint(1) DEFAULT NULL,
  `numvalue` decimal(16,2) DEFAULT NULL,
  `strvalue` varchar(45) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `processdetail_UK1` (`pid`,`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `processdetail`
--

LOCK TABLES `processdetail` WRITE;
/*!40000 ALTER TABLE `processdetail` DISABLE KEYS */;
INSERT INTO `processdetail` VALUES (9,1,3,7,11,'P','进气压力','String',NULL,NULL,'2.5kg-4kg',''),(10,1,2,7,12,'P','蒸化时长','String',NULL,NULL,'40分钟',''),(11,1,4,7,13,'P','箱内压力','String',NULL,NULL,'0.8',''),(12,2,3,7,12,'P','蒸化时长','String',NULL,NULL,'35分钟',''),(13,2,4,7,13,'P','箱内压力','String',NULL,NULL,'0.6',''),(14,2,5,7,14,'P','大排气','String',NULL,NULL,'开一圈',''),(15,2,2,7,11,'P','进气压力','String',NULL,NULL,'2.5kg-4kg',''),(16,2,1,7,2,'P','增白直印','String',NULL,NULL,'老圆筒1#2#',''),(17,3,1,7,3,'P','常规羊毛','String',NULL,NULL,'老圆筒1#2#',''),(18,3,2,7,11,'P','进气压力','String',NULL,NULL,'2.5kg-4kg',''),(19,3,3,7,12,'P','蒸化时长','String',NULL,NULL,'50分钟',''),(20,3,4,7,13,'P','箱内压力','String',NULL,NULL,'0.8',''),(21,4,1,7,4,'P','羊毛双面尼','String',NULL,NULL,'老圆筒1#2#',''),(22,4,2,7,11,'P','进气压力','String',NULL,NULL,'2,5g-4kg',''),(23,4,3,7,12,'P','蒸化时长','String',NULL,NULL,'40分钟＋40分钟',''),(24,4,4,7,13,'P','箱内压力','String',NULL,NULL,'0.8',''),(25,5,1,7,5,'P','化纤工艺','String',NULL,NULL,'老圆筒1#2#',''),(26,5,2,7,11,'P','进气压力','String',NULL,NULL,'2.5kg-4kg',''),(27,5,3,7,12,'P','蒸化时长','String',NULL,NULL,'35分钟其中先排气4分钟',''),(28,5,4,7,13,'P','箱内压力','String',NULL,NULL,'1.6',''),(29,6,1,7,6,'P','新工艺工艺','String',NULL,NULL,'老圆筒1#2#',''),(30,6,2,7,11,'P','进气压力','String',NULL,NULL,'2.5kg-4kg',''),(31,6,3,7,12,'P','蒸化时长','String',NULL,NULL,'15分钟其中排气4分钟',''),(32,6,4,7,13,'P','箱内压力','String',NULL,NULL,'0.8',''),(33,7,1,7,7,'P','防印工艺','String',NULL,NULL,'老圆筒1#2#',''),(34,7,2,7,11,'P','进气压力','String',NULL,NULL,'2.5kg-4kg',''),(35,7,3,7,12,'P','蒸化时长','String',NULL,NULL,'40分钟先用新工艺蒸15分钟再用直印工艺蒸25分钟',''),(37,8,1,7,8,'P','吊印工艺','String',NULL,NULL,'老圆筒1#2#',''),(38,8,2,7,11,'P','进气压力','String',NULL,NULL,'2.5kg-4kg',''),(39,8,3,7,12,'P','蒸化时长','String',NULL,NULL,'10分钟排气5分钟',''),(40,8,4,7,13,'P','箱内压力','String',NULL,NULL,'0.8',''),(41,9,1,7,9,'P','活性数码','String',NULL,NULL,'老圆筒1#2#',''),(42,9,2,7,11,'P','进气压力','String',NULL,NULL,'2.5kg-4kg',''),(43,9,3,7,12,'P','蒸化时长','String',NULL,NULL,'12姆米以下蒸12分钟',''),(44,9,4,7,13,'P','箱内压力','String',NULL,NULL,'0.6',''),(45,10,1,7,10,'P','活性数码(12姆米以上)','String',NULL,NULL,'老圆筒1#2#',''),(46,10,2,7,11,'P','进气压力','String',NULL,NULL,'2.5kg-4kg',''),(47,10,3,7,12,'P','蒸化时长','String',NULL,NULL,'12姆米以上蒸15-20分钟',''),(48,10,4,7,13,'P','箱内压力','String',NULL,NULL,'0.6','');
/*!40000 ALTER TABLE `processdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `processgroup`
--

DROP TABLE IF EXISTS `processgroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `processgroup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `groupno` varchar(10) NOT NULL,
  `name` varchar(45) NOT NULL,
  `deptid` int(11) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `status` varchar(2) NOT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `credate` date DEFAULT NULL,
  `optuser` varchar(20) DEFAULT NULL,
  `optdate` date DEFAULT NULL,
  `cfmuser` varchar(20) DEFAULT NULL,
  `cfmdate` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `processgroup_UK1` (`groupno`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `processgroup`
--

LOCK TABLES `processgroup` WRITE;
/*!40000 ALTER TABLE `processgroup` DISABLE KEYS */;
INSERT INTO `processgroup` VALUES (1,'01号','常规直印',1,'','N','系统管理员','2016-09-03','系统管理员','2016-09-09',NULL,NULL),(2,'02号','增白直印',1,'','N','系统管理员','2016-09-03',NULL,NULL,NULL,NULL),(3,'03号','常规羊毛',1,'','N','系统管理员','2016-09-04',NULL,NULL,NULL,NULL),(4,'04号','羊毛双面尼',1,'','N','系统管理员','2016-09-04',NULL,NULL,NULL,NULL),(5,'05号','化纤工艺',1,'','N','系统管理员','2016-09-04',NULL,NULL,NULL,NULL),(6,'06号','新工艺工艺',1,'','N','系统管理员','2016-09-04',NULL,NULL,NULL,NULL),(7,'07号','防印工艺',1,'','N','系统管理员','2016-09-04',NULL,NULL,NULL,NULL),(8,'08号','吊印工艺',1,'','N','系统管理员','2016-09-04',NULL,NULL,NULL,NULL),(9,'09号','活性数码(12姆米以下)',1,'','N','系统管理员','2016-09-04',NULL,NULL,NULL,NULL),(10,'10号','活性数码(12姆米以上)',1,'','N','系统管理员','2016-09-04',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `processgroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `processresource`
--

DROP TABLE IF EXISTS `processresource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `processresource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL COMMENT 'Process.Id',
  `seq` int(11) NOT NULL,
  `kind` varchar(10) NOT NULL COMMENT 'H:人员\nE:设备\nM:物料\nP:工艺',
  `content` varchar(45) NOT NULL,
  `valuetype` varchar(45) NOT NULL COMMENT 'bool\nDecimal\nString',
  `boolvalue` tinyint(1) DEFAULT NULL,
  `numvalue` decimal(16,2) DEFAULT NULL,
  `strvalue` varchar(45) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `status` varchar(2) NOT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `credate` date DEFAULT NULL,
  `optuser` varchar(20) DEFAULT NULL,
  `optdate` date DEFAULT NULL,
  `cfmuser` varchar(20) DEFAULT NULL,
  `cfmdate` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `processresource_UK1` (`pid`,`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `processresource`
--

LOCK TABLES `processresource` WRITE;
/*!40000 ALTER TABLE `processresource` DISABLE KEYS */;
INSERT INTO `processresource` VALUES (1,6,2,'M','甘油','String',NULL,NULL,'%','','N',NULL,NULL,NULL,NULL,NULL,NULL),(2,6,1,'M','防染盐','String',NULL,NULL,'%','','N',NULL,NULL,NULL,NULL,NULL,NULL),(4,6,3,'M','尿素','String',NULL,NULL,'%','','N',NULL,NULL,NULL,NULL,NULL,NULL),(5,6,5,'M','酒石酸','String',NULL,NULL,'','','N',NULL,NULL,NULL,NULL,NULL,NULL),(6,6,6,'M','A浆','String',NULL,NULL,'','','N',NULL,NULL,NULL,NULL,NULL,NULL),(7,6,4,'M','纯碱','String',NULL,NULL,'%','','N',NULL,NULL,NULL,NULL,NULL,NULL),(8,7,1,'P','常规直印','String',NULL,NULL,'老圆筒1#2#','','N',NULL,NULL,NULL,NULL,NULL,NULL),(15,7,4,'P','羊毛双面尼','String',NULL,NULL,'老圆筒1#2#','','N',NULL,NULL,NULL,NULL,NULL,NULL),(16,7,3,'P','常规羊毛','String',NULL,NULL,'老圆筒1#2#','','N',NULL,NULL,NULL,NULL,NULL,NULL),(17,7,2,'P','增白直印','String',NULL,NULL,'老圆筒1#2#','','N',NULL,NULL,NULL,NULL,NULL,NULL),(18,9,1,'E','平洗','bool',0,NULL,NULL,'','N',NULL,NULL,NULL,NULL,NULL,NULL),(19,9,2,'E','绳状','bool',0,NULL,NULL,'','N',NULL,NULL,NULL,NULL,NULL,NULL),(20,9,3,'E','喷缸','bool',0,NULL,NULL,'','N',NULL,NULL,NULL,NULL,NULL,NULL),(21,9,4,'E','防水','bool',0,NULL,NULL,'','N',NULL,NULL,NULL,NULL,NULL,NULL),(22,9,7,'M','抗静电剂','bool',0,NULL,NULL,'','N',NULL,NULL,NULL,NULL,NULL,NULL),(23,9,6,'M','加淀粉酶','bool',0,NULL,NULL,'','N',NULL,NULL,NULL,NULL,NULL,NULL),(24,9,5,'M','真丝常规','bool',0,NULL,NULL,'','N',NULL,NULL,NULL,NULL,NULL,NULL),(25,9,9,'M','汽巴固色剂','bool',0,NULL,NULL,'','N',NULL,NULL,NULL,NULL,NULL,NULL),(26,9,10,'M','羊毛固色剂','bool',0,NULL,NULL,'','N',NULL,NULL,NULL,NULL,NULL,NULL),(27,9,8,'M','常规固色剂','bool',0,NULL,NULL,'','N',NULL,NULL,NULL,NULL,NULL,NULL),(28,9,11,'M','其他固色剂','String',NULL,NULL,'','','N',NULL,NULL,NULL,NULL,NULL,NULL),(29,9,12,'M','柔软剂','String',NULL,NULL,'','','N',NULL,NULL,NULL,NULL,NULL,NULL),(30,9,13,'M','皂洗剂','bool',0,NULL,NULL,'','N',NULL,NULL,NULL,NULL,NULL,NULL),(31,9,16,'M','硅油','bool',0,NULL,NULL,'','N',NULL,NULL,NULL,NULL,NULL,NULL),(32,9,14,'M','防沾污剂','bool',0,NULL,NULL,'','N',NULL,NULL,NULL,NULL,NULL,NULL),(33,9,15,'M','增白剂','bool',0,NULL,NULL,'','N',NULL,NULL,NULL,NULL,NULL,NULL),(34,10,1,'P','数码喷印','Decimal',NULL,100.00,NULL,'','N',NULL,NULL,NULL,NULL,NULL,NULL),(36,7,9,'P','活性数码(12姆米以下)','String',NULL,NULL,'老圆筒1#2#','','N',NULL,NULL,NULL,NULL,NULL,NULL),(37,7,5,'P','化纤工艺','String',NULL,NULL,'老圆筒1#2#','','N',NULL,NULL,NULL,NULL,NULL,NULL),(38,7,6,'P','新工艺工艺','String',NULL,NULL,'老圆筒1#2#','','N',NULL,NULL,NULL,NULL,NULL,NULL),(39,7,8,'P','吊印工艺','String',NULL,NULL,'老圆筒1#2#','','N',NULL,NULL,NULL,NULL,NULL,NULL),(40,7,10,'P','活性数码(12姆米以上)','String',NULL,NULL,'老圆筒1#2#','','N',NULL,NULL,NULL,NULL,NULL,NULL),(41,7,7,'P','防印工艺','String',NULL,NULL,'老圆筒1#2#','','N',NULL,NULL,NULL,NULL,NULL,NULL),(42,7,12,'P','蒸化时长','String',NULL,NULL,'','','N',NULL,NULL,NULL,NULL,NULL,NULL),(43,7,13,'P','箱内压力','String',NULL,NULL,'','','N',NULL,NULL,NULL,NULL,NULL,NULL),(44,7,14,'P','大排气','String',NULL,NULL,'开一圈','','N',NULL,NULL,NULL,NULL,NULL,NULL),(45,7,11,'P','进气压力','String',NULL,NULL,'','','N',NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `processresource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productionfinish`
--

DROP TABLE IF EXISTS `productionfinish`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productionfinish` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `formid` varchar(20) NOT NULL,
  `formdate` date NOT NULL,
  `formtype` varchar(10) DEFAULT NULL,
  `formkind` varchar(10) DEFAULT NULL,
  `processid` int(11) DEFAULT NULL,
  `deptid` int(11) NOT NULL,
  `reason` varchar(45) DEFAULT NULL,
  `srcformtype` varchar(10) DEFAULT NULL,
  `srcformid` varchar(20) DEFAULT NULL,
  `srcitemno` varchar(45) DEFAULT NULL,
  `warehouseno` varchar(20) NOT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `status` varchar(2) NOT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `credate` datetime DEFAULT NULL,
  `optuser` varchar(20) DEFAULT NULL,
  `optdate` datetime DEFAULT NULL,
  `cfmuser` varchar(20) DEFAULT NULL,
  `cfmdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `productionfinish_UK1` (`formid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productionfinish`
--

LOCK TABLES `productionfinish` WRITE;
/*!40000 ALTER TABLE `productionfinish` DISABLE KEYS */;
/*!40000 ALTER TABLE `productionfinish` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productionfinishdetail`
--

DROP TABLE IF EXISTS `productionfinishdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productionfinishdetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` varchar(20) NOT NULL,
  `seq` int(11) NOT NULL,
  `formtype` varchar(10) DEFAULT NULL,
  `designid` int(11) NOT NULL,
  `designno` varchar(45) NOT NULL DEFAULT '0',
  `designimg` varchar(100) DEFAULT NULL,
  `designspec` varchar(100) DEFAULT NULL,
  `colorno` varchar(45) NOT NULL,
  `customercolorno` varchar(20) DEFAULT NULL,
  `customeritemno` varchar(45) DEFAULT NULL,
  `itemid` int(11) NOT NULL,
  `itemno` varchar(45) NOT NULL,
  `brand` varchar(20) DEFAULT NULL,
  `batch` varchar(20) DEFAULT NULL,
  `sn` varchar(45) DEFAULT NULL,
  `allowqty` decimal(16,2) NOT NULL DEFAULT '0.00',
  `qty` decimal(16,2) NOT NULL DEFAULT '0.00',
  `unit` varchar(45) DEFAULT NULL,
  `qcqty` decimal(16,2) NOT NULL DEFAULT '0.00',
  `defqty` decimal(16,2) NOT NULL DEFAULT '0.00',
  `badqty` decimal(16,2) NOT NULL DEFAULT '0.00',
  `addqty` decimal(16,2) NOT NULL,
  `warehouseno` varchar(20) NOT NULL,
  `badwarehouse` varchar(20) DEFAULT NULL,
  `srcapi` varchar(100) NOT NULL,
  `srcformid` varchar(20) NOT NULL,
  `srcseq` int(11) NOT NULL,
  `relapi` varchar(100) DEFAULT NULL,
  `relformid` varchar(20) DEFAULT NULL,
  `relseq` int(11) DEFAULT NULL,
  `remark` varchar(45) DEFAULT NULL,
  `status` varchar(2) NOT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `credate` datetime DEFAULT NULL,
  `optuser` varchar(20) DEFAULT NULL,
  `optdate` datetime DEFAULT NULL,
  `cfmuser` varchar(20) DEFAULT NULL,
  `cfmdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `productionfinishdetail_UK1` (`pid`,`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productionfinishdetail`
--

LOCK TABLES `productionfinishdetail` WRITE;
/*!40000 ALTER TABLE `productionfinishdetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `productionfinishdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productionorder`
--

DROP TABLE IF EXISTS `productionorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productionorder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `formid` varchar(20) NOT NULL,
  `formdate` date NOT NULL,
  `formtype` varchar(10) NOT NULL,
  `formkind` varchar(10) DEFAULT NULL,
  `customerno` varchar(20) DEFAULT NULL,
  `customer` varchar(20) DEFAULT NULL,
  `salesman` varchar(20) DEFAULT NULL,
  `designid` int(11) NOT NULL,
  `designno` varchar(45) NOT NULL DEFAULT '0',
  `designimg` varchar(100) DEFAULT NULL,
  `designspec` varchar(100) DEFAULT NULL,
  `qty` varchar(20) DEFAULT '0',
  `printdate` date DEFAULT NULL,
  `shipdate` date DEFAULT NULL,
  `issdate` datetime DEFAULT NULL,
  `findate` datetime DEFAULT NULL,
  `salesremark` varchar(200) DEFAULT NULL,
  `salesstatus` varchar(2) DEFAULT 'N',
  `jhreaddate` datetime DEFAULT NULL,
  `jhreaded` tinyint(1) DEFAULT NULL,
  `jhrecdate` datetime DEFAULT NULL,
  `jhrecman` varchar(20) DEFAULT NULL,
  `jhremark` varchar(400) DEFAULT NULL,
  `jhdeldate` date DEFAULT NULL,
  `jhdelman` varchar(20) DEFAULT NULL,
  `jhstatus` varchar(2) DEFAULT NULL,
  `hgreaddate` datetime DEFAULT NULL,
  `hgreaded` tinyint(1) DEFAULT '0',
  `hgrecdate` datetime DEFAULT NULL,
  `hgrecman` varchar(20) DEFAULT NULL,
  `hgreq` varchar(400) DEFAULT NULL,
  `hgremark` varchar(400) DEFAULT NULL,
  `hgsets` int(11) DEFAULT NULL COMMENT '套数',
  `hgspec` varchar(200) DEFAULT NULL COMMENT '规格',
  `hgcolors` int(10) unsigned DEFAULT NULL COMMENT '复色',
  `hgdeldate` date DEFAULT NULL,
  `hgdelman` varchar(20) DEFAULT NULL,
  `hgstatus` varchar(2) DEFAULT 'N',
  `zbreaddate` datetime DEFAULT NULL,
  `zbreaded` tinyint(1) DEFAULT NULL,
  `zbrecdate` datetime DEFAULT NULL,
  `zbrecman` varchar(20) DEFAULT NULL,
  `zbreq` varchar(400) DEFAULT NULL,
  `zbremark` varchar(400) DEFAULT NULL,
  `zbcount` int(11) DEFAULT NULL COMMENT '网目数',
  `zbdeldate` date DEFAULT NULL,
  `zbdelman` varchar(20) DEFAULT NULL,
  `zbstatus` varchar(2) DEFAULT 'N',
  `psreaddate` datetime DEFAULT NULL,
  `psreaded` tinyint(1) DEFAULT NULL,
  `psrecdate` datetime DEFAULT NULL,
  `psrecman` varchar(20) DEFAULT NULL,
  `psreq` varchar(400) DEFAULT NULL,
  `psremark` varchar(400) DEFAULT NULL,
  `psdeldate` date DEFAULT NULL,
  `psdelman` varchar(20) DEFAULT NULL,
  `psstatus` varchar(2) DEFAULT 'N',
  `yhreaddate` datetime DEFAULT NULL,
  `yhreaded` tinyint(1) DEFAULT NULL,
  `yhrecdate` datetime DEFAULT NULL,
  `yhrecman` varchar(20) DEFAULT NULL,
  `yhreq` varchar(400) DEFAULT NULL,
  `yhremark` varchar(400) DEFAULT NULL,
  `yhdept` varchar(45) DEFAULT NULL,
  `yhgyds` varchar(45) DEFAULT NULL,
  `yhdeldate` date DEFAULT NULL,
  `yhdelman` varchar(20) DEFAULT NULL,
  `yhstatus` varchar(2) DEFAULT 'N',
  `zhreaddate` datetime DEFAULT NULL,
  `zhreaded` tinyint(1) DEFAULT NULL,
  `zhrecdate` datetime DEFAULT NULL,
  `zhrecman` varchar(20) DEFAULT NULL,
  `zhreq` varchar(400) DEFAULT NULL,
  `zhremark` varchar(400) DEFAULT NULL,
  `zhdeldate` date DEFAULT NULL,
  `zhdelman` varchar(20) DEFAULT NULL,
  `zhstatus` varchar(2) DEFAULT 'N',
  `sxreaddate` datetime DEFAULT NULL,
  `sxreaded` tinyint(1) DEFAULT NULL,
  `sxrecdate` datetime DEFAULT NULL,
  `sxrecman` varchar(20) DEFAULT NULL,
  `sxreq` varchar(400) DEFAULT NULL,
  `sxremark` varchar(400) DEFAULT NULL,
  `sxdeldate` date DEFAULT NULL,
  `sxdelman` varchar(20) DEFAULT NULL,
  `sxstatus` varchar(2) DEFAULT 'N',
  `dxreaddate` datetime DEFAULT NULL,
  `dxreaded` tinyint(1) DEFAULT NULL,
  `dxrecdate` datetime DEFAULT NULL,
  `dxrecman` varchar(20) DEFAULT NULL,
  `dxreq` varchar(400) DEFAULT NULL,
  `dxremark` varchar(400) DEFAULT NULL,
  `dxdeldate` date DEFAULT NULL,
  `dxdelman` varchar(20) DEFAULT NULL,
  `dxstatus` varchar(2) DEFAULT NULL,
  `ckreaddate` datetime DEFAULT NULL,
  `ckreaded` tinyint(1) DEFAULT NULL,
  `ckrecdate` datetime DEFAULT NULL,
  `ckrecman` varchar(20) DEFAULT NULL,
  `ckreq` varchar(400) DEFAULT NULL,
  `ckremark` varchar(400) DEFAULT NULL,
  `ckdeldate` date DEFAULT NULL,
  `ckdelman` varchar(20) DEFAULT NULL,
  `ckstatus` varchar(2) DEFAULT NULL,
  `status` varchar(2) NOT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `credate` datetime DEFAULT NULL,
  `optdate` datetime DEFAULT NULL,
  `optuser` varchar(20) DEFAULT NULL,
  `cfmdate` datetime DEFAULT NULL,
  `cfmuser` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `productionorder_UK1` (`formid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productionorder`
--

LOCK TABLES `productionorder` WRITE;
/*!40000 ALTER TABLE `productionorder` DISABLE KEYS */;
/*!40000 ALTER TABLE `productionorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productionorderdetail`
--

DROP TABLE IF EXISTS `productionorderdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productionorderdetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` varchar(20) NOT NULL,
  `seq` int(11) NOT NULL,
  `colorno` varchar(20) DEFAULT NULL,
  `customercolorno` varchar(20) DEFAULT NULL,
  `customeritemno` varchar(45) DEFAULT NULL,
  `itemid` int(11) NOT NULL,
  `itemno` varchar(45) NOT NULL,
  `brand` varchar(20) DEFAULT NULL,
  `batch` varchar(20) DEFAULT NULL,
  `sn` varchar(20) DEFAULT NULL,
  `orderqty` decimal(16,2) NOT NULL,
  `orderunit` varchar(10) DEFAULT NULL,
  `qty` decimal(16,2) NOT NULL,
  `unit` varchar(45) DEFAULT NULL,
  `issqty` decimal(16,2) DEFAULT NULL,
  `issdate` datetime DEFAULT NULL,
  `finqty` decimal(16,2) DEFAULT '0.00',
  `findate` datetime DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `status` varchar(2) DEFAULT NULL COMMENT '00	制令输入\n10	制令确认\n20	制令申料\n30	制令领料\n\nAC	自动结案\nMC	人工结案',
  `srcapi` varchar(100) DEFAULT NULL,
  `srcformid` varchar(20) DEFAULT NULL,
  `srcseq` int(11) DEFAULT NULL,
  `relapi` varchar(100) DEFAULT NULL,
  `relformid` varchar(20) DEFAULT NULL,
  `relseq` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `productionorderdetail_UK1` (`pid`,`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productionorderdetail`
--

LOCK TABLES `productionorderdetail` WRITE;
/*!40000 ALTER TABLE `productionorderdetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `productionorderdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productionpicking`
--

DROP TABLE IF EXISTS `productionpicking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productionpicking` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `formid` varchar(20) NOT NULL,
  `formdate` date NOT NULL,
  `trtype` varchar(10) NOT NULL,
  `trkind` varchar(10) DEFAULT NULL,
  `processid` int(11) DEFAULT NULL,
  `deptid` int(11) NOT NULL,
  `reason` varchar(45) DEFAULT NULL,
  `srcformtype` varchar(10) DEFAULT NULL,
  `srcformid` varchar(20) DEFAULT NULL,
  `srcitemno` varchar(45) DEFAULT NULL,
  `warehouseno` varchar(20) NOT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `status` varchar(2) NOT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `optuser` varchar(20) DEFAULT NULL,
  `credate` datetime DEFAULT NULL,
  `optdate` datetime DEFAULT NULL,
  `cfmuser` varchar(20) DEFAULT NULL,
  `cfmdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `productionpicking_UK1` (`formid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productionpicking`
--

LOCK TABLES `productionpicking` WRITE;
/*!40000 ALTER TABLE `productionpicking` DISABLE KEYS */;
/*!40000 ALTER TABLE `productionpicking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productionpickingdetail`
--

DROP TABLE IF EXISTS `productionpickingdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productionpickingdetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` varchar(20) NOT NULL,
  `seq` int(11) NOT NULL,
  `trtype` varchar(10) NOT NULL,
  `refitemid` int(11) NOT NULL,
  `refitemno` varchar(45) NOT NULL,
  `itemid` int(11) NOT NULL,
  `itemno` varchar(45) NOT NULL,
  `colorno` varchar(20) DEFAULT NULL,
  `batch` varchar(20) DEFAULT NULL,
  `brand` varchar(20) DEFAULT NULL,
  `sn` varchar(20) DEFAULT NULL,
  `unit` varchar(10) DEFAULT NULL,
  `qty` decimal(16,2) NOT NULL,
  `warehouseno` varchar(20) NOT NULL,
  `srcapi` varchar(100) DEFAULT NULL,
  `srcformid` varchar(20) DEFAULT NULL,
  `srcseq` int(11) DEFAULT NULL,
  `relapi` varchar(100) DEFAULT NULL,
  `relformid` varchar(20) DEFAULT NULL,
  `relseq` int(11) DEFAULT NULL,
  `remark` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `productionpickingdetail_UK1` (`pid`,`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productionpickingdetail`
--

LOCK TABLES `productionpickingdetail` WRITE;
/*!40000 ALTER TABLE `productionpickingdetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `productionpickingdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productionprint`
--

DROP TABLE IF EXISTS `productionprint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productionprint` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` varchar(20) NOT NULL,
  `seq` int(11) NOT NULL,
  `deptid` int(11) NOT NULL,
  `price` decimal(16,2) NOT NULL DEFAULT '0.00',
  `qty` decimal(16,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`id`),
  UNIQUE KEY `productionprint_UK1` (`pid`,`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productionprint`
--

LOCK TABLES `productionprint` WRITE;
/*!40000 ALTER TABLE `productionprint` DISABLE KEYS */;
/*!40000 ALTER TABLE `productionprint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productionresource`
--

DROP TABLE IF EXISTS `productionresource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productionresource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` varchar(20) NOT NULL COMMENT 'Process.Id',
  `seq` int(11) NOT NULL,
  `procid` int(11) NOT NULL,
  `procseq` int(11) NOT NULL,
  `kind` varchar(10) NOT NULL COMMENT 'H:人员\nE:设备\nM:物料\nP:工艺',
  `itemid` int(11) DEFAULT NULL,
  `itemno` varchar(45) DEFAULT NULL,
  `content` varchar(45) NOT NULL,
  `valuetype` varchar(45) NOT NULL COMMENT 'bool\nDecimal\nString',
  `boolvalue` tinyint(1) DEFAULT NULL,
  `numvalue` decimal(16,2) DEFAULT NULL,
  `strvalue` varchar(45) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `itemresource_UK1` (`id`,`pid`,`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productionresource`
--

LOCK TABLES `productionresource` WRITE;
/*!40000 ALTER TABLE `productionresource` DISABLE KEYS */;
/*!40000 ALTER TABLE `productionresource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchaseacceptance`
--

DROP TABLE IF EXISTS `purchaseacceptance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchaseacceptance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `formid` varchar(20) NOT NULL,
  `formdate` date NOT NULL,
  `vendorid` int(11) NOT NULL,
  `deptid` int(11) NOT NULL,
  `warehouseno` varchar(20) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  `status` varchar(2) NOT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `credate` datetime DEFAULT NULL,
  `optuser` varchar(20) DEFAULT NULL,
  `optdate` datetime DEFAULT NULL,
  `cfmuser` varchar(20) DEFAULT NULL,
  `cfmdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `purchaseacceptance_UK1` (`formid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchaseacceptance`
--

LOCK TABLES `purchaseacceptance` WRITE;
/*!40000 ALTER TABLE `purchaseacceptance` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchaseacceptance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchaseacceptancedetail`
--

DROP TABLE IF EXISTS `purchaseacceptancedetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchaseacceptancedetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` varchar(20) NOT NULL,
  `seq` int(11) NOT NULL,
  `acceptdate` date DEFAULT NULL,
  `acceptdept` int(11) DEFAULT NULL,
  `acceptuser` int(11) DEFAULT NULL,
  `purtype` varchar(10) NOT NULL,
  `purkind` varchar(10) DEFAULT NULL,
  `itemid` int(11) NOT NULL,
  `itemno` varchar(45) NOT NULL,
  `colorno` varchar(20) DEFAULT NULL,
  `vendoritemno` varchar(45) DEFAULT NULL,
  `vendorcolorno` varchar(20) DEFAULT NULL,
  `brand` varchar(20) DEFAULT NULL,
  `batch` varchar(20) DEFAULT NULL,
  `sn` varchar(20) DEFAULT NULL,
  `allowqty` decimal(16,2) DEFAULT NULL,
  `qty` decimal(16,2) NOT NULL,
  `unit` varchar(10) DEFAULT NULL,
  `qcpass` tinyint(1) NOT NULL,
  `qcqty` decimal(16,2) NOT NULL,
  `badqty` decimal(16,2) NOT NULL,
  `addqty` decimal(16,2) NOT NULL,
  `warehouseno` varchar(20) NOT NULL,
  `badwarehouse` varchar(20) DEFAULT NULL,
  `currency` varchar(10) NOT NULL,
  `exchange` decimal(16,4) NOT NULL,
  `taxtype` varchar(2) NOT NULL,
  `taxrate` decimal(16,4) NOT NULL,
  `taxkind` varchar(10) NOT NULL,
  `tradetype` varchar(10) DEFAULT NULL,
  `tradename` varchar(45) DEFAULT NULL,
  `price` decimal(16,2) NOT NULL,
  `amts` decimal(16,2) NOT NULL,
  `extax` decimal(16,2) NOT NULL,
  `taxes` decimal(16,2) NOT NULL,
  `pricetype` varchar(2) NOT NULL COMMENT '0	手动输入\n1	合约价格\n2	历史价格',
  `srcapi` varchar(100) DEFAULT NULL,
  `srcformid` varchar(20) DEFAULT NULL,
  `srcseq` int(11) DEFAULT NULL,
  `relapi` varchar(100) DEFAULT NULL,
  `relformid` varchar(20) DEFAULT NULL,
  `relseq` int(11) DEFAULT NULL,
  `status` varchar(2) NOT NULL COMMENT '00	采购输入\n10	采购确认\n20	点收输入\n30	点收确认\n40	验收输入\n50	验收确认\nAC	自动结案\nMC	人工结案',
  `creator` varchar(20) DEFAULT NULL,
  `credate` datetime DEFAULT NULL,
  `optuser` varchar(20) DEFAULT NULL,
  `optdate` datetime DEFAULT NULL,
  `cfmuser` varchar(20) DEFAULT NULL,
  `cfmdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `purchaseacceptancedetail_UK1` (`pid`,`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchaseacceptancedetail`
--

LOCK TABLES `purchaseacceptancedetail` WRITE;
/*!40000 ALTER TABLE `purchaseacceptancedetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchaseacceptancedetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchasekind`
--

DROP TABLE IF EXISTS `purchasekind`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchasekind` (
  `id` int(11) NOT NULL,
  `purkind` varchar(10) NOT NULL,
  `name` varchar(45) NOT NULL,
  `proptype` varchar(2) DEFAULT NULL COMMENT '1成品\n2半成品\n3原料\n4物料\n5包装物\n6低值易耗品\n9费用\nA固定资产\n',
  `charge` tinyint(1) DEFAULT NULL,
  `iocode` tinyint(1) DEFAULT NULL,
  `tradeid` int(11) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  `status` varchar(2) NOT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `credate` datetime DEFAULT NULL,
  `optuser` varchar(20) DEFAULT NULL,
  `optdate` datetime DEFAULT NULL,
  `cfmuser` varchar(20) DEFAULT NULL,
  `cfmdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `purchasekind_UK1` (`purkind`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchasekind`
--

LOCK TABLES `purchasekind` WRITE;
/*!40000 ALTER TABLE `purchasekind` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchasekind` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchaseorder`
--

DROP TABLE IF EXISTS `purchaseorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchaseorder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `formid` varchar(20) NOT NULL,
  `formdate` date NOT NULL,
  `purtype` varchar(10) NOT NULL,
  `purkind` varchar(10) DEFAULT NULL,
  `abroad` tinyint(1) NOT NULL,
  `vendorid` int(11) NOT NULL,
  `deptid` int(11) NOT NULL,
  `buyerid` int(11) NOT NULL,
  `itemid` int(11) DEFAULT NULL,
  `itemno` varchar(45) DEFAULT NULL,
  `itemspec` varchar(100) DEFAULT NULL,
  `itemimg` varchar(100) DEFAULT NULL,
  `vendoritemno` varchar(45) DEFAULT NULL,
  `currency` varchar(10) NOT NULL,
  `exchange` decimal(16,4) NOT NULL,
  `taxtype` varchar(2) NOT NULL,
  `taxkind` varchar(10) NOT NULL,
  `taxrate` decimal(16,4) NOT NULL,
  `tradetype` varchar(10) DEFAULT NULL,
  `tradename` varchar(45) DEFAULT NULL,
  `paymentid` int(11) DEFAULT NULL,
  `payment` varchar(45) DEFAULT NULL,
  `refno` varchar(45) DEFAULT NULL,
  `designsets` int(11) DEFAULT NULL,
  `designprice` decimal(16,2) DEFAULT NULL,
  `totaldesign` decimal(16,2) DEFAULT NULL,
  `totalextax` decimal(16,2) DEFAULT NULL,
  `totaltaxes` decimal(16,2) DEFAULT NULL,
  `totalamts` decimal(16,2) DEFAULT NULL,
  `shiptype` varchar(10) DEFAULT NULL,
  `shpadd` varchar(200) DEFAULT NULL,
  `shipmarks` varchar(200) DEFAULT NULL,
  `freight` decimal(16,2) DEFAULT NULL,
  `insurance` decimal(16,2) DEFAULT NULL,
  `othercharges` decimal(16,2) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `testremark` varchar(200) DEFAULT NULL,
  `productremark` varchar(200) DEFAULT NULL,
  `packremark` varchar(200) DEFAULT NULL,
  `status` varchar(2) NOT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `credate` datetime DEFAULT NULL,
  `optuser` varchar(20) DEFAULT NULL,
  `optdate` datetime DEFAULT NULL,
  `cfmuser` varchar(20) DEFAULT NULL,
  `cfmdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `purchaseorder_UK1` (`formid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchaseorder`
--

LOCK TABLES `purchaseorder` WRITE;
/*!40000 ALTER TABLE `purchaseorder` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchaseorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchaseorderdetail`
--

DROP TABLE IF EXISTS `purchaseorderdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchaseorderdetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` varchar(20) NOT NULL,
  `seq` int(11) NOT NULL,
  `itemid` int(11) NOT NULL,
  `itemno` varchar(45) NOT NULL,
  `colorno` varchar(20) DEFAULT NULL,
  `customerid` int(11) DEFAULT NULL,
  `customeritemno` varchar(45) DEFAULT NULL,
  `customercolorno` varchar(20) DEFAULT NULL,
  `vendoritemno` varchar(45) DEFAULT NULL,
  `vendorcolorno` varchar(20) DEFAULT NULL,
  `brand` varchar(20) DEFAULT NULL,
  `batch` varchar(20) DEFAULT NULL,
  `sn` varchar(20) DEFAULT NULL,
  `qty` decimal(16,2) NOT NULL,
  `unit` varchar(10) DEFAULT NULL,
  `price` decimal(16,2) NOT NULL,
  `amts` decimal(16,2) NOT NULL,
  `extax` decimal(16,2) DEFAULT NULL,
  `taxes` decimal(16,2) DEFAULT NULL,
  `requestdate` date DEFAULT NULL,
  `requesttime` time DEFAULT NULL,
  `deliverydate` date NOT NULL,
  `deliverytime` time DEFAULT NULL,
  `deliveryadd` varchar(200) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `inqty` decimal(16,2) DEFAULT NULL,
  `status` varchar(2) DEFAULT NULL,
  `srcapi` varchar(100) DEFAULT NULL,
  `srcformid` varchar(20) DEFAULT NULL,
  `srcseq` int(11) DEFAULT NULL,
  `relapi` varchar(100) DEFAULT NULL,
  `relformid` varchar(20) DEFAULT NULL,
  `relseq` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `purchaseorderdetail_UK1` (`pid`,`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchaseorderdetail`
--

LOCK TABLES `purchaseorderdetail` WRITE;
/*!40000 ALTER TABLE `purchaseorderdetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchaseorderdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchaserequest`
--

DROP TABLE IF EXISTS `purchaserequest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchaserequest` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `formid` varchar(20) NOT NULL,
  `formdate` date NOT NULL,
  `deptid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `purtype` varchar(10) DEFAULT NULL,
  `purkind` varchar(10) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `status` varchar(2) NOT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `credate` date DEFAULT NULL,
  `optuser` varchar(20) DEFAULT NULL,
  `optdate` date DEFAULT NULL,
  `cfmuser` varchar(20) DEFAULT NULL,
  `cfmdate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchaserequest`
--

LOCK TABLES `purchaserequest` WRITE;
/*!40000 ALTER TABLE `purchaserequest` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchaserequest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchaserequestdetail`
--

DROP TABLE IF EXISTS `purchaserequestdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchaserequestdetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` varchar(20) NOT NULL,
  `seq` int(11) NOT NULL,
  `purtype` varchar(10) NOT NULL COMMENT '100成品\n200印花\n300白坯',
  `purkind` varchar(10) DEFAULT NULL,
  `abroad` tinyint(1) DEFAULT NULL,
  `designid` int(11) DEFAULT NULL,
  `designno` varchar(45) DEFAULT NULL,
  `designspec` varchar(100) DEFAULT NULL,
  `itemid` int(11) NOT NULL,
  `itemno` varchar(45) NOT NULL,
  `colorno` varchar(20) DEFAULT NULL,
  `customerid` int(11) DEFAULT NULL,
  `customeritemno` varchar(45) DEFAULT NULL,
  `customercolorno` varchar(20) DEFAULT NULL,
  `vendorid` int(11) DEFAULT NULL,
  `currency` varchar(10) NOT NULL,
  `exchange` decimal(16,4) NOT NULL,
  `taxtype` varchar(2) NOT NULL,
  `taxkind` varchar(10) NOT NULL,
  `taxrate` decimal(16,4) NOT NULL,
  `tradetype` varchar(10) DEFAULT NULL,
  `tradename` varchar(45) DEFAULT NULL,
  `paymentid` int(11) DEFAULT NULL,
  `payment` varchar(45) DEFAULT NULL,
  `vendoritemno` varchar(45) DEFAULT NULL,
  `vendorcolorno` varchar(20) DEFAULT NULL,
  `brand` varchar(20) DEFAULT NULL,
  `batch` varchar(20) DEFAULT NULL,
  `sn` varchar(20) DEFAULT NULL,
  `qty` decimal(16,2) NOT NULL,
  `purqty` decimal(16,2) NOT NULL,
  `unit` varchar(10) DEFAULT NULL,
  `price` decimal(16,2) NOT NULL,
  `amts` decimal(16,2) NOT NULL,
  `extax` decimal(16,2) DEFAULT NULL,
  `taxes` decimal(16,2) DEFAULT NULL,
  `requestdate` date NOT NULL,
  `requesttime` time DEFAULT NULL,
  `deliverydate` date NOT NULL,
  `deliverytime` time DEFAULT NULL,
  `deliveryadd` varchar(200) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `srcapi` varchar(100) DEFAULT NULL,
  `srcformid` varchar(20) DEFAULT NULL,
  `srcseq` int(11) DEFAULT NULL,
  `relapi` varchar(100) DEFAULT NULL,
  `relformid` varchar(20) DEFAULT NULL,
  `relseq` int(11) DEFAULT NULL,
  `status` varchar(2) DEFAULT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `credate` date DEFAULT NULL,
  `optuser` varchar(20) DEFAULT NULL,
  `optdate` date DEFAULT NULL,
  `cfmuser` varchar(20) DEFAULT NULL,
  `cfmdate` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `purchaserequestdetail_UK1` (`pid`,`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchaserequestdetail`
--

LOCK TABLES `purchaserequestdetail` WRITE;
/*!40000 ALTER TABLE `purchaserequestdetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchaserequestdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchasetransaction`
--

DROP TABLE IF EXISTS `purchasetransaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchasetransaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `formid` varchar(20) NOT NULL,
  `formdate` date NOT NULL,
  `purtype` varchar(10) NOT NULL,
  `purkind` varchar(10) DEFAULT NULL,
  `abroad` tinyint(1) NOT NULL,
  `vendorid` int(11) NOT NULL,
  `deptid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `currency` varchar(10) NOT NULL,
  `exchange` decimal(16,4) NOT NULL,
  `taxtype` varchar(2) NOT NULL,
  `taxkind` varchar(10) NOT NULL,
  `taxrate` decimal(16,4) NOT NULL,
  `tradetype` varchar(10) DEFAULT NULL,
  `tradename` varchar(45) DEFAULT NULL,
  `payment` varchar(45) DEFAULT NULL,
  `status` varchar(2) NOT NULL COMMENT '50	验收确认\nP0	部分付款\nPF	全额付款',
  `seq` int(11) NOT NULL,
  `itemid` int(11) NOT NULL,
  `itemno` varchar(45) NOT NULL,
  `colorno` varchar(20) DEFAULT NULL,
  `vendoritemno` varchar(45) DEFAULT NULL,
  `vendorcolorno` varchar(20) DEFAULT NULL,
  `brand` varchar(20) DEFAULT NULL,
  `batch` varchar(20) DEFAULT NULL,
  `sn` varchar(20) DEFAULT NULL,
  `qty` decimal(16,2) NOT NULL,
  `unit` varchar(10) DEFAULT NULL,
  `price` decimal(16,2) NOT NULL,
  `amts` decimal(16,2) NOT NULL,
  `extax` decimal(16,2) NOT NULL,
  `taxes` decimal(16,2) NOT NULL,
  `srcapi` varchar(100) DEFAULT NULL,
  `srcformid` varchar(20) DEFAULT NULL,
  `srcseq` int(11) DEFAULT NULL,
  `pid` varchar(20) DEFAULT NULL,
  `applyqty` decimal(16,2) NOT NULL DEFAULT '0.00',
  `applyamts` decimal(16,2) NOT NULL DEFAULT '0.00',
  `applyamt` decimal(16,2) NOT NULL DEFAULT '0.00',
  `puramts` decimal(16,2) NOT NULL DEFAULT '0.00',
  `puramt` decimal(16,2) NOT NULL DEFAULT '0.00',
  `preamts` decimal(16,2) DEFAULT '0.00',
  `preamt` decimal(16,2) DEFAULT '0.00',
  `addamts` decimal(16,2) DEFAULT '0.00',
  `addamt` decimal(16,2) DEFAULT '0.00',
  `offamts` decimal(16,2) DEFAULT '0.00',
  `offamt` decimal(16,2) DEFAULT '0.00',
  `shortamts` decimal(16,2) DEFAULT '0.00',
  `shortamt` decimal(16,2) DEFAULT '0.00',
  `overamts` decimal(16,2) DEFAULT '0.00',
  `overamt` decimal(16,2) DEFAULT '0.00',
  `taxamts` decimal(16,2) NOT NULL DEFAULT '0.00',
  `taxamt` decimal(16,2) NOT NULL DEFAULT '0.00',
  `backqty` decimal(16,2) DEFAULT '0.00',
  `backamts` decimal(16,2) DEFAULT '0.00',
  `backamt` decimal(16,2) DEFAULT '0.00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchasetransaction`
--

LOCK TABLES `purchasetransaction` WRITE;
/*!40000 ALTER TABLE `purchasetransaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchasetransaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salesorder`
--

DROP TABLE IF EXISTS `salesorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `salesorder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `formid` varchar(20) NOT NULL,
  `formdate` date NOT NULL,
  `ordertype` varchar(10) NOT NULL,
  `orderkind` varchar(10) DEFAULT NULL,
  `customerid` int(11) NOT NULL,
  `deptid` int(11) NOT NULL,
  `salerid` int(11) NOT NULL,
  `itemid` int(11) NOT NULL,
  `itemno` varchar(45) NOT NULL,
  `itemspec` varchar(100) DEFAULT NULL,
  `itemimg` varchar(100) DEFAULT NULL,
  `customeritemno` varchar(45) DEFAULT NULL,
  `currency` varchar(10) NOT NULL,
  `exchange` decimal(16,4) NOT NULL,
  `taxtype` varchar(2) NOT NULL,
  `taxkind` varchar(10) NOT NULL,
  `taxrate` decimal(16,4) NOT NULL,
  `tradetype` varchar(10) DEFAULT NULL,
  `tradename` varchar(45) DEFAULT NULL,
  `paymentid` int(11) DEFAULT NULL,
  `payment` varchar(45) DEFAULT NULL,
  `refno` varchar(45) DEFAULT NULL,
  `designsets` int(11) DEFAULT NULL,
  `designprice` decimal(16,2) DEFAULT NULL,
  `totaldesign` decimal(16,2) DEFAULT NULL,
  `totalextax` decimal(16,2) DEFAULT NULL,
  `totaltaxes` decimal(16,2) DEFAULT NULL,
  `totalamts` decimal(16,2) DEFAULT NULL,
  `deliverytype` varchar(10) DEFAULT NULL,
  `shipadd` varchar(200) DEFAULT NULL,
  `shipmarks` varchar(200) DEFAULT NULL,
  `freight` decimal(16,2) DEFAULT NULL,
  `insurance` decimal(16,2) DEFAULT NULL,
  `othercharges` decimal(16,2) DEFAULT NULL,
  `salesremark` varchar(200) DEFAULT NULL,
  `testremark` varchar(200) DEFAULT NULL,
  `productremark` varchar(200) DEFAULT NULL,
  `packremark` varchar(200) DEFAULT NULL,
  `status` varchar(2) NOT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `credate` datetime DEFAULT NULL,
  `optuser` varchar(20) DEFAULT NULL,
  `optdate` datetime DEFAULT NULL,
  `cfmuser` varchar(20) DEFAULT NULL,
  `cfmdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `salesorder_UK1` (`formid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salesorder`
--

LOCK TABLES `salesorder` WRITE;
/*!40000 ALTER TABLE `salesorder` DISABLE KEYS */;
/*!40000 ALTER TABLE `salesorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salesorderdetail`
--

DROP TABLE IF EXISTS `salesorderdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `salesorderdetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` varchar(20) NOT NULL,
  `seq` int(11) NOT NULL,
  `colorno` varchar(20) NOT NULL,
  `customercolorno` varchar(20) DEFAULT NULL,
  `itemid` int(11) NOT NULL,
  `itemno` varchar(45) NOT NULL,
  `brand` varchar(20) DEFAULT NULL,
  `batch` varchar(20) DEFAULT NULL,
  `sn` varchar(20) DEFAULT NULL,
  `quotedprice` decimal(16,2) DEFAULT NULL,
  `discount` decimal(16,4) DEFAULT NULL,
  `qty` decimal(16,2) NOT NULL,
  `unit` varchar(10) DEFAULT NULL,
  `price` decimal(16,2) NOT NULL,
  `amts` decimal(16,2) NOT NULL,
  `extax` decimal(16,2) DEFAULT NULL,
  `taxes` decimal(16,2) DEFAULT NULL,
  `deliverydate` date NOT NULL,
  `deliverytime` time DEFAULT NULL,
  `issqty` decimal(16,2) NOT NULL,
  `proqty` decimal(16,2) DEFAULT NULL,
  `inqty` decimal(16,2) DEFAULT '0.00',
  `shipqty` decimal(16,2) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `status` varchar(2) DEFAULT NULL COMMENT '00	订单输入\n10	订单确认\n20	安排制令\n30	制令入库\n40	通知出货\n50	出货确认\nAC	自动结案\nMC	人工结案',
  `srcapi` varchar(100) DEFAULT NULL,
  `srcformid` varchar(20) DEFAULT NULL,
  `srcseq` int(11) DEFAULT NULL,
  `relapi` varchar(100) DEFAULT NULL,
  `relformid` varchar(20) DEFAULT NULL,
  `relseq` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `salesorderdetail_UK1` (`pid`,`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salesorderdetail`
--

LOCK TABLES `salesorderdetail` WRITE;
/*!40000 ALTER TABLE `salesorderdetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `salesorderdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salesshipment`
--

DROP TABLE IF EXISTS `salesshipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `salesshipment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `formid` varchar(20) NOT NULL,
  `formdate` date NOT NULL,
  `shiptype` varchar(10) NOT NULL,
  `shipkind` varchar(10) DEFAULT NULL,
  `abroad` tinyint(1) DEFAULT NULL,
  `customerid` int(11) NOT NULL,
  `deptid` int(11) NOT NULL,
  `salerid` int(11) NOT NULL,
  `currency` varchar(10) NOT NULL,
  `exchange` decimal(16,4) NOT NULL,
  `taxtype` varchar(2) NOT NULL,
  `taxkind` varchar(10) NOT NULL,
  `taxrate` decimal(16,4) NOT NULL,
  `tradetype` varchar(10) DEFAULT NULL,
  `tradename` varchar(45) DEFAULT NULL,
  `totalextax` decimal(16,2) DEFAULT NULL,
  `totaltaxes` decimal(16,2) DEFAULT NULL,
  `totalamts` decimal(16,2) DEFAULT NULL,
  `deliverytype` varchar(45) DEFAULT NULL,
  `shipadd` varchar(200) DEFAULT NULL,
  `shipmarks` varchar(200) DEFAULT NULL,
  `freight` decimal(16,2) DEFAULT NULL,
  `insurance` decimal(16,2) DEFAULT NULL,
  `othercharges` decimal(16,2) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  `warehouseno` varchar(20) DEFAULT NULL,
  `status` varchar(2) NOT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `credate` datetime DEFAULT NULL,
  `optuser` varchar(20) DEFAULT NULL,
  `optdate` datetime DEFAULT NULL,
  `cfmuser` varchar(20) DEFAULT NULL,
  `cfmdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `salesshipment_UK1` (`formid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salesshipment`
--

LOCK TABLES `salesshipment` WRITE;
/*!40000 ALTER TABLE `salesshipment` DISABLE KEYS */;
/*!40000 ALTER TABLE `salesshipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salesshipmentdetail`
--

DROP TABLE IF EXISTS `salesshipmentdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `salesshipmentdetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` varchar(20) NOT NULL,
  `seq` int(11) NOT NULL,
  `itemid` int(11) NOT NULL,
  `itemno` varchar(45) NOT NULL,
  `itemimg` varchar(100) DEFAULT NULL,
  `colorno` varchar(20) DEFAULT NULL,
  `customeritemno` varchar(45) DEFAULT NULL,
  `customercolorno` varchar(20) DEFAULT NULL,
  `customerrefno` varchar(45) DEFAULT NULL,
  `brand` varchar(20) DEFAULT NULL,
  `batch` varchar(20) DEFAULT NULL,
  `sn` varchar(20) DEFAULT NULL,
  `allowqty` decimal(16,2) DEFAULT NULL,
  `qty` decimal(16,2) NOT NULL,
  `unit` varchar(10) DEFAULT NULL,
  `price` decimal(16,2) NOT NULL,
  `amts` decimal(16,2) NOT NULL,
  `extax` decimal(16,2) NOT NULL,
  `taxes` decimal(16,2) NOT NULL,
  `warehouseno` varchar(20) NOT NULL,
  `srcapi` varchar(100) DEFAULT NULL,
  `srcformid` varchar(20) DEFAULT NULL,
  `srcseq` int(11) DEFAULT NULL,
  `relapi` varchar(100) DEFAULT NULL,
  `relformid` varchar(20) DEFAULT NULL,
  `relseq` int(11) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  `status` varchar(2) NOT NULL COMMENT '00	订单输入\n10	订单确认\n20	生产发料\n30	生产入库\n40	出货通知\n50	出货确认\nAC	自动结案\nMC	人工结案',
  PRIMARY KEY (`id`),
  UNIQUE KEY `salesshipmentdetail_UK1` (`pid`,`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salesshipmentdetail`
--

LOCK TABLES `salesshipmentdetail` WRITE;
/*!40000 ALTER TABLE `salesshipmentdetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `salesshipmentdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salestransaction`
--

DROP TABLE IF EXISTS `salestransaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `salestransaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `formid` varchar(20) NOT NULL,
  `formdate` date NOT NULL,
  `shiptype` varchar(10) NOT NULL,
  `shipkind` varchar(10) DEFAULT NULL,
  `abroad` tinyint(1) DEFAULT '0',
  `customerid` int(11) NOT NULL,
  `deptid` int(11) NOT NULL,
  `salerid` int(11) NOT NULL,
  `currency` varchar(10) NOT NULL,
  `exchange` decimal(16,4) NOT NULL,
  `taxtype` varchar(2) NOT NULL,
  `taxkind` varchar(10) NOT NULL,
  `taxrate` decimal(16,4) NOT NULL,
  `tradetype` varchar(10) DEFAULT NULL,
  `tradename` varchar(45) DEFAULT NULL,
  `payment` varchar(45) DEFAULT NULL,
  `status` varchar(2) NOT NULL COMMENT '50出货\nB0部分开票\nBF全部开票',
  `seq` int(11) NOT NULL,
  `itemid` int(11) NOT NULL,
  `itemno` varchar(45) NOT NULL,
  `itemimg` varchar(100) DEFAULT NULL,
  `colorno` varchar(20) DEFAULT NULL,
  `customeritemno` varchar(45) DEFAULT NULL,
  `customercolorno` varchar(20) DEFAULT NULL,
  `customerrefno` varchar(45) DEFAULT NULL,
  `brand` varchar(20) DEFAULT NULL,
  `batch` varchar(20) DEFAULT NULL,
  `sn` varchar(20) DEFAULT NULL,
  `qty` decimal(16,2) NOT NULL,
  `unit` varchar(10) DEFAULT NULL,
  `price` decimal(16,2) NOT NULL,
  `amts` decimal(16,2) NOT NULL,
  `extax` decimal(16,2) NOT NULL,
  `taxes` decimal(16,2) NOT NULL,
  `srcapi` varchar(100) DEFAULT NULL,
  `srcformid` varchar(20) DEFAULT NULL,
  `srcseq` int(11) DEFAULT NULL,
  `pid` varchar(20) DEFAULT NULL,
  `billqty` decimal(16,2) NOT NULL DEFAULT '0.00',
  `shipamts` decimal(16,2) NOT NULL DEFAULT '0.00',
  `shipamt` decimal(16,2) NOT NULL DEFAULT '0.00',
  `preamts` decimal(16,2) DEFAULT '0.00',
  `preamt` decimal(16,2) DEFAULT '0.00',
  `addamts` decimal(16,2) DEFAULT '0.00',
  `addamt` decimal(16,2) DEFAULT '0.00',
  `offamts` decimal(16,2) DEFAULT '0.00',
  `offamt` decimal(16,2) DEFAULT '0.00',
  `taxamts` decimal(16,2) NOT NULL DEFAULT '0.00',
  `taxamt` decimal(16,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salestransaction`
--

LOCK TABLES `salestransaction` WRITE;
/*!40000 ALTER TABLE `salestransaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `salestransaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salestype`
--

DROP TABLE IF EXISTS `salestype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `salestype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(10) NOT NULL,
  `name` varchar(20) NOT NULL,
  `process` tinyint(1) NOT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `status` varchar(2) NOT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `credate` datetime DEFAULT NULL,
  `optuser` varchar(20) DEFAULT NULL,
  `optdate` datetime DEFAULT NULL,
  `cfmuser` varchar(20) DEFAULT NULL,
  `cfmdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `salestype_UK1` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salestype`
--

LOCK TABLES `salestype` WRITE;
/*!40000 ALTER TABLE `salestype` DISABLE KEYS */;
INSERT INTO `salestype` VALUES (1,'JX','经销',0,'','V','系统管理员','2016-07-02 20:58:01',NULL,NULL,'系统管理员','2016-07-02 21:45:36'),(2,'WX','外销',0,'','V','系统管理员','2016-07-02 20:58:55',NULL,NULL,'系统管理员','2016-07-02 21:45:30'),(3,'JG','加工',1,'','V','系统管理员','2016-07-02 20:59:06',NULL,NULL,'系统管理员','2016-07-02 21:45:25');
/*!40000 ALTER TABLE `salestype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sysgrantmodule`
--

DROP TABLE IF EXISTS `sysgrantmodule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sysgrantmodule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kind` varchar(2) NOT NULL DEFAULT 'U',
  `userid` int(11) NOT NULL,
  `moduleid` int(11) NOT NULL,
  `status` varchar(2) NOT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `credate` datetime DEFAULT NULL,
  `optuser` varchar(20) DEFAULT NULL,
  `optdate` datetime DEFAULT NULL,
  `cfmuser` varchar(20) DEFAULT NULL,
  `cfmdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sysgrantmodule_UNIQUE` (`userid`,`moduleid`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sysgrantmodule`
--

LOCK TABLES `sysgrantmodule` WRITE;
/*!40000 ALTER TABLE `sysgrantmodule` DISABLE KEYS */;
INSERT INTO `sysgrantmodule` VALUES (1,'U',0,1,'V','Admin','2015-10-15 12:29:42','Admin','2015-10-16 21:41:20','Admin','2015-11-14 13:14:35'),(2,'U',0,3,'V','Admin','2015-10-15 12:31:31','Admin','2015-10-16 21:41:18','Admin','2015-11-14 13:14:38'),(3,'U',0,2,'V','Admin','2015-10-20 15:00:18',NULL,NULL,'Admin','2015-11-14 13:14:42'),(7,'U',0,5,'V','Admin','2015-10-20 15:01:33',NULL,NULL,'Admin','2015-11-14 13:14:45'),(8,'U',0,4,'V','Admin','2015-10-20 15:04:51',NULL,NULL,'Admin','2015-11-14 13:14:48'),(13,'U',6,1,'V','Admin','2015-11-14 18:37:59',NULL,NULL,'Admin','2015-11-14 18:38:47'),(14,'U',6,3,'V','Admin','2015-11-14 18:38:18',NULL,NULL,'Admin','2015-11-14 18:38:49'),(15,'U',6,4,'N','Admin','2015-11-18 08:22:24',NULL,NULL,NULL,NULL),(16,'U',6,5,'N','Admin','2015-11-18 08:22:50',NULL,NULL,NULL,NULL),(19,'U',8,1,'N','Admin','2015-11-24 11:38:24',NULL,NULL,NULL,NULL),(20,'U',9,1,'N','Admin','2015-11-24 11:38:54',NULL,NULL,NULL,NULL),(21,'U',10,1,'N','Admin','2015-11-24 11:39:05',NULL,NULL,NULL,NULL),(22,'U',11,1,'N','Admin','2015-11-24 11:39:21',NULL,NULL,NULL,NULL),(23,'U',12,1,'N','Admin','2015-11-24 11:39:52',NULL,NULL,NULL,NULL),(24,'U',13,1,'N','Admin','2015-11-24 11:40:04',NULL,NULL,NULL,NULL),(27,'U',14,1,'N','Admin','2015-11-24 11:40:16',NULL,NULL,NULL,NULL),(28,'U',15,4,'N','Admin','2015-11-24 14:34:38',NULL,NULL,NULL,NULL),(29,'U',16,4,'N','Admin','2015-11-24 14:34:57',NULL,NULL,NULL,NULL),(30,'U',17,3,'N','Admin','2015-11-24 14:35:11',NULL,NULL,NULL,NULL),(31,'U',18,4,'N','Admin','2015-11-24 14:35:35',NULL,NULL,NULL,NULL),(32,'U',19,4,'N','Admin','2015-11-24 14:35:46',NULL,NULL,NULL,NULL),(33,'U',20,4,'N','Admin','2015-11-24 14:35:59',NULL,NULL,NULL,NULL),(34,'U',21,3,'N','Admin','2015-11-24 14:36:09',NULL,NULL,NULL,NULL),(35,'U',21,4,'N','Admin','2015-11-24 14:36:20',NULL,NULL,NULL,NULL),(36,'U',21,5,'N','Admin','2015-11-24 14:36:34',NULL,NULL,NULL,NULL),(37,'U',22,4,'N','Admin','2015-11-24 14:36:45',NULL,NULL,NULL,NULL),(38,'U',23,5,'N','Admin','2015-11-24 14:36:58',NULL,NULL,NULL,NULL),(39,'U',24,4,'N','Admin','2015-11-24 14:37:07',NULL,NULL,NULL,NULL),(40,'U',25,1,'N','Admin','2015-11-24 14:37:18',NULL,NULL,NULL,NULL),(41,'U',17,5,'N','Admin','2015-11-24 14:37:26',NULL,NULL,NULL,NULL),(42,'U',17,1,'N','Admin','2015-12-04 14:09:32',NULL,NULL,NULL,NULL),(43,'U',26,3,'N','Admin','2015-12-09 17:00:20',NULL,NULL,NULL,NULL),(44,'U',26,4,'N','Admin','2015-12-09 17:00:57',NULL,NULL,NULL,NULL),(45,'U',10,2,'N','Admin','2016-06-13 20:02:10',NULL,NULL,NULL,NULL),(46,'U',10,6,'N','Admin','2016-06-13 20:02:42',NULL,NULL,NULL,NULL),(47,'U',14,2,'N','Admin','2016-06-13 22:47:52',NULL,NULL,NULL,NULL),(48,'U',0,6,'N','Admin','2016-06-14 22:56:11',NULL,NULL,NULL,NULL),(49,'U',0,7,'N','Admin','2016-06-15 22:22:46',NULL,NULL,NULL,NULL),(50,'U',7,2,'N','Admin','2016-06-18 12:54:06',NULL,NULL,NULL,NULL),(51,'U',25,2,'N','Admin','2016-06-18 12:54:24',NULL,NULL,NULL,NULL),(52,'U',25,6,'N','Admin','2016-06-18 12:54:52',NULL,NULL,NULL,NULL),(53,'U',25,5,'N','Admin','2016-06-18 12:55:03',NULL,NULL,NULL,NULL),(54,'U',7,1,'N','Admin','2016-06-18 12:55:27',NULL,NULL,NULL,NULL),(55,'U',7,6,'N','Admin','2016-06-18 12:55:47',NULL,NULL,NULL,NULL),(56,'U',7,5,'N','Admin','2016-06-18 12:55:54',NULL,NULL,NULL,NULL),(57,'U',14,6,'N','Admin','2016-06-18 12:56:02',NULL,NULL,NULL,NULL),(58,'U',14,5,'N','Admin','2016-06-18 12:56:25',NULL,NULL,NULL,NULL),(59,'U',10,5,'N','Admin','2016-06-18 12:56:33',NULL,NULL,NULL,NULL),(60,'U',9,2,'N','Admin','2016-06-18 12:57:22',NULL,NULL,NULL,NULL),(61,'U',9,6,'N','Admin','2016-06-18 12:57:57',NULL,NULL,NULL,NULL),(62,'U',9,5,'N','Admin','2016-06-18 12:58:05',NULL,NULL,NULL,NULL),(63,'U',11,2,'N','Admin','2016-06-18 12:58:14',NULL,NULL,NULL,NULL),(64,'U',11,6,'N','Admin','2016-06-18 12:58:59',NULL,NULL,NULL,NULL),(65,'U',11,5,'N','Admin','2016-06-18 12:59:06',NULL,NULL,NULL,NULL),(66,'U',21,2,'N','系统管理员','2016-08-11 12:17:37',NULL,NULL,NULL,NULL),(67,'U',0,8,'N','系统管理员','2016-10-28 23:31:20',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `sysgrantmodule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sysmodule`
--

DROP TABLE IF EXISTS `sysmodule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sysmodule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `sortid` int(11) NOT NULL DEFAULT '0',
  `descript` varchar(50) DEFAULT NULL,
  `status` varchar(2) NOT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `credate` datetime DEFAULT NULL,
  `optdate` datetime DEFAULT NULL,
  `optuser` varchar(20) DEFAULT NULL,
  `cfmuser` varchar(20) DEFAULT NULL,
  `cfmdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sysmodule`
--

LOCK TABLES `sysmodule` WRITE;
/*!40000 ALTER TABLE `sysmodule` DISABLE KEYS */;
INSERT INTO `sysmodule` VALUES (1,'销售',2,'','V','Admin','2015-10-10 21:54:16','2016-02-10 23:25:26','Admin','Admin','2016-02-10 23:25:34'),(2,'基础',1,'','V','Admin','2015-10-10 22:01:34','2016-02-10 23:25:18','Admin','Admin','2016-02-10 23:25:36'),(3,'计划',5,'','N','Admin','2015-10-10 22:02:00','2016-06-14 23:09:34','Admin',NULL,NULL),(4,'生产',7,'','N','Admin','2015-10-10 22:02:21','2016-02-15 15:44:47','Admin',NULL,NULL),(5,'仓库',6,'','N','Admin','2015-10-11 09:59:09','2016-02-15 15:44:50','Admin',NULL,NULL),(6,'采购',4,'','N','Admin','2016-02-15 15:44:29',NULL,NULL,NULL,NULL),(7,'环境',0,'','N','Admin','2016-05-05 21:08:47',NULL,NULL,NULL,NULL),(8,'财务',3,'','N','系统管理员','2016-10-25 22:48:33',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `sysmodule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sysprg`
--

DROP TABLE IF EXISTS `sysprg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sysprg` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `api` varchar(400) NOT NULL,
  `moduleid` int(11) NOT NULL,
  `sortid` int(11) DEFAULT '0',
  `noauto` tinyint(1) NOT NULL DEFAULT '0',
  `nochange` tinyint(1) DEFAULT NULL,
  `nolead` varchar(4) DEFAULT NULL,
  `noformat` varchar(8) DEFAULT NULL,
  `noseqlen` int(11) DEFAULT '4',
  `doadd` tinyint(1) NOT NULL DEFAULT '0',
  `doedit` tinyint(1) NOT NULL DEFAULT '0',
  `dodel` tinyint(1) NOT NULL DEFAULT '0',
  `doprt` tinyint(1) NOT NULL DEFAULT '0',
  `dopriv` tinyint(1) NOT NULL DEFAULT '0',
  `docfm` tinyint(1) NOT NULL DEFAULT '0',
  `douncfm` tinyint(1) NOT NULL DEFAULT '0',
  `doaudit` tinyint(1) NOT NULL DEFAULT '0',
  `dounaudit` tinyint(1) NOT NULL DEFAULT '0',
  `rptclazz` varchar(100) DEFAULT NULL,
  `rptdesign` varchar(100) DEFAULT NULL,
  `rptjndi` varchar(200) DEFAULT NULL,
  `rptformat` varchar(10) DEFAULT NULL,
  `descript` varchar(50) DEFAULT NULL,
  `status` varchar(2) NOT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `credate` datetime DEFAULT NULL,
  `optuser` varchar(20) DEFAULT NULL,
  `optdate` datetime DEFAULT NULL,
  `cfmuser` varchar(20) DEFAULT NULL,
  `cfmdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sysprg_UK1` (`nolead`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sysprg`
--

LOCK TABLES `sysprg` WRITE;
/*!40000 ALTER TABLE `sysprg` DISABLE KEYS */;
INSERT INTO `sysprg` VALUES (1,'流转单维护','productionorder',3,21,1,0,'MA','yyMM',4,1,1,1,1,0,1,1,0,0,'','productionorder.rptdesign','java:global/HHSC/HHSC-ejb/ProductionOrderBean!com.hhsc.ejb.ProductionOrderBean','pdf','','N','Admin','2015-10-10 23:36:25','Admin','2016-02-14 16:06:33',NULL,NULL),(2,'计划维护','jh',3,22,0,0,NULL,NULL,NULL,0,1,0,0,0,1,1,0,0,NULL,'','',NULL,'','V','Admin','2015-10-10 23:37:56','Admin','2016-02-10 23:26:18','Admin','2016-02-10 23:30:44'),(3,'画稿维护','hg',4,1,0,0,NULL,NULL,NULL,0,1,0,0,0,1,1,0,0,NULL,NULL,NULL,NULL,'','V','Admin','2015-10-10 23:45:30',NULL,NULL,'Admin','2015-10-25 14:28:44'),(4,'配色维护','ps',4,3,0,0,NULL,NULL,NULL,0,1,0,1,0,1,1,0,0,NULL,'productionorder.rptdesign','java:global/HHSC/HHSC-ejb/ProductionOrderBean!com.hhsc.ejb.ProductionOrderBean','pdf','','N','Admin','2015-10-11 09:33:28','系统管理员','2016-09-09 23:57:17',NULL,NULL),(5,'制版维护','zb',4,2,0,0,NULL,NULL,NULL,0,1,0,0,0,1,1,0,0,NULL,NULL,NULL,NULL,'','V','Admin','2015-10-11 09:37:46',NULL,NULL,'Admin','2015-10-25 14:29:03'),(6,'印花维护','yh',4,4,0,0,NULL,NULL,NULL,0,1,0,0,0,1,1,0,0,NULL,NULL,NULL,NULL,'','V','Admin','2015-10-11 09:39:29',NULL,NULL,'Admin','2015-10-25 14:29:11'),(7,'蒸化维护','zh',4,5,0,0,NULL,NULL,NULL,0,1,0,0,0,1,1,0,0,NULL,'','','pdf','','N','Admin','2015-10-11 09:58:02','系统管理员','2016-07-15 21:15:44',NULL,NULL),(8,'仓库维护','ck',5,561,0,0,NULL,NULL,NULL,0,1,0,0,0,1,1,0,0,NULL,'','','pdf','','N','Admin','2015-10-11 09:59:05','系统管理员','2016-10-22 12:45:16',NULL,NULL),(9,'成品维护','cp',5,563,0,0,NULL,NULL,NULL,0,1,0,0,0,1,1,0,0,NULL,'','','pdf','','N','Admin','2015-10-11 10:05:08','系统管理员','2016-10-22 12:45:37',NULL,NULL),(10,'生产入库','productionfinish',5,560,1,0,'MF','yyMM',4,1,1,1,0,0,1,1,0,0,NULL,'','','pdf','','N','Admin','2015-11-26 22:48:53','系统管理员','2016-07-21 23:53:14',NULL,NULL),(11,'花号登记','itemdesign',2,16,1,0,'A','yy',4,1,1,1,0,0,1,1,0,0,NULL,'','','pdf','','N','Admin','2015-11-27 07:55:14','Admin','2016-03-27 22:06:02',NULL,NULL),(12,'大类维护','itemcategory',7,1,0,0,NULL,NULL,NULL,1,1,1,0,0,1,1,0,0,NULL,'','','pdf','','N','Admin','2016-02-10 23:10:41',NULL,NULL,NULL,NULL),(13,'客户登记','customer',1,11,1,0,'K','',6,1,1,1,0,0,1,1,0,0,NULL,'','','pdf','','N','Admin','2016-02-14 16:04:51',NULL,NULL,NULL,NULL),(14,'厂商登记','vendor',6,610,1,0,'HS','',4,1,1,1,0,0,1,1,0,0,NULL,'','',NULL,'','N','Admin','2016-02-15 14:52:26',NULL,NULL,NULL,NULL),(17,'面料登记','itemmaster',2,15,1,0,'W','yy',4,1,1,1,0,0,1,1,0,0,NULL,'','','pdf','','N','Admin','2016-03-06 18:42:08',NULL,NULL,NULL,NULL),(18,'销售合同','salesorder',1,21,1,0,'HH','yyMM',4,1,1,1,1,0,1,1,0,0,'com.hhsc.rpt.SalesOrderReport','salesorder.rptdesign','java:global/HHSC/HHSC-ejb/SalesOrderBean!com.hhsc.ejb.SalesOrderBean','pdf','','N','Admin','2016-03-16 23:33:02',NULL,NULL,NULL,NULL),(19,'币别汇率','currency',2,10,0,0,NULL,NULL,NULL,1,1,1,0,0,1,1,0,0,NULL,'','','pdf','','N','Admin','2016-03-22 21:51:55',NULL,NULL,NULL,NULL),(20,'部门维护','department',2,13,0,0,NULL,NULL,NULL,1,1,1,0,0,1,1,0,0,NULL,'','','pdf','','N','Admin','2016-03-22 21:52:46',NULL,NULL,NULL,NULL),(21,'区域维护','area',2,11,0,0,NULL,NULL,NULL,1,1,1,0,0,1,1,0,0,NULL,'','','pdf','','N','Admin','2016-03-27 16:18:30',NULL,NULL,NULL,NULL),(22,'单位维护','unit',2,12,0,0,NULL,NULL,NULL,1,1,1,0,0,1,1,0,0,NULL,'','','pdf','','N','Admin','2016-03-27 18:29:30',NULL,NULL,NULL,NULL),(23,'面料请购','itemmasterrequest',1,22,1,0,'RW','yyMM',4,1,1,1,1,0,1,1,0,0,NULL,'purchaserequest','java:global/HHSC/HHSC-ejb/PurchaseRequestBean!com.hhsc.ejb.PurchaseRequestBean','pdf','','N','Admin','2016-03-29 22:14:37',NULL,NULL,NULL,NULL),(24,'成品请购','itemfinishedrequest',1,23,1,0,'RT','yyMM',4,1,1,1,1,0,1,1,0,0,NULL,'purchaserequest','java:global/HHSC/HHSC-ejb/PurchaseRequestBean!com.hhsc.ejb.PurchaseRequestBean','pdf','','N','Admin','2016-03-31 19:42:45',NULL,NULL,NULL,NULL),(25,'外发成品登记','itemfinished',2,17,1,0,'T','yy',4,1,1,1,0,0,1,1,0,0,NULL,'','','pdf','','N','Admin','2016-04-01 13:19:34',NULL,NULL,NULL,NULL),(26,'采购草稿维护','purchasedraft',6,620,0,0,NULL,NULL,NULL,0,1,0,0,0,0,0,0,0,NULL,'','','pdf','','N','Admin','2016-04-02 15:46:08',NULL,NULL,NULL,NULL),(27,'请购明细查询','itemrequest',1,30,0,0,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,NULL,'','','pdf','','N','Admin','2016-04-04 14:46:22',NULL,NULL,NULL,NULL),(28,'草稿产生采购','purchaseinit',6,625,0,0,NULL,NULL,NULL,0,0,0,0,0,1,0,0,0,'','','','pdf','','N','Admin','2016-04-06 13:13:26',NULL,NULL,NULL,NULL),(29,'采购维护','purchaseorder',6,650,1,0,'PO','yyMM',4,1,1,1,1,0,1,1,0,0,'','purchaseorder','java:global/HHSC/HHSC-ejb/PurchaseOrderBean!com.hhsc.ejb.PurchaseOrderBean','pdf','','N','Admin','2016-04-07 09:20:25',NULL,NULL,NULL,NULL),(30,'库存交易类别','transactiontype',7,3,0,0,NULL,NULL,NULL,1,1,1,0,0,1,1,0,0,NULL,'','','pdf','','N','Admin','2016-05-05 20:02:38',NULL,NULL,NULL,NULL),(31,'进货点收','purchaseacceptance',5,510,1,0,'PA','yyMM',4,1,1,1,1,0,1,1,0,0,'com.hhsc.rpt.PurchaseAcceptanceReport','purchaseacceptance.rptdesign','java:global/HHSC/HHSC-ejb/PurchaseAcceptanceBean!com.hhsc.ejb.PurchaseAcceptanceBean','pdf','','N','Admin','2016-05-10 21:35:17',NULL,NULL,NULL,NULL),(32,'库号维护','warehouse',2,14,0,0,NULL,NULL,NULL,1,1,1,0,0,1,1,0,0,NULL,'','','pdf','','N','Admin','2016-05-21 13:21:47',NULL,NULL,NULL,NULL),(33,'验收入库','purchasestorage',5,530,0,0,NULL,NULL,NULL,0,1,0,0,0,1,1,0,0,NULL,'','','pdf','','N','Admin','2016-05-22 02:15:14',NULL,NULL,NULL,NULL),(34,'出货通知','shipmentadvice',1,40,1,0,'SD','yyMM',4,1,1,1,1,0,0,0,0,0,'com.hhsc.rpt.SalesShipmentReport','shipmentadvice.rptdesign','java:global/HHSC/HHSC-ejb/SalesShipmentBean!com.hhsc.ejb.SalesShipmentBean','pdf','','N','Admin','2016-05-26 09:17:36',NULL,NULL,NULL,NULL),(35,'订单出货','salesshipment',5,580,0,0,NULL,NULL,NULL,0,1,0,1,0,1,1,0,0,'com.hhsc.rpt.SalesShipmentReport','salesshipment.rptdesign','java:global/HHSC/HHSC-ejb/SalesShipmentBean!com.hhsc.ejb.SalesShipmentBean','pdf','','N','Admin','2016-05-26 09:18:42',NULL,NULL,NULL,NULL),(36,'库存查询','iteminventory',5,501,0,0,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,NULL,'','','pdf','','N','Admin','2016-06-04 21:20:09',NULL,NULL,NULL,NULL),(37,'库存查询','iteminventory',2,61,0,0,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,NULL,'','','pdf','','N','Admin','2016-06-05 10:37:57',NULL,NULL,NULL,NULL),(38,'客供面料登记','itemcustomer',2,18,1,0,'KG','yy',4,1,1,1,0,0,1,1,0,0,NULL,'','','pdf','','N','系统管理员','2016-06-27 23:15:24',NULL,NULL,NULL,NULL),(39,'库存异动','itemtransaction',5,503,1,0,'IS','yyMM',4,1,1,1,0,0,1,1,0,0,NULL,'','','pdf','','N','系统管理员','2016-06-27 23:18:23',NULL,NULL,NULL,NULL),(40,'销售类别维护','salestype',7,2,0,0,NULL,NULL,NULL,1,1,1,0,0,1,1,0,0,NULL,'','','pdf','','N','系统管理员','2016-07-02 20:55:24',NULL,NULL,NULL,NULL),(41,'生产工艺','process',2,20,0,0,NULL,NULL,NULL,1,1,1,0,0,1,1,0,0,NULL,'','','pdf','','N','系统管理员','2016-07-06 16:50:53',NULL,NULL,NULL,NULL),(42,'生产领料','productionpicking',5,552,1,0,'MB','yyMM',4,1,1,1,1,0,1,1,0,0,NULL,'productionpicking.rptdesign','java:global/HHSC/HHSC-ejb/ProductionPickingBean!com.hhsc.ejb.ProductionPickingBean','pdf','','N','系统管理员','2016-07-11 14:26:01',NULL,NULL,NULL,NULL),(43,'工艺设计','itemprocess',3,1,0,0,NULL,NULL,NULL,1,1,1,1,0,1,1,0,0,NULL,'itemprocess.rptdesign','java:global/HHSC/HHSC-ejb/ItemProcessBean!com.hhsc.ejb.ItemProcessBean','pdf','','N','系统管理员','2016-07-11 22:17:39',NULL,NULL,NULL,NULL),(44,'水洗维护','sx',4,7,0,0,NULL,NULL,NULL,0,1,0,0,0,1,1,0,0,NULL,'','','pdf','','N','系统管理员','2016-07-15 21:40:00',NULL,NULL,NULL,NULL),(45,'定型维护','dx',4,8,0,0,NULL,NULL,NULL,0,1,0,0,0,1,1,0,0,NULL,'','','pdf','','N','系统管理员','2016-07-15 21:40:57',NULL,NULL,NULL,NULL),(46,'工艺群组','processgroup',2,21,0,0,NULL,NULL,NULL,1,1,1,0,0,1,1,0,0,NULL,'','','pdf','','N','系统管理员','2016-09-03 09:44:04',NULL,NULL,NULL,NULL),(47,'采购明细查询','itempurchase',5,1,0,0,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,NULL,'','','pdf','','N','系统管理员','2016-10-20 14:07:04',NULL,NULL,NULL,NULL),(48,'采购明细查询','purchasedetail',6,660,0,0,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,'','','','pdf','','N','系统管理员','2016-10-22 12:01:04',NULL,NULL,NULL,NULL),(49,'点验明细查询','itemacceptance',5,540,0,0,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,NULL,'','','pdf','','N','系统管理员','2016-10-22 12:36:16',NULL,NULL,NULL,NULL),(50,'销售开票','salesinvoice',8,1,0,0,NULL,NULL,NULL,1,0,1,0,0,1,1,0,0,'','','','pdf','','N','系统管理员','2016-10-25 22:50:10',NULL,NULL,NULL,NULL),(55,'销售收款','accountreceipt',8,2,1,0,'SR','yyMM',4,1,1,1,0,0,1,1,0,0,'',' ','','pdf','','N','系统管理员','2016-10-28 15:47:04',NULL,NULL,NULL,NULL),(56,'生产明细查询','productiondetail',3,25,0,0,NULL,NULL,NULL,0,0,0,0,0,0,0,0,0,'','','','pdf','','N','系统管理员','2016-10-30 21:32:24',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `sysprg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `systemuser`
--

DROP TABLE IF EXISTS `systemuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `systemuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(20) NOT NULL,
  `username` varchar(16) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(32) NOT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `birth` datetime DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `photo` varchar(200) DEFAULT NULL,
  `profile` varchar(45) DEFAULT NULL,
  `deptid` int(11) DEFAULT NULL,
  `superuser` tinyint(1) DEFAULT '0',
  `failure` int(11) DEFAULT '0',
  `locked` tinyint(1) DEFAULT '0',
  `lastlogin` datetime DEFAULT NULL,
  `newestlogin` datetime DEFAULT NULL,
  `status` varchar(2) NOT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `credate` datetime DEFAULT NULL,
  `optuser` varchar(20) DEFAULT NULL,
  `optdate` datetime DEFAULT NULL,
  `cfmuser` varchar(20) DEFAULT NULL,
  `cfmdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `systemuser_UK1` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `systemuser`
--

LOCK TABLES `systemuser` WRITE;
/*!40000 ALTER TABLE `systemuser` DISABLE KEYS */;
INSERT INTO `systemuser` VALUES (0,'Admin','系统管理员',NULL,'e10adc3949ba59abbe56e057f20f883e',NULL,NULL,NULL,NULL,NULL,1,1,0,0,'2016-10-29 15:57:38','2016-10-30 22:32:26','N',NULL,NULL,'Admin','2016-06-14 22:13:28',NULL,NULL),(6,'13636520123','陈志华','chen@shs1988.com','b08edf02dfe454ff45d445d7f2f7aab7',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,'2015-11-14 18:39:03','2016-09-29 12:46:52','V','system','2015-11-14 13:12:34','系统管理员','2016-09-29 12:40:39','系统管理员','2016-09-29 12:41:03'),(7,'13621722756','王月','wangyue@shs1988.com','338950d9c045e30e626c25e22f2d7776',NULL,NULL,NULL,NULL,NULL,1,0,0,0,'2016-10-14 17:25:13','2016-10-29 17:10:25','M','system','2015-11-18 08:22:04','Admin','2015-11-19 14:56:53',NULL,NULL),(8,'13651967124','唐金秋','tang@shs1988.com','59f2443a4317918ce29ad28a14e1bdb7',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,'2016-06-18 10:31:56','2016-06-18 12:46:27','N','system','2015-11-19 08:38:12',NULL,NULL,NULL,NULL),(9,'13621722196','朱怡雯','zyw@shs1988.com','dc7bba7b24cf93eb48778b771364ecb2',NULL,NULL,NULL,NULL,NULL,1,0,0,0,'2016-10-29 16:48:00','2016-10-30 09:30:38','N','system','2015-11-19 08:41:10',NULL,NULL,NULL,NULL),(10,'13916935477','田宇','tianyu@shs1988.com','72da43b6f18c8c03b59f387e6bece8b3',NULL,NULL,NULL,NULL,NULL,1,0,0,0,'2016-09-28 22:32:48','2016-09-29 12:43:57','N','system','2015-11-19 08:43:01',NULL,NULL,NULL,NULL),(11,'13901699485','计秋森','jiqiusun@shs1988.com','7f16109f1619fd7a733daf5a84c708c1',NULL,NULL,NULL,NULL,NULL,1,0,0,0,'2016-09-06 09:29:07','2016-09-06 12:14:00','N','system','2015-11-19 08:44:14',NULL,NULL,NULL,NULL),(12,'15900934495','彭康健','pengkangjian@shs1988.com','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,'2016-03-01 11:56:25','2016-03-08 08:29:35','N','system','2015-11-19 08:45:03',NULL,NULL,NULL,NULL),(13,'13621722716','姚媛超','yaoyuanchao@shs1988.com','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,'2016-01-26 12:02:26','2016-01-29 13:23:46','N','system','2015-11-19 08:46:43',NULL,NULL,NULL,NULL),(14,'13621722096','朱燕华','zhuyanhua1@shs1988.com','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,NULL,1,0,0,0,'2016-08-10 17:37:10','2016-10-14 17:57:30','N','system','2015-11-19 08:47:53',NULL,NULL,NULL,NULL),(15,'13661442009','陆委林','luweiling@shs1988.com','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,'2016-03-03 16:29:44','2016-03-03 16:29:54','N','system','2015-11-19 08:48:41',NULL,NULL,NULL,NULL),(16,'13917179806','戴伟东','daiweidong@shs1988.com','fbe82b93c071bedda31afded400cca52',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,'2016-10-14 16:32:03','2016-10-14 16:35:15','N','system','2015-11-19 08:49:22',NULL,NULL,NULL,NULL),(17,'13764958023','张惠英','zhanghuiying@shs1988.com','190a1768c60cd491a156a5a86f1cfbed',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,'2016-10-08 13:52:05','2016-10-08 13:56:12','N','system','2015-11-19 08:50:09',NULL,NULL,NULL,NULL),(18,'13761692428','沈卫峰','shengweifeng@shs1988.com','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,'2016-02-25 16:12:12','2016-03-15 13:01:40','N','system','2015-11-19 08:50:58',NULL,NULL,NULL,NULL),(19,'13636563953','陈彩虹','chencaihong@shs1988.com','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,'2016-03-02 07:35:58','2016-03-15 14:27:23','N','system','2015-11-19 08:52:48',NULL,NULL,NULL,NULL),(20,'13482191385','孙林其','shenglingqi@shs1988.com','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,'2016-03-07 09:19:12','2016-03-08 08:10:07','N','system','2015-11-19 08:53:39',NULL,NULL,NULL,NULL),(21,'13795269466','宋拥军','songyongjun@shs1988.com','4297f44b13955235245b2497399d7a93',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,'2016-10-14 17:31:23','2016-10-15 15:32:12','N','system','2015-11-19 08:54:34',NULL,NULL,NULL,NULL),(22,'15800708343','孙林枫','sunlingfeng@shs1988.com','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,NULL,NULL,'N','system','2015-11-19 08:55:30',NULL,NULL,NULL,NULL),(23,'13701674039','沈瑞华','shengruihua@shs1988.com','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,NULL,NULL,'N','system','2015-11-19 08:55:58',NULL,NULL,NULL,NULL),(24,'13512165255','周志军','zhouzhijun@shs1988.com','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,NULL,NULL,'N','system','2015-11-19 09:11:53',NULL,NULL,NULL,NULL),(25,'13621722176','王璐','wanglu@shs1988.com','ef1317d315e091a034adbc06ca7e9b3d',NULL,NULL,NULL,NULL,NULL,2,0,0,0,'2016-10-29 16:47:57','2016-10-29 16:49:51','N','system','2015-11-19 09:12:28',NULL,NULL,NULL,NULL),(26,'13918029155','顾国兴','13918029155@163.com','e10adc3949ba59abbe56e057f20f883e',NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,'2016-09-03 14:21:51','2016-09-06 10:15:32','N','system','2015-12-09 16:54:26',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `systemuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactionreason`
--

DROP TABLE IF EXISTS `transactionreason`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transactionreason` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reasontype` varchar(10) DEFAULT NULL,
  `content` varchar(45) NOT NULL,
  `status` varchar(2) NOT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `credate` datetime DEFAULT NULL,
  `optuser` varchar(20) DEFAULT NULL,
  `optdate` datetime DEFAULT NULL,
  `cfmuser` varchar(20) DEFAULT NULL,
  `cfmdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactionreason`
--

LOCK TABLES `transactionreason` WRITE;
/*!40000 ALTER TABLE `transactionreason` DISABLE KEYS */;
/*!40000 ALTER TABLE `transactionreason` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactiontype`
--

LOCK TABLES `transactiontype` WRITE;
/*!40000 ALTER TABLE `transactiontype` DISABLE KEYS */;
INSERT INTO `transactiontype` VALUES (1,'PAA','验收入库','PUR',1,1,'','','','',NULL,0,0,'','V','Admin','2016-05-05 20:23:27',NULL,NULL,'Admin','2016-05-05 21:23:24'),(2,'PAB','验收退货','PUR',-1,1,'','','','',NULL,0,0,'','V','Admin','2016-05-05 20:33:45',NULL,NULL,'Admin','2016-05-05 21:25:25'),(3,'SDA','销售出货','SD',-1,1,'','','','',NULL,0,0,'','V','Admin','2016-05-05 20:34:13',NULL,NULL,'Admin','2016-05-05 21:27:01'),(4,'SDB','销售退货','SD',1,1,'','','','',NULL,0,0,'','V','Admin','2016-05-05 20:42:58','Admin','2016-05-18 07:38:54','Admin','2016-05-18 07:41:29'),(5,'IAA','异动入库','MM',1,1,'','','','',NULL,0,0,'','N','系统管理员','2016-06-29 22:46:02',NULL,NULL,NULL,NULL),(6,'IAB','异动出库','MM',-1,1,'','','','',NULL,0,0,'','N','系统管理员','2016-06-29 22:47:37',NULL,NULL,NULL,NULL),(7,'IKA','客供入库','MM',1,0,'Customer','customerSelect','SalesOrderDetailForQuery','salesordermaterialSelect',NULL,0,0,'','N','系统管理员','2016-06-29 22:48:03',NULL,NULL,NULL,NULL),(8,'IKB','客供出库','MM',-1,0,'Customer','customerSelect','','',NULL,0,0,'','N','系统管理员','2016-06-29 22:48:30',NULL,NULL,NULL,NULL),(9,'MPA','内部领料','PP',-1,1,'','','','',NULL,0,0,'','N','系统管理员','2016-07-13 22:28:05',NULL,NULL,NULL,NULL),(10,'MPB','内部退料','MM',1,1,'','','','',NULL,0,0,'','N','系统管理员','2016-07-13 22:33:23',NULL,NULL,NULL,NULL),(11,'MKA','客供领料','PP',-1,0,'','','','',NULL,0,0,'','N','系统管理员','2016-07-13 22:31:51',NULL,NULL,NULL,NULL),(12,'MKB','客供退料','MM',1,0,'','','','',NULL,0,0,'','N','系统管理员','2016-07-13 22:35:45',NULL,NULL,NULL,NULL),(13,'MFA','良品入库','PF',1,1,'','','','',NULL,0,0,'','N','系统管理员','2016-07-23 12:11:21',NULL,NULL,NULL,NULL),(14,'MFB','不良入库','PF',1,1,'','','','',NULL,0,0,'','N','系统管理员','2016-07-23 19:26:59',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `transactiontype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unit`
--

DROP TABLE IF EXISTS `unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `unit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `unit` varchar(20) NOT NULL,
  `enunit` varchar(20) DEFAULT NULL,
  `status` varchar(2) NOT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `credate` datetime DEFAULT NULL,
  `optuser` varchar(20) DEFAULT NULL,
  `optdate` datetime DEFAULT NULL,
  `cfmuser` varchar(20) DEFAULT NULL,
  `cfmdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unit`
--

LOCK TABLES `unit` WRITE;
/*!40000 ALTER TABLE `unit` DISABLE KEYS */;
INSERT INTO `unit` VALUES (1,'米','m','N','Admin','2016-05-20 07:42:30',NULL,NULL,NULL,NULL),(2,'条','Pcs','N','Admin','2016-06-13 22:03:24',NULL,NULL,NULL,NULL),(3,'张','unit','N','Admin','2016-06-13 22:04:02','朱怡雯','2016-10-15 14:22:56',NULL,NULL),(5,'码','yds','N','13901699485','2016-06-18 13:08:33',NULL,NULL,NULL,NULL),(6,'公斤','kg','V','13621722756','2016-06-18 13:10:23',NULL,NULL,'系统管理员','2016-09-06 01:25:07'),(7,'厘米','cm','N','13621722196','2016-06-18 13:02:26',NULL,NULL,NULL,NULL),(8,'姆米','mm','N','13916935477','2016-06-18 13:10:29',NULL,NULL,NULL,NULL),(9,'套','set','N','系统管理员','2016-07-31 09:24:03',NULL,NULL,NULL,NULL),(10,'个','unit','N','朱怡雯','2016-10-15 14:21:32',NULL,NULL,NULL,NULL),(11,'匹','roll','N','朱怡雯','2016-10-15 14:21:58',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendor`
--

DROP TABLE IF EXISTS `vendor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vendor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vendorno` varchar(20) NOT NULL,
  `vendor` varchar(20) NOT NULL,
  `fullname` varchar(100) NOT NULL,
  `simplecode` varchar(10) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `fax` varchar(20) DEFAULT NULL,
  `tel2` varchar(20) DEFAULT NULL,
  `fax2` varchar(20) DEFAULT NULL,
  `boss` varchar(20) DEFAULT NULL,
  `weburl` varchar(45) DEFAULT NULL,
  `src` varchar(20) DEFAULT NULL,
  `grade` varchar(20) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `industry` varchar(200) DEFAULT NULL,
  `contacter` varchar(20) DEFAULT NULL,
  `contactadd` varchar(200) DEFAULT NULL,
  `zipcode` varchar(10) DEFAULT NULL,
  `country` varchar(10) DEFAULT NULL,
  `area` varchar(10) DEFAULT NULL,
  `city` varchar(10) DEFAULT NULL,
  `buyerid` int(11) DEFAULT NULL,
  `deptid` int(11) DEFAULT NULL,
  `abroad` tinyint(1) NOT NULL,
  `currency` varchar(10) NOT NULL,
  `taxtype` varchar(1) NOT NULL,
  `taxkind` varchar(10) NOT NULL,
  `taxrate` decimal(16,4) NOT NULL,
  `tradetype` varchar(10) NOT NULL,
  `tradename` varchar(45) DEFAULT NULL,
  `paymentid` int(11) DEFAULT NULL,
  `payment` varchar(45) DEFAULT NULL,
  `purkind` varchar(10) DEFAULT NULL,
  `regaddress` varchar(200) DEFAULT NULL,
  `regcapital` decimal(16,4) DEFAULT NULL,
  `taxid` varchar(30) DEFAULT NULL,
  `bankid` varchar(60) DEFAULT NULL,
  `bankaccount` varchar(60) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `status` varchar(2) NOT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `credate` date DEFAULT NULL,
  `optuser` varchar(20) DEFAULT NULL,
  `optdate` date DEFAULT NULL,
  `cfmuser` varchar(20) DEFAULT NULL,
  `cfmdate` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `vendor_UK1` (`vendorno`),
  UNIQUE KEY `vendor_UK2` (`vendor`),
  UNIQUE KEY `vendor_UK3` (`fullname`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendor`
--

LOCK TABLES `vendor` WRITE;
/*!40000 ALTER TABLE `vendor` DISABLE KEYS */;
INSERT INTO `vendor` VALUES (2,'G0001','浙江华越丝绸','浙江华越丝绸制品有限公司',NULL,'05738526770','057385526770','13706837358','','','',NULL,NULL,NULL,NULL,'吴春光',NULL,'','','','',NULL,NULL,0,'CNY','0','VAT17',17.0000,'C&F',NULL,NULL,'',NULL,'浙江省海盐西塘桥西场路18号',NULL,'',NULL,NULL,'','N','王璐','2016-09-06',NULL,NULL,NULL,NULL),(3,'G0002','南通奔时代','江苏南通友盟纺织有限公司',NULL,'13815219898','0513-85283319','13815219898','','','',NULL,NULL,NULL,NULL,'赵勇',NULL,'','','','江苏',NULL,NULL,0,'CNY','0','VAT17',17.0000,'C&F',NULL,NULL,'',NULL,'江苏省南通市人民东路金路大厦A座302室',NULL,'',NULL,NULL,'','N','王璐','2016-09-06',NULL,NULL,NULL,NULL),(4,'G0003','苏州华丽','苏州工业园区唯亭华丽丝绒厂',NULL,'13801545154','','13801545154','0512-65079343','','',NULL,NULL,NULL,NULL,'孙大男',NULL,'','','','',NULL,NULL,0,'CNY','0','VAT17',17.0000,'C&F',NULL,NULL,'',NULL,'苏州工业园区唯亭镇浦田民营区10-2幢',NULL,'',NULL,NULL,'','N','王璐','2016-09-06',NULL,NULL,NULL,NULL),(5,'G0004','博翔','桐乡博翔纺织有限公司',NULL,'','','13819309338','0573-88671178','','',NULL,NULL,NULL,NULL,'沈红明',NULL,'','','','',NULL,NULL,0,'CNY','0','VAT17',17.0000,'C&F',NULL,NULL,'',NULL,'浙江省桐乡市河山镇平安路46号',NULL,'',NULL,NULL,'','N','王璐','2016-09-06',NULL,NULL,NULL,NULL),(6,'G0005','爱特丝','苏州爱特丝纺织有限公司',NULL,'','','13052866212','0512-67686687','','',NULL,NULL,NULL,NULL,'崔金国',NULL,'','','','',NULL,NULL,0,'CNY','0','VAT17',17.0000,'C&F',NULL,NULL,'',NULL,'苏州郭巷尹新路98号7栋3楼',NULL,'',NULL,NULL,'','N','王璐','2016-09-06',NULL,NULL,NULL,NULL),(7,'G0006','恒源','南京恒源商贸有限公司',NULL,'','','13594017327','','','',NULL,NULL,NULL,NULL,'陈蓓蓓',NULL,'','','','',NULL,NULL,0,'CNY','0','VAT17',17.0000,'C&F',NULL,NULL,'',NULL,'吴江市七都镇庙港开弓村',NULL,'',NULL,NULL,'','N','王璐','2016-09-06',NULL,NULL,NULL,NULL),(8,'G0007','中兴丝绸','苏州中兴丝绸纺织有限公司',NULL,'','0512-66164277','13776008908','','','',NULL,NULL,NULL,NULL,'李中',NULL,'','','','',NULL,NULL,0,'CNY','0','VAT17',17.0000,'C&F',NULL,NULL,'',NULL,'苏州市高新区长江路北文昌路3号',NULL,'',NULL,NULL,'','N','王璐','2016-09-06',NULL,NULL,NULL,NULL),(9,'G0008','湖州锦帛缘纺织','湖州锦帛缘纺织有限公司',NULL,'','','18767290244','0572-3353500','','',NULL,NULL,NULL,NULL,'黄树云',NULL,'','','','',NULL,NULL,0,'CNY','0','VAT17',17.0000,'C&F',NULL,NULL,'',NULL,'湖州市菱湖镇思溪村西横街27号',NULL,'',NULL,NULL,'','N','王璐','2016-09-06',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `vendor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendoritem`
--

DROP TABLE IF EXISTS `vendoritem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vendoritem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `vendorno` varchar(20) NOT NULL COMMENT '供应商编号',
  `seq` int(11) NOT NULL,
  `itemid` int(11) NOT NULL,
  `itemno` varchar(45) NOT NULL,
  `vendoritemno` varchar(45) NOT NULL,
  `vendoritemdesc` varchar(45) DEFAULT NULL,
  `vendoritemspec` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendoritem`
--

LOCK TABLES `vendoritem` WRITE;
/*!40000 ALTER TABLE `vendoritem` DISABLE KEYS */;
INSERT INTO `vendoritem` VALUES (1,3,'G0002',1,65,'W160015','SSS ###111','',NULL),(2,2,'G0001',1,69,'W160016','H176','test',NULL),(3,69,'G0003',2,69,'W160016','897987','sdhidhiow',NULL),(4,8,'G0007',1,71,'T160001','ddwew','',NULL),(5,2,'G0001',1,76,'W160020','W2325','',NULL),(6,6,'G0005',1,77,'T160003','GGG','',NULL);
/*!40000 ALTER TABLE `vendoritem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warehouse`
--

DROP TABLE IF EXISTS `warehouse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `warehouse` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `warehouseno` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `hascost` tinyint(1) NOT NULL,
  `sysid` int(11) DEFAULT NULL,
  `areaid` int(11) DEFAULT NULL,
  `managerid` int(11) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `status` varchar(2) NOT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `credate` datetime DEFAULT NULL,
  `optuser` varchar(20) DEFAULT NULL,
  `optdate` datetime DEFAULT NULL,
  `cfmuser` varchar(20) DEFAULT NULL,
  `cfmdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `warehouse_UK1` (`warehouseno`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warehouse`
--

LOCK TABLES `warehouse` WRITE;
/*!40000 ALTER TABLE `warehouse` DISABLE KEYS */;
INSERT INTO `warehouse` VALUES (1,'W300','白坯仓',1,NULL,NULL,NULL,'',NULL,'V','Admin','2016-06-15 21:31:59','Admin','2016-06-15 21:33:21','系统管理员','2016-07-04 21:35:51'),(2,'W100','成品仓',1,NULL,NULL,NULL,'',NULL,'V','Admin','2016-06-15 21:32:37',NULL,NULL,'Admin','2016-06-15 21:33:15'),(3,'W999','客供仓',0,NULL,NULL,NULL,'',NULL,'V','系统管理员','2016-07-04 21:34:02',NULL,NULL,'系统管理员','2016-07-04 21:35:47'),(4,'W400','不良仓',1,NULL,NULL,NULL,'',NULL,'N','系统管理员','2016-07-26 13:02:28',NULL,NULL,NULL,NULL),(5,'F4','4楼',1,NULL,NULL,NULL,'',NULL,'N','系统管理员','2016-10-29 14:01:16',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `warehouse` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-30 22:57:38
