package collegeapplication.lecturer;

import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import collegeapplication.common.DataBaseConnection;
import collegeapplication.common.TimeUtil;

/*
 * Title : LecturerData.java
 * Created by : Ajaysinh Rathod
 * Purpose : Handling all the data related to lecturer
 * Mail : ajaysinhrathod1290@gmail.com
 */
public class LecturerData {
	static Connection con = DataBaseConnection.getConnection();

	public static void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getTotalLecturer() {
		int totalf = 0;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select count(*) from lecturer");
			rs.next();
			totalf = rs.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalf;
	}

	public int getTotalLecturers(String dept_ID) {
		int totalf = 0;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(
					"select count(*) from lecturer where dept_ID='" + dept_ID + "'");
			rs.next();
			totalf = rs.getInt(1);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalf;
	}

	public int createLecturerID() {
		int id = 101;
		try {
			java.sql.Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select count(*) from lecturer");
			rs.next();
			id = id + rs.getInt(1);
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return id;

	}

	public ResultSet getLecturerInfo() {
		ResultSet rs = null;
		try {
			String query = "select l.lecturerID as 'Lecturer ID',l.lecturerName as 'Lecturer Name',d.dept_Name as 'Department',l.email as 'Email',l.phoneNumber as 'Phone' from lecturer as l,department as d where l.dept_ID = d.dept_ID order by l.lecturerID";
			java.sql.Statement st = con.createStatement();
			rs = st.executeQuery(query);
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return rs;
	}

	public ResultSet searchLecturer(String query) {
		ResultSet rs = null;
		query += " order by lecturerID";
		try {
			java.sql.Statement st = con.createStatement();
			rs = st.executeQuery(query);

		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return rs;
	}

	public String getLecturerName(String lecturerID) {
		try {
			String query = "select lecturerName from lecturer where lecturerID=" + lecturerID;
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			rs.next();
			return rs.getString(1);
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return "";
	}

	public String getLecturerID(String lecturerName) {
		String subcode = null;
		String query = "select lecturerID from lecturer where lecturerName='" + lecturerName + "'";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			rs.next();
			subcode = rs.getString(1);

		} catch (Exception exp) {
			return null;
		}

		return subcode;

	}

	public String[] getLecturerName() {
		String lecturername[];
		int i = 0;
		lecturername = new String[getTotalLecturer() + 1];
		lecturername[i++] = "---Select Lecturer---";

		try {
			Statement pr = con.createStatement();
			ResultSet st = pr.executeQuery("select lecturerName from lecturer");

			while (st.next()) {
				lecturername[i++] = st.getString(1);
			}
			pr.close();
			st.close();
			return lecturername;

		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return lecturername;

	}

	// add
	public int addLecturerData(Lecturer f) {
		int result = 0;
		String query = "insert into lecturer values(?,?,?,?,?,?)";
		try {
			PreparedStatement pr = con.prepareStatement(query);
			pr.setString(1, f.getLecturerid());
			pr.setString(2, f.getLecturername());
			pr.setString(3, f.getDept_id());
			pr.setString(4, f.getCity());
			pr.setString(5, f.getEmail());
			pr.setString(6, f.getContactnumber());
			result = pr.executeUpdate();
			pr.close();
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return result;
	}

	// update
	public int updateLecturerData(Lecturer fold, Lecturer f) {
		int result = 0;
		String query = "update lecturer set lecturerID=? , lecturerName=? ,dept_ID=? , city=? , email=? , phoneNumber=? where lecturerID="
				+ fold.getLecturerid();
		try {
			PreparedStatement pr = con.prepareStatement(query);
			pr.setString(1, f.getLecturerid());
			pr.setString(2, f.getLecturername());
			pr.setString(3, f.getDept_id());
			pr.setString(4, f.getCity());
			pr.setString(5, f.getEmail());
			pr.setString(6, f.getContactnumber());
			result = pr.executeUpdate();
			pr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	//
	public int deleteLecturer(String lecturerID) {
		int result = 0;
		String query = "delete from lecturer where lecturerID = '" + lecturerID + "'";
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

	public Lecturer getLecturerInfo(int row) {
		Lecturer f = new Lecturer();
		String query = "select * from lecturer where sr_no=" + row;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			rs.next();
			f.setLecturerid(rs.getString(1));
			f.setLecturername(rs.getString(2));
			f.setDept_id(rs.getString(3));
			f.setCity(rs.getString(4));
			f.setEmail(rs.getString(5));
			f.setContactnumber(rs.getString(6));
			st.close();

			return f;
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return f;
	}

	public Lecturer getLecturerInfobyId(String lecturerID) {
		Lecturer f = new Lecturer();
		String query = "select * from lecturer where lecturerID= '" + lecturerID + "'";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			rs.next();
			f.setLecturerid(rs.getString(1));
			f.setLecturername(rs.getString(2));
			f.setDept_id(rs.getString(3));
			f.setCity(rs.getString(4));
			f.setEmail(rs.getString(5));
			f.setContactnumber(rs.getString(6));
			st.close();
			return f;
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return f;
	}

	public ArrayList<Lecturer> getTotalLecturer(String condition) {
		ArrayList<Lecturer> list = new ArrayList<Lecturer>();

		String query = "select * from lecturer" + condition + " order by lecturerID asc";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				Lecturer f = new Lecturer();
				f.setLecturerid(rs.getString(1));
				f.setLecturername(rs.getString(2));
				f.setDept_id(rs.getString(3));
				f.setCity(rs.getString(4));
				f.setEmail(rs.getString(5));
				f.setContactnumber(rs.getString(6));
				list.add(f);
			}

		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return list;
	}

	public Lecturer getLecturerInfobyUserId(String lecturerID) {
		Lecturer f = new Lecturer();
		lecturerID = lecturerID.replaceAll("\\s", "");
		String query = "select * from lecturer where lecturerID=" + lecturerID;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			rs.next();
			f.setLecturerid(rs.getString(1));
			f.setLecturername(rs.getString(2));
			f.setDept_id(rs.getString(3));
			f.setCity(rs.getString(4));
			f.setEmail(rs.getString(5));
			f.setContactnumber(rs.getString(6));
			st.close();
			return f;
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return f;
	}

	public ResultSet getLecturerLecturerInfo(String condition) {
		ResultSet rs = null;
		try {
			String query = "select lecturerID as 'Lecturer ID',lecturerName as 'Lecturer Name',dept_ID as 'Class',semoryear as 'Sem/Year',lecturer as 'Lecturer',position as 'Position' from lecturer "
					+ condition + " order by lecturerID asc";
			java.sql.Statement st = con.createStatement();
			rs = st.executeQuery(query);

		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return rs;
	}

	public boolean isExist(String lecID) {
		try {
			String query = " select count(*) from lecturer where lecturerID='" + lecID + "'";

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			rs.next();
			return rs.getInt(1) == 1 ? true : false;
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return false;
	}

}
