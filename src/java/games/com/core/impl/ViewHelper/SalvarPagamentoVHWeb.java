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
class SalvarPagamentoVHWeb implements IViewHelper
{

    public SalvarPagamentoVHWeb(){}

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
        Pedido pedido = (Pedido) request.getSession().getAttribute("carrinho");
        
        return pedido;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
        if(resultado.getListMensagens().isEmpty()) //A lista de mensagens de erro esta vazia
        {
            StringBuilder sucess = new StringBuilder();
            sucess.append("Parabéns, sua solicitação de pagamento foi efetuada com sucesso!");
            sucess.append("\n");
            sucess.append("Acompanhe a aprovação de seu pedido");
            request.setAttribute("sucess", sucess.toString());  //retornando mensagem de sucesso da operacao
            try
            {
                request.getRequestDispatcher("Sucesso.jsp").forward(request, response);
            } catch (ServletException ex)
            {
                Logger.getLogger(SalvarPagamentoVHWeb.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex)
            {
                Logger.getLogger(SalvarPagamentoVHWeb.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            request.setAttribute("erroTransacao", resultado.getListMensagens());
            try
            {
                request.getRequestDispatcher("erro.jsp").forward(request, response);
            } catch (ServletException ex)
            {
                Logger.getLogger(SalvarPagamentoVHWeb.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex)
            {
                Logger.getLogger(SalvarPagamentoVHWeb.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
