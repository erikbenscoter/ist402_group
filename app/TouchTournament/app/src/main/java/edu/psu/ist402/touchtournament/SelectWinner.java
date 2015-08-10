package edu.psu.ist402.touchtournament;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;


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
    public RadioButton m_team1;
    public RadioButton m_team2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_winner);

        //initialize the varibales
        GetValues();

        //set the radiobuttons
        SetRadioButtonString();

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

        //get the radio buttons
        m_team1 = (RadioButton) findViewById(R.id.RBteam1);
        m_team2 = (RadioButton) findViewById(R.id.RBteam2);
    }

    public void GetIntentVariables(){

        m_teamID1 = getIntent().getIntExtra(m_constTeam1ID,-10);
        m_teamID2 = getIntent().getIntExtra(m_constTeam2ID,-10);
        m_seedingID = getIntent().getIntExtra(m_constSeedingID, -10);
        m_tournamentID = getIntent().getIntExtra(TournamentPairings.const_TournamentID, -10);

    }

    public String GetTeamName( int p_teamID ){

        //create query
        String myQuery = "SELECT Team.TeamName FROM Team WHERE Team.ROWID = "+Integer.toString(p_teamID);

        //execute query
        Cursor myCursor = DatabaseCommunicator.CreateFetchQuery(myQuery);
        myCursor.moveToFirst();
        return myCursor.getString(0);
    }
    public void SetRadioButtonString( ){

        String team1Name = GetTeamName(m_teamID1);
        String team2Name = GetTeamName(m_teamID2);

        m_team1.setText(team1Name);
        m_team2.setText(team2Name);
    }


    public void SubmitPickedWinnerValues(View v){

        //figure out what is selected
        int winner;
        if(m_team1.isChecked()){
            winner = m_teamID1;
        }else {
            winner = m_teamID2;
        }


        //make query
        String myQuery = "INSERT INTO Winners(TournamentID, TeamID, SeedingID, WinnerScore,LoserScore)" +
                            "VALUES('"+m_tournamentID+"','"+winner+"','"+m_seedingID+"','"+m_winerScore+"','"+m_loserScore+"')";

        //execute query
        DatabaseCommunicator.CreateInsertQuery(myQuery);

        finish();
    }
}
