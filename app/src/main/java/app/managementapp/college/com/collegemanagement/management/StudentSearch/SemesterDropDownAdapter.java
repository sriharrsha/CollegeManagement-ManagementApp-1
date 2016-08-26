package app.managementapp.college.com.collegemanagement.management.StudentSearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.managementapp.college.com.collegemanagement.R;
import app.managementapp.college.com.collegemanagement.api.StudentSearch.SemesterList.DataList;

/**
 * Created by Sri Harrsha on 29-Jul-16.
 */
public class SemesterDropDownAdapter extends ArrayAdapter<DataList> {
    ArrayList<DataList> semesters;
    LayoutInflater inflater;
    String semesterId = "";

    public SemesterDropDownAdapter(Context context, List<DataList> semesters) {
        super(context, android.R.layout.simple_spinner_dropdown_item, semesters);
        this.semesters = (ArrayList<DataList>) semesters;
    }

    @Override
    public int getCount() {
        try {
            return semesters.size();
        } catch (NullPointerException nullexp) {
            return 0;
        }
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }


    // This funtion called for each row ( Called data.size() times )
    public View getCustomView(int position, View convertView, ViewGroup parent) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_layout, parent, false);
        TextView label = (TextView) row.findViewById(R.id.text1);
        if (position == 0) {
            label.setText("Select Semester");
        } else {
            label.setText(semesters.get(position).getDrpName());
        }
        return row;
    }
}
