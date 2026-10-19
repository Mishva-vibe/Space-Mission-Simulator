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

    public int getMissionId() {
        return missionId;
    }

    public String getMissionName() {
        return missionName;
    }

    public String getPlanetName() {
        return planetName;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    public String getDescription() {
        return description;
    }

    public void displayMission() {
        System.out.println("Mission ID: " + missionId);
        System.out.println("Mission: " + missionName);
        System.out.println("Planet: " + planetName);
        System.out.println("Description: " + description);
        System.out.println("Reward: " + rewardPoints);
    }
}
