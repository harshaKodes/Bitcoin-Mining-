// import java.security.MessageDigest;
// import java.security.NoSuchAlgorithmException;
// import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting Bitcoin Miner Server...");
        Server server = new Server();
        server.start();
    }
}
