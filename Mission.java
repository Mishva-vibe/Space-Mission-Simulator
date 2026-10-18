public class Mission {
    private int missionId;
    private String missionName;
    private String planetName;
    private int rewardPoints;
    private String description;

    public Mission(int missionId, String missionName,
                   String planetName, int rewardPoints,
                   String description) {
        this.missionId = missionId;
        this.missionName = missionName;
        this.planetName = planetName;
        this.rewardPoints = rewardPoints;
        this.description = description;
    }
