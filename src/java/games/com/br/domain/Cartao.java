/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.br.domain;

/**
 *
 * @author Felipe
 */
public class Cartao extends EntidadeDominio
{
    private String numeroCartao;
    private String numeroFinalCartao;
    private String nomeCartao;
    private String parcelas;

    public String getNumeroCartao()
    {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao)
    {
        this.numeroCartao = numeroCartao;
    }

    public String getNumeroFinalCartao()
    {
        return numeroFinalCartao;
    }

    public void setNumeroFinalCartao(String numeroFinalCartao)
    {
        this.numeroFinalCartao = numeroFinalCartao;
    }

    public String getNomeCartao()
    {
        return nomeCartao;
    }

    public void setNomeCartao(String nomeCartao)
    {
        this.nomeCartao = nomeCartao;
    }

    public String getParcelas()
    {
        return parcelas;
    }

    public void setParcelas(String parcelas)
    {
        this.parcelas = parcelas;
    }
    
    
}
