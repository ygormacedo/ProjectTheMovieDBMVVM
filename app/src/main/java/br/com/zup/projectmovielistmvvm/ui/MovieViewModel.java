package br.com.zup.projectmovielistmvvm.ui;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import br.com.zup.projectmovielistmvvm.data.response.MovieResponse;
import br.com.zup.projectmovielistmvvm.util.Constants;

public class MovieViewModel extends AndroidViewModel {

    private MovieRepository mMovieRepository;
    private LiveData<MovieResponse> mMovieResponseLiveData;


    public MovieViewModel(@NonNull Application application) {
        super(application);

        mMovieRepository = new MovieRepository();
        this.mMovieResponseLiveData = mMovieRepository.getMovie(Constants.API_KEY,"pt-br");
    }

    public LiveData<MovieResponse> getmMovieResponseLiveData(){
        return mMovieResponseLiveData;
    }

    //finish
}
