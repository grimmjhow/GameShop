/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.core.impl.ViewHelper;

import games.com.br.application.Resultado;
import games.com.br.domain.EntidadeDominio;
import games.com.br.domain.Jogo;
import games.com.core.IViewHelper;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe
 */
public class SalvarJogoVHWeb implements IViewHelper
{
    private Jogo jogo;
    
    @Override
    public EntidadeDominio getEntidade(HttpServletRequest request, HttpServletResponse response)
    {
        jogo = new Jogo();
        
        jogo.setTitulo(request.getParameter("txtTitulo"));
        jogo.setDescricao(request.getParameter("txtDescricao"));
        jogo.setGenero(request.getParameter("txtGenero"));
        jogo.setFaixaEtaria(request.getParameter("txtFaixa"));
        
        String valor = request.getParameter("txtValor").replace(",", ".");
        valor = valor == null || valor.equals("") ? "0": valor;
        jogo.setValor(Float.parseFloat(valor));
        jogo.setAno(request.getParameter("txtAnoLancamento"));
        String multi = request.getParameter("ckbMultiPlayer") == null ? "off":request.getParameter("ckbMultiPlayer");
        jogo.setMultiplayer(multi.equals("on") ? "S":"N"); 
        
        String online = request.getParameter("ckbOnline") == null ? "N":request.getParameter("ckbOnline");
        jogo.setOnline(online.equals("on")?"S":"N");
        
        jogo.setIdioma(request.getParameter("txtLinguagem"));
        
        String imagem = "images/"+request.getParameter("txtImagem");
        
        if( request.getParameter("txtImagem") == null || request.getParameter("txtImagem").equals(""))
            imagem = "images/sem-imagem.jpg";
        
        jogo.setImage(imagem);
        
        if(request.getParameter("txtQuantidade") == null || request.getParameter("txtQuantidade").equals(""))
            jogo.setQuantidade(0);
        else
            jogo.setQuantidade(Integer.parseInt(request.getParameter("txtQuantidade")));
        
        return jogo;
    }

    @Override
    public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
    {
        if(resultado.getListMensagens().isEmpty())
        {
           request.setAttribute("sucessSalvarJogo", "Jogo Salvo com Sucesso!");
        }
        else
        {
            request.setAttribute("jogo", jogo);
            request.setAttribute("erroSalvarJogo", resultado.getListMensagens());
        }
        try
        {
            request.getRequestDispatcher("SalvarJogos.jsp").forward(request, response);
        } catch (ServletException ex)
        {
            Logger.getLogger(SalvarJogoVHWeb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(SalvarJogoVHWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
