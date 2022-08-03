package collegeapplication.subject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;

import net.proteanit.sql.DbUtils;

/*
 * Title : AddSubjectDialog.java
 * Created by : Ajaysinh Rathod
 * Purpose : For adding new subject to cource
 * Mail : ajaysinhrathod1290@gmail.com
 */

@SuppressWarnings("serial")
public class AddSubjectDialog extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField subjectcodefield;
	private JTextField subjectnamefield;
	private JTextField factorfield;
	private JTextField practicalmarksfield;
	private JButton addsubject;
	private JComboBox<String> courcetypecombo;
	private String departmentID;
	private JTable table;
	private JLabel lblError;
	private JTextField creditsfield;
	private static AddSubjectDialog dialog;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			dialog = new AddSubjectDialog("IT", null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddSubjectDialog(String courceCode, JTable datatable) {

		super(dialog, "", Dialog.ModalityType.APPLICATION_MODAL);
		setBackground(new Color(0, 128, 128));
		this.table = datatable;

		this.departmentID = courceCode;
		setResizable(false);
		setSize(518, 450);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		// code
		JLabel lblNewLabel = new JLabel("Subject Code");
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel.setBounds(30, 71, 132, 35);
		contentPanel.add(lblNewLabel);
		subjectcodefield = new JTextField();
		subjectcodefield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					subjectcodefield.setFocusable(false);
				}
			}
		});
		subjectcodefield.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				subjectcodefield.setFocusable(true);
			}
		});
		subjectcodefield.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		subjectcodefield.setColumns(10);
		subjectcodefield.setBounds(172, 70, 320, 40);
		contentPanel.add(subjectcodefield);
		// name
		JLabel lblSubjectName = new JLabel("Subject Name");
		lblSubjectName.setForeground(Color.DARK_GRAY);
		lblSubjectName.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblSubjectName.setBounds(30, 134, 132, 35);
		contentPanel.add(lblSubjectName);

		subjectnamefield = new JTextField();
		subjectnamefield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					subjectnamefield.setFocusable(false);
				}
			}
		});
		subjectnamefield.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				subjectnamefield.setFocusable(true);
			}
		});
		subjectnamefield.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		subjectnamefield.setColumns(10);
		subjectnamefield.setBounds(172, 136, 320, 40);
		contentPanel.add(subjectnamefield);
		// factor
		JLabel lblFactor = new JLabel("Factor");
		lblFactor.setForeground(Color.DARK_GRAY);
		lblFactor.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblFactor.setBounds(30, 197, 132, 35);
		contentPanel.add(lblFactor);

		factorfield = new JTextField();
		factorfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					factorfield.setFocusable(false);
				}
			}
		});
		factorfield.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				factorfield.setFocusable(true);
			}
		});
		factorfield.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		factorfield.setColumns(10);
		factorfield.setBounds(172, 199, 320, 40);
		contentPanel.add(factorfield);
		// credits
		JLabel lblCredits = new JLabel("Credit");
		lblCredits.setForeground(Color.DARK_GRAY);
		lblCredits.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblCredits.setBounds(30, 265, 132, 35);
		contentPanel.add(lblCredits);

		creditsfield = new JTextField();
		creditsfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					creditsfield.setFocusable(false);
				}
			}
		});
		creditsfield.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				creditsfield.setFocusable(true);
			}
		});
		creditsfield.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		creditsfield.setColumns(10);
		creditsfield.setBounds(172, 267, 320, 40);
		contentPanel.add(creditsfield);
		// Header
		JLabel headerlabel = new JLabel("Add New Subject");
		headerlabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerlabel.setBackground(new Color(32, 178, 170));
		headerlabel.setOpaque(true);
		headerlabel.setForeground(new Color(255, 255, 255));
		headerlabel.setFont(new Font("Arial", Font.BOLD, 23));
		headerlabel.setBounds(0, 0, 518, 44);
		headerlabel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.LIGHT_GRAY));
		contentPanel.add(headerlabel);

		addsubject = new JButton("Add Subject");
		addsubject.setBorder(new EmptyBorder(0, 0, 0, 0));
		addsubject.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				HandCursor();

			}

			public void mouseExited(MouseEvent e) {
				DefaultCursor();
			}
		});

		addsubject.setBackground(new Color(32, 178, 170));
		addsubject.setForeground(Color.WHITE);
		addsubject.setFont(new Font("Segoe UI", Font.BOLD, 15));
		addsubject.setBounds(350, 365, 139, 35);
		addsubject.addActionListener(this);
		addsubject.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addsubject.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addsubject.setFocusable(false);

		contentPanel.add(addsubject);

		JLabel borderlabel = new JLabel("");
		borderlabel.setBounds(0, 340, 512, 14);
		contentPanel.add(borderlabel);
		borderlabel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(192, 192, 192)));

		lblError = new JLabel("This is required question !");
		lblError.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(255, 0, 0)));
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Candara", Font.PLAIN, 17));
		lblError.setBounds(172, 107, 320, 30);
		lblError.setVisible(false);
		contentPanel.add(lblError);

	}

	public void HandCursor() {
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	public void DefaultCursor() {
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		lblError.setVisible(false);
		lblError.setText("This is required question..!=-");
		if (subjectnamefield.getText().isEmpty() || subjectcodefield.getText().isEmpty()) {
			showerror();
		} else if (factorfield.getText().isEmpty()) {
			showerror();
		} else if (creditsfield.getText().isEmpty()) {
			showerror();
		} else {
			if (e.getSource() == addsubject) {
				String numbererror = "";
				try {
					String subjectcode = subjectcodefield.getText();
					String subjectname = subjectnamefield.getText();
					Float factor = Float.parseFloat(factorfield.getText());
					Integer credit = Integer.parseInt(creditsfield.getText());
					Subject su = new Subject();
					su.setSubjectName(subjectname);
					su.setSubjectID(subjectcode);
					su.setDept_ID(departmentID);
					su.setFactor(factor);
					su.setCredit(credit);
					int result = new SubjectData().addSubject(su);
					if (result == 1) {
						ResultSet st = new SubjectData().getSubjectinfo(departmentID);
						table.setModel(DbUtils.resultSetToTableModel(st));
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
						this.dispose();
					}
				} catch (NumberFormatException exp) {
					if (numbererror.equals("theorymarks")) {
						lblError.setBounds(factorfield.getX(),
								factorfield.getY() + factorfield.getHeight(), 320, 30);
					}
					lblError.setVisible(true);
					lblError.setText("Characters are not allowed !");
				}
			}
		}

	}

	public void showerror() {
		JOptionPane.showMessageDialog(new JFrame(), "Please fill in all fields marked with an asterisk(*) !", "Dialog",
				JOptionPane.WARNING_MESSAGE);
	}
}
