 -- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: apskaitavnext2
-- ------------------------------------------------------
-- Server version	5.7.20-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES UTF8MB4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

DROP DATABASE ACCOUNTING;
CREATE DATABASE ACCOUNTING;
USE ACCOUNTING;


--
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = UTF8MB4 */;
CREATE TABLE `accounts` (
  `id` bigint unsigned NOT NULL,
  `account_name` varchar(255) COLLATE UTF8MB4_bin NOT NULL,
  `balance` decimal(20,2) NOT NULL,
  `account_type` smallint unsigned NOT NULL,
  `official_code` varchar(20) COLLATE UTF8MB4_bin NOT NULL,
  `is_archived` tinyint unsigned NOT NULL,
  `inserted_at` datetime NOT NULL,
  `inserted_by` varchar(255) COLLATE UTF8MB4_bin NOT NULL,
  `updated_at` datetime NOT NULL,
  `updated_by` varchar(255) COLLATE UTF8MB4_bin NOT NULL,
  `parent_account_id` bigint unsigned,
  PRIMARY KEY (`id`),
  KEY `accounts_account_type_idx` (`account_type`),
  KEY `accounts_official_code_idx` (`official_code`),
   CONSTRAINT `account_type_fk` FOREIGN KEY (`account_type`) REFERENCES `accounts_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
   CONSTRAINT `parent_account_id_fk` FOREIGN KEY (`parent_account_id`) REFERENCES `accounts` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `accounts_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = UTF8MB4 */;
CREATE TABLE `accounts_type` (
  `id` smallint unsigned NOT NULL,
  `account_name` varchar(255) COLLATE UTF8MB4_bin NOT NULL,
  `description` varchar(255) COLLATE UTF8MB4_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `balance_and_income_lines`
--

DROP TABLE IF EXISTS `balance_and_income_lines`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = UTF8MB4 */;
CREATE TABLE `balance_and_income_lines` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `line_type` enum('base_header','balance_header','income_header','balance_line','income_line') CHARACTER SET ascii NOT NULL,
  `visible_index` int unsigned NOT NULL,
  `printed_no` varchar(20) COLLATE UTF8MB4_bin NOT NULL,
  `line_text` varchar(255) COLLATE UTF8MB4_bin NOT NULL,
  `value_type` enum('D','C') CHARACTER SET ascii NOT NULL,
  `left_index` int unsigned NOT NULL,
  `right_index` int unsigned NOT NULL,
  `inserted_at` datetime NOT NULL,
  `inserted_by` varchar(255) COLLATE UTF8MB4_bin NOT NULL,
  `updated_at` datetime NOT NULL,
  `updated_by` varchar(255) COLLATE UTF8MB4_bin NOT NULL,
  PRIMARY KEY (`id`),
  KEY `balance_and_income_lines_line_type_idx` (`line_type`),
  KEY `balance_and_income_lines_visible_index_idx` (`visible_index`),
  KEY `balance_and_income_lines_left_index_idx` (`left_index`),
  KEY `balance_and_income_lines_right_index_idx` (`right_index`),
  KEY `balance_and_income_lines_value_type_idx` (`value_type`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cash_flow_adjustments`
--

DROP TABLE IF EXISTS `cash_flow_adjustments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = UTF8MB4 */;
CREATE TABLE `cash_flow_adjustments` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `transaction_id` int unsigned NOT NULL,
  `account_id` bigint unsigned NOT NULL,
  `entry_type` enum('D','C') CHARACTER SET ascii NOT NULL,
  `amount` decimal(20,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `cash_flow_adjustments_entry_type_idx` (`entry_type`),
  KEY `cash_flow_adjustments_transaction_id_fk` (`transaction_id`),
  KEY `cash_flow_adjustments_account_id_fk` (`account_id`),
  CONSTRAINT `cash_flow_adjustments_account_id_fk` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `cash_flow_adjustments_transaction_id_fk` FOREIGN KEY (`transaction_id`) REFERENCES `transactions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cash_flow_line_assignments`
--

DROP TABLE IF EXISTS `cash_flow_line_assignments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = UTF8MB4 */;
CREATE TABLE `cash_flow_line_assignments` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `account_id` bigint unsigned NOT NULL,
  `cash_flow_line_id` int unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `cash_flow_line_assignments_account_id_fk` (`account_id`),
  KEY `cash_flow_line_assignments_cash_flow_line_id_fk` (`cash_flow_line_id`),
  CONSTRAINT `cash_flow_line_assignments_account_id_fk` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cash_flow_line_assignments_cash_flow_line_id_fk` FOREIGN KEY (`cash_flow_line_id`) REFERENCES `cash_flow_lines` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cash_flow_lines`
--

DROP TABLE IF EXISTS `cash_flow_lines`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = UTF8MB4 */;
CREATE TABLE `cash_flow_lines` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `line_type` enum('header','line') CHARACTER SET ascii NOT NULL,
  `is_net_income` tinyint unsigned NOT NULL,
  `visible_index` int unsigned NOT NULL,
  `printed_no` varchar(20) COLLATE UTF8MB4_bin NOT NULL,
  `line_text` varchar(255) COLLATE UTF8MB4_bin NOT NULL,
  `value_type` enum('D','C') CHARACTER SET ascii NOT NULL,
  `balance_type` enum('total','per_period','D','C') CHARACTER SET ascii NOT NULL,
  `left_index` int unsigned NOT NULL,
  `right_index` int unsigned NOT NULL,
  `inserted_at` datetime NOT NULL,
  `inserted_by` varchar(255) COLLATE UTF8MB4_bin NOT NULL,
  `updated_at` datetime NOT NULL,
  `updated_by` varchar(255) COLLATE UTF8MB4_bin NOT NULL,
  PRIMARY KEY (`id`),
  KEY `cash_flow_lines_line_type_idx` (`line_type`),
  KEY `cash_flow_lines_is_net_income_idx` (`is_net_income`),
  KEY `cash_flow_lines_visible_index_idx` (`visible_index`),
  KEY `cash_flow_lines_value_type_idx` (`value_type`),
  KEY `cash_flow_lines_balance_type_idx` (`balance_type`),
  KEY `cash_flow_lines_left_index_idx` (`left_index`),
  KEY `cash_flow_lines_right_index_idx` (`right_index`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `country_codes`
--

DROP TABLE IF EXISTS `country_codes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = UTF8MB4 */;
CREATE TABLE `country_codes` (
  `id` char(2) CHARACTER SET ascii NOT NULL,
  `is_archived` tinyint unsigned NOT NULL,
  `inserted_at` datetime NOT NULL,
  `inserted_by` varchar(255) COLLATE UTF8MB4_bin NOT NULL,
  `updated_at` datetime NOT NULL,
  `updated_by` varchar(255) COLLATE UTF8MB4_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `currency_codes`
--

DROP TABLE IF EXISTS `currency_codes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = UTF8MB4 */;
CREATE TABLE `currency_codes` (
  `id` char(3) CHARACTER SET ascii NOT NULL,
  `is_archived` tinyint unsigned NOT NULL,
  `inserted_at` datetime NOT NULL,
  `inserted_by` varchar(255) COLLATE UTF8MB4_bin NOT NULL,
  `updated_at` datetime NOT NULL,
  `updated_by` varchar(255) COLLATE UTF8MB4_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `documents`
--

DROP TABLE IF EXISTS `documents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = UTF8MB4 */;
CREATE TABLE `documents` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `document_date` date NOT NULL,
  `quantity` decimal(6,3) NOT NULL,
  `rate` decimal(5,2) NOT NULL,
  `total` decimal(20,2) NOT NULL,
  `documents_type` int unsigned NOT NULL,
  `snf` decimal(4,2),
  `fat` decimal(4,2),
  `degree` decimal(4,2),
  `document_time` enum('M','E') CHARACTER SET ascii,
  `animal_type` enum('B','C') CHARACTER SET ascii, 
  `inserted_at` datetime NOT NULL,
  `inserted_by` varchar(255) COLLATE UTF8MB4_bin NOT NULL,
  `updated_at` datetime,
  `updated_by` varchar(255) COLLATE UTF8MB4_bin,
  `updated_comment` varchar(255) COLLATE UTF8MB4_bin,
  PRIMARY KEY (`id`),
  KEY `documents_document_date_idx` (`document_date`),
  KEY `documents_animal_type_idx` (`animal_type`),
  KEY `documents_document_time_type_idx` (`document_time`),
  CONSTRAINT `documents_type_fk` FOREIGN KEY (`documents_type`) REFERENCES `documents_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE  
) ENGINE=InnoDB AUTO_INCREMENT=1000001 DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `documents`
--

DROP TABLE IF EXISTS `documents_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = UTF8MB4 */;
CREATE TABLE `documents_type` (
  `id` int unsigned NOT NULL,
  `type` varchar(255) COLLATE UTF8MB4_bin NOT NULL,  
  `description` varchar(255) COLLATE UTF8MB4_bin,  
  PRIMARY KEY (`id`)  
) ENGINE=InnoDB AUTO_INCREMENT=1000001 DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `equity_columns`
--

DROP TABLE IF EXISTS `equity_columns`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = UTF8MB4 */;
CREATE TABLE `equity_columns` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `column_text` varchar(255) COLLATE UTF8MB4_bin NOT NULL,
  `visible_index` int unsigned NOT NULL,
  `inserted_at` datetime NOT NULL,
  `inserted_by` varchar(255) COLLATE UTF8MB4_bin NOT NULL,
  `updated_at` datetime NOT NULL,
  `updated_by` varchar(255) COLLATE UTF8MB4_bin NOT NULL,
  PRIMARY KEY (`id`),
  KEY `equity_columns_visible_index_idx` (`visible_index`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `equity_line_assignments`
--

DROP TABLE IF EXISTS `equity_line_assignments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = UTF8MB4 */;
CREATE TABLE `equity_line_assignments` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `equity_line_id` int unsigned NOT NULL,
  `document_type` smallint NOT NULL,
  `extended_document_type_id` char(32) CHARACTER SET ascii COLLATE ascii_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `equity_line_assignments_document_type_idx` (`document_type`),
  KEY `equity_line_assignments_equity_line_id_fk` (`equity_line_id`),
  KEY `equity_line_assignments_extended_document_type_id_fk` (`extended_document_type_id`),
  CONSTRAINT `equity_line_assignments_equity_line_id_fk` FOREIGN KEY (`equity_line_id`) REFERENCES `equity_lines` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `equity_line_assignments_extended_document_type_id_fk` FOREIGN KEY (`extended_document_type_id`) REFERENCES `extended_document_types` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `equity_lines`
--

DROP TABLE IF EXISTS `equity_lines`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = UTF8MB4 */;
CREATE TABLE `equity_lines` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `visible_index` int unsigned NOT NULL,
  `printed_no` varchar(20) COLLATE UTF8MB4_bin NOT NULL,
  `line_text` varchar(255) COLLATE UTF8MB4_bin NOT NULL,
  `line_type` enum('initial_balance','cumulative_delta','zero_balance','first_delta','first_balance','second_delta','second_balance') CHARACTER SET ascii NOT NULL,
  `inserted_at` datetime NOT NULL,
  `inserted_by` varchar(255) COLLATE UTF8MB4_bin NOT NULL,
  `updated_at` datetime NOT NULL,
  `updated_by` varchar(255) COLLATE UTF8MB4_bin NOT NULL,
  PRIMARY KEY (`id`),
  KEY `equity_lines_visible_index_idx` (`visible_index`),
  KEY `equity_lines_line_type_idx` (`line_type`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `extended_document_types`
--

DROP TABLE IF EXISTS `extended_document_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = UTF8MB4 */;
CREATE TABLE `extended_document_types` (
  `id` char(32) CHARACTER SET ascii COLLATE ascii_bin NOT NULL,
  `extension_id` int unsigned,
  `type_name` varchar(255) COLLATE UTF8MB4_bin NOT NULL,
  `fallback_document_type` smallint unsigned,
  PRIMARY KEY (`id`),
  KEY `extended_document_types_extension_id_fk` (`extension_id`),
  CONSTRAINT `extended_document_types_extension_id_fk` FOREIGN KEY (`extension_id`) REFERENCES `extensions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `extended_fixed_assets_operation_types`
--

DROP TABLE IF EXISTS `extended_fixed_assets_operation_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = UTF8MB4 */;
CREATE TABLE `extended_fixed_assets_operation_types` (
  `id` char(32) CHARACTER SET ascii COLLATE ascii_bin NOT NULL,
  `extension_id` int unsigned NOT NULL,
  `type_name` varchar(255) COLLATE UTF8MB4_bin NOT NULL,
  `fallback_fixed_assets_operation_type` smallint unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `extended_fixed_assets_operation_types_extension_id_fk` (`extension_id`),
  CONSTRAINT `extended_fixed_assets_operation_types_extension_id_fk` FOREIGN KEY (`extension_id`) REFERENCES `extensions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `extended_inventory_operation_types`
--

DROP TABLE IF EXISTS `extended_inventory_operation_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = UTF8MB4 */;
CREATE TABLE `extended_inventory_operation_types` (
  `id` char(32) CHARACTER SET ascii COLLATE ascii_bin NOT NULL,
  `extension_id` int unsigned NOT NULL,
  `type_name` varchar(255) COLLATE UTF8MB4_bin NOT NULL,
  `fallback_inventory_operation_type` smallint unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `extended_inventory_operation_types_extension_id_fk` (`extension_id`),
  CONSTRAINT `extended_inventory_operation_types_extension_id_fk` FOREIGN KEY (`extension_id`) REFERENCES `extensions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `extensions`
--

DROP TABLE IF EXISTS `extensions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = UTF8MB4 */;
CREATE TABLE `extensions` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `extension_guid` char(32) CHARACTER SET ascii COLLATE ascii_bin NOT NULL,
  `extension_name` varchar(255) COLLATE UTF8MB4_bin NOT NULL,
  `extension_version` varchar(20) COLLATE UTF8MB4_bin NOT NULL,
  `inserted_at` datetime NOT NULL,
  `inserted_by` varchar(255) COLLATE UTF8MB4_bin NOT NULL,
  `updated_at` datetime NOT NULL,
  `updated_by` varchar(255) COLLATE UTF8MB4_bin NOT NULL,
  `uninstalled_at` datetime DEFAULT NULL,
  `uninstalled_by` varchar(255) COLLATE UTF8MB4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ledger_entries`
--

DROP TABLE IF EXISTS `ledger_entries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = UTF8MB4 */;
CREATE TABLE `ledger_entries` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `transaction_id` int unsigned NOT NULL,
  `account_id` bigint unsigned NOT NULL,
  `entry_type` enum('D','C') CHARACTER SET ascii NOT NULL,
  `amount` decimal(20,2) NOT NULL,
  `closing_balance` decimal(20,2) NOT NULL,
  `cost_centre_id` int unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ledger_entries_entry_type_idx` (`entry_type`),
  KEY `ledger_entries_transaction_id_fk` (`transaction_id`),
  KEY `ledger_entries_account_id_fk` (`account_id`),
  CONSTRAINT `ledger_entries_account_id_fk` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `ledger_entries_transaction_id_fk` FOREIGN KEY (`transaction_id`) REFERENCES `transactions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1000001 DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = UTF8MB4 */;
CREATE TABLE `transactions` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `document_id` int unsigned NOT NULL,
  `transaction_date` date NOT NULL,
  `description` varchar(255) COLLATE UTF8MB4_bin NOT NULL,
  PRIMARY KEY (`id`),
  KEY `transactions_transaction_date_idx` (`transaction_date`),
  KEY `transactions_document_id_fk` (`document_id`),
  CONSTRAINT `transactions_document_id_fk` FOREIGN KEY (`document_id`) REFERENCES `documents` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1000001 DEFAULT CHARSET=UTF8MB4 COLLATE=UTF8MB4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping events for database 'apskaitavnext2'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-08-10  0:35:46
