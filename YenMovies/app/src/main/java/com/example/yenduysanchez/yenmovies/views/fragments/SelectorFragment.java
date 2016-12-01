package com.example.yenduysanchez.yenmovies.views.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yenduysanchez.yenmovies.MainActivity;
import com.example.yenduysanchez.yenmovies.views.EndlessRecyclerViewOnScrollListener;
import com.example.yenduysanchez.yenmovies.views.ItemAdapter;

/**
 * Created by Yenduy Sanchez on 11/20/2016.
 */

public class SelectorFragment  extends Fragment {
    private Activity activity;
    private RecyclerView recyclerView;
    //    private LinearLayoutManager managerLayout;
    private ItemAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.activity = (Activity) context;
            TheMovieLibraryApp app = (TheMovieLibraryApp) activity.getApplication();
            adapter = app.getAdapter();
            adapter.recalculateList();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_selector, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager managerLayout = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(managerLayout);
//        adapter.setHasStableIds(true);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new EndlessRecyclerViewOnScrollListener(managerLayout) {
            @Override
            public void onLoadMore(int current_page) {
                adapter.setPage(current_page);
                adapter.recalculateList();
            }
        });
        adapter.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) activity).showSelectedItem(adapter.getDetailItem(recyclerView.getChildAdapterPosition(v)));
            }
        });
        return view;
    }

}
