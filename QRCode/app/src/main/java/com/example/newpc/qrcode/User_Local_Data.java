package com.example.newpc.qrcode;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * Created by Mark on 30/03/2017.
 */

public class User_Local_Data {

    public static final String SP_NAME = "userData";
    SharedPreferences userLocalDatabase;

    public User_Local_Data(Context context) {
        // get the shared preference stored on the phone
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }


    public void StoreUserData(User user) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("firstname", user.firstname);
        spEditor.putString("surname", user.surname);

        spEditor.commit();
    }


    public void StoreUserData(Map<String, String> data_to_edit) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("userid", data_to_edit.get("userid"));
        spEditor.putString("firstname", data_to_edit.get("firstname"));
        spEditor.putString("surname", data_to_edit.get("surname"));

        spEditor.commit();
    }


    public User get_logged_in_user() {
        String userid = userLocalDatabase.getString("userid", "");
        String username = userLocalDatabase.getString("username", "");
        String password = userLocalDatabase.getString("password", "");

        User stored_user = new User(userid,username,password);

        return stored_user;
    }


    public String get_username() {
        String username = userLocalDatabase.getString("username", "");
        return username;
    }


    public void set_user_logged_in(boolean logged_in) {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("logged_in", logged_in);
        spEditor.commit();
    }


    public boolean get_user_logged_in() {
        if (userLocalDatabase.getBoolean("logged_in", false) == true) {
            return true;
        } else {
            return false;
        }
    }

    public void clear_user_data() {
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }

}
