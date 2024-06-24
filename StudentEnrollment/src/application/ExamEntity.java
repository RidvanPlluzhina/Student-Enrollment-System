package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ExamEntity {

    private Connection connection;

    public ExamEntity(Connection connection) {
        this.connection = connection;
    }

    // Method for adding exams after the INSERT button is pressed
    public void addExam(int examId, String examDate) throws SQLException {
        String sql = "INSERT INTO Exam (examId, examDate) VALUES (?, ?)";
        try (PreparedStatement examInfo = connection.prepareStatement(sql)) {
            examInfo.setInt(1, examId);
            examInfo.setDate(2, Date.valueOf(examDate));
            examInfo.executeUpdate();
        }
    }

    // Method for deleting exams after the DELETE button is pressed
    public void deleteExam(int examId) throws SQLException {
        String sql = "DELETE FROM Exam WHERE examId = ?";
        try (PreparedStatement examInfo = connection.prepareStatement(sql)) {
            examInfo.setInt(1, examId);
            examInfo.executeUpdate();
        }
    }

    public List<Exam> getAllExams() throws SQLException {
        List<Exam> exams = new ArrayList<>();
        String sql = "SELECT * FROM Exam";
        try (PreparedStatement examInfo = connection.prepareStatement(sql);
             ResultSet rs = examInfo.executeQuery()) {
            while (rs.next()) {
                Exam exam = new Exam(
                        rs.getInt("examId"),
                        rs.getDate("examDate").toString()
                );
                exams.add(exam);
            }
        }
        return exams;
    }

    public static class Exam {
        private final int examId;
        private final String examDate;

        public Exam(int examId, String examDate) {
            this.examId = examId;
            this.examDate = examDate;
        }

        public int getExamId() {
            return examId;
        }

        public String getExamDate() {
            return examDate;
        }
    }
}
