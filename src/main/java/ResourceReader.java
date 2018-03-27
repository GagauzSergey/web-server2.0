import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ResourceReader {

    private Request request;

    private String webAppPath;

    private String url;

    public ResourceReader(String webAppPath) {
        this.webAppPath = webAppPath;
    }

    public BufferedReader getResource() {
        BufferedReader resourceBufferedReader = null;
        try {
            resourceBufferedReader = new BufferedReader
                    (new FileReader(
                            ("src/"+webAppPath + "/" + url)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return resourceBufferedReader;
    }

    public String getWebAppPath() {
        return webAppPath;
    }

    public void setWebAppPath(String webAppPath) {
        this.webAppPath = webAppPath;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
