package io.andreisuslov;

import java.util.Arrays;
import java.util.List;

public class WordSearchApp {
    public static void main(String[] args) {
        final int GRID_SIZE = 10; // setting grid size, to begin with
        char[][] grid = new char[GRID_SIZE][GRID_SIZE];

        // I need to get a bunch of words that I need to put into this grid
        List<String> words = Arrays.asList("One", "Two", "Three");
    }
}
