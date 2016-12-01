package com.example.yenduysanchez.yenmovies.helpers;

import com.example.yenduysanchez.yenmovies.models.FilmItem;



/**
 * Created by Yenduy Sanchez on 11/19/2016.
 */
public interface IFilmJSONParser {
    FilmItem parseResponse(String response);
}
