import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WheelOfFortuneMain {


    public static void main(String[] args) {
        List<String> phraseList=null;
        // Get the phrase from a file of phrases
        try {
            phraseList = Files.readAllLines(Paths.get("phrases.txt"));
        } catch (IOException e) {
            System.out.println(e);
        }


        // Get a random phrase from the list
        Random rand = new Random();
        int r= rand.nextInt(3); // gets 0, 1, or 2
        String phrase = phraseList.get(r);


//        Make the text hidden by looking through the phrase and seeing if each char is a letter if it is change it to a * if not pass it through like a space or ?
        String hiddenText = "";
        for (int i = 0; i < phrase.length(); i++) {
            if (Character.isLetter(phrase.charAt(i))) {
                hiddenText += "*";
            } else {
                hiddenText +=  phrase.charAt(i);
            }
        }


//        Set the lives and print out the rules
        int lives = 5;
        System.out.println("RULES");
        System.out.println("If you have ever played hangman or seen the tv show wheel of fortune then you already know hwo to play. But for those that do not here is how:");
        System.out.println("You are given 5 lives, and a hidden phrase when you click play. You are to then guess a single letter, if that letter is within the hidden phrase,");
        System.out.println("then that letter and all occurances of it will be shown. If it is not, you will lose a life. The goal is to figure out the phrase before your lives run out!");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Lives Left: " + lives);
        System.out.println("The Hidden Phrase: " + hiddenText);


//        Set the variables that are to be used below in the while loop
        StringBuilder newHiddenText = new StringBuilder(hiddenText);
        boolean errors;
        List<Character> guessedAlready = new ArrayList<>();


//        While the text still is hidden because it has * and you are not out of lives
        while(newHiddenText.toString().contains("*") && lives != 0) {

            // Allow the user to guess a letter by using the scanner class. Need to not allow % and other symbols and only 1 character per guess
            Scanner in = new Scanner(System.in);
            char userEntered = ' ';
            System.out.println("--------------------------------------------------");
            System.out.print("Guess A Letter: ");
            String userInput = in.nextLine();

            if (userInput.length() != 1) {
                System.out.println("You Entered In More Than One Character. Please Only Guess One At A Time.");
                errors = true;
            } else if (!Character.isLetter(userInput.charAt(0))) {
                System.out.println("You Entered In Something That Is Not A Letter. Please Only Enter Letters.");
                errors = true;
            } else {
                userEntered = userInput.charAt(userInput.length() - 1);
                errors = false;
            }
            // System.out.println("User Selected: " + userEntered);

//            Penalize the user for incorrect guesses, but not if it has been already guessed
            if (!errors) {
                if (!guessedAlready.contains(userEntered)) {
                    boolean noChar = true;
                    for (int i = 0; i < phrase.length(); i++) {
                        if (Character.toUpperCase(phrase.charAt(i)) == Character.toUpperCase(userEntered)) {
                            noChar = false;
                            newHiddenText.setCharAt(i, phrase.charAt(i));
                        }
                    }
                    guessedAlready.add(userEntered);
                    if (noChar) {
                        lives -= 1;
                    }
                    String guessedString = "";
                    for (char letters : guessedAlready) {
                        guessedString += " " + letters;
                    }
                    System.out.println("Lives Left: " + lives);
                    System.out.println("Guessed Letters:" + guessedString);
                    System.out.println("The Hidden Phrase: " + newHiddenText);
                } else {
                    System.out.println("You Have Already Guessed: " + userEntered + " (No Lives Were Taken Away).");
                }
            }
        }


//        Tell the user they won or lost
        if (lives == 0) {
            System.out.println("--------------------------------------------------");
            System.out.println("You have run out of lives! The hidden phrase was: " + phrase);
        } 
        if (!newHiddenText.toString().contains("*") && lives != 0) {
            System.out.println("--------------------------------------------------");
            System.out.println("Congratulations! You've guessed the correct phrase: " + phrase);
        }
    }
}