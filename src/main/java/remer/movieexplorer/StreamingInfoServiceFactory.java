package remer.movieexplorer;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class StreamingInfoServiceFactory
{
    public StreamingInfoService getService()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://streaming-availability.p.rapidapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        return retrofit.create(StreamingInfoService.class);
    }
}
