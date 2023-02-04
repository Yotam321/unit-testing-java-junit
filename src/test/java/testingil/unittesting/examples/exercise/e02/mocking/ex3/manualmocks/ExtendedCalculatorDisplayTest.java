package testingil.unittesting.examples.exercise.e02.mocking.ex3.manualmocks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExtendedCalculatorDisplayTest {

    @Test
    public void whenExternalMonitorIsOn_thenMirrorTheCalculatorDisplay() {

        // Create a manual mock instance of ExternalDisplay
        ExternalDisplayManualMock externalDisplayMock = new ExternalDisplayManualMock();
        externalDisplayMock.setIsOnMock(true);

        // Create the class-under-test instance and inject the mock into it
        ExtendedCalculatorDisplay calculatorDisplay = new ExtendedCalculatorDisplay(externalDisplayMock);

        // Verify external display is connected
        assertFalse(calculatorDisplay.getExternalDisplayIsOff());

        // Verify proper calculator and external display behaviour
        pressSequenceOfChars(calculatorDisplay, "14+");
        assertDisplays("14", calculatorDisplay, externalDisplayMock);
        assertEquals(2, externalDisplayMock.verifyInvocationsNumberOfShow());
        externalDisplayMock.resetInvocationsNumberOfShow();

        calculatorDisplay.press("3");
        assertDisplays("3", calculatorDisplay, externalDisplayMock);
        assertEquals(1, externalDisplayMock.verifyInvocationsNumberOfShow());
        externalDisplayMock.resetInvocationsNumberOfShow();

        calculatorDisplay.press("=");
        assertDisplays("17", calculatorDisplay, externalDisplayMock);
        assertEquals(1, externalDisplayMock.verifyInvocationsNumberOfShow());
    }

    @Test
    public void whenExternalMonitorIsOff_thenCalculatorReflectsIt() {

        // Create a manual mock instance of ExternalDisplay
        ExternalDisplayManualMock externalDisplayMock = new ExternalDisplayManualMock();
        externalDisplayMock.setIsOnMock(false);

        // Create the class-under-test instance and inject the mock into it
        ExtendedCalculatorDisplay calculatorDisplay = new ExtendedCalculatorDisplay(externalDisplayMock);

        assertTrue(calculatorDisplay.getExternalDisplayIsOff());
        calculatorDisplay.press("5");
        assertEquals(0, externalDisplayMock.verifyInvocationsNumberOfShow());
    }

    private void assertDisplays(String expectedDisplay, ExtendedCalculatorDisplay calculatorDisplay, ExternalDisplayManualMock externalDisplayManualMock) {
        assertEquals(expectedDisplay, calculatorDisplay.getDisplay());
        assertEquals(expectedDisplay, externalDisplayManualMock.verifyShow());
    }

    @Test
    public void whenPressingZero_thenDisplayShowsZero() {

        ICalculatorDisplay calculatorDisplay = new SimpleCalculatorDisplay();

        calculatorDisplay.press("0");
        assertEquals("0", calculatorDisplay.getDisplay());
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
