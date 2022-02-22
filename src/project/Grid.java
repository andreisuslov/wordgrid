package project;

import org.apache.maven.shared.utils.StringUtils;

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
        DIAGONAL,
        HORIZONTAL,
        VERTICAL,
        DIAGONAL_INVERSE,
        HORIZONTAL_INVERSE,
        VERTICAL_INVERSE
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
        Collections.shuffle(coordinates);
        for (String word : words) {
            for (Coordinate coordinate : coordinates) {
                int x = coordinate.x;
                int y = coordinate.y;
                Direction selectedDirection = getDirectionForFit(word, coordinate);
                if (selectedDirection != null) { // I found the direction
                    switch (selectedDirection) {
                        case HORIZONTAL:
                            for (char c : word.toCharArray()) {
                                contents[x][y++] = c;
                            }
                            break;
                        case VERTICAL:
                            for (char c : word.toCharArray()) {
                                contents[x++][y] = c;
                            }
                            break;
                        case DIAGONAL:
                            for (char c : word.toCharArray()) {
                                contents[x++][y++] = c;
                            }
                            break;
                        case HORIZONTAL_INVERSE:
                            for (char c : word.toCharArray()) {
                                contents[x][y--] = c;
                            }
                            break;
                        case VERTICAL_INVERSE:
                            for (char c : word.toCharArray()) {
                                contents[x--][y] = c;
                            }
                            break;
                        case DIAGONAL_INVERSE:
                            for (char c : word.toCharArray()) {
                                contents[x--][y--] = c;
                            }
                            break;
                    }
                    break; // done with the case so I can go to the next case
                }
            }
        }
        randomFillGrid();
    }

    public void displayGrid() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                System.out.print(contents[i][j] + "  ");
            } // inner loop is the end of the row
            System.out.println(""); // the cursor here goes to the next line
        }
    }

    private void randomFillGrid() {
        String allCapitalLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (contents[i][j] == '_') {
                    int randomIndex = ThreadLocalRandom.current().nextInt(0, allCapitalLetters.length());
                    contents[i][j] = allCapitalLetters.charAt(randomIndex);
                }
            }
        }
    }

    private Direction getDirectionForFit(String word, Coordinate coordinate) {
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
                    if (contents[coordinate.x][coordinate.y + i] != '_') return false;
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
            case HORIZONTAL_INVERSE:
                if (coordinate.y < word.length()) return false;
                for (int i = 0; i < word.length(); i++) {
                    if (contents[coordinate.x][coordinate.y - i] != '_') return false;
                }
                break;
            case VERTICAL_INVERSE:
                if (coordinate.x < word.length()) return false;
                for (int i = 0; i < word.length(); i++) {
                    if (contents[coordinate.x - i][coordinate.y] != '_') return false;
                }
                break;
            case DIAGONAL_INVERSE:
                if (coordinate.x < word.length() || coordinate.y < word.length()) return false;
                for (int i = 0; i < word.length(); i++) {
                    if (contents[coordinate.x - i][coordinate.y - i] != '_') return false;
                }
                break;
        }
        return true;
    }
}
