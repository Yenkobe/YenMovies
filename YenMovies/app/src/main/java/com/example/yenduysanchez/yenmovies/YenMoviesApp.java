package com.example.yenduysanchez.yenmovies;

import android.app.Application;

import com.example.yenduysanchez.yenmovies.views.ItemAdapter;

/**
 * Created by Yenduy Sanchez on 11/20/2016.
 */

public class YenMoviesApp extends Application {

    //    private FilteredItemAdapter adapter;
//    private List<DetailItem> itemList;
    private ItemAdapter adapter;

    @Override
    public void onCreate() {
//        itemList = new ArrayList<DetailItem>();
//        adapter = new FilteredItemAdapter(this, itemList);
//        adapter = new FilteredItemAdapter(this);
        adapter = new ItemAdapter(this);
    }

    public ItemAdapter getAdapter() {
        return adapter;
    }

}

