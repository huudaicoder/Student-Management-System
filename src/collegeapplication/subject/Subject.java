package collegeapplication.subject;

import collegeapplication.department.Department;
import collegeapplication.department.DepartmentData;

public class Subject extends Department {

	private String subjectID;
	private String subjectName;
	private String dept_id;
	private Float factor;
	private int credit;

	public String getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(String subjectID) {
		this.subjectID = subjectID;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_ID(String dept_id) {
		this.dept_id = dept_id;
	}

	public Float getFactor() {
		return factor;
	}

	public void setFactor(Float factor) {
		this.factor = factor;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public String getDept_name() {
		// return dept_name;

		return new DepartmentData().getdept_Name(getDept_id());
	}
}
