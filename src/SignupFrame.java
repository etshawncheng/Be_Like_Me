import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import javax.swing.ButtonGroup;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import java.awt.event.ActionEvent;

public class SignupFrame extends JFrame {

	private JPanel contentPane;
	private JTextField heightTextField, weightTextField, ageTextField, nameTextField;
	private JPanel panel;
	private JButton resetButton, confirmButton;
	private JLabel lblNewLabel_1;
	private DatabaseAPI db;
	private Date dNow;
	private JRadioButton male, female;

	/**
	 * Create the frame.
	 */
	public SignupFrame() {
		setTitle("Sign up");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.rowHeights = new int[] { 40, 200, 40 };
		gbl_contentPane.columnWidths = new int[] { 125, 125 };
		contentPane.setLayout(gbl_contentPane);

		lblNewLabel_1 = new JLabel("基本資料");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 2;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);

		panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridwidth = 2;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.rowHeights = new int[] { 40, 40, 40, 40, 40 };
		gbl_panel.columnWidths = new int[] { 100, 150 };
		panel.setLayout(gbl_panel);

		JLabel lblNewLabel_4 = new JLabel("性別");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 0;
		panel.add(lblNewLabel_4, gbc_lblNewLabel_4);

		JPanel sexPanel = new JPanel();
		GridBagConstraints gbc_sexPanel = new GridBagConstraints();
		gbc_sexPanel.anchor = GridBagConstraints.WEST;
		gbc_sexPanel.insets = new Insets(0, 0, 5, 0);
		gbc_sexPanel.gridx = 1;
		gbc_sexPanel.gridy = 0;
		panel.add(sexPanel, gbc_sexPanel);

		male = new JRadioButton("男");
		female = new JRadioButton("女");
		ButtonGroup group = new ButtonGroup();
		group.add(male);
		group.add(female);
		sexPanel.add(male);
		sexPanel.add(female);
		male.setSelected(true);

		JLabel ageLabel = new JLabel("年紀");
		GridBagConstraints gbc_ageLabel = new GridBagConstraints();
		gbc_ageLabel.anchor = GridBagConstraints.EAST;
		gbc_ageLabel.insets = new Insets(0, 0, 5, 5);
		gbc_ageLabel.gridx = 0;
		gbc_ageLabel.gridy = 1;
		panel.add(ageLabel, gbc_ageLabel);

		ageTextField = new JTextField();
		GridBagConstraints gbc_ageTextField = new GridBagConstraints();
		gbc_ageTextField.anchor = GridBagConstraints.WEST;
		gbc_ageTextField.insets = new Insets(0, 0, 5, 0);
		gbc_ageTextField.gridx = 1;
		gbc_ageTextField.gridy = 1;
		panel.add(ageTextField, gbc_ageTextField);
		ageTextField.setColumns(10);
		
		JLabel nameLabel = new JLabel("姓名");
		GridBagConstraints gbc_nameLabel = new GridBagConstraints();
		gbc_nameLabel.anchor = GridBagConstraints.EAST;
		gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nameLabel.gridx = 0;
		gbc_nameLabel.gridy = 2;
		panel.add(nameLabel, gbc_nameLabel);

		nameTextField = new JTextField();
		GridBagConstraints gbc_nameTextField = new GridBagConstraints();
		gbc_nameTextField.anchor = GridBagConstraints.WEST;
		gbc_nameTextField.insets = new Insets(0, 0, 5, 0);
		gbc_nameTextField.gridx = 1;
		gbc_nameTextField.gridy = 2;
		panel.add(nameTextField, gbc_nameTextField);
		nameTextField.setColumns(10);

		JLabel heightLabel = new JLabel("身高");
		GridBagConstraints gbc_heightLabel = new GridBagConstraints();
		gbc_heightLabel.anchor = GridBagConstraints.EAST;
		gbc_heightLabel.insets = new Insets(0, 0, 5, 5);
		gbc_heightLabel.gridx = 0;
		gbc_heightLabel.gridy = 3;
		panel.add(heightLabel, gbc_heightLabel);

		heightTextField = new JTextField();
		GridBagConstraints gbc_heightTextField = new GridBagConstraints();
		gbc_heightTextField.insets = new Insets(0, 0, 5, 0);
		gbc_heightTextField.anchor = GridBagConstraints.WEST;
		gbc_heightTextField.gridx = 1;
		gbc_heightTextField.gridy = 3;
		panel.add(heightTextField, gbc_heightTextField);
		heightTextField.setColumns(10);

		JLabel weightLabel = new JLabel("體重");
		GridBagConstraints gbc_weightLabel = new GridBagConstraints();
		gbc_weightLabel.anchor = GridBagConstraints.EAST;
		gbc_weightLabel.insets = new Insets(0, 0, 5, 5);
		gbc_weightLabel.gridx = 0;
		gbc_weightLabel.gridy = 4;
		panel.add(weightLabel, gbc_weightLabel);

		weightTextField = new JTextField();
		GridBagConstraints gbc_weightTextField = new GridBagConstraints();
		gbc_weightTextField.insets = new Insets(0, 0, 5, 0);
		gbc_weightTextField.anchor = GridBagConstraints.WEST;
		gbc_weightTextField.gridx = 1;
		gbc_weightTextField.gridy = 4;
		panel.add(weightTextField, gbc_weightTextField);
		weightTextField.setColumns(10);

		resetButton = new JButton("重置");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				male.setSelected(true);
				ageTextField.setText("0");
				nameTextField.setText("");
				heightTextField.setText("0");
				weightTextField.setText("0");
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 2;
		contentPane.add(resetButton, gbc_btnNewButton);

		confirmButton = new JButton("確認");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db = new DatabaseAPI();
				dNow = new Date();
				String name = nameTextField.getText();
				String sex="female";
				if (male.isSelected()) {
					sex = "male";
				}
				try {
					int age = Integer.parseInt(ageTextField.getText());
					double height = Double.parseDouble(heightTextField.getText());
					double weight = Double.parseDouble(weightTextField.getText());
					if (!name.equals("")) {
						boolean signup = db.signupUser(name);
						if (signup) {
							db.updateInfo(sex, db.getUserID(name), dNow, height, weight, age);
							JOptionPane.showMessageDialog(null, "註冊成功", "提示", JOptionPane.INFORMATION_MESSAGE);
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, "註冊失敗。", "警告", JOptionPane.WARNING_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "請填入資料", "警告", JOptionPane.WARNING_MESSAGE);
					}
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "無法連線資料庫。", "錯誤", JOptionPane.ERROR_MESSAGE);
					ex.getMessage();
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "請輸入數字。", "錯誤", JOptionPane.ERROR_MESSAGE);
					ex.getMessage();
				} catch (NullPointerException ex) {
					JOptionPane.showMessageDialog(null, "請輸入內容。", "錯誤", JOptionPane.ERROR_MESSAGE);
					ex.getMessage();
				}
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 2;
		contentPane.add(confirmButton, gbc_btnNewButton_1);
	}

}
