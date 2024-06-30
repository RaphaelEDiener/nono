package src.game;

import java.net.*;
import java.nio.charset.*;
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

    Response handle_playing(GameCommands cmd) {
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

    public void start() {
        //noinspection InfiniteLoopStatement since it gets terminated!
        while (true) {
            Socket connection = this.server.connect();
            this.print(connection.toString());

            var request = this.server.receive(connection);
            this.print(request.toString());

            var cmd = GameCommands.from_request(request);
            this.print(cmd.toString());

            if (cmd.isEmpty()) {
                this.server.respond(request, connection);
            }
            else {
                var response = switch (this.state) {
                    case SELECTION, PLAYING, CREATING -> handle_playing(cmd.get());
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
