Create Table Admin(
AdminID			INT(6)		AUTO_INCREMENT	PRIMARY KEY,
UserID			INT(6)		NOT NULL,
TournamentID	INT(6)		NOT NULL,
FOREIGN KEY(UserID)
	REFERENCES User(UserID),
FOREIGN KEY(TournamentID)
	REFERENCES Tournament(TournamentID)
);