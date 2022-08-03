package collegeapplication.common;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

import collegeapplication.admin.AdminMain;
import collegeapplication.department.DepartmentData;
import collegeapplication.lecturer.Lecturer;
import collegeapplication.lecturer.LecturerData;
import collegeapplication.lecturer.ViewLecturerPanel;
import collegeapplication.student.Student;
import collegeapplication.student.StudentData;
import collegeapplication.student.ViewStudentPanel;
import net.proteanit.sql.DbUtils;

/*
 * Title : SearchPanel.java
 * Created by : Ajaysinh Rathod
 * Purpose : For searching student of lecturer
 * Mail : ajaysinhrathod1290@gmail.com
 */

@SuppressWarnings("serial")
public class SearchPanel extends JPanel implements ActionListener {

	private JTable table;
	private JScrollPane tableviewscroll;
	private JTextField searchfield;
	private JComboBox<String> departmentnamecombo;
	private JComboBox<String> semoryearcombo;
	private JComboBox<String> studentandlecturercombo;

	private JButton searchbutton;

	/**
	 * Create the panel.
	 */
	public SearchPanel(AdminMain am) {
		this();
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() > 1 && e.getButton() == MouseEvent.BUTTON1) {
					if (studentandlecturercombo.getSelectedIndex() == 0) {
						JTable t = (JTable) e.getSource();
						int row = t.getSelectedRow();
						String departmentcode = table.getValueAt(row, 0) + "";
						String strsem = table.getValueAt(row, 4) + "";
						int sem = Integer.parseInt(strsem.substring(strsem.indexOf('-') + 1));
						String strroll = table.getValueAt(row, 1) + "";
						long rollnumber = Long.parseLong(strroll);
						Student s = new StudentData().getStudentDetails(rollnumber);

						am.viewstudentpanel = new ViewStudentPanel(s, am, am.searchpanel);
						am.viewstudentpanel.setVisible(true);
						am.searchpanel.setVisible(false);
						am.viewstudentpanel.setLocation(am.panelx, 0);
						am.viewstudentpanel.setVisible(true);
						am.viewstudentpanel.setFocusable(true);
						am.contentPane.add(am.viewstudentpanel);
					} else {
						JTable t = (JTable) e.getSource();
						String fid = t.getValueAt(t.getSelectedRow(), 0) + "";
						Lecturer f = new LecturerData().getLecturerInfobyId(fid);

						am.viewlecturerpanel = new ViewLecturerPanel(f, am, am.searchpanel);
						am.viewlecturerpanel.setVisible(true);
						am.searchpanel.setVisible(false);
						am.viewlecturerpanel.setLocation(am.panelx, am.panely);
						am.viewlecturerpanel.setVisible(true);
						am.viewlecturerpanel.setFocusable(true);
						am.contentPane.add(am.viewlecturerpanel);
					}
				}
			}
		});

	}

	public SearchPanel() {
		this.setName("Search Panel");
		setBackground(new Color(255, 255, 255));
		this.setSize(1116, 705);
		setLayout(null);

		tableviewscroll = new JScrollPane();
		tableviewscroll.setBorder(new EmptyBorder(0, 0, 0, 0));
		tableviewscroll.setBounds(10, 194, 1096, 500);
		for (Component c : tableviewscroll.getComponents()) {
			c.setBackground(Color.white);
		}
		add(tableviewscroll);

		table = new JTable();

		table.setBorder(new LineBorder(Color.LIGHT_GRAY));

		table.getTableHeader().setBackground(new Color(32, 178, 170));
		table.getTableHeader().setForeground(Color.white);
		table.setSelectionBackground(new Color(240, 255, 255));
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
		table.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		table.setModel(DbUtils.resultSetToTableModel(new StudentData().getStudentinfo("")));
		table.getTableHeader().setPreferredSize(new Dimension(50, 40));
		table.setFocusable(false);
		table.setDragEnabled(false);
		table.setRowHeight(40);
		table.setDefaultEditor(Object.class, null);
		table.setCursor(new Cursor(Cursor.HAND_CURSOR));
		table.setGridColor(Color.LIGHT_GRAY);
		table.getTableHeader().setReorderingAllowed(false);
		tableviewscroll.setViewportView(table);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setBounds(10, 0, 1096, 183);
		add(panel);
		panel.setLayout(null);
		JLabel lblStudentManagement = new JLabel("Search");
		lblStudentManagement.setIcon(null);
		lblStudentManagement.setBounds(10, 38, 224, 44);
		panel.add(lblStudentManagement);
		lblStudentManagement.setBackground(new Color(32, 178, 170));
		lblStudentManagement.setHorizontalAlignment(SwingConstants.LEFT);
		lblStudentManagement.setForeground(Color.WHITE);
		lblStudentManagement.setFont(new Font("Segoe UI", Font.BOLD, 30));
		lblStudentManagement.setOpaque(true);

		studentandlecturercombo = new JComboBox<String>();
		studentandlecturercombo.setModel(new DefaultComboBoxModel<String>(new String[] { "Students", "Faculties" }));
		this.arrangeStudentTable();
		studentandlecturercombo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		studentandlecturercombo.setBounds(10, 128, 205, 40);
		studentandlecturercombo.addActionListener(this);
		panel.add(studentandlecturercombo);

		String departmentname[] = new DepartmentData().getDepartmentName();
		departmentname[0] = "All Departments";
		departmentnamecombo = new JComboBox<String>(departmentname);

		departmentnamecombo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		departmentnamecombo.setBounds(225, 128, 255, 40);
		departmentnamecombo.addActionListener(this);

		panel.add(departmentnamecombo);

		semoryearcombo = new JComboBox<String>();
		semoryearcombo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		semoryearcombo.setBounds(490, 128, 214, 40);
		semoryearcombo.addActionListener(this);
		panel.add(semoryearcombo);

		searchfield = new HintTextField("Search");
		searchfield.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		searchfield.setForeground(Color.DARK_GRAY);
		searchfield.setBounds(714, 129, 248, 40);
		panel.add(searchfield);
		searchfield.setColumns(10);

		searchbutton = new JButton();
		searchbutton.setForeground(new Color(0, 139, 139));
		searchbutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		searchbutton.setText("Search");
		searchbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		searchbutton.setBackground(new Color(255, 255, 255));
		searchbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		searchbutton.setIcon(new ImageIcon("./assets/search.png"));
		searchbutton.setBounds(972, 129, 114, 40);
		searchbutton.addActionListener(this);
		searchbutton.setFocusable(false);
		panel.add(searchbutton);

	}

	public void arrangeStudentTable() {
		table.getColumnModel().getColumn(0).setMaxWidth(150);
		table.getColumnModel().getColumn(1).setMaxWidth(200);
		table.getColumnModel().getColumn(2).setMaxWidth(300);
		table.getColumnModel().getColumn(3).setMaxWidth(300);
		table.getColumnModel().getColumn(4).setMaxWidth(150);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
	}

	public void arrangeLecturerTable() {
		table.getColumnModel().getColumn(0).setMaxWidth(200);
		table.getColumnModel().getColumn(1).setMaxWidth(300);
		table.getColumnModel().getColumn(2).setMaxWidth(500);
		table.getColumnModel().getColumn(3).setMaxWidth(250);
		table.getColumnModel().getColumn(4).setMaxWidth(250);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		DefaultTableCellRenderer cellrenderer = new DefaultTableCellRenderer();
		cellrenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(cellrenderer);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == departmentnamecombo) {

			if (departmentnamecombo.getSelectedIndex() == 0) {
				semoryearcombo.setModel(new DefaultComboBoxModel<String>(new String[] { "" }));

			}

			else {
				// String department = (String) departmentnamecombo.getSelectedItem();
				// //String semoryear[] = new DepartmentData().getSemorYear(department);
				// semoryear[0] = "All " + semoryear[1].substring(0, semoryear[1].indexOf(' '));
				// semoryearcombo.setModel(new DefaultComboBoxModel<String>(semoryear));
			}

		}
		if (e.getSource() == searchbutton) {

			createtablemodel();
		}

	}

	public void createtablemodel() {
		String searchtext = searchfield.getText().trim();
		if (studentandlecturercombo.getSelectedIndex() == 0) {
			String defaultquery = "select s.departmentcode as 'Class' ,s.rollnumber as 'Roll Number',concat(s.firstname,' ',s.lastname) as 'Student Name',c.departmentname as 'Department Name',concat(c.semoryear,'-',s.semoryear) as 'Sem/Year' from students s left join departments c on s.departmentcode=c.departmentcode ";
			String query = defaultquery;
			if (departmentnamecombo.getSelectedIndex() > 0) {
				String departmentcode = new DepartmentData()
						.getDepartmentcode(departmentnamecombo.getSelectedItem() + "");
				query += " where s.departmentcode='" + departmentcode + "'";
				if (semoryearcombo.getSelectedIndex() > 0) {
					query += " and s.semoryear=" + semoryearcombo.getSelectedIndex();
				}

			}

			if (!searchtext.isEmpty()) {
				String searchquery = "s.firstname like '" + searchtext + "%' or s.lastname like '" + searchtext
						+ "%' or s.rollnumber like '" + searchtext + "%' ";
				if (!query.contains("where")) {
					query += "where " + searchquery;
				} else {
					query += " and ( " + searchquery + " ) ";
				}

			}
			table.setModel(DbUtils.resultSetToTableModel(new StudentData().searchStudent(query)));
			this.arrangeStudentTable();
		} else if (studentandlecturercombo.getSelectedIndex() == 1) {
			String defaultquery = "select lecturerid as 'Lecturer ID',lecturername as 'Lecturer Name',emailid as 'Email ID',qualification as 'Qualification',experience as 'Experience' from faculties f ";
			String query = defaultquery;
			if (departmentnamecombo.getSelectedIndex() > 0) {
				String departmentcode = new DepartmentData()
						.getDepartmentcode(departmentnamecombo.getSelectedItem() + "");
				query += " where f.departmentcode='" + departmentcode + "'";
				if (semoryearcombo.getSelectedIndex() > 0) {
					query += " and f.semoryear=" + semoryearcombo.getSelectedIndex();
				}

			}
			if (!searchtext.equals("Search") && !searchtext.isEmpty()) {
				String searchquery = " f.lecturername like '" + searchtext + "%' or f.lecturerid like '" + searchtext
						+ "%' ";
				if (!query.contains("where")) {
					query += "where " + searchquery;
				} else {
					query += " and ( " + searchquery + " ) ";
				}

			}
			table.setModel(DbUtils.resultSetToTableModel(new LecturerData().searchLecturer(query)));
			this.arrangeLecturerTable();
		}
	}
}
