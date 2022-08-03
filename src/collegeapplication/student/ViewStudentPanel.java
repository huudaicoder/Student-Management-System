package collegeapplication.student;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import collegeapplication.admin.AdminMain;
import collegeapplication.common.ChangePasswordDialog;
import collegeapplication.department.DepartmentData;

/*
 * Title : ViewStudentPanel.java
 * Created by : Ajaysinh Rathod
 * Purpose : Displaying all the details of student
 * Mail : ajaysinhrathod1290@gmail.com
 */

@SuppressWarnings("serial")
public class ViewStudentPanel extends JPanel {

	private JButton editdetailsbutton;
	public JComponent lastpanel;
	AdminMain am;
	private JButton backbutton;
	private JButton deletebutton;
	private JLabel lblLastLogin;
	private JButton viewmarksheet;
	static Student s1;

	public static void main(String[] args) {
		try {
			ViewStudentPanel vsp = new ViewStudentPanel(s1);
			vsp.setVisible(true);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Create the panel.
	 * 
	 * @wbp.parser.constructor
	 */
	private ViewStudentPanel(Student s) {

		setBackground(new Color(255, 255, 255));
		this.setSize(1116, 705);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setBounds(10, 0, 1095, 188);
		add(panel);
		panel.setLayout(null);

		// JLabel lblDisplayingStudentDetails = new JLabel(s.getFullName() + " (" +
		// s.getUserId() + ")");
		// lblDisplayingStudentDetails.setHorizontalAlignment(SwingConstants.RIGHT);
		// lblDisplayingStudentDetails.setForeground(new Color(255, 255, 255));
		// lblDisplayingStudentDetails.setFont(new Font("Segoe UI", Font.BOLD, 30));
		// lblDisplayingStudentDetails.setBounds(0, 11, 1067, 44);
		// panel.add(lblDisplayingStudentDetails);

		editdetailsbutton = new JButton("Edit Details");
		editdetailsbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		editdetailsbutton.setFocusable(false);
		editdetailsbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		editdetailsbutton.setForeground(new Color(0, 139, 139));
		editdetailsbutton.setBackground(new Color(255, 255, 255));
		editdetailsbutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		editdetailsbutton.setBounds(932, 141, 140, 35);
		panel.add(editdetailsbutton);

		deletebutton = new JButton("Delete Student");
		deletebutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		deletebutton.setFocusable(false);
		deletebutton.setForeground(new Color(0, 139, 139));
		deletebutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		deletebutton.setBackground(new Color(255, 255, 255));
		deletebutton.setBounds(782, 141, 140, 35);
		deletebutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel.add(deletebutton);

		backbutton = new JButton("Back");
		backbutton.setContentAreaFilled(false);
		backbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		backbutton.setIcon(new ImageIcon(".\\assets\\back.png"));
		backbutton.setFocusable(false);
		backbutton.setForeground(Color.WHITE);
		backbutton.setFont(new Font("Segoe UI", Font.BOLD, 16));
		backbutton.setBackground(new Color(32, 178, 170));
		backbutton.setBounds(10, 141, 88, 36);
		panel.add(backbutton);

		viewmarksheet = new JButton("View Mark Sheet");
		viewmarksheet.setBorder(new EmptyBorder(0, 0, 0, 0));
		viewmarksheet.setFocusable(false);
		viewmarksheet.setFont(new Font("Segoe UI", Font.BOLD, 16));
		viewmarksheet.setForeground(new Color(0, 139, 139));
		viewmarksheet.setBackground(new Color(255, 255, 255));
		viewmarksheet.setBounds(650, 141, 120, 35);
		viewmarksheet.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel.add(viewmarksheet);

		JLabel lblStudentDetails = new JLabel("Student Details");
		lblStudentDetails.setHorizontalAlignment(SwingConstants.LEFT);
		lblStudentDetails.setForeground(Color.WHITE);
		lblStudentDetails.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblStudentDetails.setBounds(10, 65, 415, 44);
		panel.add(lblStudentDetails);

		JLabel lblRollNo = new JLabel("Student ID");
		lblRollNo.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblRollNo.setBackground(new Color(255, 255, 255));
		lblRollNo.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lblRollNo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRollNo.setOpaque(true);
		lblRollNo.setBounds(309, 66 + 150, 274, 48);
		add(lblRollNo);

		JLabel lblStudentName = new JLabel("Student Name  ");
		lblStudentName.setOpaque(true);
		lblStudentName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStudentName.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lblStudentName.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblStudentName.setBackground(Color.WHITE);
		lblStudentName.setBounds(309, 113 + 150, 274, 48);
		add(lblStudentName);

		JLabel lblAddress = new JLabel("Gender  ");
		lblAddress.setOpaque(true);
		lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAddress.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lblAddress.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblAddress.setBackground(Color.WHITE);
		lblAddress.setBounds(309, 160 + 150, 274, 48);
		add(lblAddress);

		JLabel lblEmailId = new JLabel("CPA  ");
		lblEmailId.setOpaque(true);
		lblEmailId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmailId.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lblEmailId.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblEmailId.setBackground(Color.WHITE);
		lblEmailId.setBounds(309, 207 + 150, 274, 48);
		add(lblEmailId);

		JLabel lblDateOfBirth = new JLabel("Date Of Birth ");
		lblDateOfBirth.setOpaque(true);
		lblDateOfBirth.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDateOfBirth.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lblDateOfBirth.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblDateOfBirth.setBackground(Color.WHITE);
		lblDateOfBirth.setBounds(309, 254 + 150, 274, 48);
		add(lblDateOfBirth);

		JLabel lblContactNumber = new JLabel("Total credits ");
		lblContactNumber.setOpaque(true);
		lblContactNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContactNumber.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lblContactNumber.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblContactNumber.setBackground(Color.WHITE);
		lblContactNumber.setBounds(309, 300 + 150, 274, 48);
		add(lblContactNumber);

		JLabel lblDepartment = new JLabel("Department  ");
		lblDepartment.setOpaque(true);
		lblDepartment.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDepartment.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lblDepartment.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblDepartment.setBackground(Color.WHITE);
		lblDepartment.setBounds(309, 348 + 150, 274, 48);
		add(lblDepartment);

		JLabel rollnumberlabel = new JLabel("  " + s.getStudentID());
		rollnumberlabel.setOpaque(true);
		rollnumberlabel.setHorizontalAlignment(SwingConstants.LEFT);
		rollnumberlabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		rollnumberlabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		rollnumberlabel.setBackground(Color.WHITE);
		rollnumberlabel.setBounds(582, 66 + 150, 523, 48);
		add(rollnumberlabel);

		JLabel studentnamelabel = new JLabel("  " + s.getStudentName());
		studentnamelabel.setOpaque(true);
		studentnamelabel.setHorizontalAlignment(SwingConstants.LEFT);
		studentnamelabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		studentnamelabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		studentnamelabel.setBackground(Color.WHITE);
		studentnamelabel.setBounds(582, 113 + 150, 523, 48);
		add(studentnamelabel);

		JLabel addresslabel = new JLabel("  " + s.getGender());
		addresslabel.setOpaque(true);
		addresslabel.setHorizontalAlignment(SwingConstants.LEFT);
		addresslabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		addresslabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		addresslabel.setBackground(Color.WHITE);
		addresslabel.setBounds(582, 160 + 150, 523, 48);
		add(addresslabel);

		JLabel emailidlabel = new JLabel("  " + s.getCpa());
		emailidlabel.setOpaque(true);
		emailidlabel.setHorizontalAlignment(SwingConstants.LEFT);
		emailidlabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		emailidlabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		emailidlabel.setBackground(Color.WHITE);
		emailidlabel.setBounds(582, 207 + 150, 523, 48);
		add(emailidlabel);

		JLabel dateofbirthlabel = new JLabel("  " + s.getBirthDate());
		dateofbirthlabel.setOpaque(true);
		dateofbirthlabel.setHorizontalAlignment(SwingConstants.LEFT);
		dateofbirthlabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		dateofbirthlabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		dateofbirthlabel.setBackground(Color.WHITE);
		dateofbirthlabel.setBounds(582, 254 + 150, 523, 48);
		add(dateofbirthlabel);

		JLabel contactnumberlabel = new JLabel("  " + s.getTotalCredits());
		contactnumberlabel.setOpaque(true);
		contactnumberlabel.setHorizontalAlignment(SwingConstants.LEFT);
		contactnumberlabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		contactnumberlabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		contactnumberlabel.setBackground(Color.WHITE);
		contactnumberlabel.setBounds(582, 300 + 150, 523, 48);
		add(contactnumberlabel);

		JLabel departmentnamelabel = new JLabel("  " + new DepartmentData().getdept_Name(s.getDept_id()));
		departmentnamelabel.setOpaque(true);
		departmentnamelabel.setHorizontalAlignment(SwingConstants.LEFT);
		departmentnamelabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		departmentnamelabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		departmentnamelabel.setBackground(Color.WHITE);
		departmentnamelabel.setBounds(582, 348 + 150, 523, 48);
		add(departmentnamelabel);

		JLabel profilepiclabel = new JLabel();
		profilepiclabel.setBounds(20, 66 + 150, 250, 300);
		add(profilepiclabel);
		profilepiclabel.setBorder(new LineBorder(new Color(192, 192, 192), 2));
		profilepiclabel.setIcon(new ImageIcon("./assets/profilepicdefault.png"));
		profilepiclabel.setOpaque(true);
		profilepiclabel.setBackground(new Color(240, 248, 255));
		profilepiclabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		profilepiclabel.setHorizontalAlignment(SwingConstants.CENTER);

	}

	public ViewStudentPanel(Student s, AdminMain am, JComponent lastpanel) {
		this(s);
		this.am = am;
		this.lastpanel = lastpanel;

		backbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				am.viewstudentpanel.setVisible(false);
				if (lastpanel.getName().equals("Student Panel")) {
					am.studentpanel.createtablemodel();
					am.studentpanel.setVisible(true);
				} else if (lastpanel.getName().equals("Search Panel")) {
					am.searchpanel.createtablemodel();
					am.searchpanel.setVisible(true);
				} else if (lastpanel.getName().equals("Users Panel")) {
					// am.userspanel.createtablemodel();
					am.userspanel.setVisible(true);
				} else {
					lastpanel.setVisible(true);
				}

			}
		});
		editdetailsbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				AddStudentDialog as = new AddStudentDialog(am, s);
				as.setLocationRelativeTo(null);
				as.setVisible(true);

			}

		});

		deletebutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				int result = new StudentData().deleteStudent(s.getStudentID());
				am.viewstudentpanel.setVisible(false);
				am.studentpanel.setVisible(true);
				am.studentpanel.createtablemodel();
			}

		});

		viewmarksheet.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				ViewMarkSheet ms = new ViewMarkSheet(s.getStudentID());
				ms.setLocationRelativeTo(null);
				ms.setVisible(true);
			}

		});

	}

}
