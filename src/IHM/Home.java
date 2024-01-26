package IHM;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import metier.Personne;
import metier.Telephone;
import persistance.PersonneDAO;
import persistance.PersonneDAOImpl;
import persistance.TelDAO;
import persistance.TelDAOImpl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Home extends Application {
    private Parent root;
    // Initialize your DAO
    private final PersonneDAO personneDAO = new PersonneDAOImpl(); 
    private final TelDAO  telDAO=new TelDAOImpl();
    private final ListView<Personne> personneListView = new ListView<>();
    private final Label detailLabel = new Label();
    private final TextField searchField = new TextField();
  
    public void setRoot(Parent root) {
        this.root = root;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Repertoire telephonique");

        TitledPane titledPane = new TitledPane();
        titledPane.setAnimated(false);
        titledPane.setText("Repertoire telephonique");

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setMinHeight(180);
        anchorPane.setMinWidth(200);

        // Initialize ListView with data
        displayPersons();

        titledPane.setContent(personneListView);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(detailLabel);

        Button ajouterPersonneButton = new Button("Ajouter Personne");
        Button ajouterTelephoneButton = new Button("Ajouter Téléphone");

        ajouterPersonneButton.setOnAction(e -> openAddPersonneWindow());
        ajouterTelephoneButton.setOnAction(e -> openAddTelephoneWindow());

        // Search bar
        searchField.setPromptText("Search...");
        searchField.setOnKeyReleased(e -> searchPersons());
        ImageView searchIcon = new ImageView(new Image("file:/home/chaden/Bureau/TpJava/TP6/IHM/icons8-search-30.png"));
        searchIcon.setFitHeight(20);
        searchIcon.setFitWidth(20);

        // Create an HBox to hold the search icon and the search field
        HBox hbox = new HBox(20); // Adjust the spacing as needed
        hbox.getChildren().addAll(searchIcon, searchField);
        // Button HBox
        HBox buttonBox = new HBox(20);
        buttonBox.getChildren().addAll(ajouterPersonneButton, ajouterTelephoneButton);
        // VBox with search bar, buttons, and titled pane
        VBox vBox = new VBox(20, buttonBox,hbox , titledPane);
        Scene scene = new Scene(new VBox(vBox, borderPane), 800, 600);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Search
    private void searchPersons() {
        ObservableList<Personne> allPersons = personneListView.getItems();

        // Filter persons based on the search text
        List<Personne> filteredPersons = allPersons.stream()
                .filter(personne -> personne.getNom().toLowerCase().contains(searchField.getText().toLowerCase()))
                .collect(Collectors.toList());

        // Display the filtered persons in the ListView
        personneListView.setItems(FXCollections.observableArrayList(filteredPersons));

        // If there's only one person in the filtered list, show their details
        if (filteredPersons.size() == 1) {
            showPersonDetails(filteredPersons.get(0));
        } else {
            // Clear the detailLabel if no or multiple persons are found
            detailLabel.setText("");
        }
    }
    //LISTE PERSONNE 
    private void displayPersons() {
        List<Personne> personnes = personneDAO.getAllPersonne();
        ObservableList<Personne> observableList = FXCollections.observableArrayList(personnes);
        personneListView.setItems(observableList);

        personneListView.setCellFactory(param -> new ListCell<Personne>() {
            @Override
            protected void updateItem(Personne personne, boolean empty) {
                super.updateItem(personne, empty);

                if (empty || personne == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    HBox hbox = new HBox(10);
                    ImageView avatar = new ImageView(
                            new Image("file:/home/chaden/Bureau/TpJava/TP6/IHM/icons8-user-30.png")); 
                    avatar.setFitHeight(40);
                    avatar.setFitWidth(40);
                    avatar.setOnMouseClicked(event -> {
                        event.consume(); // Consume the event to prevent item selection
                        showPersonDetails(personne);
                    });
                    Label nameLabel = new Label(personne.getNom() + " " + personne.getPrenom());
                    ImageView deleteIcon = new ImageView(
                            new Image("file:/home/chaden/Bureau/TpJava/TP6/IHM/icons8-delete-30.png"));
                    deleteIcon.setFitHeight(20);
                    deleteIcon.setFitWidth(20);
                    deleteIcon.setOnMouseClicked(event -> handleDeletePersonne(personne));

                    ImageView updateIcon = new ImageView(
                            new Image("file:/home/chaden/Bureau/TpJava/TP6/IHM/icons8-edit-30.png")); 
                    updateIcon.setFitHeight(20);
                    updateIcon.setFitWidth(20);
                    updateIcon.setOnMouseClicked(event -> openUpdatePersonneWindow(personne));

                    hbox.getChildren().addAll(avatar, nameLabel, deleteIcon, updateIcon);
                    setGraphic(hbox);
                }
            }
        });
    }
//supprimer Pesonne
    private void handleDeletePersonne(Personne personne) {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText("Supprimer Personne");
        confirmationAlert.setContentText(
                "Vous etes sur de supprimer " + personne.getNom() + " " + personne.getPrenom() + "?");

        ButtonType yesButton = new ButtonType("OUI", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("Non", ButtonBar.ButtonData.NO);

        confirmationAlert.getButtonTypes().setAll(yesButton, noButton);
    
        confirmationAlert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == yesButton) {
                personneDAO.supprime(personne);
                displayPersons();
                  Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("personne deleted successfully.");
        alert.showAndWait();
            }
        });
    }
    //show details de personne 

    private void showPersonDetails(Personne personne) {

        Telephone tel = new Telephone();
        if (personne != null) {
            System.out.println("CIN: " + personne.getCin());
            List<Telephone> telephones = telDAO.getPhonesByCin(personne.getCin());
            StringBuilder details = new StringBuilder(
                    "L'information " + personne.getNom() + " " + personne.getPrenom() + ":\n");
            details.append("CIN: ").append(personne.getCin()).append("\n");
            details.append("Civilité: ").append(personne.getCivilite()).append("\n");

            // Create an HBox for person info
            HBox personInfoBox = new HBox(10);

            if (telephones != null && !telephones.isEmpty()) {
    details.append("Telephones:\n");

    ListView<Telephone> phoneListView = new ListView<>();
    phoneListView.getItems().addAll(telephones);

    phoneListView.setCellFactory(param -> new ListCell<>() {
        @Override
        protected void updateItem(Telephone telephone, boolean empty) {
            super.updateItem(telephone, empty);

            if (empty || telephone == null) {
                setText(null);
                setGraphic(null);
            } else {
                HBox hbox = new HBox(10);

                // Update Icon
                ImageView updateIcon = new ImageView(
                        new Image("file:/home/chaden/Bureau/TpJava/TP6/IHM/icons8-edit-30.png")); 
                updateIcon.setFitHeight(20);
                updateIcon.setFitWidth(20);
                updateIcon.setOnMouseClicked(event -> openUpdateTelephoneWindow(telephone));

                // Delete Icon
                ImageView deleteIcon = new ImageView(
                        new Image("file:/home/chaden/Bureau/TpJava/TP6/IHM/icons8-delete-30.png")); 
                deleteIcon.setFitHeight(20);
                deleteIcon.setFitWidth(20);
                deleteIcon.setOnMouseClicked(event -> handleDeleteTelphone(telephone));

                hbox.getChildren().addAll(new Label(" Num de telphone: " + telephone.getValeur()), updateIcon, deleteIcon);
                setGraphic(hbox);
            }
        }
    });

                // Add phoneListView to details
                personInfoBox.getChildren().addAll(phoneListView);
            } else {
                details.append("Cette personne ne possése pas un num de telephone.\n");
            }
            // Update the UI with the details
            detailLabel.setText(details.toString());
            detailLabel.setGraphic(personInfoBox);
        } else {
            detailLabel.setText("");
        }
    }
