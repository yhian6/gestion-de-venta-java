-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: gestionventa
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorias` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre_categoria` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` VALUES (1,'bebidas'),(2,'galletas'),(3,'chocolate'),(5,'pañales'),(6,'refrescos'),(7,'nose'),(8,'lololo'),(9,'actualizado'),(10,'gaseosas'),(11,'calamina'),(12,'lacteos'),(13,'fideos'),(15,'atun'),(16,'papel higienico');
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `apellidos` varchar(100) DEFAULT NULL,
  `dni` char(8) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'yhian carlos','chiroque ramos','48582277'),(2,'saul','chiroque','02882258'),(3,'fiorella','chiroque ramos','9876543'),(6,'pedro','zapata','12345678'),(7,'pedro','castillo','69854712'),(8,'pedro','castro','78789654'),(9,'yoli','chiroque','78987456'),(13,'yoli','zapata','48985874');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compras`
--

DROP TABLE IF EXISTS `compras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compras` (
  `id_compra` bigint NOT NULL AUTO_INCREMENT,
  `fecha` datetime DEFAULT CURRENT_TIMESTAMP,
  `id_proveedor` bigint DEFAULT NULL,
  `id_usuario` tinyint unsigned NOT NULL,
  `total` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id_compra`),
  KEY `id_proveedor` (`id_proveedor`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `compras_ibfk_1` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`id`),
  CONSTRAINT `compras_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compras`
--

LOCK TABLES `compras` WRITE;
/*!40000 ALTER TABLE `compras` DISABLE KEYS */;
INSERT INTO `compras` VALUES (1,'2025-04-29 04:54:14',1,12,10.00),(2,'2025-04-29 16:17:57',2,12,10.00),(3,'2025-04-29 16:21:10',1,12,30.00),(4,'2025-04-29 22:43:30',1,12,10.00),(5,'2025-04-29 22:59:45',2,12,90.00),(6,'2025-04-29 23:01:02',1,12,92.50),(7,'2025-04-29 23:17:35',1,12,35.00),(8,'2025-04-29 23:57:38',NULL,12,10.00),(9,'2025-04-30 00:18:47',NULL,12,40.50),(10,'2025-04-30 01:47:36',NULL,12,22.50),(11,'2025-04-30 01:55:18',1,12,10.00),(12,'2025-04-30 02:10:53',NULL,12,2.50),(13,'2025-04-30 02:17:36',NULL,12,2.50),(14,'2025-04-30 02:21:11',2,12,20.00),(15,'2025-04-30 02:56:15',1,12,62.50),(16,'2025-04-30 03:03:27',NULL,12,125.00),(17,'2025-04-30 20:15:20',2,12,30.00),(18,'2025-05-28 00:44:52',2,12,35.00),(19,'2025-07-29 10:27:40',1,12,70.00);
/*!40000 ALTER TABLE `compras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_compra`
--

DROP TABLE IF EXISTS `detalle_compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_compra` (
  `id_detalle` bigint NOT NULL AUTO_INCREMENT,
  `id_compra` bigint DEFAULT NULL,
  `id_producto` bigint DEFAULT NULL,
  `cantidad` int DEFAULT NULL,
  `precio_unitario` decimal(10,2) DEFAULT NULL,
  `fecha_vencimiento` date DEFAULT NULL,
  PRIMARY KEY (`id_detalle`),
  KEY `id_compra` (`id_compra`),
  KEY `id_producto` (`id_producto`),
  CONSTRAINT `detalle_compra_ibfk_1` FOREIGN KEY (`id_compra`) REFERENCES `compras` (`id_compra`),
  CONSTRAINT `detalle_compra_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_compra`
--

LOCK TABLES `detalle_compra` WRITE;
/*!40000 ALTER TABLE `detalle_compra` DISABLE KEYS */;
INSERT INTO `detalle_compra` VALUES (1,6,1,5,4.50,NULL),(2,6,10,10,7.00,NULL),(3,7,8,5,3.00,NULL),(4,7,4,5,4.00,NULL),(5,8,13,5,2.00,NULL),(6,9,10,4,7.00,NULL),(7,9,3,5,2.50,NULL),(8,14,4,5,4.00,'2026-04-16'),(9,15,14,25,2.50,'2026-04-30'),(10,16,14,50,2.50,'2026-04-16'),(11,17,11,5,6.00,'2027-07-27'),(12,18,15,10,3.50,'2025-06-28'),(13,19,15,20,3.50,'2025-07-30');
/*!40000 ALTER TABLE `detalle_compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_venta`
--

DROP TABLE IF EXISTS `detalle_venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_venta` (
  `id_detalle` bigint NOT NULL AUTO_INCREMENT,
  `id_venta` bigint DEFAULT NULL,
  `id_producto` bigint DEFAULT NULL,
  `cantidad` int DEFAULT NULL,
  `precio_unitario` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id_detalle`),
  KEY `id_venta` (`id_venta`),
  KEY `id_producto` (`id_producto`),
  CONSTRAINT `detalle_venta_ibfk_1` FOREIGN KEY (`id_venta`) REFERENCES `ventas` (`id_venta`),
  CONSTRAINT `detalle_venta_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_venta`
