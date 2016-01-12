/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.core.impl.dao;

import games.com.br.domain.Usuario;
import games.com.br.domain.Endereco;
import games.com.br.domain.EnderecoEntrega;
import games.com.br.domain.EntidadeDominio;
import games.com.core.IDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Felipe
 */
public class ClienteDAO extends AbstractJdbcDAO
{

    public ClienteDAO(Connection connection, String table, String idTable)
    {
        super(connection, table, idTable);
    }

    public ClienteDAO(String table, String idTable)
    {
        super(table, idTable);
    }

    public ClienteDAO(Connection connection)
    {
        super(connection, "clientes", "id");
    }

    public ClienteDAO()
    {
        super("clientes", "id");
    }

    @Override
    public void salvar(EntidadeDominio entidade) throws SQLException
    {
        PreparedStatement pst = null;
        int idCliente;
        try
        {
            openConnection(); //abrindo conexao
            conn.setAutoCommit(false);  //setando ao commit para false

            IDAO dao = new EnderecoDAO(conn, "enderecos", "id");

            dao.salvar(((Usuario) entidade).getEndereco()); //salva o endereco no banco de dados primeiro!!
            //se nao conseguir salvar, vai gerar uma exception

            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO CLIENTES ");
            sql.append("(nome,cpf,sexo,email,telefone,id_endereco,dt_nasc,dt_cad,senha,status,tipo) ");
            sql.append("VALUES (?,?,?,?,?,?,?,?,?,?,?)");

            //executando sql e retornando ultima chave inserida
            pst = conn.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, ((Usuario) entidade).getNome());
            pst.setString(2, ((Usuario) entidade).getCpf());
            pst.setString(3, ((Usuario) entidade).getSexo());
            pst.setString(4, ((Usuario) entidade).getEmail());
            pst.setString(5, ((Usuario) entidade).getTelefone());
            pst.setInt(6, ((Usuario) entidade).getEndereco().getId());
            pst.setTimestamp(7, new Timestamp(((Usuario) entidade).getDataNascimento().getTimeInMillis()));
            pst.setTimestamp(8, new Timestamp(entidade.getDate().getTimeInMillis()));
            pst.setString(9, ((Usuario) entidade).getSenha());
            pst.setInt(10, 1);
            pst.setString(11, "cliente");
            
            pst.execute();//executa no sql no banco
            
            ResultSet rs = pst.getGeneratedKeys();
            
            if(rs.next())
            {
                entidade.setId(rs.getInt("id"));
            }
            
            dao = new SaldoDAO(conn, null, null);
            
            dao.salvar(entidade);   //salvando saldo do cliente
            
            conn.commit();  //efetiva as alteracoes no banco de dados

        } catch (SQLException ex)
        {
            ex.printStackTrace();
            try
            {
                conn.rollback();
            } catch (SQLException ex1)
            {
                throw new SQLDataException(ex1);
            }
            throw new SQLException(ex);
        } finally
        {
            pst.close();

            conn.close();
        }
    }

    @Override
    public void atualizar(EntidadeDominio entidade) throws SQLException
    {
        PreparedStatement pst = null;
        try
        {
            openConnection();   //abrindo conexao com o banco de dados
            conn.setAutoCommit(false);  //setando autocommit

            //atualizando dados do endereco
            IDAO dao = new EnderecoDAO(conn, "enderecos", "id");

            dao.atualizar(((Usuario) entidade).getEndereco());   //atualiza o endereco

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE CLIENTES SET ");
            sql.append("NOME = ?,");
            sql.append("CPF = ?,");
            sql.append("SEXO = ?,");
            sql.append("EMAIL = ?,");
            sql.append("TELEFONE = ?,");
            sql.append("DT_NASC = ?, ");
            sql.append("SENHA = ? ");
            sql.append("WHERE ID = ?");

            //setando parametros da query
            pst = conn.prepareStatement(sql.toString());
            Usuario cliente = (Usuario) entidade;
            pst.setString(1, cliente.getNome());
            pst.setString(2, cliente.getCpf());
            pst.setString(3, cliente.getSexo());
            pst.setString(4, cliente.getEmail());
            pst.setString(5, cliente.getTelefone());
            Timestamp dtNasc = new Timestamp(cliente.getDataNascimento().getTimeInMillis());
            pst.setTimestamp(6, dtNasc);
            pst.setString(7, cliente.getSenha());
            pst.setInt(8, cliente.getId());

            pst.executeUpdate();    //executando a query de update

            conn.commit();  //efetivando as alteracoes no banco
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
            //pst.close();    //fecha o caminho 
            conn.close();   //fecha a conexao com a query
        }
    }

    @Override
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException
    {
        PreparedStatement pst = null;
        List<EntidadeDominio> clientes = new ArrayList<>();
        Usuario cliente = (Usuario) entidade;

        try
        {
            openConnection();   //abrindo conexao com o banco
            conn.setAutoCommit(false);
            String atributo = "";   //atributo 
            StringBuilder sql = gerateQuery(entidade);
            
            pst = conn.prepareStatement(sql.toString());
            
            //apartir daqui o codigo eh beta!
            pst.setString(1, cliente.getEmail());
            pst.setString(2, cliente.getCpf());
            pst.setString(3, cliente.getNome());
            
            /*if (cliente.getId() != 0)
            {
                pst.setInt(1, cliente.getId());
            } else
            {
                pst.setString(1, atributo);
            }*/

            ResultSet rs = pst.executeQuery();

            while (rs.next())
            {
                Usuario c = new Usuario();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("Nome"));
                c.setRg(rs.getString("rg"));
                c.setCpf(rs.getString("cpf"));
                c.setSexo(rs.getString("sexo"));
                c.setEmail(rs.getString("email"));
                c.setTelefone(rs.getString("telefone"));
                c.setCelular(rs.getString("celular"));
                c.setStatus(rs.getInt("status"));
                c.setEndereco(new Endereco());

                java.util.Date dt = new Date(rs.getTimestamp("dt_nasc").getTime());
                c.setDataNascimento(Calendar.getInstance());
                c.getDataNascimento().setTime(dt);

                c.getEndereco().setId(rs.getInt("id_endereco"));
                IDAO dao = new EnderecoDAO(conn, "enderecos", "id");

                List<EntidadeDominio> entidades = dao.consultar(c.getEndereco());

                Endereco end = (Endereco) entidades.get(0);

                end.setId(end.getId());
                end.setLogradouro(end.getLogradouro());
                end.setBairro(end.getBairro());
                end.setCEP(end.getCEP());
                end.setCidade(end.getCidade());
                end.setEstado(end.getEstado());
                end.setNumero(end.getNumero());
                c.setEndereco(end);

                c.setEnderecoEntrega(new EnderecoEntrega());
                c.getEnderecoEntrega().setId(rs.getInt("id_endereco_entrega"));
                IDAO daoEntrega = new EnderecoEntregaDAO(conn, "", "");

                List<EntidadeDominio> entregas = daoEntrega.consultar(c.getEnderecoEntrega());

                EnderecoEntrega entrega = (EnderecoEntrega) entregas.get(0);

                c.setEnderecoEntrega(entrega);

                clientes.add(c);
            }
            return clientes;

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
            pst.close();
            conn.close();
        }
        return clientes;
    }

    @Override
    public void excluir(EntidadeDominio entidade) throws SQLException
    {
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder();
        
        try
        {
            if(conn == null || conn.isClosed())
                openConnection();   //abrindo conexao com banco de dados!
            
            conn.setAutoCommit(false);  //setando auto commit para false!
            
            sql.append("UPDATE CLIENTES SET ");
            sql.append("STATUS = 0 ");
            sql.append("WHERE ID = ?");
            
            pst = conn.prepareStatement(sql.toString());
            
            pst.setInt(1, entidade.getId());
            
            pst.executeUpdate();
            
            conn.commit();  //commitando as alteracoes
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
        }
        finally
        {
            try
            {
                conn.close();
            }
            catch(SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    public void consultaEmailSenha(EntidadeDominio entidade) throws SQLException
    {
        try
        {
            PreparedStatement pst;
            StringBuilder sql = new StringBuilder();

            Usuario cliente = (Usuario) entidade;

            openConnection();   //abrindo conexao
            sql.append("SELECT * FROM CLIENTES ");
            sql.append("WHERE EMAIL = ? AND SENHA = ? AND STATUS = 1");

            pst = conn.prepareStatement(sql.toString());
            pst.setString(1, cliente.getEmail());
            pst.setString(2, cliente.getSenha());

            ResultSet rs = pst.executeQuery();

            if (rs.next())
            {
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("Nome"));
                cliente.setRg(rs.getString("rg"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setSexo(rs.getString("sexo"));
                cliente.setEmail(rs.getString("email"));
                cliente.setSenha(rs.getString("senha"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setCelular(rs.getString("celular"));
                cliente.setTipo(rs.getString("tipo"));
                cliente.setEndereco(new Endereco());

                java.util.Date dt = new Date(rs.getTimestamp("dt_nasc").getTime());
                cliente.setDataNascimento(Calendar.getInstance());
                cliente.getDataNascimento().setTime(dt);

                cliente.getEndereco().setId(rs.getInt("id_endereco"));
                IDAO dao = new EnderecoDAO(conn, "enderecos", "id");
                List<EntidadeDominio> entidades = dao.consultar(cliente.getEndereco());

                Endereco end = (Endereco) entidades.get(0);

                end.setId(end.getId());
                end.setLogradouro(end.getLogradouro());
                end.setBairro(end.getBairro());
                end.setCEP(end.getCEP());
                end.setCidade(end.getCidade());
                end.setEstado(end.getEstado());
                end.setNumero(end.getNumero());
                cliente.setEndereco(end);
                
                if(conn == null || conn.isClosed())
                    openConnection();
                
                sql = new StringBuilder();  //criando nova instancia para gerar uma nova query!
                
                sql.append("SELECT id ");
                sql.append("FROM enderecos_entrega ");
                sql.append("WHERE id_cliente = ?");
                
                pst = conn.prepareStatement(sql.toString());
                
                pst.setInt(1, cliente.getId());
                
                rs = pst.executeQuery();    //pegando resultado da query
                
                IDAO daoEntrega = new EnderecoEntregaDAO(conn, "", "");
                
                while(rs.next())
                {
                  EnderecoEntrega entrega = new EnderecoEntrega(rs.getInt("id"));
                  
                  List<EntidadeDominio> entregas = daoEntrega.consultar(entrega);
                  
                  entrega = (EnderecoEntrega) entregas.get(0);
                  
                  cliente.addEnderecos(entrega);
                }

            } else
            {
                throw new SQLException("Email ou Senha Invalidos!");
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new SQLException("Email ou Senha Invalidos!");
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
    
    public StringBuilder gerateQuery(EntidadeDominio entidade)
    {
        Usuario usuario = (Usuario) entidade;
        StringBuilder query = new StringBuilder();
        /**
         * Caso a QUERY SEJA APENAS DE CONSULTA!
         */
        query.append("SELECT * FROM CLIENTES ");
        query.append("WHERE ");
        query.append("email = ? or cpf = ? or nome = ?");
        
        return query;
    }
}
