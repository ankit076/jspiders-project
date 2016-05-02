/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.0.19-nt : Database - omg
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`omg` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `omg`;

/*Table structure for table `donor_table` */

DROP TABLE IF EXISTS `donor_table`;

CREATE TABLE `donor_table` (
  `id` int(11) NOT NULL auto_increment,
  `donordate` date default NULL,
  `user_id` int(11) default NULL,
  `bloodgroup` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKC6B08B5397E73D9` (`user_id`),
  CONSTRAINT `FKC6B08B5397E73D9` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `donor_table` */

insert  into `donor_table`(`id`,`donordate`,`user_id`,`bloodgroup`) values (1,'2016-04-25',1,NULL),(2,'2016-04-25',1,'a naegtive'),(3,'2011-04-25',NULL,'negtative'),(4,'2011-04-25',NULL,'negtative'),(5,'2011-04-25',1,'negtative');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL auto_increment,
  `bloodgroup` varchar(255) default NULL,
  `contactnumber` varchar(255) default NULL,
  `email` varchar(255) NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `name` varchar(255) default NULL,
  `password` varchar(255) default NULL,
  `sex` varchar(255) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`id`,`bloodgroup`,`contactnumber`,`email`,`latitude`,`longitude`,`name`,`password`,`sex`) values (1,NULL,NULL,'ravi@gamil.com',0,0,NULL,'negtative',NULL),(2,'postive','687876','adam@gmail.com',0,0,'adam','adam','male'),(3,'postive','687876','saba@gamil.com',0,0,'adam','adam','male'),(4,'postive','687876','shruthi@gamil.com',0,0,'adam','adam','male'),(5,'postive','687876','fa@gamil.com',0,0,'adam','werttttt','male'),(6,NULL,NULL,'balvinder@gamil.com',0,0,'balvinder','negtative','male');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
