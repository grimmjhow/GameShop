/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.teste;

import games.com.br.domain.Usuario;
import games.com.br.domain.EntidadeDominio;
import games.com.br.domain.ItemPedido;
import games.com.br.domain.Jogo;
import games.com.br.domain.Pedido;
import games.com.core.IDAO;
import games.com.core.impl.dao.PedidoDAO;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Felipe
 */
public class TestePedido
{
    public static void main(String[] args) throws SQLException
    {
        consultarPedidoData();
    }
   
    public static void salvar() throws SQLException
    {
        Pedido pedido = new Pedido();
        Usuario cliente = new Usuario();
        Jogo jogo = new Jogo();
        ItemPedido item = new ItemPedido();
        
        pedido.setDataNormal(new Date());
        pedido.setDataPedido(Calendar.getInstance());
        //dados do cliente
        cliente.setId(69);
        pedido.setCliente(cliente);
        //setando tipo de pagamento
        pedido.setTipoPagamento("Cartão de Crédito");
        //dados do jogo
        jogo.setId(3);
        jogo.setValor(99.99f);
        item.setJogo(jogo);
        item.setQuantidade(2);
        pedido.addItem(item);
        
        item = new ItemPedido();
        Jogo jogo1 = new Jogo();
        jogo1.setId(2); 
        item.setJogo(jogo1);
        item.setQuantidade(3);
        pedido.addItem(item);
        
        IDAO dao = new PedidoDAO();
        
        dao.salvar(pedido); 
    }
    
    public static void consultar() throws SQLException
    {
        Pedido pedido = new Pedido();
        Usuario cliente = new Usuario();
        
        cliente.setId(69);
        pedido.setCliente(cliente);
        
        IDAO dao = new PedidoDAO();
        
        List<EntidadeDominio> pedidos = dao.consultar(pedido);
        
        for(EntidadeDominio ed : pedidos)
        {
            System.out.println("Nome Cliente: "+((Pedido)ed).getCliente().getNome());  
        }
    }
    
    public static void consultarPedidoData() throws SQLException
    {
        Pedido pedido = new Pedido();
        Usuario cliente = new Usuario();
        
        cliente.setId(69);
        pedido.setCliente(cliente);
        pedido.setDataPedido(Calendar.getInstance());
        pedido.setDataNormal(new Date());
        
        IDAO dao = new PedidoDAO();
        
        List<EntidadeDominio> entidades = dao.consultar(pedido);
        SimpleDateFormat smf = new SimpleDateFormat("dd/MM/yyyy");
        for(EntidadeDominio entidade : entidades)
        {
            Pedido p = (Pedido) entidade;
            
            System.out.println(smf.format(p.getDataNormal()));
            System.out.println(p.getTipoPagamento());
        }
    }
}
