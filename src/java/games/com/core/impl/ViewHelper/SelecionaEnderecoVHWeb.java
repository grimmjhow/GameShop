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
public class SelecionaEnderecoVHWeb implements IViewHelper
{
    private int idEndereco;
    
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
        String index[] = request.getParameter("selEndereco").split("-");
        
        idEndereco = Integer.parseInt(index[0].trim())-1;
        
        return null;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
        Pedido pedido = (Pedido) request.getSession().getAttribute("carrinho");
        
        pedido.setEntrega(new EnderecoEntrega());
        
        Usuario usuario = (Usuario) request.getSession().getAttribute("user");
        
        EnderecoEntrega endereco = usuario.getEnderecos().get(idEndereco);
        
        pedido.setEntrega(endereco);
        
        request.getSession().setAttribute("carrinho", pedido);
        
        try
        {
            request.getRequestDispatcher("MetodoPagamento.jsp").forward(request, response);
        } catch (ServletException ex)
        {
            Logger.getLogger(SelecionaEnderecoVHWeb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(SelecionaEnderecoVHWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
