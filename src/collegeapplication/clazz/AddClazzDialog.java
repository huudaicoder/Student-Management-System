package collegeapplication.clazz;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;

import net.proteanit.sql.DbUtils;
import collegeapplication.admin.AdminMain;
import collegeapplication.common.HintTextField;
import collegeapplication.lecturer.LecturerData;
import collegeapplication.subject.SubjectData;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;

/*
 * Title : AddClazzDialog.java
 * Created by : Ajaysinh Rathod
 * Purpose : Dialog for adding new clazz
 * Mail : ajaysinhrathod1290@gmail.com
 */

@SuppressWarnings("serial")
public class AddClazzDialog extends JDialog implements ActionListener {

	private JTextField clazzcodefield;
	private JLabel lblError;
	private ClazzPanel clazzpanel;
	private HintTextField semeterfield;
	private JButton addclazzbutton;
	private JComboBox<String> subjectnamecombo;
	private JComboBox<String> lecturernamecombo;
	private JComboBox<String> roomnamecombo;
	private JComboBox<String> timeslotnamecombo;
	ClazzPanel sp;
	private AdminMain am;
	private Clazz clazz;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddClazzDialog dialog = new AddClazzDialog(null);

			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddClazzDialog(ClazzPanel clazzpanel, JTable table) {
		this(table);
		this.clazzpanel = clazzpanel;
	}

	public AddClazzDialog(JTable table) {
		this.table = table;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 480, 600);
		getContentPane().setLayout(null);

		JLabel lblAddNewClazz = new JLabel("Add New Class");
		lblAddNewClazz.setForeground(new Color(255, 255, 255));
		lblAddNewClazz.setBackground(new Color(32, 178, 170));
		lblAddNewClazz.setOpaque(true);
		lblAddNewClazz.setFont(new Font("Arial", Font.BOLD, 23));
		lblAddNewClazz.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddNewClazz.setBounds(0, 0, 480, 55);
		getContentPane().add(lblAddNewClazz);
		// code
		JLabel lblClazzCode = new JLabel("Class ID");
		lblClazzCode.setBorder(new EmptyBorder(0, 0, 0, 5));
		lblClazzCode.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblClazzCode.setHorizontalAlignment(SwingConstants.LEFT);
		lblClazzCode.setBounds(10, 79, 150, 24);
		lblClazzCode.setFocusable(true);
		getContentPane().add(lblClazzCode);

		clazzcodefield = new HintTextField("");
		clazzcodefield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		clazzcodefield.setBounds(165, 72, 290, 40);
		int total = new ClazzData().getTotalClazz();
		clazzcodefield.setText(String.valueOf(total + 1));
		clazzcodefield.setEditable(false);
		getContentPane().add(clazzcodefield);
		clazzcodefield.setColumns(10);
		// subject
		JLabel lblSubjectCode = new JLabel("Subject");
		lblSubjectCode.setHorizontalAlignment(SwingConstants.LEFT);
		lblSubjectCode.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblSubjectCode.setBorder(new EmptyBorder(0, 0, 0, 5));
		lblSubjectCode.setBounds(10, 147, 150, 24);
		getContentPane().add(lblSubjectCode);

		subjectnamecombo = new JComboBox<String>(new SubjectData().getSubjectName());
		subjectnamecombo.setForeground(Color.DARK_GRAY);
		subjectnamecombo.setToolTipText("Subject");
		subjectnamecombo.setFont(new Font("Segoe UI Historic", Font.PLAIN, 18));
		subjectnamecombo.addActionListener(this);
		subjectnamecombo.setBackground(new Color(255, 255, 255));
		subjectnamecombo.setBounds(165, 141, 290, 40);
		subjectnamecombo.setFocusable(false);
		getContentPane().add(subjectnamecombo);
		// lecturerid
		JLabel lblLecturerID = new JLabel("Lecturer");
		lblLecturerID.setHorizontalAlignment(SwingConstants.LEFT);
		lblLecturerID.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblLecturerID.setBorder(new EmptyBorder(0, 0, 0, 5));
		lblLecturerID.setBounds(10, 218, 150, 24);
		getContentPane().add(lblLecturerID);

		lecturernamecombo = new JComboBox<String>(new LecturerData().getLecturerName());
		lecturernamecombo.setForeground(Color.DARK_GRAY);
		lecturernamecombo.setToolTipText("Lecturer");
		lecturernamecombo.setFont(new Font("Segoe UI Historic", Font.PLAIN, 18));
		lecturernamecombo.addActionListener(this);
		lecturernamecombo.setBackground(new Color(255, 255, 255));
		lecturernamecombo.setBounds(165, 212, 290, 40);
		lecturernamecombo.setFocusable(false);
		getContentPane().add(lecturernamecombo);
		// roomID
		JLabel lblroomID = new JLabel("Room");
		lblroomID.setHorizontalAlignment(SwingConstants.LEFT);
		lblroomID.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblroomID.setBorder(new EmptyBorder(0, 0, 0, 5));
		lblroomID.setBounds(10, 284, 150, 24);
		getContentPane().add(lblroomID);

