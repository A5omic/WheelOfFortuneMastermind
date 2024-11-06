import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Bot code which is the same as the object oriented except it now takes no user arguments in and has a bot guess
public class WheelOfFortuneBot {

//    Variable definitions
    private String phrase;
    private StringBuilder hiddenPhrase;
    private List<Character> previousGuesses;


//    Constructor initializes the game state with a random phrase, the hidden phrase, and an array for all the users guesses
    public WheelOfFortuneBot(String phrase) {
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
        WheelOfFortuneBot wheelOfFortune = new WheelOfFortuneBot(randomPhrase);
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


//    Shows the rules to the user via print statements
    private void showRules() {
        System.out.println("RULES");
        System.out.println("If you have ever played hangman or seen the TV show Wheel of Fortune, then you already know how to play. But for those that do not, here is how:");
        System.out.println("You are given 5 lives, and a hidden phrase when you click play. You are to then guess a single letter; if that letter is within the hidden phrase,");
        System.out.println("then that letter and all occurrences of it will be shown. If it is not, you will lose a life. The goal is to figure out the phrase before your lives run out!");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }


//    Start the game with the hidden text and the phrase
    private void startGame() {
//        Give the user five lives and then print out the lives and hidden phrase so the user knows where they are at
        int lives = 5;
        while (hiddenPhrase.toString().contains("*") && lives != 0) {
            System.out.println("--------------------------------------------------");
            System.out.println("Lives Left: " + lives);
            System.out.println("The Hidden Phrase: " + hiddenPhrase);
            char botEnteredGuess = getGuess();
//            Check to see if the user has already guessed that letter
            if (!previousGuesses.contains(botEnteredGuess)) {
                lives = processGuess(botEnteredGuess, lives);
            } else {
                System.out.println("You Have Already Guessed: " + botEnteredGuess + " (No Lives Were Taken Away).");
            }
            printGuessStatus(lives);
        }
        showFinalResult(lives);
    }


//    Gets input from the user and returns it
    private char getGuess() {
        char botEntered = ' ';
        // Check if there is exactly one hidden character * and if so guess i or a
        if (countHiddenCharacters(hiddenPhrase.toString()) == 1) {
            Random random = new Random();
            if (random.nextBoolean()) {
                botEntered = 'i';
            } else {
                botEntered = 'a';
            }
        } else {
            while (true) {
                Random random = new Random();
                char botGuessChar = (char) ('a' + random.nextInt(26));
                if (Character.isLetter(botGuessChar)) {
                    botEntered = Character.toLowerCase(botGuessChar);
                    break;
                }
            }
        }
        return botEntered;
    }


//    Count the number on * to see if the bot should guess an a or i
    private int countHiddenCharacters(String phrase) {
        int count = 0;
        for (char c : phrase.toCharArray()) {
            if (c == '*') {
                count++;
            }
        }
        return count;
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


//     Print the users final results
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