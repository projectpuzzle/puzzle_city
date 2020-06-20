package puzzle_city_ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.json.JSONException;
import org.json.JSONObject;

import puzzle_city_client.Client;
import puzzle_city_client_model.ApiEnum;
import puzzle_city_client_model.SendPackage;
import javax.swing.JCheckBox;
import javax.swing.JScrollBar;
import javax.swing.JSlider;
import javax.swing.JTextPane;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.UIManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.SystemColor;

public class ConfigSensorAir {

	public JFrame frame;
	private JTextField said;
	private int id;
	private String address;
	private int no2;
	private int pm10;
	private int o3;
	private boolean alert;
	private int alert_id;
	private boolean isActivated;
	private int counter;

	public Client client;// = new Client("127.0.0.1", 4000);
	private JTable table;
	JPanel panel_cityinfo;
	private JLabel errorM = new JLabel();
	private JTextField text1;
	private JTextField text2;
	private JTextField text3;
	private JSlider slider;
	private JSlider slider2;
	private JSlider slider3;
	private JSlider sliderTimer;
	private JCheckBox activatedCheckBox;
	private JLabel timeLeft;
	private JTextField text4;
	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private JButton buttonTimer;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 * 
	 * @wbp.parser.constructor
	 */

	public ConfigSensorAir(Client socket, int id, String address, int no2, int pm10, int o3, boolean alert,
			int alert_id, boolean isActivated) {
		this.id = id;
		this.address = address;
		this.no2 = no2;
		this.pm10 = pm10;
		this.o3 = o3;
		this.alert = alert;
		this.alert_id = alert_id;
		this.isActivated = isActivated;
		client = socket;
		initialize(id, address, no2, pm10, o3, alert);
	}

