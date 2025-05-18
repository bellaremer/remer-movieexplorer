package remer.movieexplorer;

import com.andrewoid.apikeys.ApiKey;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MovieExplorerFrame
{
    private static void createAndShowGUI()
    {
        JFrame frame = new JFrame("Movie Explorer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700);

        JPanel mainPanel = new JPanel(new BorderLayout(3, 3));

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
        MovieController controller = new MovieController(gridPanel, service, apiKey);

        // Shared action for both Enter key and button
        Runnable doSearch = () -> {
            String query = searchField.getText().trim();
            if (!query.isEmpty()) {
                controller.searchAndDisplay(query);
            }
        };

        searchField.addActionListener((ActionEvent e) -> doSearch.run());
        searchButton.addActionListener((ActionEvent e) -> doSearch.run());

        frame.setContentPane(mainPanel);
        frame.setVisible(true);
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(MovieExplorerFrame::createAndShowGUI);
    }
}
