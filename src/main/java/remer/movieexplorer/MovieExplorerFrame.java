package remer.movieexplorer;

import com.andrewoid.apikeys.ApiKey;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MovieExplorerFrame extends JFrame
{
    public MovieExplorerFrame()
    {
        super("Movie Explorer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);

        final JPanel mainPanel = new JPanel(new BorderLayout(3, 3));

        // Top panel for search field and button
        JPanel searchPanel = new JPanel(new BorderLayout(2, 2));
        JTextField searchField = new JTextField();
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        JButton searchButton = new JButton("Search");
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        mainPanel.add(searchPanel, BorderLayout.NORTH);

        JPanel gridPanel = new JPanel(new GridLayout(3, 3, 2, 2));
        JScrollPane scrollPane = new JScrollPane(gridPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        MovieService service = new MovieServiceFactory().getService();
        ApiKey apiKey = new ApiKey();
        MovieController controller = new MovieController(gridPanel, service, apiKey, searchField);

        Runnable doSearch = controller::searchAndDisplay;

        searchField.addActionListener((ActionEvent e) -> doSearch.run());
        searchButton.addActionListener((ActionEvent e) -> doSearch.run());

        setContentPane(mainPanel);
        setLocationRelativeTo(null); // Center on screen
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> {
            MovieExplorerFrame frame = new MovieExplorerFrame();
            frame.setVisible(true);
        });
    }
}
