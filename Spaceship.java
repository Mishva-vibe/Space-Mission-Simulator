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
  