//Creation de personne 
    private void openAddPersonneWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/IHM/Ajout.fxml"));

            // Set the controller with personneDAO
            AjoutController ajoutController = new AjoutController(personneDAO);
            loader.setController(ajoutController);

            // Load the FXML file
            Parent root = loader.load();

            Stage ajoutStage = new Stage();
            ajoutStage.setTitle("Add Personne");
            ajoutStage.initModality(Modality.APPLICATION_MODAL); 
            ajoutStage.initStyle(StageStyle.UTILITY); 
            Scene ajoutScene = new Scene(root);
            ajoutStage.setScene(ajoutScene);
            ajoutStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//modifiction de personne 
    
    private void openUpdatePersonneWindow(Personne personne) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/IHM/Modifier.fxml"));
            Parent root = loader.load();
            UpdatePERcntrl updateController = loader.getController();

            if (updateController == null) {
                throw new NullPointerException("Controller is null. Check FXML file and controller class.");
            }
            updateController.setPersonne(personne);
            updateController.setPersonneDOA(personneDAO);
            Stage updateStage = new Stage();
            updateStage.setTitle("Modifier Personne");
            Scene updateScene = new Scene(root);
            updateScene.setRoot(root);
            updateStage.setScene(updateScene);
            updateStage.show();
              displayPersons();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }



    private void openAddTelephoneWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/IHM/AjouterTel.fxml"));

            // Load the FXML file and get the root
            Parent root = loader.load();
        
            AjouterTelCntrl ajoutTelController = loader.getController();
            ajoutTelController.setTelephoneDAO(telDAO); 

            Stage ajoutTelStage = new Stage();
            ajoutTelStage.setTitle("Add Telephone");
            ajoutTelStage.initModality(Modality.APPLICATION_MODAL); 
            ajoutTelStage.initStyle(StageStyle.UTILITY); 
            Scene ajoutTelScene = new Scene(root);
            ajoutTelStage.setScene(ajoutTelScene);

            // Show the add telephone window
            ajoutTelStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openUpdateTelephoneWindow(Telephone telephone) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/IHM/ModifierTel.fxml"));
            Parent root = loader.load();
            modifierTelCntrl updateTelephoneController = loader.getController();
           updateTelephoneController.setTlphone(telephone);
            updateTelephoneController.setTelDAO(telDAO); 
            Stage updateTelephoneStage = new Stage();
            updateTelephoneStage.setTitle("Modifier Telephone");
            Scene updateTelephoneScene = new Scene(root);
            updateTelephoneStage.setScene(updateTelephoneScene);
            updateTelephoneStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleDeleteTelphone(Telephone telephone) {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText("Supprimer Personne");
        confirmationAlert.setContentText(
                "Vous etes sur de supprimer " + telephone.getValeur()+ "?");

        ButtonType yesButton = new ButtonType("OUI", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("Non", ButtonBar.ButtonData.NO);

        confirmationAlert.getButtonTypes().setAll(yesButton, noButton);

        confirmationAlert.showAndWait().ifPresent(buttonType -> {
           
            if (buttonType == yesButton) {
                telDAO.supprimetel(telephone);
            }
        });
    }

}