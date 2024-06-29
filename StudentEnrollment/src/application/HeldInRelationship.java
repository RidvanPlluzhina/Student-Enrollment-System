package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class HeldInRelationship {

    private Connection connection;
    
    // Initializes the HeldInRelationship with a database connection
    public HeldInRelationship(Connection connection) {
        this.connection = connection;
    }

    // Adds a new HeldIn relationship record to the database
    public void addHeldIn(int examId, int classroomId) throws SQLException {
        String sql = "INSERT INTO HeldIn (examid, classroomid) VALUES (?, ?)";
        try (PreparedStatement heldInInfo = connection.prepareStatement(sql)) {
            heldInInfo.setInt(1, examId);
            heldInInfo.setInt(2, classroomId);
            heldInInfo.executeUpdate();
        }
    }

    // Deletes a HeldIn relationship record from the database based on the exam ID and classroom ID
    public void deleteHeldIn(int examId, int classroomId) throws SQLException {
        String sql = "DELETE FROM HeldIn WHERE examid = ? AND classroomid = ?";
        try (PreparedStatement heldInInfo = connection.prepareStatement(sql)) {
            heldInInfo.setInt(1, examId);
            heldInInfo.setInt(2, classroomId);
            heldInInfo.executeUpdate();
        }
    }
    
    // Retrieves all HeldIn relationship records from the database
    public List<HeldIn> getAllHeldIn() throws SQLException {
        List<HeldIn> heldInList = new ArrayList<>();
        String sql = "SELECT * FROM HeldIn";
        try (PreparedStatement heldInInfo = connection.prepareStatement(sql);
             ResultSet rs = heldInInfo.executeQuery()) {
            while (rs.next()) {
                HeldIn heldIn = new HeldIn(
                        rs.getInt("examid"),
                        rs.getInt("classroomid")
                );
                heldInList.add(heldIn);
            }
        }
        return heldInList;
    }
    
    // Represents a HeldIn relationship record
    public static class HeldIn {
        private final int examId;
        private final int classroomId;

        // Initializes the HeldIn relationship with specified details
        public HeldIn(int examId, int classroomId) {
            this.examId = examId;
            this.classroomId = classroomId;
        }
        // Getters
        public int getExamId() {
            return examId;
        }

        public int getClassroomId() {
            return classroomId;
        }
    }
}
