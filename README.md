RESIT CHANGES: strategy is removed and its documentation is now #5, documentation #3 and #4 were added, see below.


This code can also be viewed at: https://github.com/Alexandra-3319/TicTacToe.git

TicTacToe game:
Alexandra en Tijn

We used visual studio code with an extension pack for java, a live share extention, to make sure we could both edit the same file live, which was useful when making the initial game together. we also referred to refactoring.guru and the lesson material often for information.

We made the base game together and they we applied the different patterns to it.
us making the base game and implementing the stragegy pattern (also made this file together)->
[a photo of day 1](AlexTijn.jpeg)
[a photo of day 2](TijnAlex.jpeg)

Design patterns implemented:

1. [Builder](/src/com/hz/IntroductionBuilder.java) (by Tijn)
The builder class (named TextBuilder.java) takes four input Strings and adds /ln between them to make sure they appear properly in the console. I initially experimented with a much broader approach that also used a Director, but I found this unnecessarily complicated because this function would only be called once anyways.
I had also initially named the builder IntroductionBuilder, but I figured that it would be best to keep this class broad, because of course it can be used for more than just building the introduction.
The current version of the builder has three layers of specification:
  1. the interface, this is simply a builder that uses strings in some way.
  2. the TextBuilder, this is a builder that adds line breaks to four strings, and that has a get() function.
  3. the instance introductionBuilder, this is an instance of the TextBuilder that specifially outputs an introduction to our game.

the code snippet below describes the working best, it simply takes the _text variable and adds line breaks to it. it then outputs this variable.

from [TextBuilder](/src/com/hz/IntroductionBuilder.java):
_________________

   @Override
   public void paragraph4(String content) {
      _text += "\n \n" + content;
   }

   String getText(String para1, String para2, String para3, String para4) {
      this.paragraph1(para1);
      this.paragraph2(para2);
      this.paragraph3(para3);
      this.paragraph4(para4);
      return this._text;
   }
_________________



2. [Adapter](src\com\hz\InputAdapter.java) (by Alexandra)
In the game you are able to choose a position for your X to be placed on, we first had the option of using 1-9. But since explaining what number is what position I made it so that you can say a position like, top left and it would also work. I did this through an adapter. 

I made a two demensional array with the psoitions names that you could posibliy choose from. 

Then I used the Console reader to get the players input.

_________________

        private ConsoleReader reader;

        public InputAdapter(ConsoleReader reader) {
                this.reader = reader;
        }

        public Integer readResponse() {
                String rawAnswer = this.reader.readLine();
                return this.isConvered(rawAnswer);
        } 
_________________   

Inside the readResponse I call the functions isConvered where it checkes wheter the the input of the player is find in the two deminsional array. 

_________________

        public Integer isConvered(String answer) {
                int place = 0;
                for (int i = 0; i < 9; i++) {
                        if (Arrays.asList(InputAdapter.Choices[i]).contains(answer)) {
                                place = i + 1;
                        }
                }
                return place;
        }
_________________

inside the functions I made an integer variable called place, this will be the "translation" to the position chosen but as a number. if the answer is indeed in one of the arrays it takes the index of the arrays placement that's inside the parent array. You need to at the +1 ofcourse since the index nubes start at 0 and thats not a position. 

I call the functions readResponse in the [Main game file](src\com\hz\TicTacToe.java) and have the translated integer as the players position. 

_________________

            ConsoleReader reader = new ConsoleReader();
            InputAdapter adapter = new InputAdapter(reader);

            int playerPos = adapter.readResponse();
_________________

3. [Template Method](src/com/hz/TextBuilder.java) (Tijn) 
Note: i have replaced the original implentation of the Strategy with this design pattern.

The template method is normally used to avoid code duplication using inheritance, but tic-tac-toe was too small to have the problem of code duplication. For this reason I added a result word-art generator to have another extension for the TextBuilder class. 
The [Textbuilder](src/com/hz/TextBuilder.java) class is the actual template method and [IntroductionBuilder](src/com/hz/IntroductionBuilder.java) and [ArtBuilder](src/com/hz/ArtBuilder.java) both inherit from this template method. Following the definition on TheRefactoringGuru I made sure that the template method had a few pre-defined methods (paragraph1 paragraph2 etc.) (that could be overridden) and one abstract method that had to be overridden. I also made sure that the children of this class had different implementations of this abstract class, see example below:

_________________
from [IntroductionBuilder](src/com/hz/IntroductionBuilder.java):
   String getText(String para1, String para2, String para3, String para4) {
      this.paragraph1(para1);
      this.paragraph2(para2);
      this.paragraph3(para3);
      this.paragraph4(para4);
      return this._text;
   }
_________________

from [ArtBuilder](src/com/hz/ArtBuilder.java)
_________________
       String getText(String para1, String para2, String para3, String para4) {
        if (this._text == null) {
            System.out.println("Generating art...");
        }
        this.paragraph1(para1);
        this.paragraph2(para2);
        this.paragraph3(para3);
        this.paragraph4(para4);
        return this._text;
    }
_________________
as you can see, ArtBuilder needed to do an additional check and output an extra line.

