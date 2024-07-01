package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class MentorsRelationship {

    private Connection connection;

    // Initializes the MentorsRelationship with a database connection
    public MentorsRelationship(Connection connection) {
        this.connection = connection;
    }

    // Adds a new Mentors relationship record to the database
    public void addMentors(int professorId, int studentId) throws SQLException {
        String sql = "INSERT INTO Mentors (professorId, studentId) VALUES (?, ?)";
        try (PreparedStatement mentorsInfo = connection.prepareStatement(sql)) {
            mentorsInfo.setInt(1, professorId);
            mentorsInfo.setInt(2, studentId);
            mentorsInfo.executeUpdate();
        }
    }

    // Deletes a Mentors relationship record from the database based on the professor ID and student ID
    public void deleteMentors(int professorId, int studentId) throws SQLException {
        String sql = "DELETE FROM Mentors WHERE professorId = ? AND studentId = ?";
        try (PreparedStatement mentorsInfo = connection.prepareStatement(sql)) {
            mentorsInfo.setInt(1, professorId);
            mentorsInfo.setInt(2, studentId);
            mentorsInfo.executeUpdate();
        }
    }

    // Retrieves all Mentors relationship records from the database
    public List<Mentors> getAllMentors() throws SQLException {
        List<Mentors> mentorsList = new ArrayList<>();
        String sql = "SELECT * FROM Mentors";
        try (PreparedStatement mentorsInfo = connection.prepareStatement(sql);
             ResultSet rs = mentorsInfo.executeQuery()) {
            while (rs.next()) {
                Mentors mentors = new Mentors(
                        rs.getInt("professorId"),
                        rs.getInt("studentId")
                );
                mentorsList.add(mentors);
            }
        }
        return mentorsList;
    }

    // Represents a Mentors relationship record
    public static class Mentors {
        private final int professorId;
        private final int studentId;

        // Initializes the Mentors relationship with specified details
        public Mentors(int professorId, int studentId) {
            this.professorId = professorId;
            this.studentId = studentId;
        }

        // Getters
        public int getProfessorId() {
            return professorId;
        }

        public int getStudentId() {
            return studentId;
        }
    }
}
