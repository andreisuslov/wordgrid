package project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Grid {

    private int gridSize; // setting grid size, to begin with
    private char[][] contents;
    private List<Coordinate> coordinates = new ArrayList<>();

    private enum Direction {
        HORIZONTAL,
        VERTICAL,
        DIAGONAL
    }

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
            Collections.shuffle(coordinates);
            for (Coordinate coordinate : coordinates) {
                int x = coordinate.x;
                int y = coordinate.y;
                if (doesFit(word, coordinate)) {
                    for (char c : word.toCharArray()) {
                        contents[x][y++] = c;
                    }
                    break;
                }
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

    private Direction doesFit(String word, Coordinate coordinate) {
        List<Direction> directions = Arrays.asList(Direction.values());
        Collections.shuffle(directions);
        for (Direction direction : directions) {
            if (doesFit(word, coordinate, direction)) {
                return direction;
            }
        }
        return null;
    }

    private boolean doesFit(String word, Coordinate coordinate, Direction direction) {
        int wordLength = word.length();
        switch (direction) {
            case HORIZONTAL:
                if (coordinate.y + word.length() > gridSize) return false;
                for (int i = 0; i < word.length(); i++) {
                    if (contents[coordinate.x][coordinate.y + 1] != '_') return false;
                }
                break;
            case VERTICAL:
                if (coordinate.x + word.length() > gridSize) return false;
                for (int i = 0; i < word.length(); i++) {
                    if (contents[coordinate.x + i][coordinate.y] != '_') return false;
                }
                break;
            case DIAGONAL:
                if (coordinate.x + word.length() > gridSize || coordinate.y + word.length() > gridSize) return false;
                for (int i = 0; i < word.length(); i++) {
                    if (contents[coordinate.x + i][coordinate.y + i] != '_') return false;
                }
                break;
        }
        return true;
    }


}
