package collegeapplication.student;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.DefaultFormatter;

import collegeapplication.admin.AdminMain;
import collegeapplication.common.PhotoViewPanel;
import net.proteanit.sql.DbUtils;

@SuppressWarnings("serial")
public class StudentPanel extends JPanel implements ActionListener {
	public JTable table;
	private JButton viewstudentbutton, addstudentbutton;
	public AdminMain am;
	public JButton viewbutton;
	public JScrollPane tableviewscroll;
	public JScrollPane photoviewscrollpane;
	private JSpinner maxphotospinner;
	private JLabel maxphotolabel;
	private int maxphoto = 5;
	private String condition = "";
	private JLabel studentslabel;
	private JButton backbutton;
	private JPanel panel;

	/**
	 * Create the panel.
	 */
	public StudentPanel(AdminMain am) {
		this();
		this.am = am;
	}

	private StudentPanel() {
		this.setName("Student Panel");
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
		table.getTableHeader().setPreferredSize(new Dimension(50, 40));
		table.setFocusable(false);
		table.setDragEnabled(false);
		table.setRowHeight(40);
		createtablemodel();
		table.setDefaultEditor(Object.class, null);
		table.setCursor(new Cursor(Cursor.HAND_CURSOR));
		table.setGridColor(Color.LIGHT_GRAY);
		table.getTableHeader().setReorderingAllowed(false);
		tableviewscroll.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() > 1 && e.getButton() == MouseEvent.BUTTON1) {

					JTable t = (JTable) e.getSource();
					int row = t.getSelectedRow();
					String strroll = table.getValueAt(row, 0) + "";
					// System.out.println(strroll);
					long rollnumber = Long.parseLong(strroll);
					Student s = new StudentData().getStudentDetails(rollnumber);

					if (am != null) {
						am.viewstudentpanel = new ViewStudentPanel(s, am, am.studentpanel);
						am.viewstudentpanel.setVisible(true);
						am.studentpanel.setVisible(false);
						am.viewstudentpanel.setLocation(am.panelx, 0);
						am.viewstudentpanel.setVisible(true);
						am.viewstudentpanel.setFocusable(true);
						am.contentPane.add(am.viewstudentpanel);
					}
				}

			}
		});

		panel = new JPanel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setBounds(10, 0, 1096, 183);
		add(panel);
		panel.setLayout(null);

		studentslabel = new JLabel("All Students");
		studentslabel.setIcon(null);
		studentslabel.setBounds(10, 65, 224, 44);
		panel.add(studentslabel);
		studentslabel.setBackground(new Color(32, 178, 170));
		studentslabel.setHorizontalAlignment(SwingConstants.LEFT);
		studentslabel.setForeground(Color.WHITE);
		studentslabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
		studentslabel.setOpaque(true);

		// viewstudentbutton = new JButton("View Student");
		// viewstudentbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		// viewstudentbutton.setBounds(769, 139, 153, 33);
		// panel.add(viewstudentbutton);
		// viewstudentbutton.setFocusable(false);
		// viewstudentbutton.setForeground(new Color(0, 128, 128));
		// viewstudentbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		// viewstudentbutton.setBackground(new Color(255, 255, 255));
		// viewstudentbutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		// viewstudentbutton.addActionListener(this);

		addstudentbutton = new JButton("Add Student");
		addstudentbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		addstudentbutton.setBounds(932, 139, 153, 33);
		panel.add(addstudentbutton);
		addstudentbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addstudentbutton.setFocusable(false);
		addstudentbutton.setForeground(new Color(0, 128, 128));
		addstudentbutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		addstudentbutton.setBackground(new Color(255, 255, 255));
		addstudentbutton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == addstudentbutton) {
			AddStudentDialog sd = new AddStudentDialog(table, this);
			sd.setLocationRelativeTo(null);
			sd.setVisible(true);
		}
		// createtablemodel();
		tableviewscroll.setVisible(true);
	}

	public void createphotopanel() {
	}

	public void createtablemodel() {
		ResultSet rs = new StudentData().getStudentinfo(condition);
		if (rs != null) {
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(0).setMaxWidth(150);
		table.getColumnModel().getColumn(1).setMaxWidth(300);
		table.getColumnModel().getColumn(2).setMaxWidth(150);
		table.getColumnModel().getColumn(3).setMaxWidth(100);
		table.getColumnModel().getColumn(4).setMaxWidth(390);

		// table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
	}
}
