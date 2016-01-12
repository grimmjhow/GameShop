/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.core.impl.dao;

import games.com.br.domain.EntidadeDominio;
import games.com.br.domain.RelatorioClientesCompras;
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
public class RelatorioComprasDAO extends AbstractJdbcDAO
{

    public RelatorioComprasDAO()
    {
        super(null, null);
    }
    
    public RelatorioComprasDAO(Connection connection, String table, String idTable)
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
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();
        List<EntidadeDominio> entidades = new ArrayList<>();
        try
        {
            openConnection();   //abrindo conexao com o banco de dados!
            
            sql.append("SELECT DISTINCT ");
            sql.append("c.nome, count(p.id) as compras,date_part('month',p.data_normal) as dt ");
            sql.append("FROM clientes as c, pedidos as p, pedidos_jogos as pj ");
            sql.append("WHERE p.id_cliente = c.id and p.id = pj.id_pedidos and date_part('month',p.data_normal) between 4 and 6 ");
            sql.append("GROUP BY c.nome, date_part('month',p.data_normal) ");
            sql.append("ORDER BY dt,compras ASC ");
            
            pst = conn.prepareStatement(sql.toString());
            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next())
            {
                RelatorioClientesCompras jogos = new RelatorioClientesCompras();
                
                jogos.setTitulo(rs.getString("nome"));
                jogos.setQuantidade(rs.getInt("compras"));
                jogos.setData(rs.getString("dt"));
                
                entidades.add(jogos);
            }
            
            return entidades;
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally
        {
            conn.close();
        }
    }    
}
