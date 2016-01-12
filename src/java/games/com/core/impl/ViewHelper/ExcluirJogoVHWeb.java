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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe Monteiro
 */
public class ExcluirJogoVHWeb implements IViewHelper
{
    private Jogo jogo;
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
        jogo = new Jogo();  //criando instancia do jogo!
        
        int id = Integer.parseInt(request.getParameter("txtIdJogo"));
        
        jogo.setId(id);
        jogo.setTitulo(request.getParameter("txtTitulo"));
        
        return jogo;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {        
        request.setAttribute("MsgExcluirJogo", jogo.getTitulo()+" inativado com sucesso!");
        
        try
        {
            request.getRequestDispatcher("/root/dashboard.jsp").forward(request, response);
        } catch (ServletException ex)
        {
            ex.printStackTrace();
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
}
