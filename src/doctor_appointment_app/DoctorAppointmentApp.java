package doctor_appointment_app;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.ImageIcon;

public class DoctorAppointmentApp {

	private JFrame frmDoctorAppointmentManager;

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
		frmDoctorAppointmentManager.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\arias\\eclipse-workspace\\doctor-appointment-app\\resources\\date.png"));
		frmDoctorAppointmentManager.setTitle("Doctor Appointment Manager");
		frmDoctorAppointmentManager.setBounds(100, 100, 572, 507);
		frmDoctorAppointmentManager.setLocationRelativeTo(null);
		frmDoctorAppointmentManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblExaminationIcon = new JLabel("");
		lblExaminationIcon.setBounds(153, 37, 306, 306);
		lblExaminationIcon.setIcon(new ImageIcon("C:\\Users\\arias\\eclipse-workspace\\doctor-appointment-app\\resources\\examination.png"));
		
		JLabel lblWelcome = new JLabel("WELCOME");
		lblWelcome.setBounds(176, 349, 157, 47);
		lblWelcome.setFont(new Font("Trebuchet MS", Font.BOLD, 34));
		
		JLabel lblLogin = new JLabel("<HTML><U>Login</HTML></U>");
		lblLogin.setBounds(200, 423, 46, 20);
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLogin.setForeground(Color.BLUE);
		lblLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JLabel lblSlash = new JLabel("/");
		lblSlash.setBounds(245, 423, 6, 20);
		lblSlash.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblRegister = new JLabel("<HTML><U>Register</HTML></U>");
		lblRegister.setBounds(256, 423, 65, 20);
		lblRegister.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRegister.setForeground(Color.BLUE);
		lblRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frmDoctorAppointmentManager.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Doctor Appointment Manager");
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblNewLabel.setBounds(121, 0, 284, 36);
		frmDoctorAppointmentManager.getContentPane().add(lblNewLabel);
		frmDoctorAppointmentManager.getContentPane().add(lblLogin);
		frmDoctorAppointmentManager.getContentPane().add(lblSlash);
		frmDoctorAppointmentManager.getContentPane().add(lblRegister);
		frmDoctorAppointmentManager.getContentPane().add(lblExaminationIcon);
		frmDoctorAppointmentManager.getContentPane().add(lblWelcome);
	}
}
