/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.domain;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Rusimovic
 */
public class Product {
    
    private long ID;
    private String name;
    private String description;
    private BigDecimal price;
    private MeasurementUnit measurementUnit;
    private Manufacturer manufacturer;
    private User savedByUser;

    public Product() {
    }

    public Product(long ID, String name, String description, BigDecimal price, MeasurementUnit measurementUnit, Manufacturer manufacturer, User savedByUser) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.measurementUnit = measurementUnit;
        this.manufacturer = manufacturer;
        this.savedByUser = savedByUser;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public MeasurementUnit getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(MeasurementUnit measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public User getSavedByUser() {
        return savedByUser;
    }

    public void setSavedByUser(User savedByUser) {
        this.savedByUser = savedByUser;
    }

    @Override
    public String toString() {
        return "Product{" + "ID=" + ID + ", name=" + name + ", description=" + description + ", price=" + price + ", measurementUnit=" + measurementUnit + ", manufacturer=" + manufacturer + ", savedByUser=" + savedByUser + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (int) (this.ID ^ (this.ID >>> 32));
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.description);
        hash = 67 * hash + Objects.hashCode(this.price);
        hash = 67 * hash + Objects.hashCode(this.measurementUnit);
        hash = 67 * hash + Objects.hashCode(this.manufacturer);
        hash = 67 * hash + Objects.hashCode(this.savedByUser);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (this.ID != other.ID) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        if (this.measurementUnit != other.measurementUnit) {
            return false;
        }
        if (!Objects.equals(this.manufacturer, other.manufacturer)) {
            return false;
        }
        if (!Objects.equals(this.savedByUser, other.savedByUser)) {
            return false;
        }
        return true;
    }
    
    
}
