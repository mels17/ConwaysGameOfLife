package test;

import main.DataFormatter;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DataFormatterClassTest {
    @Test
    public void splitsStringWithOneStringBetween() {
        String[] expectedResult = {"a", "b"};
        assertThat(expectedResult, equalTo(DataFormatter.splitStringWhereSpaces("a b")));
    }

    @Test
    public void splitStringWithMultipleSpaces() {
        String[] expectedResult = {"a", "b"};
        assertThat(expectedResult, equalTo(DataFormatter.splitStringWhereSpaces("a  b")));
    }

    @Test
    public void returnsBooleanArrayGivenListOfValidStringAndNoOfColumns() {
        List<String> input = Arrays.asList("0 1 0 1 0", "1 1 1 1 1");
        boolean[][] expectedResult = {{false, true, false, true, false},
                                        {true, true, true, true, true}};
        assertThat(expectedResult, equalTo(DataFormatter.storeValueIntoBooleanArray(input, 5)));
    }

    @Test
    public void returnsBooleanArrayGivenListOfStringWithCharactersOtherThan0sAnd1s() {
        List<String> input = Arrays.asList("0 0 a", "1 2 3");
        boolean[][] expectedResult = {{false, false, true},
                                        {true, true, true}};
        assertThat(expectedResult, equalTo(DataFormatter.storeValueIntoBooleanArray(input, 3)));
    }

    @Test
    public void returnsBooleanArrayWithInputListWithStrings() {
        List<String> input = Arrays.asList("0 0 abc", "def 1 0");
        boolean[][] expectedResult = {{false, false, true},
                                        {true, true, false}};
        assertThat(expectedResult, equalTo(DataFormatter.storeValueIntoBooleanArray(input, 3)));
    }
}
