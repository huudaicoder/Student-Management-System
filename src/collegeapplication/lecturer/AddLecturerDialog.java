package collegeapplication.lecturer;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
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
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import collegeapplication.admin.AdminMain;
import collegeapplication.department.DepartmentData;

/*
 * Title : AddLecturerDialog.java
 * Created by : Ajaysinh Rathod
 * Purpose : For adding new lecturer 
 * Mail : ajaysinhrathod1300@gmail.com
 */

@SuppressWarnings("serial")
public class AddLecturerDialog extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField lectureridfield;
	private JTextField lecturernamefield;
	private JTextField statefield;
	private JTextField cityfield;
	private JTextField emailidfield;
	private JTextField contactnumberfield;
	private static AddLecturerDialog dialog;
	private JButton addlecturerbutton;
	private JLabel Errorlabel;
	// private JSpinner birthdatespinner;
	LecturerPanel fp;
	private AdminMain am;
	private Lecturer lecturer;
	private JLabel headerlabel;
	private JComboBox<String> departmentnamecombo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog = new AddLecturerDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddLecturerDialog() {

		super(dialog, "", Dialog.ModalityType.APPLICATION_MODAL);

		setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		setBounds(350, 20, 710, 410);
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().setLayout(null);
		headerlabel = new JLabel("Add New Lecturer");
		headerlabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		headerlabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerlabel.setBounds(0, 0, 710, 40);
		getContentPane().add(headerlabel);

		headerlabel.setBackground(new Color(32, 178, 170));
		headerlabel.setOpaque(true);
		headerlabel.setForeground(new Color(255, 255, 255));
		headerlabel.setFont(new Font("Arial", Font.BOLD, 23));
		headerlabel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.LIGHT_GRAY));

		// id
		JLabel lecturerIDlabel = new JLabel("Lecturer ID(*)");
		lecturerIDlabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lecturerIDlabel.setBounds(20, 53, 134, 30);
		getContentPane().add(lecturerIDlabel);

		lectureridfield = new JTextField();
		lectureridfield.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				lectureridfield.setFocusable(true);
			}
		});
		lectureridfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					lectureridfield.setFocusable(false);
				}

			}
		});
		lectureridfield.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lectureridfield.setColumns(10);
		lectureridfield.setBounds(20, 85, 320, 40);
		getContentPane().add(lectureridfield);
		// name
		JLabel lecturernamelabel = new JLabel("Lecturer Name(*)");
		lecturernamelabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lecturernamelabel.setBounds(360, 53, 166, 30);
		getContentPane().add(lecturernamelabel);

		lecturernamefield = new JTextField();
		lecturernamefield.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				lecturernamefield.setFocusable(true);
			}
		});
		lecturernamefield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					lecturernamefield.setFocusable(false);
				}

			}
		});
		lecturernamefield.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lecturernamefield.setColumns(10);
		lecturernamefield.setBounds(360, 85, 320, 40);
		getContentPane().add(lecturernamefield);
		// department
		JLabel lblDepartment = new JLabel("Department(*)");
		lblDepartment.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblDepartment.setBounds(20, 145, 134, 20);
		getContentPane().add(lblDepartment);

		departmentnamecombo = new JComboBox<String>(new DepartmentData().getDepartmentName());
		departmentnamecombo.setForeground(Color.DARK_GRAY);
		departmentnamecombo.setToolTipText("Department");
		departmentnamecombo.setFont(new Font("Segoe UI Historic", Font.PLAIN, 18));
		departmentnamecombo.addActionListener(this);
		departmentnamecombo.setBackground(new Color(255, 255, 255));
		departmentnamecombo.setBounds(20, 173, 320, 40);
		departmentnamecombo.setFocusable(false);
		getContentPane().add(departmentnamecombo);

		JLabel lblCity = new JLabel("City");
		lblCity.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblCity.setBounds(360, 141, 143, 30);
		getContentPane().add(lblCity);

		cityfield = new JTextField();
		cityfield.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		cityfield.setColumns(10);
		cityfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					cityfield.setFocusable(false);
				}

			}
		});
		cityfield.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				cityfield.setFocusable(true);
			}
		});
		cityfield.setBounds(360, 173, 320, 40);
		getContentPane().add(cityfield);

		JLabel lblEmailId = new JLabel("Email");
		lblEmailId.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblEmailId.setBounds(20, 231, 134, 30);
		getContentPane().add(lblEmailId);

		emailidfield = new JTextField();
		emailidfield.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		emailidfield.setColumns(10);
		emailidfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					emailidfield.setFocusable(false);
				}

			}
		});
		emailidfield.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				emailidfield.setFocusable(true);
			}
		});
		emailidfield.setBounds(20, 260, 322, 40);
		getContentPane().add(emailidfield);

		JLabel lblPhoneNumber = new JLabel("Contact Number");
		lblPhoneNumber.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblPhoneNumber.setBounds(360, 226, 185, 30);
		getContentPane().add(lblPhoneNumber);

		contactnumberfield = new JTextField();
		contactnumberfield.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		contactnumberfield.setColumns(10);
		contactnumberfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					contactnumberfield.setFocusable(false);
				}

			}
		});
		contactnumberfield.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				contactnumberfield.setFocusable(true);
			}
		});
		contactnumberfield.setBounds(360, 260, 320, 40);
		getContentPane().add(contactnumberfield);

		addlecturerbutton = new JButton("Add Lecturer");
		addlecturerbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		addlecturerbutton.setForeground(new Color(255, 255, 255));
		addlecturerbutton.setBackground(new Color(32, 178, 170));
		addlecturerbutton.setFont(new Font("Segoe UI", Font.BOLD, 16));
		addlecturerbutton.setFocusable(false);
		addlecturerbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addlecturerbutton.addActionListener(this);
		addlecturerbutton.setBounds(280, 320, 150, 35);
		getContentPane().add(addlecturerbutton);

		Errorlabel = new JLabel("This is required question !");
		Errorlabel.setForeground(new Color(255, 0, 0));
		Errorlabel.setFont(new Font("Calibri", Font.PLAIN, 16));
		Errorlabel.setBounds(20, 127, 320, 17);
		Errorlabel.setVisible(false);
		getContentPane().add(Errorlabel);

	}

	public AddLecturerDialog(LecturerPanel lecturerpanel) {

		this();
		this.fp = lecturerpanel;
	}

	public AddLecturerDialog(AdminMain am, Lecturer f) {
		this();
		this.lecturer = f;
		this.am = am;
		lectureridfield.setText(f.getLecturerid() + "");
		lecturernamefield.setText(f.getLecturername());
		departmentnamecombo.setSelectedItem(new DepartmentData().getdept_Name(f.getDept_id()));
		cityfield.setText(f.getCity());
		emailidfield.setText(f.getEmail());
		contactnumberfield.setText(f.getContactnumber());
		// birthdatespinner.setValue(f.getBirthDateInDateFormat());
		headerlabel.setText("Edit Lecturer Details");
		addlecturerbutton.setText("Update Lecturer");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Errorlabel.setVisible(false);
		if (e.getSource() == addlecturerbutton) {
			if (lecturernamefield.getText().isEmpty()) {
				showerror();
			} else if (lectureridfield.getText().isEmpty()) {
				showerror();
			} else if (departmentnamecombo.getSelectedIndex() == 0) {
				showerror();
			} else {
				try {
					Lecturer f = new Lecturer();
					f.setLecturerid((lectureridfield.getText()));
					f.setLecturername(lecturernamefield.getText());
					f.setDept_id(new DepartmentData().getDepartmentcode(departmentnamecombo.getSelectedItem() + ""));
					f.setCity(cityfield.getText());
					f.setEmail(emailidfield.getText());
					f.setContactnumber(contactnumberfield.getText());
					int result = 0;
					// if (fp != null) {
					// result = new LecturerData().addLecturerData(f);
					// } else if (am != null && lecturer != null) {
					// result = new LecturerData().updateLecturerData(lecturer, f);
					// }

					if (new LecturerData().isExist(f.getLecturerid())) {
						result = new LecturerData().updateLecturerData(lecturer, f);
					} else {
						result = new LecturerData().addLecturerData(f);
					}
					if (result > 0) {
						if (fp != null) {
							fp.createtablemodel();

						} else if (am != null && lecturer != null) {
							am.viewlecturerpanel.setVisible(false);
							am.viewlecturerpanel = new ViewLecturerPanel(
									new LecturerData().getLecturerInfobyId(f.getLecturerid()), am,
									am.viewlecturerpanel.lastpanel);
							am.viewlecturerpanel.setVisible(true);
							am.viewlecturerpanel.setLocation(am.panelx, am.panely);
							am.getContentPane().add(am.viewlecturerpanel);

						}
						this.dispose();

					}

				} catch (Exception exp) {
					exp.printStackTrace();
				}
			}
		}
	}

	public void showerror() {
		JOptionPane.showMessageDialog(new JFrame(), "Please fill in all fields marked with an asterisk(*). !", "Dialog",
				JOptionPane.WARNING_MESSAGE);
	}
}
