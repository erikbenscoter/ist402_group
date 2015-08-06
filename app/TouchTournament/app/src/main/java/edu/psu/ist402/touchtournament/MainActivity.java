package edu.psu.ist402.touchtournament;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    DatabaseCommunicator m_myDbCommunicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_myDbCommunicator = new DatabaseCommunicator(this);
        m_myDbCommunicator.Init();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void createNewTournament(View view) {
        Intent intent = new Intent (this, CreateNewTourney.class);
        startActivity(intent);
    }

    public void runExisting(View view) {
        Intent intent = new Intent (this, SelectExistingTournament.class);
        startActivity(intent);
    }

    public void viewInProgres(View view) {
        Intent intent = new Intent (this, SelectExistingTournament.class);
        startActivity(intent);
    }

    public void searchCompleted(View view) {
        Intent intent = new Intent (this, SearchTournaments.class);
        startActivity(intent);

    }


    public void CreateAccount(View view) {
        Intent intent = new Intent(this, CreateAccount.class);
        startActivity(intent);
    }
}
