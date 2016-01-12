/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.core.impl.negocio;

import games.com.br.domain.EntidadeDominio;
import games.com.br.domain.Fornecedor;
import games.com.core.IStrategy;

/**
 *
 * @author java
 */
public class ValidadorDadosFornecedor implements IStrategy
{

    @Override
    public String processar(EntidadeDominio entidade)
    {
        Fornecedor f = (Fornecedor) entidade;
        StringBuilder msg = new StringBuilder();
        
        //validando valores iniciais do cnpj
        String temp = f.getCnpj().replace(".", "").replace("/", "").replace("-", "");
        
        if(temp.length() < 14 || temp.length() > 14)
        {
            msg.append("Formato Invalido do CNPJ\n");
        }
        
        if(temp.equals(""))
        {
            msg.append("CNPJ Nao pode estar Vazio\n");
        }
        //verificando o nome
        if(f.getNome().equals(""))
        {
            msg.append("Preencha o Nome do Fornecedor\n");
        }
        
        //verificando o telefone
        if(f.getEmail().equals(""))
        {
            msg.append("Email deve ser preenchido\n");
        }
        else if(!f.getEmail().contains("@"))
        {
            msg.append("Formato invalido de Email\n");
        }
        
        if(msg.length() == 0)
        {
            return null;
        }
        return msg.toString();
    }
    
}
