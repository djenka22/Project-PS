/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ps.view.components;

import java.math.BigDecimal;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.ps.domain.Invoice;
import rs.ac.bg.fon.ps.domain.InvoiceItem;
import rs.ac.bg.fon.ps.domain.MeasurementUnit;
import rs.ac.bg.fon.ps.domain.Product;

/**
 *
 * @author Rusimovic
 */
public class TableModelInvoiceItem extends AbstractTableModel{
    
    
    private final Invoice invoice;
    private final String[] columnName = {"Order no","Product","Unit","Price","Quantity","Total"};

    public TableModelInvoiceItem(Invoice invoice) {
        this.invoice = invoice;
    }
    
   
    

    @Override
    public int getRowCount() {
        if(invoice != null){
            return invoice.getItems().size();
        }
        return 0;
    }

    @Override
    public int getColumnCount() {
        return columnName.length;
    }
    
    public String getColumnName(int column){
        return columnName[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceItem item = invoice.getItems().get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return item.getOrderNumber();
            case 1:
                return item.getProduct().getName();
            case 2:
                return item.getPrice();
            case 3:
                return item.getMeasurementUnit().toString();
            case 4:
                return item.getPrice();
            case 5:
                return item.getQuantity();
            case 6:
                return item.getTotal();
            default:
                return null;
             
        }
    }
    
    public void addInvoiceItem(Product product, BigDecimal quantity, BigDecimal price, MeasurementUnit unit){
        InvoiceItem item = new InvoiceItem();
        item.setOrderNumber(invoice.getItems().size() + 1);
        item.setProduct(product);
        item.setPrice(price);
        item.setQuantity(quantity);
        item.setMeasurementUnit(unit);
        item.setTotal(item.getQuantity().multiply(item.getPrice()));
        invoice.getItems().add(item);
        
        fireTableRowsInserted(invoice.getItems().size() - 1, invoice.getItems().size() - 1);
    }
    
    public void removeInvoiceItem(int row){
        throw new UnsupportedOperationException();
    }
    public void editInvoiceItem(InvoiceItem item, int row){
        throw new UnsupportedOperationException();
    }
}
