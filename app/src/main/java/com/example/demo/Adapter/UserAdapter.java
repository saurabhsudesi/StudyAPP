package com.example.demo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.Model.UserModel;
import com.example.demo.R;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {
    Context mContext;
    ArrayList<UserModel> userModelArrayList;


    public UserAdapter(Context mContext, ArrayList<UserModel> userModelArrayList) {
        this.mContext = mContext;
        this.userModelArrayList = userModelArrayList;
    }

    @NonNull
    @Override
    public UserAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.MyViewHolder holder, int position) {
        UserModel userModel = userModelArrayList.get(position);

        holder.name.setText("Name: "+userModel.getName());
        holder.email.setText("Email Id: "+userModel.getEmail_id());

    }

    @Override
    public int getItemCount() {
        return userModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, email;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.et_name);
            email = itemView.findViewById(R.id.et_email_ID);
        }
    }
}
