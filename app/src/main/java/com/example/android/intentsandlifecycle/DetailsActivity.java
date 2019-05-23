package com.example.android.intentsandlifecycle;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class DetailsActivity extends AppCompatActivity  implements
        View.OnClickListener {

    TextView datePicker;
    TextView nameTxtView;
    EditText ageEdtTxt;
    EditText addressEdtTxt;
    EditText cityEdtTxt;
    String name;

    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        datePicker = findViewById(R.id.date_picker);
        datePicker.setOnClickListener(this);

        getNameFromIntent();
        startMapsIntent();

    }

    //Start the maps intent, putting all of the information gathered from this activity as Extra
    private void startMapsIntent() {
        ageEdtTxt = findViewById(R.id.age_edit_text);
        addressEdtTxt = findViewById(R.id.address_edit_text);
        cityEdtTxt = findViewById(R.id.city_edit_text);
        Button next = findViewById(R.id.next_btn);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ageRegex = "^([1-9]?\\d|100)$";
                if(ageEdtTxt.length() == 0){
                    ageEdtTxt.setError("Моля попълнете възрастта си!");
                }if(addressEdtTxt.length() == 0){
                    addressEdtTxt.setError("Моля, попълнете адреса си!");
                }if(cityEdtTxt.length() == 0){
                    cityEdtTxt.setError("Моля, попълнете града си!");
                }

                if(ageEdtTxt.getText().toString().matches(ageRegex)){
                    Intent mapsIntent = new Intent(DetailsActivity.this, MapsActivity.class);
                    mapsIntent.putExtra("NAME", name);
                    mapsIntent.putExtra("AGE", ageEdtTxt.getText().toString());
                    mapsIntent.putExtra("ADDRESS", addressEdtTxt.getText().toString());
                    mapsIntent.putExtra("CITY", cityEdtTxt.getText().toString());
                    startActivity(mapsIntent);
                }

            }
        });
    }

    //We get the extra (name) from the LoginActivity and then set a TextView to tell the user to fill the next EditText fields
    private void getNameFromIntent() {
        nameTxtView = findViewById(R.id.name_text_view);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle!=null)
        {
            name = bundle.getString("NAME");
            nameTxtView.setText("Здравейте, " + name + "! Моля попълнете следващите полета:");
        }
    }

    @Override
    public void onClick(View v) {
        // Get Current Date and set the year to 10 years ago
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR) - 10;
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        datePicker.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}
