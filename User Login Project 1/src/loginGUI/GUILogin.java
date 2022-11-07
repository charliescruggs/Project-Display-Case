package loginGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class GUILogin implements ActionListener
{
	
	private static JLabel userLabel;
	private static JTextField userText;
	private static JLabel passwordLabel;
	private static JPasswordField passwordText;
	private static JButton button;
	private static JLabel success;
	
	
	public static void main (String[] args)
	{
		JPanel panel = new JPanel();
		JFrame frame = new JFrame();
		
		frame.setSize(350,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);	
		
		panel.setLayout(null);
		
		userLabel = new JLabel("User");
		userLabel.setBounds(10,20,80,25);
		panel.add(userLabel);
		
		userText = new JTextField(20);
		userText.setBounds(100,20,165,25);
		panel.add(userText);
		
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10,50,80,25);
		panel.add(passwordLabel);
		
		passwordText = new JPasswordField();
		passwordText.setBounds( 100,50,165,25);
		panel.add(passwordText);
		
		button = new JButton("Login");
		button.setBounds(10,80,80,25);
		button.addActionListener(new GUILogin());
		panel.add(button);
		
		success = new JLabel("");
		success.setBounds(10,110,300,25);
		panel.add(success);
		
		frame.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String user = userText.getText();
		String password = passwordText.getText();
		
		//Instead of printing user and pass, we can store those values if create new account is true
		System.out.println(user + ", " + password);
		
		if(user.equals("user") && password.equals("pass"))
		{
			success.setText("Login Successful");
		}
		
		else {
			success.setText("");
		}
		
	}

}
