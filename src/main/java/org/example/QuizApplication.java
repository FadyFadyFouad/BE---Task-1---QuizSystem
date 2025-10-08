package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class QuizApplication {
    List<Question> AllQuestions = new ArrayList<>();
    Quiz quiz = new Quiz();
    User user = new User();

    // Constructor
    public QuizApplication() {
        initializeQuestions();
        int size = AllQuestions.size();
        List<Integer> addedQuestions = new ArrayList<>();

        // Pick 10 random questions
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            int index = rand.nextInt(size);
            while (addedQuestions.contains(index)) {
                index = rand.nextInt(size); // Avoid repeated questions
            }
            quiz.addQuestion(AllQuestions.get(index));
            addedQuestions.add(index);
        }
    }

    // Method to initialize the questions from a file
    public void initializeQuestions() {
        File myObj = new File("C:\\Users\\ffahim\\Downloads\\questions.txt"); // Make sure to provide the correct path
        try (Scanner myReader = new Scanner(myObj)) {
            while (myReader.hasNextLine()) {
                String question = myReader.nextLine();
                String option1 = myReader.nextLine();
                String option2 = myReader.nextLine();
                String option3 = myReader.nextLine();
                String option4 = myReader.nextLine();
                int correctOption = Integer.parseInt(myReader.nextLine());
                AllQuestions.add(new Question(question, option1, option2, option3, option4, correctOption));
                if (myReader.hasNextLine()) myReader.nextLine(); // Skip any empty lines
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while loading questions.");
            e.printStackTrace();
        }
    }

    // Method to start the quiz and handle user input
    public void startQuiz() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String username = scanner.nextLine();
        user.setUsername(username);

        for (Question question : quiz.Questions) {
            System.out.println(question.getQuestionText());
            List<String> options = question.getOptions();
            for (String option : options) {
                System.out.println(option);
            }

            System.out.print("Select an option (1-4): ");
            int chosenOption = scanner.nextInt();
            quiz.recordAnswer(question, chosenOption);

            if (question.isCorrect(chosenOption)) {
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect!");
                System.out.println("Correct Answer is " + question.getCorrectOption());
            }
            System.out.println();
        }

        // Displaying score after all questions are answered
        displayScore();
    }

    // Method to display user's score
    public void displayScore() {
        int correctAnswers = 0;
        for (Question question : quiz.Questions) {
            if (quiz.userAnswers.get(question)) {
                correctAnswers++;
            }
        }
        int totalQuestions = quiz.Questions.size();
        System.out.println(user.getUsername() + "'s quiz completed!");
        System.out.println("Your score: " + correctAnswers + "/" + totalQuestions);
    }

    // Main method to run the quiz
    public static void main(String[] args) {
        QuizApplication app = new QuizApplication();
        app.startQuiz();
    }
}
