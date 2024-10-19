-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 19-10-2024 a las 20:43:23
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `biblioteca`
--
CREATE DATABASE IF NOT EXISTS `biblioteca` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `biblioteca`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `book`
--

CREATE TABLE `book` (
  `ID` bigint(20) NOT NULL,
  `AUTHOR` varchar(255) DEFAULT NULL,
  `GENDER` varchar(255) DEFAULT NULL,
  `ISBN` varchar(255) DEFAULT NULL,
  `STATE` tinyint(1) DEFAULT 0,
  `TITLE` varchar(255) DEFAULT NULL,
  `YEARPUBLICATION` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `book`
--

INSERT INTO `book` (`ID`, `AUTHOR`, `GENDER`, `ISBN`, `STATE`, `TITLE`, `YEARPUBLICATION`) VALUES
(101, 'Gabriel Marquez', 'Fantasia', '1231', 0, '100 anios de soledad', '2024-10-03'),
(751, 'Pruebas', 'Tester', '1231', 0, 'Pruebas Unitarias', '2024-10-11'),
(1352, 'Dan Brown', 'Intriga', '1231', 1, 'El Codigo da Vinci. Autor', '2001-02-19');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `loan`
--

CREATE TABLE `loan` (
  `ID` bigint(20) NOT NULL,
  `DATELOAN` date DEFAULT NULL,
  `DEVOLUTIONDATE` date DEFAULT NULL,
  `STATE` varchar(255) DEFAULT NULL,
  `BOOK_ID` bigint(20) DEFAULT NULL,
  `USER_ID` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `loan`
--

INSERT INTO `loan` (`ID`, `DATELOAN`, `DEVOLUTIONDATE`, `STATE`, `BOOK_ID`, `USER_ID`) VALUES
(102, '2024-10-18', '2024-10-18', 'Devuelto', 101, 51),
(801, '2024-10-04', '2024-10-19', 'Devuelto', 1352, 1351),
(1401, '2024-10-19', '2024-10-19', 'Devuelto', 1352, 1351);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sequence`
--

CREATE TABLE `sequence` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `sequence`
--

INSERT INTO `sequence` (`SEQ_NAME`, `SEQ_COUNT`) VALUES
('SEQ_GEN', 1450);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `ID` bigint(20) NOT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `LASTNAME` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `ROL` varchar(255) DEFAULT NULL,
  `STATE` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`ID`, `EMAIL`, `LASTNAME`, `NAME`, `PASSWORD`, `ROL`, `STATE`) VALUES
(51, 'camilo.va11999@gmail.com', 'vargas', 'Camilo', 'Ex6gLS5WdmxEJB0bSF9CXg==', 'Administrador', 1),
(1301, 'ccvargas@gmail.com', 'vargas', 'Camilo', 'FXrdsGnQBLHIC6ecO2J+ZQ==', 'Administrador', 1),
(1351, 'sgct.21@gmail.com', 'Cardona', 'Gabriela', 'Ex6gLS5WdmxEJB0bSF9CXg==', 'Administrador', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `loan`
--
ALTER TABLE `loan`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_LOAN_BOOK_ID` (`BOOK_ID`),
  ADD KEY `FK_LOAN_USER_ID` (`USER_ID`);

--
-- Indices de la tabla `sequence`
--
ALTER TABLE `sequence`
  ADD PRIMARY KEY (`SEQ_NAME`);

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `EMAIL` (`EMAIL`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `loan`
--
ALTER TABLE `loan`
  ADD CONSTRAINT `FK_LOAN_BOOK_ID` FOREIGN KEY (`BOOK_ID`) REFERENCES `book` (`ID`),
  ADD CONSTRAINT `FK_LOAN_USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
