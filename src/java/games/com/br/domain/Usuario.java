/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.br.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author java
 */
public class Usuario extends Pessoa
{
    private String email;
    private String senha;
    private int status;
    private String telefone;
    private String celular;
    private EnderecoEntrega enderecoEntrega;
    private List<EnderecoEntrega> enderecos = new ArrayList<>();

    public List<EnderecoEntrega> getEnderecos()
    {
        enderecos.indexOf(id);
        return enderecos;
    }
    
    public void addEnderecos(EnderecoEntrega enderecos)
    {
        this.enderecos.add(enderecos);
    }
    
    public EnderecoEntrega getEnderecoEntrega()
    {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(EnderecoEntrega enderecoEntrega)
    {
        this.enderecoEntrega = enderecoEntrega;
    }

    public String getSenha()
    {
        return senha;
    }

    public void setSenha(String senha)
    {
        this.senha = senha;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getTelefone()
    {
        return telefone;
    }

    public void setTelefone(String telefone)
    {
        this.telefone = telefone;
    }

    public String getCelular()
    {
        return celular;
    }

    public void setCelular(String celular)
    {
        this.celular = celular;
    }
}
