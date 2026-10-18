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
