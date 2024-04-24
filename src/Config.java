
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JTextField;

public class Config extends JFrame implements Runnable, ActionListener {

	private JFrame frame;
	private JPanel contentPane;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblCindex;
	public String Mac;
	public String Ip;
	public String Screen;
	public String command = "ipconfig";
	private JButton btn_Submit;
	Gson gson = new Gson();
	HttpClient httpClient = HttpClient.newBuilder().build();
	Common common = new Common();
	String path = System.getProperty("user.home")+File.separator + common.FolderName;
	
	
	/**
	 * Create the application.
	 * 
	 * @param Index_No
	 */
	public Config(String Index_No, String SYS_CNT) {
		try {
			initialize(Index_No, SYS_CNT);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @param Index_No
	 */
	public void initialize(String Index_No, String SYS_CNT) throws IOException

	{

		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false); 
		setTitle("Inspection tool");
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/Image/ssc-pune-logo.png"));
		setIconImage(icon.getImage());
		setLocationRelativeTo(null);
		setSize(628, 564);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 239, 213));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel background;
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(255, 235, 205));
		lblNewLabel.setBounds(0, 0, 494, 372);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		background = new JLabel("");
		background.setIcon(new ImageIcon (getClass().getResource("/Image/MSBSHSE-Opacity.png")));
		background.setFont(new Font("Tahoma", Font.BOLD, 11));
		background.setBackground(Color.GRAY);
		background.setBounds(203, 58, 328, 449);
		contentPane.add(background);
		contentPane.setLayout(null);

		long millis = System.currentTimeMillis();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		lblNewLabel_1 = new JLabel("Date :-"+formatter.format(millis));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(450, 25, 136, 22);
		contentPane.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("MARCH-"+common.Year()+"  Online Exam Inspection Tool");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(115, 69, 373, 27);
		contentPane.add(lblNewLabel_2);

		lblCindex = new JLabel("Index No   :-" );
		lblCindex.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCindex.setBounds(36, 147, 147, 22);
		contentPane.add(lblCindex);
		
		JLabel lblSysNO = new JLabel("System No   :-");
		lblSysNO.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSysNO.setBounds(36, 180, 120, 22);
		contentPane.add(lblSysNO);

		JLabel lblOs = new JLabel("Operating System   :-");
		lblOs.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblOs.setBounds(36, 213, 171, 22);
		contentPane.add(lblOs);

		JLabel lblRAM = new JLabel("RAM Size     :-");
		lblRAM.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRAM.setBounds(36, 246, 147, 22);
		contentPane.add(lblRAM);

		JLabel lblHDD = new JLabel("HDD Size   :-");
		lblHDD.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHDD.setBounds(36, 284, 147, 22);
		contentPane.add(lblHDD);

		JLabel lblIP = new JLabel("IP Address    :-");
		lblIP.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIP.setBounds(39, 324, 144, 27);
		contentPane.add(lblIP);

		JLabel lblMAC = new JLabel("MAC ID  :-");
		lblMAC.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMAC.setBounds(39, 362, 117, 22);
		contentPane.add(lblMAC);

		JLabel lblScreen = new JLabel("Screen  Resolution   :-");
		lblScreen.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblScreen.setBounds(39, 395, 168, 27);
		contentPane.add(lblScreen);

		JLabel lblNewLabel_13 = new JLabel(
				"Copyright "+common.Year()+"@Maharashtra State Board of Secondary  and  Higher Secondary  Education");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_13.setBounds(30, 487, 572, 51);
		contentPane.add(lblNewLabel_13);

		btn_Submit = new JButton("EXIT");
		btn_Submit.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_Submit.setBounds(240, 438, 107, 38);
		contentPane.add(btn_Submit);

		JLabel lblINDEX = new JLabel("");
		lblINDEX.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblINDEX.setBounds(223, 153, 136, 16);
		contentPane.add(lblINDEX);
		lblINDEX.setText(Index_No);
		//System.out.println(Index_No);
		
		JLabel lblSYSTEM = new JLabel("");
		lblSYSTEM.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSYSTEM.setBounds(223, 186, 124, 14);
		contentPane.add(lblSYSTEM);
		
		lblSYSTEM.setText(SYS_CNT);
		//System.out.println(SYS_CNT);
		
		String Os = common.OsName_Version();
		JLabel lblOprating_System = new JLabel(""+Os);
		lblOprating_System.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblOprating_System.setBounds(223, 219, 136, 14);
		contentPane.add(lblOprating_System);

		com.sun.management.OperatingSystemMXBean mxbean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		//System.out.println(mxbean.getTotalPhysicalMemorySize() + " Bytes "); 
		long Ramsize = +mxbean.getTotalPhysicalMemorySize() / 1024 / 1024;
		String Ram=String.valueOf(Ramsize);
		
		JLabel lblRamSize = new JLabel(""+Ram+"MB");
		lblRamSize.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRamSize.setBounds(223, 252, 107, 16);
		contentPane.add(lblRamSize);
		
		long diskSize = new File("/").getTotalSpace();
		long diskspace = +diskSize / 1024 / 1024 / 1024;
		String Disk=String.valueOf(diskspace);
		
		JLabel lblHDD_SIZE = new JLabel(""+Disk+"GB");
		lblHDD_SIZE.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHDD_SIZE.setBounds(223, 284, 107, 16);
		contentPane.add(lblHDD_SIZE);

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
	              //System.out.println("......."+stringBuilder.toString());
	              Mac = stringBuilder.toString();
	           }
	        }
	     } catch (Exception e) {
	        e.printStackTrace();
	     }

		  try {
				InetAddress address = InetAddress.getLocalHost();
				NetworkInterface na = NetworkInterface.getByInetAddress(address);

				byte[] mac = na.getHardwareAddress();
				for (int j = 0; j < mac.length; j++) 
				{
					 //System.out.print("MAC address : ");
				}
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < mac.length; i++) 
				{
					sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
				}
				Mac = sb.toString();
				
			} catch (Exception exe) {
				exe.printStackTrace();
				 }
	  
		JLabel lblMAC_ID = new JLabel(Mac);
		lblMAC_ID.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMAC_ID.setBounds(223, 368, 328, 16);
		contentPane.add(lblMAC_ID);
		//lblMAC_ID.setText(Mac);
		
		//System.out.println(Inet4Address.getLocalHost().getHostAddress());
		JLabel lblIP_ADDRESS = new JLabel(""+Inet4Address.getLocalHost().getHostAddress());
		lblIP_ADDRESS.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIP_ADDRESS.setBounds(223, 324, 302, 16);
		contentPane.add(lblIP_ADDRESS);

		  Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
	       int width = (int)size.getWidth();
	       int height = (int)size.getHeight();
	       Screen = String.valueOf(width)+"*"+String.valueOf(height);
		JLabel lblScreen_Re = new JLabel(""+Screen);
		lblScreen_Re.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblScreen_Re.setBounds(223, 405, 190, 22);
		contentPane.add(lblScreen_Re);
		lblScreen_Re.setText(Screen);

		GetDetail_Model model = new GetDetail_Model();
		model.Index_No = Index_No;
		model.SYS_CNT = SYS_CNT;
		String json = gson.toJson(model);
		HttpRequest request = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(json))
				.header("Content-Type", "application/json").uri(URI.create(common.URL + "Get_Inspection_Record"))
				.build();
		try {
			HttpResponse<String> respons = httpClient.send(request, HttpResponse.BodyHandlers.ofString()); //
			/* common.ShowMessage(respons.body()); */
			Response_Model reponce_model = gson.fromJson(respons.body(), Response_Model.class);

			if (reponce_model.Result.equals("Success")) {

				if (reponce_model.Records != null) {
					
					lblINDEX.setText(reponce_model.Records.Index_No);
					lblSYSTEM.setText(reponce_model.Records.SYS_No);
					lblOprating_System.setText(reponce_model.Records.OS_Name);
					lblRamSize.setText(reponce_model.Records.Ram);
					lblHDD_SIZE.setText(reponce_model.Records.HDD);
					lblMAC_ID.setText(reponce_model.Records.MAC);
					lblIP_ADDRESS.setText(reponce_model.Records.Extn_IP);
					lblScreen_Re.setText(reponce_model.Records.Screen_Res);
        	}
				setVisible(false);
				setLocationRelativeTo(null);
				dispose();

			} else {
				common.ShowMessage("Invalid Index Number");
				return;
			}
		} catch (IOException e1) {
			JFrame f = new JFrame();
			JOptionPane.showMessageDialog(f, "Error:" + e1);

		} catch (InterruptedException e1) {
			JFrame f = new JFrame();
			JOptionPane.showMessageDialog(f, "Error:" + e1);

		}
		// --------------------------------------------------------------------------------------------------------------------------------------------------------
		File customDir = new File(path);

		if (customDir.exists()) {
			//System.out.println(customDir + " already exists");
		} else if (customDir.mkdirs()) {
			//System.out.println(customDir + " was created");
		} else {
			//System.out.println(customDir + " was not created");
		}
		// customDir.setReadOnly();

		FileWriter fileWriter;
		try {
			String Encstring = AESCipher.aesEncryptString(Index_No, common.Key);
			JTextField text = new JTextField();
			text.getText();
			text.setText(Encstring);
			
			String file = path + "/Index_No.txt";
			fileWriter = new FileWriter(file);
			text.write(fileWriter);
			fileWriter.close();
		} catch (Exception e2) { 
			// TODO Auto-generated catch block
			e2.printStackTrace();

		} 
		btn_Submit.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// TODO Auto-generated method stub

		if (e.getSource() == btn_Submit) {

		     setVisible(false);
		     common.ShowMessage("This Machine Inspected Successfully!!!");

		     System.exit(0);

		}

	}
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}

