package collegeapplication.clazz;

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
import collegeapplication.student.Student;
import collegeapplication.subject.SubjectData;

/*
 * Title : UserData.java
 * Created by : Ajaysinh Rathod
 * Purpose : Handling all the data related to clazz
 * Mail : ajaysinhrathod1290@gmail.com
 */

public class ClazzData {

	static Connection con = DataBaseConnection.getConnection();

	public static void closeConnection() throws SQLException {
		con.close();
	}

	public int addClazz(Clazz c) {
		int result = 0;
		try {
			String query = "insert into class values(?,?,?,?,?,?)";
			PreparedStatement pr = con.prepareStatement(query);
			pr.setString(1, c.getClazzID());
			pr.setString(2, c.getSubjectID());
			pr.setString(3, c.getLectureID());
			pr.setString(4, c.getRoomID());
			pr.setString(5, c.getTimeSlotID());
			pr.setInt(6, Integer.parseInt(c.getSemester()));
			result = pr.executeUpdate();
			pr.close();
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return result;
	}

	// concat(c.totalsemoryear,' ',c.semoryear) as 'Total Sem/Year'
	public ResultSet getClazzinfo() {

		ResultSet st = null;
		try {
			String query = "select c.classID as 'Class ID',concat(s.subjectID,'-',s.subjectName) as 'Subject',concat(t.start_hour,':',t.start_minute,'-',t.end_hour,':',t.end_minute,'-',t.day) as 'Time',concat(r.roomNumber,'-',r.building) as 'Classroom', c.semester as 'Semester' "
					+ "from class as c,room as r,timeslot as t,subject as s where c.roomID = r.roomID and c.timeslotID = t.timeslotID and c.subjectID = s.subjectID order by c.classID";
			PreparedStatement pr = con.prepareStatement(query);
			st = pr.executeQuery();
			return st;
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return st;
	}

	public int getTotalClazz() {
		int totalrow = 0;
		try {
			Statement pr = con.createStatement();
			ResultSet st = pr.executeQuery("select * from class");
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

	public String[] getClazzNameBySubjectCode(String code) {
		String clazzname[];
		int i = 0;
		String s = new SubjectData().getSubjectName(code);
		clazzname = new String[getTotalClazz() + 1];
		clazzname[i++] = "---" + s + "---";

		try {
			String sql = "select concat(classID,'-',start_hour,':',start_minute,'-',end_hour,':',end_minute,'-',day,'--P',roomNumber,'-',building,'-',semester)"
					+ " from class as c, room as r, timeslot as t where c.roomID = r.roomID and c.timeslotID=t.timeslotID and c.subjectID ='"
					+ code + "'";
			Statement pr = con.createStatement();
			ResultSet st = pr.executeQuery(sql);
			while (st.next()) {
				clazzname[i++] = st.getString(1);
			}
			pr.close();
			st.close();
			return clazzname;

		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return clazzname;

	}

	public int getRollTotalClazz() {
		int totalrow = 0;
		try {
			Statement pr = con.createStatement();
			ResultSet st = pr.executeQuery(
					"select clazzname from class where classID Not IN(select distinct classID from rollgenerator)");
			while (st.next()) {
				totalrow++;
			}
			pr.close();
			st.close();
			return totalrow;

		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return totalrow;
	}

	public String[] getRollClazzName() {
		String clazzname[];
		int i = 0;
		clazzname = new String[getRollTotalClazz() + 1];
		clazzname[i++] = "---select---";

		try {
			Statement pr = con.createStatement();
			ResultSet st = pr.executeQuery(
					"select clazzname from class where classID NOT IN(select distinct classID from rollgenerator)");

			while (st.next()) {
				clazzname[i++] = st.getString(1);
			}
			pr.close();
			st.close();
			return clazzname;

		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return clazzname;

	}

	public String[] getSemorYear(String Clazzname) {
		String query = "select semoryear, totalsemoryear from class where clazzname='" + Clazzname + "'";
		String totalsem[] = new String[1];
		totalsem[0] = "---Select Sem/Year---";
		if (!Clazzname.contains("--select--")) {
			try {
				Statement pr = con.createStatement();
				ResultSet st = pr.executeQuery(query);
				st.next();
				String semoryear = st.getString(1);
				int totalsemoryear = st.getInt(2);

				totalsem = new String[totalsemoryear + 1];
				if (semoryear.contains("sem")) {
					semoryear = "Semester";
				} else {
					semoryear = "Year";
				}
				totalsem[0] = "---Select " + semoryear + "---";
				for (int i = 1; i <= totalsemoryear; i++) {
					totalsem[i] = semoryear + " " + i;
				}
				pr.close();
				st.close();
				return totalsem;
			} catch (Exception exp) {
				exp.printStackTrace();
			}
		}
		return totalsem;

	}

	public String[] getClazzcode() {
		String classID[] = new String[this.getTotalClazz()];
		String query = "select classID from class";
		try {
			Statement pr = con.createStatement();
			ResultSet st = pr.executeQuery(query);
			int i = 0;
			while (st.next()) {
				classID[i++] = st.getString(1);
			}
			pr.close();
			st.close();

			return classID;

		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return classID;

	}

	public String getClazzcode(String clazzname) {
		String query = "select classID from class where clazzname='" + clazzname + "'";
		String classID = null;
		try {
			Statement pr = con.createStatement();
			ResultSet st = pr.executeQuery(query);
			st.next();
			classID = st.getString(1);
			pr.close();
			st.close();
			return classID;

		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return classID;
	}

	public String getsemoryear(String classID) {
		String query = "select semoryear from class where classID='" + classID + "'";
		String semoryear = null;
		try {
			Statement pr = con.createStatement();
			ResultSet st = pr.executeQuery(query);

			st.next();
			semoryear = st.getString(1);

			pr.close();
			st.close();
			return semoryear;

		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return semoryear;
	}

	public String getclazzname(String classID) {
		String query = "select clazzname from class where classID='" + classID + "'";
		String clazzname = null;
		try {
			Statement pr = con.createStatement();
			ResultSet st = pr.executeQuery(query);

			st.next();
			clazzname = st.getString(1);

			pr.close();
			st.close();
			return clazzname;

		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return clazzname;
	}

	public int getTotalsemoryear(String clazzname) {
		String query = "select totalsemoryear from class where clazzname='" + clazzname + "'";
		int totalsemoryear = 0;
		try {
			Statement pr = con.createStatement();
			ResultSet st = pr.executeQuery(query);
			while (st.next()) {
				totalsemoryear = st.getInt(1);
			}
			pr.close();
			st.close();

			return totalsemoryear;

		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return totalsemoryear;
	}

	public boolean isClazzCodeExist(String classID) {
		try {

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select count(*) from class where classID='" + classID + "'");
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

	public boolean isClazzNameExist(String clazzname) {
		try {

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select count(*) from class where clazzname='" + clazzname + "'");
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

	public boolean isDeclared(String classID, int semoryear) {
		boolean isdeclared = false;
		try {
			String query = "select isdeclared from result where classID='" + classID + "' and semoryear="
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

	public int getID(String courseName) {
		int result = 0;
		try {
			String query = "select Clazzcode from class where ClazzName=" + courseName;
			PreparedStatement pr = con.prepareStatement(query);
			result = pr.executeUpdate();
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return result;
	}

	// --------------ROOM-----------------
	public int getTotalRoom() {
		int totalrow = 0;
		try {
			Statement pr = con.createStatement();
			ResultSet st = pr.executeQuery("select * from room");
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

	public String[] getRoomName() {
		String roomname[];
		int i = 0;
		roomname = new String[getTotalRoom() + 1];
		roomname[i++] = "---Select Room---";

		try {
			Statement pr = con.createStatement();
			ResultSet st = pr.executeQuery("select concat(building,'-',roomNumber) from room");

			while (st.next()) {
				roomname[i++] = st.getString(1);
			}
			pr.close();
			st.close();
			return roomname;

		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return roomname;

	}

	// ---------------TimeSlot---------------
	public int getTotalTimeSlot() {
		int totalrow = 0;
		try {
			Statement pr = con.createStatement();
			ResultSet st = pr.executeQuery("select * from timeslot");
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

	public String[] getTimeSlotName() {
		String timeslotname[];
		int i = 0;
		timeslotname = new String[getTotalTimeSlot() + 1];
		timeslotname[i++] = "---Select TimeSlot---";

		try {
			Statement pr = con.createStatement();
			ResultSet st = pr.executeQuery(
					"select concat(start_hour,':',start_minute,'-',end_hour,':',end_minute,'-',day) from timeslot");

			while (st.next()) {
				timeslotname[i++] = st.getString(1);
			}
			pr.close();
			st.close();
			return timeslotname;

		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return timeslotname;

	}

	// add manage student
	public int addManageStudent(String studentID, String classID) {
		int result = 0;
		try {
			String query = "insert into managestudent(studentID,classID) values(?,?)";
			PreparedStatement pr = con.prepareStatement(query);
			pr.setString(1, studentID);
			pr.setString(2, classID);
			// pr.setString(3, );
			// pr.setString(4, );
			result = pr.executeUpdate();
			pr.close();
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return result;
	}

	// update manage student
	public int updateManageStudent(Student s, String classID, String grade, Float mark, int cre) {
		int result = 0;
		try {
			String query = "update managestudent set grade=?,mark=? where studentID=? and classID=?";
			PreparedStatement pr1 = con.prepareStatement(query);
			pr1.setString(1, grade);
			pr1.setFloat(2, mark);
			pr1.setString(3, s.getStudentID());
			pr1.setString(4, classID);
			result = pr1.executeUpdate();

			Float cpa = Float.parseFloat(s.getCpa());
			Integer totalcre = Integer.parseInt(s.getTotalCredits());
			cpa = (cpa * totalcre + cre * mark) / (totalcre + cre);
			totalcre += cre;
			String query2 = "update student set CPA=?, total_cred=? where studentID=?";
			PreparedStatement pr2 = con.prepareStatement(query2);
			pr2.setFloat(1, cpa);
			pr2.setInt(2, totalcre);
			pr2.setString(3, s.getStudentID());
			result = pr2.executeUpdate();
			pr1.close();
			pr2.close();
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return result;
	}

	// check managestudent
	public boolean ManageStudentIsExist(String studentID, String classID) {
		try {
			String query = " select count(*) from managestudent where studentID='" + studentID + "' and classID ='"
					+ classID + "'";

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
