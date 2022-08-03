package collegeapplication.common;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import collegeapplication.department.DepartmentData;
import collegeapplication.lecturer.Lecturer;
import collegeapplication.lecturer.LecturerData;
import collegeapplication.lecturer.LecturerPanel;
import collegeapplication.lecturer.ViewLecturerPanel;
import collegeapplication.student.Student;
import collegeapplication.student.StudentData;
import collegeapplication.student.StudentPanel;
import collegeapplication.student.ViewStudentPanel;

/*
 * Title : PhotoViewPanel.java
 * Created by : Ajaysinh Rathod
 * Purpose : To displaying students and faculties image
 * Mail : ajaysinhrathod1290@gmail.com
 */
@SuppressWarnings("serial")
public class PhotoViewPanel extends JPanel {
	int xpos[];
	JPanel panel[][];
	JLabel profilepiclabel[][];
	JLabel namelabel[][];
	JLabel degreelabel[][];
	int totalfaculties = -1;
	int totalstudents = -1;
	int maxphotosinrow = 3;
	int incrementx = 0;
	int incrementy = 0;
	LecturerPanel fp;
	StudentPanel sp;

	/**
	 * Create the panel.
	 * 
	 */

	@Override
	public Dimension getPreferredSize() {
		int n = 0;
		if (totalfaculties != -1) {
			n = totalfaculties;
		}
		if (totalstudents != -1) {
			n = totalstudents;
		}
		int row = n % maxphotosinrow == 0 ? n / maxphotosinrow : (n / maxphotosinrow) + 1;
		if (row == 1) {
			return new Dimension(xpos[maxphotosinrow - 1] + xpos[1] - xpos[0], incrementy + 20);
		}

		return new Dimension(1116, row * (incrementy));
	}

	public int changeNameFont() {
		return maxphotosinrow < 4 ? 22 : maxphotosinrow < 8 ? 17 : maxphotosinrow < 12 ? 13 : 10;
	}

	public int changeDegreeFont() {
		return maxphotosinrow < 4 ? 18 : maxphotosinrow < 8 ? 14 : maxphotosinrow < 12 ? 13 : 10;
	}

}
