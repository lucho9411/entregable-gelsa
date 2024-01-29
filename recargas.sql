-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 29-01-2024 a las 13:00:10
-- Versión del servidor: 10.4.25-MariaDB
-- Versión de PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `recargas`
--
CREATE DATABASE IF NOT EXISTS `recargas` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci;
USE `recargas`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `operadores`
--

DROP TABLE IF EXISTS `operadores`;
CREATE TABLE IF NOT EXISTS `operadores` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `imagen` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- RELACIONES PARA LA TABLA `operadores`:
--

--
-- Volcado de datos para la tabla `operadores`
--

INSERT DELAYED IGNORE INTO `operadores` (`id`, `nombre`, `imagen`) VALUES
(1, 'Claro', 'http://localhost:4200/assets/Claro.png'),
(2, 'Tigo', 'http://localhost:4200/assets/Tigo.png'),
(3, 'Wom', 'http://localhost:4200/assets/Wom.png'),
(4, 'Movistar', 'http://localhost:4200/assets/Movistar.png'),
(5, 'Uff', 'http://localhost:4200/assets/Uff.png'),
(6, 'Avantel', 'http://localhost:4200/assets/Avantel.png');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `recargas`
--

DROP TABLE IF EXISTS `recargas`;
CREATE TABLE IF NOT EXISTS `recargas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_hora` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `numero` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `valor` decimal(8,2) NOT NULL,
  `fk_vendedor` bigint(20) NOT NULL,
  `fk_operador` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_operador` (`fk_operador`),
  KEY `fk_vendedor` (`fk_vendedor`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- RELACIONES PARA LA TABLA `recargas`:
--   `fk_operador`
--       `operadores` -> `id`
--   `fk_vendedor`
--       `usuarios` -> `id`
--

--
-- Volcado de datos para la tabla `recargas`
--

INSERT DELAYED IGNORE INTO `recargas` (`id`, `fecha_hora`, `numero`, `valor`, `fk_vendedor`, `fk_operador`) VALUES
(4, '2024-01-29 11:07:15', '3166403530', '15000.00', 1, 1),
(5, '2024-01-29 11:07:47', '3166403530', '20000.00', 1, 5),
(6, '2024-01-29 11:38:39', '3189886352', '18000.00', 1, 2),
(7, '2024-01-29 11:40:12', '3202997856', '50000.00', 1, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

DROP TABLE IF EXISTS `roles`;
CREATE TABLE IF NOT EXISTS `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- RELACIONES PARA LA TABLA `roles`:
--

--
-- Volcado de datos para la tabla `roles`
--

INSERT DELAYED IGNORE INTO `roles` (`id`, `nombre`) VALUES
(1, 'Vendedor');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `clave` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `estado` int(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- RELACIONES PARA LA TABLA `usuarios`:
--

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT DELAYED IGNORE INTO `usuarios` (`id`, `nombre`, `clave`, `estado`) VALUES
(1, 'Vendedor Prueba', '$2a$10$wrFY6HqwCQgbguAEDIA30.85mbZnRYduzOADoLhdhwi37C0LO1aXm', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios_roles`
--

DROP TABLE IF EXISTS `usuarios_roles`;
CREATE TABLE IF NOT EXISTS `usuarios_roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `usuario` bigint(20) NOT NULL,
  `rol` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `rol` (`rol`),
  KEY `usuario` (`usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- RELACIONES PARA LA TABLA `usuarios_roles`:
--   `rol`
--       `roles` -> `id`
--   `usuario`
--       `usuarios` -> `id`
--

--
-- Volcado de datos para la tabla `usuarios_roles`
--

INSERT DELAYED IGNORE INTO `usuarios_roles` (`id`, `usuario`, `rol`) VALUES
(1, 1, 1);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `recargas`
--
ALTER TABLE `recargas`
  ADD CONSTRAINT `recargas_ibfk_1` FOREIGN KEY (`fk_operador`) REFERENCES `operadores` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `recargas_ibfk_2` FOREIGN KEY (`fk_vendedor`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `usuarios_roles`
--
ALTER TABLE `usuarios_roles`
  ADD CONSTRAINT `usuarios_roles_ibfk_1` FOREIGN KEY (`rol`) REFERENCES `roles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `usuarios_roles_ibfk_2` FOREIGN KEY (`usuario`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;


--
-- Metadatos
--
USE `phpmyadmin`;

--
-- Metadatos para la tabla operadores
--

--
-- Metadatos para la tabla recargas
--

--
-- Metadatos para la tabla roles
--

--
-- Metadatos para la tabla usuarios
--

--
-- Metadatos para la tabla usuarios_roles
--

--
-- Metadatos para la base de datos recargas
--
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
