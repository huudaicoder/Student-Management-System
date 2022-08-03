package collegeapplication.subject;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

import collegeapplication.admin.AdminMain;
import collegeapplication.department.DepartmentData;
import net.proteanit.sql.DbUtils;

/*
 * Title : SubjectPanel.java
 * Created by : Ajaysinh Rathod
 * Purpose : Displaying all the subject in given department and sem
 * Mail : ajaysinhrathod1290@gmail.com
 */

@SuppressWarnings("serial")
public class SubjectPanel extends JPanel implements ActionListener {

	/**
	 * Create the panel.
	 */
	private JComboBox<String> departmentnamecombo;
	private JComboBox<String> semoryearcombo;
	private JButton addsubject;
	private String Departmentcode[];
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel selectdepartmentlabel;
	private JLabel selectsemlabel;
	private JLabel headerlabel;
	private JButton backbutton;

	public SubjectPanel(AdminMain am) {
		this();
	}

	private SubjectPanel() {
		new Timer(100, this);
		// timer.start();
		setBackground(Color.WHITE);
		setForeground(Color.WHITE);
		this.setSize(1116, 705);
		setLayout(null);
		headerlabel = new JLabel("Subject Management");
		headerlabel.setBackground(new Color(32, 178, 170));
		headerlabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerlabel.setForeground(new Color(255, 255, 255));
		headerlabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
		headerlabel.setBounds(10, 0, 1096, 66);
		headerlabel.setOpaque(true);
		add(headerlabel);

		selectdepartmentlabel = new JLabel("Select Department  ");
		selectdepartmentlabel.setHorizontalAlignment(SwingConstants.LEFT);
		selectdepartmentlabel.setForeground(Color.DARK_GRAY);
		selectdepartmentlabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		selectdepartmentlabel.setBounds(30, 89, 248, 42);
		add(selectdepartmentlabel);

		departmentnamecombo = new JComboBox<String>(new DepartmentData().getDepartmentName());
		departmentnamecombo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		departmentnamecombo.setBackground(Color.WHITE);
		departmentnamecombo.setFocusable(false);
		departmentnamecombo.addActionListener(this);
		departmentnamecombo.setBounds(300, 88, 750, 42);
		add(departmentnamecombo);

		Departmentcode = new DepartmentData().getDepartmentcode();

		addsubject = new JButton("Add New Subject");
		addsubject.setBorder(new EmptyBorder(0, 0, 0, 0));
		addsubject.addActionListener(this);
		addsubject.setForeground(new Color(255, 255, 255));
		addsubject.setBackground(new Color(32, 178, 170));
		addsubject.setFont(new Font("Segoe UI", Font.BOLD, 15));
		addsubject.setBounds(930, 242, 150, 37);
		addsubject.setVisible(false);
		addsubject.setFocusable(false);
		addsubject.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(addsubject);

		scrollPane = new JScrollPane();
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBounds(10, 311, 1096, 361);
		for (Component c : scrollPane.getComponents()) {
			c.setBackground(Color.white);
		}
		add(scrollPane);

		table = new JTable();
		table.setBorder(new LineBorder(new Color(192, 192, 192), 2));
		table.setBackground(Color.white);
		table.setRowHeight(40);

		table.getTableHeader().setBackground(new Color(32, 178, 170));
		table.getTableHeader().setForeground(Color.white);
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
		table.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		table.getTableHeader().setPreferredSize(new Dimension(50, 40));
		table.setDragEnabled(false);
		table.setGridColor(Color.LIGHT_GRAY);
		table.getTableHeader().setReorderingAllowed(false);
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		scrollPane.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (departmentnamecombo.getSelectedIndex() == 0) {
			scrollPane.setVisible(false);
		}
		if (e.getSource() == departmentnamecombo) {
			// int index = departmentnamecombo.getSelectedIndex();
			if (departmentnamecombo.getSelectedIndex() > 0) {
				// System.out.println(departmentnamecombo.getSelectedIndex());
				scrollPane.setVisible(true);
				addsubject.setVisible(true);
				String department = (String) departmentnamecombo.getSelectedItem();
				this.createtablemodel(new DepartmentData().getDepartmentcode(department));
			}
		}
		// String courseName = (String) departmentnamecombo.getSelectedItem();
		// this.createtablemodel(DepartmentData.getDepartmentcode(courseName));
		if (e.getSource() == addsubject) {
			int index = departmentnamecombo.getSelectedIndex();
			// String courseName = (String) departmentnamecombo.getSelectedItem();
			AddSubjectDialog sd = new AddSubjectDialog(
					new DepartmentData().getDepartmentcode(departmentnamecombo.getSelectedItem() + ""), table);
			sd.setLocationRelativeTo(null);
			sd.setVisible(true);
			scrollPane.setVisible(true);

		}
	}

	public void createtablemodel(String departmentcode) {
		ResultSet st = new SubjectData().getSubjectinfo(departmentcode);
		if (st != null) {
			table.setModel(DbUtils.resultSetToTableModel(st));
		}
		// table.setModel(DbUtils.resultSetToTableModel(st));
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(0).setMaxWidth(200);
		table.getColumnModel().getColumn(1).setMaxWidth(490);
		table.getColumnModel().getColumn(2).setMaxWidth(200);
		table.getColumnModel().getColumn(3).setMaxWidth(200);

	}

}
