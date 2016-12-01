package com.example.yenduysanchez.yenmovies.helpers;

import com.example.yenduysanchez.yenmovies.models.DetailItem;

import java.util.List;

/**
 * Created by Yenduy Sanchez on 11/20/2016.
 */
public interface IJSONParser {
    List<DetailItem> parseResponse(String response);
}