package com.example.newpc.qrcode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RemoveUser extends AppCompatActivity implements View.OnClickListener {

    Button submit_b;
    EditText et_remove_id, et_remove_surname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_user);

        submit_b = (Button) findViewById(R.id.remove_user_submit_id);
        submit_b.setOnClickListener(this);

        et_remove_id = (EditText) findViewById(R.id.et_remove_user_id);
        et_remove_surname = (EditText) findViewById(R.id.et_remove_surname_id);
    }

    @Override
    public void onClick(View v) {
        // check if user has active internet connection
        if (AppStatus.getInstance(this).isOnline()) {

            switch (v.getId()) {

                case R.id.remove_user_submit_id:

                    String userid = et_remove_id.getText().toString();
                    String surname = et_remove_surname.getText().toString();
                    String firstname = "Dummy";

                    if(isEmpty(userid) == false){
                        et_remove_id.setError("Enter userid");
                        return;
                    }


                    if(isEmpty(surname) == false){
                        et_remove_surname.setError("Enter Surname");
                        return;
                    }
                    else {
                        User user = new User(userid, firstname, surname);
                        remove_user(user);
                    }

                    break;
            }
        }else {
            Toast.makeText(getApplicationContext(),"You are offline", Toast.LENGTH_SHORT).show();
        }
    }

    private void remove_user(User user) {
        Server_Requests server_requests = new Server_Requests(this);
        server_requests.remove_user(user, new Get_String_Callback() {
            @Override
            public void done(String returned_string) {

                if (returned_string.trim().equals("UserID Invalid")) {
                    et_remove_id.setError("Userid Invalid");
                    Toast.makeText(getApplicationContext(),"Userid Invalid", Toast.LENGTH_SHORT).show();
                }
                if (returned_string.trim().equals("Data Error")){
                    et_remove_surname.setError("Surname Invalid");
                    Toast.makeText(getApplicationContext(),"Surname Invalid", Toast.LENGTH_SHORT).show();
                }
                if (returned_string.trim().equals("Successful")){
                    //startActivity(new Intent(RemoveUser.this, Staffoptions2.class));
                    Toast.makeText(getApplicationContext(),returned_string, Toast.LENGTH_SHORT).show();
                    Log.d("TAg", returned_string);
                    finish();

                }
                else{
                    Toast.makeText(getApplicationContext(),"Data Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // check if editText is empty
    static private boolean isEmpty(String text) {
        return (text.trim().length() > 0);
    }

}
