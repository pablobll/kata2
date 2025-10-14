import software.ulpgc.kata2.io.RemoteMovieLoader;
import software.ulpgc.kata2.model.Movie;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        RemoteMovieLoader movieLoader = new RemoteMovieLoader();
        List<Movie> movies = movieLoader.loadAll();
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }
}
