package remer.movieexplorer.json;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ShowResponse
{
    @SerializedName("imdb_id")
    public String imdbId;

    @SerializedName("title")
    public String title;

    @SerializedName("streaming_options")
    public List<StreamingOption> streamingOptions;
}

