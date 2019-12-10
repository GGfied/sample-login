DROP TABLE IF EXISTS app_user_role;
DROP TABLE IF EXISTS app_role;
DROP TABLE IF EXISTS app_user;
DROP TABLE IF EXISTS language;

CREATE TABLE language (
  id bigint NOT NULL AUTO_INCREMENT,
  code varchar(2) NOT NULL,
  name varchar(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE app_role (
  id bigint NOT NULL AUTO_INCREMENT,
  description varchar(255) DEFAULT NULL,
  role_name varchar(255) DEFAULT NULL UNIQUE,
  PRIMARY KEY (id)
);

CREATE TABLE app_user (
  id bigint NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  username varchar(255) NOT NULL UNIQUE,
  language_id bigint,
  CONSTRAINT fkey_language_id FOREIGN KEY (language_id) REFERENCES language (id),
  PRIMARY KEY (id)
);

CREATE TABLE app_user_role (
  app_user_id bigint,
  app_role_id bigint,
  CONSTRAINT fkey_app_user_id FOREIGN KEY (app_user_id) REFERENCES app_user (id),
  CONSTRAINT fkey_app_role_id FOREIGN KEY (app_role_id) REFERENCES app_role (id),
  CONSTRAINT pkey_app_user_role PRIMARY KEY (app_user_id, app_role_id)
);

drop table if exists oauth_client_details;
create table oauth_client_details (
  client_id VARCHAR(256),
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(256)
);

create index idx_oauth_client_details_client_id_client_secret on oauth_client_details(client_id, client_secret);

drop table if exists oauth_client_token;
create table oauth_client_token (
  token_id VARCHAR(255),
  token BLOB,
  authentication_id VARCHAR(255),
  user_name VARCHAR(255),
  client_id VARCHAR(255)
);

create index idx_oauth_client_token_token_id on oauth_client_token(token_id);

drop table if exists oauth_access_token;
create table oauth_access_token (
  token_id VARCHAR(255),
  token BLOB,
  authentication_id VARCHAR(255),
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication BLOB,
  refresh_token VARCHAR(255)
);

create index idx_oauth_access_token_token_id on oauth_access_token(token_id);

drop table if exists oauth_refresh_token;
create table oauth_refresh_token (
  token_id VARCHAR(255),
  token BLOB,
  authentication BLOB
);

create index idx_oauth_refresh_token_token_id on oauth_refresh_token(token_id);

drop table if exists oauth_code;
create table oauth_code (
  code VARCHAR(255),
  authentication BLOB
);