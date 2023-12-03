package day.two;

import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DayTwoTests {

    @Test
    void readLineAndLoadCubes() {
        String line = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green";
        Map<String, Integer> expected = Map.of(
                "blue", 3,
                "red", 4
        );
        Map<String, Integer> expected1 = Map.of(
                "red", 1,
                "green", 2,
                "blue", 6
        );
        Map<String, Integer> expected2 = Map.of(
                "green", 2
        );
        CubeGame cubeGame = new CubeGame();
        List<Map<String, Integer>> actual = cubeGame.parseLine(line);

        assertTrue(Maps.difference(expected, actual.get(0)).areEqual());
        assertTrue(Maps.difference(expected1, actual.get(1)).areEqual());
        assertTrue(Maps.difference(expected2, actual.get(2)).areEqual());
    }

    @Test
    void isGamePossibleTrue() {
        CubeGame cubeGame = new CubeGame();
        String line = "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green";
        assertTrue(cubeGame.isPossible(line));
    }

    @Test
    void isGamePossibleFalse() {
        CubeGame cubeGame = new CubeGame();
        String line = "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red";
        assertFalse(cubeGame.isPossible(line));
    }

    @Test
    void idSumOfPossibleGames() {
        CubeGame cubeGame = new CubeGame();
        List<String> lines = new ArrayList<>();
        lines.add("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green");
        lines.add("Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue");
        lines.add("Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red");
        lines.add("Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red");
        lines.add("Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green");
        assertEquals(8, cubeGame.sumOfPossibleGames(lines));
    }

    @Test
    void fewestNumberOfCubesOfEachColor() {
        CubeGame cubeGame = new CubeGame();
        Map<String, Integer> expected = Map.of(
                "red", 14,
                "green", 3,
                "blue", 15
        );
        String line = "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red";
        Map<String, Integer> actual = cubeGame.fewestNumberOfCubesOfEachColor(line);
        assertTrue(Maps.difference(expected, actual).areEqual());
    }

    @Test
    void sumOfAPowerOfASetOfCubes() {
        CubeGame cubeGame = new CubeGame();
        List<String> lines = new ArrayList<>();
        lines.add("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green");
        lines.add("Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue");
        lines.add("Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red");
        lines.add("Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red");
        lines.add("Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green");
        assertEquals(2286, cubeGame.sumOfAPowerOfASetOfCubes(lines));
    }
}
