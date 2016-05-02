/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.0.19-nt : Database - temple
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`temple` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `temple`;

/*Table structure for table `event_information` */

DROP TABLE IF EXISTS `event_information`;

CREATE TABLE `event_information` (
  `id` int(11) NOT NULL auto_increment,
  `eventdate` date default NULL,
  `name` varchar(255) default NULL,
  `temple_id` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKB0C02F07332308D9` (`temple_id`),
  CONSTRAINT `FKB0C02F07332308D9` FOREIGN KEY (`temple_id`) REFERENCES `temple_information` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `event_information` */

insert  into `event_information`(`id`,`eventdate`,`name`,`temple_id`) values (1,'2016-05-18','b',1),(2,'2016-05-25','v',2),(3,'2016-05-18','ywerqrrrerr',1);

/*Table structure for table `seva_information` */

DROP TABLE IF EXISTS `seva_information`;

CREATE TABLE `seva_information` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) default NULL,
  `sevadate` date default NULL,
  `temple_id` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK943AD06A332308D9` (`temple_id`),
  CONSTRAINT `FK943AD06A332308D9` FOREIGN KEY (`temple_id`) REFERENCES `temple_information` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `seva_information` */

insert  into `seva_information`(`id`,`name`,`sevadate`,`temple_id`) values (1,'a','2016-05-03',1),(2,'b','2016-05-04',2),(3,'ereeeerrrrr','2016-05-03',1),(4,'ereeeerrrrr','2016-05-03',2);

/*Table structure for table `temple_information` */

DROP TABLE IF EXISTS `temple_information`;

CREATE TABLE `temple_information` (
  `id` int(11) NOT NULL auto_increment,
  `address` varchar(255) default NULL,
  `contactnumber` varchar(255) default NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `name` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `temple_information` */

insert  into `temple_information`(`id`,`address`,`contactnumber`,`latitude`,`longitude`,`name`) values (1,NULL,NULL,0,0,NULL),(2,NULL,NULL,0,0,NULL),(3,'bangalore','65466576867',0,0,'Shravanabelagola'),(4,'bangalore','34234523',0,0,'Sringeri'),(5,'bangalore','3231412',0,0,'Gokarna');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL auto_increment,
  `contactnumber` varchar(255) default NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) default NULL,
  `password` varchar(255) default NULL,
  `sex` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`id`,`contactnumber`,`email`,`name`,`password`,`sex`) values (1,'223243','admin@gmail.com','admin','admin','male'),(2,'34325465','user@gmail.com','user','user','male');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
