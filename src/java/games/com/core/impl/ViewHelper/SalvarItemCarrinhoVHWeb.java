package games.com.core.impl.ViewHelper;

import games.com.br.application.Resultado;
import games.com.br.domain.EntidadeDominio;
import games.com.br.domain.ItemPedido;
import games.com.br.domain.Jogo;
import games.com.br.domain.Pedido;
import games.com.core.IViewHelper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe Monteiro
 */
public class SalvarItemCarrinhoVHWeb implements IViewHelper
{

    private Pedido pedido;
    private Jogo jogo;

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
        pedido = (Pedido) request.getSession().getAttribute("carrinho");

        if (pedido == null)  //nao existe o atributo ainda?
        {
            pedido = new Pedido();
        }

        jogo = new Jogo();
        ItemPedido item = new ItemPedido();

        if (pedido.getItens().isEmpty()) //carrinho esta vazio?
        {
            pedido.setItens(new ArrayList<>()); //criando array list para manipulacao dos itens!
        }
        String titulo = request.getParameter("txtTitulo");
        int id = Integer.parseInt(request.getParameter("txtCompraId"));
        float valor = Float.parseFloat(request.getParameter("txtValor"));

        jogo.setTitulo(titulo);
        jogo.setId(id);
        jogo.setValor(valor);

        item.setJogo(jogo);
        item.setQuantidade(1);

        //verifica se ja existe um carrinho no jogo!
        for (ItemPedido p : pedido.getItens())
        {
            if (p.getJogo().getTitulo().equals(jogo.getTitulo()))
            {
                jogo = null;
                return pedido;
            }
        }// O jogo nao existe no carrinho!

        pedido.addItem(item);

        return pedido;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
        if (jogo == null)    //o jogo ja existe no carrinho?
        {
            request.setAttribute("msgCompraDuplicada", "Esse jogo já foi adicionado no Carrinho!");
        } else
        {
            request.setAttribute("msgCompra", jogo.getTitulo() + " adicionado ao carrinho!");
        }

        request.getSession().setAttribute("carrinho", pedido);

        try
        {
            request.getRequestDispatcher("Games.jsp").forward(request, response);
        } catch (ServletException ex)
        {
            ex.printStackTrace();
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

}
