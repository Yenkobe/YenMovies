package com.example.yenduysanchez.yenmovies.controllers;

import com.example.yenduysanchez.yenmovies.models.DetailItem;

import java.util.List;

/**
 * Created by Yenduy Sanchez on 11/17/2016.
 */

public interface ItemCallerResponse {
    public void onCompleted(List<DetailItem> itemList);
}
