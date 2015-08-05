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


public class CreateAccount extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_account, menu);
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

    public void CreateAccountSubmit(View view) {

        //grab the fields
        EditText editTextEmail = (EditText) findViewById(R.id.EditTextEmail);
        EditText editTextPassword = (EditText) findViewById(R.id.EditTextPassword);

        //grab the text input
        String emailInput = editTextEmail.getText().toString();
        String passwordInput = editTextPassword.getText().toString();

        //create the query
        String myInputQuery = "INSERT INTO User(UserEmail, UserPassword, UserWin, UserLoss)" +
                "VALUES("+emailInput +","+passwordInput+", 0 ,0 )";

        //run the query
        DatabaseCommunicator.CreateInsertQuery(myInputQuery);

        //test
        Cursor myCursor = DatabaseCommunicator.CreateFetchQuery("SELECT * FROM User");

        myCursor.moveToFirst();
        String myOutput = "";

        while(myCursor.moveToNext()){
            myOutput += myCursor.getString(0);
            myOutput += myCursor.getString(1);
            myOutput += myCursor.getString(2);
            myOutput += myCursor.getString(3);
        }

        Toast.makeText(this.getApplicationContext(),myOutput,Toast.LENGTH_LONG).show();
        //Toast.makeText(this.getApplicationContext(),"Thank you",Toast.LENGTH_LONG).show();

    }
}
