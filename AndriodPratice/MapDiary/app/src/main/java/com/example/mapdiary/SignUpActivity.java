package com.example.mapdiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    String id,pass,name;
    EditText signup_id, signup_password,signup_name;
    Button registration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signup_id = (EditText) findViewById(R.id.signup_id);
        signup_password = (EditText) findViewById(R.id.signup_password);
        signup_name = (EditText) findViewById(R.id.signup_name);
        registration = (Button) findViewById(R.id.register);

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = signup_id.getText().toString();
                pass = signup_password.getText().toString();
                name = signup_name.getText().toString();

                new SendData(id,pass,name).execute();

                Intent intnet = new Intent();
                finish();

            }
        });
    }
}
