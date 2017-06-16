package client.android.cobe.com.androidclient.event;

public class NewQuestionDataEvent {

    private boolean newQuestionDataEvent;

    public NewQuestionDataEvent(boolean newQuestionDataEvent) {
        this.newQuestionDataEvent = newQuestionDataEvent;
    }

    public boolean isNewQuestionDataEvent() {
        return newQuestionDataEvent;
    }
}
