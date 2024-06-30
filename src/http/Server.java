package src.http;

import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.util.*;

public class Server {

    private final ServerSocket socket;
    public final int port;
    public final boolean debug;
    private final Response not_found_response;
    private final Response htaccess;
    private final Response favicon;
    private final HashMap<String, String> redirects;
    private static final Response default_not_found_response = Response.ok(
            Protocol.HTTP1_1,
            StatusCode.NOT_FOUND,
            ContextType.TEXT_HTML,
            "<h1> 404 - Page not found </h1>",
            StandardCharsets.UTF_8
    );
    private static final Response default_htaccess = Response.ok(
            Protocol.HTTP1_1,
            StatusCode.OK,
            ContextType.TEXT_PLAIN,
            "ErrorDocument 404 /not-found-page",
            StandardCharsets.UTF_8
    );
    private static final Response default_favicon = Response.ok(
            Protocol.HTTP1_1,
            StatusCode.OK,
            ContextType.IMAGE_SVG,
            """
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<svg
width="48"
height="48"
viewBox="0 0 12.7 12.7"
version="1.1"
id="svg1"
sodipodi:docname="icon.svg"
xmlns:inkscape="http://www.inkscape.org/namespaces/inkscape"
xmlns:sodipodi="http://sodipodi.sourceforge.net/DTD/sodipodi-0.dtd"
xmlns="http://www.w3.org/2000/svg"
xmlns:svg="http://www.w3.org/2000/svg">
<sodipodi:namedview
id="namedview1"
pagecolor="#ffffff"
bordercolor="#666666"
borderopacity="1.0" />
<defs
id="defs1" />
<g
id="layer1">
<path
d="M 9.525,0 V 3.175 6.35 H 6.35 V 9.525 12.7 H 9.525 12.7 V 9.525 6.35 3.175 0 Z"
style="fill:#161616;stroke-width:0.84137;stroke-linecap:round;stroke-linejoin:round;paint-order:stroke markers fill"
id="path3" />
<path
d="M 0,0 V 3.175 6.35 9.525 12.7 H 3.175 V 9.525 6.35 H 6.35 V 3.175 0 H 3.175 Z"
style="fill:#161616;stroke-width:0.84137;stroke-linecap:round;stroke-linejoin:round;paint-order:stroke markers fill"
id="path4" />
</g>
</svg>
""",
            StandardCharsets.UTF_8
    );

    public Server(Server old) {
        this.socket = old.socket;
        this.port = old.port;
        this.debug = old.debug;
        this.not_found_response = old.not_found_response;
        this.htaccess = old.htaccess;
        this.favicon = old.favicon;
        this.redirects = old.redirects;
    }

    public Server(HashMap<String, String> redirects, int port) throws IOException
    {
        this.socket = new ServerSocket(port);
        this.port = port;
        this.debug = false;
        this.not_found_response = Server.default_not_found_response;
        this.favicon = Server.default_favicon;
        this.htaccess = Server.default_htaccess;
        this.redirects = redirects;
    }

    public Server(HashMap<String, String> redirects)
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
        this.not_found_response = Server.default_not_found_response;
        this.favicon = Server.default_favicon;
        this.htaccess = Server.default_htaccess;
        this.redirects = redirects;
    }

    public Server(HashMap<String, String> redirects, int port, boolean debug) throws IOException
    {
        this.socket = new ServerSocket(port);
        this.port = port;
        this.debug = debug;
        this.not_found_response = Server.default_not_found_response;
        this.favicon = Server.default_favicon;
        this.htaccess = Server.default_htaccess;
        this.redirects = redirects;
    }

    public Server(HashMap<String, String> redirects, boolean debug)
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
        this.not_found_response = Server.default_not_found_response;
        this.favicon = Server.default_favicon;
        this.htaccess = Server.default_htaccess;
        this.redirects = redirects;
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

    public void respond(Optional<Request> req, Socket connection) {
        if (req.isEmpty()) {
            this.send(this.not_found_response, connection);
        }
        else {
            this.respond(req.get(), connection);
        }
    }

    public void respond(Request req, Socket connection) {
        this.print("---- responding... ----");
        if (this.redirects.containsKey(req.url())) {
            var response = Response.redirect(
                    Protocol.HTTP1_1,
                    StatusCode.SEE_OTHER,
                    StandardCharsets.UTF_8,
                    this.redirects.get(req.url())
            );
            this.send(response, connection);
        } else {
            var type = RequestType.from_request(req);
            // java can't pattern match over more than absolute trivial things...
            if (type.isEmpty()) {
                this.send(this.not_found_response, connection);
            }
            else if (type.get() == RequestType.SHUT_DOWN) {
                System.exit(0);
            }
            else {
                var response = switch (type.get()) {
                    case NOT_FOUND_PAGE -> this.not_found_response;
                    case FAVICON -> this.favicon;
                    case HTACCESS -> this.htaccess;
                    case SHUT_DOWN -> throw new RuntimeException(
                            "Unreachable! Server is trying to respond after shutting down");
                };
                this.send(response, connection);
            }
        }
        this.print("---- responded! ----");
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
