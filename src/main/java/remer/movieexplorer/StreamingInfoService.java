package remer.movieexplorer;

import io.reactivex.rxjava3.core.Single;
import remer.movieexplorer.json.ShowResponse;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface StreamingInfoService
{
    @GET("shows/{imdbId}")
    Single<ShowResponse> getStreamingInfo(
            @Path("imdbId") String imdbId,
            @Query("country") String country,
            @Header("X-RapidAPI-Key") String apiKey,
            @Header("X-RapidAPI-Host") String host
    );

}
