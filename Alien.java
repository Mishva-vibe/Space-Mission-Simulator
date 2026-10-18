
public class Alien {
    private String name;
    private int health;
    private int attackPower;

    public Alien(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }
  
 public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttackPower() {
        return attackPower;
    }


    public void takeDamage(int damage) {
        if (damage > 0) {
            health = Math.max(0, health - damage);
        }
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void displayDetails() {
        System.out.println("Alien: " + name);
        System.out.println("Health: " + health);
        System.out.println("Attack Power: " + attackPower);
    }
}
