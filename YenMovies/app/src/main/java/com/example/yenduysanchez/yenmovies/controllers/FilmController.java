package com.example.yenduysanchez.yenmovies.controllers;

import com.example.yenduysanchez.yenmovies.helpers.IFilmJSONParser;
import com.example.yenduysanchez.yenmovies.models.FilmItem;

/**
 * Created by Yenduy Sanchez on 11/19/2016.
 */

public class FilmController implements IFilmController {
    @Override
    public FilmItem getFilmItem(String stringUrl, IFilmJSONParser parser) {
        String response = new HttpRetriever().getStringResponse(stringUrl);
        return parser.parseResponse(response);
    }
}

