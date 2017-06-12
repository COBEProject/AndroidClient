package client.android.cobe.com.androidclient.model;

public class Party {

    private String name;
    private int nbPlayers;

    public String getName() {
        return name;
    }

    public Party setName(String name) {
        this.name = name;
        return this;
    }

    public int getNbPlayers() {
        return nbPlayers;
    }

    public Party setNbPlayers(int nbPlayers) {
        this.nbPlayers = nbPlayers;
        return this;
    }
}
