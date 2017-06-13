package client.android.cobe.com.androidclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import client.android.cobe.com.androidclient.web_interface.WaitPlayersActivity;

public class GameJoiningActivity extends AppCompatActivity {

    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("https://cobe-website.cfapps.io");
        } catch (URISyntaxException e) {}
    }

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

        mSocket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... args) {}

        }).on("connected", new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                JSONObject obj = (JSONObject)args[0];
                Logger.getGlobal().log(Level.INFO, obj.toString());
            }

        }).on("playerJoinedRoom", new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                startActivity(intent);
            }

        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... args) {}

        });
        mSocket.connect();

        gameId = Integer.parseInt(gameIdEditText.getText().toString());
        playerName = playerNameEditText.getText().toString();

        JSONObject obj = new JSONObject();
        try {
            obj.put("gameId", gameId);
            obj.put("playerName", playerName);
        } catch (JSONException e) {
            Logger.getGlobal().log(Level.WARNING, e.toString());
        }

        mSocket.emit("playerJoinGame", obj);

    }
}
