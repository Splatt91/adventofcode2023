package day.two;

import utility.ResourceReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CubeGame {

    private static final Logger LOGGER = Logger.getLogger(CubeGame.class.getName());

    private final Map<String, Integer> rules;

    public CubeGame() {
        rules = Map.of(
                "red", 12,
                "green", 13,
                "blue", 14
        );
    }

    public boolean isPossible(String line) {
        List<Map<String, Integer>> game = parseLine(line);
        for (Map<String, Integer> gameCubes : game) {
            for (Map.Entry<String, Integer> entry : gameCubes.entrySet()) {
                String key = entry.getKey();
                int gameCubesCount = gameCubes.get(key);
                int rulesCubeCount = rules.get(key);
                if (gameCubesCount > rulesCubeCount) {
                    return false;
                }
            }
        }
        return true;
    }

    public List<Map<String, Integer>> parseLine(String line) {
        String[] firstSplit = line.split(":");
        String[] secondSplit = firstSplit[1].split(";");
        List<Map<String, Integer>> result = new ArrayList<>();
        for (String revealedCubes : secondSplit) {
            String[] cubes = revealedCubes.split(",");
            Map<String, Integer> cubeMap = new HashMap<>();
            for (String cube : cubes) {
                String[] cubeInformation = cube.trim().split(" ");
                int count = Integer.parseInt(cubeInformation[0]);
                String color = cubeInformation[1];
                cubeMap.put(color, count);
            }
            result.add(cubeMap);
        }
        return result;
    }

    public int sumOfPossibleGames(List<String> lines) {
        int i = 1;
        int sum = 0;
        for (String line : lines) {
            if (isPossible(line)) {
                sum += i;
            }
            i++;
        }
        return sum;
    }

    public Map<String, Integer> fewestNumberOfCubesOfEachColor(String line) {
        List<Map<String, Integer>> game = parseLine(line);
        Map<String, Integer> result = new HashMap<>();
        for (Map<String, Integer> cubes : game) {
            for (Map.Entry<String, Integer> entry : cubes.entrySet()) {
                String color = entry.getKey();
                int count = cubes.get(color);
                result.merge(color, count, Math::max);
            }
        }
        return result;
    }

    public int sumOfAPowerOfASetOfCubes(List<String> lines) {
        int sum = 0;
        for (String line : lines) {
            Map<String, Integer> game = fewestNumberOfCubesOfEachColor(line);
            int power = 1;
            for (int count : game.values()) {
                power *= count;
            }
            sum += power;
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = ResourceReader.getResourceReader("day-two/games.txt");
        List<String> games = new ArrayList<>();
        for (String line; (line = reader.readLine()) != null; ) {
            games.add(line);
        }
        CubeGame cubeGame = new CubeGame();
        String sumOfPossibleGames = String.valueOf(cubeGame.sumOfPossibleGames(games));
        String sumOfAPowerOfASetOfCubes = String.valueOf(cubeGame.sumOfAPowerOfASetOfCubes(games));
        LOGGER.log(Level.INFO, sumOfPossibleGames);
        LOGGER.log(Level.INFO, sumOfAPowerOfASetOfCubes);

    }
}
