/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.repository.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Rusimovic
 */
public class DbConnectionFactory {
    private Connection connection;
    private static DbConnectionFactory instance;
    
    private DbConnectionFactory(){
        
    }
    
    public static DbConnectionFactory getInstance(){
        if(instance == null){
            instance = new DbConnectionFactory();
            
        }
        return instance;
    }
    public Connection getConnection() throws SQLException{
        if (connection == null || connection.isClosed()) {
            try {
                // Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3308/psdb";
                String user = "root";
                String password = "admin";
                connection = DriverManager.getConnection(url, user, password);
                connection.setAutoCommit(false);
            } catch (SQLException ex) {
                System.out.println("Neuspesno uspostavljanje konekcije!\n" + ex.getMessage());
                throw ex;
            }
        }
        return connection;
    }
}
