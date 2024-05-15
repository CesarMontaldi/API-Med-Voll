CREATE SEQUENCE IF NOT EXISTS consultas_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE IF NOT EXISTS consultas
(
    id bigint NOT NULL DEFAULT nextval('consultas_id_seq'::regclass),
    medico_id bigint NOT NULL,
    paciente_id bigint NOT NULL,
    data date NOT NULL,

    CONSTRAINT consultas_pkey PRIMARY KEY (id),
    CONSTRAINT fk_consultas_medico_id FOREIGN KEY (medico_id) REFERENCES medicos(id),
    CONSTRAINT fk_consultas_paciente_id FOREIGN KEY (paciente_id) REFERENCES pacientes(id)

);