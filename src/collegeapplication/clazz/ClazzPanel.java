package collegeapplication.clazz;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

import collegeapplication.admin.Admin;
import collegeapplication.admin.AdminMain;
import collegeapplication.common.ScrollPaneUtil;
import net.proteanit.sql.DbUtils;

import javax.swing.border.EmptyBorder;

/*
 * Title : ClazzPanel.java
 * Created by : Ajaysinh Rathod
 * Purpose : Displaying all the clazzs table using this file
 * Mail : ajaysinhrathod1290@gmail.com
 */
@SuppressWarnings("serial")
public class ClazzPanel extends JPanel implements ActionListener {
	private JScrollPane scrollPane;
	private JButton addclazz, registerbutton;
	AdminMain am;
	private JTable table;
	private JButton updateresultbutton;

	/**
	 * Create the panel.
	 */
	public ClazzPanel() {

		setForeground(Color.GRAY);
		setBackground(Color.WHITE);
		this.setSize(1116, 705);
		setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBounds(10, 200, 1096, 494);
		for (Component c : scrollPane.getComponents()) {
			c.setBackground(Color.white);
		}
		add(scrollPane);

		table = new JTable();
		table.setBorder(new LineBorder(new Color(192, 192, 192)));
		table.setForeground(Color.DARK_GRAY);
		table.setRowHeight(40);

		table.getTableHeader().setBackground(new Color(32, 178, 170));

		table.getTableHeader().setForeground(Color.white);
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
		table.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		table.getTableHeader().setPreferredSize(new Dimension(50, 40));
		table.setDragEnabled(false);

		table.setGridColor(Color.LIGHT_GRAY);
		table.getTableHeader().setReorderingAllowed(false);
		this.updatetableData();
		table.setEnabled(false);
		scrollPane.setViewportView(table);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(32, 178, 170));
		panel.setBounds(10, 0, 1096, 183);
		add(panel);
		panel.setLayout(null);

		JLabel allClazzslbl = new JLabel("All Classes");
		allClazzslbl.setForeground(new Color(255, 255, 255));
		allClazzslbl.setFont(new Font("Segoe UI", Font.BOLD, 30));
		allClazzslbl.setBounds(10, 65, 321, 34);
		panel.add(allClazzslbl);

		registerbutton = new JButton("Register Class");
		registerbutton.setBounds(785, 135, 150, 33);
		panel.add(registerbutton);
		registerbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		registerbutton.setForeground(new Color(0, 139, 139));
		registerbutton.setFocusable(false);
		registerbutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		registerbutton.setBackground(new Color(255, 255, 255));
		registerbutton.setVisible(true);
		registerbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		registerbutton.addActionListener(this);

		updateresultbutton = new JButton("Update Result");
		updateresultbutton.setBounds(630, 135, 150, 33);
		panel.add(updateresultbutton);
		updateresultbutton.setBorder(new EmptyBorder(0, 0, 0, 0));
		updateresultbutton.setForeground(new Color(0, 139, 139));
		updateresultbutton.setFocusable(false);
		updateresultbutton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		updateresultbutton.setBackground(new Color(255, 255, 255));
		updateresultbutton.setVisible(true);
		updateresultbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		updateresultbutton.addActionListener(this);

		addclazz = new JButton("Add Class");
		addclazz.setBounds(940, 135, 150, 33);
		panel.add(addclazz);
		addclazz.setBorder(new EmptyBorder(0, 0, 0, 0));
		addclazz.setForeground(new Color(0, 139, 139));
		addclazz.setBackground(new Color(255, 255, 255));
		addclazz.setFocusable(false);
		addclazz.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addclazz.addActionListener(this);
		addclazz.setFont(new Font("Segoe UI", Font.BOLD, 15));

	}

	public ClazzPanel(AdminMain am) {
		this();
		this.am = am;
	}

	public void updatetableData() {

		ClazzData c = new ClazzData();
		ResultSet st = c.getClazzinfo();
		table.setModel(DbUtils.resultSetToTableModel(st));
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(0).setMaxWidth(150);
		table.getColumnModel().getColumn(1).setMaxWidth(400);
		table.getColumnModel().getColumn(2).setMaxWidth(250);
		table.getColumnModel().getColumn(3).setMaxWidth(160);
		table.getColumnModel().getColumn(4).setMaxWidth(130);
		// table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == registerbutton) {
			RegisterClazz rc = new RegisterClazz();
			rc.setLocationRelativeTo(null);
			rc.setVisible(true);
		}
		// }
		if (e.getSource() == addclazz) {
			AddClazzDialog ac = new AddClazzDialog(table);
			ac.setLocationRelativeTo(null);
			ac.setVisible(true);
			ScrollPaneUtil.scrollToBottom(scrollPane);
		}
		if (e.getSource() == updateresultbutton) {
			UpdateResult ur = new UpdateResult();
			ur.setLocationRelativeTo(null);
			ur.setVisible(true);
		}

	}
}
