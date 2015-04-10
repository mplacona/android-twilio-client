package twilio.com.twilioclient;

import org.apache.http.client.methods.HttpUriRequest;

/**
 * Created by mplacona on 4/10/15.
 */
public abstract class HttpHandler {
    public abstract HttpUriRequest getHttpRequestMethod();
    public abstract void onResponse(String result);
    public void execute(){
        new AsyncHttpTask(this).execute();
    }
}
