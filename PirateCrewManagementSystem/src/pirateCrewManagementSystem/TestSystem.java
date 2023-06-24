package pirateCrewManagementSystem;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.stage.Modality;

public class TestSystem extends Application {

    private TextField nameField, ageField, roleField, bountyField, fruitNameField, fruitTypeField;
    private VBox root2;
    private String name, role, bounty, fruitName, fruitType;
    private int age;
    private Bounty newBounty;
    private DevilFruit newFruit;
    private final CrewMember crewMember = new CrewMember();
    private Crew newCrewMember;

    @Override
    public void start(Stage primaryStage) {
        nameField = new TextField();
        ageField = new TextField();
        roleField = new TextField();
        bountyField = new TextField();
        fruitNameField = new TextField();
        fruitTypeField = new TextField();
        Stage introStage = new Stage();
        Label introLabel = new Label("Welcome to the Pirate Crew Management System!");
        introLabel.setStyle("-fx-font-weight: bold; -fx-font-style: italic;");
        Button nextButton = new Button("Next");
        introStage.initModality(Modality.APPLICATION_MODAL);
        introStage.setTitle("Introduction");
        Image introImage = new Image(getClass().getResourceAsStream("Image/introstage.png"));
        ImageView imageView = new ImageView(introImage);
        Image gifImage = new Image(getClass().getResourceAsStream("Image/strawhat02.gif"));
        ImageView gifImageView = new ImageView(gifImage);

        gifImageView.setFitWidth(600);
        gifImageView.setFitHeight(300);

        Group gifPane = new Group(gifImageView);

        nextButton.setOnAction(event -> {
            primaryStage.show(); // Show the main stage
            introStage.close(); // Close the intro stage
            // Play audio after the intro stage is closed
            AudioClip audioClip = new AudioClip(getClass().getResource("Audio/Onepiece.mp3").toString());
            //audioClip.play();
        });

        VBox introLayout = new VBox(imageView, introLabel, nextButton);
        introLayout.setStyle("-fx-background-color: lightblue;");
        introLayout.setAlignment(Pos.CENTER);
        introLayout.setSpacing(15);
        introLayout.setPadding(new Insets(50));

        Scene introScene = new Scene(introLayout, 500, 500);
        introStage.setScene(introScene);

        Button addButton = new Button("Add Crew Member");
        Button viewButton = new Button("View Crew Member");
        VBox root = new VBox(addButton, viewButton);
        root2 = new VBox(viewButton);

        addButton.setOnAction((ActionEvent e) -> {
            // Perform add operation
            name = nameField.getText().toLowerCase();
            try {
                age = Integer.parseInt(ageField.getText()); // Parse age as integer
            } catch (NumberFormatException ex) {
                System.out.println("Invalid age input. Please enter a valid integer.");
                return; // Stop processing and prompt user again
            }
            role = roleField.getText();
            bounty = bountyField.getText();
            fruitName = fruitNameField.getText();
            fruitType = fruitTypeField.getText();

            newBounty = new Bounty(bounty);
            newFruit = new DevilFruit(fruitName, fruitType);
            newCrewMember = new Crew(name, age, role, newBounty, newFruit);

            try {
                validateCrewMemberName(name);
                crewMember.addCrewMember(newCrewMember);
                nameField.clear();
                ageField.clear();
                roleField.clear();
                bountyField.clear();
                fruitNameField.clear();
                fruitTypeField.clear();
                viewButton.setOnAction(event -> displayImage());
            } catch (InvalidCrewMemberException ex) {
                System.out.println(ex.getMessage());
            }
        });

        VBox formLayout = new VBox(
                new Label("Name:"), nameField,
                new Label("Age:"), ageField,
                new Label("Role:"), roleField,
                new Label("Bounty:"), bountyField,
                new Label("Devil Fruit Type:"), fruitTypeField,
                new Label("Devil Fruit name:"), fruitNameField
        );

        VBox rootLayout = new VBox(formLayout, root, root2, gifPane);
        rootLayout.setStyle("-fx-background-color: lightblue;");
        VBox.setMargin(gifPane, new Insets(-50, 100, 200, 200));

        Scene scene = new Scene(rootLayout, 850, 650);
        scene.setFill(Color.BLACK);
        primaryStage.setTitle("Strawhat Crew Management System");
        primaryStage.setScene(scene);
        introStage.show();
    }

    private void displayImage() {
        HBox layout = new HBox();

        for (Crew crew : crewMember.getAllCrewMembers()) {
            name = crew.getName();
            age = crew.getAge();
            role = crew.getRole();
            newBounty = crew.getBounty();
            newFruit = crew.getDevilFruit();

            Image image = null;
            AudioClip tone = null;

            if (name.contains("luffy")) {
                image = new Image(getClass().getResourceAsStream("Image/luffy.png"));
                tone = new AudioClip(getClass().getResource("Audio/luffyTheme.mp3").toString());
            } else if (name.contains("zoro")) {
                image = new Image(getClass().getResourceAsStream("Image/zoro.png"));
                tone = new AudioClip(getClass().getResource("Audio/zoroTheme.mp3").toString());
            } else if (name.contains("sanji")) {
                image = new Image(getClass().getResourceAsStream("Image/sanji.jpg"));
                tone = new AudioClip(getClass().getResource("Audio/sanjiTheme.mp3").toString());
            }

            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(200);
            imageView.setFitHeight(200);

            VBox crewLayout = new VBox(
                    new Label("Name: " + name),
                    new Label("Age: " + age),
                    new Label("Role: " + role),
                    new Label("Bounty: " + newBounty.getAmount()),
                    new Label("Devil Fruit Type: " + newFruit.getType()),
                    new Label("Devil Fruit Name: " + newFruit.getName())
            );
            VBox.setMargin(crewLayout, new Insets(20, 150, 0, 0));
            HBox detailsLayout = new HBox(crewLayout);
            VBox crewView = new VBox(imageView, detailsLayout);

            layout.getChildren().add(crewView);

            interaction(imageView, tone);
        }

        Scene imageScene = new Scene(layout);
        Stage imageStage = new Stage();
        imageStage.setTitle("Strawhat Pirates");
        imageStage.setScene(imageScene);
        imageStage.show();
    }

    private void interaction(ImageView imageView, AudioClip audioClip) {

        imageView.setOnMousePressed((MouseEvent me) -> {
            audioClip.play();
        });
        imageView.setOnMouseDragged((MouseEvent me) -> {
            System.out.println(me.getSceneX() + " " + me.getSceneY());
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void validateCrewMemberName(String name) throws InvalidCrewMemberException {
        if (!(name.contains("luffy") || name.contains("sanji") || name.contains("zoro"))) {
            throw new InvalidCrewMemberException("Invalid crew member name. Please enter a valid name (Luffy, Sanji, or Zoro).");
        }
    }
}

class InvalidCrewMemberException extends Exception {
    public InvalidCrewMemberException(String message) {
        super(message);
    }
}
