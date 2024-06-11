package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProfessorEntity {

    private Connection connection;

    public ProfessorEntity(Connection connection) {
        this.connection = connection;
    }
    //Method for adding professor after INSERT button is pressed
    public void addProfessor(int professorId, String firstName, String lastName, String email, String subject, String phoneNumbers) throws SQLException {
        String sql = "INSERT INTO Professor (professorId, FirstName, LastName, Email, Subject, PhoneNumbers) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement professorInfo = connection.prepareStatement(sql)) {
            professorInfo.setInt(1, professorId);
            professorInfo.setString(2, firstName);
            professorInfo.setString(3, lastName);
            professorInfo.setString(4, email);
            professorInfo.setString(5, subject);

            if (phoneNumbers == null || phoneNumbers.trim().isEmpty()) {
                professorInfo.setNull(6, java.sql.Types.VARCHAR);
            } else {
                professorInfo.setString(6, phoneNumbers);
            }

            professorInfo.executeUpdate();
        }
    }
    //Method for deleting professor after DELETE button is pressed
    public void deleteProfessor(int professorId) throws SQLException {
        String sql = "DELETE FROM Professor WHERE professorId = ?";
        try (PreparedStatement professorInfo = connection.prepareStatement(sql)) {
            professorInfo.setInt(1, professorId);
            professorInfo.executeUpdate();
        }
    }

    public List<Professor> getAllProfessors() throws SQLException {
        String sql = "SELECT * FROM Professor";
        List<Professor> professors = new ArrayList<>();
        try (PreparedStatement professorInfo = connection.prepareStatement(sql);
             ResultSet rs = professorInfo.executeQuery()) {

            while (rs.next()) {
                Professor professor = new Professor(
                        rs.getInt("professorId"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getString("subject"),
                        rs.getString("phoneNumbers")
                );
                professors.add(professor);
            }
        }
        return professors;
    }

    public static class Professor {
        private final int professorId;
        private final String firstName;
        private final String lastName;
        private final String email;
        private final String subject;
        private final String phoneNumbers;

        public Professor(int professorId, String firstName, String lastName, String email, String subject, String phoneNumbers) {
            this.professorId = professorId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.subject = subject;
            this.phoneNumbers = phoneNumbers;
        }

        public int getProfessorId() {
            return professorId;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getEmail() {
            return email;
        }

        public String getSubject() {
            return subject;
        }

        public String getPhoneNumbers() {
            return phoneNumbers;
        }
    }
}
