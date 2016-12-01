package com.example.yenduysanchez.yenmovies.controllers;

import com.example.yenduysanchez.yenmovies.helpers.IJSONParser;
import com.example.yenduysanchez.yenmovies.models.DetailItem;

import java.util.List;

/**
 * Created by Yenduy Sanchez on 11/17/2016.
 */
public class ItemController implements IItemController {

    @Override
    public List<DetailItem> getItemList(String stringUrl, IJSONParser parser) {
        String response = new HttpRetriever().getStringResponse(stringUrl);
        return parser.parseResponse(response);
    }
}
