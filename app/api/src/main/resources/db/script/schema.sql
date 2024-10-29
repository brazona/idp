-- api.roles_seq definition

-- DROP SEQUENCE api.roles_seq;

CREATE SEQUENCE api.roles_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- api.session_seq definition

-- DROP SEQUENCE api.session_seq;

CREATE SEQUENCE api.session_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- api.users_seq definition

-- DROP SEQUENCE api.users_seq;

CREATE SEQUENCE api.users_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;


-- api.roles definition

-- Drop table

-- DROP TABLE api.roles;

CREATE TABLE api.roles (
	id int8 NOT NULL,
	"name" varchar(255) NULL,
	CONSTRAINT roles_pkey PRIMARY KEY (id)
);


-- api."session" definition

-- Drop table

-- DROP TABLE api."session";

CREATE TABLE api."session" (
	id int8 NOT NULL,
	access_token varchar(10012) NOT NULL,
	expires_in varchar(255) NULL,
	jwt_token varchar(10012) NOT NULL,
	refresh_expires_in varchar(255) NULL,
	refresh_token varchar(10012) NOT NULL,
	user_id int8 NULL,
	CONSTRAINT session_pkey PRIMARY KEY (id)
);


-- api.users definition

-- Drop table

-- DROP TABLE api.users;

CREATE TABLE api.users (
	id int8 NOT NULL,
	email varchar(255) NULL,
	is_account_non_expired bool NULL,
	is_account_non_locked bool NULL,
	is_credentials_non_expired bool NULL,
	is_enabled bool NULL,
	"name" varchar(255) NULL,
	"password" varchar(255) NULL,
	CONSTRAINT users_pkey PRIMARY KEY (id)
);


-- api.users_roles definition

-- Drop table

-- DROP TABLE api.users_roles;

CREATE TABLE api.users_roles (
	user_id int8 NOT NULL,
	role_id int8 NOT NULL,
	CONSTRAINT users_roles_pkey PRIMARY KEY (user_id, role_id),
	CONSTRAINT fk2o0jvgh89lemvvo17cbqvdxaa FOREIGN KEY (user_id) REFERENCES api.users(id),
	CONSTRAINT fkj6m8fwv7oqv74fcehir1a9ffy FOREIGN KEY (role_id) REFERENCES api.roles(id)
);

--CREATE UNIQUE INDEX session_pkey ON api.session USING btree (id);
--
--CREATE UNIQUE INDEX roles_pkey ON api.roles USING btree (id);
--
--CREATE UNIQUE INDEX users_roles_pkey ON api.users_roles USING btree (user_id, role_id);
--
--CREATE UNIQUE INDEX users_pkey ON api.users USING btree (id);