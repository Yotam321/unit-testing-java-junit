package testingil.unittesting.examples.exercise.e02.mocking.ex5.mockstatic;

public class ExtendedCalculatorDisplay implements ICalculatorDisplay {
    String display = "";
    int lastArgument = 0;
    int result = 0;
    Boolean newArgument = false;
    Boolean shouldReset = true;

    OperationType lastOperation;

    @Override
    public void press(String key) {
        if (key.equals("+")) {
            lastOperation = OperationType.Plus;
            lastArgument = Integer.parseInt(display);
            newArgument = true;
        } else {
            if (key.equals("/")) {
                lastOperation = OperationType.Div;
                lastArgument = Integer.parseInt(display);
                newArgument = true;
            } else if (key.equals("=")) {
                int currentArgument = Integer.parseInt(display);
                if (lastOperation == OperationType.Plus) {
                    display = Integer.toString(lastArgument + currentArgument);
                    setExternalDisplay(display);
                }
                if (lastOperation == OperationType.Div && currentArgument == 0) {
                    display = "Division By Zero Error";
                }
                shouldReset = true;
            } else {
                if (shouldReset) {
                    display = "";
                    shouldReset = false;
                }
                if (newArgument) {
                    display = "";
                    newArgument = false;
                }
                display += key;
                setExternalDisplay(display);
            }
        }
    }

    @Override
    public String getDisplay() {
        if (display.equals(""))
            return "0";
        if (display.length() > 5)
            return "E";
        return display;
    }

    void setExternalDisplay(String text) {
        PlasmaScreen.show(text);
    }



}