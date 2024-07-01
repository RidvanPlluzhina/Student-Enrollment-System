package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SubjectEntity {

    private Connection connection;

    public SubjectEntity(Connection connection) {
        this.connection = connection;
    }

    // Method for adding subjects
    public void addSubject(int subjectId, String subjectName) throws SQLException {
        String sql = "INSERT INTO Subject (subjectId, subjectName) VALUES (?, ?)";
        try (PreparedStatement subjectInfo = connection.prepareStatement(sql)) {
            subjectInfo.setInt(1, subjectId);
            subjectInfo.setString(2, subjectName);
            subjectInfo.executeUpdate();
        }
    }

    // Method for deleting subjects
    public void deleteSubject(int subjectId) throws SQLException {
        String sql = "DELETE FROM Subject WHERE subjectId = ?";
        try (PreparedStatement subjectInfo = connection.prepareStatement(sql)) {
            subjectInfo.setInt(1, subjectId);
            subjectInfo.executeUpdate();
        }
    }

    // Method for retrieving all subjects
    public List<Subject> getAllSubjects() throws SQLException {
        String sql = "SELECT * FROM Subject";
        List<Subject> subjects = new ArrayList<>();
        try (PreparedStatement subjectInfo = connection.prepareStatement(sql);
             ResultSet rs = subjectInfo.executeQuery()) {

            while (rs.next()) {
                Subject subject = new Subject(
                        rs.getInt("subjectId"),
                        rs.getString("subjectName")
                );
                subjects.add(subject);
            }
        }
        return subjects;
    }

    public static class Subject {
        private final int subjectId;
        private final String subjectName;

        public Subject(int subjectId, String subjectName) {
            this.subjectId = subjectId;
            this.subjectName = subjectName;
        }

        public int getSubjectId() {
            return subjectId;
        }

        public String getSubjectName() {
            return subjectName;
        }
    }
}
