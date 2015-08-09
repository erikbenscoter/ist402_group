package edu.psu.ist402.touchtournament;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class CreateNewTourney extends ActionBarActivity {

    EditText TextViewBeginningDate;
    EditText TextViewEndingDate;

    Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener BeginDate = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateTextViewBeginningDate();
        }

    };

    DatePickerDialog.OnDateSetListener EndDate = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateTextViewEndingDate();
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_tourney);

        TextViewBeginningDate = (EditText) findViewById(R.id.TextViewBeginningDate);
        TextViewEndingDate = (EditText) findViewById(R.id.TextViewEndingDate);

        TextViewBeginningDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(CreateNewTourney.this, BeginDate, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        TextViewEndingDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(CreateNewTourney.this, EndDate, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

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

    private String m_TournementType = "";
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
        m_TournementType = ((Spinner)findViewById(R.id.tourneyType)).getSelectedItem().toString();
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
        }


        //create query to insert into Tournament Table
        String myQuery;
        myQuery = "INSERT INTO Tournament(TournamentName,TournamentActive,TournamentLocation," +
                "BeginDate,EndDate,TournamentType)" +
                    "VALUES('"+m_TourneyName+"','1','"+m_Location+"','"+m_beginningDate+"','"+
                m_endingDate+"','"+m_TournementType+"')";

        Log.d("CreateNewTourney", "SQL insert = " + myQuery);

        //execute query
        DatabaseCommunicator.CreateInsertQuery(myQuery);



        //get tournamentID
        myQuery = "Select ROWID FROM Tournament WHERE TournamentName = '"+m_TourneyName+"'"+
                    " AND TournamentLocation = '"+m_Location+"'";
        Cursor myCursor = DatabaseCommunicator.CreateFetchQuery(myQuery);

        //grab the returned data
        myCursor.moveToFirst();
        int tournamentID = myCursor.getInt(0);

        //insert into admin table
        myQuery = "INSERT INTO Admin(AdminEmail,TournamentID)" +
                    "VALUES('"+m_email+"', "+tournamentID+")";

        DatabaseCommunicator.CreateInsertQuery(myQuery);

        Toast.makeText(getApplicationContext(),"Congratulations your tournament has been created",Toast.LENGTH_LONG);

        Intent intent = new Intent(this, EnterTeamData.class);
        intent.putExtra(TournamentPairings.const_TournamentID,tournamentID);
        intent.putExtra(TournamentPairings.const_NumOfParticipants, m_numberOfParticipants);
        startActivity(intent);



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

    private void updateTextViewBeginningDate() {

        String displayFormat = "MM/dd/yy"; //displayed on screen
        String SQLFormat = "yyyy/MM/dd"; //stored in DB
        SimpleDateFormat displayDate = new SimpleDateFormat(displayFormat, Locale.US);
        SimpleDateFormat SQLDate = new SimpleDateFormat(SQLFormat);
        m_beginningDate = SQLDate.format(myCalendar.getTime());
        Log.d("CreateNewTourney", "Check date format for SQLite = " + m_beginningDate);

        TextViewBeginningDate.setText(displayDate.format(myCalendar.getTime()));

    }

    private void updateTextViewEndingDate() {

        String displayFormat = "MM/dd/yy"; //displayed on screen
        String SQLFormat = "yyyy/MM/dd"; //stored in DB
        SimpleDateFormat displayDate = new SimpleDateFormat(displayFormat, Locale.US);
        SimpleDateFormat SQLDate = new SimpleDateFormat(SQLFormat);
        m_endingDate = SQLDate.format(myCalendar.getTime());
        Log.d("CreateNewTourney", "Check date format for SQLite = " + m_endingDate);

        TextViewEndingDate.setText(displayDate.format(myCalendar.getTime()));


    }

}
