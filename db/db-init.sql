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


##########################################################################################
# Forum Tables
##########################################################################################

DROP TABLE IF EXISTS `Topic`;
CREATE TABLE `Topic` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `meta_info` TEXT NOT NULL,
  `title` TEXT NOT NULL,
  `content` TEXT NOT NULL,
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY(`id`)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;


DROP TABLE IF EXISTS `Post`;
CREATE TABLE `Post` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `topic_id` INT(10) NOT NULL,
  `meta_info` TEXT NOT NULL,
  `title` TEXT NOT NULL,
  `content` TEXT NOT NULL,
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

CREATE INDEX PostIndex ON Post(topic_id);

DROP TABLE IF EXISTS `Feed`;
CREATE TABLE `Feed` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `post_id` INT(10) NOT NULL,
  `meta_info` TEXT NOT NULL,
  `content` TEXT NOT NULL,
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET = utf8;

CREATE INDEX FeedIndex ON Feed(post_id);