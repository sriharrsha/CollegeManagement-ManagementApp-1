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
import app.managementapp.college.com.collegemanagement.api.StudentSearch.BranchList.DataList;

/**
 * Created by Sri Harrsha on 29-Jul-16.
 */
public class BranchDropDownAdapter extends ArrayAdapter<DataList> {
    ArrayList<DataList> branches;
    LayoutInflater inflater;
    String branchId = "";

    public BranchDropDownAdapter(Context context, List<DataList> branches) {
        super(context, android.R.layout.simple_spinner_dropdown_item, branches);
        this.branches = (ArrayList<DataList>) branches;
    }

    @Override
    public int getCount() {
        try {
            return branches.size();
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

        /********** Inflate spinner_rows.xml file for each row ( Defined below ) ************/
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_layout, parent, false);
        TextView label = (TextView) row.findViewById(R.id.text1);
        if (position == 0) {
            label.setText("Select Branch");
        } else {
            label.setText(branches.get(position).getDrpName());
        }
        return row;
    }
}
