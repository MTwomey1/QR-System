package com.example.newpc.qrcode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Keith on 07/03/2017.
 */

public class Staffoptions2 extends Activity {
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staffoption2);
    }
    public void add(android.view.View v) {
        if (v.getId() == R.id.in)
        {
            //go to login class
            Intent intent = new Intent(Staffoptions2.this,Addperson.class);
            startActivity(intent);
        }
    }
    public void remove(View v) {
        if (v.getId() == R.id.in)
        {
            //go to login class
            Intent intent = new Intent(Staffoptions2.this, Removemember.class);
            startActivity(intent);
        }
    }
    public void viewin(View v) {
        if (v.getId() == R.id.in)
        {
            //go to login class
            Intent intent = new Intent(Staffoptions2.this, Location.class);
            startActivity(intent);
        }
    }
    public void viewout(View v) {
        if (v.getId() == R.id.in)
        {
            //go to login class
            Intent intent = new Intent(Staffoptions2.this, Location.class);
            startActivity(intent);
        }
    }
}
