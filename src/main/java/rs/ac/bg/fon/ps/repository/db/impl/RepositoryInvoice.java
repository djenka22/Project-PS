/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.repository.db.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import rs.ac.bg.fon.ps.domain.Invoice;
import rs.ac.bg.fon.ps.domain.InvoiceItem;
import rs.ac.bg.fon.ps.domain.MeasurementUnit;
import rs.ac.bg.fon.ps.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.ps.repository.db.DbRepository;

/**
 *
 * @author Rusimovic
 */
public class RepositoryInvoice implements DbRepository<Invoice,Long> {

    @Override
    public List<Invoice> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Invoice invoice) throws Exception {
       Connection connection = DbConnectionFactory.getInstance().getConnection();
       String upit = "INSERT INTO Invoice (number,date,total) VALUES (?,?,?)";
       PreparedStatement statement = connection.prepareStatement(upit,Statement.RETURN_GENERATED_KEYS);
       statement.setString(1, invoice.getNubmer());
       statement.setDate(2, new java.sql.Date(invoice.getDate().getTime()));
       statement.setBigDecimal(3, invoice.getTotal());
       
       statement.executeQuery();
       ResultSet rs = statement.getGeneratedKeys();
       if(rs.next()){
           Long invoiceID = rs.getLong(1);
           invoice.setId(invoiceID);
           upit = "INSERT INTO Invoice_item VALUES(?,?,?,?,?,?,?)";
           statement = connection.prepareStatement(upit);
           
           for(InvoiceItem item : invoice.getItems()){
               statement.setLong(1, invoice.getId());
               statement.setInt(2, item.getOrderNumber());
               statement.setLong(3, item.getProduct().getID());
               statement.setBigDecimal(4, item.getPrice());
               statement.setBigDecimal(5, item.getQuantity());
               statement.setString(6, item.getMeasurementUnit().toString());
               statement.setBigDecimal(7, item.getTotal());
           }
           
       }else{
           throw new Exception("Invoice id is not generated");
       }
    }

    @Override
    public void edit(Invoice t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Invoice t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Invoice getById(Long k) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
