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
    static int i;
    Context appContext;
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
    public void click1(View view) throws IOException {
        TableLayout table = (TableLayout) findViewById(R.id.myTableLayout);
        appContext = getBaseContext();
        //To retrieve .json file from assets folder
        GetAssets getFile = new GetAssets(appContext);
        String bufferString = getFile.getFileAssets();
        GetJson getJson1 = new GetJson(bufferString);
        //create list of Arraylists where each arraylist contain onr column
        List<ArrayList<String>> ab= new ArrayList<ArrayList<String>>();
        try {
            ab = getJson1.getIt(); // get the List of ArrayLists
            int nCol = ab.get(0).size(); //size of column
            int nRow = ab.size(); //size of row
            for (i = 0; i < nRow; i++) {
                TableRow row = new TableRow(this);
                //TableRow row = new TableRow(this);
                row.setClickable(true);
                String[] debt = new String[nCol];
                for(int j =0;j<nCol ; j++){
                    TextView[] text = new TextView[nCol];
                    debt[j] = ab.get(i).get(j);
                    text[j] = new TextView(MainActivity.this);
                    text[j].setText(debt[j]);
                    text[j].setPadding(5, 2, 2, 2);
                    row.addView(text[j]);
                }
                table.addView(row);
                row.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("mainacc", "hello rami");
                            Toast.makeText(MainActivity.this, "number is "+i,Toast.LENGTH_SHORT).show();
                        }});
                } }catch (JSONException e) {
                e.printStackTrace();
            }
        }
}