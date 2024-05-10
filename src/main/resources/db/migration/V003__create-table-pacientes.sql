CREATE SEQUENCE IF NOT EXISTS pacientes_id_seq
    INCREMENT 1
    START 4
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE IF NOT EXISTS pacientes
(
    id bigint NOT NULL DEFAULT nextval('pacientes_id_seq'::regclass),
    nome character varying(255),
    cpf character varying(20),
    email character varying(255),
    telefone character varying(20),
    bairro character varying(255) COLLATE pg_catalog."default",
    cep character varying(255) COLLATE pg_catalog."default",
    cidade character varying(255) COLLATE pg_catalog."default",
    complemento character varying(255) COLLATE pg_catalog."default",
    logradouro character varying(255) COLLATE pg_catalog."default",
    numero character varying(255) COLLATE pg_catalog."default",
    uf character varying(255) COLLATE pg_catalog."default",
    ativo boolean DEFAULT true,
    CONSTRAINT pacientes_pkey PRIMARY KEY (id)
);
