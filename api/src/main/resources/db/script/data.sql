--liquibase formatted sql
--changeset dirk:2

INSERT INTO roles (id, name) VALUES (nextval('roles_seq'), 'MAINTAINER');
INSERT INTO roles (id, name) VALUES (nextval('roles_seq'), 'ADMINISTRATOR');
INSERT INTO roles (id, name) VALUES (nextval('roles_seq'), 'COORDINATOR');
INSERT INTO roles (id, name) VALUES (nextval('roles_seq'), 'TEACHER');
INSERT INTO roles (id, name) VALUES (nextval('roles_seq'), 'STUDENT');
INSERT INTO roles (id, name) VALUES (nextval('roles_seq'), 'VISITOR');

INSERT INTO users (id, name, email, password, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled)
VALUES (nextval('users_seq'), '${sql_user_name_1}', 'user1@brazona.com.br',
'$2a$10$0aFkQ9xFY.tLmmnymiY0puLAGFkin3n0MDMfqJWJ//XaskkpLmOF.', true, true, true, true);
INSERT INTO users (id, name, email, password, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled)
VALUES (nextval('users_seq'), '${sql_user_name_2}', 'user2@brazona.com.br',
'$2a$10$0aFkQ9xFY.tLmmnymiY0puLAGFkin3n0MDMfqJWJ//XaskkpLmOF.', true, true, true, true);
INSERT INTO users (id, name, email, password, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled)
VALUES (nextval('users_seq'), '${sql_user_name_3}', 'user3@brazona.com.br',
'$2a$10$0aFkQ9xFY.tLmmnymiY0puLAGFkin3n0MDMfqJWJ//XaskkpLmOF.', true, true, true, true);
INSERT INTO users (id, name, email, password, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled)
VALUES (nextval('users_seq'), '${sql_user_name_4}', 'user4@brazona.com.br',
'$2a$10$0aFkQ9xFY.tLmmnymiY0puLAGFkin3n0MDMfqJWJ//XaskkpLmOF.', true, true, true, true);

