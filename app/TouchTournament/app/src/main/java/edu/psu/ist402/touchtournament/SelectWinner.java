package edu.psu.ist402.touchtournament;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class SelectWinner extends ActionBarActivity {

    public static final String m_constTeam1ID = "Team1ID";
    public static final String m_constTeam2ID = "Team2ID";
    public static final String m_constSeedingID = "SeedingID";
    public int m_tournamentID;
    public int m_teamID1;
    public int m_teamID2;
    public int m_seedingID;
    public int m_winerScore;
    public int m_loserScore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_winner);

        GetValues();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select_winner, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void GetValues(){

        //get the intent variables
        GetIntentVariables();
    }

    public void GetIntentVariables(){

        m_teamID1 = getIntent().getIntExtra(m_constTeam1ID,-10);
        m_teamID2 = getIntent().getIntExtra(m_constTeam2ID,-10);
        m_seedingID = getIntent().getIntExtra(m_constSeedingID, -10);
        m_tournamentID = getIntent().getIntExtra(TournamentPairings.const_TournamentID, -10);

    }

    public void SubmitValues(){

        //make query
        String myQuery = "INSERT INTO Winners(TournamentID, TeamID, SeedingID, WinnerScore,LoserScore)" +
                            "VALUES('"+m_tournamentID+"','"+m_teamID1+"','"+m_seedingID+"','"+m_winerScore+"','"+m_loserScore+"')";

        //execute query
        DatabaseCommunicator.CreateInsertQuery(myQuery);
    }
}
