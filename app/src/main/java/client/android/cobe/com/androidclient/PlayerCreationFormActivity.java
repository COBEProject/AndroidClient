package client.android.cobe.com.androidclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import client.android.cobe.com.androidclient.model.User;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class PlayerCreationFormActivity extends AppCompatActivity {

    private String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_creation_form);
    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.M:
                if (checked)
                    gender = "M";
                    break;
            case R.id.F:
                if (checked)
                    gender = "F";
                    break;
        }
    }

    public void createUser(View view) {
        User user = new User();
        EditText pseudoEditText= (EditText)findViewById(R.id.pseudo);
        EditText ageEditText = (EditText)findViewById(R.id.age);

        String nom = pseudoEditText.getText().toString();
        String age = ageEditText.getText().toString();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user.setId_game(extras.getString("id_game"));
        }

        user.setName(nom).setAge(age).setGender(gender);
        final Intent intent = new Intent(this, BeginActivity.class);
        StringEntity entity = null;

        try {
            entity = new StringEntity(user.toString());
        } catch (UnsupportedEncodingException e) {
            startActivity(intent);
        }

        AsyncHttpClient client = new AsyncHttpClient();
        client.post(this, "http://demo5712444.mockable.io/user", entity , "application/json", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                startActivity(intent);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                startActivity(intent);
            }
        });
    }
}
