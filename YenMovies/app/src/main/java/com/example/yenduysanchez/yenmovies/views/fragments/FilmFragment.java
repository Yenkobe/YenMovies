package com.example.yenduysanchez.yenmovies.views.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yenduysanchez.yenmovies.PlayVideoActivity;
import com.example.yenduysanchez.yenmovies.controllers.FilmItemCaller;
import com.example.yenduysanchez.yenmovies.controllers.FilmItemCallerResponse;
import com.example.yenduysanchez.yenmovies.helpers.ImageDownloader;
import com.example.yenduysanchez.yenmovies.models.FilmItem;

/**
 * Created by Yenduy Sanchez on 11/20/2016.
 */

public class FilmFragment extends Fragment {

    private static final String TAG = "FilmFragment";
    public static final String ARG_ID = "arg_id";
    private static final String BASE_URL = "http://api.themoviedb.org/3/movie/";
    private static final String API_KEY = "?api_key=5300b939a689dbb7cc14fae0dc554a9e";

    private Activity activity;
    private FilmItem film;
    private FilmItemCaller caller;
    private ImageDownloader imageDownloader;
    private TextView title;
    private TextView genre;
    private TextView releaseDate;
    private TextView score;
    private TextView votes;
    private TextView tagLine;
    private TextView description;
    private TextView productCompanies;
    private TextView productCountries;
    private TextView budget;
    private TextView runtime;
    private TextView revenue;
    private ImageView image;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.activity = (Activity) context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_item_film, container, false);

        title = (TextView) view.findViewById(R.id.original_title);
        releaseDate = (TextView) view.findViewById(R.id.release_date);
        genre = (TextView) view.findViewById(R.id.genre);
        score = (TextView) view.findViewById(R.id.score);
        votes = (TextView) view.findViewById(R.id.vote_count);
        tagLine = (TextView) view.findViewById(R.id.tagline);
        description = (TextView) view.findViewById(R.id.description);
        productCompanies = (TextView) view.findViewById(R.id.production_companies);
        productCountries = (TextView) view.findViewById(R.id.production_countries);
        budget = (TextView) view.findViewById(R.id.budget);
        runtime = (TextView) view.findViewById(R.id.runtime);
        revenue = (TextView) view.findViewById(R.id.revenue);
        image = (ImageView) view.findViewById(R.id.front_image);

        Bundle args = getArguments();
        if (args != null) {
            int id = args.getInt(ARG_ID);
            showItem(id, view);
        } else {
//            ponInfoLibro(0, vista);
        }

        return view;
    }

    public void showItem(int id) {
        showItem(id, getView());
    }

    private void showItem(int id, final View view) {
        caller = new FilmItemCaller(BASE_URL + String.valueOf(id) + API_KEY, new FilmJSONParser(), new FilmItemCallerResponse() {
            @Override
            public void onCompleted(FilmItem filmItem) {
                film = filmItem;
                title.setText(filmItem.getTitle());
                genre.setText(filmItem.getGenre());
                releaseDate.setText(filmItem.getReleaseDate());
                score.setText(String.valueOf(filmItem.getPoints()));
                votes.setText(String.valueOf(filmItem.getVotes()));
                tagLine.setText(filmItem.getTagline());
                description.setText(filmItem.getOverview());
                productCompanies.setText(filmItem.getProductionCompanies());
                productCountries.setText(filmItem.getProductionCountries());
                budget.setText(filmItem.getBudget()+" $");
                runtime.setText(filmItem.getRunTime()+" Min.");
                revenue.setText(filmItem.getRevenue()+" $");
                imageDownloader = new ImageDownloader("http://image.tmdb.org/t/p/w500"+filmItem.getImageUrl(), image);
                imageDownloader.execute();
            }
        });
        caller.execute();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu)
//    {
//        MenuItem itemPlay = menu.findItem(R.id.menu_play);
//        itemPlay.setVisible(false);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_play:
                Log.d(TAG, "HOLA");
                if(film.hasVideo()){
                    Intent intent = new Intent(activity, PlayVideoActivity.class);
                    intent.putExtra("url", "http://www.youtube.com/watch?v="+film.getVideoKey());
                    startActivity(intent);
                } else
                    Toast.makeText(activity, "This movie does not contain a trailer video", Toast.LENGTH_SHORT).show();
                return true;
            default:
                break;
        }

        return false;
    }
}