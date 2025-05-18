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

    public MovieController(JPanel gridPanel, MovieService service, ApiKey apiKey)
    {
        this.gridPanel = gridPanel;
        this.service = service;
        this.apiKey = apiKey;
    }

    public void searchAndDisplay(String query)
    {
        service.searchMovies(query, apiKey.get())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.from(SwingUtilities::invokeLater))
                .subscribe(
                        this::handleResponse,
                        e -> showError("Error: " + e.getMessage())
                );
    }

    private void handleResponse(MovieSearchResponse response)
    {
        gridPanel.removeAll();
        gridPanel.setLayout(new GridLayout(3, 3, 2, 2));

        if (response.Search == null || response.Search.isEmpty())
        {
            showError("No results found.");
            return;
        }

        int count = 0;
        for (Movie movie : response.Search)
        {
            if (count >= 9) break; // Show only 9 posters

            JLabel label = new JLabel("<html>" + movie.Title + " (" + movie.Year + ")</html>", JLabel.CENTER);
            label.setVerticalTextPosition(JLabel.BOTTOM);
            label.setHorizontalTextPosition(JLabel.CENTER);

            // Set the poster image if available
            if (movie.Poster != null && !movie.Poster.equalsIgnoreCase("N/A"))
            {
                try
                {
                    Image img = ImageIO.read(new URL(movie.Poster));
                    if (img != null)
                    {
                        ImageIcon icon = new ImageIcon(img.getScaledInstance(150, 220, Image.SCALE_SMOOTH));
                        label.setIcon(icon);
                    }
                } catch (IOException ignored) {}
            }

            // Add mouse listener for detail popup
            label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            label.addMouseListener(new java.awt.event.MouseAdapter()
            {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt)
                {
                    showMovieDetail(movie.imdbID);
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

    private void showMovieDetail(String imdbID)
    {
        service.getMovieDetail(imdbID, apiKey.get())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.from(SwingUtilities::invokeLater))
                .subscribe(
                        movieDetail -> {
                            MovieDetailFrame detailFrame = new MovieDetailFrame(movieDetail);
                            detailFrame.setLocationRelativeTo(null);
                            detailFrame.setVisible(true);
                        },
                        e -> JOptionPane.showMessageDialog(gridPanel, "Error loading details: " + e.getMessage())
                );
    }

    private void showError(String message)
    {
        gridPanel.removeAll();
        gridPanel.setLayout(new GridLayout(1, 1));
        gridPanel.add(new JLabel(message, JLabel.CENTER));
        gridPanel.revalidate();
        gridPanel.repaint();
    }
}
