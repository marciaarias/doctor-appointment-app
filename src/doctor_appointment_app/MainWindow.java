package doctor_appointment_app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.util.Properties;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.UIManager;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		frmMain.setResizable(false);
		frmMain.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\arias\\eclipse-workspace\\doctor-appointment-app\\resources\\appointmentManager.png"));
		frmMain.setTitle("Home - Doctor Appointment Manager");
		frmMain.setBounds(100, 100, 774, 976);
		frmMain.setLocationRelativeTo(null);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMain.getContentPane().setLayout(null);
		
		JLabel lblWelcomeBack = new JLabel("Welcome back, Fulano");
		lblWelcomeBack.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblWelcomeBack.setBounds(7, 9, 426, 23);
		frmMain.getContentPane().add(lblWelcomeBack);
		
		JLabel lblLogout = new JLabel("<HTML><U>Logout</HTML></U>");
		lblLogout.setToolTipText("Logout & return to welcome window");
		lblLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DoctorAppointmentApp doctorAppointmentApp = new DoctorAppointmentApp();
				doctorAppointmentApp.frmDoctorAppointmentManager.setVisible(true);
				
				frmMain.dispose();
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
		
		JComboBox comboBoxSelectDoctorTop = new JComboBox();
		comboBoxSelectDoctorTop.setToolTipText("Selection will influence Appointments & Patients tab");
		comboBoxSelectDoctorTop.setBounds(347, 62, 179, 22);
		frmMain.getContentPane().add(comboBoxSelectDoctorTop);
		
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
		
		JLabel lblViewAppointments = new JLabel("View appointments of Dr. ...:");
		lblViewAppointments.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblViewAppointments.setBounds(10, 16, 330, 18);
		panelAppointments.add(lblViewAppointments);
		
		JButton btnShowAppointments = new JButton("Show Appointments");
		btnShowAppointments.setBounds(552, 10, 127, 23);
		panelAppointments.add(btnShowAppointments);
		
		tableAppointments = new JTable();
		tableAppointments.setBounds(10, 40, 669, 201);
		panelAppointments.add(tableAppointments);
		
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
		
		JComboBox comboBoxAppointmentPatient = new JComboBox();
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
		
		JPanel panelPatients = new JPanel();
		tabbedPane.addTab("       ", new ImageIcon("C:\\Users\\arias\\eclipse-workspace\\doctor-appointment-app\\resources\\patients.png"), panelPatients, null);
		panelPatients.setLayout(null);
		
		JLabel lblViewPatients = new JLabel("View patients of Dr. ...:");
		lblViewPatients.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblViewPatients.setBounds(10, 16, 330, 18);
		panelPatients.add(lblViewPatients);
		
		JButton btnShowPatients = new JButton("Show Patients");
		btnShowPatients.setBounds(552, 10, 127, 23);
		panelPatients.add(btnShowPatients);
		
		tablePatients = new JTable();
		tablePatients.setBounds(10, 40, 669, 201);
		panelPatients.add(tablePatients);
		
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
		
		JComboBox comboBoxPatientGender = new JComboBox();
		comboBoxPatientGender.setToolTipText("Select patient's gender");
		comboBoxPatientGender.setBounds(363, 394, 189, 22);
		panelPatients.add(comboBoxPatientGender);
		
		JLabel lblPatientPhone = new JLabel("Phone Number:");
		lblPatientPhone.setBounds(21, 429, 98, 14);
		panelPatients.add(lblPatientPhone);
		
		JFormattedTextField formattedTextFieldPatientPhone = new JFormattedTextField();
		formattedTextFieldPatientPhone.setToolTipText("Enter patient's phone number");
		formattedTextFieldPatientPhone.setBounds(21, 444, 189, 20);
		panelPatients.add(formattedTextFieldPatientPhone);
		
		JLabel lblPatientEmail = new JLabel("Email:");
		lblPatientEmail.setBounds(363, 427, 46, 14);
		panelPatients.add(lblPatientEmail);
		
		textFieldPatientEmail = new JTextField();
		textFieldPatientEmail.setToolTipText("Enter patient's email");
		textFieldPatientEmail.setBounds(363, 442, 89, 22);
		panelPatients.add(textFieldPatientEmail);
		textFieldPatientEmail.setColumns(10);
		
		JComboBox comboBoxPatientEmail = new JComboBox();
		comboBoxPatientEmail.setToolTipText("Select email provider");
		comboBoxPatientEmail.setBounds(454, 442, 98, 22);
		panelPatients.add(comboBoxPatientEmail);
		
		JButton btnAddPatient = new JButton("Add");
		btnAddPatient.setToolTipText("Add current patient");
		btnAddPatient.setBounds(580, 489, 89, 23);
		panelPatients.add(btnAddPatient);
		
		JButton btnUpdatePatient = new JButton("Update");
		btnUpdatePatient.setToolTipText("Update selected patient");
		btnUpdatePatient.setBounds(481, 489, 89, 23);
		panelPatients.add(btnUpdatePatient);
		
		JButton btnDeletePatient = new JButton("Delete");
		btnDeletePatient.setToolTipText("Delete selected patient");
		btnDeletePatient.setBounds(382, 489, 89, 23);
		panelPatients.add(btnDeletePatient);
		
		JPanel panelDoctors = new JPanel();
		tabbedPane.addTab("", new ImageIcon("C:\\Users\\arias\\eclipse-workspace\\doctor-appointment-app\\resources\\doctors.png"), panelDoctors, null);
		panelDoctors.setLayout(null);
		
		JLabel lblViewDoctors = new JLabel("View doctors:");
		lblViewDoctors.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblViewDoctors.setBounds(10, 16, 330, 18);
		panelDoctors.add(lblViewDoctors);
		
		JButton btnShowDoctors = new JButton("Show Doctors");
		btnShowDoctors.setBounds(552, 10, 127, 23);
		panelDoctors.add(btnShowDoctors);
		
		tableDoctors = new JTable();
		tableDoctors.setBounds(10, 40, 669, 201);
		panelDoctors.add(tableDoctors);
		
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
		
		JComboBox comboBoxDoctorTitle = new JComboBox();
		comboBoxDoctorTitle.setToolTipText("Select doctor's title");
		comboBoxDoctorTitle.setBounds(20, 303, 189, 22);
		panelDoctors.add(comboBoxDoctorTitle);
		
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
		
		JComboBox comboBoxDoctorGender = new JComboBox();
		comboBoxDoctorGender.setToolTipText("Select doctor's gender");
		comboBoxDoctorGender.setBounds(363, 394, 189, 22);
		panelDoctors.add(comboBoxDoctorGender);
		
		JLabel lblDoctorPhone = new JLabel("Phone Number:");
		lblDoctorPhone.setBounds(21, 429, 98, 14);
		panelDoctors.add(lblDoctorPhone);
		
		JFormattedTextField formattedTextFieldDoctorPhone = new JFormattedTextField();
		formattedTextFieldDoctorPhone.setToolTipText("Enter doctor's phone number");
		formattedTextFieldDoctorPhone.setBounds(21, 444, 189, 20);
		panelDoctors.add(formattedTextFieldDoctorPhone);
		
		JLabel lblDoctorEmail = new JLabel("Email:");
		lblDoctorEmail.setBounds(363, 427, 46, 14);
		panelDoctors.add(lblDoctorEmail);
		
		textFieldDoctorEmail = new JTextField();
		textFieldDoctorEmail.setToolTipText("Enter doctor's email");
		textFieldDoctorEmail.setColumns(10);
		textFieldDoctorEmail.setBounds(363, 442, 89, 22);
		panelDoctors.add(textFieldDoctorEmail);
		
		JComboBox comboBoxDoctorEmail = new JComboBox();
		comboBoxDoctorEmail.setToolTipText("Select email provider");
		comboBoxDoctorEmail.setBounds(454, 442, 98, 22);
		panelDoctors.add(comboBoxDoctorEmail);
		
		JButton btnAddDoctor = new JButton("Add");
		btnAddDoctor.setToolTipText("Add current doctor");
		btnAddDoctor.setBounds(580, 489, 89, 23);
		panelDoctors.add(btnAddDoctor);
		
		JButton btnUpdateDoctor = new JButton("Update");
		btnUpdateDoctor.setToolTipText("Update selected doctor");
		btnUpdateDoctor.setBounds(481, 489, 89, 23);
		panelDoctors.add(btnUpdateDoctor);
		
		JButton btnDeleteDoctor = new JButton("Delete");
		btnDeleteDoctor.setToolTipText("Delete selected doctor");
		btnDeleteDoctor.setBounds(382, 489, 89, 23);
		panelDoctors.add(btnDeleteDoctor);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.setToolTipText("Quit the application");
		btnQuit.setBounds(639, 887, 89, 30);
		frmMain.getContentPane().add(btnQuit);
	}
}
