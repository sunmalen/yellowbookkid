package com.malen.yellowbookkidmidterm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.malen.yellowbookkidmidterm.database.DatabaseHelper;
import com.malen.yellowbookkidmidterm.models.Vaccine;

public class RecordVaccineActivity extends AppCompatActivity {
    EditText etName, etDes, etMonthDue;
    Button bSave;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_vaccine);

        etName = findViewById(R.id.etVaccineName);
        etDes = findViewById(R.id.etDescription);
        etMonthDue = findViewById(R.id.etMonthDue);
        bSave = findViewById(R.id.btnSaveVaccine);

        db = new DatabaseHelper(this);

        bSave.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String des = etDes.getText().toString();
            String monthDueString = etMonthDue.getText().toString();
            if (name.isEmpty() || des.isEmpty() || monthDueString.isEmpty()) {
                Toast.makeText(RecordVaccineActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                int monthDue = Integer.parseInt(monthDueString);
                Vaccine vaccine = new Vaccine(0, name, des, monthDue);
                try {
                    db.addVaccine(vaccine);
                    Toast.makeText(RecordVaccineActivity.this, "Vaccine recorded successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RecordVaccineActivity.this, RecordVaccinationActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    Log.e("RecordVaccineActivity", "Error inserting vaccine", e);
                    Toast.makeText(RecordVaccineActivity.this, "Error recording vaccine", Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException e) {
                Log.e("RecordVaccineActivity", "Invalid month due value", e);
                Toast.makeText(RecordVaccineActivity.this, "Please enter a valid number for months due", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
