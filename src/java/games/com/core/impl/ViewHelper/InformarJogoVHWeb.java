/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.core.impl.ViewHelper;

import games.com.br.application.Resultado;
import games.com.br.domain.EntidadeDominio;
import games.com.br.domain.Jogo;
import games.com.core.IViewHelper;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe
 */
public class InformarJogoVHWeb implements IViewHelper
{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
        Jogo jogo = new Jogo();
        
        jogo.setTitulo(request.getParameter("txtTitulo"));
        
        return jogo;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
        request.setAttribute("jogo", resultado.getEntidades().get(0));
        
        try
        {
            request.getRequestDispatcher("VisualizarJogo.jsp").forward(request, response);
        } catch (ServletException ex)
        {
            Logger.getLogger(InformarJogoVHWeb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(InformarJogoVHWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
