package edu.psu.ist402.touchtournament;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;


public class TournamentPairings extends ActionBarActivity {

    int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_pairings);

        //insert code to bring in size here
        size = 8;

        //determine number of columns and rows

        int tempSize = size;
        int cols = 1;
        int rows = 1;
        int totalSpots = 0;

        while(tempSize > 1){
            rows = rows + tempSize;
            cols++;
            tempSize = tempSize / 2;
        }





        TableLayout t1 = (TableLayout) findViewById(R.id.tourneyTableLayout);
        //t1.addView(row1);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tournament_pairings, menu);
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
}
