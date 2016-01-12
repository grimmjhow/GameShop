/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.core.impl.dao;

import games.com.br.domain.EntidadeDominio;
import games.com.br.domain.Jogo;
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
public class JogoDAO extends AbstractJdbcDAO
{

    public JogoDAO(Connection connection, String table, String idTable)
    {
        super(connection, table, idTable);
    }

    public JogoDAO()
    {
        super("games", "id");
    }

    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException
    {
        PreparedStatement pst = null;
        Jogo jogo = (Jogo) entidade;
        try
        {
            openConnection();   //abrindo conexao com o jogo
            conn.setAutoCommit(false);

            StringBuilder sql = new StringBuilder();

            sql.append("INSERT INTO JOGOS ");
            sql.append("(TITULO,DESCRICAO,FAIXA_ETARIA,GENERO,VALOR,ANO_LANCAMENTO,MULTIPLAYER,ONLINE,IDIOMAS,IMAGE,QUANTIDADE_ESTOQUE,ATIVO) ");
            sql.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");

            pst = conn.prepareStatement(sql.toString());
            //setando os parametros 
            pst.setString(1, jogo.getTitulo());
            pst.setString(2, jogo.getDescricao());
            pst.setString(3, jogo.getFaixaEtaria());
            pst.setString(4, jogo.getGenero());
            pst.setFloat(5, jogo.getValor());
            pst.setString(6, jogo.getAno());
            pst.setString(7, jogo.getMultiplayer());
            pst.setString(8, jogo.getOnline());
            pst.setString(9, jogo.getIdioma());
            pst.setString(10, jogo.getImage());
            pst.setInt(11, jogo.getQuantidade());
            pst.setInt(12, 1);
            
            pst.execute();  //salva as alteracoes no banco!

            conn.commit();  //commita as alteracoes no banco de dados!
            
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
                conn.close();
                pst.close();
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
        Jogo jogo = (Jogo) entidade;
        StringBuilder sql = new StringBuilder();

        try
        {
            openConnection();   //abrindo a conexao com o banco!
            conn.setAutoCommit(false);

            if (jogo.getQuantidade() != 0)
            {
                sql.append("UPDATE JOGOS SET ");
                sql.append("quantidade_estoque = ? ");
                sql.append("WHERE ID = ?");

                pst = conn.prepareStatement(sql.toString());

                pst.setInt(1, jogo.getQuantidade());
                pst.setInt(2, jogo.getId());
            } 
            else
            {
                sql.append("UPDATE JOGOS SET ");
                sql.append("titulo = ?, ");
                sql.append("descricao = ?, ");
                sql.append("faixa_etaria = ?, ");
                sql.append("genero = ?, ");
                sql.append("valor = ?, ");
                sql.append("ano_lancamento = ?, ");
                sql.append("multiplayer = ?, ");
                sql.append("online = ?, ");
                sql.append("idiomas = ? ");
                sql.append("WHERE id = ?");

                pst = conn.prepareStatement(sql.toString());

                pst.setString(1, jogo.getTitulo());
                pst.setString(2, jogo.getDescricao());
                pst.setString(3, jogo.getFaixaEtaria());
                pst.setString(4, jogo.getGenero());
                pst.setDouble(5, jogo.getValor());
                pst.setString(6, jogo.getAno());
                pst.setString(7, jogo.getMultiplayer());
                pst.setString(8, jogo.getOnline());
                pst.setString(9, jogo.getIdioma());
                /*pst.setString(10, jogo.getImage());
                 pst.setInt(11, jogo.getQuantidade());*/
                pst.setInt(10, jogo.getId());
            }

            pst.executeUpdate();
            
            conn.commit();
        } catch (SQLException ex)
        {
            conn.rollback();
            ex.printStackTrace();
        } finally
        {
            pst.close();
            conn.close();
        }
    }

    @Override
    @SuppressWarnings("null")
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException
    {
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();
        List<EntidadeDominio> games = new ArrayList<>();
        Jogo j = (Jogo) entidade;
        List<String> parametros = new ArrayList<String>();
        try
        {
            openConnection();   //abrindo conexao com o banco de dados

            //evitando nullpointer no inicio do programa
            if (j.getTitulo() == null)
            {
                j.setTitulo("");
            }

            if (j.getGenero() == null)
            {
                j.setGenero("");
            }

            if (j.getFaixaEtaria() == null)
            {
                j.setFaixaEtaria("");
            }

            if (j.getAno() == null)
            {
                j.setAno("");
            }

            sql.append("SELECT * FROM JOGOS WHERE ");

            if (!j.getTitulo().equals(""))
            {
                sql.append("TITULO ILIKE ? and ");
                parametros.add("%" + j.getTitulo() + "%");
            }

            if (!j.getGenero().equals("") && !j.getGenero().equals("Genero"))
            {
                sql.append("GENERO ILIKE ? and ");
                parametros.add("%" + j.getGenero() + "%");
            }

            if (!j.getFaixaEtaria().equals(""))
            {
                sql.append("FAIXA_ETARIA = ? and ");
                parametros.add(j.getFaixaEtaria());
            }

            if (!j.getAno().equals(""))
            {
                sql.append("ANO_LANCAMENTO = ? and ");
                parametros.add(j.getAno());
            }
            
            sql.append("ativo = 1 ORDER BY quantidade_estoque ASC");//colocando
            
            if (parametros.isEmpty())
            {
                sql = new StringBuilder("SELECT * FROM JOGOS WHERE ativo = 1 ORDER BY quantidade_estoque ASC");
            }

            pst = conn.prepareStatement(sql.toString());

            if (!parametros.isEmpty())
            {
                int i = 1;
                for (String p : parametros)
                {
                    pst.setString(i, p);
                    i++;
                }
            }
            /*pst.setString(1, "%" + j.getTitulo() + "%");
             pst.setString(2, "%" + j.getGenero() + "%");
             pst.setString(3, j.getFaixaEtaria());
             pst.setString(4, j.getAno()); */

            ResultSet rs = pst.executeQuery();

            while (rs.next())
            {
                Jogo jogo = new Jogo();
                jogo.setId(rs.getInt("id"));
                jogo.setAno(rs.getString("ano_lancamento"));
                jogo.setTitulo(rs.getString("titulo"));
                jogo.setDescricao(rs.getString("descricao"));
                jogo.setGenero(rs.getString("genero"));
                jogo.setFaixaEtaria(rs.getString("faixa_etaria"));
                jogo.setValor(rs.getFloat("valor"));
                jogo.setMultiplayer(rs.getString("multiplayer"));
                jogo.setOnline(rs.getString("online"));
                jogo.setIdioma(rs.getString("idiomas"));
                jogo.setImage(rs.getString("image"));
                jogo.setQuantidade(rs.getInt("quantidade_estoque"));

                games.add(jogo);    //adicionando jogo na lista de jogos!
            }

            return games;

        } catch (SQLException ex)
        {
            ex.printStackTrace();
        } finally
        {
            conn.close();
            pst.close();
        }
        return null;
    }

    public List<EntidadeDominio> consultarTodos() throws SQLException
    {
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();
        List<EntidadeDominio> games = new ArrayList<>();

        try
        {
            openConnection();   //abrindo conexao com o banco de dados

            sql.append("SELECT * FROM JOGOS ");

            pst = conn.prepareStatement(sql.toString());

            ResultSet rs = pst.executeQuery();

            while (rs.next())
            {
                Jogo jogo = new Jogo();
                jogo.setId(rs.getInt("id"));
                jogo.setTitulo(rs.getString("titulo"));
                jogo.setDescricao(rs.getString("descricao"));
                jogo.setGenero(rs.getString("genero"));
                jogo.setFaixaEtaria(rs.getString("faixa_etaria"));
                jogo.setValor(rs.getFloat("valor"));
                jogo.setImage(rs.getString("image"));
                jogo.setIdioma(rs.getString("idiomas"));
                jogo.setQuantidade(rs.getInt("quantidade_estoque"));
                games.add(jogo);    //adicionando jogo na lista de jogos!
            }

            if (!games.isEmpty())
            {
                return games;
            } else
            {
                return null;
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        } finally
        {
            conn.close();
            pst.close();
        }
        return null;
    }

    public void atualizarAquantidade(EntidadeDominio entidade) throws SQLException
    {
        PreparedStatement pst = null;
        Jogo jogo = (Jogo) entidade;
        try
        {
            if (conn == null || conn.isClosed()) //conexao fechada?
            {
                openConnection();   //abrindo a conexao com o banco!
            }
            if (!ctrlTransaction)
            {
                conn.setAutoCommit(false);
            }

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE JOGOS SET ");
            sql.append("quantidade_estoque = quantidade_estoque - (?) ");
            sql.append("WHERE id = ? and quantidade_estoque >= ?");

            pst = conn.prepareStatement(sql.toString());

            pst.setInt(1, jogo.getQuantidade());
            pst.setInt(2, jogo.getId());
            pst.setInt(3, jogo.getQuantidade());

            pst.executeUpdate();

            if (!ctrlTransaction)
            {
                conn.commit();
            }

        } catch (SQLException ex)
        {
            if (!ctrlTransaction)
            {
                conn.rollback();
            }
            ex.printStackTrace();
        } finally
        {
            if (!ctrlTransaction)
            {
                pst.close();
                conn.close();
            }
        }
    }

    @Override
    public void excluir(EntidadeDominio entidade) throws SQLException
    {
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();
        Jogo jogo = (Jogo) entidade;
        try
        {
            openConnection();   //abrindo a conexao com o banco de dados
            conn.setAutoCommit(false);

            sql.append("UPDATE JOGOS SET ");
            sql.append("ativo = 0 ");
            sql.append("WHERE id = ?");

            pst = conn.prepareStatement(sql.toString());

            pst.setInt(1, jogo.getId());

            pst.executeUpdate();    //executando query do programa!

            conn.commit();  //efetivando as alteracoes
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
