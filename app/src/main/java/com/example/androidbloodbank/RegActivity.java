package com.example.androidbloodbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.Random;

public class RegActivity extends AppCompatActivity {

    private DatabaseReference mFirebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private TextView email,name,number,city,password,blood;
    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);


        firebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference().child("users");

        login = (Button) findViewById(R.id.loginButton_id);

        name = findViewById(R.id.name_id);
        email = findViewById(R.id.email_id);
        number = findViewById(R.id.number_id);
        city = findViewById(R.id.City_id);
        password = findViewById(R.id.pass_id);
        blood = findViewById(R.id.blood_id);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nname = name.getText().toString();
                String nemail = email.getText().toString();
                String nnumber = number.getText().toString();
                String ncity = city.getText().toString();
                String npassword = password.getText().toString();
                String nblood = blood.getText().toString();
                if (nname.equals("") || nemail.equals("") || nnumber.equals("") || ncity.equals("") || npassword.equals("")) {
                    Toast.makeText(RegActivity.this, "Please fill all the details.", Toast.LENGTH_SHORT).show();
                } else {

                    int random = (int)(Math.random() * 500000000 + 1);
                    String userid = "" + random;

                    mFirebaseDatabase.child(userid).child("Name").setValue(nname);
                    mFirebaseDatabase.child(userid).child("Email").setValue(nemail);
                    mFirebaseDatabase.child(userid).child("Number").setValue(nnumber);
                    mFirebaseDatabase.child(userid).child("City").setValue(ncity);
                    mFirebaseDatabase.child(userid).child("Password").setValue(npassword);
                    mFirebaseDatabase.child(userid).child("Blood").setValue(nblood);
                    Toast.makeText(RegActivity.this, "Donor Added", Toast.LENGTH_SHORT).show();
                    Intent home = new Intent(RegActivity.this, MainActivity.class);
                    startActivity(home);
                    finish();
                }
            }
        });

    }
}
