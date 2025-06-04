package remer.movieexplorer.json;

import com.google.gson.annotations.SerializedName;

public class MovieDetailResponse
{
    @SerializedName("Title")
    public String title;

    @SerializedName("Year")
    public String year;

    @SerializedName("Genre")
    public String genre;

    @SerializedName("Actors")
    public String actors;

    @SerializedName("Plot")
    public String plot;

    @SerializedName("Poster")
    public String poster;

    @SerializedName("imdbRating")
    public String imdbRating;

    @SerializedName("imdbID")
    public String imdbId;
}
