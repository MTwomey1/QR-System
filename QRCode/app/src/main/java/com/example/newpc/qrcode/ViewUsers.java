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

import java.util.Iterator;

public class ViewUsers extends AppCompatActivity {

    String userid;
    TextView tv_return;
    JSONObject jsonObject;
    JSONArray jsonArray;
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

                        //jsonArray = jsonObject.getJSONArray("users");
                        //JSONArray jsonArray = new JSONArray(returned_string);

                        /*int count = 0;

                        while(count<jsonArray.length()){
                            JSONObject JO = jsonArray.getJSONObject(count);
                            userid = JO.getString("userid0");
                            firstname = JO.getString("firstname0");
                            surname = JO.getString("lastname0");

                            User user = new User(userid, firstname, surname);
                            userAdapter.add(user);
                            count++;
                        }*/

                       /* String userid = jObject.getString("userid0");
                        String firstname = jObject.getString("FirstName0");
                        String surname = jObject.getString("LastName0");

                        user = new User(userid, firstname, surname);

                        log_user_out(user); */



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
