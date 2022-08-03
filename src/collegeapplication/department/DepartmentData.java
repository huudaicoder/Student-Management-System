package collegeapplication.department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import collegeapplication.common.DataBaseConnection;
import collegeapplication.common.Notification;
//import collegeapplication.common.NotificationData;
import collegeapplication.common.TimeUtil;

/*
 * Title : UserData.java
 * Created by : Ajaysinh Rathod
 * Purpose : Handling all the data related to cource
 * Mail : ajaysinhrathod1290@gmail.com
 */

public class DepartmentData {

	static Connection con = DataBaseConnection.getConnection();

	public static void closeConnection() throws SQLException {
		con.close();
	}

	public int addDepartment(String dept_ID, String dept_Name, String semoryear, String totalyearorsem) {
		int result = 0;
		try {
			String query = "insert into department values(?,?,?,?)";
			PreparedStatement pr = con.prepareStatement(query);
			pr.setString(1, dept_ID.toUpperCase());
			pr.setString(2, dept_Name);
			pr.setString(3, semoryear);
			pr.setString(4, totalyearorsem);
			result = pr.executeUpdate();

			pr.close();
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return result;
	}

	public ResultSet getDepartmentinfo() {

		ResultSet st = null;
		try {
			String query = "select d.dept_ID as 'Department ID' ,d.dept_Name as 'Department Name',(select count(*) from subject where subject.dept_ID=d.dept_ID) as 'Subjects' ,(select count(*) from student where student.dept_ID=d.dept_ID) as 'Students',d.building as 'Building' from department d;";
			PreparedStatement pr = con.prepareStatement(query);

			st = pr.executeQuery();
			return st;
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return st;
	}

	public int getTotalDepartment() {
		int totalrow = 0;
		try {
			Statement pr = con.createStatement();
			ResultSet st = pr.executeQuery("select * from department");
			while (st.next()) {
				totalrow++;
			}
			pr.close();

			return totalrow;

		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return totalrow;
	}

	public String[] getDepartmentName() {
		String dept_Name[];
		int i = 0;
		dept_Name = new String[getTotalDepartment() + 1];
		dept_Name[i++] = "---Select Department---";

		try {
			Statement pr = con.createStatement();
			ResultSet st = pr.executeQuery("select dept_Name from department");

			while (st.next()) {
				dept_Name[i++] = st.getString(1);
			}
			pr.close();
			st.close();
			return dept_Name;

		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return dept_Name;

	}

	public String[] getDepartmentcode() {
		String dept_ID[] = new String[this.getTotalDepartment()];
		String query = "select dept_ID from department";
		try {
			Statement pr = con.createStatement();
			ResultSet st = pr.executeQuery(query);
			int i = 0;
			while (st.next()) {
				dept_ID[i++] = st.getString(1);
			}
			pr.close();
			st.close();

			return dept_ID;

		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return dept_ID;

	}

	public String getDepartmentcode(String dept_Name) {
		String query = "select dept_ID from department where dept_Name='" + dept_Name + "'";
		String dept_ID = null;
		try {
			Statement pr = con.createStatement();
			ResultSet st = pr.executeQuery(query);
			st.next();
			dept_ID = st.getString(1);
			pr.close();
			st.close();
			return dept_ID;

		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return dept_ID;
	}

	public String getdept_Name(String dept_ID) {
		String query = "select dept_Name from department where dept_ID='" + dept_ID + "'";
		String dept_Name = null;
		try {
			Statement pr = con.createStatement();
			ResultSet st = pr.executeQuery(query);

			st.next();
			dept_Name = st.getString(1);
			pr.close();
			st.close();
			return dept_Name;

		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return dept_Name;
	}

	public boolean isDepartmentCodeExist(String dept_ID) {
		try {

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select count(*) from department where dept_ID='" + dept_ID + "'");
			rs.next();
			if (rs.getInt(1) > 0) {
				return true;
			}
			rs.close();
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isDepartmentNameExist(String dept_Name) {
		try {

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select count(*) from department where dept_Name='" + dept_Name + "'");
			rs.next();
			if (rs.getInt(1) > 0) {
				return true;
			}
			rs.close();
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isDeclared(String dept_ID, int semoryear) {
		boolean isdeclared = false;
		try {
			String query = "select isdeclared from result where dept_ID='" + dept_ID + "' and semoryear="
					+ semoryear;
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				isdeclared = rs.getBoolean(1);
			}
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return isdeclared;
	}

	// public ArrayList<Department> getDepartmentsForDeclareResult(String dept_Name)
	// {
	// ArrayList<Department> list = new ArrayList<Department>();
	// try {
	// String query = "select dept_Name,dept_ID,totalsemoryear from department where
	// dept_Name='" + dept_Name
	// + "'";
	// Statement st = con.createStatement();
	// ResultSet rs = st.executeQuery(query);
	// while (rs.next()) {
	// int totalsem = rs.getInt(3);
	// for (int i = 0; i < totalsem; i++) {
	// Department cource = new Department();
	// cource.setDept_Name(rs.getString(1));
	// cource.setDept_ID(rs.getString(2));
	// cource.setSemorYear((i + 1));
	// cource.setIsDeclared(isDeclared(rs.getString(2), (i + 1)));
	// list.add(cource);
	// }
	// }
	// } catch (Exception exp) {
	// exp.printStackTrace();
	// }
	// return list;
	// }

	// public int updateResult(Department c) {
	// int result = 0;
	// try {
	// String query = "update result set isdeclared=" + d.getIsDeclared() + " where
	// dept_ID='"
	// + d.getDepartmentCode() + "' and semoryear=" + d.getSemorYear();
	// PreparedStatement pr = con.prepareStatement(query);
	// result = pr.executeUpdate();
	// } catch (Exception exp) {
	// exp.printStackTrace();
	// }
	// return result;
	// }

	// public void declareResult(Department c) {
	// try {
	// if (d.getIsDeclared()) {
	// Notification n = new Notification();
	// n.setUserProfile("Student");
	// n.setDepartmentCode(d.getDepartmentCode());
	// n.setSemorYear(d.getSemorYear());
	// n.setTitle("Result");
	// n.setUserId("Admin");
	// n.setMessage("Your result is declared. now you can see your marksheet.");
	// n.setTime(TimeUtil.getCurrentTime());
	// }
	// if (updateResult(c) == 0) {
	// String query = "insert into result values(?,?,?)";
	// PreparedStatement pr = con.prepareStatement(query);
	// pr.setString(1, d.getDepartmentCode());
	// pr.setInt(2, d.getSemorYear());
	// pr.setBoolean(3, d.getIsDeclared());
	// pr.executeUpdate();
	// }

	// } catch (Exception exp) {
	// exp.printStackTrace();
	// }

	// }

	public int getID(String dept_Name) {
		int result = 0;
		try {
			String query = "select Departmentcode from department where DepartmentName='" + dept_Name + "'";
			PreparedStatement pr = con.prepareStatement(query);
			result = pr.executeUpdate();
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return result;
	}

	public int deleteDept(String depID) {
		int result = 0;
		String query = "delete from department where dept_ID = '" + depID + "'";
		try {
			PreparedStatement pr = con.prepareStatement(query);
			result = pr.executeUpdate();
			pr.close();
			return result;
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public Department getDepartmentDetails(Long deptID) {
		Department s = new Department();

		String query = " select * from department where dept_ID=" + deptID;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			rs.next();
			s.setDept_ID(rs.getString(1));
			s.setDept_Name(rs.getString(2));
			s.setBuilding(rs.getString(3));
			s.setPhoneNumber(rs.getString(4));
			return s;
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	public int updateDepartment(String dept_ID, String dept_Name, String building, String phone) {
		int result = 0;
		String query = "update department set dept_Name=? ,building=? , phoneNumber=? where dept_ID=?";
		try {
			PreparedStatement pr = con.prepareStatement(query);
			pr.setString(1, dept_Name);
			pr.setString(2, building);
			pr.setString(3, phone);
			pr.setString(4, dept_ID);
			result = pr.executeUpdate();
			pr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
