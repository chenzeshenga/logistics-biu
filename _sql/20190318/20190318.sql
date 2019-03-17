-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: 47.105.107.242    Database: logistics-prod
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
-- Table structure for table `l_channel`
--

DROP TABLE IF EXISTS `l_channel`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8mb4;
CREATE TABLE `l_channel`
(
  `id`                      int(11)        DEFAULT NULL,
  `channel_code`            tinytext,
  `channel_name`            tinytext,
  `cooperator_code`         tinytext,
  `currency_code`           tinytext,
  `min_days`                int(11)        DEFAULT NULL,
  `max_days`                int(11)        DEFAULT NULL,
  `weight_rule_code`        int(11)        DEFAULT NULL,
  `weight_multi`            int(11)        DEFAULT NULL,
  `default_package_weight`  double         DEFAULT NULL,
  `default_package_fee`     double         DEFAULT NULL,
  `fee_code_rule`           int(11)        DEFAULT NULL,
  `whether_multi_package`   tinytext,
  `whether_volume`          tinytext,
  `whether_charge_at_first` tinytext,
  `whether_charge_for_them` tinytext,
  `whether_insurance`       tinytext,
  `enable`                  tinytext,
  `remarks`                 varchar(500)   DEFAULT NULL,
  `update_on`               timestamp NULL DEFAULT NULL,
  `create_by`               tinytext,
  `update_by`               tinytext,
  `create_on`               timestamp NULL DEFAULT NULL,
  `channel_adapter`         tinytext
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_channel`
--

LOCK TABLES `l_channel` WRITE;
/*!40000 ALTER TABLE `l_channel`
  DISABLE KEYS */;
INSERT INTO `l_channel`
VALUES (10, 'CHL0009', '虚拟海外仓转发-日邮派送', '1', 'JPY', 1, 3, 3, 0, 0, 0, 3, 'N', 'N', 'Y', 'N', 'N', 'Y', '', '2019-02-21 17:17:00', 'admin', 'admin',
        '2019-02-21 17:10:11', '虚拟海外仓转发'),
       (11, 'CHL0011', '虚拟海外仓转发-空加派', '1', 'CNY', 3, 7, 3, 0, 0, 0, 3, 'N', 'N', 'Y', 'N', 'N', 'Y', '', '2019-02-21 17:16:48', 'admin', 'admin',
        '2019-02-21 17:11:35', '虚拟海外仓转发');
/*!40000 ALTER TABLE `l_channel`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_label`
--

DROP TABLE IF EXISTS `l_label`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8mb4;
CREATE TABLE `l_label`
(
  `id`    int(11) DEFAULT NULL,
  `key`   tinytext,
  `value` tinytext
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_label`
--

LOCK TABLES `l_label` WRITE;
/*!40000 ALTER TABLE `l_label`
  DISABLE KEYS */;
INSERT INTO `l_label`
VALUES (1, 'cooperator_1', '日本邮政'),
       (2, 'cooperator_2', '佐川急便'),
       (3, 'cooperator_3', '黑猫运输'),
       (4, 'cooperator_4', '西浓运输'),
       (5, 'cooperator_5', '卡车派送'),
       (6, 'cooperator_6', '其他'),
       (7, 'currency_CNY', '人民币'),
       (8, 'currency_USD', '美金'),
       (9, 'currency_JPY', '日元'),
       (10, 'weightRule_1', '重量*系数'),
       (11, 'weightRule_2', '重量+包装'),
       (12, 'weightRule_3', '人工设置'),
       (13, 'feeRule_1', '人工输入'),
       (14, 'feeRule_2', '后台计算'),
       (15, 'feeRule_3', '后台计算+人工输入'),
       (16, 'head_1', '东岳头程'),
       (17, 'head_2', '其他头程'),
       (18, 'method_1', '海运整柜20GP/HQ'),
       (19, 'method_2', '海运整柜40GP/HQ'),
       (20, 'method_3', '海运散货'),
       (21, 'method_4', '空运'),
       (22, 'method_5', '卡车派送'),
       (23, 'method_6', '其他'),
       (24, 'size_1', '小件(三边和60cm以下，最长边34cm以下，厚度3cm以下，重量1kg以下)'),
       (25, 'size_2', '标准件(三边和160cm以下，重量25kg以下)'),
       (26, 'size_3', '大件(三边和160cm以上，重量50kg以下)'),
       (27, 'size_4', '其他'),
       (28, 'order_1', '海外仓代发订单'),
       (29, 'order_2', '特色小包'),
       (30, 'status_1', '已生成订单,未确认相关信息及提交出库'),
       (31, 'status_2', '已确认相关信息,未提交出库'),
       (32, 'status_3', '已提交出库'),
       (33, 'status_4', '已出库'),
       (34, 'status_5', '已完成'),
       (35, 'status_6', '订单已拦截,您可重新编辑提交'),
       (36, 'platform_1', '亚马逊'),
       (37, 'platform_2', '乐天'),
       (38, 'platform_3', '雅虎'),
       (39, 'platform_4', '沃尔玛'),
       (40, 'category_1', '海外仓代发订单'),
       (41, 'category_2', '特色小包'),
       (42, 'carrier_2', '佐川急便'),
       (43, 'carrier_3', '黑猫运输'),
       (44, 'carrier_4', '西浓运输'),
       (45, 'carrier_5', '卡车派送'),
       (46, 'ord_status_1', '待审核'),
       (47, 'ord_status_2', '待发货'),
       (48, 'ord_status_3', '已发货'),
       (49, 'ord_status_4', '问题件'),
       (50, 'ord_status_5', '已废弃'),
       (51, 'ord_status_6', '暂存'),
       (52, 'ord_status_7', '历史'),
       (53, 'deal_with_1', '重新上架'),
       (54, 'deal_with_2', '转运'),
       (55, 'deal_with_3', '销毁'),
       (56, 'deal_with_4', '不良品'),
       (57, 'classification_1', '小物'),
       (58, 'classification_2', '服装'),
       (59, 'classification_3', '户外运动'),
       (60, 'classification_4', '电子产品'),
       (61, 'classification_5', '家居用品'),
       (62, 'classification_6', '玩具'),
       (63, 'classification_7', '易碎品'),
       (64, 'classification_8', '大件'),
       (65, 'classification_9', '其他'),
       (66, 'warehousing_1', '新建'),
       (67, 'warehousing_2', '前置海外仓'),
       (68, 'warehousing_3', '头程收货待转运'),
       (69, 'warehousing_4', '转运中'),
       (70, 'warehousing_5', '海外仓入库清点'),
       (71, 'warehousing_6', '入库清点完成'),
       (72, 'warehousing_7', '海外仓上架完成'),
       (73, 'warehousing_8', '废弃'),
       (74, 'warehousing_9', '异常'),
       (75, 'clearanceType_1', '单独退税报关'),
       (76, 'clearanceType_2', '无退税报关'),
       (77, 'taxType_1', '预缴关税'),
       (78, 'taxType_2', '实报实销');
/*!40000 ALTER TABLE `l_label`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_manual_order`
--

DROP TABLE IF EXISTS `l_manual_order`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8mb4;
CREATE TABLE `l_manual_order`
(
  `id`             int(11)        DEFAULT NULL,
  `ord_no`         varchar(255)   DEFAULT NULL,
  `category`       varchar(255)   DEFAULT NULL,
  `carrier_no`     varchar(255)   DEFAULT NULL,
  `track_no`       varchar(255)   DEFAULT NULL,
  `owner`          varchar(255)   DEFAULT NULL,
  `status`         varchar(255)   DEFAULT NULL,
  `update_on`      timestamp NULL DEFAULT NULL,
  `create_on`      timestamp NULL DEFAULT NULL,
  `create_by`      varchar(255)   DEFAULT NULL,
  `update_by`      varchar(255)   DEFAULT NULL,
  `from_ken_id`    varchar(255)   DEFAULT NULL,
  `from_city_id`   varchar(255)   DEFAULT NULL,
  `from_town_id`   varchar(255)   DEFAULT NULL,
  `from_where`     varchar(255)   DEFAULT NULL,
  `from_zipcode`   varchar(255)   DEFAULT NULL,
  `to_ken_id`      varchar(255)   DEFAULT NULL,
  `to_city_id`     varchar(255)   DEFAULT NULL,
  `to_town_id`     varchar(255)   DEFAULT NULL,
  `to_where`       varchar(255)   DEFAULT NULL,
  `to_zipcode`     varchar(255)   DEFAULT NULL,
  `on_behalf`      varchar(255)   DEFAULT NULL,
  `amount`         varchar(255)   DEFAULT NULL,
  `china_no`       varchar(255)   DEFAULT NULL,
  `from_contact`   varchar(255)   DEFAULT NULL,
  `to_contact`     varchar(255)   DEFAULT NULL,
  `from_whom`      varchar(255)   DEFAULT NULL,
  `to_whom`        varchar(255)   DEFAULT NULL,
  `channel`        varchar(255)   DEFAULT NULL,
  `abnormalReason` varchar(2000)  DEFAULT NULL,
  UNIQUE KEY `l_manual_order_ord_no_uindex` (`ord_no`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_manual_order`
--

LOCK TABLES `l_manual_order` WRITE;
/*!40000 ALTER TABLE `l_manual_order`
  DISABLE KEYS */;
INSERT INTO `l_manual_order`
VALUES (16, 'DYb337f6bb', '1', '3', '23', 'user01', '7', '2019-02-28 12:22:41', '2019-02-24 16:50:56', 'user01', NULL, '9', '9203', '92030043',
        'xxxxxxxxxx', 'xxxxxxxx', '9', '9206', '92060125', 'xxxxxxxx', 'xxxxxxx', 'false', NULL, 'xxxxxx', 'xxxxxxxxxx', 'xxxxxxxxxx', 'user01',
        'xxxxxxxxxxxx', 'CHL0011', NULL),
       (18, 'DY25ceaa9f', '1', '3', '', 'user01', '4', '2019-02-28 12:22:09', '2019-02-27 03:13:16', 'user01', NULL, '9', '9203', '92030043', '', '',
        '9', '9206', '92060125', '', '', 'false', NULL, '', '', '', 'user01', '', 'CHL0011', NULL),
       (19, 'DY2137af36816a48c4a8688019603797ef', '1', '3', 'xxxxxx', 'admin', '5', '2019-03-11 14:32:30', '2019-03-11 14:32:30', 'admin', 'admin',
        '9', '9203', '92030043', 'xxxxxxx', 'xxxxxxxxx', '9', '9206', '92060125', 'xxxxx', 'xxxxx', 'false', 'DY2137af36816a48c4a8688019603797ef',
        'xxxxxxx', 'xxxxxxx', 'xxxxx', 'xxxxxx', 'xxxx', 'CHL0011', NULL),
       (NULL, 'DY72474e393c42436884e1d9f6cfc361e1', '1', '5', 'DY37045cd7db4b4f4c9dedec4694a3395d', 'admin', '3', '2019-03-16 08:54:35',
        '2019-03-13 14:48:46', 'admin', 'admin', '9', '9203', '92030043', 'xx', 'xx', '9', '9206', '92060125', 'xx', 'xx', 'false',
        'DY72474e393c42436884e1d9f6cfc361e1', '80005387728', 'xx', 'xx', 'user01', 'xxx', 'CHL0011', NULL),
       (NULL, 'DY3221de91', '1', '3', 'DY5263de48d2824a9983bedec04d3abff4', 'user01', '7', '2019-03-15 15:13:21', '2019-02-24 16:47:52', 'user01',
        'admin', '9', '9203', '92030043', 'xxxxxxxxxxx', 'xxxxxxxxxxxxx', '9', '9206', '92060125', 'xxxxxxxxxx', 'xxxxx', 'false', 'DY3221de91',
        'xxxx', 'xxxxxxxxxx', 'xxxxxxxxxx', 'user01', 'xxxxxxxx', 'CHL0011', NULL),
       (NULL, 'DYbb2c975be82b4ffdaec56acd8b3e15a6', '1', '3', 'DYe948181f93aa48c787d95777514a3d7b', 'admin', '4', '2019-03-15 15:59:48',
        '2019-03-15 15:59:48', 'admin', 'admin', '5', '5368', '53680004', '11', '11', '1', '1436', '14360011', 'aaaa', 'aaaaaa', 'false',
        'DYbb2c975be82b4ffdaec56acd8b3e15a6', '11', '11', '11', '11', '11', 'CHL0009', '缺货'),
       (NULL, 'DYc798aef7cd364f0c8e1bd6144eed16d3', '1', '3', 'DYa55dd811687c4785a5dd942d9cbba08c', 'admin', '2', '2019-03-16 06:29:38',
        '2019-03-16 06:29:38', 'admin', 'admin', '', '', '', '日本岡山仓(okayama)', '', '5', '5203', '52030098', 'xx', 'xx', 'false',
        'DYc798aef7cd364f0c8e1bd6144eed16d3', 'xxx', '', 'xx', '东岳物流', 'xx', 'CHL0009', NULL),
       (NULL, 'DY5d8f22d303444aeab377945f9e4bbf3a', '1', '3', '10', 'user01', '1', '2019-03-17 16:15:39', '2019-03-16 09:09:10', 'user01', 'admin',
        '', '', '', '日本岡山仓(okayama)', '', '1', '1436', '14360001', 'xx', 'xx', 'false', 'DY5d8f22d303444aeab377945f9e4bbf3a', '', '', 'xx', '东岳物流',
        'xx', 'CHL0009', NULL),
       (NULL, 'DY37ea92c27b304255acd75771bdb4c0b9', '1', '4', 'DY9bbdff82c7a54f18a209a8f4d917cfbb', 'user01', '1', '2019-03-17 07:57:01',
        '2019-03-16 09:13:12', 'user01', 'admin', '', '', '', '日本岡山仓(okayama)', '', '11', '11201', '112010017', 'xx', 'xx', 'false',
        'DY37ea92c27b304255acd75771bdb4c0b9', '', '', 'xx', '东岳物流', 'xxx', 'CHL0009', NULL),
       (NULL, 'DY7b97610e63ae4beb9e6603b1c643723b', '1', '5', 'DYb9511b2693b34d98b9a0627c422de560', 'admin', '4', '2019-03-16 11:32:24',
        '2019-03-16 11:31:20', 'admin', 'admin', '', '', '', '日本岡山仓(okayama)', '', '5', '5368', '53680001', 'xx', 'xx', 'false',
        'DY7b97610e63ae4beb9e6603b1c643723b', '', '', 'xxx', '东岳物流', 'xxx', 'CHL0009', '缺货'),
       (NULL, 'DY58a6987adcf2493d95e1dbd1dd85ae07', '1', '4', 'DYc54d01d9d32143a293669cc06bdf3e23', 'admin', '1', '2019-03-17 07:57:01',
        '2019-03-16 13:53:32', 'admin', 'admin', '', '', '', '日本岡山仓(okayama)', '', '9', '9203', '92030043', 'xxx', 'xxx', 'false',
        'DY58a6987adcf2493d95e1dbd1dd85ae07', '', '', 'xxx', '东岳物流', 'xxx', 'CHL0009', NULL);
/*!40000 ALTER TABLE `l_manual_order`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_manual_order_content`
--

DROP TABLE IF EXISTS `l_manual_order_content`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8mb4;
CREATE TABLE `l_manual_order_content`
(
  `ordno`     varchar(255) DEFAULT NULL,
  `sku`       varchar(255) DEFAULT NULL,
  `name`      varchar(255) DEFAULT NULL,
  `num`       varchar(255) DEFAULT NULL,
  `price`     varchar(255) DEFAULT NULL,
  `picked`    varchar(255) DEFAULT NULL,
  `satisfied` varchar(255) DEFAULT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_manual_order_content`
--

LOCK TABLES `l_manual_order_content` WRITE;
/*!40000 ALTER TABLE `l_manual_order_content`
  DISABLE KEYS */;
INSERT INTO `l_manual_order_content`
VALUES ('DY7f70a288', '121211', '1212', '12', '111', NULL, NULL),
       ('DY0e08ea33', 'sku00001', 'skjfk', '12', '9', NULL, NULL),
       ('DYa27fdda4', 'sku00001', '', '7', '0', NULL, NULL),
       ('DY39c6f28c', 'sku00001', 'skjfk', '10', '9', NULL, NULL),
       ('DYb41755c0', 'sdfsdf', 'aaa', '5', '22', NULL, NULL),
       ('DY25ceaa9f', 'sku00001', 'skjfk', '1', '9', NULL, NULL),
       ('DY25ceaa9f', 'sku00001', 'skjfk', '1', '9', NULL, NULL),
       ('DYb337f6bb', 'sku0002', '箱子', '12', '8', '12', '1'),
       ('DYb337f6bb', 'sku0002', '箱子', '12', '8', '11', '0'),
       ('DYc798aef7cd364f0c8e1bd6144eed16d3', 'sdfsdf/3601324744525', 'aaa', '1', '22', NULL, '0'),
       ('DY5d8f22d303444aeab377945f9e4bbf3a', 'dd/3601324744525', 'aaa', '1', '22', NULL, '0'),
       ('DY37ea92c27b304255acd75771bdb4c0b9', 'sdfsdf/3601324744525', 'aaa', '1', '22', NULL, '0'),
       ('DY3221de91', 'sku0002', '箱子', '23', '8', '23', '1'),
       ('DY3221de91', 'sku0002', '箱子', '23', '8', '23', '1'),
       ('DY72474e393c42436884e1d9f6cfc361e1', 'dd/3601324744525', 'aaa', '1', '22', '1', '1'),
       ('DYbb2c975be82b4ffdaec56acd8b3e15a6', 'sdfsdf/3601324744525', 'aaa', '1', '22', '0', '0'),
       ('DY7b97610e63ae4beb9e6603b1c643723b', '121211/1281211134862', '1212', '4', '111', '3', '0'),
       ('DY58a6987adcf2493d95e1dbd1dd85ae07', 'sdfsdf/3601324744525', 'aaa', '5', '22', NULL, '0'),
       ('DY58a6987adcf2493d95e1dbd1dd85ae07', '1212444/1281211134862', '1212', '5', '111', NULL, '0');
/*!40000 ALTER TABLE `l_manual_order_content`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_productions`
--

DROP TABLE IF EXISTS `l_productions`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8mb4;
CREATE TABLE `l_productions`
(
  `sku`            varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `dy_sku`         varchar(13) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '1234567890123',
  `name`           varchar(1000) COLLATE utf8_bin                           DEFAULT NULL,
  `classification` varchar(300) CHARACTER SET utf8 COLLATE utf8_bin         DEFAULT NULL,
  `color`          varchar(45) CHARACTER SET utf8 COLLATE utf8_bin          DEFAULT NULL,
  `size`           varchar(45) CHARACTER SET utf8 COLLATE utf8_bin          DEFAULT NULL,
  `length`         double                                                   DEFAULT NULL,
  `width`          double                                                   DEFAULT NULL,
  `height`         double                                                   DEFAULT NULL,
  `weight`         double                                                   DEFAULT NULL,
  `createdBy`      varchar(45) CHARACTER SET utf8 COLLATE utf8_bin          DEFAULT NULL,
  `updateBy`       varchar(45) CHARACTER SET utf8 COLLATE utf8_bin          DEFAULT NULL,
  `value`          varchar(45) COLLATE utf8_bin                             DEFAULT NULL,
  `location`       varchar(45) CHARACTER SET utf8 COLLATE utf8_bin          DEFAULT NULL,
  `img_path`       varchar(200) CHARACTER SET utf8 COLLATE utf8_bin         DEFAULT NULL,
  `alert_num`      int(11)                                                  DEFAULT NULL COMMENT '缺货预警数目',
  `create_on`      datetime                                                 DEFAULT NULL,
  `update_on`      datetime                                                 DEFAULT NULL,
  `ava_num`        int(11)                                                  DEFAULT NULL COMMENT '该商品可售数量,该项为商品在仓库中的所有批次的商品数量之和',
  PRIMARY KEY (`sku`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_productions`
--

LOCK TABLES `l_productions` WRITE;
/*!40000 ALTER TABLE `l_productions`
  DISABLE KEYS */;
INSERT INTO `l_productions`
VALUES ('121211', '1281211134862', '1212', '0', '', '2', 12, 1, 1, 1, 'user01', 'user01', '111', '',
        '/opt/web/productdesc/fe8495ca58d64de6af92ac003a905973QQ浏览器截图_20180109235724_5640D09F303C4184B6D3FF2069398BEE.jpg', 23, '2018-12-07 01:23:51',
        '2018-12-31 23:49:27', 1041),
       ('1212444', '1281211134862', '1212', '0', '', '2', 12, 1, 1, 1, 'user01', 'user01', '111', '',
        '/opt/web/productdesc/fe8495ca58d64de6af92ac003a905973QQ浏览器截图_20180109235724_5640D09F303C4184B6D3FF2069398BEE.jpg', 23, '2018-12-07 01:23:51',
        '2018-12-31 23:49:27', 1041),
       ('dd', '3601324744525', 'aaa', '0', '', '', 2, 2, 2, 2, 'user01', 'user01', '22', '', '', 0, '2018-12-07 01:27:51', '2018-12-31 23:21:18', 0),
       ('sdfsdf', '3601324744525', 'aaa', '0', '', '', 2, 2, 2, 2, 'user01', 'user01', '22', '', '', 0, '2018-12-07 01:27:51', '2018-12-31 23:21:18',
        0);
/*!40000 ALTER TABLE `l_productions`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `l_track_code`
--

DROP TABLE IF EXISTS `l_track_code`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8mb4;
CREATE TABLE `l_track_code`
(
  `id`             int(11) NOT NULL AUTO_INCREMENT,
  `carrier_no`     varchar(255) DEFAULT NULL,
  `min_track_code` mediumtext,
  `max_track_code` mediumtext,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 17
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `l_track_code`
--

LOCK TABLES `l_track_code` WRITE;
/*!40000 ALTER TABLE `l_track_code`
  DISABLE KEYS */;
INSERT INTO `l_track_code`
VALUES (7, '4', '20000004', '20001000'),
       (10, '4', '7', '13'),
       (11, '4', '20000005', '20001008'),
       (12, '3', '10', '19'),
       (13, '2', '20000000', '20001000'),
       (14, '3', '20000005', '20001008'),
       (15, '3', '20000000', '20001000'),
       (16, '3', '20000002', '20001000');
/*!40000 ALTER TABLE `l_track_code`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_perm`
--

DROP TABLE IF EXISTS `sys_perm`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8mb4;
CREATE TABLE `sys_perm`
(
  `pval`    varchar(50) NOT NULL COMMENT '权限值，shiro的权限控制表达式',
  `parent`  varchar(25)      DEFAULT NULL COMMENT '父权限id',
  `pname`   varchar(50)      DEFAULT NULL COMMENT '权限名称',
  `ptype`   int(3)           DEFAULT NULL COMMENT '权限类型：1.菜单 2.按钮 3.接口 4.特殊',
  `leaf`    tinyint(1)       DEFAULT NULL COMMENT '是否叶子节点',
  `created` timestamp   NULL DEFAULT NULL COMMENT '创建时间',
  `updated` timestamp   NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`pval`),
  UNIQUE KEY `pval` (`pval`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='权限';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_perm`
--

LOCK TABLES `sys_perm` WRITE;
/*!40000 ALTER TABLE `sys_perm`
  DISABLE KEYS */;
INSERT INTO `sys_perm`
VALUES ('*', NULL, '所有权限', 0, NULL, '2018-04-19 10:14:12', NULL),
       ('a:auth', NULL, '登录模块', 3, 1, NULL, NULL),
       ('a:gradleBuild', 'a:test', '构建gradle', 3, 1, NULL, NULL),
       ('a:mvn:install', 'a:test', 'mvnInstall', 3, 1, NULL, NULL),
       ('a:option', NULL, '选项模块', 3, 1, NULL, NULL),
       ('a:sys:perm', NULL, '系统权限模块', 3, 1, NULL, NULL),
       ('a:sys:role', NULL, '系统角色模块', 3, 1, NULL, NULL),
       ('a:sys:user:add', 'a:sys:接口', '添加系统用户', 3, 1, NULL, NULL),
       ('a:sys:user:del', 'a:sys:接口', '删除系统用户', 3, 1, NULL, NULL),
       ('a:sys:user:info', 'a:sys:接口', '查询系统用户信息', 3, 1, NULL, NULL),
       ('a:sys:user:info:update', 'a:sys:接口', '更新系统用户的信息', 3, 1, NULL, NULL),
       ('a:sys:user:list', 'a:sys:接口', '查询所有系统用户', 3, 1, NULL, NULL),
       ('a:sys:user:role:find', 'a:sys:接口', '查找系统用户的角色', 3, 1, NULL, NULL),
       ('a:sys:user:role:update', 'a:sys:接口', '更新系统用户的角色', 3, 1, NULL, NULL),
       ('a:sys:接口', NULL, '系统用户模块', 3, 0, NULL, NULL),
       ('a:test', NULL, '测试模块模块', 3, 0, NULL, NULL),
       ('b:user:add', 'm:sys:user', '添加用户', 2, NULL, '2018-06-02 03:00:37', NULL),
       ('b:user:delete', 'm:sys:user', '删除用户', 2, NULL, '2018-06-02 03:00:56', NULL),
       ('m:menu1', NULL, '菜单1', 1, 1, NULL, NULL),
       ('m:menu2', NULL, '菜单2', 1, 1, NULL, NULL),
       ('m:menu3', NULL, '菜单3', 1, 0, NULL, NULL),
       ('m:menu3:1', 'm:menu3', '菜单3-1', 1, 1, NULL, NULL),
       ('m:menu3:2', 'm:menu3', '菜单3-2', 1, 1, NULL, NULL),
       ('m:menu3:3', 'm:menu3', '菜单3-3', 1, 1, NULL, NULL),
       ('m:menu4', NULL, '东岳', 1, 0, NULL, NULL),
       ('m:menu4:1', 'm:menu4', '菜单4-1', 1, 0, NULL, NULL),
       ('m:menu4:1:a', 'm:menu4:1', '菜单4-1-a', 1, 1, NULL, NULL),
       ('m:menu4:1:b', 'm:menu4:1', '菜单4-1-b', 1, 1, NULL, NULL),
       ('m:menu4:1:c', 'm:menu4:1', '菜单4-1-c', 1, 1, NULL, NULL),
       ('m:menu4:2', 'm:menu4', '菜单4-2', 1, 1, NULL, NULL),
       ('m:sys', NULL, '系统', 1, 0, NULL, NULL),
       ('m:sys:perm', 'm:sys', '权限管理', 1, 1, NULL, NULL),
       ('m:sys:role', 'm:sys', '角色管理', 1, 1, NULL, NULL),
       ('m:sys:user', 'm:sys', '用户管理', 1, 1, NULL, NULL);
/*!40000 ALTER TABLE `sys_perm`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8mb4;
CREATE TABLE `sys_role`
(
  `rid`     varchar(25)  NOT NULL COMMENT '角色id',
  `rname`   varchar(50)       DEFAULT NULL COMMENT '角色名，用于显示',
  `rdesc`   varchar(100)      DEFAULT NULL COMMENT '角色描述',
  `rval`    varchar(100) NOT NULL COMMENT '角色值，用于权限判断',
  `created` timestamp    NULL DEFAULT NULL COMMENT '创建时间',
  `updated` timestamp    NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`rid`),
  UNIQUE KEY `rval` (`rval`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role`
  DISABLE KEYS */;
INSERT INTO `sys_role`
VALUES ('1002748319131680769', '普通用户', '具有一般的权限，不具备系统菜单权限1', 'common', '2018-06-02 03:06:44', '2018-06-02 03:10:57'),
       ('1106791070877892609', 'operator', '操作员', 'operator', '2019-03-16 05:35:29', '2019-03-16 05:38:24'),
       ('999999888888777777', '超级管理员', '具有本系统中最高权限', 'root', '2018-04-19 09:34:33', NULL);
/*!40000 ALTER TABLE `sys_role`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role_perm`
--

DROP TABLE IF EXISTS `sys_role_perm`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8mb4;
CREATE TABLE `sys_role_perm`
(
  `role_id`   varchar(25) NOT NULL,
  `perm_val`  varchar(25) NOT NULL,
  `perm_type` int(5) DEFAULT NULL,
  PRIMARY KEY (`role_id`, `perm_val`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role_perm`
--

LOCK TABLES `sys_role_perm` WRITE;
/*!40000 ALTER TABLE `sys_role_perm`
  DISABLE KEYS */;
INSERT INTO `sys_role_perm`
VALUES ('1002748319131680769', 'a:perm:update', 3),
       ('1002748319131680769', 'a:sys:perm', 3),
       ('1002748319131680769', 'b:user:add', 2),
       ('1002748319131680769', 'm:sys', 1),
       ('1002748319131680769', 'm:sys:user', 1),
       ('1106791070877892609', 'a:auth', 3),
       ('1106791070877892609', 'a:gradleBuild', 3),
       ('1106791070877892609', 'a:mvn:install', 3),
       ('1106791070877892609', 'a:option', 3),
       ('1106791070877892609', 'a:sys:perm', 3),
       ('1106791070877892609', 'a:sys:role', 3),
       ('1106791070877892609', 'a:sys:user:add', 3),
       ('1106791070877892609', 'a:sys:user:del', 3),
       ('1106791070877892609', 'a:sys:user:info', 3),
       ('1106791070877892609', 'a:sys:user:info:update', 3),
       ('1106791070877892609', 'a:sys:user:list', 3),
       ('1106791070877892609', 'a:sys:user:role:find', 3),
       ('1106791070877892609', 'a:sys:user:role:update', 3),
       ('1106791070877892609', 'a:sys:接口', 3),
       ('1106791070877892609', 'a:test', 3),
       ('1106791070877892609', 'b:user:add', 2),
       ('1106791070877892609', 'b:user:delete', 2),
       ('1106791070877892609', 'm:menu1', 1),
       ('1106791070877892609', 'm:menu2', 1),
       ('1106791070877892609', 'm:menu3', 1),
       ('1106791070877892609', 'm:menu3:1', 1),
       ('1106791070877892609', 'm:menu3:2', 1),
       ('1106791070877892609', 'm:menu3:3', 1),
       ('1106791070877892609', 'm:menu4', 1),
       ('1106791070877892609', 'm:menu4:1', 1),
       ('1106791070877892609', 'm:menu4:1:a', 1),
       ('1106791070877892609', 'm:menu4:1:b', 1),
       ('1106791070877892609', 'm:menu4:1:c', 1),
       ('1106791070877892609', 'm:menu4:2', 1),
       ('1106791070877892609', 'm:order:list', 1),
       ('1106791070877892609', 'm:sys', 1),
       ('1106791070877892609', 'm:sys:perm', 1),
       ('1106791070877892609', 'm:sys:role', 1),
       ('1106791070877892609', 'm:sys:user', 1),
       ('999999888888777777', '*', NULL);
/*!40000 ALTER TABLE `sys_role_perm`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8mb4;
CREATE TABLE `sys_user`
(
  `uid`     varchar(25) NOT NULL COMMENT '用户id',
  `uname`   varchar(50)      DEFAULT NULL COMMENT '登录名，不可改',
  `nick`    varchar(50)      DEFAULT NULL COMMENT '用户昵称，可改',
  `pwd`     varchar(200)     DEFAULT NULL COMMENT '已加密的登录密码',
  `salt`    varchar(200)     DEFAULT NULL COMMENT '加密盐值',
  `lock`    tinyint(1)       DEFAULT NULL COMMENT '是否锁定',
  `created` timestamp   NULL DEFAULT NULL COMMENT '创建时间',
  `updated` timestamp   NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uname` (`uname`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user`
  DISABLE KEYS */;
INSERT INTO `sys_user`
VALUES ('1102572958276222978', 'operator', 'operator', 'IDqrdqoZV+s0P4I5rOpjOn0tyickm7jjMMtgX+TfMp0=', 'tgihJgGRWa+y3IYBjJuX9A==', NULL,
        '2019-03-04 14:14:12', '2019-03-16 05:37:24'),
       ('1106843272720011266', 'user01', 'user01', '6LDVOuhRnzwhjK5QrvrW29VXlF+BR8CKO6ea7577HOU=', 'XvWmzxqA2k6YRE+ynxkPSQ==', NULL,
        '2019-03-16 09:02:55', NULL),
       ('986177923098808322', 'admin', 'admin', 'Wg6yBLK9bfOtWSAA0Dan1GgFlxJ2++On3ZGJ0Wg1JvU=', 'pUZnmTffC7vgLbFEP2nyMg==', 0, '2018-04-17 09:41:53',
        '2019-03-04 13:53:28');
/*!40000 ALTER TABLE `sys_user`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
SET character_set_client = utf8mb4;
CREATE TABLE `sys_user_role`
(
  `user_id` varchar(25) NOT NULL,
  `role_id` varchar(25) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role`
  DISABLE KEYS */;
INSERT INTO `sys_user_role`
VALUES ('1102572958276222978', '999999888888777777'),
       ('1106843272720011266', '999999888888777777'),
       ('986177923098808322', '999999888888777777');
/*!40000 ALTER TABLE `sys_user_role`
  ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'logistics-prod'
--

--
-- Dumping routines for database 'logistics-prod'
--
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2019-03-18  7:10:56