--

LOCK TABLES `detalle_venta` WRITE;
/*!40000 ALTER TABLE `detalle_venta` DISABLE KEYS */;
INSERT INTO `detalle_venta` VALUES (2,12,1,1,4.50),(3,14,1,1,4.50),(4,15,2,2,6.00),(5,16,3,1,2.50),(6,16,2,5,6.00),(7,16,4,5,4.00),(8,17,1,2,4.50),(9,17,2,2,6.00),(10,18,4,1,4.00),(11,18,2,2,6.00),(12,19,3,1,2.50),(13,21,1,1,4.50),(14,21,2,1,6.00),(15,21,3,1,2.50),(16,21,4,1,4.00),(17,22,1,1,4.50),(18,23,7,1,2.50),(19,24,8,1,3.00),(20,24,9,1,2.50),(21,24,10,1,7.00),(22,25,10,4,7.00),(23,26,9,1,2.50),(24,27,8,2,3.00),(25,27,4,3,4.00),(26,28,2,2,6.00),(27,28,7,5,2.50),(28,29,3,2,2.50),(29,29,7,4,2.50),(30,30,10,1,7.00),(31,30,9,1,2.50),(32,31,10,8,7.00),(33,32,8,10,3.00),(34,33,9,5,2.50),(35,34,11,1,6.00),(36,35,11,3,6.00),(37,35,2,6,6.00),(38,36,14,5,2.50),(39,37,9,3,2.50),(40,37,6,6,25.00),(41,38,14,5,2.50),(42,38,10,5,7.00),(43,39,1,19,4.50),(44,40,1,1,4.50),(45,41,12,2,3.00);
/*!40000 ALTER TABLE `detalle_venta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleados`
--

DROP TABLE IF EXISTS `empleados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleados` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  `telefono` varchar(9) DEFAULT NULL,
  `direccion` varchar(150) DEFAULT NULL,
  `dni` varchar(8) DEFAULT NULL,
  `id_rol` tinyint unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_rol` (`id_rol`),
  CONSTRAINT `empleados_ibfk_1` FOREIGN KEY (`id_rol`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleados`
--

LOCK TABLES `empleados` WRITE;
/*!40000 ALTER TABLE `empleados` DISABLE KEYS */;
INSERT INTO `empleados` VALUES (1,'Juan','Pérez','12345679','Calle Ficticia 123','12345678',1),(2,'María','Gómez','98765321','Avenida Real 456','87654321',2),(3,'prueba','sgsgsd','12345678','fdfhdfh','12345678',2),(4,'carmen','rosa vilchez','98365225','av circunvalacion','78879865',2),(7,'fdsffsdf','sdfsdf','4444','fdfsdf','54545',1),(8,'paolo','hurtado','99966658','av progreso mzA lt6','88997744',2),(9,'elmer','chiroque','96965874','los portales mz a lt 9','98765421',2),(10,'yhian','chiroque','98765412','jr chulucanas','48582277',1),(11,'fiorella','chiroque','98745632','av caceres mz a lote z','63365214',2),(12,'eje','prueba','123456789','prueba','46464644',2);
/*!40000 ALTER TABLE `empleados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `codigo` varchar(50) DEFAULT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `id_categoria` int NOT NULL,
  `id_unidad_medida` tinyint unsigned NOT NULL,
  `precioCompra` decimal(10,2) DEFAULT NULL,
  `precioVenta` decimal(10,2) DEFAULT NULL,
  `stock` int DEFAULT '0',
  `ubicacion` varchar(50) DEFAULT NULL,
  `estado` varchar(10) DEFAULT 'ACTIVO',
  PRIMARY KEY (`id`),
  KEY `id_categoria` (`id_categoria`),
  KEY `id_unidad_medida` (`id_unidad_medida`),
  CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`id_categoria`) REFERENCES `categorias` (`id`),
  CONSTRAINT `productos_ibfk_2` FOREIGN KEY (`id_unidad_medida`) REFERENCES `unidad_medida` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,'P001','Leche Gloria','Leche evaporada en lata',12,1,3.00,4.50,0,'Estante A1','Inactivo'),(2,'P002','Coca-Cola 1.5L','Bebida gaseosa',1,1,4.00,6.00,30,'Refrigeradora 2','Activo'),(3,'P004','ajinomen','carne',7,1,2.00,2.50,20,NULL,'Activo'),(4,'P007','halss','azul',9,3,15.00,4.00,50,'B-2','Activo'),(6,'P008','haggies talla g','',5,3,20.00,25.00,4,'A-II','Activo'),(7,'P009','sublime','blanco ',3,1,2.00,2.50,50,'B-3','Activo'),(8,'P010','oreo','chica de 6 galletas',2,1,2.50,3.00,20,'C-1','Activo'),(9,'P011','soda','chica ',2,1,2.00,2.50,3,'C-1','Activo'),(10,'P012','soda vainilla','grande de 24 unidades',2,1,6.00,7.00,10,'C-1','Activo'),(11,'PO13','marina','atun chica',15,1,5.50,6.00,5,'C-1','Activo'),(12,'P014','mayonesa','chica',7,1,2.50,3.00,30,'C-2','Activo'),(13,'P015','noble','chica',16,1,1.50,2.00,20,'C-2','Activo'),(14,'P016','suave','chica ',16,1,2.00,2.50,40,'C-2','Inactivo'),(15,'P017','keke','sabor chocolate',8,1,3.00,3.50,30,'C-3','Activo');
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proveedor` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ruc` varchar(20) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `direccion` varchar(150) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
INSERT INTO `proveedor` VALUES (1,'111111111','alvarez bohl','jr piura','987444222'),(2,'22222222222','r&b','av peru','789654123');
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` tinyint unsigned NOT NULL AUTO_INCREMENT,
  `nombre_rol` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'administrador'),(2,'vendedor');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unidad_medida`
--

DROP TABLE IF EXISTS `unidad_medida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unidad_medida` (
  `id` tinyint unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `abreviacion` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unidad_medida`
--

LOCK TABLES `unidad_medida` WRITE;
/*!40000 ALTER TABLE `unidad_medida` DISABLE KEYS */;
INSERT INTO `unidad_medida` VALUES (1,'unidad','und'),(2,'kilo','kl'),(3,'paquete','paq'),(4,'docena','doc'),(5,'libra','lib'),(7,'medio kilo','mk'),(8,'papel higienico','ph');
/*!40000 ALTER TABLE `unidad_medida` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` tinyint unsigned NOT NULL AUTO_INCREMENT,
  `id_empleado` bigint NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `contraseña` varchar(60) NOT NULL,
  `estado` varchar(10) DEFAULT 'ACTIVO',
  PRIMARY KEY (`id`),
  UNIQUE KEY `usuario` (`usuario`),
  KEY `id_empleado` (`id_empleado`),
  CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`id_empleado`) REFERENCES `empleados` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,1,'juanpe','admin123','INACTIVO'),(2,2,'pedro','vendedor123','INACTIVO'),(3,8,'paolohut','1234','INACTIVO'),(4,4,'carmen','1234','INACTIVO'),(5,3,'prueba','1234','ACTIVO'),(12,10,'yhica','1234','ACTIVO'),(13,11,'fio','1234','ACTIVO'),(14,11,'fiore','1234','ACTIVO'),(15,9,'el','1234','ACTIVO'),(16,12,'eje','1234','ACTIVO');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ventas`
--

DROP TABLE IF EXISTS `ventas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ventas` (
  `id_venta` bigint NOT NULL AUTO_INCREMENT,
  `fecha` datetime DEFAULT CURRENT_TIMESTAMP,
  `id_cliente` bigint DEFAULT NULL,
  `id_usuario` tinyint unsigned NOT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id_venta`),
  KEY `id_cliente` (`id_cliente`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `ventas_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`),
  CONSTRAINT `ventas_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventas`
--

LOCK TABLES `ventas` WRITE;
/*!40000 ALTER TABLE `ventas` DISABLE KEYS */;
INSERT INTO `ventas` VALUES (2,'2025-04-17 14:29:49',1,12,22.50),(3,'2025-04-17 14:33:56',NULL,12,30.50),(4,'2025-04-17 14:34:54',NULL,12,75.00),(5,'2025-04-17 14:54:45',NULL,12,50.00),(6,'2025-04-17 15:09:18',NULL,12,125.00),(7,'2025-04-17 15:12:58',NULL,12,75.00),(8,'2025-04-17 15:16:39',NULL,12,50.00),(9,'2025-04-17 15:27:43',NULL,12,75.00),(10,'2025-04-17 15:29:54',NULL,12,75.00),(11,'2025-04-17 23:10:01',NULL,12,4.50),(12,'2025-04-17 23:11:12',NULL,12,4.50),(13,'2025-04-17 23:12:45',NULL,12,4.50),(14,'2025-04-17 23:16:16',NULL,12,4.50),(15,'2025-04-17 23:18:22',NULL,12,12.00),(16,'2025-04-17 23:25:41',NULL,12,52.50),(17,'2025-04-18 02:18:36',NULL,13,21.00),(18,'2025-04-22 01:11:19',NULL,12,16.00),(19,'2025-04-22 01:39:40',NULL,13,2.50),(20,'2025-04-22 01:43:16',NULL,13,21.00),(21,'2025-04-22 01:51:32',NULL,15,17.00),(22,'2025-04-22 02:00:41',NULL,15,4.50),(23,'2025-04-23 00:51:21',NULL,13,2.50),(24,'2025-04-23 00:58:20',NULL,15,12.50),(25,'2025-04-23 14:18:32',NULL,13,28.00),(26,'2025-04-23 14:23:24',9,15,2.50),(27,'2025-04-23 14:28:48',2,15,18.00),(28,'2025-04-23 15:13:53',6,13,24.50),(29,'2025-04-24 00:32:57',3,15,15.00),(30,'2025-04-25 10:16:03',7,12,9.50),(31,'2025-04-26 01:48:27',NULL,13,56.00),(32,'2025-04-26 01:52:02',9,15,30.00),(33,'2025-04-26 03:16:42',NULL,12,12.50),(34,'2025-04-26 10:14:16',2,16,6.00),(35,'2025-04-26 10:15:07',NULL,16,54.00),(36,'2025-04-30 03:05:18',NULL,12,12.50),(37,'2025-04-30 03:39:47',NULL,15,157.50),(38,'2025-04-30 20:09:16',NULL,12,47.50),(39,'2025-04-30 20:19:39',1,13,85.50),(40,'2025-04-30 20:21:40',NULL,13,4.50),(41,'2025-07-29 10:36:12',6,13,6.00);
/*!40000 ALTER TABLE `ventas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-03-15 19:50:12
