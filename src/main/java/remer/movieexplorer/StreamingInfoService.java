package remer.movieexplorer;

import io.reactivex.rxjava3.core.Single;
import remer.movieexplorer.json.ShowResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StreamingInfoService
{
    @GET("/api/v1/get")
    Single<ShowResponse> getStreamingInfo(
            @Query("imdb_id") String imdbId,
            @Query("api_key") String apiKey
    );
}
