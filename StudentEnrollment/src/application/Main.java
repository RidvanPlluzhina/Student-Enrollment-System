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

    // Entity handlers - manage interactions with the database tables for each entity
    private StudentEntity studentEntity;
    private ProfessorEntity professorEntity;
    private ExamEntity examEntity;
    private SubjectEntity subjectEntity;
    private ClassroomEntity classroomEntity;

    // Relationship handlers - manage interactions with the database tables for each relationship
    private TakesRelationship takesRelationship;
    private HostsRelationship hostsRelationship;
    private TaughtByRelationship taughtByRelationship;
    private MentorsRelationship mentorsRelationship;

    // Observable list for storing combined data
    private ObservableList<Person> combinedList;
    private TableView<Person> combinedTableView;

    @Override
    public void start(Stage primaryStage) {
        // Database Connection Java + PostgreSQL
        Connection connection = null;
        try {
            // Load PostgreSQL driver
            Class.forName("org.postgresql.Driver");
            // Establish connection to the database
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/StudentEnrollmentDB", "postgres", "admin");
            // Initialize entity and relationship handlers
            studentEntity = new StudentEntity(connection);
            professorEntity = new ProfessorEntity(connection);
            examEntity = new ExamEntity(connection);
            subjectEntity = new SubjectEntity(connection);
            classroomEntity = new ClassroomEntity(connection);
            takesRelationship = new TakesRelationship(connection);
            hostsRelationship = new HostsRelationship(connection);
            taughtByRelationship = new TaughtByRelationship(connection);
            mentorsRelationship = new MentorsRelationship(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Initialize combined list for table view
        combinedList = FXCollections.observableArrayList();

        // Create TabPane for different entities
        TabPane tabPane = new TabPane();

        // GridPane layout for organizing components in a grid structure
        // CREATE UI COMPONENTS FOR SUBJECTS
        GridPane subjectGrid = new GridPane();
        subjectGrid.setPadding(new Insets(10, 10, 10, 10));
        subjectGrid.setVgap(5);
        subjectGrid.setHgap(10);

        // Header of subject input form
        Label subjectHeaderLabel = new Label("Add Subject Data");
        subjectHeaderLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        GridPane.setConstraints(subjectHeaderLabel, 0, 0, 2, 1);

        // Subject ID
        Label subjectIdLabel = new Label("*Subject ID:");
        GridPane.setConstraints(subjectIdLabel, 0, 1);
        TextField subjectIdInput = new TextField();
        GridPane.setConstraints(subjectIdInput, 1, 1);

        // Subject Name
        Label subjectNameLabel = new Label("*Subject Name:");
        GridPane.setConstraints(subjectNameLabel, 0, 2);
        TextField subjectNameInput = new TextField();
        GridPane.setConstraints(subjectNameInput, 1, 2);

        // Add components to subject grid
        subjectGrid.getChildren().addAll(subjectHeaderLabel, subjectIdLabel, subjectIdInput, subjectNameLabel, subjectNameInput);

        // CREATE UI COMPONENTS FOR Classroom
        GridPane classroomGrid = new GridPane();
        classroomGrid.setPadding(new Insets(10, 10, 10, 10));
        classroomGrid.setVgap(5);
        classroomGrid.setHgap(10);

        // Header of Classroom input form
        Label classroomHeaderLabel = new Label("Add Classroom Data");
        classroomHeaderLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        GridPane.setConstraints(classroomHeaderLabel, 0, 0, 2, 1);

        // RoomNumber
        Label roomNumberLabel = new Label("*RoomNr:");
        GridPane.setConstraints(roomNumberLabel, 0, 1);
        TextField roomNumberInput = new TextField();
        GridPane.setConstraints(roomNumberInput, 1, 1);

        // Building Name
        Label buildingLabel = new Label("*Building:");
        GridPane.setConstraints(buildingLabel, 0, 2);
        TextField buildingInput = new TextField();
        GridPane.setConstraints(buildingInput, 1, 2);

        // Capacity
        Label capacityLabel = new Label("*Capacity:");
        GridPane.setConstraints(capacityLabel, 0, 3);
        TextField capacityInput = new TextField();
        GridPane.setConstraints(capacityInput, 1, 3);

        // Add components to GridPane
        classroomGrid.getChildren().addAll(
            classroomHeaderLabel,
            roomNumberLabel, roomNumberInput,
            buildingLabel, buildingInput,
            capacityLabel, capacityInput
        );

        // CREATE UI COMPONENTS FOR STUDENTS
        GridPane studentGrid = new GridPane();
        studentGrid.setPadding(new Insets(10, 10, 10, 10));
        studentGrid.setVgap(5);
        studentGrid.setHgap(10);

        // Header of student input form
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

        // CREATE UI COMPONENTS FOR PROFESSORS
        GridPane professorGrid = new GridPane();
        professorGrid.setPadding(new Insets(10, 10, 10, 10));
        professorGrid.setVgap(5);
        professorGrid.setHgap(10);

        // Header of professor input form
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

        // Department
        Label professorDepartmentLabel = new Label("Department:");
        GridPane.setConstraints(professorDepartmentLabel, 0, 5);
        TextField departmentInput = new TextField();
        GridPane.setConstraints(departmentInput, 1, 5);

        // Affiliation
        Label professorAffiliationLabel = new Label("Affiliation:");
        GridPane.setConstraints(professorAffiliationLabel, 0, 6);
        TextField affiliationInput = new TextField();
        GridPane.setConstraints(affiliationInput, 1, 6);

        // Add components to professor grid
        professorGrid.getChildren().addAll(professorHeaderLabel, professorIdLabel, professorIdInput, professorFirstNameLabel, professorFirstNameInput, professorLastNameLabel, professorLastNameInput, professorEmailLabel, professorEmailInput, professorDepartmentLabel, departmentInput, professorAffiliationLabel, affiliationInput);

        // CREATE UI COMPONENTS FOR EXAMS
        GridPane examGrid = new GridPane();
        examGrid.setPadding(new Insets(10, 10, 10, 10));
        examGrid.setVgap(5);
        examGrid.setHgap(10);

        // Header of exam input form
        Label examHeaderLabel = new Label("Add Exam Data");
        examHeaderLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        GridPane.setConstraints(examHeaderLabel, 0, 0, 2, 1);

        // Exam ID
        Label examIdLabel = new Label("*Exam ID:");
        GridPane.setConstraints(examIdLabel, 0, 1);
        TextField examIdInput = new TextField();
        GridPane.setConstraints(examIdInput, 1, 1);

        // Exam Date
        Label examDateLabel = new Label("*Exam Date (yyyy-mm-dd):");
        GridPane.setConstraints(examDateLabel, 0, 2);
        TextField examDateInput = new TextField();
        GridPane.setConstraints(examDateInput, 1, 2);

        // Exam Start Time
        Label examStartTimeLabel = new Label("*Exam Start Time (HH:mm):");
        GridPane.setConstraints(examStartTimeLabel, 0, 3);
        TextField examStartTimeInput = new TextField();
        GridPane.setConstraints(examStartTimeInput, 1, 3);

        // Exam End Time
        Label examEndTimeLabel = new Label("*Exam End Time (HH:mm):");
        GridPane.setConstraints(examEndTimeLabel, 0, 4);
        TextField examEndTimeInput = new TextField();
        GridPane.setConstraints(examEndTimeInput, 1, 4);

        // Add components to exam grid
        examGrid.getChildren().addAll(examHeaderLabel, examIdLabel, examIdInput, examDateLabel, examDateInput, examStartTimeLabel, examStartTimeInput, examEndTimeLabel, examEndTimeInput);

        // Create Tabs
        Tab studentTab = new Tab("Student", studentGrid);
        studentTab.setClosable(false); // Remove the close button

        Tab professorTab = new Tab("Professor", professorGrid);
        professorTab.setClosable(false); // Remove the close button

        Tab examTab = new Tab("Exam", examGrid);
        examTab.setClosable(false); // Remove the close button

        Tab subjectTab = new Tab("Subject", subjectGrid);
        subjectTab.setClosable(false); // Remove the close button

        Tab classroomTab = new Tab("Classroom", classroomGrid);
        classroomTab.setClosable(false); // Remove the close button

        tabPane.getTabs().addAll(studentTab, professorTab, examTab, subjectTab, classroomTab);

        // Single Insert Button
        Button insertButton = new Button("Insert");
        insertButton.setOnAction(e -> {
            // Validate subject fields
            String subjectIdText = subjectIdInput.getText();
            int subjectId = subjectIdText.isEmpty() ? 0 : Integer.parseInt(subjectIdText);
            String subjectName = subjectNameInput.getText();

            // Validate classroom fields
            String roomNr = roomNumberInput.getText();
            String building = buildingInput.getText();
            String capacityText = capacityInput.getText();
            int capacity = capacityText.isEmpty() ? 0 : Integer.parseInt(capacityText);

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
            String department = departmentInput.getText();
            String affiliation = affiliationInput.getText();

            // Validate exam fields
            String examIdText = examIdInput.getText();
            int examId = examIdText.isEmpty() ? 0 : Integer.parseInt(examIdText);
            String examDate = examDateInput.getText();
            String examStartTime = examStartTimeInput.getText();
            String examEndTime = examEndTimeInput.getText();

            // Check for empty or invalid subject fields
            if (subjectName.trim().isEmpty() || subjectId == 0) {
                showAlert(Alert.AlertType.ERROR, "Error", "Complete required subject fields.");
                return;
            }

            // Check for empty or invalid classroom fields
            if (roomNr.trim().isEmpty() || building.trim().isEmpty() || capacity == 0) {
                showAlert(Alert.AlertType.ERROR, "Error", "Complete required classroom fields.");
                return;
            }

            // Check for empty or invalid student fields
            if (firstName.trim().isEmpty() || lastName.trim().isEmpty() || dateOfBirth.trim().isEmpty() || level == null || studentId == 0) {
                showAlert(Alert.AlertType.ERROR, "Error", "Complete required student fields.");
                return;
            }

            // Check for empty or invalid professor fields
            if (professorFirstName.trim().isEmpty() || professorLastName.trim().isEmpty() || professorId == 0) {
                showAlert(Alert.AlertType.ERROR, "Error", "Complete required professor fields.");
                return;
            }

            // Check for empty or invalid exam fields
            if (examDate.trim().isEmpty() || examStartTime.trim().isEmpty() || examEndTime.trim().isEmpty() || examId == 0) {
                showAlert(Alert.AlertType.ERROR, "Error", "Complete required exam fields.");
                return;
            }

            // Insert entities into the database
            try {
                subjectEntity.addSubject(subjectId, subjectName);
            } catch (SQLException ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Database Error", "An error occurred while adding subject, check again the Primary Keys or other constraints");
                return; // Stop further execution if subject insertion fails
            }

            try {
                classroomEntity.addClassroom(roomNr, building, capacity);
            } catch (SQLException ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Database Error", "An error occurred while adding classroom, check again the Primary Keys or other constraints");
                return; // Stop further execution if classroom insertion fails
            }

            try {
                studentEntity.addStudent(studentId, firstName, lastName, dateOfBirth, level, email, work);
            } catch (SQLException ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Database Error", "An error occurred while adding student, check again the Primary Keys or other constraints");
                return; // Stop further execution if student insertion fails
            }

            try {
                professorEntity.addProfessor(professorId, professorFirstName, professorLastName, professorEmail, department, affiliation);
            } catch (SQLException ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Database Error", "An error occurred while adding professor, check again the Primary Keys or other constraints");
                return; // Stop further execution if professor insertion fails
            }

            try {
                examEntity.addExam(examId, examDate, examStartTime, examEndTime, professorId, subjectId, roomNr, building);
            } catch (SQLException ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Database Error", "An error occurred while adding exam, check again the Primary Keys or other constraints");
                return; // Stop further execution if exam insertion fails
            }

            // Insert relationships into the database
            try {
                takesRelationship.addTakes(studentId, examId);
            } catch (SQLException ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Database Error", "An error occurred while adding takes relationship, check again the Primary Keys or other constraints");
                return; // Stop further execution if takes insertion fails
            }

            try {
                hostsRelationship.addHosts(roomNr, building, subjectId);
            } catch (SQLException ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Database Error", "An error occurred while adding hosts relationship, check again the Primary Keys or other constraints");
                return; // Stop further execution if hosts insertion fails
            }

            try {
                taughtByRelationship.addTaughtBy(subjectId, professorId);
            } catch (SQLException ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Database Error", "An error occurred while adding taughtBy relationship, check again the Primary Keys or other constraints");
                return; // Stop further execution if taughtBy insertion fails
            }

            try {
                mentorsRelationship.addMentors(professorId, studentId);
            } catch (SQLException ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Database Error", "An error occurred while adding mentors relationship, check again the Primary Keys or other constraints");
                return; // Stop further execution if mentors insertion fails
            }

            // Create and add a Person object to the combined list
            Person person = new Person(studentId, professorId, level, firstName + " " + lastName, professorFirstName + " " + professorLastName, department, affiliation, examId, examDate, subjectId, subjectName, roomNr, building, capacity);
            combinedList.add(person);
            System.out.println("Enrollment added successfully");

            // Clear input fields
            subjectIdInput.clear();
            subjectNameInput.clear();

            roomNumberInput.clear();
            buildingInput.clear();
            capacityInput.clear();

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
            departmentInput.clear();
            affiliationInput.clear();

            examIdInput.clear();
            examDateInput.clear();
            examStartTimeInput.clear();
            examEndTimeInput.clear();
        });

        // Delete Button
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            // Get selected Person from the table view
            Person selectedPerson = combinedTableView.getSelectionModel().getSelectedItem();
            if (selectedPerson != null) {
                try {
                    int studentId = selectedPerson.getStudentId();
                    int professorId = selectedPerson.getProfessorId();
                    int examId = selectedPerson.getExamId();
                    int subjectId = selectedPerson.getSubjectId();
                    String roomNr = selectedPerson.getRoomNr();
                    String building = selectedPerson.getBuilding();

                    // Log deletion steps
                    System.out.println("Delete relationships and main entities for selected person.");

                    // Delete relationships first
                    try {
                        takesRelationship.deleteTakes(studentId, examId);
                        System.out.println("Deleted from Takes relationship.");
                    } catch (SQLException ex) {
                        System.err.println("Failed to delete from Takes relationship.");
                        ex.printStackTrace();
                    }

                    try {
                        hostsRelationship.deleteHosts(roomNr, building, subjectId);
                        System.out.println("Deleted from Hosts relationship.");
                    } catch (SQLException ex) {
                        System.err.println("Failed to delete from Hosts relationship.");
                        ex.printStackTrace();
                    }

                    try {
                        taughtByRelationship.deleteTaughtBy(subjectId, professorId);
                        System.out.println("Deleted from TaughtBy relationship.");
                    } catch (SQLException ex) {
                        System.err.println("Failed to delete from TaughtBy relationship.");
                        ex.printStackTrace();
                    }

                    try {
                        mentorsRelationship.deleteMentors(professorId, studentId);
                        System.out.println("Deleted from Mentors relationship.");
                    } catch (SQLException ex) {
                        System.err.println("Failed to delete from Mentors relationship.");
                        ex.printStackTrace();
                    }

                    // Delete main entities
                    try {
                        studentEntity.deleteStudent(studentId);
                        System.out.println("Deleted student.");
                    } catch (SQLException ex) {
                        System.err.println("Failed to delete student.");
                        ex.printStackTrace();
                    }

                    try {
                        professorEntity.deleteProfessor(professorId);
                        System.out.println("Deleted professor.");
                    } catch (SQLException ex) {
                        System.err.println("Failed to delete professor.");
                        ex.printStackTrace();
                    }

                    try {
                        examEntity.deleteExam(examId);
                        System.out.println("Deleted exam.");
                    } catch (SQLException ex) {
                        System.err.println("Failed to delete exam.");
                        ex.printStackTrace();
                    }

                    try {
                        subjectEntity.deleteSubject(subjectId);
                        System.out.println("Deleted subject.");
                    } catch (SQLException ex) {
                        System.err.println("Failed to delete subject.");
                        ex.printStackTrace();
                    }

                    combinedList.remove(selectedPerson);
                    System.out.println("Enrollment deleted successfully.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    showAlert(Alert.AlertType.ERROR, "Database Error", "An error occurred while deleting the student, professor, exam, subject, and classroom.");
                }
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "No enrollment selected.");
            }
        });

        // Load existing data from the database
        try {
            List<StudentEntity.Student> students = studentEntity.getAllStudents();
            List<ProfessorEntity.Professor> professors = professorEntity.getAllProfessors();
            List<ExamEntity.Exam> exams = examEntity.getAllExams();
            List<SubjectEntity.Subject> subjects = subjectEntity.getAllSubjects();
            List<ClassroomEntity.Classroom> classrooms = classroomEntity.getAllClassrooms();

            // Add existing students to combined list
            for (StudentEntity.Student student : students) {
                combinedList.add(new Person(student.getStudentId(), 0, student.getLevel(), student.getFirstName() + " " + student.getLastName(), "", "", "", 0, "", 0, "", "", "", 0));
            }

            // Add existing professors to combined list
            for (ProfessorEntity.Professor professor : professors) {
                for (Person person : combinedList) {
                    if (person.getProfessor().isEmpty()) {
                        person.setProfessor(professor.getFirstName() + " " + professor.getLastName());
                        person.setDepartment(professor.getDepartment());
                        person.setAffiliation(professor.getAffiliation());
                        person.setProfessorId(professor.getProfessorId());
                        break;
                    }
                }
            }

            // Add existing exams to combined list
            for (ExamEntity.Exam exam : exams) {
                for (Person person : combinedList) {
                    if (person.getExamId() == 0) {
                        person.setExamId(exam.getExamId());
                        person.setExamDate(exam.getExamDate());
                        break;
                    }
                }
            }
            // Add existing subjects to combined list
            for (SubjectEntity.Subject subject : subjects) {
                for (Person person : combinedList) {
                    if (person.getSubject().isEmpty()) {
                        person.setSubject(subject.getSubjectName());
                        person.setSubjectId(subject.getSubjectId());
                        break;
                    }
                }
            }
            // Add existing classrooms to combined list
            for (ClassroomEntity.Classroom classroom : classrooms) {
                for (Person person : combinedList) {
                    if (person.getRoomNr().isEmpty() && person.getBuilding().isEmpty()) {
                        person.setRoomNr(classroom.getRoomNr());
                        person.setBuilding(classroom.getBuilding());
                        person.setCapacity(classroom.getCapacity());
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Table View for combined list of Students, Professors, Exams, Subjects, and Classrooms
        combinedTableView = new TableView<>(combinedList);

        // Define columns for the table view
        TableColumn<Person, String> subjectColumn = new TableColumn<>("Subject");
        subjectColumn.setCellValueFactory(cellData -> cellData.getValue().subjectProperty());

        TableColumn<Person, String> examDateColumn = new TableColumn<>("Exam Date");
        examDateColumn.setCellValueFactory(cellData -> cellData.getValue().examDateProperty());

        TableColumn<Person, Number> examIdColumn = new TableColumn<>("Exam ID");
        examIdColumn.setCellValueFactory(cellData -> cellData.getValue().examIdProperty());

        TableColumn<Person, String> professorColumn = new TableColumn<>("Professor");
        professorColumn.setCellValueFactory(cellData -> cellData.getValue().professorProperty());

        TableColumn<Person, Number> studentIdColumn = new TableColumn<>("Student ID");
        studentIdColumn.setCellValueFactory(cellData -> cellData.getValue().studentIdProperty());

        TableColumn<Person, String> nameColumn = new TableColumn<>("Student");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        TableColumn<Person, String> roomNrColumn = new TableColumn<>("Room Number");
        roomNrColumn.setCellValueFactory(cellData -> cellData.getValue().roomNrProperty());

        TableColumn<Person, String> buildingColumn = new TableColumn<>("Building");
        buildingColumn.setCellValueFactory(cellData -> cellData.getValue().buildingProperty());

        TableColumn<Person, Number> capacityColumn = new TableColumn<>("Capacity");
        capacityColumn.setCellValueFactory(cellData -> cellData.getValue().capacityProperty());
        
        combinedTableView.getColumns().addAll(subjectColumn, examDateColumn, examIdColumn, professorColumn, studentIdColumn, nameColumn, roomNrColumn, buildingColumn, capacityColumn);

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(tabPane, insertButton, deleteButton, combinedTableView);
        vbox.setPadding(new Insets(10, 10, 10, 10));

        // Setup scene and stage
        Scene scene = new Scene(vbox, 1500, 775);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Student Enrollment System");
        primaryStage.show();
    }

    // Utility method to show alert dialogs
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
        private final StringProperty professor;
        private final StringProperty department;
        private final StringProperty affiliation;
        private final IntegerProperty examId;
        private final StringProperty examDate;
        private final IntegerProperty subjectId;
        private final StringProperty subject;
        private final StringProperty roomNr;
        private final StringProperty building;
        private final IntegerProperty capacity;

        public Person(int studentId, int professorId, String level, String name, String professor, String department, String affiliation, int examId, String examDate, int subjectId, String subject, String roomNr, String building, int capacity) {
            this.studentId = new SimpleIntegerProperty(studentId);
            this.professorId = new SimpleIntegerProperty(professorId);
            this.level = new SimpleStringProperty(level);
            this.name = new SimpleStringProperty(name);
            this.professor = new SimpleStringProperty(professor);
            this.department = new SimpleStringProperty(department);
            this.affiliation = new SimpleStringProperty(affiliation);
            this.examId = new SimpleIntegerProperty(examId);
            this.examDate = new SimpleStringProperty(examDate);
            this.subjectId = new SimpleIntegerProperty(subjectId);
            this.subject = new SimpleStringProperty(subject);
            this.roomNr = new SimpleStringProperty(roomNr);
            this.building = new SimpleStringProperty(building);
            this.capacity = new SimpleIntegerProperty(capacity);
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

        public String getProfessor() {
            return professor.get();
        }

        public void setProfessor(String professor) {
            this.professor.set(professor);
        }

        public StringProperty professorProperty() {
            return professor;
        }

        public String getDepartment() {
            return department.get();
        }

        public void setDepartment(String department) {
            this.department.set(department);
        }

        public StringProperty departmentProperty() {
            return department;
        }

        public String getAffiliation() {
            return affiliation.get();
        }

        public void setAffiliation(String affiliation) {
            this.affiliation.set(affiliation);
        }

        public StringProperty affiliationProperty() {
            return affiliation;
        }

        public int getExamId() {
            return examId.get();
        }

        public void setExamId(int examId) {
            this.examId.set(examId);
        }

        public IntegerProperty examIdProperty() {
            return examId;
        }

        public String getExamDate() {
            return examDate.get();
        }

        public void setExamDate(String examDate) {
            this.examDate.set(examDate);
        }

        public StringProperty examDateProperty() {
            return examDate;
        }

        public int getSubjectId() {
            return subjectId.get();
        }

        public void setSubjectId(int subjectId) {
            this.subjectId.set(subjectId);
        }

        public IntegerProperty subjectIdProperty() {
            return subjectId;
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

        public String getRoomNr() {
            return roomNr.get();
        }

        public void setRoomNr(String roomNr) {
            this.roomNr.set(roomNr);
        }

        public StringProperty roomNrProperty() {
            return roomNr;
        }

        public String getBuilding() {
            return building.get();
        }

        public void setBuilding(String building) {
            this.building.set(building);
        }

        public StringProperty buildingProperty() {
            return building;
        }

        public int getCapacity() {
            return capacity.get();
        }

        public void setCapacity(int capacity) {
            this.capacity.set(capacity);
        }

        public IntegerProperty capacityProperty() {
            return capacity;
        }
    }
}
