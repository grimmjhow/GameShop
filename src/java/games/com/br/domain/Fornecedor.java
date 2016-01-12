/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.br.domain;

/**
 *
 * @author java
 */
public class Fornecedor extends Pessoa
{
    private String Cnpj;
    private String telefone;
    private String email;

    public String getTelefone()
    {
        return telefone;
    }

    public void setTelefone(String telefone)
    {
        this.telefone = telefone;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public String getCnpj()
    {
        return Cnpj;
    }

    public void setCnpj(String Cnpj)
    {
        this.Cnpj = Cnpj;
    }
}
