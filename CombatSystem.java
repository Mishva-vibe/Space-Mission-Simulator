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

            ystem.out.println("You dealt " + damage + " damage.");

                if (!alien.isAlive()) {
                    System.out.println("Alien defeated!");
                    player.addScore(50);
                    return true;
                }

                ship.takeDamage(alien.getAttackPower());

                

                System.out.println("The alien attacked your spaceship!");
            } else if (choice == 2) {
                int damage = Math.max(1, alien.getAttackPower() / 3);

            ship.takeDamage(damage);

                System.out.println("Defence reduced the damage to "
                        + damage + ".");
            } else if (choice == 3) {
                System.out.println("You escaped from the alien.");
                ship.consumeFuel(10);
                return false;
            } else {
                System.out.println("Invalid choice.");
            }

            ship.displayStatus();
        }

        if (ship.getHealth() <= 0) {
            System.out.println("Your spaceship has been destroyed!");
        }

        return false;
    }
}
