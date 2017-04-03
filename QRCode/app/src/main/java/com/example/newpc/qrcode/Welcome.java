package com.example.newpc.qrcode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Welcome extends Activity {

    User_Local_Data user_local_data;
    EditText et_surname;
    TextView et_firstname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        et_surname = (EditText) findViewById(R.id.et_surname);
        et_firstname = (TextView) findViewById(R.id.et_firstname);

        user_local_data = new User_Local_Data(this);
    }

    private boolean authenticate() {
        return user_local_data.get_user_logged_in();
    }


    private void display_user_details() {

        User user = user_local_data.get_logged_in_user();

        et_firstname.setText(user.firstname);
        et_surname.setText(user.surname);
    }


    @Override
    protected void onStart() {
        super.onStart();

        if (authenticate() == true ) {
            display_user_details();
        } else {
            //startActivity(new Intent(MainActivity.this, Login.class));
        }
    }

}
