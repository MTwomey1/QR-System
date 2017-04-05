package com.example.newpc.qrcode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ViewUsers extends AppCompatActivity {

    String userid = "Test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users);

        User user = new User(userid);
        displayUsers(user);
    }

    private void displayUsers(User user){
        Server_Requests server_requests = new Server_Requests(this);
        server_requests.fetch_all_users(user, new Get_String_Callback() {
            @Override
            public void done(String returned_string) {
                Log.d("MyTag", returned_string);
            }
        });
    }
}
