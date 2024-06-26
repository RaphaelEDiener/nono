package src.game;

import java.net.*;
import java.nio.charset.*;
import src.html.*;
import src.http.*;

public class GameServer {

    private final Server server;
    private Game game;
    public final int port;
    public final boolean debug;

    public GameServer(Game game) {
        this.server = new Server();
        this.game = game;
        this.port = this.server.port;
        this.debug = false;
    }

    public GameServer(Game game, boolean debug) {
        this.server = new Server(debug);
        this.game = game;
        this.port = this.server.port;
        this.debug = debug;
    }

    public void start() {
        //noinspection InfiniteLoopStatement since it gets terminated!
        while (true) {
            Socket connection = this.server.connect();
            if (this.debug) {
                this.print(connection.toString());
            }
            var request = this.server.receive(connection);
            if (this.debug) {
                this.print(request.toString());
            }
            var cmd = GameCommands.from_request(request);
            if (this.debug) {
                this.print(cmd.toString());
            }
            if (cmd.isEmpty()) {
                this.server.respond(request, connection);
            } else {
                this.game = switch (cmd.get()) {
                    case UP -> this.game.up();
                    case DOWN -> this.game.down();
                    case LEFT -> this.game.left();
                    case RIGHT -> this.game.right();
                    case MARK -> this.game.mark();
                    case CONFIRM -> this.game.confirm();
                    default -> this.game;
                };
                var response_body = switch (cmd.get()) {
                    case CONFIRM, MARK, UP, DOWN, RIGHT, LEFT -> game.innerHtml();
                    case BACK -> "";
                    case GET_VIEW -> new Body(game.toHtml())
                            .toHtml();
                };
                var response = new Response(
                        Protocol.HTTP1_1,
                        StatusCode.OK,
                        ContextType.TEXT_HTML,
                        response_body,
                        StandardCharsets.UTF_8
                );
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
