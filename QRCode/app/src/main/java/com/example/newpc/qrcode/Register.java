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

public class Register extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    EditText et_username, et_password, et_confirm_password, et_firstname, et_surname, et_location;
    Button submit_button;

    private boolean valid_email = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_username = (EditText) findViewById(R.id.usernameID);
        et_password = (EditText) findViewById(R.id.passwordID);
        et_confirm_password = (EditText) findViewById(R.id.confirmPasswordID);
        et_firstname = (EditText) findViewById(R.id.user_fname_id);
        et_surname = (EditText) findViewById(R.id.user_sname_id);
        et_location = (EditText) findViewById(R.id.user_loc_id);

        submit_button = (Button) findViewById(R.id.submitButtonID);

        submit_button.setOnClickListener(this);
        et_username.addTextChangedListener(this);
    }

    @Override
    public void onClick(View v) {

        // check if user has active internet connection
        if (AppStatus.getInstance(this).isOnline()) {

            switch (v.getId()) {
                case R.id.submitButtonID:

                    String username = et_username.getText().toString();
                    String password = et_password.getText().toString();
                    String confirm_pass = et_confirm_password.getText().toString();
                    String firstname = et_firstname.getText().toString();
                    String surname = et_surname.getText().toString();
                    String location = et_location.getText().toString();
                    String status = "OUT";

                    if (isEmpty(username) == false) {
                        et_username.setError("Enter username");
                        return;
                    }

                    if (isEmpty(password) == false) {
                        et_password.setError("Enter password");
                        return;
                    }

                    if (isEmpty(confirm_pass) == false) {
                        et_confirm_password.setError("Enter confirm password");
                        return;
                    }

                    if(isEmpty(firstname) == false){
                        et_firstname.setError("Enter firstname");
                        return;
                    }

                    if(isEmpty(surname) == false){
                        et_surname.setError("Enter Surname");
                        return;
                    }

                    if(isEmpty(location) == false){
                        et_location.setError("Enter Location");
                        return;
                    }

                    if (password.equals(confirm_pass)) {
                            User user = new User(username, password, firstname, surname, location, status);
                            register_user(user);

                    } else {
                        Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_SHORT).show();
                    }

                    break;
            }
        }else {
            Toast.makeText(getApplicationContext(),"You are offline", Toast.LENGTH_SHORT).show();
        }
    }


    private void register_user(User user) {
        Server_Requests server_requests = new Server_Requests(this);
        server_requests.store_user_data_in_background(user, new Get_String_Callback() {
            @Override
            public void done(String returned_string) {

                if (returned_string.trim().equals("Username taken")) {
                    et_username.setError("Username Already in Use");
                }
                 else {
                    startActivity(new Intent(Register.this, StaffSignin.class));
                    finish();
                }
            }
        });
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    // check if editText is empty
    static private boolean isEmpty(String text) {
        return (text.trim().length() > 0);
    }
}
