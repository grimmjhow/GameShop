/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.core.impl.ViewHelper;

import games.com.br.application.Resultado;
import games.com.br.domain.EntidadeDominio;
import games.com.br.domain.ItemPedido;
import games.com.br.domain.Jogo;
import games.com.br.domain.Pedido;
import games.com.core.IStrategy;
import games.com.core.IViewHelper;
import games.com.core.impl.negocio.validaQuantidadeEstoque;
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
public class VerificaQuantidadeVHWeb implements IViewHelper
{
    private Jogo jogo;
    private int quantidade;
    private int index;
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
        jogo = new Jogo();
        String titulo = request.getParameter("txtTitulo");
        quantidade = Integer.parseInt(request.getParameter("txtQuantidade"));
        index = Integer.parseInt(request.getParameter("txtIndex"));
        
        jogo.setTitulo(titulo);
        jogo.setQuantidade(quantidade);
        
        return jogo;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
        IStrategy bussines = new validaQuantidadeEstoque();
        
        String result = bussines.processar(jogo);
        
        if(result == null)
        {
            Pedido pedido = (Pedido) request.getSession().getAttribute("carrinho");
            
            pedido.getItens().get(index).setQuantidade(quantidade); //setando a quantidade permitida para o item
            
            request.getSession().setAttribute("carrinho", pedido);
        }
        else
        {
            request.setAttribute("erroQtde", result);
        }
        
        try
        {
            request.getRequestDispatcher("VisualizarCarrinho.jsp").forward(request, response);  //enviando a requisicao de volta!
        } catch (ServletException ex)
        {
            Logger.getLogger(VerificaQuantidadeVHWeb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(VerificaQuantidadeVHWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
