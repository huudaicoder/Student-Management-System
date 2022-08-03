package collegeapplication.department;

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
import collegeapplication.lecturer.LecturerData;
import collegeapplication.student.StudentData;
import collegeapplication.subject.SubjectData;

@SuppressWarnings("serial")
public class ViewDepartmentPanel extends JPanel {

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
	private ViewDepartmentPanel(Department f) {
		setBackground(new Color(255, 255, 255));
		this.setSize(1116, 705);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setBounds(10, 0, 1096, 188);
		add(panel);
		panel.setLayout(null);

		editdetailsbutton = new JButton("Edit Details");
		editdetailsbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		editdetailsbutton.setFocusable(false);
		editdetailsbutton.setForeground(new Color(0, 139, 139));
		editdetailsbutton.setBackground(new Color(255, 255, 255));
		editdetailsbutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		editdetailsbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		editdetailsbutton.setBounds(919, 141, 153, 35);
		panel.add(editdetailsbutton);

		deletebutton = new JButton("Delete Department");
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

		JLabel lblStudentDetails = new JLabel("Department Details");
		lblStudentDetails.setHorizontalAlignment(SwingConstants.LEFT);
		lblStudentDetails.setForeground(Color.WHITE);
		lblStudentDetails.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblStudentDetails.setBounds(10, 65, 415, 44);
		panel.add(lblStudentDetails);

		JLabel deptIDlbl = new JLabel("Department ID   ");
		deptIDlbl.setBorder(new LineBorder(new Color(192, 192, 192)));
		deptIDlbl.setBackground(new Color(255, 255, 255));
		deptIDlbl.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		deptIDlbl.setHorizontalAlignment(SwingConstants.RIGHT);
		deptIDlbl.setOpaque(true);
		deptIDlbl.setBounds(309, 66 + 150, 274, 48);
		add(deptIDlbl);

		JLabel deptNamelbl = new JLabel("Department Name   ");
		deptNamelbl.setOpaque(true);
		deptNamelbl.setHorizontalAlignment(SwingConstants.RIGHT);
		deptNamelbl.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		deptNamelbl.setBorder(new LineBorder(new Color(192, 192, 192)));
		deptNamelbl.setBackground(Color.WHITE);
		deptNamelbl.setBounds(309, 113 + 150, 274, 48);
		add(deptNamelbl);

		JLabel lblnumLec = new JLabel("Number of Lecturer  ");
		lblnumLec.setOpaque(true);
		lblnumLec.setHorizontalAlignment(SwingConstants.RIGHT);
		lblnumLec.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lblnumLec.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblnumLec.setBackground(Color.WHITE);
		lblnumLec.setBounds(309, 160 + 150, 274, 48);
		add(lblnumLec);

		JLabel lblnumStu = new JLabel("Number of Student  ");
		lblnumStu.setOpaque(true);
		lblnumStu.setHorizontalAlignment(SwingConstants.RIGHT);
		lblnumStu.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lblnumStu.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblnumStu.setBackground(Color.WHITE);
		lblnumStu.setBounds(309, 207 + 150, 274, 48);
		add(lblnumStu);

		JLabel lblnumSub = new JLabel("Number of Subject ");
		lblnumSub.setOpaque(true);
		lblnumSub.setHorizontalAlignment(SwingConstants.RIGHT);
		lblnumSub.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lblnumSub.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblnumSub.setBackground(Color.WHITE);
		lblnumSub.setBounds(309, 254 + 150, 274, 48);
		add(lblnumSub);

		JLabel deptIDlabel = new JLabel("  " + f.getDept_ID());
		deptIDlabel.setOpaque(true);
		deptIDlabel.setHorizontalAlignment(SwingConstants.LEFT);
		deptIDlabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		deptIDlabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		deptIDlabel.setBackground(Color.WHITE);
		deptIDlabel.setBounds(582, 66 + 150, 520, 48);
		add(deptIDlabel);

		JLabel deptNamelabel = new JLabel("  " + f.getDept_Name());
		deptNamelabel.setOpaque(true);
		deptNamelabel.setHorizontalAlignment(SwingConstants.LEFT);
		deptNamelabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		deptNamelabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		deptNamelabel.setBackground(Color.WHITE);
		deptNamelabel.setBounds(582, 113 + 150, 520, 48);
		add(deptNamelabel);

		JLabel leclabel = new JLabel(String.valueOf("  " + new LecturerData().getTotalLecturers(f.getDept_ID())));
		leclabel.setOpaque(true);
		leclabel.setHorizontalAlignment(SwingConstants.LEFT);
		leclabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		leclabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		leclabel.setBackground(Color.WHITE);
		leclabel.setBounds(582, 160 + 150, 520, 48);
		add(leclabel);

		JLabel stulabel = new JLabel(String.valueOf("  " + new StudentData().getTotalStudentInCource(f.getDept_ID())));
		stulabel.setOpaque(true);
		stulabel.setHorizontalAlignment(SwingConstants.LEFT);
		stulabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		stulabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		stulabel.setBackground(Color.WHITE);
		stulabel.setBounds(582, 207 + 150, 520, 48);
		add(stulabel);

		JLabel sublabel = new JLabel(
				String.valueOf("  " + new SubjectData().getTotalSubjectinDepartment(f.getDept_ID())));
		sublabel.setOpaque(true);
		sublabel.setHorizontalAlignment(SwingConstants.LEFT);
		sublabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		sublabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		sublabel.setBackground(Color.WHITE);
		sublabel.setBounds(582, 254 + 150, 520, 48);
		add(sublabel);

		JLabel lblbuilding = new JLabel("Building  ");
		lblbuilding.setOpaque(true);
		lblbuilding.setHorizontalAlignment(SwingConstants.RIGHT);
		lblbuilding.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lblbuilding.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblbuilding.setBackground(Color.WHITE);
		lblbuilding.setBounds(309, 301 + 150, 274, 48);
		add(lblbuilding);

		JLabel buildinglabel = new JLabel("  " + f.getBuilding());
		buildinglabel.setOpaque(true);
		buildinglabel.setHorizontalAlignment(SwingConstants.LEFT);
		buildinglabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		buildinglabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		buildinglabel.setBackground(Color.WHITE);
		buildinglabel.setBounds(582, 301 + 150, 520, 48);
		add(buildinglabel);

		JLabel lblphone = new JLabel("Contact Number  ");
		lblphone.setOpaque(true);
		lblphone.setHorizontalAlignment(SwingConstants.RIGHT);
		lblphone.setFont(new Font("Segoe UI Historic", Font.PLAIN, 20));
		lblphone.setBorder(new LineBorder(new Color(192, 192, 192)));
		lblphone.setBackground(Color.WHITE);
		lblphone.setBounds(309, 348 + 150, 274, 48);
		add(lblphone);

		JLabel phonelabel = new JLabel("  " + f.getPhoneNumber());
		phonelabel.setOpaque(true);
		phonelabel.setHorizontalAlignment(SwingConstants.LEFT);
		phonelabel.setFont(new Font("Segoe UI Historic", Font.BOLD, 20));
		phonelabel.setBorder(new LineBorder(new Color(192, 192, 192)));
		phonelabel.setBackground(Color.WHITE);
		phonelabel.setBounds(582, 348 + 150, 520, 48);
		add(phonelabel);

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

	public ViewDepartmentPanel(Department f, AdminMain am, JComponent lastpanel) {
		// TODO Auto-generated constructor stub
		this(f);
		this.lastpanel = lastpanel;

		backbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				am.viewdepartmentpanel.setVisible(false);
				am.departmentpanel.updatetableData();
				am.departmentpanel.setVisible(true);

			}
		});
		editdetailsbutton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddDepartmentDialog ad = new AddDepartmentDialog(am, f);
				ad.setLocationRelativeTo(null);
				ad.setVisible(true);

			}

		});
		deletebutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int result = new DepartmentData().deleteDept(f.getDept_ID());
				am.viewdepartmentpanel.setVisible(false);
				am.departmentpanel.setVisible(true);
				am.departmentpanel.updatetableData();
			}

		});

	}

}
