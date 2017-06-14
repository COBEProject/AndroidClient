package client.android.cobe.com.androidclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.logging.Level;
import java.util.logging.Logger;

import client.android.cobe.com.androidclient.util.SOCKETConfiguration;
import client.android.cobe.com.androidclient.web_interface.WaitPlayersActivity;


public class GameJoiningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_joining);
    }

    public void connectSocket(View view) {
        final Intent intent = new Intent(this, WaitPlayersActivity.class);

        EditText playerNameEditText= (EditText)findViewById(R.id.playerName);
        EditText gameIdEditText = (EditText)findViewById(R.id.gameId);
        String playerName = null;
        int gameId = 0;

        SOCKETConfiguration.getInstance().connectSocket("https://cobe-website.cfapps.io");

        gameId = Integer.parseInt(gameIdEditText.getText().toString());
        playerName = playerNameEditText.getText().toString();

        JSONObject obj = new JSONObject();
        try {
            obj.put("gameId", gameId);
            obj.put("playerName", playerName);
        } catch (JSONException e) {
            Logger.getGlobal().log(Level.WARNING, e.toString());
        }

        SOCKETConfiguration.getInstance().getSocket().emit("playerJoinGame", obj);

        while (!readyToGo()) {
            readyToGo();
        }

        startActivity(intent);

    }

    private boolean readyToGo() {
        if (!SOCKETConfiguration.getInstance().isWaitForPlayers()) {
            return false;
        } else {
            return true;
        }
    }
}
