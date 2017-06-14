drop database if EXISTS CPU_DB;

create database CPU_DB CHARACTER SET utf8 COLLATE utf8_general_ci;

use CPU_DB;

CREATE TABLE USERS (
  id VARCHAR(11) PRIMARY KEY,
  name NVARCHAR(255) not null,
  mobile varchar(64),
  birthDate DATE not null,
  email VARCHAR(64) not null,
  password VARCHAR(255) not null,
  isActive BOOL not NUll
);

CREATE TABLE PERMISSION(
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(64) not NULL
);

create TABLE USER_PERMISSION(
  userId VARCHAR(11) NOT NULL ,
  permissionId INT not NULL ,
  UNIQUE (userId, permissionId),
  FOREIGN KEY (userId) REFERENCES USERS(id),
  FOREIGN KEY (permissionId) REFERENCES PERMISSION(id)
);

create TABLE HOSPITAL(
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(256) not null,
  address VARCHAR(128) not null,
  phoneNumber VARCHAR(64),
  email VARBINARY(64)
);

CREATE TABLE HOSPITAL_USERS(
  userId VARCHAR(11) NOT NULL ,
  hospitalId  INt NOT NULL ,
  UNIQUE (userId, hospitalId),
  FOREIGN KEY (userId) REFERENCES USERS(id),
  FOREIGN KEY (hospitalId) REFERENCES HOSPITAL(id)
);





