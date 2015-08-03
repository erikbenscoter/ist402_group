package edu.psu.ist402.touchtournament;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsoluteLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class TournamentPairings extends ActionBarActivity {

    int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_pairings);
        /*
        I can't figure out how to make the bracket dynamically so I will make a 4, 8, 16, and 32
        spot bracket statically


        //insert code to bring in size here
        size = 16;

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

        totalSpots = cols * rows;

        Log.d("TournamentPairings", "Columns = " + cols + " Rows = " + rows +
                " Total TextViews = " + totalSpots);



        TableLayout t1 = (TableLayout) findViewById(R.id.tourneyTableLayout);
        //t1.addView(row1);

        TableRow[] tr = new TableRow[rows];
        TextView[] tv = new TextView[totalSpots];

        int initTVto;
        int j = 0;

        for (int i = 0; i < rows; i++){
            tr[i] = new TableRow(this);
            tr[i].setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            t1.addView(tr[i]);

            initTVto = (i + 1)*cols;

            while (j < initTVto){
                tv[j] = new TextView(this);
                tv[j].setText("Test " + j + " ");
                tr[i].addView(tv[j]);
                j++;
            }


        }
        */
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
