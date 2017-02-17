package verify.test.com.testverify;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.nexmo.sdk.NexmoClient;
import com.nexmo.sdk.core.client.ClientBuilderException;
import com.nexmo.sdk.verify.client.VerifyClient;
import com.nexmo.sdk.verify.event.UserObject;
import com.nexmo.sdk.verify.event.VerifyClientListener;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static final String MY_APP_ID = "yourAppId";
    public static final String MY_APP_SHARED_SECRET = "YourSharedSecretKey";
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = getApplicationContext();
        NexmoClient nexmoClient = null;
        try {
            nexmoClient = new NexmoClient.NexmoClientBuilder()
                    .context(context)
                    .applicationId(MY_APP_ID) //your App key
                    .sharedSecretKey(MY_APP_SHARED_SECRET) //your App secret
                    .build();
        } catch (ClientBuilderException e) {
            e.printStackTrace();
        }

        VerifyClient verifyClient = new VerifyClient(nexmoClient);

        verifyClient.addVerifyListener(new VerifyClientListener() {
            @Override
            public void onVerifyInProgress(final VerifyClient verifyClient, UserObject user) {
                Log.d(TAG, "onVerifyInProgress for number: " + user.getPhoneNumber());
            }

            @Override
            public void onUserVerified(final VerifyClient verifyClient, UserObject user) {
                Log.d(TAG, "onUserVerified for number: " + user.getPhoneNumber());
            }

            @Override
            public void onError(final VerifyClient verifyClient, final com.nexmo.sdk.verify.event.VerifyError errorCode, UserObject user) {
                Log.d(TAG, "onError: " + errorCode + " for number: " + user.getPhoneNumber());
            }

            @Override
            public void onException(final IOException exception) {
            }
        });
    }


}
