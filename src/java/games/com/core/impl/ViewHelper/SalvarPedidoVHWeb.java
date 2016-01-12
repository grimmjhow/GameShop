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
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe
 */
public class SalvarPedidoVHWeb implements IViewHelper
{
    private Pedido pedido;
    
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
        pedido = new Pedido();   //criando objeto de pedido!

        pedido = (Pedido) request.getSession().getAttribute("carrinho");
        pedido.setTipoPagamento((String)request.getSession().getAttribute("tipoPagamento"));
        pedido.setDataNormal(new Date());
        pedido.setDataPedido(Calendar.getInstance());

        return pedido;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
        if (resultado.getListMensagens().isEmpty())
        {
            String Message = "Seu pedido foi realizado com sucesso!";

            request.setAttribute("finalizado", Message);
            
            request.getSession().setAttribute("carrinho", null);

            try
            {
                request.getRequestDispatcher("Sucess.jsp").forward(request, response);   //redirecionando para a pagina principal
            } catch (ServletException ex)
            {
                ex.printStackTrace();
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        } else
        {
            String Message = null;

            for (String msg : resultado.getListMensagens())
            {
                Message = msg += "\n";
            }

            request.setAttribute("ErroFinalizado", Message);

            try
            {
                request.getRequestDispatcher("Credito.jsp").forward(request, response);   //redirecionando para a pagina principal
            } catch (ServletException ex)
            {
                ex.printStackTrace();
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }

}
