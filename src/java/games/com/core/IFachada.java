/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.core;
import games.com.br.application.Resultado;
import games.com.br.domain.EntidadeDominio;
import java.util.List;
/**
 *
 * @author java
 */
public interface IFachada
{
    public Resultado salvar(EntidadeDominio entidade);
    public Resultado atualizar(EntidadeDominio entidade);
    public Resultado excluir(EntidadeDominio entidade);
    public Resultado consultar(EntidadeDominio entidade);
}
