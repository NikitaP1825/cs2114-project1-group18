

\# Word² - CS 2114 Project 1



Group 18 | Fall 2026



\## About Our Project



Word² is a word-guessing game inspired by Wordle, but with a twist.

Instead of guessing one word, the player has to figure out two

different five-letter words at the same time, using only seven guesses.



Every guess is applied to both hidden words, and the player receives

separate feedback for each one.



Our goal was to create a simple, fully functional game while learning

how to turn an initial project idea into an actual working program.



\## How to Play



The game randomly selects two different five-letter words.



After entering a guess, the player receives feedback for both words:



\- ✓ - Correct letter in the correct position

\- \~ - Correct letter in the wrong position

\- X - Letter is not in the word



Players have seven valid guesses to solve both words. Invalid words,

incorrect word lengths, and repeated guesses are rejected without

using an attempt.



The game ends when both words are guessed correctly or all seven

attempts have been used.



\## Features



Our completed game includes:



\- Two randomly selected five-letter target words

\- Seven valid guesses shared between both words

\- Input validation and detection of repeated guesses

\- Separate feedback for both target words

\- Correct handling of repeated letters

\- A game board showing previous guesses and their feedback

\- Win and loss detection

\- The option to restart without closing the program

\- Game history that lets players review previous games, including

&#x20; their guesses, feedback, and results



Game history is stored while the program is running and resets

when the program is closed.



\## How to Run the Game



1\. Download or clone this GitHub repository.

2\. Open the project in Eclipse with a compatible Java JDK.

3\. Make sure all five game classes are in the src folder.

4\. Open GameDisplay.java.

5\. Right-click the file and select Run As > Java Application.

6\. Follow the instructions in the console to start playing.



After each game, players can choose to play again, review their

game history, or exit.



\## Project Structure



We divided the game into five classes so each class has its

own responsibility.



\- Word2Game.java - Controls the game, tracks guesses, stores

&#x20; feedback, and determines when the game ends.



\- GameDisplay.java - Handles the console interface, displays

&#x20; feedback and previous guesses, and manages the game history.



\- WordBank.java - Stores the dictionary and randomly selects

&#x20; two different target words.



\- WordValidator.java - Checks that guesses meet the game's

&#x20; requirements and have not already been submitted.



\- FeedbackGenerator.java - Compares guesses against the target

&#x20; words and generates the appropriate feedback.



\## Testing



We used JUnit 5 to test the main parts of our program, including

normal gameplay and invalid-input cases.



Our tests cover:



\- Starting and resetting a game

\- Valid and invalid guesses

\- Repeated guesses

\- Word selection and dictionary validation

\- Correct and incorrect letter positions

\- Repeated letters in feedback

\- Invalid input and exception handling



Our latest test run passed all 29 tests.



To run the tests in Eclipse:



1\. Make sure JUnit 5 is included in the project's build path.

2\. Open the test classes inside the src folder.

3\. Right-click the project and select Run As > JUnit Test.



The four test classes are:



\- Word2GameTest.java

\- WordValidatorTest.java

\- WordBankTest.java

\- FeedbackGeneratorTest.java



\## Team Members



\- Nikita - Word2Game, JUnit tests, and integration

\- Aditi - GameDisplay

\- Chandrika - WordBank and WordValidator

\- Madison - FeedbackGenerator



We used GitHub to combine our work, track changes, and integrate

our individual classes into the final game.



\## System Diagram



Our system diagram shows how our five main classes work together.



Word2Game acts as the main controller and connects the console

interface to the word bank, input validator, and feedback generator.



!\[Word2 System Diagram](docs/system-diagram.png)

