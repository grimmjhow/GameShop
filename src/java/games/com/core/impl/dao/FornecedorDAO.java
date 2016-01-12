/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.core.impl.dao;

import games.com.br.domain.Endereco;
import games.com.br.domain.EntidadeDominio;
import games.com.br.domain.Fornecedor;
import games.com.core.IDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author java
 */
public class FornecedorDAO extends AbstractJdbcDAO
{

    public FornecedorDAO(Connection connection, String table, String idTable)
    {
        super(connection, table, idTable);
    }

    public FornecedorDAO(String table, String idTable)
    {
        super(table, idTable);
    }

    public FornecedorDAO()
    {
        super("", "");
    } //construtor default

    @Override
    public void salvar(EntidadeDominio entidade)
    {
        IDAO enderecoDAO = new EnderecoDAO(conn, "enderecos", "id");

        //criando caminho nulo para o banco de dados
        PreparedStatement pst = null;
        try
        {
            enderecoDAO.salvar(((Fornecedor) entidade).getEndereco());   //salva primeiro endereco

            System.out.println("FK_ENDERECO: " + ((Fornecedor) entidade).getEndereco().getId());   //verificando FK do endereco

            if (conn == null || conn.isClosed())
            {
                //abrindo conexao com o banco
                openConnection();
            }

            conn.setAutoCommit(false);  //setando autocommit para false

            //criando string query de insert
            StringBuilder sb = new StringBuilder();

            sb.append("INSERT INTO FORNECEDORES (nome,cnpj,telefone,email,id_endereco) ");
            sb.append("VALUES (?,?,?,?,?)");  //criando sql de insercao na tabela fornecedores

            //abrindo caminho para a conexao
            pst = conn.prepareStatement(sb.toString());

            //setando parametros
            pst.setString(1, ((Fornecedor) entidade).getNome());
            pst.setString(2, ((Fornecedor) entidade).getCnpj());
            pst.setString(3, ((Fornecedor) entidade).getTelefone());
            pst.setString(4, ((Fornecedor) entidade).getEmail());
            pst.setInt(5, ((Fornecedor) entidade).getEndereco().getId());

            //executando a query no banco
            pst.execute();

            //commitando alteracoes no banco
            conn.commit();
        } catch (SQLException ex)
        {
            try
            {
                conn.rollback();
            } catch (SQLException ex1)
            {
                ex.printStackTrace();
                System.out.println("Erro ao executar Rollback (FornecedorDAO)");
            }
            ex.printStackTrace();
        } finally
        {
            if (ctrlTransaction)
            {
                try
                {
                    pst.close();    //fechando caminho

                    if (ctrlTransaction)
                    {
                        conn.close();   //fechando conexao
                    }
                } catch (SQLException ex)
                {
                    ex.printStackTrace();
                    System.out.println("Erro ao fechar a conexao (FORNECEDOR DAO)");
                }
            }
        }
    }

    @Override
    public void atualizar(EntidadeDominio entidade)
    {
        PreparedStatement pst = null;   //criando caminho para o banco
        try
        {
            if (conn == null || conn.isClosed())
            {
                openConnection();   //abrindo conexao com o banco de dados
            }
            //setando autocommit para false
            conn.setAutoCommit(false);

            //criando sql de update
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE FORNECEDORES SET ");
            sb.append("nome = (?),");
            sb.append("cnpj = (?),");
            sb.append("telefone = (?),");
            sb.append("email = (?) ");
            sb.append("WHERE ID = ?");

            IDAO dao = new EnderecoDAO();

            dao.atualizar(((Fornecedor) entidade).getEndereco());

            pst = conn.prepareStatement(sb.toString()); //abrindo caminho para a alteracao na base

            pst.setString(1, ((Fornecedor) entidade).getNome());
            pst.setString(2, ((Fornecedor) entidade).getCnpj());
            pst.setString(3, ((Fornecedor) entidade).getTelefone());
            pst.setString(4, ((Fornecedor) entidade).getEmail());
            pst.setInt(5, entidade.getId());

            pst.executeUpdate();    //executando sql no banco

            conn.commit();  //efetiva as alteracoes na base de dados
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
                if (ctrlTransaction)
                {
                    pst.close();
                }

                if (ctrlTransaction)
                {
                    conn.close();
                }
            } catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Buscar um Fornecedor na base de dados, utilizando como pesquisa o ID do
     * fornecedor
     *
     * @param entidade - Objeto Fornecedor com seu ID populado
     * @return - Retorna um objeto de Fornecedor com todos seus atributos
     * populados - Retorna null se o Fornecedor nao foi encontrado
     */
    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade)
    {
        PreparedStatement pst = null;
        List<EntidadeDominio> entidades = new ArrayList<>();

        try
        {
            if (conn == null || conn.isClosed()) //conexao esta fechada?
            {//conexao fechadas!
                openConnection();   //abrindo conexao com o banco
            }

            //criando sql 
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT * FROM FORNECEDORES ");
            sb.append("WHERE cnpj = (?)");

            //criando caminho para o banco
            pst = conn.prepareStatement(sb.toString());

            //setando parametros do sql
            pst.setString(1, ((Fornecedor) entidade).getCnpj());

            //pegando resultados da query de consulta
            ResultSet rs = pst.executeQuery();

            if (rs.next())//consegue avancar para o primeiro registro?
            {//avancando para o primeiro registro
                Fornecedor f = new Fornecedor();
                f.setId(rs.getInt("id"));
                f.setNome(rs.getString("nome"));
                f.setCnpj(rs.getString("cnpj"));
                f.setTelefone(rs.getString("telefone"));
                f.setEmail(rs.getString("email"));
                f.setEndereco(new Endereco());
                f.getEndereco().setId(rs.getInt("id_endereco"));

                IDAO dao = new EnderecoDAO();
                Endereco end = (Endereco) dao.consultar(f.getEndereco());
                f.setEndereco(end);

                entidades.add(f);   //adicionando um fornecedor na lista

                return entidades;
            }
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
            if (ctrlTransaction)
            {
                try
                {
                    pst.close();

                    if (ctrlTransaction)
                    {
                        conn.close();
                    }

                } catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
        }
        return entidades;
    }

    @Override
    public void excluir(EntidadeDominio entidade) throws SQLException
    {
        super.excluir(entidade);

        IDAO dao = new EnderecoDAO("enderecos", "id");

        dao.excluir(((Fornecedor) entidade).getEndereco());
    }
}
