Create Table Match(
MatchID		ROWID,
TournamentID	INT		NOT NULL,
Team1ID		INT		NOT NULL,
Team2ID		INT		NOT NULL,
MatchNumber	INT		NOT NULL,
FOREIGN KEY(TournamentID)
	REFERENCES Tournament(TournamentID),
FOREIGN KEY(Team1ID)
	REFERENCES Team(TeamID),
FOREIGN KEY(Team2ID)
	REFERENCES Team(Team2ID)
);
