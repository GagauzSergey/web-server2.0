import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class ResponseWriter {

    public void writeSuccessResponse(ResourceReader resourceReader, BufferedWriter writer) {

        StringBuilder dataFromResourceFile = new StringBuilder();
        BufferedReader reader = resourceReader.getResource();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                dataFromResourceFile.append(line);
            }
            System.out.println(dataFromResourceFile);

            String responseHtml = htmlSuccessLayout() + dataFromResourceFile.toString();
            System.out.println(responseHtml);
            writer.write(responseHtml);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeBadResponse(BufferedWriter writer) {

        String responseHtml = htmlErrorLayout();
        System.out.println(responseHtml);
        try {
            writer.write(responseHtml);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String htmlSuccessLayout() {
        StringBuilder html = new StringBuilder();

        html.append("HTTP/1.1 " + 200 + " " + statusCodeAnswer(200) + "\n");
        html.append("\n");

        return html.toString();
    }

    public String htmlErrorLayout() {
        StringBuilder html = new StringBuilder();
        html.append("HTTP/1.1 " + 405 + " " + statusCodeAnswer(405) + "\n");
        html.append("\n");
        return html.toString();
    }


    private String statusCodeAnswer(int code) {
        if (code == 200) {
            return "OK";
        } else if (code == 405)
            return "Method Not Allowed";
        else return "Internal Server Error";
    }
}
