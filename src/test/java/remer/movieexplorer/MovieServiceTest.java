package remer.movieexplorer;

import com.andrewoid.apikeys.ApiKey;
import org.junit.jupiter.api.Test;
import remer.movieexplorer.json.MovieSearchResponse;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MovieServiceTest
{
    @Test
    public void searchMoviesReturnsResults()
    {
        // given
        MovieService service = new MovieServiceFactory().getService();
        ApiKey apiKey = new ApiKey();
        String keyString = apiKey.get();

        // when
        MovieSearchResponse response = service.searchMovies(
                "Frozen",
                keyString
        ).blockingGet();

        // then
        assertNotNull(response.search, "Search results should not be null");
        assertTrue(response.search.size() > 0, "Should return at least one movie");
    }
}
