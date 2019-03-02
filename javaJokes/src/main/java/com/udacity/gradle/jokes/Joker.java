package com.udacity.gradle.jokes;

import java.util.Random;

public class Joker {
    final static String[] jokesList = new String[]{
            "This is totally a funny joke 1",
            "another totally  funny joke 2",
            "another another  totally  funny joke :D 3",
            "This is the last totally a funny joke 4"
    };
    public String getJoke() {
        Random random = new Random();
         return jokesList[random.nextInt(jokesList.length)];
    }
}
