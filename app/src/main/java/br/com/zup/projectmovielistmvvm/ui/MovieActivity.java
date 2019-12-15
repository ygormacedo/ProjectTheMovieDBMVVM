package br.com.zup.projectmovielistmvvm.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import br.com.zup.projectmovielistmvvm.R;

public class MovieActivity extends AppCompatActivity {

    MovieViewModel movieViewModel;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);

        getListMovie();
        recyclerView = findViewById(R.id.rvMovie);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

    }

    private void getListMovie() {
        movieViewModel.getmMovieResponseLiveData().observe(this, movieResponse -> {
            if (movieResponse != null) {
                MovieAdapter movieAdapter = new MovieAdapter(movieResponse.getMovieResults());
                recyclerView.setAdapter(movieAdapter);
            }
        });
    }
}
