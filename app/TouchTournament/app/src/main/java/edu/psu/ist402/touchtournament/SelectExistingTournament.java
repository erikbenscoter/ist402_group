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

import java.util.Vector;


public class SelectExistingTournament extends ActionBarActivity {
    int numberOfTourneys=0;
    Vector <String> tournamentNamesVector = new Vector<String>();

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

    ///////////////////////////////////////////////////////////////////////////
    //						Function To Get
    //                      All Running Tournaments
    ///////////////////////////////////////////////////////////////////////////
    public void GetTournaments(){

        //prepare a query
        String myQuery = "SELECT TournamentName FROM Tournament";
        Cursor myCursor = DatabaseCommunicator.CreateFetchQuery(myQuery);
        Log.d("SelectExistingTourney", "Query Complete");
        //cycle through the results
        myCursor.moveToFirst();

        //Vector <String> tournamentNamesVector = new Vector<String>();
        tournamentNamesVector.add(myCursor.getString(0));

        Log.d("SelectExistingTourney", "create vector");

        while(myCursor.moveToNext()){
            tournamentNamesVector.add(myCursor.getString(0));
            numberOfTourneys++;
        }

        Log.d("SelectExistingTourney", "while loop complete " + numberOfTourneys);


    }

    public void createDisplay(){
        RadioGroup buttonGroup = (RadioGroup) findViewById(R.id.buttonGroup);
        Log.d("SelectExistingTourney", "Tournaments =" + numberOfTourneys);
        RadioButton[] selectButton = new RadioButton[(numberOfTourneys + 1)];
        for (int i = 0; i <= numberOfTourneys; i++) {
            selectButton[i] = new RadioButton(this);
            selectButton[i].setText(tournamentNamesVector.get(i));
            selectButton[i].setId(i);
            selectButton[i].setLayoutParams(new RadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));

            buttonGroup.addView(selectButton[i]);



        }







    }

    public void select(View view) {
        Intent intent = new Intent (this, Authenication.class);
        startActivity(intent);
    }
}
