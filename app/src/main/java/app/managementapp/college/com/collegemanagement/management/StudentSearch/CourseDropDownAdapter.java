package app.managementapp.college.com.collegemanagement.management.StudentSearch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.managementapp.college.com.collegemanagement.R;
import app.managementapp.college.com.collegemanagement.api.CourseList.DataList;

/**
 * Created by Sri Harrsha on 29-Jul-16.
 */
public class CourseDropDownAdapter extends ArrayAdapter<DataList> {
    List<DataList> courses = new ArrayList<>(3);
    LayoutInflater inflater;
    String courseId = "";

    public CourseDropDownAdapter(StudentSearch context, ArrayList<DataList> courses) {
        super(context, android.R.layout.simple_spinner_dropdown_item, courses);
        this.courses = courses;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }


    @Override
    public int getCount() {
        try {
            return courses.size();
        } catch (NullPointerException nullexp) {
            return 0;
        }
    }

    public DataList getItem(int position) {
        return courses.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    // This funtion called for each row ( Called data.size() times )
    public View getCustomView(int position, View convertView, ViewGroup parent) {
        /********** Inflate spinner_rows.xml file for each row ( Defined below ) ************/
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_layout, parent, false);
        TextView label = (TextView) row.findViewById(R.id.text1);
        if (position == 0) {
            label.setText("Select Course");
        } else {
            label.setText(courses.get(position).getDrpName());
        }
        return row;
    }
}
