package src.game;

import java.util.*;
import src.http.*;

public enum GameCommands {
    UP,
    DOWN,
    LEFT,
    RIGHT,
    MARK,
    CONFIRM,
    BACK,
    GET_VIEW;

    public static Optional<GameCommands> from_request(Request request) {
        var split = Arrays.stream(request.url.split("/"))
                          .filter(x -> !x.isEmpty())
                          .toArray(String[]::new);
        if (split.length < 1) return Optional.empty();
        return switch (split[split.length-1]) {
            case "up" -> Optional.of(UP);
            case "down" -> Optional.of(DOWN);
            case "left" -> Optional.of(LEFT);
            case "right" -> Optional.of(RIGHT);
            case "mark" -> Optional.of(MARK);
            case "confirm" -> Optional.of(CONFIRM);
            case "back" -> Optional.of(BACK);
            case String s when GameStates.from_string(s).isPresent() -> Optional.of(GET_VIEW);
            default -> Optional.empty();
        };
    }

    public static Optional<GameCommands> from_request(Optional<Request> optionalRequest) {
        if (optionalRequest.isEmpty()) {
            return Optional.empty();
        }
        else {
            return from_request(optionalRequest.get());
        }
    }
}
