/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.br.domain;

/**
 *
 * @author Felipe
 */
public class PedidoJogo extends EntidadeDominio
{
    private int idPedido;
    private int idJogo;
    private int quantidade;
    private ItemPedido item;

    public ItemPedido getItem()
    {
        return item;
    }

    public void setItem(ItemPedido item)
    {
        this.item = item;
    }
    
    public int getQuantidade()
    {
        return quantidade;
    }

    public void setQuantidade(int quantidade)
    {
        this.quantidade = quantidade;
    }

    public int getIdPedido()
    {
        return idPedido;
    }

    public void setIdPedido(int idPedido)
    {
        this.idPedido = idPedido;
    }

    public int getIdJogo()
    {
        return idJogo;
    }

    public void setIdJogo(int idJogo)
    {
        this.idJogo = idJogo;
    }
}
