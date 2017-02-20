package verify.test.com.testverify;

import android.content.Context;
import android.util.Log;

import com.nexmo.sdk.NexmoClient;
import com.nexmo.sdk.core.client.ClientBuilderException;
import com.nexmo.sdk.verify.client.VerifyClient;
import com.nexmo.sdk.verify.event.UserObject;
import com.nexmo.sdk.verify.event.VerifyClientListener;

import java.io.IOException;

/**
 * Created by SuperBlah12 on 2/19/2017.
 */

public class NexmoAPI {
    public static final String MY_APP_ID = "b776f3ff-abda-4a51-83a2-2d13c73d9c82";
    public static final String MY_APP_SHARED_SECRET = "e845b00ef565226";
    public static NexmoClient nexmoClient = null;

    public static VerifyClient verifyClient = null;

    public NexmoAPI(final String TAG, Context context){
        try {
            nexmoClient = new NexmoClient.NexmoClientBuilder()
                    .context(context)
                    .applicationId(MY_APP_ID) //your App key
                    .sharedSecretKey(MY_APP_SHARED_SECRET) //your App secret
                    .build();
        } catch (ClientBuilderException e) {
            e.printStackTrace();
        }

        verifyClient = new VerifyClient(nexmoClient);

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

    public static VerifyClient getVerifyClient() {
        return verifyClient;
    }

    public static NexmoClient getNexmoClient() {
        return nexmoClient;
    }
}
