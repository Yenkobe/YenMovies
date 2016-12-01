package com.example.yenduysanchez.yenmovies.controllers;

import com.example.yenduysanchez.yenmovies.models.FilmItem;

/**
 * Created by Yenduy Sanchez on 11/19/2016.
 */

public interface FilmItemCallerResponse {
    void onCompleted(FilmItem filmItem);
}
