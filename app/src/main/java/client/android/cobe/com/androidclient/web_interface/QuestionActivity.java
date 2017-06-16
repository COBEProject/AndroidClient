package client.android.cobe.com.androidclient.web_interface;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.logging.Level;
import java.util.logging.Logger;

import client.android.cobe.com.androidclient.EndGameActivity;
import client.android.cobe.com.androidclient.R;
import client.android.cobe.com.androidclient.event.EndGameEvent;
import client.android.cobe.com.androidclient.event.NewQuestionDataEvent;
import client.android.cobe.com.androidclient.util.SOCKETConfiguration;
import de.greenrobot.event.EventBus;

import static client.android.cobe.com.androidclient.R.id.buttonA;
import static client.android.cobe.com.androidclient.R.id.buttonB;
import static client.android.cobe.com.androidclient.R.id.buttonC;
import static client.android.cobe.com.androidclient.R.id.buttonD;

public class QuestionActivity extends AppCompatActivity {

    Button buttonA;
    Button buttonB;
    Button buttonC;
    Button buttonD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        EventBus.getDefault().registerSticky(this);

        buttonA = (Button) findViewById(R.id.buttonA);
        buttonB = (Button) findViewById(R.id.buttonB);
        buttonC = (Button) findViewById(R.id.buttonC);
        buttonD = (Button) findViewById(R.id.buttonD);

    }

    public void sendAnswerA(View view) {

        JSONObject obj = new JSONObject();
        try {
            obj.put("gameId", SOCKETConfiguration.getInstance().getGameId());
            obj.put("currentQuestion", SOCKETConfiguration.getInstance().getQuestion().getNumquestion());
            obj.put("playerId", SOCKETConfiguration.getInstance().getUserId());
            obj.put("answer", "A");
        } catch (JSONException e) {
            Logger.getGlobal().log(Level.WARNING, e.toString());
        }
        Logger.getGlobal().log(Level.WARNING, obj.toString());
        SOCKETConfiguration.getInstance().getSocket().emit("playerAnswer", obj);

    }

    public void sendAnswerB(View view) {

        JSONObject obj = new JSONObject();
        try {
            obj.put("gameId", SOCKETConfiguration.getInstance().getGameId());
            obj.put("currentQuestion", SOCKETConfiguration.getInstance().getQuestion().getNumquestion());
            obj.put("playerId", SOCKETConfiguration.getInstance().getUserId());
            obj.put("answer", "B");
        } catch (JSONException e) {
            Logger.getGlobal().log(Level.WARNING, e.toString());
        }
        Logger.getGlobal().log(Level.WARNING, obj.toString());
        SOCKETConfiguration.getInstance().getSocket().emit("playerAnswer", obj);

    }

    public void sendAnswerC(View view) {

        JSONObject obj = new JSONObject();
        try {
            obj.put("gameId", SOCKETConfiguration.getInstance().getGameId());
            obj.put("currentQuestion", SOCKETConfiguration.getInstance().getQuestion().getNumquestion());
            obj.put("playerId", SOCKETConfiguration.getInstance().getUserId());
            obj.put("answer", "C");
        } catch (JSONException e) {
            Logger.getGlobal().log(Level.WARNING, e.toString());
        }
        Logger.getGlobal().log(Level.WARNING, obj.toString());
        SOCKETConfiguration.getInstance().getSocket().emit("playerAnswer", obj);

    }

    public void sendAnswerD(View view) {

        JSONObject obj = new JSONObject();
        try {
            obj.put("gameId", SOCKETConfiguration.getInstance().getGameId());
            obj.put("currentQuestion", SOCKETConfiguration.getInstance().getQuestion().getNumquestion());
            obj.put("playerId", SOCKETConfiguration.getInstance().getUserId());
            obj.put("answer", "D");
        } catch (JSONException e) {
            Logger.getGlobal().log(Level.WARNING, e.toString());
        }
        Logger.getGlobal().log(Level.WARNING, obj.toString());
        SOCKETConfiguration.getInstance().getSocket().emit("playerAnswer", obj);
    }

    public void onEvent(NewQuestionDataEvent event) {

        buttonA = (Button) findViewById(R.id.buttonA);
        buttonB = (Button) findViewById(R.id.buttonB);
        buttonC = (Button) findViewById(R.id.buttonC);
        buttonD = (Button) findViewById(R.id.buttonD);

         if (SOCKETConfiguration.getInstance().getQuestion().getAnswer().getC().equals("")) {
             runOnUiThread(new Runnable() {
                 @Override
                 public void run() {
                     buttonA.setText(SOCKETConfiguration.getInstance().getQuestion().getAnswer().getA());
                     buttonB.setText(SOCKETConfiguration.getInstance().getQuestion().getAnswer().getB());
                     buttonC.setVisibility(View.INVISIBLE);
                     buttonD.setVisibility(View.INVISIBLE);
                 }
             });
        } else {
             runOnUiThread(new Runnable() {
                 @Override
                 public void run() {
                     buttonC.setVisibility(View.VISIBLE);
                     buttonD.setVisibility(View.VISIBLE);
                     buttonA.setText(SOCKETConfiguration.getInstance().getQuestion().getAnswer().getA());
                     buttonB.setText(SOCKETConfiguration.getInstance().getQuestion().getAnswer().getB());
                     buttonC.setText(SOCKETConfiguration.getInstance().getQuestion().getAnswer().getC());
                     buttonD.setText(SOCKETConfiguration.getInstance().getQuestion().getAnswer().getD());
                 }
             });
        }

    }

    public void onEvent(EndGameEvent event) {
        Intent intent = new Intent(getApplicationContext(), EndGameActivity.class);
        startActivity(intent);
    }
}
