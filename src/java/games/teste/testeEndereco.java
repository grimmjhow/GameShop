/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.teste;

import games.com.br.domain.EnderecoEntrega;
import games.com.br.domain.EntidadeDominio;
import games.com.core.IDAO;
import games.com.core.impl.dao.EnderecoEntregaDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Felipe
 */
public class testeEndereco
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        EnderecoEntrega entrega = new EnderecoEntrega();
        
        entrega.setLogradouro("Rua Salvador Ferreira");
        entrega.setNumero("404");
        entrega.setBairro("Centro");
        entrega.setCep("08710680");
        entrega.setCidade("Mogi das Cruzes");
        entrega.setEstado("Sao Paulo");
        
        IDAO dao = new EnderecoEntregaDAO();
        
        try
        {
            dao.salvar(entrega);
            List<EntidadeDominio> entidades = dao.consultar(entrega);
            
            for (EntidadeDominio ed : entidades )
            {
                System.out.println("logradouro: "+((EnderecoEntrega)ed).getLogradouro());
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();   //imprimindo pilha de erros!
        }
    }    
}
