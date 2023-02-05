package testingil.unittesting.examples.exercise.e02.mocking.ex4.mockito;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ExtendedCalculatorDisplayTest {

    @Test
    public void whenExternalMonitorIsOn_thenMirrorTheCalculatorDisplay() {

        // Create a mock of ExternalDisplay using Mockito
        ExternalDisplay externalDisplayMock = mock(ExternalDisplay.class);
        when(externalDisplayMock.isOn()).thenReturn(true);

        // Create the class-under-test instance and inject the mock into it
        ExtendedCalculatorDisplay calculatorDisplay = new ExtendedCalculatorDisplay(externalDisplayMock);

        // Verify external display is connected
        assertFalse(calculatorDisplay.getExternalDisplayIsOff());

        // Verify proper calculator and external display behaviour
        pressSequenceOfChars(calculatorDisplay, "14+");
        assertDisplays("14", calculatorDisplay, externalDisplayMock);
        verify(externalDisplayMock, times(2)).show(anyString());

        calculatorDisplay.press("3");
        assertDisplays("3", calculatorDisplay, externalDisplayMock);
        verify(externalDisplayMock, times(3)).show(anyString());

        calculatorDisplay.press("=");
        assertDisplays("17", calculatorDisplay, externalDisplayMock);
        verify(externalDisplayMock, times(4)).show(anyString());
    }

    @Test
    public void whenExternalMonitorIsOff_thenCalculatorReflectsIt() {

        // Create a mock of ExternalDisplay using Mockito
        ExternalDisplay externalDisplayMock = mock(ExternalDisplay.class);
        when(externalDisplayMock.isOn()).thenReturn(false);

        // Create the class-under-test instance and inject the mock into it
        ExtendedCalculatorDisplay calculatorDisplay = new ExtendedCalculatorDisplay(externalDisplayMock);

        assertTrue(calculatorDisplay.getExternalDisplayIsOff());
        calculatorDisplay.press("5");
        verify(externalDisplayMock, times(0)).show(anyString());
    }

    private void assertDisplays(String expectedDisplay, ExtendedCalculatorDisplay calculatorDisplay, ExternalDisplay externalDisplayMock) {
        assertEquals(expectedDisplay, calculatorDisplay.getDisplay());
        verify(externalDisplayMock).show(expectedDisplay);
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
