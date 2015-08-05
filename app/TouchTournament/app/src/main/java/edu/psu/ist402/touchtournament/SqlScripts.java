package edu.psu.ist402.touchtournament;

/**
 * Created by erikbenscoter on 8/4/15.
 */
public class SqlScripts {

    ///////////////////////////////////////////////////////////////////////////
    //                      Member Variables                                 //
    ///////////////////////////////////////////////////////////////////////////

    //create user
    private static String m_UserCreation = "CREATE TABLE User(\n" +
            "UserEmail\tTEXT,\n" +
            "UserPassword\tTEXT\t\tNULL,\n" +
            "UserWin\t\tINT\t\tNULL,\n" +
            "UserLoss\tINT\t\tNULL\n" +
            ");";

    //create a TournamentTable
    private static String m_TournamentTableCreation = "CREATE TABLE Tournament(\n" +
            "TournamentID\t\tROWID,\n" +
            "TournamentName\t\tTEXT\t\tNOT NULL,\n" +
            "TournamentActive\tBOOLEAN,\n" +
            "TournamentLocation\tTEXT\t\tNOT NULL,\n" +
            "Winner\t\t\tINT,\n" +
            "FOREIGN KEY(Winner)\n" +
            "REFERENCES Team(TeamID)\n" +
            ");";

    //create Team Table
    private static String m_TeamTableCreation = "Create Table Team(\n" +
            "TeamID\t\tROWID,\n" +
            "TeamName\tTEXT\t\tNOT NULL,\n" +
            "Win\t\tINT\t\tNOT NULL,\n" +
            "Loss\t\tINT\t\tNOT NULL\n" +
            ");";

    //create match table
    private static String m_MatchTableCreation = "Create Table Match(\n" +
            "MatchID\t\tROWID,\n" +
            "TournamentID\tINT\t\tNOT NULL,\n" +
            "Team1ID\t\tINT\t\tNOT NULL,\n" +
            "Team2ID\t\tINT\t\tNOT NULL,\n" +
            "MatchNumber\tINT\t\tNOT NULL,\n" +
            "FOREIGN KEY(TournamentID)\n" +
            "\tREFERENCES Tournament(TournamentID),\n" +
            "FOREIGN KEY(Team1ID)\n" +
            "\tREFERENCES Team(TeamID),\n" +
            "FOREIGN KEY(Team2ID)\n" +
            "\tREFERENCES Team(Team2ID)\n" +
            ");";

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
    private static String m_CreateAdminTable = "Create Table Client(\n" +
            "ClientID\tROWID,\n" +
            "TournamentID\tI\t\tNOT NULL,\n" +
            "FOREIGN KEY(ClientID)\n" +
            "\tREFERENCES User(UserID),\n" +
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
