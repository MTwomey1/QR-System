package com.example.newpc.qrcode;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

public class ReaderActivity extends AppCompatActivity implements View.OnClickListener {

    TextView returnView;
    String userid = null;
    User_Local_Data user_local_data;
    Button retry_bt, home_bt;
    Globals g = Globals.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);

        final Activity activity = this;
        IntentIntegrator integrator = new IntentIntegrator(activity);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setCameraId(0);
        integrator.setPrompt(" ");
        integrator.setBeepEnabled(true);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();

        user_local_data = new User_Local_Data(this);
        //returnView = (TextView) findViewById(R.id.tv_return);
        retry_bt = (Button) findViewById(R.id.retry_bt_id);
        home_bt = (Button) findViewById(R.id.home_bt_id);

        retry_bt.setOnClickListener(this);
        home_bt.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents()==null){
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this, result.getContents(),Toast.LENGTH_LONG).show();
                super.onActivityResult(requestCode, resultCode, data);
                userid = data.getStringExtra("SCAN_RESULT");
                String format = data.getStringExtra("SCAN_RESULT_FORMAT");
                //TextView dataView = (TextView) findViewById(R.id.tv_data);
                //dataView.setText(userid);
                User user = new User(userid);
                if(g.getTest()==1){
                    authenticate(user);
                }
                else if(g.getTest()==0){
                    logout(user);
                }
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
            userid = data.getStringExtra("SCAN_RESULT");
            String format = data.getStringExtra("SCAN_RESULT_FORMAT");
            //TextView dataView = (TextView) findViewById(R.id.tv_data);
           // dataView.setText("poop");
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
    }

    private void authenticate(User user) {
        Log.d("myTag", "authenticating..");

        Server_Requests server_requests = new Server_Requests(this);
        server_requests.fetch_user_data_in_background(user, new Get_String_Callback() {
            @Override
            public void done(String returned_string) {
                if (returned_string.equals("failed")) {
                    show_error_message("Incorrect QR");
                    Log.d("myTag", "String fail..");
                } else {
                    Log.d("myTag", "String win..");
                    //returnView.setText(returned_string);

                    try {
                        // create a user;
                        User user;

                        // Create a JSONObject from the returned String
                        JSONObject jObject = new JSONObject(returned_string);

                        String userid = jObject.getString("userid0");
                        String firstname = jObject.getString("FirstName0");
                        String surname = jObject.getString("LastName0");
                        //dataView.setText(firstname);

                        user = new User(userid, firstname, surname);

                        log_user_in(user);

                        //dataView.setText(firstname);

                        finish();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }// end else
            }// end done();
        });
    }

    private void logout(User user) {
        Log.d("myTag", "logout..");

        Server_Requests server_requests = new Server_Requests(this);
        server_requests.logout_user(user, new Get_String_Callback() {
            @Override
            public void done(String returned_string) {
                if (returned_string.equals("failed")) {
                    show_error_message("Incorrect QR");
                    Log.d("myTag", "logout fail..");
                } else {
                    Log.d("myTag", "logout win..");

                    try {
                        // create a user;
                        User user;

                        // Create a JSONObject from the returned String
                        JSONObject jObject = new JSONObject(returned_string);

                        String userid = jObject.getString("userid0");
                        String firstname = jObject.getString("FirstName0");
                        String surname = jObject.getString("LastName0");

                        user = new User(userid, firstname, surname);

                        log_user_out(user);


                        finish();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }// end else
            }// end done();
        });
    }

    private void log_user_in(User returned_user) {
        Log.d("myTag", "log_user..");
        user_local_data.StoreUserData(returned_user);
        user_local_data.set_user_logged_in(true);


        startActivity(new Intent(this, Welcome.class));
        finish();
        //startActivity(new Intent(this, SideMenu.class));
    }

    private void log_user_out(User returned_user) {
        Log.d("myTag", "log_user_out..");
        user_local_data.StoreUserData(returned_user);
        //user_local_data.set_user_logged_in(true);

        startActivity(new Intent(this, Goodbye.class));
        finish();
    }

    private void show_error_message(String error) {
        AlertDialog.Builder dialog_builder = new AlertDialog.Builder(ReaderActivity.this);
        dialog_builder.setMessage(error);
        dialog_builder.setPositiveButton("Ok", null);
        dialog_builder.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.retry_bt_id:
                this.recreate();
                break;

            case R.id.home_bt_id:
                Intent intent = new Intent(this, Start.class);
                startActivity(intent);
                finish();
                break;
        }

    }
}
