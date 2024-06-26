package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class CoversRelationship {

    private Connection connection;

    public CoversRelationship(Connection connection) {
        this.connection = connection;
    }

    public void addCovers(int examId, int subjectId) throws SQLException {
        String sql = "INSERT INTO Covers (examid, subjectid) VALUES (?, ?)";
        try (PreparedStatement coversInfo = connection.prepareStatement(sql)) {
            coversInfo.setInt(1, examId);
            coversInfo.setInt(2, subjectId);
            coversInfo.executeUpdate();
        }
    }

    public void deleteCovers(int examId, int subjectId) throws SQLException {
        String sql = "DELETE FROM Covers WHERE examid = ? AND subjectid = ?";
        try (PreparedStatement coversInfo = connection.prepareStatement(sql)) {
            coversInfo.setInt(1, examId);
            coversInfo.setInt(2, subjectId);
            coversInfo.executeUpdate();
        }
    }

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

    public static class Covers {
        private final int examId;
        private final int subjectId;

        public Covers(int examId, int subjectId) {
            this.examId = examId;
            this.subjectId = subjectId;
        }

        public int getExamId() {
            return examId;
        }

        public int getSubjectId() {
            return subjectId;
        }
    }
}
