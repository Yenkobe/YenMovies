package com.example.yenduysanchez.yenmovies.views;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yenduysanchez.yenmovies.helpers.FilmDetailParser;
import com.example.yenduysanchez.yenmovies.models.DetailItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yenduy Sanchez on 11/20/2016.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    final public static String TAG = "ItemAdapter";

    public static final int TOPRATED_FILMS = 1;
    public static final int MOSTPOPULAR_FILMS = 2;
    public static final int SEARCH_FILMS = 6;

    private static final String API_KEY = "&api_key=5300b939a689dbb7cc14fae0dc554a9e";
    private static final String TOPRATED_FILMS_URL = "http://api.themoviedb.org/3/movie/top_rated?page=";
    private static final String MOSTPOPULAR_FILMS_URL = "http://api.themoviedb.org/3/movie/popular?page=";
    private static final String SEARCH_FILMS_URL = "http://api.themoviedb.org/3/search/movie?api_key=5300b939a689dbb7cc14fae0dc554a9e&page=";

    private int type = TOPRATED_FILMS;
    private LayoutInflater inflator;
    private List<DetailItem> detailItemList;
    private View.OnClickListener onClickListener;
    private ImageDownloader imageDownloader;
    private int page = 1;
    private String nameItemToSearch;

    public ItemAdapter(Context context) {
        inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        detailItemList = new ArrayList<DetailItem>();
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setNameItemToSearch(String name) {
        nameItemToSearch = name;
//        recalculateList();
    }

    public DetailItem getDetailItem(int position) {
        return detailItemList.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflator.inflate(R.layout.selector_item, null);
        v.setOnClickListener(onClickListener);
        return new ViewHolder(v);
    }


    @Override
    public int getItemCount() {
        return detailItemList.size();
    }

    public void setOnItemClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setItemType(int type) {
        this.type = type;
        recalculateList();
    }

    public void recalculateList() {
        Log.d(TAG, String.valueOf(page));
        ItemCaller itemCaller = null;
        switch (type) {
            case TOPRATED_FILMS:
                itemCaller = new ItemCaller(TOPRATED_FILMS_URL + String.valueOf(page) + API_KEY, new FilmDetailParser(), new ItemCallerResponse() {
                    @Override
                    public void onCompleted(List<DetailItem> itemList) {
                        if (page == 1)
                            detailItemList = itemList;
                        else
                            detailItemList.addAll(itemList);
//                        notifyDataSetChanged();
                        notifyItemRangeChanged(0, getItemCount());
//                        notifyItemRangeInserted(0 , detailItemList.size()-1);
                    }
                });
                break;
            case MOSTPOPULAR_FILMS:
                itemCaller = new ItemCaller(MOSTPOPULAR_FILMS_URL + String.valueOf(page) + API_KEY, new FilmDetailParser(), new ItemCallerResponse() {
                    @Override
                    public void onCompleted(List<DetailItem> itemList) {
                        if (page == 1)
                            detailItemList = itemList;
                        else
                            detailItemList.addAll(itemList);
//                        notifyDataSetChanged();
                        notifyItemRangeChanged(0, getItemCount());
//                        notifyItemRangeInserted(0 , detailItemList.size()-1);
                    }
                });
                break;
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView image;
            public TextView title;
            public TextView releaseDate;
            public TextView votes;
            public TextView score;

            public ViewHolder(View itemView) {
                super(itemView);
                image = (ImageView) itemView.findViewById(R.id.image);
                title = (TextView) itemView.findViewById(R.id.title);
                releaseDate = (TextView) itemView.findViewById(R.id.release_date);
                votes = (TextView) itemView.findViewById(R.id.vote_count);
                score = (TextView) itemView.findViewById(R.id.score);
            }
        }
    }


