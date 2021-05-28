DROP TABLE IF EXISTS oauth_access_token;
CREATE TABLE oauth_access_token (
  token_id varchar(256) DEFAULT NULL,
  token mediumblob,
  authentication_id varchar(256) NOT NULL,
  user_name varchar(256) DEFAULT NULL,
  client_id varchar(256) DEFAULT NULL,
  authentication mediumblob,
  refresh_token varchar(256) DEFAULT NULL,
  PRIMARY KEY (authentication_id)
);

--
-- Table structure for table `oauth_approvals`
--

DROP TABLE IF EXISTS oauth_approvals;
CREATE TABLE oauth_approvals (
  userId varchar(256) DEFAULT NULL,
  clientId varchar(256) DEFAULT NULL,
  scope varchar(256) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  expiresAt timestamp NULL DEFAULT NULL,
  lastModifiedAt timestamp NULL DEFAULT NULL
);
DROP TABLE IF EXISTS oauth_client_details;
CREATE TABLE oauth_client_details (
  client_id varchar(255) NOT NULL,
  client_secret varchar(255) NOT NULL,
  web_server_redirect_uri varchar(2048) DEFAULT NULL,
  scope varchar(255) DEFAULT NULL,
  access_token_validity int DEFAULT NULL,
  refresh_token_validity int DEFAULT NULL,
  resource_ids varchar(1024) DEFAULT NULL,
  authorized_grant_types varchar(1024) DEFAULT NULL,
  authorities varchar(1024) DEFAULT NULL,
  additional_information varchar(4096) DEFAULT NULL,
  autoapprove varchar(255) DEFAULT NULL,
  PRIMARY KEY (client_id)
);

DROP TABLE IF EXISTS oauth_client_token;
CREATE TABLE oauth_client_token (
  token_id varchar(256) DEFAULT NULL,
  token mediumblob,
  authentication_id varchar(256) NOT NULL,
  user_name varchar(256) DEFAULT NULL,
  client_id varchar(256) DEFAULT NULL,
  PRIMARY KEY (authentication_id)
);

DROP TABLE IF EXISTS oauth_code;
CREATE TABLE oauth_code (
  `code` varchar(256) DEFAULT NULL,
  authentication mediumblob
);
DROP TABLE IF EXISTS oauth_refresh_token;
CREATE TABLE oauth_refresh_token (
  token_id varchar(256) DEFAULT NULL,
  token mediumblob,
  authentication mediumblob
);

DROP TABLE IF EXISTS permission;
CREATE TABLE permission (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(512) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY `name` (`name`)
);

DROP TABLE IF EXISTS permission_role;
CREATE TABLE permission_role (
  `permission_id` int DEFAULT NULL,
  `role_id` int DEFAULT NULL);

DROP TABLE IF EXISTS role;
CREATE TABLE `role` (
  id int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS role_user;
CREATE TABLE role_user (
  role_id int DEFAULT NULL,
  user_id int DEFAULT NULL
);

DROP TABLE IF EXISTS user;
CREATE TABLE `user` (
  id int NOT NULL AUTO_INCREMENT,
  username varchar(100) NOT NULL,
  `password` varchar(1024) NOT NULL,
  email varchar(1024) NOT NULL,
  enabled tinyint NOT NULL,
  accountNonExpired tinyint NOT NULL,
  credentialsNonExpired tinyint NOT NULL,
  accountNonLocked tinyint NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY username (username)
);
