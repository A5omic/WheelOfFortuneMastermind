**Wheel of Fortune**


**Overview:**

Within this project we were to build the fan favorite game wheel of fortune. We were provided with some starter code that extracts some sayings from a text file, and were tasked with creating four different versions of the game to show our understandings of java. The game is a command-line simplified version of [Wheel of Fortune](https://davidwolber.notion.site/WOF-and-Mastermind-with-Inheritance-Project-fe031ecd073b4780b70bd64add68f2c5). The game generates a phrase, e.g., "The only thing to fear is fear itself" and shows the user the phrase with all letters hidden, e.g., "*** **** ***** ** **** ** **** ******". The user guesses a letter. If the letter is in the phrase, all instances are displayed. If it is not, a wrong guess is counted. So if 'r' were guessed, the new hidden phrase would be *** **** ***** ** ***r ** ***r ******. In this new version for project 4 I take on the idea of abstract classes and interfaces that are used throughout the files in order to make good object oriented reusable code. The goal is to add that all into the previous project as well as build up a new mastermind project that falls into this guessing gamne class.



**App Development:**

For the user to deploy the app, they can run it in one of two ways if they have any sort of IDE, such as InteliJ Then they can just download the code from GitHub open up the project in InteliJ and then, from there, select which version of wheel of Fortune they would like to play in this case there is the user version where they can play games themselves, or the ai version where there are three bots one that does random guessing, another the does vowel guessing first, and another that does common letters guessing first, from there if you also want to view the part 2 code you can play mastermind.


If the user does not have an ID, they can still run the program as long as they have Java installed on there computer to do this you would download the program open up a terminal or command prompt window cd Into the directory where the Java files are located most likely /downloads/wheeloffortune/src/ And then they can run the Java compile command, and the Java run command:

javac WheelOfFortuneMain.java

java WheelOfFortuneMain


And from there, they can play the game in the command prompt window. Enjoy!




**Development Process:**

In the development process of this app, I started off with the previous given code from the projects and then went on from there, making sure to read all the way thorugh the documentation and writing down all of the classes I thought were needed and would be important. From there I then went on and started creating the overarching classes that would be used throughout the game such as the Game class, the AllGamesRecord class and so on, so that I had the baseline classess that I needed all filled out and made. 


From there I then went on to fill out the different files and start to switch over files to implement the new abstract classess, from there I used the built in add methods button in intelij to really get a feel for what the code would look like by having it craft all of the method headers so I could see all that was needed to be filled in, and from there it was just coding out each of the fucntions and trial and error to get the final product to run.


For part 2 of the code I followed the same structor of the code and ideas where I started with creating the guessing game class and then worked on implementing it into my already made classes. Once I has it implemented I then moved over to mastermind and worked on coding both of those simulatniously.


Some of the key concepts that I learned through this project are:


Overall, I feel like my knowledge for how classess work with eachother has been expanded, as well as my knowledge on inheritence. I work a lot daily with code but they are normally different projects, so there is not much use of inheritence within the code, so this was a great learning area for me within this project.




**Completed:**
P1:

AllGamesRecord'

FrequentLetterPlayer

Game

GameRecord

RandomGuessPlayer

VowelFirstPlayer

WheelOfForutneAIGame

WheelOfFortuneInheritance

WheelOfFortunePlayer

WheelOfFortuneUserGame




**Test Plan:**

For my test plan Michael to start was just a test the basics so to start I would see if a user entered in one character two characters or more would then receive an error in which they did which is expected and they get a nice print out statement, telling them that they should only be entering in one character at a time.


Next up, I then wanted to see if the user didn’t enter in anything and just tried to click enter they should also then get a nice message asking them to please enter in a character. And then from there, I then knew that I was getting one and only one character so from there I took the character and then would check through the Phrase to see if any of the characters lined up another test that had to be done here was whether or not the character was entered in in caps or in lowercase so I would basically call the tolowercase function on the character So that the cases of the characters I’m trying to match are all the same.


Lastly, one of the trickier things to do was to see if the user had already guessed that certain character, and if so, not to subtract a life to do this I set up a list of characters and basically every single time the user guest I would see if the character that the guest is within the list if it is, I would let them know and not subtract the life and if it was not, then the game would continue on, but I would make sure to append the character that they just guessed to the list.


And then, lastly, for testing I just played the game multiple times trying all sorts of different edge cases entering in multiple spaces, random characters and different things to see if I could get it to break or throw any errors.




**Test Video:**

Link (Might have to download the video to play it due to size): 
[Video](https://drive.google.com/file/d/1M7z8X9FWuw7qJ_lRqLkA0jB-W4REYBMk/view?usp=sharing)
