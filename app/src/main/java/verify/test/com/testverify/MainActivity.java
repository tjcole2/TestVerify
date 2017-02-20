package verify.test.com.testverify;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.nexmo.sdk.NexmoClient;
import com.nexmo.sdk.core.client.ClientBuilderException;
import com.nexmo.sdk.verify.client.VerifyClient;
import com.nexmo.sdk.verify.event.SearchListener;
import com.nexmo.sdk.verify.event.UserObject;
import com.nexmo.sdk.verify.event.UserStatus;
import com.nexmo.sdk.verify.event.VerifyClientListener;
import com.nexmo.sdk.verify.event.VerifyError;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";
    private static NexmoAPI nexmo;

    private static String myPhoneNumber = "19288976711"; //This is my phone number. Change it to yours to test.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = getApplicationContext();
        nexmo = new NexmoAPI(TAG,context);
    }

    public void startVerification(View view){
        nexmo.getVerifyClient().getVerifiedUser("US", myPhoneNumber);
        //TODO: Fetch User Phone Number
    }

    public void verifyUser(View view) {
        EditText et = (EditText) findViewById(R.id.editText2);
        nexmo.getVerifyClient().checkPinCode(et.getText().toString()); //This verifies it.
        nexmo.getVerifyClient().getUserStatus("US", myPhoneNumber, new SearchListener() {

            @Override
            public void onException(IOException exception) {
                //TODO: Add Exception Code
            }

            @Override
            public void onUserStatus(UserStatus userStatus) {
                //TODO: Add User Status Code
            }

            @Override
            public void onError(VerifyError errorCode, String errorMessage) {
                //TODO: Add error code
            }
        });
    }
}