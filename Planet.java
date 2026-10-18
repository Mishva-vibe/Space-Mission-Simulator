public class Planet {
    private String name;
    private String description;
    private int difficulty;

public Planet(String name , String description, int difficulty){
    this.name=name;
    this.description=description;
    this.difficulty=difficulty;
}
    public String getName(){
        return name;
    }

public String description(){
    return description;
}

public int difficulty(){
    return difficulty;
}

    public void displayDetails() {
        System.out.println("\nPlanet: " + name);
        System.out.println("Description: " + description);
        System.out.println("Difficulty: " + difficulty + "/10");
    }
}
