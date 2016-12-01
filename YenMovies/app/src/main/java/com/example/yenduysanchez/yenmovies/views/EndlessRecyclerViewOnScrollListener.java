package com.example.yenduysanchez.yenmovies.views;

import android.util.Log;

/**
 * Created by Yenduy Sanchez on 11/20/2016.
 */

public class EndlessRecyclerViewOnScrollListener extends RecyclerView.OnScrollListener {
    public static String TAG = EndlessRecyclerViewOnScrollListener.class.getSimpleName();

    private int previousTotal = 0; // The total number of items in the dataset after the last load
    private boolean loading = true; // True if we are still waiting for the last set of data to load.
    private int visibleThreshold = 5; // The minimum amount of items to have below your current scroll position before loading more.
    int firstVisibleItem, visibleItemCount, totalItemCount;

    private int current_page = 1;

    private LinearLayoutManager mLinearLayoutManager;

    public EndlessRecyclerViewOnScrollListener(LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mLinearLayoutManager.getItemCount();
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();


        if (loading && totalItemCount > previousTotal) {
            loading = false;
            previousTotal = totalItemCount;
            Log.d(TAG, "PASA POR AQUI, loading = "+String.valueOf(loading));
            Log.d(TAG, "current page = "+String.valueOf(current_page));
        }

        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
            // End has been reached
            // Do something
//            loading = true;
            current_page++;
            onLoadMore(current_page);
            loading = true;
            Log.d(TAG, "PASA POR AQUI");
            Log.d(TAG, "current page = "+String.valueOf(current_page));
        }
    }

    public abstract void onLoadMore(int current_page);
}
