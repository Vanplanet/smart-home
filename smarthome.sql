/*
 Navicat Premium Data Transfer

 Source Server         : Nemo
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : localhost:3306
 Source Schema         : smarthome

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 04/05/2021 20:39:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for air_conditioner
-- ----------------------------
DROP TABLE IF EXISTS `air_conditioner`;
CREATE TABLE `air_conditioner`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `temperature` int(10) NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `speed` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of air_conditioner
-- ----------------------------
INSERT INTO `air_conditioner` VALUES ('1', 27, '制热', 5);
INSERT INTO `air_conditioner` VALUES ('2', 22, '通风', 11);
INSERT INTO `air_conditioner` VALUES ('3', 24, '通风', 14);
INSERT INTO `air_conditioner` VALUES ('4', 26, '抽湿', 12);
INSERT INTO `air_conditioner` VALUES ('5', 27, '自动', 8);

-- ----------------------------
-- Table structure for light
-- ----------------------------
DROP TABLE IF EXISTS `light`;
CREATE TABLE `light`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `bright` int(10) NULL DEFAULT NULL,
  `temperature` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of light
-- ----------------------------
INSERT INTO `light` VALUES ('101', 20, 200);
INSERT INTO `light` VALUES ('102', 100, 120);
INSERT INTO `light` VALUES ('103', 80, 999);
INSERT INTO `light` VALUES ('104', 110, 130);

SET FOREIGN_KEY_CHECKS = 1;
