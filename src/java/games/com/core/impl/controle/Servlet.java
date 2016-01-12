package games.com.core.impl.controle;

import games.com.br.application.Resultado;
import games.com.br.domain.EntidadeDominio;
import games.com.core.ICommand;
import games.com.core.IViewHelper;
import games.com.core.impl.Command.AbstractICommand;
import games.com.core.impl.ViewHelper.ViewHelperFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe Monteiro
 */
public class Servlet extends HttpServlet
{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        IViewHelper vh = ViewHelperFactory.create(req.getRequestURI());
        
        EntidadeDominio entidade = vh.getEntidade(req, resp);
        
        //Este codigo esta aqui por causa da DASHBOARD e GAMES.jsp!
        String operacao = req.getParameter("operacao") == null ? (String) req.getAttribute("operacao") : req.getParameter("operacao");
        
        ICommand command = AbstractICommand.getCommand(operacao);
        
        Resultado resultado = command.execute(entidade);
        
        vh.setView(resultado, req, resp); 
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        this.doPost(req, resp);
    }
}
