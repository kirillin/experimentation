-- MySQL dump 10.13  Distrib 5.6.10, for Win32 (x86)
--
-- Host: localhost    Database: eshop
-- ------------------------------------------------------
-- Server version	5.6.10

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
-- Table structure for table `blacklist`
--

DROP TABLE IF EXISTS `blacklist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `blacklist` (
  `blacklist_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  `description` varchar(250) DEFAULT '',
  PRIMARY KEY (`blacklist_id`),
  UNIQUE KEY `BlackListID_UNIQUE` (`blacklist_id`),
  KEY `fk_BlackList_Users1_idx` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blacklist`
--

LOCK TABLES `blacklist` WRITE;
/*!40000 ALTER TABLE `blacklist` DISABLE KEYS */;
/*!40000 ALTER TABLE `blacklist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `category_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `category` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'books'),(2,'clothes'),(3,'technics'),(4,'cars'),(5,'different');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cities`
--

DROP TABLE IF EXISTS `cities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cities` (
  `city_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `city` varchar(100) DEFAULT '',
  PRIMARY KEY (`city_id`),
  KEY `city` (`city`)
) ENGINE=InnoDB AUTO_INCREMENT=271 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cities`
--

LOCK TABLES `cities` WRITE;
/*!40000 ALTER TABLE `cities` DISABLE KEYS */;
INSERT INTO `cities` VALUES (94,'Абай'),(75,'Агадырь'),(119,'Айдабул'),(251,'Ак-Коль'),(218,'Акбеит'),(76,'Акжал'),(177,'Акжал'),(28,'Акжар'),(56,'Акколь'),(219,'Акмолинск'),(220,'Аксу'),(178,'Аксуат'),(77,'Актас'),(95,'Актас'),(96,'Актау'),(252,'Актобе'),(78,'Актогай'),(197,'Актогай'),(1,'Актюбинск'),(2,'Акшам'),(253,'Акший'),(3,'Алга'),(29,'Алексеевка'),(120,'Алексеевка'),(221,'Алексеевка'),(16,'Алма-Ата'),(17,'Алматы'),(211,'Амангельды'),(198,'Андреевка'),(109,'Аралсульфат'),(110,'Аральск'),(212,'Аркалык'),(254,'Аршалы'),(235,'Арысь'),(222,'Астана'),(268,'Астана'),(223,'Астраханка'),(30,'Асубулак'),(224,'Атабасар'),(79,'Атасу'),(255,'Атбасар'),(256,'Атырау'),(269,'Атырау'),(48,'Атырау(Гурьев)'),(179,'Ауэзов'),(236,'Ачисай'),(180,'Аягуз'),(4,'Байганин'),(237,'Байжансай'),(57,'Байкадам'),(267,'Байконур'),(45,'Байчунас'),(18,'Баканас'),(257,'Балкашино'),(225,'Балкащино'),(80,'Балхаш'),(46,'Балыкши'),(181,'Баршатас'),(5,'Батамшинский'),(151,'Баутино'),(156,'Баянаул'),(152,'Бейнеу'),(31,'Белогорский'),(32,'Белоусовка'),(238,'Белые Воды'),(182,'Бельагаш'),(226,'Бестобе'),(168,'Благовещенка'),(183,'Боко'),(184,'Большая Владимировка'),(121,'Боровое'),(137,'Боровской'),(185,'Бородулиха'),(58,'Брлик'),(169,'Булаево'),(59,'Бурное'),(19,'Бурундай'),(239,'Ванновка'),(33,'Верхнеберезовский'),(227,'Вишневка'),(122,'Володарское'),(81,'Восточно-Коунрадский'),(47,'Ганюшкино'),(60,'Георгиевка'),(186,'Георгиевка'),(34,'Глубокое'),(61,'Гранитогорск'),(82,'Гульшад'),(83,'Дарьинский'),(213,'Державинск'),(111,'Джалагаш'),(62,'Джамбул'),(84,'Джамбул'),(199,'Джансугуров'),(85,'Джезды'),(86,'Джезказган'),(138,'Джетыгара'),(240,'Джетысай'),(112,'Джусалы'),(49,'Доссор'),(97,'Егиндыбулак'),(157,'Ермак'),(228,'Ерментау'),(214,'Есиль'),(215,'Жаксы'),(229,'Жалтыр'),(63,'Жанатас'),(187,'Жангизтобе'),(188,'Жарма'),(87,'Жарык'),(258,'Жезказган'),(158,'Железинка'),(230,'Жолымбет'),(35,'Зайсан'),(139,'Затобольск'),(123,'Зеренда'),(36,'Зыряновск'),(50,'Индерборский'),(6,'Иргиз'),(159,'Иртышск'),(51,'Искининский'),(20,'Иссык'),(113,'Казалинск'),(189,'Кайнар'),(88,'Кайракты'),(160,'Калкаман'),(140,'Камышное'),(200,'Капал'),(21,'Капчагай'),(201,'Карабулак'),(7,'Карабутак'),(98,'Карагайлы'),(99,'Караганда'),(37,'Карагужиха'),(89,'Каражал'),(64,'Каратау'),(52,'Каратон'),(100,'Каркаралинск'),(22,'Каскелен'),(38,'Катон-Карагай'),(124,'Келлеровка'),(241,'Кентау'),(114,'Кзыл-Орда'),(125,'Кзылту'),(101,'Киевка'),(202,'Кировский'),(190,'Кокпекты'),(65,'Коктал'),(126,'Кокчетав'),(259,'Кокшетау'),(141,'Комсомолец'),(170,'Корнеевка'),(260,'Костанай'),(127,'Красноармейск'),(161,'Краснокутск'),(128,'Красный Яр'),(203,'Кугалы'),(129,'Куйбышевский'),(53,'Кульсары'),(231,'Кургальджинский'),(261,'Курчатов'),(39,'Курчум'),(142,'Кустанай'),(143,'Кушмурун'),(262,'Кызылорда'),(162,'Лебяжье'),(242,'Ленгер'),(130,'Ленинградское'),(40,'Лениногорск'),(144,'Ленинское'),(145,'Лисаковск'),(66,'Луговое'),(163,'Майкаин'),(191,'Маканчи'),(54,'Макат'),(232,'Макинск'),(171,'Мамлютка'),(8,'Мартук'),(263,'Махамбет'),(67,'Мерке'),(68,'Михайловка'),(55,'Миялы'),(23,'Нарынкол'),(90,'Никольский'),(192,'Новая Шульба'),(9,'Новоалексеевка'),(115,'Новоказалинск'),(69,'Новотроицкое'),(153,'Новый Узень'),(70,'Ойтал'),(10,'Октябрьск'),(216,'Октябрьское'),(146,'Орджоникидзе'),(102,'Осакаровка'),(71,'Отар'),(164,'Павлодар'),(204,'Панфилов'),(172,'Петропавловск'),(173,'Пресновка'),(147,'Рудный'),(131,'Рузаевка'),(41,'Самарское'),(103,'Сарань'),(205,'Сарканд'),(243,'Сарыагач'),(206,'Сарыозек'),(91,'Сарышаган'),(148,'Семиозерное'),(193,'Семипалатинск'),(174,'Сергеевка'),(42,'Серебрянск'),(175,'Соколовка'),(266,'Степногорск'),(132,'Степняк'),(24,'Талгар'),(207,'Талды-Курган'),(133,'Талшик'),(92,'Тараз'),(116,'Тасбугет'),(194,'Таскескен'),(208,'Текели'),(244,'Темирлановка'),(104,'Темиртау'),(149,'Тобол'),(105,'Токаревка'),(106,'Топар'),(217,'Тургай'),(245,'Туркестан'),(25,'Узунагач'),(11,'Уил'),(93,'Улытау'),(107,'Ульяновский'),(264,'Уральск'),(270,'Уральска'),(195,'Урджар'),(150,'Урицкий'),(165,'Успенка'),(43,'Усть-Каменогорск'),(209,'Учарал'),(210,'Уштобе'),(154,'Форт-Шевченко'),(72,'Фурмановка'),(12,'Хромтау'),(233,'Целиноград'),(246,'Чардара'),(196,'Чарск'),(247,'Чаян'),(13,'Челкар'),(73,'Чиганак'),(117,'Чиили'),(26,'Чилик'),(248,'Чимкент'),(134,'Чистополье'),(135,'Чкалово'),(74,'Чу'),(249,'Чулаккурган'),(27,'Чунджа'),(250,'Шаульдер'),(108,'Шахтинск'),(155,'Шевченко'),(44,'Шемонаиха'),(234,'Шортанды'),(14,'Шубаркудук'),(265,'Шымкент'),(166,'Щербакты'),(136,'Щучинск'),(167,'Экибастуз'),(15,'Эмба'),(176,'Явленка'),(118,'Яныкурган');
/*!40000 ALTER TABLE `cities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `order_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  `payment_method_id` int(10) unsigned NOT NULL,
  `shipping_id` int(10) unsigned NOT NULL,
  `status_id` int(10) unsigned NOT NULL,
  `order_date` timestamp NULL DEFAULT NULL,
  `total_price` float DEFAULT '0',
  PRIMARY KEY (`order_id`),
  KEY `fk_orders_users1_idx` (`user_id`),
  KEY `fk_orders_PaypantMethod1_idx` (`payment_method_id`),
  KEY `fk_Orders_Shipping1_idx` (`shipping_id`),
  KEY `fk_Orders_Status1_idx` (`status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (20,2,1,35,4,'2013-08-05 15:31:27',599.999),(21,2,1,36,4,'2013-08-06 15:57:06',19.99),(22,2,2,37,1,'2013-08-06 16:20:01',10),(23,5,1,38,3,'2013-08-06 16:22:36',609.999),(24,2,1,39,1,'2013-08-06 16:38:43',210),(25,2,2,40,1,'2013-08-06 16:43:53',19.99),(26,8,1,41,1,'2013-08-06 16:55:20',1015620),(27,7,2,42,1,'2013-08-06 16:55:56',15000),(28,2,1,43,1,'2013-08-06 17:03:31',100),(29,2,2,44,1,'2013-08-07 11:13:40',112.98),(30,2,2,47,1,'2013-08-07 15:03:48',13),(31,2,1,48,1,'2013-08-07 15:54:16',10),(32,2,1,49,1,'2014-02-21 17:18:40',11);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders_products`
--

DROP TABLE IF EXISTS `orders_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders_products` (
  `order_id` int(10) unsigned NOT NULL,
  `product_id` int(10) unsigned NOT NULL,
  `quantity` int(10) unsigned NOT NULL DEFAULT '1',
  KEY `fk_OrdersProducts_products1_idx` (`product_id`),
  KEY `fk_Orders_Products_Orders1_idx` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders_products`
--

LOCK TABLES `orders_products` WRITE;
/*!40000 ALTER TABLE `orders_products` DISABLE KEYS */;
INSERT INTO `orders_products` VALUES (20,39,1),(21,46,1),(22,37,1),(23,39,1),(23,37,1),(24,37,1),(25,46,1),(26,39,1),(26,42,1),(26,40,1),(26,46,1),(27,40,1),(28,41,1),(29,37,6),(29,43,1),(29,46,1),(29,47,1),(30,43,1),(31,37,1),(32,37,1),(32,44,1);
/*!40000 ALTER TABLE `orders_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_methods`
--

DROP TABLE IF EXISTS `payment_methods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment_methods` (
  `payment_method_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `payment_method` varchar(45) NOT NULL DEFAULT '',
  `description` varchar(200) DEFAULT '',
  PRIMARY KEY (`payment_method_id`),
  UNIQUE KEY `Type_UNIQUE` (`payment_method`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_methods`
--

LOCK TABLES `payment_methods` WRITE;
/*!40000 ALTER TABLE `payment_methods` DISABLE KEYS */;
INSERT INTO `payment_methods` VALUES (1,'cash','Payment order is received in the office of an online store cash.'),(2,'bank cards','Payment by credit cards of international payment systems.'),(3,'emoney','Payment by electronic money.');
/*!40000 ALTER TABLE `payment_methods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `product_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `category_id` int(10) unsigned NOT NULL,
  `product_name` varchar(45) NOT NULL DEFAULT '',
  `product_price` float NOT NULL DEFAULT '0',
  `quantity` int(11) NOT NULL,
  `description` varchar(150) DEFAULT NULL,
  `actual` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`product_id`),
  KEY `fk_products_Category1_idx` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (37,1,'Наивно. Супер.',10,989,'Крутая книжка!',1),(38,2,'Кофта',100.3,0,'Красная кофта',1),(39,3,'Telephon',599.999,0,'Хороший телефон',1),(40,4,'BWM M3',15000,-1,'Машина',1),(41,3,'Монстр Сергей Иванович',100,0,'Качественный такой монстр.',1),(42,2,'Костюм Дарт Вэйдера',1000000,0,'Новый',1),(43,5,'Звезда смерти',13,2,'Поломанная, но как объект восхищение еще годится.',1),(44,5,'Кот',1,0,'рыжий и вредный. а так же очень вредный',1),(45,2,'Ð¨Ð»ÑÐ¿Ð° ÐºÑÐ¾Ð»Ð¸ÐºÐ° Ð¥Ð°ÑÐ²Ð¸',19.99,7,'Ð¨Ð»ÑÐ¿Ð° ÑÑÑ',1),(46,2,'Шляпа кролика Харви',19.99,3,'Шляпа чья',1),(47,2,'Шляпа кролика Харви',19.99,6,'Шляпа чья',1);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `role_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role` varchar(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'administrator'),(2,'user');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipping_methods`
--

DROP TABLE IF EXISTS `shipping_methods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shipping_methods` (
  `shipping_method_id` int(11) NOT NULL AUTO_INCREMENT,
  `shipping_method` varchar(45) NOT NULL DEFAULT '',
  `cost` float DEFAULT '0',
  `length` int(11) DEFAULT '0',
  `description` varchar(200) DEFAULT '',
  PRIMARY KEY (`shipping_method_id`),
  UNIQUE KEY `Name_UNIQUE` (`shipping_method`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipping_methods`
--

LOCK TABLES `shipping_methods` WRITE;
/*!40000 ALTER TABLE `shipping_methods` DISABLE KEYS */;
INSERT INTO `shipping_methods` VALUES (1,'courier',20,1,'Delivery at the appointed day and time manager, including on the day of booking, subject to availability.'),(2,'post',10,2,'Shipping is at the post office.'),(3,'pickup',0,0,'When Pickup by your order you can pick up at our office at: деревня Дедушки, спросить Ваньку.');
/*!40000 ALTER TABLE `shipping_methods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipping_orders`
--

DROP TABLE IF EXISTS `shipping_orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shipping_orders` (
  `shipping_order_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `shipping_method_id` int(10) unsigned NOT NULL,
  `city_id` int(10) unsigned NOT NULL,
  `adress` varchar(100) DEFAULT NULL,
  `zip` varchar(45) DEFAULT NULL,
  `phone` char(11) DEFAULT NULL,
  PRIMARY KEY (`shipping_order_id`),
  KEY `fk_shipping_orders_cities1_idx` (`city_id`),
  KEY `fk_shipping_orders_shipping_methods1_idx` (`shipping_method_id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipping_orders`
--

LOCK TABLES `shipping_orders` WRITE;
/*!40000 ALTER TABLE `shipping_orders` DISABLE KEYS */;
INSERT INTO `shipping_orders` VALUES (1,1,94,'123','123','+77'),(2,1,94,'12 das','1000','+7722222'),(3,1,251,'','','+77'),(4,3,31,'счм','счсм','+77мчсм'),(5,3,31,'счм','счсм','+7724345453'),(6,1,75,'123','123','+77'),(7,1,94,'adawd 12','10000000','+7700000'),(8,1,75,'123','1','+77123'),(9,1,94,'213','12','+7732'),(10,1,94,'234','3535','+77234'),(11,2,75,'4564646436','456','+77346'),(12,2,94,'556','5555','+7756'),(13,1,75,'eee','333','+7733'),(14,1,75,'222','2222','+77222'),(15,2,218,'654','666','+77464'),(16,2,119,'223','2222','+77323'),(17,1,75,'12312','12313','+77333'),(18,1,119,'df','df','+77'),(19,1,75,'12e','1111','+77212121'),(20,1,75,'1d','11','+771'),(21,1,75,'dqw','wed','+77qwd'),(22,1,94,'БлобЛОбо. kdkf, dad.','10000000','+772121'),(23,2,251,'fewf23','13413242','+7712312313'),(24,3,212,'Аркалык, тебе кирдык','13164','+7701275285'),(25,3,212,'Аркалык, тебе кирдык','13164','+770127528'),(26,1,75,'12321312','1233123','+77123123'),(27,1,94,'23','100','+7733'),(28,3,218,'123','123','+77123'),(29,3,119,'123','123','+77123'),(30,1,75,'123','151324584','+77234'),(31,1,75,'10','100','+77101111'),(32,1,75,'1232131211','1233123','+77123'),(33,2,177,'htre','765','+7743'),(34,2,77,'1010k','1111111111','+7711'),(35,2,152,'деревня дедушки, Дедушка','123456789','87010000000'),(36,1,74,'unknow :)','123456789','87771231212'),(37,3,251,'789','123456978','87555555555'),(38,1,197,'fsaf124','52151','87895082105'),(39,1,75,'1232131211','333333333333333333','87ddd'),(40,1,180,'qwer qwe qwe','78','87018964525'),(41,2,222,'Ленин ','1442','874515'),(42,1,99,'1','1','87'),(43,2,257,'123123йцу','123123123','87123123322'),(44,3,98,'101','100000000','87012020202'),(45,2,218,'4312','123',NULL),(46,2,218,'4312','123123123',NULL),(47,2,218,'4312','123123123','87311111111'),(48,2,190,'555','123123123','87010110101'),(49,1,119,'121212','123456789','87001234567');
/*!40000 ALTER TABLE `shipping_orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `statuses`
--

DROP TABLE IF EXISTS `statuses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `statuses` (
  `status_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `status` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`status_id`),
  UNIQUE KEY `status_UNIQUE` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statuses`
--

LOCK TABLES `statuses` WRITE;
/*!40000 ALTER TABLE `statuses` DISABLE KEYS */;
INSERT INTO `statuses` VALUES (4,'completed'),(2,'paid'),(1,'panding'),(3,'sent');
/*!40000 ALTER TABLE `statuses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role_id` int(10) unsigned NOT NULL,
  `login` varchar(45) NOT NULL DEFAULT '',
  `password` varchar(45) NOT NULL DEFAULT '',
  `email` varchar(45) DEFAULT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UserID_UNIQUE` (`user_id`),
  KEY `fk_Users_Roles1_idx` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,1,'admin','pass','admin@admin.ru','Black','Vasya'),(2,1,'','','qq@qq.qq','ывп','Вася'),(3,2,'vasya','vasya','vasya@vasya.ru','Pupkin','Petya'),(4,2,'qwe','qwe','qwe@qwe.rr','qwe','qwe'),(5,2,'Hodor','Hodor','wizali@mail.ru','Hodor','Hodor'),(6,2,'katishek','katishek','katishek@mail.ru','Кат','Ышек'),(7,2,'superparty08','123123','superparty@mail.ru','Ann','Ann'),(8,2,'Wade','wade6','qwe@qawe.we','Rus ','SAM');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-02-21 23:36:12
