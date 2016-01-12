/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.core.impl.dao;

import games.com.br.domain.EntidadeDominio;
import games.com.br.domain.TransacaoCartao;
import games.com.core.IDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Felipe
 */
public class TransacaoCartaoDAO extends AbstractJdbcDAO
{

    public TransacaoCartaoDAO(Connection connection, String table, String idTable)
    {
        super(connection, table, idTable);
    }

    public TransacaoCartaoDAO()
    {
        super(null, null);
    }
    
    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException
    {
        PreparedStatement pst = null;
        TransacaoCartao transacao = (TransacaoCartao) entidade;
        try
        {
            openConnection();   //abrindo conexao com banco!
            conn.setAutoCommit(false);
            
            IDAO dao = new SaldoDAO(conn, "", ""); 
            
            dao.atualizar(transacao);   //atualiza o saldo do cliente
            
            //pagamento foi feito, mudando status do pedido
            transacao.getPedido().setPosicao("Pago!");
            
            dao = new PedidoDAO(conn, "", "");
            dao.salvar(transacao.getPedido());  //salvando pedido do cliente!
            
            StringBuilder sql = new StringBuilder();
            
            sql.append("INSERT INTO TRANSACOES ");
            sql.append("(ID_PEDIDO,ID_CLIENTE,STATUS,DATA_TRANSACAO,DESCRICAO,VALOR,NUMERO_CARTAO,NUMERO_PARCELAS,NOME_CARTAO) ");
            sql.append("VALUES (?,?,?,?,?,?,?,?,?) ");
            
            pst = conn.prepareStatement(sql.toString());
            
            pst.setInt(1, transacao.getPedido().getId());
            pst.setInt(2, transacao.getCliente().getId());
            pst.setString(3, transacao.getStatus());
            java.sql.Date dt = new Date(Calendar.getInstance().getTimeInMillis());
            pst.setDate(4, dt);
            pst.setString(5, transacao.getDescricao());
            pst.setFloat(6, transacao.getValor());
            pst.setString(7, transacao.getCartao().getNumeroFinalCartao());
            pst.setString(8, transacao.getCartao().getParcelas());
            pst.setString(9, transacao.getCartao().getNomeCartao());
            
            pst.executeUpdate();
            
            conn.commit();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            try
            {
               conn.rollback();
            }
            catch(SQLException ex1)
            {
                ex1.printStackTrace();
            }
            throw new SQLException(ex);
        }
        finally
        {
            try
            {
                //pst.close();
                conn.close();
            }
            catch(SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void atualizar(EntidadeDominio entidade) throws SQLException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
