package games.com.core.impl.dao;

import games.com.br.domain.EnderecoEntrega;
import games.com.br.domain.EntidadeDominio;
import games.com.br.domain.Frete;
import games.com.br.domain.ItemPedido;
import games.com.br.domain.Jogo;
import games.com.br.domain.Pedido;
import games.com.br.domain.PedidoJogo;
import games.com.br.domain.Transacao;
import games.com.core.IDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Felipe Monteiro
 */
public class PedidoDAO extends AbstractJdbcDAO
{

    public PedidoDAO()
    {
        super("pedidos", "id");
    }

    public PedidoDAO(Connection connection, String table, String idTable)
    {
        super(connection, table, idTable);
    }

    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException
    {
        PreparedStatement pst = null;

        try
        {
            Pedido pedido = (Pedido) entidade;
            PedidoJogo pedidoJogo = new PedidoJogo();

            if (conn.isClosed() || conn == null) //abre uma conexao caso ela seja Nula!
            {
                openConnection();   //abrindo conexao!
            }
            StringBuilder sql = new StringBuilder();    //criando string para conter a query!

            sql.append("INSERT INTO PEDIDOS ");
            sql.append("(data_pedido,total,id_cliente,tipo_pagamento,data_normal,posicao,id_endereco_entrega,status,frete) ");
            sql.append("VALUES ");
            sql.append("(?,?,?,?,?,?,?,?,?)");

            pst = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);    //adicionando sql e retornando ultima chave adicionada!

            //convertendo dada para o banco de dados!
            Timestamp dataTime = new Timestamp(pedido.getDataPedido().getTimeInMillis());
            pst.setTimestamp(1, dataTime);  

            pst.setFloat(2, pedido.ValorTotal());
            pst.setInt(3, pedido.getCliente().getId());
            pst.setString(4, pedido.getTipoPagamento());
            pst.setDate(5, new java.sql.Date(pedido.getDataNormal().getTime()));
            pst.setString(6, pedido.getPosicao());
            pst.setInt(7, pedido.getEntrega().getId());
            pst.setString(8, pedido.getStatus());
            pst.setFloat(9, pedido.getFrete().getValor());

            pst.executeUpdate();  //executa o sql!

            ResultSet rs = pst.getGeneratedKeys();   //retornar ID da ultima insercao

            if (rs.next())
            {
                pedido.setId(rs.getInt("id"));
            } else
            {
                throw new SQLException();   //nao pode proseguir sem o id!
            }
            
            IDAO dao;

            for (ItemPedido ip : pedido.getItens())   //iterando pela tabela de pedidos!
            {
                
                sql = new StringBuilder();
                
                sql.append("INSERT INTO PEDIDOS_JOGOS ");
                sql.append("(id_pedidos,id_jogos,quantidade,desconto) ");
                sql.append("VALUES (?,?,?,?) ");
                
                pst = conn.prepareStatement(sql.toString());
                
                pst.setInt(1, pedido.getId());
                pst.setInt(2, ip.getJogo().getId());
                pst.setInt(3, ip.getQuantidade());
                pst.setFloat(4, ip.getDesconto());
                
                pst.execute();
                
                // inicio do teste
                dao = new JogoDAO(conn, "", "");

                ip.getJogo().setQuantidade(ip.getQuantidade());   //setando a quantidade desejada

                ((JogoDAO) dao).atualizarAquantidade(ip.getJogo());  //atualizando a quantidade no banco de dados
            }

            //conn.commit(); --> vai ser executado pela classe DAO Transacao!
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            try
            {
                conn.rollback();
            } catch (SQLException ex1)
            {
                ex1.printStackTrace();
            }
        } finally
        {
            try
            {
                if (!ctrlTransaction)
                {
                    conn.close();   //Fechando a Conexao caso nao acha uma transacao!
                }
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void atualizar(EntidadeDominio entidade) throws SQLException
    {
        PreparedStatement pst = null;
        Pedido pedido = (Pedido) entidade;
        StringBuilder sql = new StringBuilder();
        
        try
        {
            if(conn == null || conn.isClosed())
                openConnection();   //abrindo conexao caso nao exista!
            
            sql.append("UPDATE PEDIDOS ");
            sql.append("SET POSICAO = ? ");
            sql.append("WHERE ID = ?");
            
            pst = conn.prepareStatement(sql.toString());
            
            pst.setString(1, "Pago!");
            pst.setInt(2, pedido.getId());
            
            pst.executeUpdate();
            
            //commit vai ser feito pela classe transcao DAO assim como o fechamento da conexao
        }
        catch(SQLException ex)
        {
            throw new SQLException(ex);
        }
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException
    {
        PreparedStatement pst = null;
        Pedido pedido = (Pedido) entidade;
        List<EntidadeDominio> entidades = new ArrayList<>();
        int mes, ano;
        StringBuilder sql = new StringBuilder();

        try
        {
            openConnection();    //abrindo conexao com o banco

            if (pedido.getDataNormal() != null)
            {
                sql.append("SELECT DISTINCT ");
                sql.append("p.id, p.data_pedido, p.tipo_pagamento, p.posicao, p.status, p.frete,p.id_endereco_entrega ");
                sql.append("FROM PEDIDOS AS p ");
                sql.append("WHERE p.id_cliente = ? and ");
                sql.append("date_part('month',p.data_normal) = ? and ");
                sql.append("date_part('year',p.data_normal) = ?");

                //pegando mes e ano da data!
                Calendar cl = Calendar.getInstance();
                cl.setTime(pedido.getDataNormal());
                mes = cl.get(Calendar.MONTH) + 1;
                ano = cl.get(Calendar.YEAR);
                //fim do ano e mes!

                pst = conn.prepareStatement(sql.toString());

                pst.setInt(1, pedido.getCliente().getId());
                pst.setInt(2, mes);
                pst.setInt(3, ano);

                ResultSet rs = pst.executeQuery();  //pegando resultado da consulta!

                while (rs.next())    //prossiga ate que nao tenha mais cursor!
                {
                    Pedido ped = new Pedido();
                    ped.setId(rs.getInt("id"));
                    Calendar dtPedido = Calendar.getInstance();
                    dtPedido.setTimeInMillis(rs.getDate("data_pedido").getTime());
                    ped.setDataPedido(dtPedido);
                    ped.setTipoPagamento(rs.getString("tipo_pagamento"));
                    ped.setPosicao(rs.getString("posicao"));
                    ped.setStatus(rs.getString("status"));
                    ped.setFrete(new Frete(rs.getFloat("frete")));
                    ped.setEntrega(new EnderecoEntrega(rs.getInt("id_endereco_entrega")));

                    entidades.add(ped); // colocando os pedidos!
                }

                return entidades;   //retornando os pedidos!
            } else
            {
                sql.append("SELECT DISTINCT j.*,pj.quantidade, pj.desconto ");
                sql.append("FROM JOGOS as j, PEDIDOS_JOGOS as pj, PEDIDOS as p ");
                sql.append("WHERE pj.id_pedidos = ? and j.id = pj.id_jogos ");

                pst = conn.prepareStatement(sql.toString());

                pst.setInt(1, pedido.getId());

                ResultSet rs = pst.executeQuery();
                
                //acessando DAO do endereco de entrega
                IDAO dao = new EnderecoEntregaDAO(conn, null, null);

                while (rs.next())
                {
                    Jogo jogo = new Jogo(); //criando objeto de jogo

                    jogo.setId(rs.getInt("id"));
                    jogo.setTitulo(rs.getString("titulo"));
                    jogo.setValor(rs.getFloat("valor"));

                    ItemPedido item = new ItemPedido(); //criando objeto de itemPedido

                    item.setJogo(jogo);
                    item.setDesconto(rs.getFloat("desconto"));
                    item.setQuantidade(rs.getInt("quantidade"));

                    pedido.addItem(item);   //adicionando o item em pedidos!
                }

                //pegando o endereco de entrga
                List<EntidadeDominio> enderecos = dao.consultar(pedido.getEntrega());

                pedido.setEntrega((EnderecoEntrega) enderecos.get(0));  //setando entrega

                entidades.add(0, pedido);

                return entidades;
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            return null;
        } finally
        {
            try
            {
                conn.close();
                pst.close();
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    public List<EntidadeDominio> consultaPedidoData(EntidadeDominio entidade)
    {
        PreparedStatement pst = null;
        Pedido pedido = (Pedido) entidade;
        List<EntidadeDominio> entidades = new ArrayList<>();
        int mes, ano;    //mes e ano da data escolhida pelo usuario
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT * FROM PEDIDOS ");
        sql.append("WHERE id_cliente = ? and ");
        sql.append("date_part('month',data_normal) = ? and date_part('year',data_normal) = ?");

        try
        {
            openConnection();    //abrindo conexao com o banco

            pst = conn.prepareStatement(sql.toString());
            Calendar dt = Calendar.getInstance();
            dt.setTime(pedido.getDataNormal());

            mes = dt.get(Calendar.MONTH);
            ano = dt.get(Calendar.YEAR);

            pst.setInt(1, pedido.getCliente().getId());
            pst.setInt(2, mes);
            pst.setInt(3, ano);

            ResultSet rs = pst.executeQuery();   //pegando o retorno da consulta!

            while (rs.next())   //pegando todas as informacoes do pedido
            {
                Pedido ped = new Pedido();

                ped.setId(rs.getInt("id"));
                Timestamp time = rs.getTimestamp("data_pedido");
                Calendar date = Calendar.getInstance();
                date.setTimeInMillis(time.getTime());
                ped.setDataPedido(date);
                ped.setTipoPagamento(rs.getString("tipo_pagamento"));
                ped.setPosicao(rs.getString("posicao"));
                Frete frete = new Frete();
                frete.setValor(rs.getFloat("frete"));
                ped.setFrete(frete);

                entidades.add(ped);
            }

            return entidades;

        } catch (SQLException ex)
        {
            ex.printStackTrace();
            return null;
        } finally
        {
            try
            {
                conn.close();
                pst.close();
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }
}
