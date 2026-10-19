CREATE DATABASE IF NOT EXISTS space_mission_db;
USE space_mission_db;

CREATE TABLE IF NOT EXISTS players (
    player_id INT PRIMARY KEY AUTO_INCREMENT,
    player_name VARCHAR(50) NOT NULL,
    score INT NOT NULL DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS spaceships (
    player_id INT PRIMARY KEY,
    spaceship_name VARCHAR(50) NOT NULL,
    fuel INT NOT NULL DEFAULT 100,
    oxygen INT NOT NULL DEFAULT 100,
    health INT NOT NULL DEFAULT 100,
    FOREIGN KEY (player_id)
        REFERENCES players(player_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS missions (
    mission_id INT PRIMARY KEY AUTO_INCREMENT,
    mission_name VARCHAR(100) NOT NULL UNIQUE,
    planet_name VARCHAR(50) NOT NULL,
    reward_points INT NOT NULL,
    description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS player_missions (
    progress_id INT PRIMARY KEY AUTO_INCREMENT,
    player_id INT NOT NULL,
    mission_id INT NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'NOT_STARTED',
    completed_at TIMESTAMP NULL,
    FOREIGN KEY (player_id)
        REFERENCES players(player_id) ON DELETE CASCADE,
    FOREIGN KEY (mission_id)
        REFERENCES missions(mission_id),
    UNIQUE (player_id, mission_id)
);

INSERT IGNORE INTO missions
(mission_name, planet_name, reward_points, description)
VALUES
('Explore the Red Planet', 'Mars', 100,
 'Explore the surface of Mars'),
('Collect Water Ice', 'Europa', 150,
 'Collect ice from Europa'),
('Discover Energy Crystals', 'Kepler-186f', 200,
 'Discover energy crystals');

SELECT * FROM players;
SELECT * FROM spaceships;
SELECT * FROM missions;
SELECT * FROM player_missions;
