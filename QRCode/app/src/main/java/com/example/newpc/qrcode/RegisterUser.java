package com.example.newpc.qrcode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener{

    EditText et_user_firstname, et_user_surname, et_user_location, et_user_userid;
    Button submit_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        et_user_firstname = (EditText) findViewById(R.id.user_fname_id);
        et_user_surname = (EditText) findViewById(R.id.user_sname_id);
        et_user_location = (EditText) findViewById(R.id.user_loc_id);
        et_user_userid = (EditText) findViewById(R.id.user_id_id) ;

        submit_button = (Button) findViewById(R.id.user_submit_bt);

        submit_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        // check if user has active internet connection
        if (AppStatus.getInstance(this).isOnline()) {

            switch (v.getId()) {
                case R.id.user_submit_bt:

                    String userid = et_user_userid.getText().toString();
                    String firstname = et_user_firstname.getText().toString();
                    String surname = et_user_surname.getText().toString();
                    String location = et_user_location.getText().toString();
                    String status = "OUT";

                    if(isEmpty(userid) == false){
                        et_user_userid.setError("Enter userid");
                        return;
                    }

                    if(isEmpty(firstname) == false){
                        et_user_firstname.setError("Enter firstname");
                        return;
                    }

                    if(isEmpty(surname) == false){
                        et_user_surname.setError("Enter Surname");
                        return;
                    }

                    if(isEmpty(location) == false){
                        et_user_location.setError("Enter Location");
                        return;

                    } else {
                        User user = new User(userid, firstname, surname, location, status);
                        register_user(user);
                    }

                    break;
            }
        }else {
            Toast.makeText(getApplicationContext(),"You are offline", Toast.LENGTH_SHORT).show();
        }
    }


    private void register_user(User user) {
        Server_Requests server_requests = new Server_Requests(this);
        server_requests.register_user(user, new Get_String_Callback() {
            @Override
            public void done(String returned_string) {

                if (returned_string.trim().equals("UserID taken")) {
                    et_user_userid.setError("Userid Already in Use");
                    Toast.makeText(getApplicationContext(),"Userid Already in Use", Toast.LENGTH_SHORT).show();
                }
                else {
                    startActivity(new Intent(RegisterUser.this, Staffoptions2.class));
                    finish();

                }
            }
        });
    }


    // check if editText is empty
    static private boolean isEmpty(String text) {
        return (text.trim().length() > 0);
    }
}
