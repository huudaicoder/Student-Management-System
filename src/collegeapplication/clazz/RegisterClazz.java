package collegeapplication.clazz;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import collegeapplication.admin.AdminMain;
import collegeapplication.common.HintTextField;
import collegeapplication.lecturer.LecturerData;
import collegeapplication.student.StudentData;
import collegeapplication.subject.SubjectData;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

/*
 * Title : RegisterClazz.java
 * Created by : Ajaysinh Rathod
 * Purpose : Dialog for adding new clazz
 * Mail : ajaysinhrathod1290@gmail.com
 */

@SuppressWarnings("serial")
public class RegisterClazz extends JDialog implements ActionListener {

    private JTextField studentcodefield;
    private JTextField subjectcodefield;
    private JTextField roomIDfield;
    private JComboBox<String> semoryearcombo;
    private JLabel lblError;
    private ClazzPanel clazzpanel;
    private HintTextField lectureridfield;
    private HintTextField timeslotIDfield;
    private HintTextField semeterfield;
    private JButton registerbutton;
    private JComboBox<String> subjectnamecombo;
    private JComboBox<String> classnamecombo;
    private JComboBox<String> roomnamecombo;
    private JComboBox<String> timeslotnamecombo;
    ClazzPanel sp;
    private AdminMain am;
    private Clazz clazz;
    private JButton search;
    private JButton find;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            RegisterClazz dialog = new RegisterClazz();

            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public RegisterClazz(ClazzPanel clazzpanel) {
        this();
        this.clazzpanel = clazzpanel;
    }

