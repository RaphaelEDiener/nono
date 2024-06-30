package src.game;

import java.util.*;
import src.http.*;

public enum GameStates {
    SELECTION,
    PLAYING,
    CREATING;

    public static Optional<GameStates> from_request(Request request) {
        var split = Arrays.stream(request.url().split("/"))
                          .filter(x -> !x.isEmpty())
                          .toArray(String[]::new);
        if (split.length < 1) return Optional.empty();
        return GameStates.from_string(split[0]);
    }

    public static Optional<GameStates> from_request(Optional<Request> optionalRequest) {
        if (optionalRequest.isEmpty()) {
            return Optional.empty();
        }
        else {
            return from_request(optionalRequest.get());
        }
    }

    public static Optional<GameStates> from_string(String string) {
        return switch (string) {
            case "select" -> Optional.of(SELECTION);
            case "play" -> Optional.of(PLAYING);
            case "create" -> Optional.of(CREATING);
            default -> Optional.empty();
        };
    }
}
