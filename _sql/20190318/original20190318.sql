-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: 47.105.107.242    Database: logistics
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
SET NAMES utf8;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `l_amazon_order`
--

DROP TABLE IF EXISTS `l_amazon_order`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8mb4;
CREATE TABLE `l_amazon_order`
(
  `amazonOrderId`                varchar(45) COLLATE utf8_bin NOT NULL,
  `userId`                       varchar(45) COLLATE utf8_bin  DEFAULT NULL,
  `sellerOrderId`                varchar(45) COLLATE utf8_bin  DEFAULT NULL,
  `purchaseDate`                 varchar(45) COLLATE utf8_bin  DEFAULT NULL,
  `lastUpdateDate`               varchar(45) COLLATE utf8_bin  DEFAULT NULL,
  `orderStatus`                  varchar(45) COLLATE utf8_bin  DEFAULT NULL,
  `fulfillmentChannel`           varchar(45) COLLATE utf8_bin  DEFAULT NULL,
  `salesChannel`                 varchar(45) COLLATE utf8_bin  DEFAULT NULL,
  `ShipServiceLevel`             varchar(45) COLLATE utf8_bin  DEFAULT NULL,
  `name`                         varchar(45) COLLATE utf8_bin  DEFAULT NULL,
  `addressLine1`                 varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `stateOrRegion`                varchar(45) COLLATE utf8_bin  DEFAULT NULL,
  `postalCode`                   varchar(45) COLLATE utf8_bin  DEFAULT NULL,
  `countryCode`                  varchar(45) COLLATE utf8_bin  DEFAULT NULL,
  `currencyCode`                 varchar(45) COLLATE utf8_bin  DEFAULT NULL,
  `amount`                       varchar(45) COLLATE utf8_bin  DEFAULT NULL,
  `numberOfItemsUnshipped`       varchar(45) COLLATE utf8_bin  DEFAULT NULL,
  `numberOfItemsShipped`         varchar(45) COLLATE utf8_bin  DEFAULT NULL,
  `paymentMethod`                varchar(45) COLLATE utf8_bin  DEFAULT NULL,
  `paymentMethodDetail`          varchar(45) COLLATE utf8_bin  DEFAULT NULL,
  `marketplaceId`                varchar(45) COLLATE utf8_bin  DEFAULT NULL,
  `buyerName`                    varchar(45) COLLATE utf8_bin  DEFAULT NULL,
  `shipmentServiceLevelCategory` varchar(45) COLLATE utf8_bin  DEFAULT NULL,
  `orderType`                    varchar(45) COLLATE utf8_bin  DEFAULT NULL,
  `eraliestShipDate`             varchar(45) COLLATE utf8_bin  DEFAULT NULL,
  `latestShipDate`               varchar(45) COLLATE utf8_bin  DEFAULT NULL,
  `isBusinessOrder`              varchar(45) COLLATE utf8_bin  DEFAULT NULL,
  `isPrime`                      varchar(45) COLLATE utf8_bin  DEFAULT NULL,
  `isPremiumOrder`               varchar(45) COLLATE utf8_bin  DEFAULT NULL,
  `isReplacementOrder`           varchar(45) COLLATE utf8_bin  DEFAULT NULL,
  PRIMARY KEY (`amazonOrderId`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_amazon_order`
--

LOCK TABLES `l_amazon_order` WRITE;
/*!40000 ALTER TABLE `l_amazon_order`
  DISABLE KEYS */;
/*!40000 ALTER TABLE `l_amazon_order`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_carrier_order`
--

DROP TABLE IF EXISTS `l_carrier_order`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8mb4;
CREATE TABLE `l_carrier_order`
(
  `id`             int(11) NOT NULL,
  `carrier_no`     varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `order_no_start` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `order_no_end`   varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_carrier_order`
--

LOCK TABLES `l_carrier_order` WRITE;
/*!40000 ALTER TABLE `l_carrier_order`
  DISABLE KEYS */;
/*!40000 ALTER TABLE `l_carrier_order`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_category`
--

DROP TABLE IF EXISTS `l_category`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8mb4;
CREATE TABLE `l_category`
(
  `id`   int(11) NOT NULL AUTO_INCREMENT,
  `seq`  int(11)                       DEFAULT NULL,
  `type` varchar(20) COLLATE utf8_bin  DEFAULT NULL,
  `name` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `l_catagory_id_uindex` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 33
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_category`
--

LOCK TABLES `l_category` WRITE;
/*!40000 ALTER TABLE `l_category`
  DISABLE KEYS */;
INSERT INTO `l_category`
VALUES (1, 1, 'order', '海外仓代发订单'),
       (2, 2, 'order', '特色小包'),
       (7, 1, 'carrier', '日本邮政'),
       (8, 2, 'carrier', '佐川急便'),
       (9, 3, 'carrier', '黑猫运输'),
       (10, 4, 'carrier', '西浓运输'),
       (11, 5, 'carrier', '卡车派送'),
       (12, 3, 'order', '已拦截订单'),
       (13, 4, 'order', '其他'),
       (14, 6, 'carrier', '其他'),
       (15, 1, 'deliver', '海运整柜20GP/HQ'),
       (16, 2, 'deliver', '海运整柜40GP/HQ'),
       (17, 3, 'deliver', '海运散货'),
       (18, 4, 'deliver', '空运'),
       (19, 5, 'deliver', '卡车派送'),
       (20, 6, 'deliver', '其他'),
       (21, 1, 'classification', '小物'),
       (22, 2, 'classification', '服装'),
       (23, 3, 'classification', '户外运动'),
       (24, 4, 'classification', '电子产品'),
       (25, 5, 'classification', '家居用品'),
       (26, 6, 'classification', '玩具'),
       (27, 7, 'classification', '易碎品'),
       (28, 8, 'classification', '大件'),
       (30, 9, 'classification', '其他'),
       (31, 1, 'method', '东岳头程'),
       (32, 2, 'method', '其他头程');
/*!40000 ALTER TABLE `l_category`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_claim`
--

DROP TABLE IF EXISTS `l_claim`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8mb4;
CREATE TABLE `l_claim`
(
  `claim_id`  varchar(50) COLLATE utf8_bin NOT NULL,
  `order_no`  varchar(45) COLLATE utf8_bin      DEFAULT NULL,
  `track_no`  varchar(45) COLLATE utf8_bin      DEFAULT NULL,
  `remarks`   varchar(200) COLLATE utf8_bin     DEFAULT NULL,
  `img_path`  varchar(1000) COLLATE utf8_bin    DEFAULT NULL,
  `create_on` timestamp                    NULL DEFAULT NULL,
  `update_on` timestamp                    NULL DEFAULT NULL,
  `create_by` varchar(30) COLLATE utf8_bin      DEFAULT NULL,
  `update_by` varchar(30) COLLATE utf8_bin      DEFAULT NULL,
  PRIMARY KEY (`claim_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_claim`
--

LOCK TABLES `l_claim` WRITE;
/*!40000 ALTER TABLE `l_claim`
  DISABLE KEYS */;
INSERT INTO `l_claim`
VALUES ('DY4ae01f84', NULL, NULL, NULL, '', NULL, NULL, NULL, NULL),
       ('DY5789d505', 'DYb41755c0', 'xxxxx', 'xxxxxxxx', '', '2019-02-19 14:59:05', NULL, 'user01', NULL),
       ('DY661eb085', NULL, NULL, NULL, '', NULL, NULL, NULL, NULL),
       ('DY947912ce', NULL, NULL, NULL, '', NULL, NULL, NULL, NULL),
       ('DYa4914f98', NULL, NULL, NULL,
        'C:/tmp/claim/DYa4914f98/DYa6fd5011_QQ浏览器截图_20180109235724_5640D09F303C4184B6D3FF2069398BEE.jpg;C:/tmp/claim/DYa4914f98/DY792f2959_QQ浏览器截图_20180206214851_D57E3F203B584a9c8B8F0D80E031379A.jpg;C:/tmp/claim/DYa4914f98/DYd8db7538_QQ浏览器截图_20180206215234_BDCB19050F184154834BFF8C2D0BFA5C.jpg',
        NULL, NULL, NULL, NULL),
       ('DYb37e077d', 'DYb41755c0', 'xxx', 'xxx', '', NULL, '2019-02-19 15:49:49', 'user01', 'user01'),
       ('DYbdf89284', 'DYb41755c0', 'xxxxxx', 'xxxxxxxx', '', NULL, '2019-02-21 15:04:42', 'user01', 'user01'),
       ('DYd0e94b8a', 'xx', 'xx', 'xx', '', '2018-12-15 03:36:01', NULL, 'user01', NULL),
       ('DYde599d70', NULL, NULL, NULL, 'C:/tmp/claim/DYde599d70/DYb6ffbb1f_QQ浏览器截图_20180308223736_54E2B1592E1B4d43936BB660859994D0.jpg', NULL, NULL,
        NULL, NULL),
       ('DYf7251a75', 'xxx', 'xx', 'xx', '', '2018-12-15 15:06:02', NULL, 'user01', NULL),
       ('undefined', NULL, NULL, NULL, '', NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `l_claim`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_claim_content`
--

DROP TABLE IF EXISTS `l_claim_content`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8mb4;
CREATE TABLE `l_claim_content`
(
  `claim_id`  varchar(20) COLLATE utf8_bin  DEFAULT NULL,
  `sku`       varchar(20) COLLATE utf8_bin  DEFAULT NULL,
  `name`      varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `deal_with` int(11)                       DEFAULT NULL,
  `num`       int(11)                       DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_claim_content`
--

LOCK TABLES `l_claim_content` WRITE;
/*!40000 ALTER TABLE `l_claim_content`
  DISABLE KEYS */;
INSERT INTO `l_claim_content`
VALUES ('DY2843543d', '121211', '1212', 1, 12),
       ('DY2843543d', '121211', '1212', 1, 12),
       ('DY65d842c7', 'sdfsdf', 'aaa', 1, 5),
       ('DY65d842c7', 'sdfsdf', 'aaa', 1, 5),
       ('DY65d842c7', 'sdfsdf', 'aaa', 1, 5),
       ('DY4ae01f84', 'sdfsdf', 'aaa', 1, 5),
       ('DY4ae01f84', 'sdfsdf', 'aaa', 1, 5),
       ('DY36784423', 'sdfsdf', 'aaa', 1, 5),
       ('DY36784423', 'sdfsdf', 'aaa', 1, 5),
       ('DYbdf89284', 'sdfsdf', 'aaa', 1, 5);
/*!40000 ALTER TABLE `l_claim_content`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_claim_storage`
--

DROP TABLE IF EXISTS `l_claim_storage`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8mb4;
CREATE TABLE `l_claim_storage`
(
  `id`                 int(11)   NOT NULL AUTO_INCREMENT,
  `sku`                varchar(20) COLLATE utf8_bin   DEFAULT NULL,
  `num`                int(11)                        DEFAULT NULL,
  `type`               int(11)                        DEFAULT NULL,
  `target`             varchar(200) COLLATE utf8_bin  DEFAULT NULL,
  `create_on`          timestamp NULL                 DEFAULT NULL,
  `update_on`          timestamp NULL                 DEFAULT NULL,
  `create_by`          varchar(50) COLLATE utf8_bin   DEFAULT NULL,
  `update_by`          varchar(50) COLLATE utf8_bin   DEFAULT NULL,
  `status`             int(11)                        DEFAULT NULL,
  `remarks`            varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `name_for_customers` varchar(200) COLLATE utf8_bin  DEFAULT NULL,
  `price`              double                         DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_claim_storage`
--

LOCK TABLES `l_claim_storage` WRITE;
/*!40000 ALTER TABLE `l_claim_storage`
  DISABLE KEYS */;
INSERT INTO `l_claim_storage`
VALUES (1, '121211', 18, 2, 'xxx', '2018-12-16 04:22:57', '2018-12-22 15:09:32', 'user01', NULL, 6, NULL, NULL, NULL);
/*!40000 ALTER TABLE `l_claim_storage`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_deliver_box`
--

DROP TABLE IF EXISTS `l_deliver_box`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8mb4;
CREATE TABLE `l_deliver_box`
(
  `parentsku`      varchar(40) COLLATE utf8_bin NOT NULL,
  `sku`            varchar(45) COLLATE utf8_bin NOT NULL DEFAULT 'skudeflaut',
  `box_seq`        int(11)                               DEFAULT NULL,
  `num`            int(11)                               DEFAULT NULL,
  `owner`          varchar(45) COLLATE utf8_bin          DEFAULT NULL,
  `deliverStatus`  varchar(45) COLLATE utf8_bin          DEFAULT NULL,
  `method`         varchar(45) COLLATE utf8_bin          DEFAULT NULL,
  `create_on`      datetime                              DEFAULT CURRENT_TIMESTAMP,
  `update_on`      datetime                              DEFAULT CURRENT_TIMESTAMP,
  `insurance`      varchar(45) COLLATE utf8_bin          DEFAULT NULL,
  `head`           varchar(45) COLLATE utf8_bin          DEFAULT NULL,
  `defect_num`     double                                DEFAULT NULL,
  `normal_num`     double                                DEFAULT NULL,
  `loss_num`       double                                DEFAULT NULL,
  `wrap_type`      varchar(45) COLLATE utf8_bin          DEFAULT NULL,
  `track_no`       varchar(45) COLLATE utf8_bin          DEFAULT NULL,
  `tax_type`       int(11)                               DEFAULT NULL,
  `clearance_type` int(11)                               DEFAULT NULL,
  `estimated_date` datetime                              DEFAULT NULL,
  `img_path`       varchar(2000) COLLATE utf8_bin        DEFAULT NULL,
  `user_confirm`   varchar(20) COLLATE utf8_bin          DEFAULT NULL,
  `deliver_way`    varchar(30) COLLATE utf8_bin          DEFAULT NULL,
  `operation_type` varchar(30) COLLATE utf8_bin          DEFAULT NULL,
  `fee_1`          varchar(2) COLLATE utf8_bin           DEFAULT NULL,
  `fee_2`          varchar(2) COLLATE utf8_bin           DEFAULT NULL,
  `fee_3`          varchar(2) COLLATE utf8_bin           DEFAULT NULL,
  `fee_4`          varchar(2) COLLATE utf8_bin           DEFAULT NULL,
  `fee_5`          varchar(2) COLLATE utf8_bin           DEFAULT NULL,
  `fee_6`          varchar(2) COLLATE utf8_bin           DEFAULT NULL,
  `fee_7`          varchar(2) COLLATE utf8_bin           DEFAULT NULL,
  `fee_8`          varchar(2) COLLATE utf8_bin           DEFAULT NULL,
  `fee_a`          varchar(2) COLLATE utf8_bin           DEFAULT NULL,
  `fee_d`          varchar(2) COLLATE utf8_bin           DEFAULT NULL,
  `fee_c`          varchar(2) COLLATE utf8_bin           DEFAULT NULL,
  `fee_b`          varchar(2) COLLATE utf8_bin           DEFAULT NULL,
  `fee_e`          varchar(2) COLLATE utf8_bin           DEFAULT NULL,
  `channel`        int(11)                               DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_deliver_box`
--

LOCK TABLES `l_deliver_box` WRITE;
/*!40000 ALTER TABLE `l_deliver_box`
  DISABLE KEYS */;
INSERT INTO `l_deliver_box`
VALUES ('DYeb03880d', 'sdfsdf', 1, 2, 'user01', '4', '2', '2018-12-09 14:54:07', '2019-02-15 23:35:26', '222', '1', NULL, NULL, NULL, '非自带包装', '',
        NULL, NULL, NULL, NULL, 'Y', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('DY15b5262b', '121211', 1, 1, 'user01', '3', '3', '2018-12-09 15:01:34', '2019-02-19 10:54:10', '222222222', '1', NULL, NULL, NULL, '自带包装',
        '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('DY3bc55cf4', '121211', 1, 23, 'user01', '1', '2', '2018-12-09 17:01:41', '2018-12-29 16:03:20', '12', '1', 1, 21, 1, '自带包装', '', 2, 2,
        '2018-12-09 00:00:00',
        'C:/tmp/upload4/DY3bc55cf4/DYf3831b01_QQ浏览器截图_20180206215234_BDCB19050F184154834BFF8C2D0BFA5C.jpg;C:/tmp/upload4/DY3bc55cf4/DYbab8647e_QQ浏览器截图_20180206215525_B5FD3DD2082C47a6962FDFD9F300762A.jpg;C:/tmp/upload4/DY3bc55cf4/DY905bd5ee_QQ浏览器截图_20180308223736_54E2B1592E1B4d43936BB660859994D0.jpg',
        'Y', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('DYf87e115b', '121211', 1, 222, 'user01', '2', '3', '2018-12-09 22:39:55', '2019-02-22 01:18:30', '15', '1', NULL, NULL, NULL, '自带包装', '', 2,
        2, '2018-12-09 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('DY763170db', 'sku00001', 1, 23, 'user01', '1', '2', '2018-12-10 23:19:45', '2018-12-10 23:19:45', '', '1', NULL, 2, NULL, '自带包装', '', 2, 2,
        '2018-12-22 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('DYd93322fc', 'sku00001', 1, 24, 'user01', '1', '3', '2018-12-11 21:48:21', '2019-01-31 21:38:40', '', '2', NULL, NULL, NULL, '自带包装', 'xxx',
        2, 2, '2018-12-29 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('DY020fe2ff', 'sku00001', 1, 23, 'user01', '1', '4', '2018-12-11 21:48:44', '2018-12-11 21:48:44', '', '2', NULL, NULL, NULL, '自带包装', 'xxxx',
        2, 2, '2018-12-29 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('DYbc3bfc55', 'sku00001', 1, 11, 'user01', '1', '5', '2018-12-11 21:49:10', '2018-12-31 22:58:39', '', '2', NULL, NULL, NULL, '自带包装', 'xxxx',
        2, 2, '2019-03-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('DYcff595bb', 'sku00001', 1, 23, 'user01', '1', '5', '2018-12-11 21:50:09', '2018-12-11 21:50:09', '', '2', NULL, NULL, NULL, '自带包装', 'xxx',
        2, 2, '2018-12-22 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('DY424390c2', 'sku00001', 1, 24, 'user01', '1', '5', '2018-12-11 21:50:24', '2018-12-11 21:50:24', '', '2', NULL, NULL, NULL, '自带包装', 'xxx',
        2, 2, '2018-12-30 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('DYede5a1d1', 'sku00001', 1, 55, 'user01', '1', '6', '2018-12-11 21:50:51', '2018-12-11 21:50:51', '', '2', NULL, NULL, NULL, '自带包装', 'xxxx',
        2, 2, '2018-12-30 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('DYd171f7b7', 'sku00001', 1, 22, 'user01', '1', '2', '2018-12-11 22:59:16', '2019-01-05 16:40:32', '', '2', NULL, NULL, NULL, '自带包装', 'xx',
        -1, -1, '2018-12-11 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('DY938d8b97', '121211', 1, 12, 'user01', '1', '3', '2018-12-16 21:12:32', '2018-12-31 22:56:08', '', '1', NULL, NULL, NULL, '自带包装', '', 2, 2,
        '2018-12-22 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('DY11b8dfd0', '121211', 1, 12, 'user01', '1', '1', '2018-12-17 00:11:26', '2019-01-09 15:24:26', '23.0', '2', NULL, NULL, NULL, '自带包装', 'xxx',
        0, 1, '2019-01-01 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('DY5e0a194e', '121211', 1, 10, 'user01', '1', '6', '2019-01-09 15:30:25', '2019-01-30 23:01:46', '', '1', NULL, NULL, NULL, '自带包装', '', 2, 2,
        '2019-01-10 00:00:00', NULL, NULL, '集装箱卡车派送', '普货入库', 'Y', 'Y', 'N', 'N', 'N', 'N', 'Y', 'Y', 'N', 'N', 'N', 'N', 'N', NULL),
       ('DY5e0a194e', 'sdfsdf', 2, 20, 'user01', '1', '6', '2019-01-09 15:30:25', '2019-01-30 23:01:46', '', '1', NULL, NULL, NULL, '自带包装', '', 2, 2,
        '2019-01-10 00:00:00', NULL, NULL, '集装箱卡车派送', '普货入库', 'Y', 'Y', 'N', 'N', 'N', 'N', 'Y', 'Y', 'N', 'N', 'N', 'N', 'N', NULL),
       ('DY5e0a194e', 'sku00001', 3, 30, 'user01', '1', '6', '2019-01-09 15:30:25', '2019-01-30 23:01:46', '', '1', NULL, NULL, NULL, '自带包装', '', 2,
        2, '2019-01-10 00:00:00', NULL, NULL, '集装箱卡车派送', '普货入库', 'Y', 'Y', 'N', 'N', 'N', 'N', 'Y', 'Y', 'N', 'N', 'N', 'N', 'N', NULL),
       ('DY6e2e3a93', 'sku0002', 1, 10, 'user01', '1', '4', '2019-01-25 15:02:25', '2019-01-31 21:39:02', '', '1', NULL, NULL, NULL, '自带包装', '', 2, 2,
        '2019-02-03 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('DY6e2e3a93', 'sku0002', 2, 20, 'user01', '1', '4', '2019-01-25 15:02:25', '2019-01-31 21:39:02', '', '1', NULL, NULL, NULL, '自带包装', '', 2, 2,
        '2019-02-03 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('DY639bf784', '121211', 1, 10, 'user01', '1', '6', '2019-01-31 21:14:16', '2019-01-31 21:38:58', '', '1', NULL, NULL, NULL, '自带包装', '', 2, 2,
        '2019-02-03 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('DY639bf784', 'sku0002', 2, 20, 'user01', '1', '6', '2019-01-31 21:14:16', '2019-01-31 21:38:58', '', '1', NULL, NULL, NULL, '自带包装', '', 2, 2,
        '2019-02-03 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('DY21d5de5c', '121211', 1, 12, 'user01', '1', '2', '2019-01-31 22:44:48', '2019-01-31 22:44:45', '25', '1', NULL, NULL, NULL, '自带包装', '', 2,
        2, '2019-01-31 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('DYe30c2943', 'sku00001/8950615433071', 1, 12, 'user01', '1', '', '2019-02-06 17:14:59', '2019-02-06 17:14:59', '', '2', NULL, NULL, NULL,
        '自带包装', 'xx', 2, 2, '2019-02-08 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, -1),
       ('DYf0254818', 'sku00001/8950615433071', 1, 12, 'user01', '1', '', '2019-02-06 18:50:56', '2019-02-06 18:50:57', '', '2', NULL, NULL, NULL,
        '自带包装', 'xxxxxxxxxxxx', 2, 2, '2019-02-07 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, -1);
/*!40000 ALTER TABLE `l_deliver_box`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_deliver_box_p`
--

DROP TABLE IF EXISTS `l_deliver_box_p`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8mb4;
CREATE TABLE `l_deliver_box_p`
(
  `parentsku`     varchar(40) COLLATE utf8_bin NOT NULL,
  `sku`           varchar(45) COLLATE utf8_bin NOT NULL,
  `box_seq`       int(11)                      DEFAULT NULL,
  `num`           int(11)                      DEFAULT NULL,
  `owner`         varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `deliverStatus` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `method`        varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `create_on`     datetime                     DEFAULT CURRENT_TIMESTAMP,
  `update_on`     datetime                     DEFAULT CURRENT_TIMESTAMP,
  `insurance`     varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `head`          varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `defect_num`    double                       DEFAULT NULL,
  `normal_num`    double                       DEFAULT NULL,
  `loss_num`      double                       DEFAULT NULL,
  `wrap_type`     varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `track_no`      varchar(45) COLLATE utf8_bin DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_deliver_box_p`
--

LOCK TABLES `l_deliver_box_p` WRITE;
/*!40000 ALTER TABLE `l_deliver_box_p`
  DISABLE KEYS */;
/*!40000 ALTER TABLE `l_deliver_box_p`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_express_track_code`
--

DROP TABLE IF EXISTS `l_express_track_code`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8mb4;
CREATE TABLE `l_express_track_code`
(
  `id`                   int(11) NOT NULL AUTO_INCREMENT,
  `express_company_code` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `min_track_code`       varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `max_track_code`       varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 8
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_express_track_code`
--

LOCK TABLES `l_express_track_code` WRITE;
/*!40000 ALTER TABLE `l_express_track_code`
  DISABLE KEYS */;
INSERT INTO `l_express_track_code`
VALUES (5, '1', '23', '299999'),
       (7, '2', '9', '2000');
/*!40000 ALTER TABLE `l_express_track_code`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_group_role`
--

DROP TABLE IF EXISTS `l_group_role`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8mb4;
CREATE TABLE `l_group_role`
(
  `id`      int(11) NOT NULL AUTO_INCREMENT,
  `groupid` int(11) DEFAULT NULL,
  `roleid`  int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_group_role`
--

LOCK TABLES `l_group_role` WRITE;
/*!40000 ALTER TABLE `l_group_role`
  DISABLE KEYS */;
/*!40000 ALTER TABLE `l_group_role`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_group_user`
--

DROP TABLE IF EXISTS `l_group_user`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8mb4;
CREATE TABLE `l_group_user`
(
  `id`      int(11) NOT NULL AUTO_INCREMENT,
  `groupid` int(11) DEFAULT NULL,
  `userid`  int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_group_user`
--

LOCK TABLES `l_group_user` WRITE;
/*!40000 ALTER TABLE `l_group_user`
  DISABLE KEYS */;
/*!40000 ALTER TABLE `l_group_user`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_order_status`
--

DROP TABLE IF EXISTS `l_order_status`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8mb4;
CREATE TABLE `l_order_status`
(
  `id`   int(11) NOT NULL AUTO_INCREMENT,
  `seq`  int(11)                      DEFAULT NULL,
  `type` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_order_status`
--

LOCK TABLES `l_order_status` WRITE;
/*!40000 ALTER TABLE `l_order_status`
  DISABLE KEYS */;
INSERT INTO `l_order_status`
VALUES (1, 1, 'order', '新增'),
       (2, 2, 'order', '前置海外仓'),
       (3, 3, 'order', '头程收获待转运'),
       (4, 4, 'order', '转运中'),
       (5, 5, 'order', '海外仓入库清点'),
       (6, 7, 'order', '入库清点完成'),
       (7, 8, 'order', '海外仓上架完成'),
       (8, 9, 'order', '废弃'),
       (9, 10, 'order', '异常');
/*!40000 ALTER TABLE `l_order_status`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_permission`
--

DROP TABLE IF EXISTS `l_permission`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8mb4;
CREATE TABLE `l_permission`
(
  `id`     int(11) NOT NULL AUTO_INCREMENT,
  `name`   varchar(100) COLLATE utf8_bin  DEFAULT NULL,
  `desc`   varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `url`    varchar(100) COLLATE utf8_bin  DEFAULT NULL,
  `pid`    int(11)                        DEFAULT NULL,
  `method` varchar(100) COLLATE utf8_bin  DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 12
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_permission`
--

LOCK TABLES `l_permission` WRITE;
/*!40000 ALTER TABLE `l_permission`
  DISABLE KEYS */;
INSERT INTO `l_permission`
VALUES (1, 'ROLE_HOME', 'home', '/', NULL, 'GET'),
       (5, 'ROLE_ADMIN', 'admin', '/**', NULL, 'ALL'),
       (7, 'ROLE_USER', 'user', '/**', NULL, 'ALL'),
       (8, 'ROLE_ANONYMOUS', 'ROLE_ANONYMOUS', '/user/**', NULL, 'POST'),
       (9, 'ROLE_DEFAULT_COMM', 'ROLE_DEFAULT_COMM', '/**', NULL, 'ALL'),
       (10, 'ROLE_ANONYMOUS', 'ROLE_ANONYMOUS', '/login', NULL, 'ALL'),
       (11, 'ROLE_ANONYMOUS', 'ROLE_ANONYMOUS', '/script/**', NULL, 'ALL');
/*!40000 ALTER TABLE `l_permission`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_permission_role`
--

DROP TABLE IF EXISTS `l_permission_role`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8mb4;
CREATE TABLE `l_permission_role`
(
  `id`           int(11) NOT NULL AUTO_INCREMENT,
  `roleid`       int(11) DEFAULT NULL,
  `permissionid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 96
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_permission_role`
--

LOCK TABLES `l_permission_role` WRITE;
/*!40000 ALTER TABLE `l_permission_role`
  DISABLE KEYS */;
INSERT INTO `l_permission_role`
VALUES (1, 1, 5),
       (3, 2, 5),
       (9, 3, 5),
       (83, 4, 5),
       (91, 5, 5),
       (92, 6, 5);
/*!40000 ALTER TABLE `l_permission_role`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_sale_record`
--

DROP TABLE IF EXISTS `l_sale_record`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8mb4;
CREATE TABLE `l_sale_record`
(
  `saleRecNo`      varchar(45) COLLATE utf8_bin NOT NULL,
  `sku`            varchar(45) COLLATE utf8_bin   DEFAULT NULL,
  `carrierNo`      varchar(45) COLLATE utf8_bin   DEFAULT NULL,
  `orderNo`        varchar(45) COLLATE utf8_bin   DEFAULT NULL,
  `fromUserId`     varchar(45) COLLATE utf8_bin   DEFAULT NULL,
  `fromContact`    varchar(45) COLLATE utf8_bin   DEFAULT NULL,
  `fromPostCode`   varchar(45) COLLATE utf8_bin   DEFAULT NULL,
  `fromWhere`      varchar(450) COLLATE utf8_bin  DEFAULT NULL,
  `toContact`      varchar(45) COLLATE utf8_bin   DEFAULT NULL,
  `toPostCode`     varchar(45) COLLATE utf8_bin   DEFAULT NULL,
  `toWhere`        varchar(450) COLLATE utf8_bin  DEFAULT NULL,
  `toWho`          varchar(45) COLLATE utf8_bin   DEFAULT NULL,
  `onBehalf`       varchar(1) COLLATE utf8_bin    DEFAULT NULL,
  `amountOnbehalf` varchar(45) COLLATE utf8_bin   DEFAULT NULL,
  `name`           varchar(1000) COLLATE utf8_bin DEFAULT NULL,
  `num`            varchar(45) COLLATE utf8_bin   DEFAULT NULL,
  `value`          varchar(45) COLLATE utf8_bin   DEFAULT NULL,
  `column1`        varchar(45) COLLATE utf8_bin   DEFAULT NULL,
  `column2`        varchar(45) COLLATE utf8_bin   DEFAULT NULL,
  `column3`        varchar(45) COLLATE utf8_bin   DEFAULT NULL,
  `price`          varchar(45) COLLATE utf8_bin   DEFAULT NULL,
  `status`         int(11)                        DEFAULT NULL,
  `creator`        varchar(45) COLLATE utf8_bin   DEFAULT NULL,
  `createOn`       datetime                       DEFAULT NULL,
  `updateOn`       datetime                       DEFAULT NULL,
  `category`       int(11)                        DEFAULT NULL,
  `length`         varchar(45) COLLATE utf8_bin   DEFAULT NULL,
  `width`          varchar(45) COLLATE utf8_bin   DEFAULT NULL,
  `height`         varchar(45) COLLATE utf8_bin   DEFAULT NULL,
  `weight`         varchar(45) COLLATE utf8_bin   DEFAULT NULL,
  `sizeCategory`   varchar(20) COLLATE utf8_bin   DEFAULT NULL,
  `fromParentSku`  varchar(45) COLLATE utf8_bin   DEFAULT NULL,
  `ken_id`         int(2)                         DEFAULT NULL,
  `city_id`        int(5)                         DEFAULT NULL,
  `town_id`        int(9)                         DEFAULT NULL,
  `from_ken_id`    int(2)                         DEFAULT NULL,
  `from_city_id`   int(5)                         DEFAULT NULL,
  `from_town_id`   int(9)                         DEFAULT NULL,
  PRIMARY KEY (`saleRecNo`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_sale_record`
--

LOCK TABLES `l_sale_record` WRITE;
/*!40000 ALTER TABLE `l_sale_record`
  DISABLE KEYS */;
INSERT INTO `l_sale_record`
VALUES ('222', 'sku0004', '1', '222', '', '', '', '', '', '', '', '', NULL, '', '', '20', NULL, NULL, NULL, NULL, '', 4, 'user01',
        '2018-12-01 00:37:01', '2018-12-01 11:36:47', 1, '0', '0', '0', '0', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('249-0105826-7896651', '5U-Y2SK-VDC5', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA ワンタッチテント テント 3～5人用 ワンタッチ 2WAY テント 3WAY テント 設営簡単 防災用 キャンプ用品 撥水加工 紫外線防止 登山 折りたたみ 防水 通気性 アウトドア 240*210*135cm (ブルー, 3～5人用)', '1', NULL,
        NULL, NULL, NULL, NULL, 1, 'admin', '2018-12-31 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-0123495-4604635', '2N-KDXR-ZK1D', NULL, NULL, 'admin', NULL, NULL, NULL, '0427691024', '252-0344', '神奈川県--相模原市南区古淵2‐2‐15', '前徳美智子', NULL,
        'JPY--11960.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (シルバー アルミフレーム 光沢, Lサイズ（7泊以上/約86 リットル）)', '1', NULL,
        NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-22 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-0179037-4504603', '52-EV2N-XBRO', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド アルミフレーム 光沢, Mサイズ（5-7泊/約66 リットル）)', '0', NULL,
        NULL, NULL, NULL, NULL, 1, 'admin', '2019-03-02 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-0179342-2958255', '9I-ZQH0-MJ73', '1', '', 'admin', '', '', '', '', '612-8394', '京都府--京都市伏見区下鳥羽西芹川町5番地', '奥村　佳正', NULL, '',
        'SAHASAHA ワンタッチテント テント 3～5人用 ワンタッチ 2WAY テント 3WAY テント 設営簡単 防災用 キャンプ用品 撥水', '3', NULL, NULL, NULL, NULL, '', 4, 'user01', '2018-11-07 14:43:07',
        '2018-11-04 11:57:27', 1, '0', '0', '0', '0', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('249-0318620-3487823', 'WG-0DAJ-6HFY', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (シルバー, Mサイズ（5-7泊/約66リットル）)', '0', NULL, NULL, NULL,
        NULL, NULL, 1, 'admin', '2019-02-18 20:00:05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-0352710-9356656', 'FH-WZ2Q-OYJP', NULL, NULL, 'admin', NULL, NULL, NULL, '080−1693−2826', '259-1206', '神奈川県--平塚市真田４−２６−２６', '菅原　志保',
        NULL, 'JPY--8260.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド,Mサイズ（5-7泊/約66リットル）)',
        '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-25 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-0509226-6055029', '5F-TQLF-KUTC', NULL, NULL, 'admin', NULL, NULL, NULL, '0480437661', '340-0164', '埼玉県--幸手市香日向3-1-4', '細木 まゆみ', NULL,
        'JPY--2980.00', 'SAHASAHA 鍵盤ハーモニカ (メロディーピアノ) かわいい ピンク ブルー 32鍵 (ピンク)', '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-23 20:00:05',
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-0749121-3166231', 'LQ-IAIV-1HTJ', NULL, NULL, 'admin', NULL, NULL, NULL, '08040172065', '573-1151', '大阪府--枚方市東牧野町27-1', '吉田　隆', NULL,
        'JPY--7960.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー 拡張 ジッパー 全サイズ 有り (シャンパン, Mサイズ（5-7泊/61（拡張時72）リットル）)',
        '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2018-12-08 12:00:06', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-0960327-0245437', 'FA-BWNY-IM4Z', NULL, NULL, 'admin', NULL, NULL, NULL, '09074483161', '811-3112', '福岡県--古賀市花見東4丁目6-7', '金丸亜弥', NULL,
        'JPY--10260.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (シルバー アルミフレーム 光沢, Mサイズ（5-7泊/約66 リットル）)', '1', NULL,
        NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-27 20:00:06', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-1002727-6495039', 'O8-1VYB-PZQ4', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド, 機内持込サイズ(1-3泊/約41リットル）)', '1', NULL, NULL,
        NULL, NULL, NULL, 1, 'admin', '2019-03-02 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-1313519-7892657', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, '', '989-6405', '宮城県--大崎市岩出山池月字上一栗根岸７６の１', '伊藤和彦', NULL,
        'JPY--399.00', 'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-04 20:00:03', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-1380959-3998241', 'VK-7V5A-H543', NULL, NULL, 'admin', NULL, NULL, NULL, '', '318-0033', '茨城県--高萩市本町３−２７', '渡邊 千尋', NULL, 'JPY--6980.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-10 20:00:03', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-1505959-8093411', 'FH-WZ2Q-OYJP', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド,Mサイズ（5-7泊/約66リットル）)', '1', NULL, NULL, NULL,
        NULL, NULL, 1, 'admin', '2019-02-28 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-1609886-9435807', 'HM-B5BT-1IWO', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー 拡張 ジッパー 全サイズ 有り (ローズゴールド, Ｓサイズ（3-5泊/47（拡張時56）リットル）)', '1', NULL,
        NULL, NULL, NULL, NULL, 1, 'admin', '2018-11-24 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL),
       ('249-1680239-1835047', 'Dustbin-white', NULL, NULL, 'admin', NULL, NULL, NULL, '07055625383', '260-0033', '千葉県--千葉市中央区春日2-21-10-1F', '山下弘毅',
        NULL, 'JPY--9800.00', 'SAHASAH 赤外線センサー式全自動開閉式ステンレスごみ箱　近づくだけで自働的に開ける　清潔　インテリア　容量7Ｌ (ホワイト)', '2', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2018-12-19 20:00:05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-1863248-5025416', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, '', '503-0801', '岐阜県--大垣市和合新町', '武藤郁', NULL, 'JPY--399.00',
        'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2018-12-30 20:00:04', NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-1871551-6507840', 'O2-R8WF-BJFL', NULL, NULL, 'admin', NULL, NULL, NULL, '+819043698161', '333-0851', '埼玉県--川口市芝新町4-30-705', '杉森佳子',
        NULL, 'JPY--3680.00', 'SAHASAHA ワンタッチテント テント 1-2人用 設営簡単 防災用 キャンプ用品 撥水加工 紫外線防止 登山 折りたたみ 防水 通気性 アウトドア 210 * 140 * 110cm グリーン 収納ケース 日本語説明書付き',
        '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-03-11 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-1951689-8391854', 'UW-NJCD-KF9N', NULL, NULL, 'admin', NULL, NULL, NULL, '', '514-2221', '三重県--津市高野尾町634ー7', 'はせがわみゆき', NULL,
        'JPY--7660.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-02-07 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-1975088-7943839', 'YM-RFHA-8TVU', NULL, NULL, 'admin', NULL, NULL, NULL, '0449114212', '214-0014', '神奈川県--川崎市多摩区登戸2828-614', '影山奈穂美',
        NULL, 'JPY--1980.00', 'SAHASAHA 着付け 着物 クリップ 衿止めクリップ 和装クリップ 花 ３色 2セット 6個', '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-01-05 20:00:56', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-2036129-8179816', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, '', '266-0005', '千葉県--千葉市緑区誉田町2－11－180', '大塚義之', NULL,
        'JPY--998.00', 'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-06 20:00:03', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-2102393-7818252', 'SN-43IS-TWEP', NULL, NULL, 'admin', NULL, NULL, NULL, '09013463655', '902-0071', '沖縄県--那覇市繁多川4ー7ー15', '當眞 功也', NULL,
        'JPY--11960.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド, Lサイズ（7泊以上/約86 リットル）)', '1',
        NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-02 20:00:06', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-2308591-9833433', 'FH-WZ2Q-OYJP', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド,Mサイズ（5-7泊/約66リットル）)', '1', NULL, NULL, NULL,
        NULL, NULL, 1, 'admin', '2019-01-22 20:00:05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-2466942-3995846', 'ED-0UIY-40IQ', NULL, NULL, 'admin', NULL, NULL, NULL, '', '558-0053', '大阪府--大阪市住吉区帝塚山中', '菅野　佐紀', NULL,
        'JPY--8260.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-02-13 20:00:05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-2510562-9086269', 'ED-0UIY-40IQ', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド,Mサイズ（5-7泊/約66リットル）)', '1', NULL, NULL, NULL,
        NULL, NULL, 1, 'admin', '2019-03-03 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-2523256-7504627', '52-EV2N-XBRO', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド アルミフレーム 光沢, Mサイズ（5-7泊/約66 リットル）)', '1', NULL,
        NULL, NULL, NULL, NULL, 1, 'admin', '2019-03-02 20:00:05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-2545457-7694210', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, '', '243-0202', '神奈川県--厚木市中荻野944-4', '花上　まゆみ', NULL,
        'JPY--399.00', 'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-03 20:00:03', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-2604500-3450203', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, '', '257-0003', '神奈川県--秦野市南矢名536-1', '近藤岳', NULL, 'JPY--998.00',
        'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-12 20:00:03', NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-2668698-8016605', 'O8-1VYB-PZQ4', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド, 機内持込サイズ(1-3泊/約41リットル）)', '1', NULL, NULL,
        NULL, NULL, NULL, 1, 'admin', '2019-01-25 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-2772987-0048651', 'UW-NJCD-KF9N', NULL, NULL, 'admin', NULL, NULL, NULL, '', '080-2473', '北海道--帯広市西二十三条南4丁目13〜15', '山口 裕美子', NULL,
        'JPY--7277.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-02-02 20:00:02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-2862014-0790254', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, '', '660-0051', '兵庫県--尼崎市東七松町1丁目11-19ヒルズ立花2-203', '山中雅幸', NULL,
        'JPY--399.00', 'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-03 20:00:03', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-3069342-3844629', '0Y-K99Y-JMPZ', NULL, NULL, 'admin', NULL, NULL, NULL, '', '169-0073', '東京都--百人町1-3-14ナビ新宿509', '和田優花', NULL,
        'JPY--9960.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-01-30 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-3121506-8491021', 'TG-7U8Z-SBK2', NULL, NULL, 'admin', NULL, NULL, NULL, '09068893131', '771-1212', '徳島県--板野郡藍住町徳命字前須東111-14', '井内　美沙緒',
        NULL, 'JPY--17999.00', 'キクタニ 二胡 セミハードケース、弓付属 #73', '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-17 20:00:15', NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-3164211-0287833', 'SF-0LCQ-50C6', NULL, NULL, 'admin', NULL, NULL, NULL, '', '187-0032', '東京都--小平市　小川町　１丁目 ４４５', '岩谷　貴樹', NULL,
        'JPY--699.00', 'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (ブラック)', '23', NULL, NULL, NULL, NULL, NULL, 1, 'user01', '2018-11-03 20:00:05',
        NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('249-3497165-7724644', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, '', '675-0061', '兵庫県--加古川市加古川町', '増田　章人', NULL, 'JPY--499.00',
        'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-06 20:00:03', NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-3549687-2659057', '26-7CM8-AURL', NULL, NULL, 'admin', NULL, NULL, NULL, '0827-32-4549', '740-0034', '山口県--岩国市南岩国町１丁目10-30', '河村哲夫',
        NULL, 'JPY--4980.00', 'SAHASAHA ワンタッチテント テント 2～4人用 ワンタッチ 設営簡単 防災用 キャンプ用品 撥水加工 紫外線防止 登山 折りたたみ 防水 通気性 アウトドア', '1', NULL, NULL, NULL, NULL, NULL,
        1, 'admin', '2019-02-25 20:00:06', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-3582124-2346266', '0Y-K99Y-JMPZ', NULL, NULL, 'admin', NULL, NULL, NULL, '', '335-0021', '埼玉県--戸田市新曽', '橋本　みなみ', NULL, 'JPY--9960.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-09 20:00:02', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-3608502-1767044', '5F-TQLF-KUTC', NULL, NULL, 'admin', NULL, NULL, NULL, '090-2736-1215', '820-0011', '福岡県--飯塚市柏の森', '広瀬　憲子', NULL,
        'JPY--2980.00', 'SAHASAHA 鍵盤ハーモニカ (メロディーピアノ) かわいい ピンク ブルー 32鍵 (ピンク)', '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-03-12 20:00:04',
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-3692143-7796668', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, '', '999-3511', '山形県--西村山郡河北町谷地丁45-8', '土田正幸', NULL,
        'JPY--1197.00', 'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-02 20:00:05',
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-3782268-7360610', '0Y-K99Y-JMPZ', NULL, NULL, 'admin', NULL, NULL, NULL, '', '669-1533', '兵庫県--三田市三田町', 'Arianne San Jose', NULL,
        'JPY--9960.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-02-09 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-3817591-6490224', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, '', '657-0844', '兵庫県--神戸市灘区都通5-3-1-402', '山田　宏美', NULL,
        'JPY--399.00', 'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-02 20:00:04', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-3864140-2188612', 'FH-WZ2Q-OYJP', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー 拡張 ジッパー 全サイズ 有り (ローズゴールド, Mサイズ（5-7泊/61（拡張時72）リットル）)', '0', NULL,
        NULL, NULL, NULL, NULL, 1, 'admin', '2018-12-26 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-3893618-0246238', 'VK-7V5A-H543', NULL, NULL, 'admin', NULL, NULL, NULL, '', '167-0032', '東京都--杉並区天沼2-30-12', '藤田夕稀', NULL,
        'JPY--6980.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-02-23 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-4288156-1613400', '0Y-K99Y-JMPZ', NULL, NULL, 'admin', NULL, NULL, NULL, '', '615-0047', '京都府--京都市右京区西院六反田町14-702号', '具志沙也歌', NULL,
        'JPY--9960.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-02-22 20:00:02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-4289309-8575846', 'FH-WZ2Q-OYJP', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド,Mサイズ（5-7泊/約66リットル）)', '1', NULL, NULL, NULL,
        NULL, NULL, 1, 'admin', '2019-02-27 20:00:05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-4329323-1886226', 'ED-0UIY-40IQ', NULL, NULL, 'admin', NULL, NULL, NULL, '', '174-0074', '東京都--板橋区東新町', '大久保万希子', NULL, 'JPY--8260.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-08 20:00:06', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-4525124-0923005', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-11 20:00:02', NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-4538851-4929400', 'FH-WZ2Q-OYJP', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド,Mサイズ（5-7泊/約66リットル）)', '1', NULL, NULL, NULL,
        NULL, NULL, 1, 'admin', '2019-01-27 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-4715789-9610258', 'LQ-IAIV-1HTJ', NULL, NULL, 'admin', NULL, NULL, NULL, '09043234513', '601-8474', '京都府--京都市南区四ツ塚町32-2', '向 みづき', NULL,
        'JPY--7960.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー 拡張 ジッパー 全サイズ 有り (シャンパン, Mサイズ（5-7泊/61（拡張時72）リットル）)',
        '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2018-12-13 16:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-4807412-8175065', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, '', '655-0861', '兵庫県--神戸市垂水区下畑町下畑町2053-2', '藤本', NULL,
        'JPY--998.00', 'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-13 20:00:05', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-4840635-9775039', 'ED-0UIY-40IQ', NULL, NULL, 'admin', NULL, NULL, NULL, '', '359-0045', '埼玉県--所沢市美原町', '山田恵李子', NULL, 'JPY--7847.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-02 20:00:02', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-4952441-9127038', 'FH-WZ2Q-OYJP', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'JPY--8260.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド,Mサイズ（5-7泊/約66リットル）)', '0', NULL, NULL, NULL,
        NULL, NULL, 1, 'admin', '2019-02-20 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-5142073-8243823', 'WG-0DAJ-6HFY', NULL, NULL, 'admin', NULL, NULL, NULL, '08031675489', '179-0085', '東京都--練馬区早宮一丁目', '林田麻衣', NULL,
        'JPY--7960.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー 拡張 ジッパー 全サイズ 有り (シルバー, Mサイズ（5-7泊/61（拡張時72）リットル）)',
        '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2018-12-16 20:00:02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-5212427-2431806', 'LT-GIWM-8NXY', NULL, NULL, 'admin', NULL, NULL, NULL, '0986260533', '885-0082', '宮崎県--都城市南鷹尾町46ｰ9ｰ1号', 'カイトクチエコ',
        NULL, 'JPY--42000.00', '黒檀二胡 中国蘇州二胡 高級 ケース付', '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-03-10 20:00:04', NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-5311811-5314209', 'VK-7V5A-H543', NULL, NULL, 'admin', NULL, NULL, NULL, '', '241-0024', '神奈川県--横浜市旭区本村町', '若月沙彩', NULL, 'JPY--6980.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-16 20:00:05', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-5431604-7100667', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, '', '651-2112', '兵庫県--神戸市西区大津和２‐１０‐３', 'ローソン大津和二丁目', NULL,
        'JPY--399.00', 'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2018-12-25 20:00:03', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-5591453-5803800', '0Y-K99Y-JMPZ', NULL, NULL, 'admin', NULL, NULL, NULL, '', '164-0012', '東京都--中野区本町3-28-8', '太田 有香', NULL,
        'JPY--9960.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-02-08 20:00:05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-5599415-5117435', 'ED-0UIY-40IQ', NULL, NULL, 'admin', NULL, NULL, NULL, '', '959-3124', '新潟県--村上市金屋1520-2', '近藤昭人', NULL,
        'JPY--8260.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-01-29 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-5719373-1761403', 'SH-T-205', NULL, NULL, 'admin', NULL, NULL, NULL, '08020533513', '330-0064', '埼玉県--さいたま市浦和区岸町', '菊池海斗', NULL,
        'JPY--8980.00', 'SAHASAHA アウトドア テント 4-5人用 2WAY 防雨・防風・防災 折りたたみ 簡易テント 二つドア 三つ窓口 天窓 チュール 高通気性 紫外線カット 240*240*140cm 4.3kg', '1', NULL, NULL, NULL,
        NULL, NULL, 1, 'admin', '2019-03-11 20:00:05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-5838711-8831024', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, '', '215-0036', '神奈川県--川崎市麻生区はるひ野4-5-1 207号室', '竹山　博洋', NULL,
        'JPY--699.00', 'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2018-11-24 20:00:02', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('249-5875291-2787064', 'FH-WZ2Q-OYJP', NULL, NULL, 'admin', NULL, NULL, NULL, '0593831746', '513-0809', '三重県--鈴鹿市西条', '鈴木 ますみ', NULL,
        'JPY--8260.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド,Mサイズ（5-7泊/約66リットル）)', '1',
        NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-26 20:00:09', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-6046490-9091029', 'FH-WZ2Q-OYJP', NULL, NULL, 'admin', NULL, NULL, NULL, '09028594541', '861-2101', '熊本県--熊本市東区桜木', '澤田琴美', NULL,
        'JPY--6960.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー 拡張 ジッパー 全サイズ 有り (ローズゴールド, Mサイズ（5-7泊/61（拡張時72）リットル）)', '1', NULL,
        NULL, NULL, NULL, NULL, 1, 'user01', '2018-11-04 12:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL),
       ('249-6200334-0880667', 'SF-0LCQ-50C6', NULL, NULL, 'admin', NULL, NULL, NULL, '', '791-1104', '愛媛県--松山市北土居3-2-13', '藤村 竹生', NULL,
        'JPY--699.00', 'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (ブラック)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2018-11-17 16:00:04', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('249-6247979-0936653', 'LF-F5N1-68IN', NULL, NULL, 'admin', NULL, NULL, NULL, '0722295252', '590-0984', '大阪府--堺市堺区神南辺町', '鈴木宏隆', NULL,
        'JPY--9660.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ブラック アルミフレーム, Ｓサイズ（3-5泊/約51 リットル）)',
        '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-03-08 20:00:02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-6400463-5833461', 'O8-1VYB-PZQ4', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'JPY--6960.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-05 20:00:44', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-6513959-9523056', '0Y-K99Y-JMPZ', NULL, NULL, 'admin', NULL, NULL, NULL, '', '215-0006', '神奈川県--川崎市麻生区金程', '有松初音', NULL, 'JPY--10460.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-03 20:00:03', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-6600971-9159054', 'SN-43IS-TWEP', NULL, NULL, 'admin', NULL, NULL, NULL, '09021903279', '610-0121', '京都府--城陽市寺田庭井145-34', '資延 萌', NULL,
        'JPY--9960.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド, Lサイズ（7泊以上/約86 リットル）)', '1',
        NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-25 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-6624671-3862226', 'HM-B5BT-1IWO', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド, Ｓサイズ（3-5泊/約51リットル）)', '0', NULL, NULL, NULL,
        NULL, NULL, 1, 'admin', '2019-03-02 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-6665177-9223030', 'UW-NJCD-KF9N', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド, Ｓサイズ（3-5泊/約51リットル）)', '1', NULL, NULL, NULL,
        NULL, NULL, 1, 'admin', '2019-01-28 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-6712232-5543806', 'ED-0UIY-40IQ', NULL, NULL, 'admin', NULL, NULL, NULL, '', '277-0872', '千葉県--柏市十余二276-389', '山口卓也', NULL,
        'JPY--8260.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-01-31 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-6849258-2339843', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, '', '408-0115', '山梨県--北杜市須玉町大豆生田588', '輿石光彦', NULL,
        'JPY--399.00', 'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-03 20:00:02', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-6868883-2215819', 'VK-7V5A-H543', NULL, NULL, 'admin', NULL, NULL, NULL, '', '390-0306', '長野県--松本市南浅間612-8', '赤羽莉奈', NULL,
        'JPY--7131.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-02-04 20:00:02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-6967004-5914207', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, '', '166-0003', '東京都--杉並区高円寺南4ー13ー5ー502', '大矢泰斗', NULL,
        'JPY--499.00', 'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-11 20:00:03', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-7010486-1911822', 'M0-CFTC-91ME', NULL, NULL, 'admin', NULL, NULL, NULL, '0774931814', '619-0245', '京都府--相楽郡精華町下狛浄楽52-3', '太田　芳秀', NULL,
        'JPY--20780.00', 'SAHASAHA ゲーミングチェア オフィスチェア 多機能 ゲーム用チェア リクライニング パソコンチェア ハイバック ヘッドレスト 腰痛対策 ランバーサポート ひじ掛け付き レザー (ホワイト)', '1', NULL, NULL, NULL,
        NULL, NULL, 1, 'admin', '2019-01-05 20:00:54', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-7027205-2275069', '93-KCRN-BNNG', NULL, NULL, 'admin', NULL, NULL, NULL, '08033923541', '186-0004', '東京都--国立市中', '菊地 宏', NULL,
        'JPY--11511.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド アルミフレーム 光沢, Lサイズ（7泊以上/約86 リットル）)', '1', NULL,
        NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-23 20:00:05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-7057096-3783861', 'FH-WZ2Q-OYJP', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド,Mサイズ（5-7泊/約66リットル）)', '0', NULL, NULL, NULL,
        NULL, NULL, 1, 'admin', '2019-02-26 20:00:10', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-7150820-3438210', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, '', '503-2418', '岐阜県--揖斐郡池田町草深', '高橋経晴', NULL, 'JPY--499.00',
        'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-10 20:00:05', NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-7335281-1650212', 'Dustbin-white', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAH 赤外線センサー式全自動開閉式ステンレスごみ箱　近づくだけで自働的に開ける　清潔　インテリア　容量7Ｌ (ホワイト)', '2', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-12 20:00:03',
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-7434410-2220609', 'SF-0LCQ-50C6', NULL, NULL, 'admin', NULL, NULL, NULL, '', '263-0041', '千葉県--千葉市稲毛区黒砂台', '熱田 佑介', NULL, 'JPY--699.00',
        'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (ブラック)', '3', NULL, NULL, NULL, NULL, NULL, 1, 'user01', '2018-11-04 12:00:02', NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('249-7576051-6035842', 'Tent-05', NULL, NULL, 'admin', NULL, NULL, NULL, '09040186199', '136-0073', '東京都--江東区北砂3-2-2', '星卓也', NULL,
        'JPY--4980.00', 'SAHASAHA ワンタッチテント 2代 3～4人用 撥水加工 防水 通気性 設営簡単 2色選択可能 登山 折りたたみ 210 * 200 * 135C', '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2018-11-18 18:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('249-7589123-0094259', 'ED-0UIY-40IQ', NULL, NULL, 'admin', NULL, NULL, NULL, '', '811-2310', '福岡県--糟屋郡粕屋町花ヶ浦3-3-32', '高下恵里花', NULL,
        'JPY--8760.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-03-11 20:00:05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-7687650-5203049', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, '', '404-0051', '山梨県--甲州市塩山竹森1139-2', '田邉一輝', NULL,
        'JPY--399.00', 'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-04 20:00:03', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-7754209-1817460', 'UN-CC16-9Q87', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (シルバー, 機内持込サイズ(1-3泊/約41リットル）)', '0', NULL, NULL, NULL,
        NULL, NULL, 1, 'admin', '2019-01-13 20:00:06', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-7883096-8445461', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, '', '833-0002', '福岡県--筑後市前津860-2ハイデンスグリーン5  508号', '田川', NULL,
        'JPY--399.00', 'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-01 20:00:06', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-7885082-6647052', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, '', '503-0973', '岐阜県--大垣市木戸町', 'ロショ アンデルソン', NULL,
        'JPY--798.00', 'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2018-12-30 20:00:02', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-7901925-3039006', 'SN-43IS-TWEP', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド, Lサイズ（7泊以上/約86 リットル）)', '0', NULL, NULL,
        NULL, NULL, NULL, 1, 'admin', '2019-01-22 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-7924765-1469443', 'YM-RFHA-8TVU', NULL, NULL, 'admin', NULL, NULL, NULL, '0267826765', '384-0414', '長野県--佐久市下越１－２７', '井出奈緒美', NULL,
        'JPY--999.00', 'SAHASAHA 着付け 着物 クリップ 衿止めクリップ 和装クリップ 花 6個セット', '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-22 20:00:04', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-7926947-0515803', 'SF-0LCQ-50C6', NULL, NULL, 'admin', NULL, NULL, NULL, '', '212-0054', '神奈川県--川崎市幸区小倉4-19-44', '御宿　政司', NULL,
        'JPY--699.00', 'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (ブラック)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2018-11-18 18:00:02', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('249-8027194-9987006', 'VK-7V5A-H543', NULL, NULL, 'admin', NULL, NULL, NULL, '', '101-0054', '東京都--千代田区神田錦町', '伊藤文馨', NULL, 'JPY--6980.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-23 20:00:04', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-8459616-4527813', 'ED-0UIY-40IQ', NULL, NULL, 'admin', NULL, NULL, NULL, '', '133-0053', '東京都--江戸川区北篠崎', '三橋ほのか', NULL, 'JPY--8260.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-03-08 20:00:04', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-8471342-9159010', 'ED-0UIY-40IQ', NULL, NULL, 'admin', NULL, NULL, NULL, '', '569-1114', '大阪府--高槻市', '上原 眸', NULL, 'JPY--8260.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-30 20:00:03', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-8511971-1090221', 'FH-WZ2Q-OYJP', NULL, NULL, 'admin', NULL, NULL, NULL, '08095067972', '371-0857', '群馬県--前橋市1-22-46', '小野沙絵里', NULL,
        'JPY--7960.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー 拡張 ジッパー 全サイズ 有り (ローズゴールド, Mサイズ（5-7泊/61（拡張時72）リットル）)', '1', NULL,
        NULL, NULL, NULL, NULL, 1, 'admin', '2018-11-24 20:00:02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL),
       ('249-8648661-3801424', 'ED-0UIY-40IQ', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド,Mサイズ（5-7泊/約66リットル）)', '1', NULL, NULL, NULL,
        NULL, NULL, 1, 'admin', '2019-02-01 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-8691174-6962229', 'QB-1TG8-0OMK', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (シルバー, Lサイズ（7泊以上/約86 リットル）)', '1', NULL, NULL, NULL,
        NULL, NULL, 1, 'admin', '2019-01-24 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-8931422-9836661', 'UW-NJCD-KF9N', NULL, NULL, 'admin', NULL, NULL, NULL, '', '107-0052', '東京都--港区赤坂９−７−７−２０３', '野田　るり', NULL,
        'JPY--7660.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-02-07 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-9037899-7380612', 'WG-0DAJ-6HFY', NULL, NULL, 'admin', NULL, NULL, NULL, '08079891647', '511-1135', '三重県--桑名市長島町浦安14番地1', '江越真実子', NULL,
        'JPY--7960.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (シルバー, Mサイズ（5-7泊/約66リットル）)', '1',
        NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-05 20:00:57', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-9045071-4344623', '5W-P3L6-0PBG', NULL, NULL, 'admin', NULL, NULL, NULL, '', '839-0863', '福岡県--久留米市国分町', '蛯原夏生', NULL, 'JPY--15840.00',
        'SAHASAHA テント 一人用 コンパクト 迷彩柄 キャンプテント ソロテント 小型テント　防災　緊急　 キャンプ用品 �', '4', NULL, NULL, NULL, NULL, NULL, 1, 'user01', '2018-11-04 12:00:03', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('249-9134286-5741461', 'A4-TWLW-BYFK', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA ワンタッチテント テント 3～5人用 ワンタッチ 3WAY テント 設営簡単 防災用 キャンプ用品 撥水加工 紫外線防止 登山 折りたたみ 防水 通気性 アウトドア ブルー 240 * 210 * 135cm', '1', NULL, NULL, NULL,
        NULL, NULL, 1, 'admin', '2018-12-08 12:00:05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-9313956-2590229', '93-KCRN-BNNG', NULL, NULL, 'admin', NULL, NULL, NULL, '0425671778', '207-0013', '東京都--東大和市向原', '菊池直美', NULL,
        'JPY--11511.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド アルミフレーム 光沢, Lサイズ（7泊以上/約86 リットル）)', '1', NULL,
        NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-24 20:00:05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-9503210-8081409', 'UW-NJCD-KF9N', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド, Ｓサイズ（3-5泊/約51リットル）)', '1', NULL, NULL, NULL,
        NULL, NULL, 1, 'admin', '2019-02-04 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-9658335-1830254', 'VK-7V5A-H543', NULL, NULL, 'admin', NULL, NULL, NULL, '', '106-0045', '東京都--港区麻布十番３－１－１', '島村彩香', NULL,
        'JPY--6980.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-03-03 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-9835338-2155810', 'WG-0DAJ-6HFY', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (シルバー, Mサイズ（5-7泊/約66リットル）)', '1', NULL, NULL, NULL,
        NULL, NULL, 1, 'admin', '2019-02-19 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('249-9899176-1419044', 'UW-NJCD-KF9N', NULL, NULL, 'admin', NULL, NULL, NULL, '', '240-0011', '神奈川県--横浜市保土ケ谷区桜ケ丘', '加藤亜美', NULL,
        'JPY--7660.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-02-25 20:00:05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-0003353-5510207', 'UN-CC16-9Q87', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー 拡張 ジッパー 全サイズ 有り (シルバー, 機内持込サイズ(1-3泊/33（拡張時40）リットル）)', '1', NULL,
        NULL, NULL, NULL, NULL, 1, 'admin', '2018-12-19 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-0182499-2591818', 'O8-1VYB-PZQ4', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー 拡張 ジッパー 全サイズ 有り (ローズゴールド, 機内持込サイズ(1-3泊/33（拡張時40）リットル）)', '0', NULL,
        NULL, NULL, NULL, NULL, 1, 'admin', '2018-12-25 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-0250212-0333456', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, '', '581-0885', '大阪府--八尾市神宮寺5丁目48', '浅井 廣司', NULL,
        'JPY--798.00', 'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-02 20:00:04', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-0258167-9866266', 'SF-0LCQ-50C6', NULL, NULL, 'admin', NULL, NULL, NULL, '', '179-0084', '東京都--練馬区氷川台3-33-20', '長岩　里奈', NULL,
        'JPY--699.00', 'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (ブラック)', '5', NULL, NULL, NULL, NULL, NULL, 1, 'user01', '2018-11-03 20:00:05',
        NULL, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('250-0287133-8948674', 'ED-0UIY-40IQ', NULL, NULL, 'admin', NULL, NULL, NULL, '', '861-6402', '熊本県--天草市倉岳町棚底2855-2', '中村大樹', NULL,
        'JPY--8260.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-02-05 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-0408379-4981403', 'YI-E0XQ-KIDU', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '初心者入門 紅木二胡 中国蘇州二胡 ケース付',
        '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-05 20:00:53', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-0529749-7925412', 'UW-NJCD-KF9N', NULL, NULL, 'admin', NULL, NULL, NULL, '', '651-2244', '兵庫県--神戸市西区井吹台北町4-55-6', '大庭潤平', NULL,
        'JPY--7660.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-02-08 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-0583718-7485468', 'ED-0UIY-40IQ', NULL, NULL, 'admin', NULL, NULL, NULL, '', '861-8064', '熊本県--熊本市北区八景水谷1-15-9', '山川　俊彦', NULL,
        'JPY--8260.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-03-10 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-0600678-7654211', 'O8-1VYB-PZQ4', NULL, NULL, 'admin', NULL, NULL, NULL, '08065032856', '252-0815', '神奈川県--藤沢市石川４－２３－３', '山田　満久', NULL,
        'JPY--6960.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド, 機内持込サイズ(1-3泊/約41リットル）)',
        '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-23 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-0660024-7771806', 'FH-WZ2Q-OYJP', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド,Mサイズ（5-7泊/約66リットル）)', '0', NULL, NULL, NULL,
        NULL, NULL, 1, 'admin', '2019-01-22 20:00:05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-0677307-0876669', 'YM-RFHA-8TVU', NULL, NULL, 'admin', NULL, NULL, NULL, '0429950286', '359-0042', '埼玉県--所沢市並木2-3-1-1009', '関口真弓', NULL,
        'JPY--999.00', 'SAHASAHA 2019 着付け 着物 クリップ 衿止めクリップ 和装クリップ 扇子 花 3色セット (3個セット, 花)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-02-26 20:00:07', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-0789290-9213413', 'VK-7V5A-H543', NULL, NULL, 'admin', NULL, NULL, NULL, '', '120-0033', '東京都--足立区千住寿町', '山下彩夏', NULL, 'JPY--6980.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-01 20:00:05', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-0907942-0991823', 'W8-PTVD-477D', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース TSAロック 半鏡面仕上げ アライン加工 アルミフレーム レトロ 旅行 出張 軽量 静音 ファスナーレス 機内持込可 (S, アルミフレーム -ローズゴールド)', '1', NULL, NULL, NULL, NULL, NULL,
        1, 'admin', '2019-02-16 20:00:05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-0940926-1857448', 'UW-NJCD-KF9N', NULL, NULL, 'admin', NULL, NULL, NULL, '', '134-0083', '東京都--江戸川区中葛西4-13-4', '齋藤優月', NULL,
        'JPY--7660.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-02-26 20:00:10', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-1035498-8179065', '0Y-K99Y-JMPZ', NULL, NULL, 'admin', NULL, NULL, NULL, '', '053-0821', '北海道--苫小牧市しらかば町３丁目１２番２', '小林公人', NULL,
        'JPY--9960.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-02-17 20:00:14', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-1067834-8972615', 'VK-7V5A-H543', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド, 機内持込サイズ(1-3泊/約41リットル）)', '1', NULL, NULL,
        NULL, NULL, NULL, 1, 'admin', '2019-03-05 20:00:06', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-1076985-2163047', 'UW-NJCD-KF9N', NULL, NULL, 'admin', NULL, NULL, NULL, '', '244-0815', '神奈川県--横浜市戸塚区下倉田町', '河野真知子', NULL,
        'JPY--7660.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-01-29 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-1120141-9051813', 'VK-7V5A-H543', NULL, NULL, 'admin', NULL, NULL, NULL, '', '483-8103', '愛知県--江南市力長町宮前112', '上岡知寿美', NULL,
        'JPY--6980.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-02-13 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-1278877-8339859', '3Q-YKCK-C573', NULL, NULL, 'admin', NULL, NULL, NULL, '0256470044', '955-0047', '新潟県--三条市東三条2-22-14', '岩瀬智子', NULL,
        'JPY--8960.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー 拡張 ジッパー 全サイズ 有り (ローズゴールド アルミフレーム 光沢, 機内持込サイズ(1-3泊/約41 リットル）)', '1',
        NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-22 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-1288245-7584657', '52-EV2N-XBRO', NULL, NULL, 'admin', NULL, NULL, NULL, '0453325195', '240-0004', '神奈川県--横浜市保土ケ谷区岩間町', '小川　真祐子', NULL,
        'JPY--10260.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド アルミフレーム, Mサイズ（5-7泊/約66 リットル）)', '1', NULL,
        NULL, NULL, NULL, NULL, 1, 'admin', '2019-03-11 20:00:06', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-1304604-9748648', 'VK-7V5A-H543', NULL, NULL, 'admin', NULL, NULL, NULL, '', '215-0011', '神奈川県--川崎市麻生区百合丘2-19-3', '後藤真子', NULL,
        'JPY--6980.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-02-19 20:00:05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-1413122-1858203', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, '', '395-0803', '長野県--飯田市鼎下山９１３‐１', 'ローソン飯田西鼎', NULL,
        'JPY--499.00', 'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-12 20:00:03', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-1625292-4124625', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, '', '930-0088', '富山県--富山市諏訪川原２−４−４', '金子 平', NULL,
        'JPY--399.00', 'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2018-12-27 20:00:03', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-1681430-1291016', 'M0-CFTC-91ME', NULL, NULL, 'admin', NULL, NULL, NULL, '08030146500', '300-2417', '茨城県--つくばみらい市富士見ヶ丘1-32-21', '深谷泰介',
        NULL, 'JPY--20780.00', 'SAHASAHA ゲーミングチェア オフィスチェア 多機能 ゲーム用チェア リクライニング パソコンチェア ハイバック ヘッドレスト 腰痛対策 ランバーサポート ひじ掛け付き レザー (ホワイト)', '1', NULL, NULL,
        NULL, NULL, NULL, 1, 'admin', '2019-02-27 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-1692004-7649433', 'VK-7V5A-H543', NULL, NULL, 'admin', NULL, NULL, NULL, '', '141-0022', '東京都--品川区東五反田5-9-22', '宮野寛子', NULL,
        'JPY--6980.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-02-22 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-1747514-0260642', 'Dustbin-sliver', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAH 赤外線センサー式全自動開閉式ステンレスごみ箱　近づくだけで自働的に開ける　清潔　インテリア　容量7Ｌ (シルバー)', '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2018-12-29 20:00:03',
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-1831961-8222222', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, '', '822-1302', '福岡県--田川郡糸田町大熊', '竹田菜央', NULL, 'JPY--399.00',
        'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2018-12-27 20:00:04', NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-1857835-6283009', 'FH-WZ2Q-OYJP', NULL, NULL, 'admin', NULL, NULL, NULL, '0824281297', '739-0147', '広島県--東広島市八本松西', '平岡真理子', NULL,
        'JPY--8260.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド,Mサイズ（5-7泊/約66リットル）)', '1',
        NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-14 20:00:05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-1926622-3631849', 'pet tent-white big', NULL, NULL, 'admin', NULL, NULL, NULL, '08065023774', '195-0063', '東京都--町田市野津田町3535-2', '長谷部菜々',
        NULL, 'JPY--4599.00', 'SAHASAHA ペット テント ティピーテント 犬小屋 ペットハウス かわいい ティピー ビション 犬用 猫用 小型犬 ホワイト小さい　クッション別売り (60*60*70, ホワイト)', '1', NULL, NULL, NULL,
        NULL, NULL, 1, 'admin', '2019-01-21 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-1947445-7158240', '6B-OYS1-RBC2', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー 拡張 ジッパー 全サイズ 有り (ローズゴールド アルミフレーム 光沢, Ｓサイズ（3-5泊/約51 リットル）)', '1',
        NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-24 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-2024231-0709426', 'SN-43IS-TWEP', NULL, NULL, 'admin', NULL, NULL, NULL, '09069085472', '616-8333', 'Kyoto-fu--Kyoutoshi Ukyouku',
        'Andou Junpei', NULL, 'JPY--9960.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー 拡張 ジッパー 全サイズ 有り (ローズゴールド, Lサイズ（7泊以上/88（拡張時102）リットル）)', '1', NULL,
        NULL, NULL, NULL, NULL, 1, 'admin', '2018-12-15 20:00:02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-2153430-2770216', 'YI-E0XQ-KIDU', NULL, NULL, 'admin', NULL, NULL, NULL, '09085548675', '192-0045', '東京都--八王子市大和田町6丁目7-7', '上田陽太', NULL,
        'JPY--16000.00', '初心者入門 紅木二胡 中国蘇州二胡 ケース付', '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2018-12-22 20:00:02', NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-2171501-8988660', 'HM-B5BT-1IWO', NULL, NULL, 'admin', NULL, NULL, NULL, '0359762608', '112-0013', '東京都--文京区音羽', '伊藤万起', NULL,
        'JPY--7426.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド, Ｓサイズ（3-5泊/約51リットル）)', '1',
        NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-22 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-2272802-4733467', '52-EV2N-XBRO', NULL, NULL, 'admin', NULL, NULL, NULL, '08061787832', '572-0801', '大阪府--寝屋川市', '春日裕佳子', NULL,
        'JPY--10260.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド アルミフレーム 光沢, Mサイズ（5-7泊/約66 リットル）)', '1', NULL,
        NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-07 20:00:02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-2273020-1923046', 'YI-E0XQ-KIDU', NULL, NULL, 'admin', NULL, NULL, NULL, '07040745678', '136-0072', '東京都--江東区大島', '朱建春', NULL,
        'JPY--16000.00', '初心者入門 紅木二胡 中国蘇州二胡 ケース付', '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-01 20:00:03', NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-2380797-3007854', 'ED-0UIY-40IQ', NULL, NULL, 'admin', NULL, NULL, NULL, '', '808-0027', '福岡県--北九州市若松区北湊町', '阿部美咲', NULL, 'JPY--8760.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-03-07 20:00:07', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-2689454-8251035', 'HM-B5BT-1IWO', NULL, NULL, 'admin', NULL, NULL, NULL, '09010855334', '651-2102', '兵庫県--神戸市西区学園東町', '上田俊幸', NULL,
        'JPY--7660.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー 拡張 ジッパー 全サイズ 有り (ローズゴールド, Ｓサイズ（3-5泊/47（拡張時56）リットル）)', '1', NULL,
        NULL, NULL, NULL, NULL, 1, 'admin', '2018-12-10 16:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-2755749-8064646', 'FH-WZ2Q-OYJP', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド,Mサイズ（5-7泊/約66リットル）)', '1', NULL, NULL, NULL,
        NULL, NULL, 1, 'admin', '2019-01-21 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-2792897-2149416', 'FH-WZ2Q-OYJP', NULL, NULL, 'admin', NULL, NULL, NULL, '0118963990', '004-0041', '北海道--札幌市厚別区大谷地東7丁目3', '永井　里佳', NULL,
        'JPY--8960.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー 拡張 ジッパー 全サイズ 有り (ローズゴールド, Mサイズ（5-7泊/61（拡張時72）リットル）)', '1', NULL,
        NULL, NULL, NULL, NULL, 1, 'admin', '2018-11-17 16:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL),
       ('250-2818787-8350246', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, '', '252-1115', '神奈川県--綾瀬市落合南2−12−55', '石澤朋幸', NULL,
        'JPY--798.00', 'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2018-12-29 20:00:04', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-2850337-1543042', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, '', '720-1131', '広島県--福山市 駅家町万能倉337-1', '雨森　圭亮', NULL,
        'JPY--499.00', 'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-07 20:00:03', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-2852067-5576659', 'O8-1VYB-PZQ4', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド, 機内持込サイズ(1-3泊/約41リットル）)', '1', NULL, NULL,
        NULL, NULL, NULL, 1, 'admin', '2019-01-13 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-2938250-2291802', 'P3-AXD1-32XQ', NULL, NULL, 'admin', NULL, NULL, NULL, '', '354-0046', '埼玉県--入間郡三芳町竹間沢東4-6', 'QA231 ID: QA231-W0052',
        NULL, 'JPY--999.00', 'SAHASAHA ワンタッチテント テント 3～5人用 ワンタッチ 2WAY テント 設営簡単 防災用 キャンプ用品 撥水加工 紫外�', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-01-01 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-2962827-4248626', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, '', '563-0104', '大阪府--豊能郡　豊能町　光風台', '越尾　拓矢', NULL,
        'JPY--798.00', 'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-01 20:00:04', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-3028863-7282266', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, '', '799-1101', '愛媛県--西条市小松町新屋敷', '白石亮徳', NULL, 'JPY--399.00',
        'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2018-12-29 20:00:03', NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-3106865-4843020', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, '', '418-0114', '静岡県--富士宮市下条833-2', '石川　幸久', NULL,
        'JPY--399.00', 'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2018-12-28 20:00:03', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-3132899-5032642', 'SN-43IS-TWEP', NULL, NULL, 'admin', NULL, NULL, NULL, '08055093145', '308-0827', '茨城県--筑西市市野辺', 'ワニダ', NULL,
        'JPY--9960.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド, Lサイズ（7泊以上/約86 リットル）)', '1',
        NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-03-04 18:00:06', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-3143588-4896645', '5P-FC8V-S16L', NULL, NULL, 'admin', NULL, NULL, NULL, '08016998931', '181-0011', '東京都--三鷹市井口', '斎藤綾乃', NULL,
        'JPY--7960.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー 拡張 ジッパー 全サイズ 有り (ブラック, Mサイズ（5-7泊/61（拡張時72）リットル）)',
        '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2018-12-13 16:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-3293459-1551836', '90-K4KW-NX3B', NULL, NULL, 'admin', NULL, NULL, NULL, '09072044256', '878-0025', '大分県--竹田市拝田原67', '佐藤千代美', NULL,
        'JPY--11890.00',
        'SAHASAHA かわいい スーツケース 便携ケース セット キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー ピンクとブルー (便携ケース+キャリーケース, Lサイズ（7泊以上）)', '1',
        NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-27 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-3428249-9591825', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, '', '370-3503', '群馬県--北群馬郡榛東村大字新井２５２８', 'ローソン榛東新井南', NULL,
        'JPY--998.00', 'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-06 20:00:04', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-3580674-9954220', '0Y-K99Y-JMPZ', NULL, NULL, 'admin', NULL, NULL, NULL, '', '306-0053', '茨城県--古河市中田', '秋庭しおり', NULL, 'JPY--10062.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-28 20:00:03', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-3620334-2613434', 'M0-I6TZ-MSCV', NULL, NULL, 'admin', NULL, NULL, NULL, '08014191574', '177-0051', '東京都--練馬区関町北2-32-7-205', '松岡久美',
        NULL, 'JPY--1620.00', 'SAHASAHA 2019 着付け 着物 クリップ 衿止めクリップ 和装クリップ 扇子 花 3色セット (6個セット, 花)', '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-03-08 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-3646306-1634258', 'O8-1VYB-PZQ4', NULL, NULL, 'admin', NULL, NULL, NULL, '03-3397-3736', '167-0034', '東京都--杉並区桃井', '山田 啓子', NULL,
        'JPY--6960.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド, 機内持込サイズ(1-3泊/約41リットル）)',
        '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-25 20:00:06', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-3695495-0985461', 'FA-BWNY-IM4Z', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (シルバー アルミフレーム 光沢, Mサイズ（5-7泊/約66 リットル）)', '1', NULL,
        NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-21 20:00:02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-3791972-7807842', '3Q-YKCK-C573', NULL, NULL, 'admin', NULL, NULL, NULL, '09081354586', '514-0101', '三重県--津市白塚町588-1', '笠井麻帆', NULL,
        'JPY--8960.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー 拡張 ジッパー 全サイズ 有り (ローズゴールド アルミフレーム 光沢, 機内持込サイズ(1-3泊/約41 リットル）)', '1',
        NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-03-04 18:00:05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-3870554-3980650', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2018-12-26 20:00:05', NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-4018169-6450239', 'YM-RFHA-8TVU', NULL, NULL, 'admin', NULL, NULL, NULL, '08052128603', '859-5152', '長崎県--平戸市岩の上町1016-14', '岡田由紀', NULL,
        'JPY--999.00', 'SAHASAHA 2019 着付け 着物 クリップ 衿止めクリップ 和装クリップ 扇子 花 3色セット (3個セット, 花)', '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-03-11 20:00:07', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-4069068-5451849', 'O2-R8WF-BJFL', NULL, NULL, 'admin', NULL, NULL, NULL, '07052615332', '577-0808', '大阪府--東大阪市横沼町3-6-4', '尾川進一', NULL,
        'JPY--3680.00', 'SAHASAHA ワンタッチテント テント 1-2人用 設営簡単 防災用 キャンプ用品 撥水加工 紫外線防止 登山 折りたたみ 防水 通気性 アウトドア 210 * 140 * 110cm グリーン 収納ケース 日本語説明書付き', '1',
        NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-12 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-4272132-0109445', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, '', '369-1104', '埼玉県--深谷市菅沼22-11', '小関　修', NULL, 'JPY--699.00',
        'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '6', NULL, NULL, NULL, NULL, NULL, 1, 'user01', '2018-11-03 20:00:04', NULL, 4, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('250-4406703-1990250', 'SF-0LCQ-50C6', NULL, NULL, 'admin', NULL, NULL, NULL, '', '654-0053', '兵庫県--神戸市須磨区天神町1丁目1-19', '朝倉由実', NULL,
        'JPY--699.00', 'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (ブラック)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2018-12-02 12:00:03', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('250-4597671-3003030', 'FH-WZ2Q-OYJP', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-22 20:00:06', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-4967053-4442267', 'LW-5DP8-UO3G', NULL, NULL, 'admin', NULL, NULL, NULL, '09022232138', '292-0826', '千葉県--木更津市畑沢南4-12-31', '佐藤　知子', NULL,
        'JPY--1544.00', 'SAHASAHA LED電飾 球形 追加用　カラー（球型）', '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2018-12-10 16:00:04', NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-4968548-6111024', 'M0-CFTC-91ME', NULL, NULL, 'admin', NULL, NULL, NULL, '0664534920', '553-0002', '大阪府--大阪市福島区鷺洲', '渡辺千鶴', NULL,
        'JPY--20780.00', 'SAHASAHA ゲーミングチェア オフィスチェア 多機能 ゲーム用チェア リクライニング パソコンチェア ハイバック ヘッドレスト 腰痛対策 ランバーサポート ひじ掛け付き レザー (ホワイト)', '1', NULL, NULL, NULL,
        NULL, NULL, 1, 'admin', '2019-01-24 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-4984689-1818208', 'CJ-B6AK-37VC', NULL, NULL, 'admin', NULL, NULL, NULL, '09060076815', '460-0012', '愛知県--名古屋市中区千代田１丁目', '牛山　直美', NULL,
        'JPY--999.00', 'SAHASAHA 2019 着付け 着物 クリップ 衿止めクリップ 和装クリップ 扇子 花 3色セット (3個セット, 扇子)', '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-03-12 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-4998639-8519858', 'O8-1VYB-PZQ4', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド, 機内持込サイズ(1-3泊/約41リットル）)', '1', NULL, NULL,
        NULL, NULL, NULL, 1, 'admin', '2018-12-31 20:00:02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-5066757-2780600', 'YM-RFHA-8TVU', NULL, NULL, 'admin', NULL, NULL, NULL, '09080461754', '193-0832', '東京都--八王子市散田町2-67-13', '是枝 真由美',
        NULL, 'JPY--999.00', 'SAHASAHA 着付け 着物 クリップ 衿止めクリップ 和装クリップ 花 3個セット', '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-24 20:00:02',
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-5072775-3391862', 'VK-7V5A-H543', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド, 機内持込サイズ(1-3泊/約41リットル）)', '1', NULL, NULL,
        NULL, NULL, NULL, 1, 'admin', '2019-02-19 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-5147204-2115045', 'ED-0UIY-40IQ', NULL, NULL, 'admin', NULL, NULL, NULL, '', '656-0016', '兵庫県--洲本市下内膳384', '槇本浩子', NULL, 'JPY--8260.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-11 20:00:04', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-5184186-5006220', 'YM-RFHA-8TVU', NULL, NULL, 'admin', NULL, NULL, NULL, '09080461754', '193-0832', '東京都--八王子市散田町2-67-13', '是枝 真由美',
        NULL, 'JPY--999.00', 'SAHASAHA 着付け 着物 クリップ 衿止めクリップ 和装クリップ 花 3個セット', '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-24 20:00:03',
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-5300912-6400654', 'SN-43IS-TWEP', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'JPY--19920.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド, Lサイズ（7泊以上/約86 リットル）)', '0', NULL, NULL,
        NULL, NULL, NULL, 1, 'admin', '2019-02-24 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-5368518-8749415', 'HM-B5BT-1IWO', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド, Ｓサイズ（3-5泊/約51リットル）)', '0', NULL, NULL, NULL,
        NULL, NULL, 1, 'admin', '2018-12-30 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-5374794-7847017', 'YI-E0XQ-KIDU', NULL, NULL, 'admin', NULL, NULL, NULL, '08045144593', '966-0002', '福島県--喜多方市岩月町宮津中田付2810', '矢吹優斗',
        NULL, 'JPY--16000.00', '初心者入門 紅木二胡 中国蘇州二胡 ケース付', '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-04 20:00:02', NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-5531926-7679062', '0Y-K99Y-JMPZ', NULL, NULL, 'admin', NULL, NULL, NULL, '', '123-0857', '東京都--足立区本木北町9-20', '持木美穂', NULL,
        'JPY--9960.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-02-06 20:00:02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-5541884-7042255', 'tent-blue', NULL, NULL, 'admin', NULL, NULL, NULL, '08029527199', '910-0373', '福井県--坂井市丸岡町高柳21一4', '森長千香子', NULL,
        'JPY--3690.00', 'SAHASAHA キッズプレイテント子供用 可愛いテントブルー6角キッズテント キッズハウス 屋内装飾 室内 室外 ギフト プリンセス ピカピカLED電球付き (ブルー)', '1', NULL, NULL, NULL, NULL, NULL, 1,
        'admin', '2018-11-24 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('250-5570148-2596600', '5U-Y2SK-VDC5', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA ワンタッチテント テント 3～5人用 ワンタッチ 2WAY テント 3WAY テント 設営簡単 防災用 キャンプ用品 撥水加工 紫外線防止 登山 折りたたみ 防水 通気性 アウトドア 240*210*135cm (ブルー, 3～5人用)', '0', NULL,
        NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-01 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-5617783-4579050', 'UW-NJCD-KF9N', NULL, NULL, 'admin', NULL, NULL, NULL, '', '838-0228', '福岡県--朝倉郡筑前町二', 'MISAKI HIRAYAMA', NULL,
        'JPY--8200.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-02-08 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-5665139-3735804', 'SN-43IS-TWEP', NULL, NULL, 'admin', NULL, NULL, NULL, '08016671315', '036-8225', '青森県--弘前市西ケ丘町', 'ウスイホノミ', NULL,
        'JPY--9960.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド, Lサイズ（7泊以上/約86 リットル）)', '1',
        NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-24 20:00:05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-5679208-7810211', '0Y-K99Y-JMPZ', NULL, NULL, 'admin', NULL, NULL, NULL, '', '231-0822', '神奈川県--横浜市中区本牧元町13-1', '貝原梨奈', NULL,
        'JPY--9462.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-02-05 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-5697921-3399851', '6B-OYS1-RBC2', NULL, NULL, 'admin', NULL, NULL, NULL, '0567675377', '498-0017', '愛知県--弥富市前ケ須町西勘助222-1', '桐山 真由美',
        NULL, 'JPY--9326.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー 拡張 ジッパー 全サイズ 有り (ローズゴールド アルミフレーム 光沢, Ｓサイズ（3-5泊/約51 リットル）)', '1',
        NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-24 20:00:05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-5804876-1825453', 'O8-1VYB-PZQ4', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド, 機内持込サイズ(1-3泊/約41リットル）)', '0', NULL, NULL,
        NULL, NULL, NULL, 1, 'admin', '2019-01-07 20:00:02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-5821068-8551063', 'O8-1VYB-PZQ4', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド, 機内持込サイズ(1-3泊/約41リットル）)', '1', NULL, NULL,
        NULL, NULL, NULL, 1, 'admin', '2018-12-31 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-5896269-9031037', 'FH-WZ2Q-OYJP', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド,Mサイズ（5-7泊/約66リットル）)', '1', NULL, NULL, NULL,
        NULL, NULL, 1, 'admin', '2019-03-06 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-6057339-2254259', 'VK-7V5A-H543', NULL, NULL, 'admin', NULL, NULL, NULL, '', '350-1142', '埼玉県--川越市藤間', '岡田千晶', NULL, 'JPY--6631.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-03 20:00:04', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-6095174-7992669', 'WG-0DAJ-6HFY', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (シルバー, Mサイズ（5-7泊/約66リットル）)', '1', NULL, NULL, NULL,
        NULL, NULL, 1, 'admin', '2019-01-08 20:00:02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-6299272-8085405', 'O8-1VYB-PZQ4', NULL, NULL, 'admin', NULL, NULL, NULL, '08013517290', '346-0012', '埼玉県--久喜市栗原141-85', '児玉 真后', NULL,
        'JPY--6960.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド, 機内持込サイズ(1-3泊/約41リットル）)',
        '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-06 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-6333928-5161441', 'SN-43IS-TWEP', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド, Lサイズ（7泊以上/約86 リットル）)', '1', NULL, NULL,
        NULL, NULL, NULL, 1, 'admin', '2019-02-26 20:00:08', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-6429746-5664621', 'SN-43IS-TWEP', NULL, NULL, 'admin', NULL, NULL, NULL, '09059718668', '671-1524', '兵庫県--揖保郡太子町東保', '大森秀一郎', NULL,
        'JPY--9960.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド, Lサイズ（7泊以上/約86 リットル）)', '1',
        NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-25 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-6551013-8907868', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, '', '567-0027', '大阪府--茨木市西田中町4-26', '小坂純司', NULL, 'JPY--499.00',
        'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-12 20:00:02', NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-6594978-5086264', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, '', '509-7201', '岐阜県--恵那市大井町２０８７−１７６', '酒井　福己', NULL,
        'JPY--499.00', 'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-10 20:00:04', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-6778705-4607043', 'IV-5BAG-B6JP', NULL, NULL, 'admin', NULL, NULL, NULL, '0456410413', '231-0023', '神奈川県--横浜市中区山下町125-1クリオ横浜元町', '張毅',
        NULL, 'JPY--12960.00', 'SAHASAHA スーツケース キャリーケース TSAロック 半鏡面仕上げ アライン加工 アルミフレーム レトロ 旅行 出張 軽量 静音 ファスナーレス 機内持込可 (L, アルミフレーム-グリーン)', '1', NULL,
        NULL, NULL, NULL, NULL, 1, 'admin', '2019-03-09 20:00:05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-6798964-8698247', 'VK-7V5A-H543', NULL, NULL, 'admin', NULL, NULL, NULL, '', '345-0004', '埼玉県--北葛飾郡杉戸町', 'MOMOE SUZUKI', NULL,
        'JPY--6980.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-02-07 20:00:02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-6854034-9015830', 'O8-1VYB-PZQ4', NULL, NULL, 'admin', NULL, NULL, NULL, '07052621776', '551-0002', '大阪府--大阪市大正区三軒家東1-13-22', '小川保奈美',
        NULL, 'JPY--6960.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー 拡張 ジッパー 全サイズ 有り (ローズゴールド, 機内持込サイズ(1-3泊/33（拡張時40）リットル）)', '1', NULL,
        NULL, NULL, NULL, NULL, 1, 'admin', '2018-12-10 16:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-6980584-7888605', 'ED-0UIY-40IQ', NULL, NULL, 'admin', NULL, NULL, NULL, '', '400-0061', '山梨県--甲府市荒川1-10-25', '今村有依', NULL,
        'JPY--8260.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-02-14 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-7128364-6323854', 'P3-AXD1-32XQ', NULL, NULL, 'admin', NULL, NULL, NULL, '', '174-0046', '東京都--板橋区蓮根', '山下 浩', NULL, 'JPY--1998.00',
        'SAHASAHA ワンタッチテント テント 3～5人用 ワンタッチ 2WAY テント 設営簡単 防災用 キャンプ用品 撥水加工 紫外�', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-01 20:00:05',
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-7160397-0371015', 'UW-NJCD-KF9N', NULL, NULL, 'admin', NULL, NULL, NULL, '', '514-2222', '三重県--津市豊が丘', '荒川麻依', NULL, 'JPY--7660.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-23 20:00:04', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-7330565-3362219', 'FH-WZ2Q-OYJP', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー 拡張 ジッパー 全サイズ 有り (ローズゴールド, Mサイズ（5-7泊/61（拡張時72）リットル）)', '0', NULL,
        NULL, NULL, NULL, NULL, 1, 'admin', '2018-11-18 18:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL),
       ('250-7576531-6543841', 'FH-WZ2Q-OYJP', NULL, NULL, 'admin', NULL, NULL, NULL, '03-3659-3244', '125-0052', '東京都--葛飾区柴又6-28-3', '吉田　秀美', NULL,
        'JPY--8260.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド,Mサイズ（5-7泊/約66リットル）)', '1',
        NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-18 20:00:05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-7771372-6941408', '52-EV2N-XBRO', NULL, NULL, 'admin', NULL, NULL, NULL, '09067773127', '113-0034', '東京都--文京区湯島', '加藤 晶子', NULL,
        'JPY--10260.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-01-05 20:00:46', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-7993048-9004648', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, '', '509-6102', '岐阜県--瑞浪市稲津町萩原1661', '横田　茂弘', NULL,
        'JPY--399.00', 'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2018-12-25 20:00:02', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-8066304-6217458', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, '', '245-0046', '神奈川県--神奈川県横浜市泉区和泉町5733-1', 'hirotakakoyama',
        NULL, 'JPY--399.00', 'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-02 20:00:05',
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-8192428-5134234', 'P3-AXD1-32XQ', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA ワンタッチテント テント 3～5人用 ワンタッチ 2WAY テント 設営簡単 防災用 キャンプ用品 撥水加工 紫外線防止 登山 折りたたみ 防水 通気性 アウトドア 240 * 210 * 135cm', '1', NULL, NULL, NULL, NULL,
        NULL, 1, 'admin', '2018-12-30 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-8285550-8842220', 'HM-B5BT-1IWO', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド, Ｓサイズ（3-5泊/約51リットル）)', '1', NULL, NULL, NULL,
        NULL, NULL, 1, 'admin', '2019-01-21 20:00:05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-8383949-9011010', 'O8-1VYB-PZQ4', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド, 機内持込サイズ(1-3泊/約41リットル）)', '1', NULL, NULL,
        NULL, NULL, NULL, 1, 'admin', '2019-01-25 20:00:02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-8417324-6859804', 'VK-7V5A-H543', NULL, NULL, 'admin', NULL, NULL, NULL, '', '174-0051', '東京都--板橋区小豆沢4-27-7 810号', '福川舞', NULL,
        'JPY--6980.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-03-09 20:00:05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-8519327-4115064', 'FH-WZ2Q-OYJP', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー 拡張 ジッパー 全サイズ 有り (ローズゴールド, Mサイズ（5-7泊/61（拡張時72）リットル）)', '1', NULL,
        NULL, NULL, NULL, NULL, 1, 'admin', '2018-11-18 18:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL),
       ('250-8622060-7871055', 'UN-CC16-9Q87', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'JPY--6960.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (シルバー, 機内持込サイズ(1-3泊/約41リットル）)', '0', NULL, NULL, NULL,
        NULL, NULL, 1, 'admin', '2019-01-23 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-8645620-8303032', 'VK-7V5A-H543', NULL, NULL, 'admin', NULL, NULL, NULL, '', '251-0035', '神奈川県--藤沢市片瀬海岸3-24-22-504', '内宮 彩佳', NULL,
        'JPY--6631.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-02-06 20:00:02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-8907875-2716665', '1J-W6A4-NHO1', NULL, NULL, 'admin', NULL, NULL, NULL, '0593895976', '513-0826', '三重県--鈴鹿市住吉５－４－３３', '周園園', NULL,
        'JPY--8960.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ブラック アルミフレーム 光沢, 機内持込サイズ(1-3泊/約41 リットル）)', '0', NULL,
        NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-21 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-8946061-0571015', 'ED-0UIY-40IQ', NULL, NULL, 'admin', NULL, NULL, NULL, '', '311-2104', '茨城県--鉾田市梶山467', '飯岡夏穂', NULL, 'JPY--8260.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-12 20:00:03', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-9023857-8880641', 'SN-43IS-TWEP', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド, Lサイズ（7泊以上/約86 リットル）)', '1', NULL, NULL,
        NULL, NULL, NULL, 1, 'admin', '2019-02-27 20:00:06', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-9671533-6936646', '5U-Y2SK-VDC5', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA ワンタッチテント テント 3～5人用 ワンタッチ 2WAY テント 3WAY テント 設営簡単 防災用 キャンプ用品 撥水加工 紫外線防止 登山 折りたたみ 防水 通気性 アウトドア 240*210*135cm (ブルー, 3～5人用)', '1', NULL,
        NULL, NULL, NULL, NULL, 1, 'admin', '2018-12-10 16:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-9673661-9693420', 'SF-0LCQ-50C6', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (ブラック)', '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2018-12-02 12:00:04', NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('250-9689348-1694204', 'ED-0UIY-40IQ', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド,Mサイズ（5-7泊/約66リットル）)', '1', NULL, NULL, NULL,
        NULL, NULL, 1, 'admin', '2019-02-10 20:00:02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('250-9925596-8875841', 'HM-B5BT-1IWO', NULL, NULL, 'admin', NULL, NULL, NULL, '076-288-7481', '929-0345', '石川県--河北郡津幡町字太田は-40-2', '柏倉　優',
        NULL, 'JPY--7660.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド, Ｓサイズ（3-5泊/約51リットル）)',
        '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-03-02 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-0011755-5197423', 'ED-0UIY-40IQ', NULL, NULL, 'admin', NULL, NULL, NULL, '', '561-0844', '大阪府--豊中市利倉西', '橋本菜々', NULL, 'JPY--8760.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-13 20:00:04', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-0015281-1582274', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, '', '561-0812', '大阪府--豊中市北条町2-9-20', '岩田新悟', NULL,
        'JPY--399.00', 'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2018-12-29 20:00:04', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-0031321-3180637', '52-EV2N-XBRO', NULL, NULL, 'admin', NULL, NULL, NULL, '08067755494', '197-0827', '東京都--あきる野市油平175-1', '佐藤かす美', NULL,
        'JPY--10260.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド アルミフレーム 光沢, Mサイズ（5-7泊/約66 リットル）)', '1', NULL,
        NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-22 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-0037106-3676668', 'VK-7V5A-H543', NULL, NULL, 'admin', NULL, NULL, NULL, '', '957-0067', '新潟県--新発田市中曽根町', '石山晴菜', NULL, 'JPY--6980.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-03-05 20:00:04', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-0116214-5520657', 'ED-0UIY-40IQ', NULL, NULL, 'admin', NULL, NULL, NULL, '', '391-0011', '長野県--茅野市玉川３４４４−８', '小河原里恵', NULL,
        'JPY--8260.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-02-04 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-0358769-5996665', 'O2-R8WF-BJFL', NULL, NULL, 'admin', NULL, NULL, NULL, '0667881198', '577-0053', '大阪府--東大阪市高井田23-20', '鳥羽道夫', NULL,
        'JPY--3680.00', 'SAHASAHA ワンタッチテント テント 1-2人用 設営簡単 防災用 キャンプ用品 撥水加工 紫外線防止 登山 折りたたみ 防水 通気性 アウトドア 210 * 140 * 110cm グリーン 収納ケース 日本語説明書付き', '1',
        NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-25 20:00:05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-0442911-6139052', 'ED-0UIY-40IQ', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド,Mサイズ（5-7泊/約66リットル）)', '0', NULL, NULL, NULL,
        NULL, NULL, 1, 'admin', '2019-03-08 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-0592636-1955834', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, '', '617-0856', '京都府--長岡京市金ケ原', '戸田　諭', NULL, 'JPY--499.00',
        'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-13 20:00:04', NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-0660401-3667017', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, '', '870-1123', '大分県--大分市大字寒田１５１１番地の７', '亀井和彦', NULL,
        'JPY--399.00', 'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-03 20:00:02', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-0664340-3492612', 'UO-PCEL-6KJ8', NULL, NULL, 'admin', NULL, NULL, NULL, '09093496169', '264-0002', '千葉県--千葉市若葉区千城台東', '青津', NULL,
        'JPY--7660.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ブラック,Ｓサイズ（3-5泊/約51リットル）)', '1', NULL,
        NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-24 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-0670897-2872627', 'VK-7V5A-H543', NULL, NULL, 'admin', NULL, NULL, NULL, '', '335-0034', '埼玉県--戸田市笹目1-19-46', '宮澤千織', NULL,
        'JPY--6631.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-01-28 20:00:02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-0876484-3870223', 'ED-0UIY-40IQ', NULL, NULL, 'admin', NULL, NULL, NULL, '', '812-0016', '福岡県--福岡市博多区博多駅南5丁目9-9-902', '岩間麗愛', NULL,
        'JPY--8260.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-03-07 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-1084006-4235864', 'O8-1VYB-PZQ4', NULL, NULL, 'admin', NULL, NULL, NULL, '08042574108', '922-0106', '石川県--加賀市山中温泉上原町1-176', '漆原愛子', NULL,
        'JPY--6960.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド, 機内持込サイズ(1-3泊/約41リットル）)',
        '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-07 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-1109570-8187840', 'ED-0UIY-40IQ', NULL, NULL, 'admin', NULL, NULL, NULL, '', '700-0923', '岡山県--岡山市北区奥田本町22-10', '南 享子', NULL,
        'JPY--8260.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-02-08 20:00:06', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-1326458-9806241', '5W-P3L6-0PBG', NULL, NULL, 'admin', NULL, NULL, NULL, '', '610-0101', '京都府--城陽市平川横道８−１５', '奥山　雅昭', NULL,
        'JPY--1980.00', 'SAHASAHA テント 一人用 コンパクト 迷彩柄 キャンプテント ソロテント 小型テント 防災 緊急 キャンプ用品 撥水�', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2018-11-17 16:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('503-1499764-8513449', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, '', '940-1151', '新潟県--長岡市三和１‐２‐２０', 'ローソン長岡三和一丁目', NULL,
        'JPY--1197.00', 'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-02 20:00:03',
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-1513947-0529400', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, '', '274-0805', '千葉県--船橋市二和東6-21-26-103', '中屋敷佑一', NULL,
        'JPY--998.00', 'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-11 20:00:03', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-1520292-4949426', 'FH-WZ2Q-OYJP', NULL, NULL, 'admin', NULL, NULL, NULL, '0357028374', '222-0026', '神奈川県--横浜市港北区篠原町959', '志田 悠理子', NULL,
        'JPY--8260.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド,Mサイズ（5-7泊/約66リットル）)', '1',
        NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-21 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-1633990-3115862', 'RG-YGRV-MVH6', NULL, NULL, 'admin', NULL, NULL, NULL, '09074707656', '807-0851', '福岡県--北九州市八幡西区永犬丸', '白石憲一', NULL,
        'JPY--8960.00', 'SAHASAHA スーツケース キャリーケース TSAロック 半鏡面仕上げ アライン加工 アルミフレーム レトロ 旅行 出張 軽量 静音 ファスナーレス 機内持込可 (SS機内持込可, アルミフレーム-シルバー)', '1', NULL, NULL,
        NULL, NULL, NULL, 1, 'admin', '2019-03-11 20:00:07', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-1644843-7221406', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2018-12-31 20:00:03', NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-1654472-6887867', 'UW-NJCD-KF9N', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド, Ｓサイズ（3-5泊/約51リットル）)', '1', NULL, NULL, NULL,
        NULL, NULL, 1, 'admin', '2019-02-20 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-1865323-6505432', 'UN-CC16-9Q87', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (シルバー, 機内持込サイズ(1-3泊/約41リットル）)', '1', NULL, NULL, NULL,
        NULL, NULL, 1, 'admin', '2019-01-13 20:00:07', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-1944337-0977408', 'O8-1VYB-PZQ4', NULL, NULL, 'admin', NULL, NULL, NULL, '03−3726−3231', '145-0064', '東京都--大田区上池台５−３１−２０', '金子　恵利華',
        NULL, 'JPY--6960.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド, 機内持込サイズ(1-3泊/約41リットル）)', '1', NULL, NULL,
        NULL, NULL, NULL, 1, 'admin', '2019-01-06 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-2190056-6445404', 'A4-TWLW-BYFK', NULL, NULL, 'admin', NULL, NULL, NULL, '', '271-0068', '千葉県--松戸市古ケ崎4丁目3542の8', '張　東', NULL,
        'JPY--2980.00', 'SAHASAHA ワンタッチテント テント 3～5人用 ワンタッチ 3WAY テント 設営簡単 防災用 キャンプ用品 撥水加工 紫外�', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2018-11-24 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('503-2222051-8462215', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-04 20:00:02', NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-2302418-8304641', 'UW-NJCD-KF9N', NULL, NULL, 'admin', NULL, NULL, NULL, '', '957-0105', '新潟県--北蒲原郡聖籠町次第浜3134-1', '渡辺法行', NULL,
        'JPY--7660.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-03-10 20:00:02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-2327802-6720638', 'P3-AXD1-32XQ', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA ワンタッチテント テント 3～5人用 ワンタッチ 2WAY テント 設営簡単 防災用 キャンプ用品 撥水加工 紫外線防止 登山 折りたたみ 防水 通気性 アウトドア 240 * 210 * 135cm', '1', NULL, NULL, NULL, NULL,
        NULL, 1, 'admin', '2018-12-31 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-2422076-1994245', 'Dustbin-white', NULL, NULL, 'admin', NULL, NULL, NULL, '0364526582', '107-0062', '東京都--港区南青山', '徳川秀隆', NULL,
        'JPY--4900.00', 'SAHASAH 赤外線センサー式全自動開閉式ステンレスごみ箱　近づくだけで自働的に開ける　清潔　インテリア　容量7Ｌ (ホワイト)', '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-03-06 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-2431669-8526211', 'QS-MP8J-RNYN', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (シャンパン アルミフレーム, Mサイズ（5-7泊/約66 リットル）)', '1', NULL,
        NULL, NULL, NULL, NULL, 1, 'admin', '2019-03-07 20:00:06', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-2469758-3313450', 'O2-R8WF-BJFL', NULL, NULL, 'admin', NULL, NULL, NULL, '0928077805', '819-0166', '福岡県--福岡市西区横浜１丁目４１－３０', '川谷　恒和', NULL,
        'JPY--7360.00', 'SAHASAHA ワンタッチテント テント 1-2人用 設営簡単 防災用 キャンプ用品 撥水加工 紫外線防止 登山 折りたたみ 防水 通気性 アウトドア 210 * 140 * 110cm グリーン 収納ケース 日本語説明書付き', '2',
        NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2018-12-05 10:00:02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-2586776-0105444', 'HM-B5BT-1IWO', NULL, NULL, 'admin', NULL, NULL, NULL, '08067743030', '245-0053', '神奈川県--横浜市戸塚区上矢部町', '奈良岡美桜', NULL,
        'JPY--7660.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド, Ｓサイズ（3-5泊/約51リットル）)', '0',
        NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-28 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-2600590-9614268', 'UW-NJCD-KF9N', NULL, NULL, 'admin', NULL, NULL, NULL, '', '956-0012', '新潟県--新潟市秋葉区荻野町15-14', '平岩哲男', NULL,
        'JPY--7660.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-03-11 20:00:06', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-2645418-2916624', 'N0-9ZUR-9YJ7', NULL, NULL, 'admin', NULL, NULL, NULL, '09081061085', '305-0821', '茨城県--つくば市春日3-24-1  KMT210号室',
        '吉武　沙和佳', NULL, 'JPY--11800.00', 'SAHASAHA 旅行用 スーツケース キャリーケース TSAロック 半鏡面仕上げ アライン加工 アルミフレーム レトロ 旅行 出張 軽量 静音 ファスナーレス (M, ローズゴールド)', '1', NULL,
        NULL, NULL, NULL, NULL, 1, 'admin', '2019-03-07 20:00:06', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-2672774-1766211', 'UW-NJCD-KF9N', NULL, NULL, 'admin', NULL, NULL, NULL, '', '252-0244', '神奈川県--相模原市中央区田名', '長田桃', NULL, 'JPY--8160.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-14 20:00:03', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-2673662-8679000', 'SN-43IS-TWEP', NULL, NULL, 'admin', NULL, NULL, NULL, '08011292074', '114-0012', '東京都--北区田端新町2-18-9-803', '小野寺聖子',
        NULL, 'JPY--9960.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド, Lサイズ（7泊以上/約86 リットル）)',
        '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-27 20:00:05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-2772704-1546255', 'ED-0UIY-40IQ', NULL, NULL, 'admin', NULL, NULL, NULL, '', '145-0063', '東京都--大田区南千束', '大橋栞', NULL, 'JPY--7847.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-04 20:00:04', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-2773867-3534269', '5W-P3L6-0PBG', NULL, NULL, 'admin', NULL, NULL, NULL, '', '350-1236', '埼玉県--日高市大字猿田字神明41-1', 'ヤマト運輸営業所 日高武蔵台センタ',
        NULL, 'JPY--1980.00', 'SAHASAHA テント 一人用 コンパクト 迷彩柄 キャンプテント ソロテント 小型テント　防災　緊急　 キャンプ用品 �', '7', NULL, NULL, NULL, NULL, NULL, 1, 'user01',
        '2018-11-04 12:00:02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('503-2972583-7255833', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-05 20:00:47', NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-3028946-8631826', '0Y-K99Y-JMPZ', NULL, NULL, 'admin', NULL, NULL, NULL, '', '430-0855', '静岡県--浜松市南区楊子町1368-15', '杉浦舞', NULL,
        'JPY--9960.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-02-20 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-3149428-0305446', 'VK-7V5A-H543', NULL, NULL, 'admin', NULL, NULL, NULL, '', '170-0013', '東京都--豊島区東池袋', '星野光代', NULL, 'JPY--6980.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-03-07 20:00:03', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-3173630-2937443', '0Y-K99Y-JMPZ', NULL, NULL, 'admin', NULL, NULL, NULL, '', '384-2204', '長野県--佐久市協和212-6', '佐藤精米　佐藤元紀', NULL,
        'JPY--9960.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-02-18 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-3281983-9985452', 'SF-0LCQ-50C6', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (ブラック)', '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2018-12-08 12:00:05', NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-3306706-2080664', '3Q-YKCK-C573', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー 拡張 ジッパー 全サイズ 有り (ローズゴールド アルミフレーム, 機内持込サイズ(1-3泊/約41 リットル）)', '0',
        NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-03-07 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-3396390-0226201', '52-EV2N-XBRO', NULL, NULL, 'admin', NULL, NULL, NULL, '08019901027', '169-0051', '東京都--新宿区西早稲田', '福家沙也加', NULL,
        'JPY--10260.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー 拡張 ジッパー 全サイズ 有り (ローズゴールド TASロック, Mサイズ（5-7泊/66 リットル）)', '1', NULL,
        NULL, NULL, NULL, NULL, 1, 'admin', '2018-12-13 16:00:02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-3406351-7573446', 'SF-0LCQ-50C6', NULL, NULL, 'admin', NULL, NULL, NULL, '', '248-0007', '神奈川県--鎌倉市大町', '中嶋康晴', NULL, 'JPY--699.00',
        'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (ブラック)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2018-12-02 12:00:03', NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('503-3491802-1795838', 'FH-WZ2Q-OYJP', NULL, NULL, 'admin', NULL, NULL, NULL, '0785924303', '651-1101', '兵庫県--神戸市北区山田町小部字東菅谷4',
        'MICHIKOYOSHIDA', NULL, 'JPY--8260.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド,Mサイズ（5-7泊/約66リットル）)', '0', NULL, NULL, NULL,
        NULL, NULL, 1, 'admin', '2019-02-28 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-3599430-1699056', 'UW-NJCD-KF9N', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド, Ｓサイズ（3-5泊/約51リットル）)', '1', NULL, NULL, NULL,
        NULL, NULL, 1, 'admin', '2019-03-05 20:00:06', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-3742825-7929423', '5F-TQLF-KUTC', NULL, NULL, 'admin', NULL, NULL, NULL, '09039620645', '264-0023', '千葉県--千葉市若葉区貝塚町1102-6', '小貫彩', NULL,
        'JPY--2980.00', 'SAHASAHA 鍵盤ハーモニカ (メロディーピアノ) かわいい ピンク ブルー 32鍵 (ピンク)', '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-17 20:00:15',
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-4125353-6280648', '93-KCRN-BNNG', NULL, NULL, 'admin', NULL, NULL, NULL, '0727250667', '562-0015', '大阪府--箕面市稲', '緒形 由香', NULL,
        'JPY--11960.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド アルミフレーム, Lサイズ（7泊以上/約86 リットル）)', '1', NULL,
        NULL, NULL, NULL, NULL, 1, 'admin', '2019-03-12 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-4158985-5446218', 'P3-AXD1-32XQ', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA ワンタッチテント テント 3～5人用 ワンタッチ 2WAY テント 設営簡単 防災用 キャンプ用品 撥水加工 紫外線防止 登山 折りたたみ 防水 通気性 アウトドア 240 * 210 * 135cm', '1', NULL, NULL, NULL, NULL,
        NULL, 1, 'admin', '2019-01-01 20:00:05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-4320149-7863821', 'YI-E0XQ-KIDU', NULL, NULL, 'admin', NULL, NULL, NULL, '08018339704', '031-0801', '青森県--八戸市江陽', '小川 ゆか', NULL,
        'JPY--16000.00', '初心者入門 紅木二胡 中国蘇州二胡 ケース付', '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-13 20:00:03', NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-4323553-3206256', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, '', '243-0035', '神奈川県--厚木市愛甲', '小原英隆', NULL, 'JPY--399.00',
        'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-01 20:00:05', NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-4445331-4003806', '52-EV2N-XBRO', NULL, NULL, 'admin', NULL, NULL, NULL, '08065776363', '165-0035', '東京都--中野区白鷺1丁目17番2号', '岡村侑香', NULL,
        'JPY--10260.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー 拡張 ジッパー 全サイズ 有り (ローズゴールド TASロック, Mサイズ（5-7泊/66 リットル）)', '1', NULL,
        NULL, NULL, NULL, NULL, 1, 'admin', '2018-12-13 16:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-4524669-4123860', 'UW-NJCD-KF9N', NULL, NULL, 'admin', NULL, NULL, NULL, '', '114-0001', '東京都--北区東十条', '上原美恵', NULL, 'JPY--7777.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-05 20:00:03', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-4603189-6228651', '5U-Y2SK-VDC5', NULL, NULL, 'admin', NULL, NULL, NULL, '', '970-1151', '福島県--いわき市好間町下好間字浦田50-4', '渡部秀司', NULL,
        'JPY--999.00', 'SAHASAHA ワンタッチテント テント 3～5人用 ワンタッチ 2WAY テント 3WAY テント 設営簡単 防災用 キャンプ用品 撥水', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-01-01 20:00:07', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-4656601-9488641', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, '', '807-0831', '福岡県--北九州市八幡西区則松604-6', 'カラーコンセプト　古賀　健一郎', NULL,
        'JPY--699.00', 'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2018-12-09 20:00:03', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-4761725-6815068', 'VK-7V5A-H543', NULL, NULL, 'admin', NULL, NULL, NULL, '', '708-0344', '岡山県--苫田郡鏡野町原157-3', '塚本紫帆', NULL,
        'JPY--6980.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-02-18 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-4918877-0569413', 'SN-43IS-TWEP', NULL, NULL, 'admin', NULL, NULL, NULL, '09028357837', '929-0217', '石川県--白山市湊町7−123', '王威', NULL,
        'JPY--9960.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-02-25 20:00:05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-4929037-6092615', 'O8-1VYB-PZQ4', NULL, NULL, 'admin', NULL, NULL, NULL, '', '661-0985', '兵庫県--尼崎市南清水', '北畑美子', NULL, 'JPY--6761.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド, 機内持込サイズ(1-3泊/約41リットル）)', '1', NULL, NULL,
        NULL, NULL, NULL, 1, 'admin', '2019-01-24 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-5039381-0193417', 'UN-CC16-9Q87', NULL, NULL, 'admin', NULL, NULL, NULL, '08032741370', '183-0032', '東京都--府中市本宿町', '川口 栞', NULL,
        'JPY--6960.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (シルバー, 機内持込サイズ(1-3泊/約41リットル）)', '1',
        NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-03-07 20:00:05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-5090908-4429427', 'SN-43IS-TWEP', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー 拡張 ジッパー 全サイズ 有り (ローズゴールド, Lサイズ（7泊以上/88（拡張時102）リットル）)', '1', NULL,
        NULL, NULL, NULL, NULL, 1, 'admin', '2018-12-10 16:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-5231073-4751002', '6N-OHKM-LVVR', NULL, NULL, 'admin', NULL, NULL, NULL, '09023551244', '639-2301', '奈良県--御所市元町', '中島美鈴', NULL,
        'JPY--5960.00', 'SAHASAHA 電熱ベスト ヒートベスト 充電式 ヒーターベスト 電熱ウェア 防寒着 暖かい スキー ゴルフ バイク 速暖 電熱 作業着 釣り 男女兼用 (4XL, ブラック)', '1', NULL, NULL, NULL, NULL,
        NULL, 1, 'admin', '2018-12-30 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-5261651-1830207', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, '', '411-0044', '静岡県--三島市徳倉879-17', 'DESIGN IMPACT 福田吉秀・久乃',
        NULL, 'JPY--499.00', 'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-09 20:00:02',
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-5368688-1946251', 'O8-1VYB-PZQ4', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド, 機内持込サイズ(1-3泊/約41リットル）)', '0', NULL, NULL,
        NULL, NULL, NULL, 1, 'admin', '2019-03-05 20:00:05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-5442023-9693426', '52-EV2N-XBRO', NULL, NULL, 'admin', NULL, NULL, NULL, '09028874993', '030-0943', '青森県--青森市幸畑', '若松  誠', NULL,
        'JPY--9260.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー 拡張 ジッパー 全サイズ 有り (ローズゴールド TASロック, Mサイズ（5-7泊/66 リットル）)', '1', NULL,
        NULL, NULL, NULL, NULL, 1, 'admin', '2018-11-18 18:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL),
       ('503-5643550-3040601', 'UN-CC16-9Q87', NULL, NULL, 'admin', NULL, NULL, NULL, '08054284161', '251-0037', '神奈川県--藤沢市鵠沼海岸7-10-9-1', '海里', NULL,
        'JPY--6960.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (シルバー, 機内持込サイズ(1-3泊/約41リットル）)', '1',
        NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-11 20:00:02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-5654054-6102226', '0Y-K99Y-JMPZ', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド, Lサイズ（7泊以上/約86 リットル）)', '1', NULL, NULL,
        NULL, NULL, NULL, 1, 'admin', '2019-01-27 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-5694157-3240601', '0Y-K99Y-JMPZ', NULL, NULL, 'admin', NULL, NULL, NULL, '', '860-0811', '熊本県--熊本市中央区本荘6-4-3', '内田奈那', NULL,
        'JPY--9960.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-02-22 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-5957106-9304622', 'ED-0UIY-40IQ', NULL, NULL, 'admin', NULL, NULL, NULL, '', '511-0912', '三重県--桑名市星見ヶ丘９丁目７１８', '竹内　佳美', NULL,
        'JPY--7847.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-02-04 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-6142566-5215041', 'ED-0UIY-40IQ', NULL, NULL, 'admin', NULL, NULL, NULL, '', '344-0004', '埼玉県--春日部市牛島10-17', '福田莉於', NULL,
        'JPY--8260.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-02-08 20:00:05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-6205444-2494232', 'EK-ON40-GKM5', NULL, NULL, 'admin', NULL, NULL, NULL, '0473750004', '272-0822', '千葉県--市川市宮久保４−１１−１０', '鴨川美栄子', NULL,
        'JPY--12960.00', 'SAHASAHA スーツケース キャリーケース TSAロック 半鏡面仕上げ アライン加工 アルミフレーム レトロ 旅行 出張 軽量 静音 ファスナーレス 機内持込可 (L, アルミフレーム -ローズゴールド)', '1', NULL, NULL,
        NULL, NULL, NULL, 1, 'admin', '2019-01-11 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-6604148-8648615', 'M0-I6TZ-MSCV', NULL, NULL, 'admin', NULL, NULL, NULL, '0484653112', '351-0007', '埼玉県--朝霞市岡２−１−１９', '宮澤昇子', NULL,
        'JPY--1620.00', 'SAHASAHA 2019 着付け 着物 クリップ 衿止めクリップ 和装クリップ 扇子 花 3色セット (6個セット, 花)', '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-03-07 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-6720952-8951030', 'FH-WZ2Q-OYJP', NULL, NULL, 'admin', NULL, NULL, NULL, '08019072786', '731-3361', '広島県--広島市安佐北区あさひが丘8丁目', '上手康生', NULL,
        'JPY--8260.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド,Mサイズ（5-7泊/約66リットル）)', '1',
        NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-03-11 20:00:07', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-6748558-4599005', 'ED-0UIY-40IQ', NULL, NULL, 'admin', NULL, NULL, NULL, '', '132-0025', '東京都--江戸川区松江', '嶋崎尚', NULL, 'JPY--8260.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-21 20:00:03', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-6779533-4980665', 'M0-I6TZ-MSCV', NULL, NULL, 'admin', NULL, NULL, NULL, '09032638987', '574-0045', '大阪府--大東市太子田1-9-10', '石井薫', NULL,
        'JPY--1620.00', 'SAHASAHA 2019 着付け 着物 クリップ 衿止めクリップ 和装クリップ 扇子 花 3色セット (6個セット, 花)', '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-02-27 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-6931679-6223053', '3Q-YKCK-C573', NULL, NULL, 'admin', NULL, NULL, NULL, '08060157867', '038-3662', '青森県--北津軽郡板柳町板柳', '佐藤 嘉織', NULL,
        'JPY--8960.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー 拡張 ジッパー 全サイズ 有り (ローズゴールド アルミフレーム, 機内持込サイズ(1-3泊/約41 リットル）)', '1',
        NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-03-10 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-7103872-5928666', 'ED-0UIY-40IQ', NULL, NULL, 'admin', NULL, NULL, NULL, '', '336-0026', '埼玉県--さいたま市南区辻8-17-1 502', '檀崎友起子', NULL,
        'JPY--8260.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-02-11 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-7323177-9323849', 'GT-82OW-TXM4', NULL, NULL, 'admin', NULL, NULL, NULL, '07014014613', '252-0232', '神奈川県--相模原市中央区矢部3-7-4', '丸山亮子', NULL,
        'JPY--7660.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (シルバー, Ｓサイズ（3-5泊/約51リットル）)', '1',
        NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-10 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-7379171-2686214', 'UW-NJCD-KF9N', NULL, NULL, 'admin', NULL, NULL, NULL, '', '339-0032', '埼玉県--さいたま市岩槻区南下新井', '佐藤花', NULL,
        'JPY--8160.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-02-24 20:00:05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-7384897-7616603', 'WG-0DAJ-6HFY', NULL, NULL, 'admin', NULL, NULL, NULL, '09016796550', '651-0072', '兵庫県--神戸市中央区脇浜町1-4-40', '今出愛美', NULL,
        'JPY--8260.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (シルバー, Mサイズ（5-7泊/約66リットル）)', '1',
        NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-16 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-7507675-6755853', '0Y-K99Y-JMPZ', NULL, NULL, 'admin', NULL, NULL, NULL, '', '175-0094', '東京都--板橋区成増２−３７−２', '鈴木謙一', NULL,
        'JPY--9960.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-02-23 20:00:05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-7667909-5707068', 'FH-WZ2Q-OYJP', NULL, NULL, 'admin', NULL, NULL, NULL, '03−3726−3231', '145-0064', '東京都--大田区上池台５−３１−２０', '金子　恵利華',
        NULL, 'JPY--7960.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー 拡張 ジッパー 全サイズ 有り (ローズゴールド, Mサイズ（5-7泊/61（拡張時72）リットル）)', '1', NULL,
        NULL, NULL, NULL, NULL, 1, 'admin', '2018-12-26 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-7798366-4686205', 'VK-7V5A-H543', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド, 機内持込サイズ(1-3泊/約41リットル）)', '1', NULL, NULL,
        NULL, NULL, NULL, 1, 'admin', '2019-03-05 20:00:05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-7837617-1083812', 'P3-AXD1-32XQ', NULL, NULL, 'admin', NULL, NULL, NULL, '', '700-0956', '岡山県--岡山市南区当新田356-43', '北島直人', NULL,
        'JPY--999.00', 'SAHASAHA ワンタッチテント テント 3～5人用 ワンタッチ 2WAY テント 設営簡単 防災用 キャンプ用品 撥水加工 紫外�', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-01-01 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-8057603-5467849', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, '', '880-0001', '宮崎県--宮崎市橘通西', 'Crystal Pharaoh', NULL,
        'JPY--998.00', 'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-13 20:00:06', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-8302295-4396627', 'SN-43IS-TWEP', NULL, NULL, 'admin', NULL, NULL, NULL, '07026598535', '335-0016', '埼玉県--戸田市下前1-7-13 307', '渡辺 優花',
        NULL, 'JPY--9960.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド, Lサイズ（7泊以上/約86 リットル）)',
        '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-10 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-8363106-0263035', 'FH-WZ2Q-OYJP', NULL, NULL, 'admin', NULL, NULL, NULL, '09028063165', '213-0005', '神奈川県--川崎市高津区北見方', '小竿双葉', NULL,
        'JPY--8260.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド,Mサイズ（5-7泊/約66リットル）)', '1',
        NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-18 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-8368115-0454217', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, '', '675-1114', '兵庫県--加古郡稲美町国安1162-1', '大路　優子', NULL,
        'JPY--399.00', 'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-03 20:00:03', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-8445322-8450252', 'UW-NJCD-KF9N', NULL, NULL, 'admin', NULL, NULL, NULL, '', '928-0063', '石川県--輪島市水守町堂ノ端', '牧野 栄美', NULL, 'JPY--7660.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-03-07 20:00:05', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-8517352-0443063', 'ED-0UIY-40IQ', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド,Mサイズ（5-7泊/約66リットル）)', '1', NULL, NULL, NULL,
        NULL, NULL, 1, 'admin', '2019-03-03 20:00:05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-8612593-6731062', 'VK-7V5A-H543', NULL, NULL, 'admin', NULL, NULL, NULL, '', '144-0047', '東京都--大田区萩中', '竹花麻由', NULL, 'JPY--6980.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-21 20:00:02', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-8713220-4269422', 'FH-WZ2Q-OYJP', NULL, NULL, 'admin', NULL, NULL, NULL, '02019265192', '610-0302', '京都府--綴喜郡井手町井手', 'Supthone Saowanee',
        NULL, 'JPY--8260.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド,Mサイズ（5-7泊/約66リットル）)',
        '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-16 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-8864408-9951860', 'ED-0UIY-40IQ', NULL, NULL, 'admin', NULL, NULL, NULL, '', '906-0103', '沖縄県--宮古島市城辺福里377ｰ7番地E棟202', '長濱姫歌', NULL,
        'JPY--8260.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-03-08 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-8879469-1176650', 'SN-43IS-TWEP', NULL, NULL, 'admin', NULL, NULL, NULL, '0487260790', '362-0059', '埼玉県--上尾市大字平方3136-5', '奈良　美治', NULL,
        'JPY--12940.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー 拡張 ジッパー 全サイズ 有り (ローズゴールド, Lサイズ（7泊以上/88（拡張時102）リットル）)', '1', NULL,
        NULL, NULL, NULL, NULL, 1, 'admin', '2018-12-05 10:00:02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-9021736-3471028', 'ZQ-19XZ-EBKH', NULL, NULL, 'admin', NULL, NULL, NULL, '09096486241', '166-0001', '東京都--杉並区阿佐谷北3-28-19', '山下瑠菜', NULL,
        'JPY--13176.00', 'SAHASAHA スーツケース キャリーケース TSAロック 半鏡面仕上げ アライン加工 アルミフレーム レトロ 旅行 出張 軽量 静音 ファスナーレス 機内持込可 (XL, キャリージッパー-ローズゴールド)', '1', NULL, NULL,
        NULL, NULL, NULL, 1, 'admin', '2019-02-20 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-9150767-8818217', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, '', '491-0201', 'Aichi-ken--Ichinomya shi',
        'Manalang westy james', NULL, 'JPY--499.00', 'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '0', NULL, NULL, NULL, NULL, NULL, 1,
        'admin', '2019-01-12 20:00:02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-9232299-8766262', 'UW-NJCD-KF9N', NULL, NULL, 'admin', NULL, NULL, NULL, '', '630-0131', '奈良県--生駒市上町', '谷 春奈', NULL, 'JPY--7660.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-08 20:00:04', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-9309640-1601414', 'VK-7V5A-H543', NULL, NULL, 'admin', NULL, NULL, NULL, '', '891-0114', '鹿児島県--鹿児島市小松原', '弟子丸豪', NULL, 'JPY--6980.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-24 20:00:04', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-9418342-3886207', '5U-Y2SK-VDC5', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA ワンタッチテント テント 3～5人用 ワンタッチ 2WAY テント 3WAY テント 設営簡単 防災用 キャンプ用品 撥水加工 紫外線防止 登山 折りたたみ 防水 通気性 アウトドア 240*210*135cm (ブルー, 3～5人用)', '0', NULL,
        NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-01 20:00:07', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-9429329-9508652', 'Dustbin-white', NULL, NULL, 'admin', NULL, NULL, NULL, '09043719439', '892-0871', '鹿児島県--鹿児島市吉野町 9373-3', '外園翼生',
        NULL, 'JPY--4900.00', 'SAHASAH 赤外線センサー式全自動開閉式ステンレスごみ箱　近づくだけで自働的に開ける　清潔　インテリア　容�', '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-03-10 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-9469508-3912637', 'VK-7V5A-H543', NULL, NULL, 'admin', NULL, NULL, NULL, '', '244-0817', '神奈川県--横浜市戸塚区吉田町 1623-6', '大城里奈子', NULL,
        'JPY--6631.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-02-01 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-9572745-1047861', 'ED-0UIY-40IQ', NULL, NULL, 'admin', NULL, NULL, NULL, '', '906-0103', '沖縄県--宮古島市城辺福里377ｰ7番地E棟202', '長濱姫歌', NULL,
        'JPY--8260.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-02-05 20:00:02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-9647879-0807834', 'HM-B5BT-1IWO', NULL, NULL, 'admin', NULL, NULL, NULL, '09090525453', '573-0013', '大阪府--枚方市星丘4-7-11', '前崎　こず恵', NULL,
        'JPY--7660.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー 拡張 ジッパー 全サイズ 有り (ローズゴールド, Ｓサイズ（3-5泊/47（拡張時56）リットル）)', '1', NULL,
        NULL, NULL, NULL, NULL, 1, 'admin', '2018-12-19 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-9664274-4201414', 'ED-0UIY-40IQ', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド,Mサイズ（5-7泊/約66リットル）)', '1', NULL, NULL, NULL,
        NULL, NULL, 1, 'admin', '2019-03-11 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-9670522-0994216', 'TG-7U8Z-SBK2', NULL, NULL, 'admin', NULL, NULL, NULL, '0927143005', '810-0073', '福岡県--福岡市中央区舞鶴1-2-1', '日下', NULL,
        'JPY--17999.00', 'キクタニ 二胡 セミハードケース、弓付属 #73', '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-14 20:00:04', NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-9683178-6219855', 'VK-7V5A-H543', NULL, NULL, 'admin', NULL, NULL, NULL, '', '811-1311', '福岡県--南区横手二丁目36-1', '山内友紀菜', NULL,
        'JPY--7171.00', 'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSA', '0', NULL, NULL, NULL, NULL, NULL, 1, 'admin',
        '2019-02-01 20:00:04', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-9737261-1274261', '5J-EU14-VO30', NULL, NULL, 'admin', NULL, NULL, NULL, '0453241805', '220-0051', '神奈川県--横浜市西区中央', '四宮はな', NULL,
        'JPY--13870.00', 'SAHASAHA スーツケース キャリーケース TSAロック 半鏡面仕上げ アライン加工 アルミフレーム レトロ 旅行 出張 軽量 静音 ファスナーレス 機内持込可 (XL, アルミフレーム -ローズゴールド)', '1', NULL, NULL,
        NULL, NULL, NULL, 1, 'admin', '2019-01-23 20:00:05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-9868557-1343822', '52-EV2N-XBRO', NULL, NULL, 'admin', NULL, NULL, NULL, '0487348789', '344-0023', '埼玉県--春日部市大枝89', '宮本 千尋', NULL,
        'JPY--10260.00',
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド アルミフレーム 光沢, Mサイズ（5-7泊/約66 リットル）)', '1', NULL,
        NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-06 20:00:02', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-9922969-2577425', 'QU-6T21-ZKAH', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA マグネット式車載スマホ/タブレットホルダー 360度回転 粘着式 (シルバー)', '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-01-06 20:00:04', NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-9979035-4144650', 'VK-7V5A-H543', NULL, NULL, 'admin', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        'SAHASAHA スーツケース キャリーケース キャリーバッグ 機内持ち込みサイズから ファスナー 傷が目立ちにくい TSAロック ハードキャリー ジッパー 全サイズ 有り (ローズゴールド, 機内持込サイズ(1-3泊/約41リットル）)', '1', NULL, NULL,
        NULL, NULL, NULL, 1, 'admin', '2019-02-25 20:00:03', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('503-9991635-9182267', 'TG-7U8Z-SBK2', NULL, NULL, 'admin', NULL, NULL, NULL, '0927143005', '810-0073', '福岡県--福岡市中央区舞鶴1-2-1', '日下', NULL,
        'JPY--17999.00', 'キクタニ 二胡 セミハードケース、弓付属 #73', '1', NULL, NULL, NULL, NULL, NULL, 1, 'admin', '2019-02-16 20:00:04', NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, 0, 0, 0, 0, 0, 0),
       ('9', '6947503775353', NULL, 'default', 'user01', '', '', '', '', '', '', '', 'N', '0', '', '1', NULL, NULL, NULL, NULL, '0', 1, 'user01',
        '2018-11-13 00:57:48', '2018-11-13 00:57:48', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('DY06daff2d', NULL, NULL, 'xxx', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1,
        'user01', '2019-02-19 10:57:51', '2019-02-19 10:57:51', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('DY0d97460d', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1,
        'user01', '2019-02-21 17:33:07', '2019-02-21 17:33:07', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('DY2843543d', NULL, NULL, 'xxx', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1,
        'user01', '2019-02-21 17:30:05', '2019-02-21 17:30:05', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('DY3617b663', NULL, NULL, 'xx', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1,
        'user01', '2019-02-21 17:33:25', '2019-02-21 17:33:25', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('DY36784423', NULL, NULL, 'DYb41755c0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        1, 'user01', '2019-02-21 17:33:27', '2019-02-21 17:33:27', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('DY4676224b', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1,
        'user01', '2019-02-22 01:27:46', '2019-02-22 01:27:46', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('DY65d842c7', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1,
        'user01', '2019-02-21 17:30:13', '2019-02-21 17:30:13', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('asasaa', '444444', '1', '2197d62c526d', 'user01', '', '', '', '', '', '', '', 'N', '', '', '10', NULL, NULL, NULL, NULL, '0', 4, 'user01',
        '2018-11-18 23:27:39', '2018-11-18 23:37:52', 1, '0', '0', '0', '0', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('dsfsd', '6947503775353', NULL, 'default', 'user01', '', '', '', '', '', '', '', 'N', '0', '', '1', NULL, NULL, NULL, NULL, '0', 1, 'user01',
        '2018-11-13 01:12:32', '2018-11-13 01:12:32', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('kl;klkl', '6947503775353', NULL, 'default', 'user01', '', '', '', '', '', '', '', 'N', '0', '', '1', NULL, NULL, NULL, NULL, '0', 1,
        'user01', '2018-11-13 00:59:15', '2018-11-13 00:59:15', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('klklklk', '6947503775353', NULL, 'default', 'user01', '', '', '', '', '', '', '', 'N', '', '', '', NULL, NULL, NULL, NULL, '', 1, 'user01',
        '2018-11-13 01:09:12', '2018-11-13 01:09:12', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('opop', '6947503775353', NULL, 'default', 'user01', '', '', '', '', '', '', '', 'N', '0', '', '1', NULL, NULL, NULL, NULL, '0', 1, 'user01',
        '2018-11-13 01:07:44', '2018-11-13 01:07:44', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('ordno001', 'sku0004', NULL, '22', 'user01', 'xxx', 'xxx', 'xxx', 'xxx', 'xxx', 'xxx', 'xxx', 'N', '', NULL, '10', NULL, NULL, NULL, NULL,
        '222', 1, 'user01', '2018-12-02 22:41:33', '2018-12-02 22:41:33', 1, NULL, NULL, NULL, NULL, NULL, NULL, 16, 16323, -1, 2, 2202, 22020002),
       ('sdsfs', '6947503775353', NULL, 'default', 'user01', '', '', '', '', '', '', '', 'N', '0', '', '1', NULL, NULL, NULL, NULL, '0', 1, 'user01',
        '2018-11-13 01:04:58', '2018-11-13 01:04:58', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('test001', 'sku0004', NULL, '2197434257df', 'user01', 'xxx', 'xx', 'xx', 'xx', 'xx', 'xx', 'xx', 'N', '', 'xxx', '20', NULL, NULL, NULL, NULL,
        '2', 3, 'user01', '2018-11-30 22:33:53', '2018-11-30 22:55:48', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `l_sale_record`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_shelf`
--

DROP TABLE IF EXISTS `l_shelf`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8mb4;
CREATE TABLE `l_shelf`
(
  `id`       int(11) NOT NULL AUTO_INCREMENT,
  `shelf_no` varchar(50) DEFAULT NULL,
  `area`     varchar(20) DEFAULT NULL,
  `row_no`   varchar(20) DEFAULT NULL,
  `layer`    varchar(20) DEFAULT NULL,
  `line`     varchar(20) DEFAULT NULL,
  `enable`   varchar(1)  DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_shelf`
--

LOCK TABLES `l_shelf` WRITE;
/*!40000 ALTER TABLE `l_shelf`
  DISABLE KEYS */;
INSERT INTO `l_shelf`
VALUES (1, '@xx-null-xxxxxx-xxx', 'xx', NULL, 'xxxxxx', 'xxx', 'Y'),
       (2, '@1-2-3-4', '1', '2', '3', '4', 'Y');
/*!40000 ALTER TABLE `l_shelf`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_sku_mapping`
--

DROP TABLE IF EXISTS `l_sku_mapping`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8mb4;
CREATE TABLE `l_sku_mapping`
(
  `id`        int(11)   NOT NULL AUTO_INCREMENT,
  `dy_sku`    varchar(13) COLLATE utf8_bin  DEFAULT NULL,
  `sku`       varchar(20) COLLATE utf8_bin  DEFAULT NULL,
  `platform`  varchar(20) COLLATE utf8_bin  DEFAULT NULL,
  `create_on` timestamp NULL                DEFAULT NULL,
  `create_by` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 7
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_sku_mapping`
--

LOCK TABLES `l_sku_mapping` WRITE;
/*!40000 ALTER TABLE `l_sku_mapping`
  DISABLE KEYS */;
INSERT INTO `l_sku_mapping`
VALUES (5, '9540879835477', 'skutest001', '1', '2018-12-18 13:52:45', 'user01'),
       (6, '8950615433071', 'sku00001', '2', '2018-12-18 13:52:45', 'user01');
/*!40000 ALTER TABLE `l_sku_mapping`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_status`
--

DROP TABLE IF EXISTS `l_status`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8mb4;
CREATE TABLE `l_status`
(
  `id`   int(11) NOT NULL AUTO_INCREMENT,
  `seq`  int(11)                      DEFAULT NULL,
  `type` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `l_status_id_uindex` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 12
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_status`
--

LOCK TABLES `l_status` WRITE;
/*!40000 ALTER TABLE `l_status`
  DISABLE KEYS */;
INSERT INTO `l_status`
VALUES (1, 1, 'order', '已生成订单,未确认相关信息及提交出库'),
       (2, 2, 'order', '已确认相关信息,未提交出库'),
       (3, 3, 'order', '已提交出库'),
       (4, 4, 'order', '已出库'),
       (5, 5, 'order', '已完成'),
       (6, 6, 'order', '订单已拦截,您可重新编辑提交'),
       (7, 1, 'deliverbox', '新建'),
       (8, 2, 'deliverbox', '头程'),
       (9, 3, 'deliverbox', '入库待确认'),
       (10, 4, 'deliverbox', '已入库'),
       (11, 5, 'deliverbox', '已完成');
/*!40000 ALTER TABLE `l_status`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_store_info`
--

DROP TABLE IF EXISTS `l_store_info`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8mb4;
CREATE TABLE `l_store_info`
(
  `id`           int(11) NOT NULL AUTO_INCREMENT,
  `userId`       int(11)                       DEFAULT NULL,
  `sellerId`     varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `mwsAuthToken` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `secretKey`    varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `platform`     int(11)                       DEFAULT NULL,
  `enable`       varchar(1) COLLATE utf8_bin   DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_store_info`
--

LOCK TABLES `l_store_info` WRITE;
/*!40000 ALTER TABLE `l_store_info`
  DISABLE KEYS */;
INSERT INTO `l_store_info`
VALUES (1, 1, 'AOYEK60LBAPEN', 'AKIAJ7YDUM5NCBSW4ODA', 'SIOhohQJmYcqwCXGgP9t5ZlK9XoTQ3Dp0xWyUUsE', NULL, NULL),
       (2, 34, '', '', '', 0, 'N');
/*!40000 ALTER TABLE `l_store_info`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_user`
--

DROP TABLE IF EXISTS `l_user`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8mb4;
CREATE TABLE `l_user`
(
  `id`       int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `email`    varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `type`     varchar(45) COLLATE utf8_bin  DEFAULT NULL,
  `enable`   varchar(1) COLLATE utf8_bin   DEFAULT NULL,
  `inner_id` varchar(45) COLLATE utf8_bin  DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 43
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_user`
--

LOCK TABLES `l_user` WRITE;
/*!40000 ALTER TABLE `l_user`
  DISABLE KEYS */;
INSERT INTO `l_user`
VALUES (1, 'admin', '$2a$10$8XoXYzVCdH2o0VC5WFf3yOpqsDBRgIehj/gRrOikiE.8AErxE2XB6', NULL, NULL, 'Y', 'DY1'),
       (4, 'chenzeshenga', '$2a$10$8XoXYzVCdH2o0VC5WFf3yOpqsDBRgIehj/gRrOikiE.8AErxE2XB6', '222', '商户', 'Y', 'DY4'),
       (34, 'user01', '$2a$10$8XoXYzVCdH2o0VC5WFf3yOpqsDBRgIehj/gRrOikiE.8AErxE2XB6', 'chenzeshenga@163.com', '商户', 'Y', 'DY34'),
       (41, 'operator', '$2a$10$8XoXYzVCdH2o0VC5WFf3yOpqsDBRgIehj/gRrOikiE.8AErxE2XB6', 'test@11.com', '商户', 'Y', 'DY41'),
       (42, 'head_operator', '$2a$10$8XoXYzVCdH2o0VC5WFf3yOpqsDBRgIehj/gRrOikiE.8AErxE2XB6', NULL, '商户', 'Y', '');
/*!40000 ALTER TABLE `l_user`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_user_balance`
--

DROP TABLE IF EXISTS `l_user_balance`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8mb4;
CREATE TABLE `l_user_balance`
(
  `userId`         int(11) NOT NULL AUTO_INCREMENT,
  `userBalance`    varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `userBalanceJpy` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 89
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_user_balance`
--

LOCK TABLES `l_user_balance` WRITE;
/*!40000 ALTER TABLE `l_user_balance`
  DISABLE KEYS */;
INSERT INTO `l_user_balance`
VALUES (88, NULL, NULL);
/*!40000 ALTER TABLE `l_user_balance`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_user_info`
--

DROP TABLE IF EXISTS `l_user_info`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8mb4;
CREATE TABLE `l_user_info`
(
  `userId`       int(11) NOT NULL,
  `userAddress`  varchar(450) COLLATE utf8_bin DEFAULT NULL,
  `name`         varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '公司名称',
  `addr`         varchar(450) COLLATE utf8_bin DEFAULT NULL,
  `mainContacts` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `telephone`    varchar(45) COLLATE utf8_bin  DEFAULT NULL,
  `contact`      varchar(45) COLLATE utf8_bin  DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_user_info`
--

LOCK TABLES `l_user_info` WRITE;
/*!40000 ALTER TABLE `l_user_info`
  DISABLE KEYS */;
INSERT INTO `l_user_info`
VALUES (1, '', 'klklkl', 'qqq', '', '', NULL),
       (34, '', '', '', '', '', NULL),
       (88, NULL, NULL, NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `l_user_info`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_volume_weight`
--

DROP TABLE IF EXISTS `l_volume_weight`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8mb4;
CREATE TABLE `l_volume_weight`
(
  `id`       int(11) NOT NULL AUTO_INCREMENT,
  `refno`    varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `ref_type` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `length`   double                       DEFAULT NULL,
  `width`    double                       DEFAULT NULL,
  `height`   double                       DEFAULT NULL,
  `weight`   double                       DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_volume_weight`
--

LOCK TABLES `l_volume_weight` WRITE;
/*!40000 ALTER TABLE `l_volume_weight`
  DISABLE KEYS */;
/*!40000 ALTER TABLE `l_volume_weight`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_warehousing`
--

DROP TABLE IF EXISTS `l_warehousing`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8mb4;
CREATE TABLE `l_warehousing`
(
  `parent_sku`                 varchar(40) NOT NULL,
  `owner`                      varchar(45) NOT NULL,
  `deliver_status`             varchar(20) DEFAULT NULL,
  `method`                     varchar(20) DEFAULT NULL,
  `create_on`                  datetime    DEFAULT CURRENT_TIMESTAMP,
  `update_on`                  datetime    DEFAULT CURRENT_TIMESTAMP,
  `create_by`                  varchar(45) DEFAULT NULL,
  `update_by`                  varchar(45) DEFAULT NULL,
  `insurance`                  varchar(45) DEFAULT NULL,
  `head`                       varchar(45) DEFAULT NULL,
  `track_no`                   varchar(45) DEFAULT NULL,
  `clearance_type`             varchar(45) DEFAULT NULL,
  `estimated_date`             datetime    DEFAULT CURRENT_TIMESTAMP,
  `deliver_way`                varchar(30) DEFAULT NULL,
  `operation_type`             varchar(30) DEFAULT NULL,
  `fee_1`                      varchar(20) DEFAULT NULL,
  `fee_2`                      varchar(20) DEFAULT NULL,
  `fee_3`                      varchar(20) DEFAULT NULL,
  `fee_4`                      varchar(20) DEFAULT NULL,
  `fee_5`                      varchar(20) DEFAULT NULL,
  `fee_6`                      varchar(20) DEFAULT NULL,
  `fee_7`                      varchar(20) DEFAULT NULL,
  `fee_8`                      varchar(20) DEFAULT NULL,
  `fee_a`                      varchar(20) DEFAULT NULL,
  `fee_d`                      varchar(20) DEFAULT NULL,
  `fee_c`                      varchar(20) DEFAULT NULL,
  `fee_b`                      varchar(20) DEFAULT NULL,
  `fee_e`                      varchar(20) DEFAULT NULL,
  `channel`                    varchar(20) DEFAULT NULL,
  `estimate_date_to_warehouse` datetime    DEFAULT CURRENT_TIMESTAMP,
  `tax_type`                   varchar(20) DEFAULT NULL,
  `carrier`                    varchar(50) DEFAULT NULL,
  `fee_1_num`                  varchar(20) DEFAULT NULL,
  `fee_2_num`                  varchar(20) DEFAULT NULL,
  `fee_3_num`                  varchar(20) DEFAULT NULL,
  `fee_4_num`                  varchar(20) DEFAULT NULL,
  `fee_5_num`                  varchar(20) DEFAULT NULL,
  `fee_6_num`                  varchar(20) DEFAULT NULL,
  `fee_7_num`                  varchar(20) DEFAULT NULL,
  `fee_8_num`                  varchar(20) DEFAULT NULL,
  `fee_a_num`                  varchar(20) DEFAULT NULL,
  `fee_b_num`                  varchar(20) DEFAULT NULL,
  `fee_c_num`                  varchar(20) DEFAULT NULL,
  `fee_d_num`                  varchar(20) DEFAULT NULL,
  `fee_e_num`                  varchar(20) DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_warehousing`
--

LOCK TABLES `l_warehousing` WRITE;
/*!40000 ALTER TABLE `l_warehousing`
  DISABLE KEYS */;
INSERT INTO `l_warehousing`
VALUES ('DY003e4c1c', 'user01', '1', '', '2019-02-06 19:01:47', '2019-02-14 23:38:27', 'user01', 'user01', 'N', '2', 'xx', '2', '2019-02-09 00:00:00',
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CHL0008', NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('DY12379a54', 'user01', '7', '2', '2019-02-06 19:03:38', '2019-02-18 22:27:59', 'user01', 'operator', 'N', '2', 'xxxx', '2', NULL, '集装箱卡车派送',
        '普货入库', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'N', 'Y', 'N', 'N', 'Y', 'N', 'CHL0008', NULL, '2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, '200', '500', NULL, NULL, NULL),
       ('DY76861bc2', 'user01', '1', '', '2019-02-16 15:16:32', '2019-02-16 15:16:32', 'user01', 'user01', 'N', '2', '7897987398472983', '2',
        '2019-02-16 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CHL0008', NULL, '2', '顺丰',
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('DYbc8947d5', 'user01', '1', '', '2019-02-16 15:19:40', '2019-02-16 15:19:40', 'user01', 'user01', 'N', '2', '123213143', '2',
        '2019-02-23 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CHL0008', NULL, '2', '德邦',
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('DYfcb5e0ea', 'user01', '1', '', '2019-02-16 15:21:34', '2019-02-16 15:21:34', 'user01', 'user01', 'N', '2', '8932849287', '2',
        '2019-02-23 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CHL0008', NULL, '2', '顺丰',
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('DYaa853c30', 'user01', '1', '', '2019-02-16 15:26:53', '2019-02-21 00:06:04', 'user01', 'user01', 'N', '2', '2389462648', '2',
        '2019-02-23 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '-1', NULL, '2', '顺丰', NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('DY44605a27', 'user01', '1', '4', '2019-02-20 20:35:09', '2019-02-21 00:12:15', 'user01', 'user01', '30', '1', '', '2', NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CHL0006', NULL, '2', '', NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL),
       ('DY2b82c128', 'user01', '1', '4', '2019-02-22 01:03:44', '2019-02-22 01:03:44', 'user01', 'user01', 'N', '1', '', '2', NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'CHL0006', NULL, '2', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL),
       ('DYbd55f0ce', 'user01', '1', '', '2019-02-28 21:25:15', '2019-02-28 21:25:15', 'user01', 'user01', 'N', '2', 'xx', '2', '2019-03-01 00:00:00',
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', NULL, '2', 'xxx', NULL, NULL, NULL, NULL, NULL,
        NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `l_warehousing`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_warehousing_content`
--

DROP TABLE IF EXISTS `l_warehousing_content`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8mb4;
CREATE TABLE `l_warehousing_content`
(
  `parent_sku`   varchar(40) COLLATE utf8_bin NOT NULL,
  `sku`          varchar(45) COLLATE utf8_bin NOT NULL,
  `box_seq`      varchar(20) COLLATE utf8_bin   DEFAULT NULL,
  `num`          varchar(20) COLLATE utf8_bin   DEFAULT NULL,
  `defect_num`   varchar(20) COLLATE utf8_bin   DEFAULT NULL,
  `normal_num`   varchar(20) COLLATE utf8_bin   DEFAULT NULL,
  `wrap_type`    varchar(45) COLLATE utf8_bin   DEFAULT NULL,
  `user_confirm` varchar(2) COLLATE utf8_bin    DEFAULT NULL,
  `img_path`     varchar(2000) COLLATE utf8_bin DEFAULT NULL,
  `dy_sku`       varchar(20) COLLATE utf8_bin   DEFAULT NULL,
  `comments`     varchar(2000) COLLATE utf8_bin DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_warehousing_content`
--

LOCK TABLES `l_warehousing_content` WRITE;
/*!40000 ALTER TABLE `l_warehousing_content`
  DISABLE KEYS */;
INSERT INTO `l_warehousing_content`
VALUES ('DY003e4c1c', 'sku00001', '1', '12', NULL, NULL, '自带包装', NULL, NULL, '8950615433071', NULL),
       ('DY76861bc2', '121211', '1', '12', NULL, NULL, '自带包装', NULL, NULL, '1281211134862', NULL),
       ('DYbc8947d5', 'sku0002', '1', '12', NULL, NULL, '自带包装', NULL, NULL, '0082316348939', NULL),
       ('DYbc8947d5', 'sku0002', '2', '12', NULL, NULL, '自带包装', NULL, NULL, '0082316348939', NULL),
       ('DYfcb5e0ea', 'sku0002', '1', '12', NULL, NULL, '自带包装', NULL, NULL, '0082316348939', NULL),
       ('DYfcb5e0ea', 'sku0002', '2', '12', NULL, NULL, '自带包装', NULL, NULL, '0082316348939', NULL),
       ('DY12379a54', 'sku00001', '1', '12', '2', '10', '自带包装', 'Y',
        'F:/project/tmp/warehousing/upload/DY12379a54/QQ浏览器截图_20180206214851_D57E3F203B584a9c8B8F0D80E031379A.jpg', '8950615433071', 'xxxxxxxx'),
       ('DYaa853c30', 'sku0002', '1', '12', NULL, NULL, '自带包装', NULL, NULL, '0082316348939', NULL),
       ('DYaa853c30', 'sku0002', '2', '123', NULL, NULL, '自带包装', NULL, NULL, '0082316348939', NULL),
       ('DY2b82c128', 'sku0002', '1', '10', NULL, NULL, '自带包装', NULL, NULL, '0082316348939', NULL),
       ('DY2b82c128', 'sku0002', '2', '15', NULL, NULL, '自带包装', NULL, NULL, '0082316348939', NULL),
       ('DYbd55f0ce', 'sku0002', '1', '12', NULL, NULL, '自带包装', NULL, NULL, '0082316348939', NULL);
/*!40000 ALTER TABLE `l_warehousing_content`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'logistics'
--

--
-- Dumping routines for database 'logistics'
--
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2019-03-18  7:11:39
