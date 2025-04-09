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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Ajout.fxml"));

        PersonneDAO personneDAO = new PersonneDAOImpl();

        AjoutController ajoutController = new AjoutController(personneDAO);

        loader.setController(ajoutController);

        Parent root = loader.load();

        Scene scene = new Scene(root, 500, 200);

        primaryStage.setScene(scene);

        primaryStage.show();
    }
}



     
