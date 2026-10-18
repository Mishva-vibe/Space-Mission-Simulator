import java.util.ArrayList;

public class Spaceship {
    private String spaceshipName;
    private int fuel;
    private int oxygen;
    private int health;
    private ArrayList<Resource> inventory;

    public Spaceship(String spaceshipName) {
      this(spaceshipName, 100,100, 100);
    }

     public Spaceship(String spaceshipName, int fuel,
                     int oxygen, int health) {
        this.spaceshipName = spaceshipName;
        this.fuel = Math.max(0, Math.min(100, fuel));
        this.oxygen = Math.max(0, Math.min(100, oxygen));
        this.health = Math.max(0, Math.min(100, health));
        inventory = new ArrayList<>();
    }
