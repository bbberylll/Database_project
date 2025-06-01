-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: project_db
-- ------------------------------------------------------
-- Server version	8.0.42

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seat`
--

LOCK TABLES `seat` WRITE;
/*!40000 ALTER TABLE `seat` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
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
  PRIMARY KEY (`train_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `train`
--

LOCK TABLES `train` WRITE;
/*!40000 ALTER TABLE `train` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `train_schedule`
--

LOCK TABLES `train_schedule` WRITE;
/*!40000 ALTER TABLE `train_schedule` DISABLE KEYS */;
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

-- Dump completed on 2025-06-01 20:55:55
