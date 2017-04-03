package com.example.newpc.qrcode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Keith on 07/03/2017.
 */

public class StaffStart extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staffstart);
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
}
