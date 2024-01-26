package IHM;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import persistance.PersonneDAO;
import persistance.PersonneDAOImpl;

public class AjoutPeronne extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("JavaFX Example");

        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Ajout.fxml"));

        // Create an instance of PersonneDAO
        PersonneDAO personneDAO = new PersonneDAOImpl();

        // Create an AjoutController instance and inject the PersonneDAO
        AjoutController ajoutController = new AjoutController(personneDAO);

        // Set the controller for the FXMLLoader
        loader.setController(ajoutController);

        // Load the FXML
        Parent root = loader.load();

        // Create the scene
        Scene scene = new Scene(root, 500, 200);

        // Set the scene to the stage
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();
    }
}



        
        // Label l = new Label();
        // l.setText("Cin");
        // TextField e=new TextField();
        // Label l1 = new Label();
        // l1.setText("Nom");
        //   TextField e1=new TextField();
        //   Label l2 = new Label();
        //  l2.setText("pernom");
        //    TextField e2=new TextField();
        //    Label l3 = new Label();
        //   l3.setText("Civilité");
        //   RadioButton r1=new RadioButton();
        //   r1.setText("mr");
        //   RadioButton r2=new RadioButton();
        //   r2.setText("mme");
        //   RadioButton r3=new RadioButton();
        //   r3.setText("melle");
        //   VBox v= new VBox(10);
        //   v.getChildren().addAll(r1,r2,r3);
        // Button button1 = new Button("Ajouter");
        // button1.setOnAction(a -> handleAjouterButtonClick(a, e.getText(), e1.getText(), e2.getText()));
        // Button button2 = new Button("Annuler");
        //  GridPane grid = new GridPane();
        //  grid.setHgap(10); 
        //  grid.setVgap(10);
        //  grid.add(l, 0, 0);
        //  grid.add(e, 1, 0);
 
        //  grid.add(l1, 0, 1);
        //  grid.add(e1, 1, 1);
 
        //  grid.add(l2, 0, 2);
        //  grid.add(e2, 1, 2);
 
        //  grid.add(l3, 0, 3);
        //  grid.add(v, 1, 3);
 
        //  grid.add(button1, 0, 4);
        //  grid.add(button2, 1, 4);
 
    //   Parent root = new FXMLLoader(getClass().getResource("/home/chaden/Bureau/TpJava/TP6/IHM/Ajout.fxml"));

    //     Scene scene = new Scene(root, 500, 200);
    //     primaryStage.setScene(scene);

    //     primaryStage.show();
    // }
//     private void handleAjouterButtonClick(ActionEvent event, String cin, String nom, String prenom) {
//         if (cin.isEmpty() || nom.isEmpty() || prenom.isEmpty()) {
//             showAlert("Error", "Champs vides", "Veuillez remplir tous les champs.");
//         } else {
             
           
//             System.out.println("Ajouter clicked");
//         }
//     }

//     private void showAlert(String title, String header, String content) {
//         Alert alert = new Alert(Alert.AlertType.ERROR);
//         alert.setTitle(title);
//         alert.setHeaderText(header);
//         alert.setContentText(content);
//         alert.showAndWait();
//     }


// }

