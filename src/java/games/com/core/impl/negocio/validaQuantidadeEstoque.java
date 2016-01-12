/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.core.impl.negocio;

import games.com.br.domain.EntidadeDominio;
import games.com.br.domain.Jogo;
import games.com.core.IDAO;
import games.com.core.IStrategy;
import games.com.core.impl.dao.JogoDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Felipe
 */
public class validaQuantidadeEstoque implements IStrategy
{

    @Override
    public String processar(EntidadeDominio entidade)
    {
        IDAO dao = new JogoDAO();
        
        try
        {
            List<EntidadeDominio> entidades = dao.consultar(entidade);
            
            Jogo jogo = (Jogo) entidades.get(0);
            
            int qtdePedido = ((Jogo)entidade).getQuantidade();
            int qtdeEstoque = jogo.getQuantidade();
            
            String quantidade = ""+((Jogo)entidade).getQuantidade();
            
            if(quantidade.contains(".") || quantidade.contains(","))
                return "Digite um valor inteiro!";
            
            String resultado = qtdePedido > qtdeEstoque ? "Desculpe, não temos essa quantidade disponível em estoque": null;
            
            return resultado;
            
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    
}
