CREATE TABLE FollowTeam(
FollowTeamID	ROWID,
TeamID 		INT,
UserID		INT,
FOREIGN KEY(TeamID)
	REFERENCES Team(TeamID),
FOREIGN KEY(UserID)
	REFERENCES User(UserID)
);
