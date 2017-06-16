package client.android.cobe.com.androidclient.event;

public class EndGameEvent {
     private boolean endGame;

    public EndGameEvent(boolean endGame) {
        this.endGame = endGame;
    }

    public boolean isEndGame() {
        return endGame;
    }
}
