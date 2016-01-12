/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.br.domain;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
/**
 *
 * @author Felipe Arruda Monteiro
 * Classe para conter os dados de um pedido
 */
public class Pedido extends EntidadeDominio
{
    private Calendar dataPedido;
    private Usuario cliente;
    private List<ItemPedido> itens = new ArrayList<>();
    private String tipoPagamento;
    private Date dataNormal;
    private Frete frete;
    private String posicao;
    private String status;
    private EnderecoEntrega entrega;

    public EnderecoEntrega getEntrega()
    {
        return entrega;
    }

    public void setEntrega(EnderecoEntrega entrega)
    {
        this.entrega = entrega;
    }
    
    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }
    
    public void setItens(List<ItemPedido> itens)
    {
        this.itens = itens;
    }
    
    public String getPosicao()
    {
        return posicao;
    }

    public void setPosicao(String posicao)
    {
        this.posicao = posicao;
    }
    
    public Pedido()
    {
        frete = new Frete();
    }
    
    public Frete getFrete()
    {
        return frete;
    }

    public void setFrete(Frete frete)
    {
        this.frete = frete;
    }
    
    
    public Date getDataNormal()
    {
        return dataNormal;
    }

    public void setDataNormal(Date dataNormal)
    {
        this.dataNormal = dataNormal;
    }

    public String getTipoPagamento()
    {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento)
    {
        this.tipoPagamento = tipoPagamento;
    } 
    
    public void addItem(ItemPedido item)
    {
        itens.add(item);
    }
    
    public List<ItemPedido> getItens()
    {
        return itens;
    }
    
    public Calendar getDataPedido()
    {
        return dataPedido;
    }

    public void setDataPedido(Calendar dataPedido)
    {
        this.dataPedido = dataPedido;
    }

    public float ValorTotal()
    {
        float valorTotal = 0;
        
        for(ItemPedido ip: itens)
        {
            valorTotal += ip.valorTotalItem();
        }
        return valorTotal;
    }

    public Usuario getCliente()
    {
        return cliente;
    }

    public void setCliente(Usuario cliente)
    {
        this.cliente = cliente;
    }
    
    public int quantidadeItens()
    {
        return itens.size();    //retornando a quantidade de itens dentro de um pedido
    }
    
    public void ordenarItens()
    {
        int x = 0;
        for (ItemPedido item : itens)
        {
            item.setId(x);
            x++;
        }
    }
    
    public float valorTotalDesconto(float desconto)
    {
        float total = 0;
        
        for (ItemPedido it : itens)
        {
            total += (it.getQuantidade() * (it.getJogo().getValor() - (it.getJogo().getValor() * desconto)));
        }

        return total;
    }
    
    public int totalItens()
    {
      return itens.size();
    }
}
