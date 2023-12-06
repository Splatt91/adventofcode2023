package day.three;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class DayThreeTests {

    @Test
    void createRowFromLine() {
        Gondola gondola = new Gondola();
        String line = "467..114..";
        char[] expected = {'4', '6', '7', '.', '.', '1', '1', '4', '.', '.'};
        char[] actual = gondola.createRowFromLine(line);
        assertArrayEquals(expected, actual);
    }

    @Test
    void findNumbersAdjacentToSymbol() {
        Gondola gondola = new Gondola();
        int[] expected = {467, 35, 633, 617, 592, 755, 664, 598};
        int[] actual = gondola.findNumbersAdjacentToSymbol(new char[][] {
                new char[]{'4', '6', '7', '.', '.', '1', '1', '4', '.', '.'},
                new char[]{'.', '.', '.', '*', '.', '.', '.', '.', '.', '.'},
                new char[]{'.', '.', '3', '5', '.', '.', '6', '3', '3', '.'},
                new char[]{'.', '.', '.', '.', '.', '.', '#', '.', '.', '.'},
                new char[]{'6', '1', '7', '*', '.', '.', '.', '.', '.', '.'},
                new char[]{'.', '.', '.', '.', '.', '+', '.', '5', '8', '.'},
                new char[]{'.', '.', '5', '9', '2', '.', '.', '.', '.', '.'},
                new char[]{'.', '.', '.', '.', '.', '.', '.', '7', '5', '5'},
                new char[]{'.', '.', '.', '$', '.', '.', '*', '.', '.', '.'},
                new char[]{'.', '6', '6', '4', '.', '.', '5', '9', '8', '.'}
        });
        assertArrayEquals(expected, actual);
    }
}
