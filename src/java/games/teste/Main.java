/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.teste;

import games.com.br.domain.EntidadeDominio;
import games.com.br.domain.Jogo;
import games.com.core.IDAO;
import games.com.core.impl.dao.JogoDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author java
 */
public class Main
{
    public static void main(String[] args) throws SQLException
    {
        IDAO dao = new JogoDAO();
        
        Jogo jogo = new Jogo();
        
        jogo.setTitulo("");
        
        List<EntidadeDominio> jogos = dao.consultar(jogo);
        
        for (EntidadeDominio ed : jogos)
        {
            System.out.println(((Jogo)ed).getTitulo());
        }
        
        System.out.println(jogo.getTitulo());
    }
}