public class StartApp {
    public static void main(String[] args) {

        Server Server = new Server();
        Server.setPort(3000);
        Server.setWebAppDir("resources/webapp");
        Server.start();
    }
}
