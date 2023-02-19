package com.hz;

public class ArtBuilder extends TextBuilder{ //a builder to generate our art, we implemented getText and didn't need to override any of the other methods

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
}
