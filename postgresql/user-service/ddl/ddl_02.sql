\c root

CREATE SCHEMA IF NOT EXISTS app
    AUTHORIZATION root;

CREATE TABLE IF NOT EXISTS app."user"
(
    uuid uuid NOT NULL,
    dt_create timestamp(3) without time zone,
    dt_update timestamp(3) without time zone,
    fio character varying(255) COLLATE pg_catalog."default",
    email character varying(255) COLLATE pg_catalog."default",
    password character varying(255) COLLATE pg_catalog."default",
    role character varying(255) COLLATE pg_catalog."default",
    status character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT userentity_pkey PRIMARY KEY (uuid)
);