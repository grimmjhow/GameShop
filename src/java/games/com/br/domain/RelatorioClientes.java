/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.br.domain;

import games.com.core.impl.dao.RelatorioClientesDAO;
import de.laures.cewolf.DatasetProduceException;
import de.laures.cewolf.DatasetProducer;
import games.com.core.IDAO;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Felipe
 */
public class RelatorioClientes extends EntidadeDominio implements DatasetProducer
{
    private String nome;
    private float gasto;
    private int compras;
    private String idade;
    private String dt;
    
    @Override
    public Object produceDataset(Map map) throws DatasetProduceException
    {
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        List<EntidadeDominio> entidades = null;
        IDAO dao = new RelatorioClientesDAO();
        String [] month = {"Jan","Fev","Mar","Abr","Mai","Jun","Jul"};
        try
        {
            entidades = dao.consultar(null);
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        
        for (EntidadeDominio ed : entidades)
        {
            RelatorioClientes rl = (RelatorioClientes) ed;
            Integer mth = new Integer(rl.dt);
            ds.setValue(rl.getGasto(), rl.getNome(), month[mth-1]);
        }
        
        /*
         69;"Felipe Arruda";866;5;23
         77;"Laisla Aparecida do Prado";444.099998474121;4;22
         65;"Elaine Arruda";387.690498352051;3;23
         */
        
        return ds;
    }

    @Override
    public boolean hasExpired(Map map, Date date)
    {
        return true;
    }

    @Override
    public String getProducerId()
    {
        return "ClienteGrafico";
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public float getGasto()
    {
        return gasto;
    }

    public void setGasto(float gasto)
    {
        this.gasto = gasto;
    }

    public int getCompras()
    {
        return compras;
    }

    public void setCompras(int compras)
    {
        this.compras = compras;
    }

    public String getIdade()
    {
        return idade;
    }

    public void setIdade(String idade)
    {
        this.idade = idade;
    }

    public String getDt()
    {
        return dt;
    }

    public void setDt(String dt)
    {
        this.dt = dt;
    }
}
