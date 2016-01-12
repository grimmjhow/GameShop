/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.core.impl.dao;

import games.com.br.domain.EntidadeDominio;
import games.com.br.domain.Saldo;
import games.com.br.domain.Transacao;
import games.com.br.domain.TransacaoCartao;
import games.com.br.domain.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Felipe
 */
public class SaldoDAO extends AbstractJdbcDAO
{

    public SaldoDAO(Connection connection, String table, String idTable)
    {
        super(connection, table, idTable);
    }

    public SaldoDAO()
    {
        super(null, null);
    }

    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException
    {
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();
        try
        {
            if(conn == null || conn.isClosed())
                openConnection();   //abrindo a conexao com o banco de dados
            
            sql.append("INSERT INTO SALDOS ");
            sql.append("(ID_CLIENTE,SALDO) ");
            sql.append("VALUES (?,?)");
            
            pst = conn.prepareStatement(sql.toString());
            
            pst.setInt(1, entidade.getId());
            pst.setFloat(2, 500f);
            
            pst.execute();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            throw new SQLException(ex);
        }
    }

    @Override
    public void atualizar(EntidadeDominio entidade) throws SQLException
    {
        PreparedStatement pst = null;
        TransacaoCartao transacao = (TransacaoCartao) entidade;
        try
        {
            if (conn == null || conn.isClosed())
            {
                openConnection();   //abrindo conexao caso nao esteja aberta
            }

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE SALDOS ");
            sql.append("SET saldo = saldo - ? ");
            sql.append("WHERE ID_CLIENTE = ? and saldo > ?");

            pst = conn.prepareStatement(sql.toString());

            pst.setFloat(1, transacao.TotalTransacao());
            pst.setInt(2, transacao.getCliente().getId());
            pst.setFloat(3, transacao.TotalTransacao());

            pst.executeUpdate();

        } catch (SQLException ex)
        {
             ex.printStackTrace();
             throw new SQLException();
        }
        finally
        {
            if(!ctrlTransaction)
                pst.close();
            
            if(!ctrlTransaction)
                conn.close();
        }
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public EntidadeDominio consultaSaldoEspecifico(EntidadeDominio entidade)
    {
        PreparedStatement pst = null;
        
        TransacaoCartao transacao =(TransacaoCartao) entidade;
        
        try
        {
            openConnection();
            
            StringBuilder sql = new StringBuilder();

            sql.append("SELECT saldo FROM SALDOS ");
            sql.append("WHERE SALDO >= (?) and ID_CLIENTE = (?)");

            pst = conn.prepareStatement(sql.toString());

            pst.setFloat(1, transacao.getValor());
            pst.setInt(2, transacao.getCliente().getId());

            ResultSet rs = pst.executeQuery();

            if (rs.next())
            {
                Saldo saldo = new Saldo();
                saldo.setSaldo(rs.getFloat("saldo"));
                return saldo;
            } else
            {
                return null;
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
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }

}
