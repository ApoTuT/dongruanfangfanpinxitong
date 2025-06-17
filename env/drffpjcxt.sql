-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: drffpjcxt
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_administrative_division`
--

DROP TABLE IF EXISTS `t_administrative_division`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_administrative_division` (
  `code` varchar(50) NOT NULL COMMENT '行政区划代码',
  `division_name` varchar(200) NOT NULL COMMENT '行政区划名称',
  `level_type` tinyint unsigned NOT NULL COMMENT '级别:1-省,2-市,3-县,4-乡,5-村',
  `parent_code` varchar(50) DEFAULT NULL COMMENT '上级行政区划代码',
  `is_deleted` tinyint unsigned DEFAULT '0' COMMENT '逻辑删除标记:0-未删除,1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`code`),
  KEY `idx_parent_code` (`parent_code`),
  KEY `idx_level_type` (`level_type`),
  KEY `idx_is_deleted` (`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='行政区划表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_administrative_division`
--

LOCK TABLES `t_administrative_division` WRITE;
/*!40000 ALTER TABLE `t_administrative_division` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_administrative_division` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_application_attachment`
--

DROP TABLE IF EXISTS `t_application_attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_application_attachment` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '附件ID',
  `application_id` bigint unsigned NOT NULL COMMENT '关联申请ID',
  `original_file_name` varchar(500) DEFAULT NULL COMMENT '原始文件名',
  `file_path` varchar(1000) DEFAULT NULL COMMENT '文件存储路径',
  `file_type` varchar(100) DEFAULT NULL COMMENT '文件类型',
  `file_size` bigint unsigned DEFAULT NULL COMMENT '文件大小(字节)',
  `is_deleted` tinyint unsigned DEFAULT '0' COMMENT '逻辑删除标记:0-未删除,1-已删除',
  `uploaded_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '上传时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_application_id` (`application_id`),
  KEY `idx_is_deleted` (`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='申请佐证材料表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_application_attachment`
--

LOCK TABLES `t_application_attachment` WRITE;
/*!40000 ALTER TABLE `t_application_attachment` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_application_attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_approval_record`
--

DROP TABLE IF EXISTS `t_approval_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_approval_record` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '审批记录ID',
  `business_type` varchar(100) NOT NULL COMMENT '业务类型',
  `business_id` bigint unsigned NOT NULL COMMENT '业务ID',
  `step_name` varchar(100) DEFAULT NULL COMMENT '审批步骤名称',
  `approver_id` bigint unsigned NOT NULL COMMENT '审批人用户ID',
  `result` tinyint unsigned DEFAULT NULL COMMENT '审批结果:1-通过,2-驳回,3-退回修改',
  `comments` text COMMENT '审批意见',
  `approval_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '审批时间',
  `is_deleted` tinyint unsigned DEFAULT '0' COMMENT '逻辑删除标记:0-未删除,1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_business` (`business_type`,`business_id`),
  KEY `idx_approver_id` (`approver_id`),
  KEY `idx_approval_time` (`approval_time`),
  KEY `idx_is_deleted` (`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='审批记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_approval_record`
--

LOCK TABLES `t_approval_record` WRITE;
/*!40000 ALTER TABLE `t_approval_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_approval_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_clue_verification`
--

DROP TABLE IF EXISTS `t_clue_verification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_clue_verification` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '核实记录ID',
  `clue_id` bigint unsigned NOT NULL COMMENT '关联线索ID',
  `verifier_id` bigint unsigned NOT NULL COMMENT '核实人用户ID',
  `verification_date` date NOT NULL COMMENT '实际核实日期',
  `process_description` text COMMENT '核实过程描述',
  `conclusion` varchar(100) DEFAULT NULL COMMENT '核实结论',
  `detailed_situation` text COMMENT '详细情况说明',
  `suggestion` text COMMENT '后续处置建议',
  `is_deleted` tinyint unsigned DEFAULT '0' COMMENT '逻辑删除标记:0-未删除,1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '核实报告提交时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_clue_id` (`clue_id`),
  KEY `idx_verifier_id` (`verifier_id`),
  KEY `idx_verification_date` (`verification_date`),
  KEY `idx_is_deleted` (`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='线索核实记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_clue_verification`
--

LOCK TABLES `t_clue_verification` WRITE;
/*!40000 ALTER TABLE `t_clue_verification` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_clue_verification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_dashboard`
--

DROP TABLE IF EXISTS `t_dashboard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_dashboard` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '仪表盘ID',
  `dashboard_name` varchar(200) NOT NULL COMMENT '仪表盘名称',
  `description` text COMMENT '仪表盘描述',
  `created_by` bigint unsigned DEFAULT NULL COMMENT '创建人用户ID',
  `is_deleted` tinyint unsigned DEFAULT '0' COMMENT '逻辑删除标记:0-未删除,1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_created_by` (`created_by`),
  KEY `idx_is_deleted` (`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='仪表盘表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_dashboard`
--

LOCK TABLES `t_dashboard` WRITE;
/*!40000 ALTER TABLE `t_dashboard` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_dashboard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_dashboard_widget`
--

DROP TABLE IF EXISTS `t_dashboard_widget`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_dashboard_widget` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '组件ID',
  `dashboard_id` bigint unsigned NOT NULL COMMENT '仪表盘ID',
  `title` varchar(200) DEFAULT NULL COMMENT '组件标题',
  `chart_type` varchar(50) DEFAULT NULL COMMENT '图表类型',
  `layout_json` json DEFAULT NULL COMMENT '布局信息JSON',
  `data_query_json` json DEFAULT NULL COMMENT '数据查询配置JSON',
  `is_deleted` tinyint unsigned DEFAULT '0' COMMENT '逻辑删除标记:0-未删除,1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_dashboard_id` (`dashboard_id`),
  KEY `idx_is_deleted` (`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='仪表盘组件表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_dashboard_widget`
--

LOCK TABLES `t_dashboard_widget` WRITE;
/*!40000 ALTER TABLE `t_dashboard_widget` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_dashboard_widget` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_data_dictionary`
--

DROP TABLE IF EXISTS `t_data_dictionary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_data_dictionary` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '字典ID',
  `type_code` varchar(100) NOT NULL COMMENT '字典类型编码',
  `item_code` varchar(100) NOT NULL COMMENT '字典项编码',
  `item_name` varchar(200) NOT NULL COMMENT '字典项名称',
  `sort_order` int unsigned DEFAULT '0' COMMENT '排序号',
  `is_enabled` tinyint unsigned DEFAULT '1' COMMENT '是否启用:0-禁用,1-启用',
  `description` text COMMENT '描述',
  `is_deleted` tinyint unsigned DEFAULT '0' COMMENT '逻辑删除标记:0-未删除,1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_type_item` (`type_code`,`item_code`),
  KEY `idx_type_code` (`type_code`),
  KEY `idx_is_deleted` (`is_deleted`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='数据字典表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_data_dictionary`
--

LOCK TABLES `t_data_dictionary` WRITE;
/*!40000 ALTER TABLE `t_data_dictionary` DISABLE KEYS */;
INSERT INTO `t_data_dictionary` VALUES (1,'MONITORING_TYPE','1','脱贫不稳定户',1,1,NULL,0,'2025-06-12 09:34:16','2025-06-12 09:34:16'),(2,'MONITORING_TYPE','2','边缘易致贫户',2,1,NULL,0,'2025-06-12 09:34:16','2025-06-12 09:34:16'),(3,'MONITORING_TYPE','3','突发严重困难户',3,1,NULL,0,'2025-06-12 09:34:16','2025-06-12 09:34:16'),(4,'RISK_LEVEL','1','高风险',1,1,NULL,0,'2025-06-12 09:34:16','2025-06-12 09:34:16'),(5,'RISK_LEVEL','2','中风险',2,1,NULL,0,'2025-06-12 09:34:16','2025-06-12 09:34:16'),(6,'RISK_LEVEL','3','低风险',3,1,NULL,0,'2025-06-12 09:34:16','2025-06-12 09:34:16'),(7,'CLUE_SOURCE','VILLAGE_VISIT','乡村干部走访',1,1,NULL,0,'2025-06-12 09:34:16','2025-06-12 09:34:16'),(8,'CLUE_SOURCE','INDUSTRY_DATA','行业部门数据',2,1,NULL,0,'2025-06-12 09:34:16','2025-06-12 09:34:16'),(9,'CLUE_SOURCE','PUBLIC_REPORT','群众举报',3,1,NULL,0,'2025-06-12 09:34:16','2025-06-12 09:34:16'),(10,'CLUE_SOURCE','SYSTEM_SCAN','系统自动扫描',4,1,NULL,0,'2025-06-12 09:34:16','2025-06-12 09:34:16'),(11,'URGENCY_LEVEL','1','特急',1,1,NULL,0,'2025-06-12 09:34:16','2025-06-12 09:34:16'),(12,'URGENCY_LEVEL','2','紧急',2,1,NULL,0,'2025-06-12 09:34:16','2025-06-12 09:34:16'),(13,'URGENCY_LEVEL','3','一般',3,1,NULL,0,'2025-06-12 09:34:16','2025-06-12 09:34:16'),(14,'APPROVAL_RESULT','1','通过',1,1,NULL,0,'2025-06-12 09:34:16','2025-06-12 09:34:16'),(15,'APPROVAL_RESULT','2','驳回',2,1,NULL,0,'2025-06-12 09:34:16','2025-06-12 09:34:16'),(16,'APPROVAL_RESULT','3','退回修改',3,1,NULL,0,'2025-06-12 09:34:16','2025-06-12 09:34:16');
/*!40000 ALTER TABLE `t_data_dictionary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_family_member`
--

DROP TABLE IF EXISTS `t_family_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_family_member` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '家庭成员ID',
  `farmer_id` bigint unsigned NOT NULL COMMENT '关联农户档案ID',
  `member_name` varchar(100) NOT NULL COMMENT '成员姓名',
  `relation_to_head` varchar(50) DEFAULT NULL COMMENT '与户主关系',
  `id_card` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `gender` tinyint unsigned DEFAULT NULL COMMENT '性别:1-男,2-女',
  `birth_date` date DEFAULT NULL COMMENT '出生日期',
  `health_status` varchar(200) DEFAULT NULL COMMENT '健康状况',
  `labor_ability` varchar(200) DEFAULT NULL COMMENT '劳动能力',
  `education_level` varchar(100) DEFAULT NULL COMMENT '文化程度',
  `school_status` varchar(200) DEFAULT NULL COMMENT '在校情况',
  `employment_status` varchar(200) DEFAULT NULL COMMENT '务工情况',
  `is_deleted` tinyint unsigned DEFAULT '0' COMMENT '逻辑删除标记:0-未删除,1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_farmer_id` (`farmer_id`),
  KEY `idx_id_card` (`id_card`),
  KEY `idx_is_deleted` (`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='家庭成员表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_family_member`
--

LOCK TABLES `t_family_member` WRITE;
/*!40000 ALTER TABLE `t_family_member` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_family_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_farmer_profile`
--

DROP TABLE IF EXISTS `t_farmer_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_farmer_profile` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '农户档案ID',
  `head_name` varchar(100) NOT NULL COMMENT '户主姓名',
  `head_id_card` varchar(18) NOT NULL COMMENT '户主身份证号',
  `address_code` varchar(50) NOT NULL COMMENT '家庭住址行政区划代码',
  `address_detail` varchar(500) DEFAULT NULL COMMENT '详细地址',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `population_count` int unsigned DEFAULT '1' COMMENT '家庭人口数量',
  `monitoring_type` tinyint unsigned DEFAULT NULL COMMENT '监测对象类型:1-脱贫不稳定户,2-边缘易致贫户,3-突发严重困难户',
  `risk_level` tinyint unsigned DEFAULT NULL COMMENT '风险等级:1-高风险,2-中风险,3-低风险',
  `main_risk_types` json DEFAULT NULL COMMENT '主要风险类型(JSON数组)',
  `economic_info_json` json DEFAULT NULL COMMENT '经济状况信息(JSON)',
  `housing_info_json` json DEFAULT NULL COMMENT '住房与生活条件信息(JSON)',
  `policy_info_json` json DEFAULT NULL COMMENT '享受政策与帮扶情况(JSON)',
  `status` tinyint unsigned DEFAULT '1' COMMENT '档案状态:1-正常监测,2-已解除风险',
  `is_deleted` tinyint unsigned DEFAULT '0' COMMENT '逻辑删除标记:0-未删除,1-已删除',
  `created_by` bigint unsigned DEFAULT NULL COMMENT '创建人用户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_by` bigint unsigned DEFAULT NULL COMMENT '最后更新人用户ID',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_head_id_card` (`head_id_card`),
  KEY `idx_address_code` (`address_code`),
  KEY `idx_monitoring_type` (`monitoring_type`),
  KEY `idx_risk_level` (`risk_level`),
  KEY `idx_status` (`status`),
  KEY `idx_created_by` (`created_by`),
  KEY `idx_is_deleted` (`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='农户档案表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_farmer_profile`
--

LOCK TABLES `t_farmer_profile` WRITE;
/*!40000 ALTER TABLE `t_farmer_profile` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_farmer_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_high_risk_application`
--

DROP TABLE IF EXISTS `t_high_risk_application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_high_risk_application` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '申请ID',
  `farmer_id` bigint unsigned NOT NULL COMMENT '关联农户档案ID',
  `applicant_id` bigint unsigned NOT NULL COMMENT '申请人用户ID',
  `application_type` tinyint unsigned NOT NULL COMMENT '申请类型:1-新增高风险标记,2-申请解除高风险标记',
  `reason` text NOT NULL COMMENT '申请理由和风险描述',
  `target_risk_level` tinyint unsigned DEFAULT NULL COMMENT '期望风险等级',
  `status` varchar(50) DEFAULT '待乡级审批' COMMENT '审批状态',
  `is_deleted` tinyint unsigned DEFAULT '0' COMMENT '逻辑删除标记:0-未删除,1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '申请提交时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_farmer_id` (`farmer_id`),
  KEY `idx_applicant_id` (`applicant_id`),
  KEY `idx_status` (`status`),
  KEY `idx_create_time` (`create_time`),
  KEY `idx_is_deleted` (`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='高风险标记申请表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_high_risk_application`
--

LOCK TABLES `t_high_risk_application` WRITE;
/*!40000 ALTER TABLE `t_high_risk_application` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_high_risk_application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_high_risk_profile`
--

DROP TABLE IF EXISTS `t_high_risk_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_high_risk_profile` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '高风险档案ID',
  `farmer_id` bigint unsigned NOT NULL COMMENT '关联农户档案ID',
  `marking_application_id` bigint unsigned DEFAULT NULL COMMENT '关联高风险标记申请ID',
  `risk_cause_description` text COMMENT '风险原因详细描述',
  `risk_trend_description` text COMMENT '风险发展趋势描述',
  `monitoring_status` tinyint unsigned DEFAULT '1' COMMENT '监测状态:1-重点监测,2-常规监测,3-观察期',
  `risk_identified_date` date DEFAULT NULL COMMENT '首次识别为高风险的日期',
  `last_warning_time` datetime DEFAULT NULL COMMENT '最近一次预警时间',
  `total_warning_count` int unsigned DEFAULT '0' COMMENT '累计预警次数',
  `is_deleted` tinyint unsigned DEFAULT '0' COMMENT '逻辑删除标记:0-未删除,1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_farmer_id` (`farmer_id`),
  KEY `idx_monitoring_status` (`monitoring_status`),
  KEY `idx_risk_identified_date` (`risk_identified_date`),
  KEY `idx_is_deleted` (`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='高风险户档案表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_high_risk_profile`
--

LOCK TABLES `t_high_risk_profile` WRITE;
/*!40000 ALTER TABLE `t_high_risk_profile` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_high_risk_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_material`
--

DROP TABLE IF EXISTS `t_material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_material` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '物资ID',
  `material_name` varchar(200) NOT NULL COMMENT '物资名称',
  `specification` varchar(500) DEFAULT NULL COMMENT '规格型号',
  `unit` varchar(50) DEFAULT NULL COMMENT '计量单位',
  `description` text COMMENT '物资描述',
  `is_deleted` tinyint unsigned DEFAULT '0' COMMENT '逻辑删除标记:0-未删除,1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_material_name` (`material_name`),
  KEY `idx_is_deleted` (`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='物资信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_material`
--

LOCK TABLES `t_material` WRITE;
/*!40000 ALTER TABLE `t_material` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_material_application`
--

DROP TABLE IF EXISTS `t_material_application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_material_application` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '申请单ID',
  `applicant_id` bigint unsigned NOT NULL COMMENT '申请人用户ID',
  `farmer_id` bigint unsigned NOT NULL COMMENT '申请物资针对的农户ID',
  `reason` text COMMENT '申请理由和用途说明',
  `status` varchar(50) DEFAULT '待审批' COMMENT '审批状态',
  `is_deleted` tinyint unsigned DEFAULT '0' COMMENT '逻辑删除标记:0-未删除,1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '申请提交时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_applicant_id` (`applicant_id`),
  KEY `idx_farmer_id` (`farmer_id`),
  KEY `idx_status` (`status`),
  KEY `idx_is_deleted` (`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='物资申请单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_material_application`
--

LOCK TABLES `t_material_application` WRITE;
/*!40000 ALTER TABLE `t_material_application` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_material_application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_material_application_detail`
--

DROP TABLE IF EXISTS `t_material_application_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_material_application_detail` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '申请明细ID',
  `application_id` bigint unsigned NOT NULL COMMENT '物资申请单ID',
  `material_id` bigint unsigned NOT NULL COMMENT '物资信息ID',
  `requested_quantity` int unsigned NOT NULL COMMENT '申请数量',
  `approved_quantity` int unsigned DEFAULT '0' COMMENT '批准数量',
  `is_deleted` tinyint unsigned DEFAULT '0' COMMENT '逻辑删除标记:0-未删除,1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_application_id` (`application_id`),
  KEY `idx_material_id` (`material_id`),
  KEY `idx_is_deleted` (`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='物资申请明细表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_material_application_detail`
--

LOCK TABLES `t_material_application_detail` WRITE;
/*!40000 ALTER TABLE `t_material_application_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_material_application_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_material_distribution`
--

DROP TABLE IF EXISTS `t_material_distribution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_material_distribution` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '发放记录ID',
  `application_id` bigint unsigned NOT NULL COMMENT '关联物资申请单ID',
  `recipient_name` varchar(100) DEFAULT NULL COMMENT '实际领取人姓名',
  `signature_url` varchar(1000) DEFAULT NULL COMMENT '签字确认图片URL',
  `distribution_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '实际发放时间',
  `distributed_by` bigint unsigned DEFAULT NULL COMMENT '发放经办人用户ID',
  `is_deleted` tinyint unsigned DEFAULT '0' COMMENT '逻辑删除标记:0-未删除,1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_application_id` (`application_id`),
  KEY `idx_distribution_time` (`distribution_time`),
  KEY `idx_is_deleted` (`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='物资发放记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_material_distribution`
--

LOCK TABLES `t_material_distribution` WRITE;
/*!40000 ALTER TABLE `t_material_distribution` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_material_distribution` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_operation_log`
--

DROP TABLE IF EXISTS `t_operation_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_operation_log` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `operator_id` bigint unsigned DEFAULT NULL COMMENT '操作人用户ID',
  `operator_name` varchar(100) DEFAULT NULL COMMENT '操作人姓名',
  `module` varchar(100) DEFAULT NULL COMMENT '操作模块',
  `operation_type` varchar(100) DEFAULT NULL COMMENT '操作类型',
  `description` text COMMENT '操作详细描述',
  `request_ip` varchar(50) DEFAULT NULL COMMENT '请求IP地址',
  `operation_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `is_success` tinyint unsigned DEFAULT '1' COMMENT '操作是否成功:0-失败,1-成功',
  `error_message` text COMMENT '错误信息',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_operator_id` (`operator_id`),
  KEY `idx_operation_time` (`operation_time`),
  KEY `idx_module` (`module`),
  KEY `idx_is_success` (`is_success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='操作日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_operation_log`
--

LOCK TABLES `t_operation_log` WRITE;
/*!40000 ALTER TABLE `t_operation_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_operation_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_pairing`
--

DROP TABLE IF EXISTS `t_pairing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_pairing` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '结对关系ID',
  `farmer_id` bigint unsigned NOT NULL COMMENT '农户档案ID',
  `user_id` bigint unsigned NOT NULL COMMENT '帮扶干部用户ID',
  `start_date` date NOT NULL COMMENT '结对开始日期',
  `end_date` date DEFAULT NULL COMMENT '结对结束日期',
  `status` tinyint unsigned DEFAULT '1' COMMENT '结对状态:0-已解除,1-有效',
  `reason_for_change` text COMMENT '调整或解除原因',
  `created_by` bigint unsigned DEFAULT NULL COMMENT '创建人用户ID',
  `is_deleted` tinyint unsigned DEFAULT '0' COMMENT '逻辑删除标记:0-未删除,1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_farmer_id` (`farmer_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_status` (`status`),
  KEY `idx_is_deleted` (`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='帮扶结对关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_pairing`
--

LOCK TABLES `t_pairing` WRITE;
/*!40000 ALTER TABLE `t_pairing` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_pairing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_permission`
--

DROP TABLE IF EXISTS `t_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_permission` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `permission_name` varchar(100) NOT NULL COMMENT '权限名称',
  `permission_code` varchar(100) NOT NULL COMMENT '权限标识符',
  `module` varchar(50) DEFAULT NULL COMMENT '所属模块',
  `description` text COMMENT '权限描述',
  `is_deleted` tinyint unsigned DEFAULT '0' COMMENT '逻辑删除标记:0-未删除,1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_permission_code` (`permission_code`),
  KEY `idx_module` (`module`),
  KEY `idx_is_deleted` (`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_permission`
--

LOCK TABLES `t_permission` WRITE;
/*!40000 ALTER TABLE `t_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_poverty_clue`
--

DROP TABLE IF EXISTS `t_poverty_clue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_poverty_clue` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '线索ID',
  `farmer_id` bigint unsigned DEFAULT NULL COMMENT '关联农户档案ID',
  `clue_source` varchar(100) DEFAULT NULL COMMENT '线索来源',
  `risk_type` varchar(100) DEFAULT NULL COMMENT '主要风险类型',
  `risk_description` text COMMENT '风险情况描述',
  `urgency_level` tinyint unsigned DEFAULT NULL COMMENT '紧急程度:1-特急,2-紧急,3-一般',
  `discovery_date` date DEFAULT NULL COMMENT '发现日期',
  `status` varchar(50) DEFAULT '待分派' COMMENT '处理状态',
  `assignee_id` bigint unsigned DEFAULT NULL COMMENT '分派接收人ID',
  `dispatch_time` datetime DEFAULT NULL COMMENT '分派时间',
  `deadline` date DEFAULT NULL COMMENT '处理时限',
  `entry_source_description` varchar(500) DEFAULT NULL COMMENT '录入来源描述',
  `created_by` bigint unsigned DEFAULT NULL COMMENT '录入人用户ID',
  `is_deleted` tinyint unsigned DEFAULT '0' COMMENT '逻辑删除标记:0-未删除,1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_farmer_id` (`farmer_id`),
  KEY `idx_status` (`status`),
  KEY `idx_assignee_id` (`assignee_id`),
  KEY `idx_urgency_level` (`urgency_level`),
  KEY `idx_create_time` (`create_time`),
  KEY `idx_is_deleted` (`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='贫困线索表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_poverty_clue`
--

LOCK TABLES `t_poverty_clue` WRITE;
/*!40000 ALTER TABLE `t_poverty_clue` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_poverty_clue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_report_instance`
--

DROP TABLE IF EXISTS `t_report_instance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_report_instance` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '报告实例ID',
  `report_name` varchar(200) NOT NULL COMMENT '报告名称',
  `template_id` bigint unsigned NOT NULL COMMENT '使用的模板ID',
  `generation_params_json` json DEFAULT NULL COMMENT '生成参数JSON',
  `file_path` varchar(1000) DEFAULT NULL COMMENT '生成的报告文件路径',
  `created_by` bigint unsigned DEFAULT NULL COMMENT '生成人用户ID',
  `is_deleted` tinyint unsigned DEFAULT '0' COMMENT '逻辑删除标记:0-未删除,1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '生成时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_template_id` (`template_id`),
  KEY `idx_created_by` (`created_by`),
  KEY `idx_create_time` (`create_time`),
  KEY `idx_is_deleted` (`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='报告实例表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_report_instance`
--

LOCK TABLES `t_report_instance` WRITE;
/*!40000 ALTER TABLE `t_report_instance` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_report_instance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_report_template`
--

DROP TABLE IF EXISTS `t_report_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_report_template` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '模板ID',
  `template_name` varchar(200) NOT NULL COMMENT '模板名称',
  `description` text COMMENT '模板描述',
  `applicable_level` varchar(50) DEFAULT NULL COMMENT '适用用户层级',
  `report_cycle_type` varchar(50) DEFAULT NULL COMMENT '报告周期类型',
  `template_file_path` varchar(1000) DEFAULT NULL COMMENT '模板文件存储路径',
  `data_rule_json` json DEFAULT NULL COMMENT '数据源和填充规则JSON配置',
  `is_deleted` tinyint unsigned DEFAULT '0' COMMENT '逻辑删除标记:0-未删除,1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_template_name` (`template_name`),
  KEY `idx_applicable_level` (`applicable_level`),
  KEY `idx_is_deleted` (`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='报告模板表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_report_template`
--

LOCK TABLES `t_report_template` WRITE;
/*!40000 ALTER TABLE `t_report_template` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_report_template` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role`
--

DROP TABLE IF EXISTS `t_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_role` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(100) NOT NULL COMMENT '角色名称',
  `role_code` varchar(50) NOT NULL COMMENT '角色编码',
  `description` text COMMENT '角色描述',
  `is_deleted` tinyint unsigned DEFAULT '0' COMMENT '逻辑删除标记:0-未删除,1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_role_name` (`role_name`),
  UNIQUE KEY `uk_role_code` (`role_code`),
  KEY `idx_is_deleted` (`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role`
--

LOCK TABLES `t_role` WRITE;
/*!40000 ALTER TABLE `t_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role_permission`
--

DROP TABLE IF EXISTS `t_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_role_permission` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` bigint unsigned NOT NULL COMMENT '角色ID',
  `permission_id` bigint unsigned NOT NULL COMMENT '权限ID',
  `is_deleted` tinyint unsigned DEFAULT '0' COMMENT '逻辑删除标记:0-未删除,1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_role_permission` (`role_id`,`permission_id`),
  KEY `idx_role_id` (`role_id`),
  KEY `idx_permission_id` (`permission_id`),
  KEY `idx_is_deleted` (`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色权限关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role_permission`
--

LOCK TABLES `t_role_permission` WRITE;
/*!40000 ALTER TABLE `t_role_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_support_measure`
--

DROP TABLE IF EXISTS `t_support_measure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_support_measure` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '措施ID',
  `plan_id` bigint unsigned NOT NULL COMMENT '关联帮扶计划ID',
  `measure_type` varchar(100) DEFAULT NULL COMMENT '措施类型',
  `measure_content` text COMMENT '措施内容描述',
  `responsible_id` bigint unsigned DEFAULT NULL COMMENT '责任人用户ID',
  `budget_amount` decimal(15,2) DEFAULT NULL COMMENT '预算金额',
  `status` varchar(50) DEFAULT '未开始' COMMENT '执行状态',
  `is_deleted` tinyint unsigned DEFAULT '0' COMMENT '逻辑删除标记:0-未删除,1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_plan_id` (`plan_id`),
  KEY `idx_responsible_id` (`responsible_id`),
  KEY `idx_status` (`status`),
  KEY `idx_is_deleted` (`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='帮扶措施表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_support_measure`
--

LOCK TABLES `t_support_measure` WRITE;
/*!40000 ALTER TABLE `t_support_measure` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_support_measure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_support_plan`
--

DROP TABLE IF EXISTS `t_support_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_support_plan` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '计划ID',
  `farmer_id` bigint unsigned NOT NULL COMMENT '关联农户档案ID',
  `plan_name` varchar(200) NOT NULL COMMENT '计划名称',
  `main_goal` text COMMENT '主要目标',
  `start_date` date DEFAULT NULL COMMENT '计划开始日期',
  `end_date` date DEFAULT NULL COMMENT '计划结束日期',
  `status` varchar(50) DEFAULT '草稿' COMMENT '计划状态',
  `created_by` bigint unsigned DEFAULT NULL COMMENT '制定人用户ID',
  `is_deleted` tinyint unsigned DEFAULT '0' COMMENT '逻辑删除标记:0-未删除,1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_farmer_id` (`farmer_id`),
  KEY `idx_status` (`status`),
  KEY `idx_created_by` (`created_by`),
  KEY `idx_is_deleted` (`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='帮扶计划表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_support_plan`
--

LOCK TABLES `t_support_plan` WRITE;
/*!40000 ALTER TABLE `t_support_plan` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_support_plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_support_record`
--

DROP TABLE IF EXISTS `t_support_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_support_record` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '帮扶记录ID',
  `measure_id` bigint unsigned NOT NULL COMMENT '关联帮扶措施ID',
  `plan_id` bigint unsigned NOT NULL COMMENT '关联帮扶计划ID',
  `farmer_id` bigint unsigned NOT NULL COMMENT '关联农户档案ID',
  `activity_date` date NOT NULL COMMENT '帮扶活动日期',
  `activity_content` text COMMENT '活动内容纪实',
  `resource_input_description` text COMMENT '资源投入描述',
  `financial_input_amount` decimal(15,2) DEFAULT NULL COMMENT '投入资金金额',
  `progress_and_effect` text COMMENT '进展与成效',
  `problems_encountered` text COMMENT '遇到的困难和问题',
  `created_by` bigint unsigned DEFAULT NULL COMMENT '记录填报人用户ID',
  `is_deleted` tinyint unsigned DEFAULT '0' COMMENT '逻辑删除标记:0-未删除,1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_measure_id` (`measure_id`),
  KEY `idx_plan_id` (`plan_id`),
  KEY `idx_farmer_id` (`farmer_id`),
  KEY `idx_activity_date` (`activity_date`),
  KEY `idx_is_deleted` (`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='帮扶记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_support_record`
--

LOCK TABLES `t_support_record` WRITE;
/*!40000 ALTER TABLE `t_support_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_support_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_support_record_attachment`
--

DROP TABLE IF EXISTS `t_support_record_attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_support_record_attachment` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '附件ID',
  `record_id` bigint unsigned NOT NULL COMMENT '关联帮扶记录ID',
  `attachment_type` varchar(100) DEFAULT NULL COMMENT '附件类型',
  `original_file_name` varchar(500) DEFAULT NULL COMMENT '原始文件名',
  `file_path` varchar(1000) DEFAULT NULL COMMENT '文件存储路径',
  `is_deleted` tinyint unsigned DEFAULT '0' COMMENT '逻辑删除标记:0-未删除,1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_record_id` (`record_id`),
  KEY `idx_is_deleted` (`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='帮扶记录附件表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_support_record_attachment`
--

LOCK TABLES `t_support_record_attachment` WRITE;
/*!40000 ALTER TABLE `t_support_record_attachment` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_support_record_attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_system_notification`
--

DROP TABLE IF EXISTS `t_system_notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_system_notification` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '通知ID',
  `recipient_id` bigint unsigned NOT NULL COMMENT '接收人用户ID',
  `title` varchar(200) NOT NULL COMMENT '通知标题',
  `content` text COMMENT '通知内容',
  `notification_type` varchar(100) DEFAULT NULL COMMENT '通知类型',
  `business_id` bigint unsigned DEFAULT NULL COMMENT '关联业务ID',
  `business_type` varchar(100) DEFAULT NULL COMMENT '关联业务类型',
  `is_read` tinyint unsigned DEFAULT '0' COMMENT '是否已读:0-未读,1-已读',
  `is_deleted` tinyint unsigned DEFAULT '0' COMMENT '逻辑删除标记:0-未删除,1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_recipient_id` (`recipient_id`),
  KEY `idx_is_read` (`is_read`),
  KEY `idx_notification_type` (`notification_type`),
  KEY `idx_create_time` (`create_time`),
  KEY `idx_is_deleted` (`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统通知表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_system_notification`
--

LOCK TABLES `t_system_notification` WRITE;
/*!40000 ALTER TABLE `t_system_notification` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_system_notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '用户唯一ID',
  `employee_id` varchar(50) NOT NULL COMMENT '员工工号',
  `real_name` varchar(100) NOT NULL COMMENT '真实姓名',
  `phone` varchar(20) NOT NULL COMMENT '手机号码(登录账号)',
  `password` varchar(255) NOT NULL COMMENT '加密后的登录密码',
  `id_card` varchar(18) NOT NULL COMMENT '身份证号码',
  `email` varchar(100) DEFAULT NULL COMMENT '电子邮箱',
  `avatar_url` varchar(500) DEFAULT NULL COMMENT '头像URL',
  `province_code` varchar(20) DEFAULT NULL COMMENT '省级行政区划代码',
  `city_code` varchar(20) DEFAULT NULL COMMENT '市级行政区划代码',
  `county_code` varchar(20) DEFAULT NULL COMMENT '县级行政区划代码',
  `township_code` varchar(20) DEFAULT NULL COMMENT '乡级行政区划代码',
  `village_code` varchar(20) DEFAULT NULL COMMENT '村级行政区划代码',
  `department_code` varchar(50) DEFAULT NULL COMMENT '所属单位代码',
  `department_name` varchar(200) DEFAULT NULL COMMENT '所属单位名称',
  `status` tinyint unsigned DEFAULT '2' COMMENT '账户状态:0-禁用,1-启用,2-待审批,3-已锁定',
  `is_deleted` tinyint unsigned DEFAULT '0' COMMENT '逻辑删除标记:0-未删除,1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_phone` (`phone`),
  UNIQUE KEY `uk_id_card` (`id_card`),
  KEY `idx_phone` (`phone`),
  KEY `idx_department_code` (`department_code`),
  KEY `idx_status` (`status`),
  KEY `idx_is_deleted` (`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户基础信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_role`
--

DROP TABLE IF EXISTS `t_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user_role` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint unsigned NOT NULL COMMENT '用户ID',
  `role_id` bigint unsigned NOT NULL COMMENT '角色ID',
  `is_deleted` tinyint unsigned DEFAULT '0' COMMENT '逻辑删除标记:0-未删除,1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_role` (`user_id`,`role_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_role_id` (`role_id`),
  KEY `idx_is_deleted` (`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户角色关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_role`
--

LOCK TABLES `t_user_role` WRITE;
/*!40000 ALTER TABLE `t_user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_verification_attachment`
--

DROP TABLE IF EXISTS `t_verification_attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_verification_attachment` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '附件ID',
  `verification_id` bigint unsigned NOT NULL COMMENT '关联核实记录ID',
  `attachment_type` varchar(100) DEFAULT NULL COMMENT '附件类型',
  `original_file_name` varchar(500) DEFAULT NULL COMMENT '原始文件名',
  `file_path` varchar(1000) DEFAULT NULL COMMENT '文件存储路径',
  `file_size` bigint unsigned DEFAULT NULL COMMENT '文件大小(字节)',
  `uploaded_by` bigint unsigned DEFAULT NULL COMMENT '上传人用户ID',
  `is_deleted` tinyint unsigned DEFAULT '0' COMMENT '逻辑删除标记:0-未删除,1-已删除',
  `uploaded_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '上传时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_verification_id` (`verification_id`),
  KEY `idx_is_deleted` (`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='核实佐证材料表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_verification_attachment`
--

LOCK TABLES `t_verification_attachment` WRITE;
/*!40000 ALTER TABLE `t_verification_attachment` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_verification_attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_visit_plan`
--

DROP TABLE IF EXISTS `t_visit_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_visit_plan` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '走访计划ID',
  `plan_name` varchar(200) NOT NULL COMMENT '计划名称',
  `planner_id` bigint unsigned NOT NULL COMMENT '计划制定人用户ID',
  `start_date` date NOT NULL COMMENT '计划开始日期',
  `end_date` date NOT NULL COMMENT '计划结束日期',
  `description` text COMMENT '计划描述和走访重点',
  `status` varchar(50) DEFAULT '待执行' COMMENT '计划状态',
  `is_deleted` tinyint unsigned DEFAULT '0' COMMENT '逻辑删除标记:0-未删除,1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_planner_id` (`planner_id`),
  KEY `idx_status` (`status`),
  KEY `idx_is_deleted` (`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='走访计划表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_visit_plan`
--

LOCK TABLES `t_visit_plan` WRITE;
/*!40000 ALTER TABLE `t_visit_plan` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_visit_plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_visit_plan_target`
--

DROP TABLE IF EXISTS `t_visit_plan_target`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_visit_plan_target` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '关联记录ID',
  `plan_id` bigint unsigned NOT NULL COMMENT '走访计划ID',
  `farmer_id` bigint unsigned NOT NULL COMMENT '目标农户档案ID',
  `is_deleted` tinyint unsigned DEFAULT '0' COMMENT '逻辑删除标记:0-未删除,1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_plan_farmer` (`plan_id`,`farmer_id`),
  KEY `idx_plan_id` (`plan_id`),
  KEY `idx_farmer_id` (`farmer_id`),
  KEY `idx_is_deleted` (`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='走访计划目标表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_visit_plan_target`
--

LOCK TABLES `t_visit_plan_target` WRITE;
/*!40000 ALTER TABLE `t_visit_plan_target` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_visit_plan_target` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_visit_record`
--

DROP TABLE IF EXISTS `t_visit_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_visit_record` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '走访记录ID',
  `plan_id` bigint unsigned DEFAULT NULL COMMENT '关联走访计划ID',
  `farmer_id` bigint unsigned NOT NULL COMMENT '被走访农户档案ID',
  `visitor_id` bigint unsigned NOT NULL COMMENT '执行走访的用户ID',
  `visit_date` date NOT NULL COMMENT '实际走访日期',
  `visit_content` text COMMENT '走访内容纪要',
  `problems_found` text COMMENT '发现的新问题',
  `farmer_requests` text COMMENT '农户提出的困难或诉求',
  `follow_up_action` text COMMENT '后续处理建议',
  `status` varchar(50) DEFAULT '待审核' COMMENT '记录状态',
  `is_deleted` tinyint unsigned DEFAULT '0' COMMENT '逻辑删除标记:0-未删除,1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_farmer_id` (`farmer_id`),
  KEY `idx_visitor_id` (`visitor_id`),
  KEY `idx_visit_date` (`visit_date`),
  KEY `idx_status` (`status`),
  KEY `idx_is_deleted` (`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='走访记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_visit_record`
--

LOCK TABLES `t_visit_record` WRITE;
/*!40000 ALTER TABLE `t_visit_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_visit_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_warning_record`
--

DROP TABLE IF EXISTS `t_warning_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_warning_record` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '预警记录ID',
  `high_risk_id` bigint unsigned NOT NULL COMMENT '高风险户档案ID',
  `farmer_id` bigint unsigned NOT NULL COMMENT '农户档案ID',
  `trigger_rule` varchar(500) DEFAULT NULL COMMENT '触发预警的规则描述',
  `warning_level` varchar(50) DEFAULT NULL COMMENT '预警级别',
  `warning_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '预警触发时间',
  `status` tinyint unsigned DEFAULT '1' COMMENT '处理状态:1-待处理,2-处理中,3-已闭环',
  `is_deleted` tinyint unsigned DEFAULT '0' COMMENT '逻辑删除标记:0-未删除,1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_high_risk_id` (`high_risk_id`),
  KEY `idx_farmer_id` (`farmer_id`),
  KEY `idx_warning_time` (`warning_time`),
  KEY `idx_status` (`status`),
  KEY `idx_is_deleted` (`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='风险预警记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_warning_record`
--

LOCK TABLES `t_warning_record` WRITE;
/*!40000 ALTER TABLE `t_warning_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_warning_record` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-12  9:35:03
