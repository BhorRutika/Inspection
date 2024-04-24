import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import javax.swing.JSlider;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import java.lang.management.ManagementFactory;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;

public class Wait extends JFrame implements Runnable {

	String IndexNo;
	private JPanel contentPane;
	private JFrame frame;

	Response_Model rm = new Response_Model();
	Input_Model model = new Input_Model();
	Record_Model record = new Record_Model();

	Common common = new Common();
	public JProgressBar progressBar_1;

	HttpClient httpClient = HttpClient.newBuilder().build();
	Gson gson = new Gson();

	pbThread t1;
	String SYS_CNT;

	public String Mac;
	public String Ip;
	public String Screen;
	public String command = "ipconfig";

	/**
	 * Launch the application.
	 */

	class pbThread extends Thread {

		JProgressBar pbar;

		pbThread(JProgressBar pbar) {

			pbar = progressBar_1;
		}

		public void run() {
			int min = 0;
			int max = 100;

			for (int i = min; i <= max; i++) {
				progressBar_1.setValue(i);

				model.Index_No = IndexNo;
				model.SYS_No = (SYS_CNT + "");
				model.OS_Name = System.getProperty("os.name");
				model.MAC = Get_Mac();
				model.IP_Address = Get_IP();

				; // ----------------------------
				com.sun.management.OperatingSystemMXBean mxbean = (com.sun.management.OperatingSystemMXBean) ManagementFactory
						.getOperatingSystemMXBean();

				@SuppressWarnings("deprecation")
				long Ramsize = +mxbean.getTotalPhysicalMemorySize() / 1024 / 1024;
				model.Ram = String.valueOf(Ramsize);
				// ----------------------------
				long diskSize = new File("/").getTotalSpace();
				long diskspace = +diskSize / 1024 / 1024 / 1024;
				model.HDD = String.valueOf(diskspace);
				// ----------------------------
				model.Screen_Res = Get_Resolution();
				model.Extn_IP = Get_IP();
				model.Browser_Name = "Linux";
				model.IE_Version = "Linux";
				if (i == 100) {
					
					rm.IP_Address = model.IP_Address;
					System.out.println(record.IP_Address);
					rm.Index_No = "" + model.Index_No;
					rm.SYS_No = "" + model.SYS_No;
					rm.OS_Name = "" + model.OS_Name;
					rm.Ram = "" + model.Ram;
					rm.HDD = "" + model.HDD;
					rm.MAC = "" + model.MAC;
					rm.Browser_Name = "" + model.Browser_Name;
					rm.Extn_IP = "" + model.Extn_IP;
					rm.Screen_Res = "" + model.Screen_Res;
					rm.IE_Version = "" + model.IE_Version;

					String json = gson.toJson(rm);
					System.out.println(json);
					HttpRequest request = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(json))
							.header("Content-Type", "application/json").uri(URI.create(common.URL + "Add_Inspection"))
							.build();
					try {

						HttpResponse<String> respons = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
						System.out.println(respons.body());
						Response_Model reponce_model = gson.fromJson(respons.body(), Response_Model.class);
						
						
						reponce_model.IP_Address = "" + model.IP_Address;
						//System.out.println("---"+reponce_model.Records.IP_Address);
						reponce_model.Index_No = "" + model.Index_No;
						reponce_model.SYS_No = "" + model.SYS_No;
						reponce_model.OS_Name = "" + model.OS_Name;
						reponce_model.Ram = "" + model.Ram;
						reponce_model.HDD = "" + model.HDD;
						reponce_model.MAC = "" + model.MAC;
						reponce_model.Browser_Name = "" + model.Browser_Name;
						reponce_model.Extn_IP = "" + model.Extn_IP;
						reponce_model.Screen_Res = "" + model.Screen_Res;
						reponce_model.IE_Version = "" + model.IE_Version;

						System.out.println("///*****////" + json + respons.body());

						if (reponce_model.Result.equals("Success")) {
							dispose();
							Config nw = new Config(IndexNo, SYS_CNT);
							nw.show();
							nw.setVisible(true);
							nw.setLocationRelativeTo(null);

						}

					} catch (IOException e1) {
						JFrame f = new JFrame();
						JOptionPane.showMessageDialog(f, "Error:" + e1);

					} catch (InterruptedException e1) {
						JFrame f = new JFrame();
						JOptionPane.showMessageDialog(f, "Error:" + e1);
					}
				}

				try {
					sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	/**
	 * Create the application.
	 */

	// Constructor
	public Wait(String Index_No, String System_Count) {
		// frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\lenovo\\Desktop\\Images\\ssc-pune-logo.png"));
		initialize(Index_No, System_Count);
		IndexNo = Index_No;
		System.out.println(Index_No+IndexNo);
		SYS_CNT = System_Count;
		System.out.println("---"+SYS_CNT);
		t1 = new pbThread(progressBar_1);
		t1.start();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String Index_No, String system_Count) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Inspection Basic Info");

		ImageIcon icon = new ImageIcon(this.getClass().getResource("/Image/ssc-pune-logo.png"));
		setIconImage(icon.getImage());
		setSize(558, 420);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 239, 213));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel background;
		JLabel lblNewLabel = new JLabel("");

