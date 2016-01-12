package games.com.core.impl.ViewHelper;

import games.com.br.application.Resultado;
import games.com.br.domain.EnderecoEntrega;
import games.com.br.domain.EntidadeDominio;
import games.com.br.domain.Frete;
import games.com.br.domain.Pedido;
import games.com.core.IViewHelper;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe Monteiro
 */
public class DetalhePedidoVHWeb implements IViewHelper
{
    //construtor default
    public DetalhePedidoVHWeb()
    {
    }
    private Pedido pedido;
    
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
        pedido = new Pedido();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar dataPedido = Calendar.getInstance();
        int id = Integer.parseInt(request.getParameter("txtId"));
        Date data = null;
        try
        {
            data = sdf.parse(request.getParameter("txtData"));
            dataPedido.setTime(data);
        } catch (ParseException ex)
        {
            ex.printStackTrace();
        }
        String tipoPagamento = request.getParameter("txtTipoPagamento");
        String posicao = request.getParameter("txtPosicao");
        String status = request.getParameter("txtStatus");
        int idEntrega = Integer.parseInt(request.getParameter("txtIdEntrega"));
        float frete = Float.parseFloat(request.getParameter("txtFrete"));
        
        pedido.setId(id);
        pedido.setFrete(new Frete(frete));
        pedido.setStatus(status);
        pedido.setTipoPagamento(tipoPagamento);
        pedido.setPosicao(posicao);
        pedido.setDataPedido(dataPedido);
        pedido.setEntrega(new EnderecoEntrega(idEntrega));
        
        return pedido;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
        RequestDispatcher dispatcher;
        
        if(resultado.getListMensagens().isEmpty())
        {
            pedido = (Pedido) resultado.getEntidades().get(0);
            
            request.setAttribute("pedido", pedido);
            
            dispatcher = request.getRequestDispatcher("DetalhePedido.jsp");
        }
        else    //deu algum erro se entrar no else!
        {
            request.setAttribute("MsgErroPedido", resultado.getListMensagens());
            
            dispatcher = request.getRequestDispatcher("Pedidos.jsp");
        }
         
        //mandando a requisicao de volta para o usuario!
        try
        {
            dispatcher.forward(request, response); //manda  requisicao devolta!
        } catch (ServletException ex)
        {
            ex.printStackTrace();
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
}
