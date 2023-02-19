package com.hz;

public class IntroductionBuilder extends TextBuilder { //A builder to generate our introduction, paragraph4 is the only one we needed to override. we also implemented getText

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