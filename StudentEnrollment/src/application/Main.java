package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main extends Application {

    private StudentEntity studentEntity;
    private ProfessorEntity professorEntity;
    private ObservableList<Person> combinedList;
    private TableView<Person> combinedTableView;

    @Override
    public void start(Stage primaryStage) {
    	
        // Database Connection Java + postgresql
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/StudentEnrollmentDB", "postgres", "admin");
            studentEntity = new StudentEntity(connection);
            professorEntity = new ProfessorEntity(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }

        combinedList = FXCollections.observableArrayList();

        // Create TabPane
        TabPane tabPane = new TabPane();

        //Here is the code for the first entity.
        // CREATE UI COMPONENTS FOR STUDENTS
        GridPane studentGrid = new GridPane();
        studentGrid.setPadding(new Insets(10, 10, 10, 10));
        studentGrid.setVgap(5);
        studentGrid.setHgap(10);

        Label studentHeaderLabel = new Label("Add Student Data");
        studentHeaderLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        GridPane.setConstraints(studentHeaderLabel, 0, 0, 2, 1);

        // Student ID
        Label studentIdLabel = new Label("*Student ID:");
        GridPane.setConstraints(studentIdLabel, 0, 1);
        TextField studentIdInput = new TextField();
        GridPane.setConstraints(studentIdInput, 1, 1);

        // First Name
        Label firstNameLabel = new Label("*First Name:");
        GridPane.setConstraints(firstNameLabel, 0, 2);
        TextField firstNameInput = new TextField();
        GridPane.setConstraints(firstNameInput, 1, 2);

        // Last Name
        Label lastNameLabel = new Label("*Last Name:");
        GridPane.setConstraints(lastNameLabel, 0, 3);
        TextField lastNameInput = new TextField();
        GridPane.setConstraints(lastNameInput, 1, 3);

        // Date of Birth
        Label dobLabel = new Label("*Date of Birth (yyyy-mm-dd):");
        GridPane.setConstraints(dobLabel, 0, 4);
        TextField dobInput = new TextField();
        GridPane.setConstraints(dobInput, 1, 4);

        // Level
        Label levelLabel = new Label("*Level:");
        GridPane.setConstraints(levelLabel, 0, 5);
        ComboBox<String> levelComboBox = new ComboBox<>();
        levelComboBox.setItems(FXCollections.observableArrayList("BSc", "MSc", "PhD"));
        GridPane.setConstraints(levelComboBox, 1, 5);

        // Email
        Label emailLabel = new Label("Email:");
        GridPane.setConstraints(emailLabel, 0, 6);
        TextField emailInput = new TextField();
        GridPane.setConstraints(emailInput, 1, 6);

        // Work
        Label workLabel = new Label("Work if employed:");
        GridPane.setConstraints(workLabel, 0, 7);
        TextField workInput = new TextField();
        GridPane.setConstraints(workInput, 1, 7);

        // Add components to student grid
        studentGrid.getChildren().addAll(studentHeaderLabel, studentIdLabel, studentIdInput, firstNameLabel, firstNameInput, lastNameLabel, lastNameInput, dobLabel, dobInput, levelLabel, levelComboBox, emailLabel, emailInput, workLabel, workInput);

        //Here is the code for the second entity
        // CREATE UI COMPONENTS FOR PROFESSORS
        GridPane professorGrid = new GridPane();
        professorGrid.setPadding(new Insets(10, 10, 10, 10));
        professorGrid.setVgap(5);
        professorGrid.setHgap(10);

        Label professorHeaderLabel = new Label("Add Professor Data");
        professorHeaderLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        GridPane.setConstraints(professorHeaderLabel, 0, 0, 2, 1);

        // Professor ID
        Label professorIdLabel = new Label("*Professor ID:");
        GridPane.setConstraints(professorIdLabel, 0, 1);
        TextField professorIdInput = new TextField();
        GridPane.setConstraints(professorIdInput, 1, 1);

        // First Name
        Label professorFirstNameLabel = new Label("*First Name:");
        GridPane.setConstraints(professorFirstNameLabel, 0, 2);
        TextField professorFirstNameInput = new TextField();
        GridPane.setConstraints(professorFirstNameInput, 1, 2);

        // Last Name
        Label professorLastNameLabel = new Label("*Last Name:");
        GridPane.setConstraints(professorLastNameLabel, 0, 3);
        TextField professorLastNameInput = new TextField();
        GridPane.setConstraints(professorLastNameInput, 1, 3);

        // Email
        Label professorEmailLabel = new Label("Email:");
        GridPane.setConstraints(professorEmailLabel, 0, 4);
        TextField professorEmailInput = new TextField();
        GridPane.setConstraints(professorEmailInput, 1, 4);

        // Subject
        Label subjectLabel = new Label("*Subject:");
        GridPane.setConstraints(subjectLabel, 0, 5);
        TextField subjectInput = new TextField();
        GridPane.setConstraints(subjectInput, 1, 5);

        // Phone Numbers
        Label phoneNumbersLabel = new Label("Phone Numbers:");
        GridPane.setConstraints(phoneNumbersLabel, 0, 6);
        TextField phoneNumbersInput = new TextField();
        GridPane.setConstraints(phoneNumbersInput, 1, 6);

        // Add components to professor grid
        professorGrid.getChildren().addAll(professorHeaderLabel, professorIdLabel, professorIdInput, professorFirstNameLabel, professorFirstNameInput, professorLastNameLabel, professorLastNameInput, professorEmailLabel, professorEmailInput, subjectLabel, subjectInput, phoneNumbersLabel, phoneNumbersInput);

        // Create Tabs
        Tab studentTab = new Tab("Student", studentGrid);
        studentTab.setClosable(false); // Remove the close button

        Tab professorTab = new Tab("Professor", professorGrid);
        professorTab.setClosable(false); // Remove the close button

        tabPane.getTabs().addAll(studentTab, professorTab);

        // Single Insert Button
        Button insertButton = new Button("Insert");
        insertButton.setOnAction(e -> {
            // Validate student fields
            String studentIdText = studentIdInput.getText();
            int studentId = studentIdText.isEmpty() ? 0 : Integer.parseInt(studentIdText);
            String firstName = firstNameInput.getText();
            String lastName = lastNameInput.getText();
            String dateOfBirth = dobInput.getText();
            String level = levelComboBox.getValue();
            String email = emailInput.getText();
            String work = workInput.getText();

            // Validate professor fields
            String professorIdText = professorIdInput.getText();
            int professorId = professorIdText.isEmpty() ? 0 : Integer.parseInt(professorIdText);
            String professorFirstName = professorFirstNameInput.getText();
            String professorLastName = professorLastNameInput.getText();
            String professorEmail = professorEmailInput.getText();
            String subject = subjectInput.getText();
            String phoneNumbers = phoneNumbersInput.getText();

            if (firstName.trim().isEmpty() || lastName.trim().isEmpty() || dateOfBirth.trim().isEmpty() || level == null || studentId == 0) {
                showAlert(Alert.AlertType.ERROR, "Error", "Complete required student fields.");
                return;
            }

            if (professorFirstName.trim().isEmpty() || professorLastName.trim().isEmpty() || subject.trim().isEmpty() || professorId == 0) {
                showAlert(Alert.AlertType.ERROR, "Error", "Complete required professor fields.");
                return;
            }

            try {
                studentEntity.addStudent(studentId, firstName, lastName, dateOfBirth, level, email, work);
                professorEntity.addProfessor(professorId, professorFirstName, professorLastName, professorEmail, subject, phoneNumbers);

                Person person = new Person(studentId, professorId, level, firstName + " " + lastName, subject, professorFirstName + " " + professorLastName);
                combinedList.add(person);
                System.out.println("Student and Professor added successfully");

                // Clear input fields
                studentIdInput.clear();
                firstNameInput.clear();
                lastNameInput.clear();
                dobInput.clear();
                levelComboBox.setValue(null);
                emailInput.clear();
                workInput.clear();

                professorIdInput.clear();
                professorFirstNameInput.clear();
                professorLastNameInput.clear();
                professorEmailInput.clear();
                subjectInput.clear();
                phoneNumbersInput.clear();

            } catch (SQLException ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Database Error", "An error occurred while adding the student and professor.");
            }
        });

        // Delete Button
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            Person selectedPerson = combinedTableView.getSelectionModel().getSelectedItem();
            if (selectedPerson != null) {
                try {
                    int studentId = selectedPerson.getStudentId();
                    studentEntity.deleteStudent(studentId);

                    int professorId = selectedPerson.getProfessorId();
                    professorEntity.deleteProfessor(professorId);

                    combinedList.remove(selectedPerson);
                    System.out.println("Student and Professor deleted successfully");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    showAlert(Alert.AlertType.ERROR, "Database Error", "An error occurred while deleting the student and professor.");
                }
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "No student selected.");
            }
        });

        // Load existing data from the database
        try {
            List<StudentEntity.Student> students = studentEntity.getAllStudents();
            List<ProfessorEntity.Professor> professors = professorEntity.getAllProfessors();

            for (StudentEntity.Student student : students) {
                combinedList.add(new Person(student.getStudentId(), 0, student.getLevel(), student.getFirstName() + " " + student.getLastName(), "", ""));
            }

            for (ProfessorEntity.Professor professor : professors) {
                for (Person person : combinedList) {
                    if (person.getProfessor().isEmpty()) {
                        person.setSubject(professor.getSubject());
                        person.setProfessor(professor.getFirstName() + " " + professor.getLastName());
                        person.setProfessorId(professor.getProfessorId()); 
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Table View for combined list of Students and Professors
        combinedTableView = new TableView<>(combinedList); 

        TableColumn<Person, Number> studentIdColumn = new TableColumn<>("Student ID");
        studentIdColumn.setCellValueFactory(cellData -> cellData.getValue().studentIdProperty());

        TableColumn<Person, Number> professorIdColumn = new TableColumn<>("Professor ID");
        professorIdColumn.setCellValueFactory(cellData -> cellData.getValue().professorIdProperty());

        TableColumn<Person, String> levelColumn = new TableColumn<>("Level");
        levelColumn.setCellValueFactory(cellData -> cellData.getValue().levelProperty());

        TableColumn<Person, String> nameColumn = new TableColumn<>("Student");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        TableColumn<Person, String> subjectColumn = new TableColumn<>("Subject");
        subjectColumn.setCellValueFactory(cellData -> cellData.getValue().subjectProperty());

        TableColumn<Person, String> professorColumn = new TableColumn<>("Professor");
        professorColumn.setCellValueFactory(cellData -> cellData.getValue().professorProperty());

        combinedTableView.getColumns().addAll(studentIdColumn, professorIdColumn, levelColumn, nameColumn, subjectColumn, professorColumn);

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(tabPane, insertButton, deleteButton, combinedTableView);
        vbox.setPadding(new Insets(10, 10, 10, 10));

        // Setup scene and stage
        Scene scene = new Scene(vbox, 1500, 775);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Student Enrollment System");
        primaryStage.show();
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Inner class to represent a Person
    public static class Person {
        private final IntegerProperty studentId;
        private final IntegerProperty professorId; 
        private final StringProperty level;
        private final StringProperty name;
        private final StringProperty subject;
        private final StringProperty professor;

        public Person(int studentId, int professorId, String level, String name, String subject, String professor) {
            this.studentId = new SimpleIntegerProperty(studentId);
            this.professorId = new SimpleIntegerProperty(professorId);
            this.level = new SimpleStringProperty(level);
            this.name = new SimpleStringProperty(name);
            this.subject = new SimpleStringProperty(subject);
            this.professor = new SimpleStringProperty(professor);
        }

        public int getStudentId() {
            return studentId.get();
        }

        public IntegerProperty studentIdProperty() {
            return studentId;
        }

        public int getProfessorId() {
            return professorId.get();
        }

        public IntegerProperty professorIdProperty() {
            return professorId;
        }

        public void setProfessorId(int professorId) {
            this.professorId.set(professorId);
        }

        public String getLevel() {
            return level.get();
        }

        public void setLevel(String level) {
            this.level.set(level);
        }

        public StringProperty levelProperty() {
            return level;
        }

        public String getName() {
            return name.get();
        }

        public void setName(String name) {
            this.name.set(name);
        }

        public StringProperty nameProperty() {
            return name;
        }

        public String getSubject() {
            return subject.get();
        }

        public void setSubject(String subject) {
            this.subject.set(subject);
        }

        public StringProperty subjectProperty() {
            return subject;
        }

        public String getProfessor() {
            return professor.get();
        }

        public void setProfessor(String professor) {
            this.professor.set(professor);
        }

        public StringProperty professorProperty() {
            return professor;
        }
    }
}
