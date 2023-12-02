package day.one;

import utility.ResourceReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalibrationDecoder {

    private static final Logger LOGGER = Logger.getLogger(CalibrationDecoder.class.getName());

    private final Map<String, Integer> digitMap;

    public CalibrationDecoder() {
        digitMap = Map.of(
                "one", 1,
                "two", 2,
                "three", 3,
                "four", 4,
                "five", 5,
                "six", 6,
                "seven", 7,
                "eight", 8,
                "nine", 9
        );
    }

    public List<Integer> findAllDigits(String line) {
        List<Integer> result = new ArrayList<>();
        Pattern digitRegex = Pattern.compile("one|two|three|four|five|six|seven|eight|nine|\\d");
        Matcher digitMatcher = digitRegex.matcher(line);
        int matchIndex = 0;
        while (digitMatcher.find(matchIndex)) {
            String digit = digitMatcher.group();
            boolean isWord = digit.length() > 1;
            if (isWord) {
                result.add(digitMap.get(digit));
                matchIndex = digitMatcher.end() - 1;
            } else {
                result.add(Integer.parseInt(digit));
                matchIndex = digitMatcher.end();
            }
        }
        return result;
    }

    public long createNumberFromDigits(String line) {
        List<Integer> digits = findAllDigits(line);
        return switch (digits.size()) {
            case 0 -> 0;
            case 1 -> digits.get(0) * 10 + digits.get(0);
            default -> digits.get(0) * 10 + digits.get(digits.size() - 1);
        };
    }

    public long sumDigitsFromAllLines(List<String> lines) {
        int sum = 0;
        for (String line : lines) {
            long number = createNumberFromDigits(line);
            sum += number;
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = ResourceReader.getResourceReader("day-one/calibrations.txt");
        List<String> calibrations = new ArrayList<>();
        for (String line; (line = reader.readLine()) != null;) {
            calibrations.add(line);
        }
        CalibrationDecoder calibrationDecoder = new CalibrationDecoder();
        String decodedCalibrations = String.valueOf(calibrationDecoder.sumDigitsFromAllLines(calibrations));
        LOGGER.log(Level.INFO, decodedCalibrations);
    }
}
