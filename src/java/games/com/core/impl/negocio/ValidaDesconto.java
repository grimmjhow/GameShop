/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.core.impl.negocio;

import games.com.br.domain.EntidadeDominio;
import games.com.br.domain.ItemPedido;
import games.com.br.domain.Pedido;
import games.com.br.domain.Transacao;
import games.com.br.domain.TransacaoCartao;
import games.com.core.IStrategy;

/**
 *
 * @author Felipe
 */
public class ValidaDesconto implements IStrategy
{

    @Override
    public String processar(EntidadeDominio entidade)
    {
        if(entidade instanceof TransacaoCartao)
        {
            float desconto = 0;
            
            TransacaoCartao tc = (TransacaoCartao) entidade;
            int parcelas = Integer.parseInt(tc.getCartao().getParcelas().substring(0, 1));
            
            if(parcelas == 1)
                desconto = 0.1f;
            else if(parcelas > 1 && parcelas < 4)
                desconto = 0.05f;
            else
                desconto = 0f;
            
            Pedido pedido = tc.getPedido();
            
            for(ItemPedido ip : pedido.getItens())
            {
                ip.setDesconto(desconto);
            }
        }
        else
        {
            Transacao tc = (Transacao) entidade;
            Pedido pedido = tc.getPedido();
            
            for(ItemPedido ip : pedido.getItens())
            {
                ip.setDesconto(0.15f);
            }
        }
        return null;
    }
}
