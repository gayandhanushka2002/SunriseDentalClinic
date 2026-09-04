-- phpMyAdmin SQL Dump
-- version 5.2.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Sep 04, 2026 at 05:25 PM
-- Server version: 8.4.7
-- PHP Version: 8.3.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sunrise_dental_clinic`
--

DELIMITER $$
--
-- Procedures
--
DROP PROCEDURE IF EXISTS `CalculateBillTotal`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `CalculateBillTotal` (IN `apptNo` VARCHAR(10), OUT `total` DECIMAL(10,2))   BEGIN
  DECLARE fee DECIMAL(10,2);
  SELECT d.consultation_fee INTO fee
  FROM appointment a JOIN dentist d ON a.dentist_id = d.dentist_id
  WHERE a.appointment_no = apptNo;
  SELECT fee + IFNULL(SUM(t.treatment_cost), 0) INTO total
  FROM treatment t WHERE t.appointment_no = apptNo;
END$$

--
-- Functions
--
DROP FUNCTION IF EXISTS `GetAppointmentCount`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `GetAppointmentCount` (`pId` VARCHAR(10)) RETURNS INT DETERMINISTIC BEGIN
  DECLARE cnt INT;
  SELECT COUNT(*) INTO cnt FROM appointment WHERE patient_id = pId;
  RETURN cnt;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
CREATE TABLE IF NOT EXISTS `appointment` (
  `appointment_no` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `patient_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `dentist_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `appt_date` date DEFAULT NULL,
  `appt_time` time DEFAULT NULL,
  PRIMARY KEY (`appointment_no`),
  KEY `patient_id` (`patient_id`),
  KEY `dentist_id` (`dentist_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `appointment`
--

INSERT INTO `appointment` (`appointment_no`, `patient_id`, `dentist_id`, `appt_date`, `appt_time`) VALUES
('a1', 'p1', 'D002', '2026-08-19', '10:11:00'),
('a3', 'p3', 'D001', '2026-08-19', '10:37:00'),
('a15', 'p15', 'D002', '2026-09-15', '22:45:00'),
('a4', 'p4', 'D002', '2026-09-16', '16:45:00');

--
-- Triggers `appointment`
--
DROP TRIGGER IF EXISTS `trg_prevent_double_booking`;
DELIMITER $$
CREATE TRIGGER `trg_prevent_double_booking` BEFORE INSERT ON `appointment` FOR EACH ROW BEGIN
  IF EXISTS (SELECT 1 FROM appointment
             WHERE dentist_id = NEW.dentist_id
               AND appt_date = NEW.appt_date
               AND appt_time = NEW.appt_time) THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Dentist already booked at this time';
  END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
CREATE TABLE IF NOT EXISTS `bill` (
  `bill_no` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `appointment_no` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `total_cost` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`bill_no`),
  KEY `appointment_no` (`appointment_no`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `bill`
--

INSERT INTO `bill` (`bill_no`, `appointment_no`, `total_cost`) VALUES
('B-9542', 'a1', 3000.00),
('B1140', 'a3', 3000.00),
('B545', 'a15', 6600.00),
('B5659', 'a4', 5600.00);

-- --------------------------------------------------------

--
-- Table structure for table `dentist`
--

DROP TABLE IF EXISTS `dentist`;
CREATE TABLE IF NOT EXISTS `dentist` (
  `dentist_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `consultation_fee` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`dentist_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `dentist`
--

INSERT INTO `dentist` (`dentist_id`, `name`, `consultation_fee`) VALUES
('D001', 'Dr. Smith', 2500.00),
('D002', 'Dr. Perera', 3000.00),
('D003', 'Dr.saman', 4300.00),
('D004', 'Dr Ruwan', 2500.00);

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
CREATE TABLE IF NOT EXISTS `patient` (
  `patient_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `contact_number` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`patient_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`patient_id`, `name`, `address`, `contact_number`, `email`) VALUES
('p1', 'kamal', 'badulla', '0753575521', NULL),
('p3', 'susntha', 'nn', '0789631666', NULL),
('p15', 'dil', 'bd', '0740569333', 'ddilshan2002918@gmail.com'),
('p4', 'dilshan s', 'colombo', '0740569887', 'dilshan@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
CREATE TABLE IF NOT EXISTS `staff` (
  `staff_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`staff_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`staff_id`, `username`, `password`) VALUES
(1, 'staff1', 's123');

-- --------------------------------------------------------

--
-- Table structure for table `treatment`
--

DROP TABLE IF EXISTS `treatment`;
CREATE TABLE IF NOT EXISTS `treatment` (
  `treatment_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `appointment_no` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `treatment_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `treatment_cost` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`treatment_id`),
  KEY `appointment_no` (`appointment_no`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `treatment`
--

INSERT INTO `treatment` (`treatment_id`, `appointment_no`, `treatment_type`, `treatment_cost`) VALUES
('T2309', 'a15', 'Root Canal Treatment', 3500.00),
('T8881', 'a4', 'Teeth Cleaning', 2500.00);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
