/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.repository.db.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ps.domain.Manufacturer;
import rs.ac.bg.fon.ps.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.ps.repository.db.DbRepository;

/**
 *
 * @author Rusimovic
 */
public class RepositoryManufacturer implements DbRepository<Manufacturer,Long>{

    private Connection connection;

    public RepositoryManufacturer() {

    }

    public List<Manufacturer> getAll() throws Exception {
        try {
            List<Manufacturer> manufacturers = new ArrayList<>();

            String upit = "SELECT id, name FROM Manufacturer";
            connection = DbConnectionFactory.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(upit);
            while (rs.next()) {
                Manufacturer manufacturer = new Manufacturer();
                manufacturer.setID(rs.getLong("id"));
                manufacturer.setName(rs.getString("name"));
                manufacturers.add(manufacturer);

            }
            rs.close();
            statement.close();
             System.out.println("Uspesno ucitavanje liste Manufacturer");
            return manufacturers;
        } catch (SQLException ex) {
            //System.out.println("Neuspesno ucitavanje liste manufacturer\n" + ex.getMessage());
            throw new Exception("Neuspesno ucitavanje liste manufacturer\n" + ex);

        }
    }

    @Override
    public void add(Manufacturer t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(Manufacturer t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Manufacturer t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Manufacturer getById(Long k) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
