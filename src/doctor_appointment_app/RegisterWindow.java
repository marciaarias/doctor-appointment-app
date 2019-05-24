package doctor_appointment_app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class RegisterWindow {

	public JFrame frmRegister;
	private JTextField textFieldUsername;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldRepeat;

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
		frmRegister.setResizable(false);
		frmRegister.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\arias\\eclipse-workspace\\doctor-appointment-app\\resources\\register.png"));
		frmRegister.setTitle("Register - Doctor Appointment Manager");
		frmRegister.setBounds(100, 100, 472, 201);
		frmRegister.setLocationRelativeTo(null);
		frmRegister.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(165, 56, 68, 14);
		frmRegister.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("");
		passwordField.setBounds(229, 50, 191, 20);
		frmRegister.getContentPane().add(passwordField);
		
		JLabel lblRepeatPassword = new JLabel("Repeat password:");
		lblRepeatPassword.setBounds(126, 81, 89, 14);
		frmRegister.getContentPane().add(lblRepeatPassword);
		
		passwordFieldRepeat = new JPasswordField();
		passwordFieldRepeat.setToolTipText("");
		passwordFieldRepeat.setBounds(229, 75, 191, 20);
		frmRegister.getContentPane().add(passwordFieldRepeat);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(229, 117, 89, 23);
		frmRegister.getContentPane().add(btnRegister);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(331, 117, 89, 23);
		frmRegister.getContentPane().add(btnCancel);
	}

}
