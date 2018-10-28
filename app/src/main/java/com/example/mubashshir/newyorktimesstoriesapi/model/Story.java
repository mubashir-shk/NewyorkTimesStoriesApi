package com.example.mubashshir.newyorktimesstoriesapi.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Story implements Serializable {

    String storyimage;

    String headline;


    public String getHeadline() {

        return headline;
    }

    public String getImage() {
        return storyimage;
    }


    public Story(JSONObject jsonObject) {
        try {
            this.headline = jsonObject.getString("title");

            JSONArray multimedia = jsonObject.getJSONArray("multimedia");


            if (multimedia.length() > 0) {
                JSONObject multimediaJson = multimedia.getJSONObject(4);
                this.storyimage =  multimediaJson.getString("url");
            } else {
                this.storyimage = "";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}


