// import java.security.MessageDigest;
// import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private int zeros;
    private int coinsRequired;
    private int processes;

    public void start() {
        // Input values (You can replace these with dynamic user input for flexibility)
        zeros = 4; // Number of leading zeros
        coinsRequired = 10; // Bitcoins to mine
        processes = 8; // Number of processes/threads

        System.out.println("Server starting with:");
        System.out.println("Zeros: " + zeros);
        System.out.println("Coins to mine: " + coinsRequired);
        System.out.println("Processes: " + processes);

        ExecutorService executor = Executors.newFixedThreadPool(processes);

        for (int i = 0; i < processes; i++) {
            executor.execute(new Miner(zeros, coinsRequired));
        }
        executor.shutdown();
    }
}
