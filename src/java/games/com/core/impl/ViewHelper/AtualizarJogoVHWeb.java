package games.com.core.impl.ViewHelper;

import games.com.br.application.Resultado;
import games.com.br.domain.EntidadeDominio;
import games.com.br.domain.Jogo;
import games.com.core.IViewHelper;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe Monteiro
 */
public class AtualizarJogoVHWeb implements IViewHelper 
{
    private Jogo jogo;
    
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
        jogo = new Jogo();
        
        int id = Integer.parseInt(request.getParameter("txtId"));
        String titulo = request.getParameter("txtTitulo");
        String descricao = request.getParameter("txtDescricao").trim();
        String genero = request.getParameter("txtGenero");
        String faixa = request.getParameter("txtFaixa");
        float valor =  request.getParameter("txtValor").equals("") ? 0 : Float.parseFloat(request.getParameter("txtValor").replace(",", "."));
        String ano = request.getParameter("txtAno");
        String multiplayer = request.getParameter("ckbMultiPlayer") == null ? "N" : "S";
        String online = request.getParameter("ckbOnline") == null ? "N" : "S";
        String idioma = request.getParameter("txtIdioma");
        
        jogo.setTitulo(titulo);
        jogo.setDescricao(descricao);
        jogo.setGenero(genero);
        jogo.setFaixaEtaria(faixa);
        jogo.setValor(valor);
        jogo.setAno(ano);
        jogo.setMultiplayer(multiplayer);
        jogo.setOnline(online);
        jogo.setIdioma(idioma);
        jogo.setId(id);
        
        return jogo;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
        RequestDispatcher rq = null;
        if(!resultado.getListMensagens().isEmpty()) //a lista de mensagens nao eh vazia!
        {
            request.setAttribute("jogo", jogo);
            request.setAttribute("erroAtualizarJogo", resultado.getListMensagens());
            rq = request.getRequestDispatcher("AtualizarJogo.jsp");
        }
        else
        {
           request.setAttribute("sucessAtualizarJogo", jogo.getTitulo()+" atualizado com sucesso!");
           rq = request.getRequestDispatcher("dashboard.jsp");
        }
        
        try
        {
            rq.forward(request, response);
        } 
        catch (ServletException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
    
}
