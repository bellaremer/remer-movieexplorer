package remer.movieexplorer.json;

import com.google.gson.annotations.SerializedName;

public class StreamingOption
{
    @SerializedName("service_name")
    public String serviceName;

    @SerializedName("type")
    public String type;

    @SerializedName("link")
    public String link;

    @SerializedName("quality")
    public String quality;

    @SerializedName("price")
    public String price;

    @SerializedName("expires_on")
    public Long expiresOn;
}
