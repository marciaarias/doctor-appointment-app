package doctor_appointment_app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.SwingConstants;

public class MainWindow {

	private JFrame frmMain;

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
		frmMain.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\arias\\eclipse-workspace\\doctor-appointment-app\\resources\\date.png"));
		frmMain.setTitle("Doctor Appointment Manager");
		frmMain.setBounds(100, 100, 777, 575);
		frmMain.setLocationRelativeTo(null);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMain.getContentPane().setLayout(null);
		
		JLabel lblWelcomeBack = new JLabel("Welcome back, Fulano");
		lblWelcomeBack.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblWelcomeBack.setBounds(7, 9, 426, 23);
		frmMain.getContentPane().add(lblWelcomeBack);
		
		JLabel lblLogout = new JLabel("<HTML><U>Logout</HTML></U>");
		lblLogout.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLogout.setForeground(Color.BLUE);
		lblLogout.setBounds(702, 11, 49, 21);
		lblLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frmMain.getContentPane().add(lblLogout);
		
		JComboBox comboBoxSelectDoctor = new JComboBox();
		comboBoxSelectDoctor.setBounds(322, 62, 179, 22);
		frmMain.getContentPane().add(comboBoxSelectDoctor);
		
		JLabel lblSelectDoctorIcon = new JLabel("Select Doctor:");
		lblSelectDoctorIcon.setIcon(new ImageIcon("C:\\Users\\arias\\eclipse-workspace\\doctor-appointment-app\\resources\\stethoscope.png"));
		lblSelectDoctorIcon.setBounds(207, 58, 115, 30);
		frmMain.getContentPane().add(lblSelectDoctorIcon);
		
		JButton btnAppointments = new JButton("");
		btnAppointments.setIcon(new ImageIcon("C:\\Users\\arias\\eclipse-workspace\\doctor-appointment-app\\resources\\appointment_large.png"));
		btnAppointments.setBounds(33, 186, 206, 206);
		frmMain.getContentPane().add(btnAppointments);
		
		JButton btnPatients = new JButton("");
		btnPatients.setIcon(new ImageIcon("C:\\Users\\arias\\eclipse-workspace\\doctor-appointment-app\\resources\\patient_large.png"));
		btnPatients.setBounds(278, 186, 206, 206);
		frmMain.getContentPane().add(btnPatients);
		
		JButton btnDoctors = new JButton("");
		btnDoctors.setIcon(new ImageIcon("C:\\Users\\arias\\eclipse-workspace\\doctor-appointment-app\\resources\\doctor_large.png"));
		btnDoctors.setBounds(520, 186, 206, 206);
		frmMain.getContentPane().add(btnDoctors);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(7, 41, 744, 23);
		frmMain.getContentPane().add(separator);
		
		JButton btnRefresh = new JButton("R");
		btnRefresh.setBounds(511, 62, 39, 23);
		frmMain.getContentPane().add(btnRefresh);
		
		JLabel lblAppointments = new JLabel("Appointments");
		lblAppointments.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAppointments.setBounds(84, 392, 106, 23);
		frmMain.getContentPane().add(lblAppointments);
		
		JLabel lblPatients = new JLabel("Patients");
		lblPatients.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPatients.setBounds(351, 392, 62, 23);
		frmMain.getContentPane().add(lblPatients);
		
		JLabel lblDoctors = new JLabel("Doctors");
		lblDoctors.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDoctors.setBounds(592, 392, 62, 23);
		frmMain.getContentPane().add(lblDoctors);
	}
}
