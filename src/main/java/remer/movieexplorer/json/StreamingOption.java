package remer.movieexplorer.json;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class StreamingOption
{
    @SerializedName("service")
    public JsonObject service;

    @SerializedName("type")
    public String type;

    @SerializedName("link")
    public String link;

    @SerializedName("quality")
    public String quality;

    @SerializedName("price")
    public Price price;

    @SerializedName("addOn")
    public String addOn;

    @SerializedName("leaving")
    public Long leaving;
}
