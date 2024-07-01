package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class ExamEntity {

    private Connection connection;

    public ExamEntity(Connection connection) {
        this.connection = connection;
    }

    // Method for adding exams after the INSERT button is pressed
    public void addExam(int examId, String examDate, String examStartTime, String examEndTime, int professorId, int subjectId, String roomNr, String building) throws SQLException {
        String sql = "INSERT INTO Exam (examId, examDate, examStartTime, examEndTime, professorId, subjectId, roomNr, building) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement examInfo = connection.prepareStatement(sql)) {
            examInfo.setInt(1, examId);
            examInfo.setDate(2, Date.valueOf(examDate));
            examInfo.setTime(3, Time.valueOf(examStartTime + ":00"));
            examInfo.setTime(4, Time.valueOf(examEndTime + ":00"));
            examInfo.setInt(5, professorId);
            examInfo.setInt(6, subjectId);
            examInfo.setString(7, roomNr);
            examInfo.setString(8, building);
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
                        rs.getDate("examDate").toString(),
                        rs.getTime("examStartTime").toString(),
                        rs.getTime("examEndTime").toString(),
                        rs.getInt("professorId"),
                        rs.getInt("subjectId"),
                        rs.getString("roomNr"),
                        rs.getString("building")
                );
                exams.add(exam);
            }
        }
        return exams;
    }

    public static class Exam {
        private final int examId;
        private final String examDate;
        private final String examStartTime;
        private final String examEndTime;
        private final int professorId;
        private final int subjectId;
        private final String roomNr;
        private final String building;

        public Exam(int examId, String examDate, String examStartTime, String examEndTime, int professorId, int subjectId, String roomNr, String building) {
            this.examId = examId;
            this.examDate = examDate;
            this.examStartTime = examStartTime;
            this.examEndTime = examEndTime;
            this.professorId = professorId;
            this.subjectId = subjectId;
            this.roomNr = roomNr;
            this.building = building;
        }

        public int getExamId() {
            return examId;
        }

        public String getExamDate() {
            return examDate;
        }

        public String getExamStartTime() {
            return examStartTime;
        }

        public String getExamEndTime() {
            return examEndTime;
        }

        public int getProfessorId() {
            return professorId;
        }

        public int getSubjectId() {
            return subjectId;
        }

        public String getRoomNr() {
            return roomNr;
        }

        public String getBuilding() {
            return building;
        }
    }
}
