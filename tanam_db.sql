-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 07, 2019 at 08:24 PM
-- Server version: 5.6.16
-- PHP Version: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `tanam_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `dat_donasi`
--

CREATE TABLE IF NOT EXISTS `dat_donasi` (
  `id_donasi` int(20) NOT NULL,
  `id_akun` int(20) NOT NULL,
  `jml_donasi` varchar(20) NOT NULL,
  `jml_pohon` varchar(30) NOT NULL,
  `metode` varchar(10) NOT NULL,
  PRIMARY KEY (`id_donasi`),
  KEY `dat_pesan` (`id_akun`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dat_donasi`
--

INSERT INTO `dat_donasi` (`id_donasi`, `id_akun`, `jml_donasi`, `jml_pohon`, `metode`) VALUES
(386933, 9, '50000', '5', 'OVO');

-- --------------------------------------------------------

--
-- Table structure for table `dat_usr`
--

CREATE TABLE IF NOT EXISTS `dat_usr` (
  `id_akun` int(20) NOT NULL AUTO_INCREMENT,
  `nama` varchar(70) NOT NULL,
  `email` varchar(150) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`id_akun`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `dat_usr`
--

INSERT INTO `dat_usr` (`id_akun`, `nama`, `email`, `username`, `password`) VALUES
(8, 'Jeje Deavin', 'jedev087@gmail.com', 'deavin087', 'indomaret'),
(9, 'Alexander Prima', 'Primakuy@kuylah.com', 'prima', 'kt');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `dat_donasi`
--
ALTER TABLE `dat_donasi`
  ADD CONSTRAINT `dat_pesan` FOREIGN KEY (`id_akun`) REFERENCES `dat_usr` (`id_akun`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