4. [Factory](/src/com/hz/PieceFactory.java) (Alexandra)
In the game you have pieces you place on the game board like the X for the player and the O for the cpu. I decided to make a factory of the pieces you can place so that in the future you are able to choose with what pieces you play with.

I first created the interface under which the pieces would fall, called Piece.
This is the interface for all the types of pieces with a method that is called draw, this method will be used by the types of pieces each with their personal outcome of the method.
_________________

        public interface Piece {
        char draw();
        }
_________________
from [Factory](/src/com/hz/Piece.java)

 This is the code for the different types of pieces where they all have the draw function and return their own piece type, what they return can be anything you want it to be so it is really easy to create new pieces that you would like, for example the first letter of your name or an emoij.

_________________

        public class Cross implements Piece {

        @Override
        public char draw() {
        return 'X';
        }
        }
_________________
from [Factory](/src/com/hz/Cross.java)

_________________

        public class Circle implements Piece {

        @Override
        public char draw() {
        return 'O';
        }
        }
_________________
from [Factory](/src/com/hz/Circle.java)

_________________

        public class Player implements Piece {

        @Override
        public char draw() {
        return 'P';
        }
        }
_________________
from [Factory](/src/com/hz/Player.java)

_________________

        public class CPU implements Piece {

        @Override
        public char draw() {
        return 'C';
        }
        }
_________________
from [Factory](/src/com/hz/CPU.java)
 
Then I created the factory of the pieces itself, where they get "made" ypu can see this by the return "new ....()" this is where the pieces come from when calling it in the main file which is TicTacToe

_________________

        public class PieceFactory {
                
        //use getShape method to get object of type shape 
        public Piece getPiece(String pieceType){
        if(pieceType == null){
                return null;
        }		
        if(pieceType.equalsIgnoreCase("CROSS")){
                return new Cross();
                
        } else if(pieceType.equalsIgnoreCase("CIRCLE")){
                return new Circle();
                
        } else if(pieceType.equalsIgnoreCase("PLAYER")){
                return new Player();
        
        } else if(pieceType.equalsIgnoreCase("CPU")){
                return new CPU();
        }
        
        return null;
        }
        }
_________________
from [Factory](/src/com/hz/PieceFactory.java)

In the TicTacToe.java file there are a few things that I changed so that the pieces will be created from the factory and are not set symbols/pieces like they were before. The changes happend inside the place piece function since that is the only place they are being used and created. 
Nothing changed in the switch case but I added it here so it would still be understandable what happens with the symbol variable you see.

The things I added I put a star infront of so it is easier to recognize. 

_________________

        public static void placePiece(char[][] gameBoard, int pos, String user) {
*               PieceFactory pieceFactory = new PieceFactory();
                char symbol = ' ';
                if (user.equals("player")) {
*               Piece piece = pieceFactory.getPiece("CROSS");
+               symbol = piece.draw();
                playerPositions.add(pos);
                } else if (user.equals("cpu")) {
*               Piece piece = pieceFactory.getPiece("CIRCLE");
*               symbol = piece.draw();
                cpuPositions.add(pos);
                }

                switch (pos) {
                case 1:
                        gameBoard[0][0] = symbol;
                        break;
                case 2:
                        gameBoard[0][2] = symbol;
                        break;
                case 3:
                        gameBoard[0][4] = symbol;
                        break;
                case 4:
                        gameBoard[2][0] = symbol;
                        break;
                case 5:
                        gameBoard[2][2] = symbol;
                        break;
                case 6:
                        gameBoard[2][4] = symbol;
                        break;
                case 7:
                        gameBoard[4][0] = symbol;
                        break;
                case 8:
                        gameBoard[4][2] = symbol;
                        break;
                case 9:
                        gameBoard[4][4] = symbol;
                        break;
                }
        }
_________________
from [Factory](/src/com/hz/TicTacToe.java)

For now it just grabes the Cross and Circle piece as a default but if wanted we could add the possibility to have the player play with different pieces in the future, with for example a readerinput and an extra if statement. 

||REMOVED||
5. [Strategy](/src/com/hz/Builder.java) (Peer programmed by both)
The Builder interface is used to make sure that any other builders follow the format described in the interface. This provides much less complexity, if anyone were to make an extended version of tic-tac-toe that needed many builders. It was decided to make a strategy for the builder and not another class, because we thought the builder was most likely to be implemented again in some other way, thus needing regulation the most.

below is a code snippet that summarizes this pattern, the builder interface defines methods, which are then implemented and overridden by the TextBuilder class
from [Builder](/src/com/hz/Builder.java):
_________________

public interface builder { //interface for a builder that uses strings
     void paragraph1(String content);

     void paragraph2(String content);

     void paragraph3(String content);

     void paragraph4(String content);
}
_________________

from [TextBuilder](/src/com/hz/IntroductionBuilder.java):
_________________

public class TextBuilder implements builder { //a builder to seperate lines of text into a more readable format.
   String _text = "";

   @Override
   public void paragraph1(String content) {
      _text += "\n" + content;
   }
_________________