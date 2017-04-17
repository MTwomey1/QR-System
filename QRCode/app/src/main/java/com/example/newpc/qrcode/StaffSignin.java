package com.example.newpc.qrcode;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;


public class StaffSignin extends Activity implements View.OnClickListener {

    EditText et_username, et_password;
    Button login_button;
    User_Local_Data user_local_data;
    TextView return_view;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staffsign);

        et_username = (EditText) findViewById(R.id.staff_username_id);
        et_password = (EditText) findViewById(R.id.staff_pass_id);
        login_button = (Button) findViewById(R.id.login_bt_id);
        return_view = (TextView) findViewById(R.id.return_tv);

        login_button.setOnClickListener(this);
        user_local_data = new User_Local_Data(this);
    }

    @Override
    public void onClick(View v) {

        // check if user has active internet connection
        if (AppStatus.getInstance(this).isOnline()) {

            switch (v.getId()) {

                case R.id.login_bt_id:

                    String username = et_username.getText().toString();
                    String password = et_password.getText().toString();

                    User user = new User(username, password);

                    authenticate(user);
                    break;
            }

        } else {
            Toast.makeText(getApplicationContext(),"You are offline", Toast.LENGTH_LONG).show();
        }
    }

    private void authenticate(User user) {

        Server_Requests server_requests = new Server_Requests(this);
        server_requests.login_staff(user, new Get_String_Callback() {
            @Override
            public void done(String returned_string) {
                if (returned_string.equals("failed")) {
                    show_error_message("Incorrect username or password");
                    Log.d("myTag","Fookd String");
                } else {

                    try {
                        Log.d("myTag","String fill");
                        return_view.setText(returned_string);

                        // Create a JSONObject from the returned String
                        JSONObject jObject = new JSONObject(returned_string);

                        String username = jObject.getString("username");
                        String password = jObject.getString("password");
                        String firstname = jObject.getString("firstname");
                        String surname = jObject.getString("surname");
                        String location = jObject.getString("location");
                        String status = jObject.getString("status");

                        User user = new User(username,password,firstname,surname,location,status);

                        log_user_in(user);

                        //finish();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }// end else
            }// end done();
        });
    }

    private void show_error_message(String error) {
        AlertDialog.Builder dialog_builder = new AlertDialog.Builder(StaffSignin.this);
        dialog_builder.setMessage(error);
        dialog_builder.setPositiveButton("Ok", null);
        dialog_builder.show();
    }

    private void log_user_in(User returned_user) {
        Log.d("myTag", "Log user");
        user_local_data.StoreStaff(returned_user);
        user_local_data.set_user_logged_in(true);


        startActivity(new Intent(this, Staffoptions2.class));
        finish();
    }

}
