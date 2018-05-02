package sk.testing.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Runner extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception{
        Pane root = FXMLLoader.load(Runner.class.getResource("/fxml/sample.fxml"));
        primaryStage.setTitle("Deskit is the best!");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
