import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WheelOfFortuneObject {

//    Variable definitions
    private String phrase;
    private StringBuilder hiddenPhrase;
    private List<Character> previousGuesses;


//    Constructor initializes the game state with a random phrase, the hidden phrase, and an array for all the users guesses
    public WheelOfFortuneObject(String phrase) {
        this.phrase = phrase;
        this.hiddenPhrase = new StringBuilder();
        this.previousGuesses = new ArrayList<>();
    }


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
        WheelOfFortuneObject wheelOfFortune = new WheelOfFortuneObject(randomPhrase);
//        Show the rules to the user and start the game now that we have a random phrase selected
        wheelOfFortune.hiddenPhrase = new StringBuilder(generateHiddenPhrase(wheelOfFortune.phrase));
        wheelOfFortune.showRules();
        wheelOfFortune.startGame();
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
    private void showRules() {
        System.out.println("RULES");
        System.out.println("If you have ever played hangman or seen the TV show Wheel of Fortune, then you already know how to play. But for those that do not, here is how:");
        System.out.println("You are given 5 lives, and a hidden phrase when you click play. You are to then guess a single letter; if that letter is within the hidden phrase,");
        System.out.println("then that letter and all occurrences of it will be shown. If it is not, you will lose a life. The goal is to figure out the phrase before your lives run out!");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }


//    Start the gaem with the hidden text and the phrase
    private void startGame() {
        int lives = 5;
        Scanner in = new Scanner(System.in);
        while (hiddenPhrase.toString().contains("*") && lives != 0) {
            System.out.println("--------------------------------------------------");
            System.out.println("Lives Left: " + lives);
            System.out.println("The Hidden Phrase: " + hiddenPhrase);
            char userEntered = getGuess(in);
            if (!previousGuesses.contains(userEntered)) {
                lives = processGuess(userEntered, lives);
            } else {
                System.out.println("You Have Already Guessed: " + userEntered + " (No Lives Were Taken Away).");
            }
            printGuessStatus(lives);
        }
        showFinalResult(lives);
    }


//    Gets input from the user and returns it
    private char getGuess(Scanner in) {
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
                userEntered = Character.toLowerCase(userInput.charAt(0));
                break;
            }
        }
        return userEntered;
    }


//    See what the user guessed and if then add to guessedAlready characters
    private int processGuess(char userEntered, int lives) {
        boolean noChar = true;
//        Go through the phrase and see if the user entered a letter that is in it. If so then change the hidden phrase at that location
        for (int i = 0; i < phrase.length(); i++) {
            if (Character.toLowerCase(phrase.charAt(i)) == userEntered) {
                noChar = false;
                hiddenPhrase.setCharAt(i, phrase.charAt(i));
            }
        }
//        Make sure to add this new guess to the users previous guess
        previousGuesses.add(userEntered);
        if (noChar) {
            lives -= 1;
        }
        return lives;
    }


//    Print the current guess and lives and phrase
    private void printGuessStatus(int lives) {
//        Print out the users lives and the previousGuesses
        System.out.println("Lives Left: " + lives);
        System.out.print("Guessed Letters:");
        for (char letter : previousGuesses) {
            System.out.print(" " + letter);
        }
        System.out.println();
        System.out.println("The Hidden Phrase: " + hiddenPhrase);
    }


//    Print the users final results
    private void showFinalResult(int lives) {
//        Let them know how they did and what the phrase was
        System.out.println("--------------------------------------------------");
        if (lives == 0) {
            System.out.println("You have run out of lives! The hidden phrase was: " + phrase);
        } else if (!hiddenPhrase.toString().contains("*")) {
            System.out.println("Congratulations! You've guessed the correct phrase: " + phrase);
        }
    }
}