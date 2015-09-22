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
    TextView[] text;
    TextView[] text1;
    TextView[] text2;
    TextView[] text3;
    TextView[] text4;
    TextView[] text5;
    TextView[] text6;
    TextView[] text7;
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
            int size = ab.get(0).size();
            text = new TextView[size];
            text1 = new TextView[size];
            text2 = new TextView[size];
            text3 = new TextView[size];
            text4 = new TextView[size];
            text5 = new TextView[size];
            text6 = new  TextView[size];
            text7 = new  TextView[size];
            for (int i = 0; i < size; i++) {
                TableRow row = new TableRow(this);
                row.setClickable(true);
                String debt = ab.get(0).get(i);
                String fee = ab.get(1).get(i);
                String fee1 = ab.get(2).get(i);
                String fee2 = ab.get(3).get(i);
                String debt1 = ab.get(0).get(i);
                String fee12 = ab.get(1).get(i);
                String fee11 = ab.get(2).get(i);
                String fee21 = ab.get(3).get(i);
                text[i] = new TextView(MainActivity.this);
                text1[i] = new TextView(MainActivity.this);
                text2[i] = new TextView(MainActivity.this);
                text3[i] = new TextView(MainActivity.this);
                text4[i] = new TextView(MainActivity.this);
                text5[i] = new TextView(MainActivity.this);
                text6[i] = new TextView(MainActivity.this);
                text7[i] = new TextView(MainActivity.this);
                text[i].setText(debt);text1[i].setText(fee);
                text2[i].setText(fee1);text3[i].setText(fee2);
                text4[i].setText(debt);text5[i].setText(fee);
                text3[i].setText(fee1);text7[i].setText(fee2);
//                text[i].setPadding(20,10,10,10);text1[i].setPadding(20,10,10,10);
//                text2[i].setPadding(20,10,10,10);text3[i].setPadding(20,10,10,10);
                row.addView(text[i]);row.addView(text1[i]);
                row.addView(text2[i]);row.addView(text3[i]);
                row.addView(text4[i]);row.addView(text5[i]);
                row.addView(text6[i]);row.addView(text7[i]);
                table.addView(row);
                row.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("mainacc", "hello rami");
                    }});
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}