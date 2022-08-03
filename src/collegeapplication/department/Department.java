package collegeapplication.department;

public class Department {

	private String dept_ID;
	private String dept_Name;
	private String building;
	private String phoneNumber;

	public String getDept_ID() {
		return dept_ID;
	}

	public void setDept_ID(String dept_ID) {
		this.dept_ID = dept_ID;
	}

	public String getDept_Name() {
		return dept_Name != null ? dept_Name : new DepartmentData().getdept_Name(dept_ID);
	}

	public void setDept_Name(String dept_Name) {
		this.dept_Name = dept_Name;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
