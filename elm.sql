/*
 Navicat Premium Data Transfer

 Source Server         : jin
 Source Server Type    : MySQL
 Source Server Version : 80200
 Source Host           : 43.139.152.229:3307
 Source Schema         : elm

 Target Server Type    : MySQL
 Target Server Version : 80200
 File Encoding         : 65001

 Date: 04/01/2025 22:25:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for business
-- ----------------------------
DROP TABLE IF EXISTS `business`;
CREATE TABLE `business`  (
  `businessid` int NOT NULL AUTO_INCREMENT,
  `businessname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `businessaddress` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `businessexplain` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `businessimg` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `ordertypeid` int NULL DEFAULT NULL,
  `starprice` decimal(10, 2) NULL DEFAULT 0.00,
  `deliveryprice` decimal(10, 2) NULL DEFAULT 0.00,
  `remarks` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`businessid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of business
-- ----------------------------
INSERT INTO `business` VALUES (1, '11万家饺子（软件园E18店）', '软件园E18店', '各种饺子炒菜', 'assets/img/sj01.png', 1, 15.00, 3.00, '');
INSERT INTO `business` VALUES (2, '小锅饭豆腐馆（全运店）', '全运店', '特色美食', 'assets/img/sj02.png', 1, 15.00, 3.00, '');
INSERT INTO `business` VALUES (3, '麦当劳麦乐送（全运路店）', '全运路店', '汉堡薯条', 'assets/img/sj03.png', 4, 15.00, 3.00, '');
INSERT INTO `business` VALUES (4, '米村拌饭（浑南店）', '浑南店', '各种炒菜拌饭', 'assets/img/sj04.png', 1, 15.00, 3.00, '');
INSERT INTO `business` VALUES (5, '申记串道（中海康城店）', '中海康城店', '烤串炸串', 'assets/img/sj05.png', 1, 15.00, 3.00, '');
INSERT INTO `business` VALUES (6, '半亩良田排骨米饭', '软件园店', '排骨米饭套餐', 'assets/img/sj06.png', 1, 15.00, 3.00, '');
INSERT INTO `business` VALUES (7, '茶兮鲜果音频（国际软件园店）', '国际软件园店', '甜品饮品', 'assets/img/sj07.png', 4, 15.00, 3.00, '');
INSERT INTO `business` VALUES (8, '唯一水果捞（软件园E18店）', '软件园E18店', '新鲜水果', 'assets/img/sj08.png', 5, 15.00, 3.00, '');
INSERT INTO `business` VALUES (9, '满园春饼（全运路店）', '全运路店', '各种春饼', 'assets/img/sj09.png', 1, 15.00, 3.00, '');

-- ----------------------------
-- Table structure for business_type
-- ----------------------------
DROP TABLE IF EXISTS `business_type`;
CREATE TABLE `business_type`  (
  `typeid` int NOT NULL,
  `typename` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`typeid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of business_type
-- ----------------------------

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`  (
  `cartid` int NOT NULL AUTO_INCREMENT,
  `foodid` int NULL DEFAULT NULL,
  `businessid` int NULL DEFAULT NULL,
  `userid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `quantity` int NULL DEFAULT 1,
  PRIMARY KEY (`cartid`) USING BTREE,
  INDEX `foodid`(`foodid` ASC) USING BTREE,
  INDEX `businessid`(`businessid` ASC) USING BTREE,
  INDEX `userid`(`userid` ASC) USING BTREE,
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`foodid`) REFERENCES `food` (`foodid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`businessid`) REFERENCES `business` (`businessid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `cart_ibfk_3` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cart
-- ----------------------------

-- ----------------------------
-- Table structure for delivery_address
-- ----------------------------
DROP TABLE IF EXISTS `delivery_address`;
CREATE TABLE `delivery_address`  (
  `daid` int NOT NULL AUTO_INCREMENT,
  `contactname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `contactsex` int NULL DEFAULT 1,
  `contacttel` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `userid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`daid`) USING BTREE,
  INDEX `userid`(`userid` ASC) USING BTREE,
  CONSTRAINT `delivery_address_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of delivery_address
-- ----------------------------

-- ----------------------------
-- Table structure for food
-- ----------------------------
DROP TABLE IF EXISTS `food`;
CREATE TABLE `food`  (
  `foodid` int NOT NULL AUTO_INCREMENT,
  `foodname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `foodexplain` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `foodimg` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `foodprice` decimal(10, 2) NOT NULL,
  `businessid` int NULL DEFAULT NULL,
  `remarks` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `categoryid` int NULL DEFAULT NULL,
  PRIMARY KEY (`foodid`) USING BTREE,
  INDEX `businessid`(`businessid` ASC) USING BTREE,
  CONSTRAINT `food_ibfk_1` FOREIGN KEY (`businessid`) REFERENCES `business` (`businessid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of food
-- ----------------------------
INSERT INTO `food` VALUES (1, '纯肉鲜肉（水饺）', '新鲜猪肉', 'assets/img/sp01.png', 15.00, 1, '', 1);
INSERT INTO `food` VALUES (2, '玉米鲜肉（水饺）', '玉米加新鲜猪肉', 'assets/img/sp02.png', 16.00, 1, '', 1);
INSERT INTO `food` VALUES (3, '虾仁三鲜（水饺）', '虾仁加新鲜猪肉', 'assets/img/sp03.png', 18.00, 1, '', 1);
INSERT INTO `food` VALUES (4, '素三鲜（蒸饺）', '素馅饺子', 'assets/img/sp04.png', 12.00, 1, '', 2);
INSERT INTO `food` VALUES (5, '角瓜鸡蛋（蒸饺）', '角瓜鸡蛋', 'assets/img/sp05.png', 16.00, 1, '', 2);
INSERT INTO `food` VALUES (6, '小白菜肉（水饺）', '小白菜加新鲜猪肉', 'assets/img/sp06.png', 17.00, 1, '', 1);
INSERT INTO `food` VALUES (7, '芹菜牛肉（水饺）', '芹菜加新鲜牛肉', 'assets/img/sp07.png', 15.00, 1, '', 1);
INSERT INTO `food` VALUES (8, '韭菜鸡蛋（蒸饺）', '韭菜鸡蛋', 'assets/img/sp01.png', 14.00, 1, '', 2);
INSERT INTO `food` VALUES (9, '青椒土豆丝', '爽口开胃', 'assets/img/sp02.png', 12.00, 1, '', 3);
INSERT INTO `food` VALUES (10, '地三鲜', '茄子土豆青椒', 'assets/img/sp03.png', 18.00, 1, '', 3);
INSERT INTO `food` VALUES (11, '酱香饼', '传统风味', 'assets/img/sp04.png', 8.00, 1, '', 4);
INSERT INTO `food` VALUES (12, '葱油饼', '香酥可口', 'assets/img/sp05.png', 8.00, 1, '', 4);

-- ----------------------------
-- Table structure for food_category
-- ----------------------------
DROP TABLE IF EXISTS `food_category`;
CREATE TABLE `food_category`  (
  `categoryid` int NOT NULL,
  `categoryname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `categoryimg` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`categoryid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of food_category
-- ----------------------------
INSERT INTO `food_category` VALUES (1, '美食', 'assets/img/dcfl01.png');
INSERT INTO `food_category` VALUES (2, '早餐', 'assets/img/dcfl02.png');
INSERT INTO `food_category` VALUES (3, '跑腿代购', 'assets/img/dcfl03.png');
INSERT INTO `food_category` VALUES (4, '汉堡披萨', 'assets/img/dcfl04.png');
INSERT INTO `food_category` VALUES (5, '甜品饮品', 'assets/img/dcfl05.png');
INSERT INTO `food_category` VALUES (6, '速食简餐', 'assets/img/dcfl06.png');
INSERT INTO `food_category` VALUES (7, '地方小吃', 'assets/img/dcfl07.png');
INSERT INTO `food_category` VALUES (8, '米粉面馆', 'assets/img/dcfl08.png');
INSERT INTO `food_category` VALUES (9, '包子粥铺', 'assets/img/dcfl09.png');
INSERT INTO `food_category` VALUES (10, '炸鸡炸串', 'assets/img/dcfl10.png');

-- ----------------------------
-- Table structure for orderdetailet
-- ----------------------------
DROP TABLE IF EXISTS `orderdetailet`;
CREATE TABLE `orderdetailet`  (
  `odid` int NOT NULL AUTO_INCREMENT,
  `orderid` int NULL DEFAULT NULL,
  `foodid` int NULL DEFAULT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`odid`) USING BTREE,
  INDEX `orderid`(`orderid` ASC) USING BTREE,
  INDEX `foodid`(`foodid` ASC) USING BTREE,
  CONSTRAINT `orderdetailet_ibfk_1` FOREIGN KEY (`orderid`) REFERENCES `orders` (`orderid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `orderdetailet_ibfk_2` FOREIGN KEY (`foodid`) REFERENCES `food` (`foodid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orderdetailet
-- ----------------------------

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `orderid` int NOT NULL AUTO_INCREMENT,
  `userid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `businessid` int NULL DEFAULT NULL,
  `orderdate` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `ordertotal` decimal(10, 2) NOT NULL,
  `daid` int NULL DEFAULT NULL,
  `orderstate` int NULL DEFAULT 0,
  PRIMARY KEY (`orderid`) USING BTREE,
  INDEX `daid`(`daid` ASC) USING BTREE,
  INDEX `idx_business_orders`(`businessid` ASC) USING BTREE,
  INDEX `idx_order_state`(`orderstate` ASC) USING BTREE,
  INDEX `idx_user_orders`(`userid` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (6, '13800138000', 1, '2025-01-04 20:43:42', 33.00, NULL, 1);
INSERT INTO `orders` VALUES (8, '13800138000', 1, '2025-01-04 20:46:32', 33.00, NULL, 0);
INSERT INTO `orders` VALUES (9, '13800138000', 1, '2025-01-04 20:48:18', 33.00, NULL, 0);
INSERT INTO `orders` VALUES (10, '13800138000', 1, '2025-01-04 20:48:50', 33.00, NULL, 0);
INSERT INTO `orders` VALUES (11, '13800138000', 1, '2025-01-04 20:49:40', 52.00, NULL, 0);
INSERT INTO `orders` VALUES (13, '123456', 1, '2025-01-04 21:26:22', 52.00, NULL, 1);
INSERT INTO `orders` VALUES (14, '123456', 1, '2025-01-04 21:26:48', 52.00, NULL, 0);
INSERT INTO `orders` VALUES (15, '123456', 1, '2025-01-04 21:28:31', 52.00, NULL, 0);
INSERT INTO `orders` VALUES (16, '1234567', 1, '2025-01-04 22:08:41', 34.00, NULL, 0);
INSERT INTO `orders` VALUES (17, '123', 1, '2025-01-04 22:12:27', 36.00, NULL, 0);
INSERT INTO `orders` VALUES (18, '12345', 1, '2025-01-04 22:22:53', 37.00, NULL, 0);
INSERT INTO `orders` VALUES (19, '12345', 1, '2025-01-04 22:23:11', 18.00, NULL, 0);

-- ----------------------------
-- Table structure for test_table
-- ----------------------------
DROP TABLE IF EXISTS `test_table`;
CREATE TABLE `test_table`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `age` int NULL DEFAULT NULL COMMENT '年龄',
  `score` decimal(10, 2) NULL DEFAULT NULL COMMENT '分数',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '测试表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test_table
-- ----------------------------
INSERT INTO `test_table` VALUES (1, '测试用户1', 25, 98.50, '2025-01-04 15:53:49', '2025-01-04 15:53:49');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userid` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `usersex` int NULL DEFAULT 1,
  `userimg` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `deltag` int NULL DEFAULT 0,
  PRIMARY KEY (`userid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('123', '123', '123465', 0, NULL, 1);
INSERT INTO `user` VALUES ('1234', '1234', '123456', 0, NULL, 1);
INSERT INTO `user` VALUES ('12345', '12345', '123456', 0, NULL, 1);
INSERT INTO `user` VALUES ('123456', '123456', '123456', 0, NULL, 1);
INSERT INTO `user` VALUES ('1234567', '1234567', '123456', 0, NULL, 1);
INSERT INTO `user` VALUES ('13800138000', '测试用户', '123456', 1, 'default.jpg', 1);

SET FOREIGN_KEY_CHECKS = 1;
