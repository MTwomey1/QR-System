package com.example.newpc.qrcode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Start extends AppCompatActivity {

    Globals g = Globals.getInstance();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Button button = (Button)findViewById(R.id.admin);
        button.setOnLongClickListener(
                new Button.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        Intent intent = new Intent(Start.this, StaffStart.class);
                        startActivity(intent);
                        return true;
                    }
                }
        );
    }
    //onclick signin button
    public void signIn(View v) {
        if (v.getId() == R.id.in)
        {
            //go to login class
            Intent intent = new Intent(Start.this, ReaderActivity.class);
            startActivity(intent);
            g.setTest(1);
        }
    }
    //onclick signout button
    public void signOut(View v) {
        if (v.getId() == R.id.out)
        {
            //go to the register class
            Intent intent = new Intent(Start.this, ReaderActivity.class);
            startActivity(intent);
            g.setTest(0);
        }
    }
}
