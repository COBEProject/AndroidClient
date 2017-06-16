package client.android.cobe.com.androidclient.event;

public class WaitForBeginningEvent {

    private boolean waitForBeginning;

    public WaitForBeginningEvent(boolean waitForBeginning) {
        this.waitForBeginning = waitForBeginning;
    }

    public boolean isWaitForBeginning() {
        return waitForBeginning;
    }
}
