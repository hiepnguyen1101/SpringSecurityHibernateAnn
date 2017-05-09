--“mkyong” and password “123456”.
--http://www.mkyong.com/spring-security/spring-security-hibernate-annotation-example/
--http://docs.jboss.org/hibernate/core/3.3/reference/en/html/querysql.html

CREATE  TABLE users (
  username VARCHAR(45) NOT NULL ,
  password VARCHAR(60) NOT NULL ,
  enabled SMALLINT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (username));

CREATE TABLE user_roles (
  user_role_id int NOT NULL IDENTITY,
  username varchar(45) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  CONSTRAINT uni_username_role UNIQUE (role,username)
 ,
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username));

CREATE INDEX fk_username_idx ON user_roles (username);

INSERT INTO users(username,password,enabled)
VALUES ('mkyong','$2a$10$04TVADrR6/SPLBjsK0N30.Jf5fNjBugSACeGv1S69dZALR7lSov0y', true);
INSERT INTO users(username,password,enabled)
VALUES ('alex','$2a$10$04TVADrR6/SPLBjsK0N30.Jf5fNjBugSACeGv1S69dZALR7lSov0y', true);

INSERT INTO user_roles (username, role)
VALUES ('mkyong', 'ROLE_USER');
INSERT INTO user_roles (username, role)
VALUES ('mkyong', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role)
VALUES ('alex', 'ROLE_USER');