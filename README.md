# Movie Explorer
A simple Java application for searching movies and viewing details using the OMDb API and RapidAPI's Streaming Availability service.

## Description
Movie Explorer allows users to search for movies by title, view a grid of movie posters, and click on any movie to see detailed information including plot, actors, genre, and IMDb rating. The app uses RxJava and Retrofit for asynchronous API calls and displays results in a modern Swing GUI.
When viewing movie details, Movie Explorer gets real-time streaming availability for each title using the Streaming Availability API on RapidAPI. This will show which platforms the movie is available on, including service name, type (subscription, rent, buy), quality, price, and a direct link to watch.

## Screenshots
### Main Search Screen
![Main Screen](screenshots/main_screenshot.png)

### Movie Details Popup
![Details Popup](screenshots/detail_screenshot.png)

### Movie Streaming Screen 
![Streaming Information](screenshots/streaming_screenshot.png)


## Authors
**Bella Remer**
([@bellaremer](https://github.com/bellaremer))

## Acknowledgments
- [OMDb API](https://www.omdbapi.com/)
- [awesome-readme][1](https://gist.github.com/DomPizzie/7a5ff55ffa9081f2de27c315f5018afc)
- Java Swing community

