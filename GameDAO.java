import java.sql.*;
import java.util.ArrayList;

public class GameDAO {

    public int registerPlayer(String name) throws SQLException {
        String query = "INSERT INTO players (player_name) VALUES (?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     query, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, name);
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int playerId = rs.getInt(1);

                    String shipQuery =
                            "INSERT INTO spaceships (player_id, spaceship_name) "
                            + "VALUES (?, ?)";

                    try (PreparedStatement shipPS =
                                 con.prepareStatement(shipQuery)) {
                        shipPS.setInt(1, playerId);
                        shipPS.setString(2, "Explorer-1");
                        shipPS.executeUpdate();
                    }

                    return playerId;
                }
            }
        }

        return -1;
    }

    public Player loadPlayer(int playerId) throws SQLException {
        String query =
                "SELECT p.player_id, p.player_name, p.score, "
                + "s.spaceship_name, s.fuel, s.oxygen, s.health "
                + "FROM players p JOIN spaceships s "
                + "ON p.player_id = s.player_id "
                + "WHERE p.player_id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, playerId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Spaceship ship = new Spaceship(
                            rs.getString("spaceship_name"),
                            rs.getInt("fuel"),
                            rs.getInt("oxygen"),
                            rs.getInt("health"));

                    return new Player(
                            rs.getInt("player_id"),
                            rs.getString("player_name"),
                            rs.getInt("score"),
                            ship);
                }
            }
        }

        return null;
    }

    public void saveGame(Player player) throws SQLException {
        String shipQuery =
                "UPDATE spaceships SET fuel = ?, oxygen = ?, health = ?, "
                + "spaceship_name = ? WHERE player_id = ?";

        String scoreQuery =
                "UPDATE players SET score = ? WHERE player_id = ?";

        try (Connection con = DatabaseConnection.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(shipQuery)) {
                Spaceship ship = player.getSpaceship();

                ps.setInt(1, ship.getFuel());
                ps.setInt(2, ship.getOxygen());
                ps.setInt(3, ship.getHealth());
                ps.setString(4, ship.getSpaceshipName());
                ps.setInt(5, player.getPlayerId());

                ps.executeUpdate();
            }

            try (PreparedStatement ps = con.prepareStatement(scoreQuery)) {
                ps.setInt(1, player.getScore());
                ps.setInt(2, player.getPlayerId());
                ps.executeUpdate();
            }
        }
    }

    public ArrayList<Mission> getMissions() throws SQLException {
        ArrayList<Mission> missions = new ArrayList<>();

        String query = "SELECT * FROM missions ORDER BY mission_id";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                missions.add(new Mission(
                        rs.getInt("mission_id"),
                        rs.getString("mission_name"),
                        rs.getString("planet_name"),
                        rs.getInt("reward_points"),
                        rs.getString("description")));
            }
        }

        return missions;
    }

    public void updateMissionStatus(int playerId, int missionId,
                                    String status) throws SQLException {
        String query =
                "INSERT INTO player_missions "
                + "(player_id, mission_id, status, completed_at) "
                + "VALUES (?, ?, ?, CASE WHEN ? = 'COMPLETED' "
                + "THEN CURRENT_TIMESTAMP ELSE NULL END) "
                + "ON DUPLICATE KEY UPDATE status = VALUES(status), "
                + "completed_at = CASE WHEN VALUES(status) = 'COMPLETED' "
                + "THEN CURRENT_TIMESTAMP ELSE completed_at END";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, playerId);
            ps.setInt(2, missionId);
            ps.setString(3, status);
            ps.setString(4, status);
            ps.executeUpdate();
        }
    }

    public void showMissionHistory(int playerId) throws SQLException {
        String query =
                "SELECT m.mission_name, m.planet_name, pm.status "
                + "FROM player_missions pm "
                + "JOIN missions m ON pm.mission_id = m.mission_id "
                + "WHERE pm.player_id = ? ORDER BY pm.progress_id";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, playerId);

            try (ResultSet rs = ps.executeQuery()) {
                System.out.println("\n--- MISSION HISTORY ---");

                boolean found = false;

                while (rs.next()) {
                    found = true;
                    System.out.println(
                            rs.getString("mission_name") + " | "
                            + rs.getString("planet_name") + " | "
                            + rs.getString("status"));
                }

                if (!found) {
                    System.out.println("No mission history yet.");
                }
            }
        }
    }

    public void showLeaderboard() throws SQLException {
        String query =
                "SELECT player_name, score FROM players "
                + "ORDER BY score DESC, player_name ASC LIMIT 10";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n--- LEADERBOARD ---");

            int rank = 1;

            while (rs.next()) {
                System.out.println(rank++ + ". "
                        + rs.getString("player_name")
                        + " - " + rs.getInt("score") + " points");
            }
        }
    }
}
