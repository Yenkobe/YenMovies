package com.example.yenduysanchez.yenmovies;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.yenduysanchez.yenmovies.models.DetailItem;
import com.example.yenduysanchez.yenmovies.views.ItemAdapter;
import com.example.yenduysanchez.yenmovies.views.fragments.FilmFragment;
import com.example.yenduysanchez.yenmovies.views.fragments.SelectorFragment;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {

    private ItemAdapter adapter;
    private String type = "film";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = ((YenMoviesApp) getApplicationContext()).getAdapter();

        if ((findViewById(R.id.small_container) != null) && (getSupportFragmentManager().findFragmentById(R.id.small_container) == null)) {
            SelectorFragment firstFragment = new SelectorFragment();
            getFragmentManager().beginTransaction().add(R.id.small_container, firstFragment).commit();

        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Save UI state changes to the savedInstanceState.(UI 상태 변경 사항을 savedInstanceState에 저장합니다.)
        // This bundle will be passed to onCreate if the process is.(이 번들은 프로세스가 onCreate 인 경우 onCreate에 전달됩니다.)
        // killed and restarted.(살해되고 다시 시작됩니다.)
        savedInstanceState.putString("type", type);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        type = savedInstanceState.getString("type");
    }

    @SuppressWarnings("StatementWithEmptyBody")

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.film_most_popular) {
            adapter.setPage(1);
            adapter.setItemType(ItemAdapter.MOSTPOPULAR_FILMS);
            type = "film";
        } else if (id == R.id.film_top_rated) {
            adapter.setPage(1);
            adapter.setItemType(ItemAdapter.TOPRATED_FILMS);
            type = "film";

        }
        // adapter.notifyDataSetChanged();
        // adapter.recalculateList();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        if (getFragmentManager().getBackStackEntryCount() != 0)
            getFragmentManager().popBackStack();
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (getFragmentManager().getBackStackEntryCount() != 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
    {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu)
    {
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                callSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }

            public void callSearch(String query) {
                adapter.setNameItemToSearch(query);
                switch (type) {
                    case "film":
                        adapter.setPage(1);
                        adapter.setItemType(ItemAdapter.SEARCH_FILMS);
                        break;
                }
                adapter.recalculateList();
            }
        });
        MenuItem itemPlay = menu.findItem(R.id.menu_play);
        itemPlay.setVisible(false);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search:

                break;
            case R.id.menu_about:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.about_message);
                builder.setPositiveButton(android.R.string.ok, null);
                builder.create().show();
                break;
        }
        return false;
    }

//   public void showSelectedItem(int id) {
//        switch (type) {
//            case "film":
//                createFilmFragment(id);
//                break;
//
//        }

    public void showSelectedItem(DetailItem detailItem) {
        switch (type) {
            case "film":
                createFilmFragment(detailItem.getId());
                break;
        }
    }
    private void launchVideoActivity(DetailItem item) {
        Intent intent = new Intent(this, PlayVideoActivity.class);
        intent.putExtra("url", item.getReleaseDate());
        startActivity(intent);

    }

    private void createFilmFragment(int id) {
        FilmFragment filmFragment = (FilmFragment) getFragmentManager().findFragmentById(R.id.item_fragment);
        if (filmFragment != null) {
            filmFragment.showItem(id);
        } else {
            FilmFragment newFilmFragment = new FilmFragment();
            Bundle args = new Bundle();
            args.putInt(FilmFragment.ARG_ID, id);
            newFilmFragment.setArguments(args);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.small_container, newFilmFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

}



