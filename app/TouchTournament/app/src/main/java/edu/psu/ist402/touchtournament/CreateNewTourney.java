package edu.psu.ist402.touchtournament;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class CreateNewTourney extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_tourney);
    }

    ///////////////////////////////////////////////////////////////////////////
    //						Member Variables
    ///////////////////////////////////////////////////////////////////////////
    private String m_TourneyName = "";
    private String m_Location = "";
    private String m_beginningDate = "";
    private String m_endingDate = "";
    private String m_email = "";
    private String m_password = "";

    private String m_typeOfTournement = "";
    private int m_numberOfParticipants = 0;


    ///////////////////////////////////////////////////////////////////////////
    //						Function To	Grab All User Input
    ///////////////////////////////////////////////////////////////////////////
    public void CollectFields(){

        //get all the fields and put them into member variables of the class

        m_TourneyName   = ((TextView)findViewById(R.id.TextViewTournamentName)).getText().toString();
        m_Location      = ((TextView)findViewById(R.id.TextViewLocation)).getText().toString();
        m_beginningDate = ((TextView)findViewById(R.id.TextViewBeginningDate)).getText().toString();
        m_endingDate    = ((TextView)findViewById(R.id.TextViewEndingDate)).getText().toString();
        m_email         = ((TextView)findViewById(R.id.TextViewEmailCreateTourney)).getText().toString();
        m_password      = ((TextView)findViewById(R.id.TextViewPassword)).getText().toString();

        //get the number of particpants
        String tmpNumberOfParticipants = ((Spinner)findViewById(R.id.noOfParticipants)).getSelectedItem().toString();
        m_numberOfParticipants = Integer.parseInt(tmpNumberOfParticipants);

        //get the type of tourney
        m_typeOfTournement = ((Spinner)findViewById(R.id.tourneyType)).getSelectedItem().toString();
    }

    ///////////////////////////////////////////////////////////////////////////
    //						Take The Input Data And
    //                      Add It To The Database
    ///////////////////////////////////////////////////////////////////////////
    public void SubmitForm(View view) {

        //collect all the information
        CollectFields();

        //reset the context of the database communicator


        //check to see if user is legitimate
        if( !DatabaseCommunicator.IsLegitimate(m_email,m_password) ){
            Toast.makeText(getApplicationContext(),"That is not a legitimate username/password combo",Toast.LENGTH_LONG).show();
            return;
        };


        //create query to insert into Tournament Table
        String myQuery;
        myQuery = "INSERT INTO Tournament(TournamentName,TournamentActive,TournamentLocation)" +
                    "VALUES('"+m_TourneyName+"','1','"+m_Location+"')";

        //execute query
        DatabaseCommunicator.CreateInsertQuery(myQuery);



        //get tournamentID
        myQuery = "Select TournamentID FROM Tournament WHERE TournamentName = '"+m_TourneyName+"'"+
                    "AND TournamentActive = 1 AND TournamentLocation = '"+m_Location+"'";
        Cursor myCursor = DatabaseCommunicator.CreateFetchQuery(myQuery);

        //grab the returned data
        myCursor.moveToFirst();
        int tournamentID = myCursor.getInt(0);

        //insert into admin table
        myQuery = "INSERT INTO Admin(AdminEmail,TournamentID)" +
                    "VALUES('"+m_email+"', "+tournamentID+")";

        Toast.makeText(getApplicationContext(),"Congratulations your tournament has been created",Toast.LENGTH_LONG);

    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_new_tourney, menu);
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
}
