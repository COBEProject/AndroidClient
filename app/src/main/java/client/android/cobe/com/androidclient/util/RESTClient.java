package client.android.cobe.com.androidclient.util;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class RESTClient {

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void getREST(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void postREST(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return RESTConfiguration.getBaseURL() + relativeUrl;
    }

    public static Callback<String> getQuestionType(Integer id, final Callback<String> callback) {

        getREST("questions/" +id, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, String response) {
                callback.equals(response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String response, Throwable e) {
                callback.equals(response);
            }
        });

        return callback;
    }


}
