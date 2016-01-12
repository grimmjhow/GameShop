/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.core.impl.negocio;

import games.com.br.domain.EntidadeDominio;
import games.com.core.IStrategy;
import java.util.Calendar;

/**
 *
 * @author java
 */
public class ComplementadorDtCadastro implements IStrategy
{

    @Override
    public String processar(EntidadeDominio entidade)
    {
        Calendar c = Calendar.getInstance();
        
        entidade.setDate(c);
        
        return null;
    }
    
}
