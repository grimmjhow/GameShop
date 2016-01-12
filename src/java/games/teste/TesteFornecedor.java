/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.teste;

import games.com.br.domain.Endereco;
import games.com.br.domain.Fornecedor;
import games.com.core.IDAO;
import games.com.core.impl.dao.FornecedorDAO;

/**
 *
 * @author Felipe
 */
public class TesteFornecedor
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Endereco end = new Endereco();
        end.setLogradouro("Rua Augusta, 1836");
        end.setBairro("Consolacao");
        end.setCEP("08710-930");
        end.setCidade("Sao Paulo");
        end.setEstado("Sao Paulo");
        
        Fornecedor f = new Fornecedor();
        f.setNome("CIFE - Centro Institucional");
        f.setTelefone("4722-3623");
        f.setEmail("elaineiec@ig.com.br");
        f.setCnpj("10230110000101");
        f.setEndereco(end);
        
        f.setCnpj("10230110000101");
        
        IDAO dao = new FornecedorDAO();
        /*Fornecedor fornecedor  = (Fornecedor) ((FornecedorDAO)dao).consultaByCnpj(f);
        
        if(fornecedor == null)
        {
            System.out.println("Fornecedor nao econtrado");
            System.exit(0);
        } 
        
        System.out.println("Logradouro: "+fornecedor.getEndereco().getLogradouro());
        System.out.println("CNPJ: "+fornecedor.getCnpj()); */
    }
}
