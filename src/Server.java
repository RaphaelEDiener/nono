package src;

import java.io.*;
import java.net.*;
import java.nio.charset.*;
import src.html.*;

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
        var length = message.getBytes(StandardCharsets.UTF_8).length;
        try (var stream = connection.getOutputStream()) {
            var response = """
                        HTTP/1.1 200 OK
                        Content-Type: text/html
                        Content-Length: %d
                        
                        %s
                        """.formatted(length, message);
            System.out.println(response);
            stream.write(response.getBytes(StandardCharsets.UTF_8));
        } catch (IOException ignore) {
        }
    }

    private void print(String message) {
        if (this.debug) {
            System.out.println(message);
        }
    }
    public void start() {
        while (true) {
            this.print("accepting...");
            Socket connection = this.connect();
            this.print("connected!");
            this.print("responding...");
            var game = new Body(new Game(10,10).toString());
            this.respond(game.toString(), connection);
            this.print("responded!");
        }
    }
}
