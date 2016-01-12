/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.core;
import games.com.br.domain.EntidadeDominio;
/**
 *
 * @author java
 */
public interface IStrategy
{
    public String processar(EntidadeDominio entidade);
}
