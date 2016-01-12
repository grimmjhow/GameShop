-- Table: clientes
CREATE TABLE clientes
(
  id serial NOT NULL,
  nome character varying(41),
  rg character varying(9),
  cpf character varying(11),
  sexo character varying(1),
  email character varying(41),
  telefone character varying(12),
  celular character varying(13),
  id_endereco integer,
  dt_nasc timestamp without time zone,
  dt_cad timestamp without time zone,
  status integer,
  senha character varying(45),
  id_endereco_entrega integer,
  saldo double precision,
  tipo character varying(45),
  CONSTRAINT pk_clientte PRIMARY KEY (id),
  CONSTRAINT fk_endereco FOREIGN KEY (id_endereco)
      REFERENCES enderecos (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_id_endereco_entrega FOREIGN KEY (id_endereco_entrega)
      REFERENCES enderecos_entrega (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT uq_rg UNIQUE (rg)
)
-- ---------------------------------------------------------------------------------------

-- Table: clientes_entregas
CREATE TABLE clientes_entregas
(
  id integer NOT NULL,
  id_cliente integer,
  id_end_entrega integer,
  CONSTRAINT pk_cliente_entrega PRIMARY KEY (id),
  CONSTRAINT fk_cliente_entrega FOREIGN KEY (id_cliente)
      REFERENCES clientes (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_entrega_cliente FOREIGN KEY (id_end_entrega)
      REFERENCES enderecos_entrega (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
-- ---------------------------------------------------------------------------------------

-- Table: enderecos
CREATE TABLE enderecos
(
  id serial NOT NULL,
  logradouro character varying(42),
  cep character varying(20),
  bairro character varying(42),
  estado character varying(42),
  cidade character varying(42),
  numero character varying(10),
  CONSTRAINT pk_endereco PRIMARY KEY (id)
)
-- ---------------------------------------------------------------------------------------

-- Table: enderecos_entrega
CREATE TABLE enderecos_entrega
(
  id serial NOT NULL,
  logradouro character varying(45),
  numero character varying(45),
  bairro character varying(45),
  cep character varying(45),
  cidade character varying(45),
  estado character varying(45),
  id_cliente integer,
  CONSTRAINT pk_endereco_entrega PRIMARY KEY (id),
  CONSTRAINT fk_id_cliente FOREIGN KEY (id_cliente)
      REFERENCES clientes (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
-- ---------------------------------------------------------------------------------------

-- Table: jogos
CREATE TABLE jogos
(
  id integer NOT NULL DEFAULT nextval('games_id_seq'::regclass),
  titulo character varying(45),
  descricao character varying(300),
  faixa_etaria character varying(20),
  genero character varying(45),
  valor double precision,
  ano_lancamento character varying(15),
  multiplayer character varying(5),
  online character varying(5),
  idiomas character varying(100),
  image character varying(45),
  quantidade_estoque integer,
  ativo integer,
  CONSTRAINT pk_games PRIMARY KEY (id)
)
-- ---------------------------------------------------------------------------------------

-- Table: login
CREATE TABLE login
(
  id serial NOT NULL,
  login character varying(45),
  senha character varying(45),
  id_user integer,
  status integer,
  CONSTRAINT pk_login PRIMARY KEY (id)
)
-- ---------------------------------------------------------------------------------------

-- Table: pedidos
CREATE TABLE pedidos
(
  id serial NOT NULL,
  data_pedido timestamp without time zone,
  total double precision,
  id_cliente integer,
  tipo_pagamento character varying(300),
  data_normal date,
  posicao character varying(300),
  id_endereco_entrega integer,
  status character varying(200),
  frete double precision,
  CONSTRAINT pk_pedidos PRIMARY KEY (id),
  CONSTRAINT fk_entrega_pedidos FOREIGN KEY (id_endereco_entrega)
      REFERENCES enderecos_entrega (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
-- ---------------------------------------------------------------------------------------

-- Table: pedidos_jogos
CREATE TABLE pedidos_jogos
(
  id_pedidos integer NOT NULL,
  id_jogos integer NOT NULL,
  quantidade integer,
  desconto double precision,
  CONSTRAINT pk_pedidos_jogos PRIMARY KEY (id_pedidos, id_jogos),
  CONSTRAINT fk_jogos FOREIGN KEY (id_jogos)
      REFERENCES jogos (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_pedidos FOREIGN KEY (id_pedidos)
      REFERENCES pedidos (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
-- ---------------------------------------------------------------------------------------

-- Table: saldos
CREATE TABLE saldos
(
  id integer NOT NULL DEFAULT nextval('seq_saldos'::regclass),
  id_cliente integer,
  saldo double precision,
  CONSTRAINT pk_saldos PRIMARY KEY (id),
  CONSTRAINT fk_cliente_saldo FOREIGN KEY (id_cliente)
      REFERENCES clientes (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
-- ---------------------------------------------------------------------------------------

-- Table: transacoes
CREATE TABLE transacoes
(
  id integer NOT NULL DEFAULT nextval('transacao_id_seq'::regclass),
  id_pedido integer,
  id_cliente integer,
  status character varying(45),
  data_transacao date,
  descricao character varying(300),
  valor double precision,
  numero_cartao character varying(45),
  numero_parcelas character varying(45),
  nome_cartao character varying(45),
  CONSTRAINT pk_transacao PRIMARY KEY (id),
  CONSTRAINT fk_cliente FOREIGN KEY (id_cliente)
      REFERENCES clientes (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_pedido FOREIGN KEY (id_pedido)
      REFERENCES pedidos (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)