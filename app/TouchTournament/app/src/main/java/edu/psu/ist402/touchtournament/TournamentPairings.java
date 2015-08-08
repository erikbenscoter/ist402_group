package edu.psu.ist402.touchtournament;

import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class TournamentPairings extends ActionBarActivity {

    ///////////////////////////////////////////////////////////////////////////
    //						Member Variables
    ///////////////////////////////////////////////////////////////////////////
    int size;
    public static String const_NumOfParticipants = "NumParticipants";
    public static String const_TournamentID = "TournamentID";
    private int m_numberOfParticipants;
    private int m_numberOfByes;
    private int m_TournamentID;
    private String [] m_arrParticipantNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_pairings);

        //get the number of participants for the tournament
        m_numberOfParticipants = getIntent().getIntExtra(const_NumOfParticipants,-10);

        //get the tournamentID
        m_TournamentID = getIntent().getIntExtra(const_TournamentID, -10);

        //calculate the number of teams that will get a bye
        m_numberOfByes = TournamentGenerator.ByeCalculator(m_numberOfParticipants);

        //make the appropriate layout visible
        PickTournamentLayout();

        //grab the team names and seeds
        PopulateNameArr();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tournament_pairings, menu);
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
    //						Function To Pick the
    //                  Appropriate Layout for the Tournament
    ///////////////////////////////////////////////////////////////////////////
    public void PickTournamentLayout(){

        //declarations
        TableLayout sixteenTeamLayout;
        TableLayout eightTeamLayout;
        TableLayout fourTeamLayout;

        //grab all the layouts
        sixteenTeamLayout = (TableLayout) findViewById(R.id.sixteenTeamLayout);
        eightTeamLayout = (TableLayout) findViewById(R.id.eightTeamLayout);
        fourTeamLayout = (TableLayout) findViewById(R.id.fourTeamLayout);

        //set all of them invisible
        sixteenTeamLayout.setVisibility(View.GONE);
        eightTeamLayout.setVisibility(View.GONE);
        fourTeamLayout.setVisibility(View.GONE);

        //set the appropriate one to visible
        switch ( m_numberOfParticipants ){
            case 2:
            case 3:
            case 4:
                fourTeamLayout.setVisibility(View.VISIBLE);
                break;
            case 5:
            case 6:
            case 7:
            case 8:
                eightTeamLayout.setVisibility(View.VISIBLE);
                break;
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
                sixteenTeamLayout.setVisibility(View.VISIBLE);
                break;
        }//end switch
    }//end function

    ///////////////////////////////////////////////////////////////////////////
    //						Function To Populate
    //                          Name and Seed Array
    ///////////////////////////////////////////////////////////////////////////
    public void PopulateNameArr(){

        //////////get info from db//////

        //make query statement
        String myQuery = "SELECT TeamID, Seed FROM Seeding WHERE TournamentID = '"+m_TournamentID+"'";

        //execute the query
        Cursor myCursor = DatabaseCommunicator.CreateFetchQuery(myQuery);

        //the number of participants is the size of the array
        m_arrParticipantNames = new String[m_numberOfParticipants];

        myCursor.moveToFirst();

        //put the TeamID in the array by seed
        m_arrParticipantNames[myCursor.getInt(1)-1] = Integer.toString(myCursor.getInt(0));

        while( myCursor.moveToNext() ){

            //put the TeamID in the array by seed
            m_arrParticipantNames[myCursor.getInt(1)-1] = Integer.toString(myCursor.getInt(0));
        }

        //now the array is filled with IDs for the table
        //let's replace those rowids with something more meaningful to the user
        for (int arrayItterator = 0; arrayItterator < m_arrParticipantNames.length; arrayItterator ++ ){

            //create a query of the db
            myQuery = "SELECT TeamName FROM Team WHERE ROWID = '"+m_arrParticipantNames[arrayItterator]+"'";

            //execute the query
            myCursor = DatabaseCommunicator.CreateFetchQuery(myQuery);

            myCursor.moveToFirst();

            //set the name value in the array
            m_arrParticipantNames[arrayItterator] = myCursor.getString(0);
        }

    }//end function

}//end class



