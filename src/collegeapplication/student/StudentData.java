package collegeapplication.student;

import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import collegeapplication.common.DataBaseConnection;
import collegeapplication.common.Notification;
import collegeapplication.common.TimeUtil;
import collegeapplication.subject.SubjectData;

/*
 * Title : StudentData.java
 * Created by : Ajaysinh Rathod
 * Purpose : Handling all the data related to student
 * Mail : ajaysinhrathod1290@gmail.com
 */

public class StudentData {

	static Connection con = DataBaseConnection.getConnection();

	public static void closeConnection() throws SQLException {
		con.close();
	}

	public int addStudent(Student s) {
		int result = 0;
		String query = "insert into student values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement pr = con.prepareStatement(query);
			pr.setString(1, s.getStudentID());
			pr.setString(3, s.getStudentName());
			pr.setString(2, s.getDept_id());
			pr.setString(4, s.getBirthDate());
			pr.setString(5, s.getGender());
			pr.setString(6, s.getCpa());
			pr.setString(7, s.getTotalCredits());
			result = pr.executeUpdate();
			pr.close();
			return result;

		}

		catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public int deleteStudent(String studentID) {
		int result = 0;
		String query = "delete from student where studentID = '" + studentID + "'";
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

	public int updateStudentData(Student sold, Student s) {
		int result = 0;
		String query = "update student set studentID=?,dept_ID=?,studentName=?,DOB=?,gender=?,CPA=?,total_cred=? where studentID='"
				+ sold.getStudentID() + "'";
		try {

			PreparedStatement pr = con.prepareStatement(query);
			pr.setString(1, s.getStudentID());
			pr.setString(3, s.getStudentName());
			pr.setString(2, s.getDept_id());
			pr.setString(4, s.getBirthDate());
			pr.setString(5, s.getGender());
			pr.setString(6, s.getCpa());
			pr.setString(7, s.getTotalCredits());
			result = pr.executeUpdate();
			pr.close();
			return result;

		}

		catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public int getTotalStudentInCource(String Courcecode) {
		int studentID = 0;

		String query = "select studentID from student where dept_ID='" + Courcecode + "'";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				studentID++;
			}
			st.close();
			rs.close();
			return studentID;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return studentID;
	}

	public ResultSet getStudentinfo(String condition) {
		ResultSet rs = null;
		String query = "select s.studentID as 'Student ID',s.studentName as 'Full name', s.DOB as 'DOB',s.gender as 'Gender',(select dept_Name from department where dept_ID=s.dept_ID) as 'Department' from student s "
				+ condition + " order by s.studentID asc";

		try {
			Statement st = con.createStatement();
			rs = st.executeQuery(query);
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	public ResultSet searchStudent(String query) {

		query += " order by s.dept_ID asc,s.semoryear asc,s.studentID asc";
		ResultSet rs = null;
		try {
			Statement st = con.createStatement();
			rs = st.executeQuery(query);
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	public Student getStudentDetails(Long studentID) {
		Student s = new Student();

		String query = " select * from student where studentID=" + studentID;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			rs.next();
			s.setStudentID(rs.getString(1));
			s.setStudentName(rs.getString(3));
			s.setDept_id(rs.getString(2));
			s.setBirthDate(rs.getString(4));
			s.setGender(rs.getString(5));
			s.setCpa(rs.getString(6));
			s.setTotalCredits(rs.getString(7));
			return s;
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	// public ArrayList<Marks> getStudentTheoryMarksDetails(String Courcecode, int
	// sem, String subjectname) {
	// ResultSet rs = null;
	// ArrayList<Marks> marks = new ArrayList<Marks>();
	// String subjectcode = new SubjectData().getSubjectCode(Courcecode, sem,
	// subjectname);
	// String query = "select distinct
	// s.studentName,s.studentID,subject.subjectname,subject.theorymarks,m.theorymarks
	// from student s left join marks m on s.studentID=m.studentID and
	// m.subjectcode='"
	// + subjectcode + "',subject where s.dept_ID='" + Courcecode + "' and
	// s.semoryear=" + sem
	// + " and subject.subjectcode='" + subjectcode + "' order by s.studentID asc";
	// String subjecttype = new SubjectData().checkCoreorOptional(subjectcode);
	// if (!subjecttype.equals("core")) {
	// query = "select distinct
	// s.studentName,s.studentID,subject.subjectname,subject.theorymarks,m.theorymarks
	// from student s left join marks m on s.studentID=m.studentID and
	// m.subjectcode='"
	// + subjectcode + "',subject where s.optionalsubject=subject.subjectname and
	// s.dept_ID='"
	// + Courcecode + "' and s.semoryear=" + sem + " and subject.subjectcode='" +
	// subjectcode
	// + "' order by s.studentID asc";

	// }
	// try {
	// Statement st = con.createStatement();
	// rs = st.executeQuery(query);
	// while (rs.next()) {
	// Marks m = new Marks();
	// m.setRollNumber(rs.getLong(2));
	// m.setStudentName(rs.getString(1));
	// m.setSubjectName(rs.getString(3));
	// m.setMaxTheoryMarks(rs.getInt(4));
	// m.setTheoryMarks(rs.getInt(5));
	// marks.add(m);

	// }
	// st.close();
	// }

	// catch (Exception e) {
	// e.printStackTrace();
	// }
	// return marks;
	// }

	// public ArrayList<Marks> getStudentPracticalMarksDetails(String Courcecode,
	// int sem, String subjectname) {
	// ResultSet rs = null;
	// ArrayList<Marks> marks = new ArrayList<Marks>();
	// String subjectcode = new SubjectData().getSubjectCode(Courcecode, sem,
	// subjectname);
	// String query = "select distinct
	// s.studentName,s.studentID,subject.subjectname,subject.practicalmarks,m.practicalmarks
	// from student s left join marks m on s.studentID=m.studentID and
	// m.subjectcode='"
	// + subjectcode + "',subject where s.dept_ID='" + Courcecode + "' and
	// s.semoryear=" + sem
	// + " and subject.subjectcode='" + subjectcode + "' order by s.studentID asc";
	// String subjecttype = new SubjectData().checkCoreorOptional(subjectcode);
	// if (!subjecttype.equals("core")) {
	// query = "select distinct
	// s.studentName,s.studentID,subject.subjectname,subject.practicalmarks,m.practicalmarks
	// from student s left join marks m on s.studentID=m.studentID and
	// m.subjectcode='"
	// + subjectcode + "',subject where s.optionalsubject=subject.subjectname and
	// s.dept_ID='"
	// + Courcecode + "' and s.semoryear=" + sem + " and subject.subjectcode='" +
	// subjectcode
	// + "' order by s.studentID asc";

	// }
	// try {
	// Statement st = con.createStatement();
	// rs = st.executeQuery(query);
	// while (rs.next()) {
	// Marks m = new Marks();
	// m.setRollNumber(rs.getLong(2));
	// m.setStudentName(rs.getString(1));
	// m.setSubjectName(rs.getString(3));
	// m.setMaxPracticalMarks(rs.getInt(4));
	// m.setPracticalMarks(rs.getInt(5));
	// marks.add(m);

	// }
	// st.close();
	// }

	// catch (Exception e) {
	// e.printStackTrace();
	// }
	// return marks;
	// }

	public Student getStudentDetails(int row) {
		Student s = new Student();
		String query = "select * from student where sr_no=" + row;
		try {

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			rs.next();
			s.setStudentID(rs.getString(1));
			s.setStudentName(rs.getString(2));
			s.setDept_id(rs.getString(3));
			s.setBirthDate(rs.getString(4));
			s.setGender(rs.getString(5));
			s.setCpa(rs.getString(6));
			s.setTotalCredits(rs.getString(7));
			return s;

		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	public Student getStudentDetailsByUserId(String userid) {
		Student s = new Student();
		String query = "select * from student where studentID='" + userid + "'";
		try {

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			rs.next();
			s.setStudentID(rs.getString(1));
			s.setStudentName(rs.getString(2));
			s.setDept_id(rs.getString(3));
			s.setBirthDate(rs.getString(4));
			s.setGender(rs.getString(5));
			s.setCpa(rs.getString(6));
			s.setTotalCredits(rs.getString(7));
			return s;

		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	public int getTotalStudents() {
		int totalstudent = 0;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from student");
			while (rs.next()) {
				totalstudent++;
			}
			rs.close();
			st.close();
			return totalstudent;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalstudent;
	}

	public ResultSet getMarkSheet(String studentID, String condition) {
		ResultSet rs = null;
		String query = "select ms.studentID as 'Student ID', c.subjectID as 'Subject Code',s.subjectName as 'Subject Name', ms.mark as 'Mark (/4)',"
				+
				" ms.grade as 'Grade', c.semester as 'Semester' from managestudent as ms, class as c,subject as s where ms.classID = c.classID and c.subjectID = s.subjectID and studentID='"
				+ studentID + "' order by " + condition + " desc";

		try {
			Statement st = con.createStatement();
			rs = st.executeQuery(query);
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;

	}

	public boolean isExist(String studentID) {
		try {
			String query = " select count(*) from student where studentID='" + studentID + "'";

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			rs.next();
			return rs.getInt(1) == 1 ? true : false;
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return false;
	}

	// public ArrayList<Marks> getMarksheetReportBySubject(Marks marks) {
	// ArrayList<Marks> list = new ArrayList<Marks>();
	// try {
	// String query = "select distinct s.studentID,concat(s.studentName,'
	// ',s.dept_ID) as 'Student Name'"
	// + ",(select theorymarks from marks where "
	// + "subjectcode='" + marks.getSubjectCode()
	// + "' and studentID=s.studentID) as 'Theory'"
	// + ",(select practicalmarks from marks where"
	// + " subjectcode='" + marks.getSubjectCode()
	// + "' and studentID=s.studentID) as 'Practical',"
	// + "(select theorymarks from subject where subjectcode='" +
	// marks.getSubjectCode()
	// + "') as 'Total theory',(select practicalmarks from subject where
	// subjectcode='"
	// + marks.getSubjectCode()
	// + "') as 'Total Practical' from student s left join marks m on
	// s.studentID=m.studentID where s.dept_ID='"
	// + marks.getCourceCode()
	// + "' and s.semoryear=" + marks.getSemorYear();
	// String subjecttype = new
	// SubjectData().checkCoreorOptional(marks.getSubjectCode());
	// if (!subjecttype.equals("core")) {
	// query += " and s.optionalsubject='" + marks.getSubjectName() + "'";
	// }
	// query += " order by s.studentID asc";

	// Statement st = con.createStatement();
	// ResultSet rs = st.executeQuery(query);
	// while (rs.next()) {
	// Marks m = new Marks();
	// m.setRollNumber(rs.getLong(1));
	// m.setStudentName(rs.getString(2));
	// m.setSubjectName(marks.getSubjectName());
	// m.setTheoryMarks(rs.getInt(3));
	// m.setPracticalMarks(rs.getInt(4));
	// m.setMaxTheoryMarks(rs.getInt(5));
	// m.setMaxPracticalMarks(rs.getInt(6));

	// list.add(m);
	// }

	// } catch (Exception exp) {
	// exp.printStackTrace();
	// }
	// return list;

	// }

	// public ArrayList<Marks> getMarksheetReportByClass(Marks marks) {
	// ArrayList<Marks> list = new ArrayList<Marks>();
	// try {
	// String query = "select distinct s.studentID,concat(s.studentName,'
	// ',s.dept_ID) as 'Student Name',(select sum(theorymarks) from marks where
	// dept_ID=s.dept_ID and semoryear=s.semoryear and
	// studentID=s.studentID) as 'Theory',(select sum(practicalmarks) from marks
	// where dept_ID=s.dept_ID and semoryear=s.semoryear and
	// studentID=s.studentID) as 'Practical',(select sum(theorymarks) from subject
	// where dept_ID=s.dept_ID and semoryear=s.semoryear and
	// subjecttype='core') as 'Total theory',(select sum(practicalmarks) from
	// subject where dept_ID=s.dept_ID and semoryear=s.semoryear and
	// subjecttype='core' ) as 'Total Practical' from student s left join marks m
	// on s.studentID=m.studentID where s.dept_ID='"
	// + marks.getCourceCode() + "' and s.semoryear=" + marks.getSemorYear();
	// Statement st = con.createStatement();
	// ResultSet rs = st.executeQuery(query);
	// while (rs.next()) {
	// Marks m = new Marks();
	// String subjectcode = this.getOptionalSubjectCode(marks.getCourceCode(),
	// marks.getSemorYear(),
	// rs.getLong(1));
	// int maxpracticalmarks = new
	// SubjectData().getMaxTheoryMarksOfSubject(subjectcode);
	// int maxtheorymarks = new
	// SubjectData().getMaxPracticalMarksOfSubject(subjectcode);

	// m.setRollNumber(rs.getLong(1));
	// m.setStudentName(rs.getString(2));
	// m.setSubjectName(marks.getSubjectName());
	// m.setTheoryMarks(rs.getInt(3));
	// m.setPracticalMarks(rs.getInt(4));
	// m.setMaxTheoryMarks(rs.getInt(5) + maxtheorymarks);
	// m.setMaxPracticalMarks(rs.getInt(6) + maxpracticalmarks);

	// list.add(m);
	// }

	// } catch (Exception exp) {
	// exp.printStackTrace();
	// }
	// return list;

	// }

	public boolean checkPassword(String userid, String password) {
		boolean result = false;
		try {
			String query = "select count(*) from student where userid='" + userid + "' and password='" + password
					+ "'";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			rs.next();
			if (rs.getInt(1) > 0) {
				result = true;
			} else {
				JOptionPane.showMessageDialog(null, "Incorrect User-Id or Password", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return result;
	}

	public ArrayList<Student> getStudentsDetails(String condition) {
		ArrayList<Student> list = new ArrayList<Student>();
		String query = "select * from student s " + condition
				+ " order by s.dept_ID asc,s.semoryear asc,s.studentID asc";
		try {

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				Student s = new Student();
				s.setStudentID(rs.getString(1));
				s.setStudentName(rs.getString(2));
				s.setDept_id(rs.getString(3));
				s.setBirthDate(rs.getString(4));
				s.setGender(rs.getString(5));
				s.setCpa(rs.getString(6));
				s.setTotalCredits(rs.getString(7));
				list.add(s);
			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean isActive(String userid) {
		try {
			String query = "select activestatus from student where userid='" + userid + "'";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			rs.next();
			boolean active = rs.getBoolean(1);
			st.close();
			rs.close();
			return active;
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return false;
	}

	public String getStudentName(String userid) {
		String name = "";
		try {
			String query = "select concat(studentName,' ',dept_ID) from student where userid='" + userid + "'";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			rs.next();
			name = rs.getString(1);

			rs.close();
			st.close();

		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return name;
	}

	public int setActiveStatus(boolean activestatus, String userid) {
		int result = 0;
		try {
			String query = "update student set activestatus=" + activestatus + " where userid='" + userid + "'";
			PreparedStatement pr = con.prepareStatement(query);
			result = pr.executeUpdate();
			pr.close();
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return result;
	}

	public String getLastLogin(String userid) {
		try {
			String query = "select lastlogin from student where userid='" + userid + "'";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			rs.next();
			return rs.getString(1);
		} catch (Exception exp) {
			exp.printStackTrace();
			return "deleted";
		}
	}

	public Image getProfilePic(String userid) {
		Image image = null;
		try {
			String query = "select profilepic from student where userid='" + userid + "'";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			rs.next();
			byte[] imagedata = rs.getBytes(1);
			image = Toolkit.getDefaultToolkit().createImage(imagedata);
			rs.close();
			st.close();
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return image;
	}

	public int changePassword(String userid, String password) {
		try {
			String query = "update student set password='" + password + "' where userid='" + userid + "'";
			PreparedStatement pr = con.prepareStatement(query);
			return pr.executeUpdate();
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		return 0;
	}

}
