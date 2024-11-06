import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WheelOfFortuneMethods {


//    The main function where we set the phrase list from the phrases.txt file
    public static void main(String[] args) {
        List<String> phraseList=null;
        // Get the phrase from a txt file of phrases
        try {
            phraseList = Files.readAllLines(Paths.get("phrases.txt"));
        } catch (IOException e) {
            System.out.println(e);
        }
        String randomPhrase = randomPhrase(phraseList);
        String hiddenText = generateHiddenPhrase(randomPhrase);
//        Show the rules to the user and start the game now that we have a random phrase selected
        showRules();
        startGame(randomPhrase, hiddenText);
    }


//    Gets the random saying from the phrasesList which is passed in from above
    private static String randomPhrase(List<String> phrases) {
        Random rand = new Random();
        return phrases.get(rand.nextInt(phrases.size()));
    }


//    Hides the phrase from the user by converting it to *
    private static String generateHiddenPhrase(String phrase) {
        StringBuilder hiddenText = new StringBuilder();
        for (int i = 0; i < phrase.length(); i++) {
            if (Character.isLetter(phrase.charAt(i))) {
                hiddenText.append("*");
            } else {
                hiddenText.append(phrase.charAt(i));
            }
        }
        return hiddenText.toString();
    }


//    Shows the rules to the user
    private static void showRules() {
        System.out.println("RULES");
        System.out.println("If you have ever played hangman or seen the TV show Wheel of Fortune, then you already know how to play. But for those that do not, here is how:");
        System.out.println("You are given 5 lives, and a hidden phrase when you click play. You are to then guess a single letter; if that letter is within the hidden phrase,");
        System.out.println("then that letter and all occurrences of it will be shown. If it is not, you will lose a life. The goal is to figure out the phrase before your lives run out!");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }


//    Start the game with the hidden text and the phrase
    private static void startGame(String phrase, String hiddenText) {
        int lives = 5;
        System.out.println("Lives Left: " + lives);
        System.out.println("The Hidden Phrase: " + hiddenText);
        StringBuilder newHiddenText = new StringBuilder(hiddenText);
        List<Character> guessedAlready = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        while (newHiddenText.toString().contains("*") && lives != 0) {
            char userEntered = ' ';
            System.out.println("--------------------------------------------------");
            userEntered = getGuess(in);
            if (!guessedAlready.contains(userEntered)) {
                lives = processGuess(phrase, newHiddenText, guessedAlready, userEntered, lives);
            } else {
                System.out.println("You Have Already Guessed: " + userEntered + " (No Lives Were Taken Away).");
            }
            printGuessStatus(lives, guessedAlready, newHiddenText);
        }
        showFinalResult(lives, newHiddenText.toString(), phrase);
    }


//    Gets input from the user and returns it
    private static char getGuess(Scanner in) {
        char userEntered = ' ';
//        Only need a while true now because we check the game logic in previous function
        while (true) {
            System.out.print("Guess A Letter: ");
            String userInput = in.nextLine();
            if (userInput.length() != 1) {
                if (userInput.isEmpty()) {
                    System.out.println("You Did Not Enter Anything. Please Enter Something.");
                } else {
                    System.out.println("You Entered In More Than One Character. Please Only Guess One At A Time.");
                }
            } else if (!Character.isLetter(userInput.charAt(0))) {
                System.out.println("You Entered In Something That Is Not A Letter. Please Only Enter Letters.");
            } else {
                userEntered = Character.toLowerCase(userInput.charAt(0)); // Normalize input to lowercase
                break;
            }
        }
        return userEntered;
    }


//    See what the user guessed and if then add to guessed already characters
    private static int processGuess(String phrase, StringBuilder newHiddenText, List<Character> guessedAlready, char userEntered, int lives) {
        boolean noChar = true;
        for (int i = 0; i < phrase.length(); i++) {
            if (Character.toLowerCase(phrase.charAt(i)) == userEntered) {
                noChar = false;
                newHiddenText.setCharAt(i, phrase.charAt(i));
            }
        }
        guessedAlready.add(userEntered);
        if (noChar) {
            lives -= 1;
        }
        return lives;
    }


//    Print the current guess and lives and phrase
    private static void printGuessStatus(int lives, List<Character> guessedAlready, StringBuilder newHiddenText) {
        System.out.println("Lives Left: " + lives);
        System.out.print("Guessed Letters:");
        for (char letter : guessedAlready) {
            System.out.print(" " + letter);
        }
        System.out.println();
        System.out.println("The Hidden Phrase: " + newHiddenText);
    }


//    Print the users final results
    private static void showFinalResult(int lives, String hiddenText, String phrase) {
        System.out.println("--------------------------------------------------");
        if (lives == 0) {
            System.out.println("You have run out of lives! The hidden phrase was: " + phrase);
        } else if (!hiddenText.contains("*")) {
            System.out.println("Congratulations! You've guessed the correct phrase: " + phrase);
        }
    }
}