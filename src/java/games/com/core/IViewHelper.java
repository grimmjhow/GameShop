/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.core;

import games.com.br.application.Resultado;
import games.com.br.domain.EntidadeDominio;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe
 */
public interface IViewHelper
{
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response);
    
    public void setView(Resultado resultado,HttpServletRequest request, HttpServletResponse response);
}
