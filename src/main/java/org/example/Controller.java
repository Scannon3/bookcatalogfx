package org.example;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;
import java.awt.event.ActionEvent;

public class Controller {
    private Stage stage;
    private Scene scene;
    private FXMLLoader loader;

    public void switchScene1(ActionEvent event) throws IOException {
         loader = new FXMLLoader(getClass().getResource("/org/example/scene1.fxml"));
         stage = (Stage)((Node)event.getSource()).getScene().getWindow();
         scene = new Scene(loader.load());
         stage.setScene(scene);
         stage.show();

    }
    public void switchScene2(ActionEvent event) throws IOException {
        loader = new FXMLLoader(getClass().getResource("/org/example/scene2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }
}

