package day.three;

import com.google.common.primitives.Ints;
import utility.ResourceReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class Gondola {

    private static final Logger LOGGER = Logger.getLogger(Gondola.class.getName());


    public static void main(String[] args) throws IOException {
        BufferedReader reader = ResourceReader.getResourceReader("day-three/schematic.txt");
        char[][] schematic = new char[140][140];
        Gondola gondola = new Gondola();
        int i = 0;
        for (String line; (line = reader.readLine()) != null; ) {
            schematic[i] = gondola.createRowFromLine(line);
            i++;
        }
        int[] adjacentNumbers = gondola.findNumbersAdjacentToSymbol(schematic);
        String sumOfAdjacentNumbers = String.valueOf(IntStream.of(adjacentNumbers).sum());
        LOGGER.info(sumOfAdjacentNumbers);
        String sumOfAllGearRatios = String.valueOf(gondola.sumOfAllGearRatios(schematic));
        LOGGER.info(sumOfAllGearRatios);
    }

    public char[] createRowFromLine(String line) {
        return line.toCharArray();
    }

    public int[] findNumbersAdjacentToSymbol(char[][] schematic) {
        List<Integer> result = new ArrayList<>();
        int n = schematic.length;
        int m = schematic[0].length;
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            boolean isAdjacent = false;
            for (int j = 0; j < m; j++) {
                char c = schematic[i][j];
                if (Character.isDigit(c)) {
                    sb.append(c);
                    if (!isAdjacent) {
                        isAdjacent = isAdjacentToSymbol(schematic, i, j);
                    }
                }
                if (!Character.isDigit(c) || j == (m - 1)) {
                    if (isAdjacent) {
                        result.add(Integer.parseInt(sb.toString()));
                        isAdjacent = false;
                    }
                    sb.setLength(0);
                }
            }
        }
        return Ints.toArray(result);
    }

    public boolean isAdjacentToSymbol(char[][] schematic, int x, int y) {
        int n = schematic.length;
        int m = schematic[0].length;
        int[] xCoordinates = new int[]{-1, -1, 0, 1, 1, 1, 0, -1};
        int[] yCoordinates = new int[]{0, 1, 1, 1, 0, -1, -1, -1};
        for (int i = 0; i < xCoordinates.length; i++) {
            int neighbourX = x + xCoordinates[i];
            int neighbourY = y + yCoordinates[i];
            if (!isOutsideBounds(neighbourX, neighbourY, n, m)) {
                char c = schematic[neighbourX][neighbourY];
                if (!Character.isDigit(c)
                        && c != '.') {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isOutsideBounds(int x, int y, int n, int m) {
        return x < 0 || y < 0 || x > (n - 1) || y > (m - 1);
    }

    public Point isAdjacentToGear(char[][] schematic, int x, int y) {
        int n = schematic.length;
        int m = schematic[0].length;
        int[] xCoordinates = new int[]{-1, -1, 0, 1, 1, 1, 0, -1};
        int[] yCoordinates = new int[]{0, 1, 1, 1, 0, -1, -1, -1};
        for (int i = 0; i < xCoordinates.length; i++) {
            int neighbourX = x + xCoordinates[i];
            int neighbourY = y + yCoordinates[i];
            if (!isOutsideBounds(neighbourX, neighbourY, n, m)) {
                char c = schematic[neighbourX][neighbourY];
                if (!Character.isDigit(c)
                        && c == '*') {
                    return new Point(neighbourX, neighbourY);
                }
            }
        }
        return null;
    }

    public long sumOfAllGearRatios(char[][] schematic) {
        Map<Point, List<Long>> result = new HashMap<>();
        int n = schematic.length;
        int m = schematic[0].length;
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            boolean isAdjacent = false;
            Point gear = new Point();
            for (int j = 0; j < m; j++) {
                char c = schematic[i][j];
                if (Character.isDigit(c)) {
                    sb.append(c);
                    if (!isAdjacent) {
                        gear = isAdjacentToGear(schematic, i, j);
                        if (gear != null) {
                            isAdjacent = true;
                        }
                    }
                }
                if (!Character.isDigit(c) || j == (m - 1)) {
                    if (isAdjacent) {
                        long val = Long.parseLong(sb.toString());
                        result.merge(gear, List.of(val), (oldVal, newVal) -> {
                            List<Long> mergedVal = new ArrayList<>();
                            mergedVal.addAll(oldVal);
                            mergedVal.addAll(newVal);
                            return mergedVal;
                        });
                        isAdjacent = false;
                    }
                    sb.setLength(0);
                }
            }
        }
        long sum = 0;
        for (List<Long> numbers : result.values()) {
            if (numbers.size() == 2) {
                sum += numbers.get(0) * numbers.get(1);
            }
        }
        return sum;
    }

    public class Point {
        private int x;
        private int y;

        public Point() {
            this.x = -1;
            this.y = -1;
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Point)) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
