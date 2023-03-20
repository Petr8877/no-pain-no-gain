\c root

CREATE SCHEMA IF NOT EXISTS app
    AUTHORIZATION root;

CREATE TABLE IF NOT EXISTS app.audit
(
    uuid uuid NOT NULL,
    client uuid,
    client_email character varying(255) COLLATE pg_catalog."default",
    client_fio character varying(255) COLLATE pg_catalog."default",
    client_role character varying(255) COLLATE pg_catalog."default",
    dt_create timestamp(6) without time zone,
    id_type integer NOT NULL,
    text character varying(255) COLLATE pg_catalog."default",
    type character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT audit_pkey PRIMARY KEY (uuid)
);
