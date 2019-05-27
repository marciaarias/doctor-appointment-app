package doctor_appointment_app;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

import java.awt.Toolkit;
import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class MainWindow {

	public JFrame frmMain;
	private JTable tableAppointments;
	private JTable tablePatients;
	private JTable tableDoctors;
	private JTextField textFieldAppointmentDoctor;
	private JTextField textFieldPatientFirstName;
	private JTextField textFieldPatientLastName;
	private JTextField textFieldPatientEmail;
	private JTextField textFieldDoctorFirstName;
	private JTextField textFieldDoctorLastName;
	private JTextField textFieldDoctorEmail;
	private JTextField textFieldDoctorOfPatient;
	private JFormattedTextField formattedTextFieldDoctorPhone;
	private JFormattedTextField formattedTextFieldPatientPhone;
	private JLabel lblViewAppointments;
	private JLabel lblViewPatients;
	JComboBox<String> comboBoxSelectDoctorTop = new JComboBox<>();
	private List<Integer> idsSelectDoctorTop = new ArrayList<>();
	JComboBox<String> comboBoxAppointmentPatient = new JComboBox<>();
	Utilities utilities = new Utilities();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try { 
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmMain.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMain = new JFrame();
		
		//Override "windowClosing" event.
		
		frmMain.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				utilities.confirmQuitDialog(frmMain);
				
			}
			
			//Override "windowOpened" event.
			
			@Override
			public void windowOpened(WindowEvent e) {
				
					DataModule data = new DataModule();
				
					try {
						Connection connection = data.getConnection();
					
						String querySelect = "SELECT CONCAT(first_name, ' ', last_name) AS full_name, id "
											+ "FROM doctors";
						
						//Get doctors' id's to populate "tablePatients" and "tableAppointments".
						data.fillList(connection, querySelect, idsSelectDoctorTop, "id"); 
						
						//Fill "comboBoxSelectDoctorTop".
						data.fillComboBox(connection, querySelect, comboBoxSelectDoctorTop, "full_name");
						
						//Implement labels "lblViewPatients" & "lblViewAppointments".
						lblViewPatients.setText("<HTML>View patients of Dr. <B>" + comboBoxSelectDoctorTop.getSelectedItem().toString() + "</B>:</HTML>");
						lblViewAppointments.setText("<HTML>View appointments of Dr. <B>" + comboBoxSelectDoctorTop.getSelectedItem().toString() + "</B>:</HTML>");
						
						//Fill "comboBoxAppointmentPatient".
						comboBoxAppointmentPatient.removeAllItems();
						querySelect = "SELECT CONCAT(patients.first_name, ' ', patients.last_name) AS patient_full_name "
											+ "FROM patients "
												+ "JOIN doctors ON patients.doctor_id = doctors.id "
											+ "WHERE patients.doctor_id = " + idsSelectDoctorTop.get(comboBoxSelectDoctorTop.getSelectedIndex());
						data.fillComboBox(connection, querySelect, comboBoxAppointmentPatient, "patient_full_name");
							
					} catch (Exception exception) {
								exception.printStackTrace();
					}
				
			}
		});
		frmMain.setResizable(false);
		frmMain.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\arias\\eclipse-workspace\\doctor-appointment-app\\resources\\appointmentManager.png"));
		frmMain.setTitle("Home - Doctor Appointment Manager");
		frmMain.setBounds(100, 100, 774, 976);
		frmMain.setLocationRelativeTo(null);
		frmMain.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmMain.getContentPane().setLayout(null);

		JLabel lblWelcomeBack = new JLabel("Welcome back, " + new LoginWindow().getUser());
		lblWelcomeBack.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblWelcomeBack.setBounds(7, 9, 426, 23);
		frmMain.getContentPane().add(lblWelcomeBack);
		
		//Implement label "Logout".
		
		JLabel lblLogout = new JLabel("<HTML><U>Logout</U></HTML>");
		lblLogout.setToolTipText("Logout & return to welcome window");
		lblLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int clickedOption = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
				
			    if(clickedOption == JOptionPane.YES_OPTION) {
			    	frmMain.dispose();
			    	
					DoctorAppointmentApp doctorAppointmentApp = new DoctorAppointmentApp();
					doctorAppointmentApp.frmDoctorAppointmentManager.setVisible(true);
			    }
				
			}
		});
		lblLogout.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLogout.setForeground(Color.BLUE);
		lblLogout.setBounds(702, 12, 49, 21);
		lblLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frmMain.getContentPane().add(lblLogout);
		
		JSeparator separator0 = new JSeparator();
		separator0.setBounds(7, 41, 744, 23);
		frmMain.getContentPane().add(separator0);
		
		JLabel lblSelectDoctorTop = new JLabel("Select Doctor:");
		lblSelectDoctorTop.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSelectDoctorTop.setIcon(new ImageIcon("C:\\Users\\arias\\eclipse-workspace\\doctor-appointment-app\\resources\\selectDoctor.png"));
		lblSelectDoctorTop.setBounds(232, 58, 112, 30);
		frmMain.getContentPane().add(lblSelectDoctorTop);
		
		JLabel lblAppointments = new JLabel("Appointments");
		lblAppointments.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAppointments.setBounds(110, 99, 106, 23);
		frmMain.getContentPane().add(lblAppointments);
		
		JLabel lblPatients = new JLabel("Patients");
		lblPatients.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPatients.setBounds(361, 99, 62, 23);
		frmMain.getContentPane().add(lblPatients);
		
		JLabel lblDoctors = new JLabel("Doctors");
		lblDoctors.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDoctors.setBounds(590, 99, 62, 23);
		frmMain.getContentPane().add(lblDoctors);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(34, 122, 694, 747);
		frmMain.getContentPane().add(tabbedPane);
		
		JPanel panelAppointments = new JPanel();
		tabbedPane.addTab("        ", new ImageIcon("C:\\Users\\arias\\eclipse-workspace\\doctor-appointment-app\\resources\\appointments.png"), panelAppointments, null);
		panelAppointments.setLayout(null);
		
		lblViewAppointments = new JLabel();
		lblViewAppointments.setText("View appointments of Dr.:");
		lblViewAppointments.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblViewAppointments.setBounds(10, 16, 330, 18);
		panelAppointments.add(lblViewAppointments);
		
		//Implement button "Show Appointments".
		
		JButton btnShowAppointments = new JButton("Show Appointments");
		btnShowAppointments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DataModule data = new DataModule();
				
				try {
					Connection connection = data.getConnection();
					
					String querySelect = "SELECT "
											+ "appointments.id, "
											+ "CONCAT(patients.first_name, ' ', patients.last_name) AS patient_full_name, "
											+ "DATE_FORMAT(appointment_date, '%m-%d-%Y') AS appointment_date, "
											+ "appointment_hour, "
											+ "appointment_reason "
										+ "FROM appointments "
											+ "JOIN patients ON appointments.patient_id = patients.id "
											+ "JOIN doctors ON appointments.doctor_id = doctors.id "
										+ "WHERE appointments.doctor_id = " + idsSelectDoctorTop.get(comboBoxSelectDoctorTop.getSelectedIndex()); 
					data.selectData(connection, querySelect, tableAppointments);
					
					String[] columnNames = {"Patient", "Date", "Hour", "Reason of the appointment"};
					utilities.renameColumns(tableAppointments, columnNames);
					
				} catch (Exception exception) {
					exception.printStackTrace();
				}
				
			}
		});
		btnShowAppointments.setBounds(552, 10, 127, 23);
		panelAppointments.add(btnShowAppointments);
		
		JLabel lblManageAppointments = new JLabel("Manage Appointments:");
		lblManageAppointments.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblManageAppointments.setBounds(10, 257, 206, 18);
		panelAppointments.add(lblManageAppointments);
		
		JSeparator separator1 = new JSeparator();
		separator1.setBounds(10, 281, 669, 10);
		panelAppointments.add(separator1);
		
		JSeparator separator2 = new JSeparator();
		separator2.setOrientation(SwingConstants.VERTICAL);
		separator2.setBounds(9, 281, 9, 243);
		panelAppointments.add(separator2);
		
		JSeparator separator3 = new JSeparator();
		separator3.setOrientation(SwingConstants.VERTICAL);
		separator3.setBounds(679, 281, 9, 243);
		panelAppointments.add(separator3);
		
		JSeparator separator4 = new JSeparator();
		separator4.setBounds(10, 523, 669, 10);
		panelAppointments.add(separator4);
		
		JLabel lblAppointmentDoctor = new JLabel("Doctor:");
		lblAppointmentDoctor.setBounds(20, 286, 46, 14);
		panelAppointments.add(lblAppointmentDoctor);
		
		textFieldAppointmentDoctor = new JTextField();
		textFieldAppointmentDoctor.setToolTipText("Change doctor on \"Select Doctor\" field at the top");
		textFieldAppointmentDoctor.setFont(new Font("Tahoma", Font.ITALIC, 11));
		textFieldAppointmentDoctor.setEditable(false);
		textFieldAppointmentDoctor.setBounds(20, 303, 189, 20);
		panelAppointments.add(textFieldAppointmentDoctor);
		textFieldAppointmentDoctor.setColumns(10);
		
		JLabel lblAppointmentPatient = new JLabel("Select Patient:");
		lblAppointmentPatient.setBounds(363, 286, 93, 14);
		panelAppointments.add(lblAppointmentPatient);
		
		comboBoxAppointmentPatient.setToolTipText("Select the patient for the appointment");
		comboBoxAppointmentPatient.setBounds(363, 301, 189, 22);
		panelAppointments.add(comboBoxAppointmentPatient);
		
		JLabel lblAppointmentDate = new JLabel("Select Date:");
		lblAppointmentDate.setBounds(20, 334, 93, 14);
		panelAppointments.add(lblAppointmentDate);
		
		UtilDateModel appointmentModel = new UtilDateModel();
		Properties appointmentProperties = new Properties();
		appointmentProperties.put("text.today", "Today");
		appointmentProperties.put("text.month", "Month");
		appointmentProperties.put("text.year", "Year");
		JDatePanelImpl appointmentPanel = new JDatePanelImpl(appointmentModel, appointmentProperties);
		JDatePickerImpl appointmentPicker = new JDatePickerImpl(appointmentPanel, new DateLabelFormatter());
		appointmentPicker.getJFormattedTextField().setToolTipText("Select a date from associated button");
		appointmentPicker.setBounds(20, 349, 214, 23);
		panelAppointments.add(appointmentPicker);
		
		JLabel lblAppointmentHour = new JLabel("Select Hour:");
		lblAppointmentHour.setBounds(363, 334, 84, 14);
		panelAppointments.add(lblAppointmentHour);
		
		JComboBox comboBoxAppointmentHour = new JComboBox();
		comboBoxAppointmentHour.setToolTipText("Select the hour of the appointment");
		comboBoxAppointmentHour.setBounds(363, 349, 189, 22);
		panelAppointments.add(comboBoxAppointmentHour);
		
		JLabel lblAppointmentReason = new JLabel("Reason of the appointment:");
		lblAppointmentReason.setBounds(20, 383, 179, 14);
		panelAppointments.add(lblAppointmentReason);
		
		JComboBox comboBoxAppointmentReason = new JComboBox();
		comboBoxAppointmentReason.setToolTipText("Select a main reason for the appointment");
		comboBoxAppointmentReason.setBounds(20, 398, 189, 22);
		panelAppointments.add(comboBoxAppointmentReason);
		
		JButton btnAddAppointment = new JButton("Add");
		btnAddAppointment.setToolTipText("Add current appointment");
		btnAddAppointment.setBounds(580, 489, 89, 23);
		panelAppointments.add(btnAddAppointment);
		
		JButton btnUpdateAppointment = new JButton("Update");
		btnUpdateAppointment.setToolTipText("Update selected appointment");
		btnUpdateAppointment.setBounds(481, 489, 89, 23);
		panelAppointments.add(btnUpdateAppointment);
		
		JButton btnDeleteAppointment = new JButton("Delete");
		btnDeleteAppointment.setToolTipText("Delete selected appointment");
		btnDeleteAppointment.setBounds(382, 489, 89, 23);
		panelAppointments.add(btnDeleteAppointment);
		
		JButton btnClearAppointments = new JButton("Clear");
		btnClearAppointments.setBounds(28, 489, 89, 23);
		panelAppointments.add(btnClearAppointments);
		
		JScrollPane scrollPaneAppointments = new JScrollPane();
		scrollPaneAppointments.setBounds(10, 40, 669, 201);
		panelAppointments.add(scrollPaneAppointments);
		
		//Fill "tableAppointments" with "mouseClicked" event.
		
		tableAppointments = new JTable();
		tableAppointments.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				
