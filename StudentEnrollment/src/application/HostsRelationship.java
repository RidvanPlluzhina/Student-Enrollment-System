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
    public void addHosts(String roomNr, String building, int subjectId) throws SQLException {
        String sql = "INSERT INTO Hosts (roomNr, building, subjectId) VALUES (?, ?, ?)";
        try (PreparedStatement hostsInfo = connection.prepareStatement(sql)) {
            hostsInfo.setString(1, roomNr);
            hostsInfo.setString(2, building);
            hostsInfo.setInt(3, subjectId);
            hostsInfo.executeUpdate();
        }
    }

    // Deletes a Hosts relationship record from the database based on the room number, building, and subject ID
    public void deleteHosts(String roomNr, String building, int subjectId) throws SQLException {
        String sql = "DELETE FROM Hosts WHERE roomNr = ? AND building = ? AND subjectId = ?";
        try (PreparedStatement hostsInfo = connection.prepareStatement(sql)) {
            hostsInfo.setString(1, roomNr);
            hostsInfo.setString(2, building);
            hostsInfo.setInt(3, subjectId);
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
                        rs.getString("roomNr"),
                        rs.getString("building"),
                        rs.getInt("subjectId")
                );
                hostsList.add(hosts);
            }
        }
        return hostsList;
    }

    // Represents a Hosts relationship record
    public static class Hosts {
        private final String roomNr;
        private final String building;
        private final int subjectId;

        // Initializes the Hosts relationship with specified details
        public Hosts(String roomNr, String building, int subjectId) {
            this.roomNr = roomNr;
            this.building = building;
            this.subjectId = subjectId;
        }

        // Getters
        public String getRoomNr() {
            return roomNr;
        }

        public String getBuilding() {
            return building;
        }

        public int getSubjectId() {
            return subjectId;
        }
    }
}
