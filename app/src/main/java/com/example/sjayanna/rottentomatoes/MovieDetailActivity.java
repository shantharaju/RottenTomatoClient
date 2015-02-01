package com.example.sjayanna.rottentomatoes;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class MovieDetailActivity extends ActionBarActivity {

    private ImageView ivPosterImage;
    private TextView tvTitle;
    private TextView tvCriticsScore;
    private TextView tvAudienceScore;
    private TextView tvCast;
    private TextView tvCriticsConsensus;
    private TextView tvSynopsis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ivPosterImage = (ImageView) findViewById(R.id.ivPosterImage);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvCriticsScore = (TextView) findViewById(R.id.tvCriticScore);
        tvAudienceScore = (TextView) findViewById(R.id.tvAudienceScore);
        tvCast = (TextView) findViewById(R.id.tvCast);
        tvCriticsConsensus = (TextView) findViewById(R.id.tvCriticsConsensus);
        tvSynopsis = (TextView) findViewById(R.id.tvSynopsis);

        BoxOfficeMovie movie = (BoxOfficeMovie) getIntent().getSerializableExtra("movie");
        loadMovie(movie);
    }

    private void loadMovie(BoxOfficeMovie movie) {

        Picasso.with(this).load(movie.getLargePosterUrl()).placeholder(R.drawable.ic_movieplaceholder).into(ivPosterImage);
        tvTitle.setText(movie.getTitle());
        tvCriticsScore.setText("Critics score: " + movie.getCriticsScore());
        tvAudienceScore.setText("Audience score: " + movie.getAudienceScore());
        tvCast.setText(movie.getCastList());
        tvCriticsConsensus.setText("Consensus: " + movie.getCriticsConsensus());
        tvSynopsis.setText("Synopsis: " + movie.getSynopsis());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movie_detail, menu);
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
