CREATE TABLE Tournament(
TournamentID		ROWID,
TournamentName		TEXT		NOT NULL,
TournamentActive	BOOLEAN,
TournamentLocation	TEXT		NOT NULL,
Winner			INT,
FOREIGN KEY(Winner)
REFERENCES Team(TeamID)
);
