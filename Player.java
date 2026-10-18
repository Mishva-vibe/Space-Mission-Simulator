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
    public Player(int playerId, String playerName, int score,
                  Spaceship spaceship) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.score = score;
        this.spaceship = spaceship;
    }

    public int getPlayerId() {
        return playerId;
    }
 public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }

    public Spaceship getSpaceship() {
        return spaceship;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
