import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PraticalTest02 extends Application {

    @Override
    public void start(Stage primaryStage) {
        TextField textfield = new TextField();
        Label label = new Label();
        Button btn = new Button();
        btn.setText("Display Text");
        btn.setOnAction((ActionEvent event) -> {
            String text = textfield.getText();
            label.setText("Hello " + text + "!");
        });

        VBox root = new VBox(10);
        root.getChildren().addAll(textfield, btn, label);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Practical Test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
