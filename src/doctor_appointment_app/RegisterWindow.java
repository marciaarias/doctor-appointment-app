package doctor_appointment_app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.Connection;

public class RegisterWindow {

	public JFrame frmRegister;
	private JTextField textFieldUsername;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldRepeat;
	private JLabel lblUsernameWarning;
	private JLabel lblPasswordWarning;
	private JLabel lblRepeatPasswordWarning;

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
					RegisterWindow window = new RegisterWindow();
					window.frmRegister.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RegisterWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRegister = new JFrame();
		
		//Override windowClosing event.
		
		frmRegister.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				frmRegister.dispose();
				
				DoctorAppointmentApp doctorAppointmentApp = new DoctorAppointmentApp();
				doctorAppointmentApp.frmDoctorAppointmentManager.setVisible(true);
				
			}
		});
		
		frmRegister.setResizable(false);
		frmRegister.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\arias\\eclipse-workspace\\doctor-appointment-app\\resources\\register.png"));
		frmRegister.setTitle("Register - Doctor Appointment Manager");
		frmRegister.setBounds(100, 100, 472, 201);
		frmRegister.setLocationRelativeTo(null);
		frmRegister.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmRegister.getContentPane().setLayout(null);
		
		JLabel lblRegisterIcon = new JLabel("");
		lblRegisterIcon.setIcon(new ImageIcon("C:\\Users\\arias\\eclipse-workspace\\doctor-appointment-app\\resources\\user.png"));
		lblRegisterIcon.setBounds(21, 23, 80, 80);
		frmRegister.getContentPane().add(lblRegisterIcon);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(164, 31, 68, 14);
		frmRegister.getContentPane().add(lblUsername);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setToolTipText("");
		textFieldUsername.setColumns(10);
		textFieldUsername.setBounds(229, 25, 191, 20);
		frmRegister.getContentPane().add(textFieldUsername);
		
		lblUsernameWarning = new JLabel("");
		lblUsernameWarning.setIcon(new ImageIcon("C:\\Users\\arias\\eclipse-workspace\\doctor-appointment-app\\resources\\warning.png"));
		lblUsernameWarning.setBounds(421, 31, 21, 14);
		lblUsernameWarning.setVisible(false);
		frmRegister.getContentPane().add(lblUsernameWarning);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(165, 56, 68, 14);
		frmRegister.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("");
		passwordField.setBounds(229, 50, 191, 20);
		frmRegister.getContentPane().add(passwordField);
		
		lblPasswordWarning = new JLabel("");
		lblPasswordWarning.setIcon(new ImageIcon("C:\\Users\\arias\\eclipse-workspace\\doctor-appointment-app\\resources\\warning.png"));
		lblPasswordWarning.setBounds(421, 56, 21, 14);
		lblPasswordWarning.setVisible(false);
		frmRegister.getContentPane().add(lblPasswordWarning);
		
		JLabel lblRepeatPassword = new JLabel("Repeat password:");
		lblRepeatPassword.setBounds(126, 81, 89, 14);
		frmRegister.getContentPane().add(lblRepeatPassword);
		
		passwordFieldRepeat = new JPasswordField();
		passwordFieldRepeat.setToolTipText("");
		passwordFieldRepeat.setBounds(229, 75, 191, 20);
		frmRegister.getContentPane().add(passwordFieldRepeat);
		
		lblRepeatPasswordWarning = new JLabel("");
		lblRepeatPasswordWarning.setIcon(new ImageIcon("C:\\Users\\arias\\eclipse-workspace\\doctor-appointment-app\\resources\\warning.png"));
		lblRepeatPasswordWarning.setBounds(421, 81, 21, 14);
		lblRepeatPasswordWarning.setVisible(false);
		frmRegister.getContentPane().add(lblRepeatPasswordWarning);
		
		//Implement button "Register".
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				lblUsernameWarning.setVisible(false);
				lblUsernameWarning.setToolTipText("");
				
				lblPasswordWarning.setVisible(false);
				lblPasswordWarning.setToolTipText("");
				
				lblRepeatPasswordWarning.setVisible(false);
				lblRepeatPasswordWarning.setToolTipText("");
				
				DataModule data = new DataModule();
				
				try {
					Connection connection = data.getConnection();
					
					String queryUsername = "SELECT username FROM log_in WHERE username = '" + textFieldUsername.getText() + "'";
					queryUsername = data.getColumnAsString(connection, queryUsername, "username");
					
					boolean isWarningClear = true;
					
					if(textFieldUsername.getText().isEmpty() || textFieldUsername.getText().startsWith(" ")) {
						lblUsernameWarning.setVisible(true);
						lblUsernameWarning.setToolTipText("Username cannot be empty or start with a space");
						
						isWarningClear = false;
						
					} else if(textFieldUsername.getText().equals(queryUsername)) {
						lblUsernameWarning.setVisible(true);
						lblUsernameWarning.setToolTipText("Username already exists");
						
						isWarningClear = false;
					}
					
					if(String.valueOf(passwordField.getPassword()).isEmpty() || String.valueOf(passwordField.getPassword()).startsWith(" ")) {
						lblPasswordWarning.setVisible(true);
						lblPasswordWarning.setToolTipText("Password cannot be empty or start with a space");
						
						isWarningClear = false;
					}
					
					if(!String.valueOf(passwordFieldRepeat.getPassword()).equals(String.valueOf(passwordField.getPassword()))) {
						lblRepeatPasswordWarning.setVisible(true);
						lblRepeatPasswordWarning.setToolTipText("The passwords do not match");
						
						isWarningClear = false;
					}
					
					//Insert data if all warnings have been cleared.
					
					if(isWarningClear == true) {
						String hashedPassword = data.getColumnAsString(connection, "SELECT SHA1('" + String.valueOf(passwordField.getPassword()) + "') AS hashedPassword", "hashedPassword");
						
						String queryInsert = "INSERT INTO log_in VALUES('" + textFieldUsername.getText() + "', '" + hashedPassword + "')";
						data.updateData(connection, queryInsert);
						
						frmRegister.dispose();
						DoctorAppointmentApp doctorAppointmentApp = new DoctorAppointmentApp();
						doctorAppointmentApp.frmDoctorAppointmentManager.setVisible(true);

						JOptionPane.showMessageDialog(new JFrame(), "Registration successful!");
						
					}
					
				} catch (Exception exception) {
					exception.printStackTrace();
				}
				
			}
		});
		btnRegister.setBounds(229, 117, 89, 23);
		frmRegister.getContentPane().add(btnRegister);
		
		//Implement button "Cancel".
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frmRegister.dispose();
				
				DoctorAppointmentApp doctorAppointmentApp = new DoctorAppointmentApp();
				doctorAppointmentApp.frmDoctorAppointmentManager.setVisible(true);
				
			}
		});
		btnCancel.setBounds(331, 117, 89, 23);
		frmRegister.getContentPane().add(btnCancel);
	}

}
