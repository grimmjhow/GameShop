/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.br.domain;
import java.util.List;
/**
 *
 * @author Felipe Arruda
 */
public class Jogo extends EntidadeDominio
{
    private String descricao;
    private String ano;
    private String titulo;
    private String faixaEtaria;
    private String genero;
    private String multiplayer;
    private String online;
    private String idioma;
    private List<String> plataformas;
    private float valor;
    private String image;
    private int quantidade;

    public int getQuantidade()
    {
        return quantidade;
    }

    public void setQuantidade(int quantidade)
    {
        this.quantidade = quantidade;
    }
    
    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }
    
    public String getMultiplayer()
    {
        return multiplayer;
    }

    public void setMultiplayer(String multiplayer)
    {
        this.multiplayer = multiplayer;
    }

    public String getOnline()
    {
        return online;
    }

    public void setOnline(String online)
    {
        this.online = online;
    }

    public String getIdioma()
    {
        return idioma;
    }

    public void setIdioma(String idioma)
    {
        this.idioma = idioma;
    }

    public String getAno()
    {
        return ano;
    }

    public void setAno(String ano)
    {
        this.ano = ano;
    }

    public float getValor()
    {
        return valor;
    }

    public void setValor(float valor)
    {
        this.valor = valor;
    }

    
    public void addPlataforma(String plataforma)
    {
        plataformas.add(plataforma);
    }
    
    public List<String> getPlataformas()
    {
        return plataformas;
    }
    
    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public String getTitulo()
    {
        return titulo;
    }

    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }

    public String getFaixaEtaria()
    {
        return faixaEtaria;
    }

    public void setFaixaEtaria(String faixaEtaria)
    {
        this.faixaEtaria = faixaEtaria;
    }

    public String getGenero()
    {
        return genero;
    }

    public void setGenero(String genero)
    {
        this.genero = genero;
    }
}
