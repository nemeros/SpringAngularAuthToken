  
-- Table: public.t_role

-- DROP TABLE public.t_role;

CREATE TABLE public.t_role
(
  id integer NOT NULL DEFAULT nextval('t_role_id_seq'::regclass),
  nom character varying(10),
  CONSTRAINT t_role_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.t_role
  OWNER TO postgres;


-- Table: public.t_user

-- DROP TABLE public.t_user;

CREATE TABLE public.t_user
(
  id integer NOT NULL DEFAULT nextval('t_user_id_seq'::regclass),
  nom character varying(10),
  id_role integer,
  CONSTRAINT t_user_pkey PRIMARY KEY (id),
  CONSTRAINT t_user_id_role_fkey FOREIGN KEY (id_role)
      REFERENCES public.t_role (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.t_user
  OWNER TO postgres;

