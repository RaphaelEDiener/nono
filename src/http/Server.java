package src.http;

import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.util.*;

public class Server {

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

    public Socket connect() {
        this.print("---- accepting... ----");
        Socket connection = null;
        while (connection == null) {
            try {
                connection = this.socket.accept();
            }
            catch (IOException ignored) {
            }
        }
        this.print("---- connected! ----");
        return connection;
    }

    /**
     * This is a debug function - do not use!
     */
    private void respond(String message, Socket connection) {
        try (var stream = connection.getOutputStream()) {
            var response = new Response(
                    Protocol.HTTP1_1,
                    StatusCode.OK,
                    ContextType.TEXT_HTML,
                    message,
                    StandardCharsets.UTF_8
            );
            if (this.debug) {
                this.print(response.toString());
            }
            stream.write(response.getBytes());
        }
        catch (IOException ignore) {
        }
    }

    public Optional<Request> receive(Socket connection) {
        this.print("---- receiving... ----");
        try {
            var reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            var line = reader.readLine();
            if (this.debug) {
                this.print(line);
            }
            this.print("---- received! ----");
            return Request.from_raw(line);
        }
        catch (IOException ignored) {
            this.print("---- Failed! ----");
            return Optional.empty();
        }
    }

    public void send(Response response, Socket connection) {
        this.print("---- sending... ----");
        try (var stream = connection.getOutputStream()) {
            if (this.debug) {
                this.print(response.toString());
            }
            stream.write(response.getBytes());
            this.print("---- send! ----");
        }
        catch (IOException ignore) {
            this.print("---- Failed! ----");
        }
    }

    private void print(String message) {
        if (this.debug) {
            System.out.println(message);
        }
    }
}
