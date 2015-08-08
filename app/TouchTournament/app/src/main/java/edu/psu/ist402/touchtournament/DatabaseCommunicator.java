package edu.psu.ist402.touchtournament;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import android.widget.Toast;

import static android.database.sqlite.SQLiteDatabase.deleteDatabase;
import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

/**
 * Created by erikbenscoter on 8/4/15.
 */
public class DatabaseCommunicator {



    ///////////////////////////////////////////////////////////////////////////
    //							Member Variables
    ///////////////////////////////////////////////////////////////////////////
    static SQLiteDatabase m_db = null;
    static Context m_context;
    private boolean inTest = false;
    final private String m_DATABASENAME = "TouchTournamentDatabase";

    ///////////////////////////////////////////////////////////////////////////
    //							Constructor
    ///////////////////////////////////////////////////////////////////////////

    DatabaseCommunicator(Context p_context){
        m_context = p_context;
    }
    
    ///////////////////////////////////////////////////////////////////////////
    //						Function To	Check if table exists
    ///////////////////////////////////////////////////////////////////////////
    public boolean DoesTableExist(){

        boolean returnValue = true;

        try{
            //query the User table
            Cursor myCursor = m_db.rawQuery("Select * from User", null);
            //returnValue = true;
        }catch (Exception e){
            Log.d("DatabaseCommunicator", "error Querying User", e);
           returnValue = false;
        }


        return returnValue;
    }

    ///////////////////////////////////////////////////////////////////////////
    //						Function To	Run Query 
    //						Against the Database
    ///////////////////////////////////////////////////////////////////////////
    //         Database Insert Query Catching error messages from DB
    public static int CreateInsertQuery(String input){

        try{

            m_db.execSQL(input);
        }catch (Exception e){
            Toast.makeText(m_context, e.toString(), Toast.LENGTH_LONG).show();
            Log.d("DatabaseCommunicator", "####ERROR###" + e);
        }

        //return no errors
        return 0;
    }
    //      Database Insert Query Catching Custom error messages
    public static int CreateInsertQuery(String input, String error){
            int result = 0;
        try{

            m_db.execSQL(input);
        }catch (Exception e){
            Toast.makeText(m_context, error, Toast.LENGTH_LONG).show();
            Log.d("DatabaseCommunicator", "####ERROR###" + e + error);
            result = 1;
        }

        //return no errors
        return result;
    }

    ///////////////////////////////////////////////////////////////////////////
    //						Function to Run Query
    //						Against the Database
    //						Where Data Is Expected
   	//						To Be Returned
    ///////////////////////////////////////////////////////////////////////////
    public static Cursor CreateFetchQuery(String input){

        Cursor cursor = m_db.rawQuery(input,null);
        return cursor;
    }

    ///////////////////////////////////////////////////////////////////////////
    //						Function To Open Database
    //						Or Make It If It Does Not
    ///////////////////////////////////////////////////////////////////////////
    public int Init(){

        //if we are in testing situation start with a fresh db
        if( inTest ){
            RemoveDB();
        }

        //open or create
        m_db = m_context.openOrCreateDatabase(m_DATABASENAME,Context.MODE_PRIVATE,null);

        //no errors
        int returnValue = 0;



        //check to see if table exists,
        //if it doesn't::
        if(!DoesTableExist()){
            Log.d("DatabaseCommunicator", "Tables do not exist");
            returnValue = CreateTables();
        }

        //return no errors
        return returnValue;
    }

    ///////////////////////////////////////////////////////////////////////////
    //						Function To	Create Tables
    ///////////////////////////////////////////////////////////////////////////
    public static int CreateTables(){



        try {
            //create tables from sql
            m_db.execSQL(SqlScripts.getM_UserCreation());
            m_db.execSQL(SqlScripts.getM_TeamTableCreation());
            m_db.execSQL(SqlScripts.getM_TournamentTableCreation());
            m_db.execSQL(SqlScripts.getM_ClientTableCreation());
            m_db.execSQL(SqlScripts.getM_CreateAdminTable());
            m_db.execSQL(SqlScripts.getM_FollowTeamTableCreation());
            m_db.execSQL(SqlScripts.getM_MatchTableCreation());
        }catch (Exception e){
            Log.d("DatabaseCommunicator", "####ERROR### couldn't create tables", e);
            return  -1;
        }
        Log.d("DatabaseCommunicator", "tables created sucessfully");
        //return no errors
        return 0;

    }

    ///////////////////////////////////////////////////////////////////////////
    //						Function To	Drop Tables
    //						And Their Information (Testing Purposes)
    ///////////////////////////////////////////////////////////////////////////
    protected int RemoveDB(){

    	m_context.deleteDatabase(m_DATABASENAME);
    	
        //return successful
        return 0;
    }

    ///////////////////////////////////////////////////////////////////////////
    //						Function To	Authenticate User
    ///////////////////////////////////////////////////////////////////////////
    public static boolean IsLegitimate(String p_email, String p_password){

        //create query

        Cursor myCursor = CreateFetchQuery("SELECT * FROM User" +
                " WHERE UserEmail='"+p_email+"'");

        int matches = myCursor.getCount();

        //declare and initiate the return value
        boolean returnValue = false;

        //if there is atleast one person with that username and password
        //then return true, otherwise return false
        if( matches > 0 ){
            returnValue = true;
        }else{
            returnValue = false;
        }

        return returnValue;
    }

    ///////////////////////////////////////////////////////////////////////////
    //					Function To Get The ROWID Of
    //                      What Was Just Input
    ///////////////////////////////////////////////////////////////////////////
    public static int GetRowID( String p_tableName ){

        //create the query
        String myQuery = "SELECT ROWID from "+p_tableName+" order by ROWID DESC limit 1";
        Cursor myCursor = DatabaseCommunicator.CreateFetchQuery(myQuery);
        myCursor.moveToFirst();

        //return the rowid
        return myCursor.getInt(0);

    }

    ///////////////////////////////////////////////////////////////////////////
    //							Reset the Context
    ///////////////////////////////////////////////////////////////////////////
    public static void setM_context(Context p_context) {
        m_context = p_context;

    }




}


