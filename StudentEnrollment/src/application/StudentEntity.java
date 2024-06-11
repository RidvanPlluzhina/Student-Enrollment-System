package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentEntity {

    private Connection connection;

    public StudentEntity(Connection connection) {
        this.connection = connection;
    }
    
    //Method for adding students after INSERT button pressed
    public void addStudent(int studentId, String firstName, String lastName, String dateOfBirth, String level, String email, String work) throws SQLException {
        String sql = "INSERT INTO Student (studentId, FirstName, LastName, DateOfBirth, Level, Email, Work) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement studentInfo = connection.prepareStatement(sql)) {
            studentInfo.setInt(1, studentId);
            studentInfo.setString(2, firstName);
            studentInfo.setString(3, lastName);
            studentInfo.setDate(4, java.sql.Date.valueOf(dateOfBirth));
            studentInfo.setString(5, level);

            if (email == null || email.trim().isEmpty()) {
                studentInfo.setNull(6, java.sql.Types.VARCHAR);
            } else {
                studentInfo.setString(6, email);
            }

            if (work == null || work.trim().isEmpty()) {
                studentInfo.setNull(7, java.sql.Types.VARCHAR);
            } else {
                studentInfo.setString(7, work);
            }

            studentInfo.executeUpdate();
        }
    }
    
    //Method for deleting student after DELETE button is pressed
    public void deleteStudent(int studentId) throws SQLException {
        String sql = "DELETE FROM Student WHERE studentId = ?";
        try (PreparedStatement studentInfo = connection.prepareStatement(sql)) {
            studentInfo.setInt(1, studentId);
            studentInfo.executeUpdate();
        }
    }

    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM Student";
        try (PreparedStatement studentInfo = connection.prepareStatement(sql);
             ResultSet rs = studentInfo.executeQuery()) {
            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("studentId"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getDate("DateOfBirth").toString(),
                        rs.getString("Level"),
                        rs.getString("Email"),
                        rs.getString("Work")
                );
                students.add(student);
            }
        }
        return students;
    }

    public static class Student {
        private final int studentId;
        private final String firstName;
        private final String lastName;
        private final String dateOfBirth;
        private final String level;
        private final String email;
        private final String work;

        public Student(int studentId, String firstName, String lastName, String dateOfBirth, String level, String email, String work) {
            this.studentId = studentId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.dateOfBirth = dateOfBirth;
            this.level = level;
            this.email = email;
            this.work = work;
        }

        public int getStudentId() {
            return studentId;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getDateOfBirth() {
            return dateOfBirth;
        }

        public String getLevel() {
            return level;
        }

        public String getEmail() {
            return email;
        }

        public String getWork() {
            return work;
        }
    }
}
