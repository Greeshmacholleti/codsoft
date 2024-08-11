import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    private String questionText;
    private String[] options;
    private int correctOption;

    public Question(String questionText, String[] options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public boolean isCorrect(int userAnswer) {
        return userAnswer == correctOption;
    }
}

public class QuizApplication {
    private static final int TIME_LIMIT = 10; // Time limit per question in seconds
    private static int userAnswer = -1; // To capture user answer
    private static boolean answered = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Define questions
        Question[] questions = {
            new Question("What is the capital of France?", new String[] {"1. Berlin", "2. Paris", "3. Madrid", "4. Rome"}, 2),
            new Question("Which planet is known as the Red Planet?", new String[] {"1. Earth", "2. Jupiter", "3. Mars", "4. Venus"}, 3),
            new Question("Who wrote 'Hamlet'?", new String[] {"1. Charles Dickens", "2. Leo Tolstoy", "3. William Shakespeare", "4. Mark Twain"}, 3)
        };

        int score = 0;
        boolean timeUp;

        // Loop through each question
        for (Question question : questions) {
            System.out.println("\n" + question.getQuestionText());
            for (String option : question.getOptions()) {
                System.out.println(option);
            }

            // Timer for the question
            Timer timer = new Timer();
            timeUp = false;

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (!answered) {
                        System.out.println("\nTime's up! Moving to the next question...");
                        userAnswer = -1; // Reset answer if time is up
                        answered = true;
                    }
                }
            }, TIME_LIMIT * 1000);

            // Read user's answer within the time limit
            while (!answered) {
                System.out.print("Your answer (1-4): ");
                userAnswer = scanner.nextInt();
                answered = true;
            }

            // Check if the answer is correct
            if (userAnswer != -1 && question.isCorrect(userAnswer)) {
                System.out.println("Correct!");
                score++;
            } else if (userAnswer != -1) {
                System.out.println("Incorrect.");
            }

            timer.cancel(); // Stop the timer
            answered = false; // Reset for the next question
        }

        // Display final results
        System.out.println("\n--- Quiz Results ---");
        System.out.println("Total Questions: " + questions.length);
        System.out.println("Correct Answers: " + score);
        System.out.println("Incorrect Answers: " + (questions.length - score));
        System.out.println("Your final score is: " + score + "/" + questions.length);

        scanner.close();
    }
}
