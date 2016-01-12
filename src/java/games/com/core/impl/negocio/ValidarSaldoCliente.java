/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.core.impl.negocio;

import games.com.br.domain.EntidadeDominio;
import games.com.br.domain.Pedido;
import games.com.br.domain.Transacao;
import games.com.br.domain.TransacaoCartao;
import games.com.core.IDAO;
import games.com.core.IStrategy;
import games.com.core.impl.dao.PedidoDAO;
import games.com.core.impl.dao.SaldoDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Felipe Monteiro
 */
public class ValidarSaldoCliente implements IStrategy
{

    @Override
    public String processar(EntidadeDominio entidade)
    {
        SaldoDAO dao = new SaldoDAO();
        TransacaoCartao trCartao = new TransacaoCartao();
        
        if(entidade instanceof Transacao)
        {
            trCartao.setCliente(((Transacao)entidade).getCliente());
            trCartao.setPedido(((Transacao)entidade).getPedido());
            trCartao.setValor(((Transacao)entidade).getValor());
        }
        else
        {
            trCartao = (TransacaoCartao) entidade;
        }
        
        if(trCartao.getPedido().ValorTotal() == 0.0)
        {
            IDAO pedidoDAO =  new PedidoDAO();
            
            try
            {
                List<EntidadeDominio> entidades = pedidoDAO.consultar(trCartao.getPedido());
                trCartao.setPedido((Pedido)entidades.get(0));
                trCartao.setValor(trCartao.TotalTransacao());
                
            } catch (SQLException ex)
            {
                ex.printStackTrace();
                return "ERRO AO CONSULTAR VALOR DO PEDIDO!";
            }
        }
        
        EntidadeDominio saldo = dao.consultaSaldoEspecifico(trCartao);
        
        if(saldo == null)   //cliente nao tem saldo!
        {
            return "Pedido recusado pelo cartão";
        }
        return null;
    }
}
