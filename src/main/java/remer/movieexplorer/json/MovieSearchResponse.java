package remer.movieexplorer.json;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MovieSearchResponse
{
    @SerializedName("Search")
    public List<Movie> search;
}
