package com.example.mubashshir.newyorktimesstoriesapi;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Story implements Serializable {

    String thumbnail;

    String headline;


    public String getHeadline() {

        return this.headline;
    }

    public String getThumbnail() {
        return this.thumbnail;
    }


    public Story(JSONObject jsonObject) {
        try {
            this.headline = jsonObject.getString("title");

            JSONArray multimedia = jsonObject.getJSONArray("multimedia");


            if (multimedia.length() > 0) {
                JSONObject multimediaJson = multimedia.getJSONObject(4);
                this.thumbnail =  multimediaJson.getString("url");
            } else {
                this.thumbnail = "";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}


