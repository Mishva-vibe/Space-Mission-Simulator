import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameManager {
    private Scanner scanner;
    private GameDAO dao;
    private Player player;
    private ExplorationSystem explorationSystem;

    public GameManager() {
        scanner = new Scanner(System.in);
        dao = new GameDAO();
        explorationSystem = new ExplorationSystem(scanner);
    }
 public void startGame() {
        System.out.println("==================================");
        System.out.println("     SPACE MISSION SIMULATOR");
        System.out.println("==================================");

        try {
            loginOrRegister();
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            System.out.println("Check MySQL and your connection settings.");
            return;
        }

        if (player == null) {
            return;
        }

        int choice = 0;

        while (choice != 10) {
            displayMenu();

            try {
                choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 1:
                        player.displayProfile();
                        break;

                    case 2:
                        player.getSpaceship().displayStatus();
                        break;

                    case 3:
                        explorePlanet();
                        break;

                case 4:
                        showMissions();
                        break;

                    case 5:
                        startMission();
                        break;

                    case 6:
                        player.getSpaceship().displayInventory();
                        break;

                    case 7:
                        dao.showMissionHistory(player.getPlayerId());
                        break;

                    case 8:
                        dao.showLeaderboard();
                        break;

                    case 9:
                        saveGame();
                        break;

                    case 10:
                        saveGame();
                        System.out.println("Thank you for playing!");
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            }
        }
    }

    private void loginOrRegister() throws SQLException {
        System.out.println("1. Register new player");
        System.out.println("2. Load existing player");
        System.out.print("Choose an option: ");

        int choice;

        try {
            choice = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice.");
            return;
        }

        if (choice == 1) {
            System.out.print("Enter player name: ");
            String name = scanner.nextLine().trim();

            if (name.isEmpty() || name.length() > 50) {
                System.out.println("Name must contain 1 to 50 characters.");
                return;
            }

            int id = dao.registerPlayer(name);
