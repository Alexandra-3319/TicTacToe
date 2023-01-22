package com.hz;

public class TextBuilder implements builder { //a builder to seperate lines of text into a more readable format.
   String _text = "";

   @Override
   public void paragraph1(String content) {
      _text += "\n" + content;
   }

   @Override
   public void paragraph2(String content) {
      _text += "\n" + content;
   }

   @Override
   public void paragraph3(String content) {
      _text += "\n" + content;
   }

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

}