/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.br.domain;

/**
 *
 * @author Felipe Arruda Monteiro
 */
public class ItemPedido extends EntidadeDominio
{
    private Jogo jogo;
    private int quantidade = 0;
    private float desconto;

    public float getDesconto()
    {
        return desconto;
    }

    public void setDesconto(float desconto)
    {
        this.desconto = desconto;
    }

    public Jogo getJogo()
    {
        return jogo;
    }

    public void setJogo(Jogo jogo)
    {
        this.jogo = jogo;
    }

    public int getQuantidade()
    {
        return quantidade;
    }

    public void setQuantidade(int quantidade)
    {
        this.quantidade = quantidade;
    }
    //valor total do item com o desconto
    public float valorTotalItem()
    {
        float total = (jogo.getValor() - (jogo.getValor() * desconto)) * quantidade;
        return total;
    }
}