		roomnamecombo = new JComboBox<String>(new ClazzData().getRoomName());
		roomnamecombo.setForeground(Color.DARK_GRAY);
		roomnamecombo.setToolTipText("Room");
		roomnamecombo.setFont(new Font("Segoe UI Historic", Font.PLAIN, 18));
		roomnamecombo.addActionListener(this);
		roomnamecombo.setBackground(new Color(255, 255, 255));
		roomnamecombo.setBounds(165, 278, 290, 40);
		roomnamecombo.setFocusable(false);
		getContentPane().add(roomnamecombo);
		// timeslotID
		JLabel lbltimeslotID = new JLabel("Time Slot");
		lbltimeslotID.setHorizontalAlignment(SwingConstants.LEFT);
		lbltimeslotID.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lbltimeslotID.setBorder(new EmptyBorder(0, 0, 0, 5));
		lbltimeslotID.setBounds(10, 350, 150, 24);
		getContentPane().add(lbltimeslotID);

		timeslotnamecombo = new JComboBox<String>(new ClazzData().getTimeSlotName());
		timeslotnamecombo.setForeground(Color.DARK_GRAY);
		timeslotnamecombo.setToolTipText("Department");
		timeslotnamecombo.setFont(new Font("Segoe UI Historic", Font.PLAIN, 18));
		timeslotnamecombo.addActionListener(this);
		timeslotnamecombo.setBackground(new Color(255, 255, 255));
		timeslotnamecombo.setBounds(165, 344, 290, 40);
		timeslotnamecombo.setFocusable(false);
		getContentPane().add(timeslotnamecombo);
		// semeter
		JLabel lblsemeter = new JLabel("Semeter");
		lblsemeter.setHorizontalAlignment(SwingConstants.LEFT);
		lblsemeter.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblsemeter.setBorder(new EmptyBorder(0, 0, 0, 5));
		lblsemeter.setBounds(10, 416, 150, 24);
		getContentPane().add(lblsemeter);

		semeterfield = new HintTextField("");
		semeterfield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		semeterfield.setColumns(10);
		semeterfield.setBounds(165, 410, 290, 40);
		getContentPane().add(semeterfield);
		// add
		addclazzbutton = new JButton("Add Class");
		addclazzbutton.setBackground(new Color(32, 178, 170));
		addclazzbutton.setForeground(new Color(255, 255, 255));
		addclazzbutton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		addclazzbutton.setBounds(310, 500, 150, 37);
		addclazzbutton.addActionListener(this);
		getContentPane().add(addclazzbutton);

		lblError = new JLabel("This is required question !");
		lblError.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(255, 0, 0)));
		lblError.setForeground(new Color(255, 0, 0));
		lblError.setFont(new Font("Candara", Font.PLAIN, 15));
		lblError.setVisible(false);
		lblError.setBounds(165, 115, 355, 21);
		getContentPane().add(lblError);

		JLabel label = new JLabel("");
		label.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		label.setBounds(0, 346, 470, 14);
		getContentPane().add(label);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		lblError.setForeground(Color.red);
		lblError.setVisible(false);
		lblError.setText("This is required question !");
		if (e.getSource() == addclazzbutton) {
			if (subjectnamecombo.getSelectedIndex() == 0 || lecturernamecombo.getSelectedIndex() == 0
					|| roomnamecombo.getSelectedIndex() == 0 || timeslotnamecombo.getSelectedIndex() == 0
					|| semeterfield.getText().isEmpty()) {
				showerror();
			} else {
				try {
					Clazz c = new Clazz();
					c.setClazzID(clazzcodefield.getText());
					c.setSubjectID(new SubjectData().getSubjectCode(subjectnamecombo.getSelectedItem() + ""));
					c.setLectureID(new LecturerData().getLecturerID(lecturernamecombo.getSelectedItem() + ""));
					c.setRoomID(String.valueOf(roomnamecombo.getSelectedIndex()));
					c.setTimeSlotID(String.valueOf(timeslotnamecombo.getSelectedIndex()));
					c.setSemester(semeterfield.getText());
					int result = 0;

					result = new ClazzData().addClazz(c);

					ClazzData c1 = new ClazzData();
					ResultSet st = c1.getClazzinfo();
					table.setModel(DbUtils.resultSetToTableModel(st));
					DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
					centerRenderer.setHorizontalAlignment(JLabel.CENTER);
					table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
					table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
					table.getColumnModel().getColumn(0).setMaxWidth(200);
					table.getColumnModel().getColumn(1).setMaxWidth(470);
					// table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
					this.dispose();

				} catch (NumberFormatException exp) {

				} catch (Exception e1) {

					// TODO Auto-generated catch block

					e1.printStackTrace();
				}

			}

		}

	}

	public void showerror() {
		JOptionPane.showMessageDialog(new JFrame(), "Please fill in all fields marked with an asterisk(*). !", "Dialog",
				JOptionPane.WARNING_MESSAGE);
	}

}
