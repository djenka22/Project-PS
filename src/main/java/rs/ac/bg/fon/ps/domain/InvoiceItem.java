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
public class InvoiceItem {

    private Invoice invoice;
    private int orderNumber;
    private Product product;
    private BigDecimal price;
    private BigDecimal quantity;
    private MeasurementUnit measurementUnit;
    private BigDecimal total;

    public InvoiceItem() {
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public Product getProduct() {
        return product;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public MeasurementUnit getMeasurementUnit() {
        return measurementUnit;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public void setMeasurementUnit(MeasurementUnit measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.invoice);
        hash = 23 * hash + this.orderNumber;
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
        final InvoiceItem other = (InvoiceItem) obj;
        if (this.orderNumber != other.orderNumber) {
            return false;
        }
        if (!Objects.equals(this.invoice, other.invoice)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "InvoiceItem{" + "invoice=" + invoice + ", orderNumber=" + orderNumber + ", product=" + product + ", price=" + price + ", quantity=" + quantity + ", measurementUnit=" + measurementUnit + ", total=" + total + '}';
    }

}
