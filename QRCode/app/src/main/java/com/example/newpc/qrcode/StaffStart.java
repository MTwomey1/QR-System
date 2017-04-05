package com.example.newpc.qrcode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Keith on 07/03/2017.
 */

public class StaffStart extends Activity implements View.OnClickListener {

    Button register_btn, view_users_btn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staffstart);

        register_btn = (Button) findViewById(R.id.register_bt_ID);
        view_users_btn = (Button) findViewById(R.id.viewUsers_bt_ID);

        register_btn.setOnClickListener(this);
        view_users_btn.setOnClickListener(this);
    }
    public void signIn(View v) {
        if (v.getId() == R.id.in)
        {
            //go to login class
            Intent intent = new Intent(StaffStart.this, StaffSignin.class);
            startActivity(intent);
        }
    }
    //onclick signout button
    public void signOut(View v) {
        if (v.getId() == R.id.out)
        {
            //go to the register class
            //Intent intent = new Intent(.this, MainActivity.class);
            //startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.register_bt_ID:

                Intent intent = new Intent(this, Register.class);
                startActivity(intent);

                break;

            case R.id.viewUsers_bt_ID:

                Intent intent2 = new Intent(this, ViewUsers.class);
                startActivity(intent2);
        }
    }
}
