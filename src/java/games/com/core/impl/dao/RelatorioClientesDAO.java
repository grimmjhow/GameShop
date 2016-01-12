/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.core.impl.dao;

import games.com.br.domain.EntidadeDominio;
import games.com.br.domain.RelatorioClientes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Felipe Monteiro
 */
public class RelatorioClientesDAO extends AbstractJdbcDAO
{

    public RelatorioClientesDAO()
    {
        super(null, null);
    }

    public RelatorioClientesDAO(Connection connection, String table, String idTable)
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
            openConnection();   //abrindo conexao
            
            sql.append("SELECT DISTINCT ");
            sql.append("c.id,c.nome, sum(p.total) as gasto, count(c.id) as compras,(2015 - date_part('year',c.dt_nasc)) as idade, date_part('month',p.data_normal) as dt ");
            sql.append("FROM clientes as c, pedidos as p, pedidos_jogos as pj ");
            sql.append("WHERE p.id_cliente = c.id and p.id = pj.id_pedidos and date_part('month',p.data_normal) between 4 and 6 ");
            sql.append("GROUP BY c.id, c.nome,c.dt_nasc, date_part('month',p.data_normal) ");
            sql.append("ORDER BY dt,gasto ASC ");
            
            pst = conn.prepareStatement(sql.toString());
            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next())
            {
                RelatorioClientes rc = new RelatorioClientes();
                
                rc.setNome(rs.getString("nome"));
                rc.setGasto(rs.getFloat("gasto"));
                rc.setCompras(rs.getInt("compras"));
                rc.setIdade(rs.getString("idade"));
                rc.setDt(rs.getString("dt"));
                
                entidades.add(rc);
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
