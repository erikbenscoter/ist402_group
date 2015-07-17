Create Table Client(
ClientID			INT(6)		AUTO_INCREMENT	PRIMARY KEY,
ClientID			INT(6)		NOT NULL,
TournamentID		INT(6)		NOT NULL,
FOREIGN KEY(ClientID)
	REFERENCES User(UserID),
FOREIGN KEY(TournamentID)
	REFERENCES Tournament(TournamentID)
);