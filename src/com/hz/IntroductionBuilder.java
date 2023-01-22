package com.hz;

public class IntroductionBuilder {
   String _introduction = "";

   void welcome() {
      _introduction += "\nWelcome player, to our fantastic CLI tic-tac-toe game. \n";
   }

   void aiInfo() {
      _introduction += "You will be matched up against our enhanced algorythm\nthat dynamically makes its moves based on RNG-powered-decisionmaking\n";
   }

   void instruction() {
      _introduction += "Simply enter a number on your turn, and a cross will be placed in the corresponding square.\nIf you don't know the rules of tic-tac-toe, google them.\n";
   }

   void goodLuck() {
      _introduction += "\nGood luck! ☺";
   }

   String getIntroduction() {
      this.welcome();
      this.aiInfo();
      this.instruction();
      this.goodLuck();
      return this._introduction;
   }

}