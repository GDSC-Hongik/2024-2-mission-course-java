import java.util.Random;

public class GenerateRandomNum {
    private static final Random randomNumber = new Random();

    public static int generateRandomNumInRange(int minValue, int maxValue) {
        return randomNumber.nextInt(maxValue - minValue + 1) + minValue;
    }
}