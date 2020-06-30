import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.Component;
import javax.swing.Box;

@SuppressWarnings("serial")
public class SuggestPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private Object[][] sData;
	private Object[][] dData; 
	private JComboBox<String> comboBox;
	private double cal;
	private Random rand = new Random();
	private DatabaseAPI db = new DatabaseAPI();
	/**
	 * Create the panel.
	 * 
	 * @throws SQLException
	 */
	public SuggestPanel(final CardLayout cl, final JPanel contP) throws SQLException {
		try {
			cal = this.getCal(MainFrame.getUserId());
			sData = db.getData("SELECT * FROM Sport_Cal");
			dData = db.getData("SELECT * FROM Recommend_Diet");
		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null, "無法連線資料庫。", "錯誤", JOptionPane.ERROR_MESSAGE);
		}
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {150, 100};
		gridBagLayout.rowHeights = new int[] {50, 0, 150, 0, 150};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("運動、飲食建議");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);
		
		//返回選單頁
		JButton btnNewButton = new JButton("回選單");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(contP, "1");
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 0;
		add(btnNewButton, gbc_btnNewButton);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_1 = new GridBagConstraints();
		gbc_verticalStrut_1.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_1.gridx = 0;
		gbc_verticalStrut_1.gridy = 1;
		add(verticalStrut_1, gbc_verticalStrut_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "\u6BCF\u65E5\u71DF\u990A\u651D\u53D6\u91CF", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridwidth = 2;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {80, 80, 80};
		gbl_panel.rowHeights = new int[] {40, 30, 30};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, 1.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0};
		panel.setLayout(gbl_panel);
		
		comboBox = new JComboBox<String>();
		comboBox.addItem("一般健身");
		comboBox.addItem("高碳水");
		comboBox.addItem("超高蛋白");
		comboBox.addItem("生酮");
		comboBox.setSelectedIndex(0);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 2;
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 0;
		panel.add(comboBox, gbc_comboBox);
		
		/*
		 * 確認comboBox選中項目，計算並將textField,textField_1,textField_2設為結果
		 */
		JButton btnNewButton_1 = new JButton("確認");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_1.setText(Math.round(Integer.parseInt(((String) dData[comboBox.getSelectedIndex()][1])) * 0.01 * cal * 0.25) + "g");
				textField.setText(Math.round(Integer.parseInt(((String) dData[comboBox.getSelectedIndex()][2])) * 0.01 * cal * 0.25) + "g");
				textField_2.setText(Math.round(Integer.parseInt(((String) dData[comboBox.getSelectedIndex()][3])) * 0.01 * cal * 0.11) + "g");
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 0;
		panel.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("蛋白質");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.SOUTH;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("碳水化合物");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.SOUTH;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 1;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("脂肪");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.SOUTH;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_3.gridx = 2;
		gbc_lblNewLabel_3.gridy = 1;
		panel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		//蛋白質結果
		textField = new JTextField();
		textField.setText("0");
		textField.setEditable(false);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 2;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		//碳水結果
		textField_1 = new JTextField();
		textField_1.setText("0");
		textField_1.setEditable(false);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 2;
		panel.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		//脂肪結果
		textField_2 = new JTextField();
		textField_2.setText("0");
		textField_2.setEditable(false);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 2;
		gbc_textField_2.gridy = 2;
		panel.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_2 = new GridBagConstraints();
		gbc_verticalStrut_2.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_2.gridx = 0;
		gbc_verticalStrut_2.gridy = 3;
		add(verticalStrut_2, gbc_verticalStrut_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "\u4ECA\u65E5\u63A8\u85A6\u904B\u52D5", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridwidth = 2;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 4;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] {240};
		gbl_panel_1.rowHeights = new int[] {30, 30};
		gbl_panel_1.columnWeights = new double[]{0.0};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0};
		panel_1.setLayout(gbl_panel_1);
		
		//接收推薦的運動資訊
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		int sRand = rand.nextInt(sData.length);
		textField_3.setText(String.format("%s  以時速%6s 可消耗%6s ", sData[sRand][1], sData[sRand][2], sData[sRand][3]));
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.insets = new Insets(0, 0, 5, 0);
		gbc_textField_3.gridx = 0;
		gbc_textField_3.gridy = 0;
		panel_1.add(textField_3, gbc_textField_3);
		
		//隨機選個新的運動給使用者
		JButton btnNewButton_2 = new JButton("換一個");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sRand = rand.nextInt(sData.length);
				textField_3.setText(String.format("%s  以時速%6s 可消耗%6s ", sData[sRand][1], sData[sRand][2], sData[sRand][3]));
			}
		});
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.gridx = 0;
		gbc_btnNewButton_2.gridy = 1;
		panel_1.add(btnNewButton_2, gbc_btnNewButton_2);
	}

	public double getCal(int id) {
		DatabaseAPI db = new DatabaseAPI();
		double cal = 0;
		try {
			int age = Integer.parseInt(db.getUserInfo(MainFrame.getUserId(), "Age"));
			double weight = Double.parseDouble(db.getUserInfo(MainFrame.getUserId(), "Weight"));
			double height = Double.parseDouble(db.getUserInfo(MainFrame.getUserId(), "Height"));
			if (db.getUserInfo(MainFrame.getUserId(), "Sex").equals("male")) {
				cal = (10 * weight) + (6.25 * height) - (5 * age) + 5;
			} else {
				cal = (10 * weight) + (6.25 * height) - (5 * age) - 161;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "無法連線資料庫。", "錯誤", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "發生錯誤。", "錯誤", JOptionPane.ERROR_MESSAGE);
		}
		return cal;
	}
}
