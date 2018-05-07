package sk.sim;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Pane pane = FXMLLoader.load(Main.class.getResource("/fxml/MainWindow.fxml"));

        Scene scene = new Scene(pane);

        primaryStage.setTitle("Symulacja restauracji");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
