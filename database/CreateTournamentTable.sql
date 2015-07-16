CREATE TABLE Tournament(
TournamentID		INT(6)			AUTO_INCREMENT PRIMARY KEY,
TournamentName		VARCHAR(60)		NOT NULL,
TournamentActive	BOOLEAN,
TournamentLocation	VARCHAR(72)		NOT NULL,
Winner				INT(6),
FOREIGN KEY(Winner)
	REFERENCES Team(TeamID)
);