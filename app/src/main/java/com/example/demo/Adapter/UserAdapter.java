package com.example.demo.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.Database.DatabaseCon;
import com.example.demo.MainApp.MainActivity;
import com.example.demo.MainApp.UpdateDetailsActivity;
import com.example.demo.Model.UserModel;
import com.example.demo.R;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {
    Context mContext;
    ArrayList<UserModel> userModelArrayList;
    DatabaseCon databaseCon;

    public UserAdapter(Context mContext, ArrayList<UserModel> userModelArrayList) {
        this.mContext = mContext;
        this.userModelArrayList = userModelArrayList;
        databaseCon = new DatabaseCon(mContext);
        databaseCon.open();

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

        holder.id.setText("Id: " + userModel.getId());
        holder.name.setText("Name: " + userModel.getName());
        holder.email.setText("Email Id: " + userModel.getEmail_id());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(mContext);
                builder1.setMessage("Do you want to remove ?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Delete",
                        new DialogInterface.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.N)
                            public void onClick(DialogInterface dialog, int id) {
                                boolean result = databaseCon.delete("login", "id", "'" + userModel.getId() + "'");
                                Log.e("result", "result===" + result);
                                //Do your code...
                                if (result) {
                                    Toast.makeText(mContext, "Delete", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(mContext, MainActivity.class);
                                    mContext.startActivity(intent);
                                } else {
                                    dialog.cancel();
                                    Toast.makeText(mContext, "Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                builder1.setNegativeButton(
                        "Edit",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(mContext, UpdateDetailsActivity.class);
                                intent.putExtra("name",userModel.getName());
                                intent.putExtra("email",userModel.getEmail_id());
                                intent.putExtra("contact",userModel.getContact_no());
                                intent.putExtra("address",userModel.getAddress());
                                intent.putExtra("password",userModel.getPassword());
                                mContext.startActivity(intent);
                                Toast.makeText(mContext, "Edit", Toast.LENGTH_SHORT).show();

                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return userModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, email, id;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.et_id);
            name = itemView.findViewById(R.id.et_name);
            email = itemView.findViewById(R.id.et_email_ID);
        }
    }
}
