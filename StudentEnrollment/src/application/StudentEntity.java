package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentEntity {

    private Connection connection;

    public StudentEntity(Connection connection) {
        this.connection = connection;
    }

    public void addStudent(String firstName, String lastName, String dateOfBirth, String email) throws SQLException {
        String sql = "INSERT INTO Student (FirstName, LastName, DateOfBirth, Email) VALUES (?, ?, ?, ?)";
        try (PreparedStatement studentInfo = connection.prepareStatement(sql)) {
        	studentInfo.setString(1, firstName);
        	studentInfo.setString(2, lastName);
        	studentInfo.setDate(3, java.sql.Date.valueOf(dateOfBirth));
            
            if (email == null || email.trim().isEmpty()) {
            	studentInfo.setNull(4, java.sql.Types.VARCHAR);
            } else {
            	studentInfo.setString(4, email);
            }
            
            studentInfo.executeUpdate();
        }
    }
}
