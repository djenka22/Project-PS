/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import rs.ac.bg.fon.ps.domain.Invoice;
import rs.ac.bg.fon.ps.domain.Manufacturer;
import rs.ac.bg.fon.ps.domain.Product;
import rs.ac.bg.fon.ps.domain.User;
import rs.ac.bg.fon.ps.repository.Repository;
import rs.ac.bg.fon.ps.repository.db.DbRepository;
import rs.ac.bg.fon.ps.repository.db.impl.RepositoryInvoice;
import rs.ac.bg.fon.ps.repository.db.impl.RepositoryManufacturer;
import rs.ac.bg.fon.ps.repository.db.impl.RepositoryProduct;
import rs.ac.bg.fon.ps.repository.db.impl.RepositoryUser;

/**
 *
 * @author Rusimovic
 */
public class Controller {

    private static Controller instance;
    private final DbRepository storageUser;
    private final DbRepository storageManufacturer;
    private final DbRepository storageProduct;
    private final DbRepository storageInvoice;
    private User currentUser;

    private Controller() {
        this.storageUser = new RepositoryUser();
        this.storageManufacturer = new RepositoryManufacturer();
        this.storageProduct = new RepositoryProduct();
        this.storageInvoice = new RepositoryInvoice();
    }

    public User login(String username, String password) throws Exception {
        try {
            storageUser.connect();

            List<User> users = storageUser.getAll();
            storageUser.commit();
            for (User user : users) {
                if (user.getUsername().equals(username) & user.getPassword().equals(password)) {
                    return user;
                }
            }
            throw new Exception("Unknown user");
        } catch (Exception ex) {
            storageUser.rollback();
            throw ex;
        } finally {
            storageUser.disconnect();
        }

    }

    public List<Manufacturer> getAllManufacturers() throws Exception {
        return storageManufacturer.getAll();
    }

    public void addProduct(Product product) throws Exception {
        storageInvoice.connect();
        try {
            if (!storageInvoice.getAll().contains(product)) {
                storageInvoice.add(product);
                storageInvoice.commit();
                System.out.println("Uspesno dodavanje product!");
            } else {
                throw new Exception("Product alredy exists");

            }
        } catch (Exception ex) {
            storageInvoice.rollback();
        } finally{
            storageInvoice.disconnect();
        }
    }

    public static Controller getInstance() {

        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public List<Product> getAllProducts() throws Exception {
        return storageInvoice.getAll();
    }

    public void addPInvoice(Invoice invoice) throws Exception {
        storageInvoice.connect();
        try {
            if (!storageInvoice.getAll().contains(invoice)) {
                storageInvoice.add(invoice);
                storageInvoice.commit();
                System.out.println("Uspesno dodavanje invoice!");
            } else {
                throw new Exception("Invoice alredy exists");

            }
        } catch (Exception ex) {
            storageInvoice.rollback();
        } finally{
            storageInvoice.disconnect();
        }
    }
}
