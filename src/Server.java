package src;

import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.util.*;
import src.html.*;
import src.http.*;

public class Server
{
    private final ServerSocket socket;
    public final int port;
    public final boolean debug;

    public Server(int port) throws IOException
    {
        this.socket = new ServerSocket(port);
        this.port = port;
        this.debug = false;
    }
    public Server()
    {
        int temp_port = 8000;
        ServerSocket temp_socket = null;
        boolean established = false;
        while (!established) {
            try {
                temp_socket = new ServerSocket(temp_port);
                established = true;
            }
            catch (IOException ignored) {
                temp_port++;
            }
        }
        this.socket = temp_socket;
        this.port = temp_port;
        this.debug = false;
    }
    public Server(int port, boolean debug) throws IOException
    {
        this.socket = new ServerSocket(port);
        this.port = port;
        this.debug = debug;
    }
    public Server(boolean debug)
    {
        int temp_port = 8000;
        ServerSocket temp_socket = null;
        boolean established = false;
        while (!established) {
            try {
                temp_socket = new ServerSocket(temp_port);
                established = true;
            }
            catch (IOException ignored) {
                temp_port++;
            }
        }
        this.socket = temp_socket;
        this.port = temp_port;
        this.debug = debug;
    }
    private Socket connect() {
        Socket connection = null;
        while (connection == null) {
            try {
                connection = this.socket.accept();
            }
            catch (IOException ignored) {
            }
        }
        return connection;
    }
    // TODO: return error if failed
    private void respond(String message, Socket connection) {
        try (var stream = connection.getOutputStream()) {
            var response = new Response(
                    Protocol.HTTP1_1,
                    StatusCode.OK,
                    ContextType.TEXT_HTML,
                    message,
                    StandardCharsets.UTF_8
            );
            if (this.debug) System.out.println(response);
            stream.write(response.getBytes());
        } catch (IOException ignore) {
        }
    }

    private Optional<Request> receive(Socket connection) {
        try {
            var reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            var line = reader.readLine();
            if (this.debug) System.out.println(line);
            return Optional.of(Request.from_raw(line));
        }
        catch (IOException ignored) {
            return Optional.empty();
        }
    }

    private void print(String message) {
        if (this.debug) {
            System.out.println(message);
        }
    }
    public void start(Game game) {
        while (true) {
            this.print("---- accepting... ----");
            Socket connection = this.connect();
            this.print("---- connected! ----");
            this.print("---- receiving... ----");
            var request = this.receive(connection);
            this.print("---- received! ----");
            this.print("---- responding... ----");
            this.respond(new Body(game.toHtml()).toHtml(), connection);
            this.print("---- responded! ----");
        }
    }
}
