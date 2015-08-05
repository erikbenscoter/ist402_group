package edu.psu.ist402.touchtournament;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

/**
 * Created by erikbenscoter on 8/4/15.
 */
public class DatabaseCommunicator {

    SQLiteDatabase m_db = null;
    Context m_context;

    DatabaseCommunicator(Context p_context){
        m_context = p_context;
    }
    
    public boolean DoesTableExist(){

        boolean returnValue = false;

        try{
            //query the User table
            Cursor myCursor = m_db.rawQuery("Select * from Users", null);
            returnValue = true;
        }catch (Exception e){
           returnValue = false;
        }



        return returnValue;
    }

    public int CreateInsertQuery(String input){

        m_db.execSQL(input);

        //return no errors
        return 0;
    }


    public int Init(){

        //open or create
        m_db = m_context.openOrCreateDatabase("TouchTournamentDatabase",Context.MODE_PRIVATE,null);


        int returnValue = 0;

        //check to see if table exists,
        //if it doesn't::
        if(!DoesTableExist()){
            returnValue = CreateTables();
        }

        //return no errors
        return returnValue;
    }
    public int CreateTables(){



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
            System.out.println("####ERROR### couldn't create tables");
            return  -1;
        }

        //return no errors
        return 0;

    }

}
