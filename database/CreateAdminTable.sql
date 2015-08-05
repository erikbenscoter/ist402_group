Create Table Admin(
AdminID		ROWID,
UserID		INT		NOT NULL,
TournamentID	INT		NOT NULL,
FOREIGN KEY(UserID)
	REFERENCES User(UserID),
FOREIGN KEY(TournamentID)
	REFERENCES Tournament(TournamentID)
);
