/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.core;
import games.com.br.domain.EntidadeDominio;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author java
 */
public interface IDAO
{
    public void salvar(EntidadeDominio entidade) throws SQLException;
    public void atualizar(EntidadeDominio entidade) throws SQLException;
    public void excluir(EntidadeDominio entidade) throws SQLException;
    public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException;
}
