package remer.movieexplorer;

import com.andrewoid.apikeys.ApiKey;
import io.reactivex.rxjava3.schedulers.Schedulers;
import remer.movieexplorer.json.Movie;
import remer.movieexplorer.json.MovieSearchResponse;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class MovieController
{
    private final JPanel gridPanel;
    private final MovieService service;
    private final ApiKey apiKey;
    private final JTextField searchField;
    private final StreamingInfoService streamingService;
    private final String streamingApiKey;

    public MovieController(JPanel gridPanel,
                           MovieService service,
                           ApiKey apiKey,
                           JTextField searchField,
                           StreamingInfoService streamingService,
                           String streamingApiKey)
    {
        this.gridPanel = gridPanel;
        this.service = service;
        this.apiKey = apiKey;
        this.searchField = searchField;
        this.streamingService = streamingService;
        this.streamingApiKey = streamingApiKey;
    }

    public void searchAndDisplay()
    {
        String query = searchField.getText().trim();
        if (query.isEmpty())
        {
            showError("please enter a search term.");
            return;
        }

        service.searchMovies(query, apiKey.get())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.from(SwingUtilities::invokeLater))
                .subscribe(
                        this::handleResponse,
                        e -> e.printStackTrace()
                );
    }

    private void handleResponse(MovieSearchResponse response)
    {
        gridPanel.removeAll();
        gridPanel.setLayout(new GridLayout(3, 3, 2, 2));

        if (response.search == null || response.search.isEmpty())
        {
            showError("No results found.");
            return;
        }

        int count = 0;
        for (Movie movie : response.search)
        {
            if (count >= 9)
            {
                break; // Show only 9 posters
            }

            JLabel label = new JLabel("<html>" + movie.title + " (" + movie.year + ")</html>", JLabel.CENTER);
            label.setVerticalTextPosition(JLabel.BOTTOM);
            label.setHorizontalTextPosition(JLabel.CENTER);

            // Set the poster image if available
            if (movie.poster != null && !movie.poster.equalsIgnoreCase("N/A"))
            {
                try
                {
                    Image img = ImageIO.read(new URL(movie.poster));
                    if (img != null)
                    {
                        ImageIcon icon = new ImageIcon(img.getScaledInstance(150, 220, Image.SCALE_SMOOTH));
                        label.setIcon(icon);
                    }
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

            // Add mouse listener for detail popup
            label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            label.addMouseListener(new java.awt.event.MouseAdapter()
            {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt)
                {
                    showMovieDetail(movie.imdbId);
                }
            });

            gridPanel.add(label);
            count++;
        }
        // Fill empty cells if less than 9 results
        while (count < 9)
        {
            gridPanel.add(new JLabel());
            count++;
        }
        gridPanel.revalidate();
        gridPanel.repaint();
    }

    private void showMovieDetail(String imdbId)
    {
        service.getMovieDetail(imdbId, apiKey.get())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.from(SwingUtilities::invokeLater))
                .subscribe(
                        movieDetail -> {
                            MovieDetailFrame detailFrame = new MovieDetailFrame(movieDetail, streamingService, streamingApiKey);
                            detailFrame.setLocationRelativeTo(null);
                            detailFrame.setVisible(true);
                        },
                        e -> JOptionPane.showMessageDialog(gridPanel, "Error loading details: " + e.getMessage())
                );
    }

    private void showError(String message)
    {
        JOptionPane.showMessageDialog(gridPanel, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