	public ConfigSensorAir() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int id, String address, int no2, int pm10, int o3, boolean alert) {
		setType(Type.UTILITY);
		panel_cityinfo = new JPanel();
		panel_cityinfo.setBackground(new Color(240, 240, 240));
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 499);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 664, 439);
		frame.getContentPane().add(panel);

		panel_cityinfo.setBounds(10, 42, 644, 407);
		panel.add(panel_cityinfo);
		panel_cityinfo.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Sensor Address :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(31, 14, 105, 14);
		panel_cityinfo.add(lblNewLabel_1);

		said = new JTextField();
		said.setBounds(122, 11, 315, 20);
		if (id != 0) {
			said.setText(address);

		}

		panel_cityinfo.add(said);
		said.setColumns(10);

		JButton btnCreate = new JButton("Update");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateSensorInfo();
			}
		});
		btnCreate.setBounds(83, 365, 89, 20);
		panel_cityinfo.add(btnCreate);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel();
			}
		});
		btnCancel.setBounds(444, 364, 89, 21);
		panel_cityinfo.add(btnCancel);

		table = new JTable();
		table.setBounds(51, 111, 1, 1);
		panel_cityinfo.add(table);

		JButton btnCreate_1 = new JButton("Delete");
		btnCreate_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		btnCreate_1.setBounds(269, 365, 89, 20);
		panel_cityinfo.add(btnCreate_1);

		JLabel lblNewLabel_1_1 = new JLabel("Configure threshold (in \u00B5g/m\u00B3) :");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(0, 165, 184, 14);

		panel_cityinfo.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("NO\u2082");
		lblNewLabel_1_2.setForeground(Color.DARK_GRAY);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2.setBounds(18, 190, 194, 14);
		panel_cityinfo.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("PM10\r\n");
		lblNewLabel_1_3.setForeground(Color.DARK_GRAY);
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_3.setBounds(31, 241, 179, 29);
		panel_cityinfo.add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_4 = new JLabel(" O\u2083");
		lblNewLabel_1_4.setForeground(Color.DARK_GRAY);
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_4.setBounds(33, 297, 179, 28);
		panel_cityinfo.add(lblNewLabel_1_4);

		JButton btnMore_1 = new JButton("show alert history");
		btnMore_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				AlertHistory a = new AlertHistory(client, id, address, no2, pm10, o3, alert, alert_id, isActivated);
				a.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnMore_1.setBounds(472, 10, 141, 23);
		panel_cityinfo.add(btnMore_1);

		JLabel lblNewLabel_1_1_2 = new JLabel("Sensor Status : \r\n");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_2.setBounds(0, 46, 112, 14);
		panel_cityinfo.add(lblNewLabel_1_1_2);

		activatedCheckBox = new JCheckBox("ON");
		activatedCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!activatedCheckBox.isSelected()) {
					btn1.setEnabled(false);
					btn2.setEnabled(false);
					btn3.setEnabled(false);
					buttonTimer.setEnabled(false);
					
					slider.setValue(0);
					slider2.setValue(0);
					slider3.setValue(0);
					sliderTimer.setValue(0);
					text1.setText("0");
					text2.setText("0");
					text3.setText("0");
					text4.setText("0");
				
				} else {
					btn1.setEnabled(true);
					btn2.setEnabled(true);
					btn3.setEnabled(true);
					buttonTimer.setEnabled(true);


				}
				//e.getSource() == activatedCheckBox
			}
		});
		activatedCheckBox.setSelected(isActivated);
		activatedCheckBox.setBounds(122, 42, 97, 23);
		panel_cityinfo.add(activatedCheckBox);

		slider = new JSlider();
		slider.setFont(new Font("Tahoma", Font.BOLD, 12));
		slider.setBackground(UIManager.getColor("Button.background"));
		slider.setEnabled(false);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {

			}
		});
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(100);
		slider.setMaximum(500);
		slider.setPaintLabels(true);
		slider.setValue(70);
		slider.setBounds(224, 185, 208, 45);
		panel_cityinfo.add(slider);
		
				JLabel lblNewLabel_1_1_2_1 = new JLabel("Set up timer for results (in seconds) :");
				lblNewLabel_1_1_2_1.setBounds(10, 71, 184, 14);
				panel_cityinfo.add(lblNewLabel_1_1_2_1);
				lblNewLabel_1_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 11));

		slider2 = new JSlider();
		slider2.setBackground(UIManager.getColor("Button.background"));
		slider2.setFont(new Font("Tahoma", Font.BOLD, 12));
		slider2.setForeground(Color.WHITE);
		slider2.setEnabled(false);
		slider2.setPaintTicks(true);
		slider2.setPaintLabels(true);
		slider2.setMajorTickSpacing(20);
		slider2.setValue(40);
		slider2.setBounds(222, 241, 208, 45);
		panel_cityinfo.add(slider2);

		slider3 = new JSlider();
		slider3.setFont(new Font("Tahoma", Font.BOLD, 12));
		slider3.setBackground(UIManager.getColor("Button.background"));
		slider3.setEnabled(false);
		slider3.setMajorTickSpacing(50);
		slider3.setMaximum(300);
		slider3.setPaintLabels(true);
		slider3.setPaintTicks(true);
		slider3.setValue(10);
		slider3.setBounds(222, 297, 208, 45);
		panel_cityinfo.add(slider3);

		sliderTimer = new JSlider();
		sliderTimer.setForeground(Color.WHITE);
		sliderTimer.setFont(new Font("Tahoma", Font.BOLD, 12));
		sliderTimer.setBackground(UIManager.getColor("Button.background"));
		sliderTimer.setEnabled(false);
		sliderTimer.setPaintTicks(true);
		sliderTimer.setMaximum(30);
		sliderTimer.setPaintLabels(true);
		sliderTimer.setMajorTickSpacing(5);
		sliderTimer.setValue(70);
		sliderTimer.setBounds(203, 84, 231, 52);
		panel_cityinfo.add(sliderTimer);

		text1 = new JTextField();
		text1.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE)) {
					e.consume();
					
				}
			}
		});
		text1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		text1.setHorizontalAlignment(SwingConstants.CENTER);
		text1.setText(String.valueOf(no2));
		text1.setColumns(10);
		text1.setBounds(444, 187, 37, 20);
		panel_cityinfo.add(text1);
		int a = Integer.parseInt(text1.getText());
		slider.setValue(a);
		text2 = new JTextField();
		text2.addKeyListener(new KeyAdapter() {
		
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE)) {
					e.consume();
					
				}
			}
		});
		text2.setText(String.valueOf(pm10));
		text2.setHorizontalAlignment(SwingConstants.CENTER);
		text2.setColumns(10);
		text2.setBounds(444, 246, 36, 20);
		panel_cityinfo.add(text2);
		int b = Integer.parseInt(text2.getText());
		slider2.setValue(b);

		text3 = new JTextField();
		text3.addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE)) {
					e.consume();
					
				}
			}
		});
		text3.setText(String.valueOf(o3));
		text3.setHorizontalAlignment(SwingConstants.CENTER);
		text3.setColumns(10);
		text3.setBounds(444, 302, 36, 20);
		panel_cityinfo.add(text3);
		int c = Integer.parseInt(text3.getText());
		slider3.setValue(c);
		btn1 = new JButton("set value\r\n");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int d = Integer.parseInt(text1.getText());
				
				if (sliderLengthNO2(d)) {
					JOptionPane.showMessageDialog(frame, "excessive value !");
					text1.setText("0");
					slider.setValue(0);
				} else if (sliderLength2(d)) {
					JOptionPane.showMessageDialog(frame, "Impossible value !");
					text1.setText("0");
					slider.setValue(0);
				} 
				else {
					slider.setValue(d);
				}
			}
		});

		btn1.setBounds(489, 187, 91, 23);
		panel_cityinfo.add(btn1);

		 btn2 = new JButton("set value");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int d = Integer.parseInt(text2.getText());
				if (sliderLengthPM10(d)) {
					JOptionPane.showMessageDialog(frame, "excessive value !");
					text2.setText("0");
					slider2.setValue(0);
				} else if (sliderLength2(d)) {
					JOptionPane.showMessageDialog(frame, "impossible value !");
					text2.setText("0");
					slider2.setValue(0);

				} else {
					
					slider2.setValue(d);
				}
			}
		});
		btn2.setBounds(489, 245, 91, 23);
		panel_cityinfo.add(btn2);

		 btn3 = new JButton("set value");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int d = Integer.parseInt(text3.getText());
				if (sliderLengthO3(d)) {
					JOptionPane.showMessageDialog(frame, "excessive value !");
					text3.setText("0");
					slider3.setValue(0);
				} else if (sliderLength2(d)) {
					JOptionPane.showMessageDialog(frame, "impossible value !");
					text3.setText("0");
					slider3.setValue(0);

				} else {
					slider3.setValue(d);
				}
			}
		});
		btn3.setBounds(490, 301, 91, 23);
		panel_cityinfo.add(btn3);

		JLabel lblNewLabel_1_1_1 = new JLabel("Remaining time for next release");
		lblNewLabel_1_1_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1_1.setBounds(214, 136, 188, 37);
		panel_cityinfo.add(lblNewLabel_1_1_1);

		 buttonTimer = new JButton("set value");
		buttonTimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStartMouseClicked(e);

			}
		});
		buttonTimer.setBounds(514, 95, 98, 23);
		panel_cityinfo.add(buttonTimer);

		timeLeft = new JLabel(".");
		timeLeft.setForeground(Color.BLACK);
		timeLeft.setHorizontalAlignment(SwingConstants.CENTER);
		timeLeft.setFont(new Font("Tahoma", Font.BOLD, 13));
		timeLeft.setBounds(393, 146, 44, 14);
		panel_cityinfo.add(timeLeft);

		text4 = new JTextField();
		text4.addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE)) {
					e.consume();
					
				}
			}
		});
		text4.setText("0");
		text4.setHorizontalAlignment(SwingConstants.CENTER);
		text4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		int e = Integer.parseInt(text4.getText());
		text4.setColumns(10);
		text4.setBounds(456, 95, 37, 20);
		panel_cityinfo.add(text4);

		JLabel lblNewLabel = new JLabel("Air Quality Sensor Details");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(214, 11, 240, 27);
		panel.add(lblNewLabel);

	}

	void simulate() {
		this.no2 = (int) (Math.random() * 500);
		this.pm10 = (int) (Math.random() * 100);
		this.o3 = (int) (Math.random() * 300);
		this.alert = this.no2 >= 400 || this.pm10 >= 80 || this.o3 >= 240;

		// lblNewLabel_1_1_1.setVisible(alert);
//		text1.setText(String.valueOf(no2));
//		text2.setText(String.valueOf(pm10));
//		text3.setText(String.valueOf(o3));
//		slider.setValue(no2);
//		slider2.setValue(pm10);
//		slider3.setValue(o3);

	}

	private void setType(Type utility) {
		// TODO Auto-generated method stub

	}

	private void setDataToField(JSONObject res) {
		// TODO Auto-generated method stub
		try {
			said.setText(res.getString("address"));
//			txtHeight.setText(String.valueOf( res.getDouble("Height")));
//			txtWidth.setText(String.valueOf( res.getDouble("Width")));
//			txtLat.setText(String.valueOf( res.getDouble("CenterLat")));
//			txtLong.setText(String.valueOf( res.getDouble("CenterLong")));
//			txtMapZoom.setText(String.valueOf( res.getInt("MapZoom")));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void updateSensorInfo() {
		try {
			final String address = said.getText();
			if (!isNotEmpty(address)) {
				JOptionPane.showMessageDialog(frame, "Empty field !");

				return;
			}
			if (isInvalidData(address)) {
				JOptionPane.showMessageDialog(frame, "Invalid data !");

				return;
			}
			if (checkLength(address)) {
				JOptionPane.showMessageDialog(frame, "Too long !");

				return;
			}

			client.setResponseData(null);
			JSONObject bodyItem = new JSONObject();
			bodyItem.put("id", "" + this.id);
			bodyItem.put("address", "" + said.getText());
			bodyItem.put("no2", "" + slider.getValue());
			bodyItem.put("pm10", "" + slider2.getValue());
			bodyItem.put("o3", "" + slider3.getValue());
			bodyItem.put("alert", alert);
			bodyItem.put("alert_id", alert_id);
			bodyItem.put("isActivated", activatedCheckBox.isSelected());

			SendPackage sendPa = new SendPackage();
			sendPa.setApi(ApiEnum.SENSORAIR_UPDATE);
			sendPa.setBody(bodyItem);
			client.setSendP(sendPa);

			JSONObject res = null;

			while (res == null) {
				// res = client.getResponseData();
				res = client.getResponseData();
				System.out.println("wait res:" + res);
				if (res != null) {
					// if success

					boolean sMess = res.getBoolean("success");
					if (sMess) {
						errorM.setText(res.getString("msg"));
						cancel();
					} else {
						errorM.setText("Error :" + res.getString("msg"));
					}
					System.out.println("tra ve:" + res.toString());
				}
			}

//			getSensorInfo();

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void cancel() {
		SensorAirList sa = new SensorAirList(client);
		sa.frame.setVisible(true);
		frame.dispose();
	}

	public void fill() {
		int i = 0;
		try {
			while (i <= 100) {
				// progressBar.setValue(i + 10);
				Thread.sleep(1000);
				i += 20;
			}
		} catch (Exception e) {
		}
	}

	void delete() {

		try {
			client.setResponseData(null);
			JSONObject bodyItem = new JSONObject();
			bodyItem.put("id", "" + this.id);
			bodyItem.put("alert_id", alert_id);
			SendPackage sendPa = new SendPackage();
			sendPa.setApi(ApiEnum.SENSORAIR_DELETE);
			sendPa.setBody(bodyItem);
			client.setSendP(sendPa);

			JSONObject res = null;
			while (res == null) {
				res = client.getResponseData();
				// chof
				System.out.println("wait res:" + res);

				if (res != null) {
					// if success

					boolean sMess = res.getBoolean("success");
					if (sMess) {
						errorM.setText(res.getString("msg"));
						cancel();
					} else {
						errorM.setText("Error :" + res.getString("msg"));
					}
					System.out.println("tra ve:" + res.toString());
				}
			}

//			getSensorInfo();

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private boolean isNotEmpty(String text) {
		return text != null & !text.isEmpty();
		
	}
	

	private boolean isInvalidData(String text) {
		Pattern p = Pattern.compile("[^a-z0-9- ]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(text);
		return m.find();

	}

	private boolean sliderLengthPM10(int a) {
		return a > 100;
	}

	private boolean sliderLengthO3(int a) {
		return a > 300;
	}

	private boolean sliderLengthNO2(int a) {
		return a > 500;
	}

	private boolean sliderLength3(int c) {
		return c > 30;
	}

	private boolean sliderLength2(int b) {
		return b < 0;
	}

	private boolean checkLength(String text) {
		return text.length() >= 15;
	}

//	public void setPanelX(JPanel panelX) {
//		this.panelX = panelX;
//		this.panelX.setVisible(!activatedCheckBox.isSelected());
//	}

	private void btnStartMouseClicked(ActionEvent evt) {
		int d = Integer.parseInt(text4.getText());
		Timer timer = new Timer();

		if (sliderLength3(d)) {
			JOptionPane.showMessageDialog(frame, "excessive value !");
			text4.setText("0");
			sliderTimer.setValue(0);
			timer.cancel();
		} else if (sliderLength2(d)) {
			JOptionPane.showMessageDialog(frame, "Impossible value !");
			text4.setText("0");
			sliderTimer.setValue(0);
			timer.cancel();
		}

		else {
			sliderTimer.setValue(d);
		}

		counter = d;
		TimerTask task = new TimerTask() {
			public void run() {
				timeLeft.setText(Integer.toString(counter));
				counter--;
				if (counter == -1) {
//					timer.cancel();
					simulate();

					counter = d;

				}
			}
		};
		timer.scheduleAtFixedRate(task, 1000, 1000);
	}
}
