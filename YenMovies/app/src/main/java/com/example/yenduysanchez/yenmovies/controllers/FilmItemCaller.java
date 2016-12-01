package com.example.yenduysanchez.yenmovies.controllers;

import android.os.AsyncTask;

import com.example.yenduysanchez.yenmovies.helpers.IFilmJSONParser;
import com.example.yenduysanchez.yenmovies.models.FilmItem;

/**
 * Created by Yenduy Sanchez on 11/19/2016.
 */

public class FilmItemCaller extends AsyncTask<Void, Void, FilmItem> {

    private FilmItemCallerResponse callerResponse;
    private String stringUrl;
    private IFilmJSONParser parser;

    public FilmItemCaller(String stringUrl, IFilmJSONParser parser, FilmItemCallerResponse callerResponse) {
        this.callerResponse = callerResponse;
        this.stringUrl = stringUrl;
        this.parser = parser;
    }

    @Override
    protected FilmItem doInBackground(Void... params) {
        FilmItem item = new FilmController().getFilmItem(stringUrl, parser);
        return item;
    }

    @Override
    protected void onPostExecute(FilmItem filmItem) {
        callerResponse.onCompleted(filmItem);
    }
}