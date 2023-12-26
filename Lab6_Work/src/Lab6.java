import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JLayeredPane;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Point;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Lab6 implements NetworkRequestCallback<List<Forecast>>, PreferenceObserver {

	private JFrame frmWeather;
	private JLayeredPane layeredPane1;
	private JLayeredPane layeredPane2;
	private JLayeredPane layeredPane3;
	private ForecastAdapter adapter;
	private ListView listView;
	private List<Forecast> forecastList;
	private JTextField textFieldCity;
	private boolean preferencesModified;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lab6 window = new Lab6();
					window.frmWeather.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Lab6() {
		initialize();
		adapter = new ForecastAdapter();
		listView = new ListView();
		listView.setAdapter(adapter);
		refreshForecast();
		Preferences.getInstance().registerObserver(this);
		preferencesModified = false;
		
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWeather = new JFrame();
		frmWeather.setTitle("Weather");
		frmWeather.setBounds(100, 100, 952, 657);
		frmWeather.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWeather.getContentPane().setLayout(null);
		
		layeredPane1 = new JLayeredPane();
		layeredPane1.setBounds(0, 0, 300, 600);
		frmWeather.getContentPane().add(layeredPane1);
		
		JLabel lblLocation = new JLabel("Location");
		layeredPane1.setLayer(lblLocation, 0);
		lblLocation.setBackground(Color.BLUE);
		lblLocation.setForeground(Color.BLACK);
		lblLocation.setHorizontalAlignment(SwingConstants.LEFT);
		lblLocation.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLocation.setBounds(10, 0, 240, 50);
		layeredPane1.add(lblLocation);
		
		JLayeredPane layeredPane11 = new JLayeredPane();
		layeredPane11.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				goToDetailsScreen(0);
			}
		});
		layeredPane11.setBounds(0, 50, 300, 150);
		layeredPane1.add(layeredPane11);
		
		JLabel lblLabel11_1 = new JLabel("Date 0");
		lblLabel11_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLabel11_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblLabel11_1.setBounds(0, 0, 300, 25);
		layeredPane11.add(lblLabel11_1);
		
		JLabel lblLabel11_2 = new JLabel("Image 0");
		lblLabel11_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblLabel11_2.setBounds(10, 25, 80, 80);
		layeredPane11.add(lblLabel11_2);
		
		JLabel lblLabel11_3 = new JLabel("Desc 0");
		lblLabel11_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLabel11_3.setBounds(10, 105, 140, 45);
		layeredPane11.add(lblLabel11_3);
		
		JLabel lblLabel11_4 = new JLabel("HT0");
		lblLabel11_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblLabel11_4.setFont(new Font("Tahoma", Font.PLAIN, 36));
		lblLabel11_4.setBounds(150, 25, 150, 75);
		layeredPane11.add(lblLabel11_4);
		
		JLabel lblLabel11_5 = new JLabel("LT0");
		lblLabel11_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblLabel11_5.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblLabel11_5.setBounds(150, 100, 150, 50);
		layeredPane11.add(lblLabel11_5);
		
		JSeparator separator11 = new JSeparator();
		separator11.setBounds(0, 200, 300, 5);
		layeredPane1.add(separator11);
		
		JLayeredPane layeredPane12 = new JLayeredPane();
		layeredPane12.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				goToDetailsScreen(1);
			}
		});
		layeredPane12.setBounds(0, 200, 300, 80);
		layeredPane1.add(layeredPane12);
		
		JLabel lblLabel12_1 = new JLabel("Image 1");
		lblLabel12_1.setBounds(5, 5, 70, 70);
		layeredPane12.add(lblLabel12_1);
		
		JLabel lblLabel12_2 = new JLabel("Date 1");
		lblLabel12_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLabel12_2.setBounds(80, 20, 100, 20);
		layeredPane12.add(lblLabel12_2);
		
		JLabel lblLabel12_3 = new JLabel("Desc 1");
		lblLabel12_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLabel12_3.setBounds(80, 40, 100, 20);
		layeredPane12.add(lblLabel12_3);
		
		JLabel lblLabel12_4 = new JLabel("HT1");
		lblLabel12_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblLabel12_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLabel12_4.setBounds(180, 20, 60, 40);
		layeredPane12.add(lblLabel12_4);
		
		JLabel lblLabel12_5 = new JLabel("LT1");
		lblLabel12_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLabel12_5.setBounds(240, 20, 60, 40);
		layeredPane12.add(lblLabel12_5);
		
		JLayeredPane layeredPane13 = new JLayeredPane();
		layeredPane13.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				goToDetailsScreen(2);
			}
		});
		layeredPane13.setBounds(0, 280, 300, 80);
		layeredPane1.add(layeredPane13);
		
		JLabel lblLabel13_1 = new JLabel("Image 2");
		lblLabel13_1.setBounds(5, 5, 70, 70);
		layeredPane13.add(lblLabel13_1);
		
		JLabel lblLabel13_2 = new JLabel("Date 2");
		lblLabel13_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLabel13_2.setBounds(80, 20, 100, 20);
		layeredPane13.add(lblLabel13_2);
		
		JLabel lblLabel13_3 = new JLabel("Desc 2");
		lblLabel13_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLabel13_3.setBounds(80, 40, 100, 20);
		layeredPane13.add(lblLabel13_3);
		
		JLabel lblLabel13_4 = new JLabel("HT2");
		lblLabel13_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblLabel13_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLabel13_4.setBounds(180, 20, 60, 40);
		layeredPane13.add(lblLabel13_4);
		
		JLabel lblLabel13_5 = new JLabel("LT2");
		lblLabel13_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLabel13_5.setBounds(240, 20, 60, 40);
		layeredPane13.add(lblLabel13_5);
		
		JLayeredPane layeredPane14 = new JLayeredPane();
		layeredPane14.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				goToDetailsScreen(3);
			}
		});
		layeredPane14.setBounds(0, 360, 300, 80);
		layeredPane1.add(layeredPane14);
		
		JLabel lblLabel14_1 = new JLabel("Image 3");
		lblLabel14_1.setBounds(5, 5, 70, 70);
		layeredPane14.add(lblLabel14_1);
		
		JLabel lblLabel14_2 = new JLabel("Date 3");
		lblLabel14_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLabel14_2.setBounds(80, 20, 100, 20);
		layeredPane14.add(lblLabel14_2);
		
		JLabel lblLabel14_3 = new JLabel("Desc 3");
		lblLabel14_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLabel14_3.setBounds(80, 40, 100, 20);
		layeredPane14.add(lblLabel14_3);
		
		JLabel lblLabel14_4 = new JLabel("HT3");
		lblLabel14_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblLabel14_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLabel14_4.setBounds(180, 20, 60, 40);
		layeredPane14.add(lblLabel14_4);
		
		JLabel lblLabel14_5 = new JLabel("LT3");
		lblLabel14_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLabel14_5.setBounds(240, 20, 60, 40);
		layeredPane14.add(lblLabel14_5);
		
		JLayeredPane layeredPane15 = new JLayeredPane();
		layeredPane15.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				goToDetailsScreen(4);
			}
		});
		layeredPane15.setBounds(0, 440, 300, 80);
		layeredPane1.add(layeredPane15);
		
		JLabel lblLabel15_1 = new JLabel("Image 4");
		lblLabel15_1.setBounds(5, 5, 70, 70);
		layeredPane15.add(lblLabel15_1);
		
		JLabel lblLabel15_2 = new JLabel("Date 4");
		lblLabel15_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLabel15_2.setBounds(80, 20, 100, 20);
		layeredPane15.add(lblLabel15_2);
		
		JLabel lblLabel15_3 = new JLabel("Desc 4");
		lblLabel15_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLabel15_3.setBounds(80, 40, 100, 20);
		layeredPane15.add(lblLabel15_3);
		
		JLabel lblLabel15_4 = new JLabel("HT4");
		lblLabel15_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblLabel15_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLabel15_4.setBounds(180, 20, 60, 40);
		layeredPane15.add(lblLabel15_4);
		
		JLabel lblLabel15_5 = new JLabel("LT4");
		lblLabel15_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLabel15_5.setBounds(240, 20, 60, 40);
		layeredPane15.add(lblLabel15_5);
		
		JSeparator separator12 = new JSeparator();
		separator12.setBounds(0, 280, 300, 5);
		layeredPane1.add(separator12);
		
		JSeparator separator13 = new JSeparator();
		separator13.setBounds(0, 360, 300, 5);
		layeredPane1.add(separator13);
		
		JSeparator separator14 = new JSeparator();
		separator14.setBounds(0, 440, 300, 5);
		layeredPane1.add(separator14);
		
		JSeparator separator15 = new JSeparator();
		separator15.setBounds(0, 520, 300, 5);
		layeredPane1.add(separator15);
		
		layeredPane2 = new JLayeredPane();
		layeredPane2.setBounds(310, 0, 300, 600);
		frmWeather.getContentPane().add(layeredPane2);
		
		JLabel lblLabel21_1 = new JLabel("High Temperature");
		lblLabel21_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLabel21_1.setBounds(10, 150, 190, 50);
		layeredPane2.add(lblLabel21_1);
		
		JLabel lblLabel21_2 = new JLabel("HT");
		lblLabel21_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLabel21_2.setBounds(200, 150, 100, 50);
		layeredPane2.add(lblLabel21_2);
		
		JLabel lblLabel22_1 = new JLabel("Low Temperature");
		lblLabel22_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLabel22_1.setBounds(10, 200, 190, 50);
		layeredPane2.add(lblLabel22_1);
		
		JLabel lblLabel22_2 = new JLabel("LT");
		lblLabel22_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLabel22_2.setBounds(200, 200, 100, 50);
		layeredPane2.add(lblLabel22_2);
		
		JLabel lblLabel23_1 = new JLabel("Wind Speed");
		lblLabel23_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLabel23_1.setBounds(10, 250, 190, 50);
		layeredPane2.add(lblLabel23_1);
		
		JLabel lblLabel23_2 = new JLabel("WS");
		lblLabel23_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLabel23_2.setBounds(200, 250, 100, 50);
		layeredPane2.add(lblLabel23_2);
		
		JLabel lblLabel24_1 = new JLabel("Probability of Precipitation");
		lblLabel24_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLabel24_1.setBounds(10, 300, 190, 50);
		layeredPane2.add(lblLabel24_1);
		
		JLabel lblLabel24_2 = new JLabel("PoP");
		lblLabel24_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLabel24_2.setBounds(200, 300, 100, 50);
		layeredPane2.add(lblLabel24_2);
		
		JLabel lblLabel25_1 = new JLabel("Accumulate Precipitation");
		lblLabel25_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLabel25_1.setBounds(10, 350, 190, 50);
		layeredPane2.add(lblLabel25_1);
		
		JLabel lblLabel25_2 = new JLabel("AP");
		lblLabel25_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLabel25_2.setBounds(200, 350, 100, 50);
		layeredPane2.add(lblLabel25_2);
		
		JLabel lblLabel26_1 = new JLabel("Humidity");
		lblLabel26_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLabel26_1.setBounds(10, 400, 190, 50);
		layeredPane2.add(lblLabel26_1);
		
		JLabel lblLabel26_2 = new JLabel("H");
		lblLabel26_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLabel26_2.setBounds(200, 400, 100, 50);
		layeredPane2.add(lblLabel26_2);
		
		JLabel lblReturn = new JLabel("Return");
		lblReturn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				goToMainScreen();
			}
		});
		lblReturn.setForeground(Color.BLACK);
		lblReturn.setBackground(Color.BLUE);
		lblReturn.setHorizontalAlignment(SwingConstants.LEFT);
		lblReturn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblReturn.setBounds(10, 0, 290, 50);
		layeredPane2.add(lblReturn);
		
		JSeparator separator21 = new JSeparator();
		separator21.setBounds(0, 200, 300, 5);
		layeredPane2.add(separator21);
		
		JSeparator separator22 = new JSeparator();
		separator22.setBounds(0, 250, 300, 5);
		layeredPane2.add(separator22);
		
		JSeparator separator23 = new JSeparator();
		separator23.setBounds(0, 300, 300, 5);
		layeredPane2.add(separator23);
		
		JSeparator separator24 = new JSeparator();
		separator24.setBounds(0, 350, 300, 5);
		layeredPane2.add(separator24);
		
		JSeparator separator25 = new JSeparator();
		separator25.setBounds(0, 400, 300, 5);
		layeredPane2.add(separator25);
		
		JSeparator separator26 = new JSeparator();
		separator26.setBounds(0, 450, 300, 5);
		layeredPane2.add(separator26);
		
		JSeparator separator20 = new JSeparator();
		separator20.setBounds(0, 150, 300, 5);
		layeredPane2.add(separator20);
		
		JLabel lblLabel20_1 = new JLabel("Image");
		lblLabel20_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblLabel20_1.setBounds(100, 50, 100, 70);
		layeredPane2.add(lblLabel20_1);
		
		JLabel lblLabel20_2 = new JLabel("Desc");
		lblLabel20_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLabel20_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblLabel20_2.setBounds(100, 120, 100, 30);
		layeredPane2.add(lblLabel20_2);
		
		JSeparator separator10 = new JSeparator();
		separator10.setBounds(0, 50, 300, 5);
		layeredPane1.add(separator10);
		
		JLabel lblSettingsImage = new JLabel("New label");
		lblSettingsImage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				goToSettingsScreen();
			}
		});
		lblSettingsImage.setBounds(260, 10, 30, 30);
		lblSettingsImage.setIcon(ImageIconScale.getScaledImageIcon(lblSettingsImage.getWidth(), lblSettingsImage.getHeight(), "settings_icon"));
		layeredPane1.add(lblSettingsImage);
		
		JSeparator separator27 = new JSeparator();
		separator27.setBounds(0, 50, 300, 5);
		layeredPane2.add(separator27);
		
		layeredPane3 = new JLayeredPane();
		layeredPane3.setBounds(620, 0, 300, 300);
		frmWeather.getContentPane().add(layeredPane3);
		
		JLabel lblSettings = new JLabel("\u2190     Settings");
		lblSettings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				goToMainScreenFromSettings();
			}
		});
		lblSettings.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSettings.setBounds(10, 0, 290, 50);
		layeredPane3.add(lblSettings);
		
		JLabel lblLabel3_1 = new JLabel("City (No Space)");
		lblLabel3_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLabel3_1.setBounds(10, 50, 120, 50);
		layeredPane3.add(lblLabel3_1);
		
		textFieldCity = new JTextField();
