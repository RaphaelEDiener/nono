package src.game;

import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import src.html.*;
import src.http.*;

public class GameServer {

    private final Server server;
    private Game game;
    private GameStates state;
    public final int port;
    public final boolean debug;

    public GameServer(Game game) {
        var redirects = new HashMap<String, String>();
        redirects.put("/", "/select/");
        redirects.put("/play/", "/select/");
        this.server = new Server(redirects);
        this.game = game;
        this.port = this.server.port;
        this.debug = false;
        this.state = GameStates.SELECTION;
    }

    public GameServer(Game game, boolean debug) {
        var redirects = new HashMap<String, String>();
        redirects.put("/", "/select/");
        redirects.put("/play/", "/select/");
        this.server = new Server(redirects, debug);
        this.game = game;
        this.port = this.server.port;
        this.debug = debug;
        this.state = GameStates.SELECTION;
    }

    private Response handle_playing(GameCommands cmd) {
        this.game = switch (cmd) {
            case UP -> this.game.up();
            case DOWN -> this.game.down();
            case LEFT -> this.game.left();
            case RIGHT -> this.game.right();
            case MARK -> this.game.mark();
            case CONFIRM -> this.game.confirm();
            default -> this.game;
        };
        var response_body = switch (cmd) {
            case CONFIRM, MARK, UP, DOWN, RIGHT, LEFT -> game.innerHtml();
            case BACK -> "";
            case GET_VIEW -> new Body(game.toHtml())
                    .toHtml();
        };
        return Response.ok(
                Protocol.HTTP1_1,
                StatusCode.OK,
                ContextType.TEXT_HTML,
                response_body,
                StandardCharsets.UTF_8
        );
    }

    private Response handle_selecting(GameCommands cmd) {
        var p = Paths.get("saves").toAbsolutePath();
        if (!p.toFile().exists()) {
            try {
                Files.createDirectory(p);
            }
            catch (IOException ignored) {
                return Response.ok(
                        Protocol.HTTP1_1,
                        StatusCode.INTERNAL_SERVER_ERROR,
                        ContextType.TEXT_PLAIN,
                        "Could not create files",
                        StandardCharsets.UTF_8
                );
            }
        }

        // can't be null, since it's created above
        for (var f : Objects.requireNonNull(p.toFile().listFiles())) {

        }
        return Response.ok(
                Protocol.HTTP1_1,
                StatusCode.OK,
                ContextType.TEXT_HTML,
                new Body("TODO").toHtml(),
                StandardCharsets.UTF_8
        );
    }
    private Response handle_creating(GameCommands cmd) {
        return Response.ok(
                Protocol.HTTP1_1,
                StatusCode.OK,
                ContextType.TEXT_HTML,
                new Body("TODO").toHtml(),
                StandardCharsets.UTF_8
        );
    }

    public void start() {
        //noinspection InfiniteLoopStatement since it gets terminated!
        while (true) {
            Socket connection = this.server.connect();
            this.print(connection.toString());

            var request = this.server.receive(connection);
            this.print(request.toString());

            var state = GameStates.from_request(request);
            this.state = state.orElseGet(() -> this.state);
            this.print(state.toString());

            var cmd = GameCommands.from_request(request);
            this.print(cmd.toString());

            if (cmd.isEmpty()) {
                this.server.respond(request, connection);
            }
            else {
                var response = switch (this.state) {
                    case PLAYING  -> handle_playing(cmd.get());
                    case SELECTION -> handle_selecting(cmd.get());
                    case CREATING -> handle_creating(cmd.get());
                };
                this.server.send(response, connection);
            }
        }
    }

    private void print(String message) {
        if (this.debug) {
            System.out.println(message);
        }
    }
}
