import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class RequestParser {

    public RequestParser() {
    }

    public Request requestHeaders(Request request, BufferedReader requestReader) {

        String message;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            while (!(message = requestReader.readLine()).isEmpty()) {
                stringBuilder.append(message).append("  ");
            }
            injectUrlAndMethod(request, stringBuilder.toString());

            if (request.getHttpMethod().equals("GET")){
                injectHeaders(request, stringBuilder.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return request;
    }

    public void injectUrlAndMethod(Request request, String line) {
        String getMethod = line.substring(0, 4);
        int indexOfHTTP = line.indexOf("H");
        int indexSlash = line.indexOf("/");
        String url = line.substring(indexSlash+1, indexOfHTTP);
        System.out.println(getMethod);
        System.out.println(url);

        if (getMethod.equals("GET ")){
            request.setHttpMethod(HttpMethod.GET);
            request.setUrl(url);
            System.out.println("Method: GET");
        }
        else if (getMethod.equals("POST")){
            request.setHttpMethod(HttpMethod.POST);
            System.out.println("Method: POST");
        }
        else System.out.println("Unsupported method");
        System.out.println(line);

    }

    public void injectHeaders(Request request, String line) {
        Map<String, String> headersMap = new HashMap<String, String>();
        String hostHeader = line.substring(line.indexOf("Host"), line.indexOf("Connection")-2);
        headersMap.put("Host", hostHeader);

        String cookie = line.substring(line.indexOf("Cookie"));
        headersMap.put("Cookie", cookie);

        request.setHeaders(headersMap);
    }
}
