package IHM;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import metier.Telephone;
import persistance.TelDAO;

public class modifierTelCntrl {

    @FXML
    private Button BAuuler;

    @FXML
    private Button BModifier;

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

    private TelDAO telDAO;


    public void setTelDAO(TelDAO telDAO) {
        this.telDAO = telDAO;
    }
    public void setTlphone(Telephone telephone) {
        System.out.println("Setting values: " + telephone.getValeur() + " " + telephone.getCin() + " " + telephone.getType());
        if (telephone != null) {
            TCIN.setText(String.valueOf(telephone.getCin()));
            TValuerr.setText(String.valueOf(telephone.getValeur()));
    
            String type = telephone.getType();
            if ("T".equals(type)) {
                CT.setSelected(true);
            } else if ("B".equals(type)) {
                CB.setSelected(true);
            } else if ("D".equals(type)) {
                CD.setSelected(true);
            } else {
                CINTER.setSelected(true);
            }
        }
    }
    


@FXML
private void handleBAuulerButton(ActionEvent event) {
    closeWindow();
}

private void closeWindow() {
    Stage stage = (Stage) BAuuler.getScene().getWindow();
    stage.close();
}

    @FXML
    private void handleBModifierButton(ActionEvent event) {
        try {
            // Retrieve data from UI components
            int cin = Integer.parseInt(TCIN.getText());
          int valeur = Integer.parseInt(TValuerr.getText());
            String type = CB.isSelected() ? "B" : (CD.isSelected() ? "D" : "T");
    
           
    
            // Create a Personne object with the retrieved data
            Telephone telupdated = new Telephone();
            telupdated.setCin(cin);
            telupdated.setValeur(valeur);
            telupdated.setType(type);
           
            // Call the update method in the PersonneDOA
            telDAO.modifiertel(telupdated);
     Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("Telphone upfated successfully.");
        alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
            // Log or handle the exception appropriately
            System.err.println("Failed to update Telphone: " + e.getMessage());
        }
    }
}
