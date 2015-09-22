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
                value[j] = jsonObject.optString(key.get(j)).toString();
            frmula.add((value[0]));
            ab.add(value[1]);
            ab1.add((value[2]));
            urrl.add(value[3]);
        }
        ab11.add(urrl);
        ab11.add(frmula);
        ab11.add(ab);
        ab11.add(ab1);
        ab11.add(urrl);
        ab11.add(frmula);
        ab11.add(ab);
        ab11.add(ab1);
        return ab11;
    }

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

    public int getCount() {
        return count;
    }
}
