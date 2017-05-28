CREATE SCHEMA IF NOT EXISTS CITIES;

DROP TABLE IF EXISTS our_users;
DROP TABLE IF EXISTS our_users_role;
DROP TABLE IF EXISTS role;

CREATE TABLE our_users (
  id_user          INT(11)     NOT NULL AUTO_INCREMENT,
  login            VARCHAR(40) NOT NULL,
  email            VARCHAR(40)          DEFAULT NULL,
  encoded_password VARCHAR(60)          DEFAULT NULL,
  PRIMARY KEY (id_user),
  UNIQUE (login),
  UNIQUE (email)
);

CREATE TABLE role (
  id_role   INT(11)     NOT NULL AUTO_INCREMENT,
  role_name VARCHAR(40) NOT NULL,
  UNIQUE (role_name)
);

CREATE TABLE our_users_role (
  id_user INT(11),
  id_role INT(11)
);