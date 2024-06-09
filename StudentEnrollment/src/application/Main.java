package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main extends Application {

    private StudentEntity studentEntity;

    @Override
    public void start(Stage primaryStage) {
        // Setup database connection
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/StudentEnrollmentDB", "postgres", "admin");
            studentEntity = new StudentEntity(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create UI components
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8); 	
        grid.setHgap(10);

        // First Name
        Label firstNameLabel = new Label("First Name:");
        GridPane.setConstraints(firstNameLabel, 0, 0);
        TextField firstNameInput = new TextField();
        GridPane.setConstraints(firstNameInput, 1, 0);

        // Last Name
        Label lastNameLabel = new Label("Last Name:");
        GridPane.setConstraints(lastNameLabel, 0, 1);
        TextField lastNameInput = new TextField();
        GridPane.setConstraints(lastNameInput, 1, 1);

        // Date of Birth
        Label dobLabel = new Label("Date of Birth (yyyy-mm-dd):");
        GridPane.setConstraints(dobLabel, 0, 2);
        TextField dobInput = new TextField();
        GridPane.setConstraints(dobInput, 1, 2);

        // Email
        Label emailLabel = new Label("Email:");
        GridPane.setConstraints(emailLabel, 0, 3);
        TextField emailInput = new TextField();
        GridPane.setConstraints(emailInput, 1, 3);

        // Submit Button
        Button submitButton = new Button("Add Student");
        GridPane.setConstraints(submitButton, 1, 4);
        submitButton.setOnAction(e -> {
            String firstName = firstNameInput.getText();
            String lastName = lastNameInput.getText();
            String dateOfBirth = dobInput.getText();
            String email = emailInput.getText();
            try {
                studentEntity.addStudent(firstName, lastName, dateOfBirth, email);
                System.out.println("Student added successfully");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        // Add components to grid
        grid.getChildren().addAll(firstNameLabel, firstNameInput, lastNameLabel, lastNameInput, dobLabel, dobInput, emailLabel, emailInput, submitButton);

        // Setup scene and stage
        Scene scene = new Scene(grid, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Student Enrollment System");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
