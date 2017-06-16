package client.android.cobe.com.androidclient.web_interface;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import client.android.cobe.com.androidclient.R;
import client.android.cobe.com.androidclient.event.NewQuestionDataEvent;
import client.android.cobe.com.androidclient.event.WaitForBeginningEvent;
import de.greenrobot.event.EventBus;

public class WaitPlayersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_players);

        EventBus.getDefault().registerSticky(this);
    }

    public void onEvent(WaitForBeginningEvent event) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "Le jeu commence dans 5 secondes", Toast.LENGTH_SHORT ).show();
            }
        });
    }

    public void onEvent(NewQuestionDataEvent event) {
        final Intent intent = new Intent(this, QuestionActivity.class);
        startActivity(intent);
    }
}
