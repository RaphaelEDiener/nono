package src;

import java.io.*;
import java.nio.charset.*;
import java.util.*;

public class Main {

    public static void main(String[] args)
    {
        final Server main_server = new Server(true);
        System.out.println("port: " + main_server.port);
        main_server.start();
    }
}

