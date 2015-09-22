package com.asrajarshi.myapplication;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by asrajarshi on 9/18/2015.
 */
public class GetJson {
    String json;
    String data = "";
    int countt;
    ArrayList<String> frmula = new ArrayList<>();
    ArrayList<String> urrl = new ArrayList<>();
    ArrayList<String> ab = new ArrayList<>();
    ArrayList<String> ab1 = new ArrayList<>();
    List<ArrayList<String>> ab11 = new ArrayList<>();
    int count;

    GetJson(String json) {
        this.json = json;
    }
    public List<ArrayList<String>> getIt() throws JSONException {
        JSONObject reader = new JSONObject(json);
        //Get the instance of JSONArray that contains JSONObjects
        JSONArray jsonArray = reader.optJSONArray("formules");
        //Iterate the jsonArray and print the info of JSONObjects
        ArrayList<String> key = gotIt(jsonArray);
        int cn = getCount();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String[] value = new String[cn];
            for(int j =0;j<cn;j++)
                value[j] = jsonObject.optString(key.get(j)).toString(); // to retrive elemtns in one object
            frmula.add((value[0])); //add value to 1st column
            ab.add(value[1]); // add value to second colum
            ab1.add((value[2])); // add to third column
            urrl.add(value[3]); // add to forth column
        }
        ab11.add(urrl); //add 1st column to the list
        ab11.add(frmula); // add 2nd column to the list
        ab11.add(ab); // add 3rd column to the list
        ab11.add(ab1); // add 4th column to the lsit
        return ab11;
    }
// This method is to iterate over keys in one json object and to return those keys
    public ArrayList<String > gotIt(JSONArray jsonArray) throws JSONException {
        JSONObject jsonObject = jsonArray.getJSONObject(1);
        Iterator iterator = jsonObject.keys();
        ArrayList<String> key = new ArrayList<>();
        while (iterator.hasNext()) {
            key.add((String) iterator.next());
            count++;
        }
        return key;
    }
    public int getCount(){
        return count;
    }

    public void setCountt(int countt) {
        this.countt = countt;
    }
    public int getCounttt(){
        return countt;
    }
}
