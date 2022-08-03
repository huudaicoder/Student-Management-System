package collegeapplication.lecturer;

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

/*
 * Title : LecturerPanel.java
 * Created by : Ajaysinh Rathod
 * Purpose : To display all lecturer in table or photoview
 * Mail : ajaysinhrathod1290@gmail.com
 */

@SuppressWarnings("serial")
public class LecturerPanel extends JPanel implements ActionListener {
	private JButton addnewfaculity;
	public JButton viewbutton;
	public JTable table;
	public AdminMain am;
	public JPanel tableviewpanel;
	public JScrollPane photoviewscrollpane;
	private String condition = "";
	private JLabel headinglabel;

	/**
	 * Create the panel.
	 * 
	 * @param am
	 */
	public LecturerPanel(AdminMain am) {
		this();
		this.am = am;
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() > 1 && e.getButton() == MouseEvent.BUTTON1) {
					JTable t = (JTable) e.getSource();
					String fid = t.getValueAt(t.getSelectedRow(), 0) + "";
					Lecturer f = new LecturerData().getLecturerInfobyId(fid);

					am.viewlecturerpanel = new ViewLecturerPanel(f, am, am.lecturerpanel);
					am.viewlecturerpanel.setVisible(true);
					am.lecturerpanel.setVisible(false);
					am.viewlecturerpanel.setLocation(am.panelx, 0);
					am.viewlecturerpanel.setVisible(true);
					am.viewlecturerpanel.setFocusable(true);
					am.contentPane.add(am.viewlecturerpanel);

				}

			}
		});
	}

	private LecturerPanel() {
		this.setName("Lecturer Panel");
		setBackground(Color.WHITE);
		this.setSize(1116, 705);
		setLayout(null);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setBounds(10, 0, 1096, 183);
		add(panel);
		panel.setLayout(null);
		headinglabel = new JLabel("List Lecturer");
		headinglabel.setIcon(null);
		headinglabel.setBounds(10, 65, 272, 44);
		panel.add(headinglabel);
		headinglabel.setBackground(new Color(32, 178, 170));
		headinglabel.setHorizontalAlignment(SwingConstants.LEFT);
		headinglabel.setForeground(Color.WHITE);
		headinglabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
		headinglabel.setOpaque(true);

		addnewfaculity = new JButton("Add Lecturer");
		addnewfaculity.setBorder(new EmptyBorder(0, 0, 0, 0));
		addnewfaculity.setBounds(932, 139, 153, 33);
		panel.add(addnewfaculity);
		addnewfaculity.setFocusable(false);
		addnewfaculity.setForeground(new Color(0, 128, 128));
		addnewfaculity.setFont(new Font("Segoe UI", Font.BOLD, 15));
		addnewfaculity.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addnewfaculity.setBackground(new Color(255, 255, 255));
		addnewfaculity.addActionListener(this);

		tableviewpanel = new JPanel();
		tableviewpanel.setBackground(Color.WHITE);
		tableviewpanel.setBounds(0, 189, 1116, 528);
		add(tableviewpanel);
		tableviewpanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 1095, 483);
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		for (Component c : scrollPane.getComponents()) {
			c.setBackground(Color.white);
		}
		tableviewpanel.add(scrollPane);

		table = new JTable();
		table = new JTable();
		table.setCursor(new Cursor(Cursor.HAND_CURSOR));
		table.setBorder(new LineBorder(Color.LIGHT_GRAY));
		table.getTableHeader().setBackground(new Color(32, 178, 170));
		table.getTableHeader().setForeground(Color.white);
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
		table.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		table.getTableHeader().setPreferredSize(new Dimension(50, 40));
		table.setDragEnabled(false);
		table.setRowHeight(40);
		createtablemodel();
		table.setSelectionBackground(new Color(240, 255, 255));
		table.setFocusable(false);
		table.setDefaultEditor(Object.class, null);
		table.setGridColor(Color.LIGHT_GRAY);
		table.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(table);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == addnewfaculity) {
			AddLecturerDialog afd = new AddLecturerDialog(this);
			afd.setLocationRelativeTo(null);
			afd.setVisible(true);
		}
	}

	public void createtablemodel() {
		ResultSet rs = new LecturerData().getLecturerInfo();
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
		table.getColumnModel().getColumn(1).setMaxWidth(250);
		table.getColumnModel().getColumn(2).setMaxWidth(450);
		table.getColumnModel().getColumn(3).setMaxWidth(150);
		table.getColumnModel().getColumn(4).setMaxWidth(150);
		// table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
	}

}
