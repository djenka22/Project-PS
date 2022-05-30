/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.ps.domain.Manufacturer;
import rs.ac.bg.fon.ps.domain.MeasurementUnit;
import rs.ac.bg.fon.ps.domain.Product;
import rs.ac.bg.fon.ps.domain.User;

/**
 *
 * @author Rusimovic
 */
public class DatabaseBroker {
    private Connection connection;

    public void uspostaviKonekciju() throws SQLException {
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
    }

    public void raskiniKonekciju() throws SQLException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Neuspesno raskidanje konekcije!\n" + ex.getMessage());
                throw ex;
            };
        }
    }
    
    public void potvrdiTransakciju() throws SQLException{
        if(connection != null){
            
            try {
                connection.commit();
                System.out.println("Uspesno potvrdjivanje transakcije!");
            } catch (SQLException ex) {
                System.out.println("Neuspesno potvrdjivanje transakcije!\n"+ex.getMessage());
                throw ex;
            }
        }
    }
    public void ponistiTransakciju() throws SQLException{
        if(connection != null){
            
            try {
                connection.rollback();
                System.out.println("Uspesno ponistavanje transakcije!");
            } catch (SQLException ex) {
                System.out.println("Neuspesno ponistavanje transakcije!\n"+ex.getMessage());
                throw ex;
            }
        }
    }
    
    public void kreirajManufacturer(Manufacturer manufacturer) throws SQLException{
        
        try {
            String upit = "INSERT INTO Manufacturer (id,name) VALUES ("
                    + "" +manufacturer.getID()+ ","
                    + "'" +manufacturer.getName()+"'"
                    +")";
            Statement statement = connection.createStatement();
            statement.executeUpdate(upit);
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Neuspesno kreiranje manufacturer\n" + ex.getMessage());
            throw ex;
        }
    }
    
    public List<Manufacturer> vratiSveManufacturer() throws SQLException{
        try {
            List<Manufacturer> manufacturers = new ArrayList<>();
            
            String upit = "SELECT id, name FROM Manufacturer";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(upit);
            while(rs.next()){
                Manufacturer manufacturer = new Manufacturer();
                manufacturer.setID(rs.getLong("id"));
                manufacturer.setName(rs.getString("name"));
                manufacturers.add(manufacturer);                
                
            }
            rs.close();
            statement.close();
            return manufacturers;
        } catch (SQLException ex) {
            System.out.println("Neuspesno ucitavanje liste manufacturer\n" + ex.getMessage());
            throw ex;

        }
    }
    
    
    /*public void kreirajProduct(Product product) throws SQLException{
        
        try {
            String upit = "INSERT into Products(id,name,description,price,measurementUnit,manufacturer,savedByUser) VALUES ("
                    +"" +product.getID() + ", "
                    + "'" + product.getName() + "', "
                    + "'" + product.getDescription() + "',"
                    +  product.getPrice() + ","
                    + "'" + product.getMeasurementUnit().toString() + "',"
                    +  product.getManufacturer().getID() + ","
                    +  product.getSavedByUser().getId() + ")";
            
            Statement statement = connection.createStatement();
            statement.executeUpdate(upit);
            statement.close();
            System.out.println("Uspesno kreiranje product");
        } catch (SQLException ex) {
            System.out.println("Neuspesno kreiranje product!");
            throw ex;
        }
    }*/
    
    public void kreirajProductBolji(Product product) throws SQLException{
        
        try {
            String upit = "INSERT into Products(id,name,description,price,measurementUnit,manufacturer,savedByUser) VALUES  (?,?,?,?,?,?,?)";
            
            PreparedStatement statement = connection.prepareStatement(upit);
            statement.setLong(1, product.getID());
            statement.setString(2, product.getName());
            statement.setString(3, product.getDescription());
            statement.setBigDecimal(4, product.getPrice());
            statement.setString(5, product.getMeasurementUnit().toString());
            statement.setLong(6, product.getManufacturer().getID());
            statement.setLong(7, product.getSavedByUser().getId());
            
            statement.executeUpdate();
            System.out.println("Uspesno kreiranje product");
        } catch (SQLException ex) {
            System.out.println("Neuspesno kreiranje product!");
            throw ex;
        }
    }
    
    public void sacuvajSveProduct(List<Product> products) throws SQLException{
        
        try {
            String upit = "INSERT into Products(id,name,description,price,measurementUnit,manufacturer,savedByUser) VALUES  (?,?,?,?,?,?,?)";
            
            PreparedStatement statement = connection.prepareStatement(upit);
            for(Product product : products){
            statement.setLong(1, product.getID());
            statement.setString(2, product.getName());
            statement.setString(3, product.getDescription());
            statement.setBigDecimal(4, product.getPrice());
            statement.setString(5, product.getMeasurementUnit().toString());
            statement.setLong(6, product.getManufacturer().getID());
            statement.setLong(7, product.getSavedByUser().getId());
            
            statement.executeUpdate();
            }
            System.out.println("Uspesno kreiranje liste product");
        } catch (SQLException ex) {
            System.out.println("Neuspesno kreiranje liste product!");
            throw ex;
        }
    }
    
    public List<Product> getAllProduct() throws SQLException{
         List<Product> products = new ArrayList<>();
        try {
           
            String upit = "SELECT p.id as pid, p.name as pname, p.description as description, p.price as price, p.measurementunit as measurementunit, m.id as mid, m.name as mname, u.id as uid, u.name as uname, u.lastname as lastname, u.username as username, u.password as password "
                    + "FROM Products p INNER JOIN Manufacturer m INNER JOIN User u";
            
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(upit);
            
            while(rs.next() == true){
                Product product = new Product();
                product.setID(rs.getLong("pid"));
                product.setName(rs.getString("pname"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setMeasurementUnit(MeasurementUnit.valueOf(rs.getString("measurementunit")));
                Manufacturer manufacturer = new Manufacturer();
                manufacturer.setID(rs.getLong("mid"));
                manufacturer.setName(rs.getString("mname"));
                product.setManufacturer(manufacturer);
                User user = new User();
                user.setId(rs.getLong("uid"));
                user.setFirstName(rs.getString("uname"));
                user.setLastName(rs.getString("lastname"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                product.setSavedByUser(user);
                products.add(product);
            }
            rs.close();
            statement.close();
            System.out.println("Uspesno ucitavanje liste Product");
        } catch (SQLException ex) {
           System.out.println("Neuspesno ucitavanje liste Product!\n" + ex.getMessage());
           throw ex;
        }
        return products;
    }
    

}


