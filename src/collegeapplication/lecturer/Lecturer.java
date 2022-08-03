package collegeapplication.lecturer;

import collegeapplication.common.TimeUtil;
import collegeapplication.department.Department;
import collegeapplication.department.DepartmentData;

/*
 * Title : Lecturer.java
 * Created by : Ajaysinh Rathod
 * Purpose : Binding all the of lecturer
 * Mail : ajaysinhrathod1290@gmail.com
 */
public class Lecturer extends Department {

	private String lecturerid;
	private String lecturername;
	private String dept_id;
	private String email;
	private String contactnumber;
	private String city;

	public String getLecturerid() {
		return lecturerid;
	}

	public void setLecturerid(String lecturerid) {
		this.lecturerid = lecturerid;
	}

	public String getLecturername() {
		return lecturername;
	}

	public void setLecturername(String lecturername) {
		this.lecturername = lecturername;
	}

	public String getDept_name() {
		// return dept_name;

		return new DepartmentData().getdept_Name(getDept_id());
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactnumber() {
		return contactnumber;
	}

	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

}