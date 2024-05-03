CREATE TABLE IF NOT EXISTS public.medicos
(
    id bigint NOT NULL DEFAULT nextval('medicos_id_seq'::regclass),
    nome character varying(255) COLLATE pg_catalog."default",
    crm character varying(255) COLLATE pg_catalog."default",
    especialidade character varying(255) COLLATE pg_catalog."default",
    email character varying(255) COLLATE pg_catalog."default",
    telefone character varying(255) COLLATE pg_catalog."default",
    bairro character varying(255) COLLATE pg_catalog."default",
    cep character varying(255) COLLATE pg_catalog."default",
    cidade character varying(255) COLLATE pg_catalog."default",
    complemento character varying(255) COLLATE pg_catalog."default",
    logradouro character varying(255) COLLATE pg_catalog."default",
    numero character varying(255) COLLATE pg_catalog."default",
    uf character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT medicos_pkey PRIMARY KEY (id),
    CONSTRAINT medicos_especialidade_check CHECK (especialidade::text = ANY (ARRAY['ORTOPEDIA'::character varying, 'CARDIOLOGIA'::character varying, 'GINECOLOGIA'::character varying, 'DERMATOLOGIA'::character varying]::text[]))
)
