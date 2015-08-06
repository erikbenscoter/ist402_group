package edu.psu.ist402.touchtournament;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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
                "VALUES('"+emailInput +"','"+passwordInput+"', 0 ,0 )";


        //run the query
        DatabaseCommunicator.CreateInsertQuery(myInputQuery);


        //get the email from the db
        Cursor myCursor = DatabaseCommunicator.CreateFetchQuery("SELECT UserEmail,UserPassword FROM User" +
                                                                " WHERE UserEmail='"+emailInput+"'");

        //move to the first result spot and add it to the string
        myCursor.moveToFirst();
        String email = "";
        email = email + myCursor.getString(0);
        email = email + myCursor.getString(1);


        //cycle through the rows (if any) and add them to email
        while(myCursor.moveToNext()){
            email += myCursor.getString(0);
        }

        //grab the welcome box
        TextView welcomeMsg = (TextView) findViewById(R.id.EditTextWelcomeMsg);
        welcomeMsg.setText("thank you, " + email + ", you have been successfully added");
        welcomeMsg.setFocusable(false);


    }
}
