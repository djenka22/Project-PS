/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.repository.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ps.domain.Manufacturer;
import rs.ac.bg.fon.ps.domain.MeasurementUnit;
import rs.ac.bg.fon.ps.domain.Product;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.ps.repository.db.DbRepository;

/**
 *
 * @author Rusimovic
 */
public class RepositoryProduct implements DbRepository<Product,Long>{

    private Connection connection;

    public RepositoryProduct() {

    }

    public List<Product> getAll() throws SQLException {

        List<Product> products = new ArrayList<>();
        try {

            String upit = "SELECT p.id as pid, p.name as pname, p.description as description, p.price as price, p.measurementunit as measurementunit, m.id as mid, m.name as mname, u.id as uid, u.firstname as uname, u.lastname as lastname, u.username as username, u.password as password FROM Products p INNER JOIN Manufacturer m on p.manufacturer = m.id INNER JOIN User u on p.savedbyuser = u.id ";

            connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(upit);

            while (rs.next() == true) {
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

    public void add(Product product) throws SQLException {

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

    

    @Override
    public void edit(Product t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Product t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product getById(Long k) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
