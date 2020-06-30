import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	private static int userId;
	private static JPanel menu, record, bodyRecord, recommend;

	public MainFrame(int user) throws SQLException {
		final JPanel contP = new JPanel();
		final CardLayout cl = new CardLayout();

		userId = user;
		
		menu = new MenuPanel(cl, contP);
		record = new RecordPanel(cl, contP);
		bodyRecord = new BodyRecordPanel(cl, contP);
		recommend = new SuggestPanel(cl, contP);

		contP.setLayout(cl);
		contP.add(menu, "1");
		contP.add(record, "2");
		contP.add(bodyRecord, "3");
		contP.add(recommend, "4");

		JMenuBar menuBar = new JMenuBar();
		JMenu jMenu = new JMenu();
		JMenuItem menuItem = new JMenuItem();
		JMenuItem recordItem = new JMenuItem();
		JMenuItem bodyRecordItem = new JMenuItem();
		JMenuItem recommendItem = new JMenuItem();
		class MenuListener implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cl.show(contP, "1");
				setTitle("Be Like Me-Menu");
			}

		}
		class RecordListener implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cl.show(contP, "2");
				setTitle("Be Like Me-Record");
			}

		}
		class BodyRecordListener implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setTitle("Be Like Me-BodyRecord");
			}

		}
		class RecommendListener implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cl.show(contP, "4");
				setTitle("Be Like Me-Recommend");
			}

		}
		menuItem.addActionListener(new MenuListener());
		recordItem.addActionListener(new RecordListener());
		bodyRecordItem.addActionListener(new BodyRecordListener());
		recommendItem.addActionListener(new RecommendListener());
		jMenu.add(menuItem);
		jMenu.add(recordItem);
		jMenu.add(bodyRecordItem);
		jMenu.add(recommendItem);
		jMenu.setPopupMenuVisible(true);
		menuBar.add(jMenu);
		contP.add(menuBar);

		super.setBounds(100, 100, 300, 450);
		super.setContentPane(contP);
		cl.show(contP, "1");
		super.setTitle("Be Like Me-Menu");
	}

	public static int getUserId() {
		return userId;
	}

	public static void setUserId(int user) {
		userId = user;
	}

	@Override
	public void setTitle(String title) {
		super.setTitle(title);
	}
}
