package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    private static final String DATABASE_URL = "jdbc:sqlite:tutor.db";
    private static final String QUESTION_QUERY = "SELECT * FROM questions ORDER BY id";

    public static void main(String[] args) {
        System.out.println("Welcome to HintTutor.");
        System.out.println();
        System.out.println("How it works:");
        System.out.println("- Read each topic and question.");
        System.out.println("- You get one submitted answer per question.");
        System.out.println("- Type 'hint' if you want one hint before answering.");
        System.out.println("- All questions are fill in the blank.");
        System.out.println("- After you finish, your answers and score will be shown.");
        System.out.println();

        // Store session results so the user can review everything at the end.
        ArrayList<String> userAnswers = new ArrayList<>();
        ArrayList<String> correctAnswers = new ArrayList<>();
        ArrayList<Boolean> hintsUsed = new ArrayList<>();
        ArrayList<Boolean> answersCorrect = new ArrayList<>();

        try (
                Connection connection = DriverManager.getConnection(DATABASE_URL);
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery(QUESTION_QUERY);
                Scanner scanner = new Scanner(System.in)
        ) {
            while (result.next()) {
                boolean hintUsed = false;

                // Display the current question retrieved from SQLite.
                System.out.println("Difficulty: " + result.getString("difficulty"));
                System.out.println();
                System.out.println("Topic:");
                System.out.println(result.getString("topic"));
                System.out.println();
                System.out.println("Question:");
                System.out.println(result.getString("question_text"));
                System.out.println();

                System.out.println("Type your answer, or type 'hint' to see one hint:");
                String userAnswer = scanner.nextLine();

                if (userAnswer.equalsIgnoreCase("hint")) {
                    hintUsed = true;
                    System.out.println();
                    System.out.println("Hint:");
                    System.out.println(result.getString("hint"));
                    System.out.println();
                    System.out.println("Now type your answer:");
                    userAnswer = scanner.nextLine();
                }

                String correctAnswer = result.getString("answer");
                boolean isCorrect = normalizeAnswer(userAnswer)
                        .equals(normalizeAnswer(correctAnswer));

                userAnswers.add(userAnswer);
                correctAnswers.add(correctAnswer);
                hintsUsed.add(hintUsed);
                answersCorrect.add(isCorrect);

                System.out.println();
                System.out.println("~~~~~~~~~~~~~ Next Question ~~~~~~~~~~~~~");
                System.out.println();
            }

            printReview(userAnswers, correctAnswers, hintsUsed, answersCorrect);

        } catch (Exception e) {
            System.out.println("Something went wrong:");
            System.out.println(e.getMessage());
        }
    }

    // Ignore capitalization and whitespace so formatting differences do not
    // incorrectly mark equivalent short answers as wrong (for example,
    // "num:numbers" and "num : numbers").
    private static String normalizeAnswer(String answer) {
        return answer
                .trim()
                .toLowerCase(Locale.ROOT)
                .replaceAll("\\s+", "");
    }

    private static void printReview(
            ArrayList<String> userAnswers,
            ArrayList<String> correctAnswers,
            ArrayList<Boolean> hintsUsed,
            ArrayList<Boolean> answersCorrect
    ) {
        int correctCount = 0;

        System.out.println();
        System.out.println("All questions complete.");
        System.out.println();
        System.out.println("Review:");
        System.out.println();

        for (int i = 0; i < userAnswers.size(); i++) {
            System.out.println("Question " + (i + 1));
            System.out.println();
            System.out.println("Your answer:");
            System.out.println(userAnswers.get(i));
            System.out.println();
            System.out.println("Correct answer:");
            System.out.println(correctAnswers.get(i));
            System.out.println();
            System.out.println("Hint used: " + hintsUsed.get(i));
            System.out.println();
            System.out.println("Result: " + (answersCorrect.get(i) ? "Correct" : "Incorrect"));
            System.out.println();
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            if (answersCorrect.get(i)) {
                correctCount++;
            }
        }

        System.out.println("Final score: " + correctCount + " / " + userAnswers.size());
    }
}
