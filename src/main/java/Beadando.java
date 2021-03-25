import com.sajtos.bead2.BeadandoClient;
import com.sajtos.bead2.BeadandoServer;

public class Beadando {
    public static void main(String[] args) {


        if (args.length > 1) {
            String server = args[0];
            int port = Integer.parseInt(args[1]);
            String text = args[2];
            BeadandoClient beadandoClient = new BeadandoClient();
            beadandoClient.writeToServer(server, port, text);
        } else {
            int port = 8888;
            try {
                port = Integer.parseInt(args[0]);
            } catch (Exception e) {
            }

            BeadandoServer beadandoServer = new BeadandoServer();
            beadandoServer.start(port);
        }

    }
}
