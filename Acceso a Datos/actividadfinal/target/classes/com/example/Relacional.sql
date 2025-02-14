-- Database: energetica

-- DROP DATABASE IF EXISTS energetica;

CREATE DATABASE energetica
    WITH
    OWNER = mati
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

	-- SCHEMA: public

-- DROP SCHEMA IF EXISTS public ;

CREATE SCHEMA IF NOT EXISTS public
    AUTHORIZATION pg_database_owner;

COMMENT ON SCHEMA public
    IS 'standard public schema';

GRANT USAGE ON SCHEMA public TO PUBLIC;

GRANT ALL ON SCHEMA public TO pg_database_owner;

-- Table: public.cliente

-- DROP TABLE IF EXISTS public.cliente;

CREATE TABLE IF NOT EXISTS public.cliente
(
    dni character varying(10) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT cliente_pkey PRIMARY KEY (dni)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.cliente
    OWNER to mati;

	-- Table: public.consumo

-- DROP TABLE IF EXISTS public.consumo;

CREATE TABLE IF NOT EXISTS public.consumo
(
    contador integer NOT NULL,
    dia integer NOT NULL,
    hora integer NOT NULL,
    consumo double precision NOT NULL,
    precio double precision NOT NULL,
    CONSTRAINT consumo_pkey PRIMARY KEY (contador, dia, hora),
    CONSTRAINT fk_contador FOREIGN KEY (contador)
        REFERENCES public.contador (contador) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT "ck_dia>=0" CHECK (dia >= 0),
    CONSTRAINT "ck_hora>=0" CHECK (hora >= 0),
    CONSTRAINT "ck_dia<31" CHECK (dia < 31),
    CONSTRAINT "ck_hora<24" CHECK (hora < 24)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.consumo
    OWNER to mati;

	-- Table: public.contador

-- DROP TABLE IF EXISTS public.contador;

CREATE TABLE IF NOT EXISTS public.contador
(
    contador integer NOT NULL,
    CONSTRAINT contador_pkey PRIMARY KEY (contador)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.contador
    OWNER to mati;

	-- Table: public.contrato

-- DROP TABLE IF EXISTS public.contrato;

CREATE TABLE IF NOT EXISTS public.contrato
(
    id integer NOT NULL DEFAULT nextval('contrato_id_seq'::regclass),
    vivienda character varying(50) COLLATE pg_catalog."default" NOT NULL,
    cliente character varying(10) COLLATE pg_catalog."default" NOT NULL,
    contador integer,
    CONSTRAINT contrato_pkey PRIMARY KEY (id),
    CONSTRAINT fk_cliente FOREIGN KEY (cliente)
        REFERENCES public.cliente (dni) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT fk_contador FOREIGN KEY (contador)
        REFERENCES public.contador (contador) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.contrato
    OWNER to mati;