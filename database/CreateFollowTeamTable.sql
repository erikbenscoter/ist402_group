CREATE TABLE FollowTeam(
FollowTeamID	INT(6)	AUTO_INCREMENT PRIMARY KEY,
TeamID 			INT(6),
UserID			INT(6),
FOREIGN KEY(TeamID)
	REFERENCES Team(TeamID),
FOREIGN KEY(UserID)
	REFERENCES User(UserID)
);