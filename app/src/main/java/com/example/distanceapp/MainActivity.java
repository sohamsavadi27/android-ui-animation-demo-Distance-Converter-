package com.example.distanceapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;


public class MainActivity extends AppCompatActivity {
    EditText etDistance;
    RadioGroup rgChoice;

    Button btnConvert;

    TextView tvMsg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etDistance = findViewById(R.id.etDistance);
        rgChoice = findViewById(R.id.rgchoice);
        btnConvert = findViewById(R.id.btnConvert);
        tvMsg = findViewById(R.id.tvMsg);

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String msg = "";
                double result = 0.0;
                try {
                    double distance = Double.parseDouble(etDistance.getText().toString());
                    int id = rgChoice.getCheckedRadioButtonId();
                    RadioButton rb = findViewById(id);
                    String choice = rb.getText().toString();

                    if (choice.equals("Km2M")) {
                        result = distance * 1000;
                        msg = distance + "km= " + result + " m";
                        tvMsg.setText(msg);
                    } else {
                        result = distance / 1000;
                        msg = distance + " m=" + result + " km";
                        tvMsg.setText(msg);
                    }
                } catch (NumberFormatException e) {
                    msg = "Distance should be in numbers ONLY";
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            }
        });

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                new MaterialAlertDialogBuilder(MainActivity.this)
                        .setTitle("Exit App")
                        .setMessage("Do you want to Exit")
                        .setPositiveButton("Yes",((dialog, which) -> finish()))
                        .setNegativeButton("No",(dialog, which) -> dialog.dismiss())
                        .show();

            }
        });
    }
}