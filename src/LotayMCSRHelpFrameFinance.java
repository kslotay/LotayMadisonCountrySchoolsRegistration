import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;

public class LotayMCSRHelpFrameFinance extends JFrame {

	private JPanel contentPane;
	private final JLabel lblhouseholdMemberName = new JLabel("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Member Name</h3>\r\n<p style=\"font-size 13;\">Please enter the member's Name</p>\r\n\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Member Relationship</h3>\r\n<p style=\"font-size 13;\">Please specify the member's relationship to the child</p>\r\n\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Member Age</h3>\r\n<p style=\"font-size 13;\">Please enter the member's Age</p>\r\n\r\n\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Member Social Security Number</h3>\r\n<p style=\"font-size 13;\">Please enter the member's Social Security Number</p>\r\n\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Income</h3>\r\n<p style=\"font-size 13;\">Please enter the details regarding income</p>\r\n\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Expenses</h3>\r\n<p style=\"font-size 13;\">Please enter the details regarding expenses</p>\r\n</html>");
	private final JButton btnClose = new JButton("Close");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LotayMCSRHelpFrameFinance frame = new LotayMCSRHelpFrameFinance();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LotayMCSRHelpFrameFinance() {
		jbInit();
	}
	private void jbInit() {
		setTitle("Finance Information Help");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 518, 536);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		lblhouseholdMemberName.setBounds(31, 26, 457, 358);
		
		contentPane.add(lblhouseholdMemberName);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnClose_actionPerformed(e);
			}
		});
		btnClose.setBounds(199, 424, 89, 23);
		
		contentPane.add(btnClose);
	}

	protected void do_btnClose_actionPerformed(ActionEvent e){
		this.dispose();
	}
}
