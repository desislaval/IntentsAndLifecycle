package com.example.android.intentsandlifecycle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    Button loginBtn;
    EditText nameEdtTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initialize edit text and button
        nameEdtTxt = findViewById(R.id.name_edit_text);
        loginBtn = findViewById(R.id.login_btn);

        //Set on click listener to the button, then check if the edit text field is empty or if it matches the regex (only cyrillic letters)
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String regex = "[а-я А-Я]+";
                if(nameEdtTxt.length() == 0){
                    nameEdtTxt.setError("Моля напишете името си на кирилица.");
                }else if(nameEdtTxt.getText().toString().matches(regex)){
                    //Starts an intent to the DetailsActivity and puts name as Extra
                    Intent detailsIntent = new Intent(LoginActivity.this, DetailsActivity.class);
                    nameEdtTxt = findViewById(R.id.name_edit_text);
                    String name = nameEdtTxt.getText().toString();
                    detailsIntent.putExtra("NAME", name);
                    startActivity(detailsIntent);
                }else{
                    nameEdtTxt.setError("Моля напишете името си на кирилица.");
                }
            }
        });

    }
}
