/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.br.domain;

import games.com.core.IEntidade;
import java.util.Calendar;
/**
 *
 * @author java
 */
public class EntidadeDominio implements IEntidade
{
  Integer id;
  private Calendar date;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Calendar getDate()
    {
        return date;
    }

    public void setDate(Calendar date)
    {
        this.date = date;
    }
}
