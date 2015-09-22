package com.asrajarshi.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
   Context appContext;
    int i;
    int jjj;
    int rowCount;
    Integer[] jj = new Integer[100];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // This method is called when a buttonis pressed
        public void click1(View view) throws IOException {
            TableLayout table = (TableLayout) findViewById(R.id.myTableLayout);
            appContext = getBaseContext();
            //To retrieve .json file from assets folder
            GetAssets getFile = new GetAssets(appContext);
            String bufferString = getFile.getFileAssets();
            //Create insttance of GetJson: in method getIt() we can parse the json string
            final GetJson getJson1 = new GetJson(bufferString);
            //create list of Arraylists where each arraylist contain onr column
            List<ArrayList<String>> ab;
            try {
                ab = getJson1.getIt(); // get the List of ArrayLists i.e. whole table
                int size = ab.get(0).size(); // get rowcount
                int nCol = ab.size(); // get column count
                for (i = 0; i < size; i++) {
                    TableRow row = new TableRow(this);
                    row.setClickable(true);
                    String[] debt = new String[nCol];
                    for(int j =0;j<nCol ; j++){
                        TextView[] text = new TextView[size];
                        debt[j] = ab.get(j).get(i); // set row wise
                        text[j] = new TextView(MainActivity.this);
                        text[j].setText(debt[j]);
                        text[j].setPadding(20, 10, 10, 10);
                        row.addView(text[j]);
                        getJson1.setCountt(i);
                    }
                    table.addView(row);
                    row.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            jj[jjj] = rowCount;
                            jjj++;
                            rowCount++;
                            int hh = getJson1.getCounttt();
                            Log.d("mainacc", "hello rami");
                            Toast.makeText(MainActivity.this, "Discount should be less than 100"+hh,Toast.LENGTH_SHORT).show();
                        }});
                } }catch (JSONException e) {
                e.printStackTrace();
            }
        }
}