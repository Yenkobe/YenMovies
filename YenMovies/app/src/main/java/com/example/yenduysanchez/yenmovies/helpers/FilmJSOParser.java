package com.example.yenduysanchez.yenmovies.helpers;

import android.util.Log;

import com.example.yenduysanchez.yenmovies.models.FilmItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Yenduy Sanchez on 11/20/2016.
 */

public class FilmJSOParser implements IFilmJSONParser {
    private static final String TAG = "FilmJSONParser";

    @Override
    public FilmItem parseResponse(String response) {
        FilmItem film = new FilmItem();
        try {
            JSONObject jsonObject = new JSONObject(response);
            film.setId(jsonObject.getInt("id"));
            film.setTitle(jsonObject.getString("original_title"));
            film.setReleaseDate(jsonObject.getString("release_date"));
            film.setPoints(jsonObject.getInt("vote_average"));
            film.setVotes(jsonObject.getInt("vote_count"));
            film.setImageUrl(jsonObject.getString("poster_path"));
            film.setBudget(jsonObject.getString("budget"));
            film.setRevenue(jsonObject.getString("revenue"));
            JSONArray genreArray = (JSONArray) jsonObject.get("genres");
            film.setGenre(getFields(genreArray));
            JSONArray productionCompaniesArray = (JSONArray) jsonObject.get("production_companies");
            film.setProductionCompanies(getFields(productionCompaniesArray));
            JSONArray productionCountriesArray = (JSONArray) jsonObject.get("production_countries");
            film.setProductionCountries(getFields(productionCountriesArray));
            film.setRunTime(jsonObject.getString("runtime"));
            film.setTagline(jsonObject.getString("tagline"));
            film.setOverview(jsonObject.getString("overview"));
            film.setHasVideo(jsonObject.getBoolean("video"));
            if(film.hasVideo()){
                film.setVideoKey(jsonObject.getString("video_key"));
                Log.d(TAG, film.getTitle()+" has video: "+film.getVideoKey());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return film;
    }

    private String getFields(JSONArray jsonArray){
        StringBuilder builder = new StringBuilder();
        for(int i = 0 ; i<jsonArray.length(); i++)
        {
            try {
                JSONObject object = jsonArray.getJSONObject(i);
                builder.append(object.get("name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(i<jsonArray.length()-1)
                builder.append(", ");
        }
        return builder.toString();
    }

}

