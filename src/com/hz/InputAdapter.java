package com.hz;

import java.util.Arrays;

public class InputAdapter {

        public static void InputAdapter() {
                System.out.println(Choices[1]);
                System.out.println(Choices[1][1]);
        }

    private static String[][] Choices = {
            { "een", "ONE", "One", "one", "1", "Een", "EEN", "top left", "topleft", "TOPLEFT", "TOP LEFT", "Top Left",
                    "Topleft", "Top left" },
            { "twee", "TWO", "Two", "two", "2", "Twee", "TWEE", "top middle", "topmiddle", "TOPMIDDLE", "TOP MIDDLE",
                    "Top Middle", "Topmiddle", "Top middle" },
            { "drie", "THREE", "Three", "three", "3", "Drie", "DRIE", "top right", "topright", "TOPRIGHT", "TOP RIGHT",
                    "Top Right", "Topright", "Top right" },
            { "vier", "FOUR", "Four", "four", "4", "Vier", "VIER", "middle left", "middleleft", "MIDDLELEFT",
                    "MIDDLE LEFT", "Middle Left", "Middleleft", "Middle left" },
            { "vijf", "FIVE", "Five", "five", "5", "Vijf", "VIJF", "middle", "MIDDLE", "Middle", "MIDDLE", "Middle",
                    "Middle", "Middle" },
            { "zes", "SIX", "Six", "six", "6", "Zes", "ZES", "middle right", "middleright", "MIDDLERIGHT",
                    "MIDDLE RIGHT", "Middle Right", "Middleright", "Middle right" },
            { "zeven", "SEVEN", "Seven", "seven", "7", "Zeven", "ZEVEN", "bottom left", "bottomleft", "BOTTOMLEFT",
                    "BOTTOM LEFT", "Bottom Left", "Bottomleft", "Bottom left" },
            { "acht", "EIGHT", "Eight", "eight", "8", "Acht", "ACHT", "bottom middle", "bottommiddle", "BOTTOMMIDDLE",
                    "BOTTOM MIDDLE", "Bottom Middle", "Bottommiddle", "Bottom middle" },
            { "negen", "NINE", "Nine", "nine", "9", "Negen", "NEGEN", "bottom right", "bottomright", "BOTTOMRIGHT",
                    "BOTTOM RIGHT", "Bottom Right", "Bottomright", "Bottom right" }
    };
    private ConsoleReader reader;
    private ConsoleWriter writer;

    public InputAdapter(ConsoleReader reader) {
        this.reader = reader;
    }

    public InputAdapter(ConsoleWriter writer) {
        this.writer = writer;
    }

//     public Integer readResponse() {
//         String rawAnswer = this.reader.readLine();
//         return this.isConvered(rawAnswer);
//     }

public Integer isConvered(Integer answer) {
        int place = 0;
        for(int i = 0; i < 9; i++) {
            if (Arrays.asList(InputAdapter.Choices[i]).contains(answer)) {
                System.out.println("You chose " + answer);
                System.out.println("The place is " + i);
                place = i;
            } else {
                return null;
            }
          
        }
        return place;
    }

}

