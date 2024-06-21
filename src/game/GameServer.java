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

    private void sm() {
    }

    public void start() {
        while (true) {
            Socket connection = this.server.connect();
            if (this.debug) this.print(connection.toString());
            var request = this.server.receive(connection);
            if (this.debug) this.print(request.toString());
            var cmd = GameCommands.from_request(request);
            if (this.debug) this.print(cmd.toString());
            if (cmd.isPresent() && cmd.get() == GameCommands.GET_VIEW) {
                var response = new Response(
                        Protocol.HTTP1_1,
                        StatusCode.OK,
                        ContextType.TEXT_HTML,
                        new Body(game.toHtml()).toHtml(),
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
