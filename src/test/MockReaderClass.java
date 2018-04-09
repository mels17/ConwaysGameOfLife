package test;

import main.Reader;

import java.util.List;

public class MockReaderClass implements Reader{
    private List<String> userInput;
    private int count = 0;

    @Override
    public String readInput() {
        String input = this.userInput.get(count);
        ++count;
        return input;
    }

    public void setReturnValue(List<String> userInput) {
        this.userInput = userInput;
    }
}
