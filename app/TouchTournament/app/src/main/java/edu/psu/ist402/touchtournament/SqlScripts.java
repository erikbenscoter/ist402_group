package edu.psu.ist402.touchtournament;

/**
 * Created by erikbenscoter on 8/4/15.
 */
public class SqlScripts {

    ///////////////////////////////////////////////////////////////////////////
    //                      Member Variables                                 //
    ///////////////////////////////////////////////////////////////////////////

    //create user

    private static String m_UserCreation = "CREATE TABLE User (UserEmail TEXT UNIQUE NOT NULL," +
            " UserPassword TEXT, " +
            "UserWin INT, " +
            "UserLoss INT);";

    /*private static String m_UserCreation = "CREATE TABLE User(\n" +
            "UserEmail\tTEXT,\n" +
            "UserPassword\tTEXT\t\tNULL,\n" +
            "UserWin\t\tINT\t\tNULL,\n" +
            "UserLoss\tINT\t\tNULL\n" +
            ");";*/

    //create a TournamentTable

    //added BeginDate, EndDate, TournamentType fields

    private static String m_TournamentTableCreation = "CREATE TABLE Tournament (TournamentID ROWID," +
            " TournamentName TEXT NOT NULL, " +
            "TournamentActive BOOLEAN, " +
            "TournamentLocation TEXT NOT NULL," +
            " Winner INT, " +
            "BeginDate DATE, " +
            "EndDate DATE, " +
            "TournamentType TEXT," +
            " FOREIGN KEY (Winner) REFERENCES Team (TeamID));";


   /* private static String m_TournamentTableCreation = "CREATE TABLE Tournament(\n" +
            "TournamentID\t\tROWID,\n" +
            "TournamentName\t\tTEXT\t\tNOT NULL,\n" +
            "TournamentActive\tBOOLEAN,\n" +
            "TournamentLocation\tTEXT\t\tNOT NULL,\n" +
            "Winner\t\t\tINT,\n" +
            "FOREIGN KEY(Winner)\n" +
            "REFERENCES Team(TeamID)\n" +
            ");";*/



    //create Team Table

    //changed Win -> Wins, Loss -> Losses
    //added City, State, and ContactEmail



    private static String m_TeamTableCreation = "CREATE TABLE Team (" +
            "TeamID ROWID, " +
            "TeamName TEXT NOT NULL, " +
            "Wins INT NOT NULL, " +
            "Losses INT NOT NULL, " +
            "City TEXT, " +
            "State TEXT, " +
            "ContactEmail TEXT);";

/*    private static String m_TeamTableCreation = "Create Table Team(\n" +
            "TeamID\t\tROWID,\n" +
            "TeamName\tTEXT\t\tNOT NULL,\n" +
            "Win\t\tINT\t\tNOT NULL,\n" +
            "Loss\t\tINT\t\tNOT NULL\n" +
            ");";*/

    //create seeding table

    private static String m_MatchTableCreation = "CREATE TABLE Seeding (SeedingID ROWID," +
            " TournamentID INT NOT NULL," +
            " TeamID INT NOT NULL, " +
            "Seed INT NOT NULL, " +
            "FOREIGN KEY (TournamentID) REFERENCES Tournament (TournamentID), " +
            "FOREIGN KEY (TeamID) REFERENCES Team (TeamID));";


    //create followTeamTableCreation
    private static String m_FollowTeamTableCreation = "CREATE TABLE FollowTeam(\n" +
            "FollowTeamID\tROWID,\n" +
            "TeamID \t\tINT,\n" +
            "UserID\t\tINT,\n" +
            "FOREIGN KEY(TeamID)\n" +
            "\tREFERENCES Team(TeamID),\n" +
            "FOREIGN KEY(UserID)\n" +
            "\tREFERENCES User(UserID)\n" +
            ");";

    //create client table
    private static String m_ClientTableCreation = "Create Table Client(\n" +
            "ClientID\tROWID,\n" +
            "TournamentID\tI\t\tNOT NULL,\n" +
            "FOREIGN KEY(ClientID)\n" +
            "\tREFERENCES User(UserID),\n" +
            "FOREIGN KEY(TournamentID)\n" +
            "\tREFERENCES Tournament(TournamentID)\n" +
            ");";

    //create admin table

    private static String m_CreateAdminTable = "Create Table Admin(\n" +
            "AdminID\tROWID,\n" +
            "AdminEmail\tTEXT,\n"+
            "TournamentID\tI\t\tNOT NULL,\n" +
            "FOREIGN KEY(AdminEmail)\n" +
            "\tREFERENCES User(UserEmail),\n" +
            "FOREIGN KEY(TournamentID)\n" +
            "\tREFERENCES Tournament(TournamentID)\n" +
            ");";

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
}
