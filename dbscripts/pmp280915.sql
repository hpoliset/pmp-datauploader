-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.6.22-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL version:             7.0.0.4053
-- Date/time:                    2015-09-28 13:51:40
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;

-- Dumping database structure for pmp_dev
DROP DATABASE IF EXISTS `pmp_dev`;
CREATE DATABASE IF NOT EXISTS `pmp_dev` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `pmp_dev`;


-- Dumping structure for table pmp_dev.channel
DROP TABLE IF EXISTS `channel`;
CREATE TABLE IF NOT EXISTS `channel` (
  `Channel_ID` int(11) NOT NULL,
  `Channel_Name` varchar(100) NOT NULL,
  `Channel_Descr` longtext,
  `Channel_SPOC` varchar(150) DEFAULT NULL,
  `Channel_Lead` varchar(150) DEFAULT NULL,
  `Active` tinyint(1) DEFAULT '0' COMMENT '0 Means Active and 1 means Inactive',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL,
  `Created_by` varchar(45) DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Channel_ID`),
  UNIQUE KEY `Channel_Name_UNIQUE` (`Channel_Name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Dumping structure for table pmp_dev.coordinator
DROP TABLE IF EXISTS `coordinator`;
CREATE TABLE IF NOT EXISTS `coordinator` (
  `Coord_ID` int(11) NOT NULL,
  `Coord_Name` varchar(45) DEFAULT NULL,
  `Coord_Email` varchar(75) DEFAULT NULL,
  `Coord_Center_Name` varchar(45) DEFAULT NULL,
  `Coord_Loc_Address_1` varchar(45) DEFAULT NULL,
  `Coord_Loc_Address_2` varchar(45) DEFAULT NULL,
  `Coord_Loc_City` varchar(45) DEFAULT NULL,
  `Coord_Loc_State` varchar(45) DEFAULT NULL,
  `Coord_Loc_Country` varchar(45) DEFAULT NULL,
  `Coord_Loc_Zip` varchar(25) DEFAULT NULL,
  `Coord_Phone` varchar(45) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL,
  `Created_by` varchar(45) DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Coord_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Dumping structure for table pmp_dev.files_upload
DROP TABLE IF EXISTS `files_upload`;
CREATE TABLE IF NOT EXISTS `files_upload` (
  `upload_id` int(11) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(128) DEFAULT NULL,
  `file_data` longblob,
  PRIMARY KEY (`upload_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table pmp_dev.institute
DROP TABLE IF EXISTS `institute`;
CREATE TABLE IF NOT EXISTS `institute` (
  `Institute_ID` int(11) NOT NULL,
  `Institute_Name` varchar(150) NOT NULL,
  `Institute_SPOC` varchar(150) DEFAULT NULL,
  `Institute_Email` varchar(150) DEFAULT NULL,
  `Institute_Website` varchar(150) DEFAULT NULL,
  `Address_Line_1` varchar(150) DEFAULT NULL,
  `Address_line_2` varchar(150) DEFAULT NULL,
  `City` varchar(50) DEFAULT NULL,
  `State` varchar(50) DEFAULT NULL,
  `Zip` varchar(45) DEFAULT NULL,
  `Country` varchar(75) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL,
  `Created_by` varchar(45) DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Institute_ID`),
  UNIQUE KEY `Institute_Name_UNIQUE` (`Institute_Name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Dumping structure for table pmp_dev.maturity
DROP TABLE IF EXISTS `maturity`;
CREATE TABLE IF NOT EXISTS `maturity` (
  `Maturity_ID` int(11) NOT NULL,
  `Maturity_Code` varchar(45) DEFAULT NULL,
  `Maturity_Desc` varchar(250) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL,
  `Created_by` varchar(45) DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Maturity_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Dumping structure for table pmp_dev.memberships
DROP TABLE IF EXISTS `memberships`;
CREATE TABLE IF NOT EXISTS `memberships` (
  `Start_Date` date DEFAULT NULL,
  `End_Date` date DEFAULT NULL,
  `Membership_ID` int(11) NOT NULL,
  `Primary` tinyint(1) DEFAULT '0' COMMENT '0 Means the nonPrimary, 1 Means Primary',
  `Channel_ID` int(11) DEFAULT NULL,
  `Institute_ID` int(11) DEFAULT NULL,
  `Seeker_ID` int(11) DEFAULT NULL,
  `Program_Start_Date` date DEFAULT NULL,
  `Program_End_Date` date DEFAULT NULL,
  `Attribute1` varchar(150) DEFAULT NULL,
  `Attribute2` varchar(150) DEFAULT NULL,
  `Future` varchar(150) DEFAULT NULL,
  `Coord_ID` int(11) DEFAULT NULL,
  `Introduced` tinyint(1) DEFAULT '1' COMMENT '0 means Introduced, 1 means Not Introduced',
  `Introduced_date` date DEFAULT NULL,
  `Introduced_By` varchar(75) DEFAULT NULL,
  `Remarks` varchar(45) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL,
  `Created_by` varchar(45) DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Membership_ID`),
  KEY `Seeker_FKey_idx` (`Seeker_ID`),
  KEY `Channel_Fkey_idx` (`Channel_ID`),
  KEY `Institute_Fkey_idx` (`Institute_ID`),
  KEY `coord_id_FKey_idx` (`Coord_ID`),
  CONSTRAINT `Channel_Fkey` FOREIGN KEY (`Channel_ID`) REFERENCES `channel` (`Channel_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Institute_Fkey` FOREIGN KEY (`Institute_ID`) REFERENCES `institute` (`Institute_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Seeker_FKey` FOREIGN KEY (`Seeker_ID`) REFERENCES `seeker` (`Seeker_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `coord_id_FKey` FOREIGN KEY (`Coord_ID`) REFERENCES `coordinator` (`Coord_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Dumping structure for table pmp_dev.program
DROP TABLE IF EXISTS `program`;
CREATE TABLE IF NOT EXISTS `program` (
  `Program_id` int(11) NOT NULL,
  `Program_channel` varchar(50) DEFAULT NULL,
  `Program_date` date DEFAULT NULL,
  `Coord_Name` varchar(75) DEFAULT NULL,
  `Coord_Email` varchar(45) DEFAULT NULL,
  `Coord_Center_Name` varchar(45) DEFAULT NULL,
  `Coord_Country` varchar(45) DEFAULT NULL,
  `Inst_Name` varchar(45) DEFAULT NULL,
  `Inst_Website` varchar(75) DEFAULT NULL,
  `Program_Start_Date` date DEFAULT NULL,
  `Program_raw_Date` varchar(15) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL,
  `Created_by` varchar(45) DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Program_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table pmp_dev.seeker
DROP TABLE IF EXISTS `seeker`;
CREATE TABLE IF NOT EXISTS `seeker` (
  `First_Name` varchar(150) DEFAULT NULL,
  `Email` varchar(250) DEFAULT NULL,
  `Phone_Mobile` varchar(25) DEFAULT NULL,
  `Gender` int(11) DEFAULT NULL,
  `Age` tinyint(4) DEFAULT NULL,
  `Date_of_Registration` date DEFAULT NULL,
  `Abhyasi_ID` varchar(100) DEFAULT NULL,
  `Status` tinyint(1) DEFAULT '0' COMMENT '0 Means Active, 1 means Inactive',
  `Seeker_ID` int(11) NOT NULL,
  `Maturity_Status_ID` int(11) DEFAULT NULL,
  `ID_Card_Num` varchar(45) DEFAULT NULL,
  `Language` varchar(45) DEFAULT NULL,
  `Subscribe` tinyint(1) DEFAULT '0' COMMENT '0 Means Subscribed, 1 means UnSubsribed',
  `Occupation` varchar(45) DEFAULT NULL,
  `Welcome_msg_sent` tinyint(1) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL,
  `Created_by` varchar(45) DEFAULT NULL,
  `updated_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Seeker_ID`),
  UNIQUE KEY `Email_UNIQUE` (`Email`),
  KEY `Maturity_FK_idx` (`Maturity_Status_ID`),
  CONSTRAINT `Maturity_FK` FOREIGN KEY (`Maturity_Status_ID`) REFERENCES `maturity` (`Maturity_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Dumping structure for table pmp_dev.seeker_aims
DROP TABLE IF EXISTS `seeker_aims`;
CREATE TABLE IF NOT EXISTS `seeker_aims` (
  `First_Name` varchar(150) DEFAULT NULL,
  `Last_Name` varchar(150) DEFAULT NULL,
  `Middle_Name` varchar(50) DEFAULT NULL,
  `Email` varchar(250) DEFAULT NULL,
  `Phone_Mobile` varchar(25) DEFAULT NULL,
  `Gender` int(11) DEFAULT NULL,
  `Date_of_Birth` date DEFAULT NULL,
  `Date_of_Registration` date DEFAULT NULL,
  `Abhyasi_ID` varchar(100) DEFAULT NULL,
  `Status` tinyint(1) DEFAULT '0' COMMENT '0 means Active\\n1 means Inactive',
  `Address_Line_1` varchar(150) DEFAULT NULL,
  `Address_Line_2` varchar(150) DEFAULT NULL,
  `City` varchar(50) DEFAULT NULL,
  `State` varchar(50) DEFAULT NULL,
  `Country` varchar(50) DEFAULT NULL,
  `Seeker_ID` int(11) NOT NULL,
  `Program_id` int(11) NOT NULL,
  `Occupation` varchar(50) DEFAULT NULL,
  `Remarks` varchar(500) DEFAULT NULL,
  `ID_Card_Num` varchar(45) DEFAULT NULL,
  `Language` varchar(45) DEFAULT NULL,
  `Sync_Status` varchar(45) DEFAULT NULL,
  `Introduced` tinyint(1) DEFAULT '0' COMMENT '0 means No   and 1 means Yes',
  `Introduced_Date` date DEFAULT NULL,
  `Introduced_raw_date` varchar(50) DEFAULT NULL,
  `Introduced_By` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`Seeker_ID`),
  KEY `ProgramId_FK` (`Program_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
/*!40014 SET FOREIGN_KEY_CHECKS=1 */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
