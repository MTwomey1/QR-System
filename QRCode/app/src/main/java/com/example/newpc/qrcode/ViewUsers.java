package com.example.newpc.qrcode;

import android.app.AlertDialog;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ViewUsers extends AppCompatActivity {

    String userid;
    UserAdapter userAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users);
        User user = new User(userid, userid);
        displayUsers(user);

        listView = (ListView)findViewById(R.id.listview);

        userAdapter = new UserAdapter(this,R.layout.row_layout);

        listView.setAdapter(userAdapter);
    }

    private void displayUsers(final User user){
        Server_Requests server_requests = new Server_Requests(this);
        server_requests.fetch_all_users(user, new Get_String_Callback() {
            @Override
            public void done(String returned_string) {
                Log.d("MyTag", returned_string);
                if (returned_string.equals("failed")) {
                    show_error_message("Incorrect QR");
                    Log.d("myTag", "logout fail..");
                } else {
                    Log.d("myTag", "View users win..");

                    try {
                        JSONObject jObject = new JSONObject(returned_string);

                        for (int i = 0; i < jObject.length(); i++){
                            String userid  = jObject.get("userid"+i).toString();
                            String firstname  = jObject.get("firstname"+i).toString();
                            String surname = jObject.get("lastname"+i).toString();
                            User user = new User(userid, firstname, surname);
                            userAdapter.add(user);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }// end else

            }
        });
    }

    private void show_error_message(String error) {
        AlertDialog.Builder dialog_builder = new AlertDialog.Builder(ViewUsers.this);
        dialog_builder.setMessage(error);
        dialog_builder.setPositiveButton("Ok", null);
        dialog_builder.show();
    }

}
