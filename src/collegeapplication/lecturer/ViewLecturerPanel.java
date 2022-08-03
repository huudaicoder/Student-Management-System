package collegeapplication.lecturer;

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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import collegeapplication.admin.AdminMain;
import collegeapplication.common.ChangePasswordDialog;
import collegeapplication.department.DepartmentData;

/*
 * Title : ViewLecturerPanel.java
 * Created by : Ajaysinh Rathod
 * Purpose : Displaying all the details of  lecturer
 * Mail : ajaysinhrathod1290@gmail.com
 */

@SuppressWarnings("serial")
public class ViewLecturerPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public JComponent lastpanel;
	private JButton assignsubjectbutton;
	private JButton backbutton;
	private JButton editdetailsbutton;
	private JButton deletebutton;

	/**
	 * @wbp.parser.constructor
	 */
	private ViewLecturerPanel(Lecturer f) {
		setBackground(new Color(255, 255, 255));
		this.setSize(1116, 705);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setBounds(10, 0, 1096, 188);
		add(panel);
		panel.setLayout(null);
		// JLabel lblDisplayingStudentDetails = new JLabel(f.getLecturername());

		// lblDisplayingStudentDetails.setHorizontalAlignment(SwingConstants.RIGHT);
		// lblDisplayingStudentDetails.setForeground(new Color(255, 255, 255));
		// lblDisplayingStudentDetails.setFont(new Font("Segoe UI", Font.BOLD, 30));
		// lblDisplayingStudentDetails.setBounds(661, 11, 415, 44);
		// panel.add(lblDisplayingStudentDetails);

		editdetailsbutton = new JButton("Edit Details");
		editdetailsbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		editdetailsbutton.setFocusable(false);
		editdetailsbutton.setForeground(new Color(0, 139, 139));
		editdetailsbutton.setBackground(new Color(255, 255, 255));
		editdetailsbutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		editdetailsbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		editdetailsbutton.setBounds(919, 141, 153, 35);
		panel.add(editdetailsbutton);

		deletebutton = new JButton("Delete Lecturer");
		deletebutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		deletebutton.setFocusable(false);
		deletebutton.setForeground(new Color(0, 139, 139));
		deletebutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		deletebutton.setBackground(new Color(255, 255, 255));
		deletebutton.setBounds(769, 141, 140, 35);
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

		JLabel lblStudentDetails = new JLabel("Lecturer Details");
		lblStudentDetails.setHorizontalAlignment(SwingConstants.LEFT);
		lblStudentDetails.setForeground(Color.WHITE);
		lblStudentDetails.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblStudentDetails.setBounds(10, 65, 415, 44);
		panel.add(lblStudentDetails);

		JLabel lectureridlbl = new JLabel("Lecturer ID   ");
		lectureridlbl.setBorder(new LineBorder(new Color(192, 192, 192)));
		lectureridlbl.setBackground(new Color(255, 255, 255));
		lectureridlbl.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lectureridlbl.setHorizontalAlignment(SwingConstants.RIGHT);
		lectureridlbl.setOpaque(true);
		lectureridlbl.setBounds(309, 66 + 150, 274, 48);
		add(lectureridlbl);

		JLabel lecturernamelbl = new JLabel("Lecturer Name   ");
		lecturernamelbl.setOpaque(true);
		lecturernamelbl.setHorizontalAlignment(SwingConstants.RIGHT);
		lecturernamelbl.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lecturernamelbl.setBorder(new LineBorder(new Color(192, 192, 192)));
		lecturernamelbl.setBackground(Color.WHITE);
		lecturernamelbl.setBounds(309, 113 + 150, 274, 48);
		add(lecturernamelbl);

		JLabel lblAddress = new JLabel("City   ");
		lblAddress.setOpaque(true);
		lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAddress.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lblAddress.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblAddress.setBackground(Color.WHITE);
		lblAddress.setBounds(309, 160 + 150, 274, 48);
		add(lblAddress);

		JLabel lblEmailId = new JLabel("Email ID  ");
		lblEmailId.setOpaque(true);
		lblEmailId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmailId.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lblEmailId.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblEmailId.setBackground(Color.WHITE);
		lblEmailId.setBounds(309, 207 + 150, 274, 48);
		add(lblEmailId);

		JLabel lblContactNumber = new JLabel("Contact Number ");
		lblContactNumber.setOpaque(true);
		lblContactNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		lblContactNumber.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lblContactNumber.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblContactNumber.setBackground(Color.WHITE);
		lblContactNumber.setBounds(309, 254 + 150, 274, 48);
		add(lblContactNumber);

		JLabel lectureridlabel = new JLabel("  " + f.getLecturerid());
		lectureridlabel.setOpaque(true);
		lectureridlabel.setHorizontalAlignment(SwingConstants.LEFT);
		lectureridlabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		lectureridlabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		lectureridlabel.setBackground(Color.WHITE);
		lectureridlabel.setBounds(582, 66 + 150, 520, 48);
		add(lectureridlabel);

		JLabel lecturernamelabel = new JLabel("  " + f.getLecturername());
		lecturernamelabel.setOpaque(true);
		lecturernamelabel.setHorizontalAlignment(SwingConstants.LEFT);
		lecturernamelabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		lecturernamelabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		lecturernamelabel.setBackground(Color.WHITE);
		lecturernamelabel.setBounds(582, 113 + 150, 520, 48);
		add(lecturernamelabel);

		JLabel addresslabel = new JLabel("  " + f.getCity());
		addresslabel.setOpaque(true);
		addresslabel.setHorizontalAlignment(SwingConstants.LEFT);
		addresslabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		addresslabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		addresslabel.setBackground(Color.WHITE);
		addresslabel.setBounds(582, 160 + 150, 520, 48);
		add(addresslabel);

		JLabel emailidlabel = new JLabel("  " + f.getEmail());
		emailidlabel.setOpaque(true);
		emailidlabel.setHorizontalAlignment(SwingConstants.LEFT);
		emailidlabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		emailidlabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		emailidlabel.setBackground(Color.WHITE);
		emailidlabel.setBounds(582, 207 + 150, 520, 48);
		add(emailidlabel);

		JLabel contactnumberlabel = new JLabel("  " + f.getContactnumber());
		contactnumberlabel.setOpaque(true);
		contactnumberlabel.setHorizontalAlignment(SwingConstants.LEFT);
		contactnumberlabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		contactnumberlabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		contactnumberlabel.setBackground(Color.WHITE);
		contactnumberlabel.setBounds(582, 254 + 150, 520, 48);
		add(contactnumberlabel);

		JLabel lblDepartment = new JLabel("Department  ");
		lblDepartment.setOpaque(true);
		lblDepartment.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDepartment.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lblDepartment.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblDepartment.setBackground(Color.WHITE);
		lblDepartment.setBounds(309, 301 + 150, 274, 48);
		add(lblDepartment);

		JLabel departmentnamelabel = new JLabel("  " + new DepartmentData().getdept_Name(f.getDept_id()));
		departmentnamelabel.setOpaque(true);
		departmentnamelabel.setHorizontalAlignment(SwingConstants.LEFT);
		departmentnamelabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		departmentnamelabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		departmentnamelabel.setBackground(Color.WHITE);
		departmentnamelabel.setBounds(582, 301 + 150, 520, 48);
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

	public ViewLecturerPanel(Lecturer f, AdminMain am, JComponent lastpanel) {
		// TODO Auto-generated constructor stub
		this(f);
		this.lastpanel = lastpanel;

		backbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				am.viewlecturerpanel.setVisible(false);
				if (lastpanel.getName().equals("Lecturer Panel")) {
					am.lecturerpanel.createtablemodel();
					am.lecturerpanel.setVisible(true);
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
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddLecturerDialog ad = new AddLecturerDialog(am, f);
				ad.setLocationRelativeTo(null);
				ad.setVisible(true);

			}

		}

		);

		deletebutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				int result = new LecturerData().deleteLecturer(f.getLecturerid());
				am.viewlecturerpanel.setVisible(false);
				am.lecturerpanel.setVisible(true);
				am.lecturerpanel.createtablemodel();
			}

		});

	}

}
