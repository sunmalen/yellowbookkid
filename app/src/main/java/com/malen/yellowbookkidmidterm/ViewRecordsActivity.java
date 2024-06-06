package com.malen.yellowbookkidmidterm;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.malen.yellowbookkidmidterm.database.DatabaseHelper;
import com.malen.yellowbookkidmidterm.models.VaccinationRecord;

import java.util.List;

public class ViewRecordsActivity extends AppCompatActivity {
    private DatabaseHelper db;
    private ListView listViewRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_records);

        listViewRecords = findViewById(R.id.listViewRecords);

        // Initialize the DatabaseHelper
        db = new DatabaseHelper(this);

        List<VaccinationRecord> records = db.getAllVaccinationRecords();
        VaccinationRecordAdapter adapter = new VaccinationRecordAdapter(this, records);
        listViewRecords.setAdapter(adapter);
    }
}
