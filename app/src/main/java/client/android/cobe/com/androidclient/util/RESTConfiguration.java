package client.android.cobe.com.androidclient.util;

public class RESTConfiguration {

    private static final String baseURL;
    private static final String apiVersion;

    static {
        baseURL = "http://demo2945763.mockable.io/";
        apiVersion = "v1";
    }

    public static String getBaseURL() {
        return baseURL;
    }

    public static String getApiVersion() {
        return apiVersion;
    }
}
