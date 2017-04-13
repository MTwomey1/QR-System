package com.example.newpc.qrcode;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark on 13/04/2017.
 */

public class UserAdapter extends ArrayAdapter{

    List list = new ArrayList<>();

    public UserAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(User object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position,View convertView,ViewGroup parent) {

        View row;
        row = convertView;
        UserHolder userHolder;
        if (row == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);
            userHolder = new UserHolder();
            userHolder.tx_userid = (TextView) row.findViewById(R.id.tx_userid);
            userHolder.tx_firstname = (TextView) row.findViewById(R.id.tx_fname);
            userHolder.tx_surname = (TextView) row.findViewById(R.id.tx_sname);
            row.setTag(userHolder);
        }
        else{
            userHolder = (UserHolder)row.getTag();
        }

        User user = (User) this.getItem(position);
        userHolder.tx_userid.setText(user.getUserid());
        userHolder.tx_firstname.setText(user.getFirstname());
        userHolder.tx_surname.setText(user.getSurname());
        return row;
    }

    static class UserHolder{
        TextView tx_userid, tx_firstname, tx_surname;
    }
}
