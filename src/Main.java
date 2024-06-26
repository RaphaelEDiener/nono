package src;

import src.game.*;

public class Main {

    public static void main(String[] args)
    {
        final GameServer main_server = new GameServer(new Game(10,10,"test"), true);
        System.out.println("port: " + main_server.port);
        main_server.start();
    }
}

