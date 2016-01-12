/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.core.impl.Command;

import games.com.br.application.Resultado;
import games.com.br.domain.EntidadeDominio;

/**
 *
 * @author Felipe
 */
public class CommandSalvar extends AbstractICommand
{
    @Override
    public Resultado execute(EntidadeDominio entidade)
    {
        return fachada.salvar(entidade);
    }   
}
