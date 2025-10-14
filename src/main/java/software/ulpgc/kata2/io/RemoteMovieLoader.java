package software.ulpgc.kata2.io;

import software.ulpgc.kata2.model.Movie;
import software.ulpgc.kata2.serializer.TsvMovieParser;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class RemoteMovieLoader implements MovieLoader {
    private static final String remoteUrl = "https://datasets.imdbws.com/title.basics.tsv.gz";
    private static final int bufferSize = 65536;
    @Override
    public List<Movie> loadAll() {
        try {
            return loadAllFrom(new URL(remoteUrl));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Movie> loadAllFrom(URL url) throws IOException {
        return loadAllFrom(url.openConnection());
    }

    private List<Movie> loadAllFrom(URLConnection urlConnection) throws IOException {
        try (InputStream is = new GZIPInputStream(new BufferedInputStream(urlConnection.getInputStream(), bufferSize))) {
            return loadAllFrom(is);
        }
    }

    private List<Movie> loadAllFrom(InputStream is) throws IOException {
        return loadFrom(new BufferedReader(new InputStreamReader(is)));
    }

    private List<Movie> loadFrom(BufferedReader reader) throws IOException {
        List<Movie> movies = new ArrayList<>();
        TsvMovieParser parser = new TsvMovieParser();
        reader.readLine();
        while (true) {
            String line = reader.readLine();
            if (line == null) break;
            movies.add(parser.from(line));
        }
        return movies;
    }
}
