package IHM;

 import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import metier.Personne;
import persistance.PersonneDAO;
import javafx.scene.control.Alert.AlertType;

public class AjoutController implements Initializable{

    private PersonneDAO personneDAO;
    public AjoutController(PersonneDAO personneDAO) {
        this.personneDAO = personneDAO;
    }
    
    public AjoutController() {
        // Default constructor
    }

    @FXML
    private Button BAjouter;

    @FXML
    private Button BAnnuler;

    @FXML
    private RadioButton Rmelle;

    @FXML
    private RadioButton Rmme;

    @FXML
    private RadioButton Rmr;

    @FXML
    private TextField TCin;

    @FXML
    private TextField TNom;

    @FXML
    private TextField Tprenom;

     // Helper method to show an alert
    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
    @FXML
    private void handleAjouter(ActionEvent event) {
        // Check if personneDAO is not null before using it
        if (personneDAO != null) {
            
            // Get values from UI controls
            int cin = Integer.parseInt(TCin.getText());
            String nom = TNom.getText();
            String prenom = Tprenom.getText(); // You need to retrieve the value from your UI controls
            String civilite = Rmelle.getText(); // You need to retrieve the value from your UI controls
       
            // Create a Personne object
            Personne personne = new Personne();
            personne.setCin(cin);
            personne.setNom(nom);
            personne.setPrenom(prenom);
            personne.setCivilite(civilite);

            // Call the add method from the DAO
            personneDAO.add(personne);

            // Show a success message
           // showAlert("Success", "Personne  successfully.");
             Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("Personne aded successfully.");
        alert.showAndWait();
        } else {
            // Handle the case where personneDAO is null
            System.err.println("personneDAO is null");
        }
    }

   

    @Override
    public void initialize(URL location, ResourceBundle
    resources) {
     // TODO (don't really need to do anything here).
    
     }

        @FXML
private void handleBAuulerButton(ActionEvent event) {
    closeWindow();
}

private void closeWindow() {
    Stage stage = (Stage) BAnnuler.getScene().getWindow();
    stage.close();
}
    

} 
