TicTacToe game:
Alexandra en Tijn

We used visual studio code with an extension pack for java, a live share extention, to make sure we could both edit the same file live, which was useful when making the initial game together. we also referred to refactoring.guru and the lesson material often for information.

We made the base game together and they we applied the different patterns to it.
us making the base game and implementing the stragegy pattern (also made this file together)->
[a photo of day 1](AlexTijn.jpeg)
[a photo of day 2](TijnAlex.jpeg)

Design patterns implemented:

1. [Builder](/src/com/hz/TextBuilder.java) (by Tijn)
The builder class (named TextBuilder.java) takes four input Strings and adds /ln between them to make sure they appear properly in the console. I initially experimented with a much broader approach that also used a Director, but I found this unnecessarily complicated because this function would only be called once anyways.
I had also initially named the builder IntroductionBuilder, but I figured that it would be best to keep this class broad, because of course it can be used for more than just building the introduction.
The current version of the builder has three layers of specification:
  1. the interface, this is simply a builder that uses strings in some way.
  2. the TextBuilder, this is a builder that adds line breaks to four strings, and that has a get() function.
  3. the instance introductionBuilder, this is an instance of the TextBuilder that specifially outputs an introduction to our game.

    the code snippet below describes the working best, it simply takes the _text variable and adds line breaks to it. it then outputs this variable.

from [TextBuilder](/src/com/hz/TextBuilder.java):
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

3. [Strategy](/src/com/hz/Builder.java) (Peer programmed by both)
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

from [TextBuilder](/src/com/hz/TextBuilder.java):
_________________

public class TextBuilder implements builder { //a builder to seperate lines of text into a more readable format.
   String _text = "";

   @Override
   public void paragraph1(String content) {
      _text += "\n" + content;
   }
_________________