package com.example.sjayanna.rottentomatoes;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MoviesActivity extends ActionBarActivity {
    private ListView lvMovies;
    private BoxOfficeMoviesAdapter adapterMovies;
    private RottenTomatoClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        // 1. Create an array adapter
        // 2. Get list of the movies
        // 3. Get the listview
        // 4. Bind the listview to the adapter
        lvMovies = (ListView) findViewById(R.id.lvMovies);
        ArrayList<BoxOfficeMovie> aMovies = new ArrayList<BoxOfficeMovie>();
        adapterMovies = new BoxOfficeMoviesAdapter(this, aMovies);
        lvMovies.setAdapter(adapterMovies);
        fetchBoxOfficeMovies();
        setupMovieSelectedListener();
    }

    private void setupMovieSelectedListener() {
        lvMovies.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MoviesActivity.this, MovieDetailActivity.class);
                i.putExtra("movie", adapterMovies.getItem(position));
                startActivity(i);
            }
        });
    }

    private void fetchBoxOfficeMovies() {
        client = new RottenTomatoClient();
        client.getBoxOfficeMovies(new JsonHttpResponseHandler() {

            /**
             * Returns when request succeeds
             *
             * @param statusCode http response status line
             * @param headers    response headers if any
             * @param response   parsed response if any
             */
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray items = null;
                try {
                    items = response.getJSONArray("movies");
                    ArrayList<BoxOfficeMovie> movies = BoxOfficeMovie.fromJson(items);

                    for(BoxOfficeMovie movie : movies) {
                        adapterMovies.add(movie);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        } );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movies, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
