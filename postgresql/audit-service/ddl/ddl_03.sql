\c root

CREATE SCHEMA IF NOT EXISTS app
    AUTHORIZATION root;

CREATE TABLE IF NOT EXISTS app.audit
(
    uuid uuid NOT NULL,
    dt_create timestamp(3) without time zone,
    id integer NOT NULL,
    test character varying(255) COLLATE pg_catalog."default",
    type character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT audit_pkey PRIMARY KEY (uuid)
);

CREATE TABLE IF NOT EXISTS app.audit_user
(
    user_id uuid NOT NULL,
    email character varying(255) COLLATE pg_catalog."default",
    fio character varying(255) COLLATE pg_catalog."default",
    role character varying(255) COLLATE pg_catalog."default",
    uuid uuid,
    CONSTRAINT fkcc99pd0bxqj01qcmteqy6f1sk FOREIGN KEY (user_id)
        REFERENCES app.audit (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
