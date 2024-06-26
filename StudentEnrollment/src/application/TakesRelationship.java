package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class TakesRelationship {

    private Connection connection;

    public TakesRelationship(Connection connection) {
        this.connection = connection;
    }

    public void addTakes(int studentId, int examId) throws SQLException {
        String sql = "INSERT INTO Takes (studentid, examid) VALUES (?, ?)";
        try (PreparedStatement takesInfo = connection.prepareStatement(sql)) {
            takesInfo.setInt(1, studentId);
            takesInfo.setInt(2, examId);
            takesInfo.executeUpdate();
        }
    }

    public void deleteTakes(int studentId, int examId) throws SQLException {
        String sql = "DELETE FROM Takes WHERE studentid = ? AND examid = ?";
        try (PreparedStatement takesInfo = connection.prepareStatement(sql)) {
            takesInfo.setInt(1, studentId);
            takesInfo.setInt(2, examId);
            takesInfo.executeUpdate();
        }
    }

    public List<Takes> getAllTakes() throws SQLException {
        List<Takes> takesList = new ArrayList<>();
        String sql = "SELECT * FROM Takes";
        try (PreparedStatement takesInfo = connection.prepareStatement(sql);
             ResultSet rs = takesInfo.executeQuery()) {
            while (rs.next()) {
                Takes takes = new Takes(
                        rs.getInt("studentid"),
                        rs.getInt("examid")
                );
                takesList.add(takes);
            }
        }
        return takesList;
    }

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
