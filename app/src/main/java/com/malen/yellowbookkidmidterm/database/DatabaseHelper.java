package com.malen.yellowbookkidmidterm.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.malen.yellowbookkidmidterm.models.Child;
import com.malen.yellowbookkidmidterm.models.VaccinationRecord;
import com.malen.yellowbookkidmidterm.models.Vaccine;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "KidVaccination";

    // Table Names
    private static final String TABLE_CHILDREN = "children";
    private static final String TABLE_VACCINES = "vaccines";
    private static final String TABLE_VACCINATION_RECORDS = "vaccination_records";

    // Common column names
    private static final String KEY_ID = "id";

    // Children Table - column names
    private static final String KEY_CHILD_NAME = "name";
    private static final String KEY_BIRTH_DATE = "birth_date";

    // Vaccines Table - column names
    private static final String KEY_VACCINE_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_MONTHS_DUE = "months_due";

    // Vaccination Records Table - column names
    private static final String KEY_CHILD_ID = "child_id";
    private static final String KEY_VACCINE_ID = "vaccine_id";
    private static final String KEY_INJECTION_DATE = "injection_date";
    private static final String KEY_NEXT_FOLLOWUP_DATE = "next_followup_date";

    // Table Create Statements
    // Children table create statement
    private static final String CREATE_TABLE_CHILDREN = "CREATE TABLE "
            + TABLE_CHILDREN + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_CHILD_NAME
            + " TEXT," + KEY_BIRTH_DATE + " TEXT" + ")";

    // Vaccines table create statement
    private static final String CREATE_TABLE_VACCINES = "CREATE TABLE "
            + TABLE_VACCINES + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_VACCINE_NAME
            + " TEXT," + KEY_DESCRIPTION + " TEXT," + KEY_MONTHS_DUE + " INTEGER" + ")";

    // Vaccination Records table create statement
    private static final String CREATE_TABLE_VACCINATION_RECORDS = "CREATE TABLE "
            + TABLE_VACCINATION_RECORDS + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_CHILD_ID + " INTEGER," + KEY_VACCINE_ID + " INTEGER,"
            + KEY_INJECTION_DATE + " TEXT," + KEY_NEXT_FOLLOWUP_DATE + " TEXT" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_CHILDREN);
        db.execSQL(CREATE_TABLE_VACCINES);
        db.execSQL(CREATE_TABLE_VACCINATION_RECORDS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHILDREN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VACCINES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VACCINATION_RECORDS);

        // create new tables
        onCreate(db);
    }

    // CRUD operations

    // Adding a new child
    public void addChild(Child child) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CHILD_NAME, child.getName());
        values.put(KEY_BIRTH_DATE, child.getBirthDate());

        db.insert(TABLE_CHILDREN, null, values);
        db.close();
    }

    // Getting all children
    public List<Child> getAllChildren() {
        List<Child> children = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_CHILDREN;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex(KEY_ID);
                int nameIndex = cursor.getColumnIndex(KEY_CHILD_NAME);
                int birthDateIndex = cursor.getColumnIndex(KEY_BIRTH_DATE);

                if (idIndex != -1 && nameIndex != -1 && birthDateIndex != -1) {
                    Child child = new Child(
                            cursor.getInt(idIndex),
                            cursor.getString(nameIndex),
                            cursor.getString(birthDateIndex)
                    );
                    children.add(child);
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return children;
    }

    // Adding a new vaccine
    public void addVaccine(Vaccine vaccine) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_VACCINE_NAME, vaccine.getName());
        values.put(KEY_DESCRIPTION, vaccine.getDescription());
        values.put(KEY_MONTHS_DUE, vaccine.getMonthsDue());

        db.insert(TABLE_VACCINES, null, values);
        db.close();
    }

    // Getting all vaccines
    public List<Vaccine> getAllVaccines() {
        List<Vaccine> vaccines = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_VACCINES;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex(KEY_ID);
                int nameIndex = cursor.getColumnIndex(KEY_VACCINE_NAME);
                int descriptionIndex = cursor.getColumnIndex(KEY_DESCRIPTION);
                int monthsDueIndex = cursor.getColumnIndex(KEY_MONTHS_DUE);

                if (idIndex != -1 && nameIndex != -1 && descriptionIndex != -1 && monthsDueIndex != -1) {
                    Vaccine vaccine = new Vaccine(
                            cursor.getInt(idIndex),
                            cursor.getString(nameIndex),
                            cursor.getString(descriptionIndex),
                            cursor.getInt(monthsDueIndex)
                    );
                    vaccines.add(vaccine);
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return vaccines;
    }

    // Adding a new vaccination record
    public void addVaccinationRecord(VaccinationRecord record) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CHILD_ID, record.getChildId());
        values.put(KEY_VACCINE_ID, record.getVaccineId());
        values.put(KEY_INJECTION_DATE, record.getInjectionDate());
        values.put(KEY_NEXT_FOLLOWUP_DATE, record.getNextFollowupDate());

        db.insert(TABLE_VACCINATION_RECORDS, null, values);
        db.close();
    }

    // Getting all vaccination records
    public List<VaccinationRecord> getAllVaccinationRecords() {
        List<VaccinationRecord> records = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_VACCINATION_RECORDS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int idIndex = cursor.getColumnIndex(KEY_ID);
                int childIdIndex = cursor.getColumnIndex(KEY_CHILD_ID);
                int vaccineIdIndex = cursor.getColumnIndex(KEY_VACCINE_ID);
                int injectionDateIndex = cursor.getColumnIndex(KEY_INJECTION_DATE);
                int nextFollowupDateIndex = cursor.getColumnIndex(KEY_NEXT_FOLLOWUP_DATE);

                if (idIndex != -1 && childIdIndex != -1 && vaccineIdIndex != -1 && injectionDateIndex != -1 && nextFollowupDateIndex != -1) {
                    VaccinationRecord record = new VaccinationRecord(
                            cursor.getInt(idIndex),
                            cursor.getInt(childIdIndex),
                            cursor.getInt(vaccineIdIndex),
                            cursor.getString(injectionDateIndex),
                            cursor.getString(nextFollowupDateIndex)
                    );
                    records.add(record);
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return records;
    }
}