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
public class VerificaSeJogoExiste implements IStrategy
{

    @Override
    public String processar(EntidadeDominio entidade)
    {
        IDAO dao = new JogoDAO();
        
        Jogo jogo = new Jogo();
        jogo.setTitulo(((Jogo) entidade).getTitulo());
        
        try
        {
            List<EntidadeDominio> entidades = dao.consultar(jogo);
            
            if(entidades.isEmpty())
                return null;
            
            Jogo j = (Jogo) entidades.get(0);
            
            if(jogo.getTitulo().equalsIgnoreCase(j.getTitulo()))
                return "O Jogo cadastrado já existe!";
            else
                return null;
        } 
        catch (SQLException ex)
        {
            ex.printStackTrace();
            return "Desculpe, alguma coisa estranha aconteceu";
        }
    }
    
}
