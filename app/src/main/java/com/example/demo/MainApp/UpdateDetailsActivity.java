package com.example.demo.MainApp;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demo.Database.DatabaseAdapter;
import com.example.demo.Helper.RequiredFunction;
import com.example.demo.R;
import com.google.android.material.textfield.TextInputEditText;

public class UpdateDetailsActivity extends AppCompatActivity {
    TextInputEditText name, email_id, contact_no, address, password;
    Button bt_submit;
    String str_name = "", str_emailId = "", str_contactNo = "", str_address = "", str_password = "";
    String path;
    RequiredFunction rf;
    DatabaseAdapter db;
    String[] values;
    int responseID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details);
        db = new DatabaseAdapter(UpdateDetailsActivity.this);
        db.open();

        String strid = getIntent().getStringExtra("id");
        String strname = getIntent().getStringExtra("name");
        String stremail = getIntent().getStringExtra("email");
        String strcontnact = getIntent().getStringExtra("contact");
        String straddress = getIntent().getStringExtra("address");
        String strpassword = getIntent().getStringExtra("password");




        rf = new RequiredFunction();
        name = findViewById(R.id.name);
        email_id = findViewById(R.id.email_id);
        contact_no = findViewById(R.id.contact_no);
        address = findViewById(R.id.address);
        password = findViewById(R.id.password);
        bt_submit = findViewById(R.id.updte);

        name.setText(strname);
        email_id.setText(stremail);
        contact_no.setText(strcontnact);
        address.setText(straddress);
        password.setText(strpassword);


        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                str_name = name.getText().toString().trim();
                str_emailId = email_id.getText().toString();
                str_contactNo = contact_no.getText().toString();
                str_address = address.getText().toString();
                str_password = password.getText().toString();

                if (!str_name.equals("") && !str_name.isEmpty()) {
                    if (rf.validEmail(str_emailId) && !str_emailId.equals("") && !str_emailId.isEmpty()) {
                        if (rf.validContact(str_contactNo) && !str_contactNo.equals("") && !str_contactNo.isEmpty()) {
                            if (!str_address.equals("") && !str_address.isEmpty()) {
                                if (!str_password.equals("") && !str_password.isEmpty()) {

                                    /*sample*/
                                    try {
                                        db.open();
                                        ContentValues cv = new ContentValues();
                                        cv.put("name",str_name);
                                        cv.put("email_id",str_emailId);
                                        cv.put("contact_no",str_contactNo);
                                        cv.put("address",str_address);
                                        cv.put("password",str_password);

                                        responseID = db.update("login",cv,"id='"+strid+"'",null);
                                        if (responseID>0) {
                                            Intent intent = new Intent(UpdateDetailsActivity.this,MainActivity2.class);
                                            startActivity(intent);
                                            Toast.makeText(UpdateDetailsActivity.this, "Result:-   " + responseID, Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(UpdateDetailsActivity.this, "Result:-   " + responseID, Toast.LENGTH_SHORT).show();

                                        }
                                        db.close();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        Log.e("checkError", "" + e.toString());
                                        Toast.makeText(UpdateDetailsActivity.this, "" + e.toString(), Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    password.setError("Enter Password");
                                    Toast.makeText(UpdateDetailsActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                address.setError("Enter Address");
                                Toast.makeText(UpdateDetailsActivity.this, "Enter Address", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            contact_no.setError("Enter Contact Number");
                            Toast.makeText(UpdateDetailsActivity.this, "Enter Contact Number", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        email_id.setError("Enter Email-Id");
                        Toast.makeText(UpdateDetailsActivity.this, "Enter Email-Id", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    name.setError("Enter Name");
                    Toast.makeText(UpdateDetailsActivity.this, "Enter Name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}