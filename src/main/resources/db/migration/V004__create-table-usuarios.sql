CREATE SEQUENCE IF NOT EXISTS public.usuarios_id_seq
    INCREMENT 1
    START 2
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE IF NOT EXISTS public.usuarios
(
    id bigint NOT NULL DEFAULT nextval('usuarios_id_seq'::regclass),
    login character varying(100) COLLATE pg_catalog."default" NOT NULL,
    senha character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT usuarios_pkey PRIMARY KEY (id)
)