package collegeapplication.department;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

import collegeapplication.admin.AdminMain;

//import org.w3c.dom.events.MouseEvent;

import collegeapplication.common.ScrollPaneUtil;
import net.proteanit.sql.DbUtils;

import javax.swing.border.EmptyBorder;

/*
 * Title : CourcePanel.java
 * Created by : Ajaysinh Rathod
 * Purpose : Displaying all the cources table using this file
 * Mail : ajaysinhrathod1290@gmail.com
 */
@SuppressWarnings("serial")
public class DepartmentPanel extends JPanel implements ActionListener {
	private JScrollPane scrollPane;
	private JButton addcource, rollgenerator;
	public AdminMain am;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public DepartmentPanel(AdminMain am) {
		this();
		this.am = am;
	}

	public DepartmentPanel() {

		setForeground(Color.GRAY);
		setBackground(Color.WHITE);
		this.setSize(1116, 705);
		setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBounds(10, 200, 1096, 500);
		for (Component c : scrollPane.getComponents()) {
			c.setBackground(Color.white);
		}
		add(scrollPane);

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
		this.updatetableData();
		table.setDefaultEditor(Object.class, null);
		table.setCursor(new Cursor(Cursor.HAND_CURSOR));
		table.setGridColor(Color.LIGHT_GRAY);
		table.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(table);
		// table.setEnabled(false);
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() > 1 && e.getButton() == MouseEvent.BUTTON1) {

					JTable t = (JTable) e.getSource();
					int row = t.getSelectedRow();
					String strroll = table.getValueAt(row, 0) + "";
					// System.out.println(strroll);
					long ID = Long.parseLong(strroll);
					Department s = new DepartmentData().getDepartmentDetails(ID);
					if (am != null) {
						am.viewdepartmentpanel = new ViewDepartmentPanel(s, am, am.departmentpanel);
						am.viewdepartmentpanel.setVisible(true);
						am.departmentpanel.setVisible(false);
						am.viewdepartmentpanel.setLocation(am.panelx, 0);
						am.viewdepartmentpanel.setVisible(true);
						am.viewdepartmentpanel.setFocusable(true);
						am.contentPane.add(am.viewdepartmentpanel);
					}
				}

			}
		});

		JPanel panel = new JPanel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setBounds(10, 0, 1096, 183);
		add(panel);
		panel.setLayout(null);

		JLabel allCourceslbl = new JLabel("All Department");
		allCourceslbl.setForeground(new Color(255, 255, 255));
		allCourceslbl.setFont(new Font("Segoe UI", Font.BOLD, 30));
		allCourceslbl.setBounds(10, 65, 321, 34);
		panel.add(allCourceslbl);

		addcource = new JButton("Add Department");
		addcource.setBounds(939, 135, 147, 33);
		panel.add(addcource);
		addcource.setBorder(new EmptyBorder(0, 0, 0, 0));
		addcource.setForeground(new Color(0, 139, 139));
		addcource.setBackground(new Color(255, 255, 255));
		addcource.setFocusable(false);
		addcource.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addcource.addActionListener(this);
		addcource.setFont(new Font("Segoe UI", Font.BOLD, 15));

	}

	public void updatetableData() {

		DepartmentData c = new DepartmentData();
		ResultSet st = c.getDepartmentinfo();
		if (st != null) {
			table.setModel(DbUtils.resultSetToTableModel(st));
		}
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(cellRenderer);
		table.getColumnModel().getColumn(0).setMaxWidth(200);
		table.getColumnModel().getColumn(1).setMaxWidth(250);
		table.getColumnModel().getColumn(2).setMaxWidth(400);
		table.getColumnModel().getColumn(3).setMaxWidth(200);
		table.getColumnModel().getColumn(4).setMaxWidth(250);
		// table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == addcource) {
			AddDepartmentDialog ac = new AddDepartmentDialog(this);
			ac.setLocationRelativeTo(null);
			ac.setVisible(true);
			ScrollPaneUtil.scrollToBottom(scrollPane);
		}

	}
}
