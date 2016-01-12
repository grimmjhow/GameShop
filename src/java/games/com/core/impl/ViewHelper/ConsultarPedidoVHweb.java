/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.core.impl.ViewHelper;

import games.com.br.application.Resultado;
import games.com.br.domain.Usuario;
import games.com.br.domain.EntidadeDominio;
import games.com.br.domain.Pedido;
import games.com.core.IViewHelper;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe
 */
public class ConsultarPedidoVHweb implements IViewHelper
{
    private Pedido pedido = new Pedido();
    
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dataPedido = null;
        Usuario cliente = (Usuario) request.getSession().getAttribute("user");

        String requestData = request.getParameter("txtDataPedido") == null ? "" : request.getParameter("txtDataPedido");
        try
        {
            if (!requestData.equals(""))
            {
                dataPedido = dateFormat.parse(requestData);
            }
            else
            {
                dataPedido = Calendar.getInstance().getTime();  //pega a data atual se o usuario nao digitar nada!
            }
        } catch (ParseException ex)
        {
            ex.printStackTrace();
            System.out.println("Formato de Data Invalida!");
        }
        pedido.setCliente(cliente);         //setando o id do cliente
        pedido.setDataNormal(dataPedido);   //setando a data do pedido!
        
        return pedido;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
        if(resultado.getListMensagens().isEmpty())
        {
            request.setAttribute("pedidos",resultado.getEntidades());
        }
        else
        {
            request.setAttribute("msgErro", resultado.getListMensagens());
        }
        
        try
        {
            if(pedido.getDataNormal() != null)
                request.getRequestDispatcher("Pedidos.jsp").forward(request, response);
            else
                request.getRequestDispatcher("DetalhePedido.jsp").forward(request, response);
        } 
        catch (ServletException | IOException ex)
        {
            ex.printStackTrace();
        }
    }

}
