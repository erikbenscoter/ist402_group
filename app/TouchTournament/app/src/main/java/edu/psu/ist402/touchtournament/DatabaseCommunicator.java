package edu.psu.ist402.touchtournament;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by erikbenscoter on 8/4/15.
 */
public class DatabaseCommunicator {

    SQLiteDatabase db;

    public int CreateInsertQuery(){


        //return no errors
        return 0;
    }
    public int Init(){

        //return no errors
        return 0;
    }
    public int CreateTable(){

        //open or create
        db.openOrCreateDatabase("TouchTournamentDB",null);

        //create table from sql


        //return no errors
        return 0;

    }
}
