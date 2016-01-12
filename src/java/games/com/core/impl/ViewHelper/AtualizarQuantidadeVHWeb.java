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
 * @author Felipe Monteiro
 */
public class AtualizarQuantidadeVHWeb implements IViewHelper
{
    public AtualizarQuantidadeVHWeb()   //construtor default
    {
        
    }

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
        int quantidade = Integer.parseInt(request.getParameter("txtQuantidade"));
        int id = Integer.parseInt(request.getParameter("txtId"));
        
        Jogo jogo = new Jogo();
        
        jogo.setId(id);
        jogo.setQuantidade(quantidade);
        return jogo;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
        if(!resultado.getListMensagens().isEmpty()) //a lista de mensagens nao eh vazia
        {
            request.setAttribute("erroMsgEstoque", resultado.getListMensagens());
        }
        else
        {
           request.setAttribute("sucessMsgEstoque", "Quantidade atualizada com sucesso!");
        }
        //deu tudo certo!
        try
        {
            request.getRequestDispatcher("/root/dashboard.jsp").forward(request, response);
        }
        catch (ServletException ex)
        {
            ex.printStackTrace();
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
}
