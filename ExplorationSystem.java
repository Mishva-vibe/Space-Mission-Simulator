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
planets.add(new Planet(
                "Europa", "An icy moon that may contain water.", 6));

        planets.add(new Planet(
                "Titan", "A moon with a dense atmosphere.", 5));

        planets.add(new Planet(
                "Kepler-186f", "A distant exoplanet.", 9));
    }

    public ArrayList<Planet> getPlanets() {
        return new ArrayList<>(planets);
    }

    public void displayPlanets() {
        System.out.println("\n--- AVAILABLE PLANETS ---");
