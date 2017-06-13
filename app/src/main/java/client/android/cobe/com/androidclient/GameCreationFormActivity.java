package client.android.cobe.com.androidclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import com.loopj.android.http.*;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import client.android.cobe.com.androidclient.model.Party;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.StringEntity;

public class GameCreationFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_creation_form);
    }

    public void createGame(View view) {
        Party party = new Party();
        EditText nomEditText= (EditText)findViewById(R.id.name);
        EditText nbPlayersEditText = (EditText)findViewById(R.id.nbPlayers);
        String nom = nomEditText.getText().toString();
        String nbPlayers = nbPlayersEditText.getText().toString();
        party.setName(nom).setNbPlayers(nbPlayers);
        final Intent intent = new Intent(this, PlayerCreationFormActivity.class);
        final Intent intent2 = new Intent(this, BeginActivity.class);
        StringEntity entity = null;

        try {
            entity = new StringEntity(party.toString());
        } catch (UnsupportedEncodingException e) {
            startActivity(intent2);
        }

        AsyncHttpClient client = new AsyncHttpClient();
        client.post(this, "http://demo5712444.mockable.io/party", entity , "application/json", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                startActivity(intent);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                startActivity(intent2);
            }
        });
    }
}
