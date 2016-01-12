/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.br.application;

import games.com.br.domain.EntidadeDominio;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Felipe
 */
public class Resultado extends EntidadeAplicacao
{
    private String msgSimples;
    private List<EntidadeDominio> entidades = new ArrayList<>();
    private List<String> listMensagens = new ArrayList<>();

    public List<String> getListMensagens()
    {
        return listMensagens;
    }

    public void addMensagem(String msg)
    {
        this.listMensagens.add(msg);
    }

    public String getMsgSimples()
    {
        return msgSimples;
    }

    public void setMsgSimples(String msgSimples)
    {
        this.msgSimples = msgSimples;
    }

    public List<EntidadeDominio> getEntidades()
    {
        return entidades;
    }

    public void setEntidades(List<EntidadeDominio> entidades)
    {
        this.entidades = entidades;
    }    
}
