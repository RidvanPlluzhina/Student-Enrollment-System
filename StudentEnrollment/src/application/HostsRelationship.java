package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class HostsRelationship {

    private Connection connection;

    // Initializes the HostsRelationship with a database connection
    public HostsRelationship(Connection connection) {
        this.connection = connection;
    }
    
    // Adds a new Hosts relationship record to the database
    public void addHosts(int classroomId, int subjectId) throws SQLException {
        String sql = "INSERT INTO Hosts (classroomid, subjectid) VALUES (?, ?)";
        try (PreparedStatement hostsInfo = connection.prepareStatement(sql)) {
            hostsInfo.setInt(1, classroomId);
            hostsInfo.setInt(2, subjectId);
            hostsInfo.executeUpdate();
        }
    }
    
    // Deletes a Hosts relationship record from the database based on the classroom ID and subject ID
    public void deleteHosts(int classroomId, int subjectId) throws SQLException {
        String sql = "DELETE FROM Hosts WHERE classroomid = ? AND subjectid = ?";
        try (PreparedStatement hostsInfo = connection.prepareStatement(sql)) {
            hostsInfo.setInt(1, classroomId);
            hostsInfo.setInt(2, subjectId);
            hostsInfo.executeUpdate();
        }
    }
    
    // Retrieves all Hosts relationship records from the database
    public List<Hosts> getAllHosts() throws SQLException {
        List<Hosts> hostsList = new ArrayList<>();
        String sql = "SELECT * FROM Hosts";
        try (PreparedStatement hostsInfo = connection.prepareStatement(sql);
             ResultSet rs = hostsInfo.executeQuery()) {
            while (rs.next()) {
                Hosts hosts = new Hosts(
                        rs.getInt("classroomid"),
                        rs.getInt("subjectid")
                );
                hostsList.add(hosts);
            }
        }
        return hostsList;
    }

    // Represents a Hosts relationship record
    public static class Hosts {
        private final int classroomId;
        private final int subjectId;

        // Initializes the Hosts relationship with specified details
        public Hosts(int classroomId, int subjectId) {
            this.classroomId = classroomId;
            this.subjectId = subjectId;
        }
        
        // Getters
        public int getClassroomId() {
            return classroomId;
        }

        public int getSubjectId() {
            return subjectId;
        }
    }
}
