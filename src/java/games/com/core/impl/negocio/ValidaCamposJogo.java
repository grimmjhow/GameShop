/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.core.impl.negocio;

import games.com.br.domain.EntidadeDominio;
import games.com.br.domain.Jogo;
import games.com.core.IStrategy;

/**
 *
 * @author Felipe
 */
public class ValidaCamposJogo implements IStrategy
{

    @Override
    public String processar(EntidadeDominio entidade)
    {
        Jogo jogo = (Jogo) entidade;
        
        String titulo = jogo.getTitulo();
        String descricao = jogo.getDescricao();
        String faixa = jogo.getFaixaEtaria();
        String genero = jogo.getGenero();
        float valor = jogo.getValor();
        String anoLancamento = jogo.getAno();
        String multiplayer = jogo.getMultiplayer();
        String online = jogo.getOnline();
        String idiomas = jogo.getIdioma();
        int quantidade = 0;
        
/*        if(jogo.getId() == 0)   //indica uma atualizacao!
            quantidade = jogo.getQuantidade(); */
        
        StringBuilder msg = new StringBuilder();
        
        if(titulo == null || titulo.equals(""))
            msg.append("Titulo, ");
        
        if(descricao == null || descricao.equals(""))
            msg.append("Descricao, ");
            
        if(faixa == null || faixa.equals(""))
            msg.append("Faixa, ");
        
        if(genero == null || genero.equals(""))
            msg.append("Genero, ");
        
        if(valor == 0)
            msg.append("Valor, ");
        
        if(anoLancamento == null || anoLancamento.equals(""))
            msg.append("Ano, ");
        
        if(multiplayer == null || multiplayer.equals(""))
            msg.append("Multiplayer, ");
        
        if(online == null || online.equals(""))
            msg.append("Online, ");
        
        if(idiomas == null || idiomas.equals(""))
            msg.append("Idiomas, ");
        
        if(msg.length() > 0)
        {
            msg.delete(msg.length()-2,msg.length());
            msg.insert(0, "São campos obrigatórios: ");
            return msg.toString();
        }
        else
        {
            return null;
        }
    }
}