//		textFieldCity.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyTyped(KeyEvent e) {
//				System.out.println("+++ textFieldCIty");
//				Preferences.getInstance().setCity(textFieldCity.getText());
//			}
//		});
		textFieldCity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("+++ textFieldCIty");
				Preferences.getInstance().setCity(textFieldCity.getText());
				textFieldCity.setForeground(Color.BLUE);
			}
		});
		textFieldCity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textFieldCity.setBounds(130, 60, 170, 30);
		layeredPane3.add(textFieldCity);
		textFieldCity.setColumns(10);
		
		JLabel lblLabel3_2 = new JLabel("Units");
		lblLabel3_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLabel3_2.setBounds(10, 100, 70, 50);
		layeredPane3.add(lblLabel3_2);
		
		JRadioButton rdbtnUnits3_1 = new JRadioButton("Imperial (F)");
		rdbtnUnits3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				System.out.println("+++ rdbtnUnits3_1");
				Preferences.getInstance().setUnits("I");
			}
		});
		rdbtnUnits3_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnUnits3_1.setBounds(80, 110, 150, 30);
		layeredPane3.add(rdbtnUnits3_1);
		
		JRadioButton rdbtnUnits3_2 = new JRadioButton("Metric (C)");
		rdbtnUnits3_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				System.out.println("+++ rdbtnUnits3_2");
				Preferences.getInstance().setUnits("M");
			}
		});
		rdbtnUnits3_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnUnits3_2.setBounds(80, 140, 150, 30);
		layeredPane3.add(rdbtnUnits3_2);
		
		JRadioButton rdbtnUnits3_3 = new JRadioButton("Scientific (K)");
		rdbtnUnits3_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				System.out.println("+++ rdbtnUnits3_3");
				Preferences.getInstance().setUnits("S");
			}
		});
		rdbtnUnits3_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnUnits3_3.setBounds(80, 170, 150, 30);
		layeredPane3.add(rdbtnUnits3_3);
		
		ButtonGroup bg3 = new ButtonGroup();
		bg3.add(rdbtnUnits3_1);
		bg3.add(rdbtnUnits3_2);
		bg3.add(rdbtnUnits3_3);
		rdbtnUnits3_1.setSelected(true);
		
		JSeparator separator30 = new JSeparator();
		separator30.setBounds(0, 50, 300, 5);
		layeredPane3.add(separator30);
		
		Layout.addLabel1(lblLocation);
		Layout.addLabel1(lblLabel11_1);
		Layout.addLabel1(lblLabel11_2);
		Layout.addLabel1(lblLabel11_3);
		Layout.addLabel1(lblLabel11_4);
		Layout.addLabel1(lblLabel11_5);
		Layout.addLabel1(lblLabel12_1);
		Layout.addLabel1(lblLabel12_2);
		Layout.addLabel1(lblLabel12_3);
		Layout.addLabel1(lblLabel12_4);
		Layout.addLabel1(lblLabel12_5);
		Layout.addLabel1(lblLabel13_1);
		Layout.addLabel1(lblLabel13_2);
		Layout.addLabel1(lblLabel13_3);
		Layout.addLabel1(lblLabel13_4);
		Layout.addLabel1(lblLabel13_5);
		Layout.addLabel1(lblLabel14_1);
		Layout.addLabel1(lblLabel14_2);
		Layout.addLabel1(lblLabel14_3);
		Layout.addLabel1(lblLabel14_4);
		Layout.addLabel1(lblLabel14_5);
		Layout.addLabel1(lblLabel15_1);
		Layout.addLabel1(lblLabel15_2);
		Layout.addLabel1(lblLabel15_3);
		Layout.addLabel1(lblLabel15_4);
		Layout.addLabel1(lblLabel15_5);
		
		Layout.addLabel2(lblReturn);
		Layout.addLabel2(lblLabel20_1);
		Layout.addLabel2(lblLabel20_2);
		Layout.addLabel2(lblLabel21_2);
		Layout.addLabel2(lblLabel22_2);
		Layout.addLabel2(lblLabel23_2);
		Layout.addLabel2(lblLabel24_2);
		Layout.addLabel2(lblLabel25_2);
		Layout.addLabel2(lblLabel26_2);
		
		layeredPane1.setVisible(true);
		layeredPane2.setVisible(false);
		layeredPane3.setVisible(false);
		
	}
	
	private void refreshForecast() {
//		System.out.println("+++ refreshForecast");
		WeatherAPI.requestForecast(this);
	}
	
	private void showData(List<Forecast> data) {
//		System.out.println("+++ showData");
		forecastList = data;
		adapter.setData(data);
		listView.updateData();
	}
	
	private void showError(Exception err) {
		JOptionPane.showMessageDialog(null,  "<<< Error: " + err.getMessage());
	}
	
	@Override
	public void onResult(List<Forecast> data) {
//		System.out.println("+++ onResult");
		showData(data);
	}
	
	@Override
	public void onError(Exception e) {
		showError(e);
	}
	
	private void goToDetailsScreen(int n) {
		layeredPane1.setVisible(false);
		layeredPane2.setVisible(true);
		DetailsAdapter adapter = new DetailsAdapter();
		adapter.setData((forecastList.get(n)).getDetails());
		listView.setAdapter(adapter);
		listView.updateData();
	}
	
	private void goToMainScreen() {
		layeredPane2.setVisible(false);
		layeredPane1.setVisible(true);
		listView.setAdapter(adapter);
//		refreshForecast();
	}
	
	private void goToMainScreenFromSettings() {
		layeredPane3.setVisible(false);
		layeredPane2.setVisible(false);
		layeredPane1.setVisible(true);
		listView.setAdapter(adapter);
		if (preferencesModified) {
			preferencesModified = false;
			refreshForecast();
		}
	}
	
	private void goToSettingsScreen() {
		layeredPane1.setVisible(false);
		layeredPane2.setVisible(false);
		layeredPane3.setVisible(true);
		textFieldCity.setText(WeatherAPI.getCity());
	}
	
	@Override
	public void preferenceChanged(String key, Object value) {
		if (key == Preferences.CITY_KEY) WeatherAPI.setCity((String) value);
		// add the case for key == Preferences.UNITS_KEY
		preferencesModified = true;
	}
}
