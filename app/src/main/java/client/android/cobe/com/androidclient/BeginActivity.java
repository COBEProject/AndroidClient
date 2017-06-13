package client.android.cobe.com.androidclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class BeginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);
    }

    public void createGame(View view) {
        Intent intent = new Intent(this, GameCreationFormActivity.class);
        startActivity(intent);
    }

    public void listGame(View view) {
        Intent intent = new Intent(this, GameJoiningActivity.class);
        startActivity(intent);
    }
}
