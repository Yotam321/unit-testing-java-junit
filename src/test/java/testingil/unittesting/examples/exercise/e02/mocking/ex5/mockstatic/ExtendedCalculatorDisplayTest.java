package testingil.unittesting.examples.exercise.e02.mocking.ex5.mockstatic;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ExtendedCalculatorDisplayTest {

    @Test
    public void whenInteractingWithCalculator_thenMirrorTheCalculatorDisplay() {

        try (MockedStatic<PlasmaScreen> plasmaScreenMock = mockStatic(PlasmaScreen.class)) {
            ExtendedCalculatorDisplay calculatorDisplay = new ExtendedCalculatorDisplay();

            pressAndVerify(calculatorDisplay, "3", plasmaScreenMock, "3");
            pressAndVerify(calculatorDisplay, "+", plasmaScreenMock, "3");
            pressAndVerify(calculatorDisplay, "4", plasmaScreenMock, "4");
            pressAndVerify(calculatorDisplay, "=", plasmaScreenMock, "7");
        }
    }

    private void pressAndVerify(ICalculatorDisplay calculatorDisplay,
                                String textToPress,
                                MockedStatic<PlasmaScreen> plasmaScreenMock,
                                String expectedTextToShow) {

        calculatorDisplay.press(textToPress);
        plasmaScreenMock.verify(() -> PlasmaScreen.show(expectedTextToShow));
    }

    @Test
    public void whenPressingZero_thenDisplayShowsZero() {

        ICalculatorDisplay calculatorDisplay = new SimpleCalculatorDisplay();

        calculatorDisplay.press("0");
        assertEquals("0", calculatorDisplay.getDisplay());
    }

    @Test
    public void whenAddingTwoNumbers_thenDisplayShowsCorrectResult() {

        ICalculatorDisplay calculatorDisplay = new SimpleCalculatorDisplay();

        pressSequenceOfChars(calculatorDisplay, "15+10=");
        assertEquals("25", calculatorDisplay.getDisplay());
    }

    @Test
    void whenDividingByZero_thenDisplayError() {

        ICalculatorDisplay calculatorDisplay = new SimpleCalculatorDisplay();

        pressSequenceOfChars(calculatorDisplay, "5/0=");
        assertEquals("E", calculatorDisplay.getDisplay());
    }

    void pressSequenceOfChars(ICalculatorDisplay calculatorDisplay, String characterSequence) {
        char[] chars = characterSequence.toCharArray();
        for (char currentChar : chars) {
            calculatorDisplay.press(String.valueOf(currentChar));
        }
    }



}
