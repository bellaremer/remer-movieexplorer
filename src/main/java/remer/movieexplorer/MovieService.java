package remer.movieexplorer;

import io.reactivex.rxjava3.core.Single;
import remer.movieexplorer.json.MovieDetailResponse;
import remer.movieexplorer.json.MovieSearchResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService
{
    @GET("/")
    Single<MovieSearchResponse> searchMovies(
            @Query("s") String search,
            @Query("apikey") String apiKey
    );

    @GET("/")
    Single<MovieDetailResponse> getMovieDetail(
            @Query("i") String imdbId,
            @Query("apikey") String apiKey
    );

}
