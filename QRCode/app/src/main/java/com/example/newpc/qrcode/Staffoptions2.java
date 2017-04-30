package com.example.newpc.qrcode;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Keith on 07/03/2017.
 */

public class Staffoptions2 extends AppCompatActivity implements View.OnClickListener {

    Button view_in_bt, view_out_bt, view_all_bt;
    Button register_staff_bt, register_user_bt;
    Button remove_staff_b, remove_user_b, sign_all_out_b;
    Globals g2 = Globals.getInstance();

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staffoption2);

        view_in_bt = (Button) findViewById(R.id.view_users_bt_id);
        view_out_bt = (Button) findViewById(R.id.user_out_bt_id);
        view_all_bt = (Button) findViewById(R.id.all_user_bt_id);
        register_staff_bt = (Button) findViewById(R.id.register_b_id);
        register_user_bt = (Button) findViewById(R.id.register_user_bt_id);
        //remove_staff_b = (Button) findViewById(R.id.remove_staff_b_id) ;
        remove_user_b = (Button) findViewById(R.id.remove_user_b_id);
        sign_all_out_b = (Button) findViewById(R.id.sign_out_all_b_id);

        view_in_bt.setOnClickListener(this);
        view_out_bt.setOnClickListener(this);
        view_all_bt.setOnClickListener(this);
        register_staff_bt.setOnClickListener(this);
        register_user_bt.setOnClickListener(this);
        //remove_staff_b.setOnClickListener(this);
        remove_user_b.setOnClickListener(this);
        sign_all_out_b.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.register_b_id:

                Intent intent = new Intent(this, Register.class);
                startActivity(intent);

                break;

            case R.id.view_users_bt_id:

                g2.setTest(1);
                Intent intent2 = new Intent(this, ViewUsers.class);
                startActivity(intent2);

                break;

            case R.id.user_out_bt_id:

                g2.setTest(2);
                Intent intent3 = new Intent(this, ViewUsers.class);
                startActivity(intent3);

                break;

            case R.id.all_user_bt_id:

                g2.setTest(3);
                Intent intent4 = new Intent(this, ViewUsers.class);
                startActivity(intent4);

                break;

            case R.id.register_user_bt_id:

                Intent intent5 = new Intent(this, RegisterUser.class);
                startActivity(intent5);

                break;

            /*case R.id.remove_staff_b_id:

                Intent intent6 = new Intent(this, RemoveStaff.class);
                startActivity(intent6);

                break;*/

            case R.id.remove_user_b_id:

                Intent intent7 = new Intent(this, RemoveUser.class);
                startActivity(intent7);

                break;

            case R.id.sign_out_all_b_id:

                new AlertDialog.Builder(this)
                        .setTitle("Sign Out All Users")
                        .setMessage("Do you really want to Sign out all Users?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                String userid = "Dummy";
                                String firstname = "Dummy";
                                String surname = "Dummy";
                                User user = new User(userid, firstname, surname);
                                sign_all_out(user);
                                Toast.makeText(Staffoptions2.this, "All Users Signed Out", Toast.LENGTH_SHORT).show();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();

                break;
        }
    }

    private void sign_all_out(final User user){
        Server_Requests server_requests = new Server_Requests(this);
        server_requests.sign_all_out(user, new Get_String_Callback() {
            @Override
            public void done(String returned_string) {
                Log.d("MyTag", returned_string);
                if (returned_string.equals("failed")) {
                    show_error_message("Incorrect QR");
                    Log.d("myTag", "logout fail..");
                } else {
                    Log.d("myTag", "View users win..");

                }// end else

            }
        });
    }

    private void show_error_message(String error) {
        android.app.AlertDialog.Builder dialog_builder = new android.app.AlertDialog.Builder(Staffoptions2.this);
        dialog_builder.setMessage(error);
        dialog_builder.setPositiveButton("Ok", null);
        dialog_builder.show();
    }

}
