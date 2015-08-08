package edu.psu.ist402.touchtournament;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Authenication extends ActionBarActivity {

    private int m_TournamentID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenication);

        m_TournamentID = getIntent().getIntExtra(TournamentPairings.const_TournamentID, -10);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_authenication, menu);
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

    public void login(View view) {
        EditText ETusername = (EditText) findViewById(R.id.ETusername);
        EditText ETpassword = (EditText) findViewById(R.id.ETpassword);

        String username = ETusername.getText().toString();
        String password = ETpassword.getText().toString();

        String myQuery = "SELECT COUNT(UserEmail) FROM User WHERE UserEmail = '" + username +
                "' and UserPassword = '" + password + "'";

        Cursor myCursor = myCursor = DatabaseCommunicator.CreateFetchQuery(myQuery);

        myCursor.moveToFirst();

        int result = myCursor.getInt(0);

        if (result == 1){
            Intent intent = new Intent (this, TournamentPairings.class);
            intent.putExtra(TournamentPairings.const_TournamentID,m_TournamentID);
            startActivity(intent);
        }

        else{
            Toast.makeText(this, "Invalid Email and Password Combination!", Toast.LENGTH_LONG).show();
        }



    }
}
