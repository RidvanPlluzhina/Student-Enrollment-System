package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClassroomEntity {

    private Connection connection;

    public ClassroomEntity(Connection connection) {
        this.connection = connection;
    }

    // Adds a new classroom record to the database
    public void addClassroom(String roomNr, String building, int capacity) throws SQLException {
        String sql = "INSERT INTO Classroom (roomNr, building, capacity) VALUES (?, ?, ?)";
        try (PreparedStatement classroomInfo = connection.prepareStatement(sql)) {
            classroomInfo.setString(1, roomNr);
            classroomInfo.setString(2, building);
            classroomInfo.setInt(3, capacity);
            classroomInfo.executeUpdate();
        }
    }

    // Deletes a classroom record from the database based on the room number and building
    public void deleteClassroom(String roomNr, String building) throws SQLException {
        String sql = "DELETE FROM Classroom WHERE roomNr = ? AND building = ?";
        try (PreparedStatement classroomInfo = connection.prepareStatement(sql)) {
            classroomInfo.setString(1, roomNr);
            classroomInfo.setString(2, building);
            classroomInfo.executeUpdate();
        }
    }

    public List<Classroom> getAllClassrooms() throws SQLException {
        List<Classroom> classrooms = new ArrayList<>();
        String sql = "SELECT * FROM Classroom";
        try (PreparedStatement classroomInfo = connection.prepareStatement(sql);
             ResultSet rs = classroomInfo.executeQuery()) {
            while (rs.next()) {
                Classroom classroom = new Classroom(
                        rs.getString("roomNr"),
                        rs.getString("building"),
                        rs.getInt("capacity")
                );
                classrooms.add(classroom);
            }
        }
        return classrooms;
    }

    public static class Classroom {
        private final String roomNr;
        private final String building;
        private final int capacity;

        public Classroom(String roomNr, String building, int capacity) {
            this.roomNr = roomNr;
            this.building = building;
            this.capacity = capacity;
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
