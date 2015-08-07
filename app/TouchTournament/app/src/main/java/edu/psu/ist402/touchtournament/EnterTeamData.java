package edu.psu.ist402.touchtournament;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;


public class EnterTeamData extends ActionBarActivity {

    ///////////////////////////////////////////////////////////////////////////
    //						Member Variables
    ///////////////////////////////////////////////////////////////////////////
    private String m_teamName = "";
    private String m_teamWins = "";
    private String m_teamLosses = "";
    private String m_teamSeed = "";
    private String m_teamCity = "";
    private String m_teamState = "";
    private String m_teamEmail = "";
    private int m_tournamentID;
    private int m_numberParticipants;
    private int m_numberParticipantsLeft;
    private int m_numberParticipantsCountUp = 1;

    //Textview
    TextView EnteringCurrentTeam;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_team_data);

        //get the tournament id
        m_tournamentID = getIntent().getIntExtra("tournamentID",-10);
        m_numberParticipants = getIntent().getIntExtra("NumberTeams", -10);

        m_numberParticipantsLeft = m_numberParticipants;


        EnteringCurrentTeam = (TextView) findViewById(R.id.TVenteringCurrentTeam);

        teamCount();
        /*EnteringCurrentTeam.setText("Entering team " + m_numberParticipantsCountUp + " of "
                + m_numberParticipants);*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_enter_team_data, menu);
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


    ///////////////////////////////////////////////////////////////////////////
    //						Function To Grab
    //                   All The Data From Fields
    ///////////////////////////////////////////////////////////////////////////
    public void GetData(){

        //get the data from the fields
        m_teamName = ((EditText) findViewById(R.id.EditTextTeamName)).getText().toString();
        m_teamWins = ((EditText) findViewById(R.id.EditTextTeamWins)).getText().toString();
        m_teamLosses = ((EditText) findViewById(R.id.EditTextTeamLosses)).getText().toString();
        m_teamSeed = ((EditText) findViewById(R.id.EditTextTeamSeed)).getText().toString();
        m_teamCity = ((EditText) findViewById(R.id.EditTextTeamCity)).getText().toString();
        m_teamState = ((EditText) findViewById(R.id.EditTextTeamState)).getText().toString();
        m_teamEmail = ((EditText) findViewById(R.id.EditTextTeamEmail)).getText().toString();

    }

    ///////////////////////////////////////////////////////////////////////////
    //						Function To Clear
    //                  All The Data From The Fields
    ///////////////////////////////////////////////////////////////////////////
    public void ClearForm(){

        //get the data from the fields
        ((EditText) findViewById(R.id.EditTextTeamName)).setText("");
        ((EditText) findViewById(R.id.EditTextTeamWins)).setText("");
        ((EditText) findViewById(R.id.EditTextTeamLosses)).setText("");
        ((EditText) findViewById(R.id.EditTextTeamSeed)).setText("");
        ((EditText) findViewById(R.id.EditTextTeamCity)).setText("");
        ((EditText) findViewById(R.id.EditTextTeamState)).setText("");
        ((EditText) findViewById(R.id.EditTextTeamEmail)).setText("");

    }

    ///////////////////////////////////////////////////////////////////////////
    //						Function To Insert
    //                   All The Data From Fields
    //                      Into the Database
    ///////////////////////////////////////////////////////////////////////////

    public void PushData(){

        //create query to insert into team table
        String myQuery = "INSERT INTO Team(TeamName, Wins, Losses, City, State, ContactEmail) "+
                            "VALUES('"+m_teamName+"','"+m_teamWins+"','"+m_teamLosses+"','"
                            +m_teamCity+"','"+m_teamState+"','"+m_teamEmail+"')";

        //execute query
        DatabaseCommunicator.CreateInsertQuery(myQuery);

        //get the row id of the last insert
        int rowID = DatabaseCommunicator.GetRowID("Team");

        //create query to insert into seeding table
        myQuery = "INSERT INTO Seeding(TournamentID,TeamID,Seed)" +
                    "VALUES('"+m_tournamentID+"','"+rowID+"','"+m_teamSeed+"')";

        //execute the query
        DatabaseCommunicator.CreateInsertQuery(myQuery);

    }



    ///////////////////////////////////////////////////////////////////////////
    //						Function To Handle
    //                  The Click Of the Add Team Button
    ///////////////////////////////////////////////////////////////////////////
    public void AddTeam(View v){

        //get the most recent copy of the data
        GetData();

        //push the data to the database
        PushData();


        //remove 1 from the participants left
        m_numberParticipantsLeft -= 1;
        m_numberParticipantsCountUp++;

        //if there are no participants left to add thank them and exit to the main screen
        if(m_numberParticipantsLeft == 0){

            Toast.makeText(getApplicationContext(),"Thank you your final team was added",Toast.LENGTH_LONG).show();


            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }else{

            //tell them they were successful
            Toast.makeText(getApplicationContext(),"Team Added",Toast.LENGTH_LONG).show();

        }


        //startover so they can add more
        ClearForm();
        teamCount();



    }

    public void teamCount(){
        EnteringCurrentTeam.setText("Entering team " + m_numberParticipantsCountUp + " of "
                + m_numberParticipants);
    }
}
