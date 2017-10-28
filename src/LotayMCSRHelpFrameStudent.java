import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LotayMCSRHelpFrameStudent extends JFrame {

	private JPanel contentPane;
	private final JLabel lblNewLabel = new JLabel("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student First Name</h3>\r\n<p style=\"font-size: 13;\">Please enter the student's First Name</p>\r\n\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student Middle Name</h3>\r\n<p style=\"font-size: 13;\">Please enter the student's Middle Name (not required)</p>\r\n\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student Last Name</h3>\r\n<p style=\"font-size: 13;\">Please enter the student's Last Name</p>\r\n\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student Preferred Name</h3>\r\n<p style=\"font-size: 13;\">Please enter the student's preferred name, if any</p>\r\n\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student Date Of Birth</h3>\r\n<p style=\"font-size: 13;\">Please enter the student's Date Of Birth</p>\r\n\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student Gender</h3>\r\n<p style=\"font-size: 13;\">Please select the student's Gender</p>\r\n\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student Race</h3>\r\n<p style=\"font-size: 13;\">Please select the student's Race</p>\r\n\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student Grade (15/16)</h3>\r\n<p style=\"font-size: 13;\">Which grade was the student in for the 2015/16 school year?</p>\r\n\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student Social Security Number</h3>\r\n<p style=\"font-size: 13;\">Please enter the student's Social Security Number</p>\r\n\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student Subdivision</h3>\r\n<p style=\"font-size: 13;\">Please enter the student's Subdivision</p>\r\n\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Own/Lease</h3>\r\n<p style=\"font-size: 13;\">Own or Lease?</p>\r\n\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student Street Address</h3>\r\n<p style=\"font-size: 13;\">Please enter the student's Street Address</p>\r\n\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student City</h3>\r\n<p style=\"font-size: 13;\">Please enter the student's City</p>\r\n\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student ZIP Code</h3>\r\n<p style=\"font-size: 13;\">Please enter the student's ZIP Code</p>\r\n\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student Home Phone</h3>\r\n<p style=\"font-size: 13;\">Please enter the student's Home Phone Number ((###) ###-####)</p>\r\n</html>");
	private final JButton btnClose = new JButton("Close");
	
	/**
	 * Create the frame.
	 */
	public LotayMCSRHelpFrameStudent() {
		jbInit();
	}
	private void jbInit() {
		setTitle("Student Information Help");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 670, 1024);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		lblNewLabel.setBounds(21, 11, 623, 862);
		
		contentPane.add(lblNewLabel);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnClose_actionPerformed(e);	
			}
		});
		btnClose.setBounds(274, 911, 89, 23);
		
		contentPane.add(btnClose);
	}
	
	protected void do_btnClose_actionPerformed(ActionEvent e){
		this.dispose();
	}
}
