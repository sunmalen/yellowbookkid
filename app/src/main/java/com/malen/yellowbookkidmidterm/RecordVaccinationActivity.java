package com.malen.yellowbookkidmidterm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.malen.yellowbookkidmidterm.database.DatabaseHelper;
import com.malen.yellowbookkidmidterm.models.Child;
import com.malen.yellowbookkidmidterm.models.VaccinationRecord;
import com.malen.yellowbookkidmidterm.models.Vaccine;

import java.util.List;

public class RecordVaccinationActivity extends AppCompatActivity {

    private Spinner spinnerChildren;
    private Spinner spinnerVaccines;
    private EditText etInjectionDate;
    private EditText etNextFollowupDate;
    private Button btnSaveVaccination;

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_vaccination);

        spinnerChildren = findViewById(R.id.spinnerChildren);
        spinnerVaccines = findViewById(R.id.spinnerVaccines);
        etInjectionDate = findViewById(R.id.etInjectionDate);
        etNextFollowupDate = findViewById(R.id.etNextFollowupDate);
        btnSaveVaccination = findViewById(R.id.btnSaveVaccination);

        db = new DatabaseHelper(this);
        List<Child> children = db.getAllChildren();
        List<Vaccine> vaccines = db.getAllVaccines();

        if (children.isEmpty()) {
            Log.e("RecordVaccination", "No children found in database");
        } else {
            Log.d("RecordVaccination", "Children loaded: " + children.size());
        }

        if (vaccines.isEmpty()) {
            Log.e("RecordVaccination", "No vaccines found in database");
        } else {
            Log.d("RecordVaccination", "Vaccines loaded: " + vaccines.size());
        }

        ArrayAdapter<Child> childAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, children);
        childAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerChildren.setAdapter(childAdapter);

        ArrayAdapter<Vaccine> vaccineAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, vaccines);
        vaccineAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerVaccines.setAdapter(vaccineAdapter);

        btnSaveVaccination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Child selectedChild = (Child) spinnerChildren.getSelectedItem();
                Vaccine selectedVaccine = (Vaccine) spinnerVaccines.getSelectedItem();

                if (selectedChild == null || selectedVaccine == null) {
                    Toast.makeText(RecordVaccinationActivity.this, "Please select a child and a vaccine", Toast.LENGTH_SHORT).show();
                    return;
                }

                String injectionDate = etInjectionDate.getText().toString();
                String nextFollowupDate = etNextFollowupDate.getText().toString();

                if (!injectionDate.isEmpty() && !nextFollowupDate.isEmpty()) {
                    VaccinationRecord record = new VaccinationRecord(0, selectedChild.getId(), selectedVaccine.getId(), injectionDate, nextFollowupDate);
                    db.addVaccinationRecord(record);
                    Toast.makeText(RecordVaccinationActivity.this, "Vaccination recorded successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RecordVaccinationActivity.this, ViewRecordsActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(RecordVaccinationActivity.this, "Please enter all details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
