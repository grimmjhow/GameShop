/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.teste;

import games.com.br.domain.EntidadeDominio;
import games.com.br.domain.Historico;
import games.com.core.IDAO;
import games.com.core.impl.dao.HistoricoDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Felipe
 */
public class TesteHistorico
{
    public static void main(String[] args) throws SQLException
    {
        Historico h = new Historico();
        h.setIdCliente(65);
        h.setIdJogo(1);
        IDAO dao = new HistoricoDAO();
        
        List<EntidadeDominio> entidades = dao.consultar(h);
        
        for (EntidadeDominio enti : entidades)
        {
            System.out.println("quantidade comprada: "+((Historico)enti).getQuantidadeComprada());
            System.out.println("Quantidade estoque: "+((Historico)enti).getQuantidadeEstoque());
        }
    }
}
