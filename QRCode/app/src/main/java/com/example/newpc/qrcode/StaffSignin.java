package com.example.newpc.qrcode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Keith on 07/03/2017.
 */

public class StaffSignin extends Activity
{
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staffsign);
    }
    public void logIn(View v) {
        if (v.getId() == R.id.in)
        {
            //go to login class
            Intent intent = new Intent(StaffSignin.this, Staffoptions2.class);
            startActivity(intent);
        }
    }
}
