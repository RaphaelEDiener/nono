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
        return switch (split[0]) {
            case "select" -> Optional.of(SELECTION);
            case "play" -> Optional.of(PLAYING);
            case "create" -> Optional.of(CREATING);
            default -> Optional.empty();
        };
    }

    public static Optional<GameStates> from_request(Optional<Request> optionalRequest) {
        if (optionalRequest.isEmpty()) {
            return Optional.empty();
        }
        else {
            return from_request(optionalRequest.get());
        }
    }
}
