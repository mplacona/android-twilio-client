package twilio.com.twilioclient;

import android.content.Context;
import android.util.Log;

import com.twilio.client.Connection;
import com.twilio.client.Device;
import com.twilio.client.Twilio;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

import java.util.HashMap;
import java.util.Map;

public class MonkeyPhone implements Twilio.InitListener {
    private Device mDevice;
    private String TAG = "Monkey Phone";
    private Connection mConnection;
    private Context mContext;

    public MonkeyPhone(Context context)
    {
        this.mContext = context;
        Twilio.initialize(context, this);
    }

    @Override
    public void onInitialized(){
        Log.d(TAG, "Twilio SDK is ready");
        new HttpHandler(){
            @Override
            public HttpUriRequest getHttpRequestMethod(){
                return new HttpGet(mContext.getString(R.string.app_capability_url));
            }

            @Override
            public void onResponse(String token) {
                mDevice = Twilio.createDevice(token, null);
                Log.d(TAG, "Capability token: " + token);
            }
        }.execute();
    }

    /* Twilio.InitListener method */
    @Override
    public void onError(Exception e) {
        Log.e(TAG, "Twilio SDK couldn't start: " + e.getLocalizedMessage());
    }

    public void connect(String phoneNumber)
    {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("To", phoneNumber);
        mConnection = mDevice.connect(parameters, null);
        if (mConnection == null)
            Log.w(TAG, "Failed to create new connection");
    }


    public void disconnect()
    {
        if (mConnection != null) {
            mConnection.disconnect();
            mConnection = null;
        }
    }

    @Override
    protected void finalize()
    {
        if (mDevice != null)
            mDevice.release();
    }
}