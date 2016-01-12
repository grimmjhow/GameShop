/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.core.impl.ViewHelper;

import games.com.br.application.Resultado;
import games.com.br.domain.EntidadeDominio;
import games.com.core.IViewHelper;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe Monteiro
 */
public class LimparCarrinhoVHWeb implements IViewHelper
{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
        request.getSession().setAttribute("carrinho", null);    //limpando carrinho de compras!
        
        return null;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
        try
        {
            response.sendRedirect("Games.jsp"); //envia usuario para tela principal!
        } catch (IOException ex)
        {
            Logger.getLogger(LimparCarrinhoVHWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
