/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.core.impl.ViewHelper;

import games.com.br.application.Resultado;
import games.com.br.domain.Usuario;
import games.com.br.domain.Endereco;
import games.com.br.domain.EntidadeDominio;
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
public class ExcluirClienteVHWeb implements IViewHelper
{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
        Usuario cliente = new Usuario();
        int idCliente = 0;
        int idEndereco = 0;

        if (!request.getParameter("txtIdCliente").trim().equals(""))    //ID veio vazio?
        { //Sim!
            idCliente = Integer.parseInt(request.getParameter("txtIdCliente"));
            idEndereco = Integer.parseInt(request.getParameter("txtIdEndereco"));
        }

        cliente.setId(idCliente);
        cliente.setEndereco(new Endereco());
        cliente.getEndereco().setId(idEndereco);
        
        return cliente;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
        if (resultado == null)
        {
            request.getSession().setAttribute("msg", "Cliente Excluido");
            request.getSession().setAttribute("clientes", null);
        } else
        {
            request.getSession().setAttribute("msg", resultado.getListMensagens());
        }

        try
        {
            request.getRequestDispatcher("ConsultarCliente.jsp").forward(request, response);
        } catch (ServletException ex)
        {
            Logger.getLogger(ExcluirClienteVHWeb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(ExcluirClienteVHWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
