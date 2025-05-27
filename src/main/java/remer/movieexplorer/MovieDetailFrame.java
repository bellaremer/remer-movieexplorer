package remer.movieexplorer;

import remer.movieexplorer.json.MovieDetailResponse;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class MovieDetailFrame extends JFrame
{
    public MovieDetailFrame(MovieDetailResponse movie)
    {
        setTitle(movie.title + " (" + movie.year + ")");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(650, 340);

        // Main content panel with padding
        JPanel content = new JPanel(new BorderLayout(20, 0));
        content.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(content);

        // Info Panel (Left)
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);

        JLabel titleLabel = new JLabel("<html><b>"
                        + movie.title + "</b> <span style='color:gray;'>("
                        + movie.year + ")</span></html>");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        infoPanel.add(titleLabel);
        infoPanel.add(Box.createVerticalStrut(12));

        JLabel actorsLabel = new JLabel("<html><b>Actors:</b> " + movie.actors + "</html>");
        actorsLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
        infoPanel.add(actorsLabel);
        infoPanel.add(Box.createVerticalStrut(8));

        JLabel genreLabel = new JLabel("<html><b>Genre:</b> " + movie.genre + "</html>");
        genreLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
        infoPanel.add(genreLabel);
        infoPanel.add(Box.createVerticalStrut(8));

        JLabel ratingLabel = new JLabel("<html><b>IMDB Rating:</b> " + movie.imdbRating + "</html>");
        ratingLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
        infoPanel.add(ratingLabel);
        infoPanel.add(Box.createVerticalStrut(12));

        JTextArea plotArea = new JTextArea(movie.plot);
        plotArea.setLineWrap(true);
        plotArea.setWrapStyleWord(true);
        plotArea.setEditable(false);
        plotArea.setOpaque(false);
        plotArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        plotArea.setBorder(null);
        infoPanel.add(new JLabel("<html><b>Plot:</b></html>"));
        infoPanel.add(plotArea);

        content.add(infoPanel, BorderLayout.CENTER);

        // Poster Panel (Right)
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setOpaque(false);

        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        imageLabel.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
        imageLabel.setOpaque(true);
        imageLabel.setBackground(new Color(245, 245, 245));

        if (movie.poster != null && !movie.poster.equalsIgnoreCase("N/A"))
        {
            try
            {
                URL url = new URL(movie.poster);
                Image image = ImageIO.read(url);
                if (image != null)
                {
                    Image scaledImage = image.getScaledInstance(200, 280, Image.SCALE_SMOOTH);
                    imageLabel.setIcon(new ImageIcon(scaledImage));
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        } else
        {
            imageLabel.setText("No Image");
        }

        imagePanel.add(imageLabel, BorderLayout.CENTER);
        content.add(imagePanel, BorderLayout.EAST);

        // Pop up in the bottom-right corner of the screen
        SwingUtilities.invokeLater(() -> {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Dimension screenSize = toolkit.getScreenSize();
            int x = screenSize.width - getWidth() - 40;
            int y = screenSize.height - getHeight() - 80;
            setLocation(x, y);
        });
    }
}
