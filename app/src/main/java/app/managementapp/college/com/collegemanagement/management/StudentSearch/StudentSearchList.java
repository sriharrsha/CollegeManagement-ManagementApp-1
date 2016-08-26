package app.managementapp.college.com.collegemanagement.management.StudentSearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import app.managementapp.college.com.collegemanagement.R;

public class StudentSearchList extends AppCompatActivity {
    private static final String DEBUG_TAG = "StudentSearchList";
    View.OnClickListener onFilterbackclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d(DEBUG_TAG, "onClick: onFilterbackTimeTableclickListener");
            moveToSearch();
        }
    };

    @Override
    public void onBackPressed() {

        moveToSearch();

    }

    private void moveToSearch() {
        Intent i = new Intent(StudentSearchList.this, StudentSearch.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_search_list);


        ((ImageView) findViewById(R.id.backTimeTable)).setOnClickListener(onFilterbackclickListener);
    }

}
