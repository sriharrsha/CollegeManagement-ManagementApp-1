
package app.managementapp.college.com.collegemanagement.api.StudentSearch.StudentList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentListRequest {

    @SerializedName("SessionID")
    @Expose
    private String sessionID;
    @SerializedName("LoginID")
    @Expose
    private String loginID;
    @SerializedName("CollegeID")
    @Expose
    private String collegeID;
    @SerializedName("CourseID")
    @Expose
    private String courseID;
    @SerializedName("SemID")
    @Expose
    private String semID;
    @SerializedName("BranchID")
    @Expose
    private String branchID;
    @SerializedName("USN")
    @Expose
    private String uSN;
    @SerializedName("CycleID")
    @Expose
    private String cycleID;
    @SerializedName("SectionID")
    @Expose
    private String sectionID;
    @SerializedName("FirstName")
    @Expose
    private String firstName;

    /**
     * 
     * @return
     *     The sessionID
     */
    public String getSessionID() {
        return sessionID;
    }

    /**
     * 
     * @param sessionID
     *     The SessionID
     */
    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    /**
     * 
     * @return
     *     The loginID
     */
    public String getLoginID() {
        return loginID;
    }

    /**
     * 
     * @param loginID
     *     The LoginID
     */
    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }

    /**
     * 
     * @return
     *     The collegeID
     */
    public String getCollegeID() {
        return collegeID;
    }

    /**
     * 
     * @param collegeID
     *     The CollegeID
     */
    public void setCollegeID(String collegeID) {
        this.collegeID = collegeID;
    }

    /**
     * 
     * @return
     *     The courseID
     */
    public String getCourseID() {
        return courseID;
    }

    /**
     * 
     * @param courseID
     *     The CourseID
     */
    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    /**
     * 
     * @return
     *     The semID
     */
    public String getSemID() {
        return semID;
    }

    /**
     * 
     * @param semID
     *     The SemID
     */
    public void setSemID(String semID) {
        this.semID = semID;
    }

    /**
     * 
     * @return
     *     The branchID
     */
    public String getBranchID() {
        return branchID;
    }

    /**
     * 
     * @param branchID
     *     The BranchID
     */
    public void setBranchID(String branchID) {
        this.branchID = branchID;
    }

    /**
     * 
     * @return
     *     The uSN
     */
    public String getUSN() {
        return uSN;
    }

    /**
     * 
     * @param uSN
     *     The USN
     */
    public void setUSN(String uSN) {
        this.uSN = uSN;
    }

    /**
     * 
     * @return
     *     The cycleID
     */
    public String getCycleID() {
        return cycleID;
    }

    /**
     * 
     * @param cycleID
     *     The CycleID
     */
    public void setCycleID(String cycleID) {
        this.cycleID = cycleID;
    }

    /**
     * 
     * @return
     *     The sectionID
     */
    public String getSectionID() {
        return sectionID;
    }

    /**
     * 
     * @param sectionID
     *     The SectionID
     */
    public void setSectionID(String sectionID) {
        this.sectionID = sectionID;
    }

    /**
     * 
     * @return
     *     The firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * 
     * @param firstName
     *     The FirstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

}
