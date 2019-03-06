-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: 47.105.107.242    Database: logistics
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `SPRING_SESSION`
--

DROP TABLE IF EXISTS `SPRING_SESSION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `SPRING_SESSION` (
  `PRIMARY_ID` char(36) NOT NULL,
  `SESSION_ID` char(36) NOT NULL,
  `CREATION_TIME` bigint(20) NOT NULL,
  `LAST_ACCESS_TIME` bigint(20) NOT NULL,
  `MAX_INACTIVE_INTERVAL` int(11) NOT NULL,
  `EXPIRY_TIME` bigint(20) NOT NULL,
  `PRINCIPAL_NAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`PRIMARY_ID`),
  UNIQUE KEY `SPRING_SESSION_IX1` (`SESSION_ID`),
  KEY `SPRING_SESSION_IX2` (`EXPIRY_TIME`),
  KEY `SPRING_SESSION_IX3` (`PRINCIPAL_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SPRING_SESSION`
--

LOCK TABLES `SPRING_SESSION` WRITE;
/*!40000 ALTER TABLE `SPRING_SESSION` DISABLE KEYS */;
INSERT INTO `SPRING_SESSION` VALUES ('25bb9b60-acb4-4601-be72-6dfa75451d58','4a016e0a-ed05-4a3d-8585-66fa9354350c',1551891967041,1551891967041,3000,1551894967041,NULL),('4f89b201-2d86-45a9-9afb-84ebdcb47ef2','1edc941d-8e33-497d-94d0-86f84e7032f9',1551891723108,1551891723108,3000,1551894723108,NULL),('6a4bf7fd-9721-4252-9d00-8082a8f0bf9c','5ea55bf7-1830-4a1b-88c0-443c196de365',1551892731509,1551892731509,3000,1551895731509,NULL),('7514813e-50da-4ba4-8b81-03ab9baac149','ca9b4342-6e60-4e90-bf79-88adb77fc7c3',1551890492921,1551890492921,3000,1551893492921,NULL),('dd561936-8b9f-436e-9009-93f1a5174daf','f892ae9e-6519-4c1d-a15d-ad5c2d0427e9',1551892098745,1551892098745,3000,1551895098745,NULL),('f6a13adc-d953-4692-8f8e-14af516fbedf','1449a455-6c6f-41db-80cf-d07a4c19d57e',1551891953459,1551891953459,3000,1551894953459,NULL);
/*!40000 ALTER TABLE `SPRING_SESSION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SPRING_SESSION_ATTRIBUTES`
--

DROP TABLE IF EXISTS `SPRING_SESSION_ATTRIBUTES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `SPRING_SESSION_ATTRIBUTES` (
  `SESSION_PRIMARY_ID` char(36) NOT NULL,
  `ATTRIBUTE_NAME` varchar(200) NOT NULL,
  `ATTRIBUTE_BYTES` blob NOT NULL,
  PRIMARY KEY (`SESSION_PRIMARY_ID`,`ATTRIBUTE_NAME`),
  CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_PRIMARY_ID`) REFERENCES `SPRING_SESSION` (`primary_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SPRING_SESSION_ATTRIBUTES`
--

LOCK TABLES `SPRING_SESSION_ATTRIBUTES` WRITE;
/*!40000 ALTER TABLE `SPRING_SESSION_ATTRIBUTES` DISABLE KEYS */;
INSERT INTO `SPRING_SESSION_ATTRIBUTES` VALUES ('25bb9b60-acb4-4601-be72-6dfa75451d58','SPRING_SECURITY_SAVED_REQUEST',_binary '¬\í\0sr\0Aorg.springframework.security.web.savedrequest.DefaultSavedRequest@HDù6d\0I\0\nserverPortL\0contextPatht\0Ljava/lang/String;L\0cookiest\0Ljava/util/ArrayList;L\0headerst\0Ljava/util/Map;L\0localesq\0~\0L\0methodq\0~\0L\0\nparametersq\0~\0L\0pathInfoq\0~\0L\0queryStringq\0~\0L\0\nrequestURIq\0~\0L\0\nrequestURLq\0~\0L\0schemeq\0~\0L\0\nserverNameq\0~\0L\0servletPathq\0~\0xp\0\0\0Pt\0\0sr\0java.util.ArrayListx\Ò\Ça\0I\0sizexp\0\0\0\0w\0\0\0\0xsr\0java.util.TreeMapÁö>-%j\æ\0L\0\ncomparatort\0Ljava/util/Comparator;xpsr\0*java.lang.String$CaseInsensitiveComparatorw\\}\\P\å\Î\0\0xpw\0\0\0t\0acceptsq\0~\0\0\0\0w\0\0\0t\04text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2xt\0\nconnectionsq\0~\0\0\0\0w\0\0\0t\0\nkeep-alivext\0hostsq\0~\0\0\0\0w\0\0\0t\047.105.107.242xt\0\nuser-agentsq\0~\0\0\0\0w\0\0\0t\09Mozilla/5.0 (Linux; Android 4.1.1; Nexus 7 Build/JRO03D))xxsq\0~\0\0\0\0w\0\0\0sr\0java.util.Locale~ø`0ù\ì\0I\0hashcodeL\0countryq\0~\0L\0\nextensionsq\0~\0L\0languageq\0~\0L\0scriptq\0~\0L\0variantq\0~\0xpÿÿÿÿt\0USq\0~\0t\0enq\0~\0q\0~\0xxt\0GETsq\0~\0pw\0\0\0\0xppt\0/t\0http://47.105.107.242/t\0httpt\047.105.107.242t\0/'),('4f89b201-2d86-45a9-9afb-84ebdcb47ef2','SPRING_SECURITY_SAVED_REQUEST',_binary '¬\í\0sr\0Aorg.springframework.security.web.savedrequest.DefaultSavedRequest@HDù6d\0I\0\nserverPortL\0contextPatht\0Ljava/lang/String;L\0cookiest\0Ljava/util/ArrayList;L\0headerst\0Ljava/util/Map;L\0localesq\0~\0L\0methodq\0~\0L\0\nparametersq\0~\0L\0pathInfoq\0~\0L\0queryStringq\0~\0L\0\nrequestURIq\0~\0L\0\nrequestURLq\0~\0L\0schemeq\0~\0L\0\nserverNameq\0~\0L\0servletPathq\0~\0xp\0\0\0Pt\0\0sr\0java.util.ArrayListx\Ò\Ça\0I\0sizexp\0\0\0\0w\0\0\0\0xsr\0java.util.TreeMapÁö>-%j\æ\0L\0\ncomparatort\0Ljava/util/Comparator;xpsr\0*java.lang.String$CaseInsensitiveComparatorw\\}\\P\å\Î\0\0xpw\0\0\0t\0content-lengthsq\0~\0\0\0\0w\0\0\0t\00xt\0hostsq\0~\0\0\0\0w\0\0\0t\047.105.107.242:80xt\0\nuser-agentsq\0~\0\0\0\0w\0\0\0t\0tMozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/601.7.7 (KHTML, like Gecko) Version/9.1.2 Safari/601.7.7xxsq\0~\0\0\0\0w\0\0\0sr\0java.util.Locale~ø`0ù\ì\0I\0hashcodeL\0countryq\0~\0L\0\nextensionsq\0~\0L\0languageq\0~\0L\0scriptq\0~\0L\0variantq\0~\0xpÿÿÿÿt\0USq\0~\0t\0enq\0~\0q\0~\0xxt\0GETsq\0~\0pw\0\0\0\0xppt\0/t\0http://47.105.107.242/t\0httpt\047.105.107.242t\0/'),('6a4bf7fd-9721-4252-9d00-8082a8f0bf9c','SPRING_SECURITY_SAVED_REQUEST',_binary '¬\í\0sr\0Aorg.springframework.security.web.savedrequest.DefaultSavedRequest@HDù6d\0I\0\nserverPortL\0contextPatht\0Ljava/lang/String;L\0cookiest\0Ljava/util/ArrayList;L\0headerst\0Ljava/util/Map;L\0localesq\0~\0L\0methodq\0~\0L\0\nparametersq\0~\0L\0pathInfoq\0~\0L\0queryStringq\0~\0L\0\nrequestURIq\0~\0L\0\nrequestURLq\0~\0L\0schemeq\0~\0L\0\nserverNameq\0~\0L\0servletPathq\0~\0xp\0\0\0Pt\0\0sr\0java.util.ArrayListx\Ò\Ça\0I\0sizexp\0\0\0\0w\0\0\0\0xsr\0java.util.TreeMapÁö>-%j\æ\0L\0\ncomparatort\0Ljava/util/Comparator;xpsr\0*java.lang.String$CaseInsensitiveComparatorw\\}\\P\å\Î\0\0xpw\0\0\0\0xsq\0~\0\0\0\0w\0\0\0sr\0java.util.Locale~ø`0ù\ì\0I\0hashcodeL\0countryq\0~\0L\0\nextensionsq\0~\0L\0languageq\0~\0L\0scriptq\0~\0L\0variantq\0~\0xpÿÿÿÿt\0USq\0~\0t\0enq\0~\0q\0~\0xxt\0GETsq\0~\0pw\0\0\0\0xppt\0/t\0http://localhost/t\0httpt\0	localhostt\0/'),('7514813e-50da-4ba4-8b81-03ab9baac149','SPRING_SECURITY_SAVED_REQUEST',_binary '¬\í\0sr\0Aorg.springframework.security.web.savedrequest.DefaultSavedRequest@HDù6d\0I\0\nserverPortL\0contextPatht\0Ljava/lang/String;L\0cookiest\0Ljava/util/ArrayList;L\0headerst\0Ljava/util/Map;L\0localesq\0~\0L\0methodq\0~\0L\0\nparametersq\0~\0L\0pathInfoq\0~\0L\0queryStringq\0~\0L\0\nrequestURIq\0~\0L\0\nrequestURLq\0~\0L\0schemeq\0~\0L\0\nserverNameq\0~\0L\0servletPathq\0~\0xp\0\0\0Pt\0\0sr\0java.util.ArrayListx\Ò\Ça\0I\0sizexp\0\0\0\0w\0\0\0\0xsr\0java.util.TreeMapÁö>-%j\æ\0L\0\ncomparatort\0Ljava/util/Comparator;xpsr\0*java.lang.String$CaseInsensitiveComparatorw\\}\\P\å\Î\0\0xpw\0\0\0t\0content-lengthsq\0~\0\0\0\0w\0\0\0t\00xt\0hostsq\0~\0\0\0\0w\0\0\0t\047.105.107.242:80xt\0\nuser-agentsq\0~\0\0\0\0w\0\0\0t\0tMozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/601.7.7 (KHTML, like Gecko) Version/9.1.2 Safari/601.7.7xxsq\0~\0\0\0\0w\0\0\0sr\0java.util.Locale~ø`0ù\ì\0I\0hashcodeL\0countryq\0~\0L\0\nextensionsq\0~\0L\0languageq\0~\0L\0scriptq\0~\0L\0variantq\0~\0xpÿÿÿÿt\0USq\0~\0t\0enq\0~\0q\0~\0xxt\0GETsq\0~\0pw\0\0\0\0xppt\0/t\0http://47.105.107.242/t\0httpt\047.105.107.242t\0/'),('dd561936-8b9f-436e-9009-93f1a5174daf','SPRING_SECURITY_SAVED_REQUEST',_binary '¬\í\0sr\0Aorg.springframework.security.web.savedrequest.DefaultSavedRequest@HDù6d\0I\0\nserverPortL\0contextPatht\0Ljava/lang/String;L\0cookiest\0Ljava/util/ArrayList;L\0headerst\0Ljava/util/Map;L\0localesq\0~\0L\0methodq\0~\0L\0\nparametersq\0~\0L\0pathInfoq\0~\0L\0queryStringq\0~\0L\0\nrequestURIq\0~\0L\0\nrequestURLq\0~\0L\0schemeq\0~\0L\0\nserverNameq\0~\0L\0servletPathq\0~\0xp\0\0\0Pt\0\0sr\0java.util.ArrayListx\Ò\Ça\0I\0sizexp\0\0\0\0w\0\0\0\0xsr\0java.util.TreeMapÁö>-%j\æ\0L\0\ncomparatort\0Ljava/util/Comparator;xpsr\0*java.lang.String$CaseInsensitiveComparatorw\\}\\P\å\Î\0\0xpw\0\0\0t\0acceptsq\0~\0\0\0\0w\0\0\0t\04text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2xt\0\nconnectionsq\0~\0\0\0\0w\0\0\0t\0\nkeep-alivext\0hostsq\0~\0\0\0\0w\0\0\0t\047.105.107.242xt\0\nuser-agentsq\0~\0\0\0\0w\0\0\0t\09Mozilla/5.0 (Linux; Android 4.1.1; Nexus 7 Build/JRO03D))xxsq\0~\0\0\0\0w\0\0\0sr\0java.util.Locale~ø`0ù\ì\0I\0hashcodeL\0countryq\0~\0L\0\nextensionsq\0~\0L\0languageq\0~\0L\0scriptq\0~\0L\0variantq\0~\0xpÿÿÿÿt\0USq\0~\0t\0enq\0~\0q\0~\0xxt\0GETsq\0~\0pw\0\0\0\0xppt\0/t\0http://47.105.107.242/t\0httpt\047.105.107.242t\0/'),('f6a13adc-d953-4692-8f8e-14af516fbedf','SPRING_SECURITY_SAVED_REQUEST',_binary '¬\í\0sr\0Aorg.springframework.security.web.savedrequest.DefaultSavedRequest@HDù6d\0I\0\nserverPortL\0contextPatht\0Ljava/lang/String;L\0cookiest\0Ljava/util/ArrayList;L\0headerst\0Ljava/util/Map;L\0localesq\0~\0L\0methodq\0~\0L\0\nparametersq\0~\0L\0pathInfoq\0~\0L\0queryStringq\0~\0L\0\nrequestURIq\0~\0L\0\nrequestURLq\0~\0L\0schemeq\0~\0L\0\nserverNameq\0~\0L\0servletPathq\0~\0xp\0\0\0Pt\0\0sr\0java.util.ArrayListx\Ò\Ça\0I\0sizexp\0\0\0\0w\0\0\0\0xsr\0java.util.TreeMapÁö>-%j\æ\0L\0\ncomparatort\0Ljava/util/Comparator;xpsr\0*java.lang.String$CaseInsensitiveComparatorw\\}\\P\å\Î\0\0xpw\0\0\0t\0acceptsq\0~\0\0\0\0w\0\0\0t\04text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2xt\0\nconnectionsq\0~\0\0\0\0w\0\0\0t\0\nkeep-alivext\0hostsq\0~\0\0\0\0w\0\0\0t\0\rwww.jpdyu.comxt\0\nuser-agentsq\0~\0\0\0\0w\0\0\0t\09Mozilla/5.0 (Linux; Android 4.1.1; Nexus 7 Build/JRO03D))xxsq\0~\0\0\0\0w\0\0\0sr\0java.util.Locale~ø`0ù\ì\0I\0hashcodeL\0countryq\0~\0L\0\nextensionsq\0~\0L\0languageq\0~\0L\0scriptq\0~\0L\0variantq\0~\0xpÿÿÿÿt\0USq\0~\0t\0enq\0~\0q\0~\0xxt\0GETsq\0~\0pw\0\0\0\0xppt\0/t\0http://www.jpdyu.com/t\0httpt\0\rwww.jpdyu.comt\0/');
/*!40000 ALTER TABLE `SPRING_SESSION_ATTRIBUTES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_amazon_order`
--

DROP TABLE IF EXISTS `l_amazon_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `l_amazon_order` (
  `amazonOrderId` varchar(45) COLLATE utf8_bin NOT NULL,
  `userId` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `sellerOrderId` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `purchaseDate` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `lastUpdateDate` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `orderStatus` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `fulfillmentChannel` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `salesChannel` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `ShipServiceLevel` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `addressLine1` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `stateOrRegion` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `postalCode` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `countryCode` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `currencyCode` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `amount` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `numberOfItemsUnshipped` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `numberOfItemsShipped` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `paymentMethod` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `paymentMethodDetail` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `marketplaceId` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `buyerName` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `shipmentServiceLevelCategory` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `orderType` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `eraliestShipDate` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `latestShipDate` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `isBusinessOrder` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `isPrime` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `isPremiumOrder` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `isReplacementOrder` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`amazonOrderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_amazon_order`
--

LOCK TABLES `l_amazon_order` WRITE;
/*!40000 ALTER TABLE `l_amazon_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `l_amazon_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_carrier_order`
--

DROP TABLE IF EXISTS `l_carrier_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `l_carrier_order` (
  `id` int(11) NOT NULL,
  `carrier_no` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `order_no_start` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `order_no_end` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_carrier_order`
--

LOCK TABLES `l_carrier_order` WRITE;
/*!40000 ALTER TABLE `l_carrier_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `l_carrier_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_category`
--

DROP TABLE IF EXISTS `l_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `l_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `seq` int(11) DEFAULT NULL,
  `type` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `l_catagory_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_category`
--

LOCK TABLES `l_category` WRITE;
/*!40000 ALTER TABLE `l_category` DISABLE KEYS */;
INSERT INTO `l_category` VALUES (1,1,'order','æµ·å¤ä»ä»£åè®¢å'),(2,2,'order','ç¹è²å°å'),(7,1,'carrier','æ¥æ¬é®æ¿'),(8,2,'carrier','ä½å·æ¥ä¾¿'),(9,3,'carrier','é»ç«è¿è¾'),(10,4,'carrier','è¥¿æµè¿è¾'),(11,5,'carrier','å¡è½¦æ´¾é'),(12,3,'order','å·²æ¦æªè®¢å'),(13,4,'order','å¶ä»'),(14,6,'carrier','å¶ä»'),(15,1,'deliver','æµ·è¿æ´æ20GP/HQ'),(16,2,'deliver','æµ·è¿æ´æ40GP/HQ'),(17,3,'deliver','æµ·è¿æ£è´§'),(18,4,'deliver','ç©ºè¿'),(19,5,'deliver','å¡è½¦æ´¾é'),(20,6,'deliver','å¶ä»'),(21,1,'classification','å°ç©'),(22,2,'classification','æè£'),(23,3,'classification','æ·å¤è¿å¨'),(24,4,'classification','çµå­äº§å'),(25,5,'classification','å®¶å±ç¨å'),(26,6,'classification','ç©å·'),(27,7,'classification','æç¢å'),(28,8,'classification','å¤§ä»¶'),(30,9,'classification','å¶ä»'),(31,1,'method','ä¸å²³å¤´ç¨'),(32,2,'method','å¶ä»å¤´ç¨');
/*!40000 ALTER TABLE `l_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_channel`
--

DROP TABLE IF EXISTS `l_channel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `l_channel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `channel_code` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `channel_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `cooperator_code` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `currency_code` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `min_days` int(11) DEFAULT NULL,
  `max_days` int(11) DEFAULT NULL,
  `weight_rule_code` int(11) DEFAULT NULL,
  `weight_multi` int(11) DEFAULT NULL,
  `default_package_weight` double DEFAULT NULL,
  `default_package_fee` double DEFAULT NULL,
  `fee_code_rule` int(11) DEFAULT NULL,
  `whether_multi_package` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `whether_volume` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `whether_charge_at_first` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `whether_charge_for_them` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `whether_insurance` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `enable` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `remarks` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `update_on` timestamp NULL DEFAULT NULL,
  `create_by` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `update_by` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `create_on` timestamp NULL DEFAULT NULL,
  `channel_adapter` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_channel`
--

LOCK TABLES `l_channel` WRITE;
/*!40000 ALTER TABLE `l_channel` DISABLE KEYS */;
INSERT INTO `l_channel` VALUES (10,'CHL0009','èææµ·å¤ä»è½¬å-æ¥é®æ´¾é','1','JPY',1,3,3,0,0,0,3,'N','N','Y','N','N','Y','','2019-02-21 17:17:00','admin','admin','2019-02-21 17:10:11','èææµ·å¤ä»è½¬å'),(11,'CHL0011','èææµ·å¤ä»è½¬å-ç©ºå æ´¾','1','CNY',3,7,3,0,0,0,3,'N','N','Y','N','N','Y','','2019-02-21 17:16:48','admin','admin','2019-02-21 17:11:35','èææµ·å¤ä»è½¬å');
/*!40000 ALTER TABLE `l_channel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_claim`
--

DROP TABLE IF EXISTS `l_claim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `l_claim` (
  `claim_id` varchar(50) COLLATE utf8_bin NOT NULL,
  `order_no` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `track_no` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `remarks` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `img_path` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `create_on` timestamp NULL DEFAULT NULL,
  `update_on` timestamp NULL DEFAULT NULL,
  `create_by` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `update_by` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`claim_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_claim`
--

LOCK TABLES `l_claim` WRITE;
/*!40000 ALTER TABLE `l_claim` DISABLE KEYS */;
INSERT INTO `l_claim` VALUES ('DY4ae01f84',NULL,NULL,NULL,'',NULL,NULL,NULL,NULL),('DY5789d505','DYb41755c0','xxxxx','xxxxxxxx','','2019-02-19 14:59:05',NULL,'user01',NULL),('DY661eb085',NULL,NULL,NULL,'',NULL,NULL,NULL,NULL),('DY947912ce',NULL,NULL,NULL,'',NULL,NULL,NULL,NULL),('DYa4914f98',NULL,NULL,NULL,'C:/tmp/claim/DYa4914f98/DYa6fd5011_QQæµè§å¨æªå¾_20180109235724_5640D09F303C4184B6D3FF2069398BEE.jpg;C:/tmp/claim/DYa4914f98/DY792f2959_QQæµè§å¨æªå¾_20180206214851_D57E3F203B584a9c8B8F0D80E031379A.jpg;C:/tmp/claim/DYa4914f98/DYd8db7538_QQæµè§å¨æªå¾_20180206215234_BDCB19050F184154834BFF8C2D0BFA5C.jpg',NULL,NULL,NULL,NULL),('DYb37e077d','DYb41755c0','xxx','xxx','',NULL,'2019-02-19 15:49:49','user01','user01'),('DYbdf89284','DYb41755c0','xxxxxx','xxxxxxxx','',NULL,'2019-02-21 15:04:42','user01','user01'),('DYd0e94b8a','xx','xx','xx','','2018-12-15 03:36:01',NULL,'user01',NULL),('DYde599d70',NULL,NULL,NULL,'C:/tmp/claim/DYde599d70/DYb6ffbb1f_QQæµè§å¨æªå¾_20180308223736_54E2B1592E1B4d43936BB660859994D0.jpg',NULL,NULL,NULL,NULL),('DYf7251a75','xxx','xx','xx','','2018-12-15 15:06:02',NULL,'user01',NULL),('undefined',NULL,NULL,NULL,'',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `l_claim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_claim_content`
--

DROP TABLE IF EXISTS `l_claim_content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `l_claim_content` (
  `claim_id` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `sku` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `deal_with` int(11) DEFAULT NULL,
  `num` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_claim_content`
--

LOCK TABLES `l_claim_content` WRITE;
/*!40000 ALTER TABLE `l_claim_content` DISABLE KEYS */;
INSERT INTO `l_claim_content` VALUES ('DY2843543d','121211','1212',1,12),('DY2843543d','121211','1212',1,12),('DY65d842c7','sdfsdf','aaa',1,5),('DY65d842c7','sdfsdf','aaa',1,5),('DY65d842c7','sdfsdf','aaa',1,5),('DY4ae01f84','sdfsdf','aaa',1,5),('DY4ae01f84','sdfsdf','aaa',1,5),('DY36784423','sdfsdf','aaa',1,5),('DY36784423','sdfsdf','aaa',1,5),('DYbdf89284','sdfsdf','aaa',1,5);
/*!40000 ALTER TABLE `l_claim_content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_claim_storage`
--

DROP TABLE IF EXISTS `l_claim_storage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `l_claim_storage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sku` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `target` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `create_on` timestamp NULL DEFAULT NULL,
  `update_on` timestamp NULL DEFAULT NULL,
  `create_by` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `update_by` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `remarks` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `name_for_customers` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_claim_storage`
--

LOCK TABLES `l_claim_storage` WRITE;
/*!40000 ALTER TABLE `l_claim_storage` DISABLE KEYS */;
INSERT INTO `l_claim_storage` VALUES (1,'121211',18,2,'xxx','2018-12-16 04:22:57','2018-12-22 15:09:32','user01',NULL,6,NULL,NULL,NULL);
/*!40000 ALTER TABLE `l_claim_storage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_deliver_box`
--

DROP TABLE IF EXISTS `l_deliver_box`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `l_deliver_box` (
  `parentsku` varchar(40) COLLATE utf8_bin NOT NULL,
  `sku` varchar(45) COLLATE utf8_bin NOT NULL DEFAULT 'skudeflaut',
  `box_seq` int(11) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `owner` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `deliverStatus` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `method` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `create_on` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_on` datetime DEFAULT CURRENT_TIMESTAMP,
  `insurance` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `head` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `defect_num` double DEFAULT NULL,
  `normal_num` double DEFAULT NULL,
  `loss_num` double DEFAULT NULL,
  `wrap_type` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `track_no` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `tax_type` int(11) DEFAULT NULL,
  `clearance_type` int(11) DEFAULT NULL,
  `estimated_date` datetime DEFAULT NULL,
  `img_path` varchar(2000) COLLATE utf8_bin DEFAULT NULL,
  `user_confirm` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `deliver_way` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `operation_type` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `fee_1` varchar(2) COLLATE utf8_bin DEFAULT NULL,
  `fee_2` varchar(2) COLLATE utf8_bin DEFAULT NULL,
  `fee_3` varchar(2) COLLATE utf8_bin DEFAULT NULL,
  `fee_4` varchar(2) COLLATE utf8_bin DEFAULT NULL,
  `fee_5` varchar(2) COLLATE utf8_bin DEFAULT NULL,
  `fee_6` varchar(2) COLLATE utf8_bin DEFAULT NULL,
  `fee_7` varchar(2) COLLATE utf8_bin DEFAULT NULL,
  `fee_8` varchar(2) COLLATE utf8_bin DEFAULT NULL,
  `fee_a` varchar(2) COLLATE utf8_bin DEFAULT NULL,
  `fee_d` varchar(2) COLLATE utf8_bin DEFAULT NULL,
  `fee_c` varchar(2) COLLATE utf8_bin DEFAULT NULL,
  `fee_b` varchar(2) COLLATE utf8_bin DEFAULT NULL,
  `fee_e` varchar(2) COLLATE utf8_bin DEFAULT NULL,
  `channel` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_deliver_box`
--

LOCK TABLES `l_deliver_box` WRITE;
/*!40000 ALTER TABLE `l_deliver_box` DISABLE KEYS */;
INSERT INTO `l_deliver_box` VALUES ('DYeb03880d','sdfsdf',1,2,'user01','4','2','2018-12-09 14:54:07','2019-02-15 23:35:26','222','1',NULL,NULL,NULL,'éèªå¸¦åè£','',NULL,NULL,NULL,NULL,'Y',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('DY15b5262b','121211',1,1,'user01','3','3','2018-12-09 15:01:34','2019-02-19 10:54:10','222222222','1',NULL,NULL,NULL,'èªå¸¦åè£','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('DY3bc55cf4','121211',1,23,'user01','1','2','2018-12-09 17:01:41','2018-12-29 16:03:20','12','1',1,21,1,'èªå¸¦åè£','',2,2,'2018-12-09 00:00:00','C:/tmp/upload4/DY3bc55cf4/DYf3831b01_QQæµè§å¨æªå¾_20180206215234_BDCB19050F184154834BFF8C2D0BFA5C.jpg;C:/tmp/upload4/DY3bc55cf4/DYbab8647e_QQæµè§å¨æªå¾_20180206215525_B5FD3DD2082C47a6962FDFD9F300762A.jpg;C:/tmp/upload4/DY3bc55cf4/DY905bd5ee_QQæµè§å¨æªå¾_20180308223736_54E2B1592E1B4d43936BB660859994D0.jpg','Y',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('DYf87e115b','121211',1,222,'user01','2','3','2018-12-09 22:39:55','2019-02-22 01:18:30','15','1',NULL,NULL,NULL,'èªå¸¦åè£','',2,2,'2018-12-09 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('DY763170db','sku00001',1,23,'user01','1','2','2018-12-10 23:19:45','2018-12-10 23:19:45','','1',NULL,2,NULL,'èªå¸¦åè£','',2,2,'2018-12-22 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('DYd93322fc','sku00001',1,24,'user01','1','3','2018-12-11 21:48:21','2019-01-31 21:38:40','','2',NULL,NULL,NULL,'èªå¸¦åè£','xxx',2,2,'2018-12-29 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('DY020fe2ff','sku00001',1,23,'user01','1','4','2018-12-11 21:48:44','2018-12-11 21:48:44','','2',NULL,NULL,NULL,'èªå¸¦åè£','xxxx',2,2,'2018-12-29 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('DYbc3bfc55','sku00001',1,11,'user01','1','5','2018-12-11 21:49:10','2018-12-31 22:58:39','','2',NULL,NULL,NULL,'èªå¸¦åè£','xxxx',2,2,'2019-03-01 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('DYcff595bb','sku00001',1,23,'user01','1','5','2018-12-11 21:50:09','2018-12-11 21:50:09','','2',NULL,NULL,NULL,'èªå¸¦åè£','xxx',2,2,'2018-12-22 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('DY424390c2','sku00001',1,24,'user01','1','5','2018-12-11 21:50:24','2018-12-11 21:50:24','','2',NULL,NULL,NULL,'èªå¸¦åè£','xxx',2,2,'2018-12-30 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('DYede5a1d1','sku00001',1,55,'user01','1','6','2018-12-11 21:50:51','2018-12-11 21:50:51','','2',NULL,NULL,NULL,'èªå¸¦åè£','xxxx',2,2,'2018-12-30 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('DYd171f7b7','sku00001',1,22,'user01','1','2','2018-12-11 22:59:16','2019-01-05 16:40:32','','2',NULL,NULL,NULL,'èªå¸¦åè£','xx',-1,-1,'2018-12-11 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('DY938d8b97','121211',1,12,'user01','1','3','2018-12-16 21:12:32','2018-12-31 22:56:08','','1',NULL,NULL,NULL,'èªå¸¦åè£','',2,2,'2018-12-22 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('DY11b8dfd0','121211',1,12,'user01','1','1','2018-12-17 00:11:26','2019-01-09 15:24:26','23.0','2',NULL,NULL,NULL,'èªå¸¦åè£','xxx',0,1,'2019-01-01 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('DY5e0a194e','121211',1,10,'user01','1','6','2019-01-09 15:30:25','2019-01-30 23:01:46','','1',NULL,NULL,NULL,'èªå¸¦åè£','',2,2,'2019-01-10 00:00:00',NULL,NULL,'éè£ç®±å¡è½¦æ´¾é','æ®è´§å¥åº','Y','Y','N','N','N','N','Y','Y','N','N','N','N','N',NULL),('DY5e0a194e','sdfsdf',2,20,'user01','1','6','2019-01-09 15:30:25','2019-01-30 23:01:46','','1',NULL,NULL,NULL,'èªå¸¦åè£','',2,2,'2019-01-10 00:00:00',NULL,NULL,'éè£ç®±å¡è½¦æ´¾é','æ®è´§å¥åº','Y','Y','N','N','N','N','Y','Y','N','N','N','N','N',NULL),('DY5e0a194e','sku00001',3,30,'user01','1','6','2019-01-09 15:30:25','2019-01-30 23:01:46','','1',NULL,NULL,NULL,'èªå¸¦åè£','',2,2,'2019-01-10 00:00:00',NULL,NULL,'éè£ç®±å¡è½¦æ´¾é','æ®è´§å¥åº','Y','Y','N','N','N','N','Y','Y','N','N','N','N','N',NULL),('DY6e2e3a93','sku0002',1,10,'user01','1','4','2019-01-25 15:02:25','2019-01-31 21:39:02','','1',NULL,NULL,NULL,'èªå¸¦åè£','',2,2,'2019-02-03 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('DY6e2e3a93','sku0002',2,20,'user01','1','4','2019-01-25 15:02:25','2019-01-31 21:39:02','','1',NULL,NULL,NULL,'èªå¸¦åè£','',2,2,'2019-02-03 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('DY639bf784','121211',1,10,'user01','1','6','2019-01-31 21:14:16','2019-01-31 21:38:58','','1',NULL,NULL,NULL,'èªå¸¦åè£','',2,2,'2019-02-03 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('DY639bf784','sku0002',2,20,'user01','1','6','2019-01-31 21:14:16','2019-01-31 21:38:58','','1',NULL,NULL,NULL,'èªå¸¦åè£','',2,2,'2019-02-03 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('DY21d5de5c','121211',1,12,'user01','1','2','2019-01-31 22:44:48','2019-01-31 22:44:45','25','1',NULL,NULL,NULL,'èªå¸¦åè£','',2,2,'2019-01-31 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('DYe30c2943','sku00001/8950615433071',1,12,'user01','1','','2019-02-06 17:14:59','2019-02-06 17:14:59','','2',NULL,NULL,NULL,'èªå¸¦åè£','xx',2,2,'2019-02-08 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,-1),('DYf0254818','sku00001/8950615433071',1,12,'user01','1','','2019-02-06 18:50:56','2019-02-06 18:50:57','','2',NULL,NULL,NULL,'èªå¸¦åè£','xxxxxxxxxxxx',2,2,'2019-02-07 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,-1);
/*!40000 ALTER TABLE `l_deliver_box` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_deliver_box_p`
--

DROP TABLE IF EXISTS `l_deliver_box_p`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `l_deliver_box_p` (
  `parentsku` varchar(40) COLLATE utf8_bin NOT NULL,
  `sku` varchar(45) COLLATE utf8_bin NOT NULL,
  `box_seq` int(11) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `owner` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `deliverStatus` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `method` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `create_on` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_on` datetime DEFAULT CURRENT_TIMESTAMP,
  `insurance` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `head` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `defect_num` double DEFAULT NULL,
  `normal_num` double DEFAULT NULL,
  `loss_num` double DEFAULT NULL,
  `wrap_type` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `track_no` varchar(45) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_deliver_box_p`
--

LOCK TABLES `l_deliver_box_p` WRITE;
/*!40000 ALTER TABLE `l_deliver_box_p` DISABLE KEYS */;
/*!40000 ALTER TABLE `l_deliver_box_p` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_express_track_code`
--

DROP TABLE IF EXISTS `l_express_track_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `l_express_track_code` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `express_company_code` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `min_track_code` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `max_track_code` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_express_track_code`
--

LOCK TABLES `l_express_track_code` WRITE;
/*!40000 ALTER TABLE `l_express_track_code` DISABLE KEYS */;
INSERT INTO `l_express_track_code` VALUES (5,'1','23','299999'),(7,'2','9','2000');
/*!40000 ALTER TABLE `l_express_track_code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_group_role`
--

DROP TABLE IF EXISTS `l_group_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `l_group_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `groupid` int(11) DEFAULT NULL,
  `roleid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_group_role`
--

LOCK TABLES `l_group_role` WRITE;
/*!40000 ALTER TABLE `l_group_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `l_group_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_group_user`
--

DROP TABLE IF EXISTS `l_group_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `l_group_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `groupid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_group_user`
--

LOCK TABLES `l_group_user` WRITE;
/*!40000 ALTER TABLE `l_group_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `l_group_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_label`
--

DROP TABLE IF EXISTS `l_label`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `l_label` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `value` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_label`
--

LOCK TABLES `l_label` WRITE;
/*!40000 ALTER TABLE `l_label` DISABLE KEYS */;
INSERT INTO `l_label` VALUES (1,'cooperator_1','æ¥æ¬é®æ¿'),(2,'cooperator_2','ä½å·æ¥ä¾¿'),(3,'cooperator_3','é»ç«è¿è¾'),(4,'cooperator_4','è¥¿æµè¿è¾'),(5,'cooperator_5','å¡è½¦æ´¾é'),(6,'cooperator_6','å¶ä»'),(7,'currency_CNY','äººæ°å¸'),(8,'currency_USD','ç¾é'),(9,'currency_JPY','æ¥å'),(10,'weightRule_1','éé*ç³»æ°'),(11,'weightRule_2','éé+åè£'),(12,'weightRule_3','äººå·¥è®¾ç½®'),(13,'feeRule_1','äººå·¥è¾å¥'),(14,'feeRule_2','åå°è®¡ç®'),(15,'feeRule_3','åå°è®¡ç®+äººå·¥è¾å¥'),(16,'head_1','ä¸å²³å¤´ç¨'),(17,'head_2','å¶ä»å¤´ç¨'),(18,'method_1','æµ·è¿æ´æ20GP/HQ'),(19,'method_2','æµ·è¿æ´æ40GP/HQ'),(20,'method_3','æµ·è¿æ£è´§'),(21,'method_4','ç©ºè¿'),(22,'method_5','å¡è½¦æ´¾é'),(23,'method_6','å¶ä»'),(24,'size_1','å°ä»¶(ä¸è¾¹å60cmä»¥ä¸ï¼æé¿è¾¹34cmä»¥ä¸ï¼ååº¦3cmä»¥ä¸ï¼éé1kgä»¥ä¸)'),(25,'size_2','æ åä»¶(ä¸è¾¹å160cmä»¥ä¸ï¼éé25kgä»¥ä¸)'),(26,'size_3','å¤§ä»¶(ä¸è¾¹å160cmä»¥ä¸ï¼éé50kgä»¥ä¸)'),(27,'size_4','å¶ä»'),(28,'order_1','æµ·å¤ä»ä»£åè®¢å'),(29,'order_2','ç¹è²å°å'),(30,'status_1','å·²çæè®¢å,æªç¡®è®¤ç¸å³ä¿¡æ¯åæäº¤åºåº'),(31,'status_2','å·²ç¡®è®¤ç¸å³ä¿¡æ¯,æªæäº¤åºåº'),(32,'status_3','å·²æäº¤åºåº'),(33,'status_4','å·²åºåº'),(34,'status_5','å·²å®æ'),(35,'status_6','è®¢åå·²æ¦æª,æ¨å¯éæ°ç¼è¾æäº¤'),(36,'platform_1','äºé©¬é'),(37,'platform_2','ä¹å¤©'),(38,'platform_3','éè'),(39,'platform_4','æ²å°ç'),(40,'category_1','æµ·å¤ä»ä»£åè®¢å'),(41,'category_2','ç¹è²å°å'),(42,'carrier_2','ä½å·æ¥ä¾¿'),(43,'carrier_3','é»ç«è¿è¾'),(44,'carrier_4','è¥¿æµè¿è¾'),(45,'carrier_5','å¡è½¦æ´¾é'),(46,'ord_status_1','æ°å»º'),(47,'ord_status_2','å¾å®¡æ ¸'),(48,'ord_status_3','å·²å®¡æ ¸å¾åºåº'),(49,'ord_status_4','å·²åºåº'),(50,'ord_status_5','å·²å®æ'),(51,'ord_status_6','åºå¼'),(52,'ord_status_7','å¼å¸¸'),(53,'deal_with_1','éæ°ä¸æ¶'),(54,'deal_with_2','è½¬è¿'),(55,'deal_with_3','éæ¯'),(56,'deal_with_4','ä¸è¯å'),(57,'classification_1','å°ç©'),(58,'classification_2','æè£'),(59,'classification_3','æ·å¤è¿å¨'),(60,'classification_4','çµå­äº§å'),(61,'classification_5','å®¶å±ç¨å'),(62,'classification_6','ç©å·'),(63,'classification_7','æç¢å'),(64,'classification_8','å¤§ä»¶'),(65,'classification_9','å¶ä»'),(66,'warehousing_1','æ°å»º'),(67,'warehousing_2','åç½®æµ·å¤ä»'),(68,'warehousing_3','å¤´ç¨æ¶è´§å¾è½¬è¿'),(69,'warehousing_4','è½¬è¿ä¸­'),(70,'warehousing_5','æµ·å¤ä»å¥åºæ¸ç¹'),(71,'warehousing_6','å¥åºæ¸ç¹å®æ'),(72,'warehousing_7','æµ·å¤ä»ä¸æ¶å®æ'),(73,'warehousing_8','åºå¼'),(74,'warehousing_9','å¼å¸¸'),(75,'clearanceType_1','åç¬éç¨æ¥å³'),(76,'clearanceType_2','æ éç¨æ¥å³'),(77,'taxType_1','é¢ç¼´å³ç¨'),(78,'taxType_2','å®æ¥å®é');
/*!40000 ALTER TABLE `l_label` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_manual_order`
--

DROP TABLE IF EXISTS `l_manual_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `l_manual_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ord_no` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `category` int(11) DEFAULT NULL,
  `carrier_no` int(11) DEFAULT NULL,
  `track_no` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `owner` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `update_on` timestamp NULL DEFAULT NULL,
  `create_on` timestamp NULL DEFAULT NULL,
  `create_by` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `update_by` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `from_ken_id` int(11) DEFAULT NULL,
  `from_city_id` int(11) DEFAULT NULL,
  `from_town_id` int(11) DEFAULT NULL,
  `from_where` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `from_zipcode` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `to_ken_id` int(11) DEFAULT NULL,
  `to_city_id` int(11) DEFAULT NULL,
  `to_town_id` int(11) DEFAULT NULL,
  `to_where` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `to_zipcode` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `on_behalf` varchar(2) COLLATE utf8_bin DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `china_no` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `from_contact` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `to_contact` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `from_whom` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `to_whom` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `channel` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_manual_order`
--

LOCK TABLES `l_manual_order` WRITE;
/*!40000 ALTER TABLE `l_manual_order` DISABLE KEYS */;
INSERT INTO `l_manual_order` VALUES (10,'string',10,10,'string','string',1,'2019-02-25 13:31:12','2019-02-03 06:30:45','string','string',14,14152,141520036,'string','string',11,11203,112030081,'string','string','Y',10,'string','xxx','xxx','string','string','string'),(11,'DY527f1ba7',2,NULL,'','user01',1,'2019-02-21 17:14:31','2019-02-21 17:13:28','user01',NULL,-1,3484,34840026,'','',14,14210,142100015,'','','N',NULL,'80005387728','','','user01','','-1'),(13,'DYf7a4c8f1',1,4,'23','operator',1,'2019-02-24 13:59:54','2019-02-24 13:59:54','operator',NULL,1,1519,15190001,'','',14,14210,142100012,'','','N',NULL,'','','','operator','','-1'),(15,'DY3221de91',1,4,'23','user01',1,'2019-02-27 03:06:43','2019-02-24 16:47:52','user01',NULL,9,9321,93210000,'xxxxxxxxxxx','xxxxxxxxxxxxx',14,14214,142140019,'xxxxxxxxxx','xxxxx','N',NULL,'xxxx','xxxxxxxxxx','xxxxxxxxxx','user01','xxxxxxxx','-1'),(16,'DYb337f6bb',1,3,'23','user01',3,'2019-02-28 12:22:41','2019-02-24 16:50:56','user01',NULL,9,9364,93640006,'xxxxxxxxxx','xxxxxxxx',8,8447,84470014,'xxxxxxxx','xxxxxxx','N',NULL,'xxxxxx','xxxxxxxxxx','xxxxxxxxxx','user01','xxxxxxxxxxxx','-1'),(18,'DY25ceaa9f',1,NULL,'','user01',4,'2019-02-28 12:22:09','2019-02-27 03:13:16','user01',NULL,-1,-1,-1,'','',12,12104,121040023,'','','N',NULL,'','','','user01','','-1');
/*!40000 ALTER TABLE `l_manual_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_manual_order_content`
--

DROP TABLE IF EXISTS `l_manual_order_content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `l_manual_order_content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ordno` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `sku` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `real_num` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_manual_order_content`
--

LOCK TABLES `l_manual_order_content` WRITE;
/*!40000 ALTER TABLE `l_manual_order_content` DISABLE KEYS */;
INSERT INTO `l_manual_order_content` VALUES (1,'DY7f70a288','121211','1212',12,111,NULL),(2,'DY0e08ea33','sku00001','skjfk',12,9,NULL),(3,'DYa27fdda4','sku00001','',7,0,NULL),(4,'DY39c6f28c','sku00001','skjfk',10,9,NULL),(5,'DY4c9c020a',' sku0001','',3,0,NULL),(6,'DYb41755c0','sdfsdf','aaa',5,22,NULL),(7,'DY527f1ba7','111','1212',1,111,NULL),(9,'DYf7a4c8f1','sku0002','ç®±å­',1,8,NULL),(13,'DY3221de91','sku0002','ç®±å­',23,8,NULL),(14,'DY3221de91','sku0002','ç®±å­',23,8,NULL),(15,'DY3221de91','sku0002','ç®±å­',23,8,NULL),(22,'DYb337f6bb','sku0002','ç®±å­',12,8,NULL),(23,'DYb337f6bb','sku0002','ç®±å­',12,8,NULL),(24,'DY25ceaa9f','','',0,0,NULL),(25,'DY25ceaa9f','sku00001','skjfk',0,9,NULL),(26,'DY25ceaa9f','sku00001','skjfk',1,9,NULL),(27,'DY25ceaa9f','','',0,0,NULL),(28,'DY25ceaa9f','sku00001','skjfk',0,9,NULL),(29,'DY25ceaa9f','sku00001','skjfk',1,9,NULL);
/*!40000 ALTER TABLE `l_manual_order_content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_order_status`
--

DROP TABLE IF EXISTS `l_order_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `l_order_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `seq` int(11) DEFAULT NULL,
  `type` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_order_status`
--

LOCK TABLES `l_order_status` WRITE;
/*!40000 ALTER TABLE `l_order_status` DISABLE KEYS */;
INSERT INTO `l_order_status` VALUES (1,1,'order','æ°å¢'),(2,2,'order','åç½®æµ·å¤ä»'),(3,3,'order','å¤´ç¨æ¶è·å¾è½¬è¿'),(4,4,'order','è½¬è¿ä¸­'),(5,5,'order','æµ·å¤ä»å¥åºæ¸ç¹'),(6,7,'order','å¥åºæ¸ç¹å®æ'),(7,8,'order','æµ·å¤ä»ä¸æ¶å®æ'),(8,9,'order','åºå¼'),(9,10,'order','å¼å¸¸');
/*!40000 ALTER TABLE `l_order_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_permission`
--

DROP TABLE IF EXISTS `l_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `l_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `desc` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `url` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `method` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_permission`
--

LOCK TABLES `l_permission` WRITE;
/*!40000 ALTER TABLE `l_permission` DISABLE KEYS */;
INSERT INTO `l_permission` VALUES (1,'ROLE_HOME','home','/',NULL,'GET'),(5,'ROLE_ADMIN','admin','/**',NULL,'ALL'),(7,'ROLE_USER','user','/**',NULL,'ALL'),(8,'ROLE_ANONYMOUS','ROLE_ANONYMOUS','/user/**',NULL,'POST'),(9,'ROLE_DEFAULT_COMM','ROLE_DEFAULT_COMM','/**',NULL,'ALL'),(10,'ROLE_ANONYMOUS','ROLE_ANONYMOUS','/login',NULL,'ALL'),(11,'ROLE_ANONYMOUS','ROLE_ANONYMOUS','/script/**',NULL,'ALL');
/*!40000 ALTER TABLE `l_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_permission_role`
--

DROP TABLE IF EXISTS `l_permission_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `l_permission_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleid` int(11) DEFAULT NULL,
  `permissionid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_permission_role`
--

LOCK TABLES `l_permission_role` WRITE;
/*!40000 ALTER TABLE `l_permission_role` DISABLE KEYS */;
INSERT INTO `l_permission_role` VALUES (1,1,5),(3,2,5),(9,3,5),(83,4,5),(91,5,5),(92,6,5);
/*!40000 ALTER TABLE `l_permission_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_productions`
--

DROP TABLE IF EXISTS `l_productions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `l_productions` (
  `sku` varchar(40) COLLATE utf8_bin NOT NULL,
  `dy_sku` varchar(13) COLLATE utf8_bin NOT NULL DEFAULT '1234567890123',
  `productName` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `classification` varchar(300) COLLATE utf8_bin DEFAULT NULL,
  `color` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `size` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `length` double DEFAULT NULL,
  `width` double DEFAULT NULL,
  `height` double DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `createdBy` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `updateBy` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `productValue` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `location` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `img_path` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `alert_num` int(11) DEFAULT NULL COMMENT 'ç¼ºè´§é¢è­¦æ°ç®',
  `create_on` datetime DEFAULT NULL,
  `update_on` datetime DEFAULT NULL,
  `ava_num` int(11) DEFAULT NULL COMMENT 'è¯¥ååå¯å®æ°é,è¯¥é¡¹ä¸ºååå¨ä»åºä¸­çæææ¹æ¬¡çååæ°éä¹å',
  PRIMARY KEY (`sku`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_productions`
--

LOCK TABLES `l_productions` WRITE;
/*!40000 ALTER TABLE `l_productions` DISABLE KEYS */;
INSERT INTO `l_productions` VALUES ('000oooo','9253248433913','æ¯å·¾','1','ç½è²','L',10,10,3,1,'user01','user01','1',NULL,NULL,NULL,'2019-02-22 01:24:14',NULL,NULL),('121211','1281211134862','1212','0','','2',12,1,1,1,'user01','user01','111',NULL,'/opt/web/productdesc/fe8495ca58d64de6af92ac003a905973QQæµè§å¨æªå¾_20180109235724_5640D09F303C4184B6D3FF2069398BEE.jpg',23,'2018-12-07 01:23:51','2018-12-31 23:49:27',1041),('sdfsdf','3601324744525','aaa','0','','',2,2,2,2,'user01','user01','22',NULL,NULL,NULL,'2018-12-07 01:27:51','2018-12-31 23:21:18',NULL),('sku00001','8950615433071','skjfk','1','xxx','9.0',9,9,9,9,'user01','user01','9.0',NULL,NULL,NULL,'2018-12-13 22:17:35',NULL,30),('sku0002','0082316348939','ç®±å­','8','ç«ç°é','SS',30,50,25,3.5,'user01','user01','8',NULL,NULL,NULL,'2019-01-25 15:00:30',NULL,NULL);
/*!40000 ALTER TABLE `l_productions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_role`
--

DROP TABLE IF EXISTS `l_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `l_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_role`
--

LOCK TABLES `l_role` WRITE;
/*!40000 ALTER TABLE `l_role` DISABLE KEYS */;
INSERT INTO `l_role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER'),(3,'ROLE_ANONYMOUS'),(4,'ROLE_DEFAULT_COMM'),(5,'ROLE_OPERATOR'),(6,'ROLE_HEAD_OPERATOR');
/*!40000 ALTER TABLE `l_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_role_user`
--

DROP TABLE IF EXISTS `l_role_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `l_role_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `roleid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_role_user`
--

LOCK TABLES `l_role_user` WRITE;
/*!40000 ALTER TABLE `l_role_user` DISABLE KEYS */;
INSERT INTO `l_role_user` VALUES (1,1,1),(33,34,4),(41,42,6),(88,1,4),(89,41,4),(90,42,4);
/*!40000 ALTER TABLE `l_role_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_sale_record`
--

DROP TABLE IF EXISTS `l_sale_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `l_sale_record` (
  `saleRecNo` varchar(45) COLLATE utf8_bin NOT NULL,
  `sku` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `carrierNo` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `orderNo` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `fromUserId` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `fromContact` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `fromPostCode` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `fromWhere` varchar(450) COLLATE utf8_bin DEFAULT NULL,
  `toContact` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `toPostCode` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `toWhere` varchar(450) COLLATE utf8_bin DEFAULT NULL,
  `toWho` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `onBehalf` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `amountOnbehalf` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `num` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `value` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `column1` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `column2` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `column3` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `price` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `creator` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `createOn` datetime DEFAULT NULL,
  `updateOn` datetime DEFAULT NULL,
  `category` int(11) DEFAULT NULL,
  `length` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `width` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `height` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `weight` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `sizeCategory` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `fromParentSku` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `ken_id` int(2) DEFAULT NULL,
  `city_id` int(5) DEFAULT NULL,
  `town_id` int(9) DEFAULT NULL,
  `from_ken_id` int(2) DEFAULT NULL,
  `from_city_id` int(5) DEFAULT NULL,
  `from_town_id` int(9) DEFAULT NULL,
  PRIMARY KEY (`saleRecNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_sale_record`
--

LOCK TABLES `l_sale_record` WRITE;
/*!40000 ALTER TABLE `l_sale_record` DISABLE KEYS */;
INSERT INTO `l_sale_record` VALUES ('222','sku0004','1','222','','','','','','','','',NULL,'','','20',NULL,NULL,NULL,NULL,'',4,'user01','2018-12-01 00:37:01','2018-12-01 11:36:47',1,'0','0','0','0','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('249-0105826-7896651','5U-Y2SK-VDC5',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¯ã³ã¿ãããã³ã ãã³ã 3ï½5äººç¨ ã¯ã³ã¿ãã 2WAY ãã³ã 3WAY ãã³ã è¨­å¶ç°¡å é²ç½ç¨ ã­ã£ã³ãç¨å æ¥æ°´å å·¥ ç´«å¤ç·é²æ­¢ ç»å±± æãããã¿ é²æ°´ éæ°æ§ ã¢ã¦ããã¢ 240*210*135cm (ãã«ã¼, 3ï½5äººç¨)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-31 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-0123495-4604635','2N-KDXR-ZK1D',NULL,NULL,'admin',NULL,NULL,NULL,'0427691024','252-0344','ç¥å¥å·ç--ç¸æ¨¡åå¸ååºå¤æ·µ2â2â15','åå¾³ç¾æºå­',NULL,'JPY--11960.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã·ã«ãã¼ ã¢ã«ããã¬ã¼ã  åæ²¢, Lãµã¤ãºï¼7æ³ä»¥ä¸/ç´86 ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-22 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-0179037-4504603','52-EV2N-XBRO',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã ã¢ã«ããã¬ã¼ã  åæ²¢, Mãµã¤ãºï¼5-7æ³/ç´66 ãªããã«ï¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-03-02 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-0179342-2958255','9I-ZQH0-MJ73','1','','admin','','','','','612-8394','äº¬é½åº--äº¬é½å¸ä¼è¦åºä¸é³¥ç¾½è¥¿è¹å·çº5çªå°','å¥¥æãä½³æ­£',NULL,'','SAHASAHA ã¯ã³ã¿ãããã³ã ãã³ã 3ï½5äººç¨ ã¯ã³ã¿ãã 2WAY ãã³ã 3WAY ãã³ã è¨­å¶ç°¡å é²ç½ç¨ ã­ã£ã³ãç¨å æ¥æ°´','3',NULL,NULL,NULL,NULL,'',4,'user01','2018-11-07 14:43:07','2018-11-04 11:57:27',1,'0','0','0','0','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('249-0318620-3487823','WG-0DAJ-6HFY',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã·ã«ãã¼, Mãµã¤ãºï¼5-7æ³/ç´66ãªããã«ï¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-18 20:00:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-0352710-9356656','FH-WZ2Q-OYJP',NULL,NULL,'admin',NULL,NULL,NULL,'080â1693â2826','259-1206','ç¥å¥å·ç--å¹³å¡å¸çç°ï¼âï¼ï¼âï¼ï¼','èåãå¿ä¿',NULL,'JPY--8260.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã,Mãµã¤ãºï¼5-7æ³/ç´66ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-25 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-0509226-6055029','5F-TQLF-KUTC',NULL,NULL,'admin',NULL,NULL,NULL,'0480437661','340-0164','å¼çç--å¹¸æå¸é¦æ¥å3-1-4','ç´°æ¨ ã¾ãã¿',NULL,'JPY--2980.00','SAHASAHA éµç¤ãã¼ã¢ãã« (ã¡ã­ãã£ã¼ãã¢ã) ãããã ãã³ã¯ ãã«ã¼ 32éµ (ãã³ã¯)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-23 20:00:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-0749121-3166231','LQ-IAIV-1HTJ',NULL,NULL,'admin',NULL,NULL,NULL,'08040172065','573-1151','å¤§éªåº--ææ¹å¸æ±ç§éçº27-1','åç°ãé',NULL,'JPY--7960.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ æ¡å¼µ ã¸ããã¼ å¨ãµã¤ãº æã (ã·ã£ã³ãã³, Mãµã¤ãºï¼5-7æ³/61ï¼æ¡å¼µæ72ï¼ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-08 12:00:06',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-0960327-0245437','FA-BWNY-IM4Z',NULL,NULL,'admin',NULL,NULL,NULL,'09074483161','811-3112','ç¦å²¡ç--å¤è³å¸è±è¦æ±4ä¸ç®6-7','éä¸¸äºå¼¥',NULL,'JPY--10260.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã·ã«ãã¼ ã¢ã«ããã¬ã¼ã  åæ²¢, Mãµã¤ãºï¼5-7æ³/ç´66 ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-27 20:00:06',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-1002727-6495039','O8-1VYB-PZQ4',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, æ©åæè¾¼ãµã¤ãº(1-3æ³/ç´41ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-03-02 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-1313519-7892657','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,'','989-6405','å®®åç--å¤§å´å¸å²©åºå±±æ± æå­ä¸ä¸æ æ ¹å²¸ï¼ï¼ã®ï¼','ä¼è¤åå½¦',NULL,'JPY--399.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-04 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-1380959-3998241','VK-7V5A-H543',NULL,NULL,'admin',NULL,NULL,NULL,'','318-0033','è¨åç--é«è©å¸æ¬çºï¼âï¼ï¼','æ¸¡é åå°',NULL,'JPY--6980.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-10 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-1505959-8093411','FH-WZ2Q-OYJP',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã,Mãµã¤ãºï¼5-7æ³/ç´66ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-28 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-1609886-9435807','HM-B5BT-1IWO',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ æ¡å¼µ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, ï¼³ãµã¤ãºï¼3-5æ³/47ï¼æ¡å¼µæ56ï¼ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-11-24 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('249-1680239-1835047','Dustbin-white',NULL,NULL,'admin',NULL,NULL,NULL,'07055625383','260-0033','åèç--åèå¸ä¸­å¤®åºæ¥æ¥2-21-10-1F','å±±ä¸å¼æ¯',NULL,'JPY--9800.00','SAHASAH èµ¤å¤ç·ã»ã³ãµã¼å¼å¨èªåééå¼ã¹ãã³ã¬ã¹ãã¿ç®±ãè¿ã¥ãã ãã§èªåçã«éãããæ¸æ½ãã¤ã³ããªã¢ãå®¹é7ï¼¬ (ãã¯ã¤ã)','2',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-19 20:00:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-1863248-5025416','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,'','503-0801','å²éç--å¤§å£å¸ååæ°çº','æ­¦è¤é',NULL,'JPY--399.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-30 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-1951689-8391854','UW-NJCD-KF9N',NULL,NULL,'admin',NULL,NULL,NULL,'','514-2221','ä¸éç--æ´¥å¸é«éå°¾çº634ã¼7','ã¯ãããã¿ãã',NULL,'JPY--7660.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-07 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-1975088-7943839','YM-RFHA-8TVU',NULL,NULL,'admin',NULL,NULL,NULL,'0449114212','214-0014','ç¥å¥å·ç--å·å´å¸å¤æ©åºç»æ¸2828-614','å½±å±±å¥ç©ç¾',NULL,'JPY--1980.00','SAHASAHA çä»ã çç© ã¯ãªãã è¡¿æ­¢ãã¯ãªãã åè£ã¯ãªãã è± ï¼è² 2ã»ãã 6å','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-05 20:00:56',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-2036129-8179816','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,'','266-0005','åèç--åèå¸ç·åºèªç°çº2ï¼11ï¼180','å¤§å¡ç¾©ä¹',NULL,'JPY--998.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-06 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-2102393-7818252','SN-43IS-TWEP',NULL,NULL,'admin',NULL,NULL,NULL,'09013463655','902-0071','æ²ç¸ç--é£è¦å¸ç¹å¤å·4ã¼7ã¼15','ç¶ç åä¹',NULL,'JPY--11960.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, Lãµã¤ãºï¼7æ³ä»¥ä¸/ç´86 ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-02 20:00:06',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-2308591-9833433','FH-WZ2Q-OYJP',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã,Mãµã¤ãºï¼5-7æ³/ç´66ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-22 20:00:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-2466942-3995846','ED-0UIY-40IQ',NULL,NULL,'admin',NULL,NULL,NULL,'','558-0053','å¤§éªåº--å¤§éªå¸ä½ååºå¸å¡å±±ä¸­','èéãä½ç´',NULL,'JPY--8260.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-13 20:00:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-2510562-9086269','ED-0UIY-40IQ',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã,Mãµã¤ãºï¼5-7æ³/ç´66ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-03-03 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-2523256-7504627','52-EV2N-XBRO',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã ã¢ã«ããã¬ã¼ã  åæ²¢, Mãµã¤ãºï¼5-7æ³/ç´66 ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-03-02 20:00:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-2545457-7694210','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,'','243-0202','ç¥å¥å·ç--åæ¨å¸ä¸­è»é944-4','è±ä¸ãã¾ãã¿',NULL,'JPY--399.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-03 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-2604500-3450203','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,'','257-0003','ç¥å¥å·ç--ç§¦éå¸åç¢å536-1','è¿è¤å²³',NULL,'JPY--998.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-12 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-2668698-8016605','O8-1VYB-PZQ4',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, æ©åæè¾¼ãµã¤ãº(1-3æ³/ç´41ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-25 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-2772987-0048651','UW-NJCD-KF9N',NULL,NULL,'admin',NULL,NULL,NULL,'','080-2473','åæµ·é--å¸¯åºå¸è¥¿äºåä¸æ¡å4ä¸ç®13ã15','å±±å£ è£ç¾å­',NULL,'JPY--7277.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-02 20:00:02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-2862014-0790254','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,'','660-0051','åµåº«ç--å°¼å´å¸æ±ä¸æ¾çº1ä¸ç®11-19ãã«ãºç«è±2-203','å±±ä¸­éå¹¸',NULL,'JPY--399.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-03 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-3069342-3844629','0Y-K99Y-JMPZ',NULL,NULL,'admin',NULL,NULL,NULL,'','169-0073','æ±äº¬é½--ç¾äººçº1-3-14ããæ°å®¿509','åç°åªè±',NULL,'JPY--9960.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-30 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-3121506-8491021','TG-7U8Z-SBK2',NULL,NULL,'admin',NULL,NULL,NULL,'09068893131','771-1212','å¾³å³¶ç--æ¿éé¡èä½çºå¾³å½å­åé æ±111-14','äºåãç¾æ²ç·',NULL,'JPY--17999.00','ã­ã¯ã¿ã äºè¡ ã»ããã¼ãã±ã¼ã¹ãå¼ä»å± #73','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-17 20:00:15',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-3164211-0287833','SF-0LCQ-50C6',NULL,NULL,'admin',NULL,NULL,NULL,'','187-0032','æ±äº¬é½--å°å¹³å¸ãå°å·çºãï¼ä¸ç® ï¼ï¼ï¼','å²©è°·ãè²´æ¨¹',NULL,'JPY--699.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ãã©ãã¯)','23',NULL,NULL,NULL,NULL,NULL,1,'user01','2018-11-03 20:00:05',NULL,2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('249-3497165-7724644','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,'','675-0061','åµåº«ç--å å¤å·å¸å å¤å·çº','å¢ç°ãç« äºº',NULL,'JPY--499.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-06 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-3549687-2659057','26-7CM8-AURL',NULL,NULL,'admin',NULL,NULL,NULL,'0827-32-4549','740-0034','å±±å£ç--å²©å½å¸åå²©å½çºï¼ä¸ç®10-30','æ²³æå²å¤«',NULL,'JPY--4980.00','SAHASAHA ã¯ã³ã¿ãããã³ã ãã³ã 2ï½4äººç¨ ã¯ã³ã¿ãã è¨­å¶ç°¡å é²ç½ç¨ ã­ã£ã³ãç¨å æ¥æ°´å å·¥ ç´«å¤ç·é²æ­¢ ç»å±± æãããã¿ é²æ°´ éæ°æ§ ã¢ã¦ããã¢','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-25 20:00:06',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-3582124-2346266','0Y-K99Y-JMPZ',NULL,NULL,'admin',NULL,NULL,NULL,'','335-0021','å¼çç--æ¸ç°å¸æ°æ½','æ©æ¬ãã¿ãªã¿',NULL,'JPY--9960.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-09 20:00:02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-3692143-7796668','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,'','999-3511','å±±å½¢ç--è¥¿æå±±é¡æ²³åçºè°·å°ä¸45-8','åç°æ­£å¹¸',NULL,'JPY--1197.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-02 20:00:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-3782268-7360610','0Y-K99Y-JMPZ',NULL,NULL,'admin',NULL,NULL,NULL,'','669-1533','åµåº«ç--ä¸ç°å¸ä¸ç°çº','Arianne San Jose',NULL,'JPY--9960.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-09 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-3817591-6490224','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,'','657-0844','åµåº«ç--ç¥æ¸å¸çåºé½é5-3-1-402','å±±ç°ãå®ç¾',NULL,'JPY--399.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-02 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-3864140-2188612','FH-WZ2Q-OYJP',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ æ¡å¼µ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, Mãµã¤ãºï¼5-7æ³/61ï¼æ¡å¼µæ72ï¼ãªããã«ï¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-26 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-3893618-0246238','VK-7V5A-H543',NULL,NULL,'admin',NULL,NULL,NULL,'','167-0032','æ±äº¬é½--æä¸¦åºå¤©æ²¼2-30-12','è¤ç°å¤ç¨',NULL,'JPY--6980.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-23 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-4288156-1613400','0Y-K99Y-JMPZ',NULL,NULL,'admin',NULL,NULL,NULL,'','615-0047','äº¬é½åº--äº¬é½å¸å³äº¬åºè¥¿é¢å­åç°çº14-702å·','å·å¿æ²ä¹æ­',NULL,'JPY--9960.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-22 20:00:02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-4289309-8575846','FH-WZ2Q-OYJP',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã,Mãµã¤ãºï¼5-7æ³/ç´66ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-27 20:00:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-4329323-1886226','ED-0UIY-40IQ',NULL,NULL,'admin',NULL,NULL,NULL,'','174-0074','æ±äº¬é½--æ¿æ©åºæ±æ°çº','å¤§ä¹ä¿ä¸å¸å­',NULL,'JPY--8260.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-08 20:00:06',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-4525124-0923005','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-11 20:00:02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-4538851-4929400','FH-WZ2Q-OYJP',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã,Mãµã¤ãºï¼5-7æ³/ç´66ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-27 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-4715789-9610258','LQ-IAIV-1HTJ',NULL,NULL,'admin',NULL,NULL,NULL,'09043234513','601-8474','äº¬é½åº--äº¬é½å¸ååºåãå¡çº32-2','å ã¿ã¥ã',NULL,'JPY--7960.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ æ¡å¼µ ã¸ããã¼ å¨ãµã¤ãº æã (ã·ã£ã³ãã³, Mãµã¤ãºï¼5-7æ³/61ï¼æ¡å¼µæ72ï¼ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-13 16:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-4807412-8175065','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,'','655-0861','åµåº«ç--ç¥æ¸å¸åæ°´åºä¸ççºä¸ççº2053-2','è¤æ¬',NULL,'JPY--998.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-13 20:00:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-4840635-9775039','ED-0UIY-40IQ',NULL,NULL,'admin',NULL,NULL,NULL,'','359-0045','å¼çç--ææ²¢å¸ç¾åçº','å±±ç°æµæå­',NULL,'JPY--7847.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-02 20:00:02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-4952441-9127038','FH-WZ2Q-OYJP',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'JPY--8260.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã,Mãµã¤ãºï¼5-7æ³/ç´66ãªããã«ï¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-20 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-5142073-8243823','WG-0DAJ-6HFY',NULL,NULL,'admin',NULL,NULL,NULL,'08031675489','179-0085','æ±äº¬é½--ç·´é¦¬åºæ©å®®ä¸ä¸ç®','æç°éº»è¡£',NULL,'JPY--7960.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ æ¡å¼µ ã¸ããã¼ å¨ãµã¤ãº æã (ã·ã«ãã¼, Mãµã¤ãºï¼5-7æ³/61ï¼æ¡å¼µæ72ï¼ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-16 20:00:02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-5311811-5314209','VK-7V5A-H543',NULL,NULL,'admin',NULL,NULL,NULL,'','241-0024','ç¥å¥å·ç--æ¨ªæµå¸æ­åºæ¬æçº','è¥ææ²å½©',NULL,'JPY--6980.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-16 20:00:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-5431604-7100667','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,'','651-2112','åµåº«ç--ç¥æ¸å¸è¥¿åºå¤§æ´¥åï¼âï¼ï¼âï¼','ã­ã¼ã½ã³å¤§æ´¥åäºä¸ç®',NULL,'JPY--399.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-25 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-5591453-5803800','0Y-K99Y-JMPZ',NULL,NULL,'admin',NULL,NULL,NULL,'','164-0012','æ±äº¬é½--ä¸­éåºæ¬çº3-28-8','å¤ªç° æé¦',NULL,'JPY--9960.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-08 20:00:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-5599415-5117435','ED-0UIY-40IQ',NULL,NULL,'admin',NULL,NULL,NULL,'','959-3124','æ°æ½ç--æä¸å¸éå±1520-2','è¿è¤æ­äºº',NULL,'JPY--8260.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-29 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-5838711-8831024','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,'','215-0036','ç¥å¥å·ç--å·å´å¸éº»çåºã¯ãã²é4-5-1 207å·å®¤','ç«¹å±±ãåæ´',NULL,'JPY--699.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-11-24 20:00:02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('249-5875291-2787064','FH-WZ2Q-OYJP',NULL,NULL,'admin',NULL,NULL,NULL,'0593831746','513-0809','ä¸éç--é´é¹¿å¸è¥¿æ¡','é´æ¨ ã¾ãã¿',NULL,'JPY--8260.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã,Mãµã¤ãºï¼5-7æ³/ç´66ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-26 20:00:09',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-6046490-9091029','FH-WZ2Q-OYJP',NULL,NULL,'admin',NULL,NULL,NULL,'09028594541','861-2101','çæ¬ç--çæ¬å¸æ±åºæ¡æ¨','æ¾¤ç°ç´ç¾',NULL,'JPY--6960.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ æ¡å¼µ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, Mãµã¤ãºï¼5-7æ³/61ï¼æ¡å¼µæ72ï¼ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'user01','2018-11-04 12:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('249-6200334-0880667','SF-0LCQ-50C6',NULL,NULL,'admin',NULL,NULL,NULL,'','791-1104','æåªç--æ¾å±±å¸ååå±3-2-13','è¤æ ç«¹ç',NULL,'JPY--699.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ãã©ãã¯)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-11-17 16:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('249-6400463-5833461','O8-1VYB-PZQ4',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'JPY--6960.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-05 20:00:44',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-6513959-9523056','0Y-K99Y-JMPZ',NULL,NULL,'admin',NULL,NULL,NULL,'','215-0006','ç¥å¥å·ç--å·å´å¸éº»çåºéç¨','ææ¾åé³',NULL,'JPY--10460.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-03 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-6600971-9159054','SN-43IS-TWEP',NULL,NULL,'admin',NULL,NULL,NULL,'09021903279','610-0121','äº¬é½åº--åé½å¸å¯ºç°åº­äº145-34','è³å»¶ è',NULL,'JPY--9960.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, Lãµã¤ãºï¼7æ³ä»¥ä¸/ç´86 ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-25 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-6624671-3862226','HM-B5BT-1IWO',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, ï¼³ãµã¤ãºï¼3-5æ³/ç´51ãªããã«ï¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-03-02 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-6665177-9223030','UW-NJCD-KF9N',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, ï¼³ãµã¤ãºï¼3-5æ³/ç´51ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-28 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-6712232-5543806','ED-0UIY-40IQ',NULL,NULL,'admin',NULL,NULL,NULL,'','277-0872','åèç--æå¸åä½äº276-389','å±±å£åä¹',NULL,'JPY--8260.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-31 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-6849258-2339843','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,'','408-0115','å±±æ¢¨ç--åæå¸é ççºå¤§è±çç°588','è¼¿ç³åå½¦',NULL,'JPY--399.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-03 20:00:02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-6868883-2215819','VK-7V5A-H543',NULL,NULL,'admin',NULL,NULL,NULL,'','390-0306','é·éç--æ¾æ¬å¸åæµé612-8','èµ¤ç¾½èå¥',NULL,'JPY--7131.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-04 20:00:02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-6967004-5914207','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,'','166-0003','æ±äº¬é½--æä¸¦åºé«åå¯ºå4ã¼13ã¼5ã¼502','å¤§ç¢æ³°æ',NULL,'JPY--499.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-11 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-7010486-1911822','M0-CFTC-91ME',NULL,NULL,'admin',NULL,NULL,NULL,'0774931814','619-0245','äº¬é½åº--ç¸æ¥½é¡ç²¾è¯çºä¸çæµæ¥½52-3','å¤ªç°ãè³ç§',NULL,'JPY--20780.00','SAHASAHA ã²ã¼ãã³ã°ãã§ã¢ ãªãã£ã¹ãã§ã¢ å¤æ©è½ ã²ã¼ã ç¨ãã§ã¢ ãªã¯ã©ã¤ãã³ã° ãã½ã³ã³ãã§ã¢ ãã¤ããã¯ ãããã¬ã¹ã è°çå¯¾ç­ ã©ã³ãã¼ãµãã¼ã ã²ãæãä»ã ã¬ã¶ã¼ (ãã¯ã¤ã)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-05 20:00:54',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-7027205-2275069','93-KCRN-BNNG',NULL,NULL,'admin',NULL,NULL,NULL,'08033923541','186-0004','æ±äº¬é½--å½ç«å¸ä¸­','èå° å®',NULL,'JPY--11511.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã ã¢ã«ããã¬ã¼ã  åæ²¢, Lãµã¤ãºï¼7æ³ä»¥ä¸/ç´86 ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-23 20:00:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-7057096-3783861','FH-WZ2Q-OYJP',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã,Mãµã¤ãºï¼5-7æ³/ç´66ãªããã«ï¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-26 20:00:10',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-7150820-3438210','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,'','503-2418','å²éç--ææé¡æ± ç°çºèæ·±','é«æ©çµæ´',NULL,'JPY--499.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-10 20:00:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-7335281-1650212','Dustbin-white',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAH èµ¤å¤ç·ã»ã³ãµã¼å¼å¨èªåééå¼ã¹ãã³ã¬ã¹ãã¿ç®±ãè¿ã¥ãã ãã§èªåçã«éãããæ¸æ½ãã¤ã³ããªã¢ãå®¹é7ï¼¬ (ãã¯ã¤ã)','2',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-12 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-7434410-2220609','SF-0LCQ-50C6',NULL,NULL,'admin',NULL,NULL,NULL,'','263-0041','åèç--åèå¸ç¨²æ¯åºé»ç å°','ç±ç° ä½ä»',NULL,'JPY--699.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ãã©ãã¯)','3',NULL,NULL,NULL,NULL,NULL,1,'user01','2018-11-04 12:00:02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('249-7576051-6035842','Tent-05',NULL,NULL,'admin',NULL,NULL,NULL,'09040186199','136-0073','æ±äº¬é½--æ±æ±åºåç 3-2-2','æåä¹',NULL,'JPY--4980.00','SAHASAHA ã¯ã³ã¿ãããã³ã 2ä»£ 3ï½4äººç¨ æ¥æ°´å å·¥ é²æ°´ éæ°æ§ è¨­å¶ç°¡å 2è²é¸æå¯è½ ç»å±± æãããã¿ 210 * 200 * 135C','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-11-18 18:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('249-7687650-5203049','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,'','404-0051','å±±æ¢¨ç--ç²å·å¸å¡©å±±ç«¹æ£®1139-2','ç°éä¸è¼',NULL,'JPY--399.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-04 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-7754209-1817460','UN-CC16-9Q87',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã·ã«ãã¼, æ©åæè¾¼ãµã¤ãº(1-3æ³/ç´41ãªããã«ï¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-13 20:00:06',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-7883096-8445461','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,'','833-0002','ç¦å²¡ç--ç­å¾å¸åæ´¥860-2ãã¤ãã³ã¹ã°ãªã¼ã³5  508å·','ç°å·',NULL,'JPY--399.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-01 20:00:06',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-7885082-6647052','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,'','503-0973','å²éç--å¤§å£å¸æ¨æ¸çº','ã­ã·ã§ ã¢ã³ãã«ã½ã³',NULL,'JPY--798.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-30 20:00:02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-7901925-3039006','SN-43IS-TWEP',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, Lãµã¤ãºï¼7æ³ä»¥ä¸/ç´86 ãªããã«ï¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-22 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-7924765-1469443','YM-RFHA-8TVU',NULL,NULL,'admin',NULL,NULL,NULL,'0267826765','384-0414','é·éç--ä½ä¹å¸ä¸è¶ï¼ï¼ï¼ï¼','äºåºå¥ç·ç¾',NULL,'JPY--999.00','SAHASAHA çä»ã çç© ã¯ãªãã è¡¿æ­¢ãã¯ãªãã åè£ã¯ãªãã è± 6åã»ãã','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-22 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-7926947-0515803','SF-0LCQ-50C6',NULL,NULL,'admin',NULL,NULL,NULL,'','212-0054','ç¥å¥å·ç--å·å´å¸å¹¸åºå°å4-19-44','å¾¡å®¿ãæ¿å¸',NULL,'JPY--699.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ãã©ãã¯)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-11-18 18:00:02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('249-8027194-9987006','VK-7V5A-H543',NULL,NULL,'admin',NULL,NULL,NULL,'','101-0054','æ±äº¬é½--åä»£ç°åºç¥ç°é¦çº','ä¼è¤æé¦¨',NULL,'JPY--6980.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-23 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-8471342-9159010','ED-0UIY-40IQ',NULL,NULL,'admin',NULL,NULL,NULL,'','569-1114','å¤§éªåº--é«æ§»å¸','ä¸å ç¸',NULL,'JPY--8260.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-30 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-8511971-1090221','FH-WZ2Q-OYJP',NULL,NULL,'admin',NULL,NULL,NULL,'08095067972','371-0857','ç¾¤é¦¬ç--åæ©å¸1-22-46','å°éæ²çµµé',NULL,'JPY--7960.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ æ¡å¼µ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, Mãµã¤ãºï¼5-7æ³/61ï¼æ¡å¼µæ72ï¼ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-11-24 20:00:02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('249-8648661-3801424','ED-0UIY-40IQ',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã,Mãµã¤ãºï¼5-7æ³/ç´66ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-01 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-8691174-6962229','QB-1TG8-0OMK',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã·ã«ãã¼, Lãµã¤ãºï¼7æ³ä»¥ä¸/ç´86 ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-24 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-8931422-9836661','UW-NJCD-KF9N',NULL,NULL,'admin',NULL,NULL,NULL,'','107-0052','æ±äº¬é½--æ¸¯åºèµ¤åï¼âï¼âï¼âï¼ï¼ï¼','éç°ããã',NULL,'JPY--7660.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-07 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-9037899-7380612','WG-0DAJ-6HFY',NULL,NULL,'admin',NULL,NULL,NULL,'08079891647','511-1135','ä¸éç--æ¡åå¸é·å³¶çºæµ¦å®14çªå°1','æ±è¶çå®å­',NULL,'JPY--7960.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã·ã«ãã¼, Mãµã¤ãºï¼5-7æ³/ç´66ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-05 20:00:57',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-9045071-4344623','5W-P3L6-0PBG',NULL,NULL,'admin',NULL,NULL,NULL,'','839-0863','ç¦å²¡ç--ä¹çç±³å¸å½åçº','è¯åå¤ç',NULL,'JPY--15840.00','SAHASAHA ãã³ã ä¸äººç¨ ã³ã³ãã¯ã è¿·å½©æ ã­ã£ã³ããã³ã ã½ã­ãã³ã å°åãã³ããé²ç½ãç·æ¥ã ã­ã£ã³ãç¨å ï¿½','4',NULL,NULL,NULL,NULL,NULL,1,'user01','2018-11-04 12:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('249-9134286-5741461','A4-TWLW-BYFK',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¯ã³ã¿ãããã³ã ãã³ã 3ï½5äººç¨ ã¯ã³ã¿ãã 3WAY ãã³ã è¨­å¶ç°¡å é²ç½ç¨ ã­ã£ã³ãç¨å æ¥æ°´å å·¥ ç´«å¤ç·é²æ­¢ ç»å±± æãããã¿ é²æ°´ éæ°æ§ ã¢ã¦ããã¢ ãã«ã¼ 240 * 210 * 135cm','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-08 12:00:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-9313956-2590229','93-KCRN-BNNG',NULL,NULL,'admin',NULL,NULL,NULL,'0425671778','207-0013','æ±äº¬é½--æ±å¤§åå¸åå','èæ± ç´ç¾',NULL,'JPY--11511.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã ã¢ã«ããã¬ã¼ã  åæ²¢, Lãµã¤ãºï¼7æ³ä»¥ä¸/ç´86 ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-24 20:00:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-9503210-8081409','UW-NJCD-KF9N',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, ï¼³ãµã¤ãºï¼3-5æ³/ç´51ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-04 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-9658335-1830254','VK-7V5A-H543',NULL,NULL,'admin',NULL,NULL,NULL,'','106-0045','æ±äº¬é½--æ¸¯åºéº»å¸åçªï¼ï¼ï¼ï¼ï¼','å³¶æå½©é¦',NULL,'JPY--6980.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-03-03 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-9835338-2155810','WG-0DAJ-6HFY',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã·ã«ãã¼, Mãµã¤ãºï¼5-7æ³/ç´66ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-19 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('249-9899176-1419044','UW-NJCD-KF9N',NULL,NULL,'admin',NULL,NULL,NULL,'','240-0011','ç¥å¥å·ç--æ¨ªæµå¸ä¿åã±è°·åºæ¡ã±ä¸','å è¤äºç¾',NULL,'JPY--7660.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-25 20:00:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-0003353-5510207','UN-CC16-9Q87',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ æ¡å¼µ ã¸ããã¼ å¨ãµã¤ãº æã (ã·ã«ãã¼, æ©åæè¾¼ãµã¤ãº(1-3æ³/33ï¼æ¡å¼µæ40ï¼ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-19 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-0182499-2591818','O8-1VYB-PZQ4',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ æ¡å¼µ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, æ©åæè¾¼ãµã¤ãº(1-3æ³/33ï¼æ¡å¼µæ40ï¼ãªããã«ï¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-25 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-0250212-0333456','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,'','581-0885','å¤§éªåº--å«å°¾å¸ç¥å®®å¯º5ä¸ç®48','æµäº å»£å¸',NULL,'JPY--798.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-02 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-0258167-9866266','SF-0LCQ-50C6',NULL,NULL,'admin',NULL,NULL,NULL,'','179-0084','æ±äº¬é½--ç·´é¦¬åºæ°·å·å°3-33-20','é·å²©ãéå¥',NULL,'JPY--699.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ãã©ãã¯)','5',NULL,NULL,NULL,NULL,NULL,1,'user01','2018-11-03 20:00:05',NULL,3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('250-0287133-8948674','ED-0UIY-40IQ',NULL,NULL,'admin',NULL,NULL,NULL,'','861-6402','çæ¬ç--å¤©èå¸åå²³çºæ£åº2855-2','ä¸­æå¤§æ¨¹',NULL,'JPY--8260.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-05 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-0408379-4981403','YI-E0XQ-KIDU',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'åå¿èå¥é ç´æ¨äºè¡ ä¸­å½èå·äºè¡ ã±ã¼ã¹ä»','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-05 20:00:53',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-0529749-7925412','UW-NJCD-KF9N',NULL,NULL,'admin',NULL,NULL,NULL,'','651-2244','åµåº«ç--ç¥æ¸å¸è¥¿åºäºå¹å°åçº4-55-6','å¤§åº­æ½¤å¹³',NULL,'JPY--7660.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-08 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-0600678-7654211','O8-1VYB-PZQ4',NULL,NULL,'admin',NULL,NULL,NULL,'08065032856','252-0815','ç¥å¥å·ç--è¤æ²¢å¸ç³å·ï¼ï¼ï¼ï¼ï¼ï¼','å±±ç°ãæºä¹',NULL,'JPY--6960.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, æ©åæè¾¼ãµã¤ãº(1-3æ³/ç´41ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-23 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-0660024-7771806','FH-WZ2Q-OYJP',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã,Mãµã¤ãºï¼5-7æ³/ç´66ãªããã«ï¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-22 20:00:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-0677307-0876669','YM-RFHA-8TVU',NULL,NULL,'admin',NULL,NULL,NULL,'0429950286','359-0042','å¼çç--ææ²¢å¸ä¸¦æ¨2-3-1-1009','é¢å£çå¼',NULL,'JPY--999.00','SAHASAHA 2019 çä»ã çç© ã¯ãªãã è¡¿æ­¢ãã¯ãªãã åè£ã¯ãªãã æå­ è± 3è²ã»ãã (3åã»ãã, è±)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-26 20:00:07',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-0789290-9213413','VK-7V5A-H543',NULL,NULL,'admin',NULL,NULL,NULL,'','120-0033','æ±äº¬é½--è¶³ç«åºåä½å¯¿çº','å±±ä¸å½©å¤',NULL,'JPY--6980.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-01 20:00:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-0907942-0991823','W8-PTVD-477D',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ TSAã­ãã¯ åé¡é¢ä»ä¸ã ã¢ã©ã¤ã³å å·¥ ã¢ã«ããã¬ã¼ã  ã¬ãã­ æè¡ åºå¼µ è»½é éé³ ãã¡ã¹ãã¼ã¬ã¹ æ©åæè¾¼å¯ (S, ã¢ã«ããã¬ã¼ã  -ã­ã¼ãºã´ã¼ã«ã)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-16 20:00:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-0940926-1857448','UW-NJCD-KF9N',NULL,NULL,'admin',NULL,NULL,NULL,'','134-0083','æ±äº¬é½--æ±æ¸å·åºä¸­èè¥¿4-13-4','é½è¤åªæ',NULL,'JPY--7660.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-26 20:00:10',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-1035498-8179065','0Y-K99Y-JMPZ',NULL,NULL,'admin',NULL,NULL,NULL,'','053-0821','åæµ·é--è«å°ç§å¸ãããã°çºï¼ä¸ç®ï¼ï¼çªï¼','å°æå¬äºº',NULL,'JPY--9960.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-17 20:00:14',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-1067834-8972615','VK-7V5A-H543',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, æ©åæè¾¼ãµã¤ãº(1-3æ³/ç´41ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-03-05 20:00:06',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-1076985-2163047','UW-NJCD-KF9N',NULL,NULL,'admin',NULL,NULL,NULL,'','244-0815','ç¥å¥å·ç--æ¨ªæµå¸æ¸å¡åºä¸åç°çº','æ²³éçç¥å­',NULL,'JPY--7660.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-29 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-1120141-9051813','VK-7V5A-H543',NULL,NULL,'admin',NULL,NULL,NULL,'','483-8103','æç¥ç--æ±åå¸åé·çºå®®å112','ä¸å²¡ç¥å¯¿ç¾',NULL,'JPY--6980.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-13 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-1278877-8339859','3Q-YKCK-C573',NULL,NULL,'admin',NULL,NULL,NULL,'0256470044','955-0047','æ°æ½ç--ä¸æ¡å¸æ±ä¸æ¡2-22-14','å²©ç¬æºå­',NULL,'JPY--8960.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ æ¡å¼µ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã ã¢ã«ããã¬ã¼ã  åæ²¢, æ©åæè¾¼ãµã¤ãº(1-3æ³/ç´41 ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-22 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-1304604-9748648','VK-7V5A-H543',NULL,NULL,'admin',NULL,NULL,NULL,'','215-0011','ç¥å¥å·ç--å·å´å¸éº»çåºç¾åä¸2-19-3','å¾è¤çå­',NULL,'JPY--6980.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-19 20:00:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-1413122-1858203','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,'','395-0803','é·éç--é£¯ç°å¸é¼ä¸å±±ï¼ï¼ï¼âï¼','ã­ã¼ã½ã³é£¯ç°è¥¿é¼',NULL,'JPY--499.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-12 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-1625292-4124625','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,'','930-0088','å¯å±±ç--å¯å±±å¸è«è¨ªå·åï¼âï¼âï¼','éå­ å¹³',NULL,'JPY--399.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-27 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-1681430-1291016','M0-CFTC-91ME',NULL,NULL,'admin',NULL,NULL,NULL,'08030146500','300-2417','è¨åç--ã¤ãã°ã¿ããå¸å¯å£«è¦ã¶ä¸1-32-21','æ·±è°·æ³°ä»',NULL,'JPY--20780.00','SAHASAHA ã²ã¼ãã³ã°ãã§ã¢ ãªãã£ã¹ãã§ã¢ å¤æ©è½ ã²ã¼ã ç¨ãã§ã¢ ãªã¯ã©ã¤ãã³ã° ãã½ã³ã³ãã§ã¢ ãã¤ããã¯ ãããã¬ã¹ã è°çå¯¾ç­ ã©ã³ãã¼ãµãã¼ã ã²ãæãä»ã ã¬ã¶ã¼ (ãã¯ã¤ã)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-27 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-1692004-7649433','VK-7V5A-H543',NULL,NULL,'admin',NULL,NULL,NULL,'','141-0022','æ±äº¬é½--åå·åºæ±äºåç°5-9-22','å®®éå¯å­',NULL,'JPY--6980.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-22 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-1747514-0260642','Dustbin-sliver',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAH èµ¤å¤ç·ã»ã³ãµã¼å¼å¨èªåééå¼ã¹ãã³ã¬ã¹ãã¿ç®±ãè¿ã¥ãã ãã§èªåçã«éãããæ¸æ½ãã¤ã³ããªã¢ãå®¹é7ï¼¬ (ã·ã«ãã¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-29 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-1831961-8222222','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,'','822-1302','ç¦å²¡ç--ç°å·é¡ç³¸ç°çºå¤§ç','ç«¹ç°èå¤®',NULL,'JPY--399.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-27 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-1857835-6283009','FH-WZ2Q-OYJP',NULL,NULL,'admin',NULL,NULL,NULL,'0824281297','739-0147','åºå³¶ç--æ±åºå³¶å¸å«æ¬æ¾è¥¿','å¹³å²¡ççå­',NULL,'JPY--8260.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã,Mãµã¤ãºï¼5-7æ³/ç´66ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-14 20:00:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-1926622-3631849','pet tent-white big',NULL,NULL,'admin',NULL,NULL,NULL,'08065023774','195-0063','æ±äº¬é½--çºç°å¸éæ´¥ç°çº3535-2','é·è°·é¨èã',NULL,'JPY--4599.00','SAHASAHA ããã ãã³ã ãã£ãã¼ãã³ã ç¬å°å± ããããã¦ã¹ ãããã ãã£ãã¼ ãã·ã§ã³ ç¬ç¨ ç«ç¨ å°åç¬ ãã¯ã¤ãå°ãããã¯ãã·ã§ã³å¥å£²ã (60*60*70, ãã¯ã¤ã)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-21 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-1947445-7158240','6B-OYS1-RBC2',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ æ¡å¼µ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã ã¢ã«ããã¬ã¼ã  åæ²¢, ï¼³ãµã¤ãºï¼3-5æ³/ç´51 ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-24 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-2024231-0709426','SN-43IS-TWEP',NULL,NULL,'admin',NULL,NULL,NULL,'09069085472','616-8333','Kyoto-fu--Kyoutoshi Ukyouku','Andou Junpei',NULL,'JPY--9960.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ æ¡å¼µ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, Lãµã¤ãºï¼7æ³ä»¥ä¸/88ï¼æ¡å¼µæ102ï¼ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-15 20:00:02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-2153430-2770216','YI-E0XQ-KIDU',NULL,NULL,'admin',NULL,NULL,NULL,'09085548675','192-0045','æ±äº¬é½--å«çå­å¸å¤§åç°çº6ä¸ç®7-7','ä¸ç°é½å¤ª',NULL,'JPY--16000.00','åå¿èå¥é ç´æ¨äºè¡ ä¸­å½èå·äºè¡ ã±ã¼ã¹ä»','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-22 20:00:02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-2171501-8988660','HM-B5BT-1IWO',NULL,NULL,'admin',NULL,NULL,NULL,'0359762608','112-0013','æ±äº¬é½--æäº¬åºé³ç¾½','ä¼è¤ä¸èµ·',NULL,'JPY--7426.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, ï¼³ãµã¤ãºï¼3-5æ³/ç´51ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-22 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-2272802-4733467','52-EV2N-XBRO',NULL,NULL,'admin',NULL,NULL,NULL,'08061787832','572-0801','å¤§éªåº--å¯å±å·å¸','æ¥æ¥è£ä½³å­',NULL,'JPY--10260.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã ã¢ã«ããã¬ã¼ã  åæ²¢, Mãµã¤ãºï¼5-7æ³/ç´66 ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-07 20:00:02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-2273020-1923046','YI-E0XQ-KIDU',NULL,NULL,'admin',NULL,NULL,NULL,'07040745678','136-0072','æ±äº¬é½--æ±æ±åºå¤§å³¶','æ±å»ºæ¥',NULL,'JPY--16000.00','åå¿èå¥é ç´æ¨äºè¡ ä¸­å½èå·äºè¡ ã±ã¼ã¹ä»','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-01 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-2689454-8251035','HM-B5BT-1IWO',NULL,NULL,'admin',NULL,NULL,NULL,'09010855334','651-2102','åµåº«ç--ç¥æ¸å¸è¥¿åºå­¦åæ±çº','ä¸ç°ä¿å¹¸',NULL,'JPY--7660.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ æ¡å¼µ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, ï¼³ãµã¤ãºï¼3-5æ³/47ï¼æ¡å¼µæ56ï¼ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-10 16:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-2755749-8064646','FH-WZ2Q-OYJP',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã,Mãµã¤ãºï¼5-7æ³/ç´66ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-21 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-2792897-2149416','FH-WZ2Q-OYJP',NULL,NULL,'admin',NULL,NULL,NULL,'0118963990','004-0041','åæµ·é--æ­å¹å¸åå¥åºå¤§è°·å°æ±7ä¸ç®3','æ°¸äºãéä½³',NULL,'JPY--8960.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ æ¡å¼µ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, Mãµã¤ãºï¼5-7æ³/61ï¼æ¡å¼µæ72ï¼ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-11-17 16:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('250-2818787-8350246','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,'','252-1115','ç¥å¥å·ç--ç¶¾ç¬å¸è½åå2â12â55','ç³æ¾¤æå¹¸',NULL,'JPY--798.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-29 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-2850337-1543042','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,'','720-1131','åºå³¶ç--ç¦å±±å¸ é§å®¶çºä¸è½å337-1','é¨æ£®ãå­äº®',NULL,'JPY--499.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-07 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-2852067-5576659','O8-1VYB-PZQ4',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, æ©åæè¾¼ãµã¤ãº(1-3æ³/ç´41ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-13 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-2938250-2291802','P3-AXD1-32XQ',NULL,NULL,'admin',NULL,NULL,NULL,'','354-0046','å¼çç--å¥éé¡ä¸è³çºç«¹éæ²¢æ±4-6','QA231 ID: QA231-W0052',NULL,'JPY--999.00','SAHASAHA ã¯ã³ã¿ãããã³ã ãã³ã 3ï½5äººç¨ ã¯ã³ã¿ãã 2WAY ãã³ã è¨­å¶ç°¡å é²ç½ç¨ ã­ã£ã³ãç¨å æ¥æ°´å å·¥ ç´«å¤ï¿½','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-01 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-2962827-4248626','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,'','563-0104','å¤§éªåº--è±è½é¡ãè±è½çºãåé¢¨å°','è¶å°¾ãæç¢',NULL,'JPY--798.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-01 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-3028863-7282266','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,'','799-1101','æåªç--è¥¿æ¡å¸å°æ¾çºæ°å±æ·','ç½ç³äº®å¾³',NULL,'JPY--399.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-29 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-3106865-4843020','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,'','418-0114','éå²¡ç--å¯å£«å®®å¸ä¸æ¡833-2','ç³å·ãå¹¸ä¹',NULL,'JPY--399.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-28 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-3132899-5032642','SN-43IS-TWEP',NULL,NULL,'admin',NULL,NULL,NULL,'08055093145','308-0827','è¨åç--ç­è¥¿å¸å¸éè¾º','ã¯ãã',NULL,'JPY--9960.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, Lãµã¤ãºï¼7æ³ä»¥ä¸/ç´86 ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-03-04 18:00:06',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-3143588-4896645','5P-FC8V-S16L',NULL,NULL,'admin',NULL,NULL,NULL,'08016998931','181-0011','æ±äº¬é½--ä¸é·¹å¸äºå£','æè¤ç¶¾ä¹',NULL,'JPY--7960.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ æ¡å¼µ ã¸ããã¼ å¨ãµã¤ãº æã (ãã©ãã¯, Mãµã¤ãºï¼5-7æ³/61ï¼æ¡å¼µæ72ï¼ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-13 16:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-3293459-1551836','90-K4KW-NX3B',NULL,NULL,'admin',NULL,NULL,NULL,'09072044256','878-0025','å¤§åç--ç«¹ç°å¸æç°å67','ä½è¤åä»£ç¾',NULL,'JPY--11890.00','SAHASAHA ãããã ã¹ã¼ãã±ã¼ã¹ ä¾¿æºã±ã¼ã¹ ã»ãã ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ ãã³ã¯ã¨ãã«ã¼ (ä¾¿æºã±ã¼ã¹+ã­ã£ãªã¼ã±ã¼ã¹, Lãµã¤ãºï¼7æ³ä»¥ä¸ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-27 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-3428249-9591825','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,'','370-3503','ç¾¤é¦¬ç--åç¾¤é¦¬é¡æ¦æ±æå¤§å­æ°äºï¼ï¼ï¼ï¼','ã­ã¼ã½ã³æ¦æ±æ°äºå',NULL,'JPY--998.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-06 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-3580674-9954220','0Y-K99Y-JMPZ',NULL,NULL,'admin',NULL,NULL,NULL,'','306-0053','è¨åç--å¤æ²³å¸ä¸­ç°','ç§åº­ããã',NULL,'JPY--10062.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-28 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-3646306-1634258','O8-1VYB-PZQ4',NULL,NULL,'admin',NULL,NULL,NULL,'03-3397-3736','167-0034','æ±äº¬é½--æä¸¦åºæ¡äº','å±±ç° åå­',NULL,'JPY--6960.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, æ©åæè¾¼ãµã¤ãº(1-3æ³/ç´41ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-25 20:00:06',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-3695495-0985461','FA-BWNY-IM4Z',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã·ã«ãã¼ ã¢ã«ããã¬ã¼ã  åæ²¢, Mãµã¤ãºï¼5-7æ³/ç´66 ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-21 20:00:02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-3791972-7807842','3Q-YKCK-C573',NULL,NULL,'admin',NULL,NULL,NULL,'09081354586','514-0101','ä¸éç--æ´¥å¸ç½å¡çº588-1','ç¬ äºéº»å¸',NULL,'JPY--8960.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ æ¡å¼µ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã ã¢ã«ããã¬ã¼ã  åæ²¢, æ©åæè¾¼ãµã¤ãº(1-3æ³/ç´41 ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-03-04 18:00:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-3870554-3980650','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-26 20:00:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-4069068-5451849','O2-R8WF-BJFL',NULL,NULL,'admin',NULL,NULL,NULL,'07052615332','577-0808','å¤§éªåº--æ±å¤§éªå¸æ¨ªæ²¼çº3-6-4','å°¾å·é²ä¸',NULL,'JPY--3680.00','SAHASAHA ã¯ã³ã¿ãããã³ã ãã³ã 1-2äººç¨ è¨­å¶ç°¡å é²ç½ç¨ ã­ã£ã³ãç¨å æ¥æ°´å å·¥ ç´«å¤ç·é²æ­¢ ç»å±± æãããã¿ é²æ°´ éæ°æ§ ã¢ã¦ããã¢ 210 * 140 * 110cm ã°ãªã¼ã³ åç´ã±ã¼ã¹ æ¥æ¬èªèª¬ææ¸ä»ã','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-12 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-4272132-0109445','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,'','369-1104','å¼çç--æ·±è°·å¸èæ²¼22-11','å°é¢ãä¿®',NULL,'JPY--699.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','6',NULL,NULL,NULL,NULL,NULL,1,'user01','2018-11-03 20:00:04',NULL,4,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('250-4406703-1990250','SF-0LCQ-50C6',NULL,NULL,'admin',NULL,NULL,NULL,'','654-0053','åµåº«ç--ç¥æ¸å¸é ç£¨åºå¤©ç¥çº1ä¸ç®1-19','æåç±å®',NULL,'JPY--699.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ãã©ãã¯)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-02 12:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('250-4597671-3003030','FH-WZ2Q-OYJP',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-22 20:00:06',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-4967053-4442267','LW-5DP8-UO3G',NULL,NULL,'admin',NULL,NULL,NULL,'09022232138','292-0826','åèç--æ¨æ´æ´¥å¸çæ²¢å4-12-31','ä½è¤ãç¥å­',NULL,'JPY--1544.00','SAHASAHA LEDé»é£¾ çå½¢ è¿½å ç¨ãã«ã©ã¼ï¼çåï¼','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-10 16:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-4968548-6111024','M0-CFTC-91ME',NULL,NULL,'admin',NULL,NULL,NULL,'0664534920','553-0002','å¤§éªåº--å¤§éªå¸ç¦å³¶åºé·ºæ´²','æ¸¡è¾ºåé¶´',NULL,'JPY--20780.00','SAHASAHA ã²ã¼ãã³ã°ãã§ã¢ ãªãã£ã¹ãã§ã¢ å¤æ©è½ ã²ã¼ã ç¨ãã§ã¢ ãªã¯ã©ã¤ãã³ã° ãã½ã³ã³ãã§ã¢ ãã¤ããã¯ ãããã¬ã¹ã è°çå¯¾ç­ ã©ã³ãã¼ãµãã¼ã ã²ãæãä»ã ã¬ã¶ã¼ (ãã¯ã¤ã)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-24 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-4998639-8519858','O8-1VYB-PZQ4',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, æ©åæè¾¼ãµã¤ãº(1-3æ³/ç´41ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-31 20:00:02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-5066757-2780600','YM-RFHA-8TVU',NULL,NULL,'admin',NULL,NULL,NULL,'09080461754','193-0832','æ±äº¬é½--å«çå­å¸æ£ç°çº2-67-13','æ¯æ çç±ç¾',NULL,'JPY--999.00','SAHASAHA çä»ã çç© ã¯ãªãã è¡¿æ­¢ãã¯ãªãã åè£ã¯ãªãã è± 3åã»ãã','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-24 20:00:02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-5072775-3391862','VK-7V5A-H543',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, æ©åæè¾¼ãµã¤ãº(1-3æ³/ç´41ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-19 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-5147204-2115045','ED-0UIY-40IQ',NULL,NULL,'admin',NULL,NULL,NULL,'','656-0016','åµåº«ç--æ´²æ¬å¸ä¸åè³384','æ§æ¬æµ©å­',NULL,'JPY--8260.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-11 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-5184186-5006220','YM-RFHA-8TVU',NULL,NULL,'admin',NULL,NULL,NULL,'09080461754','193-0832','æ±äº¬é½--å«çå­å¸æ£ç°çº2-67-13','æ¯æ çç±ç¾',NULL,'JPY--999.00','SAHASAHA çä»ã çç© ã¯ãªãã è¡¿æ­¢ãã¯ãªãã åè£ã¯ãªãã è± 3åã»ãã','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-24 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-5300912-6400654','SN-43IS-TWEP',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'JPY--19920.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, Lãµã¤ãºï¼7æ³ä»¥ä¸/ç´86 ãªããã«ï¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-24 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-5368518-8749415','HM-B5BT-1IWO',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, ï¼³ãµã¤ãºï¼3-5æ³/ç´51ãªããã«ï¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-30 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-5374794-7847017','YI-E0XQ-KIDU',NULL,NULL,'admin',NULL,NULL,NULL,'08045144593','966-0002','ç¦å³¶ç--åå¤æ¹å¸å²©æçºå®®æ´¥ä¸­ç°ä»2810','ç¢å¹åªæ',NULL,'JPY--16000.00','åå¿èå¥é ç´æ¨äºè¡ ä¸­å½èå·äºè¡ ã±ã¼ã¹ä»','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-04 20:00:02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-5531926-7679062','0Y-K99Y-JMPZ',NULL,NULL,'admin',NULL,NULL,NULL,'','123-0857','æ±äº¬é½--è¶³ç«åºæ¬æ¨åçº9-20','ææ¨ç¾ç©',NULL,'JPY--9960.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-06 20:00:02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-5541884-7042255','tent-blue',NULL,NULL,'admin',NULL,NULL,NULL,'08029527199','910-0373','ç¦äºç--åäºå¸ä¸¸å²¡çºé«æ³21ä¸4','æ£®é·åé¦å­',NULL,'JPY--3690.00','SAHASAHA ã­ããºãã¬ã¤ãã³ãå­ä¾ç¨ å¯æããã³ããã«ã¼6è§ã­ããºãã³ã ã­ããºãã¦ã¹ å±åè£é£¾ å®¤å å®¤å¤ ã®ãã ããªã³ã»ã¹ ãã«ãã«LEDé»çä»ã (ãã«ã¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-11-24 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('250-5570148-2596600','5U-Y2SK-VDC5',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¯ã³ã¿ãããã³ã ãã³ã 3ï½5äººç¨ ã¯ã³ã¿ãã 2WAY ãã³ã 3WAY ãã³ã è¨­å¶ç°¡å é²ç½ç¨ ã­ã£ã³ãç¨å æ¥æ°´å å·¥ ç´«å¤ç·é²æ­¢ ç»å±± æãããã¿ é²æ°´ éæ°æ§ ã¢ã¦ããã¢ 240*210*135cm (ãã«ã¼, 3ï½5äººç¨)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-01 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-5617783-4579050','UW-NJCD-KF9N',NULL,NULL,'admin',NULL,NULL,NULL,'','838-0228','ç¦å²¡ç--æåé¡ç­åçºäº','MISAKI HIRAYAMA',NULL,'JPY--8200.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-08 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-5665139-3735804','SN-43IS-TWEP',NULL,NULL,'admin',NULL,NULL,NULL,'08016671315','036-8225','éæ£®ç--å¼åå¸è¥¿ã±ä¸çº','ã¦ã¹ã¤ããã',NULL,'JPY--9960.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, Lãµã¤ãºï¼7æ³ä»¥ä¸/ç´86 ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-24 20:00:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-5679208-7810211','0Y-K99Y-JMPZ',NULL,NULL,'admin',NULL,NULL,NULL,'','231-0822','ç¥å¥å·ç--æ¨ªæµå¸ä¸­åºæ¬ç§åçº13-1','è²åæ¢¨å¥',NULL,'JPY--9462.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-05 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-5697921-3399851','6B-OYS1-RBC2',NULL,NULL,'admin',NULL,NULL,NULL,'0567675377','498-0017','æç¥ç--å¼¥å¯å¸åã±é çºè¥¿åå©222-1','æ¡å±± çç±ç¾',NULL,'JPY--9326.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ æ¡å¼µ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã ã¢ã«ããã¬ã¼ã  åæ²¢, ï¼³ãµã¤ãºï¼3-5æ³/ç´51 ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-24 20:00:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-5804876-1825453','O8-1VYB-PZQ4',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, æ©åæè¾¼ãµã¤ãº(1-3æ³/ç´41ãªããã«ï¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-07 20:00:02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-5821068-8551063','O8-1VYB-PZQ4',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, æ©åæè¾¼ãµã¤ãº(1-3æ³/ç´41ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-31 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-5896269-9031037','FH-WZ2Q-OYJP',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã,Mãµã¤ãºï¼5-7æ³/ç´66ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-03-06 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-6057339-2254259','VK-7V5A-H543',NULL,NULL,'admin',NULL,NULL,NULL,'','350-1142','å¼çç--å·è¶å¸è¤é','å²¡ç°åæ¶',NULL,'JPY--6631.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-03 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-6095174-7992669','WG-0DAJ-6HFY',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã·ã«ãã¼, Mãµã¤ãºï¼5-7æ³/ç´66ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-08 20:00:02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-6299272-8085405','O8-1VYB-PZQ4',NULL,NULL,'admin',NULL,NULL,NULL,'08013517290','346-0012','å¼çç--ä¹åå¸æ å141-85','åç çå',NULL,'JPY--6960.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, æ©åæè¾¼ãµã¤ãº(1-3æ³/ç´41ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-06 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-6333928-5161441','SN-43IS-TWEP',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, Lãµã¤ãºï¼7æ³ä»¥ä¸/ç´86 ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-26 20:00:08',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-6429746-5664621','SN-43IS-TWEP',NULL,NULL,'admin',NULL,NULL,NULL,'09059718668','671-1524','åµåº«ç--æä¿é¡å¤ªå­çºæ±ä¿','å¤§æ£®ç§ä¸é',NULL,'JPY--9960.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, Lãµã¤ãºï¼7æ³ä»¥ä¸/ç´86 ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-25 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-6551013-8907868','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,'','567-0027','å¤§éªåº--è¨æ¨å¸è¥¿ç°ä¸­çº4-26','å°åç´å¸',NULL,'JPY--499.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-12 20:00:02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-6594978-5086264','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,'','509-7201','å²éç--æµé£å¸å¤§äºçºï¼ï¼ï¼ï¼âï¼ï¼ï¼','éäºãç¦å·±',NULL,'JPY--499.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-10 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-6798964-8698247','VK-7V5A-H543',NULL,NULL,'admin',NULL,NULL,NULL,'','345-0004','å¼çç--åèé£¾é¡ææ¸çº','MOMOE SUZUKI',NULL,'JPY--6980.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-07 20:00:02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-6854034-9015830','O8-1VYB-PZQ4',NULL,NULL,'admin',NULL,NULL,NULL,'07052621776','551-0002','å¤§éªåº--å¤§éªå¸å¤§æ­£åºä¸è»å®¶æ±1-13-22','å°å·ä¿å¥ç¾',NULL,'JPY--6960.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ æ¡å¼µ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, æ©åæè¾¼ãµã¤ãº(1-3æ³/33ï¼æ¡å¼µæ40ï¼ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-10 16:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-6980584-7888605','ED-0UIY-40IQ',NULL,NULL,'admin',NULL,NULL,NULL,'','400-0061','å±±æ¢¨ç--ç²åºå¸èå·1-10-25','ä»ææä¾',NULL,'JPY--8260.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-14 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-7128364-6323854','P3-AXD1-32XQ',NULL,NULL,'admin',NULL,NULL,NULL,'','174-0046','æ±äº¬é½--æ¿æ©åºè®æ ¹','å±±ä¸ æµ©',NULL,'JPY--1998.00','SAHASAHA ã¯ã³ã¿ãããã³ã ãã³ã 3ï½5äººç¨ ã¯ã³ã¿ãã 2WAY ãã³ã è¨­å¶ç°¡å é²ç½ç¨ ã­ã£ã³ãç¨å æ¥æ°´å å·¥ ç´«å¤ï¿½','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-01 20:00:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-7160397-0371015','UW-NJCD-KF9N',NULL,NULL,'admin',NULL,NULL,NULL,'','514-2222','ä¸éç--æ´¥å¸è±ãä¸','èå·éº»ä¾',NULL,'JPY--7660.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-23 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-7330565-3362219','FH-WZ2Q-OYJP',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ æ¡å¼µ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, Mãµã¤ãºï¼5-7æ³/61ï¼æ¡å¼µæ72ï¼ãªããã«ï¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-11-18 18:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('250-7576531-6543841','FH-WZ2Q-OYJP',NULL,NULL,'admin',NULL,NULL,NULL,'03-3659-3244','125-0052','æ±äº¬é½--èé£¾åºæ´å6-28-3','åç°ãç§ç¾',NULL,'JPY--8260.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã,Mãµã¤ãºï¼5-7æ³/ç´66ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-18 20:00:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-7771372-6941408','52-EV2N-XBRO',NULL,NULL,'admin',NULL,NULL,NULL,'09067773127','113-0034','æ±äº¬é½--æäº¬åºæ¹¯å³¶','å è¤ æ¶å­',NULL,'JPY--10260.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-05 20:00:46',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-7993048-9004648','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,'','509-6102','å²éç--çæµªå¸ç¨²æ´¥çºè©å1661','æ¨ªç°ãèå¼',NULL,'JPY--399.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-25 20:00:02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-8066304-6217458','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,'','245-0046','ç¥å¥å·ç--ç¥å¥å·çæ¨ªæµå¸æ³åºåæ³çº5733-1','hirotakakoyama',NULL,'JPY--399.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-02 20:00:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-8192428-5134234','P3-AXD1-32XQ',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¯ã³ã¿ãããã³ã ãã³ã 3ï½5äººç¨ ã¯ã³ã¿ãã 2WAY ãã³ã è¨­å¶ç°¡å é²ç½ç¨ ã­ã£ã³ãç¨å æ¥æ°´å å·¥ ç´«å¤ç·é²æ­¢ ç»å±± æãããã¿ é²æ°´ éæ°æ§ ã¢ã¦ããã¢ 240 * 210 * 135cm','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-30 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-8285550-8842220','HM-B5BT-1IWO',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, ï¼³ãµã¤ãºï¼3-5æ³/ç´51ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-21 20:00:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-8383949-9011010','O8-1VYB-PZQ4',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, æ©åæè¾¼ãµã¤ãº(1-3æ³/ç´41ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-25 20:00:02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-8519327-4115064','FH-WZ2Q-OYJP',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ æ¡å¼µ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, Mãµã¤ãºï¼5-7æ³/61ï¼æ¡å¼µæ72ï¼ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-11-18 18:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('250-8622060-7871055','UN-CC16-9Q87',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'JPY--6960.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã·ã«ãã¼, æ©åæè¾¼ãµã¤ãº(1-3æ³/ç´41ãªããã«ï¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-23 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-8645620-8303032','VK-7V5A-H543',NULL,NULL,'admin',NULL,NULL,NULL,'','251-0035','ç¥å¥å·ç--è¤æ²¢å¸çç¬æµ·å²¸3-24-22-504','åå®® å½©ä½³',NULL,'JPY--6631.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-06 20:00:02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-8907875-2716665','1J-W6A4-NHO1',NULL,NULL,'admin',NULL,NULL,NULL,'0593895976','513-0826','ä¸éç--é´é¹¿å¸ä½åï¼ï¼ï¼ï¼ï¼ï¼','å¨åå',NULL,'JPY--8960.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ãã©ãã¯ ã¢ã«ããã¬ã¼ã  åæ²¢, æ©åæè¾¼ãµã¤ãº(1-3æ³/ç´41 ãªããã«ï¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-21 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-8946061-0571015','ED-0UIY-40IQ',NULL,NULL,'admin',NULL,NULL,NULL,'','311-2104','è¨åç--é¾ç°å¸æ¢¶å±±467','é£¯å²¡å¤ç©',NULL,'JPY--8260.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-12 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-9023857-8880641','SN-43IS-TWEP',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, Lãµã¤ãºï¼7æ³ä»¥ä¸/ç´86 ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-27 20:00:06',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-9671533-6936646','5U-Y2SK-VDC5',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¯ã³ã¿ãããã³ã ãã³ã 3ï½5äººç¨ ã¯ã³ã¿ãã 2WAY ãã³ã 3WAY ãã³ã è¨­å¶ç°¡å é²ç½ç¨ ã­ã£ã³ãç¨å æ¥æ°´å å·¥ ç´«å¤ç·é²æ­¢ ç»å±± æãããã¿ é²æ°´ éæ°æ§ ã¢ã¦ããã¢ 240*210*135cm (ãã«ã¼, 3ï½5äººç¨)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-10 16:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-9673661-9693420','SF-0LCQ-50C6',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ãã©ãã¯)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-02 12:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('250-9689348-1694204','ED-0UIY-40IQ',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã,Mãµã¤ãºï¼5-7æ³/ç´66ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-10 20:00:02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('250-9925596-8875841','HM-B5BT-1IWO',NULL,NULL,'admin',NULL,NULL,NULL,'076-288-7481','929-0345','ç³å·ç--æ²³åé¡æ´¥å¹¡çºå­å¤ªç°ã¯-40-2','æåãåª',NULL,'JPY--7660.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, ï¼³ãµã¤ãºï¼3-5æ³/ç´51ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-03-02 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-0011755-5197423','ED-0UIY-40IQ',NULL,NULL,'admin',NULL,NULL,NULL,'','561-0844','å¤§éªåº--è±ä¸­å¸å©åè¥¿','æ©æ¬èã',NULL,'JPY--8760.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-13 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-0015281-1582274','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,'','561-0812','å¤§éªåº--è±ä¸­å¸åæ¡çº2-9-20','å²©ç°æ°æ',NULL,'JPY--399.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-29 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-0031321-3180637','52-EV2N-XBRO',NULL,NULL,'admin',NULL,NULL,NULL,'08067755494','197-0827','æ±äº¬é½--ãããéå¸æ²¹å¹³175-1','ä½è¤ããç¾',NULL,'JPY--10260.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã ã¢ã«ããã¬ã¼ã  åæ²¢, Mãµã¤ãºï¼5-7æ³/ç´66 ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-22 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-0037106-3676668','VK-7V5A-H543',NULL,NULL,'admin',NULL,NULL,NULL,'','957-0067','æ°æ½ç--æ°çºç°å¸ä¸­æ½æ ¹çº','ç³å±±æ´è',NULL,'JPY--6980.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-03-05 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-0116214-5520657','ED-0UIY-40IQ',NULL,NULL,'admin',NULL,NULL,NULL,'','391-0011','é·éç--èéå¸çå·ï¼ï¼ï¼ï¼âï¼','å°æ²³åéæµ',NULL,'JPY--8260.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-04 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-0358769-5996665','O2-R8WF-BJFL',NULL,NULL,'admin',NULL,NULL,NULL,'0667881198','577-0053','å¤§éªåº--æ±å¤§éªå¸é«äºç°23-20','é³¥ç¾½éå¤«',NULL,'JPY--3680.00','SAHASAHA ã¯ã³ã¿ãããã³ã ãã³ã 1-2äººç¨ è¨­å¶ç°¡å é²ç½ç¨ ã­ã£ã³ãç¨å æ¥æ°´å å·¥ ç´«å¤ç·é²æ­¢ ç»å±± æãããã¿ é²æ°´ éæ°æ§ ã¢ã¦ããã¢ 210 * 140 * 110cm ã°ãªã¼ã³ åç´ã±ã¼ã¹ æ¥æ¬èªèª¬ææ¸ä»ã','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-25 20:00:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-0592636-1955834','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,'','617-0856','äº¬é½åº--é·å²¡äº¬å¸éã±å','æ¸ç°ãè«­',NULL,'JPY--499.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-13 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-0660401-3667017','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,'','870-1123','å¤§åç--å¤§åå¸å¤§å­å¯ç°ï¼ï¼ï¼ï¼çªå°ã®ï¼','äºäºåå½¦',NULL,'JPY--399.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-03 20:00:02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-0664340-3492612','UO-PCEL-6KJ8',NULL,NULL,'admin',NULL,NULL,NULL,'09093496169','264-0002','åèç--åèå¸è¥èåºååå°æ±','éæ´¥',NULL,'JPY--7660.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ãã©ãã¯,ï¼³ãµã¤ãºï¼3-5æ³/ç´51ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-24 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-0670897-2872627','VK-7V5A-H543',NULL,NULL,'admin',NULL,NULL,NULL,'','335-0034','å¼çç--æ¸ç°å¸ç¬¹ç®1-19-46','å®®æ¾¤åç¹',NULL,'JPY--6631.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-28 20:00:02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-1084006-4235864','O8-1VYB-PZQ4',NULL,NULL,'admin',NULL,NULL,NULL,'08042574108','922-0106','ç³å·ç--å è³å¸å±±ä¸­æ¸©æ³ä¸åçº1-176','æ¼åæå­',NULL,'JPY--6960.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, æ©åæè¾¼ãµã¤ãº(1-3æ³/ç´41ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-07 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-1109570-8187840','ED-0UIY-40IQ',NULL,NULL,'admin',NULL,NULL,NULL,'','700-0923','å²¡å±±ç--å²¡å±±å¸ååºå¥¥ç°æ¬çº22-10','å äº«å­',NULL,'JPY--8260.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-08 20:00:06',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-1326458-9806241','5W-P3L6-0PBG',NULL,NULL,'admin',NULL,NULL,NULL,'','610-0101','äº¬é½åº--åé½å¸å¹³å·æ¨ªéï¼âï¼ï¼','å¥¥å±±ãéæ­',NULL,'JPY--1980.00','SAHASAHA ãã³ã ä¸äººç¨ ã³ã³ãã¯ã è¿·å½©æ ã­ã£ã³ããã³ã ã½ã­ãã³ã å°åãã³ã é²ç½ ç·æ¥ ã­ã£ã³ãç¨å æ¥æ°´ï¿½','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-11-17 16:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('503-1499764-8513449','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,'','940-1151','æ°æ½ç--é·å²¡å¸ä¸åï¼âï¼âï¼ï¼','ã­ã¼ã½ã³é·å²¡ä¸åä¸ä¸ç®',NULL,'JPY--1197.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-02 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-1513947-0529400','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,'','274-0805','åèç--è¹æ©å¸äºåæ±6-21-26-103','ä¸­å±æ·ä½ä¸',NULL,'JPY--998.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-11 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-1520292-4949426','FH-WZ2Q-OYJP',NULL,NULL,'admin',NULL,NULL,NULL,'0357028374','222-0026','ç¥å¥å·ç--æ¨ªæµå¸æ¸¯ååºç¯ åçº959','å¿ç° æ çå­',NULL,'JPY--8260.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã,Mãµã¤ãºï¼5-7æ³/ç´66ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-21 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-1644843-7221406','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-31 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-1654472-6887867','UW-NJCD-KF9N',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, ï¼³ãµã¤ãºï¼3-5æ³/ç´51ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-20 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-1865323-6505432','UN-CC16-9Q87',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã·ã«ãã¼, æ©åæè¾¼ãµã¤ãº(1-3æ³/ç´41ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-13 20:00:07',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-1944337-0977408','O8-1VYB-PZQ4',NULL,NULL,'admin',NULL,NULL,NULL,'03â3726â3231','145-0064','æ±äº¬é½--å¤§ç°åºä¸æ± å°ï¼âï¼ï¼âï¼ï¼','éå­ãæµå©è¯',NULL,'JPY--6960.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, æ©åæè¾¼ãµã¤ãº(1-3æ³/ç´41ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-06 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-2190056-6445404','A4-TWLW-BYFK',NULL,NULL,'admin',NULL,NULL,NULL,'','271-0068','åèç--æ¾æ¸å¸å¤ã±å´4ä¸ç®3542ã®8','å¼µãæ±',NULL,'JPY--2980.00','SAHASAHA ã¯ã³ã¿ãããã³ã ãã³ã 3ï½5äººç¨ ã¯ã³ã¿ãã 3WAY ãã³ã è¨­å¶ç°¡å é²ç½ç¨ ã­ã£ã³ãç¨å æ¥æ°´å å·¥ ç´«å¤ï¿½','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-11-24 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('503-2222051-8462215','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-04 20:00:02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-2327802-6720638','P3-AXD1-32XQ',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¯ã³ã¿ãããã³ã ãã³ã 3ï½5äººç¨ ã¯ã³ã¿ãã 2WAY ãã³ã è¨­å¶ç°¡å é²ç½ç¨ ã­ã£ã³ãç¨å æ¥æ°´å å·¥ ç´«å¤ç·é²æ­¢ ç»å±± æãããã¿ é²æ°´ éæ°æ§ ã¢ã¦ããã¢ 240 * 210 * 135cm','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-31 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-2422076-1994245','Dustbin-white',NULL,NULL,'admin',NULL,NULL,NULL,'0364526582','107-0062','æ±äº¬é½--æ¸¯åºåéå±±','å¾³å·ç§é',NULL,'JPY--4900.00','SAHASAH èµ¤å¤ç·ã»ã³ãµã¼å¼å¨èªåééå¼ã¹ãã³ã¬ã¹ãã¿ç®±ãè¿ã¥ãã ãã§èªåçã«éãããæ¸æ½ãã¤ã³ããªã¢ãå®¹é7ï¼¬ (ãã¯ã¤ã)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-03-06 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-2469758-3313450','O2-R8WF-BJFL',NULL,NULL,'admin',NULL,NULL,NULL,'0928077805','819-0166','ç¦å²¡ç--ç¦å²¡å¸è¥¿åºæ¨ªæµï¼ä¸ç®ï¼ï¼ï¼ï¼ï¼','å·è°·ãæå',NULL,'JPY--7360.00','SAHASAHA ã¯ã³ã¿ãããã³ã ãã³ã 1-2äººç¨ è¨­å¶ç°¡å é²ç½ç¨ ã­ã£ã³ãç¨å æ¥æ°´å å·¥ ç´«å¤ç·é²æ­¢ ç»å±± æãããã¿ é²æ°´ éæ°æ§ ã¢ã¦ããã¢ 210 * 140 * 110cm ã°ãªã¼ã³ åç´ã±ã¼ã¹ æ¥æ¬èªèª¬ææ¸ä»ã','2',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-05 10:00:02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-2586776-0105444','HM-B5BT-1IWO',NULL,NULL,'admin',NULL,NULL,NULL,'08067743030','245-0053','ç¥å¥å·ç--æ¨ªæµå¸æ¸å¡åºä¸ç¢é¨çº','å¥è¯å²¡ç¾æ¡',NULL,'JPY--7660.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, ï¼³ãµã¤ãºï¼3-5æ³/ç´51ãªããã«ï¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-28 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-2672774-1766211','UW-NJCD-KF9N',NULL,NULL,'admin',NULL,NULL,NULL,'','252-0244','ç¥å¥å·ç--ç¸æ¨¡åå¸ä¸­å¤®åºç°å','é·ç°æ¡',NULL,'JPY--8160.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-14 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-2673662-8679000','SN-43IS-TWEP',NULL,NULL,'admin',NULL,NULL,NULL,'08011292074','114-0012','æ±äº¬é½--ååºç°ç«¯æ°çº2-18-9-803','å°éå¯ºèå­',NULL,'JPY--9960.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, Lãµã¤ãºï¼7æ³ä»¥ä¸/ç´86 ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-27 20:00:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-2772704-1546255','ED-0UIY-40IQ',NULL,NULL,'admin',NULL,NULL,NULL,'','145-0063','æ±äº¬é½--å¤§ç°åºååæ','å¤§æ©æ ',NULL,'JPY--7847.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-04 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-2773867-3534269','5W-P3L6-0PBG',NULL,NULL,'admin',NULL,NULL,NULL,'','350-1236','å¼çç--æ¥é«å¸å¤§å­ç¿ç°å­ç¥æ41-1','ã¤ããéè¼¸å¶æ¥­æ æ¥é«æ­¦èµå°ã»ã³ã¿',NULL,'JPY--1980.00','SAHASAHA ãã³ã ä¸äººç¨ ã³ã³ãã¯ã è¿·å½©æ ã­ã£ã³ããã³ã ã½ã­ãã³ã å°åãã³ããé²ç½ãç·æ¥ã ã­ã£ã³ãç¨å ï¿½','7',NULL,NULL,NULL,NULL,NULL,1,'user01','2018-11-04 12:00:02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('503-2972583-7255833','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-05 20:00:47',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-3028946-8631826','0Y-K99Y-JMPZ',NULL,NULL,'admin',NULL,NULL,NULL,'','430-0855','éå²¡ç--æµæ¾å¸ååºæ¥å­çº1368-15','ææµ¦è',NULL,'JPY--9960.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-20 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-3173630-2937443','0Y-K99Y-JMPZ',NULL,NULL,'admin',NULL,NULL,NULL,'','384-2204','é·éç--ä½ä¹å¸åå212-6','ä½è¤ç²¾ç±³ãä½è¤åç´',NULL,'JPY--9960.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-18 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-3281983-9985452','SF-0LCQ-50C6',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ãã©ãã¯)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-08 12:00:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-3396390-0226201','52-EV2N-XBRO',NULL,NULL,'admin',NULL,NULL,NULL,'08019901027','169-0051','æ±äº¬é½--æ°å®¿åºè¥¿æ©ç¨²ç°','ç¦å®¶æ²ä¹å ',NULL,'JPY--10260.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ æ¡å¼µ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã TASã­ãã¯, Mãµã¤ãºï¼5-7æ³/66 ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-13 16:00:02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-3406351-7573446','SF-0LCQ-50C6',NULL,NULL,'admin',NULL,NULL,NULL,'','248-0007','ç¥å¥å·ç--éåå¸å¤§çº','ä¸­å¶åº·æ´',NULL,'JPY--699.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ãã©ãã¯)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-02 12:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('503-3491802-1795838','FH-WZ2Q-OYJP',NULL,NULL,'admin',NULL,NULL,NULL,'0785924303','651-1101','åµåº«ç--ç¥æ¸å¸ååºå±±ç°çºå°é¨å­æ±èè°·4','MICHIKOYOSHIDA',NULL,'JPY--8260.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã,Mãµã¤ãºï¼5-7æ³/ç´66ãªããã«ï¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-28 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-3599430-1699056','UW-NJCD-KF9N',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, ï¼³ãµã¤ãºï¼3-5æ³/ç´51ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-03-05 20:00:06',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-3742825-7929423','5F-TQLF-KUTC',NULL,NULL,'admin',NULL,NULL,NULL,'09039620645','264-0023','åèç--åèå¸è¥èåºè²å¡çº1102-6','å°è²«å½©',NULL,'JPY--2980.00','SAHASAHA éµç¤ãã¼ã¢ãã« (ã¡ã­ãã£ã¼ãã¢ã) ãããã ãã³ã¯ ãã«ã¼ 32éµ (ãã³ã¯)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-17 20:00:15',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-4158985-5446218','P3-AXD1-32XQ',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¯ã³ã¿ãããã³ã ãã³ã 3ï½5äººç¨ ã¯ã³ã¿ãã 2WAY ãã³ã è¨­å¶ç°¡å é²ç½ç¨ ã­ã£ã³ãç¨å æ¥æ°´å å·¥ ç´«å¤ç·é²æ­¢ ç»å±± æãããã¿ é²æ°´ éæ°æ§ ã¢ã¦ããã¢ 240 * 210 * 135cm','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-01 20:00:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-4320149-7863821','YI-E0XQ-KIDU',NULL,NULL,'admin',NULL,NULL,NULL,'08018339704','031-0801','éæ£®ç--å«æ¸å¸æ±é½','å°å· ãã',NULL,'JPY--16000.00','åå¿èå¥é ç´æ¨äºè¡ ä¸­å½èå·äºè¡ ã±ã¼ã¹ä»','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-13 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-4323553-3206256','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,'','243-0035','ç¥å¥å·ç--åæ¨å¸æç²','å°åè±é',NULL,'JPY--399.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-01 20:00:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-4445331-4003806','52-EV2N-XBRO',NULL,NULL,'admin',NULL,NULL,NULL,'08065776363','165-0035','æ±äº¬é½--ä¸­éåºç½é·º1ä¸ç®17çª2å·','å²¡æä¾é¦',NULL,'JPY--10260.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ æ¡å¼µ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã TASã­ãã¯, Mãµã¤ãºï¼5-7æ³/66 ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-13 16:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-4524669-4123860','UW-NJCD-KF9N',NULL,NULL,'admin',NULL,NULL,NULL,'','114-0001','æ±äº¬é½--ååºæ±åæ¡','ä¸åç¾æµ',NULL,'JPY--7777.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-05 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-4603189-6228651','5U-Y2SK-VDC5',NULL,NULL,'admin',NULL,NULL,NULL,'','970-1151','ç¦å³¶ç--ãããå¸å¥½éçºä¸å¥½éå­æµ¦ç°50-4','æ¸¡é¨ç§å¸',NULL,'JPY--999.00','SAHASAHA ã¯ã³ã¿ãããã³ã ãã³ã 3ï½5äººç¨ ã¯ã³ã¿ãã 2WAY ãã³ã 3WAY ãã³ã è¨­å¶ç°¡å é²ç½ç¨ ã­ã£ã³ãç¨å æ¥æ°´','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-01 20:00:07',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-4656601-9488641','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,'','807-0831','ç¦å²¡ç--åä¹å·å¸å«å¹¡è¥¿åºåæ¾604-6','ã«ã©ã¼ã³ã³ã»ãããå¤è³ãå¥ä¸é',NULL,'JPY--699.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-09 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-4761725-6815068','VK-7V5A-H543',NULL,NULL,'admin',NULL,NULL,NULL,'','708-0344','å²¡å±±ç--è«ç°é¡é¡éçºå157-3','å¡æ¬ç´«å¸',NULL,'JPY--6980.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-18 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-4918877-0569413','SN-43IS-TWEP',NULL,NULL,'admin',NULL,NULL,NULL,'09028357837','929-0217','ç³å·ç--ç½å±±å¸æ¹çº7â123','çå¨',NULL,'JPY--9960.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-25 20:00:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-4929037-6092615','O8-1VYB-PZQ4',NULL,NULL,'admin',NULL,NULL,NULL,'','661-0985','åµåº«ç--å°¼å´å¸åæ¸æ°´','åçç¾å­',NULL,'JPY--6761.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, æ©åæè¾¼ãµã¤ãº(1-3æ³/ç´41ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-24 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-5090908-4429427','SN-43IS-TWEP',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ æ¡å¼µ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, Lãµã¤ãºï¼7æ³ä»¥ä¸/88ï¼æ¡å¼µæ102ï¼ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-10 16:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-5231073-4751002','6N-OHKM-LVVR',NULL,NULL,'admin',NULL,NULL,NULL,'09023551244','639-2301','å¥è¯ç--å¾¡æå¸åçº','ä¸­å³¶ç¾é´',NULL,'JPY--5960.00','SAHASAHA é»ç±ãã¹ã ãã¼ããã¹ã åé»å¼ ãã¼ã¿ã¼ãã¹ã é»ç±ã¦ã§ã¢ é²å¯ç æãã ã¹ã­ã¼ ã´ã«ã ãã¤ã¯ éæ é»ç± ä½æ¥­ç é£ã ç·å¥³å¼ç¨ (4XL, ãã©ãã¯)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-30 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-5261651-1830207','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,'','411-0044','éå²¡ç--ä¸å³¶å¸å¾³å879-17','DESIGN IMPACT ç¦ç°åç§ã»ä¹ä¹',NULL,'JPY--499.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-09 20:00:02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-5368688-1946251','O8-1VYB-PZQ4',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, æ©åæè¾¼ãµã¤ãº(1-3æ³/ç´41ãªããã«ï¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-03-05 20:00:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-5442023-9693426','52-EV2N-XBRO',NULL,NULL,'admin',NULL,NULL,NULL,'09028874993','030-0943','éæ£®ç--éæ£®å¸å¹¸ç','è¥æ¾  èª ',NULL,'JPY--9260.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ æ¡å¼µ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã TASã­ãã¯, Mãµã¤ãºï¼5-7æ³/66 ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-11-18 18:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('503-5643550-3040601','UN-CC16-9Q87',NULL,NULL,'admin',NULL,NULL,NULL,'08054284161','251-0037','ç¥å¥å·ç--è¤æ²¢å¸éµ æ²¼æµ·å²¸7-10-9-1','æµ·é',NULL,'JPY--6960.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã·ã«ãã¼, æ©åæè¾¼ãµã¤ãº(1-3æ³/ç´41ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-11 20:00:02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-5654054-6102226','0Y-K99Y-JMPZ',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, Lãµã¤ãºï¼7æ³ä»¥ä¸/ç´86 ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-27 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-5694157-3240601','0Y-K99Y-JMPZ',NULL,NULL,'admin',NULL,NULL,NULL,'','860-0811','çæ¬ç--çæ¬å¸ä¸­å¤®åºæ¬è6-4-3','åç°å¥é£',NULL,'JPY--9960.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-22 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-5957106-9304622','ED-0UIY-40IQ',NULL,NULL,'admin',NULL,NULL,NULL,'','511-0912','ä¸éç--æ¡åå¸æè¦ã¶ä¸ï¼ä¸ç®ï¼ï¼ï¼','ç«¹åãä½³ç¾',NULL,'JPY--7847.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-04 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-6142566-5215041','ED-0UIY-40IQ',NULL,NULL,'admin',NULL,NULL,NULL,'','344-0004','å¼çç--æ¥æ¥é¨å¸çå³¶10-17','ç¦ç°èæ¼',NULL,'JPY--8260.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-08 20:00:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-6205444-2494232','EK-ON40-GKM5',NULL,NULL,'admin',NULL,NULL,NULL,'0473750004','272-0822','åèç--å¸å·å¸å®®ä¹ä¿ï¼âï¼ï¼âï¼ï¼','é´¨å·ç¾æ å­',NULL,'JPY--12960.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ TSAã­ãã¯ åé¡é¢ä»ä¸ã ã¢ã©ã¤ã³å å·¥ ã¢ã«ããã¬ã¼ã  ã¬ãã­ æè¡ åºå¼µ è»½é éé³ ãã¡ã¹ãã¼ã¬ã¹ æ©åæè¾¼å¯ (L, ã¢ã«ããã¬ã¼ã  -ã­ã¼ãºã´ã¼ã«ã)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-11 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-6748558-4599005','ED-0UIY-40IQ',NULL,NULL,'admin',NULL,NULL,NULL,'','132-0025','æ±äº¬é½--æ±æ¸å·åºæ¾æ±','å¶å´å°',NULL,'JPY--8260.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-21 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-6779533-4980665','M0-I6TZ-MSCV',NULL,NULL,'admin',NULL,NULL,NULL,'09032638987','574-0045','å¤§éªåº--å¤§æ±å¸å¤ªå­ç°1-9-10','ç³äºè«',NULL,'JPY--1620.00','SAHASAHA 2019 çä»ã çç© ã¯ãªãã è¡¿æ­¢ãã¯ãªãã åè£ã¯ãªãã æå­ è± 3è²ã»ãã (6åã»ãã, è±)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-27 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-7103872-5928666','ED-0UIY-40IQ',NULL,NULL,'admin',NULL,NULL,NULL,'','336-0026','å¼çç--ãããã¾å¸ååºè¾»8-17-1 502','æªå´åèµ·å­',NULL,'JPY--8260.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-11 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-7323177-9323849','GT-82OW-TXM4',NULL,NULL,'admin',NULL,NULL,NULL,'07014014613','252-0232','ç¥å¥å·ç--ç¸æ¨¡åå¸ä¸­å¤®åºç¢é¨3-7-4','ä¸¸å±±äº®å­',NULL,'JPY--7660.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã·ã«ãã¼, ï¼³ãµã¤ãºï¼3-5æ³/ç´51ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-10 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-7379171-2686214','UW-NJCD-KF9N',NULL,NULL,'admin',NULL,NULL,NULL,'','339-0032','å¼çç--ãããã¾å¸å²©æ§»åºåä¸æ°äº','ä½è¤è±',NULL,'JPY--8160.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-24 20:00:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-7384897-7616603','WG-0DAJ-6HFY',NULL,NULL,'admin',NULL,NULL,NULL,'09016796550','651-0072','åµåº«ç--ç¥æ¸å¸ä¸­å¤®åºèæµçº1-4-40','ä»åºæç¾',NULL,'JPY--8260.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã·ã«ãã¼, Mãµã¤ãºï¼5-7æ³/ç´66ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-16 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-7507675-6755853','0Y-K99Y-JMPZ',NULL,NULL,'admin',NULL,NULL,NULL,'','175-0094','æ±äº¬é½--æ¿æ©åºæå¢ï¼âï¼ï¼âï¼','é´æ¨è¬ä¸',NULL,'JPY--9960.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-23 20:00:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-7667909-5707068','FH-WZ2Q-OYJP',NULL,NULL,'admin',NULL,NULL,NULL,'03â3726â3231','145-0064','æ±äº¬é½--å¤§ç°åºä¸æ± å°ï¼âï¼ï¼âï¼ï¼','éå­ãæµå©è¯',NULL,'JPY--7960.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ æ¡å¼µ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, Mãµã¤ãºï¼5-7æ³/61ï¼æ¡å¼µæ72ï¼ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-26 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-7798366-4686205','VK-7V5A-H543',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, æ©åæè¾¼ãµã¤ãº(1-3æ³/ç´41ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-03-05 20:00:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-7837617-1083812','P3-AXD1-32XQ',NULL,NULL,'admin',NULL,NULL,NULL,'','700-0956','å²¡å±±ç--å²¡å±±å¸ååºå½æ°ç°356-43','åå³¶ç´äºº',NULL,'JPY--999.00','SAHASAHA ã¯ã³ã¿ãããã³ã ãã³ã 3ï½5äººç¨ ã¯ã³ã¿ãã 2WAY ãã³ã è¨­å¶ç°¡å é²ç½ç¨ ã­ã£ã³ãç¨å æ¥æ°´å å·¥ ç´«å¤ï¿½','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-01 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-8057603-5467849','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,'','880-0001','å®®å´ç--å®®å´å¸æ©éè¥¿','Crystal Pharaoh',NULL,'JPY--998.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-13 20:00:06',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-8302295-4396627','SN-43IS-TWEP',NULL,NULL,'admin',NULL,NULL,NULL,'07026598535','335-0016','å¼çç--æ¸ç°å¸ä¸å1-7-13 307','æ¸¡è¾º åªè±',NULL,'JPY--9960.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, Lãµã¤ãºï¼7æ³ä»¥ä¸/ç´86 ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-10 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-8363106-0263035','FH-WZ2Q-OYJP',NULL,NULL,'admin',NULL,NULL,NULL,'09028063165','213-0005','ç¥å¥å·ç--å·å´å¸é«æ´¥åºåè¦æ¹','å°ç«¿åè',NULL,'JPY--8260.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã,Mãµã¤ãºï¼5-7æ³/ç´66ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-18 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-8368115-0454217','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,'','675-1114','åµåº«ç--å å¤é¡ç¨²ç¾çºå½å®1162-1','å¤§è·¯ãåªå­',NULL,'JPY--399.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-03 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-8517352-0443063','ED-0UIY-40IQ',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã,Mãµã¤ãºï¼5-7æ³/ç´66ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-03-03 20:00:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-8612593-6731062','VK-7V5A-H543',NULL,NULL,'admin',NULL,NULL,NULL,'','144-0047','æ±äº¬é½--å¤§ç°åºè©ä¸­','ç«¹è±éº»ç±',NULL,'JPY--6980.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-21 20:00:02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-8713220-4269422','FH-WZ2Q-OYJP',NULL,NULL,'admin',NULL,NULL,NULL,'02019265192','610-0302','äº¬é½åº--ç¶´åé¡äºæçºäºæ','Supthone Saowanee',NULL,'JPY--8260.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã,Mãµã¤ãºï¼5-7æ³/ç´66ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-16 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-8879469-1176650','SN-43IS-TWEP',NULL,NULL,'admin',NULL,NULL,NULL,'0487260790','362-0059','å¼çç--ä¸å°¾å¸å¤§å­å¹³æ¹3136-5','å¥è¯ãç¾æ²»',NULL,'JPY--12940.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ æ¡å¼µ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, Lãµã¤ãºï¼7æ³ä»¥ä¸/88ï¼æ¡å¼µæ102ï¼ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-05 10:00:02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-9021736-3471028','ZQ-19XZ-EBKH',NULL,NULL,'admin',NULL,NULL,NULL,'09096486241','166-0001','æ±äº¬é½--æä¸¦åºé¿ä½è°·å3-28-19','å±±ä¸ç è',NULL,'JPY--13176.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ TSAã­ãã¯ åé¡é¢ä»ä¸ã ã¢ã©ã¤ã³å å·¥ ã¢ã«ããã¬ã¼ã  ã¬ãã­ æè¡ åºå¼µ è»½é éé³ ãã¡ã¹ãã¼ã¬ã¹ æ©åæè¾¼å¯ (XL, ã­ã£ãªã¼ã¸ããã¼-ã­ã¼ãºã´ã¼ã«ã)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-20 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-9150767-8818217','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,'','491-0201','Aichi-ken--Ichinomya shi','Manalang westy james',NULL,'JPY--499.00','SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-12 20:00:02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-9232299-8766262','UW-NJCD-KF9N',NULL,NULL,'admin',NULL,NULL,NULL,'','630-0131','å¥è¯ç--çé§å¸ä¸çº','è°· æ¥å¥',NULL,'JPY--7660.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-08 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-9309640-1601414','VK-7V5A-H543',NULL,NULL,'admin',NULL,NULL,NULL,'','891-0114','é¹¿åå³¶ç--é¹¿åå³¶å¸å°æ¾å','å¼å­ä¸¸è±ª',NULL,'JPY--6980.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-24 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-9418342-3886207','5U-Y2SK-VDC5',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¯ã³ã¿ãããã³ã ãã³ã 3ï½5äººç¨ ã¯ã³ã¿ãã 2WAY ãã³ã 3WAY ãã³ã è¨­å¶ç°¡å é²ç½ç¨ ã­ã£ã³ãç¨å æ¥æ°´å å·¥ ç´«å¤ç·é²æ­¢ ç»å±± æãããã¿ é²æ°´ éæ°æ§ ã¢ã¦ããã¢ 240*210*135cm (ãã«ã¼, 3ï½5äººç¨)','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-01 20:00:07',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-9469508-3912637','VK-7V5A-H543',NULL,NULL,'admin',NULL,NULL,NULL,'','244-0817','ç¥å¥å·ç--æ¨ªæµå¸æ¸å¡åºåç°çº 1623-6','å¤§åéå¥å­',NULL,'JPY--6631.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-01 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-9572745-1047861','ED-0UIY-40IQ',NULL,NULL,'admin',NULL,NULL,NULL,'','906-0103','æ²ç¸ç--å®®å¤å³¶å¸åè¾ºç¦é377ï½°7çªå°Eæ£202','é·æ¿±å§«æ­',NULL,'JPY--8260.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-05 20:00:02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-9647879-0807834','HM-B5BT-1IWO',NULL,NULL,'admin',NULL,NULL,NULL,'09090525453','573-0013','å¤§éªåº--ææ¹å¸æä¸4-7-11','åå´ãããæµ',NULL,'JPY--7660.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ æ¡å¼µ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, ï¼³ãµã¤ãºï¼3-5æ³/47ï¼æ¡å¼µæ56ï¼ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2018-12-19 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-9670522-0994216','TG-7U8Z-SBK2',NULL,NULL,'admin',NULL,NULL,NULL,'0927143005','810-0073','ç¦å²¡ç--ç¦å²¡å¸ä¸­å¤®åºèé¶´1-2-1','æ¥ä¸',NULL,'JPY--17999.00','ã­ã¯ã¿ã äºè¡ ã»ããã¼ãã±ã¼ã¹ãå¼ä»å± #73','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-14 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-9683178-6219855','VK-7V5A-H543',NULL,NULL,'admin',NULL,NULL,NULL,'','811-1311','ç¦å²¡ç--ååºæ¨ªæäºä¸ç®36-1','å±±ååç´è',NULL,'JPY--7171.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSA','0',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-01 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-9737261-1274261','5J-EU14-VO30',NULL,NULL,'admin',NULL,NULL,NULL,'0453241805','220-0051','ç¥å¥å·ç--æ¨ªæµå¸è¥¿åºä¸­å¤®','åå®®ã¯ãª',NULL,'JPY--13870.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ TSAã­ãã¯ åé¡é¢ä»ä¸ã ã¢ã©ã¤ã³å å·¥ ã¢ã«ããã¬ã¼ã  ã¬ãã­ æè¡ åºå¼µ è»½é éé³ ãã¡ã¹ãã¼ã¬ã¹ æ©åæè¾¼å¯ (XL, ã¢ã«ããã¬ã¼ã  -ã­ã¼ãºã´ã¼ã«ã)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-23 20:00:05',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-9868557-1343822','52-EV2N-XBRO',NULL,NULL,'admin',NULL,NULL,NULL,'0487348789','344-0023','å¼çç--æ¥æ¥é¨å¸å¤§æ89','å®®æ¬ åå°',NULL,'JPY--10260.00','SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã ã¢ã«ããã¬ã¼ã  åæ²¢, Mãµã¤ãºï¼5-7æ³/ç´66 ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-06 20:00:02',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-9922969-2577425','QU-6T21-ZKAH',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ãã°ãããå¼è»è¼ã¹ãã/ã¿ãã¬ãããã«ãã¼ 360åº¦åè»¢ ç²çå¼ (ã·ã«ãã¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-01-06 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-9979035-4144650','VK-7V5A-H543',NULL,NULL,'admin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'SAHASAHA ã¹ã¼ãã±ã¼ã¹ ã­ã£ãªã¼ã±ã¼ã¹ ã­ã£ãªã¼ããã° æ©åæã¡è¾¼ã¿ãµã¤ãºãã ãã¡ã¹ãã¼ å·ãç®ç«ã¡ã«ãã TSAã­ãã¯ ãã¼ãã­ã£ãªã¼ ã¸ããã¼ å¨ãµã¤ãº æã (ã­ã¼ãºã´ã¼ã«ã, æ©åæè¾¼ãµã¤ãº(1-3æ³/ç´41ãªããã«ï¼)','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-25 20:00:03',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('503-9991635-9182267','TG-7U8Z-SBK2',NULL,NULL,'admin',NULL,NULL,NULL,'0927143005','810-0073','ç¦å²¡ç--ç¦å²¡å¸ä¸­å¤®åºèé¶´1-2-1','æ¥ä¸',NULL,'JPY--17999.00','ã­ã¯ã¿ã äºè¡ ã»ããã¼ãã±ã¼ã¹ãå¼ä»å± #73','1',NULL,NULL,NULL,NULL,NULL,1,'admin','2019-02-16 20:00:04',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0),('9','6947503775353',NULL,'default','user01','','','','','','','','N','0','','1',NULL,NULL,NULL,NULL,'0',1,'user01','2018-11-13 00:57:48','2018-11-13 00:57:48',2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('DY06daff2d',NULL,NULL,'xxx',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'user01','2019-02-19 10:57:51','2019-02-19 10:57:51',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('DY0d97460d',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'user01','2019-02-21 17:33:07','2019-02-21 17:33:07',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('DY2843543d',NULL,NULL,'xxx',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'user01','2019-02-21 17:30:05','2019-02-21 17:30:05',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('DY3617b663',NULL,NULL,'xx',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'user01','2019-02-21 17:33:25','2019-02-21 17:33:25',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('DY36784423',NULL,NULL,'DYb41755c0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'user01','2019-02-21 17:33:27','2019-02-21 17:33:27',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('DY4676224b',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'user01','2019-02-22 01:27:46','2019-02-22 01:27:46',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('DY65d842c7',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,'user01','2019-02-21 17:30:13','2019-02-21 17:30:13',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('asasaa','444444','1','2197d62c526d','user01','','','','','','','','N','','','10',NULL,NULL,NULL,NULL,'0',4,'user01','2018-11-18 23:27:39','2018-11-18 23:37:52',1,'0','0','0','0','1',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('dsfsd','6947503775353',NULL,'default','user01','','','','','','','','N','0','','1',NULL,NULL,NULL,NULL,'0',1,'user01','2018-11-13 01:12:32','2018-11-13 01:12:32',2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('kl;klkl','6947503775353',NULL,'default','user01','','','','','','','','N','0','','1',NULL,NULL,NULL,NULL,'0',1,'user01','2018-11-13 00:59:15','2018-11-13 00:59:15',2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('klklklk','6947503775353',NULL,'default','user01','','','','','','','','N','','','',NULL,NULL,NULL,NULL,'',1,'user01','2018-11-13 01:09:12','2018-11-13 01:09:12',2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('opop','6947503775353',NULL,'default','user01','','','','','','','','N','0','','1',NULL,NULL,NULL,NULL,'0',1,'user01','2018-11-13 01:07:44','2018-11-13 01:07:44',2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('ordno001','sku0004',NULL,'22','user01','xxx','xxx','xxx','xxx','xxx','xxx','xxx','N','',NULL,'10',NULL,NULL,NULL,NULL,'222',1,'user01','2018-12-02 22:41:33','2018-12-02 22:41:33',1,NULL,NULL,NULL,NULL,NULL,NULL,16,16323,-1,2,2202,22020002),('sdsfs','6947503775353',NULL,'default','user01','','','','','','','','N','0','','1',NULL,NULL,NULL,NULL,'0',1,'user01','2018-11-13 01:04:58','2018-11-13 01:04:58',2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('test001','sku0004',NULL,'2197434257df','user01','xxx','xx','xx','xx','xx','xx','xx','N','','xxx','20',NULL,NULL,NULL,NULL,'2',3,'user01','2018-11-30 22:33:53','2018-11-30 22:55:48',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `l_sale_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_shelf`
--

DROP TABLE IF EXISTS `l_shelf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `l_shelf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shelf_no` varchar(50) DEFAULT NULL,
  `area` varchar(20) DEFAULT NULL,
  `row_no` varchar(20) DEFAULT NULL,
  `layer` varchar(20) DEFAULT NULL,
  `line` varchar(20) DEFAULT NULL,
  `enable` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_shelf`
--

LOCK TABLES `l_shelf` WRITE;
/*!40000 ALTER TABLE `l_shelf` DISABLE KEYS */;
INSERT INTO `l_shelf` VALUES (1,'@xx-null-xxxxxx-xxx','xx',NULL,'xxxxxx','xxx','Y'),(2,'@1-2-3-4','1','2','3','4','Y');
/*!40000 ALTER TABLE `l_shelf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_sku_mapping`
--

DROP TABLE IF EXISTS `l_sku_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `l_sku_mapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dy_sku` varchar(13) COLLATE utf8_bin DEFAULT NULL,
  `sku` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `platform` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `create_on` timestamp NULL DEFAULT NULL,
  `create_by` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_sku_mapping`
--

LOCK TABLES `l_sku_mapping` WRITE;
/*!40000 ALTER TABLE `l_sku_mapping` DISABLE KEYS */;
INSERT INTO `l_sku_mapping` VALUES (5,'9540879835477','skutest001','1','2018-12-18 13:52:45','user01'),(6,'8950615433071','sku00001','2','2018-12-18 13:52:45','user01');
/*!40000 ALTER TABLE `l_sku_mapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_status`
--

DROP TABLE IF EXISTS `l_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `l_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `seq` int(11) DEFAULT NULL,
  `type` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `l_status_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_status`
--

LOCK TABLES `l_status` WRITE;
/*!40000 ALTER TABLE `l_status` DISABLE KEYS */;
INSERT INTO `l_status` VALUES (1,1,'order','å·²çæè®¢å,æªç¡®è®¤ç¸å³ä¿¡æ¯åæäº¤åºåº'),(2,2,'order','å·²ç¡®è®¤ç¸å³ä¿¡æ¯,æªæäº¤åºåº'),(3,3,'order','å·²æäº¤åºåº'),(4,4,'order','å·²åºåº'),(5,5,'order','å·²å®æ'),(6,6,'order','è®¢åå·²æ¦æª,æ¨å¯éæ°ç¼è¾æäº¤'),(7,1,'deliverbox','æ°å»º'),(8,2,'deliverbox','å¤´ç¨'),(9,3,'deliverbox','å¥åºå¾ç¡®è®¤'),(10,4,'deliverbox','å·²å¥åº'),(11,5,'deliverbox','å·²å®æ');
/*!40000 ALTER TABLE `l_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_store_info`
--

DROP TABLE IF EXISTS `l_store_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `l_store_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `sellerId` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `mwsAuthToken` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `secretKey` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `platform` int(11) DEFAULT NULL,
  `enable` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_store_info`
--

LOCK TABLES `l_store_info` WRITE;
/*!40000 ALTER TABLE `l_store_info` DISABLE KEYS */;
INSERT INTO `l_store_info` VALUES (1,1,'AOYEK60LBAPEN','AKIAJ7YDUM5NCBSW4ODA','SIOhohQJmYcqwCXGgP9t5ZlK9XoTQ3Dp0xWyUUsE',NULL,NULL),(2,34,'','','',0,'N');
/*!40000 ALTER TABLE `l_store_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_user`
--

DROP TABLE IF EXISTS `l_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `l_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `type` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `enable` varchar(1) COLLATE utf8_bin DEFAULT NULL,
  `inner_id` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_user`
--

LOCK TABLES `l_user` WRITE;
/*!40000 ALTER TABLE `l_user` DISABLE KEYS */;
INSERT INTO `l_user` VALUES (1,'admin','$2a$10$8XoXYzVCdH2o0VC5WFf3yOpqsDBRgIehj/gRrOikiE.8AErxE2XB6',NULL,NULL,'Y','DY1'),(4,'chenzeshenga','$2a$10$8XoXYzVCdH2o0VC5WFf3yOpqsDBRgIehj/gRrOikiE.8AErxE2XB6','222','åæ·','Y','DY4'),(34,'user01','$2a$10$8XoXYzVCdH2o0VC5WFf3yOpqsDBRgIehj/gRrOikiE.8AErxE2XB6','chenzeshenga@163.com','åæ·','Y','DY34'),(41,'operator','$2a$10$8XoXYzVCdH2o0VC5WFf3yOpqsDBRgIehj/gRrOikiE.8AErxE2XB6','test@11.com','åæ·','Y','DY41'),(42,'head_operator','$2a$10$8XoXYzVCdH2o0VC5WFf3yOpqsDBRgIehj/gRrOikiE.8AErxE2XB6',NULL,'åæ·','Y','');
/*!40000 ALTER TABLE `l_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_user_balance`
--

DROP TABLE IF EXISTS `l_user_balance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `l_user_balance` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userBalance` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `userBalanceJpy` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_user_balance`
--

LOCK TABLES `l_user_balance` WRITE;
/*!40000 ALTER TABLE `l_user_balance` DISABLE KEYS */;
INSERT INTO `l_user_balance` VALUES (88,NULL,NULL);
/*!40000 ALTER TABLE `l_user_balance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_user_info`
--

DROP TABLE IF EXISTS `l_user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `l_user_info` (
  `userId` int(11) NOT NULL,
  `userAddress` varchar(450) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT 'å¬å¸åç§°',
  `addr` varchar(450) COLLATE utf8_bin DEFAULT NULL,
  `mainContacts` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `telephone` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `contact` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_user_info`
--

LOCK TABLES `l_user_info` WRITE;
/*!40000 ALTER TABLE `l_user_info` DISABLE KEYS */;
INSERT INTO `l_user_info` VALUES (1,'','klklkl','qqq','','',NULL),(34,'','','','','',NULL),(88,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `l_user_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_volume_weight`
--

DROP TABLE IF EXISTS `l_volume_weight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `l_volume_weight` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `refno` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `ref_type` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `length` double DEFAULT NULL,
  `width` double DEFAULT NULL,
  `height` double DEFAULT NULL,
  `weight` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_volume_weight`
--

LOCK TABLES `l_volume_weight` WRITE;
/*!40000 ALTER TABLE `l_volume_weight` DISABLE KEYS */;
/*!40000 ALTER TABLE `l_volume_weight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_warehousing`
--

DROP TABLE IF EXISTS `l_warehousing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `l_warehousing` (
  `parent_sku` varchar(40) NOT NULL,
  `owner` varchar(45) NOT NULL,
  `deliver_status` varchar(20) DEFAULT NULL,
  `method` varchar(20) DEFAULT NULL,
  `create_on` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_on` datetime DEFAULT CURRENT_TIMESTAMP,
  `create_by` varchar(45) DEFAULT NULL,
  `update_by` varchar(45) DEFAULT NULL,
  `insurance` varchar(45) DEFAULT NULL,
  `head` varchar(45) DEFAULT NULL,
  `track_no` varchar(45) DEFAULT NULL,
  `clearance_type` varchar(45) DEFAULT NULL,
  `estimated_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `deliver_way` varchar(30) DEFAULT NULL,
  `operation_type` varchar(30) DEFAULT NULL,
  `fee_1` varchar(20) DEFAULT NULL,
  `fee_2` varchar(20) DEFAULT NULL,
  `fee_3` varchar(20) DEFAULT NULL,
  `fee_4` varchar(20) DEFAULT NULL,
  `fee_5` varchar(20) DEFAULT NULL,
  `fee_6` varchar(20) DEFAULT NULL,
  `fee_7` varchar(20) DEFAULT NULL,
  `fee_8` varchar(20) DEFAULT NULL,
  `fee_a` varchar(20) DEFAULT NULL,
  `fee_d` varchar(20) DEFAULT NULL,
  `fee_c` varchar(20) DEFAULT NULL,
  `fee_b` varchar(20) DEFAULT NULL,
  `fee_e` varchar(20) DEFAULT NULL,
  `channel` varchar(20) DEFAULT NULL,
  `estimate_date_to_warehouse` datetime DEFAULT CURRENT_TIMESTAMP,
  `tax_type` varchar(20) DEFAULT NULL,
  `carrier` varchar(50) DEFAULT NULL,
  `fee_1_num` varchar(20) DEFAULT NULL,
  `fee_2_num` varchar(20) DEFAULT NULL,
  `fee_3_num` varchar(20) DEFAULT NULL,
  `fee_4_num` varchar(20) DEFAULT NULL,
  `fee_5_num` varchar(20) DEFAULT NULL,
  `fee_6_num` varchar(20) DEFAULT NULL,
  `fee_7_num` varchar(20) DEFAULT NULL,
  `fee_8_num` varchar(20) DEFAULT NULL,
  `fee_a_num` varchar(20) DEFAULT NULL,
  `fee_b_num` varchar(20) DEFAULT NULL,
  `fee_c_num` varchar(20) DEFAULT NULL,
  `fee_d_num` varchar(20) DEFAULT NULL,
  `fee_e_num` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_warehousing`
--

LOCK TABLES `l_warehousing` WRITE;
/*!40000 ALTER TABLE `l_warehousing` DISABLE KEYS */;
INSERT INTO `l_warehousing` VALUES ('DY003e4c1c','user01','1','','2019-02-06 19:01:47','2019-02-14 23:38:27','user01','user01','N','2','xx','2','2019-02-09 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'CHL0008',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('DY12379a54','user01','7','2','2019-02-06 19:03:38','2019-02-18 22:27:59','user01','operator','N','2','xxxx','2',NULL,'éè£ç®±å¡è½¦æ´¾é','æ®è´§å¥åº','N','N','N','N','N','N','N','N','Y','N','N','Y','N','CHL0008',NULL,'2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'200','500',NULL,NULL,NULL),('DY76861bc2','user01','1','','2019-02-16 15:16:32','2019-02-16 15:16:32','user01','user01','N','2','7897987398472983','2','2019-02-16 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'CHL0008',NULL,'2','é¡ºä¸°',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('DYbc8947d5','user01','1','','2019-02-16 15:19:40','2019-02-16 15:19:40','user01','user01','N','2','123213143','2','2019-02-23 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'CHL0008',NULL,'2','å¾·é¦',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('DYfcb5e0ea','user01','1','','2019-02-16 15:21:34','2019-02-16 15:21:34','user01','user01','N','2','8932849287','2','2019-02-23 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'CHL0008',NULL,'2','é¡ºä¸°',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('DYaa853c30','user01','1','','2019-02-16 15:26:53','2019-02-21 00:06:04','user01','user01','N','2','2389462648','2','2019-02-23 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'-1',NULL,'2','é¡ºä¸°',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('DY44605a27','user01','1','4','2019-02-20 20:35:09','2019-02-21 00:12:15','user01','user01','30','1','','2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'CHL0006',NULL,'2','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('DY2b82c128','user01','1','4','2019-02-22 01:03:44','2019-02-22 01:03:44','user01','user01','N','1','','2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'CHL0006',NULL,'2','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('DYbd55f0ce','user01','1','','2019-02-28 21:25:15','2019-02-28 21:25:15','user01','user01','N','2','xx','2','2019-03-01 00:00:00',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,'2','xxx',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `l_warehousing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_warehousing_content`
--

DROP TABLE IF EXISTS `l_warehousing_content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `l_warehousing_content` (
  `parent_sku` varchar(40) COLLATE utf8_bin NOT NULL,
  `sku` varchar(45) COLLATE utf8_bin NOT NULL,
  `box_seq` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `num` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `defect_num` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `normal_num` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `wrap_type` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `user_confirm` varchar(2) COLLATE utf8_bin DEFAULT NULL,
  `img_path` varchar(2000) COLLATE utf8_bin DEFAULT NULL,
  `dy_sku` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `comments` varchar(2000) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_warehousing_content`
--

LOCK TABLES `l_warehousing_content` WRITE;
/*!40000 ALTER TABLE `l_warehousing_content` DISABLE KEYS */;
INSERT INTO `l_warehousing_content` VALUES ('DY003e4c1c','sku00001','1','12',NULL,NULL,'èªå¸¦åè£',NULL,NULL,'8950615433071',NULL),('DY76861bc2','121211','1','12',NULL,NULL,'èªå¸¦åè£',NULL,NULL,'1281211134862',NULL),('DYbc8947d5','sku0002','1','12',NULL,NULL,'èªå¸¦åè£',NULL,NULL,'0082316348939',NULL),('DYbc8947d5','sku0002','2','12',NULL,NULL,'èªå¸¦åè£',NULL,NULL,'0082316348939',NULL),('DYfcb5e0ea','sku0002','1','12',NULL,NULL,'èªå¸¦åè£',NULL,NULL,'0082316348939',NULL),('DYfcb5e0ea','sku0002','2','12',NULL,NULL,'èªå¸¦åè£',NULL,NULL,'0082316348939',NULL),('DY12379a54','sku00001','1','12','2','10','èªå¸¦åè£','Y','F:/project/tmp/warehousing/upload/DY12379a54/QQæµè§å¨æªå¾_20180206214851_D57E3F203B584a9c8B8F0D80E031379A.jpg','8950615433071','xxxxxxxx'),('DYaa853c30','sku0002','1','12',NULL,NULL,'èªå¸¦åè£',NULL,NULL,'0082316348939',NULL),('DYaa853c30','sku0002','2','123',NULL,NULL,'èªå¸¦åè£',NULL,NULL,'0082316348939',NULL),('DY2b82c128','sku0002','1','10',NULL,NULL,'èªå¸¦åè£',NULL,NULL,'0082316348939',NULL),('DY2b82c128','sku0002','2','15',NULL,NULL,'èªå¸¦åè£',NULL,NULL,'0082316348939',NULL),('DYbd55f0ce','sku0002','1','12',NULL,NULL,'èªå¸¦åè£',NULL,NULL,'0082316348939',NULL);
/*!40000 ALTER TABLE `l_warehousing_content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'logistics'
--

--
-- Dumping routines for database 'logistics'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-07  1:26:49
