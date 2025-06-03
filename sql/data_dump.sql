-- MySQL dump 10.13  Distrib 9.3.0, for macos15.2 (arm64)
--
-- Host: localhost    Database: db_project
-- ------------------------------------------------------
-- Server version	9.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Temporary view structure for view `available_seats_view`
--

DROP TABLE IF EXISTS `available_seats_view`;
/*!50001 DROP VIEW IF EXISTS `available_seats_view`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `available_seats_view` AS SELECT 
 1 AS `train_id`,
 1 AS `train_name`,
 1 AS `seat_number`,
 1 AS `seat_class`,
 1 AS `availability`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `detailed_train_schedule_view`
--

DROP TABLE IF EXISTS `detailed_train_schedule_view`;
/*!50001 DROP VIEW IF EXISTS `detailed_train_schedule_view`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `detailed_train_schedule_view` AS SELECT 
 1 AS `train_id`,
 1 AS `train_name`,
 1 AS `departure_station`,
 1 AS `arrival_station`,
 1 AS `departure_time`,
 1 AS `arrival_time`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `passenger`
--

DROP TABLE IF EXISTS `passenger`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `passenger` (
  `passenger_id` char(6) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`passenger_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passenger`
--

LOCK TABLES `passenger` WRITE;
/*!40000 ALTER TABLE `passenger` DISABLE KEYS */;
INSERT INTO `passenger` VALUES ('P001','Hyunwoo','Yoon','hyunwoo.yoon1@example.com','1037900970'),('P002','Soyeon','Lee','soyeon.lee2@example.com','1044677183'),('P003','Minji','Yoon','minji.yoon3@example.com','1070874482'),('P004','Soyeon','Lim','soyeon.lim4@example.com','1074697986'),('P005','Hyunwoo','Yoon','hyunwoo.yoon5@example.com','1020457898'),('P006','Jiwon','Han','jiwon.han6@example.com','1061101941'),('P007','Sangmin','Yoon','sangmin.yoon7@example.com','1047252889'),('P008','Minji','Seo','minji.seo8@example.com','1018302273'),('P009','Jaeho','Lee','jaeho.lee9@example.com','1074520536'),('P010','Sangmin','Yoon','sangmin.yoon10@example.com','1015041616'),('P011','Yuna','Lee','yuna.lee11@example.com','1079151590'),('P012','Jiwon','Seo','jiwon.seo12@example.com','1051822095'),('P013','Jisoo','Han','jisoo.han13@example.com','1081430646'),('P014','Sangmin','Lee','sangmin.lee14@example.com','1071444227'),('P015','Hana','Park','hana.park15@example.com','1022682936'),('P016','Jisoo','Seo','jisoo.seo16@example.com','1012884235'),('P017','Yuna','Kim','yuna.kim17@example.com','1048935693'),('P018','Jaeho','Kim','jaeho.kim18@example.com','1014973128'),('P019','Hyunwoo','Yoon','hyunwoo.yoon19@example.com','1063296285'),('P020','Minji','Lee','minji.lee20@example.com','1063914688'),('P021','Soyeon','Han','soyeon.han21@example.com','1059710121'),('P022','Yuna','Park','yuna.park22@example.com','1078680586'),('P023','Jaeho','Jung','jaeho.jung23@example.com','1027530071'),('P024','Jisoo','Lee','jisoo.lee24@example.com','1050101750'),('P025','Minji','Kang','minji.kang25@example.com','1037634570'),('P026','Jisoo','Kim','jisoo.kim26@example.com','1098553952'),('P027','Yuna','Lim','yuna.lim27@example.com','1059047899'),('P028','Jiwon','Kang','jiwon.kang28@example.com','1066810011'),('P029','Donghyun','Lim','donghyun.lim29@example.com','1036829409'),('P030','Jiwon','Kang','jiwon.kang30@example.com','1010171674'),('P031','Hyunwoo','Jung','hyunwoo.jung31@example.com','1065815486'),('P032','Yuna','Lim','yuna.lim32@example.com','1069240404'),('P033','Soyeon','Choi','soyeon.choi33@example.com','1073985152'),('P034','Yuna','Lim','yuna.lim34@example.com','1089188895'),('P035','Hyunwoo','Han','hyunwoo.han35@example.com','1094496003'),('P036','Jiwon','Park','jiwon.park36@example.com','1088365328'),('P037','Soyeon','Choi','soyeon.choi37@example.com','1096653582'),('P038','Minji','Seo','minji.seo38@example.com','1089120913'),('P039','Hyunwoo','Yoon','hyunwoo.yoon39@example.com','1082389140'),('P040','Hyunwoo','Kim','hyunwoo.kim40@example.com','1087280259'),('P041','Sangmin','Choi','sangmin.choi41@example.com','1050447052'),('P042','Minji','Kang','minji.kang42@example.com','1034793940'),('P043','Soyeon','Seo','soyeon.seo43@example.com','1091212604'),('P044','Minji','Han','minji.han44@example.com','1039524118'),('P045','Jiwon','Jung','jiwon.jung45@example.com','1031175306'),('P046','Hyunwoo','Seo','hyunwoo.seo46@example.com','1039314785'),('P047','Sangmin','Choi','sangmin.choi47@example.com','1020884087'),('P048','Jaeho','Park','jaeho.park48@example.com','1047399382'),('P049','Hyunwoo','Seo','hyunwoo.seo49@example.com','1081974203'),('P050','Soyeon','Jung','soyeon.jung50@example.com','1073742893');
/*!40000 ALTER TABLE `passenger` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `passenger_reservation_summary_view`
--

DROP TABLE IF EXISTS `passenger_reservation_summary_view`;
/*!50001 DROP VIEW IF EXISTS `passenger_reservation_summary_view`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `passenger_reservation_summary_view` AS SELECT 
 1 AS `first_name`,
 1 AS `last_name`,
 1 AS `train_id`,
 1 AS `train_name`,
 1 AS `seat_number`,
 1 AS `reservation_date`,
 1 AS `payment_status`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `payment_id` int NOT NULL AUTO_INCREMENT,
  `reservation_id` int NOT NULL,
  `payment_date` datetime NOT NULL,
  `amount` decimal(10,0) NOT NULL,
  `payment_method` varchar(50) NOT NULL,
  `payment_status` varchar(20) NOT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `fk_payment_reservation` (`reservation_id`),
  CONSTRAINT `fk_payment_reservation` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`reservation_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5051 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (5001,1001,'2025-04-10 08:52:00',12000,'카드','결제대기'),(5002,1002,'2025-06-02 09:31:00',12000,'계좌이체','결제대기'),(5003,1003,'2025-05-29 16:32:00',54000,'카드','결제완료'),(5004,1004,'2025-03-22 18:36:00',68000,'계좌이체','결제완료'),(5005,1005,'2025-03-04 15:01:00',79000,'계좌이체','결제완료'),(5006,1006,'2025-03-28 13:28:00',54000,'계좌이체','결제대기'),(5007,1007,'2025-05-10 15:42:00',54000,'카드','결제대기'),(5008,1008,'2025-05-31 13:53:00',25000,'카드','결제완료'),(5009,1009,'2025-02-21 05:41:00',38000,'카드','결제대기'),(5010,1010,'2025-01-29 18:58:00',68000,'카드','결제완료'),(5011,1011,'2025-05-04 07:25:00',54000,'카드','결제완료'),(5012,1012,'2025-06-01 10:23:00',79000,'카드','결제대기'),(5013,1013,'2025-01-29 19:01:00',54000,'카드','결제대기'),(5014,1014,'2025-03-10 19:54:00',79000,'계좌이체','결제완료'),(5015,1015,'2025-05-07 05:23:00',38000,'계좌이체','결제대기'),(5016,1016,'2025-05-31 11:27:00',79000,'카드','결제완료'),(5017,1017,'2025-01-15 10:34:00',68000,'카드','결제완료'),(5018,1018,'2025-06-26 19:27:00',68000,'계좌이체','결제완료'),(5019,1019,'2025-06-12 06:51:00',12000,'카드','결제대기'),(5020,1020,'2025-04-22 13:14:00',79000,'카드','결제완료'),(5021,1021,'2025-06-23 19:02:00',25000,'카드','결제대기'),(5022,1022,'2025-01-17 15:56:00',54000,'카드','결제완료'),(5023,1023,'2025-02-25 16:01:00',38000,'계좌이체','결제대기'),(5024,1024,'2025-03-11 17:57:00',54000,'카드','결제대기'),(5025,1025,'2025-02-13 10:25:00',25000,'카드','결제완료'),(5026,1026,'2025-05-16 15:27:00',79000,'카드','결제대기'),(5027,1027,'2025-06-18 15:54:00',68000,'계좌이체','결제완료'),(5028,1028,'2025-05-08 14:39:00',54000,'계좌이체','결제대기'),(5029,1029,'2025-04-06 15:58:00',25000,'계좌이체','결제대기'),(5030,1030,'2025-02-07 15:59:00',12000,'카드','결제대기'),(5031,1031,'2025-02-06 13:41:00',79000,'카드','결제완료'),(5032,1032,'2025-04-09 12:18:00',38000,'카드','결제대기'),(5033,1033,'2025-01-04 13:41:00',54000,'계좌이체','결제대기'),(5034,1034,'2025-05-08 11:28:00',25000,'계좌이체','결제완료'),(5035,1035,'2025-06-04 19:20:00',12000,'카드','결제대기'),(5036,1036,'2025-03-31 05:30:00',25000,'계좌이체','결제완료'),(5037,1037,'2025-02-23 12:11:00',68000,'계좌이체','결제대기'),(5038,1038,'2025-03-31 12:53:00',68000,'카드','결제완료'),(5039,1039,'2025-03-13 19:54:00',54000,'카드','결제완료'),(5040,1040,'2025-02-03 13:22:00',25000,'카드','결제완료'),(5041,1041,'2025-04-20 15:54:00',79000,'계좌이체','결제대기'),(5042,1042,'2025-04-17 17:24:00',12000,'계좌이체','결제대기'),(5043,1043,'2025-05-06 18:12:00',79000,'계좌이체','결제대기'),(5044,1044,'2025-06-09 14:45:00',54000,'계좌이체','결제완료'),(5045,1045,'2025-04-28 15:45:00',12000,'카드','결제대기'),(5046,1046,'2025-04-22 09:06:00',38000,'카드','결제대기'),(5047,1047,'2025-01-18 10:11:00',38000,'계좌이체','결제완료'),(5048,1048,'2025-02-20 10:03:00',25000,'계좌이체','결제완료'),(5049,1049,'2025-05-13 15:10:00',25000,'카드','결제완료'),(5050,1050,'2025-05-23 17:09:00',25000,'계좌이체','결제대기');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation` (
  `reservation_id` int NOT NULL AUTO_INCREMENT,
  `passenger_id` char(6) NOT NULL,
  `train_id` char(6) NOT NULL,
  `seat_number` char(6) NOT NULL,
  `reservation_date` datetime NOT NULL,
  PRIMARY KEY (`reservation_id`),
  KEY `fk_reservation_passenger` (`passenger_id`),
  KEY `fk_reservation_seat` (`train_id`,`seat_number`),
  CONSTRAINT `fk_reservation_passenger` FOREIGN KEY (`passenger_id`) REFERENCES `passenger` (`passenger_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_reservation_seat` FOREIGN KEY (`train_id`, `seat_number`) REFERENCES `seat` (`train_id`, `seat_number`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_reservation_train` FOREIGN KEY (`train_id`) REFERENCES `train` (`train_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1051 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (1001,'P020','ITX003','06-09B','2025-02-01 11:59:00'),(1002,'P024','KTX038','12-10A','2025-03-12 19:05:00'),(1003,'P040','KTX042','18-09A','2025-06-19 20:29:00'),(1004,'P017','KTX012','11-13C','2025-05-22 16:48:00'),(1005,'P016','KTX036','14-12C','2025-05-12 12:31:00'),(1006,'P033','ITX007','09-09D','2025-05-20 12:22:00'),(1007,'P027','ITX043','20-05C','2025-04-15 18:48:00'),(1008,'P021','KTX034','19-08B','2025-01-20 18:38:00'),(1009,'P006','ITX015','04-15B','2025-04-18 11:15:00'),(1010,'P047','KTX040','20-03B','2025-01-31 17:28:00'),(1011,'P028','KTX048','11-20A','2025-02-20 06:17:00'),(1012,'P046','KTX010','01-01A','2025-04-29 05:33:00'),(1013,'P035','KTX004','16-09A','2025-01-20 20:28:00'),(1014,'P032','KTX010','06-17B','2025-01-04 20:14:00'),(1015,'P012','KTX026','14-02C','2025-03-31 18:02:00'),(1016,'P008','ITX033','04-12C','2025-04-20 11:46:00'),(1017,'P010','KTX014','05-15A','2025-02-17 20:08:00'),(1018,'P048','KTX004','18-17A','2025-06-28 17:03:00'),(1019,'P030','ITX001','05-02A','2025-03-28 08:12:00'),(1020,'P038','ITX007','15-13D','2025-03-21 18:18:00'),(1021,'P037','KTX044','18-12A','2025-03-06 18:15:00'),(1022,'P032','KTX006','09-18D','2025-06-08 10:06:00'),(1023,'P007','KTX042','08-09B','2025-01-18 15:46:00'),(1024,'P030','KTX042','01-17C','2025-04-01 15:18:00'),(1025,'P045','ITX011','11-20D','2025-05-20 07:39:00'),(1026,'P014','ITX001','19-17D','2025-01-23 15:07:00'),(1027,'P047','KTX046','19-19B','2025-05-03 08:48:00'),(1028,'P044','ITX003','08-12B','2025-04-17 16:31:00'),(1029,'P047','ITX007','03-15A','2025-01-15 09:29:00'),(1030,'P008','KTX006','04-04B','2025-01-26 06:37:00'),(1031,'P022','ITX005','20-13D','2025-03-23 15:45:00'),(1032,'P048','ITX047','18-12C','2025-01-01 10:21:00'),(1033,'P041','KTX034','13-05B','2025-03-03 10:01:00'),(1034,'P046','ITX021','10-01D','2025-05-13 14:09:00'),(1035,'P006','ITX031','01-05B','2025-04-19 08:33:00'),(1036,'P039','KTX014','13-06A','2025-05-13 15:09:00'),(1037,'P041','KTX034','17-07C','2025-02-19 06:03:00'),(1038,'P047','KTX036','01-13B','2025-02-17 13:11:00'),(1039,'P035','ITX029','12-08D','2025-05-18 19:45:00'),(1040,'P038','ITX031','07-11C','2025-06-17 06:31:00'),(1041,'P033','KTX002','14-06D','2025-01-08 09:10:00'),(1042,'P013','KTX038','18-01B','2025-02-08 20:21:00'),(1043,'P007','KTX016','15-13A','2025-01-16 06:35:00'),(1044,'P034','KTX042','11-03D','2025-01-28 12:52:00'),(1045,'P034','ITX045','02-19D','2025-06-25 07:22:00'),(1046,'P006','ITX023','18-15A','2025-04-28 10:08:00'),(1047,'P022','ITX037','04-14C','2025-02-26 08:03:00'),(1048,'P016','KTX046','09-15B','2025-05-01 13:36:00'),(1049,'P003','KTX010','10-01D','2025-04-08 17:15:00'),(1050,'P028','ITX015','15-18C','2025-06-26 20:14:00');
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seat`
--

DROP TABLE IF EXISTS `seat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seat` (
  `seat_id` int NOT NULL AUTO_INCREMENT,
  `train_id` char(6) NOT NULL,
  `seat_number` char(6) NOT NULL,
  `seat_class` varchar(20) NOT NULL,
  `availability` enum('Available','Booked') NOT NULL DEFAULT 'Available',
  PRIMARY KEY (`seat_id`),
  UNIQUE KEY `uq_seat_train_number` (`train_id`,`seat_number`),
  CONSTRAINT `fk_seat_train` FOREIGN KEY (`train_id`) REFERENCES `train` (`train_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3501 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seat`
--

LOCK TABLES `seat` WRITE;
/*!40000 ALTER TABLE `seat` DISABLE KEYS */;
INSERT INTO `seat` VALUES (3001,'ITX001','19-17D','특실','Booked'),(3002,'ITX001','09-04C','특실','Available'),(3003,'ITX001','05-02A','특실','Booked'),(3004,'ITX001','10-20D','일반','Available'),(3005,'ITX001','11-20D','특실','Booked'),(3006,'ITX001','14-17C','일반','Available'),(3007,'ITX001','19-06D','일반','Available'),(3008,'ITX001','08-17D','특실','Available'),(3009,'ITX001','05-10D','특실','Available'),(3010,'ITX001','20-02C','일반','Available'),(3011,'KTX002','09-02D','일반','Available'),(3012,'KTX002','03-13A','특실','Available'),(3013,'KTX002','04-14B','특실','Booked'),(3014,'KTX002','17-09C','일반','Available'),(3015,'KTX002','09-08C','일반','Available'),(3016,'KTX002','08-12C','일반','Available'),(3017,'KTX002','06-13B','일반','Available'),(3018,'KTX002','07-06D','일반','Available'),(3019,'KTX002','14-06D','일반','Booked'),(3020,'KTX002','20-09C','특실','Available'),(3021,'ITX003','15-01A','특실','Available'),(3022,'ITX003','06-09B','일반','Booked'),(3023,'ITX003','13-20C','특실','Available'),(3024,'ITX003','05-17B','특실','Available'),(3025,'ITX003','13-07D','특실','Booked'),(3026,'ITX003','15-05D','일반','Available'),(3027,'ITX003','18-11C','특실','Available'),(3028,'ITX003','15-01B','특실','Booked'),(3029,'ITX003','08-12B','일반','Booked'),(3030,'ITX003','15-05A','일반','Available'),(3031,'KTX004','16-09A','특실','Booked'),(3032,'KTX004','18-01D','일반','Booked'),(3033,'KTX004','18-17A','일반','Booked'),(3034,'KTX004','07-02B','특실','Booked'),(3035,'KTX004','15-17B','특실','Available'),(3036,'KTX004','15-15D','특실','Available'),(3037,'KTX004','18-06B','특실','Available'),(3038,'KTX004','04-17D','일반','Available'),(3039,'KTX004','05-19A','특실','Available'),(3040,'KTX004','12-10B','특실','Available'),(3041,'ITX005','20-13D','특실','Booked'),(3042,'ITX005','11-06B','특실','Available'),(3043,'ITX005','06-18D','특실','Available'),(3044,'ITX005','11-10B','일반','Available'),(3045,'ITX005','15-17B','일반','Booked'),(3046,'ITX005','04-04D','일반','Available'),(3047,'ITX005','08-13C','일반','Available'),(3048,'ITX005','13-03C','일반','Available'),(3049,'ITX005','02-01C','특실','Available'),(3050,'ITX005','10-10C','특실','Available'),(3051,'KTX006','04-04B','일반','Booked'),(3052,'KTX006','07-04A','일반','Available'),(3053,'KTX006','05-02D','특실','Available'),(3054,'KTX006','05-20C','일반','Available'),(3055,'KTX006','18-06B','일반','Available'),(3056,'KTX006','05-03C','특실','Booked'),(3057,'KTX006','09-18D','일반','Booked'),(3058,'KTX006','07-15B','일반','Available'),(3059,'KTX006','08-14D','일반','Available'),(3060,'KTX006','04-02A','특실','Available'),(3061,'ITX007','05-09C','특실','Available'),(3062,'ITX007','14-06A','특실','Available'),(3063,'ITX007','10-15A','특실','Available'),(3064,'ITX007','14-20A','특실','Available'),(3065,'ITX007','09-03B','일반','Booked'),(3066,'ITX007','17-11C','특실','Available'),(3067,'ITX007','13-07A','일반','Booked'),(3068,'ITX007','15-13D','일반','Booked'),(3069,'ITX007','09-09D','일반','Booked'),(3070,'ITX007','03-15A','특실','Booked'),(3071,'KTX008','02-05C','일반','Available'),(3072,'KTX008','06-13B','특실','Available'),(3073,'KTX008','18-12B','특실','Available'),(3074,'KTX008','16-10B','특실','Available'),(3075,'KTX008','06-08D','일반','Available'),(3076,'KTX008','04-06D','특실','Available'),(3077,'KTX008','17-10C','일반','Available'),(3078,'KTX008','09-09B','특실','Available'),(3079,'KTX008','03-15C','일반','Available'),(3080,'KTX008','20-12A','특실','Available'),(3081,'ITX009','11-14C','특실','Available'),(3082,'ITX009','13-05D','특실','Available'),(3083,'ITX009','20-08B','특실','Available'),(3084,'ITX009','18-19B','일반','Available'),(3085,'ITX009','08-08C','일반','Booked'),(3086,'ITX009','13-19D','일반','Available'),(3087,'ITX009','13-16A','일반','Available'),(3088,'ITX009','05-04D','일반','Available'),(3089,'ITX009','16-16B','일반','Booked'),(3090,'ITX009','09-14D','특실','Available'),(3091,'KTX010','07-16D','일반','Available'),(3092,'KTX010','05-01B','일반','Available'),(3093,'KTX010','06-17B','일반','Booked'),(3094,'KTX010','18-20C','특실','Booked'),(3095,'KTX010','10-01D','특실','Booked'),(3096,'KTX010','14-15D','일반','Available'),(3097,'KTX010','13-18A','특실','Available'),(3098,'KTX010','06-12A','특실','Available'),(3099,'KTX010','01-01A','특실','Booked'),(3100,'KTX010','03-09A','특실','Available'),(3101,'ITX011','12-19D','일반','Available'),(3102,'ITX011','02-18D','특실','Booked'),(3103,'ITX011','04-09A','특실','Booked'),(3104,'ITX011','11-20D','일반','Booked'),(3105,'ITX011','04-20A','일반','Available'),(3106,'ITX011','08-10B','특실','Booked'),(3107,'ITX011','14-03C','특실','Booked'),(3108,'ITX011','02-08C','특실','Available'),(3109,'ITX011','13-17C','일반','Available'),(3110,'ITX011','16-08D','일반','Booked'),(3111,'KTX012','17-04D','특실','Available'),(3112,'KTX012','06-03B','특실','Booked'),(3113,'KTX012','11-01C','일반','Available'),(3114,'KTX012','11-13C','특실','Booked'),(3115,'KTX012','15-02D','특실','Available'),(3116,'KTX012','07-08C','일반','Available'),(3117,'KTX012','16-09A','일반','Available'),(3118,'KTX012','03-03C','특실','Available'),(3119,'KTX012','17-10A','특실','Available'),(3120,'KTX012','14-19A','특실','Available'),(3121,'ITX013','03-09A','일반','Available'),(3122,'ITX013','12-09C','일반','Available'),(3123,'ITX013','07-16D','일반','Available'),(3124,'ITX013','05-14B','특실','Booked'),(3125,'ITX013','16-11A','특실','Available'),(3126,'ITX013','03-03B','특실','Available'),(3127,'ITX013','09-12C','특실','Available'),(3128,'ITX013','12-16B','일반','Available'),(3129,'ITX013','03-07C','일반','Booked'),(3130,'ITX013','20-14C','특실','Available'),(3131,'KTX014','02-02A','특실','Available'),(3132,'KTX014','05-15A','특실','Booked'),(3133,'KTX014','03-04C','일반','Available'),(3134,'KTX014','13-06A','일반','Booked'),(3135,'KTX014','13-05A','일반','Booked'),(3136,'KTX014','02-07A','특실','Available'),(3137,'KTX014','16-02C','특실','Available'),(3138,'KTX014','18-19C','일반','Booked'),(3139,'KTX014','04-15B','특실','Available'),(3140,'KTX014','13-10A','특실','Available'),(3141,'ITX015','06-15B','일반','Available'),(3142,'ITX015','19-19D','특실','Available'),(3143,'ITX015','06-10D','특실','Available'),(3144,'ITX015','15-18C','특실','Booked'),(3145,'ITX015','04-15B','일반','Booked'),(3146,'ITX015','03-16B','일반','Available'),(3147,'ITX015','19-19B','일반','Available'),(3148,'ITX015','05-12D','일반','Available'),(3149,'ITX015','03-12B','일반','Available'),(3150,'ITX015','12-04C','특실','Available'),(3151,'KTX016','06-12D','특실','Available'),(3152,'KTX016','13-06B','특실','Available'),(3153,'KTX016','19-09D','특실','Available'),(3154,'KTX016','15-13A','특실','Booked'),(3155,'KTX016','05-20A','일반','Booked'),(3156,'KTX016','06-10A','일반','Available'),(3157,'KTX016','02-18B','특실','Booked'),(3158,'KTX016','13-12D','일반','Booked'),(3159,'KTX016','16-04A','특실','Available'),(3160,'KTX016','11-04A','일반','Available'),(3161,'ITX017','15-19C','특실','Available'),(3162,'ITX017','18-11D','일반','Available'),(3163,'ITX017','16-17D','특실','Booked'),(3164,'ITX017','04-01B','일반','Available'),(3165,'ITX017','18-18D','일반','Booked'),(3166,'ITX017','07-09D','일반','Booked'),(3167,'ITX017','02-18D','특실','Available'),(3168,'ITX017','05-07A','특실','Booked'),(3169,'ITX017','06-09A','특실','Available'),(3170,'ITX017','09-04C','일반','Available'),(3171,'KTX018','20-03A','특실','Available'),(3172,'KTX018','20-08D','특실','Booked'),(3173,'KTX018','09-07A','일반','Available'),(3174,'KTX018','16-12D','일반','Available'),(3175,'KTX018','03-18A','특실','Booked'),(3176,'KTX018','14-07C','특실','Available'),(3177,'KTX018','03-03A','일반','Available'),(3178,'KTX018','08-11B','특실','Available'),(3179,'KTX018','04-12A','일반','Available'),(3180,'KTX018','02-06C','특실','Booked'),(3181,'ITX019','07-09A','특실','Available'),(3182,'ITX019','08-17D','특실','Available'),(3183,'ITX019','11-04C','특실','Available'),(3184,'ITX019','03-15B','일반','Available'),(3185,'ITX019','05-13A','특실','Booked'),(3186,'ITX019','12-11C','특실','Available'),(3187,'ITX019','18-02B','특실','Available'),(3188,'ITX019','12-15D','특실','Available'),(3189,'ITX019','11-15D','일반','Available'),(3190,'ITX019','18-02D','특실','Available'),(3191,'KTX020','05-17D','특실','Available'),(3192,'KTX020','13-17D','특실','Available'),(3193,'KTX020','16-14B','일반','Available'),(3194,'KTX020','20-17C','특실','Available'),(3195,'KTX020','06-08D','특실','Available'),(3196,'KTX020','05-16D','일반','Booked'),(3197,'KTX020','02-12B','일반','Available'),(3198,'KTX020','10-16D','일반','Available'),(3199,'KTX020','16-02A','일반','Available'),(3200,'KTX020','14-11B','특실','Available'),(3201,'ITX021','08-15D','특실','Available'),(3202,'ITX021','17-05A','일반','Booked'),(3203,'ITX021','04-04D','일반','Available'),(3204,'ITX021','20-04C','특실','Available'),(3205,'ITX021','02-03D','특실','Available'),(3206,'ITX021','07-15C','특실','Available'),(3207,'ITX021','10-01D','특실','Booked'),(3208,'ITX021','05-01D','일반','Available'),(3209,'ITX021','11-15B','일반','Available'),(3210,'ITX021','01-05B','일반','Booked'),(3211,'KTX022','07-06C','특실','Available'),(3212,'KTX022','02-19B','일반','Booked'),(3213,'KTX022','11-10D','일반','Available'),(3214,'KTX022','17-03C','특실','Available'),(3215,'KTX022','17-09C','특실','Booked'),(3216,'KTX022','17-08B','일반','Booked'),(3217,'KTX022','05-05C','일반','Available'),(3218,'KTX022','09-07C','일반','Available'),(3219,'KTX022','19-12A','일반','Booked'),(3220,'KTX022','07-17B','특실','Available'),(3221,'ITX023','06-04C','일반','Available'),(3222,'ITX023','06-03D','일반','Available'),(3223,'ITX023','09-01A','특실','Available'),(3224,'ITX023','18-12A','일반','Available'),(3225,'ITX023','08-19C','특실','Available'),(3226,'ITX023','18-15A','특실','Booked'),(3227,'ITX023','04-04A','일반','Available'),(3228,'ITX023','12-08B','일반','Booked'),(3229,'ITX023','02-09B','일반','Available'),(3230,'ITX023','12-18C','특실','Available'),(3231,'KTX024','10-07C','특실','Available'),(3232,'KTX024','16-19A','특실','Available'),(3233,'KTX024','18-09C','일반','Available'),(3234,'KTX024','06-10D','일반','Booked'),(3235,'KTX024','11-03A','특실','Available'),(3236,'KTX024','19-13A','일반','Available'),(3237,'KTX024','10-08A','일반','Booked'),(3238,'KTX024','05-14C','특실','Available'),(3239,'KTX024','16-15D','특실','Available'),(3240,'KTX024','08-19A','일반','Available'),(3241,'ITX025','18-04C','일반','Available'),(3242,'ITX025','08-18C','특실','Available'),(3243,'ITX025','13-04A','특실','Available'),(3244,'ITX025','12-12D','특실','Booked'),(3245,'ITX025','02-06C','특실','Available'),(3246,'ITX025','17-18D','일반','Available'),(3247,'ITX025','18-19C','일반','Available'),(3248,'ITX025','01-07A','특실','Available'),(3249,'ITX025','04-09D','일반','Available'),(3250,'ITX025','18-14C','일반','Available'),(3251,'KTX026','11-07B','특실','Available'),(3252,'KTX026','17-17D','일반','Booked'),(3253,'KTX026','07-10C','특실','Available'),(3254,'KTX026','14-02C','특실','Booked'),(3255,'KTX026','02-18A','특실','Booked'),(3256,'KTX026','04-12B','특실','Booked'),(3257,'KTX026','02-04A','특실','Booked'),(3258,'KTX026','02-02B','일반','Available'),(3259,'KTX026','11-07D','일반','Available'),(3260,'KTX026','06-03C','일반','Available'),(3261,'ITX027','12-06C','일반','Booked'),(3262,'ITX027','09-05B','일반','Available'),(3264,'ITX027','11-02C','일반','Available'),(3265,'ITX027','17-02C','일반','Available'),(3266,'ITX027','06-16B','일반','Available'),(3267,'ITX027','08-18B','특실','Available'),(3268,'ITX027','05-20A','일반','Available'),(3269,'ITX027','09-09C','일반','Available'),(3270,'ITX027','01-11B','일반','Available'),(3271,'KTX028','08-19C','특실','Available'),(3272,'KTX028','05-20C','일반','Available'),(3273,'KTX028','06-17B','특실','Booked'),(3274,'KTX028','09-19B','특실','Available'),(3275,'KTX028','15-13B','특실','Available'),(3276,'KTX028','05-10C','일반','Available'),(3277,'KTX028','02-15A','일반','Available'),(3278,'KTX028','05-11C','일반','Booked'),(3279,'KTX028','19-05B','일반','Available'),(3280,'KTX028','06-02D','특실','Booked'),(3281,'ITX029','05-13A','일반','Available'),(3282,'ITX029','18-17B','일반','Available'),(3283,'ITX029','14-04D','특실','Available'),(3284,'ITX029','12-08D','특실','Booked'),(3285,'ITX029','16-04B','특실','Available'),(3286,'ITX029','14-04B','일반','Available'),(3287,'ITX029','06-14C','특실','Booked'),(3288,'ITX029','10-17D','일반','Available'),(3289,'ITX029','08-03B','특실','Available'),(3290,'ITX029','08-06D','특실','Available'),(3291,'KTX030','08-06A','일반','Available'),(3292,'KTX030','17-17A','일반','Available'),(3293,'KTX030','20-15B','일반','Available'),(3294,'KTX030','15-13B','특실','Available'),(3295,'KTX030','12-10D','일반','Available'),(3296,'KTX030','15-01A','특실','Booked'),(3297,'KTX030','19-03B','일반','Booked'),(3298,'KTX030','18-08A','일반','Available'),(3299,'KTX030','17-06D','특실','Booked'),(3300,'KTX030','17-14A','일반','Booked'),(3301,'ITX031','03-08A','일반','Available'),(3302,'ITX031','02-10C','일반','Booked'),(3303,'ITX031','03-06D','특실','Available'),(3304,'ITX031','16-19C','특실','Booked'),(3305,'ITX031','12-19A','일반','Available'),(3306,'ITX031','19-12B','특실','Available'),(3307,'ITX031','01-05B','특실','Booked'),(3308,'ITX031','07-11C','특실','Booked'),(3309,'ITX031','12-13B','일반','Available'),(3310,'ITX031','04-03C','일반','Available'),(3311,'KTX032','04-12A','일반','Booked'),(3312,'KTX032','10-20C','특실','Available'),(3313,'KTX032','15-16A','일반','Booked'),(3314,'KTX032','03-17D','일반','Booked'),(3315,'KTX032','10-13B','특실','Available'),(3316,'KTX032','17-20A','특실','Available'),(3317,'KTX032','15-15D','특실','Available'),(3318,'KTX032','09-06B','일반','Booked'),(3319,'KTX032','19-04C','특실','Available'),(3320,'KTX032','04-15C','일반','Booked'),(3321,'ITX033','04-12A','일반','Available'),(3322,'ITX033','02-08D','특실','Available'),(3323,'ITX033','19-14D','일반','Available'),(3324,'ITX033','06-01B','일반','Booked'),(3325,'ITX033','07-12D','일반','Available'),(3326,'ITX033','05-05D','특실','Available'),(3327,'ITX033','04-12C','특실','Booked'),(3328,'ITX033','15-15A','특실','Available'),(3329,'ITX033','18-19D','특실','Available'),(3330,'ITX033','09-14B','특실','Available'),(3331,'KTX034','13-08B','일반','Booked'),(3332,'KTX034','08-18B','특실','Available'),(3333,'KTX034','19-08B','일반','Booked'),(3334,'KTX034','06-14B','일반','Available'),(3335,'KTX034','13-14D','특실','Available'),(3336,'KTX034','01-18A','일반','Available'),(3337,'KTX034','08-02C','일반','Available'),(3338,'KTX034','17-07C','일반','Booked'),(3339,'KTX034','16-03D','특실','Available'),(3340,'KTX034','13-05B','특실','Booked'),(3341,'ITX035','12-17B','특실','Available'),(3342,'ITX035','09-14B','일반','Booked'),(3343,'ITX035','17-06D','일반','Available'),(3344,'ITX035','02-19D','특실','Booked'),(3345,'ITX035','18-19C','특실','Available'),(3346,'ITX035','07-13C','일반','Available'),(3347,'ITX035','06-05D','일반','Available'),(3348,'ITX035','13-01A','특실','Available'),(3349,'ITX035','02-05C','일반','Available'),(3350,'ITX035','15-07A','특실','Available'),(3351,'KTX036','19-15B','특실','Available'),(3352,'KTX036','06-16A','특실','Available'),(3353,'KTX036','14-12C','특실','Booked'),(3354,'KTX036','01-13B','특실','Booked'),(3355,'KTX036','04-20D','일반','Available'),(3356,'KTX036','03-02B','특실','Available'),(3357,'KTX036','16-09C','특실','Available'),(3358,'KTX036','11-11D','특실','Available'),(3359,'KTX036','16-07D','특실','Booked'),(3360,'KTX036','14-20C','일반','Available'),(3361,'ITX037','04-07B','특실','Available'),(3362,'ITX037','02-08B','일반','Available'),(3363,'ITX037','06-16B','일반','Booked'),(3364,'ITX037','10-06B','특실','Available'),(3365,'ITX037','05-16D','특실','Available'),(3366,'ITX037','13-06A','일반','Booked'),(3367,'ITX037','15-03C','특실','Available'),(3368,'ITX037','15-09B','일반','Available'),(3369,'ITX037','04-14C','일반','Booked'),(3370,'ITX037','08-04A','특실','Available'),(3371,'KTX038','07-07B','특실','Available'),(3372,'KTX038','01-04B','일반','Booked'),(3373,'KTX038','17-15D','특실','Available'),(3374,'KTX038','03-10D','특실','Available'),(3375,'KTX038','06-02B','일반','Available'),(3376,'KTX038','12-10A','일반','Booked'),(3377,'KTX038','12-14A','일반','Booked'),(3378,'KTX038','14-04D','특실','Available'),(3379,'KTX038','18-01B','특실','Booked'),(3380,'KTX038','01-20D','일반','Available'),(3381,'ITX039','17-06C','특실','Available'),(3382,'ITX039','03-04A','일반','Available'),(3383,'ITX039','06-16A','특실','Booked'),(3384,'ITX039','12-20A','일반','Available'),(3385,'ITX039','20-06B','특실','Available'),(3386,'ITX039','05-08C','일반','Available'),(3387,'ITX039','15-18C','일반','Available'),(3388,'ITX039','18-06D','일반','Available'),(3389,'ITX039','20-01D','특실','Available'),(3390,'ITX039','07-10B','특실','Available'),(3391,'KTX040','17-08A','특실','Available'),(3392,'KTX040','13-04D','일반','Available'),(3393,'KTX040','10-02C','일반','Available'),(3394,'KTX040','09-13D','일반','Booked'),(3395,'KTX040','05-15C','특실','Available'),(3396,'KTX040','18-07A','일반','Booked'),(3397,'KTX040','20-03B','특실','Booked'),(3398,'KTX040','19-10D','특실','Available'),(3399,'KTX040','13-02B','일반','Available'),(3400,'KTX040','02-06D','특실','Available'),(3401,'ITX041','16-12D','특실','Available'),(3402,'ITX041','16-03D','일반','Available'),(3403,'ITX041','12-15D','일반','Available'),(3404,'ITX041','20-09A','특실','Booked'),(3405,'ITX041','04-02A','특실','Available'),(3406,'ITX041','08-12D','특실','Available'),(3407,'ITX041','14-16C','특실','Available'),(3408,'ITX041','03-14D','일반','Available'),(3409,'ITX041','18-04C','일반','Available'),(3410,'ITX041','17-05C','특실','Available'),(3411,'KTX042','20-14A','특실','Available'),(3412,'KTX042','08-16A','특실','Available'),(3413,'KTX042','17-19D','일반','Available'),(3414,'KTX042','10-13A','특실','Available'),(3415,'KTX042','18-09A','일반','Booked'),(3416,'KTX042','04-05B','일반','Available'),(3417,'KTX042','08-09B','일반','Booked'),(3418,'KTX042','11-05B','특실','Available'),(3419,'KTX042','01-17C','일반','Booked'),(3420,'KTX042','11-03D','일반','Booked'),(3421,'ITX043','09-01B','특실','Booked'),(3422,'ITX043','02-09B','일반','Available'),(3423,'ITX043','10-11C','일반','Available'),(3424,'ITX043','01-11B','특실','Available'),(3425,'ITX043','06-20D','일반','Available'),(3426,'ITX043','11-15D','특실','Available'),(3427,'ITX043','09-14D','일반','Booked'),(3428,'ITX043','16-19D','특실','Available'),(3429,'ITX043','19-03B','특실','Available'),(3430,'ITX043','20-05C','특실','Booked'),(3431,'KTX044','20-03D','일반','Available'),(3432,'KTX044','14-09B','특실','Available'),(3433,'KTX044','17-16C','특실','Booked'),(3434,'KTX044','19-01D','특실','Available'),(3435,'KTX044','07-02D','특실','Booked'),(3436,'KTX044','19-12A','특실','Available'),(3437,'KTX044','03-15B','특실','Available'),(3438,'KTX044','18-12A','특실','Booked'),(3439,'KTX044','03-19C','특실','Booked'),(3440,'KTX044','04-07D','일반','Available'),(3441,'ITX045','11-09C','일반','Booked'),(3442,'ITX045','19-10C','일반','Available'),(3443,'ITX045','15-02C','일반','Available'),(3444,'ITX045','02-19D','특실','Booked'),(3445,'ITX045','15-02B','특실','Available'),(3446,'ITX045','14-06C','특실','Available'),(3447,'ITX045','20-03A','일반','Available'),(3448,'ITX045','15-04A','특실','Available'),(3449,'ITX045','09-05D','일반','Available'),(3450,'ITX045','14-14B','특실','Available'),(3451,'KTX046','03-12C','특실','Available'),(3453,'KTX046','09-15B','일반','Booked'),(3454,'KTX046','19-19B','일반','Booked'),(3455,'KTX046','11-18D','일반','Booked'),(3456,'KTX046','08-10D','특실','Available'),(3457,'KTX046','20-06A','일반','Available'),(3458,'KTX046','12-15D','일반','Available'),(3459,'KTX046','12-05B','일반','Available'),(3460,'KTX046','07-08A','특실','Available'),(3461,'ITX047','05-20D','일반','Available'),(3462,'ITX047','02-06B','일반','Available'),(3463,'ITX047','18-12C','특실','Booked'),(3464,'ITX047','05-02D','일반','Available'),(3465,'ITX047','12-08A','일반','Available'),(3466,'ITX047','06-04A','특실','Available'),(3467,'ITX047','13-09C','일반','Booked'),(3468,'ITX047','13-12C','일반','Booked'),(3469,'ITX047','11-10A','특실','Booked'),(3470,'ITX047','18-11C','일반','Available'),(3471,'KTX048','17-12D','특실','Booked'),(3472,'KTX048','15-02A','일반','Available'),(3473,'KTX048','11-20A','특실','Booked'),(3474,'KTX048','03-15C','일반','Available'),(3475,'KTX048','07-14C','일반','Available'),(3476,'KTX048','13-02C','특실','Available'),(3477,'KTX048','20-16C','특실','Available'),(3478,'KTX048','13-07B','특실','Available'),(3479,'KTX048','01-14A','일반','Available'),(3480,'KTX048','09-20B','일반','Booked'),(3481,'ITX049','14-16C','일반','Booked'),(3482,'ITX049','16-10D','특실','Available'),(3483,'ITX049','18-18C','특실','Available'),(3484,'ITX049','03-11B','특실','Available'),(3485,'ITX049','14-19A','일반','Available'),(3486,'ITX049','13-07B','특실','Booked'),(3487,'ITX049','15-02D','특실','Available'),(3488,'ITX049','15-17B','일반','Available'),(3489,'ITX049','11-04C','일반','Available'),(3490,'ITX049','03-20B','일반','Available'),(3491,'KTX050','14-13D','특실','Booked'),(3492,'KTX050','17-06C','특실','Available'),(3493,'KTX050','14-01D','특실','Available'),(3494,'KTX050','09-16A','특실','Booked'),(3495,'KTX050','17-11D','일반','Available'),(3496,'KTX050','10-04A','일반','Available'),(3497,'KTX050','01-08C','특실','Available'),(3498,'KTX050','19-12C','일반','Available'),(3499,'KTX050','03-06B','일반','Booked'),(3500,'KTX050','03-16B','일반','Available');
/*!40000 ALTER TABLE `seat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `station`
--

DROP TABLE IF EXISTS `station`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `station` (
  `station_id` char(6) NOT NULL,
  `station_name` varchar(50) NOT NULL,
  `location` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`station_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `station`
--

LOCK TABLES `station` WRITE;
/*!40000 ALTER TABLE `station` DISABLE KEYS */;
INSERT INTO `station` VALUES ('BUSAN','부산역','부산'),('CHUNC','전주역','전북'),('DAEGU','이천역','경기'),('DAEJE','수원역','경기'),('GWANGJ','대구역','대구'),('ICHEON','울산역','울산'),('JEONJ','서울역','서울'),('SEOUL','대전역','대전'),('SUWON','춘천역','강원'),('ULSAN','광주역','광주');
/*!40000 ALTER TABLE `station` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `ticket_id` int NOT NULL AUTO_INCREMENT,
  `reservation_id` int NOT NULL,
  `seat_id` int NOT NULL,
  `price` decimal(10,0) NOT NULL,
  `issue_date` datetime NOT NULL,
  PRIMARY KEY (`ticket_id`),
  KEY `fk_ticket_reservation` (`reservation_id`),
  KEY `fk_ticket_seat` (`seat_id`),
  CONSTRAINT `fk_ticket_reservation` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`reservation_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_ticket_seat` FOREIGN KEY (`seat_id`) REFERENCES `seat` (`seat_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4051 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
INSERT INTO `ticket` VALUES (4001,1001,3001,79000,'2025-04-16 17:57:00'),(4002,1002,3003,12000,'2025-03-12 07:33:00'),(4003,1003,3005,12000,'2025-06-09 09:44:00'),(4004,1004,3013,25000,'2025-02-02 19:25:00'),(4005,1005,3019,68000,'2025-04-10 13:11:00'),(4006,1006,3022,54000,'2025-05-16 16:42:00'),(4007,1007,3025,25000,'2025-06-27 16:16:00'),(4008,1008,3028,68000,'2025-03-25 12:19:00'),(4009,1009,3029,12000,'2025-05-16 15:08:00'),(4010,1010,3031,25000,'2025-02-16 13:55:00'),(4011,1011,3032,12000,'2025-06-19 07:21:00'),(4012,1012,3033,38000,'2025-06-30 15:16:00'),(4013,1013,3034,38000,'2025-06-13 10:02:00'),(4014,1014,3041,12000,'2025-06-23 10:17:00'),(4015,1015,3045,79000,'2025-02-05 19:51:00'),(4016,1016,3051,54000,'2025-04-07 06:57:00'),(4017,1017,3056,68000,'2025-03-16 14:06:00'),(4018,1018,3057,25000,'2025-06-07 08:46:00'),(4019,1019,3065,54000,'2025-01-28 16:00:00'),(4020,1020,3067,12000,'2025-05-17 13:43:00'),(4021,1021,3068,79000,'2025-06-15 10:03:00'),(4022,1022,3069,25000,'2025-06-18 05:41:00'),(4023,1023,3070,79000,'2025-01-27 09:18:00'),(4024,1024,3085,38000,'2025-06-28 15:05:00'),(4025,1025,3089,12000,'2025-06-20 08:28:00'),(4026,1026,3093,38000,'2025-03-14 08:50:00'),(4027,1027,3094,68000,'2025-01-21 05:02:00'),(4028,1028,3095,38000,'2025-01-06 15:07:00'),(4029,1029,3099,25000,'2025-06-20 19:44:00'),(4030,1030,3102,68000,'2025-03-26 17:04:00'),(4031,1031,3103,68000,'2025-01-04 16:01:00'),(4032,1032,3104,25000,'2025-03-26 09:38:00'),(4033,1033,3106,25000,'2025-05-27 09:08:00'),(4034,1034,3107,54000,'2025-05-26 16:24:00'),(4035,1035,3110,38000,'2025-04-07 17:01:00'),(4036,1036,3112,68000,'2025-01-15 05:26:00'),(4037,1037,3114,25000,'2025-04-02 19:13:00'),(4038,1038,3124,25000,'2025-06-14 14:56:00'),(4039,1039,3129,79000,'2025-01-08 15:19:00'),(4040,1040,3132,54000,'2025-03-28 11:04:00'),(4041,1041,3134,38000,'2025-04-03 08:04:00'),(4042,1042,3135,12000,'2025-05-20 10:53:00'),(4043,1043,3138,79000,'2025-06-22 05:02:00'),(4044,1044,3144,68000,'2025-06-13 16:11:00'),(4045,1045,3145,38000,'2025-04-10 06:18:00'),(4046,1046,3154,38000,'2025-04-26 15:09:00'),(4047,1047,3155,25000,'2025-05-22 08:31:00'),(4048,1048,3157,79000,'2025-05-12 19:28:00'),(4049,1049,3158,54000,'2025-06-25 19:59:00'),(4050,1050,3163,38000,'2025-04-14 10:59:00');
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `train`
--

DROP TABLE IF EXISTS `train`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `train` (
  `train_id` char(6) NOT NULL,
  `train_name` varchar(50) NOT NULL,
  `train_type` varchar(50) NOT NULL,
  `departure_station_id` char(6) NOT NULL,
  `arrival_station_id` char(6) NOT NULL,
  `departure_time` datetime NOT NULL,
  `arrival_time` datetime NOT NULL,
  PRIMARY KEY (`train_id`),
  KEY `fk_train_departure` (`departure_station_id`),
  KEY `fk_train_arrival` (`arrival_station_id`),
  CONSTRAINT `fk_train_arrival` FOREIGN KEY (`arrival_station_id`) REFERENCES `station` (`station_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_train_departure` FOREIGN KEY (`departure_station_id`) REFERENCES `station` (`station_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `train`
--

LOCK TABLES `train` WRITE;
/*!40000 ALTER TABLE `train` DISABLE KEYS */;
INSERT INTO `train` VALUES ('ITX001','무궁화호','일반열차','CHUNC','SUWON','2025-02-12 09:24:00','2025-02-12 12:21:00'),('ITX003','새마을호','일반열차','ULSAN','GWANGJ','2025-02-03 06:27:00','2025-02-03 08:13:00'),('ITX005','KTX','고속열차','DAEJE','GWANGJ','2025-04-25 18:32:00','2025-04-25 19:52:00'),('ITX007','ITX-청춘','고속열차','ULSAN','CHUNC','2025-06-23 10:42:00','2025-06-23 14:37:00'),('ITX009','ITX-청춘','고속열차','JEONJ','GWANGJ','2025-05-17 11:51:00','2025-05-17 14:25:00'),('ITX011','ITX-청춘','고속열차','ICHEON','SEOUL','2025-02-24 19:08:00','2025-02-24 22:19:00'),('ITX013','새마을호','일반열차','SEOUL','JEONJ','2025-05-24 06:06:00','2025-05-24 07:42:00'),('ITX015','무궁화호','일반열차','CHUNC','ULSAN','2025-04-02 20:49:00','2025-04-02 23:21:00'),('ITX017','ITX-청춘','고속열차','GWANGJ','DAEGU','2025-04-15 05:07:00','2025-04-15 08:40:00'),('ITX019','새마을호','일반열차','BUSAN','DAEGU','2025-01-06 12:28:00','2025-01-06 14:45:00'),('ITX021','새마을호','일반열차','GWANGJ','ULSAN','2025-03-27 14:21:00','2025-03-27 16:14:00'),('ITX023','KTX','고속열차','JEONJ','BUSAN','2025-02-28 12:52:00','2025-02-28 16:16:00'),('ITX025','새마을호','일반열차','SUWON','ICHEON','2025-04-07 17:35:00','2025-04-07 19:10:00'),('ITX027','새마을호','일반열차','ULSAN','BUSAN','2025-06-17 09:37:00','2025-06-17 13:24:00'),('ITX029','KTX','고속열차','BUSAN','JEONJ','2025-02-18 13:39:00','2025-02-18 17:22:00'),('ITX031','ITX-청춘','고속열차','DAEGU','SEOUL','2025-03-13 15:03:00','2025-03-13 16:49:00'),('ITX033','무궁화호','일반열차','JEONJ','GWANGJ','2025-04-24 17:01:00','2025-04-24 18:26:00'),('ITX035','KTX','고속열차','SUWON','BUSAN','2025-03-14 13:24:00','2025-03-14 16:21:00'),('ITX037','KTX','고속열차','JEONJ','BUSAN','2025-01-07 19:25:00','2025-01-07 22:42:00'),('ITX039','ITX-청춘','고속열차','GWANGJ','JEONJ','2025-02-25 18:06:00','2025-02-25 19:46:00'),('ITX041','KTX','고속열차','JEONJ','GWANGJ','2025-02-06 20:50:00','2025-02-06 22:40:00'),('ITX043','무궁화호','일반열차','ICHEON','SEOUL','2025-01-06 18:48:00','2025-01-06 22:31:00'),('ITX045','새마을호','일반열차','DAEGU','ULSAN','2025-03-11 06:50:00','2025-03-11 08:21:00'),('ITX047','무궁화호','일반열차','ULSAN','DAEGU','2025-06-26 16:37:00','2025-06-26 19:57:00'),('ITX049','KTX','고속열차','JEONJ','CHUNC','2025-05-30 14:48:00','2025-05-30 16:53:00'),('KTX002','KTX','고속열차','CHUNC','DAEGU','2025-03-18 05:34:00','2025-03-18 07:45:00'),('KTX004','새마을호','일반열차','JEONJ','ULSAN','2025-05-07 06:24:00','2025-05-07 08:59:00'),('KTX006','ITX-청춘','고속열차','JEONJ','CHUNC','2025-03-03 12:30:00','2025-03-03 14:35:00'),('KTX008','ITX-청춘','고속열차','SUWON','BUSAN','2025-05-31 11:47:00','2025-05-31 13:33:00'),('KTX010','ITX-청춘','고속열차','GWANGJ','CHUNC','2025-01-03 06:40:00','2025-01-03 09:30:00'),('KTX012','ITX-청춘','고속열차','SEOUL','SUWON','2025-01-02 06:13:00','2025-01-02 09:21:00'),('KTX014','새마을호','일반열차','ULSAN','SUWON','2025-01-10 15:23:00','2025-01-10 18:19:00'),('KTX016','새마을호','일반열차','SEOUL','BUSAN','2025-03-24 16:07:00','2025-03-24 19:39:00'),('KTX018','새마을호','일반열차','ICHEON','JEONJ','2025-03-17 11:32:00','2025-03-17 12:50:00'),('KTX020','ITX-청춘','고속열차','BUSAN','GWANGJ','2025-02-27 11:48:00','2025-02-27 14:02:00'),('KTX022','KTX','고속열차','BUSAN','SEOUL','2025-05-07 10:00:00','2025-05-07 12:59:00'),('KTX024','새마을호','일반열차','DAEJE','CHUNC','2025-06-05 17:52:00','2025-06-05 21:20:00'),('KTX026','ITX-청춘','고속열차','DAEJE','ULSAN','2025-02-11 16:20:00','2025-02-11 18:28:00'),('KTX028','ITX-청춘','고속열차','SUWON','GWANGJ','2025-06-04 19:48:00','2025-06-04 22:30:00'),('KTX030','ITX-청춘','고속열차','DAEGU','ULSAN','2025-04-20 12:15:00','2025-04-20 16:11:00'),('KTX032','무궁화호','일반열차','DAEJE','SEOUL','2025-03-06 19:48:00','2025-03-06 23:38:00'),('KTX034','KTX','고속열차','JEONJ','ULSAN','2025-06-17 18:40:00','2025-06-17 20:18:00'),('KTX036','KTX','고속열차','JEONJ','GWANGJ','2025-02-07 09:47:00','2025-02-07 12:05:00'),('KTX038','ITX-청춘','고속열차','BUSAN','ICHEON','2025-06-13 16:17:00','2025-06-13 17:48:00'),('KTX040','KTX','고속열차','GWANGJ','BUSAN','2025-02-03 19:15:00','2025-02-03 20:37:00'),('KTX042','새마을호','일반열차','DAEGU','ICHEON','2025-04-24 08:06:00','2025-04-24 11:35:00'),('KTX044','ITX-청춘','고속열차','SUWON','BUSAN','2025-04-13 08:30:00','2025-04-13 11:37:00'),('KTX046','무궁화호','일반열차','ULSAN','CHUNC','2025-01-15 13:24:00','2025-01-15 16:29:00'),('KTX048','새마을호','일반열차','ICHEON','JEONJ','2025-06-01 17:54:00','2025-06-01 19:04:00'),('KTX050','ITX-청춘','고속열차','BUSAN','DAEGU','2025-02-14 07:57:00','2025-02-14 09:19:00');
/*!40000 ALTER TABLE `train` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `train_schedule`
--

DROP TABLE IF EXISTS `train_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `train_schedule` (
  `schedule_id` int NOT NULL AUTO_INCREMENT,
  `train_id` char(6) NOT NULL,
  `departure_station_id` char(6) NOT NULL,
  `arrival_station_id` char(6) NOT NULL,
  `departure_time` datetime DEFAULT NULL,
  `arrival_time` datetime DEFAULT NULL,
  PRIMARY KEY (`schedule_id`),
  KEY `fk_schedule_train` (`train_id`),
  KEY `fk_schedule_dep_station` (`departure_station_id`),
  KEY `fk_schedule_arr_station` (`arrival_station_id`),
  CONSTRAINT `fk_schedule_arr_station` FOREIGN KEY (`arrival_station_id`) REFERENCES `station` (`station_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_schedule_dep_station` FOREIGN KEY (`departure_station_id`) REFERENCES `station` (`station_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_schedule_train` FOREIGN KEY (`train_id`) REFERENCES `train` (`train_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2051 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `train_schedule`
--

LOCK TABLES `train_schedule` WRITE;
/*!40000 ALTER TABLE `train_schedule` DISABLE KEYS */;
INSERT INTO `train_schedule` VALUES (2001,'KTX036','GWANGJ','SUWON','2025-04-20 06:35:00','2025-04-20 10:07:00'),(2002,'ITX019','ICHEON','DAEJE','2025-01-06 13:37:00','2025-01-06 14:56:00'),(2003,'KTX034','GWANGJ','SEOUL','2025-06-19 15:10:00','2025-06-19 18:40:00'),(2004,'KTX008','CHUNC','GWANGJ','2025-05-22 19:36:00','2025-05-22 23:19:00'),(2005,'KTX014','SUWON','ULSAN','2025-04-20 17:24:00','2025-04-20 20:11:00'),(2006,'KTX032','SUWON','BUSAN','2025-03-08 19:49:00','2025-03-08 23:32:00'),(2007,'KTX010','GWANGJ','SUWON','2025-04-24 11:28:00','2025-04-24 14:53:00'),(2008,'ITX009','GWANGJ','SEOUL','2025-04-03 11:02:00','2025-04-03 13:22:00'),(2009,'ITX021','DAEGU','GWANGJ','2025-05-21 09:23:00','2025-05-21 12:31:00'),(2010,'ITX029','JEONJ','DAEGU','2025-04-12 19:36:00','2025-04-12 23:34:00'),(2011,'KTX006','SUWON','JEONJ','2025-06-08 06:42:00','2025-06-08 09:09:00'),(2012,'ITX031','SUWON','DAEGU','2025-04-16 10:29:00','2025-04-16 14:21:00'),(2013,'ITX005','ICHEON','ICHEON','2025-01-05 15:42:00','2025-01-05 17:30:00'),(2014,'KTX014','ULSAN','BUSAN','2025-01-24 11:13:00','2025-01-24 12:44:00'),(2015,'KTX018','SEOUL','SUWON','2025-03-03 09:30:00','2025-03-03 11:32:00'),(2016,'ITX023','SUWON','BUSAN','2025-02-27 19:22:00','2025-02-27 20:53:00'),(2017,'KTX024','SUWON','SUWON','2025-06-19 07:37:00','2025-06-19 09:16:00'),(2018,'KTX040','GWANGJ','ICHEON','2025-03-24 10:50:00','2025-03-24 11:59:00'),(2019,'ITX031','SUWON','ICHEON','2025-01-02 13:37:00','2025-01-02 17:37:00'),(2020,'ITX013','DAEJE','JEONJ','2025-06-16 19:59:00','2025-06-16 22:36:00'),(2021,'ITX017','GWANGJ','ULSAN','2025-01-04 14:51:00','2025-01-04 17:00:00'),(2022,'KTX008','DAEJE','CHUNC','2025-06-01 08:39:00','2025-06-01 10:35:00'),(2023,'ITX003','DAEGU','DAEGU','2025-02-02 16:12:00','2025-02-02 19:49:00'),(2024,'KTX004','JEONJ','SUWON','2025-03-02 17:07:00','2025-03-02 20:56:00'),(2025,'ITX003','ICHEON','SEOUL','2025-03-31 08:55:00','2025-03-31 12:38:00'),(2026,'ITX045','DAEGU','ICHEON','2025-06-13 16:10:00','2025-06-13 17:19:00'),(2027,'ITX003','JEONJ','DAEJE','2025-06-11 18:29:00','2025-06-11 21:32:00'),(2028,'KTX038','CHUNC','ULSAN','2025-05-21 12:34:00','2025-05-21 14:22:00'),(2029,'KTX020','BUSAN','ULSAN','2025-04-14 11:32:00','2025-04-14 13:31:00'),(2030,'KTX044','BUSAN','JEONJ','2025-03-02 10:14:00','2025-03-02 13:38:00'),(2031,'ITX033','BUSAN','ICHEON','2025-06-23 05:11:00','2025-06-23 08:43:00'),(2032,'ITX037','BUSAN','ICHEON','2025-06-17 12:20:00','2025-06-17 14:40:00'),(2033,'KTX006','DAEGU','SEOUL','2025-06-18 06:01:00','2025-06-18 08:07:00'),(2034,'KTX010','ULSAN','JEONJ','2025-01-30 16:57:00','2025-01-30 20:00:00'),(2035,'ITX035','SUWON','DAEGU','2025-04-13 16:24:00','2025-04-13 18:22:00'),(2036,'KTX002','ICHEON','SUWON','2025-02-24 10:28:00','2025-02-24 13:17:00'),(2037,'ITX033','GWANGJ','DAEJE','2025-01-23 20:09:00','2025-01-23 21:55:00'),(2038,'ITX015','ULSAN','BUSAN','2025-04-25 08:18:00','2025-04-25 09:50:00'),(2039,'ITX005','BUSAN','BUSAN','2025-02-06 15:12:00','2025-02-06 18:52:00'),(2040,'KTX046','CHUNC','DAEJE','2025-02-14 09:06:00','2025-02-14 10:06:00'),(2041,'ITX039','DAEGU','ULSAN','2025-06-19 16:19:00','2025-06-19 17:46:00'),(2042,'KTX044','CHUNC','DAEJE','2025-06-26 06:41:00','2025-06-26 09:33:00'),(2043,'KTX022','DAEJE','SUWON','2025-05-23 12:13:00','2025-05-23 15:11:00'),(2044,'ITX023','SEOUL','SUWON','2025-05-13 05:19:00','2025-05-13 06:25:00'),(2045,'KTX050','DAEGU','BUSAN','2025-06-12 16:50:00','2025-06-12 17:58:00'),(2046,'KTX030','SEOUL','SUWON','2025-06-16 10:53:00','2025-06-16 14:09:00'),(2047,'ITX047','SUWON','GWANGJ','2025-01-30 18:03:00','2025-01-30 20:05:00'),(2048,'KTX028','DAEGU','DAEJE','2025-02-15 13:39:00','2025-02-15 17:31:00'),(2049,'ITX023','ICHEON','DAEJE','2025-04-21 09:49:00','2025-04-21 10:52:00'),(2050,'KTX048','SEOUL','ICHEON','2025-03-06 19:07:00','2025-03-06 22:44:00');
/*!40000 ALTER TABLE `train_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `available_seats_view`
--

/*!50001 DROP VIEW IF EXISTS `available_seats_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `available_seats_view` AS select `s`.`train_id` AS `train_id`,`t`.`train_name` AS `train_name`,`s`.`seat_number` AS `seat_number`,`s`.`seat_class` AS `seat_class`,`s`.`availability` AS `availability` from (`seat` `s` join `train` `t` on((`s`.`train_id` = `t`.`train_id`))) where (`s`.`availability` = 'Available') */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `detailed_train_schedule_view`
--

