package IHM;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import metier.Personne;
import persistance.*;

public class UpdatePERcntrl {

    @FXML
    private Button BAnuuler;

    @FXML
    private Button BModifer;

    @FXML
    private RadioButton RMELLE;

    @FXML
    private RadioButton RMME;

    @FXML
    private RadioButton RMR;

    @FXML
    private TextField TCIN;

    @FXML
    private TextField TNOM;

    @FXML
    private TextField TPRENOM;
private PersonneDAO personneDOA;

public void setPersonneDOA(PersonneDAO personneDOA) {
    this.personneDOA = personneDOA;
}
    public void setPersonne(Personne personne) {
       

        // Set the existing person details to the UI components
        if (personne != null) {
            TCIN.setText(String.valueOf(personne.getCin()));
            TNOM.setText(personne.getNom());
            TPRENOM.setText(personne.getPrenom());

            // Set the civilite based on the person's gender
            String civilite = personne.getCivilite();
            if ("Mademoiselle".equals(civilite)) {
                RMELLE.setSelected(true);
            } else if ("Madame".equals(civilite)) {
                RMME.setSelected(true);
            } else {
                RMR.setSelected(true);
            }
        }
    }
    // Event handler for the "Modifier" button
    @FXML
    void handleModifierButtonClick(ActionEvent event) {
        try {
            // Retrieve data from UI components
            int cin = Integer.parseInt(TCIN.getText());
            String nom = TNOM.getText();
            String prenom = TPRENOM.getText();
            String civilite = RMELLE.isSelected() ? "Mademoiselle" : (RMME.isSelected() ? "Madame" : "Monsieur");
    
            // Validate user input
            if (nom.isEmpty() || prenom.isEmpty()) {
                // Show an alert or print a message indicating that name fields are required
                System.out.println("Name fields are required for updating Personne.");
                return;
            }
    
            // Create a Personne object with the retrieved data
            Personne personneToUpdate = new Personne();
            personneToUpdate.setCin(cin);
            personneToUpdate.setNom(nom);
            personneToUpdate.setPrenom(prenom);
            personneToUpdate.setCivilite(civilite);
    
            // Call the update method in the PersonneDOA
            personneDOA.modifier(personneToUpdate);
              Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("Personne upfated successfully.");
        alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
           Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Failed");
        alert.setContentText("failed.");
        alert.showAndWait();
        }
    }
    @FXML
private void handleBAuulerButton(ActionEvent event) {
    closeWindow();
}

private void closeWindow() {
    Stage stage = (Stage) BAnuuler.getScene().getWindow();
    stage.close();
}
}
