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
