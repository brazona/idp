--liquibase formatted sql
--changeset dirk:2

INSERT INTO business_partners (id, cnpj, "name", name_social)
VALUES (nextval('bp_seq'), '15.557.716/0001-61', 'Brazona Tech LTDA', 'Brazona Tecnologia');
INSERT INTO public.business_partners (id, cnpj, "name", name_social)
VALUES (nextval('bp_seq'), '99.601.036/0001-98', 'Brazona School', 'Brazona School');

INSERT INTO roles (id, name) VALUES (nextval('roles_seq'), 'MAINTAINER');
INSERT INTO roles (id, name) VALUES (nextval('roles_seq'), 'ADMINISTRATOR');
INSERT INTO roles (id, name) VALUES (nextval('roles_seq'), 'COORDINATOR');
INSERT INTO roles (id, name) VALUES (nextval('roles_seq'), 'TEACHER');
INSERT INTO roles (id, name) VALUES (nextval('roles_seq'), 'STUDENT');
INSERT INTO roles (id, name) VALUES (nextval('roles_seq'), 'VISITOR');

INSERT INTO services_providers (id, name, client_id, client_secret)
VALUES (nextval('services_seq'), 'Identity Provider', 'idp', 'mwbc1c6qJL2ifrBdqA5X26cDpxEoDSsS');
INSERT INTO services_providers (id, name, client_id, client_secret)
VALUES (nextval('services_seq'), 'Business Administration Games', 'bag', 'mwbc1c6qJL2ifrBdqA5X26cDpxEoDSsS');

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

INSERT INTO users_permissions (id, bp_id, role_id, service_id, user_id)
VALUES(nextval('users_permissions_seq'), 1, 1, 1, 1);
INSERT INTO users_permissions (id, bp_id, role_id, service_id, user_id)
VALUES(nextval('users_permissions_seq'), 1, 4, 1, 2);
INSERT INTO users_permissions (id, bp_id, role_id, service_id, user_id)
VALUES(nextval('users_permissions_seq'), 1, 6, 1, 3);

INSERT INTO user_relation_permission (user_id, permission_id)
VALUES(1, 1);
INSERT INTO user_relation_permission (user_id, permission_id)
VALUES(2, 2);
INSERT INTO user_relation_permission (user_id, permission_id)
VALUES(3, 3);