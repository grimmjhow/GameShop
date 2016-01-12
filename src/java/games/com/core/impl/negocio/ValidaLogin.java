/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.core.impl.negocio;

import games.com.br.domain.Usuario;
import games.com.br.domain.EntidadeDominio;
import games.com.core.IDAO;
import games.com.core.IStrategy;
import games.com.core.impl.dao.ClienteDAO;
import java.sql.SQLException;

/**
 *
 * @author Felipe
 */
public class ValidaLogin implements IStrategy
{

    @Override
    public String processar(EntidadeDominio entidade)
    {
        Usuario cliente  = new Usuario();
        
        IDAO dao = new ClienteDAO();
        
        try
        {
            ((ClienteDAO)dao).consultaEmailSenha(entidade);
            
        } catch (SQLException ex)
        {
            return ex.getMessage();
        }
        return null;
    }
}
