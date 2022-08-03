package collegeapplication.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import collegeapplication.admin.Admin;
import collegeapplication.department.DepartmentData;
import collegeapplication.lecturer.Lecturer;
import collegeapplication.lecturer.LecturerData;
import collegeapplication.student.Student;
import collegeapplication.student.StudentData;
import collegeapplication.subject.SubjectData;

/*
 * Title : HomePanel.java
 * Created by : Ajaysinh Rathod
 * Purpose : Home Page
 * Mail : ajaysinhrathod1290@gmail.com
 */

@SuppressWarnings("serial")
public class HomePanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private JPanel homeheaderpanel;
	private JLabel totalstudentlabel, totalfaculitieslabel, totaldepartmentlabel, totallectureslabel;
	public JLabel lastloginlabel;
	private JLabel timedifflabel;
	private JLabel welcomelabel;
	private JPanel departmentspanel;
	private JPanel faculitiespanel;
	private JPanel studentspanel;
	int pos[] = { 20, 294, 568, 842 };
	private JPanel subjectpanel;

	/**
	 * Create the panel.
	 */
	private HomePanel() {
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBackground(Color.WHITE);
		setForeground(Color.WHITE);
		this.setSize(1116, 705);
		setLayout(null);

		departmentspanel = new JPanel();
		departmentspanel.setBorder(new LineBorder(new Color(192, 192, 192), 3));
		departmentspanel.setBounds(20, 244, 253, 247);
		add(departmentspanel);
		departmentspanel.setBackground(new Color(255, 255, 255));
		departmentspanel.setLayout(null);

		totaldepartmentlabel = new JLabel("0");
		totaldepartmentlabel.setForeground(new Color(128, 128, 128));
		totaldepartmentlabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		totaldepartmentlabel.setHorizontalAlignment(SwingConstants.CENTER);
		totaldepartmentlabel.setBounds(10, 174, 233, 35);
		departmentspanel.add(totaldepartmentlabel);

		JLabel lblDepartments = new JLabel("Departments");
		lblDepartments.setFont(new Font("Segoe UI", Font.BOLD, 25));
		lblDepartments.setForeground(new Color(128, 128, 128));
		lblDepartments.setHorizontalAlignment(SwingConstants.CENTER);
		lblDepartments.setHorizontalTextPosition(JLabel.CENTER);
		lblDepartments.setVerticalTextPosition(JLabel.BOTTOM);
		lblDepartments.setBounds(10, 31, 233, 142);
		departmentspanel.add(lblDepartments);
		lblDepartments.setIcon(new ImageIcon(".//assets//departmentshomepage.png"));

		studentspanel = new JPanel();
		studentspanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		studentspanel.setLayout(null);
		studentspanel.setBackground(Color.WHITE);
		studentspanel.setBounds(294, 244, 253, 247);
		add(studentspanel);

		totalstudentlabel = new JLabel("0");
		totalstudentlabel.setText(new StudentData().getTotalStudents() + "");
		totalstudentlabel.setHorizontalAlignment(SwingConstants.CENTER);
		totalstudentlabel.setForeground(Color.GRAY);

		totalstudentlabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		totalstudentlabel.setBounds(10, 174, 233, 35);
		studentspanel.add(totalstudentlabel);

		JLabel lblStudents = new JLabel("Students");
		lblStudents.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudents.setForeground(Color.GRAY);
		lblStudents.setIcon(null);
		lblStudents.setFont(new Font("Segoe UI", Font.BOLD, 25));
		lblStudents.setBounds(10, 32, 233, 144);
		lblStudents.setHorizontalTextPosition(JLabel.CENTER);
		lblStudents.setVerticalTextPosition(JLabel.BOTTOM);
		studentspanel.add(lblStudents);
		lblStudents.setIcon(new ImageIcon(".//assets//studenthomepage.png"));

		faculitiespanel = new JPanel();
		faculitiespanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		faculitiespanel.setLayout(null);
		faculitiespanel.setBackground(Color.WHITE);
		faculitiespanel.setBounds(568, 244, 253, 247);
		add(faculitiespanel);

		totalfaculitieslabel = new JLabel("0");
		totalfaculitieslabel.setBackground(Color.WHITE);
		totalfaculitieslabel.setHorizontalAlignment(SwingConstants.CENTER);
		totalfaculitieslabel.setForeground(Color.GRAY);
		totalfaculitieslabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		totalfaculitieslabel.setBounds(10, 174, 233, 35);
		faculitiespanel.add(totalfaculitieslabel);

		JLabel lblFaculities = new JLabel("Lecturers");
		lblFaculities.setHorizontalAlignment(SwingConstants.CENTER);
		lblFaculities.setForeground(Color.GRAY);
		lblFaculities.setFont(new Font("Segoe UI", Font.BOLD, 25));
		lblFaculities.setBounds(10, 34, 233, 140);
		lblFaculities.setHorizontalTextPosition(JLabel.CENTER);
		lblFaculities.setVerticalTextPosition(JLabel.BOTTOM);
		faculitiespanel.add(lblFaculities);
		lblFaculities.setIcon(new ImageIcon(".//assets//facultyhomepage.png"));

		subjectpanel = new JPanel();
		subjectpanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		subjectpanel.setLayout(null);
		subjectpanel.setBackground(Color.WHITE);
		subjectpanel.setBounds(842, 244, 253, 247);
		add(subjectpanel);

		totallectureslabel = new JLabel("0");

		totallectureslabel.setHorizontalAlignment(SwingConstants.CENTER);
		totallectureslabel.setForeground(Color.GRAY);
		totallectureslabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		totallectureslabel.setBounds(10, 174, 233, 35);
		subjectpanel.add(totallectureslabel);

		JLabel lblLectures = new JLabel("Subjects");
		lblLectures.setHorizontalAlignment(SwingConstants.CENTER);
		lblLectures.setForeground(Color.GRAY);
		lblLectures.setFont(new Font("Segoe UI", Font.BOLD, 25));
		lblLectures.setBounds(10, 40, 233, 141);
		lblLectures.setIconTextGap(10);
		lblLectures.setHorizontalTextPosition(JLabel.CENTER);
		lblLectures.setVerticalTextPosition(JLabel.BOTTOM);
		subjectpanel.add(lblLectures);
		try {
			Image image = ImageIO.read(new File(".//assets//subjects2.png"));
			lblLectures.setIcon(new ImageIcon(image.getScaledInstance(85, 85, Image.SCALE_SMOOTH)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		homeheaderpanel = new JPanel();
		homeheaderpanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		homeheaderpanel.setBackground(new Color(32, 178, 170));
		homeheaderpanel.setLayout(null);
		homeheaderpanel.setBounds(10, 0, 1096, 279);
		add(homeheaderpanel);

		welcomelabel = new JLabel("Welcome");
		welcomelabel.setHorizontalAlignment(SwingConstants.RIGHT);
		welcomelabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
		welcomelabel.setForeground(Color.WHITE);
		welcomelabel.setBounds(10, 11, 1076, 45);
		homeheaderpanel.add(welcomelabel);

		JLabel lblHome = new JLabel("Home Page");
		lblHome.setIcon(null);
		lblHome.setForeground(Color.WHITE);
		lblHome.setFont(new Font("Segoe UI", Font.BOLD, 29));
		lblHome.setBounds(10, 97, 377, 45);
		homeheaderpanel.add(lblHome);

		lastloginlabel = new JLabel("Last Login : First Login");
		lastloginlabel.setBackground(Color.WHITE);
		lastloginlabel.setForeground(Color.WHITE);
		lastloginlabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lastloginlabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lastloginlabel.setBounds(20, 47, 1066, 30);
		homeheaderpanel.add(lastloginlabel);

		timedifflabel = new JLabel("");
		timedifflabel.setHorizontalAlignment(SwingConstants.RIGHT);
		timedifflabel.setForeground(Color.WHITE);
		timedifflabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		timedifflabel.setBackground(Color.WHITE);
		timedifflabel.setBounds(599, 75, 486, 19);
		homeheaderpanel.add(timedifflabel);

	}

	public HomePanel(Admin a) {
		this();
		totalfaculitieslabel.setText(new LecturerData().getTotalLecturer() + "");
		totalstudentlabel.setText(new StudentData().getTotalStudents() + "");
		totaldepartmentlabel.setText(new DepartmentData().getTotalDepartment() + "");
		welcomelabel.setText("Welcome Adminstrator");
		totallectureslabel.setText(new SubjectData().getTotalSubject() + "");
	}

	public void setLastLogin(String lastlogin) {
		if (lastlogin == null || lastlogin.isEmpty()) {
			this.lastloginlabel.setText("last login : First Time");
		} else {
			this.lastloginlabel.setText("last login : " + lastlogin);
			// this.timedifflabel.setText(TimeUtil.getDateDifference(lastlogin));
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
