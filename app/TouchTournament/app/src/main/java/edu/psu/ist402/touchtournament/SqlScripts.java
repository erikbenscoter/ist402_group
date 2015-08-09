package edu.psu.ist402.touchtournament;

/**
 * Created by erikbenscoter on 8/4/15.
 */
public class SqlScripts {

    ///////////////////////////////////////////////////////////////////////////
    //                      Member Variables                                 //
    ///////////////////////////////////////////////////////////////////////////

    //create user

    private static String m_UserCreation = "CREATE TABLE User (" +
            "UserEmail TEXT UNIQUE NOT NULL, " +
            "UserPassword TEXT, " +
            "UserWin INT, " +
            "UserLoss INT);";


    //create a TournamentTable

    //added BeginDate, EndDate, TournamentType fields

    private static String m_TournamentTableCreation = "CREATE TABLE Tournament (" +
            "TournamentName TEXT NOT NULL, " +
            "TournamentActive BOOLEAN, " +
            "TournamentLocation TEXT NOT NULL, " +
            "Winner INT, " +
            "BeginDate DATE, " +
            "EndDate DATE, " +
            "TournamentType TEXT, " +
            "FOREIGN KEY (Winner) REFERENCES Team (ROWID));";



    //create Team Table

    //changed Win -> Wins, Loss -> Losses
    //added City, State, and ContactEmail



    private static String m_TeamTableCreation = "CREATE TABLE Team (" +
            "TeamName TEXT NOT NULL, " +
            "Wins INT NOT NULL, " +
            "Losses INT NOT NULL, " +
            "City TEXT, " +
            "State TEXT, " +
            "ContactEmail TEXT);";

    //create seeding table

    private static String m_MatchTableCreation = "CREATE TABLE Seeding (" +
            "TournamentID INT NOT NULL, " +
            "TeamID INT NOT NULL, " +
            "Seed INT NOT NULL, " +
            "FOREIGN KEY (TournamentID) REFERENCES Tournament (ROWID), " +
            "FOREIGN KEY (TeamID) REFERENCES Team (ROWID));";


    //create followTeamTableCreation

    private static String m_FollowTeamTableCreation = "CREATE TABLE FollowTeam (" +
            "TeamID INT, " +
            "UserID INT, " +
            "FOREIGN KEY (TeamID) REFERENCES Team (ROWID), " +
            "FOREIGN KEY (UserID) REFERENCES User (ROWID));";

    //create client table
    //If we use this table it needs some help


    private static String m_ClientTableCreation = "CREATE TABLE Client (" +
            "TournamentID INT NOT NULL, " +
            "FOREIGN KEY (ClientID) REFERENCES User (UserID), " +//this line doesn't work....ClientID doesn't exist.
            "FOREIGN KEY (TournamentID) REFERENCES Tournament (TournamentID));";



    //create admin table

    private static String m_CreateAdminTable = "CREATE TABLE Admin (" +
            "AdminEmail TEXT, " +
            "TournamentID INT NOT NULL, " +
            "FOREIGN KEY (AdminEmail) REFERENCES User (UserEmail), " +
            "FOREIGN KEY (TournamentID) REFERENCES Tournament (TournamentID));";

    private static String m_CreateWinnersTable = "CREATE TABLE Winners (" +
            "TournamentID INT, " +
            "TeamID INT, " +
            "SeedingID INT, " +
            "WinnerScore INT, " +
            "LoserScore INT," +
            "FOREIGN KEY (TournamentID) REFERENCES Tournament (ROWID), " +
            "FOREIGN KEY (TeamID) REFERENCES Team (ROWID), " +
            "FOREIGN KEY (SeedingID) REFERENCES Seeding (ROWID));";


    ///////////////////////////////////////////////////////////////////////////
    //                      GETTERS
    ///////////////////////////////////////////////////////////////////////////

    public static String getM_UserCreation() {
        return m_UserCreation;
    }

    public static String getM_TournamentTableCreation() {
        return m_TournamentTableCreation;
    }

    public static String getM_TeamTableCreation() {
        return m_TeamTableCreation;
    }

    public static String getM_MatchTableCreation() {
        return m_MatchTableCreation;
    }

    public static String getM_FollowTeamTableCreation() {
        return m_FollowTeamTableCreation;
    }

    public static String getM_ClientTableCreation() {
        return m_ClientTableCreation;
    }

    public static String getM_CreateAdminTable() {
        return m_CreateAdminTable;
    }
    public static String getM_CreateWinnersTable(){
        return m_CreateWinnersTable;
    }

}
