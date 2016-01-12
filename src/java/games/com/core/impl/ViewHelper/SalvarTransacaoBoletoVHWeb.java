/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.core.impl.ViewHelper;

import games.com.br.application.Resultado;
import games.com.br.domain.Usuario;
import games.com.br.domain.EntidadeDominio;
import games.com.br.domain.Frete;
import games.com.br.domain.Pedido;
import games.com.br.domain.Transacao;
import games.com.core.IViewHelper;
import games.teste.MeuBoleto;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe
 */
public class SalvarTransacaoBoletoVHWeb implements IViewHelper
{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
        Pedido pedido = (Pedido) request.getSession().getAttribute("carrinho");
        Usuario cliente = (Usuario) request.getSession().getAttribute("user");
        
        pedido.setDataNormal(new Date());
        //pedido.setEntrega(cliente.getEnderecoEntrega());
        pedido.setDataPedido(Calendar.getInstance());
        pedido.setTipoPagamento("Boleto Bancario");
        //pedido.setFrete(frete);
        pedido.setCliente(cliente);
        pedido.setPosicao("Aguardando Pagamento");
        pedido.setStatus("Aguardando Entrega");
        
        Transacao transacao = new Transacao(pedido,pedido.getCliente());
        
        transacao.setPedido(pedido);
        transacao.setCliente(cliente);
        transacao.setStatus("pedente");
        transacao.setDescricao("Boleto Bancario");
        transacao.setDataTransacao(new Date());
        
        return transacao;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
        if(resultado.getListMensagens().isEmpty())
        {
            String transacao = "Seu Pedido foi processado com sucesso!\n"+
                    "Acompanhe a confirmação do pagamento em sua conta!";
            Pedido pedido = (Pedido) request.getSession().getAttribute("carrinho");
            //BoletoDomain boleto = new BoletoDomain(pedido);
            MeuBoleto boleto = new MeuBoleto();
            response.setContentType("application/pdf");
                            
            try
            {
                ByteArrayOutputStream outByte = boleto.getBoleto(pedido);
                
                response.setContentLength(outByte.size());
                
                request.getSession().setAttribute("carrinho", null);
                request.getSession().setAttribute("frete", null);
                request.getSession().setAttribute("requestUrl",null);
                
                ServletOutputStream out = response.getOutputStream();
                out.write(outByte.toByteArray(),0,outByte.size());
                out.flush();
                out.close();
            } 
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        else
        {
            String msg = "Erro ao processar seu pedido =[";
            
            request.setAttribute("msgTransacao", msg);
            
            try
            {
                request.getRequestDispatcher("Sucess.jsp").forward(request, response);
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
