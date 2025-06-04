package remer.movieexplorer;

import io.reactivex.rxjava3.core.Single;
import remer.movieexplorer.json.ShowResponse;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface StreamingInfoService
{
    @GET("shows/{type}/{id}")
    Single<ShowResponse> getStreamingInfo(
            @Path("type") String type,
            @Path("id") String imdbId,
            @Header("x-rapidapi-key") String apiKey,
            @Header("x-rapidapi-host") String host
    );
}
