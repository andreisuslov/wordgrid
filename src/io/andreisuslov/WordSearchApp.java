package io.andreisuslov;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class WordSearchApp {
    public static void main(String[] args) {
        final int GRID_SIZE = 10; // setting grid size, to begin with
        char[][] grid = new char[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                grid[i][j] = '_';
            }
        }
        // I need to get a bunch of words that I need to put into this grid
        List<String> words = Arrays.asList("One", "Two", "Three");

        for (String word : words) {
            // random x and y via ThreadLocalRandom - recommended way
            int x = ThreadLocalRandom.current().nextInt(0, GRID_SIZE);
            int y = ThreadLocalRandom.current().nextInt(0, GRID_SIZE);
            grid[x][y] = word.charAt(0);
        }

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                System.out.println(grid[i][j] + " ");
            } // inner loop is the end of the row
            System.out.println(""); // the cursor here goes to the next line
        }
    }
}
