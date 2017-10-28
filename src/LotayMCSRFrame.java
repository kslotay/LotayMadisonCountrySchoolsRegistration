import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.beans.PropertyChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JSeparator;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import javax.swing.JTree;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import org.eclipse.wb.swing.FocusTraversalOnArray;


/*
 * Kulvinder Lotay
 * 
 * Project 1, System Design CMPT330
 */
public class LotayMCSRFrame extends JFrame {

	private JPanel contentPane;
	
	//Create MaskFormatters for different fields
	MaskFormatter zipMask = createNumberFormatter("#####");
	MaskFormatter dayMask = createNumberFormatter("##");
	MaskFormatter yearMask = createNumberFormatter("####");
	MaskFormatter phoneMask = createNumberFormatter("(###) ###-####");
	MaskFormatter ssMask = createNumberFormatter("###-##-####");
	
	//Create Number and Currency formatters for input verification
	NumberFormat curFormat = NumberFormat.getCurrencyInstance();
	NumberFormat numFormat = NumberFormat.getNumberInstance();
	
	//Store the current and previous tab, used during tab switching/validation (find below in informationTabbedPane stateChanged listener
	Integer currentTab = 0;
	Integer previousTab = 0;
	//Used to store whether the current tabbed page is validated or not
	boolean valid = false;
	
	private final JTabbedPane informationTabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private final JPanel studentInfoPanel = new JPanel();
	private final JPanel familyInfoPanel = new JPanel();
	private final JPanel financialInfoPanel = new JPanel();
	
	//Store tabs in arraylist, necessary to retrieve previousTab
	List<JPanel> tabsList = new ArrayList<JPanel>();
	
	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu mnFile = new JMenu("File");
	private final JMenu mnHelp = new JMenu("Help");
	private final JMenuItem mntmStartNewForm = new JMenuItem("Start New Form");
	private final JMenuItem mntmExit = new JMenuItem("Exit");
	private final JMenuItem mntmStudentInformation = new JMenuItem("Student Information...");
	private final JMenuItem mntmFamilyInformation = new JMenuItem("Family Information...");
	private final JMenuItem mntmFinancialInformation = new JMenuItem("Financial Information...");
	private final JLabel lblStudentFirst = new JLabel("First Name:*");
	private final JTextField studentfnameTF = new JTextField();
	private final JLabel lblStudentMiddle = new JLabel("Middle Name:");
	private final JTextField studentmnameTF = new JTextField();
	private final JLabel lblStudentLast = new JLabel("Last Name:*");
	private final JTextField studentlnameTF = new JTextField();
	private final JLabel lblPreferredName = new JLabel("Preferred Name:");
	private final JTextField studentprefnameTF = new JTextField();
	private final JLabel lblStudentSS = new JLabel("SS#:*");
	private final JFormattedTextField StudentSSFTF = new JFormattedTextField(ssMask);
	private final JLabel lblStudentRace = new JLabel("Race:*");
	private final JComboBox studentRaceComboBox = new JComboBox();
	private final JLabel lblStudentGender = new JLabel("Gender:*");
	private final JComboBox studentGenderComboBox = new JComboBox();
	private final JLabel lblStudentDob = new JLabel("Date Of Birth:*");
	private final JComboBox studentDobMonthComboBox = new JComboBox();
	private final JFormattedTextField studentDobDayFTF = new JFormattedTextField(dayMask);
	private final JFormattedTextField studentDobYearFTF = new JFormattedTextField(yearMask);
	private final JLabel lblStudentGrade = new JLabel("Grade (15/16):*");
	private final JComboBox studentGradeComboBox = new JComboBox();
	private final JLabel lblStreetAddress = new JLabel("Street Address:*");
	private final JTextField studentStreetTF = new JTextField();
	private final JLabel lblCity = new JLabel("City:*");
	private final JTextField studentCityTF = new JTextField();
	private final JLabel lblZip = new JLabel("ZIP:*");
	private final JFormattedTextField studentZipFTF = new JFormattedTextField(zipMask);
	private final JLabel lblStudentSubdivision = new JLabel("Subdivision:");
	private final JTextField studentSubdivTF = new JTextField();
	private final JFormattedTextField studentLeaseExpFTF = new JFormattedTextField(yearMask);
	private final JLabel lblLeaseExpires = new JLabel("Lease Expires:");
	private final JLabel lblStudentOwnlease = new JLabel("Own/Lease:*");
	private final JRadioButton rdbtnStudentOwn = new JRadioButton("Own");
	private final JRadioButton rdbtnStudentLease = new JRadioButton("Lease");
	private final ButtonGroup OwnLeaseButtonGroup = new ButtonGroup();
	private final JLabel lblHomePhone = new JLabel("Home Phone:*");
	private final JFormattedTextField studentHomePhoneFTF = new JFormattedTextField(phoneMask);
	private final JLabel lblStudentRaceOther = new JLabel("Please specify:");
	private final JTextField studentRaceOtherTF = new JTextField();
	private final JButton btnNextStudent = new JButton("Next");
	private final JLabel lblFamilyFather = new JLabel("Father/Guardian 1");
	private final JLabel lblFamilyMother = new JLabel("Mother/Guardian 2");
	private final JLabel lblFatherFname = new JLabel("First Name:*");
	private final JTextField fatherFnameTF = new JTextField();
	private final JLabel lblFatherMI = new JLabel("Middle Initial:");
	private final JTextField fatherMITF = new JTextField();
	private final JLabel lblFatherLname = new JLabel("Last Name:*");
	private final JTextField fatherLnameTF = new JTextField();
	private final JTextField motherFnameTF = new JTextField();
	private final JLabel lblMotherFname = new JLabel("First Name:*");
	private final JLabel lblMotherMI = new JLabel("Middle Initial:");
	private final JLabel lblMotherLname = new JLabel("Last Name:*");
	private final JTextField motherMITF = new JTextField();
	private final JTextField motherLnameTF = new JTextField();
	private final JLabel lblFatherCell = new JLabel("Cell Phone #:*");
	private final JFormattedTextField fatherCellFTF = new JFormattedTextField(phoneMask);
	private final JLabel lblFatherWorkPhone = new JLabel("Work Phone:");
	private final JFormattedTextField fatherWorkPhoneFTF = new JFormattedTextField(phoneMask);
	private final JLabel lblFatherHomePhone = new JLabel("Home Phone:");
	private final JFormattedTextField fatherHomePhoneFTF = new JFormattedTextField(phoneMask);
	private final JTextField fatherAddressTF = new JTextField();
	private final JLabel lblFatherAddress = new JLabel("Address:*");
	private final JLabel lblFatherWorkplace = new JLabel("Place of Employment:");
	private final JTextField fatherWorkplaceTF = new JTextField();
	private final JLabel lblFatherEmail = new JLabel("Email Address:*");
	private final JTextField fatherEmailTF = new JTextField();
	private final JLabel lblFamilyGeneral = new JLabel("General");
	private final JSeparator GeneralSeparator = new JSeparator();
	private final JSeparator ParentSeparator = new JSeparator();
	private final JLabel lblMotherCell = new JLabel("Cell Phone #:*");
	private final JLabel lblMotherWorkPhone = new JLabel("Work Phone:");
	private final JLabel lblMotherHomePhone = new JLabel("Home Phone:");
	private final JLabel lblMotherAddress = new JLabel("Address:*");
	private final JLabel lblMotherWorkplace = new JLabel("Place of Employment:");
	private final JLabel lblMotherEmail = new JLabel("Email Address:*");
	private final JTextField motherEmailTF = new JTextField();
	private final JTextField motherWorkplaceTF = new JTextField();
	private final JTextField motherAddressTF = new JTextField();
	private final JFormattedTextField motherHomePhoneFTF = new JFormattedTextField(phoneMask);
	private final JFormattedTextField motherWorkPhoneFTF = new JFormattedTextField(phoneMask);
	private final JFormattedTextField motherCellFTF = new JFormattedTextField(phoneMask);
	private final JLabel lblChildLivesWith = new JLabel("Child Lives With:*");
	private final JRadioButton rdbtnBothParents = new JRadioButton("Both Parents");
	private final JRadioButton rdbtnFather = new JRadioButton("Father");
	private final JRadioButton rdbtnMother = new JRadioButton("Mother");
	private final JRadioButton rdbtnOther = new JRadioButton("Other");
	private final ButtonGroup ChildLivesWithGroup = new ButtonGroup();
	private final JLabel lblChildLivesWithOther = new JLabel("Please Specify Your Full Name:*");
	private final JTextField familyOtherNameTF = new JTextField();
	private final JLabel lblSiblings = new JLabel("Siblings (NAME, D.O.B, GRADE & SCHOOL)");
	private final JTextField siblingName1TF = new JTextField();
	private final JComboBox siblingMonth1CB = new JComboBox();
	private final JFormattedTextField siblingDay1FTF = new JFormattedTextField(dayMask);
	private final JFormattedTextField siblingYear1FTF = new JFormattedTextField(yearMask);
	private final JComboBox siblingGrade1ComboBox = new JComboBox();
	private final JTextField siblingSchool1TF = new JTextField();
	private final JTextField siblingName2TF = new JTextField();
	private final JComboBox siblingMonth2CB = new JComboBox();
	private final JFormattedTextField siblingDay2FTF = new JFormattedTextField(dayMask);
	private final JFormattedTextField siblingYear2FTF = new JFormattedTextField(yearMask);
	private final JComboBox siblingGrade2ComboBox = new JComboBox();
	private final JTextField siblingSchool2TF = new JTextField();
	private final JTextField siblingName3TF = new JTextField();
	private final JComboBox siblingMonth3CB = new JComboBox();
	private final JFormattedTextField siblingDay3TF = new JFormattedTextField(dayMask);
	private final JFormattedTextField siblingYear3FTF = new JFormattedTextField(yearMask);
	private final JComboBox siblingGrade3ComboBox = new JComboBox();
	private final JTextField siblingSchool3TF = new JTextField();
	private final JLabel lblSpecialServices = new JLabel("Special Services:");
	private final JRadioButton rdbtnGifted = new JRadioButton("Gifted");
	private final JRadioButton rdbtnSpediep = new JRadioButton("Sped-IEP");
	private final JRadioButton rdbtnSpeechied = new JRadioButton("Speech-IEP");
	private final JRadioButton rdbtnEll = new JRadioButton("ELL");
	private final JLabel lblLifeThreateningAllergy = new JLabel("Life Threatening Allergy:");
	private final JTextField lifeThreateningAllergyTF = new JTextField();
	private final JLabel lblEmergencyNumbers = new JLabel("Emergency numbers and individuals authorized to check out (in addition to parent/guardian):");
	private final JTextField emergencyName1TF = new JTextField();
	private final JComboBox emergencyRelationship1ComboBox = new JComboBox();
	private final JLabel lblEmergencyName1 = new JLabel("Name:");
	private final JLabel lblEmergencyRelationship1 = new JLabel("Relationship:");
	private final JFormattedTextField emergencyPhone1FTF = new JFormattedTextField(phoneMask);
	private final JLabel lblEmergencyPhone1 = new JLabel("Phone #:");
	private final JLabel lblEmergency1 = new JLabel("1.");
	private final JLabel lblEmergency2 = new JLabel("2.");
	private final JLabel lblEmergencyName2 = new JLabel("Name:");
	private final JLabel lblEmergencyRelationship2 = new JLabel("Relationship:");
	private final JLabel lblEmergencyPhone2 = new JLabel("Phone #:");
	private final JTextField emergencyName2TF = new JTextField();
	private final JComboBox emergencyRelationship2ComboBox = new JComboBox();
	private final JFormattedTextField emergencyPhone2FTF = new JFormattedTextField(phoneMask);
	private final JLabel lblToHelpUs = new JLabel("To help us in the placement of your child, please tell us your child's  personality, learning styles, social skills and academic strengths:*");
	private final JTextArea txtAreaToHelpUs = new JTextArea();
	private final JLabel rdbtnYesMyChilds = new JLabel("My child's name, address and phone number may appear in the MSE directory:*");
	private final ButtonGroup SpecialServicesGroup = new ButtonGroup();
	private final JButton btnNextFamily = new JButton("Next");
	private final JButton btnPreviousFamily = new JButton("Previous");
	private final JButton btnPreviousFinance = new JButton("Previous");
	private final JLabel lblFinanceInstr = new JLabel("If you are requesting free/reduced lunch for your child(ren), complete the following:");
	private final JLabel lblFinanceName = new JLabel("NAME:");
	private final JLabel lblFinanceRelationship = new JLabel("RELATIONSHIP:");
	private final JLabel lblFinanceAge = new JLabel("AGE:");
	private final JLabel lblFinanceSS = new JLabel("SS#:");
	private final JLabel lblFinance1 = new JLabel("1.");
	private final JLabel lblFinance2 = new JLabel("2.");
	private final JLabel lblFinance3 = new JLabel("3.");
	private final JLabel lblFinance4 = new JLabel("4.");
	private final JLabel lblFinance5 = new JLabel("5.");
	private final JLabel lblFinance6 = new JLabel("6.");
	private final JTextField financeMemberNameTF1 = new JTextField();
	private final JTextField financeMemberNameTF2 = new JTextField();
	private final JTextField financeMemberNameTF4 = new JTextField();
	private final JTextField financeMemberNameTF3 = new JTextField();
	private final JTextField financeMemberNameTF6 = new JTextField();
	private final JTextField financeMemberNameTF5 = new JTextField();
	private final JComboBox financeMemberRelationshipComboBox1 = new JComboBox();
	private final JComboBox financeMemberRelationshipComboBox2 = new JComboBox();
	private final JComboBox financeMemberRelationshipComboBox3 = new JComboBox();
	private final JComboBox financeMemberRelationshipComboBox4 = new JComboBox();
	private final JComboBox financeMemberRelationshipComboBox5 = new JComboBox();
	private final JComboBox financeMemberRelationshipComboBox6 = new JComboBox();
	private final JFormattedTextField financeMemberAgeFTF1 = new JFormattedTextField(numFormat);
	private final JTextField financeMemberAgeFTF2 = new JTextField();
	private final JTextField financeMemberAgeFTF4 = new JTextField();
	private final JTextField financeMemberAgeFTF3 = new JTextField();
	private final JTextField financeMemberAgeFTF6 = new JTextField();
	private final JTextField financeMemberAgeFTF5 = new JTextField();
	private final JFormattedTextField financeSS1FTF = new JFormattedTextField(ssMask);
	private final JFormattedTextField financeSS2FTF = new JFormattedTextField(ssMask);
	private final JFormattedTextField financeSS3FTF = new JFormattedTextField(ssMask);
	private final JFormattedTextField financeSS4FTF = new JFormattedTextField(ssMask);
	private final JFormattedTextField financeSS6FTF = new JFormattedTextField(ssMask);
	private final JFormattedTextField financeSS5FTF = new JFormattedTextField(ssMask);
	private final JLabel lblFinanceIncomeOfAnyone = new JLabel("Income of anyone living in your household, including yourself:");
	private final JLabel lblFinanceSalary = new JLabel("Salary:");
	private final JLabel lblFinanceUnemployment = new JLabel("Unemployment:");
	private final JLabel lblFinanceTANFAFDC = new JLabel("TANF/AFDC:");
	private final JLabel lblFinanceSocialSecurity = new JLabel("Social Security:");
	private final JLabel lblFinanceDisability = new JLabel("Disability:");
	private final JLabel lblFinanceChildSupport = new JLabel("Child Support:");
	private final JLabel lblFinanceUtilityAssistance = new JLabel("Utility Assistance:");
	private final JLabel lblFinanceFoodStamps = new JLabel("Food Stamps:");
	private final JLabel lblFinanceOtherInc = new JLabel("Other:");
	private final JLabel lblFinanceIncomeExplain = new JLabel("Explain:");
	private final JFormattedTextField SalaryIncomeTF = new JFormattedTextField(curFormat);
	private final JFormattedTextField UnemploymentIncomeTF = new JFormattedTextField(curFormat);
	private final JFormattedTextField TANFAFDCIncomeTF = new JFormattedTextField(curFormat);
	private final JFormattedTextField DisabilityIncomeTF = new JFormattedTextField(curFormat);
	private final JFormattedTextField SocialSecurityIncomeTF = new JFormattedTextField(curFormat);
	private final JFormattedTextField ChildSupportIncomeTF = new JFormattedTextField(curFormat);
	private final JFormattedTextField UtilityAsisstanceIncomeTF = new JFormattedTextField(curFormat);
	private final JFormattedTextField FoodStampsIncomeTF = new JFormattedTextField(curFormat);
	private final JFormattedTextField OtherIncomeTF = new JFormattedTextField(curFormat);
	private final JTextField IncomeExplainTF = new JTextField();
	private final JLabel lblFinanceExpensesOfAnyone = new JLabel("Expenses (Monthly of Each):");
	private final JLabel lblFinanceRent = new JLabel("Rent:");
	private final JLabel lblFinancePhoneBill = new JLabel("Phone Bill:");
	private final JLabel lblFinanceGaswater = new JLabel("Gas/Water:");
	private final JLabel lblFinanceCarPayment = new JLabel("Car Payment:");
	private final JLabel lblFinanceLightBill = new JLabel("Light Bill:");
	private final JLabel lblFinanceFurnappliances = new JLabel("Furn/Appliances:");
	private final JLabel lblFinanceCableTv = new JLabel("Cable TV:");
	private final JLabel lblFinanceDoctormed = new JLabel("Doctor/Med.:");
	private final JLabel lblFinanceOtherExpenses = new JLabel("Other:");
	private final JLabel lblFinanceExpensesExplain = new JLabel("Explain:");
	private final JFormattedTextField RentExpenseTF = new JFormattedTextField(curFormat);
	private final JFormattedTextField PhoneExpenseTF = new JFormattedTextField(curFormat);
	private final JFormattedTextField GasExpenseTF = new JFormattedTextField(curFormat);
	private final JFormattedTextField LightBillExpenseTF = new JFormattedTextField(curFormat);
	private final JFormattedTextField CarPaymentExpenseTF = new JFormattedTextField(curFormat);
	private final JFormattedTextField ApplianceExpenseTF = new JFormattedTextField(curFormat);
	private final JFormattedTextField CableTVExpenseTF = new JFormattedTextField(curFormat);
	private final JFormattedTextField DoctorExpenseTF = new JFormattedTextField(curFormat);
	private final JFormattedTextField OtherExpenseTF = new JFormattedTextField(curFormat);
	private final JTextField ExpenseExplainTF = new JTextField();
	private final JLabel lblTotalIncome = new JLabel("TOTAL:");
	private final JFormattedTextField TotalIncomeTF = new JFormattedTextField(curFormat);
	private final JLabel lblTotalExpense = new JLabel("TOTAL:");
	private final JFormattedTextField TotalExpenseTF = new JFormattedTextField(curFormat);
	private final JLabel lblNetIncome = new JLabel("Net Income:");
	private final JFormattedTextField NetIncomeTF = new JFormattedTextField(curFormat);
	private final JLabel lblStudentInformation = new JLabel("Student Information");
	private final JCheckBox chckbxNewCheckBox = new JCheckBox("Yes");
	private final JCheckBox chckbxNo = new JCheckBox("No");
	private final ButtonGroup MSEDirectoryYesNo = new ButtonGroup();
	private final JLabel lblFinanceListAnyoneIn = new JLabel("List anyone in your household, other than you or your spouse:");
	private final JSeparator separator = new JSeparator();
	private final JSeparator separator_1 = new JSeparator();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LotayMCSRFrame frame = new LotayMCSRFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//createMaskFormatter
	public MaskFormatter createNumberFormatter(String s){
		MaskFormatter formatter = null;
		try{
			formatter = new MaskFormatter(s);
			formatter.setPlaceholderCharacter('0');
		}
		catch(java.text.ParseException exc){
			System.err.println("formatter is bad: " + exc.getMessage());
			System.exit(-1);
		}
		return formatter;
	}

