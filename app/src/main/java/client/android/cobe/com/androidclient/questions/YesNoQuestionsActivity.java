package client.android.cobe.com.androidclient.questions;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import client.android.cobe.com.androidclient.R;
import client.android.cobe.com.androidclient.util.RESTConfiguration;

public class YesNoQuestionsActivity extends Activity {

    private TextView question_text;
    private ImageView imageYesNoQuestion;

    private Integer i = 1;
    private ArrayList<Boolean> userRespose = new ArrayList<>();

    private String api_url = RESTConfiguration.getBaseURL();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yes_no_questions);

        question_text = (TextView) findViewById(R.id.yesNoQuestion);
        imageYesNoQuestion = (ImageView) findViewById(R.id.imageYesNoQuestion);

        callRest(i);
    }

    public void onClickYes(View view) throws JSONException {
        userRespose.add(true);
        i = i + 1;
       callRest(i);
    }

    public void onClickNo(View view) throws JSONException {
        userRespose.add(false);
        i = i + 1;
        callRest(i);
    }

    private void callRest(Integer urlParam) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(api_url + "questions/" +urlParam, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                try {
                    String question = (String) response.get("question");
                    String image = (String) response.get("image");
                    question_text.setText(question);
                    Picasso.with(getBaseContext()).load(image).into(imageYesNoQuestion);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
