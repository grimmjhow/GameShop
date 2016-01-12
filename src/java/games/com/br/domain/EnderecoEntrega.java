/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.br.domain;

/**
 *
 * @author Felipe Monteiro
 */
public class EnderecoEntrega extends EntidadeDominio
{
    private String logradouro;
    private String numero;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;

    public EnderecoEntrega() { }
    
    public EnderecoEntrega(int id)
    {
        super.id = id;
    }
    
    public String getLogradouro()
    {
        return logradouro;
    }

    public void setLogradouro(String logradouro)
    {
        this.logradouro = logradouro;
    }

    public String getNumero()
    {
        return numero;
    }

    public void setNumero(String numero)
    {
        this.numero = numero;
    }

    public String getBairro()
    {
        return bairro;
    }

    public void setBairro(String bairro)
    {
        this.bairro = bairro;
    }

    public String getCep()
    {
        return cep;
    }

    public void setCep(String cep)
    {
        this.cep = cep;
    }

    public String getCidade()
    {
        return cidade;
    }

    public void setCidade(String cidade)
    {
        this.cidade = cidade;
    }

    public String getEstado()
    {
        return estado;
    }

    public void setEstado(String estado)
    {
        this.estado = estado;
    }
    
    @Override
    public String toString()
    {
        StringBuilder endereco = new StringBuilder();
        endereco.append(logradouro).append(numero).append(", ").append(bairro).append(" - ").append(cep);
        
        return endereco.toString();
    }
}
