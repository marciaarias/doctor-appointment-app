package doctor_appointment_app;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.Toolkit;

import java.sql.Connection;

public class LoginWindow {

	public JFrame frmLogin;
	private JTextField textFieldUsername;
	private JPasswordField passwordField;
	private JLabel lblWarning;
	private Timer timer;
	private JButton btnLogin;
	private static String user;
	
	public String getUser() {
		return user;
	}
	
	//Implement inner class "labelBlink".
	
	class labelBlink implements ActionListener {

		private JLabel label;
		private int count;
		
	    public labelBlink(JLabel label){
	        this.label = label;
	    }
	    
		@Override
	    public void actionPerformed(ActionEvent e) {
			if(count == 4) {
				timer.stop();
				count = 0;
					
			} else if(count % 2 == 0){
				label.setForeground(Color.RED.darker().darker().darker());
				
			} else {
				label.setForeground(Color.RED);
			}
				
	        count++;
	    }  	
	}

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
					LoginWindow window = new LoginWindow();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		
		//Override windowClosing event.
		
		frmLogin.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
				frmLogin.dispose();
				
				DoctorAppointmentApp doctorAppointmentApp = new DoctorAppointmentApp();
				doctorAppointmentApp.frmDoctorAppointmentManager.setVisible(true);
				
			}
		});
		
		frmLogin.setResizable(false);
		frmLogin.setTitle("Login - Doctor Appointment Manager");
		frmLogin.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\arias\\eclipse-workspace\\doctor-appointment-app\\resources\\login.png"));
		frmLogin.setBounds(100, 100, 430, 179);
		frmLogin.setLocationRelativeTo(null);
		frmLogin.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		JLabel lblLoginIcon = new JLabel("");
		lblLoginIcon.setIcon(new ImageIcon("C:\\Users\\arias\\eclipse-workspace\\doctor-appointment-app\\resources\\user.png"));
		lblLoginIcon.setBounds(21, 11, 80, 80);
		frmLogin.getContentPane().add(lblLoginIcon);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(126, 32, 68, 14);
		frmLogin.getContentPane().add(lblUsername);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setToolTipText("");
		textFieldUsername.setBounds(191, 26, 191, 20);
		frmLogin.getContentPane().add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(126, 57, 68, 14);
		frmLogin.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("");
		passwordField.setBounds(191, 51, 191, 20);
		frmLogin.getContentPane().add(passwordField);
		
		lblWarning = new JLabel("Incorrect username or password.");
		lblWarning.setForeground(Color.RED);
		lblWarning.setIcon(new ImageIcon("C:\\Users\\arias\\eclipse-workspace\\doctor-appointment-app\\resources\\warning.png"));
		lblWarning.setBounds(191, 72, 191, 14);
		lblWarning.setVisible(false);
		frmLogin.getContentPane().add(lblWarning);
		
		//Implement button "Login".
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DataModule data = new DataModule();
				
				try {
					Connection connection = data.getConnection();
									
					String hashedPassword = data.getColumnAsString(connection, "SELECT SHA1('" + String.valueOf(passwordField.getPassword()) + "') AS hashedPassword", "hashedPassword");
					String validateCredentials = "SELECT username FROM log_in WHERE username = '" + textFieldUsername.getText() +"' AND password = '" + hashedPassword + "'";
					validateCredentials = data.getColumnAsString(connection, validateCredentials, "username");
					
					if(validateCredentials != null) {
						user = validateCredentials;
						
						MainWindow mainWindow = new MainWindow();
						mainWindow.frmMain.setVisible(true);
						
						frmLogin.dispose();
						
					} else {
						passwordField.setText("");
						
						lblWarning.setVisible(true);

						timer = new Timer(120, new labelBlink(lblWarning));
						timer.start();

					}
					
				} catch (Exception exception) {
					exception.printStackTrace();
				}
				
			}
		});
		btnLogin.setBounds(194, 95, 89, 23);
		frmLogin.getContentPane().add(btnLogin);
		
		//Implement button "Cancel".
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frmLogin.dispose();
				
				DoctorAppointmentApp doctorAppointmentApp = new DoctorAppointmentApp();
				doctorAppointmentApp.frmDoctorAppointmentManager.setVisible(true);
				
			}
		});
		btnCancel.setBounds(293, 95, 89, 23);
		frmLogin.getContentPane().add(btnCancel);
	}
}
