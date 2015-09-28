package com.asrajarshi.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import org.json.JSONException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Table extends AppCompatActivity {
    Context appContext;
    private TextView recyclableTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        try {getTable();} catch (IOException e) {e.printStackTrace();}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_table, menu);
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

    public void getTable() throws IOException {
        appContext = getBaseContext();
        List<ArrayList<String>> TableList; // This Lsit will contain ArrayLists as columns
        GetAssets getFile = new GetAssets(appContext);//To retrieve .json file from assets folder
        String bufferString = getFile.getFileAssets();//To retrieve .json file from assets folder
        GetJson getJson1 = new GetJson(bufferString);//Instance of getJson1 which will be used to parse json
        try {
            TableList = getJson1.getIt(); // get the List of ArrayLists
            int nCol = TableList.get(0).size(); //size of column
            int nRow = TableList.size(); //size of row
            ArrayList<String> key1 = getJson1.key; // get keys contained in object
            TableLayout header = (TableLayout) findViewById(R.id.scrollable_part); // This is for row header
            TableRow row = new TableRow(this); // This is for row header
            row.setGravity(Gravity.LEFT); // This is for row header
            for (int j = 1; j < nCol; j++) {
                row.addView(makeTableRowWithText(key1.get(j))); // This is for row header
            }
            header.addView(row); // This is for row header
            //header (fixed horizontally)
            TableLayout fixedColumn = (TableLayout) findViewById(R.id.fixed_column); // This is for fixed column
            //rest of the table (within a scroll view)
            TableLayout scrollablePart = (TableLayout) findViewById(R.id.scrollable_part);
            TextView fixed = makeTableRowWithText(key1.get(0));// get 1st key which is header for fixed column
            fixedColumn.addView(fixed);
            for (int i = 0; i < nRow; i++) {
                TextView fixedView = makeTableRowWithText(TableList.get(i).get(0));  // set fix column
                fixedView.setBackgroundColor(Color.WHITE);
                fixedColumn.addView(fixedView);
                row = new TableRow(this);
                row.setGravity(Gravity.LEFT);
                row.setBackgroundColor(Color.WHITE);
                for (int k = 1; k < nCol; k++) {// return fixed column elements as the first element of each arraylist
                    row.addView(makeTableRowWithText(TableList.get(i).get(k)));
                }
                scrollablePart.addView(row);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public TextView makeTableRowWithText(String text) {
        recyclableTextView = new TextView(this);
        recyclableTextView.setText(text);
        recyclableTextView.setTextSize(20);
        recyclableTextView.setPadding(10, 10, 10, 10);
        return recyclableTextView;
    }
}