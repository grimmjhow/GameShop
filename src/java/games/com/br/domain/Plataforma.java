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
public class Plataforma extends EntidadeDominio
{
    private String nome;
    private String fabricante;
    private String geracao;

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getFabricante()
    {
        return fabricante;
    }

    public void setFabricante(String fabricante)
    {
        this.fabricante = fabricante;
    }

    public String getGeracao()
    {
        return geracao;
    }

    public void setGeracao(String geracao)
    {
        this.geracao = geracao;
    }
}
