package remer.movieexplorer.json;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Map;

public class ShowResponse
{
    @SerializedName("imdbId")
    public String imdbId;

    @SerializedName("title")
    public String title;

    @SerializedName("streamingOptions")
    public Map<String, List<StreamingOption>> streamingOptions;
}

