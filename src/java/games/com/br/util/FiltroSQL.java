/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.br.util;

import games.com.br.domain.EntidadeDominio;

/**
 *
 * @author Felipe
 */
public interface FiltroSQL
{
    public String getFilter(EntidadeDominio entidade);
}
