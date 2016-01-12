/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.core.impl.dao;

import games.com.br.domain.EntidadeDominio;
import games.com.br.domain.ItemPedido;
import games.com.br.domain.Jogo;
import games.com.br.domain.Pedido;
import games.com.br.domain.PedidoJogo;
import games.com.core.IDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Felipe
 */
public class PedidoJogoDAO extends AbstractJdbcDAO
{

    public PedidoJogoDAO(Connection connection, String table, String idTable)
    {
        super(connection, table, idTable);
    }

    public PedidoJogoDAO(Connection connection)
    {
        super(connection, "pedidos_jogos", "id");
    }

    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException
    {
        PreparedStatement pst = null;
        PedidoJogo pedidoJogo = (PedidoJogo) entidade;

        try
        {
            if(conn == null || conn.isClosed())
            {     
                openConnection();
                conn.setAutoCommit(false);  //setando auto commit para false!
            }   //abrindo conexao com o banco de dados!
            
            StringBuilder sql = new StringBuilder();    //string para conter a query!

            sql.append("INSERT INTO PEDIDOS_JOGOS ");
            sql.append("(id_pedidos,id_jogos,quantidade,desconto) VALUES ");
            sql.append("(?,?,?,?)");

            pst = conn.prepareStatement(sql.toString());    //criando query de conexao!

            pst.setInt(1, pedidoJogo.getIdPedido());
            pst.setInt(2, pedidoJogo.getIdJogo());
            pst.setInt(3, pedidoJogo.getQuantidade());
            pst.setFloat(4, pedidoJogo.getItem().getDesconto());
            
            pst.execute();  //executando query no banco!
            
        } catch (SQLException ex)
        {
            try
            {
                conn.rollback();
            } catch (SQLException ex1)
            {
                ex1.printStackTrace();
            }
            ex.printStackTrace();
            throw new SQLDataException(ex);
        } finally
        {
            if (!ctrlTransaction)
            {
                try
                {
                    conn.close();
                    pst.close();
                }
                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public void atualizar(EntidadeDominio entidade) throws SQLException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //retorna uma lista com os jogos do pedido!
    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException
    {
        PreparedStatement pst = null;
        Pedido pedido = (Pedido) entidade;
        try
        {
            if(conn == null || conn.isClosed())
                openConnection();   //abrindo conexao
            
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM PEDIDOS_JOGOS WHERE ID_PEDIDOS = ?");
            
            pst = conn.prepareStatement(sql.toString());
            
            pst.setInt(1, pedido.getId());
            
            ResultSet rs = pst.executeQuery();
            
            IDAO dao = new JogoDAO(conn, "", "");
            List<EntidadeDominio> itens = new ArrayList<>();
            float desconto = 0;
            while(rs.next())
            {
                Jogo jogo = new Jogo();
                jogo.setId(rs.getInt("id_jogos"));
                
                List<EntidadeDominio> jogos = dao.consultar(jogo);  //pegando lista de jogos na busca
                
                jogo = (Jogo) jogos.get(0); //pegando primeiro jogo da lista   

                ItemPedido item = new ItemPedido(); //criando Item pedido
                item.setDesconto(rs.getFloat("desconto"));
                item.setJogo(jogo);
                item.setQuantidade(rs.getInt("quantidade"));
                
                itens.add(item);    //retorna a lista de itens
            }
            return itens;
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            return null;
        }
        finally
        {
            try
            {
                conn.close();
            }
            catch(SQLException ex)
            {
                
            }
        }
    }
}
