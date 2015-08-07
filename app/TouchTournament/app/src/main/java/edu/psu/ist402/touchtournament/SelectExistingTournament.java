package edu.psu.ist402.touchtournament;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Vector;


public class SelectExistingTournament extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_existing_tournament);
        GetTournaments();
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

        //cycle through the results
        myCursor.moveToFirst();

        Vector <String> tournamentNamesVector = new Vector<String>();
        tournamentNamesVector.add(myCursor.getString(0));

        while(myCursor.moveToNext()){
            tournamentNamesVector.add(myCursor.getString(0));
        }


    }

    public void select(View view) {
        Intent intent = new Intent (this, Authenication.class);
        startActivity(intent);
    }
}
