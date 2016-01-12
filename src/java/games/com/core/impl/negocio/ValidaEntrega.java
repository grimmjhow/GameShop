/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.core.impl.negocio;

import games.com.br.domain.Endereco;
import games.com.br.domain.EnderecoEntrega;
import games.com.br.domain.EntidadeDominio;
import games.com.core.IStrategy;

/**
 *
 * @author Felipe Monteiro
 */
public class ValidaEntrega implements IStrategy
{

    @Override
    public String processar(EntidadeDominio entidade)
    {
        EnderecoEntrega endereco = (EnderecoEntrega) entidade;
        
        StringBuilder mensagem = new StringBuilder();
        
        if( (endereco.getLogradouro().equals("") || endereco.getLogradouro() == null) || 
            (endereco.getNumero().equals("") || endereco.getNumero() == null) || 
            (endereco.getBairro().equals("") || endereco.getBairro() == null) ||
            (endereco.getCep().equals("") || endereco.getCep() == null) || 
            (endereco.getCidade().equals("") || endereco.getCidade() == null) || 
            (endereco.getEstado().equals("") || endereco.getEstado() == null))
        {
            mensagem.append("Preencha os campos Obrigatórios: ");
            mensagem.append("Logradouro, Numero, Bairro, CEP, Cidade e Estado");
        }
        if(mensagem.length() > 0)
            return mensagem.toString();
        else
            return null;
    }    
}
