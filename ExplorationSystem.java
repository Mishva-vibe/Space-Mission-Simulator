import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ExplorationSystem {
    private ArrayList<Planet> planets;
    private Random random;
    private CombatSystem combatSystem;

    public ExplorationSystem(Scanner scanner) {
        planets = new ArrayList<>();
        random = new Random();
        combatSystem = new CombatSystem(scanner);

        planets.add(new Planet(
                "Mars", "The Red Planet with rocky terrain.", 3));
