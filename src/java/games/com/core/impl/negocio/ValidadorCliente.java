/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.core.impl.negocio;

import games.com.br.domain.EntidadeDominio;
import games.com.br.domain.Usuario;
import games.com.core.IDAO;
import games.com.core.IStrategy;
import games.com.core.impl.dao.ClienteDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Felipe
 */
public class ValidadorCliente implements IStrategy
{
/**
 * Metodo para buscar um cliente em um banco de dados
 * @param entidade - Subclasse Cliente 
 * @return null - se o cliente nao esta cadastrado ou uma String caso o clinete exista
 * 
 */
    @Override
    public String processar(EntidadeDominio entidade)
    {
        IDAO dao = new ClienteDAO();
        Usuario us1 = (Usuario) entidade;
        Usuario us2 = new Usuario();
        try
        {
            List<EntidadeDominio> resultado = dao.consultar(entidade);
            
            if(!resultado.isEmpty())
            {
                us2 = (Usuario) resultado.get(0);
            
                if(us2.getEmail().equals(us1.getEmail()) && us2.getCpf().equals(us1.getCpf()))
                    return "Email e CPF já cadastrados!";
                if(us2.getEmail().equals(us1.getEmail()))
                    return "Email já cadastrado!";
                else if(us2.getCpf().equals(us1.getCpf()))
                    return "CPF já cadastrado";
            }
             return null;   //se nao entrar em nenhuma dessas condicoes, estah tudo certo!
            
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            return "Erro ao achar cliente";
        }
    }
}
