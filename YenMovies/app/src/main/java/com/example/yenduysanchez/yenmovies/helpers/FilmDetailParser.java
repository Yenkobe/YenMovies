package com.example.yenduysanchez.yenmovies.helpers;

import android.util.Log;

import com.example.yenduysanchez.yenmovies.models.DetailItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yenduy Sanchez on 11/19/2016.
 */

public class FilmDetailParser implements IJSONParser{
    private final static String TAG = "FilmDetailParser";

    public List<DetailItem> parseResponse(String response) {
        Log.d(TAG, response);
        List<DetailItem> filmList = new ArrayList<DetailItem>();
        try {
            JSONObject jsonObject = new JSONObject(response);

            JSONArray jsonArray = (JSONArray) jsonObject.get("results");
            for (int i = 0; i<jsonArray.length(); i++)
            {
                JSONObject object = jsonArray.getJSONObject(i);
                DetailItem detailItem = new DetailItem();
                detailItem.setId(object.getInt("id"));
                detailItem.setTitle(object.getString("title"));
                detailItem.setReleaseDate(object.getString("release_date"));
                detailItem.setPoints(object.getInt("vote_average"));
                detailItem.setVotes(object.getInt("vote_count"));
                detailItem.setImageString(object.getString("poster_path"));
                filmList.add(detailItem);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return filmList;
    }
}