	/**
	 * Create the frame.
	 */
	public LotayMCSRFrame() {
		//Add window listener to ask for confirmation before exiting
		addWindowListener(new WindowAdapter() {
			  public void windowClosing(WindowEvent e) {
			    int confirmed = JOptionPane.showConfirmDialog(null, 
			        "Are you sure you want to exit the program?", "Exit Program Message Box",
			        JOptionPane.YES_NO_OPTION);

			    if (confirmed == JOptionPane.YES_OPTION) {
			      dispose();
			    }
			  }
			});
		
		jbInit();
	}
	
	private void jbInit() {
		setTitle("Madison County Schools Registration Form");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1256, 1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		menuBar.setBounds(0, 0, 1240, 31);
		contentPane.add(menuBar);
		menuBar.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		mnFile.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		
		menuBar.add(mnFile);
		mntmStartNewForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Call clearTextFields function passing a container as parameter
				clearTextFields(contentPane);
			}
		});
		mntmStartNewForm.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		
		mnFile.add(mntmStartNewForm);
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Exit
				do_MenuItem_Exit_Action();
			}
		});
		mntmExit.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		
		mnFile.add(mntmExit);
		mnHelp.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		
		menuBar.add(mnHelp);
		mntmStudentInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Open Help Window
				LotayMCSRHelpFrameStudent studentHelp = new LotayMCSRHelpFrameStudent();
				studentHelp.setVisible(true);
			}
		});
		mntmStudentInformation.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		
		mnHelp.add(mntmStudentInformation);
		mntmFamilyInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Open Help Window
				LotayMCSRHelpFrameFamily familyHelp = new LotayMCSRHelpFrameFamily();
				familyHelp.setVisible(true);
			}
		});
		mntmFamilyInformation.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		
		mnHelp.add(mntmFamilyInformation);
		mntmFinancialInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Open Help Window
				LotayMCSRHelpFrameFinance financeHelp = new LotayMCSRHelpFrameFinance();
				financeHelp.setVisible(true);
			}
		});
		mntmFinancialInformation.setFont(new Font("Segoe UI", Font.PLAIN, 17));
		
		mnHelp.add(mntmFinancialInformation);
		
		tabsList.add(studentInfoPanel);
		tabsList.add(familyInfoPanel);
		tabsList.add(financialInfoPanel);
		
		//Changelistener For tab state change (changing between tabs), calls the validation function, performs some checks (going to next tab or previous?)
		informationTabbedPane.addChangeListener(new ChangeListener() {
			private boolean autoStateChange = false;
			
			public void stateChanged(ChangeEvent arg0) {
				
				//Assign currentTab to previousTab, as the new currentTab is then assigned from the tabbedpane
				previousTab = currentTab;
				currentTab = informationTabbedPane.getSelectedIndex();
				
				if(currentTab > previousTab){
					if(autoStateChange){
						autoStateChange = false;
						return;
					}
					else{
						valid = validate(tabsList.get(previousTab));
					}
					
					if(valid){
						informationTabbedPane.setSelectedIndex(currentTab);
					}
					else{
						autoStateChange = true;
						informationTabbedPane.setSelectedIndex(previousTab);
	                    JOptionPane.showMessageDialog(getFocusOwner(), "<html><h3 style=\"font-size: 16\">Please complete the required fields!", "Form Incomplete!", JOptionPane.ERROR_MESSAGE);
					}
				}
				else if(currentTab < previousTab){
					informationTabbedPane.setSelectedIndex(currentTab);
				}
			}
		});
		informationTabbedPane.setFont(new Font("Calibri", Font.PLAIN, 18));
		informationTabbedPane.setBounds(0, 29, 1240, 1009);
		
		contentPane.add(informationTabbedPane);
		
		informationTabbedPane.addTab("Student Information", null, studentInfoPanel, null);
		studentInfoPanel.setLayout(null);
		lblStudentFirst.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student First Name</h3>\r\n<p style=\"font-size: 13;\">Please enter the student's First Name</p>\r\n<html>");
		lblStudentFirst.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStudentFirst.setBounds(61, 90, 142, 17);
		
		studentInfoPanel.add(lblStudentFirst);
		studentfnameTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				validateData(studentfnameTF, lblStudentFirst);
			}
		});
		studentfnameTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student First Name</h3>\r\n<p style=\"font-size: 13;\">Please enter the student's First Name</p>\r\n<html>");
		studentfnameTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		studentfnameTF.setColumns(10);
		studentfnameTF.setBounds(247, 87, 250, 23);
		
		studentInfoPanel.add(studentfnameTF);
		lblStudentMiddle.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student Middle Name</h3>\r\n<p style=\"font-size: 13;\">Please enter the student's Middle Name (not required)</p>\r\n<html>");
		lblStudentMiddle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStudentMiddle.setBounds(61, 136, 142, 17);
		
		studentInfoPanel.add(lblStudentMiddle);
		studentmnameTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student Middle Name</h3>\r\n<p style=\"font-size: 13;\">Please enter the student's Middle Name (not required)</p>\r\n<html>");
		studentmnameTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		studentmnameTF.setColumns(10);
		studentmnameTF.setBounds(247, 133, 250, 23);
		
		studentInfoPanel.add(studentmnameTF);
		lblStudentLast.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student Last Name</h3>\r\n<p style=\"font-size: 13;\">Please enter the student's Last Name</p>\r\n<html>");
		lblStudentLast.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStudentLast.setBounds(61, 182, 142, 17);
		
		studentInfoPanel.add(lblStudentLast);
		studentlnameTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				validateData(studentlnameTF, lblStudentLast);
			}
		});
		studentlnameTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student Last Name</h3>\r\n<p style=\"font-size: 13;\">Please enter the student's Last Name</p>\r\n<html>");
		studentlnameTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		studentlnameTF.setColumns(10);
		studentlnameTF.setBounds(247, 179, 250, 23);
		
		studentInfoPanel.add(studentlnameTF);
		lblPreferredName.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student Preferred Name</h3>\r\n<p style=\"font-size: 13;\">Please enter the student's preferred name, if any</p>\r\n<html>");
		lblPreferredName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPreferredName.setBounds(61, 228, 171, 17);
		
		studentInfoPanel.add(lblPreferredName);
		studentprefnameTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student Preferred Name</h3>\r\n<p style=\"font-size: 13;\">Please enter the student's preferred name, if any</p>\r\n<html>");
		studentprefnameTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		studentprefnameTF.setColumns(10);
		studentprefnameTF.setBounds(247, 225, 250, 23);
		
		studentInfoPanel.add(studentprefnameTF);
		lblStudentDob.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student Date Of Birth</h3>\r\n<p style=\"font-size: 13;\">Please enter the student's Date Of Birth</p>\r\n<html>");
		lblStudentDob.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStudentDob.setBounds(61, 273, 152, 17);
		
		studentInfoPanel.add(lblStudentDob);
		studentDobMonthComboBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				validateData(studentDobMonthComboBox, lblStudentDob);
			}
		});
		studentDobMonthComboBox.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student DOB Month</h3>\r\n<p style=\"font-size: 13;\">Please select the student's birth month</p>\r\n<html>");
		studentDobMonthComboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		studentDobMonthComboBox.setModel(new DefaultComboBoxModel(Month.values()));
		studentDobMonthComboBox.setBounds(247, 270, 156, 23);
		studentDobMonthComboBox.setSelectedIndex(-1);
		
		studentInfoPanel.add(studentDobMonthComboBox);
		studentDobDayFTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				validateDate(studentDobDayFTF, lblStudentDob, Integer.parseInt(studentDobDayFTF.getText().trim()), studentDobMonthComboBox.getSelectedIndex());
			}
		});
		studentDobDayFTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student DOB Day</h3>\r\n<p style=\"font-size: 13;\">Please enter the student's birth day</p>\r\n<html>");
		studentDobDayFTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		studentDobDayFTF.setColumns(10);
		studentDobDayFTF.setForeground(Color.GRAY);
		studentDobDayFTF.setBounds(424, 270, 54, 23);
		
		studentInfoPanel.add(studentDobDayFTF);
		studentDobYearFTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				validateDate(studentDobYearFTF, lblStudentDob, Integer.parseInt(studentDobYearFTF.getText().trim()));
			}
		});
		studentDobYearFTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student DOB Year</h3>\r\n<p style=\"font-size: 13;\">Please enter the student's birth year</p>\r\n<html>");
		studentDobYearFTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		studentDobYearFTF.setColumns(10);
		studentDobYearFTF.setForeground(Color.GRAY);
		studentDobYearFTF.setBounds(493, 270, 83, 23);
		
		studentInfoPanel.add(studentDobYearFTF);
		lblStudentGender.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student Gender</h3>\r\n<p style=\"font-size: 13;\">Please select the student's Gender</p>\r\n<html>");
		lblStudentGender.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStudentGender.setBounds(61, 320, 103, 17);
		
		studentInfoPanel.add(lblStudentGender);
		studentGenderComboBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				validateData(studentGenderComboBox, lblStudentGender);
			}
		});
		studentGenderComboBox.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student Gender</h3>\r\n<p style=\"font-size: 13;\">Please select the student's Gender</p>\r\n<html>");
		studentGenderComboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		studentGenderComboBox.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		studentGenderComboBox.setBounds(247, 317, 156, 23);
		studentGenderComboBox.setSelectedIndex(-1);
		
		studentInfoPanel.add(studentGenderComboBox);
		lblStudentRaceOther.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student Race</h3>\r\n<p style=\"font-size: 13;\">Please specify other race, or select a different option from the list</p>\r\n<html>");
		lblStudentRaceOther.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStudentRaceOther.setBounds(476, 366, 103, 17);
		lblStudentRaceOther.setVisible(false);
		
		studentInfoPanel.add(lblStudentRaceOther);
		studentRaceOtherTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				validateData(studentRaceOtherTF, lblStudentRaceOther);
			}
		});
		studentRaceOtherTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student Race</h3>\r\n<p style=\"font-size: 13;\">Please specify other race, or select a different option from the list</p>\r\n<html>");
		studentRaceOtherTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		studentRaceOtherTF.setBounds(581, 363, 220, 23);
		studentRaceOtherTF.setVisible(false);
		
		studentInfoPanel.add(studentRaceOtherTF);
		lblStudentRace.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student Race</h3>\r\n<p style=\"font-size: 13;\">Please select the student's Race</p>\r\n<html>");
		lblStudentRace.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStudentRace.setBounds(61, 366, 103, 17);
		
		studentInfoPanel.add(lblStudentRace);
		studentRaceComboBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				validateData(studentRaceComboBox, lblStudentRace);
			}
		});
		studentRaceComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(studentRaceComboBox.getSelectedIndex() != -1){
					if(studentRaceComboBox.getSelectedItem().toString().equals("Other")){
						lblStudentRaceOther.setVisible(true);
						studentRaceOtherTF.setVisible(true);
					}
					else{
						lblStudentRaceOther.setVisible(false);
						studentRaceOtherTF.setVisible(false);
					}
				}
			}
		});
		studentRaceComboBox.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student Race</h3>\r\n<p style=\"font-size: 13;\">Please select the student's Race</p>\r\n<html>");
		studentRaceComboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		studentRaceComboBox.setModel(new DefaultComboBoxModel(new String[] {"Black", "White", "Asian", "Hispanic", "Native American", "Other"}));
		studentRaceComboBox.setSelectedIndex(-1);
		studentRaceComboBox.setBounds(247, 363, 195, 23);
		
		studentInfoPanel.add(studentRaceComboBox);
		lblStudentGrade.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student Grade (15/16)</h3>\r\n<p style=\"font-size: 13;\">Which grade was the student in for the 2015/16 school year?</p>\r\n<html>");
		lblStudentGrade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStudentGrade.setBounds(61, 412, 152, 17);
		
		studentInfoPanel.add(lblStudentGrade);
		studentGradeComboBox.setModel(new DefaultComboBoxModel(new String[] {"Kindergarten", "1st Grade", "2nd Grade", "3rd Grade", "4th Grade", "5th Grade", "6th Grade", "7th Grade", "8th Grade", "9th Grade", "10th Grade", "11th Grade", "12th Grade"}));
		studentGradeComboBox.setSelectedIndex(-1);
		studentGradeComboBox.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				validateData(studentGradeComboBox, lblStudentGrade);
			}
		});
		studentGradeComboBox.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student Grade (15/16)</h3>\r\n<p style=\"font-size: 13;\">Which grade was the student in for the 2015/16 school year?</p>\r\n<html>");
		studentGradeComboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		studentGradeComboBox.setBounds(247, 409, 195, 23);
		
		studentInfoPanel.add(studentGradeComboBox);
		lblStudentSS.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student Social Security Number</h3>\r\n<p style=\"font-size: 13;\">Please enter the student's Social Security Number</p>\r\n<html>");
		lblStudentSS.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStudentSS.setBounds(61, 458, 103, 17);
		
		studentInfoPanel.add(lblStudentSS);
		StudentSSFTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				validateData(StudentSSFTF, lblStudentSS, true);
			}
		});
		StudentSSFTF.setForeground(Color.GRAY);
		StudentSSFTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student Social Security Number</h3>\r\n<p style=\"font-size: 13;\">Please enter the student's Social Security Number</p>\r\n<html>");
		StudentSSFTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		StudentSSFTF.setBounds(247, 455, 208, 23);
		
		studentInfoPanel.add(StudentSSFTF);
		lblStudentSubdivision.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student Subdivision</h3>\r\n<p style=\"font-size: 13;\">Please enter the student's Subdivision</p>\r\n<html>");
		lblStudentSubdivision.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStudentSubdivision.setBounds(61, 504, 142, 17);
		
		studentInfoPanel.add(lblStudentSubdivision);
		studentSubdivTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student Subdivision</h3>\r\n<p style=\"font-size: 13;\">Please enter the student's Subdivision</p>\r\n<html>");
		studentSubdivTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		studentSubdivTF.setColumns(10);
		studentSubdivTF.setBounds(247, 501, 250, 23);
		
		studentInfoPanel.add(studentSubdivTF);
		lblLeaseExpires.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Lease Expiration Year</h3>\r\n<p style=\"font-size: 13;\">Please enter the lease expiration year</p>\r\n<html>");
		lblLeaseExpires.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLeaseExpires.setBounds(503, 547, 108, 23);
		lblLeaseExpires.setVisible(false);
		
		studentInfoPanel.add(lblLeaseExpires);
		studentLeaseExpFTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				validateDate(studentLeaseExpFTF, lblLeaseExpires, Integer.parseInt(studentLeaseExpFTF.getText().trim()));
			}
		});
		studentLeaseExpFTF.setForeground(Color.GRAY);
		studentLeaseExpFTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Lease Expiration Year</h3>\r\n<p style=\"font-size: 13;\">Please enter the lease expiration year</p>\r\n<html>");
		studentLeaseExpFTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		studentLeaseExpFTF.setColumns(10);
		studentLeaseExpFTF.setBounds(606, 547, 103, 23);
		studentLeaseExpFTF.setVisible(false);
		
		studentInfoPanel.add(studentLeaseExpFTF);
		lblStudentOwnlease.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Own/Lease</h3>\r\n<p style=\"font-size: 13;\">Own or Lease?</p>\r\n<html>");
		lblStudentOwnlease.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStudentOwnlease.setBounds(61, 550, 126, 17);
		
		studentInfoPanel.add(lblStudentOwnlease);
		OwnLeaseButtonGroup.add(rdbtnStudentOwn);
		rdbtnStudentOwn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnStudentOwn.isSelected()){
					lblLeaseExpires.setVisible(false);
					studentLeaseExpFTF.setVisible(false);
				}
				else{
					lblLeaseExpires.setVisible(true);
					studentLeaseExpFTF.setVisible(true);
				}
			}
		});
		rdbtnStudentOwn.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Own/Lease</h3>\r\n<p style=\"font-size: 13;\">Own or Lease?</p>\r\n<html>");
		rdbtnStudentOwn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnStudentOwn.setBounds(249, 547, 86, 23);
		rdbtnStudentOwn.setSelected(false);
		
		studentInfoPanel.add(rdbtnStudentOwn);
		OwnLeaseButtonGroup.add(rdbtnStudentLease);
		rdbtnStudentLease.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnStudentLease.isSelected()){
					lblLeaseExpires.setVisible(true);
					studentLeaseExpFTF.setVisible(true);
				}
				else{
					lblLeaseExpires.setVisible(false);
					studentLeaseExpFTF.setVisible(false);
				}
			}
		});
		rdbtnStudentLease.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Own/Lease</h3>\r\n<p style=\"font-size: 13;\">Own or Lease?</p>\r\n<html>");
		rdbtnStudentLease.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnStudentLease.setBounds(347, 547, 86, 23);
		rdbtnStudentLease.setSelected(false);
		
		studentInfoPanel.add(rdbtnStudentLease);
		lblStreetAddress.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student Street Address</h3>\r\n<p style=\"font-size: 13;\">Please enter the student's Street Address</p>\r\n<html>");
		lblStreetAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStreetAddress.setBounds(61, 596, 171, 17);
		
		studentInfoPanel.add(lblStreetAddress);
		studentStreetTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				validateData(studentStreetTF, lblStreetAddress);
			}
		});
		studentStreetTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student Street Address</h3>\r\n<p style=\"font-size: 13;\">Please enter the student's Street Address</p>\r\n<html>");
		studentStreetTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		studentStreetTF.setColumns(10);
		studentStreetTF.setBounds(247, 593, 603, 23);
		
		studentInfoPanel.add(studentStreetTF);
		lblCity.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student City</h3>\r\n<p style=\"font-size: 13;\">Please enter the student's City</p>\r\n<html>");
		lblCity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCity.setBounds(61, 642, 82, 17);
		
		studentInfoPanel.add(lblCity);
		studentCityTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				validateData(studentCityTF, lblCity);
			}
		});
		studentCityTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student City</h3>\r\n<p style=\"font-size: 13;\">Please enter the student's City</p>\r\n<html>");
		studentCityTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		studentCityTF.setColumns(10);
		studentCityTF.setBounds(247, 639, 250, 23);
		
		studentInfoPanel.add(studentCityTF);
		lblZip.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student ZIP Code</h3>\r\n<p style=\"font-size: 13;\">Please enter the student's ZIP Code</p>\r\n<html>");
		lblZip.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblZip.setBounds(61, 688, 82, 17);
		
		studentInfoPanel.add(lblZip);
		studentZipFTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				validateData(studentZipFTF, lblZip, true);
			}
		});
		studentZipFTF.setForeground(Color.GRAY);
		studentZipFTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student ZIP Code</h3>\r\n<p style=\"font-size: 13;\">Please enter the student's ZIP Code</p>\r\n<html>");
		studentZipFTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		studentZipFTF.setColumns(10);
		studentZipFTF.setBounds(247, 685, 126, 23);
		
		studentInfoPanel.add(studentZipFTF);
		lblHomePhone.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student Home Phone</h3>\r\n<p style=\"font-size: 13;\">Please enter the student's Home Phone Number ((###) ###-####)</p>\r\n<html>");
		lblHomePhone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHomePhone.setBounds(61, 734, 152, 17);
		
		studentInfoPanel.add(lblHomePhone);
		studentHomePhoneFTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				validateData(studentHomePhoneFTF, lblHomePhone, true);
			}
		});
		studentHomePhoneFTF.setForeground(Color.GRAY);
		studentHomePhoneFTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student Home Phone</h3>\r\n<p style=\"font-size: 13;\">Please enter the student's Home Phone Number ((###) ###-####)</p>\r\n<html>");
		studentHomePhoneFTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		studentHomePhoneFTF.setColumns(10);
		studentHomePhoneFTF.setBounds(247, 731, 186, 23);
		
		studentInfoPanel.add(studentHomePhoneFTF);
		btnNextStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				informationTabbedPane.setSelectedIndex((informationTabbedPane.getSelectedIndex() + 1));
			}
		});
		btnNextStudent.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNextStudent.setBounds(1139, 11, 86, 23);
		
		studentInfoPanel.add(btnNextStudent);
		lblStudentInformation.setToolTipText("");
		lblStudentInformation.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblStudentInformation.setBounds(61, 38, 171, 17);
		
		studentInfoPanel.add(lblStudentInformation);
		
		informationTabbedPane.addTab("Family Information", null, familyInfoPanel, null);
		familyInfoPanel.setLayout(null);
		lblFamilyFather.setToolTipText("");
		lblFamilyFather.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFamilyFather.setBounds(233, 36, 142, 17);
		
		familyInfoPanel.add(lblFamilyFather);
		lblFamilyMother.setToolTipText("");
		lblFamilyMother.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFamilyMother.setBounds(813, 36, 142, 17);
		
		familyInfoPanel.add(lblFamilyMother);
		lblFatherFname.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Father/Guardian 1 First Name</h3>\r\n<p style=\"font-size: 13;\">Please enter the First Name</p>\r\n<html>");
		lblFatherFname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFatherFname.setBounds(64, 74, 115, 17);
		
		familyInfoPanel.add(lblFatherFname);
		fatherFnameTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				validateData(fatherFnameTF, lblFatherFname);
			}
		});
		fatherFnameTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Father/Guardian 1 First Name</h3>\r\n<p style=\"font-size: 13;\">Please enter the First Name</p>\r\n<html>");
		fatherFnameTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fatherFnameTF.setColumns(10);
		fatherFnameTF.setBounds(208, 71, 270, 23);
		
		familyInfoPanel.add(fatherFnameTF);
		lblFatherMI.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Father/Guardian 1 Middle Initial</h3>\r\n<p style=\"font-size: 13;\">Please enter the Middle Initial</p>\r\n<html>");
		lblFatherMI.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFatherMI.setBounds(64, 105, 101, 17);
		
		familyInfoPanel.add(lblFatherMI);
		fatherMITF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Father/Guardian 1 Middle Initial</h3>\r\n<p style=\"font-size: 13;\">Please enter the Middle Initial</p>\r\n<html>");
		fatherMITF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fatherMITF.setColumns(10);
		fatherMITF.setBounds(207, 102, 31, 23);
		
		familyInfoPanel.add(fatherMITF);
		fatherLnameTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				validateData(fatherLnameTF, lblFatherLname);
			}
		});
		lblFatherLname.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Father/Guardian 1 Last Name</h3>\r\n<p style=\"font-size: 13;\">Please enter the Last Name</p>\r\n<html>");
		lblFatherLname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFatherLname.setBounds(64, 136, 115, 17);
		
		familyInfoPanel.add(lblFatherLname);
		fatherLnameTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Father/Guardian 1 Last Name</h3>\r\n<p style=\"font-size: 13;\">Please enter the Last Name</p>\r\n<html>");
		fatherLnameTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fatherLnameTF.setColumns(10);
		fatherLnameTF.setBounds(207, 133, 270, 23);
		
		familyInfoPanel.add(fatherLnameTF);
		lblFatherCell.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Father/Guardian 1 Cell Phone</h3>\r\n<p style=\"font-size: 13;\">Please enter the Parent/Guardian's Cell Phone Number ((###) ###-####)</p>\r\n<html>");
		lblFatherCell.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFatherCell.setBounds(64, 167, 106, 17);
		
		familyInfoPanel.add(lblFatherCell);
		fatherCellFTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				validateData(fatherCellFTF, lblFatherCell, true);
			}
		});
		fatherCellFTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Father/Guardian 1 Cell Phone</h3>\r\n<p style=\"font-size: 13;\">Please enter the Parent/Guardian's Cell Phone Number ((###) ###-####)</p>\r\n<html>");
		fatherCellFTF.setForeground(Color.GRAY);
		fatherCellFTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fatherCellFTF.setColumns(10);
		fatherCellFTF.setBounds(207, 164, 186, 23);
		
		familyInfoPanel.add(fatherCellFTF);
		lblFatherWorkPhone.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Father/Guardian 1 Work Phone</h3>\r\n<p style=\"font-size: 13;\">Please enter the Parent/Guardian's Work Phone Number ((###) ###-####)</p>\r\n<html>");
		lblFatherWorkPhone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFatherWorkPhone.setBounds(64, 198, 106, 17);
		
		familyInfoPanel.add(lblFatherWorkPhone);
		fatherWorkPhoneFTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Father/Guardian 1 Work Phone</h3>\r\n<p style=\"font-size: 13;\">Please enter the Parent/Guardian's Work Phone Number ((###) ###-####)</p>\r\n<html>");
		fatherWorkPhoneFTF.setForeground(Color.GRAY);
		fatherWorkPhoneFTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fatherWorkPhoneFTF.setColumns(10);
		fatherWorkPhoneFTF.setBounds(207, 195, 186, 23);
		
		familyInfoPanel.add(fatherWorkPhoneFTF);
		lblFatherHomePhone.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Father/Guardian 1 Home Phone</h3>\r\n<p style=\"font-size: 13;\">Please enter the Parent/Guardian's Home Phone Number ((###) ###-####)</p>\r\n<html>");
		lblFatherHomePhone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFatherHomePhone.setBounds(64, 229, 106, 17);
		
		familyInfoPanel.add(lblFatherHomePhone);
		fatherHomePhoneFTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Father/Guardian 1 Home Phone</h3>\r\n<p style=\"font-size: 13;\">Please enter the Parent/Guardian's Home Phone Number ((###) ###-####)</p>\r\n<html>");
		fatherHomePhoneFTF.setForeground(Color.GRAY);
		fatherHomePhoneFTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fatherHomePhoneFTF.setColumns(10);
		fatherHomePhoneFTF.setBounds(207, 226, 186, 23);
		
		familyInfoPanel.add(fatherHomePhoneFTF);
		fatherAddressTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				validateData(fatherAddressTF, lblFatherAddress);
			}
		});
		fatherAddressTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Father/Guardian 1 Address</h3>\r\n<p style=\"font-size: 13;\">Please enter the parent/guardian's Address</p>\r\n<html>");
		fatherAddressTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fatherAddressTF.setColumns(10);
		fatherAddressTF.setBounds(208, 260, 378, 23);
		
		familyInfoPanel.add(fatherAddressTF);
		lblFatherAddress.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Father/Guardian 1 Address</h3>\r\n<p style=\"font-size: 13;\">Please enter the parent/guardian's Address</p>\r\n<html>");
		lblFatherAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFatherAddress.setBounds(64, 263, 115, 17);
		
		familyInfoPanel.add(lblFatherAddress);
		lblFatherWorkplace.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Father/Guardian 1 Place of Employment</h3>\r\n<p style=\"font-size: 13;\">Please enter the parent/guardian's Place of Employment</p>\r\n<html>");
		lblFatherWorkplace.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFatherWorkplace.setBounds(64, 297, 136, 17);
		
		familyInfoPanel.add(lblFatherWorkplace);
		fatherWorkplaceTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Father/Guardian 1 Place of Employment</h3>\r\n<p style=\"font-size: 13;\">Please enter the parent/guardian's Place of Employment</p>\r\n<html>");
		fatherWorkplaceTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fatherWorkplaceTF.setColumns(10);
		fatherWorkplaceTF.setBounds(208, 294, 263, 23);
		
		familyInfoPanel.add(fatherWorkplaceTF);
		lblFatherEmail.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Father/Guardian 1 Email Address</h3>\r\n<p style=\"font-size: 13;\">Please enter the parent/guardian's Email Address</p>\r\n<html>");
		lblFatherEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFatherEmail.setBounds(64, 331, 115, 17);
		
		familyInfoPanel.add(lblFatherEmail);
		fatherEmailTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				validateData(fatherEmailTF, lblFatherEmail);
			}
		});
		fatherEmailTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Father/Guardian 1 Email Address</h3>\r\n<p style=\"font-size: 13;\">Please enter the parent/guardian's Email Address</p>\r\n<html>");
		fatherEmailTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fatherEmailTF.setColumns(10);
		fatherEmailTF.setBounds(208, 328, 263, 23);
		
		familyInfoPanel.add(fatherEmailTF);
		lblFamilyGeneral.setToolTipText("");
		lblFamilyGeneral.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFamilyGeneral.setBounds(64, 414, 70, 17);
		
		familyInfoPanel.add(lblFamilyGeneral);
		GeneralSeparator.setBounds(10, 386, 1215, 17);
		
		familyInfoPanel.add(GeneralSeparator);
		ParentSeparator.setOrientation(SwingConstants.VERTICAL);
		ParentSeparator.setBounds(614, 36, 17, 339);
		
		familyInfoPanel.add(ParentSeparator);
		lblMotherFname.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Mother/Guardian 2 First Name</h3>\r\n<p style=\"font-size: 13;\">Please enter the First Name</p>\r\n<html>");
		lblMotherFname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMotherFname.setBounds(667, 74, 101, 17);
		
		familyInfoPanel.add(lblMotherFname);
		motherFnameTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				validateData(motherFnameTF, lblMotherFname);
			}
		});
		motherFnameTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Mother/Guardian 2 First Name</h3>\r\n<p style=\"font-size: 13;\">Please enter the First Name</p>\r\n<html>");
		motherFnameTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		motherFnameTF.setColumns(10);
		motherFnameTF.setBounds(810, 71, 270, 23);
		
		familyInfoPanel.add(motherFnameTF);
		lblMotherMI.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Mother/Guardian 2 Middle Initial</h3>\r\n<p style=\"font-size: 13;\">Please enter the Middle Initial</p>\r\n<html>");
		lblMotherMI.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMotherMI.setBounds(667, 105, 101, 17);
		
		familyInfoPanel.add(lblMotherMI);
		motherMITF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Mother/Guardian 2 Middle Initial</h3>\r\n<p style=\"font-size: 13;\">Please enter the Middle Initial</p>\r\n<html>");
		motherMITF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		motherMITF.setColumns(10);
		motherMITF.setBounds(810, 102, 31, 23);
		
		familyInfoPanel.add(motherMITF);
		lblMotherLname.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Mother/Guardian 2 Last Name</h3>\r\n<p style=\"font-size: 13;\">Please enter the Last Name</p>\r\n<html>");
		lblMotherLname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMotherLname.setBounds(667, 136, 106, 17);
		
		familyInfoPanel.add(lblMotherLname);
		motherLnameTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				validateData(motherLnameTF, lblMotherLname);
			}
		});
		motherLnameTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Mother/Guardian 2 Last Name</h3>\r\n<p style=\"font-size: 13;\">Please enter the Last Name</p>\r\n<html>");
		motherLnameTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		motherLnameTF.setColumns(10);
		motherLnameTF.setBounds(811, 133, 270, 23);
		
		familyInfoPanel.add(motherLnameTF);
		lblMotherCell.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Mother/Guardian 2 Cell Phone</h3>\r\n<p style=\"font-size: 13;\">Please enter the Parent/Guardian's Cell Phone Number ((###) ###-####)</p>\r\n<html>");
		lblMotherCell.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMotherCell.setBounds(667, 167, 106, 17);
		
		familyInfoPanel.add(lblMotherCell);
		motherCellFTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				validateData(motherCellFTF, lblMotherCell, true);
			}
		});
		motherCellFTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Mother/Guardian 2 Cell Phone</h3>\r\n<p style=\"font-size: 13;\">Please enter the Parent/Guardian's Cell Phone Number ((###) ###-####)</p>\r\n<html>");
		motherCellFTF.setForeground(Color.GRAY);
		motherCellFTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		motherCellFTF.setColumns(10);
		motherCellFTF.setBounds(810, 164, 186, 23);
		
		familyInfoPanel.add(motherCellFTF);
		lblMotherWorkPhone.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Mother/Guardian 2 Work Phone</h3>\r\n<p style=\"font-size: 13;\">Please enter the Parent/Guardian's Work Phone Number ((###) ###-####)</p>\r\n<html>");
		lblMotherWorkPhone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMotherWorkPhone.setBounds(667, 198, 106, 17);
		
		familyInfoPanel.add(lblMotherWorkPhone);
		motherWorkPhoneFTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Mother/Guardian 2 Work Phone</h3>\r\n<p style=\"font-size: 13;\">Please enter the Parent/Guardian's Work Phone Number ((###) ###-####)</p>\r\n<html>");
		motherWorkPhoneFTF.setForeground(Color.GRAY);
		motherWorkPhoneFTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		motherWorkPhoneFTF.setColumns(10);
		motherWorkPhoneFTF.setBounds(810, 195, 186, 23);
		
		familyInfoPanel.add(motherWorkPhoneFTF);
		lblMotherHomePhone.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Mother/Guardian 2 Home Phone</h3>\r\n<p style=\"font-size: 13;\">Please enter the Parent/Guardian's Home Phone Number ((###) ###-####)</p>\r\n<html>");
		lblMotherHomePhone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMotherHomePhone.setBounds(667, 229, 106, 17);
		
		familyInfoPanel.add(lblMotherHomePhone);
		motherHomePhoneFTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Mother/Guardian 2 Home Phone</h3>\r\n<p style=\"font-size: 13;\">Please enter the Parent/Guardian's Home Phone Number ((###) ###-####)</p>\r\n<html>");
		motherHomePhoneFTF.setForeground(Color.GRAY);
		motherHomePhoneFTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		motherHomePhoneFTF.setColumns(10);
		motherHomePhoneFTF.setBounds(810, 226, 186, 23);
		
		familyInfoPanel.add(motherHomePhoneFTF);
		lblMotherAddress.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Mother/Guardian 2 Address</h3>\r\n<p style=\"font-size: 13;\">Please enter the parent/guardian's Address</p>\r\n<html>");
		lblMotherAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMotherAddress.setBounds(667, 263, 115, 17);
		
		familyInfoPanel.add(lblMotherAddress);
		motherAddressTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				validateData(motherAddressTF, lblMotherAddress);
			}
		});
		motherAddressTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Mother/Guardian 2 Address</h3>\r\n<p style=\"font-size: 13;\">Please enter the parent/guardian's Address</p>\r\n<html>");
		motherAddressTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		motherAddressTF.setColumns(10);
		motherAddressTF.setBounds(811, 260, 378, 23);
		
		familyInfoPanel.add(motherAddressTF);
		lblMotherWorkplace.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Mother/Guardian 2 Place of Employment</h3>\r\n<p style=\"font-size: 13;\">Please enter the parent/guardian's Place of Employment</p>\r\n<html>");
		lblMotherWorkplace.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMotherWorkplace.setBounds(667, 297, 136, 17);
		
		familyInfoPanel.add(lblMotherWorkplace);
		motherWorkplaceTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Mother/Guardian 2 Place of Employment</h3>\r\n<p style=\"font-size: 13;\">Please enter the parent/guardian's Place of Employment</p>\r\n<html>");
		motherWorkplaceTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		motherWorkplaceTF.setColumns(10);
		motherWorkplaceTF.setBounds(811, 294, 263, 23);
		
		familyInfoPanel.add(motherWorkplaceTF);
		lblMotherEmail.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Mother/Guardian 2 Email Address</h3>\r\n<p style=\"font-size: 13;\">Please enter the parent/guardian's Email Address</p>\r\n<html>");
		lblMotherEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMotherEmail.setBounds(667, 331, 115, 17);
		
		familyInfoPanel.add(lblMotherEmail);
		motherEmailTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				validateData(motherEmailTF, lblMotherEmail);
			}
		});
		motherEmailTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Mother/Guardian 2 Email Address</h3>\r\n<p style=\"font-size: 13;\">Please enter the parent/guardian's Email Address</p>\r\n<html>");
		motherEmailTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		motherEmailTF.setColumns(10);
		motherEmailTF.setBounds(811, 328, 263, 23);
		
		familyInfoPanel.add(motherEmailTF);
		lblChildLivesWith.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Child Lives With</h3>\r\n<p style=\"font-size 13;\">Please specify who the child lives with</p>\r\n<html>");
		lblChildLivesWith.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblChildLivesWith.setBounds(64, 445, 115, 17);
		
		familyInfoPanel.add(lblChildLivesWith);
		ChildLivesWithGroup.add(rdbtnBothParents);
		rdbtnBothParents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnBothParents.isSelected()){
					fatherAddressTF.setText(studentStreetTF.getText().trim());
					fatherAddressTF.setEnabled(false);
					motherAddressTF.setText(studentStreetTF.getText().trim());
					motherAddressTF.setEnabled(false);
					lblChildLivesWithOther.setVisible(false);
					familyOtherNameTF.setVisible(false);
					
					fatherHomePhoneFTF.setText(studentHomePhoneFTF.getText());
					fatherHomePhoneFTF.setEnabled(false);
					motherHomePhoneFTF.setText(studentHomePhoneFTF.getText());
					motherHomePhoneFTF.setEnabled(false);
				}
			}
		});
		rdbtnBothParents.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Child Lives With</h3>\r\n<p style=\"font-size 13;\">Please specify who the child lives with</p>\r\n<html>");
		rdbtnBothParents.setSelected(false);
		rdbtnBothParents.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnBothParents.setBounds(190, 442, 115, 23);
		
		familyInfoPanel.add(rdbtnBothParents);
		ChildLivesWithGroup.add(rdbtnFather);
		rdbtnFather.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnFather.isSelected()){
					fatherAddressTF.setText(studentStreetTF.getText().trim());
					fatherAddressTF.setEnabled(false);
					motherAddressTF.setEnabled(true);
					motherAddressTF.setText("");
					lblChildLivesWithOther.setVisible(false);
					familyOtherNameTF.setVisible(false);

					fatherHomePhoneFTF.setText(studentHomePhoneFTF.getText());
					fatherHomePhoneFTF.setEnabled(false);
					motherHomePhoneFTF.setEnabled(true);
					motherHomePhoneFTF.setText("(000) 000-0000");
					
				}
			}
		});
		rdbtnFather.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Child Lives With</h3>\r\n<p style=\"font-size 13;\">Please specify who the child lives with</p>\r\n<html>");
		rdbtnFather.setSelected(false);
		rdbtnFather.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnFather.setBounds(307, 442, 76, 23);
		
		familyInfoPanel.add(rdbtnFather);
		ChildLivesWithGroup.add(rdbtnMother);
		rdbtnMother.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnMother.isSelected()){
					motherAddressTF.setText(studentStreetTF.getText().trim());
					motherAddressTF.setEnabled(false);
					fatherAddressTF.setEnabled(true);
					fatherAddressTF.setText("");
					lblChildLivesWithOther.setVisible(false);
					familyOtherNameTF.setVisible(false);
					
					fatherHomePhoneFTF.setEnabled(true);
					fatherHomePhoneFTF.setText("(000) 000-0000");
					motherHomePhoneFTF.setText(studentHomePhoneFTF.getText());
					motherHomePhoneFTF.setEnabled(false);
				}
			}
		});
		rdbtnMother.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Child Lives With</h3>\r\n<p style=\"font-size 13;\">Please specify who the child lives with</p>\r\n<html>");
		rdbtnMother.setSelected(false);
		rdbtnMother.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnMother.setBounds(385, 442, 76, 23);
		
		familyInfoPanel.add(rdbtnMother);
		ChildLivesWithGroup.add(rdbtnOther);
		rdbtnOther.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnOther.isSelected()){
					fatherAddressTF.setEnabled(true);
					fatherAddressTF.setText("");
					motherAddressTF.setEnabled(true);
					motherAddressTF.setText("");
					lblChildLivesWithOther.setVisible(true);
					familyOtherNameTF.setVisible(true);
					
					fatherHomePhoneFTF.setEnabled(true);
					fatherHomePhoneFTF.setText("(000) 000-0000");
					motherHomePhoneFTF.setEnabled(true);
					motherHomePhoneFTF.setText("(000) 000-0000");
				}
			}
		});
		rdbtnOther.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Child Lives With</h3>\r\n<p style=\"font-size 13;\">Please specify who the child lives with</p>\r\n<html>");
		rdbtnOther.setSelected(false);
		rdbtnOther.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnOther.setBounds(463, 442, 86, 23);
		
		familyInfoPanel.add(rdbtnOther);
		lblChildLivesWithOther.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Child Lives With</h3>\r\n<p style=\"font-size 13;\">Please specify who the child lives with</p>\r\n<html>");
		lblChildLivesWithOther.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblChildLivesWithOther.setBounds(555, 445, 199, 17);
		lblChildLivesWithOther.setVisible(false);
		
		familyInfoPanel.add(lblChildLivesWithOther);
		familyOtherNameTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				validateData(familyOtherNameTF, lblChildLivesWithOther);
			}
		});
		familyOtherNameTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Child Lives With</h3>\r\n<p style=\"font-size 13;\">Please specify who the child lives with</p>\r\n<html>");
		familyOtherNameTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		familyOtherNameTF.setColumns(10);
		familyOtherNameTF.setBounds(775, 442, 263, 23);
		familyOtherNameTF.setVisible(false);
		
		familyInfoPanel.add(familyOtherNameTF);
		lblSiblings.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Student Middle Name</h3>\r\n<p style=\"font-size 13;\">Please enter the student's Middle Name (not required)</p>\r\n<html>");
		lblSiblings.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSiblings.setBounds(64, 481, 287, 17);
		
		familyInfoPanel.add(lblSiblings);
		siblingName1TF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Sibling Name</h3>\r\n<p style=\"font-size 13;\">Please enter the sibling's name</p>\r\n<html>");
		siblingName1TF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		siblingName1TF.setColumns(10);
		siblingName1TF.setBounds(88, 507, 263, 23);
		
		familyInfoPanel.add(siblingName1TF);
		siblingMonth1CB.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Sibling DOB Month</h3>\r\n<p style=\"font-size: 13;\">Please select the sibling's birth month</p>\r\n<html>");
		siblingMonth1CB.setModel(new DefaultComboBoxModel(Month.values()));
		siblingMonth1CB.setSelectedIndex(0);
		siblingMonth1CB.setFont(new Font("Tahoma", Font.PLAIN, 14));
		siblingMonth1CB.setBounds(388, 507, 156, 23);
		
		familyInfoPanel.add(siblingMonth1CB);
		siblingDay1FTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				validateDate(siblingDay1FTF, lblSiblings, (Integer)siblingDay1FTF.getValue(), siblingMonth1CB.getSelectedIndex());
			}
		});
		siblingDay1FTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Sibling DOB Day</h3>\r\n<p style=\"font-size: 13;\">Please enter the sibling's birth day</p>\r\n<html>");
		siblingDay1FTF.setForeground(Color.GRAY);
		siblingDay1FTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		siblingDay1FTF.setColumns(10);
		siblingDay1FTF.setBounds(553, 507, 42, 23);
		
		familyInfoPanel.add(siblingDay1FTF);
		siblingYear1FTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student DOB Year</h3>\r\n<p style=\"font-size: 13;\">Please enter the student's birth year</p>\r\n<html>");
		siblingYear1FTF.setForeground(Color.GRAY);
		siblingYear1FTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		siblingYear1FTF.setColumns(10);
		siblingYear1FTF.setBounds(605, 507, 83, 23);
		
		familyInfoPanel.add(siblingYear1FTF);
		siblingGrade1ComboBox.setModel(new DefaultComboBoxModel(new String[] {"Kindergarten", "1st Grade", "2nd Grade", "3rd Grade", "4th Grade", "5th Grade", "6th Grade", "7th Grade", "8th Grade", "9th Grade", "10th Grade", "11th Grade", "12th Grade"}));
		siblingGrade1ComboBox.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Sibling Grade</h3>\r\n<p style=\"font-size 13;\">Please enter the sibling's Grade</p>\r\n<html>");
		siblingGrade1ComboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		siblingGrade1ComboBox.setBounds(729, 507, 105, 23);
		
		familyInfoPanel.add(siblingGrade1ComboBox);
		siblingSchool1TF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Sibling School</h3>\r\n<p style=\"font-size 13;\">Please enter the sibling's School</p>\r\n<html>");
		siblingSchool1TF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		siblingSchool1TF.setColumns(10);
		siblingSchool1TF.setBounds(880, 507, 217, 23);
		
		familyInfoPanel.add(siblingSchool1TF);
		siblingName2TF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Sibling Name</h3>\r\n<p style=\"font-size 13;\">Please enter the sibling's name</p>\r\n<html>");
		siblingName2TF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		siblingName2TF.setColumns(10);
		siblingName2TF.setBounds(88, 536, 263, 23);
		
		familyInfoPanel.add(siblingName2TF);
		siblingMonth2CB.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Sibling DOB Month</h3>\r\n<p style=\"font-size: 13;\">Please select the sibling's birth month</p>\r\n<html>");
		siblingMonth2CB.setModel(new DefaultComboBoxModel(Month.values()));
		siblingMonth2CB.setSelectedIndex(0);
		siblingMonth2CB.setFont(new Font("Tahoma", Font.PLAIN, 14));
		siblingMonth2CB.setBounds(388, 536, 156, 23);
		
		familyInfoPanel.add(siblingMonth2CB);
		siblingDay2FTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Sibling DOB Day</h3>\r\n<p style=\"font-size: 13;\">Please enter the sibling's birth day</p>\r\n<html>");
		siblingDay2FTF.setForeground(Color.GRAY);
		siblingDay2FTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		siblingDay2FTF.setColumns(10);
		siblingDay2FTF.setBounds(553, 536, 42, 23);
		
		familyInfoPanel.add(siblingDay2FTF);
		siblingYear2FTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student DOB Year</h3>\r\n<p style=\"font-size: 13;\">Please enter the student's birth year</p>\r\n<html>");
		siblingYear2FTF.setForeground(Color.GRAY);
		siblingYear2FTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		siblingYear2FTF.setColumns(10);
		siblingYear2FTF.setBounds(605, 536, 83, 23);
		
		familyInfoPanel.add(siblingYear2FTF);
		siblingGrade2ComboBox.setModel(new DefaultComboBoxModel(new String[] {"Kindergarten", "1st Grade", "2nd Grade", "3rd Grade", "4th Grade", "5th Grade", "6th Grade", "7th Grade", "8th Grade", "9th Grade", "10th Grade", "11th Grade", "12th Grade"}));
		siblingGrade2ComboBox.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Sibling Grade</h3>\r\n<p style=\"font-size 13;\">Please enter the sibling's Grade</p>\r\n<html>");
		siblingGrade2ComboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		siblingGrade2ComboBox.setBounds(729, 536, 105, 23);
		
		familyInfoPanel.add(siblingGrade2ComboBox);
		siblingSchool2TF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Sibling School</h3>\r\n<p style=\"font-size 13;\">Please enter the sibling's School</p>\r\n<html>");
		siblingSchool2TF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		siblingSchool2TF.setColumns(10);
		siblingSchool2TF.setBounds(880, 536, 217, 23);
		
		familyInfoPanel.add(siblingSchool2TF);
		siblingName3TF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Sibling Name</h3>\r\n<p style=\"font-size 13;\">Please enter the sibling's name</p>\r\n<html>");
		siblingName3TF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		siblingName3TF.setColumns(10);
		siblingName3TF.setBounds(88, 566, 263, 23);
		
		familyInfoPanel.add(siblingName3TF);
		siblingMonth3CB.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Sibling DOB Month</h3>\r\n<p style=\"font-size: 13;\">Please select the sibling's birth month</p>\r\n<html>");
		siblingMonth3CB.setModel(new DefaultComboBoxModel(Month.values()));
		siblingMonth3CB.setSelectedIndex(0);
		siblingMonth3CB.setFont(new Font("Tahoma", Font.PLAIN, 14));
		siblingMonth3CB.setBounds(388, 566, 156, 23);
		
		familyInfoPanel.add(siblingMonth3CB);
		siblingDay3TF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Sibling DOB Day</h3>\r\n<p style=\"font-size: 13;\">Please enter the sibling's birth day</p>\r\n<html>");
		siblingDay3TF.setForeground(Color.GRAY);
		siblingDay3TF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		siblingDay3TF.setColumns(10);
		siblingDay3TF.setBounds(553, 566, 42, 23);
		
		familyInfoPanel.add(siblingDay3TF);
		siblingYear3FTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size: 14;\">Student DOB Year</h3>\r\n<p style=\"font-size: 13;\">Please enter the student's birth year</p>\r\n<html>");
		siblingYear3FTF.setForeground(Color.GRAY);
		siblingYear3FTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		siblingYear3FTF.setColumns(10);
		siblingYear3FTF.setBounds(605, 566, 83, 23);
		
		familyInfoPanel.add(siblingYear3FTF);
		siblingGrade3ComboBox.setModel(new DefaultComboBoxModel(new String[] {"Kindergarten", "1st Grade", "2nd Grade", "3rd Grade", "4th Grade", "5th Grade", "6th Grade", "7th Grade", "8th Grade", "9th Grade", "10th Grade", "11th Grade", "12th Grade"}));
		siblingGrade3ComboBox.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Sibling Grade</h3>\r\n<p style=\"font-size 13;\">Please enter the sibling's Grade</p>\r\n<html>");
		siblingGrade3ComboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		siblingGrade3ComboBox.setBounds(729, 566, 105, 23);
		
		familyInfoPanel.add(siblingGrade3ComboBox);
		siblingSchool3TF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Sibling School</h3>\r\n<p style=\"font-size 13;\">Please enter the sibling's School</p>\r\n<html>");
		siblingSchool3TF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		siblingSchool3TF.setColumns(10);
		siblingSchool3TF.setBounds(880, 566, 217, 23);
		
		familyInfoPanel.add(siblingSchool3TF);
		lblSpecialServices.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Special Services</h3>\r\n<p style=\"font-size 13;\">Please speicify if your child requires special services</p>\r\n<html>");
		lblSpecialServices.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSpecialServices.setBounds(64, 606, 115, 17);
		
		familyInfoPanel.add(lblSpecialServices);
		SpecialServicesGroup.add(rdbtnGifted);
		rdbtnGifted.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Special Services</h3>\r\n<p style=\"font-size 13;\">Please speicify if your child requires special services</p>\r\n<html>");
		rdbtnGifted.setSelected(false);
		rdbtnGifted.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnGifted.setBounds(190, 603, 70, 23);
		
		familyInfoPanel.add(rdbtnGifted);
		SpecialServicesGroup.add(rdbtnSpediep);
		rdbtnSpediep.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Special Services</h3>\r\n<p style=\"font-size 13;\">Please speicify if your child requires special services</p>\r\n<html>");
		rdbtnSpediep.setSelected(false);
		rdbtnSpediep.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnSpediep.setBounds(262, 603, 86, 23);
		
		familyInfoPanel.add(rdbtnSpediep);
		SpecialServicesGroup.add(rdbtnSpeechied);
		rdbtnSpeechied.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Special Services</h3>\r\n<p style=\"font-size 13;\">Please speicify if your child requires special services</p>\r\n<html>");
		rdbtnSpeechied.setSelected(false);
		rdbtnSpeechied.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnSpeechied.setBounds(349, 603, 100, 23);
		
		familyInfoPanel.add(rdbtnSpeechied);
		SpecialServicesGroup.add(rdbtnEll);
		rdbtnEll.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Special Services</h3>\r\n<p style=\"font-size 13;\">Please speicify if your child requires special services</p>\r\n<html>");
		rdbtnEll.setSelected(false);
		rdbtnEll.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnEll.setBounds(451, 603, 70, 23);
		
		familyInfoPanel.add(rdbtnEll);
		lblLifeThreateningAllergy.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Life Threatening Allergy</h3>\r\n<p style=\"font-size 13;\">Please specify if your child has any Life Threatening Allergy</p>\r\n<html>");
		lblLifeThreateningAllergy.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLifeThreateningAllergy.setBounds(64, 636, 156, 17);
		
		familyInfoPanel.add(lblLifeThreateningAllergy);
		lifeThreateningAllergyTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Special Services</h3>\r\n<p style=\"font-size 13;\">Please speicify if your child requires special services</p>\r\n<html>");
		lifeThreateningAllergyTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lifeThreateningAllergyTF.setColumns(10);
		lifeThreateningAllergyTF.setBounds(235, 634, 263, 23);
		
		familyInfoPanel.add(lifeThreateningAllergyTF);
		lblEmergencyNumbers.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Authorized Emergency Numbers</h3>\r\n<p style=\"font-size 13;\">Please specify emergency numbers and individuals \r\nauthorized to check out in addition to the parent/guardian</p>\r\n<html>");
		lblEmergencyNumbers.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmergencyNumbers.setBounds(64, 675, 609, 17);
		
		familyInfoPanel.add(lblEmergencyNumbers);
		lblEmergency1.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Student Middle Name</h3>\r\n<p style=\"font-size 13;\">Please enter the student's Middle Name (not required)</p>\r\n<html>");
		lblEmergency1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmergency1.setBounds(91, 707, 22, 17);
		
		familyInfoPanel.add(lblEmergency1);
		lblEmergencyName1.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Authorized Emergency Numbers</h3>\r\n<p style=\"font-size 13;\">Please specify emergency numbers and individuals \r\nauthorized to check out in addition to the parent/guardian</p>\r\n<html>");
		lblEmergencyName1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmergencyName1.setBounds(139, 707, 70, 17);
		
		familyInfoPanel.add(lblEmergencyName1);
		emergencyName1TF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Authorized Emergency Numbers</h3>\r\n<p style=\"font-size 13;\">Please specify emergency numbers and individuals \r\nauthorized to check out in addition to the parent/guardian</p>\r\n<html>");
		emergencyName1TF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		emergencyName1TF.setColumns(10);
		emergencyName1TF.setBounds(242, 704, 263, 23);
		
		familyInfoPanel.add(emergencyName1TF);
		lblEmergencyRelationship1.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Authorized Emergency Numbers</h3>\r\n<p style=\"font-size 13;\">Please specify emergency numbers and individuals \r\nauthorized to check out in addition to the parent/guardian</p>\r\n<html>");
		lblEmergencyRelationship1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmergencyRelationship1.setBounds(139, 737, 83, 17);
		
		familyInfoPanel.add(lblEmergencyRelationship1);
		emergencyRelationship1ComboBox.setModel(new DefaultComboBoxModel(new String[] {"Child", "GrandChild", "Sibling", "Parent", "GrandParent", "ExtendedFamily", "Other"}));
		emergencyRelationship1ComboBox.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Authorized Emergency Numbers</h3>\r\n<p style=\"font-size 13;\">Please specify emergency numbers and individuals \r\nauthorized to check out in addition to the parent/guardian</p>\r\n<html>");
		emergencyRelationship1ComboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		emergencyRelationship1ComboBox.setBounds(242, 734, 164, 23);
		
		familyInfoPanel.add(emergencyRelationship1ComboBox);
		lblEmergencyPhone1.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Authorized Emergency Numbers</h3>\r\n<p style=\"font-size 13;\">Please specify emergency numbers and individuals \r\nauthorized to check out in addition to the parent/guardian</p>\r\n<html>");
		lblEmergencyPhone1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmergencyPhone1.setBounds(139, 767, 83, 17);
		
		familyInfoPanel.add(lblEmergencyPhone1);
		emergencyPhone1FTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Authorized Emergency Numbers</h3>\r\n<p style=\"font-size 13;\">Please specify emergency numbers and individuals \r\nauthorized to check out in addition to the parent/guardian</p>\r\n<html>");
		emergencyPhone1FTF.setForeground(Color.GRAY);
		emergencyPhone1FTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		emergencyPhone1FTF.setColumns(10);
		emergencyPhone1FTF.setBounds(242, 764, 186, 23);
		
		familyInfoPanel.add(emergencyPhone1FTF);
		lblEmergency2.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Student Middle Name</h3>\r\n<p style=\"font-size 13;\">Please enter the student's Middle Name (not required)</p>\r\n<html>");
		lblEmergency2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmergency2.setBounds(672, 707, 22, 17);
		
		familyInfoPanel.add(lblEmergency2);
		lblEmergencyName2.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Authorized Emergency Numbers</h3>\r\n<p style=\"font-size 13;\">Please specify emergency numbers and individuals \r\nauthorized to check out in addition to the parent/guardian</p>\r\n<html>");
		lblEmergencyName2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmergencyName2.setBounds(720, 707, 70, 17);
		
		familyInfoPanel.add(lblEmergencyName2);
		emergencyName2TF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Authorized Emergency Numbers</h3>\r\n<p style=\"font-size 13;\">Please specify emergency numbers and individuals \r\nauthorized to check out in addition to the parent/guardian</p>\r\n<html>");
		emergencyName2TF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		emergencyName2TF.setColumns(10);
		emergencyName2TF.setBounds(823, 704, 263, 23);
		
		familyInfoPanel.add(emergencyName2TF);
		lblEmergencyRelationship2.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Authorized Emergency Numbers</h3>\r\n<p style=\"font-size 13;\">Please specify emergency numbers and individuals \r\nauthorized to check out in addition to the parent/guardian</p>\r\n<html>");
		lblEmergencyRelationship2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmergencyRelationship2.setBounds(720, 737, 83, 17);
		
		familyInfoPanel.add(lblEmergencyRelationship2);
		emergencyRelationship2ComboBox.setModel(new DefaultComboBoxModel(new String[] {"Child", "GrandChild", "Sibling", "Parent", "GrandParent", "ExtendedFamily", "Other"}));
		emergencyRelationship2ComboBox.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Authorized Emergency Numbers</h3>\r\n<p style=\"font-size 13;\">Please specify emergency numbers and individuals \r\nauthorized to check out in addition to the parent/guardian</p>\r\n<html>");
		emergencyRelationship2ComboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		emergencyRelationship2ComboBox.setBounds(823, 734, 164, 23);
		
		familyInfoPanel.add(emergencyRelationship2ComboBox);
		lblEmergencyPhone2.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Authorized Emergency Numbers</h3>\r\n<p style=\"font-size 13;\">Please specify emergency numbers and individuals \r\nauthorized to check out in addition to the parent/guardian</p>\r\n<html>");
		lblEmergencyPhone2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmergencyPhone2.setBounds(720, 767, 83, 17);
		
		familyInfoPanel.add(lblEmergencyPhone2);
		emergencyPhone2FTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Authorized Emergency Numbers</h3>\r\n<p style=\"font-size 13;\">Please specify emergency numbers and individuals \r\nauthorized to check out in addition to the parent/guardian</p>\r\n<html>");
		emergencyPhone2FTF.setForeground(Color.GRAY);
		emergencyPhone2FTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		emergencyPhone2FTF.setColumns(10);
		emergencyPhone2FTF.setBounds(823, 764, 186, 23);
		
		familyInfoPanel.add(emergencyPhone2FTF);
		rdbtnYesMyChilds.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Allow MSE Directory Listing</h3>\r\n<p style=\"font-size 13;\">Do you wan your child's name, address and phone number to potentially appear in the MSE directory?</p>\r\n<html>");
		rdbtnYesMyChilds.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnYesMyChilds.setBounds(64, 804, 500, 23);
		
		familyInfoPanel.add(rdbtnYesMyChilds);
		MSEDirectoryYesNo.add(chckbxNewCheckBox);
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxNewCheckBox.setBounds(577, 804, 47, 23);
		
		familyInfoPanel.add(chckbxNewCheckBox);
		MSEDirectoryYesNo.add(chckbxNo);
		chckbxNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxNo.setBounds(626, 804, 47, 23);
		
		familyInfoPanel.add(chckbxNo);
		lblToHelpUs.setToolTipText("");
		lblToHelpUs.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblToHelpUs.setBounds(64, 838, 819, 17);
		
		familyInfoPanel.add(lblToHelpUs);
		txtAreaToHelpUs.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Child Details</h3>\r\n<p style=\"font-size 13;\">Please tell us your child's  personality, learning styles, social skills and academic strengths:*</p>\r\n<html>");
		txtAreaToHelpUs.setFont(new Font("Courier New", Font.PLAIN, 12));
		txtAreaToHelpUs.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				validateData(txtAreaToHelpUs, lblToHelpUs);
			}
		});
		txtAreaToHelpUs.setBounds(64, 865, 1085, 56);
		
		familyInfoPanel.add(txtAreaToHelpUs);
		btnNextFamily.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				informationTabbedPane.setSelectedIndex((informationTabbedPane.getSelectedIndex() + 1));
			}
		});
		btnNextFamily.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNextFamily.setBounds(1139, 11, 86, 23);
		
		familyInfoPanel.add(btnNextFamily);
		btnPreviousFamily.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				informationTabbedPane.setSelectedIndex((informationTabbedPane.getSelectedIndex() - 1));
			}
		});
		btnPreviousFamily.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPreviousFamily.setBounds(1054, 11, 86, 23);
		
		familyInfoPanel.add(btnPreviousFamily);
		familyInfoPanel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{fatherFnameTF, fatherMITF, fatherLnameTF, fatherCellFTF, fatherWorkPhoneFTF, fatherAddressTF, fatherWorkplaceTF, fatherEmailTF, motherFnameTF, motherMITF, motherLnameTF, motherCellFTF, motherWorkPhoneFTF, motherHomePhoneFTF, motherAddressTF, motherWorkplaceTF, motherEmailTF, rdbtnBothParents, rdbtnFather, rdbtnMother, rdbtnOther, familyOtherNameTF, siblingName1TF, siblingMonth1CB, siblingDay1FTF, siblingYear1FTF, siblingGrade1ComboBox, siblingSchool1TF, siblingName2TF, siblingMonth2CB, siblingDay2FTF, siblingYear2FTF, siblingGrade2ComboBox, siblingSchool2TF, siblingName3TF, siblingMonth3CB, siblingDay3TF, siblingYear3FTF, siblingGrade3ComboBox, siblingSchool3TF, rdbtnGifted, rdbtnSpediep, rdbtnSpeechied, rdbtnEll, lifeThreateningAllergyTF, emergencyName1TF, emergencyRelationship1ComboBox, emergencyPhone1FTF, emergencyName2TF, emergencyRelationship2ComboBox, emergencyPhone2FTF, txtAreaToHelpUs, btnNextFamily, btnPreviousFamily}));
		
		informationTabbedPane.addTab("Financial Information", null, financialInfoPanel, null);
		financialInfoPanel.setLayout(null);
		btnPreviousFinance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				informationTabbedPane.setSelectedIndex((informationTabbedPane.getSelectedIndex() - 1));
			}
		});
		btnPreviousFinance.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPreviousFinance.setBounds(1139, 11, 86, 23);
		
		financialInfoPanel.add(btnPreviousFinance);
		lblFinanceInstr.setToolTipText("");
		lblFinanceInstr.setFont(new Font("Tahoma", Font.ITALIC, 15));
		lblFinanceInstr.setBounds(47, 28, 550, 17);
		
		financialInfoPanel.add(lblFinanceInstr);
		lblFinanceListAnyoneIn.setToolTipText("");
		lblFinanceListAnyoneIn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFinanceListAnyoneIn.setBounds(47, 55, 550, 17);
		
		financialInfoPanel.add(lblFinanceListAnyoneIn);
		lblFinanceName.setToolTipText("");
		lblFinanceName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFinanceName.setBounds(193, 83, 69, 17);
		
		financialInfoPanel.add(lblFinanceName);
		lblFinanceRelationship.setToolTipText("");
		lblFinanceRelationship.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFinanceRelationship.setBounds(489, 83, 113, 17);
		
		financialInfoPanel.add(lblFinanceRelationship);
		lblFinanceAge.setToolTipText("");
		lblFinanceAge.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFinanceAge.setBounds(693, 83, 47, 17);
		
		financialInfoPanel.add(lblFinanceAge);
		lblFinanceSS.setToolTipText("");
		lblFinanceSS.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFinanceSS.setBounds(773, 83, 113, 17);
		
		financialInfoPanel.add(lblFinanceSS);
		lblFinance1.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Student Middle Name</h3>\r\n<p style=\"font-size 13;\">Please enter the student's Middle Name (not required)</p>\r\n<html>");
		lblFinance1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFinance1.setBounds(156, 117, 22, 17);
		
		financialInfoPanel.add(lblFinance1);
		lblFinance2.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Student Middle Name</h3>\r\n<p style=\"font-size 13;\">Please enter the student's Middle Name (not required)</p>\r\n<html>");
		lblFinance2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFinance2.setBounds(156, 148, 22, 17);
		
		financialInfoPanel.add(lblFinance2);
		lblFinance3.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Student Middle Name</h3>\r\n<p style=\"font-size 13;\">Please enter the student's Middle Name (not required)</p>\r\n<html>");
		lblFinance3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFinance3.setBounds(156, 179, 22, 17);
		
		financialInfoPanel.add(lblFinance3);
		lblFinance4.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Student Middle Name</h3>\r\n<p style=\"font-size 13;\">Please enter the student's Middle Name (not required)</p>\r\n<html>");
		lblFinance4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFinance4.setBounds(156, 210, 22, 17);
		
		financialInfoPanel.add(lblFinance4);
		lblFinance5.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Student Middle Name</h3>\r\n<p style=\"font-size 13;\">Please enter the student's Middle Name (not required)</p>\r\n<html>");
		lblFinance5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFinance5.setBounds(156, 242, 22, 17);
		
		financialInfoPanel.add(lblFinance5);
		lblFinance6.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Student Middle Name</h3>\r\n<p style=\"font-size 13;\">Please enter the student's Middle Name (not required)</p>\r\n<html>");
		lblFinance6.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFinance6.setBounds(156, 273, 22, 17);
		
		financialInfoPanel.add(lblFinance6);
		financeMemberNameTF1.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Member Name</h3>\r\n<p style=\"font-size 13;\">Please enter the member's Name</p>\r\n<html>");
		financeMemberNameTF1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		financeMemberNameTF1.setColumns(10);
		financeMemberNameTF1.setBounds(193, 114, 270, 23);
		TotalIncomeTF.setEditable(false);
		TotalIncomeTF.setValue(0);
		TotalExpenseTF.setEditable(false);
		
		TotalExpenseTF.setValue(0);
		NetIncomeTF.setValue(0);
		NetIncomeTF.addPropertyChangeListener(new PropertyChangeListener() {
			//Changes color of Net Income field dependning on if its value is positive of negative
			public void propertyChange(PropertyChangeEvent evt) {
				if(((Number)NetIncomeTF.getValue()).doubleValue() < 0){
					NetIncomeTF.setForeground(Color.RED);
				}
				else{
					NetIncomeTF.setForeground(Color.BLACK);
				}
			}
		});
		NetIncomeTF.setEditable(false);
		
		TotalExpenseTF.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				do_sumnettotalCalc(TotalExpenseTF, NetIncomeTF, evt);
			}
		});
		
		
		financialInfoPanel.add(financeMemberNameTF1);
		financeMemberRelationshipComboBox1.setModel(new DefaultComboBoxModel(new String[] {"Child", "GrandChild", "Sibling", "Parent", "GrandParent", "ExtendedFamily", "Other"}));
		financeMemberRelationshipComboBox1.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Member Relationship</h3>\r\n<p style=\"font-size 13;\">Please specify the member's relationship to the child</p>\r\n<html>");
		financeMemberRelationshipComboBox1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		financeMemberRelationshipComboBox1.setBounds(489, 114, 174, 23);
		
		financialInfoPanel.add(financeMemberRelationshipComboBox1);
		financeMemberNameTF2.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Member Name</h3>\r\n<p style=\"font-size 13;\">Please enter the member's Name</p>\r\n<html>");
		financeMemberNameTF2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		financeMemberNameTF2.setColumns(10);
		financeMemberNameTF2.setBounds(193, 145, 270, 23);
		
		financialInfoPanel.add(financeMemberNameTF2);
		financeMemberRelationshipComboBox2.setModel(new DefaultComboBoxModel(new String[] {"Child", "GrandChild", "Sibling", "Parent", "GrandParent", "ExtendedFamily", "Other"}));
		financeMemberRelationshipComboBox2.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Student First Name</h3>\r\n<p style=\"font-size 13;\">Please enter the student's First Name</p>\r\n<html>");
		financeMemberRelationshipComboBox2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		financeMemberRelationshipComboBox2.setBounds(489, 145, 174, 23);
		
		financialInfoPanel.add(financeMemberRelationshipComboBox2);
		financeMemberNameTF3.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Member Name</h3>\r\n<p style=\"font-size 13;\">Please enter the member's Name</p>\r\n<html>");
		financeMemberNameTF3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		financeMemberNameTF3.setColumns(10);
		financeMemberNameTF3.setBounds(193, 176, 270, 23);
		
		financialInfoPanel.add(financeMemberNameTF3);
		financeMemberRelationshipComboBox3.setModel(new DefaultComboBoxModel(new String[] {"Child", "GrandChild", "Sibling", "Parent", "GrandParent", "ExtendedFamily", "Other"}));
		financeMemberRelationshipComboBox3.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Student First Name</h3>\r\n<p style=\"font-size 13;\">Please enter the student's First Name</p>\r\n<html>");
		financeMemberRelationshipComboBox3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		financeMemberRelationshipComboBox3.setBounds(489, 176, 174, 23);
		
		financialInfoPanel.add(financeMemberRelationshipComboBox3);
		financeMemberNameTF4.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Member Name</h3>\r\n<p style=\"font-size 13;\">Please enter the member's Name</p>\r\n<html>");
		financeMemberNameTF4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		financeMemberNameTF4.setColumns(10);
		financeMemberNameTF4.setBounds(193, 207, 270, 23);
		
		financialInfoPanel.add(financeMemberNameTF4);
		financeMemberRelationshipComboBox4.setModel(new DefaultComboBoxModel(new String[] {"Child", "GrandChild", "Sibling", "Parent", "GrandParent", "ExtendedFamily", "Other"}));
		financeMemberRelationshipComboBox4.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Student First Name</h3>\r\n<p style=\"font-size 13;\">Please enter the student's First Name</p>\r\n<html>");
		financeMemberRelationshipComboBox4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		financeMemberRelationshipComboBox4.setBounds(489, 207, 174, 23);
		
		financialInfoPanel.add(financeMemberRelationshipComboBox4);
		financeMemberNameTF5.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Member Name</h3>\r\n<p style=\"font-size 13;\">Please enter the member's Name</p>\r\n<html>");
		financeMemberNameTF5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		financeMemberNameTF5.setColumns(10);
		financeMemberNameTF5.setBounds(193, 239, 270, 23);
		
		financialInfoPanel.add(financeMemberNameTF5);
		financeMemberRelationshipComboBox5.setModel(new DefaultComboBoxModel(new String[] {"Child", "GrandChild", "Sibling", "Parent", "GrandParent", "ExtendedFamily", "Other"}));
		financeMemberRelationshipComboBox5.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Student First Name</h3>\r\n<p style=\"font-size 13;\">Please enter the student's First Name</p>\r\n<html>");
		financeMemberRelationshipComboBox5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		financeMemberRelationshipComboBox5.setBounds(489, 239, 174, 23);
		
		financialInfoPanel.add(financeMemberRelationshipComboBox5);
		financeMemberNameTF6.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Member Name</h3>\r\n<p style=\"font-size 13;\">Please enter the member's Name</p>\r\n<html>");
		financeMemberNameTF6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		financeMemberNameTF6.setColumns(10);
		financeMemberNameTF6.setBounds(193, 270, 270, 23);
		
		financialInfoPanel.add(financeMemberNameTF6);
		financeMemberRelationshipComboBox6.setModel(new DefaultComboBoxModel(new String[] {"Child", "GrandChild", "Sibling", "Parent", "GrandParent", "ExtendedFamily", "Other"}));
		financeMemberRelationshipComboBox6.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Student First Name</h3>\r\n<p style=\"font-size 13;\">Please enter the student's First Name</p>\r\n<html>");
		financeMemberRelationshipComboBox6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		financeMemberRelationshipComboBox6.setBounds(489, 270, 174, 23);
		
		financialInfoPanel.add(financeMemberRelationshipComboBox6);
		financeMemberAgeFTF1.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Member Age</h3>\r\n<p style=\"font-size 13;\">Please enter the member's Age</p>\r\n<html>");
		financeMemberAgeFTF1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		financeMemberAgeFTF1.setColumns(10);
		financeMemberAgeFTF1.setBounds(693, 114, 47, 23);
		
		financialInfoPanel.add(financeMemberAgeFTF1);
		financeSS1FTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Member Social Security Number</h3>\r\n<p style=\"font-size 13;\">Please enter the member's Social Security Number</p>\r\n<html>");
		financeSS1FTF.setForeground(Color.GRAY);
		financeSS1FTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		financeSS1FTF.setBounds(773, 114, 208, 23);
		
		financialInfoPanel.add(financeSS1FTF);
		financeMemberAgeFTF2.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Member Age</h3>\r\n<p style=\"font-size 13;\">Please enter the member's Age</p>\r\n<html>");
		financeMemberAgeFTF2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		financeMemberAgeFTF2.setColumns(10);
		financeMemberAgeFTF2.setBounds(693, 145, 47, 23);
		
		financialInfoPanel.add(financeMemberAgeFTF2);
		financeSS2FTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Member Social Security Number</h3>\r\n<p style=\"font-size 13;\">Please enter the member's Social Security Number</p>\r\n<html>");
		financeSS2FTF.setForeground(Color.GRAY);
		financeSS2FTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		financeSS2FTF.setBounds(773, 145, 208, 23);
		
		financialInfoPanel.add(financeSS2FTF);
		financeMemberAgeFTF3.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Member Age</h3>\r\n<p style=\"font-size 13;\">Please enter the member's Age</p>\r\n<html>");
		financeMemberAgeFTF3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		financeMemberAgeFTF3.setColumns(10);
		financeMemberAgeFTF3.setBounds(693, 176, 47, 23);
		
		financialInfoPanel.add(financeMemberAgeFTF3);
		financeSS3FTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Member Social Security Number</h3>\r\n<p style=\"font-size 13;\">Please enter the member's Social Security Number</p>\r\n<html>");
		financeSS3FTF.setForeground(Color.GRAY);
		financeSS3FTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		financeSS3FTF.setBounds(773, 207, 208, 23);
		
		financialInfoPanel.add(financeSS3FTF);
		financeMemberAgeFTF4.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Member Age</h3>\r\n<p style=\"font-size 13;\">Please enter the member's Age</p>\r\n<html>");
		financeMemberAgeFTF4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		financeMemberAgeFTF4.setColumns(10);
		financeMemberAgeFTF4.setBounds(693, 207, 47, 23);
		
		financialInfoPanel.add(financeMemberAgeFTF4);
		financeSS4FTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Member Social Security Number</h3>\r\n<p style=\"font-size 13;\">Please enter the member's Social Security Number</p>\r\n<html>");
		financeSS4FTF.setForeground(Color.GRAY);
		financeSS4FTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		financeSS4FTF.setBounds(773, 176, 208, 23);
		
		financialInfoPanel.add(financeSS4FTF);
		financeMemberAgeFTF5.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Member Age</h3>\r\n<p style=\"font-size 13;\">Please enter the member's Age</p>\r\n<html>");
		financeMemberAgeFTF5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		financeMemberAgeFTF5.setColumns(10);
		financeMemberAgeFTF5.setBounds(693, 239, 47, 23);
		
		financialInfoPanel.add(financeMemberAgeFTF5);
		financeSS5FTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Member Social Security Number</h3>\r\n<p style=\"font-size 13;\">Please enter the member's Social Security Number</p>\r\n<html>");
		financeSS5FTF.setForeground(Color.GRAY);
		financeSS5FTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		financeSS5FTF.setBounds(773, 239, 208, 23);
		
		financialInfoPanel.add(financeSS5FTF);
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{menuBar, mnFile, mntmStartNewForm, mntmExit, mnHelp, mntmStudentInformation, mntmFamilyInformation, mntmFinancialInformation, informationTabbedPane, studentInfoPanel, lblStudentFirst, studentfnameTF, lblStudentMiddle, studentmnameTF, lblStudentLast, studentlnameTF, lblPreferredName, studentprefnameTF, lblStudentDob, studentDobMonthComboBox, studentDobDayFTF, studentDobYearFTF, lblStudentGender, studentGenderComboBox, lblStudentRaceOther, studentRaceOtherTF, lblStudentRace, studentRaceComboBox, lblStudentGrade, studentGradeComboBox, lblStudentSS, StudentSSFTF, lblStudentSubdivision, studentSubdivTF, lblLeaseExpires, studentLeaseExpFTF, lblStudentOwnlease, rdbtnStudentOwn, rdbtnStudentLease, lblStreetAddress, studentStreetTF, lblCity, studentCityTF, lblZip, studentZipFTF, lblHomePhone, studentHomePhoneFTF, btnNextStudent, lblStudentInformation, familyInfoPanel, lblFamilyFather, lblFamilyMother, lblFatherFname, fatherFnameTF, lblFatherMI, fatherMITF, lblFatherLname, fatherLnameTF, lblFatherCell, fatherCellFTF, lblFatherWorkPhone, fatherWorkPhoneFTF, lblFatherHomePhone, fatherHomePhoneFTF, fatherAddressTF, lblFatherAddress, lblFatherWorkplace, fatherWorkplaceTF, lblFatherEmail, fatherEmailTF, lblFamilyGeneral, GeneralSeparator, ParentSeparator, lblMotherFname, motherFnameTF, lblMotherMI, motherMITF, lblMotherLname, motherLnameTF, lblMotherCell, motherCellFTF, lblMotherWorkPhone, motherWorkPhoneFTF, lblMotherHomePhone, motherHomePhoneFTF, lblMotherAddress, motherAddressTF, lblMotherWorkplace, motherWorkplaceTF, lblMotherEmail, motherEmailTF, lblChildLivesWith, rdbtnBothParents, rdbtnFather, rdbtnMother, rdbtnOther, lblChildLivesWithOther, familyOtherNameTF, lblSiblings, siblingName1TF, siblingMonth1CB, siblingDay1FTF, siblingYear1FTF, siblingGrade1ComboBox, siblingSchool1TF, siblingName2TF, siblingMonth2CB, siblingDay2FTF, siblingYear2FTF, siblingGrade2ComboBox, siblingSchool2TF, siblingName3TF, siblingMonth3CB, siblingDay3TF, siblingYear3FTF, siblingGrade3ComboBox, siblingSchool3TF, lblSpecialServices, rdbtnGifted, rdbtnSpediep, rdbtnSpeechied, rdbtnEll, lblLifeThreateningAllergy, lifeThreateningAllergyTF, lblEmergencyNumbers, lblEmergency1, lblEmergencyName1, emergencyName1TF, lblEmergencyRelationship1, emergencyRelationship1ComboBox, lblEmergencyPhone1, emergencyPhone1FTF, lblEmergency2, lblEmergencyName2, emergencyName2TF, lblEmergencyRelationship2, emergencyRelationship2ComboBox, lblEmergencyPhone2, emergencyPhone2FTF, rdbtnYesMyChilds, chckbxNewCheckBox, chckbxNo, lblToHelpUs, txtAreaToHelpUs, btnNextFamily, btnPreviousFamily, financialInfoPanel, btnPreviousFinance, lblFinanceInstr, lblFinanceName, lblFinanceRelationship, lblFinanceAge, lblFinanceSS, lblFinance1, lblFinance2, lblFinance3, lblFinance4, lblFinance5, lblFinance6, financeMemberNameTF1, financeMemberNameTF2, financeMemberNameTF4, financeMemberNameTF3, financeMemberNameTF6, financeMemberNameTF5, financeMemberRelationshipComboBox1, financeMemberRelationshipComboBox2, financeMemberRelationshipComboBox3, financeMemberRelationshipComboBox4, financeMemberRelationshipComboBox5, financeMemberRelationshipComboBox6, financeMemberAgeFTF1, financeMemberAgeFTF2, financeMemberAgeFTF4, financeMemberAgeFTF3, financeMemberAgeFTF6, financeMemberAgeFTF5, financeSS1FTF, financeSS2FTF, financeSS3FTF, financeSS4FTF, financeSS5FTF, financeSS6FTF, lblFinanceIncomeOfAnyone, lblFinanceSalary, lblFinanceUnemployment, lblFinanceTANFAFDC, lblFinanceSocialSecurity, lblFinanceDisability, lblFinanceChildSupport, lblFinanceUtilityAssistance, lblFinanceFoodStamps, lblFinanceOtherInc, lblFinanceIncomeExplain, SalaryIncomeTF, UnemploymentIncomeTF, TANFAFDCIncomeTF, DisabilityIncomeTF, SocialSecurityIncomeTF, ChildSupportIncomeTF, UtilityAsisstanceIncomeTF, FoodStampsIncomeTF, OtherIncomeTF, IncomeExplainTF, lblFinanceExpensesOfAnyone, lblFinanceRent, lblFinancePhoneBill, lblFinanceGaswater, lblFinanceCarPayment, lblFinanceLightBill, lblFinanceFurnappliances, lblFinanceCableTv, lblFinanceDoctormed, lblFinanceOtherExpenses, lblFinanceExpensesExplain, RentExpenseTF, PhoneExpenseTF, GasExpenseTF, LightBillExpenseTF, CarPaymentExpenseTF, ApplianceExpenseTF, CableTVExpenseTF, DoctorExpenseTF, OtherExpenseTF, ExpenseExplainTF, lblTotalIncome, TotalIncomeTF, lblTotalExpense, TotalExpenseTF, lblNetIncome, NetIncomeTF}));
		financeMemberAgeFTF6.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Member Age</h3>\r\n<p style=\"font-size 13;\">Please enter the member's Age</p>\r\n<html>");
		financeMemberAgeFTF6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		financeMemberAgeFTF6.setColumns(10);
		financeMemberAgeFTF6.setBounds(693, 270, 47, 23);
		
		financialInfoPanel.add(financeMemberAgeFTF6);
		financeSS6FTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Member Social Security Number</h3>\r\n<p style=\"font-size 13;\">Please enter the member's Social Security Number</p>\r\n<html>");
		financeSS6FTF.setForeground(Color.GRAY);
		financeSS6FTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		financeSS6FTF.setBounds(773, 270, 208, 23);
		
		financialInfoPanel.add(financeSS6FTF);
		lblFinanceIncomeOfAnyone.setToolTipText("");
		lblFinanceIncomeOfAnyone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFinanceIncomeOfAnyone.setBounds(47, 333, 550, 17);
		
		financialInfoPanel.add(lblFinanceIncomeOfAnyone);
		lblFinanceSalary.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Income</h3>\r\n<p style=\"font-size 13;\">Please enter the details regarding income</p>\r\n<html>");
		lblFinanceSalary.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFinanceSalary.setBounds(107, 376, 69, 17);
		
		financialInfoPanel.add(lblFinanceSalary);
		lblFinanceUnemployment.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Income</h3>\r\n<p style=\"font-size 13;\">Please enter the details regarding income</p>\r\n<html>");
		lblFinanceUnemployment.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFinanceUnemployment.setBounds(107, 404, 104, 17);
		
		financialInfoPanel.add(lblFinanceUnemployment);
		lblFinanceTANFAFDC.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Income</h3>\r\n<p style=\"font-size 13;\">Please enter the details regarding income</p>\r\n<html>");
		lblFinanceTANFAFDC.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFinanceTANFAFDC.setBounds(107, 434, 104, 17);
		
		financialInfoPanel.add(lblFinanceTANFAFDC);
		lblFinanceSocialSecurity.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Income</h3>\r\n<p style=\"font-size 13;\">Please enter the details regarding income</p>\r\n<html>");
		lblFinanceSocialSecurity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFinanceSocialSecurity.setBounds(107, 490, 104, 17);
		
		financialInfoPanel.add(lblFinanceSocialSecurity);
		lblFinanceDisability.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Income</h3>\r\n<p style=\"font-size 13;\">Please enter the details regarding income</p>\r\n<html>");
		lblFinanceDisability.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFinanceDisability.setBounds(107, 462, 113, 17);
		
		financialInfoPanel.add(lblFinanceDisability);
		lblFinanceChildSupport.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Income</h3>\r\n<p style=\"font-size 13;\">Please enter the details regarding income</p>\r\n<html>");
		lblFinanceChildSupport.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFinanceChildSupport.setBounds(571, 378, 104, 17);
		
		financialInfoPanel.add(lblFinanceChildSupport);
		lblFinanceUtilityAssistance.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Income</h3>\r\n<p style=\"font-size 13;\">Please enter the details regarding income</p>\r\n<html>");
		lblFinanceUtilityAssistance.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFinanceUtilityAssistance.setBounds(571, 406, 104, 17);
		
		financialInfoPanel.add(lblFinanceUtilityAssistance);
		lblFinanceFoodStamps.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Income</h3>\r\n<p style=\"font-size 13;\">Please enter the details regarding income</p>\r\n<html>");
		lblFinanceFoodStamps.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFinanceFoodStamps.setBounds(571, 434, 104, 17);
		
		financialInfoPanel.add(lblFinanceFoodStamps);
		lblFinanceOtherInc.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Income</h3>\r\n<p style=\"font-size 13;\">Please enter the details regarding income</p>\r\n<html>");
		lblFinanceOtherInc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFinanceOtherInc.setBounds(571, 462, 104, 17);
		
		financialInfoPanel.add(lblFinanceOtherInc);
		lblFinanceIncomeExplain.setToolTipText("");
		lblFinanceIncomeExplain.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFinanceIncomeExplain.setBounds(571, 490, 104, 17);
		
		financialInfoPanel.add(lblFinanceIncomeExplain);
		SalaryIncomeTF.setValue(0);
		SalaryIncomeTF.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				do_sumtotalCalc(SalaryIncomeTF, TotalIncomeTF, arg0);
			}
		});
		SalaryIncomeTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Income</h3>\r\n<p style=\"font-size 13;\">Please enter the details regarding income</p>\r\n<html>");
		SalaryIncomeTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		SalaryIncomeTF.setColumns(10);
		SalaryIncomeTF.setBounds(238, 373, 236, 23);
		
		financialInfoPanel.add(SalaryIncomeTF);
		UnemploymentIncomeTF.setValue(0);
		UnemploymentIncomeTF.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				do_sumtotalCalc(UnemploymentIncomeTF, TotalIncomeTF, arg0);
			}
		});
		UnemploymentIncomeTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Income</h3>\r\n<p style=\"font-size 13;\">Please enter the details regarding income</p>\r\n<html>");
		UnemploymentIncomeTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		UnemploymentIncomeTF.setColumns(10);
		UnemploymentIncomeTF.setBounds(238, 401, 236, 23);
		
		financialInfoPanel.add(UnemploymentIncomeTF);
		TANFAFDCIncomeTF.setValue(0);
		TANFAFDCIncomeTF.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				do_sumtotalCalc(TANFAFDCIncomeTF, TotalIncomeTF, arg0);
			}
		});
		TANFAFDCIncomeTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Income</h3>\r\n<p style=\"font-size 13;\">Please enter the details regarding income</p>\r\n<html>");
		TANFAFDCIncomeTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		TANFAFDCIncomeTF.setColumns(10);
		TANFAFDCIncomeTF.setBounds(238, 431, 236, 23);
		
		financialInfoPanel.add(TANFAFDCIncomeTF);
		DisabilityIncomeTF.setValue(0);
		DisabilityIncomeTF.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				do_sumtotalCalc(DisabilityIncomeTF, TotalIncomeTF, arg0);
			}
		});
		
		DisabilityIncomeTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Income</h3>\r\n<p style=\"font-size 13;\">Please enter the details regarding income</p>\r\n<html>");
		DisabilityIncomeTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		DisabilityIncomeTF.setColumns(10);
		DisabilityIncomeTF.setBounds(238, 459, 236, 23);
		
		financialInfoPanel.add(DisabilityIncomeTF);
		SocialSecurityIncomeTF.setValue(0);
		SocialSecurityIncomeTF.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				do_sumtotalCalc(SocialSecurityIncomeTF, TotalIncomeTF, arg0);
			}
		});
		SocialSecurityIncomeTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Income</h3>\r\n<p style=\"font-size 13;\">Please enter the details regarding income</p>\r\n<html>");
		SocialSecurityIncomeTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		SocialSecurityIncomeTF.setColumns(10);
		SocialSecurityIncomeTF.setBounds(238, 487, 236, 23);
		
		financialInfoPanel.add(SocialSecurityIncomeTF);
		ChildSupportIncomeTF.setValue(0);
		ChildSupportIncomeTF.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				do_sumtotalCalc(ChildSupportIncomeTF, TotalIncomeTF, arg0);
			}
		});
		ChildSupportIncomeTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Income</h3>\r\n<p style=\"font-size 13;\">Please enter the details regarding income</p>\r\n<html>");
		ChildSupportIncomeTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ChildSupportIncomeTF.setColumns(10);
		ChildSupportIncomeTF.setBounds(705, 373, 236, 23);
		
		financialInfoPanel.add(ChildSupportIncomeTF);
		UtilityAsisstanceIncomeTF.setValue(0);
		UtilityAsisstanceIncomeTF.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				do_sumtotalCalc(UtilityAsisstanceIncomeTF, TotalIncomeTF, arg0);
			}
		});
		UtilityAsisstanceIncomeTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Income</h3>\r\n<p style=\"font-size 13;\">Please enter the details regarding income</p>\r\n<html>");
		UtilityAsisstanceIncomeTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		UtilityAsisstanceIncomeTF.setColumns(10);
		UtilityAsisstanceIncomeTF.setBounds(705, 401, 236, 23);
		
		financialInfoPanel.add(UtilityAsisstanceIncomeTF);
		FoodStampsIncomeTF.setValue(0);
		FoodStampsIncomeTF.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				do_sumtotalCalc(FoodStampsIncomeTF, TotalIncomeTF, arg0);
			}
		});
		FoodStampsIncomeTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Income</h3>\r\n<p style=\"font-size 13;\">Please enter the details regarding income</p>\r\n<html>");
		FoodStampsIncomeTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		FoodStampsIncomeTF.setColumns(10);
		FoodStampsIncomeTF.setBounds(705, 431, 236, 23);
		
		financialInfoPanel.add(FoodStampsIncomeTF);
		OtherIncomeTF.setValue(0);
		OtherIncomeTF.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				do_sumtotalCalc(OtherIncomeTF, TotalIncomeTF, arg0);
			}
		});
		OtherIncomeTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Income</h3>\r\n<p style=\"font-size 13;\">Please enter the details regarding income</p>\r\n<html>");
		OtherIncomeTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		OtherIncomeTF.setColumns(10);
		OtherIncomeTF.setBounds(705, 459, 236, 23);
		
		financialInfoPanel.add(OtherIncomeTF);
		IncomeExplainTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Income</h3>\r\n<p style=\"font-size 13;\">Would you like to provide an explanation?</p>\r\n<html>");
		IncomeExplainTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		IncomeExplainTF.setColumns(10);
		IncomeExplainTF.setBounds(705, 487, 318, 23);
		
		financialInfoPanel.add(IncomeExplainTF);
		lblFinanceExpensesOfAnyone.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Student Middle Name</h3>\r\n<p style=\"font-size 13;\">Please enter the student's Middle Name (not required)</p>\r\n<html>");
		lblFinanceExpensesOfAnyone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFinanceExpensesOfAnyone.setBounds(47, 574, 550, 17);
		
		financialInfoPanel.add(lblFinanceExpensesOfAnyone);
		lblFinanceRent.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Expenses</h3>\r\n<p style=\"font-size 13;\">Please enter the details regarding expenses</p>\r\n<html>");
		lblFinanceRent.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFinanceRent.setBounds(107, 628, 69, 17);
		
		financialInfoPanel.add(lblFinanceRent);
		lblFinancePhoneBill.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Expenses</h3>\r\n<p style=\"font-size 13;\">Please enter the details regarding expenses</p>\r\n<html>");
		lblFinancePhoneBill.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFinancePhoneBill.setBounds(107, 656, 104, 17);
		
		financialInfoPanel.add(lblFinancePhoneBill);
		lblFinanceGaswater.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Expenses</h3>\r\n<p style=\"font-size 13;\">Please enter the details regarding expenses</p>\r\n<html>");
		lblFinanceGaswater.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFinanceGaswater.setBounds(107, 686, 104, 17);
		
		financialInfoPanel.add(lblFinanceGaswater);
		lblFinanceCarPayment.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Expenses</h3>\r\n<p style=\"font-size 13;\">Please enter the details regarding expenses</p>\r\n<html>");
		lblFinanceCarPayment.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFinanceCarPayment.setBounds(107, 742, 104, 17);
		
		financialInfoPanel.add(lblFinanceCarPayment);
		lblFinanceLightBill.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Expenses</h3>\r\n<p style=\"font-size 13;\">Please enter the details regarding expenses</p>\r\n<html>");
		lblFinanceLightBill.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFinanceLightBill.setBounds(107, 714, 113, 17);
		
		financialInfoPanel.add(lblFinanceLightBill);
		lblFinanceFurnappliances.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Expenses</h3>\r\n<p style=\"font-size 13;\">Please enter the details regarding expenses</p>\r\n<html>");
		lblFinanceFurnappliances.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFinanceFurnappliances.setBounds(571, 630, 104, 17);
		
		financialInfoPanel.add(lblFinanceFurnappliances);
		lblFinanceCableTv.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Expenses</h3>\r\n<p style=\"font-size 13;\">Please enter the details regarding expenses</p>\r\n<html>");
		lblFinanceCableTv.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFinanceCableTv.setBounds(571, 658, 104, 17);
		
		financialInfoPanel.add(lblFinanceCableTv);
		lblFinanceDoctormed.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Expenses</h3>\r\n<p style=\"font-size 13;\">Please enter the details regarding expenses</p>\r\n<html>");
		lblFinanceDoctormed.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFinanceDoctormed.setBounds(571, 686, 104, 17);
		
		financialInfoPanel.add(lblFinanceDoctormed);
		lblFinanceOtherExpenses.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Expenses</h3>\r\n<p style=\"font-size 13;\">Please enter the details regarding expenses</p>\r\n<html>");
		lblFinanceOtherExpenses.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFinanceOtherExpenses.setBounds(571, 714, 104, 17);
		
		financialInfoPanel.add(lblFinanceOtherExpenses);
		lblFinanceExpensesExplain.setToolTipText("");
		lblFinanceExpensesExplain.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFinanceExpensesExplain.setBounds(571, 742, 104, 17);
		
		financialInfoPanel.add(lblFinanceExpensesExplain);
		RentExpenseTF.setForeground(Color.RED);
		RentExpenseTF.setValue(0);
		RentExpenseTF.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				do_sumexpenseCalc(RentExpenseTF, TotalExpenseTF, arg0);
			}
		});
		RentExpenseTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Expenses</h3>\r\n<p style=\"font-size 13;\">Please enter the details regarding expenses</p>\r\n<html>");
		RentExpenseTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		RentExpenseTF.setColumns(10);
		RentExpenseTF.setBounds(238, 625, 236, 23);
		
		financialInfoPanel.add(RentExpenseTF);
		PhoneExpenseTF.setForeground(Color.RED);
		PhoneExpenseTF.setValue(0);
		PhoneExpenseTF.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				do_sumexpenseCalc(PhoneExpenseTF, TotalExpenseTF, arg0);
			}
		});
		PhoneExpenseTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Expenses</h3>\r\n<p style=\"font-size 13;\">Please enter the details regarding expenses</p>\r\n<html>");
		PhoneExpenseTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		PhoneExpenseTF.setColumns(10);
		PhoneExpenseTF.setBounds(238, 653, 236, 23);
		
		financialInfoPanel.add(PhoneExpenseTF);
		GasExpenseTF.setForeground(Color.RED);
		GasExpenseTF.setValue(0);
		GasExpenseTF.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				do_sumexpenseCalc(GasExpenseTF, TotalExpenseTF, arg0);
			}
		});
		GasExpenseTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Expenses</h3>\r\n<p style=\"font-size 13;\">Please enter the details regarding expenses</p>\r\n<html>");
		GasExpenseTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GasExpenseTF.setColumns(10);
		GasExpenseTF.setBounds(238, 683, 236, 23);
		
		financialInfoPanel.add(GasExpenseTF);
		LightBillExpenseTF.setForeground(Color.RED);
		LightBillExpenseTF.setValue(0);
		LightBillExpenseTF.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				do_sumexpenseCalc(LightBillExpenseTF, TotalExpenseTF, arg0);
			}
		});
		LightBillExpenseTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Expenses</h3>\r\n<p style=\"font-size 13;\">Please enter the details regarding expenses</p>\r\n<html>");
		LightBillExpenseTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		LightBillExpenseTF.setColumns(10);
		LightBillExpenseTF.setBounds(238, 711, 236, 23);
		
		financialInfoPanel.add(LightBillExpenseTF);
		CarPaymentExpenseTF.setForeground(Color.RED);
		CarPaymentExpenseTF.setValue(0);
		CarPaymentExpenseTF.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				do_sumexpenseCalc(CarPaymentExpenseTF, TotalExpenseTF, arg0);
			}
		});
		CarPaymentExpenseTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Expenses</h3>\r\n<p style=\"font-size 13;\">Please enter the details regarding expenses</p>\r\n<html>");
		CarPaymentExpenseTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		CarPaymentExpenseTF.setColumns(10);
		CarPaymentExpenseTF.setBounds(238, 739, 236, 23);
		
		financialInfoPanel.add(CarPaymentExpenseTF);
		ApplianceExpenseTF.setForeground(Color.RED);
		ApplianceExpenseTF.setValue(0);
		ApplianceExpenseTF.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				do_sumexpenseCalc(ApplianceExpenseTF, TotalExpenseTF, arg0);
			}
		});
		ApplianceExpenseTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Expenses</h3>\r\n<p style=\"font-size 13;\">Please enter the details regarding expenses</p>\r\n<html>");
		ApplianceExpenseTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ApplianceExpenseTF.setColumns(10);
		ApplianceExpenseTF.setBounds(705, 628, 236, 23);
		
		financialInfoPanel.add(ApplianceExpenseTF);
		CableTVExpenseTF.setForeground(Color.RED);
		CableTVExpenseTF.setValue(0);
		CableTVExpenseTF.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				do_sumexpenseCalc(CableTVExpenseTF, TotalExpenseTF, arg0);
			}
		});
		CableTVExpenseTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Expenses</h3>\r\n<p style=\"font-size 13;\">Please enter the details regarding expenses</p>\r\n<html>");
		CableTVExpenseTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		CableTVExpenseTF.setColumns(10);
		CableTVExpenseTF.setBounds(705, 656, 236, 23);
		
		financialInfoPanel.add(CableTVExpenseTF);
		DoctorExpenseTF.setForeground(Color.RED);
		DoctorExpenseTF.setValue(0);
		DoctorExpenseTF.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				do_sumexpenseCalc(DoctorExpenseTF, TotalExpenseTF, arg0);
			}
		});
		DoctorExpenseTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Expenses</h3>\r\n<p style=\"font-size 13;\">Please enter the details regarding expenses</p>\r\n<html>");
		DoctorExpenseTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		DoctorExpenseTF.setColumns(10);
		DoctorExpenseTF.setBounds(705, 686, 236, 23);
		
		financialInfoPanel.add(DoctorExpenseTF);
		OtherExpenseTF.setForeground(Color.RED);
		OtherExpenseTF.setValue(0);
		OtherExpenseTF.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				do_sumexpenseCalc(RentExpenseTF, TotalExpenseTF, arg0);
			}
		});
		OtherExpenseTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Expenses</h3>\r\n<p style=\"font-size 13;\">Please enter the details regarding expenses</p>\r\n<html>");
		OtherExpenseTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		OtherExpenseTF.setColumns(10);
		OtherExpenseTF.setBounds(705, 714, 236, 23);
		
		financialInfoPanel.add(OtherExpenseTF);
		ExpenseExplainTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Household Expenses</h3>\r\n<p style=\"font-size 13;\">Would you like to provide an explanation?</p>\r\n<html>");
		ExpenseExplainTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ExpenseExplainTF.setColumns(10);
		ExpenseExplainTF.setBounds(705, 742, 318, 23);
		
		financialInfoPanel.add(ExpenseExplainTF);
		lblTotalIncome.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Income Total</h3>\r\n<p style=\"font-size 13;\">Total Income</p>\r\n<html>");
		lblTotalIncome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTotalIncome.setBounds(385, 535, 61, 17);
		
		financialInfoPanel.add(lblTotalIncome);
		TotalIncomeTF.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				do_sumexpenseCalc(TotalIncomeTF, NetIncomeTF, evt);
			}
		});
		TotalIncomeTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Income Total</h3>\r\n<p style=\"font-size 13;\">Total Income</p>\r\n<html>");
		TotalIncomeTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		TotalIncomeTF.setColumns(10);
		TotalIncomeTF.setBounds(470, 532, 236, 23);
		
		financialInfoPanel.add(TotalIncomeTF);
		lblTotalExpense.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Expenses Total</h3>\r\n<p style=\"font-size 13;\">Total Expenses</p>\r\n<html>");
		lblTotalExpense.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTotalExpense.setBounds(385, 805, 61, 17);
		
		financialInfoPanel.add(lblTotalExpense);
		TotalExpenseTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Expenses Total</h3>\r\n<p style=\"font-size 13;\">Total Expenses</p>\r\n<html>");
		TotalExpenseTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		TotalExpenseTF.setColumns(10);
		TotalExpenseTF.setBounds(470, 802, 236, 23);
		
		financialInfoPanel.add(TotalExpenseTF);
		lblNetIncome.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Net Income</h3>\r\n<p style=\"font-size 13;\">Net Income</p>\r\n<html>");
		lblNetIncome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNetIncome.setBounds(386, 839, 86, 17);
		
		financialInfoPanel.add(lblNetIncome);
		NetIncomeTF.setToolTipText("<html>\r\n<h3 style=\"font-weight: bold; font-size 14;\">Net Income</h3>\r\n<p style=\"font-size 13;\">Net Income</p>\r\n<html>");
		NetIncomeTF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		NetIncomeTF.setColumns(10);
		NetIncomeTF.setBounds(470, 836, 236, 23);
		
		financialInfoPanel.add(NetIncomeTF);
		separator.setBounds(107, 521, 922, 17);
		
		financialInfoPanel.add(separator);
		separator_1.setBounds(101, 777, 922, 17);
		
		financialInfoPanel.add(separator_1);
	}
	
	//Clear the form
	protected void clearTextFields(Container cont){
		for(Component c : cont.getComponents()){
			if(c instanceof JTextField){
				((JTextField)c).setText("");
			}
			else if(c instanceof JFormattedTextField){
				((JFormattedTextField)c).setText("");
			}
			else if (c instanceof JLabel){
				((JLabel)c).setForeground(Color.BLACK);
			}
			else if (c instanceof JComboBox){
				((JComboBox)c).setSelectedIndex(-1);
			}
			else if(c instanceof Container){
				clearTextFields((Container)c);
			}
		}
	}
	
	protected void do_MenuItem_Exit_Action(){
		int confirmed = JOptionPane.showConfirmDialog(null, 
		"Are you sure you want to exit the program?", "Exit Program",
	    JOptionPane.YES_NO_OPTION);

	    if (confirmed == JOptionPane.YES_OPTION) {
	      this.dispose();
	    }
	}
	
	//Foreground change function
	protected void foregroundChange(JComponent com, Integer status){
		switch(status){
		case 0:
			com.setForeground(Color.BLACK);
			break;
		case 1:
			com.setForeground(Color.RED);
			break;
		case 2:
			com.setForeground(Color.GRAY);
			break;
		}
	}
	
	//Page validation function
	protected boolean validate(Container cont){
		JComponent curr;
		JComponent next;
		for(int c = 0, l = cont.getComponentCount(); c < l; c++){
			curr = ((JComponent)cont.getComponent(c));
			if(curr instanceof JLabel){
				if(((JLabel)curr).getText().trim().contains("*")){
					next = ((JComponent)cont.getComponent(c + 1));
					if(next instanceof JFormattedTextField){
						validateData(next, ((JLabel)curr), true);
					}
					else if(next instanceof JTextField){
						validateData(next, ((JLabel)curr));
					}
					else if(next instanceof JComboBox){
						validateData(next, ((JLabel)curr));
					}
				}
			}
		}
		
		for(Component c: cont.getComponents()){
			if(c instanceof JLabel){
				if((((JLabel)c).getForeground().equals(Color.RED))){
					((JComponent)c).transferFocus();
					return false;
				}
			}
		}
		
		return true;
	}
	
	//Main data validation function
	protected void validateData(JComponent comp, JLabel lbl, boolean isFTF){
		String input = "";
		
		if(comp.isVisible()){
			if(lbl.getText().contains("Phone")){
				input = "(000) 000-0000";
			}
			else if(lbl.getText().contains("SS")){
				input = "000-00-0000";
			}
			else if(lbl.getText().contains("Zip")){
				input = "00000";
			}
			
			if(comp instanceof JFormattedTextField){
				input = ((JFormattedTextField)comp).getText().trim();
			}
			else if(comp instanceof JTextArea){
				input = ((JTextArea)comp).getText().trim();
			}
			else if(comp instanceof JTextField){
				input = ((JTextField)comp).getText().trim();
			}
			else if(comp instanceof JComboBox){
				if(((JComboBox)comp).getSelectedIndex() == -1){
					input = "";
				}
				else{
					input = "SELECTED";
				}
			}
			
			if(!isFTF){
				if(input.isEmpty()){
					foregroundChange(lbl, 1);
				}
				else{
					foregroundChange(lbl, 0);
				}
			}
			else{
				switch(input){
				case "000-00-0000":
				case "00000":
				case "(000) 000-0000":
					foregroundChange(lbl, 1);
					foregroundChange(comp, 2);
				break;
				default:
					foregroundChange(lbl, 0);
					foregroundChange(comp, 0);
				}
			}
		}
	}
	
	//validateData override when no isFTF parameter provided
	protected void validateData(JComponent comp, JLabel lbl){
		validateData(comp, lbl, false);
	}
	
	protected void validateDate(JComponent comp, JLabel lbl, Integer dt, Integer month){
		if(month.equals(-1)){
			if(dt.equals(0)){
				//check if empty/default mask
				foregroundChange(lbl, 1);
				foregroundChange(comp, 2);
			}
			else if(dt < 1900 || dt > (Calendar.getInstance().get(Calendar.YEAR))){
				//check years
				JOptionPane.showMessageDialog(comp, "<html><h3 style=\"font-size: 16\">Please enter a valid year! (should be between 1900 and the current year)</h3></html>", "Invalid Date", JOptionPane.ERROR_MESSAGE);
				comp.grabFocus();
				foregroundChange(comp, 1);
			}
			else{
				foregroundChange(lbl, 0);
				foregroundChange(comp, 0);
			}
		}
		else{
			//Check if days are in correct range
			Integer max_day = 0;
			
			switch(month){
			case 0:
			case 2:
			case 4:
			case 6:
			case 7:
			case 9: 
			case 11:
				max_day = 31;
				break;
			case 1:
				max_day = 29;
				break;
			default:
				max_day = 30;
			}
			
			if(dt.equals(00)){
				foregroundChange(lbl, 1);
				foregroundChange(comp, 2);
			}
			else if(dt > max_day || dt < 1){
				JOptionPane.showMessageDialog(comp, "<html><h3 style=\"font-size: 16\">Please enter a valid day (should be within correct date range for that month)</h3></html>", "Invalid Date", JOptionPane.ERROR_MESSAGE);
				comp.grabFocus();
				foregroundChange(comp, 1);
			}
			else{
				foregroundChange(comp, 0);
				foregroundChange(comp, 0);
			}
		}
	}
	
	//Date override if no month provided
	protected void validateDate(JComponent comp, JLabel lbl, Integer dt){
		validateDate(comp, lbl, dt, -1);
	}
	
	//Calculate sum total, used for income fields
	protected void do_sumtotalCalc(JFormattedTextField ftf, JFormattedTextField totalfield, PropertyChangeEvent e){
		double firstNum = ((Number)(ftf.getValue())).doubleValue();
		double secondNum = ((Number)(totalfield.getValue())).doubleValue();
		double sum = 0;
		
		num_ForegroundChange(ftf, firstNum);
		sum = firstNum + secondNum;
		
		num_ForegroundChange(ftf, sum);
		totalfield.setValue(sum);
	}
	
	//Calculator for expense total, almost eh same as the sumtotalcalc, but does not affect the ftf foreground color (expenses should remain red)
	protected void do_sumexpenseCalc(JFormattedTextField ftf, JFormattedTextField totalfield, PropertyChangeEvent e){
		double firstNum = ((Number)(ftf.getValue())).doubleValue();
		double secondNum = ((Number)(totalfield.getValue())).doubleValue();
		double sum = 0;

		sum = firstNum + secondNum;
		
		totalfield.setValue(sum);
	}
	
	//Calculate nettotal
	protected void do_sumnettotalCalc(JFormattedTextField ftf, JFormattedTextField totalfield, PropertyChangeEvent e){
		double firstNum = ((Number)(ftf.getValue())).doubleValue();
		double secondNum = ((Number)(totalfield.getValue())).doubleValue();
		double sum = 0;

		sum = secondNum - firstNum;
		
		num_ForegroundChange(ftf, sum);
		totalfield.setValue(sum);
	}
	
	//Change foreground if number is negative
	protected void num_ForegroundChange(JFormattedTextField ftf, double val){
		if(val < 0){
			ftf.setForeground(Color.RED);
		}
		else{
			ftf.setForeground(Color.BLACK);
		}
	}
}
