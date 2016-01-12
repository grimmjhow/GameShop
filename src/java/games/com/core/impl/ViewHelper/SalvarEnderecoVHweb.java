/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.core.impl.ViewHelper;

import games.com.br.application.Resultado;
import games.com.br.domain.Usuario;
import games.com.br.domain.EnderecoEntrega;
import games.com.br.domain.EntidadeDominio;
import games.com.br.domain.Pedido;
import games.com.core.IViewHelper;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe
 */
public class SalvarEnderecoVHweb implements IViewHelper
{
    private EnderecoEntrega endereco;
    private Usuario cliente;
    
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
        endereco = new EnderecoEntrega();
        cliente = (Usuario) request.getSession().getAttribute("user");
        
        int id = cliente.getId();
        
        String logradouro = request.getParameter("txtLogradouro");
        String numero = request.getParameter("txtNumero");
        String bairro = request.getParameter("txtBairro");
        String cidade = request.getParameter("txtCidade");
        String estado = request.getParameter("txtEstado");
        String cep = request.getParameter("txtCep");
        
        endereco.setId(id); //id do cliente!
        
        endereco.setLogradouro(logradouro);
        endereco.setNumero(numero);
        endereco.setBairro(bairro);
        endereco.setCep(cep);
        endereco.setCidade(cidade);
        endereco.setEstado(estado);
        
        return endereco;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
        RequestDispatcher dispatcher;
        
        if(resultado.getListMensagens().isEmpty())
        {
            Pedido pedido = (Pedido) request.getSession().getAttribute("carrinho");
            
            cliente.addEnderecos(endereco);
            
            pedido.setEntrega(endereco);
            
            //request.getSession().setAttribute("user", cliente);
            request.getSession().setAttribute("carrinho", pedido);
            
            dispatcher = request.getRequestDispatcher("MetodoPagamento.jsp");
        }
        else
        {
            
            request.setAttribute("msgEndereco",resultado.getListMensagens());
            //resultado.getListMensagens().clear();
            dispatcher = request.getRequestDispatcher("ConfirmaEndereco.jsp");
        }
        
        try
        {
            dispatcher.forward(request, response);
        } 
        catch (ServletException ex)
        {
            ex.printStackTrace();
        } 
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
}
