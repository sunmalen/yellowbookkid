package com.malen.yellowbookkidmidterm;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.malen.yellowbookkidmidterm.database.DatabaseHelper;
import com.malen.yellowbookkidmidterm.models.Child;

public class MainActivity extends AppCompatActivity {

    private EditText etChildName, etBirthDate;
    private Button btnSaveChild;
    private DatabaseHelper db;

    private SharedPrefsManager prefsManager;
    private EditText editTextId, editTextName, editTextValue;
    private Button buttonAdd, buttonView;
    private ListView listView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etChildName = findViewById(R.id.etChildName);
        etBirthDate = findViewById(R.id.etBirthday);
        btnSaveChild = findViewById(R.id.btnSaveChild);

        db = new DatabaseHelper(this);

        btnSaveChild.setOnClickListener(v -> {
            String name = etChildName.getText().toString();
            String birthDate = etBirthDate.getText().toString();

            if (!name.isEmpty() && !birthDate.isEmpty()) {
                try {
                    db.addChild(new Child(0, name, birthDate));
                    Toast.makeText( MainActivity.this, "Child registered", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, RecordVaccineActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    Log.e("RegisterChildActivity", "Error adding child", e);
                    Toast.makeText( MainActivity.this, "Error registering child", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText( MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
        });



        prefsManager = new SharedPrefsManager(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
