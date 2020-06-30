import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private DatabaseAPI db;

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setTitle("Be Like Me");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {75, 150};
		gbl_contentPane.rowHeights = new int[] {50, 50, 50, 50};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setFont(new Font("微軟正黑體", Font.PLAIN, 24));
		GridBagConstraints gbc_lblLogin = new GridBagConstraints();
		gbc_lblLogin.insets = new Insets(0, 0, 5, 0);
		gbc_lblLogin.gridwidth = 2;
		gbc_lblLogin.gridx = 0;
		gbc_lblLogin.gridy = 0;
		contentPane.add(lblLogin, gbc_lblLogin);
		
		JLabel lblName = new JLabel("Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 1;
		contentPane.add(lblName, gbc_lblName);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(15);
		
		JButton button = new JButton("確認");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db = new DatabaseAPI();
				try {
					int id = db.getUserID(textField.getText());
					if(id != -1) {
						JFrame f = new MainFrame(id);
						f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						f.setVisible(true);
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "您尚未註冊帳號", "提示", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.gridwidth = 2;
		gbc_button.gridx = 0;
		gbc_button.gridy = 2;
		contentPane.add(button, gbc_button);
		
		JButton button_1 = new JButton("註冊");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame f = new SignupFrame();
				f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				f.setVisible(true);
			}
		});
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.gridwidth = 2;
		gbc_button_1.gridx = 0;
		gbc_button_1.gridy = 3;
		contentPane.add(button_1, gbc_button_1);
	}

}