/*				DefaultTableModel model = (DefaultTableModel)tablePatients.getModel();
				int selectedRowIndex = tablePatients.getSelectedRow();
				
				textFieldDoctorOfPatient.setText(comboBoxSelectDoctorTop.getSelectedItem().toString());
				
				textFieldPatientFirstName.setText(model.getValueAt(selectedRowIndex, 1).toString());
				
				textFieldPatientLastName.setText(model.getValueAt(selectedRowIndex, 2).toString());
				
				String dateOfBirth = model.getValueAt(selectedRowIndex, 3).toString();
				int year = Integer.parseInt(dateOfBirth.substring(6));
				int month = Integer.parseInt(dateOfBirth.substring(0, 2)) - 1;
				int day = Integer.parseInt(dateOfBirth.substring(3, 5));
				patientDOBModel.setDate(year, month, day);
				patientDOBModel.setSelected(true);
				
				comboBoxPatientGender.setSelectedItem(model.getValueAt(selectedRowIndex, 4).toString());
				
				String phoneNumber = model.getValueAt(selectedRowIndex, 5).toString().replaceAll("-", "");
				formattedTextFieldPatientPhone.setText(phoneNumber);
				
				String emailFirstHalf = model.getValueAt(selectedRowIndex, 6).toString();
				textFieldPatientEmail.setText(emailFirstHalf.substring(0, emailFirstHalf.indexOf("@")));
				
				String emailSecondHalf = model.getValueAt(selectedRowIndex, 6).toString().substring(emailFirstHalf.indexOf("@"));
				comboBoxPatientEmail.setSelectedItem(emailSecondHalf);*/
				
			}
		});
		tableAppointments.setBounds(10, 40, 669, 201);
		scrollPaneAppointments.setViewportView(tableAppointments);
		
		JPanel panelPatients = new JPanel();
		tabbedPane.addTab("       ", new ImageIcon("C:\\Users\\arias\\eclipse-workspace\\doctor-appointment-app\\resources\\patients.png"), panelPatients, null);
		panelPatients.setLayout(null);
		
		lblViewPatients = new JLabel("View patients of Dr.:");
		lblViewPatients.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblViewPatients.setBounds(10, 16, 330, 18);
		panelPatients.add(lblViewPatients);
		
		//Implement button "Show Patients".
		
		JButton btnShowPatients = new JButton("Show Patients");
		btnShowPatients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DataModule data = new DataModule();
				
				try {
					Connection connection = data.getConnection();
					
					String querySelect = "SELECT "
											+ "id, "
											+ "first_name, "
											+ "last_name, "
											+ "DATE_FORMAT(date_of_birth, '%m-%d-%Y') AS date_of_birth, "
											+ "gender, "
											+ "phone_number, "
											+ "email "
										+ "FROM patients "
										+ "WHERE doctor_id = " + idsSelectDoctorTop.get(comboBoxSelectDoctorTop.getSelectedIndex()); 
					data.selectData(connection, querySelect, tablePatients);
					
					String[] columnNames = {"First Name", "Last Name", "Date of Birth", "Gender", "Phone Number", "Email"};
					utilities.renameColumns(tablePatients, columnNames);
					
				} catch (Exception exception) {
					exception.printStackTrace();
				}
				
			}
		});
		btnShowPatients.setBounds(552, 10, 127, 23);
		panelPatients.add(btnShowPatients);
		
		JLabel lblManagePatients = new JLabel("Manage Patients:");
		lblManagePatients.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblManagePatients.setBounds(10, 257, 206, 18);
		panelPatients.add(lblManagePatients);
		
		JSeparator separator5 = new JSeparator();
		separator5.setBounds(10, 281, 669, 10);
		panelPatients.add(separator5);
		
		JSeparator separator6 = new JSeparator();
		separator6.setOrientation(SwingConstants.VERTICAL);
		separator6.setBounds(9, 281, 9, 243);
		panelPatients.add(separator6);
		
		JSeparator separator7 = new JSeparator();
		separator7.setOrientation(SwingConstants.VERTICAL);
		separator7.setBounds(679, 281, 9, 243);
		panelPatients.add(separator7);
		
		JSeparator separator8 = new JSeparator();
		separator8.setBounds(10, 523, 669, 10);
		panelPatients.add(separator8);
		
		JLabel lblDoctorOfPatient = new JLabel("Doctor:");
		lblDoctorOfPatient.setBounds(20, 286, 81, 14);
		panelPatients.add(lblDoctorOfPatient);
		
		textFieldDoctorOfPatient = new JTextField();
		textFieldDoctorOfPatient.setForeground(Color.DARK_GRAY);
		textFieldDoctorOfPatient.setFont(new Font("Tahoma", Font.ITALIC, 11));
		textFieldDoctorOfPatient.setToolTipText("Change doctor on \"Select Doctor\" field at the top");
		textFieldDoctorOfPatient.setEditable(false);
		textFieldDoctorOfPatient.setBounds(20, 303, 189, 20);
		panelPatients.add(textFieldDoctorOfPatient);
		textFieldDoctorOfPatient.setColumns(10);
		
		JLabel lblPatientFirstName = new JLabel("First Name:");
		lblPatientFirstName.setBounds(20, 334, 76, 14);
		panelPatients.add(lblPatientFirstName);
		
		textFieldPatientFirstName = new JTextField();
		textFieldPatientFirstName.setToolTipText("Enter patient's first name");
		textFieldPatientFirstName.setBounds(20, 349, 189, 20);
		panelPatients.add(textFieldPatientFirstName);
		textFieldPatientFirstName.setColumns(10);
		
		JLabel lblPatientLastName = new JLabel("Last Name:");
		lblPatientLastName.setBounds(363, 334, 86, 14);
		panelPatients.add(lblPatientLastName);
		
		textFieldPatientLastName = new JTextField();
		textFieldPatientLastName.setToolTipText("Enter patient's last name");
		textFieldPatientLastName.setBounds(363, 349, 189, 20);
		panelPatients.add(textFieldPatientLastName);
		textFieldPatientLastName.setColumns(10);
		
		JLabel lblPatientDOB = new JLabel("Date of Birth:");
		lblPatientDOB.setBounds(21, 380, 80, 14);
		panelPatients.add(lblPatientDOB);
		
		UtilDateModel patientDOBModel = new UtilDateModel();
		Properties patientDOBProperties = new Properties();
		patientDOBProperties.put("text.today", "Today");
		patientDOBProperties.put("text.month", "Month");
		patientDOBProperties.put("text.year", "Year");
		JDatePanelImpl patientDOBPanel = new JDatePanelImpl(patientDOBModel, patientDOBProperties);
		JDatePickerImpl patientDOBPicker = new JDatePickerImpl(patientDOBPanel, new DateLabelFormatter());
		patientDOBPicker.getJFormattedTextField().setToolTipText("Select patient's date of birth from associated button");
		patientDOBPicker.setBounds(21, 395, 214, 23);
		panelPatients.add(patientDOBPicker);
		
		JLabel lblPatientGender = new JLabel("Gender:");
		lblPatientGender.setBounds(363, 380, 46, 14);
		panelPatients.add(lblPatientGender);
		
		JComboBox<String> comboBoxPatientGender = new JComboBox<>();
		comboBoxPatientGender.setToolTipText("Select patient's gender");
		comboBoxPatientGender.setBounds(363, 394, 189, 22);
		panelPatients.add(comboBoxPatientGender);
		
		//Fill "comboBoxPatientGender".
		
		{
			DataModule data = new DataModule();
		
			try {
				Connection connection = data.getConnection();
			
				String querySelect = "SELECT gender "
									+ "FROM genders";
				data.fillComboBox(connection, querySelect, comboBoxPatientGender, "gender");
			
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
		
		JLabel lblPatientPhone = new JLabel("Phone Number:");
		lblPatientPhone.setBounds(21, 429, 98, 14);
		panelPatients.add(lblPatientPhone);
		
		//Create mask for field "formattedTextFieldPatientPhone".
		
		try {
			MaskFormatter mask = new MaskFormatter("(###) ###-####");
			formattedTextFieldPatientPhone = new JFormattedTextField(mask);
			formattedTextFieldPatientPhone.setToolTipText("Enter patient's phone number");
			formattedTextFieldPatientPhone.setBounds(21, 444, 189, 20);
			panelPatients.add(formattedTextFieldPatientPhone);
		
		} catch (ParseException exception) {
			exception.printStackTrace();
		}
		
		JLabel lblPatientEmail = new JLabel("Email:");
		lblPatientEmail.setBounds(363, 427, 46, 14);
		panelPatients.add(lblPatientEmail);
		
		textFieldPatientEmail = new JTextField();
		textFieldPatientEmail.setToolTipText("Enter patient's email");
		textFieldPatientEmail.setBounds(363, 442, 89, 22);
		panelPatients.add(textFieldPatientEmail);
		textFieldPatientEmail.setColumns(10);
		
		JComboBox<String> comboBoxPatientEmail = new JComboBox<>();
		comboBoxPatientEmail.setToolTipText("Select email provider");
		comboBoxPatientEmail.setBounds(454, 442, 98, 22);
		panelPatients.add(comboBoxPatientEmail);
		
		//Fill "comboBoxPatientEmail".
		
		{
			DataModule data = new DataModule();
		
			try {
				Connection connection = data.getConnection();
			
				String querySelect = "SELECT email_provider "
									+ "FROM email_providers";
				data.fillComboBox(connection, querySelect, comboBoxPatientEmail, "email_provider");
			
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
		
		//Implement button "Add".
		
		JButton btnAddPatient = new JButton("Add");
		btnAddPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//check if fields are empty.
				
				if(textFieldPatientFirstName.getText().isEmpty() 
						|| textFieldPatientLastName.getText().isEmpty() 
						|| patientDOBPicker.getJFormattedTextField().getText().isEmpty() 
						|| formattedTextFieldPatientPhone.getText().length() < 7 
						|| textFieldPatientEmail.getText().isEmpty()) {
					
					JOptionPane.showMessageDialog(new JFrame(), "Fields cannot be left empty.", "Error", JOptionPane.ERROR_MESSAGE);
					
				} else {
					DataModule data = new DataModule();
					
					try {
						Connection connection = data.getConnection();
						
						//Check if email is unique.
						
						String email = textFieldPatientEmail.getText().concat(comboBoxPatientEmail.getSelectedItem().toString());
						String queryEmail = "SELECT email "
											+ "FROM patients "
											+ "WHERE email = '" + email + "'";
						queryEmail = data.getColumnAsString(connection, queryEmail, "email");
					    
						if(queryEmail != null) {
							JOptionPane.showMessageDialog(null, "Duplicate entry: email must be unique.", "Error", JOptionPane.ERROR_MESSAGE);
							
						} else {
							
							//Retrieve date field as "yyyy-MM-dd".
							
							String dateOfBirth = patientDOBPicker.getJFormattedTextField().getText();
							dateOfBirth = utilities.retrieveFormattedDate(dateOfBirth);
							
							//Execute query.
							
							String queryInsert = "INSERT INTO patients (doctor_id, first_name, last_name, date_of_birth, gender, phone_number, email) "
												+ "VALUES('"  
													+ idsSelectDoctorTop.get(comboBoxSelectDoctorTop.getSelectedIndex()) + "', '" 
													+ textFieldPatientFirstName.getText() + "', '" 
													+ textFieldPatientLastName.getText() + "', '"
													+ dateOfBirth + "', '"
													+ comboBoxPatientGender.getSelectedItem().toString() + "', '"
													+ formattedTextFieldPatientPhone.getText() + "', '"
													+ textFieldPatientEmail.getText().concat(comboBoxPatientEmail.getSelectedItem().toString()) 
												+ "')";
							
							PreparedStatement statement = connection.prepareStatement(queryInsert);
						    statement.executeUpdate(queryInsert);
						    data.selectData(connection, 
											"SELECT "
													+ "id, "
													+ "first_name, "
													+ "last_name, "
													+ "DATE_FORMAT(date_of_birth, '%m-%d-%Y') AS date_of_birth, "
													+ "gender, "
													+ "phone_number, "
													+ "email "
												+ "FROM patients "
												+ "WHERE doctor_id = " + idsSelectDoctorTop.get(comboBoxSelectDoctorTop.getSelectedIndex()),
		    								tablePatients
		    								);
							
						    String[] columnNames = {"First Name", "Last Name", "Date of Birth", "Gender", "Phone Number", "Email"};
							utilities.renameColumns(tablePatients, columnNames);
							
						}
						
					} catch (Exception exception) {
						exception.printStackTrace();
					}
				}
				
			}
		});
		btnAddPatient.setToolTipText("Add current patient");
		btnAddPatient.setBounds(580, 489, 89, 23);
		panelPatients.add(btnAddPatient);
		
		//Implement button "Update".
		
		JButton btnUpdatePatient = new JButton("Update");
		btnUpdatePatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selectedRowIndex = tablePatients.getSelectedRow();
				
				//Confirm row update.
				
				if(tablePatients.isRowSelected(selectedRowIndex) == true) {
				    int clickedOption = JOptionPane.showConfirmDialog(null, "Are you sure you want to modify this row?", "Confirm Update", JOptionPane.YES_NO_OPTION);
				    
				    if(clickedOption == JOptionPane.YES_OPTION) {
						DataModule data = new DataModule();
						DefaultTableModel model = (DefaultTableModel)tablePatients.getModel();
						
							try {
								Connection connection = data.getConnection();
								
								//Check if email is unique.
								
								String email = textFieldPatientEmail.getText().concat(comboBoxPatientEmail.getSelectedItem().toString());
								String queryEmail = "SELECT email "
													+ "FROM patients "
													+ "WHERE email = '" + email + "' "
														+ "AND id != " + (int)(model.getValueAt(selectedRowIndex, 0));
								queryEmail = data.getColumnAsString(connection, queryEmail, "email");
							    
								if(queryEmail != null) {
									JOptionPane.showMessageDialog(null, "Duplicate entry: email must be unique.", "Error", JOptionPane.ERROR_MESSAGE);
									
								} else {
									
									//Retrieve date field as "yyyy-MM-dd".
									
									String dateOfBirth = patientDOBPicker.getJFormattedTextField().getText();
									dateOfBirth = utilities.retrieveFormattedDate(dateOfBirth);
									
									//Execute query.
									
									String queryUpdate = "UPDATE patients SET "
															+ "first_name = '" + textFieldPatientFirstName.getText()+ "' , "
															+ "last_name = '" + textFieldPatientLastName.getText() + "' , "
															+ "date_of_birth = '" + dateOfBirth + "' , "
															+ "gender = '" + comboBoxPatientGender.getSelectedItem().toString() + "' , "
															+ "phone_number = '" + formattedTextFieldPatientPhone.getText() + "' , "
															+ "email = '" + email
														+ "' WHERE id = " + (int)(model.getValueAt(selectedRowIndex, 0));
								
									PreparedStatement statement = connection.prepareStatement(queryUpdate);
								    statement.executeUpdate(queryUpdate);
								    data.selectData(connection, 
													"SELECT "
															+ "id, "
															+ "first_name, "
															+ "last_name, "
															+ "DATE_FORMAT(date_of_birth, '%m-%d-%Y') AS date_of_birth, "
															+ "gender, "
															+ "phone_number, "
															+ "email "
														+ "FROM patients "
														+ "WHERE doctor_id = " + idsSelectDoctorTop.get(comboBoxSelectDoctorTop.getSelectedIndex()), 
						    						tablePatients
						    						);
									
								    String[] columnNames = {"First Name", "Last Name", "Date of Birth", "Gender", "Phone Number", "Email"};
									utilities.renameColumns(tablePatients, columnNames);
									
								}
							
							} catch (Exception exception) {
								exception.printStackTrace();
							}
						
				    }
				    
				} else {
					JOptionPane.showMessageDialog(null, "Please select a row first.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnUpdatePatient.setToolTipText("Update selected patient");
		btnUpdatePatient.setBounds(481, 489, 89, 23);
		panelPatients.add(btnUpdatePatient);
		
		//Implement button "Delete".
		
		JButton btnDeletePatient = new JButton("Delete");
		btnDeletePatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selectedRowIndex = tablePatients.getSelectedRow();
				
				//Confirm row deletion.
				
				if(tablePatients.isRowSelected(selectedRowIndex) == true) {
				    int clickedOption = JOptionPane.showConfirmDialog(null, "Removing a patient will also remove its associated appointments.\nAre you sure you want to delete this row?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
				    
				    if(clickedOption == JOptionPane.YES_OPTION) {	
						DataModule data = new DataModule();
						DefaultTableModel model = (DefaultTableModel)tablePatients.getModel();
					
						try {
							Connection connection = data.getConnection();
							
							//Execute query.
						
							String queryDelete = "DELETE FROM patients "
												+ "WHERE id = " + (int)(model.getValueAt(selectedRowIndex, 0));
						
							PreparedStatement statement = connection.prepareStatement(queryDelete);
						    statement.executeUpdate(queryDelete);
						    data.selectData(connection, 
						    				"SELECT "
						    						+ "id, "
						    						+ "first_name, "
						    						+ "last_name, "
						    						+ "DATE_FORMAT(date_of_birth, '%m-%d-%Y') AS date_of_birth, "
						    						+ "gender, "
						    						+ "phone_number, "
						    						+ "email "
						    					+ "FROM patients "
						    					+ "WHERE doctor_id = " + idsSelectDoctorTop.get(comboBoxSelectDoctorTop.getSelectedIndex()), 
						    				tablePatients
						    				);
							
						    String[] columnNames = {"First Name", "Last Name", "Date of Birth", "Gender", "Phone Number", "Email"};
							utilities.renameColumns(tablePatients, columnNames);
						
						} catch (Exception exception) {
							exception.printStackTrace();
						}
				    }
				    
				} else {
					JOptionPane.showMessageDialog(null, "Please select a row first.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnDeletePatient.setToolTipText("Delete selected patient");
		btnDeletePatient.setBounds(382, 489, 89, 23);
		panelPatients.add(btnDeletePatient);
		
		//Implement button "Clear".
		
		JButton btnClearPatients = new JButton("Clear");
		btnClearPatients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textFieldPatientFirstName.setText("");
				
				textFieldPatientLastName.setText("");
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd");
				Date currentDate = new Date();
				String date = dateFormat.format(currentDate);
				patientDOBModel.setDate(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(5,6)) - 1, Integer.parseInt(date.substring(7)));
				patientDOBPicker.getJFormattedTextField().setText("");
				
				comboBoxPatientGender.setSelectedIndex(0);
				
				formattedTextFieldPatientPhone.setText("");
				
				textFieldPatientEmail.setText("");
				
				comboBoxPatientEmail.setSelectedIndex(0);
				
			}
		});
		btnClearPatients.setBounds(28, 489, 89, 23);
		panelPatients.add(btnClearPatients);
		
		JScrollPane scrollPanePatients = new JScrollPane();
		scrollPanePatients.setBounds(10, 40, 669, 201);
		panelPatients.add(scrollPanePatients);
		
		//Fill "tablePatients" with "mouseClicked" event.
		
		tablePatients = new JTable();
		tablePatients.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel model = (DefaultTableModel)tablePatients.getModel();
				int selectedRowIndex = tablePatients.getSelectedRow();
				
				textFieldDoctorOfPatient.setText(comboBoxSelectDoctorTop.getSelectedItem().toString());
				
				textFieldPatientFirstName.setText(model.getValueAt(selectedRowIndex, 1).toString());
				
				textFieldPatientLastName.setText(model.getValueAt(selectedRowIndex, 2).toString());
				
				String dateOfBirth = model.getValueAt(selectedRowIndex, 3).toString();
				int year = Integer.parseInt(dateOfBirth.substring(6));
				int month = Integer.parseInt(dateOfBirth.substring(0, 2)) - 1;
				int day = Integer.parseInt(dateOfBirth.substring(3, 5));
				patientDOBModel.setDate(year, month, day);
				patientDOBModel.setSelected(true);
				
				comboBoxPatientGender.setSelectedItem(model.getValueAt(selectedRowIndex, 4).toString());
				
				String phoneNumber = model.getValueAt(selectedRowIndex, 5).toString().replaceAll("-", "");
				formattedTextFieldPatientPhone.setText(phoneNumber);
				
				String emailFirstHalf = model.getValueAt(selectedRowIndex, 6).toString();
				textFieldPatientEmail.setText(emailFirstHalf.substring(0, emailFirstHalf.indexOf("@")));
				
				String emailSecondHalf = model.getValueAt(selectedRowIndex, 6).toString().substring(emailFirstHalf.indexOf("@"));
				comboBoxPatientEmail.setSelectedItem(emailSecondHalf);
				
			}
		});
		tablePatients.setBounds(10, 40, 669, 201);
		scrollPanePatients.setViewportView(tablePatients);
		
		JPanel panelDoctors = new JPanel();
		tabbedPane.addTab("", new ImageIcon("C:\\Users\\arias\\eclipse-workspace\\doctor-appointment-app\\resources\\doctors.png"), panelDoctors, null);
		panelDoctors.setLayout(null);
		
		JLabel lblViewDoctors = new JLabel("View doctors:");
		lblViewDoctors.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblViewDoctors.setBounds(10, 16, 330, 18);
		panelDoctors.add(lblViewDoctors);
		
		//Implement button "Show Doctors".
		
		JButton btnShowDoctors = new JButton("Show Doctors");
		btnShowDoctors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DataModule data = new DataModule();
				
				try {
					Connection connection = data.getConnection();
					
					String querySelect = "SELECT "
											+ "id, "
											+ "title, "
											+ "first_name, "
											+ "last_name, "
											+ "DATE_FORMAT(date_of_birth, '%m-%d-%Y') AS date_of_birth, "
											+ "gender, "
											+ "phone_number, "
											+ "email "
										+ "FROM doctors";
					data.selectData(connection, querySelect, tableDoctors);
					
					String[] columnNames = {"Title", "First Name", "Last Name", "Date of Birth", "Gender", "Phone Number", "Email"};
					utilities.renameColumns(tableDoctors, columnNames);
					
				} catch (Exception exception) {
					exception.printStackTrace();
				}
				
			}
		});
		btnShowDoctors.setBounds(552, 10, 127, 23);
		panelDoctors.add(btnShowDoctors);
		
		JLabel lblManageDoctors = new JLabel("Manage Doctors:");
		lblManageDoctors.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblManageDoctors.setBounds(10, 257, 206, 18);
		panelDoctors.add(lblManageDoctors);
		
		JSeparator separator9 = new JSeparator();
		separator9.setBounds(10, 281, 669, 10);
		panelDoctors.add(separator9);
		
		JSeparator separator10 = new JSeparator();
		separator10.setOrientation(SwingConstants.VERTICAL);
		separator10.setBounds(9, 281, 9, 243);
		panelDoctors.add(separator10);
		
		JSeparator separator11 = new JSeparator();
		separator11.setOrientation(SwingConstants.VERTICAL);
		separator11.setBounds(679, 281, 9, 243);
		panelDoctors.add(separator11);
		
		JSeparator separator12 = new JSeparator();
		separator12.setBounds(10, 523, 669, 10);
		panelDoctors.add(separator12);
		
		JLabel lblDoctorTitle = new JLabel("Title:");
		lblDoctorTitle.setBounds(20, 286, 81, 14);
		panelDoctors.add(lblDoctorTitle);
		
		JComboBox<String> comboBoxDoctorTitle = new JComboBox<>();
		comboBoxDoctorTitle.setToolTipText("Select doctor's title");
		comboBoxDoctorTitle.setBounds(20, 303, 189, 22);
		panelDoctors.add(comboBoxDoctorTitle);
		
		//Fill "comboBoxDoctorTitle".
		
		{
			DataModule data = new DataModule();
		
			try {
				Connection connection = data.getConnection();
			
				String querySelect = "SELECT title "
									+ "FROM titles";
				data.fillComboBox(connection, querySelect, comboBoxDoctorTitle, "title");
			
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
		
		JLabel lblDoctorFirstName = new JLabel("First Name:");
		lblDoctorFirstName.setBounds(20, 334, 76, 14);
		panelDoctors.add(lblDoctorFirstName);
		
		textFieldDoctorFirstName = new JTextField();
		textFieldDoctorFirstName.setToolTipText("Enter doctor's first name");
		textFieldDoctorFirstName.setColumns(10);
		textFieldDoctorFirstName.setBounds(20, 349, 189, 20);
		panelDoctors.add(textFieldDoctorFirstName);
		
		JLabel lblDoctorLastName = new JLabel("Last Name:");
		lblDoctorLastName.setBounds(363, 334, 86, 14);
		panelDoctors.add(lblDoctorLastName);
		
		textFieldDoctorLastName = new JTextField();
		textFieldDoctorLastName.setToolTipText("Enter doctor's last name");
		textFieldDoctorLastName.setColumns(10);
		textFieldDoctorLastName.setBounds(363, 349, 189, 20);
		panelDoctors.add(textFieldDoctorLastName);
		
		JLabel lblDoctorDOB = new JLabel("Date of Birth:");
		lblDoctorDOB.setBounds(21, 380, 80, 14);
		panelDoctors.add(lblDoctorDOB);
		
		UtilDateModel doctorDOBModel = new UtilDateModel();
		Properties doctorDOBProperties = new Properties();
		doctorDOBProperties.put("text.today", "Today");
		doctorDOBProperties.put("text.month", "Month");
		doctorDOBProperties.put("text.year", "Year");
		JDatePanelImpl doctorDOBPanel = new JDatePanelImpl(doctorDOBModel, doctorDOBProperties);
		JDatePickerImpl doctorDOBPicker = new JDatePickerImpl(doctorDOBPanel, new DateLabelFormatter());
		doctorDOBPicker.getJFormattedTextField().setToolTipText("Select doctor's date of birth from associated button");
		doctorDOBPicker.setBounds(21, 395, 214, 23);
		panelDoctors.add(doctorDOBPicker);
		
		JLabel lblDoctorGender = new JLabel("Gender:");
		lblDoctorGender.setBounds(363, 380, 46, 14);
		panelDoctors.add(lblDoctorGender);
		
		JComboBox<String> comboBoxDoctorGender = new JComboBox<>();
		comboBoxDoctorGender.setToolTipText("Select doctor's gender");
		comboBoxDoctorGender.setBounds(363, 394, 189, 22);
		panelDoctors.add(comboBoxDoctorGender);
		
		//Fill "comboBoxDoctorGender".
		
		{
			DataModule data = new DataModule();
		
			try {
				Connection connection = data.getConnection();
			
				String querySelect = "SELECT gender "
									+ "FROM genders";
				data.fillComboBox(connection, querySelect, comboBoxDoctorGender, "gender");
			
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
		
		JLabel lblDoctorPhone = new JLabel("Phone Number:");
		lblDoctorPhone.setBounds(21, 429, 98, 14);
		panelDoctors.add(lblDoctorPhone);
		
		//Create mask for field "formattedTextFieldDoctorPhone".
		
		try {
			MaskFormatter mask = new MaskFormatter("(###) ###-####");
			formattedTextFieldDoctorPhone = new JFormattedTextField(mask);
			formattedTextFieldDoctorPhone.setToolTipText("Enter doctor's phone number");
			formattedTextFieldDoctorPhone.setBounds(21, 444, 189, 20);
			panelDoctors.add(formattedTextFieldDoctorPhone);
			
		} catch (ParseException exception) {
			exception.printStackTrace();
		}
		
		JLabel lblDoctorEmail = new JLabel("Email:");
		lblDoctorEmail.setBounds(363, 427, 46, 14);
		panelDoctors.add(lblDoctorEmail);
		
		textFieldDoctorEmail = new JTextField();
		textFieldDoctorEmail.setToolTipText("Enter doctor's email");
		textFieldDoctorEmail.setColumns(10);
		textFieldDoctorEmail.setBounds(363, 442, 89, 22);
		panelDoctors.add(textFieldDoctorEmail);
		
		JComboBox<String> comboBoxDoctorEmail = new JComboBox<>();
		comboBoxDoctorEmail.setToolTipText("Select email provider");
		comboBoxDoctorEmail.setBounds(454, 442, 98, 22);
		panelDoctors.add(comboBoxDoctorEmail);
		
		//Fill "comboBoxDoctorEmail".
		
		{
			DataModule data = new DataModule();
		
			try {
				Connection connection = data.getConnection();
			
				String querySelect = "SELECT email_provider "
									+ "FROM email_providers";
				data.fillComboBox(connection, querySelect, comboBoxDoctorEmail, "email_provider");
			
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
		
		//Update "lblViewPatients", "lblViewAppointments" and "comboBoxAppointmentPatient".
		
		comboBoxSelectDoctorTop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					lblViewPatients.setText("<HTML>View patients of Dr. <B>" + comboBoxSelectDoctorTop.getSelectedItem().toString() + "</B>:</HTML>");
					lblViewAppointments.setText("<HTML>View appointments of Dr. <B>" + comboBoxSelectDoctorTop.getSelectedItem().toString() + "</B>:</HTML>");
					
					//Fill "comboBoxAppointmentPatient".
					
					{
						DataModule data = new DataModule();
					
						try {
							Connection connection = data.getConnection();
						
							comboBoxAppointmentPatient.removeAllItems();
							String querySelect = "SELECT CONCAT(patients.first_name, ' ', patients.last_name) AS patient_full_name "
												+ "FROM patients "
													+ "JOIN doctors ON patients.doctor_id = doctors.id "
												+ "WHERE patients.doctor_id = " + idsSelectDoctorTop.get(comboBoxSelectDoctorTop.getSelectedIndex());
							data.fillComboBox(connection, querySelect, comboBoxAppointmentPatient, "patient_full_name");
						
						} catch (Exception exception) {
							exception.printStackTrace();
						}
					}
				
			}
		});
		comboBoxSelectDoctorTop.setToolTipText("Selection will influence Appointments & Patients tab");
		comboBoxSelectDoctorTop.setBounds(347, 62, 179, 22);
		frmMain.getContentPane().add(comboBoxSelectDoctorTop);
		
		//Implement button "Add".
		
		JButton btnAddDoctor = new JButton("Add");
		btnAddDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//check if fields are empty.
				
				if(textFieldDoctorFirstName.getText().isEmpty() 
						|| textFieldDoctorLastName.getText().isEmpty() 
						|| doctorDOBPicker.getJFormattedTextField().getText().isEmpty() 
						|| formattedTextFieldDoctorPhone.getText().length() < 7 
						|| textFieldDoctorEmail.getText().isEmpty()) {
					
					JOptionPane.showMessageDialog(new JFrame(), "Fields cannot be left empty.", "Error", JOptionPane.ERROR_MESSAGE);
					
				} else {
					DataModule data = new DataModule();
					
					try {
						Connection connection = data.getConnection();
						
						//Check if email is unique.
						
						String email = textFieldDoctorEmail.getText().concat(comboBoxDoctorEmail.getSelectedItem().toString());
						String queryEmail = "SELECT email "
											+ "FROM doctors "
											+ "WHERE email = '" + email + "'";
						queryEmail = data.getColumnAsString(connection, queryEmail, "email");
					    
						if(queryEmail != null) {
							JOptionPane.showMessageDialog(null, "Duplicate entry: email must be unique.", "Error", JOptionPane.ERROR_MESSAGE);
							
						} else {
							
							//Retrieve date field as "yyyy-MM-dd".
							
							String dateOfBirth = doctorDOBPicker.getJFormattedTextField().getText();
							dateOfBirth = utilities.retrieveFormattedDate(dateOfBirth);
							
							//Execute query.
							
							String queryInsert = "INSERT INTO doctors (title, first_name, last_name, date_of_birth, gender, phone_number, email) "
												+ "VALUES('" 
													+ comboBoxDoctorTitle.getSelectedItem().toString() + "', '" 
													+ textFieldDoctorFirstName.getText() + "', '" 
													+ textFieldDoctorLastName.getText() + "', '"
													+ dateOfBirth + "', '"
													+ comboBoxDoctorGender.getSelectedItem().toString() + "', '"
													+ formattedTextFieldDoctorPhone.getText() + "', '"
													+ textFieldDoctorEmail.getText().concat(comboBoxDoctorEmail.getSelectedItem().toString()) 
												+ "')";
							
							PreparedStatement statement = connection.prepareStatement(queryInsert);
						    statement.executeUpdate(queryInsert);
						    data.selectData(connection, 
		    								"SELECT "
		    										+ "id, "
		    										+ "title, "
		    										+ "first_name, "
		    										+ "last_name, "
		    										+ "DATE_FORMAT(date_of_birth, '%m-%d-%Y') AS date_of_birth, "
		    										+ "gender, "
		    										+ "phone_number, "
		    										+ "email "
		    									+ "FROM doctors", 
		    								tableDoctors
		    								);
							
						    String[] columnNames = {"Title", "First Name", "Last Name", "Date of Birth", "Gender", "Phone Number", "Email"};
							utilities.renameColumns(tableDoctors, columnNames);
							
							//Refresh "comboBoxSelectDoctorTop".
							
							comboBoxSelectDoctorTop.removeAllItems();
							String querySelect = "SELECT CONCAT(first_name, ' ', last_name) AS full_name, id "
												+ "FROM doctors";
							data.fillComboBox(connection, querySelect, comboBoxSelectDoctorTop, "full_name");
							
							//Get id's to populate "tablePatients" and "tableAppointments".

							idsSelectDoctorTop.clear();
							data.fillList(connection, querySelect, idsSelectDoctorTop, "id");
							
						}
						
					} catch (Exception exception) {
						exception.printStackTrace();
					}
				}
				
			}
		});
		btnAddDoctor.setToolTipText("Add current doctor");
		btnAddDoctor.setBounds(580, 489, 89, 23);
		panelDoctors.add(btnAddDoctor);
		
		//Implement button "Update".
		
		JButton btnUpdateDoctor = new JButton("Update");
		btnUpdateDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selectedRowIndex = tableDoctors.getSelectedRow();
				
				//Confirm row update.
				
				if(tableDoctors.isRowSelected(selectedRowIndex) == true) {
				    int clickedOption = JOptionPane.showConfirmDialog(null, "Are you sure you want to modify this row?", "Confirm Update", JOptionPane.YES_NO_OPTION);
				    
				    if(clickedOption == JOptionPane.YES_OPTION) {
						DataModule data = new DataModule();
						DefaultTableModel model = (DefaultTableModel)tableDoctors.getModel();
						
							try {
								Connection connection = data.getConnection();
								
								//Check if email is unique.
								
								String email = textFieldDoctorEmail.getText().concat(comboBoxDoctorEmail.getSelectedItem().toString());
								String queryEmail = "SELECT email "
													+ "FROM doctors "
													+ "WHERE email = '" + email + "' "
														+ "AND id != " + (int)(model.getValueAt(selectedRowIndex, 0));
								queryEmail = data.getColumnAsString(connection, queryEmail, "email");
							    
								if(queryEmail != null) {
									JOptionPane.showMessageDialog(null, "Duplicate entry: email must be unique.", "Error", JOptionPane.ERROR_MESSAGE);
									
								} else {
									
									//Retrieve date field as "yyyy-MM-dd".
									
									String dateOfBirth = doctorDOBPicker.getJFormattedTextField().getText();
									dateOfBirth = utilities.retrieveFormattedDate(dateOfBirth);
									
									//Execute query.
									
									String queryUpdate = "UPDATE doctors SET "
															+ "title = '" + comboBoxDoctorTitle.getSelectedItem().toString() + "' , "
															+ "first_name = '" + textFieldDoctorFirstName.getText() + "' , "
															+ "last_name = '" + textFieldDoctorLastName.getText() + "' , "
															+ "date_of_birth = '" + dateOfBirth + "' , "
															+ "gender = '" + comboBoxDoctorGender.getSelectedItem().toString() + "' , "
															+ "phone_number = '" + formattedTextFieldDoctorPhone.getText() + "' , "
															+ "email = '" + email
														+ "' WHERE id = " + (int)(model.getValueAt(selectedRowIndex, 0));
								
									PreparedStatement statement = connection.prepareStatement(queryUpdate);
								    statement.executeUpdate(queryUpdate);
								    data.selectData(connection, 
						    						"SELECT "
						    								+ "id, "
						    								+ "title, "
						    								+ "first_name, "
						    								+ "last_name, "
						    								+ "DATE_FORMAT(date_of_birth, '%m-%d-%Y') AS date_of_birth, "
						    								+ "gender, "
						    								+ "phone_number, "
						    								+ "email "
						    							+ "FROM doctors", 
						    						tableDoctors
						    						);
									
								    String[] columnNames = {"Title", "First Name", "Last Name", "Date of Birth", "Gender", "Phone Number", "Email"};
									utilities.renameColumns(tableDoctors, columnNames);
									
									//Refresh "comboBoxSelectDoctorTop".
									
									comboBoxSelectDoctorTop.removeAllItems();
									String querySelect = "SELECT CONCAT(first_name, ' ', last_name) AS full_name, id "
														+ "FROM doctors";
									data.fillComboBox(connection, querySelect, comboBoxSelectDoctorTop, "full_name");
									
									//Get id's to populate "tablePatients" and "tableAppointments".

									idsSelectDoctorTop.clear();
									data.fillList(connection, querySelect, idsSelectDoctorTop, "id");
									
								}
							
							} catch (Exception exception) {
								exception.printStackTrace();
							}
						
				    }
				    
				} else {
					JOptionPane.showMessageDialog(null, "Please select a row first.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnUpdateDoctor.setToolTipText("Update selected doctor");
		btnUpdateDoctor.setBounds(481, 489, 89, 23);
		panelDoctors.add(btnUpdateDoctor);
		
		//Implement button "Delete".
		
		JButton btnDeleteDoctor = new JButton("Delete");
		btnDeleteDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selectedRowIndex = tableDoctors.getSelectedRow();
				
				//Confirm row deletion.
				
				if(tableDoctors.isRowSelected(selectedRowIndex) == true) {
				    int clickedOption = JOptionPane.showConfirmDialog(null, "Removing a doctor will also remove its associated patients and appointments.\nAre you sure you want to delete this row?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
				    
				    if(clickedOption == JOptionPane.YES_OPTION) {	
						DataModule data = new DataModule();
						DefaultTableModel model = (DefaultTableModel)tableDoctors.getModel();
					
						try {
							Connection connection = data.getConnection();
							
							//Execute query.
						
							String queryDelete = "DELETE FROM doctors "
												+ "WHERE id = " + (int)(model.getValueAt(selectedRowIndex, 0));
						
							PreparedStatement statement = connection.prepareStatement(queryDelete);
						    statement.executeUpdate(queryDelete);
						    data.selectData(connection, 
						    				"SELECT "
						    						+ "id, "
						    						+ "title, "
						    						+ "first_name, "
						    						+ "last_name, "
						    						+ "DATE_FORMAT(date_of_birth, '%m-%d-%Y') AS date_of_birth, "
						    						+ "gender, "
						    						+ "phone_number, "
						    						+ "email "
						    					+ "FROM doctors", 
						    				tableDoctors
						    				);
							
						    String[] columnNames = {"Title", "First Name", "Last Name", "Date of Birth", "Gender", "Phone Number", "Email"};
							utilities.renameColumns(tableDoctors, columnNames);
							
							//Refresh "comboBoxSelectDoctorTop".
							
							comboBoxSelectDoctorTop.removeAllItems();
							String querySelect = "SELECT CONCAT(first_name, ' ', last_name) AS full_name, id "
												+ "FROM doctors";
							data.fillComboBox(connection, querySelect, comboBoxSelectDoctorTop, "full_name");
							
							//Get id's to populate "tablePatients" and "tableAppointments".

							idsSelectDoctorTop.clear();
							data.fillList(connection, querySelect, idsSelectDoctorTop, "id");
						
						} catch (Exception exception) {
							exception.printStackTrace();
						}
				    }
				    
				} else {
					JOptionPane.showMessageDialog(null, "Please select a row first.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnDeleteDoctor.setToolTipText("Delete selected doctor");
		btnDeleteDoctor.setBounds(382, 489, 89, 23);
		panelDoctors.add(btnDeleteDoctor);
		
		JScrollPane scrollPaneDoctors = new JScrollPane();
		scrollPaneDoctors.setBounds(10, 40, 669, 201);
		panelDoctors.add(scrollPaneDoctors);
		
		//Fill "tableDoctors" with "mouseClicked" event.
		
		tableDoctors = new JTable();
		tableDoctors.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel model = (DefaultTableModel)tableDoctors.getModel();
				int selectedRowIndex = tableDoctors.getSelectedRow();
				
				comboBoxDoctorTitle.setSelectedItem(model.getValueAt(selectedRowIndex, 1).toString());
				
				textFieldDoctorFirstName.setText(model.getValueAt(selectedRowIndex, 2).toString());
				
				textFieldDoctorLastName.setText(model.getValueAt(selectedRowIndex, 3).toString());
				
				String dateOfBirth = model.getValueAt(selectedRowIndex, 4).toString();
				int year = Integer.parseInt(dateOfBirth.substring(6));
				int month = Integer.parseInt(dateOfBirth.substring(0, 2)) - 1;
				int day = Integer.parseInt(dateOfBirth.substring(3, 5));
				doctorDOBModel.setDate(year, month, day);
				doctorDOBModel.setSelected(true);
				
				comboBoxDoctorGender.setSelectedItem(model.getValueAt(selectedRowIndex, 5).toString());
				
				String phoneNumber = model.getValueAt(selectedRowIndex, 6).toString().replaceAll("-", "");
				formattedTextFieldDoctorPhone.setText(phoneNumber);
				
				String emailFirstHalf = model.getValueAt(selectedRowIndex, 7).toString();
				textFieldDoctorEmail.setText(emailFirstHalf.substring(0, emailFirstHalf.indexOf("@")));
				
				String emailSecondHalf = model.getValueAt(selectedRowIndex, 7).toString().substring(emailFirstHalf.indexOf("@"));
				comboBoxDoctorEmail.setSelectedItem(emailSecondHalf);
				
			}
		});
		tableDoctors.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableDoctors.setBounds(10, 40, 669, 201);
		scrollPaneDoctors.setViewportView(tableDoctors);
		
		//Implement button "Clear".
		
		JButton btnClearDoctors = new JButton("Clear");
		btnClearDoctors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				comboBoxDoctorTitle.setSelectedIndex(0);
				
				textFieldDoctorFirstName.setText("");
				
				textFieldDoctorLastName.setText("");
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd");
				Date currentDate = new Date();
				String date = dateFormat.format(currentDate);
				doctorDOBModel.setDate(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(5,6)) - 1, Integer.parseInt(date.substring(7)));
				doctorDOBPicker.getJFormattedTextField().setText("");
				
				comboBoxDoctorGender.setSelectedIndex(0);
				
				formattedTextFieldDoctorPhone.setText("");
				
				textFieldDoctorEmail.setText("");
				
				comboBoxDoctorEmail.setSelectedIndex(0);
				
			}
		});
		btnClearDoctors.setBounds(28, 489, 89, 23);
		panelDoctors.add(btnClearDoctors);
		
		//Implement button "Quit".
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				utilities.confirmQuitDialog(frmMain);
				
			}
		});
		btnQuit.setToolTipText("Quit the application");
		btnQuit.setBounds(639, 887, 89, 30);
		frmMain.getContentPane().add(btnQuit);
		
	}
}
