package remer.movieexplorer;

import com.andrewoid.apikeys.ApiKey;
import org.junit.jupiter.api.Test;
import remer.movieexplorer.json.ShowResponse;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StreamingInfoServiceTest
{
    @Test
    public void getStreamingInfoReturnsResults()
    {
        // given
        StreamingInfoService service = new StreamingInfoServiceFactory().getService();
        ApiKey streamingApiKey = new ApiKey("streaming_api_key");
        String apiKeyString = streamingApiKey.get();
        String host = "streaming-availability.p.rapidapi.com";
        String imdbId = "tt2294629"; // Ex: Frozen (2013)
        String country = "us";

        // when
        ShowResponse response = service.getStreamingInfo(
                imdbId,
                country,
                apiKeyString,
                host
        ).blockingGet();

        // then
        assertNotNull(response, "ShowResponse should not be null");
        assertNotNull(response.streamingOptions, "Streaming options should not be null");
        assertTrue(response.streamingOptions.containsKey(country), "Should contain country key");
        assertNotNull(response.streamingOptions.get(country), "Streaming options list should not be null");
        assertTrue(response.streamingOptions.get(country).size() > 0, "Should return at least one streaming option");
    }
}
