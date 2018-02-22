/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duncan_nisbett_c482_software_1;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import View_Controller.MainScreenController;
import View_Controller.PartScreenController;
import javafx.stage.Modality;

/**
 *
 * @author Duncan
 */
public class Duncan_Nisbett_C482_Software_1 extends Application {
    
    private Stage mainStage;
    private AnchorPane mainScreen;
    
    @Override
    public void start(Stage stage) throws IOException {
        this.mainStage = stage;
        this.mainStage.setTitle("Inventory Mangement System"); 
        showMainScreen(stage);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void showMainScreen(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Duncan_Nisbett_C482_Software_1.class.getResource("/View_Controller/MainScreen.fxml"));
        this.mainScreen = (AnchorPane) loader.load();  

        // Give the controller access to the main app.
        MainScreenController controller = loader.getController();
        controller.setDefaults(); 
        controller.startMain(stage); 
    }
}
