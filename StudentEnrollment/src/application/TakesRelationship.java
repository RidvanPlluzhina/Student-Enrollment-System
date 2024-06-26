package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class TakesRelationship {

    private Connection connection;

    // Constructor that initializes the connection object
    public TakesRelationship(Connection connection) {
        this.connection = connection;
    }

    // Method to add a relationship between a student and an exam in the Takes table
    public void addTakes(int studentId, int examId) throws SQLException {
        String sql = "INSERT INTO Takes (studentId, examId) VALUES (?, ?)";
        try (PreparedStatement takesInfo = connection.prepareStatement(sql)) {
            takesInfo.setInt(1, studentId);
            takesInfo.setInt(2, examId);
            takesInfo.executeUpdate();
        }
    }

    // Method to delete a relationship between a student and an exam from the Takes table
    public void deleteTakes(int studentId, int examId) throws SQLException {
        String sql = "DELETE FROM Takes WHERE studentId = ? AND examId = ?";
        try (PreparedStatement takesInfo = connection.prepareStatement(sql)) {
            takesInfo.setInt(1, studentId);
            takesInfo.setInt(2, examId);
            takesInfo.executeUpdate();
        }
    }

    // Method to retrieve all relationships from the Takes table
    public List<Takes> getAllTakes() throws SQLException {
        List<Takes> takesList = new ArrayList<>();
        String sql = "SELECT * FROM Takes";
        try (PreparedStatement takesInfo = connection.prepareStatement(sql);
             ResultSet rs = takesInfo.executeQuery()) {
            while (rs.next()) {
                Takes takes = new Takes(
                        rs.getInt("studentId"),
                        rs.getInt("examId")
                );
                takesList.add(takes);
            }
        }
        return takesList;
    }

    // Inner class to represent a Takes relationship (student-exam pair)
    public static class Takes {
        private final int studentId;
        private final int examId;

        public Takes(int studentId, int examId) {
            this.studentId = studentId;
            this.examId = examId;
        }

        public int getStudentId() {
            return studentId;
        }

        public int getExamId() {
            return examId;
        }
    }
}
