package com.asrajarshi.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
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

public class Table extends AppCompatActivity {
    Context appContext;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        appContext = getBaseContext();
        try {getTable(appContext);}
        catch (IOException e) {e.printStackTrace();}
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
    public void getTable(Context context) throws IOException {
        TableLayout table = (TableLayout) findViewById(R.id.myTableLayout);
        context = getBaseContext();
        //To retrieve .json file from assets folder
        GetAssets getFile = new GetAssets(context);
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
                    text[j] = new TextView(Table.this);
                    text[j].setText(debt[j]);
                    text[j].setPadding(10,10,10,10);
                    row.addView(text[j]);
                }
                table.addView(row);
                row.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("mainacc", "hello rami");
                        TableRow tablerow = (TableRow) v;
                        TextView sample = (TextView) tablerow.getChildAt(0);
                        String result=sample.getText().toString();
                        Toast.makeText(Table.this, result, Toast.LENGTH_LONG).show();
                    }});
            } }catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