    public RegisterClazz() {

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        setModalityType(ModalityType.APPLICATION_MODAL);
        getContentPane().setBackground(Color.WHITE);
        setBounds(100, 100, 480, 600);
        getContentPane().setLayout(null);

        JLabel lblAddNewClazz = new JLabel("Register class");
        lblAddNewClazz.setForeground(new Color(255, 255, 255));
        lblAddNewClazz.setBackground(new Color(32, 178, 170));
        lblAddNewClazz.setOpaque(true);
        lblAddNewClazz.setFont(new Font("Arial", Font.BOLD, 23));
        lblAddNewClazz.setHorizontalAlignment(SwingConstants.CENTER);
        lblAddNewClazz.setBounds(0, 0, 480, 55);
        getContentPane().add(lblAddNewClazz);
        // code
        JLabel lblstudentID = new JLabel("Student ID");
        lblstudentID.setBorder(new EmptyBorder(0, 0, 0, 5));
        lblstudentID.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        lblstudentID.setHorizontalAlignment(SwingConstants.LEFT);
        lblstudentID.setBounds(10, 79, 150, 24);
        lblstudentID.setFocusable(true);
        getContentPane().add(lblstudentID);

        studentcodefield = new HintTextField("");
        studentcodefield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        studentcodefield.setBounds(165, 72, 150, 40);
        getContentPane().add(studentcodefield);
        studentcodefield.setColumns(10);

        search = new JButton("Search");
        search.setBackground(new Color(32, 178, 170));
        search.setForeground(new Color(255, 255, 255));
        search.setFont(new Font("Segoe UI", Font.BOLD, 14));
        search.setBounds(310, 72, 70, 40);
        search.addActionListener(this);
        search.setVisible(false);
        getContentPane().add(search);
        // subject
        JLabel lblSubjectCode = new JLabel("Subject Code");
        lblSubjectCode.setHorizontalAlignment(SwingConstants.LEFT);
        lblSubjectCode.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        lblSubjectCode.setBorder(new EmptyBorder(0, 0, 0, 5));
        lblSubjectCode.setBounds(10, 147, 150, 24);
        getContentPane().add(lblSubjectCode);

        subjectcodefield = new HintTextField("");
        subjectcodefield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        subjectcodefield.setBounds(165, 141, 150, 40);
        getContentPane().add(subjectcodefield);
        subjectcodefield.setColumns(10);

        find = new JButton("Find");
        find.setBackground(new Color(32, 178, 170));
        find.setForeground(new Color(255, 255, 255));
        find.setFont(new Font("Segoe UI", Font.BOLD, 14));
        find.setBounds(310, 141, 70, 40);
        find.addActionListener(this);
        getContentPane().add(find);

        // subjectnamecombo = new JComboBox<String>(new SubjectData().getSubjectName());
        // subjectnamecombo.setForeground(Color.DARK_GRAY);
        // subjectnamecombo.setToolTipText("Subject");
        // subjectnamecombo.setFont(new Font("Segoe UI Historic", Font.PLAIN, 18));
        // subjectnamecombo.addActionListener(this);
        // subjectnamecombo.setBackground(new Color(255, 255, 255));
        // subjectnamecombo.setBounds(165, 141, 290, 40);
        // subjectnamecombo.setFocusable(false);
        // getContentPane().add(subjectnamecombo);
        // lecturerid

        // add
        registerbutton = new JButton("Register");
        registerbutton.setBackground(new Color(32, 178, 170));
        registerbutton.setForeground(new Color(255, 255, 255));
        registerbutton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        registerbutton.setBounds(310, 500, 150, 37);
        registerbutton.addActionListener(this);
        registerbutton.setVisible(false);
        getContentPane().add(registerbutton);

        lblError = new JLabel("This is required question !");
        lblError.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(255, 0, 0)));
        lblError.setForeground(new Color(255, 0, 0));
        lblError.setFont(new Font("Candara", Font.PLAIN, 15));
        lblError.setVisible(false);
        lblError.setBounds(165, 115, 355, 21);
        getContentPane().add(lblError);

        classnamecombo = new JComboBox<String>();
        System.out.println(subjectcodefield.getText());
        classnamecombo.setForeground(Color.DARK_GRAY);
        classnamecombo.setToolTipText("");
        classnamecombo.setFont(new Font("Segoe UI Historic", Font.PLAIN, 18));
        classnamecombo.addActionListener(this);
        classnamecombo.setBackground(new Color(255, 255, 255));
        classnamecombo.setBounds(105, 212, 350, 40);
        classnamecombo.setFocusable(false);
        classnamecombo.setVisible(false);
        getContentPane().add(classnamecombo);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        lblError.setForeground(Color.red);
        lblError.setVisible(false);
        lblError.setText("This is required question !");
        String stuID = studentcodefield.getText();

        if (e.getSource() == registerbutton) {
            String str = classnamecombo.getSelectedItem() + "";
            String[] splitstring = str.split("-");
            String classID = splitstring[0];
            System.out.println(splitstring[0]);
            if (new StudentData().isExist(studentcodefield.getText())) {
                if (new ClazzData().ManageStudentIsExist(stuID, classID) == false) {
                    try {

                        int result = new ClazzData().addManageStudent(stuID, classID);
                        System.out.println(result);
                        this.dispose();
                    } catch (NumberFormatException exp) {

                    } catch (Exception e1) {

                        // TODO Auto-generated catch block

                        e1.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(new JFrame(), "This student registered for this class !", "Dialog",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "StudentID not exist !", "Dialog",
                        JOptionPane.ERROR_MESSAGE);
            }

        }
        if (e.getSource() == search) {

        }
        if (e.getSource() == find) {
            classnamecombo.setVisible(false);
            if (new ClazzData().getClazzNameBySubjectCode(subjectcodefield.getText()) == null) {
                JOptionPane.showMessageDialog(new JFrame(), "There are no classes for this subject yet !", "Dialog",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                classnamecombo = new JComboBox<String>(
                        new ClazzData().getClazzNameBySubjectCode(subjectcodefield.getText()));
                System.out.println(subjectcodefield.getText());
                classnamecombo.setForeground(Color.DARK_GRAY);
                classnamecombo.setToolTipText("");
                classnamecombo.setFont(new Font("Segoe UI Historic", Font.PLAIN, 18));
                classnamecombo.addActionListener(this);
                classnamecombo.setBackground(new Color(255, 255, 255));
                classnamecombo.setBounds(105, 212, 350, 40);
                classnamecombo.setFocusable(false);
                classnamecombo.setVisible(false);
                getContentPane().add(classnamecombo);
                classnamecombo.setVisible(true);
                registerbutton.setVisible(true);
            }
        }

    }

}
