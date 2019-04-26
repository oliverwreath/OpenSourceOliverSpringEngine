-- MySQL dump 10.13  Distrib 5.7.24, for Win64 (x86_64)
--
-- Host: springenginedb.cprxivj10xmm.us-east-1.rds.amazonaws.com    Database: spring_engine_test
-- ------------------------------------------------------
-- Server version	5.7.23-log

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
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup
--

SET @@GLOBAL.GTID_PURGED='';

--
-- Table structure for table `admins`
--

DROP TABLE IF EXISTS `admins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admins` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_on` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `updated_on` datetime(6) DEFAULT NULL,
  `city` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `country` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `mobile` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `state` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `street_address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `zipcode` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `gender` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `landing_offer` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `major` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `personal_interest` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `school` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `year_of_graduation` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbqi0dc0gtrod27n7w3o1dswin` (`user_id`),
  CONSTRAINT `FKbqi0dc0gtrod27n7w3o1dswin` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admins`
--

LOCK TABLES `admins` WRITE;
/*!40000 ALTER TABLE `admins` DISABLE KEYS */;
/*!40000 ALTER TABLE `admins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authorities` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authority` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_by` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_on` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `updated_on` datetime(6) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkjxx2h0wm9a4uu1vewhqmcks9` (`user_id`),
  CONSTRAINT `FKkjxx2h0wm9a4uu1vewhqmcks9` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` (`id`, `authority`, `created_by`, `created_on`, `updated_by`, `updated_on`, `user_id`) VALUES (1,'ROLE_ADMIN',NULL,NULL,NULL,NULL,1),(2,'ROLE_ADMIN',NULL,NULL,NULL,NULL,2),(3,'ROLE_USER',NULL,NULL,NULL,NULL,3);
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `courses` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `catalog` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `course_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `course_price` decimal(13,5) DEFAULT NULL,
  `created_by` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_on` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `updated_on` datetime(6) DEFAULT NULL,
  `purchase_count` bigint(20) DEFAULT NULL,
  `purchase_sum` bigint(20) DEFAULT NULL,
  `view_count` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` (`id`, `catalog`, `course_name`, `course_price`, `created_by`, `created_on`, `updated_by`, `updated_on`, `purchase_count`, `purchase_sum`, `view_count`) VALUES (1,'四大_BIG4','example_course1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,'咨询_CONSULTING','example_course2',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,'大数据_BIG_DATA','example_course3',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,'FLAG','example_course4',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,'金融_FINANCE','example_course5',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,'快消_CPG','example_course6',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `drop_down_list_entities`
--

DROP TABLE IF EXISTS `drop_down_list_entities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `drop_down_list_entities` (
  `id` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_by` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_on` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `updated_on` datetime(6) DEFAULT NULL,
  `my_values` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `drop_down_list_entities`
--

LOCK TABLES `drop_down_list_entities` WRITE;
/*!40000 ALTER TABLE `drop_down_list_entities` DISABLE KEYS */;
INSERT INTO `drop_down_list_entities` (`id`, `created_by`, `created_on`, `updated_by`, `updated_on`, `my_values`) VALUES ('CATALOG',NULL,NULL,NULL,NULL,'四大_BIG4, 咨询_CONSULTING, 大数据_BIG_DATA, FLAG, 金融_FINANCE, 快消_CPG'),('COUNTRY',NULL,NULL,NULL,NULL,'USA, Canada, Australia, New_Zealand, China, Japan, Korea, UK, Germany, Switzerland, France, Spain, Italy, Other_EuropeAmerica, Other_AsiaPacific, Other');
/*!40000 ALTER TABLE `drop_down_list_entities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `members`
--

DROP TABLE IF EXISTS `members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `members` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_on` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `updated_on` datetime(6) DEFAULT NULL,
  `city` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `country` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `mobile` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `state` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `street_address` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `zipcode` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `video_id` bigint(20) DEFAULT NULL,
  `gender` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `is_vip` bit(1) DEFAULT NULL,
  `job_target` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `landing_offer` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `major` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `personal_interest` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `school` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `year_of_graduation` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt558db5tqfrmhxqrmfhll5b22` (`user_id`),
  CONSTRAINT `FKt558db5tqfrmhxqrmfhll5b22` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members`
--

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;
INSERT INTO `members` (`id`, `created_by`, `created_on`, `updated_by`, `updated_on`, `city`, `country`, `email`, `mobile`, `state`, `street_address`, `zipcode`, `video_id`, `gender`, `is_vip`, `job_target`, `landing_offer`, `major`, `name`, `personal_interest`, `school`, `year_of_graduation`, `user_id`) VALUES (1,NULL,NULL,NULL,NULL,NULL,'Canada',NULL,NULL,NULL,NULL,NULL,NULL,'FEMALE',_binary '','四大_BIG4',NULL,'Maj_example','Anne_example',NULL,'Sch_example',NULL,2),(2,NULL,NULL,NULL,NULL,NULL,'Canada',NULL,NULL,NULL,NULL,NULL,NULL,'MALE',_binary '\0','四大_BIG4',NULL,NULL,'Jack_example',NULL,NULL,NULL,3),(3,NULL,NULL,NULL,NULL,NULL,'Canada',NULL,NULL,NULL,NULL,NULL,NULL,'MALE',_binary '\0','咨询_CONSULTING',NULL,NULL,'Jack_example',NULL,NULL,NULL,4),(4,NULL,NULL,NULL,NULL,NULL,'Canada',NULL,NULL,NULL,NULL,NULL,NULL,'MALE',_binary '\0','大数据_BIG_DATA',NULL,NULL,'Jack_example',NULL,NULL,NULL,5),(5,NULL,NULL,NULL,NULL,NULL,'Canada',NULL,NULL,NULL,NULL,NULL,NULL,'MALE',_binary '\0','FLAG',NULL,NULL,'Jack_example',NULL,NULL,NULL,6),(6,NULL,NULL,NULL,NULL,NULL,'Canada',NULL,NULL,NULL,NULL,NULL,NULL,'MALE',_binary '\0','金融_FINANCE',NULL,NULL,'Jack_example',NULL,NULL,NULL,7),(7,NULL,NULL,NULL,NULL,NULL,'Canada',NULL,NULL,NULL,NULL,NULL,NULL,'MALE',_binary '\0','快消_CPG',NULL,NULL,'Jack_example',NULL,NULL,NULL,8);
/*!40000 ALTER TABLE `members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_courses`
--

DROP TABLE IF EXISTS `order_courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_courses` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `catalog` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `course_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_by` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_on` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `updated_on` datetime(6) DEFAULT NULL,
  `purchase_count` bigint(20) DEFAULT NULL,
  `purchase_sum` bigint(20) DEFAULT NULL,
  `view_count` bigint(20) DEFAULT NULL,
  `order_type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `course_id` bigint(20) NOT NULL,
  `member_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjfxkwtg5g427ssf943cn3e48o` (`course_id`),
  KEY `FKtqo2tkvljf11t8c5tqkf7lk64` (`member_id`),
  CONSTRAINT `FKjfxkwtg5g427ssf943cn3e48o` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`),
  CONSTRAINT `FKtqo2tkvljf11t8c5tqkf7lk64` FOREIGN KEY (`member_id`) REFERENCES `members` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_courses`
--

LOCK TABLES `order_courses` WRITE;
/*!40000 ALTER TABLE `order_courses` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_videos`
--

DROP TABLE IF EXISTS `order_videos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_videos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `catalog` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `course_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_by` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_on` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `updated_on` datetime(6) DEFAULT NULL,
  `purchase_count` bigint(20) DEFAULT NULL,
  `purchase_sum` bigint(20) DEFAULT NULL,
  `view_count` bigint(20) DEFAULT NULL,
  `order_type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `member_id` bigint(20) NOT NULL,
  `video_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7n22mpcqp5tqtshvxkw0klg9w` (`member_id`),
  KEY `FKsrgvcyml3wk3g7n5o65fqkkcd` (`video_id`),
  CONSTRAINT `FK7n22mpcqp5tqtshvxkw0klg9w` FOREIGN KEY (`member_id`) REFERENCES `members` (`id`),
  CONSTRAINT `FKsrgvcyml3wk3g7n5o65fqkkcd` FOREIGN KEY (`video_id`) REFERENCES `videos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_videos`
--

LOCK TABLES `order_videos` WRITE;
/*!40000 ALTER TABLE `order_videos` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_videos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_on` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `updated_on` datetime(6) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `created_by`, `created_on`, `updated_by`, `updated_on`, `enabled`, `password`, `user_email`, `user_type`) VALUES (1,NULL,NULL,NULL,NULL,_binary '','$2a$10$E7z3fBbyHZtqLYre4S9D/uGc8Nhf6S2NnvVkT3lDMMeNkCXc/u2Gu','admin@gmail.com','ADMIN'),(2,NULL,NULL,NULL,NULL,_binary '','$2a$10$E7z3fBbyHZtqLYre4S9D/uGc8Nhf6S2NnvVkT3lDMMeNkCXc/u2Gu','admin@example.com','ADMIN'),(3,NULL,NULL,NULL,NULL,_binary '','$2a$10$E7z3fBbyHZtqLYre4S9D/uGc8Nhf6S2NnvVkT3lDMMeNkCXc/u2Gu','member@example.com','MEMBER'),(4,NULL,NULL,NULL,NULL,_binary '','$2a$10$E7z3fBbyHZtqLYre4S9D/uGc8Nhf6S2NnvVkT3lDMMeNkCXc/u2Gu','444@example.com','MEMBER'),(5,NULL,NULL,NULL,NULL,_binary '','$2a$10$E7z3fBbyHZtqLYre4S9D/uGc8Nhf6S2NnvVkT3lDMMeNkCXc/u2Gu','555@example.com','MEMBER'),(6,NULL,NULL,NULL,NULL,_binary '','$2a$10$E7z3fBbyHZtqLYre4S9D/uGc8Nhf6S2NnvVkT3lDMMeNkCXc/u2Gu','666@example.com','MEMBER'),(7,NULL,NULL,NULL,NULL,_binary '','$2a$10$E7z3fBbyHZtqLYre4S9D/uGc8Nhf6S2NnvVkT3lDMMeNkCXc/u2Gu','777@example.com','MEMBER'),(8,NULL,NULL,NULL,NULL,_binary '','$2a$10$E7z3fBbyHZtqLYre4S9D/uGc8Nhf6S2NnvVkT3lDMMeNkCXc/u2Gu','888@example.com','MEMBER');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `videos`
--

DROP TABLE IF EXISTS `videos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `videos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `catalog` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `course_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_by` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_on` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `updated_on` datetime(6) DEFAULT NULL,
  `purchase_count` bigint(20) DEFAULT NULL,
  `purchase_sum` bigint(20) DEFAULT NULL,
  `view_count` bigint(20) DEFAULT NULL,
  `video_link` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `video_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `video_price` decimal(13,5) DEFAULT NULL,
  `course_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhm8827of7nj7f1av3c5i897yr` (`course_id`),
  CONSTRAINT `FKhm8827of7nj7f1av3c5i897yr` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `videos`
--

LOCK TABLES `videos` WRITE;
/*!40000 ALTER TABLE `videos` DISABLE KEYS */;
INSERT INTO `videos` (`id`, `catalog`, `course_name`, `created_by`, `created_on`, `updated_by`, `updated_on`, `purchase_count`, `purchase_sum`, `view_count`, `video_link`, `video_name`, `video_price`, `course_id`) VALUES (1,'FLAG',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'example_video',NULL,NULL);
/*!40000 ALTER TABLE `videos` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-23 18:44:08
