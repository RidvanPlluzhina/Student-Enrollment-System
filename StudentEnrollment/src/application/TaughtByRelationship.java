package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class TaughtByRelationship {

    private Connection connection;

    public TaughtByRelationship(Connection connection) {
        this.connection = connection;
    }

    public void addTaughtBy(int subjectId, int professorId) throws SQLException {
        String sql = "INSERT INTO TaughtBy (subjectid, professorid) VALUES (?, ?)";
        try (PreparedStatement taughtByInfo = connection.prepareStatement(sql)) {
            taughtByInfo.setInt(1, subjectId);
            taughtByInfo.setInt(2, professorId);
            taughtByInfo.executeUpdate();
        }
    }

    public void deleteTaughtBy(int subjectId, int professorId) throws SQLException {
        String sql = "DELETE FROM TaughtBy WHERE subjectid = ? AND professorid = ?";
        try (PreparedStatement taughtByInfo = connection.prepareStatement(sql)) {
            taughtByInfo.setInt(1, subjectId);
            taughtByInfo.setInt(2, professorId);
            taughtByInfo.executeUpdate();
        }
    }

    public List<TaughtBy> getAllTaughtBy() throws SQLException {
        List<TaughtBy> taughtByList = new ArrayList<>();
        String sql = "SELECT * FROM TaughtBy";
        try (PreparedStatement taughtByInfo = connection.prepareStatement(sql);
             ResultSet rs = taughtByInfo.executeQuery()) {
            while (rs.next()) {
                TaughtBy taughtBy = new TaughtBy(
                        rs.getInt("subjectid"),
                        rs.getInt("professorid")
                );
                taughtByList.add(taughtBy);
            }
        }
        return taughtByList;
    }

    public static class TaughtBy {
        private final int subjectId;
        private final int professorId;

        public TaughtBy(int subjectId, int professorId) {
            this.subjectId = subjectId;
            this.professorId = professorId;
        }

        public int getSubjectId() {
            return subjectId;
        }

        public int getProfessorId() {
            return professorId;
        }
    }
}
