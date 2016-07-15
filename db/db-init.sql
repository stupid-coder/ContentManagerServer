SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `Content`;

CREATE TABLE `Content` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `meta_info` TEXT,
  `title` TEXT NOT NULL,
  `content` TEXT NOT NULL,
  `type` VARCHAR(10) NOT NULL DEFAULT "",
  `status` VARCHAR(10) NOT NULL DEFAULT "0",
  `create_time` DATETIME NOT NULL,
  `modify_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

CREATE INDEX ContentIndex ON Content(id,type,status);