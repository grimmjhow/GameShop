/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.core.impl.dao;

import games.com.br.domain.Endereco;
import games.com.br.domain.EntidadeDominio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author java
 */
public class EnderecoDAO extends AbstractJdbcDAO
{

    public EnderecoDAO(Connection connection, String table, String idTable)
    {
        super(connection, table, idTable);
    }

    public EnderecoDAO()
    {
        super("enderecos", "id");
    }
    
    public EnderecoDAO(String table, String idTable)
    {
        super(table,idTable);
    }

    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException
    {
        PreparedStatement pst = null;
        try
        {
            if (conn == null || conn.isClosed())
            {
                openConnection();   // abrindo conexao
            }
            //criando sql para inser no banco
            StringBuilder sql = new StringBuilder();

            sql.append("INSERT INTO ENDERECOS ");
            sql.append("(LOGRADOURO,CEP,BAIRRO,ESTADO,CIDADE,NUMERO) ");
            sql.append(" VALUES (?,?,?,?,?,?) ");

            //setando auto commit para false
            conn.setAutoCommit(false);

            //criando caminho para conexao no banco de dados
            pst = conn.prepareStatement(sql.toString(),
                    Statement.RETURN_GENERATED_KEYS);   //retorna a ultima chave inserida no banco

            //setando parametros do insert
            pst.setString(1, ((Endereco) entidade).getLogradouro());
            pst.setString(2, ((Endereco) entidade).getCEP());
            pst.setString(3, ((Endereco) entidade).getBairro());
            pst.setString(4, ((Endereco) entidade).getEstado());
            pst.setString(5, ((Endereco) entidade).getCidade());
            pst.setString(6,((Endereco)entidade).getNumero());

            pst.executeUpdate();  //executando a query no banco de dados

            //pegando id da ultima insercao no banco
            ResultSet rs = pst.getGeneratedKeys();

            //se conseguir interar pelo menos 1 vez
            if (rs.next())
            {//conseguiu iterar
                entidade.setId(rs.getInt("id"));
            }
            //confirmando alteracoes no banco
            //conn.commit();
        } catch (SQLException ex)
        {
            try
            {
                conn.rollback();    //nao efetua as alteracoes no banco
            } catch (SQLException ex1)
            {
                ex1.printStackTrace();  //imprime o codigo de erro no console
                throw new SQLException(ex1);
            }
            ex.printStackTrace();
            throw new SQLException(ex);
        } finally
        {
            if (!ctrlTransaction)
            {
                try
                {
                    pst.close();

                    if (!ctrlTransaction)
                    {
                        conn.close();    //fecha a conexao com o banco
                    }
                } catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    /**
     * Atualiza um Endereco na Base de dados, usando como parametro seu ID
     * @param entidade - Objeto de Endereco com seu ID populado
     * @throws java.sql.SQLException
     */
    @Override
    public void atualizar(EntidadeDominio entidade) throws SQLException
    {
        PreparedStatement pst = null;   //criando caminho para executar sql no banco
        try
        {
            if(conn == null || conn.isClosed())
            {
                openConnection();   //abrindo conexao com o banco
            }
            conn.setAutoCommit(false);  //setando autocommit para false
            
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE ENDERECOS SET ");
            sb.append("LOGRADOURO = (?),");
            sb.append("BAIRRO = (?),");
            sb.append("CEP = (?),");
            sb.append("CIDADE = (?),");
            sb.append("NUMERO = (?),");
            sb.append("ESTADO = ? ");
            sb.append("WHERE ID = (?)");
            
            pst = conn.prepareStatement(sb.toString()); //abrindo caminho para a conexao com o banco
            pst.setString(1,((Endereco)entidade).getLogradouro());
            pst.setString(2, ((Endereco)entidade).getBairro());
            pst.setString(3, ((Endereco)entidade).getCEP());
            pst.setString(4, ((Endereco)entidade).getCidade());
            pst.setString(5, ((Endereco)entidade).getNumero());
            pst.setString(6, ((Endereco)entidade).getEstado());
            pst.setInt(7,((Endereco)entidade).getId());
            
            pst.executeUpdate();    //executando query de atualizacao
            conn.commit();  //efetivando as alteracoes
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            try
            {
                conn.rollback();    //desfaz as alteracoes no banco de dados
            } 
            catch (SQLException ex1)
            {
                ex1.printStackTrace();
            }
        }
        finally
        {
            try
            {
                if(!ctrlTransaction)
                    pst.close();
                
                if(!ctrlTransaction)
                    conn.close();
            }
            catch(SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Busca e retorna um Endereco no banco de dados, utilizando seu ID como pesquisa
     * @param entidade - Subclasse de Entidade que contenha um ID populado
     * @return  - Um objeto de Endereco com todos os campos preenchidos
     */
    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade)
    {
        PreparedStatement pst = null;   //abrindo caminho para o banco de dados
        List<EntidadeDominio> entidades = new ArrayList<>();
        try
        {
            if(conn == null || conn.isClosed())
            {
                openConnection();//abre a conexao se ela estiver fechada
            }
            
            conn.setAutoCommit(false);  //setando auto commit para false
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT * FROM ENDERECOS ");
            sb.append("WHERE ID = (?)");
            
            pst = conn.prepareStatement(sb.toString());
            
            pst.setInt(1,entidade.getId()); //setando parametro
            
            ResultSet rs = pst.executeQuery();  //pegando resultado da consulta
            
            if(rs.next())
            {
                Endereco end = new Endereco();
                end.setId(rs.getInt("id"));
                end.setLogradouro(rs.getString("logradouro"));
                end.setBairro(rs.getString("bairro"));
                end.setCEP(rs.getString("cep"));
                end.setEstado(rs.getString("estado"));
                end.setCidade(rs.getString("cidade"));
                end.setNumero(rs.getString("numero"));
                entidades.add(end);
            }
            return entidades;
        }
        catch(SQLException ex)
        {
            try
            {
                conn.rollback();
            } 
            catch (SQLException ex1)
            {
                ex1.printStackTrace();
            }
        }
        finally
        {
            try 
            {
                if(ctrlTransaction)
                pst.close();
                
                if(ctrlTransaction)
                conn.close();
            }
            catch (SQLException ex) 
            {
                ex.printStackTrace();
            }
        }
        return entidades;
    }
}
