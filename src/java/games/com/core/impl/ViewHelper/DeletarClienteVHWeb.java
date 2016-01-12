/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.core.impl.ViewHelper;

import games.com.br.application.Resultado;
import games.com.br.domain.EntidadeDominio;
import games.com.br.domain.Usuario;
import games.com.core.IViewHelper;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe Monteiro
 */
public class DeletarClienteVHWeb implements IViewHelper
{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
        Usuario usuario = (Usuario) request.getSession().getAttribute("user");
        
        return usuario;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
        if(!resultado.getListMensagens().isEmpty())
        {
            request.setAttribute("MsgDesativarErro", resultado.getListMensagens());
        }
        
        try
        {
            request.getSession().invalidate(); //invalida sessao do usuario!
            request.getRequestDispatcher("DesativarConta.jsp").forward(request, response);
        }
        catch (ServletException ex)
        {
            Logger.getLogger(DeletarClienteVHWeb.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex)
        {
            Logger.getLogger(DeletarClienteVHWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
