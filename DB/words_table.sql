/*
 Navicat Premium Dump SQL

 Source Server         : MySQL-CentOS7
 Source Server Type    : MySQL
 Source Server Version : 80402 (8.4.2)
 Source Host           : 192.168.110.129:3306
 Source Schema         : wordbook

 Target Server Type    : MySQL
 Target Server Version : 80402 (8.4.2)
 File Encoding         : 65001

 Date: 30/11/2024 22:21:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for words_table
-- ----------------------------
DROP TABLE IF EXISTS `words_table`;
CREATE TABLE `words_table`  (
  `word_id` int NOT NULL AUTO_INCREMENT COMMENT '单词 ID',
  `word_user_id` int NOT NULL COMMENT '对应的用户 ID',
  `word` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '单词',
  `translation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '翻译',
  `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`word_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
