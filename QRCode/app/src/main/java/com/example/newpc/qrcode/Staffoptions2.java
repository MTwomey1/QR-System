package com.example.newpc.qrcode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Keith on 07/03/2017.
 */

public class Staffoptions2 extends Activity implements View.OnClickListener {

    Button view_in_bt, view_out_bt, view_all_bt;
    Button register_staff_bt, register_user_bt;
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

        view_in_bt.setOnClickListener(this);
        view_out_bt.setOnClickListener(this);
        view_all_bt.setOnClickListener(this);
        register_staff_bt.setOnClickListener(this);
        register_user_bt.setOnClickListener(this);
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
        }
    }
}
