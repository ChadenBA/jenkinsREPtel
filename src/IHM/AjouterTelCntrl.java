package IHM;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import metier.Personne;
import metier.Telephone;
import persistance.TelDAO;

import java.sql.SQLException;

public class AjouterTelCntrl {

    @FXML
    private Button Bajouter;

    @FXML
    private Button Bannuler;

    @FXML
    private CheckBox CB;

    @FXML
    private CheckBox CD;

    @FXML
    private CheckBox CINTER;

    @FXML
    private CheckBox CT;

    @FXML
    private TextField TCIN;

    @FXML
    private TextField TValuerr;

    private TelDAO telephoneDAO;

    public void setTelephoneDAO(TelDAO telephoneDAO) {
        this.telephoneDAO = telephoneDAO;
    }

    @FXML
    void addtel(ActionEvent event) {
        try {
            String cinText = TCIN.getText().trim();
            int cin = Integer.parseInt(cinText);

            String valeurText = TValuerr.getText().trim();
            int valeur = Integer.parseInt(valeurText);

            String type = CT.getText();
            Telephone telephone = new Telephone();
            telephone.setValeur(valeur);
            telephone.setType(type);
            Personne personne = new Personne();
            personne.setCin(cin);

            telephoneDAO.addtel(telephone, personne);
             Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("telephone aded successfully.");
        alert.showAndWait();

        } catch (NumberFormatException e) {
           Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("failed");
        alert.setContentText("telephone aded  successfully.");
        alert.showAndWait();

       
        } catch (Exception e) {
          
         Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("failed");
        alert.setContentText("personne n'existe pas.");
        alert.showAndWait();

        }
    }

    @FXML
    private void handleBAuulerButton(ActionEvent event) {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) Bannuler.getScene().getWindow();
        stage.close();
    }

    private void showSuccessAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
