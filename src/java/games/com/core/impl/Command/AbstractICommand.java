/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.core.impl.Command;

import games.com.core.ICommand;
import games.com.core.IFachada;
import games.com.core.impl.controle.Fachada;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Felipe Arruda
 */
public abstract class AbstractICommand implements ICommand
{
    protected IFachada fachada = new Fachada();
    private static final Map<String,ICommand> commands = new HashMap<>();
    
    static 
    {
        commands.put("Salvar", new CommandSalvar());
        commands.put("Atualizar", new CommandAtualizar());
        commands.put("Consultar",new CommandConsultar());
        commands.put("Excluir", new CommandExcluir());
    }
    
    public static ICommand getCommand(String action)
    {
        return commands.get(action);
    }
}
