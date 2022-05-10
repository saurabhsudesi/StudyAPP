package com.example.demo.MainApp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.demo.Database.DatabaseAdapter;
import com.example.demo.Helper.RequiredFunction;
import com.example.demo.R;
import com.google.android.material.textfield.TextInputEditText;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity {
    TextInputEditText name, email_id, contact_no, address, password;
    Button bt_submit;
    String str_name = "", str_emailId = "", str_contactNo = "", str_address = "", str_password = "";
    String path;
    RequiredFunction rf;
    DatabaseAdapter databaseAdapter;
    String[] values;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseAdapter = new DatabaseAdapter(MainActivity.this);

        rf = new RequiredFunction();
        name = findViewById(R.id.name);
        email_id = findViewById(R.id.email_id);
        contact_no = findViewById(R.id.contact_no);
        address = findViewById(R.id.address);
        password = findViewById(R.id.password);
        bt_submit = findViewById(R.id.submit);


        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] columns = new String[]{"name", "email_id", "contact_no", "address", "password"};

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
                                        databaseAdapter.open();
                                        values = new String[]{str_name, str_emailId, str_contactNo, str_address, str_password};
                                        boolean result = databaseAdapter.insert(values, columns, "login");

                                        if (result) {

                                            email_id.setText("");
                                            contact_no.setText("");
                                            address.setText("");
                                            password.setText("");
                                            name.setText("");
                                            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                                            startActivity(intent);
                                            Toast.makeText(MainActivity.this, "Result:-   " + result, Toast.LENGTH_SHORT).show();
                                        }
                                        databaseAdapter.close();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        Log.e("checkError", "" + e.toString());
                                        Toast.makeText(MainActivity.this, "" + e.toString(), Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    password.setError("Enter Password");
                                    Toast.makeText(MainActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                address.setError("Enter Address");
                                Toast.makeText(MainActivity.this, "Enter Address", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            contact_no.setError("Enter Contact Number");
                            Toast.makeText(MainActivity.this, "Enter Contact Number", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        email_id.setError("Enter Email-Id");
                        Toast.makeText(MainActivity.this, "Enter Email-Id", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    name.setError("Enter Name");
                    Toast.makeText(MainActivity.this, "Enter Name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}