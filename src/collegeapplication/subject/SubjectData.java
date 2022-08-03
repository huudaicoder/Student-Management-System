package collegeapplication.subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import collegeapplication.common.DataBaseConnection;
import collegeapplication.common.Notification;
import collegeapplication.common.TimeUtil;

/*
 * Title : SubjectData.java
 * Created by : Ajaysinh Rathod
 * Purpose : Handling all the data related to subject
 * Mail : ajaysinhrathod1290@gmail.com
 */

public class SubjectData {

	static Connection con = DataBaseConnection.getConnection();

	public static void closeConnection() throws SQLException {
		con.close();
	}

	public boolean isExist(String ID) {
		try {
			String query = " select count(*) from subject where subjectID'" + ID + "'";

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			rs.next();
			return rs.getInt(1) == 1 ? true : false;
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return false;
	}

	public int getTotalSubject() {
		int totalsubject = 0;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from subject");
			while (rs.next()) {
				totalsubject++;
			}
			rs.close();
			st.close();
			return totalsubject;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalsubject;
	}

	public int addSubject(Subject su) {
		String query = "insert into subject values (?,?,?,?,?)";
		int result = 0;
		try {

			PreparedStatement pr = con.prepareStatement(query);
			pr.setString(1, su.getSubjectID());
			pr.setString(2, su.getDept_id());
			pr.setString(3, su.getSubjectName());
			pr.setFloat(4, su.getFactor());
			pr.setInt(5, su.getCredit());
			result = pr.executeUpdate();
			pr.close();
			return result;
		} catch (Exception exp) {

			exp.printStackTrace();
		}
		return result;
	}

	public String getSubjectName(String subjectID) {
		String subjectName = null;
		try {
			String query = "select subjectName from subject where subjectID='" + subjectID + "'";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			rs.next();
			subjectName = rs.getString(1);
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return subjectName;
	}

	public ResultSet getSubjectinfo(String dept_ID) {

		ResultSet st = null;
		try {
			String query = "select subjectID as 'Subject Code',subjectName as 'Subject Name',factor as 'Factor', credits as 'Credit' from subject where dept_ID='"
					+ dept_ID + "'";
			PreparedStatement pr = con.prepareStatement(query);
			st = pr.executeQuery();
			return st;
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return st;
	}

	public String[] getSubjectinDepartment(String dept_ID) {
		int totalsubjectindepartment = this.getTotalSubjectinDepartment(dept_ID);
		if (totalsubjectindepartment > 0) {
			String[] subject = new String[totalsubjectindepartment + 1];
			subject[0] = "---Select Subject---";
			String query = "select subjectName from subject where dept_ID='" + dept_ID + "'";
			try {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(query);
				int i = 1;
				while (rs.next()) {
					subject[i++] = rs.getString(1);
				}
				rs.close();
				st.close();
				return subject;

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

		}
		return null;
	}

	public int getTotalSubjectinDepartment(String dept_ID) {
		int totalsubjectindepartment = 0;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(
					"select count(*) from subject where dept_ID='" + dept_ID + "'");
			rs.next();
			totalsubjectindepartment = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalsubjectindepartment;
	}

	public String getSubjectCode(String subjectName) {
		String subcode = null;
		String query = "select subjectID from subject where subjectName='" + subjectName + "'";
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

	public String[] getSubjectName() {
		String subjectname[];
		int i = 0;
		subjectname = new String[getTotalSubject() + 1];
		subjectname[i++] = "---Select Subject---";

		try {
			Statement pr = con.createStatement();
			ResultSet st = pr.executeQuery("select subjectName from subject");

			while (st.next()) {
				subjectname[i++] = st.getString(1);
			}
			pr.close();
			st.close();
			return subjectname;

		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return subjectname;

	}

	public int getSubjectCre(String classID) {
		int cre;
		String query = "select credits from subject where subjectID= (select subjectID from class where classID ='"
				+ classID + "')";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			rs.next();
			cre = rs.getInt(1);

		} catch (Exception exp) {
			return 0;
		}

		return cre;

	}
}
