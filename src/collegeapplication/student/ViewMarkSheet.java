package collegeapplication.student;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
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
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Component;

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
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;

import collegeapplication.admin.AdminMain;
import collegeapplication.common.HintTextField;
import collegeapplication.department.DepartmentData;
import collegeapplication.subject.SubjectData;
import net.proteanit.sql.DbUtils;

/*
 * Title : ViewMarkSheet.java
 * Created by : Ajaysinh Rathod
 * Purpose : Displaying all the details of student
 * Mail : ajaysinhrathod1290@gmail.com
 */

@SuppressWarnings("serial")
public class ViewMarkSheet extends JDialog implements ActionListener {

    public JComponent lastpanel;
    AdminMain am;
    private JTable table;
    private JScrollPane tableviewscroll;
    static Student s1;
    private final JPanel panel = new JPanel();
    private JLabel headerlabel;

    public static void main(String[] args) {
        try {
            ViewMarkSheet vsp = new ViewMarkSheet(null);
            vsp.setVisible(true);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    /**
     * Create the panel.
     * 
     * @wbp.parser.constructor
     */
    public ViewMarkSheet(String studentID) {

        super(new JFrame(), true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setBounds(350, 20, 1000, 600);
        getContentPane().setLayout(null);
        panel.setLayout(null);
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));

        headerlabel = new JLabel("MarkSheet of Student " + studentID);
        headerlabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        headerlabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerlabel.setBounds(0, 0, 1000, 40);
        getContentPane().add(headerlabel);

        headerlabel.setBackground(new Color(32, 178, 170));
        headerlabel.setOpaque(true);
        headerlabel.setForeground(new Color(255, 255, 255));
        headerlabel.setFont(new Font("Arial", Font.BOLD, 23));
        headerlabel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.LIGHT_GRAY));
        tableviewscroll = new JScrollPane();
        tableviewscroll.setBorder(new EmptyBorder(0, 0, 0, 0));
        tableviewscroll.setBounds(10, 194, 1096, 500);
        for (Component c : tableviewscroll.getComponents()) {
            c.setBackground(Color.white);
        }
        getContentPane().add(tableviewscroll);

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
        createtablemodel(studentID, "semester");
        table.setDefaultEditor(Object.class, null);
        table.setCursor(new Cursor(Cursor.HAND_CURSOR));
        table.setGridColor(Color.LIGHT_GRAY);
        table.getTableHeader().setReorderingAllowed(false);
        // table.setBounds(10, 20, 990, 380);
        tableviewscroll.setViewportView(table);

        JRadioButton rdbysemester = new JRadioButton("Sort by semester");
        rdbysemester.setBackground(Color.white);
        rdbysemester.setBounds(160, 80, 160, 30);
        getContentPane().add(rdbysemester);

        JRadioButton rdbymark = new JRadioButton("Sort by mark");
        rdbymark.setBounds(160, 112, 160, 30);
        rdbymark.setBackground(Color.white);
        getContentPane().add(rdbymark);

        JButton btnSort = new JButton("Start sorting");
        btnSort.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (rdbysemester.isSelected()) {
                    createtablemodel(studentID, "semester");
                } else {
                    createtablemodel(studentID, "mark");
                }
            }
        });
        btnSort.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnSort.setFocusable(false);
        btnSort.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnSort.setForeground(new Color(255, 255, 255));
        btnSort.setBackground(new Color(32, 178, 170));
        btnSort.setBounds(180, 140, 120, 35);
        btnSort.setCursor(new Cursor(Cursor.HAND_CURSOR));
        getContentPane().add(btnSort);

    }

    public void createtablemodel(String id, String condition) {
        ResultSet rs = new StudentData().getMarkSheet(id, condition);
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
        table.getColumnModel().getColumn(5).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(0).setMaxWidth(150);
        table.getColumnModel().getColumn(1).setMaxWidth(150);
        table.getColumnModel().getColumn(2).setMaxWidth(300);
        table.getColumnModel().getColumn(3).setMaxWidth(130);
        table.getColumnModel().getColumn(4).setMaxWidth(130);
        table.getColumnModel().getColumn(5).setMaxWidth(130);

        // table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }

    // public ViewMarkSheet(String s, AdminMain am, JComponent lastpanel) {
    // this(s);
    // this.am = am;
    // this.lastpanel = lastpanel;

    // }

}
