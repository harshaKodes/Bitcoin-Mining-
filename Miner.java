import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Miner implements Runnable {
    private final int zeros;
    private final int coinsRequired;
    private static volatile int coinsMined = 0; // Shared counter among threads

    public Miner(int zeros, int coinsRequired) {
        this.zeros = zeros;
        this.coinsRequired = coinsRequired;
    }

    @Override
    public void run() {
        while (coinsMined < coinsRequired) {
            String randomString = generateRandomString(20);
            String hashedValue = sha256Hash(randomString);

            if (isBitcoin(hashedValue, zeros)) {
                synchronized (Miner.class) {
                    if (coinsMined < coinsRequired) {
                        coinsMined++;
                        System.out.println("Bitcoin Found: " + hashedValue + " by " + Thread.currentThread().getName());
                    }
                }
            }
        }
    }

    private String generateRandomString(int length) {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder stringBuilder = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            stringBuilder.append(chars.charAt(random.nextInt(chars.length())));
        }
        return stringBuilder.toString();
    }

    private String sha256Hash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isBitcoin(String hash, int zeros) {
        String prefix = "0".repeat(zeros);
        return hash.startsWith(prefix);
    }
}
