\c root

CREATE SCHEMA IF NOT EXISTS app
    AUTHORIZATION root;

CREATE TABLE IF NOT EXISTS app.products
(
    uuid uuid NOT NULL,
    dt_create timestamp(3) without time zone,
    dt_update timestamp(3) without time zone,
    title character varying(255) COLLATE pg_catalog."default",
    weight integer,
    calories integer,
    proteins double precision,
    fats double precision,
    carbohydrates double precision,
    CONSTRAINT products_pkey PRIMARY KEY (uuid)
);

CREATE TABLE IF NOT EXISTS app.recipe
(
    uuid uuid NOT NULL,
    dt_create timestamp(3) without time zone,
    dt_update timestamp(3) without time zone,
    title character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT recipe_pkey PRIMARY KEY (uuid)
);

CREATE TABLE IF NOT EXISTS app.ingredient
(
    product uuid NOT NULL,
    weight integer NOT NULL,
    recipe_id uuid,
    CONSTRAINT ingredient_product_fkey FOREIGN KEY (product)
        REFERENCES app.products (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT ingredient_recipe_fkey FOREIGN KEY (recipe_id)
        REFERENCES app.recipe (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
);

