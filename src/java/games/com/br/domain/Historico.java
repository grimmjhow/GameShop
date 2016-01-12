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
public class Historico extends EntidadeDominio
{
    private int idJogo;
    private int idCliente;
    private int quantidade;
    private int quantidadeComprada;
    private int quantidadeEstoque;

    public int getQuantidadeComprada()
    {
        return quantidadeComprada;
    }

    public void setQuantidadeComprada(int quantidadeComprada)
    {
        this.quantidadeComprada = quantidadeComprada;
    }

    public int getQuantidadeEstoque()
    {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque)
    {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public int getIdJogo()
    {
        return idJogo;
    }

    public void setIdJogo(int idJogo)
    {
        this.idJogo = idJogo;
    }

    public int getIdCliente()
    {
        return idCliente;
    }

    public void setIdCliente(int idCliente)
    {
        this.idCliente = idCliente;
    }

    public int getQuantidade()
    {
        return quantidade;
    }

    public void setQuantidade(int quantidade)
    {
        this.quantidade = quantidade;
    }
}
