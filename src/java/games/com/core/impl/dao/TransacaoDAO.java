/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.core.impl.dao;

import games.com.br.domain.EntidadeDominio;
import games.com.br.domain.Transacao;
import games.com.br.domain.TransacaoCartao;
import games.com.core.IDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Felipe
 */
public class TransacaoDAO extends AbstractJdbcDAO
{

    public TransacaoDAO(Connection connection)
    {
        super(connection, "", "");
    }

    public TransacaoDAO()
    {
        super(null, "", "");
    }   // Construtor Default

    public TransacaoDAO(Connection connection, String table, String idTable)
    {
        super(connection, table, idTable);
    }

    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException
    {
        PreparedStatement pst = null;
        Transacao transacao = (Transacao) entidade;
        try
        {
            openConnection();   //abrindo a conexao pela primeira vez!

            conn.setAutoCommit(false);

            IDAO dao = new PedidoDAO(conn, "pedidos", "id");

            dao.salvar(transacao.getPedido());  //Salvando o pedido do cliente!

            StringBuilder sql = new StringBuilder();    //String de SQL!

            sql.append("INSERT INTO TRANSACOES ");
            sql.append("(ID_PEDIDO,ID_CLIENTE,STATUS,DATA_TRANSACAO,DESCRICAO,VALOR) ");
            sql.append("VALUES (?,?,?,?,?,?)");

            pst = conn.prepareStatement(sql.toString());    //passando SQL para os dados

            //atributos para insercao no banco!
            pst.setInt(1, transacao.getPedido().getId());
            pst.setInt(2, transacao.getCliente().getId());
            pst.setString(3, transacao.getStatus());
            java.sql.Date dt = new Date(transacao.getDataTransacao().getTime());
            pst.setDate(4, dt);
            pst.setString(5, transacao.getDescricao());
            pst.setFloat(6, transacao.getPedido().valorTotalDesconto(0.15f));

            pst.execute(); //executando a query no banco de dados!

            conn.commit();  //commitando as alteracoes no banco!

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
                conn.close();
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
        Transacao transacao = (Transacao) entidade;
        StringBuilder sql = new StringBuilder();

        try
        {
            openConnection();   //abrindo conexao com o banco!
            conn.setAutoCommit(false);
            
            //retirando o valor da conta do cliente
            IDAO dao = new SaldoDAO(conn, null, null);
            
            TransacaoCartao trCartao = new TransacaoCartao();
            trCartao.setCliente(((Transacao)entidade).getCliente());
            trCartao.setValor(((Transacao)entidade).getPedido().valorTotalDesconto(0.15f));
            trCartao.setPedido(((Transacao)entidade).getPedido());
            dao.atualizar(trCartao);    //atualizando saldo do cliente!
            
            sql.append("UPDATE TRANSACOES ");
            sql.append("SET STATUS = ? ");
            sql.append("WHERE ID_PEDIDO = ?");

            pst = conn.prepareStatement(sql.toString());

            pst.setString(1, "Pago!");
            pst.setInt(2, transacao.getPedido().getId());   //passando id do pedido como parametro

            pst.executeUpdate();    //executando a atualizacao!

            dao = new PedidoDAO(conn, null, null);

            dao.atualizar(transacao.getPedido());   //atualizando valor da transacao

            conn.commit();  //comitando as alteracoes
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
            throw new SQLException(ex);
        } finally
        {
            try
            {
                pst.close();
                conn.close();
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void excluir(EntidadeDominio entidade) throws SQLException
    {
        //nao eh viavel excluir uma transacao!!!
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
