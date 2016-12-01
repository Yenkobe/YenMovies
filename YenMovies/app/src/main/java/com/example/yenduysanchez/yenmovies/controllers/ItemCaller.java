package com.example.yenduysanchez.yenmovies.controllers;

import android.os.AsyncTask;
import com.example.yenduysanchez.yenmovies.helpers.IJSONParser;
import com.example.yenduysanchez.yenmovies.models.DetailItem;

import java.util.List;

/**
 * Created by Yenduy Sanchez on 11/17/2016.
 */

public class ItemCaller extends AsyncTask<Void, Void, List<DetailItem>> {

    private ItemCallerResponse callerResponse;
    private String stringUrl;
    private IJSONParser parser;

    public ItemCaller(String stringUrl, IJSONParser parser, ItemCallerResponse callerResponse){
        this.callerResponse=callerResponse;
        this.stringUrl = stringUrl;
        this.parser = parser;
    }

    @Override
    protected List<DetailItem> doInBackground(Void... params) {
        List<DetailItem> itemList = new ItemController().getItemList(stringUrl, parser);
        return itemList;
    }

    @Override
    protected void onPostExecute(List<DetailItem> itemList) {
        callerResponse.onCompleted(itemList);
    }
}