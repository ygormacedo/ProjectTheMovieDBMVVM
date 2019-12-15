package br.com.zup.projectmovielistmvvm.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import br.com.zup.projectmovielistmvvm.data.ApiService;
import br.com.zup.projectmovielistmvvm.data.response.MovieResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    public LiveData<MovieResponse> getMovie(String apiKey, String language) {
        final MutableLiveData<MovieResponse> data = new MutableLiveData<>();

        ApiService.getInstance().getMovieResponse(apiKey, language).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
