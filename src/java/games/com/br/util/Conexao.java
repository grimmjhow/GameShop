/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package games.com.br.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author java
 */
public class Conexao
{
    public static Connection getConnetion() throws ClassNotFoundException, SQLException
    {
        String driver = "org.postgresql.Driver";
        String url = "jdbc:postgresql://localhost:5432/Games";
        String user = "postgres";
        String password = "admin";
        
        Class.forName(driver);
        
        Connection conn = DriverManager.getConnection(url,user, password);
        
        return conn;
    }
}
