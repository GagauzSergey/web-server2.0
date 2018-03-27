import java.io.*;
import java.net.Socket;

public class RequestHandler {

    private Socket socket;

    private RequestParser requestParser;

    private ResourceReader resourceReader;

    public RequestHandler(Socket socket, ResourceReader resourceReader) throws IOException {
        this.socket = socket;
        this.resourceReader = resourceReader;
    }

    private BufferedReader bufferedReader;

    private BufferedWriter bufferedWriter;

    public void handle() throws IOException {
        bufferedReader = new BufferedReader
                (new InputStreamReader
                        (socket.getInputStream()));
        bufferedWriter = new BufferedWriter
                (new OutputStreamWriter
                        (socket.getOutputStream()));

        requestParser = new RequestParser();

        Request request = new Request();

        Request readyRequest = requestParser.requestHeaders(request, bufferedReader);

        String url = readyRequest.getUrl();

        resourceReader.setUrl(url);

        if (readyRequest.getHttpMethod().equals(HttpMethod.GET)) {

            new ResponseWriter().writeSuccessResponse(resourceReader, bufferedWriter);
        } else new ResponseWriter().writeBadResponse(bufferedWriter);

    }
}
