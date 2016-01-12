/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.core.impl.negocio;

import games.com.br.domain.Usuario;
import games.com.br.domain.EntidadeDominio;
import games.com.core.IStrategy;

/**
 *
 * @author Felipe
 */
public class ValidadorAtualizacaoCliente implements IStrategy
{

    @Override
    public String processar(EntidadeDominio entidade)
    {
        Usuario cliente = (Usuario) entidade;
        StringBuilder sb = new StringBuilder();
        
        String nome = cliente.getNome();
        String cpf = cliente.getCpf();
        String telefone = cliente.getTelefone();
        String sexo = cliente.getSexo();
        String email = cliente.getEmail();
        String senha = cliente.getSenha();
        
        if(nome == null || cpf == null || telefone == null || sexo == null)
        {
            return "Nome, CPF, Telefone e Sexo. São Obrigatórios!!";
        }
        
        if(nome.equals("") || cpf.equals("") || telefone.equals("") || sexo.equals(""))
        {
            return "Nome, CPF, Telefone, Sexo. São Obrigatórios!!";
        }
        
        String endereco = new ValidaCamposEndereco().processar(cliente.getEndereco());
        
        return endereco;
    }

}
