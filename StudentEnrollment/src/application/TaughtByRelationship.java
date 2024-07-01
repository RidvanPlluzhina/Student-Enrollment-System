package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class TaughtByRelationship {

    private Connection connection;

    // Constructor that initializes the connection object
    public TaughtByRelationship(Connection connection) {
        this.connection = connection;
    }

    // Method to add a relationship between a subject and a professor in the TaughtBy table
    public void addTaughtBy(int subjectId, int professorId) throws SQLException {
        String sql = "INSERT INTO TaughtBy (subjectId, professorId) VALUES (?, ?)";
        try (PreparedStatement taughtByInfo = connection.prepareStatement(sql)) {
            taughtByInfo.setInt(1, subjectId);
            taughtByInfo.setInt(2, professorId);
            taughtByInfo.executeUpdate();
        }
    }

    // Method to delete a relationship between a subject and a professor from the TaughtBy table
    public void deleteTaughtBy(int subjectId, int professorId) throws SQLException {
        String sql = "DELETE FROM TaughtBy WHERE subjectId = ? AND professorId = ?";
        try (PreparedStatement taughtByInfo = connection.prepareStatement(sql)) {
            taughtByInfo.setInt(1, subjectId);
            taughtByInfo.setInt(2, professorId);
            taughtByInfo.executeUpdate();
        }
    }

    // Method to retrieve all relationships from the TaughtBy table
    public List<TaughtBy> getAllTaughtBy() throws SQLException {
        List<TaughtBy> taughtByList = new ArrayList<>();
        String sql = "SELECT * FROM TaughtBy";
        try (PreparedStatement taughtByInfo = connection.prepareStatement(sql);
             ResultSet rs = taughtByInfo.executeQuery()) {
            while (rs.next()) {
                TaughtBy taughtBy = new TaughtBy(
                        rs.getInt("subjectId"),
                        rs.getInt("professorId")
                );
                taughtByList.add(taughtBy);
            }
        }
        return taughtByList;
    }

    // Inner class to represent a TaughtBy relationship (subject-professor pair)
    public static class TaughtBy {
        private final int subjectId;
        private final int professorId;

        public TaughtBy(int subjectId, int professorId) {
            this.subjectId = subjectId;
            this.professorId = professorId;
        }

        public int getSubjectId() {
            return subjectId;
        }

        public int getProfessorId() {
            return professorId;
        }
    }
}