		lblNewLabel.setBackground(new Color(255, 235, 205));
		lblNewLabel.setBounds(0, 0, 494, 372);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		background = new JLabel("");
		background.setIcon(new ImageIcon(getClass().getResource("/Image/MSBSHSE-Opacity.png")));
		background.setFont(new Font("Tahoma", Font.BOLD, 11));
		background.setBackground(Color.GRAY);
		background.setBounds(164, 0, 378, 381);
		contentPane.add(background);
		contentPane.setLayout(null);

		// setting current date and time
		long millis = System.currentTimeMillis();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		JLabel lblNewLabel_1 = new JLabel("Date:-" + formatter.format(millis));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(333, 23, 127, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_3 = new JLabel("MARCH-" + common.Year() + "  Online Exam Inspection Tool");
		lblNewLabel_3.setBackground(new Color(255, 239, 213));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(107, 69, 353, 27);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_2 = new JLabel("You are Inspecting System");
		lblNewLabel_2.setForeground(new Color(128, 0, 0));
		lblNewLabel_2.setBackground(new Color(153, 50, 204));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(164, 127, 186, 27);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_4 = new JLabel("Wait For  Some Time This Tool Looking For Your System");
		lblNewLabel_4.setForeground(new Color(128, 0, 0));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(85, 205, 407, 32);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Copyright" + common.Year()
				+ "@Maharashtra State Board of Secondary  and  Higher Secondary  Education");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_5.setBounds(10, 356, 542, 14);
		contentPane.add(lblNewLabel_5);

		progressBar_1 = new JProgressBar();
		progressBar_1.setForeground(new Color(50, 205, 50));
		progressBar_1.setBounds(170, 261, 180, 14);
		contentPane.add(progressBar_1);

	}

	// Getting Mac_ID for Linux and Windows
	public String Get_Mac() {
		// for linux
		if (System.getProperty("os.name").startsWith("Linux")) {
			try {
				Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
				while (networkInterfaces.hasMoreElements()) {
					NetworkInterface networkInterface = networkInterfaces.nextElement();
					byte[] mac = networkInterface.getHardwareAddress();
					if (mac != null) {
						// System.out.print("MAC address : ");
						StringBuilder stringBuilder = new StringBuilder();
						for (int i = 0; i < mac.length; i++) {
							stringBuilder.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
						}
						// System.out.println("......."+stringBuilder.toString());
						Mac = stringBuilder.toString();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// MacId detection for Windows
		else {
			try {
				InetAddress address = InetAddress.getLocalHost();
				NetworkInterface na = NetworkInterface.getByInetAddress(address);

				byte[] mac = na.getHardwareAddress();
				for (int j = 0; j < mac.length; j++) {
					System.out.print("");
				}
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < mac.length; i++) {
					sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
				}
				Mac = sb.toString();

			} catch (Exception exe) {
				exe.printStackTrace();
			}
		}
		return Mac;
	}

	public String Get_Resolution() {
		try {
			Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
			int width = (int) size.getWidth();
			int height = (int) size.getHeight();
			Screen = String.valueOf(width) + "*" + String.valueOf(height);

		} catch (Exception exe) {
			return "";
		}
		return Screen;
	}

	public String Get_IP() {
		try {
			InetAddress address = InetAddress.getLocalHost();

			return address.toString();
		} catch (Exception exe) {
			return "";

		}
	}

	@Override
	public void run() {
	}

}
