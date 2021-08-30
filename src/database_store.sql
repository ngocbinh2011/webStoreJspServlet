-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: moneystore
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `accountlogin`
--

DROP TABLE IF EXISTS `accountlogin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accountlogin` (
  `usernameLogin` varchar(30) NOT NULL,
  `passwordLogin` varchar(30) NOT NULL,
  UNIQUE KEY `usernameLogin` (`usernameLogin`),
  CONSTRAINT `accountlogin_chk_1` CHECK (((char_length(`passwordLogin`) > 5) and (char_length(`passwordLogin`) < 30) and (char_length(`usernameLogin`) > 5) and (char_length(`usernameLogin`) < 30)))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accountlogin`
--

LOCK TABLES `accountlogin` WRITE;
/*!40000 ALTER TABLE `accountlogin` DISABLE KEYS */;
INSERT INTO `accountlogin` VALUES ('canngocbinh','20112001'),('canngocbinh1','20112001'),('canngocbinh2','20112001'),('canngocbinh3','20112001'),('canngocbinh4','20112001'),('nguyentienan','20112001'),('nguyenvana','canngocbinh');
/*!40000 ALTER TABLE `accountlogin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `currenciesdata`
--

DROP TABLE IF EXISTS `currenciesdata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `currenciesdata` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `CATEGORY` varchar(30) NOT NULL,
  `national` varchar(30) NOT NULL,
  `image` varchar(30) DEFAULT NULL,
  `value` int NOT NULL,
  `amount` int NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `currenciesdata_chk_1` CHECK (((`amount` > 0) and (`value` > 0)))
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currenciesdata`
--

LOCK TABLES `currenciesdata` WRITE;
/*!40000 ALTER TABLE `currenciesdata` DISABLE KEYS */;
INSERT INTO `currenciesdata` VALUES (20,'EVERY THING','ET','ARGENTINA',NULL,312,31),(22,'BANG ANH','GBP','ANH',NULL,23102,3213),(23,'DASDA','DASDA','DADA',NULL,312,31),(24,'DASD','DSA','DAS',NULL,312,31),(25,'CANNGOCBINH','2010','VIET NAM',NULL,3021039,3131),(26,'VIET NAM DONG fake','VND','VIET NAM',NULL,31412,313),(27,'eqe','eq','3',NULL,3,3),(28,'4131','31','31',NULL,31,31),(29,'nghaud','fsa','ds',NULL,23,23),(30,'3131','313','131',NULL,31,31),(31,'trieu xau','321','321',NULL,321,31),(32,'cann ogsad','31','31',NULL,312,3);
/*!40000 ALTER TABLE `currenciesdata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `record`
--

DROP TABLE IF EXISTS `record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `record` (
  `id` int NOT NULL DEFAULT '0',
  `name` varchar(30) NOT NULL,
  `CATEGORY` varchar(30) NOT NULL,
  `national` varchar(30) NOT NULL,
  `image` varchar(30) DEFAULT NULL,
  `value` int NOT NULL,
  `amount` int NOT NULL,
  `dateEdit` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `record`
--

LOCK TABLES `record` WRITE;
/*!40000 ALTER TABLE `record` DISABLE KEYS */;
INSERT INTO `record` VALUES (2,'VIET NAM DONG','VND','VIET NAM',NULL,20000,2,'2021-05-03'),(19,'42','4242','242',NULL,424,42,'2021-08-29'),(17,'DO LA MY','USD','MY',NULL,20000,2,'2021-08-29'),(18,'DONG EURO','EUR','CHAU AU',NULL,24000,1,'2021-08-29'),(19,'BANG ANH','GBP','ANH',NULL,23102,3213,'2021-08-29'),(20,'DASDA','DASDA','DADA',NULL,312,31,'2021-08-29'),(17,'DO LA MY','USD','MY',NULL,20000,3,'2021-08-29'),(17,'DO LA MY','USD','MY',NULL,20000,4,'2021-08-29'),(17,'DO LA MY','USD','MY',NULL,20000,100,'2021-08-29'),(17,'DO LA MY','USD','MY',NULL,25000,100,'2021-08-29'),(22,'CANNGOCBINH','2010','VIET NAM',NULL,3021039,3131,'2021-08-29'),(23,'VIET NAM DONG','VND','VIET NAM',NULL,31412,313,'2021-08-29'),(20,'EVERY THING','ET','ARGENTINA',NULL,312,31,'2021-08-29'),(17,'DO LA MY','USD','MY',NULL,25000,101,'2021-08-29'),(24,'eqe','eq','3',NULL,3,3,'2021-08-29'),(26,'VIET NAM DONG fake','VND','VIET NAM',NULL,31412,313,'2021-08-30'),(28,'4131','31','31',NULL,31,31,'2021-08-30'),(29,'nghaud','fsa','ds',NULL,23,23,'2021-08-30'),(30,'3131','313','131',NULL,31,31,'2021-08-30'),(31,'trieu xau','321','321',NULL,321,31,'2021-08-30'),(32,'cann ogsad','31','31',NULL,312,3,'2021-08-30');
/*!40000 ALTER TABLE `record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sessioninfo`
--

DROP TABLE IF EXISTS `sessioninfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sessioninfo` (
  `SESSIONID` varchar(50) NOT NULL,
  `USERNAMELOGIN` varchar(30) NOT NULL,
  `passwordLogin` varchar(30) NOT NULL,
  `nearlest` date DEFAULT NULL,
  UNIQUE KEY `SESSIONID` (`SESSIONID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sessioninfo`
--

LOCK TABLES `sessioninfo` WRITE;
/*!40000 ALTER TABLE `sessioninfo` DISABLE KEYS */;
INSERT INTO `sessioninfo` VALUES ('23840F52C3D2DEF5B358281A3505C219','canngocbinh','20112001','2021-08-29'),('7B652ACAA729E76833457B42808A1B15','canngocbinh','20112001','2021-08-30'),('85633DEEA936CF70DEBBA41246D472F6','canngocbinh','20112001','2021-08-29'),('99C86A89E51FF48768E0D0F27CE75029','canngocbinh','20112001','2021-08-30');
/*!40000 ALTER TABLE `sessioninfo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-30 10:22:28
