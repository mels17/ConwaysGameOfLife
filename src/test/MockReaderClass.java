package test;

import main.Reader;

public class MockReaderClass implements Reader{
    private String userInput;

    @Override
    public String readInput() {
        return this.userInput;
    }

    public void setReturnValue(String userInput) {
        this.userInput = userInput;
    }
}
