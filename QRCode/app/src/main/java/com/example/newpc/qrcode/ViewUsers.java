package com.example.newpc.qrcode;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ViewUsers extends AppCompatActivity {

    String userid = "Test";
    TextView tv_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users);

        User user = new User(userid, userid);
        displayUsers(user);
    }

    private void displayUsers(User user){
        Server_Requests server_requests = new Server_Requests(this);
        server_requests.fetch_all_users(user, new Get_String_Callback() {
            @Override
            public void done(String returned_string) {
                Log.d("MyTag", returned_string);
               // tv_return.setText(returned_string);
            }
        });
    }
}
