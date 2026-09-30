CREATE DATABASE  IF NOT EXISTS `universidad2` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `universidad2`;
-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: universidad2
-- ------------------------------------------------------
-- Server version	9.3.0

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
-- Table structure for table `asignatura_carrera`
--

DROP TABLE IF EXISTS `asignatura_carrera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asignatura_carrera` (
  `id_asig_carr` bigint NOT NULL,
  `id_asignatura` bigint NOT NULL,
  `id_carrera` bigint NOT NULL,
  PRIMARY KEY (`id_asig_carr`),
  KEY `idx_asig_carr_asignatura` (`id_asignatura`),
  KEY `idx_asig_carr_carrera` (`id_carrera`),
  CONSTRAINT `asignatura_carrera_ibfk_1` FOREIGN KEY (`id_asignatura`) REFERENCES `asignaturas` (`id_asignatura`),
  CONSTRAINT `asignatura_carrera_ibfk_2` FOREIGN KEY (`id_carrera`) REFERENCES `carreras` (`id_carrera`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asignatura_carrera`
--

LOCK TABLES `asignatura_carrera` WRITE;
/*!40000 ALTER TABLE `asignatura_carrera` DISABLE KEYS */;
INSERT INTO `asignatura_carrera` VALUES (1,100,1),(2,101,1),(3,103,1),(4,103,2),(5,102,2),(6,104,1),(7,105,1),(8,108,4),(9,109,7),(10,107,3);
/*!40000 ALTER TABLE `asignatura_carrera` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asignaturas`
--

DROP TABLE IF EXISTS `asignaturas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asignaturas` (
  `id_asignatura` bigint NOT NULL,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`id_asignatura`),
  KEY `idx_asignaturas_nombre` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asignaturas`
--

LOCK TABLES `asignaturas` WRITE;
/*!40000 ALTER TABLE `asignaturas` DISABLE KEYS */;
INSERT INTO `asignaturas` VALUES (104,'Álgebra Lineal'),(100,'Cálculo 1'),(109,'Derecho Constitucional'),(107,'Economía Básica'),(102,'Estadística Aplicada'),(103,'Estadística Inferencial'),(105,'Física 1'),(101,'Programación 2'),(108,'Psicología General'),(106,'Química General');
/*!40000 ALTER TABLE `asignaturas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carreras`
--

DROP TABLE IF EXISTS `carreras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carreras` (
  `id_carrera` bigint NOT NULL,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`id_carrera`),
  KEY `idx_carreras_nombre` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carreras`
--

LOCK TABLES `carreras` WRITE;
/*!40000 ALTER TABLE `carreras` DISABLE KEYS */;
INSERT INTO `carreras` VALUES (3,'Administración de Empresas'),(6,'Arquitectura'),(10,'Biología'),(8,'Contaduría Pública'),(7,'Derecho'),(9,'Ingeniería Civil'),(1,'Ingeniería de Sistemas'),(2,'Ingeniería Industrial'),(5,'Medicina'),(4,'Psicología');
/*!40000 ALTER TABLE `carreras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curso_estudiante`
--

DROP TABLE IF EXISTS `curso_estudiante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `curso_estudiante` (
  `id_curso` bigint NOT NULL,
  `id_estudiante` bigint NOT NULL,
  PRIMARY KEY (`id_curso`,`id_estudiante`),
  KEY `idx_curso_estudiante_curso` (`id_curso`),
  KEY `idx_curso_estudiante_estudiante` (`id_estudiante`),
  CONSTRAINT `curso_estudiante_ibfk_1` FOREIGN KEY (`id_curso`) REFERENCES `cursos` (`id_curso`),
  CONSTRAINT `curso_estudiante_ibfk_2` FOREIGN KEY (`id_estudiante`) REFERENCES `estudiantes` (`id_estudiante`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curso_estudiante`
--

LOCK TABLES `curso_estudiante` WRITE;
/*!40000 ALTER TABLE `curso_estudiante` DISABLE KEYS */;
INSERT INTO `curso_estudiante` VALUES (1,88238899),(1,88239001),(2,88238900),(3,88239002),(4,88239003),(5,88239004),(6,88239005),(7,88239006),(8,88239007),(9,88239008);
/*!40000 ALTER TABLE `curso_estudiante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cursos`
--

DROP TABLE IF EXISTS `cursos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cursos` (
  `id_curso` bigint NOT NULL,
  `id_docente` bigint NOT NULL,
  `id_asig_carr` bigint NOT NULL,
  `semestre_academico` varchar(10) NOT NULL,
  `grupo` char(1) NOT NULL,
  PRIMARY KEY (`id_curso`),
  KEY `idx_cursos_docente` (`id_docente`),
  KEY `idx_cursos_asig_carr` (`id_asig_carr`),
  CONSTRAINT `cursos_ibfk_1` FOREIGN KEY (`id_docente`) REFERENCES `usuarios` (`id_usuario`),
  CONSTRAINT `cursos_ibfk_2` FOREIGN KEY (`id_asig_carr`) REFERENCES `asignatura_carrera` (`id_asig_carr`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cursos`
--

LOCK TABLES `cursos` WRITE;
/*!40000 ALTER TABLE `cursos` DISABLE KEYS */;
INSERT INTO `cursos` VALUES (1,88238995,1,'2025-1','A'),(2,88238995,3,'2025-1','B'),(3,88238998,5,'2025-1','C'),(4,23205067,2,'2025-1','D'),(5,23205067,6,'2025-1','E'),(6,88238995,7,'2025-1','F'),(7,88238998,4,'2025-1','G'),(8,23205067,1,'2025-2','H'),(9,88238995,2,'2025-2','I'),(10,88239001,3,'2025-2','J');
/*!40000 ALTER TABLE `cursos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `docente_asignatura_carrera`
--

DROP TABLE IF EXISTS `docente_asignatura_carrera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `docente_asignatura_carrera` (
  `id_doc_asig_carr` bigint NOT NULL,
  `id_usuario` bigint NOT NULL,
  `id_asig_carr` bigint NOT NULL,
  PRIMARY KEY (`id_doc_asig_carr`),
  KEY `idx_doc_asig_carr_usuario` (`id_usuario`),
  KEY `idx_doc_asig_carr_asig_carr` (`id_asig_carr`),
  CONSTRAINT `docente_asignatura_carrera_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`),
  CONSTRAINT `docente_asignatura_carrera_ibfk_2` FOREIGN KEY (`id_asig_carr`) REFERENCES `asignatura_carrera` (`id_asig_carr`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `docente_asignatura_carrera`
--

LOCK TABLES `docente_asignatura_carrera` WRITE;
/*!40000 ALTER TABLE `docente_asignatura_carrera` DISABLE KEYS */;
INSERT INTO `docente_asignatura_carrera` VALUES (1,88238995,1),(2,88238995,3),(3,88238998,5),(4,23205067,2),(5,23205067,6),(6,88238995,7),(7,88238998,4),(8,23205067,1),(9,88238995,2),(10,88239001,3);
/*!40000 ALTER TABLE `docente_asignatura_carrera` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estudiantes`
--

DROP TABLE IF EXISTS `estudiantes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estudiantes` (
  `id_estudiante` bigint NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `correo` varchar(100) NOT NULL,
  PRIMARY KEY (`id_estudiante`),
  KEY `idx_estudiantes_nombre` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estudiantes`
--

LOCK TABLES `estudiantes` WRITE;
/*!40000 ALTER TABLE `estudiantes` DISABLE KEYS */;
INSERT INTO `estudiantes` VALUES (88238899,'Ana López','ana@example.com'),(88238900,'Carlos Ruiz','carlos@example.com'),(88239001,'María Gómez','maria@example.com'),(88239002,'Juan Pérez','juan@example.com'),(88239003,'Lucía Martínez','lucia@example.com'),(88239004,'Pedro Sánchez','pedro@example.com'),(88239005,'Sofía Ramírez','sofia@example.com'),(88239006,'Diego Torres','diego@example.com'),(88239007,'Valeria Castro','valeria@example.com'),(88239008,'Andrés Morales','andres@example.com');
/*!40000 ALTER TABLE `estudiantes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reportes`
--

DROP TABLE IF EXISTS `reportes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reportes` (
  `id_reporte` bigint NOT NULL AUTO_INCREMENT,
  `id_usuario` bigint NOT NULL,
  `id_carrera` bigint NOT NULL,
  `id_asignatura` bigint NOT NULL,
  `id_curso` bigint NOT NULL,
  `id_estudiante` bigint NOT NULL,
  `fecha` date NOT NULL,
  `motivo` varchar(255) NOT NULL,
  `comentario` text,
  `calificacion` decimal(3,2) DEFAULT NULL,
  PRIMARY KEY (`id_reporte`),
  KEY `idx_reportes_usuario` (`id_usuario`),
  KEY `idx_reportes_carrera` (`id_carrera`),
  KEY `idx_reportes_asignatura` (`id_asignatura`),
  KEY `idx_reportes_curso` (`id_curso`),
  KEY `idx_reportes_estudiante` (`id_estudiante`),
  KEY `idx_reportes_fecha` (`fecha`),
  CONSTRAINT `reportes_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`),
  CONSTRAINT `reportes_ibfk_2` FOREIGN KEY (`id_carrera`) REFERENCES `carreras` (`id_carrera`),
  CONSTRAINT `reportes_ibfk_3` FOREIGN KEY (`id_asignatura`) REFERENCES `asignaturas` (`id_asignatura`),
  CONSTRAINT `reportes_ibfk_4` FOREIGN KEY (`id_curso`) REFERENCES `cursos` (`id_curso`),
  CONSTRAINT `reportes_ibfk_5` FOREIGN KEY (`id_estudiante`) REFERENCES `estudiantes` (`id_estudiante`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reportes`
--

LOCK TABLES `reportes` WRITE;
/*!40000 ALTER TABLE `reportes` DISABLE KEYS */;
INSERT INTO `reportes` VALUES (1,88238995,1,100,1,88238899,'2025-04-26','Bajo rendimiento','Estudiante presenta dificultades académicas en el primer corte.',2.50),(2,88238995,1,101,4,88239001,'2025-04-26','Inasistencia','Faltó a más del 50% de las clases programadas.',1.80),(3,88238998,1,100,1,88238899,'2025-04-28','Bajo rendimiento','Estudiante presenta dificultades académicas en el primer corte.',2.30),(4,88238998,1,101,4,88239002,'2025-04-28','Inasistencia','Faltó a más del 50% de las clases programadas.',1.50),(5,23205067,1,101,4,88239001,'2025-04-28','Bajo rendimiento','Estudiante presenta dificultades académicas en el primer corte.',2.00),(6,23205067,1,100,1,88239001,'2025-04-28','Inasistencia','Faltó a más del 50% de las clases programadas.',1.20),(7,88239001,1,101,4,88238899,'2025-04-28','Bajo rendimiento','Estudiante presenta dificultades académicas en el primer corte.',2.10),(8,88239001,1,101,4,88239003,'2025-04-28','Inasistencia','Faltó a más del 50% de las clases programadas.',2.40),(9,88239003,1,104,5,88239004,'2025-04-28','Bajo rendimiento','Estudiante presenta dificultades académicas en el primer corte.',1.90),(10,88239003,2,103,7,88239006,'2025-04-28','Inasistencia','Faltó a más del 50% de las clases programadas.',2.60);
/*!40000 ALTER TABLE `reportes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id_rol` bigint NOT NULL,
  `nombre_rol` varchar(50) NOT NULL,
  PRIMARY KEY (`id_rol`),
  UNIQUE KEY `nombre_rol` (`nombre_rol`),
  KEY `idx_roles_nombre_rol` (`nombre_rol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (3,'ADMINISTRADOR'),(2,'AUDITOR'),(1,'PROFESOR');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id_usuario` bigint NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `id_rol` bigint NOT NULL,
  PRIMARY KEY (`id_usuario`),
  KEY `idx_usuarios_id_rol` (`id_rol`),
  KEY `idx_usuarios_nombre` (`nombre`),
  CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`id_rol`) REFERENCES `roles` (`id_rol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (23205067,'Sofi Gaviria',1),(88238995,'Eduardo Pérez',1),(88238996,'Laura Sánchez',2),(88238997,'Pedro Gómez',3),(88238998,'Sofía Mendoza',1),(88238999,'Clara Díaz',2),(88239000,'Javier Ortiz',3),(88239001,'Ana Torres',1),(88239002,'Luis Vargas',2),(88239003,'Marta López',1);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-30 13:14:43
