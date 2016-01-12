/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.core.impl.ViewHelper;

import games.com.br.application.Resultado;
import games.com.br.domain.EnderecoEntrega;
import games.com.br.domain.EntidadeDominio;
import games.com.br.domain.Pedido;
import games.com.br.domain.Transacao;
import games.com.br.domain.Usuario;
import games.com.core.IViewHelper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe Monteiro
 */
public class PagamentoBoletoVHWeb implements IViewHelper
{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
        Pedido pedido = new Pedido();
        Usuario usuario = (Usuario) request.getSession().getAttribute("user");
        pedido.setCliente(usuario);
       
        int id = Integer.parseInt(request.getParameter("txtIdPedido"));
        int idEntrega = Integer.parseInt(request.getParameter("txtIdEntrega"));
        
        pedido.setId(id);
        pedido.setEntrega(new EnderecoEntrega(idEntrega));
        
        Transacao transacao = new Transacao(pedido, usuario);
        
        return transacao;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
        if(resultado.getListMensagens().isEmpty())  //nao existem lista de mensagens?
        {
            request.setAttribute("SucessPedido", "Pedido Pago com sucesso!");
        }
        else
        {
          request.setAttribute("msgPedido", resultado.getListMensagens());
        }
        
        try
        {
            request.getRequestDispatcher("Pedidos.jsp").forward(request, response);
        } catch (ServletException ex)
        {
            Logger.getLogger(PagamentoBoletoVHWeb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(PagamentoBoletoVHWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
