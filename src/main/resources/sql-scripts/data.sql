DELETE FROM app_user_role;
DELETE FROM app_role;
DELETE FROM app_user;
DELETE FROM language;

-- LANGUAGE
INSERT INTO language (id, code, name) VALUES (1, 'EN', 'English');
INSERT INTO language (id, code, name) VALUES (2, 'CN', '中文(简体)');

-- ROLE
INSERT INTO app_role (id, role_name, description) VALUES (1, 'USER', 'Standard User - Has no manager rights');
INSERT INTO app_role (id, role_name, description) VALUES (2, 'ADMIN', 'Manager User - Has permission to perform manager tasks');

-- USER
-- non-encrypted password: user
INSERT INTO app_user (id, name, password, username) VALUES (1, 'User', '$2a$10$5DRF3T3bON/Qa.EP8glZCekCVVqIr./dObbrw98N1iyGbQDF6XiUW', 'user');
-- non-encrypted password: manager
INSERT INTO app_user (id, name, password, username) VALUES (2, 'Manager', '$2a$10$X4TKlhMZ3CxSI9EoKXbnL.3sFvXnC1foKrI1VY69aPpulZ0sCCk7C', 'manager');
-- non-encrypted password: user
INSERT INTO app_user (id, name, password, username, language_id) VALUES (3, 'User (CN)', '$2a$10$5DRF3T3bON/Qa.EP8glZCekCVVqIr./dObbrw98N1iyGbQDF6XiUW', 'user_cn', 2);

-- USER + ROLE
INSERT INTO app_user_role(app_user_id, app_role_id) VALUES(1,1);
INSERT INTO app_user_role(app_user_id, app_role_id) VALUES(2,1);
INSERT INTO app_user_role(app_user_id, app_role_id) VALUES(2,2);
INSERT INTO app_user_role(app_user_id, app_role_id) VALUES(3,1);

-- CLIENT DETAILS
DELETE FROM oauth_client_details;
INSERT INTO oauth_client_details
   (client_id, client_secret, scope, authorized_grant_types,
   authorities, access_token_validity, refresh_token_validity)
VALUES
   ('testjwtclientid', '$2a$10$tK6aYeRf/Lg573lUKtycdu1esITa7lZ/4MVMCZ0TQaEFs2YpsIWDu', 'read,write', 'password,refresh_token,client_credentials,authorization_code', 'ROLE_CLIENT,ROLE_TRUSTED_CLIENT', 900, 2592000);