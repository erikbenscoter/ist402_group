package edu.psu.ist402.touchtournament;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_team_data);
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

        //create query
        String myQuery = "INSERT INTO Team(TeamName, Wins, Losses, Seed, City, State, ContactEmail) "+
                            "VALUES('"+m_teamName+"','"+m_teamWins+"','"+m_teamLosses+"'," +
                            "'"+m_teamSeed+"','"+m_teamCity+"','"+m_teamState+"','"+m_teamEmail+"')";

        //execute query
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

        //tell them they were successful
        Toast.makeText(getApplicationContext(),"Team Added",Toast.LENGTH_LONG);

        //startover so they can add more
        ClearForm();

    }
}
