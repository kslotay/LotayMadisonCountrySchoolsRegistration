import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;

public class LotayMCSRHelpFrameFamily extends JFrame {

	private JPanel contentPane;
	private final JLabel lblNewLabel = new JLabel("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">First Name</h3>\r\n<p style=\"font-size: 13;\">Please enter the First Name</p>\r\n\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Middle Initial</h3>\r\n<p style=\"font-size: 13;\">Please enter the Middle Initial</p>\r\n\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Last Name</h3>\r\n<p style=\"font-size: 13;\">Please enter the Last Name</p>\r\n\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Cell Phone</h3>\r\n<p style=\"font-size: 13;\">Please enter the Parent/Guardian's Cell Phone Number ((###) ###-####)</p>\r\n\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Work Phone</h3>\r\n<p style=\"font-size: 13;\">Please enter the Parent/Guardian's Work Phone Number ((###) ###-####)</p>\r\n\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Address</h3>\r\n<p style=\"font-size: 13;\">Please enter the parent/guardian's Address</p>\r\n\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Place of Employment</h3>\r\n<p style=\"font-size: 13;\">Please enter the parent/guardian's Place of Employment</p>\r\n\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Email Address</h3>\r\n<p style=\"font-size: 13;\">Please enter the parent/guardian's Email Address</p>\r\n\r\n<h3 style=\"font-weight: bold; font-size 14;\">Child Lives With</h3>\r\n<p style=\"font-size 13;\">Please specify who the child lives with</p>\r\n\r\n<h3 style=\"font-weight: bold; font-size 14;\">Sibling Name</h3>\r\n<p style=\"font-size 13;\">Please enter the sibling's name</p>\r\n\r\n</html>");
	private final JLabel lblsiblingNameplease = new JLabel("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Sibling DOB Month</h3>\r\n<p style=\"font-size: 13;\">Please select the sibling's birth month</p>\r\n\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Sibling DOB Day</h3>\r\n<p style=\"font-size: 13;\">Please enter the sibling's birth day</p>\r\n\r\n<h3 style=\"font-weight: bold; font-size 14;\">Sibling Grade</h3>\r\n<p style=\"font-size 13;\">Please enter the sibling's Grade</p>\r\n\r\n<h3 style=\"font-weight: bold; font-size 14;\">Sibling School</h3>\r\n<p style=\"font-size 13;\">Please enter the sibling's School</p>\r\n\r\n<h3 style=\"font-weight: bold; font-size 14;\">Special Services</h3>\r\n<p style=\"font-size 13;\">Please speicify if your child requires special services</p>\r\n\r\n<h3 style=\"font-weight: bold; font-size 14;\">Special Services</h3>\r\n<p style=\"font-size 13;\">Please speicify if your child requires special services</p>\r\n\r\n<h3 style=\"font-weight: bold; font-size 14;\">Authorized Emergency Numbers</h3>\r\n<p style=\"font-size 13;\">Please specify emergency numbers and individuals \r\nauthorized to check out in addition to the parent/guardian</p>\r\n\r\n<h3 style=\"font-weight: bold; font-size 14;\">Allow MSE Directory Listing</h3>\r\n<p style=\"font-size 13;\">Do you wan your child's name, address and phone number to potentially appear in the MSE directory?</p>\r\n\r\n<h3 style=\"font-weight: bold; font-size 14;\">Child Details</h3>\r\n<p style=\"font-size 13;\">Please tell us your child's  personality, learning styles, social skills and academic strengths:*</p>\r\n</html>");
	private final JButton button = new JButton("Close");

	/**
	 * Create the frame.
	 */
	public LotayMCSRHelpFrameFamily() {
		jbInit();
	}
	private void jbInit() {
		setTitle("Family Information Help");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1280, 762);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		lblNewLabel.setBounds(92, 39, 495, 581);
		
		contentPane.add(lblNewLabel);
		lblsiblingNameplease.setBounds(649, 39, 530, 581);
		
		contentPane.add(lblsiblingNameplease);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnClose_actionPerformed(e);	
			}
		});
		button.setBounds(582, 653, 89, 23);
		
		contentPane.add(button);
	}

	protected void do_btnClose_actionPerformed(ActionEvent e){
		this.dispose();
	}
}
