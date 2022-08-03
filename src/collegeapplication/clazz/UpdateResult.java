package collegeapplication.clazz;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import collegeapplication.admin.AdminMain;
import collegeapplication.common.HintTextField;
import collegeapplication.lecturer.LecturerData;
import collegeapplication.student.Student;
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
 * Title : UpdateResult.java
 * Created by : Ajaysinh Rathod
 * Purpose : Dialog for adding new clazz
 * Mail : ajaysinhrathod1290@gmail.com
 */

@SuppressWarnings("serial")
public class UpdateResult extends JDialog implements ActionListener {

    private JTextField studentcodefield;
    private JTextField clazzcodefield;
    private JTextField roomIDfield;
    private JComboBox<String> semoryearcombo;
    private JLabel lblError;
    private ClazzPanel clazzpanel;
    private HintTextField lectureridfield;
    private HintTextField timeslotIDfield;
    private HintTextField semeterfield;
    private JButton updatebutton;
    private JComboBox<String> subjectnamecombo;
    private JComboBox<String> classnamecombo;
    private JComboBox<String> roomnamecombo;
    private JComboBox<String> timeslotnamecombo;
    ClazzPanel sp;
    private AdminMain am;
    private Clazz clazz;
    private JButton search;
    private JButton find;
    private HintTextField markfield, gradefield;
    private JLabel lblgrade;
    private JLabel lblmark;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            UpdateResult dialog = new UpdateResult();

            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public UpdateResult(ClazzPanel clazzpanel) {
        this();
        this.clazzpanel = clazzpanel;
    }

    public UpdateResult() {

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
        search.setVisible(false);
        search.addActionListener(this);
        getContentPane().add(search);
        // subject
        JLabel lblClazzCode = new JLabel("Class Code");
        lblClazzCode.setHorizontalAlignment(SwingConstants.LEFT);
        lblClazzCode.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        lblClazzCode.setBorder(new EmptyBorder(0, 0, 0, 5));
        lblClazzCode.setBounds(10, 147, 150, 24);
        getContentPane().add(lblClazzCode);

        clazzcodefield = new HintTextField("");
        clazzcodefield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        clazzcodefield.setBounds(165, 141, 150, 40);
        getContentPane().add(clazzcodefield);
        clazzcodefield.setColumns(10);

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
        //

        // mark
        lblmark = new JLabel("Mark(/4)");
        lblmark.setHorizontalAlignment(SwingConstants.LEFT);
        lblmark.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        lblmark.setBorder(new EmptyBorder(0, 0, 0, 5));
        lblmark.setBounds(10, 222, 150, 24);
        lblmark.setVisible(false);
        getContentPane().add(lblmark);

        markfield = new HintTextField("");
        markfield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        markfield.setBounds(165, 216, 150, 40);
        markfield.setVisible(false);
        getContentPane().add(markfield);
        markfield.setColumns(10);
        // grade
        lblgrade = new JLabel("Grade");
        lblgrade.setHorizontalAlignment(SwingConstants.LEFT);
        lblgrade.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        lblgrade.setBorder(new EmptyBorder(0, 0, 0, 5));
        lblgrade.setBounds(10, 297, 150, 24);
        lblgrade.setVisible(false);
        getContentPane().add(lblgrade);

        gradefield = new HintTextField("");
        gradefield.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        gradefield.setBounds(165, 291, 150, 40);
        gradefield.setVisible(false);
        getContentPane().add(gradefield);
        gradefield.setColumns(10);
        // add
        updatebutton = new JButton("Update");
        updatebutton.setBackground(new Color(32, 178, 170));
        updatebutton.setForeground(new Color(255, 255, 255));
        updatebutton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        updatebutton.setBounds(310, 500, 150, 37);
        updatebutton.setVisible(false);
        updatebutton.addActionListener(this);
        getContentPane().add(updatebutton);

        lblError = new JLabel("This is required question !");
        lblError.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(255, 0, 0)));
        lblError.setForeground(new Color(255, 0, 0));
        lblError.setFont(new Font("Candara", Font.PLAIN, 15));
        lblError.setVisible(false);
        lblError.setBounds(165, 115, 355, 21);
        getContentPane().add(lblError);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        lblError.setForeground(Color.red);
        lblError.setVisible(false);
        lblError.setText("This is required question !");
        String stuID = studentcodefield.getText();
        String classID = clazzcodefield.getText();
        if (e.getSource() == updatebutton) {
            if (new StudentData().isExist(studentcodefield.getText())) {
                try {
                    Student s = new StudentData().getStudentDetailsByUserId(stuID);
                    String grade = gradefield.getText();
                    Float mark = Float.parseFloat(markfield.getText());
                    int cre = new SubjectData().getSubjectCre(classID);
                    int result = new ClazzData().updateManageStudent(s, classID, grade, mark, cre);
                    this.dispose();
                } catch (NumberFormatException exp) {

                } catch (Exception e1) {

                    // TODO Auto-generated catch block

                    e1.printStackTrace();
                }
            } else {
            }

        }
        if (e.getSource() == search) {
        }
        if (e.getSource() == find) {
            if (new ClazzData().ManageStudentIsExist(stuID, classID)) {
                lblmark.setVisible(true);
                markfield.setVisible(true);
                lblgrade.setVisible(true);
                gradefield.setVisible(true);
                updatebutton.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(new JFrame(),
                        "This student not register or this class \nor not exist studentID, classID !", "Dialog",
                        JOptionPane.WARNING_MESSAGE);
            }

        }

    }

}
