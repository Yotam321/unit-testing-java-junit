package testingil.unittesting.examples.exercise.e02.mocking.ex3.manualmocks;

public class ExternalDisplayManualMock implements ExternalDisplay {

    private Boolean isOnMock = null;

    private String textShowWasCalledWith;

    private Integer invocationsNumberOfShow = 0;

    @Override
    public void show(String text) {
        this.invocationsNumberOfShow++;
        this.textShowWasCalledWith = text;
    }

    @Override
    public Boolean isOn() {
        return this.isOnMock;
    }

    public void setIsOnMock(Boolean isOnMock) {
        this.isOnMock = isOnMock;
    }

    public String verifyShow() {
        return this.textShowWasCalledWith;
    }

    public Integer verifyInvocationsNumberOfShow() {
        return this.invocationsNumberOfShow;
    }

    public void resetInvocationsNumberOfShow() {
        this.invocationsNumberOfShow = 0;
    }
}
