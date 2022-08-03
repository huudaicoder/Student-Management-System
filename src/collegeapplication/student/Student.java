package collegeapplication.student;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import collegeapplication.common.TimeUtil;
import collegeapplication.department.Department;
import collegeapplication.department.DepartmentData;

public class Student extends Department {

	private String studentID;
	private String studentName;
	private String dept_id;
	private String birthDate;
	private String gender;
	private String cpa;
	private String totalCredits;

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCpa() {
		return cpa;
	}

	public void setCpa(String cpa) {
		this.cpa = cpa;
	}

	public String getTotalCredits() {
		return totalCredits;
	}

	public void setTotalCredits(String totalCredits) {
		this.totalCredits = totalCredits;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public Date getBirthDateInDateFormat() {
		Date date = null;
		try {
			date = new SimpleDateFormat("dd-MM-yyyy").parse(this.birthDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
}
