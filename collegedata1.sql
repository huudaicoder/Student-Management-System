-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 18, 2020 at 08:47 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.3

SET SQL_MODE
= "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT
= 0;
START TRANSACTION;
SET time_zone
= "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `collagedata`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin`
(
  `collagename` VARCHAR
(50) DEFAULT NULL,
  `address` VARCHAR
(100) DEFAULT NULL,
  `emailid` VARCHAR
(50) DEFAULT NULL,
  `contactnumber` VARCHAR
(40) DEFAULT NULL,
  `website` VARCHAR
(30) DEFAULT NULL,
  `lastlogin` VARCHAR
(40) DEFAULT NULL,
  `password` VARCHAR
(30) DEFAULT NULL,
  `facebook` VARCHAR
(100) DEFAULT NULL,
  `instagram` VARCHAR
(100) DEFAULT NULL,
  `twitter` VARCHAR
(100) DEFAULT NULL,
  `linkedin` VARCHAR
(100) DEFAULT NULL,
  `logo` longblob DEFAULT NULL,
  `activestatus` tinyint
(4) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

-- --------------------------------------------------------

--
-- Table structure for table `Room`
--
create table `Room`
(
    `roomID` Varchar
(10),
    `building` Varchar
(20),
    `roomNumber` int,
    `capacity` int,
    PRIMARY KEY
(`roomID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------
--
-- Table structure for table `Timeslot`
--
create table `Timeslot`
(
  `timeslotID` Varchar
(10) PRIMARY KEY,
  `day` Varchar
(10),
  `start_hour` INTEGER,
  `start_minute` INTEGER,
  `end_hour` INTEGER,
  `end_minute` INTEGER
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
--
-- Table structure for table `Department`
--

CREATE TABLE `Department`
(
  `dept_ID` VARCHAR
(10) NOT NULL,
  `dept_Name` VARCHAR
(30) NOT NULL,
  `building` VARCHAR
(5) NOT NULL,
  `phoneNumber` VARCHAR
(12) NOT NULL,
PRIMARY KEY
(`dept_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `Lecturer`
--

CREATE TABLE `Lecturer`
(
  `lecturerID` VARCHAR
(10) DEFAULT NULL,
  `lecturerName` VARCHAR
(30) DEFAULT NULL,
  `dept_ID` VARCHAR
(10) DEFAULT NULL,
  `salary` FLOAT DEFAULT NULL,
  PRIMARY KEY
(`lecturerID`),
  FOREIGN KEY
(`dept_ID`) REFERENCES `Department`
(`dept_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------
--
-- Table structure for table `Student`
--

CREATE TABLE `Student`
(
  `studentID` VARCHAR
(10) NOT NULL,
  `dept_ID` VARCHAR
(10) NOT NULL,
  `studentName` VARCHAR
(30) NOT NULL,
  `DOB` VARCHAR
(12) NOT NULL,
  `gender` VARCHAR
(10) NOT NULL,
  `CPA` FLOAT NOT NULL,
  `total_cred` INTEGER NOT NULL,
  PRIMARY KEY
(`studentID`),
  FOREIGN KEY
(`dept_ID`) REFERENCES `Department`
(`dept_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------
--
-- Table structure for table `Course`
--

create table `Course`
(
    `courseID` Varchar
(10)  PRIMARY KEY,
    `dept_ID` Varchar
(10),
    `courseName` Varchar
(30),
    `factor`  FLOAT,
    `credits` int,
    FOREIGN KEY
(`dept_ID`) REFERENCES `Department`
(`dept_ID`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `Class`
--

create table `Class`
(
    `classID` Varchar
(10) PRIMARY KEY,
    `courseID` Varchar
(10),
    `lecturerID` Varchar
(10),
    `roomID` Varchar
(10),
    `timeslotID` Varchar
(10),
    `semester` INTEGER,
    `schoolyear` INTEGER,
    FOREIGN KEY
(`lecturerID`) REFERENCES `Lecturer`
(`lecturerID`),
    FOREIGN KEY
(`courseID`) REFERENCES `Course`
(`courseID`),
    FOREIGN KEY
(`roomID`) REFERENCES  `Room`
(`roomID`),
    FOREIGN KEY
(`timeslotID`) REFERENCES `Timeslot`
(`timeslotID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `Manage Student`
--

create table `ManageStudent`
(
    `studentID` Varchar
(10),
    `classID` Varchar
(10),
    `grade` Varchar
(3),
    `mark` FLOAT,
    PRIMARY KEY
(`studentID`, `classID`),
    FOREIGN KEY
(`studentID`) REFERENCES `Student`
(`studentID`),
    FOREIGN KEY
(`classID`) REFERENCES `Class`
(`classID`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `Condition Course`
--

create table `ConditionCourse`
(
    `conCourseID` Varchar
(10),
    `courseID` Varchar
(10),
    FOREIGN KEY
(`conCourseID`) REFERENCES `Course`
(`courseID`),
    FOREIGN KEY
(`courseID`) REFERENCES `Course`
(`courseID`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
