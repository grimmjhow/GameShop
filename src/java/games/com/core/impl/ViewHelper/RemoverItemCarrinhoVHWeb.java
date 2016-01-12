/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.core.impl.ViewHelper;

import games.com.br.application.Resultado;
import games.com.br.domain.EntidadeDominio;
import games.com.br.domain.Pedido;
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
public class RemoverItemCarrinhoVHWeb implements IViewHelper
{
    private Pedido pedido;
    int indexItem;
    
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {   
        pedido = (Pedido) request.getSession().getAttribute("carrinho");
        
        indexItem = Integer.parseInt(request.getParameter("txtIdItem"));
        
        pedido.getItens().remove(indexItem);    //removendo o item do carrinho!
        
        return pedido;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
        request.getSession().setAttribute("carrinho", pedido);
        
        try
        {
            request.getRequestDispatcher("VisualizarCarrinho.jsp").forward(request, response);
        } catch (ServletException ex)
        {
            Logger.getLogger(RemoverItemCarrinhoVHWeb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(RemoverItemCarrinhoVHWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
