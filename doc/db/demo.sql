CREATE database if NOT EXISTS `demo` default character set utf8 collate utf8_general_ci;
use `demo`;


-- ----------------------------
-- Table structure for customer
-- ----------------------------
CREATE TABLE `customer`  (
  `id` varchar(32) NOT NULL,
  `account_no` varchar(20) NOT NULL,
  `pwd` varchar(32) NOT NULL,
  `register_date` datetime(0) NOT NULL,
  `salt` varchar(32) NOT NULL,
  `token` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_6eleo712xf7wjlv9h5fuwel7e`(`account_no`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for customer_profile
-- ----------------------------
CREATE TABLE `customer_profile`  (
  `age` int(2) NOT NULL,
  `gender` varchar(5) NOT NULL,
  `name` varchar(50) NOT NULL,
  `customer_id` varchar(32) NOT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for customer_tag
-- ----------------------------
CREATE TABLE `customer_tag`  (
  `customer_id` varchar(32) NOT NULL,
  `tag_id` varchar(32) NOT NULL,
  INDEX `FKkvq0vje3cl9oquy8smyvewr96`(`tag_id`),
  INDEX `FK7gxcvyu6kcm5dm5nyitk8x8cu`(`customer_id`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for favorites
-- ----------------------------
CREATE TABLE `favorites`  (
  `id` varchar(32) NOT NULL,
  `customer_id` varchar(255) NULL DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKoyt0sxepwr5sys7a8w334epdg`(`customer_id`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tag
-- ----------------------------
CREATE TABLE `tag`  (
  `id` varchar(32) NOT NULL,
  `name` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8;
