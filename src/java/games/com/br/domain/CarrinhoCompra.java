/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.br.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Felipe
 */
public class CarrinhoCompra extends EntidadeDominio
{
    private List<Jogo> ItemPedido = new ArrayList<>();
    
    public int quantidadeItens()
    {
        return ItemPedido.size();   //retorna a quantidade de itens
    }
    
    public float valorTotal()
    {
        return 10f;
    }
}
