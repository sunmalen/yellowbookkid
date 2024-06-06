package com.malen.yellowbookkidmidterm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.malen.yellowbookkidmidterm.models.VaccinationRecord;
import java.util.List;

public class VaccinationRecordAdapter extends ArrayAdapter<VaccinationRecord> {

    private final Context context;
    private final List<VaccinationRecord> records;

    public VaccinationRecordAdapter(Context context, List<VaccinationRecord> records) {
        super(context, R.layout.item_vaccination_record, records);
        this.context = context;
        this.records = records;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_vaccination_record, parent, false);
        }

        VaccinationRecord record = records.get(position);

        TextView tvChildId = convertView.findViewById(R.id.tvChildId);
        TextView tvVaccineId = convertView.findViewById(R.id.tvVaccineId);
        TextView tvInjectionDate = convertView.findViewById(R.id.tvInjectionDate);
        TextView tvNextFollowupDate = convertView.findViewById(R.id.tvNextFollowupDate);

        tvChildId.setText("Child ID: " + record.getChildId());
        tvVaccineId.setText("Vaccine ID: " + record.getVaccineId());
        tvInjectionDate.setText("Injection Date: " + record.getInjectionDate());
        tvNextFollowupDate.setText("Next Follow-up Date: " + record.getNextFollowupDate());

        return convertView;
    }
}
