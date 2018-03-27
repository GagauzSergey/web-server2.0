import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private int port;

    private String webAppDir;

    public Server() {
    }

    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            setWebAppDir(webAppDir);
            ResourceReader resourceReader = new ResourceReader(getWebAppDir());

            while (true) {
                Socket socket = serverSocket.accept();
                RequestHandler requestHandler = new RequestHandler(socket, resourceReader);
                requestHandler.handle();
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getWebAppDir() {
        return webAppDir;
    }

    public void setWebAppDir(String webAppDir) {
        this.webAppDir = webAppDir;
    }


}
