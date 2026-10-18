public class Player {
    private int playerId;
    private String playerName;
    private int score;
    private Spaceship spaceship;

    public Player(int playerId, String playerName) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.score = 0;
        this.spaceship = new Spaceship("Explorer-1");
    }
    
