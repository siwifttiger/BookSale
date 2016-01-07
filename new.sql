drop database bookmanage;
CREATE SCHEMA `bookmanage` ;
use bookmanage;
CREATE TABLE bookmanage.bookinfo (
  `isbn` VARCHAR(45) NOT NULL,
  `bookname` VARCHAR(45) NULL,
  `author` VARCHAR(45) NULL,
  `publish` VARCHAR(45) NULL,
  `year` VARCHAR(10) NULL,
  `bookremark` VARCHAR(100) NULL,
  PRIMARY KEY (`isbn`))ENGINE=InnoDB;

  
  CREATE TABLE bookmanage.supplyinfo(
  supplyid VARCHAR(45) NOT NULL,
  `supplyname` VARCHAR(45) NULL,
  `supplyaddress` VARCHAR(45) NULL,
  `supplytelephone` VARCHAR(45) NULL,
  `supplyaccount` VARCHAR(45) NULL,
  `supplycontact` VARCHAR(45) NULL,
  `supplycontacttel` VARCHAR(45) NULL,
  `supplyemail` VARCHAR(45) NULL,
  `supplyremark` VARCHAR(100) NULL,
  PRIMARY KEY (`supplyid`))ENGINE=InnoDB;

  
  CREATE TABLE `bookmanage`.`customerinfo` (
  `customerid` VARCHAR(45) NOT NULL,
  `customername` VARCHAR(45) NULL,
  `customeraddress` VARCHAR(45) NULL,
  `customertelephone` VARCHAR(45) NULL,
  `customeraccount` VARCHAR(45) NULL,
  `customercontact` VARCHAR(45) NULL,
  `customercontacttel` VARCHAR(45) NULL,
  `deliveryaddress` VARCHAR(45) NULL,
  `customeremail` VARCHAR(45) NULL,
  `customerremark` VARCHAR(100) NULL,
  PRIMARY KEY (`customerid`))ENGINE=InnoDB;

  
CREATE TABLE `bookmanage`.`userlist` (
  `name` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NULL,
  `pass` VARCHAR(45) NULL,
  `quan` VARCHAR(2) NULL,
  PRIMARY KEY (`name`))ENGINE=InnoDB;
  
  INSERT INTO `bookmanage`.`userlist` (`name`, `username`, `pass`, `quan`) VALUES ('sun', 'admin', 'admin', '1');
  
  INSERT INTO `bookmanage`.`customerinfo` (`customerid`, `customername`, `customeraddress`, `customertelephone`, `customeraccount`, `customercontact`, `customercontacttel`, `deliveryaddress`, `customeremail`, `customerremark`) VALUES ('0000', '散户', '无', '无', '无', '无', '无', '无', '无', '默认添加');

  
  CREATE TABLE `bookmanage`.`quotelist` (
  `isbn` VARCHAR(45) NOT NULL,
  `supplyid` VARCHAR(45) NOT NULL,
  `supplyprice` INT NULL,
  `supplyquantity` INT NULL,
  PRIMARY KEY (`isbn`, `supplyid`),
  foreign key(isbn) references bookinfo(isbn),
  foreign key(supplyid) references supplyinfo(supplyid)
   on update cascade
  on delete cascade)ENGINE=InnoDB;

  
  CREATE TABLE `bookmanage`.`supplylist` (
  `supplylistid` INT NOT NULL AUTO_INCREMENT,
  `supplyid` VARCHAR(45) NULL,
  `isbn` VARCHAR(45) NULL,
  `supplyprice` INT NULL,
  `neededquantity` INT NULL,
  `date` DATE NULL,
  PRIMARY KEY (`supplylistid`),
  foreign key (supplyid) references supplyinfo(supplyid),
  foreign key (isbn) references bookinfo(isbn)
  on update cascade
  on delete cascade)ENGINE=InnoDB;
  
  
  CREATE TABLE `bookmanage`.`inventory` (
  `isbn` VARCHAR(45) NOT NULL,
  `totalquantity` INT NULL,
  PRIMARY KEY (`isbn`),
  foreign key(isbn) references bookinfo(isbn)
   on update cascade
  on delete cascade)ENGINE=InnoDB;

  
  CREATE TABLE `bookmanage`.`salelist` (
  `saleid` INT NOT NULL AUTO_INCREMENT,
  `customerid` VARCHAR(45) NULL,
  `isbn` VARCHAR(45) NULL,
  `saleprice` INT NULL,
  `salequantity` INT NULL,
  `date` DATE NULL,
  PRIMARY KEY (`saleid`),
  foreign key (isbn) references bookinfo(isbn),
  foreign key (customerid) references customerinfo(customerid)
  on update cascade
  on delete cascade)ENGINE=InnoDB;
  
  CREATE TABLE `bookmanage`.`returnlist` (
  `returnid` INT NOT NULL AUTO_INCREMENT,
  `customerid` VARCHAR(45) NULL,
  `isbn` VARCHAR(45) NULL,
  `returnprice` INT NULL,
  `returnquantity` INT NULL,
  `date` VARCHAR(45) NULL,
  PRIMARY KEY (`returnid`),
  foreign key (isbn) references bookinfo(isbn),
  foreign key (customerid) references customerinfo(customerid)
  on update cascade
  on delete cascade)ENGINE=InnoDB;