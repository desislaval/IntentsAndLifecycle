package com.example.android.intentsandlifecycle;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MapsActivity extends AppCompatActivity {

    TextView userDataTxt;
    Button openMapsBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        getDataFromIntent();
        openMaps();
    }

    private void openMaps() {
        openMapsBtn = findViewById(R.id.open_maps_btn);
        openMapsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                Bundle bundle = intent.getExtras();
                String address;
                if(bundle!=null){
                    address = bundle.getString("ADDRESS");
                    address += ", " + bundle.getString("CITY");

                    String map = "http://maps.google.co.in/maps?q=" + address;
                    Uri gmmIntentUri = Uri.parse(map);
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                }


            }
        });
    }

    private void getDataFromIntent() {
        userDataTxt = findViewById(R.id.user_data_text_view);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            String data = bundle.getString("NAME");
            data += ", " + bundle.getString("AGE");
            data += ", " + bundle.getString("ADDRESS");
            data += ", " + bundle.getString("CITY");
            userDataTxt.setText(data);
        }
    }
}
