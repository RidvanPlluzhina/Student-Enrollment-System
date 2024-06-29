package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClassroomEntity {

    private Connection connection;

    // Initializes the ClassroomEntity with a database connection
    public ClassroomEntity(Connection connection) {
        this.connection = connection;
    }
    
    // Adds a new classroom record to the database
    public void addClassroom(int classroomID, String roomNr, String building, int capacity) throws SQLException {
        String sql = "INSERT INTO Classroom (classroomID, roomNr, building, capacity) VALUES (?, ?, ?, ?)";
        try (PreparedStatement classroomInfo = connection.prepareStatement(sql)) {
            classroomInfo.setInt(1, classroomID);
            classroomInfo.setString(2, roomNr);
            classroomInfo.setString(3, building);
            classroomInfo.setInt(4, capacity);
            classroomInfo.executeUpdate();
        }
    }
    // Deletes a classroom record from the database based on the classroom ID
    public void deleteClassroom(int classroomID) throws SQLException {
        String sql = "DELETE FROM Classroom WHERE classroomID = ?";
        try (PreparedStatement classroomInfo = connection.prepareStatement(sql)) {
            classroomInfo.setInt(1, classroomID);
            classroomInfo.executeUpdate();
        }
    }
    // Retrieves all classroom records from the database
    public List<Classroom> getAllClassrooms() throws SQLException {
        List<Classroom> classrooms = new ArrayList<>();
        String sql = "SELECT * FROM Classroom";
        try (PreparedStatement classroomInfo = connection.prepareStatement(sql);
             ResultSet rs = classroomInfo.executeQuery()) {
            while (rs.next()) {
                Classroom classroom = new Classroom(
                        rs.getInt("classroomID"),
                        rs.getString("roomNr"),
                        rs.getString("building"),
                        rs.getInt("capacity")
                );
                classrooms.add(classroom);
            }
        }
        return classrooms;
    }
    
    // Represents a classroom record
    public static class Classroom {
        private final int classroomID;
        private final String roomNr;
        private final String building;
        private final int capacity;

        // Initializes the Classroom with specified details
        public Classroom(int classroomID, String roomNr, String building, int capacity) {
            this.classroomID = classroomID;
            this.roomNr = roomNr;
            this.building = building;
            this.capacity = capacity;
        }
        // Getters 
        public int getClassroomID() {
            return classroomID;
        }

        public String getRoomNr() {
            return roomNr;
        }

        public String getBuilding() {
            return building;
        }

        public int getCapacity() {
            return capacity;
        }
    }
}
