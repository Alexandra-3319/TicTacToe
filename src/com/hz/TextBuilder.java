package com.hz;

public abstract class TextBuilder {

    protected String _text;

    protected void paragraph1(String content) {
        _text += "\n" + content;
    }

    protected void paragraph2(String content) {
        _text += "\n" + content;
    }

    protected void paragraph3(String content) {
        _text += "\n" + content;
    }

    protected void paragraph4(String content) {
        _text += "\n" + content;
    }

    abstract String getText(String para1, String para2, String para3, String para4);
}
