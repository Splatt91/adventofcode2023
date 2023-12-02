package day.one;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class DayOneTests {

    private static Stream<Arguments> findAllDigitsParams() {
        return Stream.of(
                Arguments.of("1abc2", List.of(1, 2)),
                Arguments.of("pqr3stu8vwx", List.of(3, 8)),
                Arguments.of("a1b2c3d4e5f", List.of(1, 2, 3, 4, 5)),
                Arguments.of("treb7uchet", List.of(7)),
                Arguments.of("two1nine", List.of(2, 1, 9)),
                Arguments.of("4nineeightseven2", List.of(4, 9, 8, 7, 2)),
                Arguments.of("eightwo", List.of(8, 2)),
                Arguments.of("zoneight234", List.of(1, 8, 2, 3, 4))
        );
    }

    private static Stream<Arguments> createNumberFromDigitsParams() {
        return Stream.of(
                Arguments.of("1abc2", 12),
                Arguments.of("pqr3stu8vwx", 38),
                Arguments.of("a1b2c3d4e5f", 15),
                Arguments.of("treb7uchet", 77),
                Arguments.of("trebuchet", 0),
                Arguments.of("two1nine", 29),
                Arguments.of("4nineeightseven2", 42),
                Arguments.of("zoneight234", 14)
        );
    }

    @ParameterizedTest
    @MethodSource("findAllDigitsParams")
    void findAllDigits(String input, List<Integer> expected) {
        CalibrationDecoder calibrationDecoder = new CalibrationDecoder();
        assertIterableEquals(expected, calibrationDecoder.findAllDigits(input));
    }

    @ParameterizedTest
    @MethodSource("createNumberFromDigitsParams")
    void createNumberFromDigits(String input, long expected) {
        CalibrationDecoder calibrationDecoder = new CalibrationDecoder();
        assertEquals(expected, calibrationDecoder.createNumberFromDigits(input));
    }

    @Test
    void sumDigitsFromAllLines() {
        CalibrationDecoder calibrationDecoder = new CalibrationDecoder();
        assertEquals(281, calibrationDecoder.sumDigitsFromAllLines(List.of("two1nine", "eightwothree",
                "abcone2threexyz", "xtwone3four", "4nineeightseven2", "zoneight234", "7pqrstsixteen")));
    }

}
