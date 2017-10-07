-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 04, 2015 at 10:45 AM
-- Server version: 5.6.12-log
-- PHP Version: 5.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `ibeto`
--
CREATE DATABASE IF NOT EXISTS `ibeto` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `ibeto`;

-- --------------------------------------------------------

--
-- Table structure for table `ibeto`
--

CREATE TABLE IF NOT EXISTS `ibeto` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `gender` varchar(1) NOT NULL,
  `age` int(3) NOT NULL,
  `image_path` varchar(100) NOT NULL,
  `mdate` varchar(100) NOT NULL,
  `guardian` varchar(100) NOT NULL,
  `maddress` varchar(100) NOT NULL,
  `mstate` varchar(100) NOT NULL,
  `mcity` varchar(100) NOT NULL,
  `htype` varchar(100) NOT NULL,
  `hcolor` varchar(100) NOT NULL,
  `complexion` varchar(100) NOT NULL,
  `body` varchar(100) NOT NULL,
  `mark` varchar(100) NOT NULL,
  `rname` varchar(100) NOT NULL,
  `relationship` varchar(100) NOT NULL,
  `contact` varchar(100) NOT NULL,
  `raddress` varchar(100) NOT NULL,
  `rstate` varchar(100) NOT NULL,
  `rcity` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

--
-- Dumping data for table `ibeto`
--

INSERT INTO `ibeto` (`id`, `name`, `gender`, `age`, `image_path`, `mdate`, `guardian`, `maddress`, `mstate`, `mcity`, `htype`, `hcolor`, `complexion`, `body`, `mark`, `rname`, `relationship`, `contact`, `raddress`, `rstate`, `rcity`) VALUES
(6, 'Adil Khan', 'M', 20, '', '11/7/2016', 'Jorawar Khan', 'A-110 Patel Nagar', 'Delhi', 'New Delhi', 'Curly', 'Black', 'Browninsh', 'Slim','Mole on left cheek','Jorawar Khan', 'Father' ,'8447251726', 'A-110 Patel Nagar', 'Delhi', 'New Delhi'),
(7, 'Nikhil', 'M', 25, '', '12/12/2004', 'avcb', 'Uttam', 'Delhi', 'New Delhi', 'Long', 'Gray', 'Fair', 'Fat', 'sfsf', 'fg', 'fdfb', '214324325', 'fvdbffgn', '', 'btntn'),
(8, 'nikhil', '', 0, '', '', '', '', '', '', 'Choose Hair Type', 'Choose Hair Color', 'Choose Complexion', 'Choose Body Build', '', '', '', '', '', '', ''),
(9, 'name', '', 0, '', '', '', '', '', '', 'Choose Hair Type', 'Choose Hair Color', 'Choose Complexion', 'Choose Body Build', '', '', '', '', '', '', ''),
(10, 'name1', '', 0, '', '', '', '', '', '', 'Choose Hair Type', 'Choose Hair Color', 'Choose Complexion', 'Choose Body Build', '', '', '', '', '', '', ''),
(11, 'name2', 'M', 23, '', '232dfdg', 'sfer', 'sfgdelh', 'Delhi', 'new', 'Partly Combed', 'Gray', 'Normal', 'Hench Back', '', 'efergtr', 'vet', '4', 'vfbttrby', '', 'new'),
(12, 'name3', '', 0, '', '', '', '', '', '', 'Choose Hair Type', 'Choose Hair Color', 'Choose Complexion', 'Choose Body Build', '', '', '', '', '', '', ''),
(13, 'drftfgvg', '', 0, '', '', '', '', '', '', 'Choose Hair Type', 'Choose Hair Color', 'Choose Complexion', 'Choose Body Build', '', '', '', '', '', '', ''),
(14, 'dwhbjbjdbjf', '', 0, '', '', '', '', '', '', 'Choose Hair Type', 'Choose Hair Color', 'Choose Complexion', 'Choose Body Build', '', '', '', '', '', '', ''),
(15, 'nik', '', 0, '', '', '', '', '', '', 'Choose Hair Type', 'Choose Hair Color', 'Choose Complexion', 'Choose Body Build', '', '', '', '', '', '', ''),
(16, 'shdbf', '', 0, '', '', '', '', '', '', 'Choose Hair Type', 'Choose Hair Color', 'Choose Complexion', 'Choose Body Build', '', '', '', '', '', '', '');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
