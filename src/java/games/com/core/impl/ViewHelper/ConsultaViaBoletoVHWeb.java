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
import games.com.br.domain.Usuario;
import games.com.core.IViewHelper;
import games.teste.MeuBoleto;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe Monteiro
 */ 
public class ConsultaViaBoletoVHWeb implements IViewHelper
{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
        Pedido pedido = new Pedido();
        Usuario cliente = (Usuario) request.getSession().getAttribute("user");
        
        pedido.setCliente(cliente);
        
        int id = Integer.parseInt(request.getParameter("txtIdPedido"));
        int idEntrega = Integer.parseInt(request.getParameter("txtIdEntrega"));
        
        pedido.setId(id);
        pedido.setEntrega(new EnderecoEntrega(idEntrega));
        
        return pedido;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
        if(resultado.getListMensagens().isEmpty())  // nao existem mensagens de erro?
        {
            MeuBoleto boleto = new MeuBoleto();
            
            Pedido pedido = (Pedido) resultado.getEntidades().get(0);   //pegando resultado retornado!
            
            ByteArrayOutputStream byteArray = boleto.getBoleto(pedido);
            
            response.setContentLength(byteArray.size());
            
            try
            {
                ServletOutputStream outServlet = response.getOutputStream();
                outServlet.write(byteArray.toByteArray(),0,byteArray.size());
                outServlet.flush();
                outServlet.close();
            } catch (IOException ex)
            {
                Logger.getLogger(ConsultaViaBoletoVHWeb.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        }
        else
        {
            request.setAttribute("msgPedidos", resultado.getListMensagens());
            
            try
            {
                request.getRequestDispatcher("Pedidos.jsp").forward(request, response);
            } catch (ServletException ex)
            {
                Logger.getLogger(ConsultaViaBoletoVHWeb.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex)
            {
                Logger.getLogger(ConsultaViaBoletoVHWeb.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
