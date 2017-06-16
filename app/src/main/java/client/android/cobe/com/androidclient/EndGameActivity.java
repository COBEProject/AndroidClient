package client.android.cobe.com.androidclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class EndGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);
    }

    public void returnToMenu(View view) {
        Intent intent = new Intent(this, BeginActivity.class);
        startActivity(intent);
    }
}