/*!50001 DROP VIEW IF EXISTS `detailed_train_schedule_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `detailed_train_schedule_view` AS select `ts`.`train_id` AS `train_id`,`tr`.`train_name` AS `train_name`,`ds`.`station_name` AS `departure_station`,`asn`.`station_name` AS `arrival_station`,`ts`.`departure_time` AS `departure_time`,`ts`.`arrival_time` AS `arrival_time` from (((`train_schedule` `ts` join `train` `tr` on((`ts`.`train_id` = `tr`.`train_id`))) join `station` `ds` on((`ts`.`departure_station_id` = `ds`.`station_id`))) join `station` `asn` on((`ts`.`arrival_station_id` = `asn`.`station_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `passenger_reservation_summary_view`
--

/*!50001 DROP VIEW IF EXISTS `passenger_reservation_summary_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `passenger_reservation_summary_view` AS select `p`.`first_name` AS `first_name`,`p`.`last_name` AS `last_name`,`r`.`train_id` AS `train_id`,`t`.`train_name` AS `train_name`,`r`.`seat_number` AS `seat_number`,`r`.`reservation_date` AS `reservation_date`,`pay`.`payment_status` AS `payment_status` from (((`reservation` `r` join `passenger` `p` on((`r`.`passenger_id` = `p`.`passenger_id`))) join `train` `t` on((`r`.`train_id` = `t`.`train_id`))) left join `payment` `pay` on((`r`.`reservation_id` = `pay`.`reservation_id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-02  1:08:34
