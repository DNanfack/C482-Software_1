/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Duncan
 */
public class Inventory {
    
    private static ObservableList<Part> partList = FXCollections.observableArrayList();
    private static ObservableList<Product> productList = FXCollections.observableArrayList();
    private static int partIDCount = 0;
    private static int productIDCount = 0;
    
    //Parts
    public static ObservableList<Part> getPartList() {
        return partList;
    }

    public static void addPart(Part part) {
        partList.add(part);
    }
    
   
    public static void removePart(Part part) {
        partList.remove(part);
    }

    public static void updatePart(int index, Part part) {
        partList.set(index, part);
    }

    public static int getPartIDCount() {
        partIDCount += 1;
        return partIDCount;
    }
    
    public static int cancelPartIDCount() {
        partIDCount -= 1;
        return partIDCount;
    }

    public static ObservableList lookupPart(String searchTerm) {
        ObservableList<Part> foundParts = FXCollections.observableArrayList();
        
        if(searchTerm.length() == 0) {
            foundParts = partList;//return all if blank as passed in as search term
        } else {
            for (int i = 0; i < partList.size(); i++) {
                if (partList.get(i).getName().toLowerCase().contains(searchTerm.toLowerCase())) {
                    foundParts.add(partList.get(i));
                }
            }    
        }
        
        return foundParts;
    }
    
    //Products
    public static ObservableList<Product> getProductList() {
        return productList;
    }

    public static void addProduct(Product product) {
        productList.add(product);
    }

    public static void removeProduct(Product product) {
        productList.remove(product);
    }

    public static int getProductIDCount() {
        productIDCount += 1;
        return productIDCount;
    }
   
    public static int cancelProductIDCount() {
        productIDCount -= 1;
        return productIDCount;
    }

    public static ObservableList lookupProduct(String searchTerm) {
        ObservableList<Product> foundProducts = FXCollections.observableArrayList();
        
        if(searchTerm.length() == 0) {
            foundProducts = productList;//return all if blank as passed in as search term
        } else {
            for (int i = 0; i < productList.size(); i++) {
                if (productList.get(i).getName().toLowerCase().contains(searchTerm.toLowerCase())) {
                    foundProducts.add(productList.get(i));
                }
            }    
        }
        
        return foundProducts;
    }
    
    public static void updateProduct(int index, Product product) {
        productList.set(index, product);
    }
}
