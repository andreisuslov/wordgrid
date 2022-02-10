package project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Grid {

    private int gridSize; // setting grid size, to begin with
    private char[][] contents;
    private List<Coordinate> coordinates = new ArrayList<>();

    private class Coordinate {
        int x;
        int y;
        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public Grid(int gridSize) {
        this.gridSize = gridSize;
        contents = new char[gridSize][gridSize];

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                coordinates.add(new Coordinate(i, j));
                contents[i][j] = '_';
            }
        }
    }

    public void fillGrid(List<String> words) {
        for (String word : words) {
            // random x and y via ThreadLocalRandom - recommended way
            int x = ThreadLocalRandom.current().nextInt(0, gridSize);
            int y = ThreadLocalRandom.current().nextInt(0, gridSize);
            if (y + word.length() >= gridSize) continue;
            for (char c : word.toCharArray()) {
                contents[x][y++] = c; // populate contents
            }
        }
    }

    public void displayGrid() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                System.out.print(contents[i][j] + " ");
            } // inner loop is the end of the row
            System.out.println(""); // the cursor here goes to the next line
        }
    }
}
