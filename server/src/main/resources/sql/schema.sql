CREATE SCHEMA IF NOT EXISTS CITIES;

DROP TABLE IF EXISTS our_users;

CREATE TABLE our_users (
  id_user         INT(11)     NOT NULL AUTO_INCREMENT,
  login           VARCHAR(40) NOT NULL,
  email           VARCHAR(40)          DEFAULT NULL,
  encodedPassword VARCHAR(60)          DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE (login),
  UNIQUE (email)
);

