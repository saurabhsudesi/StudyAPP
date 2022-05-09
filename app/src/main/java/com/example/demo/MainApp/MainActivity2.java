package com.example.demo.MainApp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.Adapter.UserAdapter;
import com.example.demo.Database.DatabaseCon;
import com.example.demo.Model.UserModel;
import com.example.demo.R;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    RecyclerView recyclerView;
    UserAdapter userAdapter;
    ArrayList<UserModel> userModelArrayList = new ArrayList<>();
    DatabaseCon databaseCon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        databaseCon = new DatabaseCon(MainActivity2.this);
        databaseCon.open();

        userModelArrayList=databaseCon.getUSerList();

        if (userModelArrayList.size()>0){
            recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            userAdapter = new UserAdapter(MainActivity2.this,userModelArrayList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity2.this);
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(userAdapter);
        }else{
            Toast.makeText(MainActivity2.this, "Empty", Toast.LENGTH_LONG).show();
        }
    }
}