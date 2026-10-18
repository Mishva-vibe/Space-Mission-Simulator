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

    alien.displayDetails();

        while (alien.isAlive() && ship.getHealth() > 0) {
            System.out.println("\n1. Attack");
            System.out.println("2. Defend");
            System.out.println("3. Escape");
            System.out.print("Choose your action: ");
int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number.");
                continue;
            }

            if (choice == 1) {
                int damage = 15 + random.nextInt(16);
                alien.takeDamage(damage);
