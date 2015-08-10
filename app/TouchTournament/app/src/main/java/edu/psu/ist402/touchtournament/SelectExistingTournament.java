package edu.psu.ist402.touchtournament;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;


public class SelectExistingTournament extends ActionBarActivity {
    Vector <String> tournamentNamesVector = new Vector<String>();
    String tournamentSelected = "";
    int tournamentID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_existing_tournament);
        Log.d("SelectExistingTourney", "Starts");
        GetTournaments();
        createDisplay();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_open_existing_tournament, menu);
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


    public void createDisplay(){
        int numberOfTourneys = tournamentNamesVector.size();
        Log.d("SelectExistingTourney", "numberOfTourneys set to " + numberOfTourneys);
        RadioGroup buttonGroup = (RadioGroup) findViewById(R.id.buttonGroup);
        Log.d("SelectExistingTourney", "RadioGroup object created");
        final RadioButton[] selectButton = new RadioButton[numberOfTourneys];
        //Log.d("SelectExistingTourney", (numberOfTourneys +1) + "RadioButton Array indexes created");
        for (int i = 0; i < numberOfTourneys; i++) {
            selectButton[i] = new RadioButton(this);
            selectButton[i].setText(tournamentNamesVector.get(i));
            selectButton[i].setId(i);
            selectButton[i].setLayoutParams(new RadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            buttonGroup.addView(selectButton[i]);



        }

        buttonGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                //tournamentSelected = selectButton[checkedId].getText().toString();
                Log.d("SelectExistingTourney", "Tournament Selected = " + (checkedId + 1));
                tournamentID = checkedId +1;

            }
        });
    }



    public void select(View view) {
        Intent intent = new Intent (this, Authenication.class);
        intent.putExtra(TournamentPairings.const_TournamentID, tournamentID);
        Log.d("SelectExistingTourney", "PutExtra added");
        startActivity(intent);
    }

    ///////////////////////////////////////////////////////////////////////////
    //						Function To Get
    //                      All Running Tournaments
    ///////////////////////////////////////////////////////////////////////////
    public void GetTournaments(){
        //add error checking here for tournament count.

        String myQuery = "SELECT COUNT(ROWID) FROM Tournament";

        Cursor myCursor = DatabaseCommunicator.CreateFetchQuery(myQuery);

        //grab the returned data
        myCursor.moveToFirst();
        int tourneyExists = myCursor.getInt(0);

        //no tourneys exits
        if(tourneyExists > 0){

            //prepare a query
            myQuery = "SELECT TournamentName FROM Tournament";
            myCursor = DatabaseCommunicator.CreateFetchQuery(myQuery);
            Log.d("SelectExistingTourney", "Query Complete");
            //cycle through the results
            myCursor.moveToFirst();

            //Vector <String> tournamentNamesVector = new Vector<String>();
            tournamentNamesVector.add(myCursor.getString(0));


            while (myCursor.moveToNext()) {
                tournamentNamesVector.add(myCursor.getString(0));

            }

        }

        else {
            Toast.makeText(getApplicationContext(), "No Tournaments have been created on the System yet.  " +
                    "Please create a Tournament first.", Toast.LENGTH_SHORT).show();
            thread.start();


        }




    }
    Thread thread = new Thread(){
        @Override
        public void run() {
            try {


                //Sleep in milli seconds
                //Toast.LENGTH_LONG = 3.5 second display
                //Toast.LENGTH_SHORT = 2 second display
                //Tweeked the timing for effect.
                Thread.sleep(1900);
                SelectExistingTournament.this.finish();
            } catch (Exception e) {
                Log.d("CreateAccount", "Error closing activity after toast message = " + e);
            }
        }
    };

}
