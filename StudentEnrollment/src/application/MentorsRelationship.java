package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class MentorsRelationship {

    private Connection connection;

    public MentorsRelationship(Connection connection) {
        this.connection = connection;
    }

    public void addMentors(int professorId, int studentId) throws SQLException {
        String sql = "INSERT INTO Mentors (professorid, studentid) VALUES (?, ?)";
        try (PreparedStatement mentorsInfo = connection.prepareStatement(sql)) {
            mentorsInfo.setInt(1, professorId);
            mentorsInfo.setInt(2, studentId);
            mentorsInfo.executeUpdate();
        }
    }

    public void deleteMentors(int professorId, int studentId) throws SQLException {
        String sql = "DELETE FROM Mentors WHERE professorid = ? AND studentid = ?";
        try (PreparedStatement mentorsInfo = connection.prepareStatement(sql)) {
            mentorsInfo.setInt(1, professorId);
            mentorsInfo.setInt(2, studentId);
            mentorsInfo.executeUpdate();
        }
    }

    public List<Mentors> getAllMentors() throws SQLException {
        List<Mentors> mentorsList = new ArrayList<>();
        String sql = "SELECT * FROM Mentors";
        try (PreparedStatement mentorsInfo = connection.prepareStatement(sql);
             ResultSet rs = mentorsInfo.executeQuery()) {
            while (rs.next()) {
                Mentors mentors = new Mentors(
                        rs.getInt("professorid"),
                        rs.getInt("studentid")
                );
                mentorsList.add(mentors);
            }
        }
        return mentorsList;
    }

    public static class Mentors {
        private final int professorId;
        private final int studentId;

        public Mentors(int professorId, int studentId) {
            this.professorId = professorId;
            this.studentId = studentId;
        }

        public int getProfessorId() {
            return professorId;
        }

        public int getStudentId() {
            return studentId;
        }
    }
}
