import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ExtraCredit {

//    Variable definitions
    private String phrase;
    private StringBuilder hiddenPhrase;
    private List<Character> previousGuesses;


//    Constructor initializes the game state with a random phrase, the hidden phrase, and an array for all the users guesses
    public ExtraCredit(String phrase) {
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
        ExtraCredit wheelOfFortune = new ExtraCredit(randomPhrase);
//        Show the rules to the user and start the game now that we have a ranodm phrase selected
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


//    Shows the rules to the user via print statements
    private void showRules() {
        System.out.println("RULES");
        System.out.println("If you have ever played hangman or seen the TV show Wheel of Fortune, then you already know how to play. But for those that do not, here is how:");
        System.out.println("You are given 5 lives, and a hidden phrase when you click play. You are to then guess a single letter; if that letter is within the hidden phrase,");
        System.out.println("then that letter and all occurrences of it will be shown. If it is not, you will lose a life. The goal is to figure out the phrase before your lives run out!");
        System.out.println("But since you are a BOT that is just coded to play the game, you will not even read this.");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }


//    Start the game with the hidden text and the phrase
    private void startGame() {
        int lives = 5;
        Scanner in = new Scanner(System.in);
        while (hiddenPhrase.toString().contains("*") && lives != 0) {
            System.out.println("--------------------------------------------------");
            System.out.println("Lives Left: " + lives);
            System.out.println("The Hidden Phrase: " + hiddenPhrase);
            String userGuess = getGuess(in);
//            Check the length of the guess to see if they entered in a character or a phrase guess
            if (userGuess.length() == 1) {
                char userEntered = userGuess.charAt(0);
                if (!previousGuesses.contains(userEntered)) {
                    lives = processGuess(userEntered, lives);
                } else {
                    System.out.println("You Have Already Guessed: " + userEntered + " (No Lives Were Taken Away).");
                }
            } else {
//                See if the user guessed correctly and if so then exit the loop as they won the game
                boolean guessedCorrectly = processFullPhraseGuess(userGuess);
                if (guessedCorrectly) {
                    break;
                } else {
                    lives--;
                }
            }
            printGuessStatus(lives);
        }
        showFinalResult(lives);
    }


//    Gets input from the user and returns it
    private String getGuess(Scanner in) {
//        Only need a while true now because we check the game logic in previous function
        while (true) {
            // Removed the bot and we are back to just checking chars. But for extra credit we are adding in a whole guess option so update print statements
            System.out.print("Guess A Letter Or The Whole Phrase: ");
            String userInput = in.nextLine().toLowerCase();
            if (userInput.isEmpty()) {
                System.out.println("Please Enter Something.");
            } else if (!Character.isLetter(userInput.charAt(0))) {
                System.out.println("You Entered Something That Is Not A Letter. Please Only Enter Letters.");
            } else {
                return userInput;
            }
        }
    }


//    See what the user guessed and if then add to guessedAlready characters
    private int processGuess(char userEntered, int lives) {
//        Go through the phrase and see if the user entered a letter that is in it. If so then change the hidden phrase at that location
        boolean noChar = true;
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


    //    Get the users whole guess and see if it is correct
    private boolean processFullPhraseGuess(String userGuess) {
//        All that is needed is to see if the phrases are equal and if so the user got it correct
        if (userGuess.equals(phrase.toLowerCase())) {
            hiddenPhrase = new StringBuilder(phrase);
            return true;
        } else {
            System.out.println("That Is Not The Full Correct Phrase.");
            return false;
        }
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