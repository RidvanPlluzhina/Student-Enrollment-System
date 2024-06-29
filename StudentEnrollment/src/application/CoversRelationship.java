package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class CoversRelationship {

    private Connection connection;

    // Initializes the CoversRelationship with a database connection
    public CoversRelationship(Connection connection) {
        this.connection = connection;
    }

    // Adds a new Covers relationship record to the database
    public void addCovers(int examId, int subjectId) throws SQLException {
        String sql = "INSERT INTO Covers (examid, subjectid) VALUES (?, ?)";
        try (PreparedStatement coversInfo = connection.prepareStatement(sql)) {
            coversInfo.setInt(1, examId);
            coversInfo.setInt(2, subjectId);
            coversInfo.executeUpdate();
        }
    }
    
    // Deletes a Covers relationship record from the database based on the exam ID and subject ID
    public void deleteCovers(int examId, int subjectId) throws SQLException {
        String sql = "DELETE FROM Covers WHERE examid = ? AND subjectid = ?";
        try (PreparedStatement coversInfo = connection.prepareStatement(sql)) {
            coversInfo.setInt(1, examId);
            coversInfo.setInt(2, subjectId);
            coversInfo.executeUpdate();
        }
    }
    
    // Retrieves all Covers relationship records from the database
    public List<Covers> getAllCovers() throws SQLException {
        List<Covers> coversList = new ArrayList<>();
        String sql = "SELECT * FROM Covers";
        try (PreparedStatement coversInfo = connection.prepareStatement(sql);
             ResultSet rs = coversInfo.executeQuery()) {
            while (rs.next()) {
                Covers covers = new Covers(
                        rs.getInt("examid"),
                        rs.getInt("subjectid")
                );
                coversList.add(covers);
            }
        }
        return coversList;
    }
    
    // Represents a Covers relationship record
    public static class Covers {
        private final int examId;
        private final int subjectId;

        // Initializes the Covers relationship with specified details
        public Covers(int examId, int subjectId) {
            this.examId = examId;
            this.subjectId = subjectId;
        }
        // Getters
        public int getExamId() {
            return examId;
        }

        public int getSubjectId() {
            return subjectId;
        }
    }
}
