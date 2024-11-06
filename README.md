**Wheel of Fortune**


**Overview:**

Within this project we were to build the fan favorite game wheel of fortune. We were provided with some starter code that extracts some sayings from a text file, and were tasked with creating four different versions of the game to show our understandings of java. The game is a command-line simplified version of [Wheel of Fortune](https://davidwolber.notion.site/WOF-and-Mastermind-with-Inheritance-Project-fe031ecd073b4780b70bd64add68f2c5). The game generates a phrase, e.g., "The only thing to fear is fear itself" and shows the user the phrase with all letters hidden, e.g., "*** **** ***** ** **** ** **** ******". The user guesses a letter. If the letter is in the phrase, all instances are displayed. If it is not, a wrong guess is counted. So if 'r' were guessed, the new hidden phrase would be *** **** ***** ** ***r ** ***r ******.
In 



**App Development:**

For the user to deploy the app, they can run it in one of two ways if they have any sort of IDE, such as InteliJ Then they can just download the code from GitHub open up the project in InteliJ and then, from there, select which version of wheel of Fortune they would like to play in this case there is the bot version the novice version the object-oriented version and the structured version and the extra credit version and then just click the run button up in the top right corner.


If the user does not have an ID, they can still run the program as long as they have Java installed on there computer to do this you would download the program open up a terminal or command prompt window cd Into the directory where the Java files are located most likely /downloads/wheeloffortune/src/ And then they can run the Java compile command, and the Java run command:

javac WheelOfFortuneMain.java

java WheelOfFortuneMain


And from there, they can play the game in the command prompt window. Enjoy!




**Development Process:**

In the development process of this app, I started off with the given code snippet and from there read through the initial instructions where we were to build the wheel of Fortune game all within the main method. this first build of the app was overall fairly straightforward. The biggest thing that I had an issue with personally was figuring out that I should just read the whole prompt instead of just reading a snippet of the prompt so I would read a snippet of the prompt and it would say the phrase needs to be hidden so I would then go and build the logic for the phrase to be hidden, but then I would read further on that we would need to include spaces just normal white spaces, which makes sense. It was just an error on my part by not reading the full prompt before I started coding. 


Same with all of the other versions in the wheel of Fortune methods and object oriented version I would read that we had to use methods and objects for the object oriented one and I would just go and coat it up and did not realize that we needed to have specific names, such as random phrase, get gas for the methods one and so on so what I’m realizing from this project is that my biggest enemy is myself and I need to read the prompt fully before I go on and start to code.


Some of the key concepts that I learned through this project are:


Overall, I feel like my algorithm for checking. The characters of the user entered in works well. I think there might be a way to make it a little bit better I would say or maybe even faster because right now I’m just using a four loop to basically scan through each character in the hidden phrase see if they match and if they do match then show that current character I feel like there is probably a better way to basically read it all at once instead of just going through each individual character, but it does work and that was what I’m happy about most.




**Completed:**

WheelOfFortuneMain

WheelOfFortuneMethods

WheelOfFortuneObject

WheelOfFortuneBot

Extra Credit - Added a complete phrase guess




**Test Plan:**

For my test plan Michael to start was just a test the basics so to start I would see if a user entered in one character two characters or more would then receive an error in which they did which is expected and they get a nice print out statement, telling them that they should only be entering in one character at a time.


Next up, I then wanted to see if the user didn’t enter in anything and just tried to click enter they should also then get a nice message asking them to please enter in a character. And then from there, I then knew that I was getting one and only one character so from there I took the character and then would check through the Phrase to see if any of the characters lined up another test that had to be done here was whether or not the character was entered in in caps or in lowercase so I would basically call the tolowercase function on the character So that the cases of the characters I’m trying to match are all the same.


Lastly, one of the trickier things to do was to see if the user had already guessed that certain character, and if so, not to subtract a life to do this I set up a list of characters and basically every single time the user guest I would see if the character that the guest is within the list if it is, I would let them know and not subtract the life and if it was not, then the game would continue on, but I would make sure to append the character that they just guessed to the list.


And then, lastly, for testing I just played the game multiple times trying all sorts of different edge cases entering in multiple spaces, random characters and different things to see if I could get it to break or throw any errors.




**Test Video:**

Link (Might have to download the video to play it due to size): 
[Video](https://drive.google.com/file/d/1M7z8X9FWuw7qJ_lRqLkA0jB-W4REYBMk/view?usp=sharing)
