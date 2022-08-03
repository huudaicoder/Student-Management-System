package collegeapplication.department;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import collegeapplication.admin.AdminMain;
import collegeapplication.common.HintTextField;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class AddDepartmentDialog extends JDialog implements ActionListener {

	private JTextField departmentcodefield;
	private JTextField departmentnamefield;
	private JTextField phoneNumberfield;
	private JComboBox<String> semoryearcombo;
	private JLabel lblError;
	private DepartmentPanel departmentpanel;
	private HintTextField buildingfield;
	private AdminMain am;
	private Department department;
	private JLabel lblAddNewDepartment;
	private JButton adddepartmentbutton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddDepartmentDialog dialog = new AddDepartmentDialog();

			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddDepartmentDialog(DepartmentPanel departmentpanel) {
		this();
		this.departmentpanel = departmentpanel;
	}

	public AddDepartmentDialog() {

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 480, 452);
		getContentPane().setLayout(null);

		lblAddNewDepartment = new JLabel("Add New Department");
		lblAddNewDepartment.setForeground(new Color(255, 255, 255));
		lblAddNewDepartment.setBackground(new Color(32, 178, 170));
		lblAddNewDepartment.setOpaque(true);
		lblAddNewDepartment.setFont(new Font("Arial", Font.BOLD, 23));
		lblAddNewDepartment.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddNewDepartment.setBounds(0, 0, 480, 55);
		getContentPane().add(lblAddNewDepartment);
		// code
		JLabel lblDepartmentCode = new JLabel("Department ID(*)");
		lblDepartmentCode.setBorder(new EmptyBorder(0, 0, 0, 5));
		lblDepartmentCode.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblDepartmentCode.setHorizontalAlignment(SwingConstants.LEFT);
		lblDepartmentCode.setBounds(10, 79, 200, 24);
		lblDepartmentCode.setFocusable(true);
		getContentPane().add(lblDepartmentCode);

		departmentcodefield = new HintTextField("");
		departmentcodefield.setText(String.valueOf(new DepartmentData().getTotalDepartment() + 1));
		departmentcodefield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		departmentcodefield.setBounds(215, 72, 240, 40);
		departmentcodefield.setEditable(false);
		getContentPane().add(departmentcodefield);
		departmentcodefield.setColumns(10);
		// name
		JLabel lblDepartmentName = new JLabel("Department Name(*)");
		lblDepartmentName.setHorizontalAlignment(SwingConstants.LEFT);
		lblDepartmentName.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblDepartmentName.setBorder(new EmptyBorder(0, 0, 0, 5));
		lblDepartmentName.setBounds(10, 147, 200, 24);
		getContentPane().add(lblDepartmentName);

		departmentnamefield = new HintTextField("");
		departmentnamefield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		departmentnamefield.setColumns(10);
		departmentnamefield.setBounds(215, 141, 240, 40);
		getContentPane().add(departmentnamefield);
		// building
		JLabel lblBuilding = new JLabel("Building");
		lblBuilding.setHorizontalAlignment(SwingConstants.LEFT);
		lblBuilding.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblBuilding.setBorder(new EmptyBorder(0, 0, 0, 5));
		lblBuilding.setBounds(10, 218, 200, 24);
		getContentPane().add(lblBuilding);

		buildingfield = new HintTextField("");
		buildingfield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		buildingfield.setColumns(10);
		buildingfield.setBounds(215, 212, 240, 40);
		getContentPane().add(buildingfield);
		// phone
		JLabel lblphoneNumber = new JLabel("Contact Number");
		lblphoneNumber.setHorizontalAlignment(SwingConstants.LEFT);
		lblphoneNumber.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		lblphoneNumber.setBorder(new EmptyBorder(0, 0, 0, 5));
		lblphoneNumber.setBounds(10, 284, 200, 24);
		getContentPane().add(lblphoneNumber);

		phoneNumberfield = new HintTextField("");
		phoneNumberfield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
		phoneNumberfield.setColumns(10);
		phoneNumberfield.setBounds(215, 278, 240, 40);
		getContentPane().add(phoneNumberfield);

		adddepartmentbutton = new JButton("Add Department");
		adddepartmentbutton.setBackground(new Color(32, 178, 170));
		adddepartmentbutton.setForeground(new Color(255, 255, 255));
		adddepartmentbutton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		adddepartmentbutton.setBounds(310, 373, 150, 37);
		adddepartmentbutton.addActionListener(this);
		getContentPane().add(adddepartmentbutton);

		lblError = new JLabel("This is required question !");
		lblError.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(255, 0, 0)));
		lblError.setForeground(new Color(255, 0, 0));
		lblError.setFont(new Font("Candara", Font.PLAIN, 15));
		lblError.setVisible(false);
		lblError.setBounds(215, 115, 355, 21);
		getContentPane().add(lblError);

		JLabel label = new JLabel("");
		label.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		label.setBounds(0, 346, 470, 14);
		getContentPane().add(label);

	}

	public AddDepartmentDialog(AdminMain am, Department s) {
		this();
		this.am = am;
		this.department = s;
		departmentcodefield.setText(s.getDept_ID());
		departmentnamefield.setText(s.getDept_Name());
		buildingfield.setText(s.getBuilding());
		phoneNumberfield.setText(s.getPhoneNumber());
		lblAddNewDepartment.setText("Edit Department Details");
		adddepartmentbutton.setText("Update Department");
		departmentcodefield.setEditable(false);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		lblError.setForeground(Color.red);
		lblError.setVisible(false);
		lblError.setText("This is required question !");
		String departmentname = departmentnamefield.getText();
		String departmentcode = departmentcodefield.getText();
		String building = buildingfield.getText();
		String phoneNum = phoneNumberfield.getText();
		Department d = new Department();
		d.setDept_ID(departmentcode);
		d.setDept_Name(departmentname);
		d.setBuilding(building);
		d.setPhoneNumber(phoneNum);
		System.out.println(departmentcode);
		if (departmentname.isEmpty()) {
			showerror();
		} else {

			try {
				DepartmentData c = new DepartmentData();
				if (new DepartmentData().isDepartmentCodeExist(departmentcode)) {
					int result = c.updateDepartment(departmentcode, departmentname, building, phoneNum);
					am.viewdepartmentpanel.setVisible(false);
					am.viewdepartmentpanel = new ViewDepartmentPanel(d, am, am.viewdepartmentpanel.lastpanel);
					am.viewdepartmentpanel.setVisible(true);
					am.viewdepartmentpanel.setLocation(am.panelx, am.panely);
					am.getContentPane().add(am.viewdepartmentpanel);
					// departmentpanel.updatetableData();
					this.dispose();
				} else {
					int result = c.addDepartment(departmentcode, departmentname, building, phoneNum);
					if (result > 0) {

						if (departmentpanel != null) {
							departmentpanel.updatetableData();
						}
						this.dispose();
					}
				}

			} catch (NumberFormatException nexp) {
				lblError.setVisible(true);
				lblError.setBounds(phoneNumberfield.getX(),
						phoneNumberfield.getY() + phoneNumberfield.getHeight(), lblError.getWidth(), 21);
				lblError.setText("Characters are not allowed !");
			}

		}

	}

	public void showerror() {
		JOptionPane.showMessageDialog(new JFrame(), "Please fill in all fields marked with an asterisk(*). !", "Dialog",
				JOptionPane.WARNING_MESSAGE);
	}

}
