-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: spotify-clone
-- ------------------------------------------------------
-- Server version	8.0.25

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
-- Table structure for table `adds`
--

DROP TABLE IF EXISTS `adds`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `adds` (
  `id_playlist` int NOT NULL,
  `id_content` int NOT NULL,
  `id_user` int NOT NULL,
  `created_date` datetime NOT NULL,
  PRIMARY KEY (`id_playlist`,`id_content`),
  KEY `fk_user_adds_idx` (`id_user`),
  KEY `fk_content_adds_idx` (`id_content`),
  CONSTRAINT `fk_content_adds` FOREIGN KEY (`id_content`) REFERENCES `content` (`id_content`),
  CONSTRAINT `fk_playlist_adds` FOREIGN KEY (`id_playlist`) REFERENCES `playlist` (`id_playlist`),
  CONSTRAINT `fk_user_adds` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adds`
--

LOCK TABLES `adds` WRITE;
/*!40000 ALTER TABLE `adds` DISABLE KEYS */;
/*!40000 ALTER TABLE `adds` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `album`
--

DROP TABLE IF EXISTS `album`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `album` (
  `id_album` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id_album`),
  UNIQUE KEY `id-album_UNIQUE` (`id_album`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `album`
--

LOCK TABLES `album` WRITE;
/*!40000 ALTER TABLE `album` DISABLE KEYS */;
INSERT INTO `album` VALUES (1,'Desde el fin del Mundo'),(2,'Ademas de mi'),(3,'Pininfarina'),(4,'Los Dioses'),(5,'Real Hasta la muerte'),(6,'Diosa'),(7,'Te bote'),(8,'El ultimo tour del mundo'),(9,'Los que no iban a salir'),(10,'OASIS'),(11,'Que mas pues?'),(12,'Colores'),(13,'Jackboys'),(14,'Astroworld'),(15,'Seeing Green'),(16,'Scorpion'),(17,'The highlights'),(18,'After hours'),(19,'Obras cumbres'),(20,'Random'),(21,'Musica del Alma'),(22,'El amor despues del amor'),(23,'Circo beat'),(24,'Dios los cria...'),(25,'Alta suciedad'),(26,'Podcast');
/*!40000 ALTER TABLE `album` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `artist`
--

DROP TABLE IF EXISTS `artist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `artist` (
  `id_artist` int NOT NULL AUTO_INCREMENT,
  `stage_name` varchar(90) NOT NULL,
  `birthdate` datetime DEFAULT NULL,
  `id_country` int DEFAULT NULL,
  PRIMARY KEY (`id_artist`),
  UNIQUE KEY `id-artist_UNIQUE` (`id_artist`),
  KEY `fk_country_artist_idx` (`id_country`),
  CONSTRAINT `fk_country_artist` FOREIGN KEY (`id_country`) REFERENCES `country` (`id_country`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artist`
--

LOCK TABLES `artist` WRITE;
/*!40000 ALTER TABLE `artist` DISABLE KEYS */;
INSERT INTO `artist` VALUES (21,'Duki','1996-06-24 10:10:10',1),(22,'Anuel AA','1992-11-26 10:10:10',2),(23,'Bad Bunny','1994-03-10 10:10:10',2),(24,'J Balvin','1985-05-07 10:10:10',3),(25,'Travis Scott','1992-04-30 10:10:10',4),(26,'Drake','1986-10-24 10:10:10',5),(27,'The Weekend','1990-02-16 10:10:10',5),(28,'Charly Garcia','1951-10-23 10:10:10',1),(29,'Fito Paez','1963-03-13 10:10:10',1),(30,'Andres Calamaro','1961-08-22 10:10:10',1),(31,'La arcana','1996-01-20 10:10:10',1),(32,'La arcana','1996-01-20 10:10:10',1),(33,'Ozuna', '1992-03-13 10:10:10',1), (34,'Myke Towers', '1994-01-15 10:10:10',1);
/*!40000 ALTER TABLE `artist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `content`
--

DROP TABLE IF EXISTS `content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `content` (
  `id_content` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `length` int NOT NULL,
  `id_genre` int DEFAULT NULL,
  `id_language` int DEFAULT NULL,
  PRIMARY KEY (`id_content`),
  UNIQUE KEY `id-content_UNIQUE` (`id_content`),
  KEY `fk_genre_content_idx` (`id_genre`),
  KEY `fk_language_content_idx` (`id_language`),
  CONSTRAINT `fk_genre_content` FOREIGN KEY (`id_genre`) REFERENCES `genre` (`id_genre`),
  CONSTRAINT `fk_language_content` FOREIGN KEY (`id_language`) REFERENCES `language` (`id_language`)
) ENGINE=InnoDB AUTO_INCREMENT=190 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `content`
--

LOCK TABLES `content` WRITE;
/*!40000 ALTER TABLE `content` DISABLE KEYS */;
INSERT INTO `content` VALUES (127,'Sudor y trabajo',3,2,1),(128,'Pintao',3,2,1),(129,'Chico estrella',3,2,1),(130,'Ademas de mi',3,2,1),(131,'Pininfarina',4,2,1),(132,'Los dioses',4,3,1),(133,'100',5,3,1),(134,'Antes',4,3,1),(135,'Quiero beber',4,3,1),(136,'Pensando en ti',4,2,1),(137,'Brindemos',5,2,1),(138,'Diosa(REMIX)',5,2,1),(139,'Soy peor',6,3,1),(140,'Te bote(REMIX)',5,2,1),(141,'El mundo es mio',5,3,1),(142,'Te mudaste',4,3,1),(143,'La noche de anoche',4,3,1),(144,'Si ella sale',4,3,1),(145,'Bye me fui',5,3,1),(146,'Mojaita',5,2,1),(147,'Yo le llego',4,3,1),(148,'Un peso',5,3,1),(149,'Que mas pues?',6,2,1),(150,'Amarillo',5,2,1),(151,'Azul',5,2,1),(152,'Rojo',5,2,1),(153,'HIGHEST IN THE ROOM',5,2,2),(154,'STARGAZING',5,2,2),(155,'YOSEMITE',6,2,2),(156,'CAROUSEL',5,2,2),(157,'SICKO MODE',4,2,2),(158,'R.I.P. SCREW',5,2,2),(159,'Seing green',6,2,2),(160,'Survival',5,2,2),(161,'Nonstop',5,2,2),(162,'Elevate',5,2,2),(163,'Save your tears',5,2,2),(164,'Blinding Lights',5,2,2),(165,'In your eyes',5,2,2),(166,'Alone again',4,2,2),(167,'Too late',4,2,2),(168,'Hardest to love',5,2,2),(169,'No voy en tren',5,1,1),(170,'Tu amor',5,1,1),(171,'Rezo por vos',5,1,1),(172,'Ella es tan kubrick',4,1,1),(173,'Primavera',5,1,1),(174,'Rivalidad',4,1,1),(175,'Dos edificios dorados',5,1,1),(176,'Variaciones sobre musica del alma',5,1,1),(177,'Hombres de mala sangre',5,1,1),(178,'El amor despues del amor',5,1,1),(179,'Dos dias en la vida',5,1,1),(180,'Circo beat',4,1,1),(181,'Mariposa tecknicolor',5,1,1),(182,'Normal 1',5,1,1),(183,'Bohemio',4,1,1),(184,'Tuyo siempre',5,1,1),(185,'Flaca',5,1,1),(186,'Alta suciedad',4,1,1),(187,'Todo lo demas tambien',6,1,1),(188,'Memorium a humberto maturana',59,4,1),(189,'La abnegacion es evolicon',50,4,1);
/*!40000 ALTER TABLE `content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country` (
  `id_country` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id_country`),
  UNIQUE KEY `id-country_UNIQUE` (`id_country`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'Argentina'),(2,'Puerto Rico'),(3,'Colombia'),(4,'Estados Unidos'),(5,'Canada');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device`
--

DROP TABLE IF EXISTS `device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `device` (
  `id_device` int NOT NULL AUTO_INCREMENT,
  `mac_address` varchar(45) NOT NULL,
  `id_user` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `model` varchar(45) DEFAULT NULL,
  `pairing_date` datetime NOT NULL,
  PRIMARY KEY (`id_device`),
  UNIQUE KEY `id-device_UNIQUE` (`id_device`),
  KEY `fk_user_device_idx` (`id_user`),
  CONSTRAINT `fk_user_device` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device`
--

LOCK TABLES `device` WRITE;
/*!40000 ALTER TABLE `device` DISABLE KEYS */;
/*!40000 ALTER TABLE `device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genre`
--

DROP TABLE IF EXISTS `genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genre` (
  `id_genre` int NOT NULL AUTO_INCREMENT,
  `genre` varchar(45) NOT NULL,
  PRIMARY KEY (`id_genre`),
  UNIQUE KEY `id-genre_UNIQUE` (`id_genre`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genre`
--

LOCK TABLES `genre` WRITE;
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
INSERT INTO `genre` VALUES (1,'Rock'),(2,'Trap'),(3,'Reggaeton'),(4,'Podcast');
/*!40000 ALTER TABLE `genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `language`
--

DROP TABLE IF EXISTS `language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `language` (
  `id_language` int NOT NULL AUTO_INCREMENT,
  `language` varchar(45) NOT NULL,
  PRIMARY KEY (`id_language`),
  UNIQUE KEY `id-language_UNIQUE` (`id_language`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `language`
--

LOCK TABLES `language` WRITE;
/*!40000 ALTER TABLE `language` DISABLE KEYS */;
INSERT INTO `language` VALUES (1,'Spanish'),(2,'English'),(3,'Portuguese');
/*!40000 ALTER TABLE `language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `listen`
--

DROP TABLE IF EXISTS `listen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `listen` (
  `id_user` int NOT NULL,
  `id_content` int NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`id_user`,`id_content`),
  KEY `fk_content_listen_idx` (`id_content`),
  CONSTRAINT `fk_content_listen` FOREIGN KEY (`id_content`) REFERENCES `content` (`id_content`),
  CONSTRAINT `fk_user_listen` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `listen`
--

LOCK TABLES `listen` WRITE;
/*!40000 ALTER TABLE `listen` DISABLE KEYS */;
/*!40000 ALTER TABLE `listen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `makes`
--

DROP TABLE IF EXISTS `makes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `makes` (
  `id_artist` int NOT NULL,
  `id_podcast` int NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`id_artist`,`id_podcast`),
  KEY `fk_podcast_makes_idx` (`id_podcast`),
  CONSTRAINT `fk_artist_makes` FOREIGN KEY (`id_artist`) REFERENCES `artist` (`id_artist`),
  CONSTRAINT `fk_podcast_makes` FOREIGN KEY (`id_podcast`) REFERENCES `podcast` (`id_content`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `makes`
--

LOCK TABLES `makes` WRITE;
/*!40000 ALTER TABLE `makes` DISABLE KEYS */;
/*!40000 ALTER TABLE `makes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlist`
--

DROP TABLE IF EXISTS `playlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `playlist` (
  `id_playlist` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `created_at` varchar(45) DEFAULT NULL,
  `id_user` int DEFAULT NULL,
  PRIMARY KEY (`id_playlist`),
  UNIQUE KEY `id-playlist_UNIQUE` (`id_playlist`),
  KEY `fk_user_playlist_idx` (`id_user`),
  CONSTRAINT `fk_user_playlist` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist`
--

LOCK TABLES `playlist` WRITE;
/*!40000 ALTER TABLE `playlist` DISABLE KEYS */;
/*!40000 ALTER TABLE `playlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `podcast`
--

DROP TABLE IF EXISTS `podcast`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `podcast` (
  `id_content` int NOT NULL,
  `chapter` int NOT NULL,
  PRIMARY KEY (`id_content`),
  UNIQUE KEY `chapter_UNIQUE` (`chapter`),
  CONSTRAINT `fk_content_podcast` FOREIGN KEY (`id_content`) REFERENCES `content` (`id_content`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `podcast`
--

LOCK TABLES `podcast` WRITE;
/*!40000 ALTER TABLE `podcast` DISABLE KEYS */;
INSERT INTO `podcast` VALUES (188,1),(189,2);
/*!40000 ALTER TABLE `podcast` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prefer`
--

DROP TABLE IF EXISTS `prefer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prefer` (
  `id_user` int NOT NULL,
  `id_language` int DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  KEY `fk_language_prefer_idx` (`id_language`),
  CONSTRAINT `fk_language_prefer` FOREIGN KEY (`id_language`) REFERENCES `language` (`id_language`),
  CONSTRAINT `fk_user_prefer` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prefer`
--

LOCK TABLES `prefer` WRITE;
/*!40000 ALTER TABLE `prefer` DISABLE KEYS */;
/*!40000 ALTER TABLE `prefer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uploads`
--

DROP TABLE IF EXISTS `uploads`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `uploads` (
  `id_artist` int NOT NULL,
  `id_content` int NOT NULL,
  `id_album` int NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`id_artist`,`id_content`,`id_album`),
  KEY `fk_album_uploads_idx` (`id_album`),
  KEY `fk_content_uploads_idx` (`id_content`),
  CONSTRAINT `fk_album_uploads` FOREIGN KEY (`id_album`) REFERENCES `album` (`id_album`),
  CONSTRAINT `fk_artist_uploads` FOREIGN KEY (`id_artist`) REFERENCES `artist` (`id_artist`),
  CONSTRAINT `fk_content_uploads` FOREIGN KEY (`id_content`) REFERENCES `content` (`id_content`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uploads`
--

LOCK TABLES `uploads` WRITE;
/*!40000 ALTER TABLE `uploads` DISABLE KEYS */;
INSERT INTO `uploads` VALUES (21,127,1,'2021-04-22 10:10:10'),(21,128,1,'2021-04-22 10:10:10'),(21,129,1,'2021-04-22 10:10:10'),(21,130,2,'2021-04-04 10:10:10'),(21,131,3,'2020-08-29 10:10:10'),(22,132,4,'2021-01-22 10:10:10'),(32,132,4,'2021-01-22 10:10:10'),(22,133,4,'2021-01-22 10:10:10'),(22,134,4,'2021-01-22 10:10:10'),(22,135,5,'2018-07-17 10:10:10'),(22,136,5,'2018-07-17 10:10:10'),(22,137,6,'2020-10-28 10:10:10'),(22,138,7,'2019-01-04 10:10:10'),(34,138,7,'2019-01-04 10:10:10'),(23,139,7,'2019-01-04 10:10:10'),(23,140,7,'2019-01-04 10:10:10'),(33,140,7,'2019-01-04 10:10:10'),(23,141,8,'2020-11-27 10:10:10'),(23,142,8,'2019-01-04 10:10:10'),(23,143,8,'2019-01-04 10:10:10'),(23,144,9,'2020-05-10 10:10:10'),(23,145,9,'2019-01-04 10:10:10'),(24,146,10,'2019-06-21 10:10:10'),(24,147,10,'2019-06-21 10:10:10'),(24,148,10,'2019-06-21 10:10:10'),(24,149,11,'2021-05-27 10:10:10'),(24,150,12,'2020-03-19 10:10:10'),(24,151,12,'2020-03-19 10:10:10'),(24,152,12,'2020-03-19 10:10:10'),(25,153,13,'2019-12-27 10:10:10'),(25,154,14,'2018-08-03 10:10:10'),(25,155,14,'2018-08-03 10:10:10'),(25,156,14,'2018-08-03 10:10:10'),(25,157,14,'2018-08-03 10:10:10'),(25,158,14,'2018-08-03 10:10:10'),(26,159,15,'2021-05-14 10:10:10'),(26,160,16,'2018-06-29 10:10:10'),(26,161,16,'2018-06-29 10:10:10'),(26,162,16,'2018-06-29 10:10:10'),(27,163,17,'2021-02-05 10:10:10'),(27,164,17,'2021-02-05 10:10:10'),(27,165,17,'2021-02-05 10:10:10'),(27,166,18,'2020-03-20 10:10:10'),(27,167,18,'2020-03-20 10:10:10'),(27,168,18,'2020-03-20 10:10:10'),(28,169,19,'1999-01-01 10:10:10'),(28,170,19,'1999-01-01 10:10:10'),(28,171,19,'1999-01-01 10:10:10'),(28,172,20,'2017-02-23 10:10:10'),(28,173,20,'2017-02-23 10:10:10'),(28,174,20,'2017-02-23 10:10:10'),(28,175,21,'1977-11-11 10:10:10'),(28,176,21,'1977-11-11 10:10:10'),(28,177,21,'1977-11-11 10:10:10'),(29,178,22,'1992-06-01 10:10:10'),(29,179,22,'1992-06-01 10:10:10'),(29,180,23,'1994-01-01 10:10:10'),(29,181,23,'1994-01-01 10:10:10'),(29,182,23,'1994-01-01 10:10:10'),(30,183,24,'2021-05-27 10:10:10'),(30,184,24,'2021-05-27 10:10:10'),(30,185,24,'2021-05-27 10:10:10'),(30,186,25,'1997-09-09 10:10:10'),(30,187,25,'1997-09-09 10:10:10'),(31,188,26,'2021-05-14 10:10:10'),(31,189,26,'2021-05-14 10:10:10');
/*!40000 ALTER TABLE `uploads` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id_user` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(90) NOT NULL,
  `birthdate` datetime DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `id_country` int DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `id-user_UNIQUE` (`id_user`),
  KEY `fk_country_user_idx` (`id_country`),
  CONSTRAINT `fk_country_user` FOREIGN KEY (`id_country`) REFERENCES `country` (`id_country`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-06 20:51:20
