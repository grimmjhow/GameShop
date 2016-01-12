create table estoque
(
	id serial,
	id_jogo int,
	data_insercao date,
	id_usuario int,
	quantidade int,
	constraint pk_estoque primary key (id),
	constraint fk_jogo foreign key (id_jogo) references jogos(id)
);

select * from estoque


create table historico_compras
(
	id serial,
	id_jogo int,
	id_cliente int,
	qtde_compra int,
	constraint pk_historico primary key (id),
	constraint fk_jogos foreign key (id_jogo) references jogos(id),
	constraint fk_cliente foreign key (id_cliente) references clientes(id)
);


select * from historico_compras  where id_cliente = 69

select * from jogos where id = 2

SELECT
(select SUM(h.qtde_compra) from historico_compras  as h 
 where h.id_cliente = 65 and h.id_jogo = 1) as qtde_compra,
e.quantidade
from estoque as e
where e.id_jogo = 1

select * from deleteItem(69,3)


select * from estoque where id_jogo = 3

select * from historico_compras 

CREATE OR REPLACE FUNCTION deleteItem(INTEGER,INTEGER)
RETURNS VOID AS
'

update estoque set
quantidade = (quantidade - (select SUM(h.qtde_compra) from historico_compras 
as h where h.id_cliente = $1 and h.id_jogo = $2))
where id_jogo = $2

'
LANGUAGE 'sql';
