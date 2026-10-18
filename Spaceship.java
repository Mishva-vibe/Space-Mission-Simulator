import java.util.ArrayList;

public class Spaceship {
    private String spaceshipName;
    private int fuel;
    private int oxygen;
    private int health;
    private ArrayList<Resource> inventory;

    public Spaceship(String spaceshipName) {
        this(spaceshipName, 100, 100, 100);
    }

    public Spaceship(String spaceshipName, int fuel,
                     int oxygen, int health) {
        this.spaceshipName = spaceshipName;
        this.fuel = Math.max(0, Math.min(100, fuel));
        this.oxygen = Math.max(0, Math.min(100, oxygen));
        this.health = Math.max(0, Math.min(100, health));
        inventory = new ArrayList<>();
    }

    public String getSpaceshipName() {
        return spaceshipName;
    }

    public int getFuel() {
        return fuel;
    }

    public int getOxygen() {
        return oxygen;
    }

    public int getHealth() {
        return health;
    }

    public void displayStatus() {
        System.out.println("\n--- SPACESHIP STATUS ---");
        System.out.println("Name: " + spaceshipName);
        System.out.println("Fuel: " + fuel);
        System.out.println("Oxygen: " + oxygen);
        System.out.println("Health: " + health);
    }

    public boolean consumeFuel(int amount) {
        if (amount <= 0 || fuel < amount) {
            System.out.println("Not enough fuel!");
            return false;
        }

        fuel -= amount;
        return true;
    }

    public boolean consumeOxygen(int amount) {
        if (amount <= 0 || oxygen < amount) {
            System.out.println("Not enough oxygen!");
            return false;
        }

        oxygen -= amount;
        return true;
    }

    public void takeDamage(int damage) {
        if (damage > 0) {
            health = Math.max(0, health - damage);
        }

        if (health == 0) {
            System.out.println("Spaceship critically damaged!");
        }
    }

    public void repair(int amount) {
        if (amount > 0) {
            health = Math.min(100, health + amount);
        }
    }

    public void refuel(int amount) {
        if (amount > 0) {
            fuel = Math.min(100, fuel + amount);
        }
    }

    public void replenishOxygen(int amount) {
        if (amount > 0) {
            oxygen = Math.min(100, oxygen + amount);
        }
    }

    public void collectResource(String resourceName) {
        for (Resource resource : inventory) {
            if (resource.getResourceName().equalsIgnoreCase(resourceName)) {
                resource.addQuantity(1);
                System.out.println("Resource quantity increased!");
                return;
            }
        }

            inventory.add(new Resource(resourceName, 1));
        System.out.println(resourceName + " collected!");
    }

    public void displayInventory() {
        System.out.println("\n--- INVENTORY ---");

        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        for (Resource resource : inventory) {
            System.out.println(resource);
        }
    }

    public boolean useResource(String resourceName) {
        for (int i = 0; i < inventory.size(); i++) {
            Resource resource = inventory.get(i);

            if (resource.getResourceName().equalsIgnoreCase(resourceName)
                    && resource.getQuantity() > 0) {
                inventory.remove(i);
                if (resource.getQuantity() > 1) {
                    resource = new Resource(resource.getResourceName(),
                            resource.getQuantity() - 1);
                    inventory.add(i, resource);
                }
                return true;
            }
        }

        return false;
    }

    public boolean isOperational() {
        return fuel > 0 && oxygen > 0 && health > 0;
    }
}
