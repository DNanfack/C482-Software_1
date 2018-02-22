/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.InHousePart;
import Model.Part;
import Model.Product;
import Model.Inventory;
import static Model.Inventory.getPartIDCount;
import static Model.Inventory.getPartList;
import static Model.Inventory.getProductList;
import static Model.Inventory.removePart;
import static Model.Inventory.removeProduct;
import Model.OutsourcedPart;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Duncan
 */

public class MainScreenController {

    @FXML
    private TableView<Part> partTable;

    @FXML
    private TableColumn<Part, Integer> partIdColumn;

    @FXML
    private TableColumn<Part, String> partNameColumn;

    @FXML
    private TableColumn<Part, Integer> partInvColumn;

    @FXML
    private TableColumn<Part, Double> partCostColumn;

    @FXML
    private TextField searchPart;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private TableColumn<Product, Integer> productIdColumn;

    @FXML
    private TableColumn<Product, String> productNameColumn;

    @FXML
    private TableColumn<Product, Integer> productInvColumn;

    @FXML
    private TableColumn<Product, Double> productCostColumn;

    @FXML
    private TextField searchProduct;
    
    private static int index = -1;
    
    public static int selectedIndex() {
        return index;
    }
    
    @FXML
    void accessPart(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PartScreen.fxml"));
        Parent part_page_parent = loader.load();  
       
        Scene part_page_scene = new Scene(part_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        PartScreenController controller = loader.getController();
        controller.startPart();
        
        app_stage.hide(); //optional
        app_stage.setScene(part_page_scene);
        app_stage.show();
    }
    
    @FXML
    void accessPart(ActionEvent event, Part part) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PartScreen.fxml"));
        Parent part_page_parent = loader.load();  
       
        Scene part_page_scene = new Scene(part_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        PartScreenController controller = loader.getController();
        controller.startPart(part);
        
        app_stage.hide(); //optional
        app_stage.setScene(part_page_scene);
        app_stage.show();
    }
    
    @FXML
    void accessProduct(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ProductScreen.fxml"));
        Parent product_page_parent = loader.load();  
       
        Scene product_page_scene = new Scene(product_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        ProductScreenController controller = loader.getController();
        controller.startProduct();
        
        app_stage.hide(); //optional
        app_stage.setScene(product_page_scene);
        app_stage.show();
    }
    
    @FXML
    void accessProduct(ActionEvent event, Product product) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ProductScreen.fxml"));
        Parent product_page_parent = loader.load();  
       
        Scene product_page_scene = new Scene(product_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        ProductScreenController controller = loader.getController();
        controller.startProduct(product);
        
        app_stage.hide(); //optional
        app_stage.setScene(product_page_scene);
        app_stage.show();
    }
    
    @FXML
    void addPart(ActionEvent event) throws IOException {
        accessPart(event);
    }

    @FXML
    void addProduct(ActionEvent event) throws IOException {
        accessProduct(event);
    }

    @FXML
    void changePart(ActionEvent event) throws IOException {
        Part selectedPart = partTable.getSelectionModel().getSelectedItem();
        index = getPartList().indexOf(selectedPart);

        if(selectedPart != null) {
            accessPart(event, selectedPart);    
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("No Part selected");
            alert.setHeaderText("Please select a part from the existing list to modify"); 
            alert.showAndWait();
        }
    }

    @FXML
    void changeProduct(ActionEvent event) throws IOException {
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        index = getProductList().indexOf(selectedProduct);

        if(selectedProduct != null) {
            accessProduct(event, selectedProduct);    
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("No Product selected");
            alert.setHeaderText("Please select a product from the existing list to modify"); 
            alert.showAndWait();
        }
    }

    @FXML
    void deletePart(ActionEvent event) {
        Part selectedPart = partTable.getSelectionModel().getSelectedItem();
        
        if(selectedPart != null) {
            Boolean found = false;
            //this uses recursion, I'm sure there is a better way to do it
            for (int i = 0; i < Inventory.getProductList().size(); i++) {
                ObservableList<Part> productParts = Inventory.getProductList().get(i).getProductParts();
                for(int x = 0; x < productParts.size(); x++) {
                    if(productParts.get(x).equals(selectedPart)) {
                       found = true; 
                    }
                }
            } 
            
            if(found) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.setTitle("Unable to delete part");
                alert.setHeaderText("Part is linked to a current product and cannot be deleted"); 
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.initModality(Modality.NONE);
                alert.setTitle("Part Delete Confirmation");
                alert.setHeaderText("Confirm deletion of part");
                alert.setContentText("Are you sure you want to delete " + selectedPart.getName()+ "?");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {
                    removePart(selectedPart);
                    updatePartTableView();
                }    
            }
            
            
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("No Part selected");
            alert.setHeaderText("Please select a part from the existing list to delete"); 
            alert.showAndWait();
        }
    }

    @FXML
    void deleteProduct(ActionEvent event) {
        //make Product has no parts, alert if true
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        
        if(selectedProduct != null) {
            if(selectedProduct.getProductParts().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.initModality(Modality.NONE);
                alert.setTitle("Product Delete Confirmation");
                alert.setHeaderText("Confirm deletion of product");
                alert.setContentText("Are you sure you want to delete " + selectedProduct.getName()+ "?");
                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.OK) {
                    removeProduct(selectedProduct);
                    updatePartTableView();
                }    
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.setTitle("Product has parts");
                alert.setHeaderText("Unable to delete a product that has parts associated with it"); 
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("No Product selected");
            alert.setHeaderText("Please select a product from the existing list to delete"); 
            alert.showAndWait();
        }
    }

    @FXML
    void exitProgram(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Confirm Exit");
        alert.setHeaderText("Please confirm you want to exit.");
        alert.setContentText("Any unsaved changes will be lost.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.exit(0);
        }
    }

    @FXML
    void searchParts(ActionEvent event) {
        String term = searchPart.getText();
        ObservableList foundParts = Inventory.lookupPart(term);
        if(foundParts.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("No Part match found");
            alert.setHeaderText("No Part Names found matching " + term); 
            alert.showAndWait();
        } else {
            partTable.setItems(foundParts);
        }
    }

    @FXML
    void searchProducts(ActionEvent event) {
        String term = searchProduct.getText();
        ObservableList foundProducts = Inventory.lookupProduct(term);
        if(foundProducts.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("No Product match found");
            alert.setHeaderText("No Product Names found matching " + term); 
            alert.showAndWait();
        } else {
            productTable.setItems(foundProducts);
        }
    }
    
    public void updatePartTableView() {
        partTable.setItems(getPartList());
    }

    public void updateProductTableView() {
        ObservableList<Product> productItems = getProductList();
        productTable.setItems(productItems);
    }
    
    public void initialize() {
        partIdColumn.setCellValueFactory(cellData -> cellData.getValue().partIDProperty().asObject());
        partNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        partInvColumn.setCellValueFactory(cellData -> cellData.getValue().inStockProperty().asObject());
        partCostColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        
        productIdColumn.setCellValueFactory(cellData -> cellData.getValue().productIDProperty().asObject());
        productNameColumn.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        productInvColumn.setCellValueFactory(cellData -> cellData.getValue().productInStockProperty().asObject());
        productCostColumn.setCellValueFactory(cellData -> cellData.getValue().productPriceProperty().asObject());
        
        updatePartTableView();
        updateProductTableView();
    }

    public void startMain(Stage stage) throws IOException {
        index = -1;//reset index each time main screen loads
        Parent part_page_parent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene part_page_scene = new Scene(part_page_parent);
        stage.setScene(part_page_scene);
        stage.show(); 
        
        updatePartTableView();
        updateProductTableView();
    }

    private void defaultParts() {
        int partID = Inventory.getPartIDCount();
        Inventory.addPart(new InHousePart(partID, "Flux Capacitor", 31.03, 42, 1, 99, 42));
        Inventory.addPart(new OutsourcedPart(getPartIDCount(), "Plutonium", 75, 2, 1, 250, "Czech"));
    }

    private void defaultProducts() {
        int productID = Inventory.getProductIDCount();
        ObservableList<Part> emptyParts = FXCollections.observableArrayList();
        Product product1 = new Product(productID, "No Parts", 999, 25, 1, 50);
        product1.setProductParts(emptyParts);
        Inventory.addProduct(product1);
        
        ObservableList<Part> copyParts = Inventory.lookupPart("Flux");
        Product product2 = new Product(Inventory.getProductIDCount(), "One Part", 234.11, 12, 1, 999);
        product2.setProductParts(copyParts);
        Inventory.addProduct(product2);
    }

    public void setDefaults() {
        defaultParts();
        defaultProducts(); 
    }

}
