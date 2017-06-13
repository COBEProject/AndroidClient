package client.android.cobe.com.androidclient.model;

import com.google.gson.GsonBuilder;

public class Party {

    private String name;
    private String nbPlayers;

    public String getName() {
        return name;
    }

    public Party setName(String name) {
        this.name = name;
        return this;
    }

    public String getNbPlayers() {
        return nbPlayers;
    }

    public Party setNbPlayers(String nbPlayers) {
        this.nbPlayers = nbPlayers;
        return this;
    }

    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this, Party.class);
    }
}
