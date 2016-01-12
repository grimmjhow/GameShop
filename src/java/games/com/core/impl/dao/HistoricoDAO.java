/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.core.impl.dao;

import games.com.br.domain.EntidadeDominio;
import games.com.br.domain.Historico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Felipe
 */
public class HistoricoDAO extends AbstractJdbcDAO
{

    public HistoricoDAO()
    {
        super("", "");
    }
    
    public HistoricoDAO(Connection connection, String table, String idTable)
    {
        super(connection, table, idTable);
    }

    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizar(EntidadeDominio entidade) throws SQLException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException
    {
        Historico historico = (Historico) entidade;
        PreparedStatement pst = null;
        List<EntidadeDominio> entidades = new ArrayList<>();

        try
        {
            openConnection();   //abrindo conexao com o banco!
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT ");
            sql.append("(SELECT SUM(h.qtde_compra) FROM historico_compras  AS h ");
            sql.append(" WHERE h.id_cliente = ? AND h.id_jogo = ?)");
            sql.append(" AS qtde_compra, e.quantidade ");
            sql.append(" FROM estoque AS e ");
            sql.append(" WHERE e.id_jogo = ?");
            
            pst = conn.prepareStatement(sql.toString());
            
            pst.setInt(1, historico.getIdCliente());
            pst.setInt(2, historico.getIdJogo());
            pst.setInt(3, historico.getIdJogo());
            
            ResultSet rs = pst.executeQuery();  //pegando resultado da consulta!
            
            if(rs.next())
            {                
                historico.setQuantidadeComprada(rs.getInt("qtde_compra"));
                historico.setQuantidadeEstoque(rs.getInt("quantidade"));
                
                entidades.add(historico);
            }
            
            return entidades;
            
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                conn.close();
                pst.close();
            }
            catch(SQLException ex)
            {
                ex.printStackTrace();
            }
        }
        
        return entidades;
    }

    @Override
    public void excluir(EntidadeDominio entidade) throws SQLException
    {
        PreparedStatement pst = null;
    }
}
