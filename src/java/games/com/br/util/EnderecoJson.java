/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.br.util;

import games.com.br.domain.Endereco;
import games.com.br.domain.EnderecoEntrega;
import sun.nio.cs.ext.TIS_620;

/**
 *
 * @author Felipe
 */
public class EnderecoJson
{
    private String resultado;
    private String resultado_txt;
    private String uf;
    private String cidade;
    private String bairro;
    private String tipo_logradouro;
    private String logradouro;

    public String getResultado()
    {
        return resultado;
    }

    public void setResultado(String resultado)
    {
        this.resultado = resultado;
    }
    
    public String getResultado_txt()
    {
        return resultado_txt;
    }

    public void setResultado_txt(String resultado_txt)
    {
        this.resultado_txt = resultado_txt;
    }

    public String getUf()
    {
        return uf;
    }

    public void setUf(String uf)
    {
        this.uf = uf;
    }

    public String getCidade()
    {
        return cidade;
    }

    public void setCidade(String cidade)
    {
        this.cidade = cidade;
    }

    public String getBairro()
    {
        return bairro;
    }

    public void setBairro(String bairro)
    {
        this.bairro = bairro;
    }

    public String getTipo_logradouro()
    {
        return tipo_logradouro;
    }

    public void setTipo_logradouro(String tipo_logradouro)
    {
        this.tipo_logradouro = tipo_logradouro;
    }

    public String getLogradouro()
    {
        return logradouro;
    }

    public void setLogradouro(String logradouro)
    {
        this.logradouro = logradouro;
    }
    
    /**
     * Convert um EnderecoJson em um Endereco da Classe de Dominio!
     * @return 
     */
    public Endereco toEndereco()
    {        
        Endereco end = new Endereco();
        
        end.setLogradouro(logradouro);
        end.setBairro(bairro);
        end.setCidade(cidade);
        end.setEstado(uf);
        return end;
    }    
    
    public EnderecoEntrega toEnderecoEntrega()
    {        
        EnderecoEntrega end = new EnderecoEntrega();
        
        end.setLogradouro(logradouro);
        end.setBairro(bairro);
        end.setCidade(cidade);
        end.setEstado(uf);
        return end;
    }    
}
