package com.example.newpc.qrcode;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class Goodbye extends Activity {

    User_Local_Data user_local_data;
    TextView et_firstname;
    TextView tv_timer;
    private TextToSpeech ts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodbye);

        et_firstname = (TextView) findViewById(R.id.et_firstname);
        tv_timer = (TextView) findViewById(R.id.tv_timer_id);

        user_local_data = new User_Local_Data(this);

        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
                tv_timer.setText(String.valueOf(millisUntilFinished / 1000));

            }
            public void onFinish() {
                Intent i = getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                user_local_data.clear_user_data();
                finish();
            }
        }.start();

    }

    private boolean authenticate() {
        return user_local_data.get_user_logged_in();
    }


    private void display_user_details() {
        Log.d("myTag", "Display..");
        User user = user_local_data.get_logged_in_user();

        et_firstname.setText(user.firstname);

        ts=new TextToSpeech(Goodbye.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                String text = "Goodbye " + et_firstname.getText();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ts.speak(text,TextToSpeech.QUEUE_FLUSH,null,null);
                } else {
                    ts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                }

            }
        });

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

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        ts.shutdown();
    }


}
