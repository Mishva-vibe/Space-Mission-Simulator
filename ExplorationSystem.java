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
for (int i = 0; i < planets.size(); i++) {
            System.out.println((i + 1) + ". "
                    + planets.get(i).getName());
        }
    }

    public boolean explore(Player player, String targetPlanet) {
        Spaceship ship = player.getSpaceship();
        Planet selectedPlanet = null;

        for (Planet planet : planets) {
            if (planet.getName().equalsIgnoreCase(targetPlanet)) {
                selectedPlanet = planet;
                break;
            }
        }


    if (selectedPlanet == null) {
            System.out.println("Planet not found.");
            return false;
        }

        int fuelCost = 10 + selectedPlanet.getDifficulty();
        int oxygenCost = 10 + selectedPlanet.getDifficulty();

        if (!ship.isOperational()) {
            System.out.println("Your spaceship is not operational.");
            return false;
        }

        if (ship.getFuel() < fuelCost
                || ship.getOxygen() < oxygenCost) {
            System.out.println("Insufficient fuel or oxygen.");
            return false;
        }

        ship.consumeFuel(fuelCost);
        ship.consumeOxygen(oxygenCost);

        selectedPlanet.displayDetails();
        System.out.println("Exploring " + selectedPlanet.getName() + "...");

        int event = random.nextInt(100);

        if (event < 50) {
            String[] resources = {
                "Minerals", "Energy Crystals", "Water Ice"
            };

            String resource =
                    resources[random.nextInt(resources.length)];

            ship.collectResource(resource);
            player.addScore(20);

            System.out.println("Exploration successful!");
            return true;

        } else if (event < 80) {
            System.out.println("An alien encounter begins!");
            combatSystem.startCombat(player);
            return true;

        } else {
            int damage = 5 + random.nextInt(16);

            ship.takeDamage(damage);

            System.out.println("Asteroid collision! Damage: " + damage);
            return true;
        }
    }
}
