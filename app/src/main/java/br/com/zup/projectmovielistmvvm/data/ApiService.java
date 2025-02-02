package br.com.zup.projectmovielistmvvm.data;

import androidx.exifinterface.media.ExifInterface;

import java.nio.channels.NonReadableChannelException;
import java.util.concurrent.TimeUnit;

import br.com.zup.projectmovielistmvvm.util.Constants;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    public static Retrofit retrofit;

    public static Service getInstance() {

        //criando o retrofite

        if (retrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit.create(Service.class);
    }
}
