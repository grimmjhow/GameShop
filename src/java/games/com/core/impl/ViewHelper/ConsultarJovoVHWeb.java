/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.core.impl.ViewHelper;

import games.com.br.application.Resultado;
import games.com.br.domain.EntidadeDominio;
import games.com.br.domain.Jogo;
import games.com.br.domain.Usuario;
import games.com.core.IViewHelper;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe Monteiro
 */
public class ConsultarJovoVHWeb implements IViewHelper
{

    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
        Jogo jogo = new Jogo();
        
        String titulo = request.getParameter("txtProcuraJogo"); 
        String genero = request.getParameter("selGenero");
        String ano = request.getParameter("txtAno");
        String faixa = request.getParameter("txtFaixa");
        
        //previnindo nullpointer
        jogo.setTitulo(titulo == null ? "":titulo); 
        jogo.setGenero(genero == null ? "":genero);
        jogo.setAno(ano == null ? "":ano);
        jogo.setFaixaEtaria(faixa == null ? "":faixa);
        
        if(request.getParameter("operacao") == null)
            request.setAttribute("operacao", "Consultar");
        
        return jogo;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
        if(resultado.getEntidades().isEmpty())
        {
            request.setAttribute("ConsultaJogoErro", "Nenhum Jogo encontrado com esse Titulo");
        }
        else
        {
            request.setAttribute("jogos", resultado.getEntidades());
        }
        
        try
        {
            Usuario usuario = (Usuario) request.getSession().getAttribute("user");
            
            if(usuario != null && usuario.getTipo().equalsIgnoreCase("admin"))
                request.getRequestDispatcher("/root/dashboard.jsp").forward(request, response);
            else
                request.getRequestDispatcher("Games.jsp").forward(request, response);
            
        } catch (ServletException ex)
        {
            Logger.getLogger(ConsultarJovoVHWeb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(ConsultarJovoVHWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
