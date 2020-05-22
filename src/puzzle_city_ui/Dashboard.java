package puzzle_city_ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import puzzle_city_client.Client;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Dashboard {

	public static JFrame frame;

	public 	Client client ;
	private int cID ;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Dashboard window = new Dashboard();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public Dashboard(Client socket,int cityID) {
		cID = cityID;
		client = socket;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(200, 100, 1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(50, 50, 900, 700);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnMenuDashboard = new JButton("Dashboard");
		btnMenuDashboard.setForeground(Color.WHITE);
		btnMenuDashboard.setBackground(Color.DARK_GRAY);
		btnMenuDashboard.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnMenuDashboard.setBounds(10, 64, 173, 23);
		panel.add(btnMenuDashboard);
		
		JButton btnMenuCityInfomation = new JButton("City Infomation");
		btnMenuCityInfomation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CityDetail ctDetail =	new CityDetail(client, cID);
				ctDetail.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnMenuCityInfomation.setBounds(10, 98, 173, 23);
		panel.add(btnMenuCityInfomation);
		
		JButton btnMenuTramwayStation = new JButton("Tramway Network");
		btnMenuTramwayStation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CityTramway windowCityTramway  = new CityTramway(client, cID);
				windowCityTramway.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnMenuTramwayStation.setBounds(10, 132, 173, 23);
		panel.add(btnMenuTramwayStation);
		
		JPanel panel_dashboard = new JPanel();
		panel_dashboard.setBounds(186, 64, 684, 574);
		panel.add(panel_dashboard);
		panel_dashboard.setLayout(null);
		
		JButton btnBackToList = new JButton("Back To List City");
		btnBackToList.setVisible(false);
		btnBackToList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CityList windowCityList  = new CityList(client);
				windowCityList.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnBackToList.setBounds(527, 11, 147, 23);
		panel_dashboard.add(btnBackToList);
		
		JLabel lblNewLabel = new JLabel("Puzzle City");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(214, 11, 197, 27);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Analyse");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(10, 540, 173, 25);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Carbon FootPrint");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_1.setBounds(10, 502, 173, 25);
		panel.add(btnNewButton_1);
	}
}
