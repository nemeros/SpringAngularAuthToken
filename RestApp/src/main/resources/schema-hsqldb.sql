CREATE TABLE t_role
(
  id integer identity,
  nom varchar(50)
);

CREATE TABLE t_user
(
  id integer identity,
  nom varchar(50),
  id_role integer REFERENCES t_role (id)
);