/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.core.impl.ViewHelper;

import games.com.br.application.Resultado;
import games.com.br.domain.EntidadeDominio;
import games.com.br.domain.Frete;
import games.com.br.domain.Pedido;
import games.com.br.util.CalculaFrete;
import games.com.core.IViewHelper;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe Monteiro
 */
public class CalcularFreteVHWeb implements IViewHelper
{

    public CalcularFreteVHWeb()
    {
    }
    
    private Pedido pedido;
    
    CalculaFrete calcula = new CalculaFrete();
    
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
        pedido = (Pedido) request.getSession().getAttribute("carrinho");//pegando carrinho de compras!
        
        String cep = request.getParameter("txtCep");    //pegando CEP para calculo do Frete!
        
        try
        {
            Frete frete = calcula.getInfoFrete(cep, "Sedex");  //parei no calculo do frete!
            
            pedido.setFrete(frete);
            
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
        
        return pedido;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
        if(pedido.getFrete() == null) //erro ao pegar o Frete!
        {
            request.setAttribute("msgFrete", calcula.getServico().cServico.MsgErro);//passando mensagem de erro do frete!
            pedido.setFrete(null);
        }
        else    //apenas colocar o objeto pedido na session!
        {
            request.getSession().setAttribute("carrinho", pedido);
        }
        
        try
        {
            request.getRequestDispatcher("VisualizarCarrinho.jsp").forward(request, response);
        } 
        catch (ServletException ex)
        {
            Logger.getLogger(CalcularFreteVHWeb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(CalcularFreteVHWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
