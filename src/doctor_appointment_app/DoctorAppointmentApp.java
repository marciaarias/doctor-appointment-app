package doctor_appointment_app;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.ImageIcon;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DoctorAppointmentApp {

	public JFrame frmDoctorAppointmentManager;

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
					DoctorAppointmentApp window = new DoctorAppointmentApp();
					window.frmDoctorAppointmentManager.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DoctorAppointmentApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDoctorAppointmentManager = new JFrame();
		frmDoctorAppointmentManager.setResizable(false);
		frmDoctorAppointmentManager.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\arias\\eclipse-workspace\\doctor-appointment-app\\resources\\appointmentManager.png"));
		frmDoctorAppointmentManager.setTitle("Welcome - Doctor Appointment Manager");
		frmDoctorAppointmentManager.setBounds(100, 100, 572, 507);
		frmDoctorAppointmentManager.setLocationRelativeTo(null);
		frmDoctorAppointmentManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Implement label "Login".
		
		JLabel lblLogin = new JLabel("<HTML><U>Login</U></HTML>");
		lblLogin.setToolTipText("Login existing user");
		lblLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				frmDoctorAppointmentManager.dispose();
				
				LoginWindow loginWindow = new LoginWindow();
				loginWindow.frmLogin.setVisible(true);
				
			}
		});
		lblLogin.setBounds(200, 423, 46, 20);
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLogin.setForeground(Color.BLUE);
		lblLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JLabel lblSlash = new JLabel("/");
		lblSlash.setBounds(245, 423, 6, 20);
		lblSlash.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		//Implement label "Register".
		
		JLabel lblRegister = new JLabel("<HTML><U>Register</U></HTML>");
		lblRegister.setToolTipText("Register new user");
		lblRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				frmDoctorAppointmentManager.dispose();
				
				RegisterWindow registerWindow = new RegisterWindow();
				registerWindow.frmRegister.setVisible(true);
				
			}
		});
		lblRegister.setBounds(256, 423, 65, 20);
		lblRegister.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRegister.setForeground(Color.BLUE);
		lblRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frmDoctorAppointmentManager.getContentPane().setLayout(null);
		
		JLabel lblTitle = new JLabel("Doctor Appointment Manager");
		lblTitle.setForeground(Color.DARK_GRAY);
		lblTitle.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblTitle.setBounds(121, 0, 284, 36);
		frmDoctorAppointmentManager.getContentPane().add(lblTitle);
		
		JLabel lblHomeIcon = new JLabel("");
		lblHomeIcon.setBounds(153, 37, 306, 306);
		lblHomeIcon.setIcon(new ImageIcon("C:\\Users\\arias\\eclipse-workspace\\doctor-appointment-app\\resources\\welcome.png"));
		frmDoctorAppointmentManager.getContentPane().add(lblHomeIcon);
		
		JLabel lblWelcome = new JLabel("WELCOME");
		lblWelcome.setBounds(176, 349, 157, 47);
		lblWelcome.setFont(new Font("Trebuchet MS", Font.BOLD, 34));
		frmDoctorAppointmentManager.getContentPane().add(lblWelcome);
		frmDoctorAppointmentManager.getContentPane().add(lblLogin);
		frmDoctorAppointmentManager.getContentPane().add(lblSlash);
		frmDoctorAppointmentManager.getContentPane().add(lblRegister);
	}
}
