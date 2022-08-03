package collegeapplication.student;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import collegeapplication.admin.AdminMain;
import collegeapplication.common.HintTextField;
import collegeapplication.department.DepartmentData;
import collegeapplication.subject.SubjectData;

/*
 * Title : AddStudentDialog.java
 * Created by : Ajaysinh Rathod
 * Purpose : For adding new student or edit student details
 * Mail : ajaysinhrathod1290@gmail.com
 */
@SuppressWarnings("serial")
public class AddStudentDialog extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField studentIDfield;
	private JTextField totalCreditsfield;
	private JComboBox<String> departmentnamecombo, gendercombo;
	private JSpinner birthdatespinner;
	private JButton addstudentbutton;
	private File file;
	private AdminMain am;
	private JLabel profilepiclabel;
	private JLabel Errorlabel;
	private static AddStudentDialog dialog;
	StudentPanel sp;
	private JLabel headerlabel;
	private Student student;
	private JTextField studentNamefield;
	private JTextField CPAfield;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		// If translucent windows aren't supported, exit.
		JFrame.setDefaultLookAndFeelDecorated(true);
		try {
			dialog = new AddStudentDialog();
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * 
	 */
	public AddStudentDialog() {
		super(new JFrame(), true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);
		setBounds(350, 20, 710, 450);
		getContentPane().setLayout(null);
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		headerlabel = new JLabel("Add New Student");
		headerlabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		headerlabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerlabel.setBounds(0, 0, 710, 40);
		getContentPane().add(headerlabel);

		headerlabel.setBackground(new Color(32, 178, 170));
		headerlabel.setOpaque(true);
		headerlabel.setForeground(new Color(255, 255, 255));
		headerlabel.setFont(new Font("Arial", Font.BOLD, 23));
		headerlabel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.LIGHT_GRAY));

		departmentnamecombo = new JComboBox<String>(new DepartmentData().getDepartmentName());
		departmentnamecombo.setForeground(Color.DARK_GRAY);
		departmentnamecombo.setToolTipText("Department");
		departmentnamecombo.setFont(new Font("Segoe UI Historic", Font.PLAIN, 18));
		departmentnamecombo.addActionListener(this);
		departmentnamecombo.setBackground(new Color(255, 255, 255));
		departmentnamecombo.setBounds(20, 55, 320, 40);
		departmentnamecombo.setFocusable(false);
		getContentPane().add(departmentnamecombo);
		// id
		JLabel rollnumberlabel = new JLabel("Student ID(*)");
		rollnumberlabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		rollnumberlabel.setBounds(20, 100, 134, 30);
		getContentPane().add(rollnumberlabel);
		// id
		studentIDfield = new JTextField();
		studentIDfield.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				studentIDfield.setFocusable(true);
			}
		});
		studentIDfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					studentIDfield.setFocusable(false);
				}

			}
		});
		studentIDfield.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		studentIDfield.setColumns(10);
		studentIDfield.setBounds(20, 132, 320, 40);
		getContentPane().add(studentIDfield);
		// name
		JLabel studentNamelabel = new JLabel("Student Name(*)");
		studentNamelabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		studentNamelabel.setBounds(360, 100, 166, 30);
		getContentPane().add(studentNamelabel);

		studentNamefield = new JTextField();
		studentNamefield.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				studentNamefield.setFocusable(true);
			}
		});
		studentNamefield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					studentNamefield.setFocusable(false);
				}

			}
		});
		studentNamefield.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		studentNamefield.setColumns(10);
		studentNamefield.setBounds(360, 132, 320, 40);
		getContentPane().add(studentNamefield);

		// DOB
		JLabel lblDateOfBirth = new JLabel("Date of Birth(*)");
		lblDateOfBirth.setForeground(Color.DARK_GRAY);
		lblDateOfBirth.setFont(new Font("Segoe UI Historic", Font.PLAIN, 19));
		lblDateOfBirth.setBounds(20, 177, 166, 30);
		getContentPane().add(lblDateOfBirth);

		birthdatespinner = new JSpinner();
		birthdatespinner.setToolTipText("Date Of Birth");
		birthdatespinner.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					birthdatespinner.setFocusable(false);
				}
			}
		});
		birthdatespinner.setFont(new Font("Segoe UI Historic", Font.PLAIN, 18));
		SimpleDateFormat model = new SimpleDateFormat("dd-MM-yyyy");
		birthdatespinner.setModel(new SpinnerDateModel());
		birthdatespinner.setEditor(new JSpinner.DateEditor(birthdatespinner, model.toPattern()));
		birthdatespinner.setBounds(20, 210, 320, 40);
		getContentPane().add(birthdatespinner);

		// gender
		JLabel lblGender = new JLabel("Gender(*)");
		lblGender.setForeground(Color.DARK_GRAY);
		lblGender.setFont(new Font("Segoe UI Historic", Font.PLAIN, 19));
		lblGender.setBounds(360, 177, 166, 30);
		getContentPane().add(lblGender);

		gendercombo = new JComboBox<String>();
		gendercombo.setToolTipText("Gender");
		gendercombo
				.setModel(new DefaultComboBoxModel<String>(new String[] { "---Select Gender---", "Male", "Female" }));
		gendercombo.setFont(new Font("Segoe UI Historic", Font.PLAIN, 16));
		gendercombo.setBackground(Color.WHITE);
		gendercombo.addActionListener(this);
		gendercombo.setBounds(360, 210, 320, 40);
		gendercombo.setFocusable(false);
		getContentPane().add(gendercombo);
		// CPA
		JLabel lblCPA = new JLabel("CPA(*)");
		lblCPA.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblCPA.setBounds(20, 255, 166, 30);
		getContentPane().add(lblCPA);

		CPAfield = new JTextField();
		CPAfield.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				CPAfield.setFocusable(true);
			}
		});
		CPAfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					CPAfield.setFocusable(false);
				}

			}
		});
		CPAfield.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		CPAfield.setColumns(10);
		CPAfield.setBounds(20, 287, 320, 40);
		getContentPane().add(CPAfield);
		// Total credits
		JLabel lbltotalCred = new JLabel("Total Credits(*)");
		lbltotalCred.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lbltotalCred.setBounds(360, 255, 166, 30);
		getContentPane().add(lbltotalCred);

		totalCreditsfield = new JTextField();
		totalCreditsfield.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				totalCreditsfield.setFocusable(true);
			}
		});
		totalCreditsfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					totalCreditsfield.setFocusable(false);
				}

			}
		});
		totalCreditsfield.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		totalCreditsfield.setColumns(10);
		totalCreditsfield.setBounds(360, 287, 320, 40);
		getContentPane().add(totalCreditsfield);

		addstudentbutton = new JButton("Add Student");
		addstudentbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		addstudentbutton.setForeground(new Color(255, 255, 255));
		addstudentbutton.setBackground(new Color(32, 178, 170));
		addstudentbutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		addstudentbutton.addActionListener(this);
		addstudentbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addstudentbutton.setBounds(280, 360, 150, 40);
		addstudentbutton.setFocusable(false);
		getContentPane().add(addstudentbutton);

		Errorlabel = new JLabel("This is required question !");
		Errorlabel.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(255, 0, 0)));
		Errorlabel.setHorizontalAlignment(SwingConstants.LEFT);
		Errorlabel.setForeground(new Color(255, 69, 0));
		Errorlabel.setFont(new Font("Calibri", Font.PLAIN, 16));
		Errorlabel.setVisible(false);
		Errorlabel.setBounds(10, 90, 320, 26);
		getContentPane().add(Errorlabel);

	}

	public AddStudentDialog(AdminMain am, Student s) {
		this();
		this.am = am;
		this.student = s;
		departmentnamecombo.setSelectedItem(new DepartmentData().getdept_Name(s.getDept_id()));
		studentIDfield.setText(s.getStudentID());
		studentIDfield.setEditable(false);
		studentNamefield.setText(s.getStudentName());
		birthdatespinner.setValue(s.getBirthDateInDateFormat());
		gendercombo.setSelectedItem(s.getGender() + "");
		CPAfield.setText(s.getCpa());
		totalCreditsfield.setText(s.getTotalCredits());
		headerlabel.setText("Edit Student Details");
		addstudentbutton.setText("Update Student");
		departmentnamecombo.setEnabled(false);
		studentIDfield.setEditable(false);
		;
		// optionalsubjectcombo.setEnabled(false);
		departmentnamecombo.setRenderer(new DefaultListCellRenderer() {
			@Override
			public void paint(Graphics g) {
				setForeground(Color.BLACK);
				setBackground(Color.WHITE);
				super.paint(g);
			}
		});

	}

	public AddStudentDialog(JTable table, StudentPanel studentpanel) {
		this();
		this.sp = studentpanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Errorlabel.setVisible(false);
		Errorlabel.setText("This is required question  !");

		if (e.getSource() == addstudentbutton) {

			if (studentIDfield.getText().isEmpty()) {
				showerror();
			} else if (studentNamefield.getText().isEmpty()) {
				showerror();
			} else if (gendercombo.getSelectedIndex() == 0) {
				showerror();
			} else if (CPAfield.getText().isEmpty()) {
				showerror();
			} else if (totalCreditsfield.getText().isEmpty()) {
				showerror();
			} else if (departmentnamecombo.getSelectedIndex() == 0) {
				showerror();
			} else {
				try {
					Student s = new Student();
					s.setStudentID(studentIDfield.getText());
					s.setStudentName(studentNamefield.getText());
					s.setDept_id(
							new DepartmentData().getDepartmentcode(departmentnamecombo.getSelectedItem() + ""));
					// s.setDept_Name(departmentnamecombo.getSelectedItem() + "");
					s.setGender(gendercombo.getSelectedItem() + "");
					Date date = (Date) birthdatespinner.getValue();
					s.setBirthDate(new SimpleDateFormat("dd-MM-yyyy").format(date));
					s.setCpa(CPAfield.getText());
					s.setTotalCredits(totalCreditsfield.getText());
					System.out.println(s.getDept_id());
					int result = 0;
					if (sp != null) {
						result = new StudentData().addStudent(s);
						System.out.println(s.getDept_id());
					} else if (am != null && student != null) {
						result = new StudentData().updateStudentData(student, s);
					}
					if (result > 0) {

						if (sp != null) {
							sp.createtablemodel();

						} else if (am != null && student != null) {

							am.viewstudentpanel.setVisible(false);
							am.viewstudentpanel = new ViewStudentPanel(s, am, am.viewstudentpanel.lastpanel);
							am.viewstudentpanel.setVisible(true);
							am.viewstudentpanel.setLocation(am.panelx, am.panely);
							am.getContentPane().add(am.viewstudentpanel);

						}
						this.dispose();
					}

				} catch (NumberFormatException exp) {
					Errorlabel.setVisible(true);
					Errorlabel.setText("Characters are not allowed!");
					Errorlabel.setBounds(studentIDfield.getX(), studentIDfield.getY() + studentIDfield.getHeight(),
							400, 26);
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

// @SuppressWarnings("serial")
// class RollNumberAvailableException extends Exception {
// public RollNumberAvailableException() {
// super("Roll number already available");
// }
// }