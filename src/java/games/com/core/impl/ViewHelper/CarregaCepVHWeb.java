/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.core.impl.ViewHelper;

import games.com.br.application.Resultado;
import games.com.br.domain.EnderecoEntrega;
import games.com.br.domain.EntidadeDominio;
import games.com.core.IViewHelper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe Monteiro
 */
public class CarregaCepVHWeb implements IViewHelper
{
    private EnderecoEntrega entrega;
    
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
        String cep = request.getParameter("txtCep");
        
        entrega = new EnderecoEntrega();
        
        entrega.setCep(cep);
        
        return entrega;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
        
    }
    
}
