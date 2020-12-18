package com.example.yaatris;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;





public class BookingPage extends AppCompatActivity {

    private Button button;
    EditText e;
    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_page);
        e = (EditText) findViewById(R.id.editTextTextEmailAddress2);

        radioGroup = findViewById(R.id.radioGroup);



        button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openConfirmed();

                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
            }
        });
    }
    public void openConfirmed() {
        Intent intent = new Intent(this, Confirmed.class);
        intent.putExtra( "ID", e.getText().toString());
        startActivity(intent);
    }

    public void checkButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        Toast.makeText(this, "Payment Method : " + radioButton.getText(),
                Toast.LENGTH_SHORT).show();
    }

}