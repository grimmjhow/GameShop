/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.teste;

import games.com.br.domain.Endereco;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Felipe
 */
public class Teste
{
    private String nome;
    private List<Endereco> enderecos;
    
    public void addEndereco(Endereco end)
    {        
        enderecos = new ArrayList<>();
        enderecos.add(end);
    }
}
