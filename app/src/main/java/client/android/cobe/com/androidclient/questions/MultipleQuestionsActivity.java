package client.android.cobe.com.androidclient.questions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import client.android.cobe.com.androidclient.R;
import client.android.cobe.com.androidclient.model.UserResponse;
import client.android.cobe.com.androidclient.util.Callback;
import client.android.cobe.com.androidclient.util.RESTClient;
import client.android.cobe.com.androidclient.util.RESTConfiguration;
import retrofit2.Call;

public class MultipleQuestionsActivity extends AppCompatActivity {

    private TextView textQuestion;
    private ImageView imageView;

    private Integer i = 1;
    private ArrayList<UserResponse> userResponse = new ArrayList<>();

    private String api_url = RESTConfiguration.getBaseURL();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_questions);

        textQuestion = (TextView) findViewById(R.id.textMultipleQuestions);
        imageView = (ImageView) findViewById(R.id.imageMultipleQuestions);

        callRest(i);
    }

    public void onClickResponse1(View view) throws JSONException {
        Callback<String> callback;
        UserResponse userResponseTemp = new UserResponse();
        i = i + 1;
        callRest(i);
    }

    public void onClickResponse2(View view) throws JSONException {
        UserResponse userResponseTemp = new UserResponse();
        i = i + 1;
        callRest(i);
    }

    public void onClickResponse3(View view) throws JSONException {
        UserResponse userResponseTemp = new UserResponse();
        i = i + 1;
        callRest(i);
    }

    public void onClickResponse4(View view) throws JSONException {
        UserResponse userResponseTemp = new UserResponse();
        i = i + 1;
        callRest(i);
    }

    private void callRest(final Integer urlParam) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(api_url + "questions/" +urlParam, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                try {
                    String type = (String) response.get("type");
                    if (type.equals("multiple")) {
                        String question = (String) response.get("question");
                        String image = (String) response.get("image");
                        textQuestion.setText(question);
                        Picasso.with(getBaseContext()).load(image).into(imageView);
                    } else {
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
