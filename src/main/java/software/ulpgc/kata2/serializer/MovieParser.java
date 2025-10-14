package software.ulpgc.kata2.serializer;

import software.ulpgc.kata2.model.Movie;

public interface MovieParser {
    Movie from(String str);
}
