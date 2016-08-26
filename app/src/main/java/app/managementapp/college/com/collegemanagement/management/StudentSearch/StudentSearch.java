package app.managementapp.college.com.collegemanagement.management.StudentSearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import app.managementapp.college.com.collegemanagement.R;
import app.managementapp.college.com.collegemanagement.api.Authentication.RegularAuth.RegularLoginResponse;
import app.managementapp.college.com.collegemanagement.api.CollegeManagementApiService;
import app.managementapp.college.com.collegemanagement.api.CourseList.CourseListResponse;
import app.managementapp.college.com.collegemanagement.api.CourseList.DataList;
import app.managementapp.college.com.collegemanagement.api.ServiceGenerator;
import app.managementapp.college.com.collegemanagement.api.StudentSearch.BranchList.BranchListResponse;
import app.managementapp.college.com.collegemanagement.api.StudentSearch.SemesterList.SemesterListResponse;
import app.managementapp.college.com.collegemanagement.management.ManagementHome;
import app.managementapp.college.com.collegemanagement.util.CredentialManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StudentSearch extends AppCompatActivity {
    private static final String DEBUG_TAG = "StudentSearch";
    CourseDropDownAdapter courseAdapter;
    Spinner courseSpinner;
    Spinner branchSpinner;
    Spinner semesterSpinner;
    CredentialManager credentialManager;
    View.OnClickListener onFilterbackclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d(DEBUG_TAG, "onClick: onFilterbackTimeTableclickListener");
            moveToLanding();
        }
    };
    private ArrayList<app.managementapp.college.com.collegemanagement.api.CourseList.DataList> courseList = new ArrayList<DataList>(3);
    private ArrayList<app.managementapp.college.com.collegemanagement.api.StudentSearch.BranchList.DataList> branchList;
    private ArrayList<app.managementapp.college.com.collegemanagement.api.StudentSearch.SemesterList.DataList> semesterList;
    private Call<RegularLoginResponse> loginCall;
    private BranchDropDownAdapter branchAdapter;
    private SemesterDropDownAdapter semesterAdapter;
    View.OnClickListener onSearchclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d(DEBUG_TAG, "onClick: onFilterbackTimeTableclickListener");
            Toast.makeText(getApplicationContext(), semesterAdapter.semesterId + branchAdapter.branchId + courseAdapter.courseId, Toast.LENGTH_LONG).show();
            Intent i = new Intent(StudentSearch.this, StudentSearchList.class);
            startActivity(i);
            finish();

        }
    };

    @Override
    public void onBackPressed() {

        moveToLanding();

    }

    private void moveToLanding() {
        Intent i = new Intent(StudentSearch.this, ManagementHome.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_search);
        ((ImageView) findViewById(R.id.backTimeTable)).setOnClickListener(onFilterbackclickListener);
        ((Button) findViewById(R.id.searchStudents)).setOnClickListener(onSearchclickListener);
        courseSpinner = (Spinner) this.findViewById(R.id.course_spinner);
        courseAdapter = new CourseDropDownAdapter(this, courseList);
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseSpinner.setAdapter(courseAdapter);

        branchSpinner = (Spinner) this.findViewById(R.id.branch_spinner);
        branchAdapter = new BranchDropDownAdapter(this, branchList);
        branchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        branchSpinner.setAdapter(branchAdapter);
        branchSpinner.setEnabled(false);


        semesterSpinner = (Spinner) this.findViewById(R.id.semester_spinner);
        semesterAdapter = new SemesterDropDownAdapter(this, semesterList);
        semesterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        semesterSpinner.setEnabled(false);
        semesterSpinner.setAdapter(semesterAdapter);


        final CollegeManagementApiService collegeManagementApiService = ServiceGenerator.createService(CollegeManagementApiService.class);
        credentialManager = new CredentialManager(this);
        loginCall = collegeManagementApiService.doRegularLogin(credentialManager.getUserName(), credentialManager.getPassword());
        loginCall.enqueue(new Callback<RegularLoginResponse>() {
            @Override
            public void onResponse(Call<RegularLoginResponse> call, Response<RegularLoginResponse> response) {
                Call<CourseListResponse> courseListResponseCall = collegeManagementApiService.getCourseList(response.body().getToken());

                courseListResponseCall.enqueue(new Callback<CourseListResponse>() {
                    @Override
                    public void onResponse(Call<CourseListResponse> call, Response<CourseListResponse> response) {
                        courseAdapter.courses = response.body().getDataList();
                        courseAdapter.notifyDataSetChanged();
                        Toast.makeText(getApplicationContext(), "Course List Fetched " + response.body().getErrorMessage(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<CourseListResponse> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onFailure(Call<RegularLoginResponse> call, Throwable t) {
                Log.e("ERROR", t.toString());
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });

        courseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                branchSpinner.setEnabled(false);
                branchAdapter.branchId = "";
                semesterAdapter.semesterId = "";
                courseAdapter.courseId = courseAdapter.courses.get(position).getDrpID();
                loginCall = collegeManagementApiService.doRegularLogin(credentialManager.getUserName(), credentialManager.getPassword());
                loginCall.enqueue(new Callback<RegularLoginResponse>() {
                    @Override
                    public void onResponse(Call<RegularLoginResponse> call, Response<RegularLoginResponse> response) {
                        final Call<SemesterListResponse> semesterResponseCall = collegeManagementApiService.getSemester(response.body().getToken(), Integer.valueOf(courseAdapter.courses.get(position).getDrpID()));
                        semesterResponseCall.enqueue(new Callback<SemesterListResponse>() {
                            @Override
                            public void onResponse(Call<SemesterListResponse> call, Response<SemesterListResponse> response) {
                                semesterAdapter.semesters = (ArrayList<app.managementapp.college.com.collegemanagement.api.StudentSearch.SemesterList.DataList>) response.body().getDataList();
                                semesterAdapter.notifyDataSetChanged();
                                Toast.makeText(getApplicationContext(), "Semester List Fetched " + response.body().getErrorMessage(), Toast.LENGTH_LONG).show();
                                semesterSpinner.setEnabled(true);
                                semesterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, final int semposition, long id) {
                                        semesterAdapter.semesterId = semesterAdapter.semesters.get(semposition).getDrpID();
                                        branchAdapter.branchId = "";
                                        loginCall = collegeManagementApiService.doRegularLogin(credentialManager.getUserName(), credentialManager.getPassword());
                                        loginCall.enqueue(new Callback<RegularLoginResponse>() {
                                            @Override
                                            public void onResponse(Call<RegularLoginResponse> call, Response<RegularLoginResponse> response) {
                                                Call<BranchListResponse> branchListResponseCall = collegeManagementApiService.getBranchOrCycle(response.body().getToken(), Integer.valueOf(courseAdapter.courses.get(position).getDrpID()), Integer.valueOf(semesterAdapter.semesters.get(semposition).getDrpID()));
                                                branchListResponseCall.enqueue(new Callback<BranchListResponse>() {
                                                    @Override
                                                    public void onResponse(Call<BranchListResponse> call, Response<BranchListResponse> response) {

                                                        branchAdapter.branches = (ArrayList<app.managementapp.college.com.collegemanagement.api.StudentSearch.BranchList.DataList>) response.body().getDataList();
                                                        branchAdapter.notifyDataSetChanged();
                                                        Toast.makeText(getApplicationContext(), "Branches  List Fetched " + response.body().getErrorMessage(), Toast.LENGTH_LONG).show();
                                                        branchSpinner.setEnabled(true);
                                                        branchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                                                            @Override
                                                            public void onItemSelected(AdapterView<?> parent, View view, int brposition, long id) {
                                                                branchAdapter.branchId = branchAdapter.branches.get(brposition).getDrpID();
                                                            }

                                                            @Override
                                                            public void onNothingSelected(AdapterView<?> parent) {

                                                            }
                                                        });
                                                    }

                                                    @Override
                                                    public void onFailure(Call<BranchListResponse> call, Throwable t) {

                                                    }

                                                });


                                            }

                                            @Override
                                            public void onFailure(Call<RegularLoginResponse> call, Throwable t) {
                                                Log.e("ERROR", t.toString());
                                                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                                            }
                                        });
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {

                                    }
                                });
                            }

                            @Override
                            public void onFailure(Call<SemesterListResponse> call, Throwable t) {

                            }
                        });


                    }

                    @Override
                    public void onFailure(Call<RegularLoginResponse> call, Throwable t) {
                        Log.e("ERROR", t.toString());
                        Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}
