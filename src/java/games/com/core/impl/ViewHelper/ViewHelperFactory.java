/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.core.impl.ViewHelper;
import games.com.core.IViewHelper;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Felipe Monteiro
 */
public class ViewHelperFactory
{
    private static final Map<String,IViewHelper> vhs = new HashMap<>();
    
    static 
    {
        vhs.put("/Ecommerce/SalvarCliente", new SalvarClienteVHWeb());
        vhs.put("/Ecommerce/ConsultarCliente", new ConsultarClienteVHWeb());
        vhs.put("/Ecommerce/ConsultarTodos", new ConsultarClienteVHWeb());
        vhs.put("/Ecommerce/AtualizarCliente", new AtualizarClienteVHWeb());
        vhs.put("/Ecommerce/ExcluirCliente", new ExcluirClienteVHWeb());
        vhs.put("/Ecommerce/root/SalvarJogo", new SalvarJogoVHWeb());
        vhs.put("/Ecommerce/InfoJogo",new InformarJogoVHWeb());
        vhs.put("/Ecommerce/FinalizarCompra", new SalvarPedidoVHWeb());
        vhs.put("/Ecommerce/ConsultarPedido", new ConsultarPedidoVHweb());
        vhs.put("/Ecommerce/DetalhePedido", new DetalhePedidoVHWeb());
        vhs.put("/Ecommerce/SalvarEnderecoEntrega", new SalvarEnderecoVHweb());
        vhs.put("/Ecommerce/FinalizarPagamento", new SalvarPagamentoVHWeb());
        vhs.put("/Ecommerce/PagamentoBoleto", new SalvarTransacaoBoletoVHWeb());
        vhs.put("/Ecommerce/PagamentoCartao", new SalvarTransacaoCartaoVHWeb());
        vhs.put("/Ecommerce/dashboard.jsp", new ListaJogoVHWeb());
        vhs.put("/Ecommerce/ConsultaJogos", new ConsultarJovoVHWeb());
        vhs.put("/Ecommerce/root/ConsultaJogos", new ConsultarJovoVHWeb());
        vhs.put("/Ecommerce/root/ExcluirJogo", new ExcluirJogoVHWeb());
        vhs.put("/Ecommerce/root/AtualizarJogo", new AtualizarJogoVHWeb());
        vhs.put("/Ecommerce/root/AtualizaQuantidade", new AtualizarQuantidadeVHWeb());
        vhs.put("/Ecommerce/Games.jsp", new ConsultarJovoVHWeb());
        vhs.put("/Ecommerce/DesativarConta", new DeletarClienteVHWeb());
        vhs.put("/Ecommerce/Carrinho", new SalvarItemCarrinhoVHWeb());
        vhs.put("/Ecommerce/RemoveItem", new RemoverItemCarrinhoVHWeb());
        vhs.put("/Ecommerce/LimparCarrinho", new LimparCarrinhoVHWeb());
        vhs.put("/Ecommerce/CalcularFrete", new CalcularFreteVHWeb());
        vhs.put("/Ecommerce/AtualizarQuantidade", new VerificaQuantidadeVHWeb());
        vhs.put("/Ecommerce/SelecionarEndereco", new SelecionaEnderecoVHWeb());
        vhs.put("/Ecommerce/SegundaViaBoleto", new ConsultaViaBoletoVHWeb());
        vhs.put("/Ecommerce/PagamentoDiretoBoleto", new PagamentoBoletoVHWeb());
    }
    
    public static IViewHelper create(String url)
    {
        return vhs.get(url);
    }
}
