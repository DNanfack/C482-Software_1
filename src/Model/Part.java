/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Duncan
 */
public abstract class Part {
    protected IntegerProperty inStock;
    protected IntegerProperty max;
    protected IntegerProperty min;
    protected StringProperty name;
    protected IntegerProperty partID;
    protected DoubleProperty price;
            
    //partID
    public void setPartID(int partID) {
        this.partID.set(partID);
    }
    
    public int getPartID() {
        return this.partID.get();
    }
    
    public IntegerProperty partIDProperty() {
        return partID;
    }
    
    //name
    public void setName(String name) {
        this.name.set(name);
    }
    
    public String getName() {
        return this.name.get();
    }
    
    public StringProperty nameProperty() {
        return name;
    }
    
    //price
    public void setPrice(double price) {
        this.price.set(price);
    }
    
    public double getPrice() {
        return this.price.get();
    }
    
    public DoubleProperty priceProperty() {
        return price;
    }
    
    //inStock
    public void setInStock(int inStock) {
        this.inStock.set(inStock);
    }
    
    public int getInStock() {
        return this.inStock.get();
    }
    
    public IntegerProperty inStockProperty() {
        return inStock;
    }
    
    //min
    public void setMin(int min) {
        this.min.set(min);
    }
    
    public int getMin() {
        return this.min.get();
    }
    
    public IntegerProperty minProperty() {
        return min;
    }
    
    //max
    public void setMax(int max) {
        this.max.set(max);
    }
    
    public int getMax() {
        return this.max.get();
    }
    
    public IntegerProperty maxProperty() {
        return max;
    }
}
