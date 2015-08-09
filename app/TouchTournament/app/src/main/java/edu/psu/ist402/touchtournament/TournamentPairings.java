package edu.psu.ist402.touchtournament;

import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
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
    private Button[] m_arrBracketButtons;
    private int m_sizeLayout;
    Cursor myCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_pairings);

        //get the tournamentID
        m_TournamentID = getIntent().getIntExtra(const_TournamentID, -10);
        Log.d("TournamentPairings", "m_TournamentID = " + m_TournamentID);

        //get the number of participants for the tournament
        //m_numberOfParticipants = getIntent().getIntExtra(const_NumOfParticipants,-10);
        m_numberOfParticipants = grabNumberofParticipants();
        Log.d("TournamentPairings", "Number of Participants = " +  m_numberOfParticipants);

        //calculate the number of teams that will get a bye
        m_numberOfByes = TournamentGenerator.ByeCalculator(m_numberOfParticipants);



        //make the appropriate layout visible
        PickTournamentLayout();

        //grab the team names and seeds
        PopulateNameArr();

        //organize the buttons in an enterable way
        OrganizeButtonArr();

        //set default text of buttons
        SetButtonsText("Not Yet Determined");

        //populate first round button texts
        PopulateFirstRoundButtons();

        //populate the byes
        PopulateByes();

    }

    private int grabNumberofParticipants() {

        String participantQuery = "SELECT COUNT(TournamentID) FROM Seeding WHERE " +
                "TournamentID = '" + m_TournamentID + "'";
        Log.d("TournamentPairings", "created query string = " + participantQuery);
        myCursor = DatabaseCommunicator.CreateFetchQuery(participantQuery);
        Log.d("TournamentPairings", "Query Sucessful");
        myCursor.moveToFirst();
        Log.d("TournamentPairings", "move to first");
        int numberOfParticipants = myCursor.getInt(0);
        Log.d("TournamentPairings", "getInt()");
        return numberOfParticipants;
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
                m_sizeLayout = 4;
                break;
            case 5:
            case 6:
            case 7:
            case 8:
                eightTeamLayout.setVisibility(View.VISIBLE);
                m_sizeLayout = 8;
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
                m_sizeLayout = 16;
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
        myCursor = DatabaseCommunicator.CreateFetchQuery(myQuery);

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

    ///////////////////////////////////////////////////////////////////////////
    //						Function To Populate
    //                          Button Array
    ///////////////////////////////////////////////////////////////////////////
    public void OrganizeButtonArr() {

        //the number of spots in the tournament are as follows
        //which is 2*numParticipants -1:
        /*
                4->7;
                8->15;
                16->31;

            Similarly we will leverage the name of the buttons in order to grab
            them in a loop.  It will be made up of two parts parts: _number_numberparticipman

            For example: spot 13 of a 16 participant tournament would be as follows
            _13_16man
         */

        //create the prestring:
        String preSpotString = "_";
        String currSpotString = "0";
        String postSpotString = "_"+Integer.toString(m_sizeLayout)+"man";

        //last button number will be 2*layoutsize -1
        int lastButtonNumber = ((2*m_sizeLayout)-1);

        //initialize the array to hold all the buttons
        m_arrBracketButtons = new Button[lastButtonNumber];

        //cycle through the buttons
        for( int currentButton = 1; currentButton <= lastButtonNumber; currentButton ++){

            String resourceName = preSpotString + Integer.toString(currentButton) + postSpotString;
            int resID = getResources().getIdentifier(resourceName,"id",getPackageName());

            //put the current button in that spot -1 of the array
            m_arrBracketButtons[currentButton-1] = (Button) findViewById(resID);
        }


    }//end function

    ///////////////////////////////////////////////////////////////////////////
    //					Function To Set All Buttons' Text
    ///////////////////////////////////////////////////////////////////////////
    public void SetButtonsText(String p_defaultText){

        for(int currButtonItt = 0; currButtonItt < m_arrBracketButtons.length; currButtonItt ++){
            m_arrBracketButtons[currButtonItt].setText(p_defaultText);
        }
    }// end funciton

    ///////////////////////////////////////////////////////////////////////////
    //					Function To Populate Button Names
    ///////////////////////////////////////////////////////////////////////////
    public void PopulateFirstRoundButtons(){
        int moreByes = m_numberOfByes;
        boolean singleAlternator = false;
        boolean pullFromBack = false;
        int backNameItterator = m_numberOfParticipants -1;
        int alternator = 1;
        int currNameItt = m_numberOfByes;
        for( int currButtonItt = 0; currButtonItt < m_sizeLayout; currButtonItt ++ ){

            if( moreByes == 0 || (currNameItt <= backNameItterator && alternator <= 2)  ){

                if( pullFromBack ){
                    m_arrBracketButtons[currButtonItt].setText(m_arrParticipantNames[backNameItterator]);
                    backNameItterator --;
                }else{
                    m_arrBracketButtons[currButtonItt].setText(m_arrParticipantNames[currNameItt]);
                    currNameItt ++;
                }

                pullFromBack = !pullFromBack;
            }
            else{

                m_arrBracketButtons[currButtonItt].setVisibility(View.GONE);
                if( singleAlternator ){
                    moreByes --;
                }
                singleAlternator = !singleAlternator;
            }

            alternator ++;
            if(alternator == 5){
                alternator = 1;
            }

        }

    }//end function

    ///////////////////////////////////////////////////////////////////////////
    //					Function To Populate Bye Button Names
    ///////////////////////////////////////////////////////////////////////////
    public void PopulateByes(){
        int moreNonByes = (m_numberOfParticipants - m_numberOfByes)/2;
        boolean singleAlternator = false;
        boolean pullFromBack = false;
        int backNameItterator = m_numberOfByes-1;
        int alternator = 1;
        int currNameItt = 0;

        for( int currButtonItt = m_sizeLayout; currButtonItt < m_sizeLayout+m_sizeLayout/2; currButtonItt ++ ){

            if( (alternator > 1 || moreNonByes == 0) && currNameItt < m_numberOfByes ){

                if(pullFromBack){
                    m_arrBracketButtons[currButtonItt].setText(m_arrParticipantNames[backNameItterator]);
                    backNameItterator --;

                }else{
                    m_arrBracketButtons[currButtonItt].setText(m_arrParticipantNames[currNameItt]);
                    currNameItt ++;

                }

                pullFromBack = !pullFromBack;
            }
            else{

                moreNonByes --;

                singleAlternator = !singleAlternator;

            }

            alternator ++;
            if(alternator == 3){
                alternator = 1;
            }

        }
    }


}//end class



