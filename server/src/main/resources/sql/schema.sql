DROP TABLE IF EXISTS USERS;

CREATE TABLE users (
  id              INT(11)     NOT NULL AUTO_INCREMENT,
  login           VARCHAR(40) NOT NULL,
  email           VARCHAR(40)          DEFAULT NULL,
  encodedPassword VARCHAR(60)          DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE (login),
  UNIQUE (email)
);