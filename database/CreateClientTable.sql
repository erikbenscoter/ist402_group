Create Table Client(
ClientID	ROWID,
TournamentID	I		NOT NULL,
FOREIGN KEY(ClientID)
	REFERENCES User(UserID),
FOREIGN KEY(TournamentID)
	REFERENCES Tournament(TournamentID)
);
