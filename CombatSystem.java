import java.util.Random;
import java.util.Scanner;

public class CombatSystem {
    private Random random = new Random();
    private Scanner scanner;

    public CombatSystem(Scanner scanner) {
        this.scanner = scanner;
    }

 public boolean startCombat(Player player) {
        Spaceship ship = player.getSpaceship();

        Alien alien = new Alien(
                "Alien-" + (random.nextInt(900) + 100),
                60,
                10 + random.nextInt(16));

        System.out.println("\nAn alien has appeared!");
