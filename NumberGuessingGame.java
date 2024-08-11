import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int maxAttempts = 5; // Limiting the number of attempts per round
        int rounds = 0;
        int score = 0;
        String playAgain;

        do {
            int numberToGuess = random.nextInt(100) + 1; // Generates a random number between 1 and 100
            int attempts = 0;
            boolean hasGuessedCorrectly = false;
            rounds++;

            System.out.println("Round " + rounds + ": Guess the number between 1 and 100!");

            while (attempts < maxAttempts && !hasGuessedCorrectly) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == numberToGuess) {
                    System.out.println("Congratulations! You've guessed the correct number.");
                    score += (maxAttempts - attempts + 1); // Higher score for fewer attempts
                    hasGuessedCorrectly = true;
                } else if (userGuess < numberToGuess) {
                    System.out.println("Your guess is too low. Try again.");
                } else {
                    System.out.println("Your guess is too high. Try again.");
                }
            }

            if (!hasGuessedCorrectly) {
                System.out.println("Sorry, you've used all your attempts. The correct number was: " + numberToGuess);
            }

            System.out.println("Your score after " + rounds + " round(s) is: " + score);
            System.out.print("Do you want to play another round? (yes/no): ");
            playAgain = scanner.next();

        } while (playAgain.equalsIgnoreCase("yes"));

        System.out.println("Thank you for playing! Your final score after " + rounds + " round(s) is: " + score);
        scanner.close();
    }
}
